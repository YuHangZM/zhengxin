package org.xpup.hafmis.sysloan.loanapply.receiveacc.business;

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
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;

import org.xpup.hafmis.orgstrct.domain.CollBank;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.GatheringAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.GatheringAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface.IReceiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.GatheringAccInfoDTO;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.dto.ReceiveaccModifyDTO;

public class ReceiveaccBS implements IReceiveaccBS {
  private BorrowerDAO borrowerDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private GatheringAccDAO gatheringAccDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollBankDAO collBankDAO = null;

  private SecurityDAO securityDAO = null;

  /**
   * ���ݺ�ͬ��Ų�ѯ�ۿ��ʺ��޸���Ϣ
   * 
   * @param contractId ��ͬ���
   * @return ReceiveaccModifyDTO
   * @throws Exception
   * @author wsh
   */
  public ReceiveaccModifyDTO findReceiveaccInfo(String contractId)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ReceiveaccModifyDTO receiveaccModifyDTO = new ReceiveaccModifyDTO();
    try {
      receiveaccModifyDTO = borrowerDAO.queryBorrowerInfo_wsh(contractId
          .toString());
      String cardKind = BusiTools.getBusiValue(Integer
          .parseInt(receiveaccModifyDTO.getCardKind().toString()),
          BusiConst.DOCUMENTSSTATE);
      if (cardKind != null && !"".equals(cardKind.trim())) {
        receiveaccModifyDTO.setCardKind(cardKind);
      }
      String loanankId = receiveaccModifyDTO.getLoanankId();
      if (loanankId != null && !"".equals(loanankId.trim())) {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanankId);
        receiveaccModifyDTO.setLoanankId(dto.getCollBankName());
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return receiveaccModifyDTO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * �޸Ŀۿ��ʺ�
   * 
   * @param contractId ��ͬ���
   * @param newLoanKouAcc �¿ۿ��ʺ�
   * @param oldBankAcc �ɿۿ��ʺ�
   * @param securityInfo Ȩ��
   * @throws Exception
   * @author wsh
   */
  public void modifyBorrowerAccInfo(String contractId, String newLoanKouAcc,
      String oldBankAcc, SecurityInfo securityInfo, String flag,
      String newbankId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = new BorrowerAcc();
    try {
      borrowerAcc = borrowerAccDAO.queryById(contractId);
      borrowerAcc.setLoanKouAcc(newLoanKouAcc);
      GatheringAcc gatheringAcc = new GatheringAcc();
      gatheringAcc.setContractId(contractId);
      gatheringAcc.setModifyDate(securityInfo.getUserInfo().getPlbizDate());
      gatheringAcc.setNewBankAcc(newLoanKouAcc);
      gatheringAcc.setOldBankAcc(oldBankAcc);
      gatheringAcc.setOprator(securityInfo.getUserName());
      gatheringAcc.setOpTime(new Date());
      if (newbankId != null && !"".equals(newbankId)) {
        borrowerAcc.setLoanBankId(new BigDecimal(newbankId));
        gatheringAccDAO.updatePL201bankId_wsh(newbankId, contractId);
        gatheringAccDAO.updatePL004bankId_wsh(newbankId, contractId);
      }
      gatheringAccDAO.insert(gatheringAcc);
      // ����
      if ("1".equals(flag)) {
        gatheringAccDAO.updatePL400KOUACC_wsh(newLoanKouAcc, contractId);
      }
      // ����
      PlOperateLog plOperateLog = new PlOperateLog();
      // ������־PL021
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN
          + ""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_DO));
      plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      plOperateLog.setOpBizId(new BigDecimal(gatheringAcc
          .getReceiveBankModifyId().toString()));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  /**
   * ���������ͬ��Ų�ѯ��ͬ����Ƿ����
   * 
   * @param contractId ��ͬ���
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findReceiveaccInfoExist(String contractId, List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    int count = 0;
    try {
      count = borrowerAccDAO.queryIdExist_wsh(contractId, loanBankList)
          .intValue();
      // ��������˵�������ͬ��Ų�����
      if (count == 0) {
        be = new BusinessException("��ͬ��Ų����ڻ����û�Ȩ���£�");
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
   * ���������ͬ����Ƿ���ڴ���δ���˵�ҵ��
   * 
   * @param contractId ��ͬ���
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findReceiveaccAvailable(String contractId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      int count = 0;
      count = loanFlowTailDAO.queryCountByContractId_wsh(contractId).intValue();
      // ��������˵���ú�ͬ����´���δ���˵�ҵ�񲻿��Խ��пۿ��˺��޸�
      if (count != 0) {
        be = new BusinessException("�ú�ͬ����´���δ���˵�ҵ�񲻿��Խ��пۿ��˺��޸ģ�");
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
   * ���������µĿۿ��ʺ��Ƿ������ݿ���ԭ�пۿ��ʺŵ��ظ�
   * 
   * @param newLoanKouAcc �¿ۿ��ʺ�
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void isLoanKouAccDuplicate(String newLoanKouAcc) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      int count = 0;
      count = borrowerAccDAO.findBorrowerAccByLoanKouAcc_wsh(newLoanKouAcc)
          .intValue();
      // ��������˵��������µĿۿ��ʺ������ݿ���ԭ�пۿ��ʺŵ��ظ�
      if (count != 0) {
        be = new BusinessException("�ۿ��˺��ظ���");
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
   * ��ѯpl130�еļ�¼
   * 
   * @param pagination
   * @throws Exception, BusinessException
   * @return List
   * @author wsh
   */
  public List findGathingAccList(Pagination pagination, List loanBankList)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    try {
      String contractId = "";
      String borrowerName = "";
      String cardNum = "";
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
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = gatheringAccDAO.queryGathingAccList_wsh(contractId, borrowerName,
          cardNum, orderBy, order, start, pageSize, loanBankList);
      Iterator iter = list.iterator();
      GatheringAccInfoDTO gatheringAccInfoDTO = null;
      while (iter.hasNext()) {
        gatheringAccInfoDTO = (GatheringAccInfoDTO) iter.next();
        CollBank dto = collBankDAO.getCollBankByCollBankid(gatheringAccInfoDTO
            .getLoanBankId());
        if (gatheringAccInfoDTO.getOprator() != null && !"".equals(gatheringAccInfoDTO.getOprator()))
          gatheringAccInfoDTO.setOprator(securityDAO.queryByUserid(gatheringAccInfoDTO.getOprator()));
        gatheringAccInfoDTO.setLoanBankId(dto.getCollBankName());// ��������
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ��ѯpl130�еļ�¼����
   * 
   * @param contractId ��ͬ���
   * @param borrwerName ���������
   * @param cardNum ֤������
   * @throws Exception, BusinessException
   * @return int
   * @author wsh
   */
  public int findGathingAccCount(String contractId, String borrwerName,
      String cardNum, List loanBankList) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // ��¼����
    int count = 0;
    try {
      count = gatheringAccDAO.queryGathingAccCount_wsh(contractId, borrwerName,
          cardNum, loanBankList).intValue();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return count;
  }

  /**
   * ��ѯ�ۿ��ʺ�ά����Ҫ��ӡ�ĵ�����Ϣ
   * 
   * @param receiveBankModifyId pl130 ����
   * @throws Exception, BusinessException
   * @return HouseListDTO
   * @author wsh
   */
  public GatheringAccInfoDTO findGatheringAccInfo(String receiveBankModifyId,
      String username) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    GatheringAccInfoDTO gatheringAccInfoDTO = new GatheringAccInfoDTO();
    try {
      gatheringAccInfoDTO = gatheringAccDAO
          .queryGathingAccInfo_wsh(receiveBankModifyId);
      CollBank dto = collBankDAO.getCollBankByCollBankid(gatheringAccInfoDTO
          .getLoanBankId());
      gatheringAccInfoDTO.setLoanBankId(dto.getCollBankName());// ��������
      String name = securityDAO.queryByUserid(username);
      gatheringAccInfoDTO.setName(name);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return gatheringAccInfoDTO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setGatheringAccDAO(GatheringAccDAO gatheringAccDAO) {
    this.gatheringAccDAO = gatheringAccDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  // ����
  public String findExitGJJBack(String contractId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    String flag = "0";
    try {

      flag = gatheringAccDAO.findExitGJJBack(contractId);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    if (!"0".equals(flag) && !"".equals(flag)) {
      flag = "1";
    }
    return flag;
  }
  // ����
}
