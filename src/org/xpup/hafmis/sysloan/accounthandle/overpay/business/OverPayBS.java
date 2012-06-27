package org.xpup.hafmis.sysloan.accounthandle.overpay.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.bsinterface.IOverPayBS;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTaDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTbFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTbShowListDTO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.security.common.domain.Userslogincollbank;

public class OverPayBS implements IOverPayBS {
  private BorrowerAccDAO borrowerAccDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private CollParaDAO collParaDAO = null;

  private LoanBankDAO loanBankDAO = null;

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * ���˰���
   * 
   * @author ���ƽ 2007-9-25 ���ݴ����˺Ų�ѯ����ҳ����������� ��ѯ������loadkouacc
   */
  public OverPayTaDTO findOverPayTaInfo(String loadkouacc,
      SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    OverPayTaDTO overPayTaDTO = null;
    List loanbankList1 = null;
    String bankDate = "";// ��������
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    try {
      // ȡ���û�Ȩ�޷ſ�����
      List loanbankList = securityInfo.getDkUserBankList();
      loanbankList1 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanbankList1.add(bank.getCollBankId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      overPayTaDTO = borrowerAccDAO
          .queryOrgPayTaInfo(loadkouacc, loanbankList1);
      if (overPayTaDTO != null) {
        bankDate = loanBankDAO.queryPL002BizDate_jj(overPayTaDTO
            .getLoanBankId());
        if (!bizDate.equals(bankDate)) {
          throw new BusinessException("��¼����������ҵ�����ڲ�һ�£�������ҵ��");
        }
        overPayTaDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
            .parseInt(overPayTaDTO.getLoanMode().toString()),
            BusiConst.PLRECOVERTYPE));
        overPayTaDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer
            .parseInt(overPayTaDTO.getCardKind().toString()),
            BusiConst.DOCUMENTSSTATE));
      } else {
        throw new BusinessException("�ô����˺Ų����ڣ����������룡");
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return overPayTaDTO;
  }

  /**
   * ���˰���
   * 
   * @author ���ƽ 2007-9-25 �������
   */
  public void saveOverPayTa(OverPayTaDTO overPayTaDTO, SecurityInfo securityInfo)
      throws Exception {
    try {
      // �ж���ͬ�Ĵ����˺����Ƿ����δ����ҵ��ҵ�����Ͱ���2��3��4��5��6��7��8��9��12��13
      int count = borrowerAccDAO.findBorrowerAccByLoanKouAcc(overPayTaDTO
          .getLoankouacc());
      if (count > 0) {
        throw new BusinessException("�ô����˺��´���δ���˵�ҵ�񣬲������ٹ���!");
      }
      // ����pl202
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());
      loanFlowHead.setBizType(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY));
      loanFlowHead.setRealCount(new BigDecimal(1));
      loanFlowHead.setOccurMoney(overPayTaDTO.getOverpayMoney());
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = overPayTaDTO.getOfficecode();
      } else {
        officeId = overPayTaDTO.getLoanBankId().toString();
      }
      String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 6);
      String loanbankid = overPayTaDTO.getLoanBankId();
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanbankid,
          bizYearmonth.substring(0, 4));
      Map office = securityInfo.getOfficeInnerCodeMap();
      String officecode = office.get(officeId).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      docNum = bizYearmonth.substring(0, 4) + officecode + "0" + loanbankid
          + docNum;
      loanFlowHead.setDocNum(docNum);
      // String docnum=plDocNumMaintainDAO.getDocNumdocNum(officeId,
      // securityInfo.getUserInfo().getPlbizDate().substring(0,6));
      // loanFlowHead.setDocNum(docnum);
      loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      loanFlowHead.setLoanBankId(new BigDecimal(overPayTaDTO.getLoanBankId()));
      loanFlowHead.setMakePerson(securityInfo.getUserName());
      loanFlowHead.setIsFinance(new Integer("1"));
      Serializable flowHeadId = loanFlowHeadDAO.insert(loanFlowHead);
      // ϵͳ�Զ����ɽ���ţ�ҵ������+��ˮ��
      String noteNum = "";
      String bizDate = securityInfo.getUserInfo().getBizDate();
      noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
      loanFlowHead.setNoteNum(noteNum);// ����ˮ�Ų嵽Ʊ�ݱ����
      // ����pl203
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId.toString()));
      loanFlowTail.setLoanKouAcc(overPayTaDTO.getLoankouacc());
      loanFlowTail.setContractId(overPayTaDTO.getContractId());
      loanFlowTail.setOccurMoney(overPayTaDTO.getOverpayMoney());
      loanFlowTail.setShouldCorpus(new BigDecimal(0.00));
      loanFlowTail.setShouldInterest(new BigDecimal(0.00));
      loanFlowTail.setShouldPunishInterest(new BigDecimal(0.00));
      loanFlowTail.setRealCorpus(new BigDecimal(0.00));
      loanFlowTail.setRealInterest(new BigDecimal(0.00));
      loanFlowTail.setRealPunishInterest(new BigDecimal(0.00));
      loanFlowTail.setTempPunishInterest(new BigDecimal(0.00));
      loanFlowTailDAO.insert(loanFlowTail);
      // ����pl020
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      plBizActiveLog.setBizid(new BigDecimal(flowHeadId.toString()));
      plBizActiveLog.setAction(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(securityInfo.getUserName());
      plBizActiveLog.setType(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY));
      plBizActiveLogDAO.insert(plBizActiveLog);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_DO));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_CONFIRM));
      plOperateLog.setOpBizId(new BigDecimal(flowHeadId.toString()));
      plOperateLog.setContractId(overPayTaDTO.getContractId());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("����ʧ��!");
    }
  }

  /**
   * ����ά��
   * 
   * @author ���ƽ 2007-9-27 ��ʾ�б�
   */
  public Object[] findOverPayTbList(Pagination pagination, List loanbankList)
      throws Exception {
    OverPayTbFindDTO overPayTbFindDTO = (OverPayTbFindDTO) pagination
        .getQueryCriterions().get("overPayTbFindDTO");
    String type = "";
    if (overPayTbFindDTO == null) {
      overPayTbFindDTO = new OverPayTbFindDTO();
    }
    if (pagination.getQueryCriterions().get("type") != null) {
      type = (String) pagination.getQueryCriterions().get("type");
    }
    String orderBy = (String) pagination.getOrderBy();
    ;
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List resultList = null;
    List list = borrowerAccDAO.queryOverPayTbList(overPayTbFindDTO,
        loanbankList, type, orderBy, order, start, pageSize, page);
    resultList = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      OverPayTbShowListDTO overPayTbShowListDTO = (OverPayTbShowListDTO) list
          .get(i);
      overPayTbShowListDTO.setTemp_bizSt(BusiTools.getBusiValue(Integer
          .parseInt(overPayTbShowListDTO.getBizSt().toString()),
          BusiConst.PLBUSINESSSTATE));
      resultList.add(overPayTbShowListDTO);
    }
    int count = 0;
    BigDecimal occurMoneySum = new BigDecimal(0.00);
    List countlist = borrowerAccDAO.queryOverPayTbListCount(overPayTbFindDTO,
        loanbankList, type);
    if (countlist != null) {
      for (int i = 0; i < countlist.size(); i++) {
        OverPayTbShowListDTO overPayTbShowListDTO = (OverPayTbShowListDTO) countlist
            .get(i);
        occurMoneySum = occurMoneySum.add(overPayTbShowListDTO.getOccurMoney());
      }
      count = countlist.size();
    }
    pagination.setNrOfElements(count);
    Object obj[] = new Object[2];
    obj[0] = resultList;
    obj[1] = occurMoneySum;
    return obj;
  }

  /**
   * ����ά��
   * 
   * @author ���ƽ 2007-9-28 ɾ����¼
   */
  public void deleteOverPayTbList(String flowHeadId, String contractId,
      SecurityInfo securityInfo) throws Exception {
    try {
      // ��ѯ������¼�Ƿ����
      LoanFlowHead loanFlowHead = null;
      loanFlowHead = loanFlowHeadDAO.queryById(new Integer(flowHeadId));
      if (loanFlowHead == null) {
        throw new BusinessException("������¼�Ѿ������ڣ�������ɾ����");
      } else {
        // ����Ҫɾ����ˮͷβ��
        List temp_FlowHeadInfo = new ArrayList();
        temp_FlowHeadInfo = loanFlowTailDAO.queryLoanFlowHeadInfo(flowHeadId);
        Object[] info = (Object[]) temp_FlowHeadInfo.get(0);
        // ������ƾ֤��
        String cancelcredenceid = info[1] + "";
        // ƾ֤����������
        String yearMonth = info[2] + "";
        // ���´�
        String officeCode = info[3] + "";
        // ����ƾ֤��
        String officeId = "";
        String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
        if (loanDocNumDocument.equals("1")) {
          officeId = officeCode;
        } else {
          officeId = info[4] + "";
        }
//        plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid, officeId,
//            yearMonth.substring(0, 6));
        plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid
            .substring(8, 12), cancelcredenceid.substring(7, 8),
            cancelcredenceid.substring(0, 4));
        // ɾ��β���¼203
        loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);
        // ɾ��ͷ���¼202
        loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
        // ɾ��ҵ����־pl020
        plBizActiveLogDAO.deletePlBizActiveLog(flowHeadId);
        // ���������־pl021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_MAINTAIN));
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId.toString()));
        plOperateLog.setContractId(contractId);
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("ɾ��ʧ��!");
    }
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
}
