package org.xpup.hafmis.sysloan.specialbiz.bailpickup.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.bsinterface.IBailpickupBS;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTaAF;


/**
 * @author yuqf 20007-10-13
 */
public class BailpickupBS implements IBailpickupBS {

  private BorrowerAccDAO borrowerAccDAO = null;// PL111 ������˻���

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// pl202 ������ˮ��ͷ��

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;// pl203

  private PlBizActiveLogDAO plBizActiveLogDAO = null;// pl020

  private PlOperateLogDAO plOperateLogDAO = null;// pl021

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * ���ݻ�����ڼ�����Ƚ�Ϣ�� 2007-10-15
   * 
   * @param securityInfo
   * @return �㷨��Դ--���� ��������--yuqf
   */
  public String accountantyear(SecurityInfo securityInfo) {
    String accountantyear = null;
     String bizDate = securityInfo.getUserInfo().getPlbizDate();//����ϵͳ�������
//    String bizDate = securityInfo.getUserInfo().getBizDate();// �鼯ϵͳ�������
    String bizYear = bizDate.substring(0, 4);
    String bizMonth = bizDate.substring(4, 6);
    if (bizMonth.compareTo("07") >= 0 && bizMonth.compareTo("12") <= 0) {
      accountantyear = "" + (Integer.parseInt(bizYear) + 1) + "0630";
    } else {
      accountantyear = bizYear+"0630";
    }
    return accountantyear;
  }

  /**
   * yuqf 2007-10-15 yyyymmdd to yyyy-mm-dd
   * 
   * @param predate
   * @return
   */
  public String yyyymmddTOyyyy_mm_dd(String predate) {
    String date = "";
    if (predate != null && !"".equals(predate)) {
      date = predate.substring(0, 4) + "-" + predate.substring(4, 6) + "-"
          + predate.substring(6, 8);
    }
    return date;
  }

