package org.xpup.hafmis.sysloan.specialbiz.bailenrol.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaPrintDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTbAF;

/**
 * @author ��Ұ 2007-10-02
 */
public class BailenRolBS implements IBailenRolBS {

  private BorrowerDAO borrowerDAO = null;// �������Ϣ�� PL110

  private BorrowerAccDAO borrowerAccDAO = null;// ������˻��� PL111

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// ��ˮͷ�� PL202

  private LoanFlowTailDAO loanFlowTailDAO = null;// ��ˮβ�� PL203

  private PlBizActiveLogDAO plBizActiveLogDAO = null;// ҵ����־ PL020

  private PlOperateLogDAO plOperateLogDAO = null;// ������־ PL021

  private CollBankDAO collBankDAO = null;// ת����������

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null;

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
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

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  /**
   * Description ��֤��Ǽǰ���
   * 
   * @author wangy 2007-10-02
   * @param ͨ����ͬ��Ŵ���ҳ����Ϣ
   * @param ��BailenRolTaFindAAC����
   * @throws Exception, BusinessException
   */
  public BailenRolTaAF queryContractInfo(String id, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BailenRolTaAF bailenRolTaAF = new BailenRolTaAF();
    boolean isContractId = false;
    try {
      isContractId = borrowerAccDAO.isExistsBorrowerAccByContractId(id,
          securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (isContractId) {
      List bailenRolTalist = borrowerAccDAO.queryBailenRolInfoByContractId(id,
          securityInfo);
      if (bailenRolTalist.size() > 0) {
        Iterator iterate = bailenRolTalist.iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          if (obj[0] != null && !obj[0].equals(""))
            bailenRolTaAF.setContractId(obj[0].toString());
          if (obj[1] != null && !obj[1].equals(""))
            bailenRolTaAF.setBorrowerName(obj[1].toString());
          String cardKind = null;
          if (obj[2] != null && !obj[2].equals(""))
            cardKind = obj[2].toString();
          if (obj[3] != null && !obj[3].equals(""))
            bailenRolTaAF.setCardNum(obj[3].toString());
          if (obj[4] != null && !obj[4].equals("")) {
            bailenRolTaAF.setLoanBankName(obj[4].toString());
          }
          // �����˺�-�ŵ�ҳ���������� 2007-11-12�޸�
          if (obj[5] != null && !obj[5].equals(""))
            bailenRolTaAF.setLoanKouAccHidden(obj[5].toString());
          if (obj[6] != null && !obj[6].equals(""))
            bailenRolTaAF.setLoanKouAcc(obj[6].toString());
          // ö��ת��֤������
          cardKind = BusiTools.getBusiValue(Integer.parseInt(cardKind),
              BusiConst.DOCUMENTSSTATE);
          bailenRolTaAF.setCardKind(cardKind);
        }
      }
    } else {
      throw new BusinessException("�ú�ͬ��Ų����ڣ����������룡");
    }
    return bailenRolTaAF;
  }

  /**
   * Description ��֤��Ǽǰ���
   * 
   * @author wangy 2007-11-12
   * @param ͨ������ID�����ſ������˺�
   * @param ��BailenRolTaFindBankAccAAC����
   * @throws Exception
   */
  public String queryBailenRolTaBankAccByBankId(String bankId) throws Exception {
    String loanAcc = "";
    try {
      loanAcc = loanBankDAO.queryBailenRolTaBankAccByBankId(bankId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanAcc;
  }

  /**
   * Description ��֤��Ǽǰ���
   * 
   * @author wangy 2007-10-02
   * @param ���뱣֤��Ǽ������Ϣ��ҳ����Ϣ��
   * @param ��BailenRolTaSaveAC����
   * @throws Exception, BusinessException
   */
  public BailenRolTaPrintDTO saveBailenRol(BailenRolTaDTO bailenRolTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BailenRolTaPrintDTO bailenRolTaPrintDTO = new BailenRolTaPrintDTO();
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
    String contractId = bailenRolTaDTO.getContractId();
    String borrowerName = bailenRolTaDTO.getBorrowerName();
    String cardKind = bailenRolTaDTO.getCardKind();
    String cardNum = bailenRolTaDTO.getCardNum();
    String loanBankName = bailenRolTaDTO.getLoanBankName();// ����ID
    String loanBankIdHidden = bailenRolTaDTO.getLoanBankId();// ����ID(������)
                                                              // 2007-11-12�޸�
    String loanKouAcc = bailenRolTaDTO.getLoanKouAcc();// �տ��˺�
    String loanKouAccHidden = bailenRolTaDTO.getLoanKouAccHidden();// �����˺�
    String bizDate = bailenRolTaDTO.getBizDate();
    BigDecimal occurMoney = bailenRolTaDTO.getOccurMoney();// ��֤����
    // �ж���ͬ�Ĵ����˺����Ƿ����δ���˵ı�֤��Ǽ�ҵ��BIZ_TYPE=14��BIZ_ST��=6��
    boolean flowHead = loanFlowTailDAO
        .isExistsLoanFlowHeadByLoanKouAcc(loanKouAccHidden);// 2007-11-12�޸�
    if (flowHead) {
      throw new BusinessException("�ô����˺��´���δ���˵ı�֤��ҵ�񣬲������ٰ���!");
    } else {
      // �޸ļ�¼����֤��Ǽ�ʱҳ��ķſ����кͷſ������ʺŲ��뵽PL110�еı�ѡ�ֶ�A��B 2007-11-12(��������ۺ����)
      // ���½�����˻���PL110
      Borrower borrower = borrowerDAO.queryById(contractId);
      borrower.setReserveaA(loanBankName);
      borrower.setReserveaB(loanKouAcc);
      // ȡƾ֤��
      CollBank collBank = collBankDAO
          .getCollBankByCollBankid_(loanBankIdHidden);// 2007-11-12�޸�
      String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 6);
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(collBank.getOffice(),
          bizYearmonth);
      // ���������ˮ��ͷ��PL202
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      loanFlowHead.setBizDate(bizDate);
      loanFlowHead.setBizType(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN)
          .toString());// ҵ������ 14����֤��
      loanFlowHead.setRealCount(new BigDecimal(1));// ʵ������
      loanFlowHead.setOccurMoney(occurMoney);// �������
      loanFlowHead.setDocNum(docNum);
      loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_SURE)
          .toString());// ҵ��״̬
      // 4��ȷ�ϣ�
      loanFlowHead.setLoanBankId(new BigDecimal(loanBankIdHidden));// 2007-11-12�޸�
      loanFlowHead.setMakePerson(operateName);
      loanFlowHead.setIsFinance(new Integer(1));// PL202�е�isFinance��1
      String flowHeadId = loanFlowHeadDAO.insert(loanFlowHead).toString();// ����PL202
      // ������flow_head_id
      // ����ˮͷ�����Ʊ�ݺ�
      loanFlowHead.setNoteNum(flowHeadId);
      // ���������ˮ��β��PL203
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
      loanFlowTail.setLoanKouAcc(loanKouAccHidden);// ����PL203�����˺� 2007-11-12�޸�
      loanFlowTail.setContractId(contractId);
      loanFlowTail.setOccurMoney(occurMoney);
      loanFlowTailDAO.insert(loanFlowTail);
      // ����ҵ����־PL020
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
      plBizActiveLog.setAction(new Integer(BusiConst.BUSINESSSTATE_SURE)
          .toString());// action 4
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(operateName);
      plBizActiveLog.setType(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN)
          .toString());// type 14
      plBizActiveLogDAO.insert(plBizActiveLog);
      // ���������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ���� 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO).toString());// ��֤��Ǽ�-ҵ�����
      // 73
      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_CONFIRM)
          .toString());// ȷ�� 11
      plOperateLog.setOpBizId(new BigDecimal(flowHeadId));// PL202.FLOW_HEAD_ID
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
      // ��ô�ӡƾ֤����
      bailenRolTaPrintDTO.setContractId(contractId);
      bailenRolTaPrintDTO.setBorrowerName(borrowerName);
      bailenRolTaPrintDTO.setCardKind(cardKind);
      bailenRolTaPrintDTO.setCardNum(cardNum);
      bailenRolTaPrintDTO.setLoanBankName(loanBankName);
      bailenRolTaPrintDTO.setLoanKouAcc(loanKouAcc);
      bailenRolTaPrintDTO.setBizDate(bizDate);
      bailenRolTaPrintDTO.setOccurMoney(occurMoney);
      bailenRolTaPrintDTO.setDocNum(docNum);
      bailenRolTaPrintDTO.setNoteNum(flowHeadId);
    }
    return bailenRolTaPrintDTO;
  }

  /**
   * Description ��֤��Ǽ�ά��
   * 
   * @author wangy 2007-10-03
   * @param ����������ѯ��֤��Ǽ�ά���б�
   * @param ��BailenRolTbShowAC����
   * @return BailenRolTbAF
   * @throws Exception, BusinessException
   */
  public BailenRolTbAF queryBailenRolListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    BigDecimal occurTotleMoney = new BigDecimal(0.00);// ��֤����-�ܶ�
    String findType = (String) pagination.getQueryCriterions().get("findType");
    if (findType == null || "".equals(findType)) {
      findType = "(4)";// Ĭ���б�
    } else {
      findType = "(4, 5, 6)";// ��ѯ
    }
    BailenRolTbAF bailenRolTbAF = new BailenRolTbAF();
    List bailerrolTbList = new ArrayList();
    List otherList = new ArrayList();
    List templist = new ArrayList();
    bailerrolTbList = loanFlowHeadDAO.queryBailenRolListByCriterions(
        contractId, borrowerName, cardNum, loanBankName, bizSt, findType,
        start, orderBy, order, pageSize, page, securityInfo);
    // count = loanFlowHeadDAO.queryBailenRolCountByCriterions(contractId,
    // borrowerName, cardNum, loanBankName, bizSt, findType, securityInfo);
    // occurTotleMoney = loanFlowHeadDAO.queryBailenRolSumMoneyByCriterions(
    // contractId, borrowerName, cardNum, loanBankName, bizSt, findType,
    // securityInfo);
    otherList = loanFlowHeadDAO.queryBailenRolSumMoneyByCriterions(contractId,
        borrowerName, cardNum, loanBankName, bizSt, findType, securityInfo);
    if (!otherList.isEmpty()) {
      BailenRolTbDTO dto = (BailenRolTbDTO) otherList.get(0);
      count = dto.getCount().intValue();
      occurTotleMoney = dto.getOccurMoneyTotal();

    }
    Iterator iterate = bailerrolTbList.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      BailenRolTbDTO bailenRolTbDTO = new BailenRolTbDTO();
      obj = (Object[]) iterate.next();
      if (obj[0] != null && !obj[0].equals(""))
        bailenRolTbDTO.setContractId(obj[0].toString());
      if (obj[1] != null && !obj[1].equals(""))
        bailenRolTbDTO.setBorrowerName(obj[1].toString());
      if (obj[2] != null && !obj[2].equals(""))
        bailenRolTbDTO.setCardNum(obj[2].toString());
      String loanBankId = null;
      if (obj[3] != null && !obj[3].equals(""))
        loanBankId = obj[3].toString();
      if (obj[4] != null && !obj[4].equals(""))
        bailenRolTbDTO.setBizDate(obj[4].toString());
      if (obj[5] != null && !obj[5].equals(""))
        bailenRolTbDTO.setOccurMoney(obj[5].toString());
      if (obj[6] != null && !obj[6].equals(""))
        bizSt = obj[6].toString();
      if (obj[7] != null && !obj[7].equals(""))
        bailenRolTbDTO.setFlowHeadId(obj[7].toString());
      // ת����������
      try {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
            .toString());
        bailenRolTbDTO.setLoanBankName(dto.getCollBankName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // ö��ת��ҵ��״̬
      try {
        bailenRolTbDTO.setBizSt(BusiTools.getBusiValue(Integer.parseInt(""
            + bizSt), BusiConst.PLBUSINESSSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      templist.add(bailenRolTbDTO);
    }
    pagination.setNrOfElements(count);
    if (occurTotleMoney != null && !occurTotleMoney.equals("")) {
      bailenRolTbAF.setOccurTotleMoney(occurTotleMoney);
    }
    bailenRolTbAF.setList(templist);
    bailenRolTbAF.setCount(new Integer(count));
    return bailenRolTbAF;
  }

  /**
   * Description ��֤��Ǽ�ά��
   * 
   * @author wangy 2007-10-04
   * @param ɾ����֤��Ǽ�ά���б�
   * @param ��BailenRolTbMaintainAC����
   * @param flowHeadId
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void deleteBailenRolInfo(String flowHeadId, String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // try {
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
    // ͨ��������ˮ��ͷ��ID��ѯҵ��״̬
    String bizSt = loanFlowHeadDAO.queryBizStByFlowHeadId(flowHeadId);
    if (bizSt.equals("4")) {// ҵ��״̬ 4 ȷ��
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
      plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid, officeCode,
          yearMonth.substring(0, 6));
      // ɾ��β��PL203
      loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);
      // ɾ��ͷ��PL202
      loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
      // ɾ��ҵ����־PL020
      plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_wy(flowHeadId);
      // ���������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ���� 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN).toString());// ��֤��Ǽ�-ҵ��ά��
      // 74
      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
          .toString());// ɾ�� 3
      plOperateLog.setOpBizId(new BigDecimal(flowHeadId));// PL202.FLOW_HEAD_ID
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);// PL203 ��ͬ���
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    } else {
      throw new BusinessException("�ñ�ҵ���״̬����ȷ�ϣ�������ɾ��!");
    }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
  }

  /**
   * Description ��֤��Ǽ�ά��
   * 
   * @author wangy 2007-10-04
   * @param ��ӡ��֤��Ǽ�ά���б�
   * @param ��BailenRolTbMaintainAC����
   * @return List
   * @throws Exception
   */
  public List findBailenRolTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    String findType = (String) pagination.getQueryCriterions().get("findType");
    if (findType == null || "".equals(findType)) {
      findType = "(4)";// Ĭ���б�
    } else {
      findType = "(4, 5, 6)";// ��ѯ
    }
    List printList = new ArrayList();
    List resultList = new ArrayList();
    // ��ѯ��֤��Ǽ�ά���б� ��ӡ
    printList = loanFlowHeadDAO.queryBailenRolPrintListByCriterions(securityInfo,contractId,
        borrowerName, cardNum, loanBankName, bizSt, findType);
    Iterator iterate = printList.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      BailenRolTbDTO bailenRolTbDTO = new BailenRolTbDTO();
      obj = (Object[]) iterate.next();
      if (obj[0] != null && !obj[0].equals(""))
        bailenRolTbDTO.setContractId(obj[0].toString());
      if (obj[1] != null && !obj[1].equals(""))
        bailenRolTbDTO.setBorrowerName(obj[1].toString());
      if (obj[2] != null && !obj[2].equals(""))
        bailenRolTbDTO.setCardNum(obj[2].toString());
      String loanBankId = null;
      if (obj[3] != null && !obj[3].equals(""))
        loanBankId = obj[3].toString();
      if (obj[4] != null && !obj[4].equals(""))
        bailenRolTbDTO.setBizDate(obj[4].toString());
      if (obj[5] != null && !obj[5].equals(""))
        bailenRolTbDTO.setOccurMoney(obj[5].toString());
      if (obj[6] != null && !obj[6].equals(""))
        bizSt = obj[6].toString();
      // ת����������
      try {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
            .toString());
        bailenRolTbDTO.setLoanBankName(dto.getCollBankName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // ö��ת��ҵ��״̬
      try {
        bailenRolTbDTO.setBizSt(BusiTools.getBusiValue(Integer.parseInt(""
            + bizSt), BusiConst.PLBUSINESSSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      resultList.add(bailenRolTbDTO);
    }
    return resultList;
  }

  /**
   * Description ��֤��Ǽ�
   * 
   * @author wangy 2007-10-29
   * @param ת����������
   * @param ��BailenRolTaSaveAC����
   * @param loanBankId
   * @return String
   * @throws Exception
   */
  public String changeBank(String loanBankId) throws Exception {
    String loanBankName = "";
    try {
      if (loanBankId != null && !"".equals(loanBankId)) {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId);
        loanBankName = dto.getCollBankName();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanBankName;
  }
}
