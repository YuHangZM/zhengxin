package org.xpup.hafmis.sysloan.loancallback.loanerlogout.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;

import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.bsinterface.ILoanerlogoutBS;
import org.xpup.hafmis.sysloan.loancallback.loanerlogout.dto.LoanerlogoutTaDTO;


public class LoanerlogoutBS implements ILoanerlogoutBS {
  private BorrowerAccDAO borrowerAccDAO = null;
  private PlOperateLogDAO plOperateLogDAO = null;
  private LoanFlowTailDAO loanFlowTailDAO=null;
  private CongealInfoDAO congealInfoDAO=null;
  /**
   * ���ע��
   * 
   * @authorwsh 2007-9-28 ���ݴ����˺Ų�ѯ�����ʺ��Ƿ���� ��ѯ������loadKouAcc
   */
  public void findLoanerlogoutTaExit(String loanKouAcc,List loanBankList) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub

    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      int count = 0;
      count = borrowerAccDAO.findBorrowerAccByLoanKouAcc_wsh(loanKouAcc,loanBankList)
          .intValue();
      // ��������˵��������µĿۿ��ʺ������ݿ���ԭ�пۿ��ʺŵ��ظ�
      if (count== 0) {
        be = new BusinessException("�����˺Ų����ڻ����û�Ȩ���£�");
        throw be;
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  /**
   * ���ע��-��ע��
   * ���������ͬ����Ƿ���ڴ���δ���˵�ҵ��
   * @param contractId ��ͬ��� 
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findLoanerlogouAvailable(String contractId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      BorrowerAcc borrowerAcc=new BorrowerAcc();
      borrowerAcc=borrowerAccDAO.queryById(contractId);
      if("13".equals(borrowerAcc.getContractSt())){
        be = new BusinessException("�ô����ע����");
        throw be;
      }
//    countΪ��ѯ�ļ�¼����
      int count=0;
      count=loanFlowTailDAO.queryCountByContractId_wsh(contractId).intValue();
//    ��������˵���ú�ͬ����´���δ���˵�ҵ�񲻿��Խ��пۿ��˺��޸�
      if(count!=0){
        be = new BusinessException("�������δ���˵�ҵ����ע����");
        throw be;
      }
    } catch (BusinessException e) {
      // TODO: handle exception
      e.printStackTrace();
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }    
  }


  /**
   * ���ע��
   * 
   * @author wsh 2007-9-29 ��ѯ�б���Ϣ
   */
  public List findLoanerlogoutTbList(Pagination pagination, List loanbankList)
      throws Exception {
    List list = null;
    List countlist = null;
    String loanKouAcc = "";
    String contractId = "";
    String borrowerName = "";
    String cardNum = "";
    String loanBankId = "";
    try {
      if (pagination.getQueryCriterions().get("loanKouAcc") != null) {
        loanKouAcc = (String) pagination.getQueryCriterions().get("loanKouAcc");
      }
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      String orderBy = (String) pagination.getOrderBy();
      ;
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = borrowerAccDAO.queryLoanerlogoutTbList(loanKouAcc, contractId,
          borrowerName, cardNum, loanBankId, orderBy, order, start, pageSize,
          loanbankList);
      countlist = borrowerAccDAO.queryLoanerlogoutTbListCount(loanKouAcc,
          contractId, borrowerName, cardNum, loanBankId, loanbankList);
      if (countlist.size() != 0) {
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }


  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }


  /**
   * ���ע��
   * 
   * @authorwsh 2007-9-28 ���ݴ����˺Ų�ѯ����ҳ����������� ��ѯ������loadKouAcc
   */
  public LoanerlogoutTaDTO findLoanerlogoutTaInfo(String loanKouAcc,List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    LoanerlogoutTaDTO loanerlogoutTaDTO = null;
    loanerlogoutTaDTO = borrowerAccDAO.queryLoanerlogoutTaInfo_wsh(loanKouAcc,loanBankList);
    if (loanerlogoutTaDTO != null) {
      loanerlogoutTaDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
          .parseInt(loanerlogoutTaDTO.getLoanMode().toString()),
          BusiConst.PLRECOVERTYPE));
      loanerlogoutTaDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer
          .parseInt(loanerlogoutTaDTO.getCardKind().toString()),
          BusiConst.DOCUMENTSSTATE));
    }
    return loanerlogoutTaDTO;
  }
  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }
  /**
   * ���ע��-��ע��
   * 
   * @authorwsh 2007-9-29
   */
  public void saveLoanerlogouTa(String contractId, SecurityInfo securityInfo) {
    // TODO Auto-generated method stub
    try {
      // �޸�pl111
      borrowerAccDAO.updateBorrowerAccContractStatus(contractId);
      // �޸�pl210
      congealInfoDAO.updateCongealInfoStatus("1",contractId);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN+""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_DO));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }
  /**
   * ���ע��-��ע��
   * 
   * @authorwsh 2007-9-28 ���ݺ�ͬ��Ž��з�ע��
   */
  public void trunLogoutLoanerlogout(String contractId, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
//    countΪ��ѯ�ļ�¼����
      int count=0;
      count=borrowerAccDAO.findBorrowerAccByContractSt_wsh(contractId).intValue();
      if(count==0){
        be = new BusinessException("�ô����������");
        throw be;
      }
      //�޸�pl111
      borrowerAccDAO.updateBorrowerAccContractStatus_wsh(contractId);
      // �޸�pl210
      congealInfoDAO.updateCongealInfoStatus("0",contractId);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN+""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_MAINTAIN));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw be;
    }catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
  }
  /**
   * ���ע��-��ӡ
   * 
   * @author wsh 2007-9-29 ��ѯ��ӡ��Ϣ
   */
  public List findLoanerlogoutTbPrintList(Pagination pagination,List loanbankList) throws Exception {
    // TODO Auto-generated method stub
    String loanKouAcc = "";
    String contractId = "";
    String borrowerName = "";
    String cardNum = "";
    String loanBankId = "";
    List list = null;
    try {
      if (pagination.getQueryCriterions().get("loanKouAcc") != null) {
        loanKouAcc = (String) pagination.getQueryCriterions().get("loanKouAcc");
      }
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      list = borrowerAccDAO.queryLoanerlogoutTbList_wsh(loanKouAcc, contractId,
          borrowerName, cardNum, loanBankId,
          loanbankList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
   
  }
 

}