  /**
   * AJAX ��ѯ ͨ�������˺Ų�ѯ����ȡ��֤����Ϣ �����˺ţ���ͬ��ţ������������֤�����룬��֤�����������������������������,����δ���ս��
   * 2007-10-13
   */
  public BailpickupTaDTO ajaxQuery(String loanKouAcc, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTaDTO bailpickupTaDTO = new BailpickupTaDTO();
    BigDecimal curIntegral = new BigDecimal(0.00);// �������
    BigDecimal preIntegral = new BigDecimal(0.00);// �������
    BigDecimal bailBalance = new BigDecimal(0.00);// ��֤�����
//    BigDecimal interest = new BigDecimal(0.00);// ��ȡ��Ϣ
    BigDecimal paramExplainInterestD = new BigDecimal(0.00);// ������Ϣ
    BigDecimal paramExplainInterestL = new BigDecimal(0.00);// ������Ϣ
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    if("0630".equals(bizDate.substring(4, 8))){//����������Ϊ��0630��������ȡ
      throw new BusinessException("�������Ϊ6��30�գ�������ȡ!");
    }
    String accountantyear = this.accountantyear(securityInfo);// ��Ƚ�Ϣ��
    String contractId = "";//��ͬ���
    String bankId = "";
    // ��Ƚ�Ϣ�գ��������
    int tempDday = BusiTools.minusDate(this
        .yyyymmddTOyyyy_mm_dd(accountantyear), this
        .yyyymmddTOyyyy_mm_dd(bizDate));
    try {
      Object[] obj = new Object[10];
      obj = borrowerAccDAO.queryObjectByLoanKouAccYU(loanKouAcc);
      if (obj[0] != null) {
        bailpickupTaDTO.setLoanKouAcc(obj[0].toString());// �����˺�
      }
      if (obj[1] != null) {
        bailpickupTaDTO.setContractId(obj[1].toString());// ��ͬ���
        contractId = obj[1].toString();
        BorrowerAcc borrowerAcc = null;
        borrowerAcc = borrowerAccDAO.queryById(contractId);
        bankId = borrowerAcc.getLoanBankId().toString();
        List list = new ArrayList();
        list = loanFlowHeadDAO.queryBizTypeByContractIdYU(obj[1].toString());
        if(list.size()!=0){// �ж�PL202�Ƿ����δ����ҵ�� 
          //������ȡ
          bailpickupTaDTO.setType("0");
        }
      }
      if (obj[2] != null) {
        bailpickupTaDTO.setBorrowerName(obj[2].toString());// ���������s
      }
      if (obj[3] != null) {
        bailpickupTaDTO.setCardNum(obj[3].toString());// ֤������
      }
      if (obj[4] != null) {
        bailpickupTaDTO.setBailBalance(obj[4].toString());// ��֤�����
        bailBalance = new BigDecimal(obj[4].toString());
      }
      if (obj[5] != null) {
        curIntegral = new BigDecimal(obj[5].toString());// �������
      }
      if (obj[6] != null) {
        preIntegral = new BigDecimal(obj[6].toString());// �������
      }
      if (obj[7] != null) {
        bailpickupTaDTO.setOverplusLoanMoney(obj[7].toString());// �������
      }
      if (obj[8] != null) {
        bailpickupTaDTO.setNoBackMoney(obj[8].toString());// ����δ���ս��
      }
      if (obj[9] != null) {
        bailpickupTaDTO.setOvaerLoanRepay(obj[9].toString());// �������
      }
      BigDecimal b1 = null;
      BigDecimal b2 = null;
      BigDecimal b3 = null;
      BigDecimal b4 = null;
      // ������ȡ��Ϣ
      if (!"0".equals(curIntegral.toString())) {// ��������������0����Ϣ���������������֤����������Ƚ�Ϣ�գ�������ڣ�����������Ϣ/365
        // ������Ϣ
        String tempInterestL = "";
        tempInterestL = loanFlowHeadDAO
        .queryParamexplainYU_(loanKouAcc);
        if(tempInterestL!=null){
        paramExplainInterestL = new BigDecimal(tempInterestL);
        }
        BigDecimal tempDdate = new BigDecimal(new Integer(tempDday).toString());// ��Ƚ�Ϣ�գ��������
        if (tempDdate != null) {
          b1 = bailBalance.multiply(tempDdate);
        }
        if (b1 != null) {
          b2 = curIntegral.subtract(b1);
        }
        if (b2 != null) {
          b3 = b2.multiply(paramExplainInterestL);
        }
        if (b3 != null) {
          b4 = b3.divide(new BigDecimal(365), 2);
        }
        bailpickupTaDTO.setPickUpInterest(b4.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
//        bailpickupTaDTO.setPickUpInterest((b4.divide(tempBigDecimal, 2))
//            .toString());
        // interest = ((curIntegral.subtract(bailBalance.multiply(new
        // BigDecimal(new
        // Integer(tempDday).toString())))).multiply(paramExplainInterestL)).divide(new
        // BigDecimal(365), 5);
        // bailpickupTaDTO.setPickUpInterest(interest.toString());
      }
      if (!"0".equals(preIntegral.toString())) {// ��������������0����Ϣ���������������֤����������Ƚ�Ϣ�գ�������ڣ�����������Ϣ/365
        // ������Ϣ
        String tempInterestD ="";
        tempInterestD = loanFlowHeadDAO
        .queryParamexplainYU(bankId);
        if(tempInterestD!=null){
        paramExplainInterestD = new BigDecimal(tempInterestD);
        }
        BigDecimal tempDdate = new BigDecimal(new Integer(tempDday).toString());// ��Ƚ�Ϣ�գ��������
        if (tempDdate != null) {
          b1 = bailBalance.multiply(tempDdate);
        }
        if (b1 != null) {
          // b2 = curIntegral.subtract(b1);//�������
          b2 = preIntegral.subtract(b1);// �������
        }
        if (b2 != null) {
          b3 = b2.multiply(paramExplainInterestD);
        }
        if (b3 != null) {
          b4 = b3.divide(new BigDecimal(365),2);
        }
        bailpickupTaDTO.setPickUpInterest(b4.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        // interest = ((preIntegral.subtract(bailBalance.multiply(new
        // BigDecimal(new
        // Integer(tempDday).toString())))).multiply(paramExplainInterestL)).divide(new
        // BigDecimal(365), 5);
        // bailpickupTaDTO.setPickUpInterest(interest.toString());
      }
      // ��ȡ�ܽ�� �� ��֤������ȡ��Ϣ
      BigDecimal pickSumMoney = new BigDecimal(0.00);
      if (bailBalance != null && b4 != null) {
        pickSumMoney = bailBalance.add(b4).setScale(2, BigDecimal.ROUND_HALF_UP);
        bailpickupTaDTO.setPickSumMoney(pickSumMoney.toString());
      } else if (bailBalance == null && b4 != null) {
        bailpickupTaDTO.setPickSumMoney(b4.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
      } else if (bailBalance != null && b4 == null) {
        bailpickupTaDTO.setPickSumMoney(bailBalance.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
      }
    } catch (Exception e) {
      throw e;
    }
    return bailpickupTaDTO;
  }
  
  public BailpickupTaDTO ajaxQuery_( String loanKouAcc, Pagination pagination,
      SecurityInfo securityInfo, String flowHeadId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTaDTO bailpickupTaDTO = new BailpickupTaDTO();
    BigDecimal bailBalance = new BigDecimal(0.00);// ��֤�����
    BigDecimal bailBalance_ = new BigDecimal(0.00);
//    BigDecimal interest = new BigDecimal(0.00);// ��ȡ��Ϣ
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    if("0630".equals(bizDate.substring(4, 8))){//����������Ϊ��0630��������ȡ
      throw new BusinessException("�������Ϊ6��30�գ�������ȡ!");
    }
   
    try {
      Object[] obj = new Object[10];
      Object[] obj_ = new Object[3];
      obj = borrowerAccDAO.queryObjectByLoanKouAccYU(loanKouAcc);
      if (obj[0] != null) {
        bailpickupTaDTO.setLoanKouAcc(obj[0].toString());// �����˺�
      }
      if (obj[1] != null) {
        bailpickupTaDTO.setContractId(obj[1].toString());// ��ͬ���
        List list = new ArrayList();
        list = loanFlowHeadDAO.queryBizTypeByContractIdYU(obj[1].toString());
        if(list.size()!=0){// �ж�PL202�Ƿ����δ����ҵ�� 
          //������ȡ
          bailpickupTaDTO.setType("0");
        }
      }
      if (obj[2] != null) {
        bailpickupTaDTO.setBorrowerName(obj[2].toString());// ���������s
      }
      if (obj[3] != null) {
        bailpickupTaDTO.setCardNum(obj[3].toString());// ֤������
      }
      if (obj[4] != null) {
        bailpickupTaDTO.setBailBalance(obj[4].toString());// ��֤�����
        bailBalance = new BigDecimal(obj[4].toString());
      }
      if (obj[7] != null) {
        bailpickupTaDTO.setOverplusLoanMoney(obj[7].toString());// �������
      }
      if (obj[8] != null) {
        bailpickupTaDTO.setNoBackMoney(obj[8].toString());// ����δ���ս��
      }
      if (obj[9] != null) {
        bailpickupTaDTO.setOvaerLoanRepay(obj[9].toString());// �������
      }
      
      obj_ = borrowerAccDAO.queryObjectByLoanKouAccYU_(loanKouAcc, flowHeadId);
      if (obj_[0] != null) {
        bailpickupTaDTO.setBailBalance(obj_[0].toString());// ��֤�����
        bailBalance = new BigDecimal(obj_[0].toString());
      }
      if (obj_[1] != null) {
        bailpickupTaDTO.setPickUpInterest(obj_[1].toString());// ��ȡ��Ϣ
        bailBalance_ = new BigDecimal(obj_[1].toString());
      }
      bailBalance = bailBalance.add(bailBalance_).abs();
      // ��ȡ�ܽ�� �� ��֤������ȡ��Ϣ
      bailpickupTaDTO.setPickSumMoney(bailBalance.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    } catch (Exception e) {
      throw e;
    }
    return bailpickupTaDTO;
  }

  /**
   * ȷ����ť
   */
  public String save(BailpickupTaDTO bailpickupTaDTO, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub

    String contractId = bailpickupTaDTO.getContractId();// ��ͬ���
    String bailBalance = bailpickupTaDTO.getBailBalance();// ��֤��
//    String overplusLoanMoney = bailpickupTaDTO.getOverplusLoanMoney();// �������
//    String noBackMoney = bailpickupTaDTO.getNoBackMoney();// ����
//    String ovaerLoanRepay = bailpickupTaDTO.getOvaerLoanRepay();// ���˽��
    String pickUpInterest = bailpickupTaDTO.getPickUpInterest();// ��ȡ��Ϣ
    String loanKouAcc = bailpickupTaDTO.getLoanKouAcc();// �����˺�

    String docNum = "";
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �鼯ҵ������
    // String bizDate = securityInfo.getUserInfo().getPlbizDate();//����ҵ������
    String loanBankId = loanFlowHeadDAO.queryBankByContractIdYU(contractId);// 111
                                                                            // �µ�
    String office = loanFlowHeadDAO.queryOfficeByBank(loanBankId);
    docNum = plDocNumMaintainDAO.getDocNumdocNum(office, bizDate
        .substring(0, 6));

    // �ж�PL202�Ƿ����δ����ҵ��
//    List list = new ArrayList();
//    list = loanFlowHeadDAO.queryBizTypeByContractIdYU(contractId);
//    if (list.size() != 0) {
//      throw new BusinessException("�ô����˺��´���δ���˵ı�֤��ҵ��,��������ȡ!");
//    }
    // �ж���֤���Ƿ�Ϊ��
//    if (bailBalance.equals("0") || bailBalance == "") {
//      throw new BusinessException("�ô����˺��µı�֤���Ѿ���ȡ!");
//    }
    // �жϴ������,����,���˽���Ƿ������
//    if ("0".equals(overplusLoanMoney) || "".equals(overplusLoanMoney)) {
//      throw new BusinessException("�ô����˺��µĴ����������Ϊ0���Ƿ������ȡ?");
//    }
//    if ("0".equals(noBackMoney) || "".equals(noBackMoney)) {
//      throw new BusinessException("�ô����˺��µĴ���δ�ջؽ������Ϊ0���Ƿ������ȡ?");
//    }
//    if ("0".equals(ovaerLoanRepay) || "".equals(ovaerLoanRepay)) {
//      throw new BusinessException("�ô����˺��µĹ����������Ϊ0���Ƿ������ȡ?");
//    }
    String pl202id = this.addPl202(securityInfo, bailBalance, pickUpInterest,
        contractId, docNum);
    if (pl202id != null && !"".equals(pl202id)) {
      this.addPl203(pl202id, loanKouAcc, contractId, bailBalance,
          pickUpInterest);
      this.addPl020(pl202id, securityInfo);
      this.addPl021(pl202id, contractId, securityInfo);
    }
    return docNum;
  }

  /**
   * ����PL202
   * 
   * @param securityInfo
   * @param bailBalance
   * @param pickUpInterest
   * @param contractId
   * @return
   */
  public String addPl202(SecurityInfo securityInfo, String bailBalance,
      String pickUpInterest, String contractId, String docNum) {
    String pl202Id = "";
    try {
//      String bizDate = securityInfo.getUserInfo().getBizDate();// �鼯ҵ������
       String bizDate = securityInfo.getUserInfo().getPlbizDate();//����ҵ������
      String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      String loanBankId = loanFlowHeadDAO.queryBankByContractIdYU(contractId);// 111
                                                                              // �µ�
//      String office = loanFlowHeadDAO.queryOfficeByBank(loanBankId);
      loanFlowHead.setBizDate(bizDate);
      loanFlowHead.setBizType(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN)
          .toString());
      loanFlowHead.setRealCount(new BigDecimal(1));// ʵ������
      if(bailBalance!=null && !"".equals(bailBalance)){
        loanFlowHead.setOccurMoney(new BigDecimal("-"+bailBalance));
      }
      if(pickUpInterest!=null && !"".equals(pickUpInterest)){
        loanFlowHead.setOtherInterest(new BigDecimal(pickUpInterest));
      }
      loanFlowHead.setDocNum(docNum);
      loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_SURE)
          .toString());
      loanFlowHead.setMakePerson(operateName);
      if(loanBankId!=null && !"".equals(loanBankId)){
        loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
      }
      loanFlowHead.setIsFinance(new Integer(1));
      pl202Id = loanFlowHeadDAO.insert(loanFlowHead).toString();
      //����Ʊ�ݺ�
      loanFlowHead.setNoteNum(pl202Id);
      // �޸ļ�¼����ͷ�����Ʊ�ݺ�   2007-11-16 wangy
      // loanFlowHeadDAO.update(loanFlowHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl202Id;
  }

  /**
   * ����PL203
   * 
   * @param flowHeadId
   * @param loanKouAcc
   * @param contractId
   * @param bailBalance
   * @param pickUpInterest
   * @return
   */
  public String addPl203(String flowHeadId, String loanKouAcc,
      String contractId, String bailBalance, String pickUpInterest) {
    String pl203Id = "";
    try {
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
      loanFlowTail.setLoanKouAcc(loanKouAcc);
      loanFlowTail.setContractId(contractId);
      loanFlowTail.setOccurMoney(new BigDecimal("-"+bailBalance));
      loanFlowTail.setOtherInterest(new BigDecimal(pickUpInterest));
      loanFlowTailDAO.insert(loanFlowTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl203Id;
  }

  /**
   * ����PL020
   * 
   * @param flowHeadId
   * @param securityInfo
   * @return
   */
  public String addPl020(String flowHeadId, SecurityInfo securityInfo) {
    String pl020Id = "";
    try {
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      String operator = securityInfo.getUserInfo().getUsername();// ����Ա
      plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
      plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE + "");
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(operator);
      plBizActiveLog.setType(BusiConst.PLBUSINESSTYPE_MARGIN + "");
      plBizActiveLogDAO.insert(plBizActiveLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl020Id;
  }

  public String addPl021(String flowHeadId, String contractId,
      SecurityInfo securityInfo) {
    String pl021Id = "";
    try {
      PlOperateLog plOperateLog = new PlOperateLog();
      String operator = securityInfo.getUserInfo().getUsername();// ����Ա
      String opIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog
          .setOpModel(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO + "");
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM + "");
      plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
      plOperateLog.setOpIp(opIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operator);
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl021Id;
  }

  /**
   * ��ȡά����ѯ
   */
  public BailpickupTbDTO tbQuery(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTbDTO bailpickupTbDTO = new BailpickupTbDTO();
    List list = new ArrayList();
    List bankList = securityInfo.getCollBankList();
    Map statusmap = new HashMap();
    int count = 0;
    statusmap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
    statusmap.remove(new Integer(BusiConst.PLBUSINESSSTATE_EXP));
    statusmap.remove(new Integer(BusiConst.PLBUSINESSSTATE_IMP));
    statusmap.remove(new Integer(BusiConst.PLBUSINESSSTATE_SIGN));
    bailpickupTbDTO.setBankList(bankList);
    bailpickupTbDTO.setMap(statusmap);
//    String sumOccurMoney = "";// �ϼ���ȡ��֤����
//    String sumOtherInterest = "";// �ϼ���ȡ��Ϣ
//    String sumPickupMoney = "";// �ϼ���ȡ���

    HashMap map = (HashMap) pagination.getQueryCriterions();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    int page = pagination.getPage();
    Object[] obj = new Object[3];
    if (map.size() != 0) {
      // ��������ѯ
      String loanKouAcc = (String) map.get("loanKouAcc");// �����˺�
      String contractId = (String) map.get("contractId");// ��ͬ���
      String borrowerName = (String) map.get("borrowerName");// ���������
      String cardNum = (String) map.get("cardNum");// ֤������
      String loanBank = (String) map.get("loanBank");// �ſ�����
      String bizStatus = (String) map.get("bizStatus");// ҵ��״̬
      list = loanFlowTailDAO.bailpickupTbdefaltQueryByCondition(loanKouAcc,
          contractId, borrowerName, cardNum, loanBank, bizStatus, orderBy,
          order, start, pageSize, page, securityInfo);
      count = loanFlowTailDAO.bailpickupTbdefaltQueryByConditionCount(
          loanKouAcc, contractId, borrowerName, cardNum, loanBank, bizStatus,
          orderBy, order, start, pageSize, page, securityInfo);
      obj = loanFlowTailDAO.querySumYU(loanKouAcc, contractId, borrowerName,
          cardNum, loanBank, bizStatus, orderBy, order, start, pageSize, page,
          securityInfo);
      if (obj[0] != null) {
        bailpickupTbDTO.setSumOccurMoney(obj[0].toString());
      }
      if (obj[1] != null) {
        bailpickupTbDTO.setSumOtherInterest(obj[1].toString());
      }
      if (obj[2] != null) {
        bailpickupTbDTO.setSumPickupMoney(obj[2].toString());
      }

    } else {
      // Ĭ����������ѯ
      list = loanFlowTailDAO.bailpickupTbdefaltQuery(orderBy, order, start,
          pageSize, page, securityInfo);
      count = loanFlowTailDAO.bailpickupTbdefaltQueryCount(orderBy, order,
          start, pageSize, page, securityInfo);
      obj = loanFlowTailDAO.querySumYU_(orderBy, order, start, pageSize, page,
          securityInfo);
      if (obj[0] != null) {
        bailpickupTbDTO.setSumOccurMoney(obj[0].toString());
      }
      if (obj[1] != null) {
        bailpickupTbDTO.setSumOtherInterest(obj[1].toString());
      }
      if (obj[2] != null) {
        bailpickupTbDTO.setSumPickupMoney(obj[2].toString());
      }
    }
    pagination.setNrOfElements(count);
    bailpickupTbDTO.setList(list);
    return bailpickupTbDTO;
  }

  /**
   * ��ȡά��ɾ��
   */
  public void tbDelete(String flowHeadId, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String bizSt = loanFlowHeadDAO.queryBizStById(flowHeadId, securityInfo);
    if (bizSt != null && !"".equals(bizSt)) {
      if ("4".equals(bizSt)) {// ҵ��Ϊȷ��״̬������ɾ��
        String docNum = loanFlowHeadDAO.queryDocNumById(flowHeadId, securityInfo);
        String contractId = loanFlowTailDAO
            .queryContractByHeadId_YU(flowHeadId);
        String yearMonth = securityInfo.getUserInfo().getPlbizDate().substring(0, 6);// �鼯ҵ������
        // String bizDate = securityInfo.getUserInfo().getPlbizDate();//����ҵ������
        String loanBankId = loanFlowHeadDAO.queryBankByContractIdYU(contractId);// 111                                                                             // �µ�
        String officeCode = loanFlowHeadDAO.queryOfficeByBank(loanBankId);
        // ɾ��β��PL203��deleteFlowTailByHeadId_YU
        loanFlowTailDAO.deleteFlowTailByHeadId_YU(flowHeadId);
        // ɾ��ͷ��PL202
        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(
            flowHeadId));
        loanFlowHeadDAO.delete(loanFlowHead);
        // ɾ��ҵ������PL020
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(flowHeadId);
        //����ƾ֤�����ϱ�
        plDocNumCancelDAO.insertPlDocNumCancel(docNum, officeCode, yearMonth);
        // ���������־
        PlOperateLog plOperateLog = new PlOperateLog();
        String operator = securityInfo.getUserInfo().getUsername();// ����Ա
        String opIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog
            .setOpModel(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN + "");
        plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
        plOperateLog.setOpIp(opIp);
        plOperateLog.setContractId(contractId);
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(operator);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        throw new BusinessException("�ñ�ҵ���״̬����ȷ�ϣ�������ɾ��!");
      }
    }
  }

  /*****************************************************************************
   * ��ӡ��ѯ
   */
  public BailpickupTaAF tbPrintQuery(String flowHeadId, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTaAF bailpickupTaAF = new BailpickupTaAF();
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(flowHeadId));
    String loan_kou_acc = loanFlowTailDAO.queryContractId(flowHeadId);// �����˺�
    BailpickupTaDTO bailpickupTaDTO = new BailpickupTaDTO();
    String bizSt = loanFlowHeadDAO.queryBizStById(flowHeadId, securityInfo);
    if(bizSt.equals("4")){
      bailpickupTaDTO = this.ajaxQuery(loan_kou_acc, pagination,
          securityInfo);
    }else{
      bailpickupTaDTO = this.ajaxQuery_(loan_kou_acc, pagination,
          securityInfo,flowHeadId);
    }
    
    String loanKouAcc = bailpickupTaDTO.getLoanKouAcc();// �����˺�
    String contractId = bailpickupTaDTO.getContractId();// ��ͬ���
    String borrowerName = bailpickupTaDTO.getBorrowerName();// ���������
    String cardNum = bailpickupTaDTO.getCardNum();// ֤������
    String bailBalance = bailpickupTaDTO.getBailBalance();// ��֤�����
    String pickUpInterest = bailpickupTaDTO.getPickUpInterest();// ��ȡ��Ϣ
    String pickSumMoney = bailpickupTaDTO.getPickSumMoney();// ��ȡ�ܽ��
    String overplusLoanMoney = bailpickupTaDTO.getOverplusLoanMoney();// �������
    String noBackMoney = bailpickupTaDTO.getNoBackMoney();// ����δ�ջؽ��
    String ovaerLoanRepay = bailpickupTaDTO.getOvaerLoanRepay();// �������
    String docNum = loanFlowHead.getDocNum();// ƾ֤��
    bailpickupTaAF.setLoanKouAcc(loanKouAcc);
    bailpickupTaAF.setContractId(contractId);
    bailpickupTaAF.setCardNum(cardNum);
    bailpickupTaAF.setBailBalance(bailBalance);
    bailpickupTaAF.setCardNum(cardNum);
    bailpickupTaAF.setPickUpInterest(pickUpInterest);
    bailpickupTaAF.setOverplusLoanMoney(overplusLoanMoney);
    bailpickupTaAF.setNoBackMoney(noBackMoney);
    bailpickupTaAF.setBorrowerName(borrowerName);
    bailpickupTaAF.setPickSumMoney(pickSumMoney);
    bailpickupTaAF.setDocNum(docNum);
    bailpickupTaAF.setOvaerLoanRepay(ovaerLoanRepay);
    return bailpickupTaAF;
  }

}
