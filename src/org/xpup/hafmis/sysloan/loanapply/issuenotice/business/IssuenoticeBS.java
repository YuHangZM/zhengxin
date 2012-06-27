package org.xpup.hafmis.sysloan.loanapply.issuenotice.business;

import java.math.BigDecimal;
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
import org.xpup.hafmis.sysloan.common.dao.AssurerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.SpecialBorrowerDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Assurer;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBank;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticePrintDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTaDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTbDTO;
import org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF;

public class IssuenoticeBS implements IIssuenoticeBS {
  private BorrowerAccDAO borrowerAccDAO = null;

  private HousesDAO housesDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private LoanContractDAO loanContractDAO = null;

  private CollBankDAO collBankDAO = null;

  private SecurityDAO securityDAO = null;

  private AssurerDAO assurerDAO = null;

  private DeveloperDAO developerDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private LoanBankDAO loanBankDAO = null;

  private SpecialBorrowerDAO specialBorrowerDAO = null;

  /**
   * �´﷢��֪ͨ��_ȷ�� �жϸú�ͬ����µ�CONTRACT_ST=4 and IS_CONTRACT_WRITE=2
   * 
   * @param contractId ��ͬ���
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findIssuenoticeAvailable(String contractId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      int count = borrowerAccDAO.queryCountByContractId_wsh(contractId)
          .intValue();
      // ��������˵���ú�ͬ����´���δ���˵�ҵ�񲻿��Խ��пۿ��˺��޸�
      if (count == 0) {
        be = new BusinessException("��ͬδǩ������δͨ����������ǩ���������ͬ����");
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
   * �´﷢��֪ͨ��_ά��_��ѯ
   * 
   * @param loanbankList �ſ�����
   * @author wsh 2007-10-03 ��ѯ�б���Ϣ
   */
  public List findIssuenoticeTbList(Pagination pagination, List loanbankList,
      String type) throws Exception {
    List list = null;
    List countlist = null;
    String contractId = "";
    String borrowerName = "";
    String cardNum = "";
    String loanBankId = "";
    String contractSt = "";
    String isPrint = "";
    BigDecimal loanMoney = new BigDecimal(0.00);
    try {
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
      if (pagination.getQueryCriterions().get("contractSt") != null) {
        contractSt = (String) pagination.getQueryCriterions().get("contractSt");
      }
      if (pagination.getQueryCriterions().get("isPrint") != null) {
        isPrint = (String) pagination.getQueryCriterions().get("isPrint");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = borrowerAccDAO.queryIssuenoticeTbList(contractId, borrowerName,
          cardNum, loanBankId, contractSt, orderBy, order, start, pageSize,
          loanbankList, type, page, isPrint);
      if (list.size() != 0) {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
          IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) iter.next();
          issuenoticeTbDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getLoanMode().toString()),
              BusiConst.PLRECOVERTYPE));
          issuenoticeTbDTO.setContractSt(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getContractSt().toString()),
              BusiConst.PLCONTRACTSTATUS));
          CollBank dto = collBankDAO.getCollBankByCollBankid(issuenoticeTbDTO
              .getLoanBankId());
          issuenoticeTbDTO.setLoanBankId(dto.getCollBankName());// ��������
          issuenoticeTbDTO.setTemp_loanMonthRate((issuenoticeTbDTO
              .getLoanMonthRate().multiply(new BigDecimal(100)) + "%"));
        }
      }
      countlist = borrowerAccDAO.queryIssuenoticeTbListCount(contractId,
          borrowerName, cardNum, loanBankId, contractSt, loanbankList, type,
          isPrint);
      if (countlist.size() != 0) {
        Iterator iter = countlist.iterator();
        while (iter.hasNext()) {
          IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) iter.next();
          issuenoticeTbDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getLoanMode().toString()),
              BusiConst.PLRECOVERTYPE));
          CollBank dto = collBankDAO.getCollBankByCollBankid(issuenoticeTbDTO
              .getLoanBankId());
          issuenoticeTbDTO.setLoanBankId(dto.getCollBankName());// ��������
          loanMoney = loanMoney.add(issuenoticeTbDTO.getLoanMoney());
        }
        IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) list.get(0);
        issuenoticeTbDTO.setLoanMoneySum(loanMoney);
      }
      if (countlist != null) {
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * �´﷢��֪ͨ��_ȷ��
   * 
   * @authorwsh 2007-10-02 ���ݺ�ͬ��Ų�ѯ����ҳ����������� ��ѯ������contractId
   */
  public IssuenoticeTaDTO findIssuenoticeTaInfo(String contractId,
      String houseType) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    IssuenoticeTaDTO issuenoticeTaDTO = null;
    issuenoticeTaDTO = housesDAO
        .queryIssuenoticeInfo_wsh(contractId, houseType);
    Borrower borrower = new Borrower();
    borrower = borrowerDAO.queryById(contractId);
    if (issuenoticeTaDTO != null) {
      issuenoticeTaDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
          .parseInt(issuenoticeTaDTO.getLoanMode().toString()),
          BusiConst.PLRECOVERTYPE));
      issuenoticeTaDTO.setTemp_cardKind(BusiTools.getBusiValue(Integer
          .parseInt(issuenoticeTaDTO.getCardKind().toString()),
          BusiConst.DOCUMENTSSTATE));
      issuenoticeTaDTO.setRedatePerson(securityDAO
          .queryByUserid(issuenoticeTaDTO.getRedatePerson()));
      issuenoticeTaDTO.setChkAgainPerson(securityDAO
          .queryByUserid(issuenoticeTaDTO.getChkAgainPerson()));
      issuenoticeTaDTO.setVipChkAgainPerson(securityDAO
          .queryByUserid(issuenoticeTaDTO.getVipChkAgainPerson()));
      issuenoticeTaDTO.setLastChkPerson(securityDAO
          .queryByUserid(issuenoticeTaDTO.getLastChkPerson()));

      issuenoticeTaDTO.setFinChkPerson(securityDAO
          .queryByUserid(issuenoticeTaDTO.getFinChkPerson()));

      String loanankId = issuenoticeTaDTO.getLoanBankId();
      if (loanankId != null && !"".equals(loanankId.trim())) {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanankId);
        issuenoticeTaDTO.setLoanBankId(dto.getCollBankName());
      }

      List list = assurerDAO.queryAssurerInfo(contractId);
      if (list.size() > 0) {
        Assurer assurer = (Assurer) list.get(0);
        issuenoticeTaDTO.setBailName(assurer.getEmpName());
      }
      String headId = issuenoticeTaDTO.getHeadId();

      if (houseType != null && !"".equals(houseType) && "01".equals(houseType)
          && headId != null && !"".equals(headId)) {
        Developer developer = developerDAO.queryById(new Integer(headId));
        issuenoticeTaDTO.setHouse(developer.getDeveloperName());
      }
      if (houseType != null && !"".equals(houseType) && "02".equals(houseType)) {
        String name = housesDAO.queryErShouFangSellerName(contractId);
        if (name != null && !"".equals(name)) {
          issuenoticeTaDTO.setHouse(name);
        }
      }
      SpecialBorrower specialBorrower = null;
      if (borrower.getPrivilegeBorrowerId() != null
          && !"".equals(borrower.getPrivilegeBorrowerId())) {
        specialBorrower = specialBorrowerDAO.queryByHeadID(new Integer(borrower
            .getPrivilegeBorrowerId().toString()));
      }
      if (specialBorrower != null && specialBorrower.getPerBankAcc() != null
          && !"".equals(specialBorrower.getPerBankAcc())) {
        issuenoticeTaDTO.setHouse(specialBorrower.getBorrowerName());
      }

    }
    return issuenoticeTaDTO;
  }

  /**
   * �´﷢��֪ͨ��_ȷ��
   * 
   * @authorwsh 2007-10-02 ���ݺ�ͬ��Ų�ѯ�������� ��ѯ������contractId
   */
  public String findHouseType(String contractId) throws Exception {
    // TODO Auto-generated method stub
    String houseType = "";
    try {
      houseType = housesDAO.findHouseType_wsh(contractId);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return houseType;
  }

  /**
   * �´﷢��֪ͨ��_ȷ��
   * 
   * @authorwsh 2007-9-29
   */
  public void saveIssuenoticeTa(String contractId, String bizDate,
      SecurityInfo securityInfo) {
    // TODO Auto-generated method stub
    try {
      // �޸�pl111
      borrowerAccDAO.updateBorrowerAccContractStatus1_wsh(contractId);
      // �޸�pl120
      loanContractDAO.updateLoanContract_wsh(bizDate, contractId);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN
          + ""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_ISSUEDNOTICE_DO));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_CONFIRM));
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

  public void saveIssuenotice(IssuenoticeTaAF issuenoticeTaAF,
      SecurityInfo securityInfo) throws BusinessException {
    String contractId = issuenoticeTaAF.getContractId();
    String bizDate = issuenoticeTaAF.getBizDate();
    String loanKouAcc = issuenoticeTaAF.getLoanKouAcc();
    try {
      // �޸�pl111
      // borrowerAccDAO.updateBorrowerAccContractStatus1_wsh(contractId);
      borrowerAccDAO.updateBorrowerAccContract(contractId, loanKouAcc);
      // �޸�pl120
      loanContractDAO.updateLoanContract_wsh(bizDate, contractId);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN
          + ""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_ISSUEDNOTICE_DO));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_CONFIRM));
      plOperateLog.setOpBizId(new BigDecimal(contractId));
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * �´﷢��֪ͨ��_ά��_ɾ��
   * 
   * @authorwsh 2007-10-04 ���ݺ�ͬ��Ž���ɾ��
   */
  public void deleteIssuenotice(String contractId, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      int count = 0;
      count = borrowerAccDAO.findBorrowerAccByContractSt9_wsh(contractId)
          .intValue();
      if (count == 0) {
        be = new BusinessException("�ñ�ҵ���״̬�����·�֪ͨ�飬������ɾ����");
        throw be;
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

  /**
   * �´﷢��֪ͨ��_ά��_ɾ��
   * 
   * @authorwsh 2007-10-03
   */
  public void deleteIssuenoticeTb(String rowArray, SecurityInfo securityInfo) {
    // TODO Auto-generated method stub
    try {
      // �޸�pl111
      // for (int i = 0; i < rowArray.length; i++) {
      borrowerAccDAO.updateBorrowerAccContractStatus4_wsh(rowArray);
      borrowerAccDAO.updateBorrowerStatus_wsh(rowArray);

      // �޸�pl120
      loanContractDAO.updateLoanContract_wsh(null, rowArray);
      // ������־PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN
          + ""));
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_ISSUEDNOTICE_MAINTAIN));
      plOperateLog.setOpButton(String
          .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
      plOperateLog.setOpBizId(new BigDecimal(rowArray));
      plOperateLog.setContractId(rowArray);
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLogDAO.insert(plOperateLog);
      // }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * �´﷢��֪ͨ��_ȷ�� ���������ͬ��Ų�ѯ��ͬ����Ƿ����
   * 
   * @param contractId ��ͬ���
   * @throws Exception, BusinessException
   * @author wsh
   */
  public void findIssuenoticeInfoExist(String contractId, List list)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    int count = 0;
    BusinessException be = null;
    try {
      count = borrowerAccDAO.queryIdExist2_wsh(contractId, list).intValue();
      // ��������˵�������ͬ��Ų�����
      if (count == 0) {
        be = new BusinessException("��ͬ��Ų����ڻ����û�Ȩ���£�");
        throw be;
      }
      count = borrowerAccDAO.queryContractWrite_wsh(contractId, list)
          .intValue();
      if (count == 0) {
        be = new BusinessException("�ú�ͬδǩ���������´﷢��֪ͨ�飡");
        throw be;
      }
      String is = borrowerAccDAO.queryIsBoKuan(contractId);
      if ("0".equals(is)) {
        throw new BusinessException("�ñʺ�ͬ����¥��δ������ܷ��ţ�");
      }
    } catch (BusinessException e) {
      // TODO: handle exception
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setLoanContractDAO(LoanContractDAO loanContractDAO) {
    this.loanContractDAO = loanContractDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public String findIssuenoticeBizDate(String contractId) throws Exception {
    // TODO Auto-generated method stub
    String bizDate = "";
    try {
      bizDate = loanContractDAO.findIssuenoticeBizDate(contractId);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return bizDate;
  }

  /*
   * ��ȡ��ʵ���� (non-Javadoc)
   * 
   * @see org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS#getUserRealName(java.lang.String)
   */
  public String getUserRealName(String name) throws Exception {
    // TODO Auto-generated method stub
    String realName = "";
    try {

      realName = securityDAO.queryByUserid(name);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return realName;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public IssuenoticeTaAF queryPrintInfo(SecurityInfo securityInfo,
      IssuenoticeTaAF form) throws Exception {
    IssuenoticeTaAF printForm = new IssuenoticeTaAF();
    String contractId = form.getContractId();
    ;
    try {
      printForm = housesDAO.queryPrintInfo(contractId);
      List list = assurerDAO.queryAssurerInfo(contractId);
      if (list.size() > 0) {
        Assurer assurer = (Assurer) list.get(0);
        printForm.setBailName(assurer.getEmpName());
      }
      String headId = printForm.getHeadId();
      if (headId != null && !"".equals(headId)) {
        Developer developer = developerDAO.queryById(new Integer(headId));
        printForm.setHouse(developer.getDeveloperName());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return printForm;
  }

  public void setAssurerDAO(AssurerDAO assurerDAO) {
    this.assurerDAO = assurerDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public Borrower findIssuenoticeBorrower(String contractId) throws Exception {
    // TODO Auto-generated method stub
    Borrower borrower = new Borrower();
    try {
      borrower = borrowerDAO.queryById(contractId);
      borrower.setIsPrintIou("1");

    } catch (Exception e) {
      e.printStackTrace();
    }
    return borrower;
  }

  public List findIssuenoticeTcList(Pagination pagination, List loanbankList,
      String type) throws Exception {
    List list = null;
    List countlist = null;
    String contractId = "";
    String borrowerName = "";
    String cardNum = "";
    String loanBankId = "";
    String contractSt = "";
    String isPrint = "";
    BigDecimal loanMoney = new BigDecimal(0.00);
    try {
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
      if (pagination.getQueryCriterions().get("contractSt") != null) {
        contractSt = (String) pagination.getQueryCriterions().get("contractSt");
      }
      if (pagination.getQueryCriterions().get("isPrint") != null) {
        isPrint = (String) pagination.getQueryCriterions().get("isPrint");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = borrowerAccDAO.queryIssuenoticeTcList(contractId, borrowerName,
          cardNum, loanBankId, contractSt, orderBy, order, start, pageSize,
          loanbankList, type, page, isPrint);
      if (list.size() != 0) {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
          IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) iter.next();
          issuenoticeTbDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getLoanMode().toString()),
              BusiConst.PLRECOVERTYPE));
          issuenoticeTbDTO.setContractSt(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getContractSt().toString()),
              BusiConst.PLCONTRACTSTATUS));
          CollBank dto = collBankDAO.getCollBankByCollBankid(issuenoticeTbDTO
              .getLoanBankId());
          issuenoticeTbDTO.setLoanBankId(dto.getCollBankName());// ��������
          issuenoticeTbDTO.setTemp_loanMonthRate((issuenoticeTbDTO
              .getLoanMonthRate().multiply(new BigDecimal(100)) + "%"));
        }
      }
      countlist = borrowerAccDAO.queryIssuenoticeTcListCount(contractId,
          borrowerName, cardNum, loanBankId, contractSt, loanbankList, type,
          isPrint);
      if (countlist.size() != 0) {
        Iterator iter = countlist.iterator();
        while (iter.hasNext()) {
          IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) iter.next();
          issuenoticeTbDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getLoanMode().toString()),
              BusiConst.PLRECOVERTYPE));
          CollBank dto = collBankDAO.getCollBankByCollBankid(issuenoticeTbDTO
              .getLoanBankId());
          issuenoticeTbDTO.setLoanBankId(dto.getCollBankName());// ��������
          loanMoney = loanMoney.add(issuenoticeTbDTO.getLoanMoney());
        }
        IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) list.get(0);
        issuenoticeTbDTO.setLoanMoneySum(loanMoney);
      }
      if (countlist != null) {
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void updateIssuenoticeBorrowerAcc(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      for (int i = 0; i < contractId.length; i++) {
        int count = 0;
        count = borrowerAccDAO.findBorrowerAccByContractSt9_wsh_1(
            contractId[i], "1", "3").intValue();
        if (count == 1) {
          be = new BusinessException("������ȷ�Ϲ��Ľ���ͬ��");
          throw be;
        }
        borrowerAccDAO.updateBorrowerAccContractStatus1_wsh_fin(contractId[i],
            "1", securityInfo.getUserName());
      }

    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

  public List findIssuenoticeTdList(Pagination pagination, List loanbankList,
      String type) throws Exception {
    List list = null;
    List countlist = null;
    String contractId = "";
    String borrowerName = "";
    String cardNum = "";
    String loanBankId = "";
    String contractSt = "";
    String isPrint = "";
    BigDecimal loanMoney = new BigDecimal(0.00);
    try {
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
      if (pagination.getQueryCriterions().get("contractSt") != null) {
        contractSt = (String) pagination.getQueryCriterions().get("contractSt");
      }
      if (pagination.getQueryCriterions().get("isPrint") != null) {
        isPrint = (String) pagination.getQueryCriterions().get("isPrint");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = borrowerAccDAO.queryIssuenoticeTdList(contractId, borrowerName,
          cardNum, loanBankId, contractSt, orderBy, order, start, pageSize,
          loanbankList, type, page, isPrint);
      if (list.size() != 0) {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
          IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) iter.next();
          issuenoticeTbDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getLoanMode().toString()),
              BusiConst.PLRECOVERTYPE));
          issuenoticeTbDTO.setContractSt(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getContractSt().toString()),
              BusiConst.PLCONTRACTSTATUS));
          CollBank dto = collBankDAO.getCollBankByCollBankid(issuenoticeTbDTO
              .getLoanBankId());
          issuenoticeTbDTO.setLoanBankId(dto.getCollBankName());// ��������
          issuenoticeTbDTO.setTemp_loanMonthRate((issuenoticeTbDTO
              .getLoanMonthRate().multiply(new BigDecimal(100)) + "%"));
        }
      }
      countlist = borrowerAccDAO.queryIssuenoticeTdListCount(contractId,
          borrowerName, cardNum, loanBankId, contractSt, loanbankList, type,
          isPrint);
      if (countlist.size() != 0) {
        Iterator iter = countlist.iterator();
        while (iter.hasNext()) {
          IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) iter.next();
          issuenoticeTbDTO.setTemp_loanMode(BusiTools.getBusiValue(Integer
              .parseInt(issuenoticeTbDTO.getLoanMode().toString()),
              BusiConst.PLRECOVERTYPE));
          CollBank dto = collBankDAO.getCollBankByCollBankid(issuenoticeTbDTO
              .getLoanBankId());
          issuenoticeTbDTO.setLoanBankId(dto.getCollBankName());// ��������
          loanMoney = loanMoney.add(issuenoticeTbDTO.getLoanMoney());
        }
        IssuenoticeTbDTO issuenoticeTbDTO = (IssuenoticeTbDTO) list.get(0);
        issuenoticeTbDTO.setLoanMoneySum(loanMoney);
      }
      if (countlist != null) {
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void updateIssuenoticeBorrowerAcc_1(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      for (int i = 0; i < contractId.length; i++) {
        int count = 0;
        count = borrowerAccDAO.findBorrowerAccByContractSt9_wsh(contractId[i])
            .intValue();
        if (count == 0) {
          be = new BusinessException("�ñ�ҵ���״̬�����·�֪ͨ�飬������ɾ����");
          throw be;
        }
        // borrowerAccDAO.updateBorrowerAccContractStatus1_wsh_fin(contractId[i],
        // "");
      }

    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

  public void updateIssuenoticeBorrowerAcc_2(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      for (int i = 0; i < contractId.length; i++) {
        int count = 0;
        count = borrowerAccDAO
            .findBorrowerAccByContractSt9_wsh_2(contractId[i]).intValue();
        if (count >= 1) {
          be = new BusinessException("�����Ѵ�ӡ���Ľ���ͬ��");
          throw be;
        }
        borrowerAccDAO.updateBorrowerAccContractStatus1_wsh_fin_chen(
            contractId[i], securityInfo.getUserName());
      }

    } catch (BusinessException e) {
      be = new BusinessException("�����Ѵ�ӡ���Ľ���ͬ��");
      throw be;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

  public LoanBank findIssuenoticeTaInfo(String loanBankId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ;
    return loanBankDAO.queryById(loanBankId);
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public String getBankName(String loanbankid) throws Exception {
    // TODO Auto-generated method stub
    CollBank dto = collBankDAO.getCollBankByCollBankid(loanbankid);
    return dto.getCollBankName();
  }

  public String findloanBankInAccount(String contractId) throws Exception {
    // TODO Auto-generated method stub
    return loanContractDAO.findloanBankInAccount(contractId);
  }

  public String findloanBankOutAccount(String contractId) throws Exception {
    // TODO Auto-generated method stub
    return loanContractDAO.findloanBankOutAccount(contractId);
  }

  public void updateIssuenoticeBorrowerAcc_3(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      for (int i = 0; i < contractId.length; i++) {
        int count = 0;
        count = borrowerAccDAO.findBorrowerAccByContractSt9_wsh_1(
            contractId[i], "0", "1").intValue();
        if (count == 1) {
          be = new BusinessException("����δȷ�Ϲ�����ɴ�ӡ���Ľ���ͬ��");
          throw be;
        }
        borrowerAccDAO.updateBorrowerAccContractStatus1_wsh_fin(contractId[i],
            "0", null);
      }

    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

  public void updateIssuenoticeBorrowerAcc_4(String contractId[],
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BusinessException be = null;
    try {
      // countΪ��ѯ�ļ�¼����
      for (int i = 0; i < contractId.length; i++) {
        int count = 0;
        count = borrowerAccDAO.findBorrowerAccByContractSt9_wsh_3(
            contractId[i], "0").intValue();
        if (count == 1) {
          be = new BusinessException("����δ��ӡ������ƾ֤�Ľ���ͬ��");
          throw be;
        }
        borrowerAccDAO.updateBorrowerAccContractStatus1_wsh_fin_3(
            contractId[i], "0", null);
      }

    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void setSpecialBorrowerDAO(SpecialBorrowerDAO specialBorrowerDAO) {
    this.specialBorrowerDAO = specialBorrowerDAO;
  }

  public IssuenoticePrintDTO getchunaPrint(String loanBankId,
      String[] contractId, String bizDate, String realname, String username)
      throws Exception, BusinessException {
    IssuenoticePrintDTO dto = new IssuenoticePrintDTO();
    try {
      for (int i = 0; i < contractId.length; i++) {
        Object[] obj = housesDAO.getLoanMoneyPerson(contractId[i]);
        if(contractId[i].equals("")){
          continue;
        }
        if (obj[0] != null) {
          dto.setMoney(dto.getMoney().add(new BigDecimal(obj[0].toString())));
        }
        if (i == contractId.length - 1) {
          if (obj[1] != null) {
            dto.setLastPerson(securityDAO.queryByUserid(obj[1].toString()));
          }
          if (obj[2] != null) {
            dto.setVipchkPerson(securityDAO.queryByUserid(obj[2].toString()));
          }
          if (obj[3] != null) {
            dto.setFinchkPerson(securityDAO.queryByUserid(obj[3].toString()));
          }
        }
        int count = borrowerAccDAO.findBorrowerAccByContractSt9_wsh_2(
            contractId[i]).intValue();
        if (count >= 1) {
          throw new BusinessException("�����Ѵ�ӡ���Ľ���ͬ��");
        }
      }
      CollBank collBank = collBankDAO.getCollBankByCollBankid(loanBankId);
      dto.setBankName(collBank.getCollBankName());
      dto.setInAccount(loanContractDAO.findloanBankInAccount(loanBankId));
      dto.setOutAccount(loanContractDAO.findloanBankOutAccount(loanBankId));
      dto.setBizDate(bizDate);
      dto.setUsername(realname);
      for (int i = 0; i < contractId.length; i++) {
        borrowerAccDAO.updateBorrowerAccContractStatus1_wsh_fin_chen(
            contractId[i], username);
      }
    } catch (BusinessException e) {
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("��ӡ����");
    }
    return dto;
  }

}
