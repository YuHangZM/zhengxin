package org.xpup.hafmis.sysloan.contractchg.baseinfochg.business;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractChgDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.CongealInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractChg;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.contractchg.baseinfochg.bsinterface.IBaseinfochgBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BuyHouserDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyNewDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyTeListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTeNewAF;

public class BaseinfochgBS implements IBaseinfochgBS {

  private BorrowerDAO borrowerDAO = null;

  private OrgDAO orgDAO = null;

  private DeveloperDAO developerDAO = null;

  private AssistantBorrowerDAO assistantBorrowerDAO = null;

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private EmpDAO empDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private DevelopProjectDAO developProjectDAO = null;

  private HousesDAO housesDAO = null;

  private ContractChgDAO contractChgDAO = null;

  private CongealInfoDAO congealInfoDAO = null;

  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }

  public void setContractChgDAO(ContractChgDAO contractChgDAO) {
    this.contractChgDAO = contractChgDAO;
  }

  public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
    this.assistantBorrowerDAO = assistantBorrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  // ��ʾ�б�
  public LoanapplyTeNewAF showBaseinfochgTa(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {

    LoanapplyTeNewAF loanapplytenewAF = new LoanapplyTeNewAF();

    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String empId = (String) pagination.getQueryCriterions().get("empId");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String buyHouseType = (String) pagination.getQueryCriterions().get(
          "buyHouseType");
      String contranct_st = null;

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List newlist = new ArrayList();
      List list = new ArrayList();
      list = borrowerAccDAO.findBasicBorrowerAccList(contractId, borrowerName,
          empId, cardNum, buyHouseType, contranct_st, orderBy, orderother,
          start, pageSize, page, securityInfo);
      // �����ҳ�б���Ϣ��
      if (list != null) {
        Object obj[] = null;
        Iterator it = list.iterator();
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          LoanapplyTeListDTO listDto = new LoanapplyTeListDTO();
          listDto.setId(obj[0].toString());
          listDto.setContractid(obj[0].toString());
          if (obj[1] != null) {
            listDto.setEmpid(BusiTools.convertSixNumber(obj[1].toString()));
          } else {
            listDto.setEmpid("");
          }
          listDto.setBorrowername(obj[2].toString());
          listDto.setCardnum(obj[3].toString());
          if (obj[4] != null) {
            listDto.setBuyhousetype(BusiTools.getBusiValue_WL(
                obj[4].toString(), BusiConst.PLHOUSETYPE));
          } else {
            listDto.setBuyhousetype("");
          }
          if (obj[5] != null) {
            listDto.setLoanmoney(obj[5].toString());
          } else {
            listDto.setLoanmoney("");
          }
          if (obj[6] != null) {
            listDto.setLoanlimit(obj[6].toString());
          } else {
            listDto.setLoanlimit("");
          }
          if (obj[7] != null) {
            String temp_st = BusiTools.getBusiValue(Integer.parseInt(obj[7]
                .toString()), BusiConst.PLCONTRACTSTATUS);
            listDto.setContract_st(temp_st);
          } else {
            listDto.setContract_st("");
          }
          newlist.add(listDto);
        }
        loanapplytenewAF.setList(newlist);
      } else {
        loanapplytenewAF.setList(list);
      }
      // �б���Ϣ����
      List lis = new ArrayList();
      lis = borrowerAccDAO.findBorrowerAccList(contractId, borrowerName, empId,
          cardNum, buyHouseType, contranct_st, securityInfo);

      pagination.setNrOfElements(lis.size());
      loanapplytenewAF.setCount(lis.size() + "");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytenewAF;
  }

  // ��ʾ�������Ϣ
  public LoanapplyNewAF showBaseinfochgTb(String contranctid) throws Exception {

    LoanapplyNewAF loanaf = new LoanapplyNewAF();
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contranctid);
    if (borrower.getEmpSt() == null) {
      borrower.setEmpSt_("");
    } else {
      borrower.setEmpSt_(BusiTools.getBusiValue(Integer.parseInt(borrower
          .getEmpSt()), BusiConst.OLDPAYMENTSTATE));
    }
    if (borrower.getOtherArrearage() != null) {
      borrower
          .setOtherArrearage_(borrower.getOtherArrearage().equals("0") ? "��"
              : "��");
    }
    if (borrower.getMonthPay() == null) {
      borrower.setMonthPay(new BigDecimal(0.00));
    }
    if (borrower.getMonthSalary() == null) {
      borrower.setMonthSalary(new BigDecimal(0.00));
    }
    if (borrower.getAccBlnce() == null) {
      borrower.setAccBlnce(new BigDecimal(0.00));
    }
    loanaf.setBorrower(borrower);
    return loanaf;
  }

  // �޸Ľ������Ϣ
  public void updateBorrowerInfo(String contranctid,
      LoanapplyNewAF loanapplyNewAF, SecurityInfo securityInfo)
      throws Exception {
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contranctid);
    try {
      String temp_i = "0";
      // �޸�ְ��
      String business = borrower.getBusiness();
      if (business == null) {
        business = "";
      }
      if (!business.equals(loanapplyNewAF.getBorrower().getBusiness().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("ְ��");
        contractchg.setChgBefInfo(business);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getBusiness()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        borrower.setBusiness(loanapplyNewAF.getBorrower().getBusiness().trim());
        temp_i = "1";
      }
      // �޸����֤��
      String cardNum = borrower.getCardNum();
      if (cardNum == null) {
        cardNum = "";
      }
      if (!cardNum.equals(loanapplyNewAF.getBorrower().getCardNum().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("���֤��");
        contractchg.setChgBefInfo(cardNum);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getCardNum()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setCardNum(loanapplyNewAF.getBorrower().getCardNum().trim());
      }
      // �޸�ְ��
      String title = borrower.getTitle();
      if (title == null) {
        title = "";
      }
      if (!title.equals(loanapplyNewAF.getBorrower().getTitle().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("ְ��");
        contractchg.setChgBefInfo(title);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getTitle()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setTitle(loanapplyNewAF.getBorrower().getTitle().trim());
      }
      // �޸�����
      String nation = borrower.getNation();
      if (nation == null) {
        nation = "";
      }
      if (!nation.equals(loanapplyNewAF.getBorrower().getNation().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("����");
        contractchg.setChgBefInfo(nation);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getNation()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setNation(loanapplyNewAF.getBorrower().getNation().trim());
      }
      // �޸Ļ������ڵ�
      String nationplace = borrower.getNativePlace();
      if (nationplace == null) {
        nationplace = "";
      }
      if (!nationplace.equals(loanapplyNewAF.getBorrower().getNativePlace()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("�������ڵ�");
        contractchg.setChgBefInfo(nationplace);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getNativePlace()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setNativePlace(loanapplyNewAF.getBorrower().getNativePlace()
            .trim());
      }
      // �޸Ļ���״��
      String marriage = borrower.getMarriageSt();
      if (marriage == null) {
        marriage = "";
      }
      if (!marriage.equals(loanapplyNewAF.getBorrower().getMarriageSt().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("����״��");
        contractchg.setChgBefInfo(marriage);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getMarriageSt()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setMarriageSt(loanapplyNewAF.getBorrower().getMarriageSt()
            .trim());
      }
      // �޸��Ļ��̶�
      String degree = borrower.getDegree();
      if (degree == null) {
        degree = "";
      }
      if (!degree.equals(loanapplyNewAF.getBorrower().getDegree().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("�Ļ��̶�");
        contractchg.setChgBefInfo(degree);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getDegree()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setDegree(loanapplyNewAF.getBorrower().getDegree().trim());
      }
      // �޸ļ�ͥסַ
      String homeaddr = borrower.getHomeAddr();
      if (homeaddr == null) {
        homeaddr = "";
      }
      if (!homeaddr.equals(loanapplyNewAF.getBorrower().getHomeAddr().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("��ͥסַ");
        contractchg.setChgBefInfo(homeaddr);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHomeAddr()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHomeAddr(loanapplyNewAF.getBorrower().getHomeAddr().trim());
      }
      // �޸ľ�ס��ַ��������
      String homemail = borrower.getHomeMail();
      if (homemail == null) {
        homemail = "";
      }
      if (!homemail.equals(loanapplyNewAF.getBorrower().getHomeMail().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("��ס��ַ��������");
        contractchg.setChgBefInfo(homemail);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHomeMail()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHomeMail(loanapplyNewAF.getBorrower().getHomeMail().trim());
      }
      // �޸�סլ�绰
      String housetel = borrower.getHouseTel();
      if (housetel == null) {
        housetel = "";
      }
      if (!housetel.equals(loanapplyNewAF.getBorrower().getHouseTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("סլ�绰");
        contractchg.setChgBefInfo(housetel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHouseTel()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHouseTel(loanapplyNewAF.getBorrower().getHouseTel().trim());
      }
      // �޸��ƶ��绰
      String homemobile = borrower.getHomeMobile();
      if (homemobile == null) {
        homemobile = "";
      }
      if (!homemobile.equals(loanapplyNewAF.getBorrower().getHomeMobile()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("�ƶ��绰");
        contractchg.setChgBefInfo(homemobile);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getHomeMobile()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setHomeMobile(loanapplyNewAF.getBorrower().getHomeMobile()
            .trim());
      }
      // �޸ĵ�λ����
      String orgname = borrower.getOrgName();
      if (orgname == null) {
        orgname = "";
      }
      if (!orgname.equals(loanapplyNewAF.getBorrower().getOrgName().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("��λ����");
        contractchg.setChgBefInfo(orgname);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgName()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgName(loanapplyNewAF.getBorrower().getOrgName().trim());
      }
      // �޸ĵ�λ�绰
      String orgtel = borrower.getOrgTel();
      if (orgtel == null) {
        orgtel = "";
      }
      if (!orgtel.equals(loanapplyNewAF.getBorrower().getOrgTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("��λ�绰");
        contractchg.setChgBefInfo(orgtel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgTel()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgTel(loanapplyNewAF.getBorrower().getOrgTel().trim());
      }
      // �޸ĵ�λ����������
      String orgmail = borrower.getOrgMail();
      if (orgmail == null) {
        orgmail = "";
      }
      if (!orgmail.equals(loanapplyNewAF.getBorrower().getOrgMail().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("��������");
        contractchg.setChgBefInfo(orgmail);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgMail()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgMail(loanapplyNewAF.getBorrower().getOrgMail().trim());
      }
      // �޸ĵ�λ��ַ
      String orgaddr = borrower.getOrgAddr();
      if (orgaddr == null) {
        orgaddr = "";
      }
      if (!orgaddr.equals(loanapplyNewAF.getBorrower().getOrgAddr().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("��λ��ַ");
        contractchg.setChgBefInfo(orgaddr);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getOrgAddr()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOrgAddr(loanapplyNewAF.getBorrower().getOrgAddr().trim());
      }
      // �޸�������ϵ������A
      String contactAName = borrower.getContactAName();
      if (contactAName == null) {
        contactAName = "";
      }
      if (!contactAName.equals(loanapplyNewAF.getBorrower().getContactAName()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ������A");
        contractchg.setChgBefInfo(contactAName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAName(loanapplyNewAF.getBorrower().getContactAName()
            .trim());
      }
      // �޸������˹�ϵA
      String relationA = borrower.getRelationA();
      if (relationA == null) {
        relationA = "";
      }
      if (!relationA.equals(loanapplyNewAF.getBorrower().getRelationA().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("�����˹�ϵA");
        contractchg.setChgBefInfo(relationA);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getRelationA()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setRelationA(loanapplyNewAF.getBorrower().getRelationA()
            .trim());
      }
      // �޸�������ϵ�˹�����λA
      String contactAOrgName = borrower.getContactAOrgName();
      if (contactAOrgName == null) {
        contactAOrgName = "";
      }
      if (!contactAOrgName.equals(loanapplyNewAF.getBorrower()
          .getContactAOrgName().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ�˹�����λA");
        contractchg.setChgBefInfo(contactAOrgName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAOrgName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAOrgName(loanapplyNewAF.getBorrower()
            .getContactAOrgName().trim());
      }
      // �޸�������ϵ�˵�λ�绰A
      String contactAOrgTel = borrower.getContactAOrgTel();
      if (contactAOrgTel == null) {
        contactAOrgTel = "";
      }
      if (!contactAOrgTel.equals(loanapplyNewAF.getBorrower()
          .getContactAOrgTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ�˵�λ�绰A");
        contractchg.setChgBefInfo(contactAOrgTel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAOrgTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAOrgTel(loanapplyNewAF.getBorrower()
            .getContactAOrgTel().trim());
      }
      // �޸�������ϵ��סլ�绰A
      String contactAHouseTel = borrower.getContactAHouseTel();
      if (contactAHouseTel == null) {
        contactAHouseTel = "";
      }
      if (!contactAHouseTel.equals(loanapplyNewAF.getBorrower()
          .getContactAHouseTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ��סլ�绰A");
        contractchg.setChgBefInfo(contactAHouseTel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAHouseTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAHouseTel(loanapplyNewAF.getBorrower()
            .getContactAHouseTel().trim());
      }
      // �޸�������ϵ���ƶ��绰A
      String contactAMobile = borrower.getContactAMobile();
      if (contactAMobile == null) {
        contactAMobile = "";
      }
      if (!contactAMobile.equals(loanapplyNewAF.getBorrower()
          .getContactAMobile().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ���ƶ��绰A");
        contractchg.setChgBefInfo(contactAMobile);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactAMobile().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactAMobile(loanapplyNewAF.getBorrower()
            .getContactAMobile().trim());
      }
      // �޸�������ϵ������B
      String contactBName = borrower.getContactBName();
      if (contactBName == null) {
        contactBName = "";
      }
      if (!contactBName.equals(loanapplyNewAF.getBorrower().getContactBName()
          .trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ������B");
        contractchg.setChgBefInfo(contactBName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBName(loanapplyNewAF.getBorrower().getContactBName()
            .trim());
      }
      // �޸������˹�ϵB
      String relationB = borrower.getRelationB();
      if (relationB == null) {
        relationB = "";
      }
      if (!relationB.equals(loanapplyNewAF.getBorrower().getRelationB().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("�����˹�ϵB");
        contractchg.setChgBefInfo(relationB);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getRelationB()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setRelationB(loanapplyNewAF.getBorrower().getRelationB()
            .trim());
      }
      // �޸�������ϵ�˹�����λB
      String contactBOrgName = borrower.getContactBOrgName();
      if (contactBOrgName == null) {
        contactBOrgName = "";
      }
      if (!contactBOrgName.equals(loanapplyNewAF.getBorrower()
          .getContactBOrgName().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ�˹�����λB");
        contractchg.setChgBefInfo(contactBOrgName);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBOrgName().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBOrgName(loanapplyNewAF.getBorrower()
            .getContactBOrgName().trim());
      }
      // �޸�������ϵ�˵�λ�绰B
      String contactborgtel = borrower.getContactBOrgTel();
      if (contactborgtel == null) {
        contactborgtel = "";
      }
      if (!contactborgtel.equals(loanapplyNewAF.getBorrower()
          .getContactBOrgTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ�˵�λ�绰B");
        contractchg.setChgBefInfo(contactborgtel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBOrgTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBOrgTel(loanapplyNewAF.getBorrower()
            .getContactBOrgTel().trim());
      }
      // �޸�������ϵ��סլ�绰B
      String contactBHouseTel = borrower.getContactBHouseTel();
      if (contactBHouseTel == null) {
        contactBHouseTel = "";
      }
      if (!contactBHouseTel.equals(loanapplyNewAF.getBorrower()
          .getContactBHouseTel().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ��סլ�绰B");
        contractchg.setChgBefInfo(contactBHouseTel);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBHouseTel().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBHouseTel(loanapplyNewAF.getBorrower()
            .getContactBHouseTel().trim());
      }
      // �޸�������ϵ���ƶ��绰B
      String contactBMobile = borrower.getContactBMobile();
      if (contactBMobile == null) {
        contactBMobile = "";
      }
      if (!contactBMobile.equals(loanapplyNewAF.getBorrower()
          .getContactBMobile().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("������ϵ���ƶ��绰B");
        contractchg.setChgBefInfo(contactBMobile);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getContactBMobile().trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setContactBMobile(loanapplyNewAF.getBorrower()
            .getContactBMobile().trim());
      }
      // �޸ı�ע
      String remark = borrower.getRemark();
      if (remark == null) {
        remark = "";
      }
      if (!remark.equals(loanapplyNewAF.getBorrower().getRemark().trim())) {

        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("��ע");
        contractchg.setChgBefInfo(remark);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower().getRemark()
            .trim());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setRemark(loanapplyNewAF.getBorrower().getRemark().trim());
      }
      // �޸�����Ƿ��
      String otherArrearage = borrower.getOtherArrearage();
      if (otherArrearage == null) {
        otherArrearage = "";
      }
      if (!otherArrearage.equals(loanapplyNewAF.getBorrower()
          .getOtherArrearage())) {
        ContractChg contractchg = new ContractChg();
        contractchg.setContractId(contranctid);
        contractchg.setChgColumn("����Ƿ��");
        contractchg.setChgBefInfo(otherArrearage);
        contractchg.setChgEndInfo(loanapplyNewAF.getBorrower()
            .getOtherArrearage());
        contractchg.setOperator(securityInfo.getUserName());
        contractchg.setOpTime(new Date());
        contractchg.setContractType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_BORROWERINFO));
        contractChgDAO.insert(contractchg);
        temp_i = "1";
        borrower.setOtherArrearage(loanapplyNewAF.getBorrower()
            .getOtherArrearage());
      }
      if (temp_i.equals("1")) {
        PlOperateLog plOperateLog = new PlOperateLog();// ������־����
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO));// ������Ϣ���_�������Ϣ
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(contranctid));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contranctid);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * hanl ��ʾ�����������Ϣ
   */
  public LoanapplyTbNewAF findAssistanBorrowerInfo_yg(String contractid,
      String auxiliaryid, String sun) throws BusinessException {
    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    try {
      List lists = assistantBorrowerDAO
          .findAssistanBorrowerListByContractid(contractid);// �������б�
      List list = assistantBorrowerDAO
          .findAssistanBorrowerListByContractid_yg(contractid);// �������б�
      boolean flag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// �Ƿ�¼�����
      if (flag) {
        loanapplytbnewAF.setTemp_types("11");// ¼����� ��ӡ�ȷ������ְ����š��޸ġ�ɾ�� ��ť����
      }
      if (list.size() == 0) {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contractid);// ���ݺ�ͬ�ţ���ѯ���������
        loanapplytbnewAF.setContractId(contractid);
        loanapplytbnewAF.setBorrowerName(borrowernname);
      } else {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contractid);// ���ݺ�ͬ�ţ���ѯ���������
        String maxauxiliaryid = "";
        if (auxiliaryid == null) {
          maxauxiliaryid = assistantBorrowerDAO
              .findMaxAuxiliaryidByContractid_yg(contractid);// ���ݺ�ͬ��Ų�ѯ���ĸ�������˱��
        } else {
          maxauxiliaryid = auxiliaryid;
        }
        AssistantBorrower ab = new AssistantBorrower();
        ab = assistantBorrowerDAO.queryById(new Integer(maxauxiliaryid));// ���������
        // �ɴ����
        String orgRate = "";
        String empRate = "";
        if (ab.getOrgId() != null && !"".equals(ab.getOrgId().toString())) {
          Org org = orgDAO.queryById(new Integer(ab.getOrgId().toString()));
          if (org != null) {
            orgRate = org.getOrgRate().toString();
            empRate = org.getEmpRate().toString();
            loanapplytbnewAF.setOrgRate(orgRate);
            loanapplytbnewAF.setEmpRate(empRate);
          }
        }
        // ����
        loanapplytbnewAF.setContractId(ab.getContractId().trim());
        loanapplytbnewAF.setBorrowerName(borrowernname.trim());
        if (ab.getEmpId() == null || ab.getEmpId().toString().equals("0")) {
          loanapplytbnewAF.setEmpId("");
        } else {
          loanapplytbnewAF.setEmpId(BusiTools.convertSixNumber(ab.getEmpId()
              .toString()));
        }
        if (ab.getStatus().equals("1")) {
          loanapplytbnewAF.setRelationStatus("����(���)");
        } else {
          loanapplytbnewAF.setRelationStatus("����");
        }
        loanapplytbnewAF.setName(ab.getName());
        loanapplytbnewAF.setRelation(ab.getRelation());
        loanapplytbnewAF.setSex(ab.getSex());
        loanapplytbnewAF.setCardKind(ab.getCardKind());
        loanapplytbnewAF.setCardNum(ab.getCardNum());
        loanapplytbnewAF.setBirthday(ab.getBirthday());
        if (ab.getAge() == null) {
          loanapplytbnewAF.setAge("");
        } else {
          loanapplytbnewAF.setAge(ab.getAge().toString());
        }

        loanapplytbnewAF.setBusiness(ab.getBusiness());
        loanapplytbnewAF.setTitle(ab.getTitle());
        loanapplytbnewAF.setNation(ab.getNation());
        loanapplytbnewAF.setNativePlace(ab.getNativePlace());
        loanapplytbnewAF.setMarriageSt(ab.getMarriageSt());
        loanapplytbnewAF.setDegree(ab.getDegree());
        loanapplytbnewAF.setHomeAddr(ab.getHomeAddr());
        loanapplytbnewAF.setHomeMail(ab.getHomeMail());
        loanapplytbnewAF.setHouseTel(ab.getHouseTel());
        loanapplytbnewAF.setHomeMobile(ab.getHomeMobile());
        loanapplytbnewAF.setEmpSt(ab.getEmpSt());
        if (ab.getOrgId() == null) {
          loanapplytbnewAF.setOrgId("");
        } else {
          loanapplytbnewAF.setOrgId(BusiTools.convertTenNumber(ab.getOrgId()
              .toString()));
        }
        loanapplytbnewAF.setOrgName(ab.getOrgName());
        loanapplytbnewAF.setOrgTel(ab.getOrgTel());
        loanapplytbnewAF.setOrgMail(ab.getOrgMail());
        loanapplytbnewAF.setOrgAddr(ab.getOrgAddr());
        if (ab.getAccBlnce() == null) {
          loanapplytbnewAF.setAccBlnce("");
        } else {
          loanapplytbnewAF.setAccBlnce(ab.getAccBlnce().toString());
        }
        if (ab.getMonthSalary() == null) {
          loanapplytbnewAF.setMonthSalary("");
        } else {
          loanapplytbnewAF.setMonthSalary(ab.getMonthSalary().toString());
        }
        if (ab.getMonthPay() == null) {
          loanapplytbnewAF.setMonthPay("");
        } else {
          loanapplytbnewAF.setMonthPay(ab.getMonthPay().toString());
        }
        // ��������֧ȡ���
        // �ʻ����-�½ɴ��*12
        String zdjzqje = "";
        if (ab.getMaxPickBalance() != null
            && !"".equals(ab.getMaxPickBalance())) {
          zdjzqje = ab.getMaxPickBalance().toString();
        } else if (!"".equals(loanapplytbnewAF.getAccBlnce())
            && !"".equals(loanapplytbnewAF.getMonthPay())) {
          zdjzqje = new BigDecimal(loanapplytbnewAF.getAccBlnce())
              .subtract(new BigDecimal(loanapplytbnewAF.getMonthPay())
                  .multiply(new BigDecimal(12)))
              + "";
        }
        if (!zdjzqje.equals("") && Float.parseFloat(zdjzqje) < 0) {
          zdjzqje = "0";
        }
        loanapplytbnewAF.setZdjzqje(zdjzqje);
        loanapplytbnewAF.setBgnDate(ab.getBgnDate());
        loanapplytbnewAF.setEndDate(ab.getEndDate());

        loanapplytbnewAF.setMaxauxiliaryid(maxauxiliaryid);
        if (ab.getEmpId() == null) {
          boolean flag1 = borrowerLoanInfoDAO
              .isFindContractidByContractid(contractid);
          if (flag1) {
            loanapplytbnewAF.setTemp_tes("3");
          } else {
            loanapplytbnewAF.setTemp_tes("2");
          }
        } else {
          loanapplytbnewAF.setTemp_tes("1");
        }
      }
      if (sun != null && sun.equals("1")) {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contractid);// ���ݺ�ͬ�ţ���ѯ���������
        String maxauxiliaryid = "";
        if (auxiliaryid == null) {
          maxauxiliaryid = assistantBorrowerDAO
              .findMaxAuxiliaryidByContractid_yg(contractid);// ���ݺ�ͬ��Ų�ѯ���ĸ�������˱��
        } else {
          maxauxiliaryid = auxiliaryid;
        }
        AssistantBorrower ab = new AssistantBorrower();
        ab = assistantBorrowerDAO.queryById(new Integer(maxauxiliaryid));// ���������
        // �ɴ����
        String orgRate = "";
        String empRate = "";
        if (ab.getOrgId() != null && !"".equals(ab.getOrgId().toString())) {
          Org org = orgDAO.queryById(new Integer(ab.getOrgId().toString()));
          if (org != null) {
            orgRate = org.getOrgRate().toString();
            empRate = org.getEmpRate().toString();
            loanapplytbnewAF.setOrgRate(orgRate);
            loanapplytbnewAF.setEmpRate(empRate);
          }
        }
        // ����
        loanapplytbnewAF.setContractId(ab.getContractId().trim());
        loanapplytbnewAF.setBorrowerName(borrowernname.trim());
        if (ab.getEmpId() == null || ab.getEmpId().toString().equals("0")) {
          loanapplytbnewAF.setEmpId("");
        } else {
          loanapplytbnewAF.setEmpId(BusiTools.convertSixNumber(ab.getEmpId()
              .toString()));
        }
        if (ab.getStatus().equals("1")) {
          loanapplytbnewAF.setRelationStatus("����(���)");
        } else {
          loanapplytbnewAF.setRelationStatus("����");
        }
        loanapplytbnewAF.setName(ab.getName());
        loanapplytbnewAF.setRelation(ab.getRelation());
        loanapplytbnewAF.setSex(ab.getSex());
        loanapplytbnewAF.setCardKind(ab.getCardKind());
        loanapplytbnewAF.setCardNum(ab.getCardNum());
        loanapplytbnewAF.setBirthday(ab.getBirthday());
        if (ab.getAge() == null) {
          loanapplytbnewAF.setAge("");
        } else {
          loanapplytbnewAF.setAge(ab.getAge().toString());
        }

        loanapplytbnewAF.setBusiness(ab.getBusiness());
        loanapplytbnewAF.setTitle(ab.getTitle());
        loanapplytbnewAF.setNation(ab.getNation());
        loanapplytbnewAF.setNativePlace(ab.getNativePlace());
        loanapplytbnewAF.setMarriageSt(ab.getMarriageSt());
        loanapplytbnewAF.setDegree(ab.getDegree());
        loanapplytbnewAF.setHomeAddr(ab.getHomeAddr());
        loanapplytbnewAF.setHomeMail(ab.getHomeMail());
        loanapplytbnewAF.setHouseTel(ab.getHouseTel());
        loanapplytbnewAF.setHomeMobile(ab.getHomeMobile());
        loanapplytbnewAF.setEmpSt(ab.getEmpSt());
        if (ab.getOrgId() == null) {
          loanapplytbnewAF.setOrgId("");
        } else {
          loanapplytbnewAF.setOrgId(BusiTools.convertTenNumber(ab.getOrgId()
              .toString()));
        }
        loanapplytbnewAF.setOrgName(ab.getOrgName());
        loanapplytbnewAF.setOrgTel(ab.getOrgTel());
        loanapplytbnewAF.setOrgMail(ab.getOrgMail());
        loanapplytbnewAF.setOrgAddr(ab.getOrgAddr());
        if (ab.getAccBlnce() == null) {
          loanapplytbnewAF.setAccBlnce("");
        } else {
          loanapplytbnewAF.setAccBlnce(ab.getAccBlnce().toString());
        }
        if (ab.getMonthSalary() == null) {
          loanapplytbnewAF.setMonthSalary("");
        } else {
          loanapplytbnewAF.setMonthSalary(ab.getMonthSalary().toString());
        }
        if (ab.getMonthPay() == null) {
          loanapplytbnewAF.setMonthPay("");
        } else {
          loanapplytbnewAF.setMonthPay(ab.getMonthPay().toString());
        }
        // ��������֧ȡ���
        // �ʻ����-�½ɴ��*12
        String zdjzqje = "";
        if (ab.getMaxPickBalance() != null
            && !"".equals(ab.getMaxPickBalance())) {
          zdjzqje = ab.getMaxPickBalance().toString();
        } else if (!"".equals(loanapplytbnewAF.getAccBlnce())
            && !"".equals(loanapplytbnewAF.getMonthPay())) {
          zdjzqje = new BigDecimal(loanapplytbnewAF.getAccBlnce())
              .subtract(new BigDecimal(loanapplytbnewAF.getMonthPay())
                  .multiply(new BigDecimal(12)))
              + "";
        }
        if (!zdjzqje.equals("") && Float.parseFloat(zdjzqje) < 0) {
          zdjzqje = "0";
        }
        loanapplytbnewAF.setZdjzqje(zdjzqje);
        loanapplytbnewAF.setBgnDate(ab.getBgnDate());
        loanapplytbnewAF.setEndDate(ab.getEndDate());

        loanapplytbnewAF.setMaxauxiliaryid(maxauxiliaryid);
        if (ab.getEmpId() == null) {
          boolean flag1 = borrowerLoanInfoDAO
              .isFindContractidByContractid(contractid);
          if (flag1) {
            loanapplytbnewAF.setTemp_tes("3");
          } else {
            loanapplytbnewAF.setTemp_tes("2");
          }
        } else {
          loanapplytbnewAF.setTemp_tes("1");
        }
      }
      loanapplytbnewAF.setList(lists);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytbnewAF;
  }

  // ��ʾ�����������Ϣ
  public LoanapplyTbNewAF findAssistanBorrowerInfochg(String contranctid,
      String maxauxiliaryid) throws Exception {

    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    try {
      List list = assistantBorrowerDAO
          .findAssistanBorrowerListByContractid(contranctid);
      if (list.size() == 0) {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contranctid);
        loanapplytbnewAF.setContractId(contranctid);
        loanapplytbnewAF.setBorrowerName(borrowernname);
      } else {
        String borrowernname = borrowerDAO
            .findBorrowerNameInfoByContractid(contranctid);
        String maxauxiliaryidnew = "";
        if (maxauxiliaryid == null) {
          maxauxiliaryidnew = assistantBorrowerDAO
              .findMaxAuxiliaryidByContractid(contranctid);
        } else {
          maxauxiliaryidnew = maxauxiliaryid;
        }
        AssistantBorrower ab = new AssistantBorrower();
        ab = assistantBorrowerDAO.queryById(new Integer(maxauxiliaryidnew));
        loanapplytbnewAF.setContractId(ab.getContractId());
        loanapplytbnewAF.setBorrowerName(borrowernname);
        if (ab.getEmpId() == null || ab.getEmpId().toString().equals("0")) {
          loanapplytbnewAF.setEmpId("");
        } else {
          loanapplytbnewAF.setEmpId(BusiTools.convertSixNumber(ab.getEmpId()
              .toString()));
        }
        loanapplytbnewAF.setName(ab.getName());
        loanapplytbnewAF.setRelation(ab.getRelation());
        loanapplytbnewAF.setSex(ab.getSex());
        loanapplytbnewAF.setCardKind(ab.getCardKind());
        loanapplytbnewAF.setCardNum(ab.getCardNum());
        loanapplytbnewAF.setBirthday(ab.getBirthday());
        if (ab.getAge() == null) {
          loanapplytbnewAF.setAge("");
        } else {
          loanapplytbnewAF.setAge(ab.getAge().toString());
        }

        loanapplytbnewAF.setBusiness(ab.getBusiness());
        loanapplytbnewAF.setTitle(ab.getTitle());
        loanapplytbnewAF.setNation(ab.getNation());
        loanapplytbnewAF.setNativePlace(ab.getNativePlace());
        loanapplytbnewAF.setMarriageSt(ab.getMarriageSt());
        loanapplytbnewAF.setDegree(ab.getDegree());
        loanapplytbnewAF.setHomeAddr(ab.getHomeAddr());
        loanapplytbnewAF.setHomeMail(ab.getHomeMail());
        loanapplytbnewAF.setHouseTel(ab.getHouseTel());
        loanapplytbnewAF.setHomeMobile(ab.getHomeMobile());
        if (ab.getOrgId() == null) {
          loanapplytbnewAF.setOrgId("");
        } else {
          loanapplytbnewAF.setOrgId(BusiTools.convertTenNumber(ab.getOrgId()
              .toString()));
        }
        loanapplytbnewAF.setOrgName(ab.getOrgName());
        loanapplytbnewAF.setOrgTel(ab.getOrgTel());
        loanapplytbnewAF.setOrgMail(ab.getOrgMail());
        loanapplytbnewAF.setOrgAddr(ab.getOrgAddr());
        if (ab.getAccBlnce() == null) {
          loanapplytbnewAF.setAccBlnce("");
        } else {
          loanapplytbnewAF.setAccBlnce(ab.getAccBlnce().toString());
        }
        if (ab.getMonthSalary() == null) {
          loanapplytbnewAF.setMonthSalary("");
        } else {
          loanapplytbnewAF.setMonthSalary(ab.getMonthSalary().toString());
        }
        if (ab.getMonthPay() == null) {
          loanapplytbnewAF.setMonthPay("");
        } else {
          loanapplytbnewAF.setMonthPay(ab.getMonthPay().toString());
        }
        if (ab.getEmpSt() != null) {
          loanapplytbnewAF.setEmpSt(ab.getEmpSt());
        } else {
          loanapplytbnewAF.setEmpSt("");
        }
        loanapplytbnewAF.setBgnDate(ab.getBgnDate());
        loanapplytbnewAF.setEndDate(ab.getEndDate());
        loanapplytbnewAF.setList(list);
        loanapplytbnewAF.setMaxauxiliaryid(maxauxiliaryidnew);

      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytbnewAF;
  }

  // ���Ҹ��������
  public LoanapplyTbNewAF findAssistantBorrowerInfo(String empId, String orgId,
      String contractaId) throws BusinessException {

    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    try {
      boolean flag = empDAO.getEmpidCount(new Integer(empId));
      if (flag) {
        EmpInfo empinfo = empDAO.findEmpInfoByEmpid(empId, orgId);
        String empname = empinfo.getName();
        String empcardnum = empinfo.getCardNum();
        String auxiliaryid = assistantBorrowerDAO.findAuxiliaryidByNameCardnum(
            empname, empcardnum, contractaId);
        if (auxiliaryid == null) {
          LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empId, orgId);
          loanapplytbnewAF.setEmpId(empId);
          loanapplytbnewAF.setName(inf.getEmpname());
          loanapplytbnewAF.setSex(inf.getSex().toString());
          loanapplytbnewAF.setCardKind(inf.getCardking());
          // �����ŵ���������ģ����ύʱ����������ȡ��������ֵ��
          loanapplytbnewAF.setSexhidden(inf.getSex().toString());
          loanapplytbnewAF.setCardKindhidden(inf.getCardking());

          loanapplytbnewAF.setCardNum(inf.getCardnum());
          loanapplytbnewAF.setBirthday(inf.getBirthday());
          loanapplytbnewAF.setHouseTel(inf.getTel());
          loanapplytbnewAF.setHomeMobile(inf.getMobiletle());
          loanapplytbnewAF.setOrgId(inf.getOrgid());
          loanapplytbnewAF.setOrgName(inf.getOrgname());
          loanapplytbnewAF.setOrgTel(inf.getOrgtel());
          loanapplytbnewAF.setOrgMail(inf.getPostalcode());
          loanapplytbnewAF.setAccBlnce(inf.getCardmoney());
          loanapplytbnewAF.setOrgAddr(inf.getAddress());
          loanapplytbnewAF.setMonthSalary(inf.getMonth_income());
          loanapplytbnewAF.setMonthPay(inf.getMonthpay());
          loanapplytbnewAF.setRelation("");
          loanapplytbnewAF.setNation("");
          loanapplytbnewAF.setNativePlace("");
          loanapplytbnewAF.setBusiness("");
          loanapplytbnewAF.setTitle("");
          loanapplytbnewAF.setMarriageSt("");
          loanapplytbnewAF.setDegree("");
          loanapplytbnewAF.setHomeAddr("");
          loanapplytbnewAF.setHomeMail("");
          try {
            loanapplytbnewAF.setEmpSt(BusiTools.getBusiValue(Integer
                .parseInt(inf.getPay_status()), BusiConst.OLDPAYMENTSTATE));
          } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          loanapplytbnewAF.setBgnDate(inf.getFirst_pay_month());
          loanapplytbnewAF.setEndDate(inf.getOrg_pay_month());

        } else {
          throw new BusinessException("��ְ���Ѿ��Ǹ��������");
        }
      } else {
        throw new BusinessException("��ְ��������");
      }
    } catch (BusinessException be) {

      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return loanapplytbnewAF;
  }

  // �޸ĸ��������
  public void addupdateAssistantBorrowerInfo(LoanapplyTbNewAF loanapplytbnewAF,
      SecurityInfo securityInfo, String temp_addtypechg)
      throws BusinessException, Exception {
    try {
      String contractid = loanapplytbnewAF.getContractId().trim();
      String name = loanapplytbnewAF.getName().trim();
      String cardnum = loanapplytbnewAF.getCardNum().trim();
      String auxiliaryid = assistantBorrowerDAO
          .findAssistanBorrowerByContractidNameCardnum(contractid, name,
              cardnum);
      if (auxiliaryid == null) {

        AssistantBorrower ab = new AssistantBorrower();// �������������
        ab.setContractId(loanapplytbnewAF.getContractId().trim());
        if (!loanapplytbnewAF.getEmpId().equals("")) {
          ab.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId().trim()));
        } else {
          BigDecimal bb = null;
          ab.setEmpId(bb);
        }
        ab.setName(loanapplytbnewAF.getName().trim());
        ab.setRelation(loanapplytbnewAF.getRelation().trim());

        ab.setSex(loanapplytbnewAF.getSexhidden().trim());

        ab.setCardKind(loanapplytbnewAF.getCardKindhidden().trim());

        ab.setCardNum(loanapplytbnewAF.getCardNum().trim());
        ab.setBirthday(loanapplytbnewAF.getBirthday().trim());
        if (!loanapplytbnewAF.getAge().equals("")) {
          ab.setAge(new BigDecimal(loanapplytbnewAF.getAge().trim()));
        }
        ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
        ab.setTitle(loanapplytbnewAF.getTitle().trim());
        ab.setNation(loanapplytbnewAF.getNation().trim());
        ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
        ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
        ab.setDegree(loanapplytbnewAF.getDegree().trim());
        ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
        ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
        ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
        ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
        if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
          ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
        }
        ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
        ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
        ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
        ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
        if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
          ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce().trim()));
        }
        if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
          ab.setMonthSalary(new BigDecimal(loanapplytbnewAF.getMonthSalary()
              .trim()));
        }
        if (!loanapplytbnewAF.getMonthPay().trim().equals("")) {
          ab.setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay().trim()));
        }
        // if(!loanapplytbnewAF.getEmpSt().trim().equals("")){
        // int empSt1 = BusiTools.getBusiKey(loanapplytbnewAF.getEmpSt().trim(),
        // BusiConst.PLCONTRACTSTATUS);
        // ab.setEmpSt(new Integer(empSt1).toString());
        // }
        ab.setEmpSt(loanapplytbnewAF.getEmpSt());
        ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
        ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
        ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
        ab.setOperator(securityInfo.getUserName().trim());
        ab.setOpTime(new Date());
        assistantBorrowerDAO.insert(ab);// ���븨�������

        CongealInfo congealInfo = new CongealInfo();// �����
        congealInfo.setContractId(contractid);
        if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
          congealInfo.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId()
              .trim()));
        }
        if (!loanapplytbnewAF.getEmpId().trim().equals("")) {
          congealInfo.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId()
              .trim()));
        }
        congealInfo.setEmpName(loanapplytbnewAF.getName());
        congealInfo.setCardKind(new Integer(loanapplytbnewAF
            .getCardKindhidden()).toString());// ��
        congealInfo.setCardNum(loanapplytbnewAF.getCardNum().trim());
        congealInfo.setPersonId(ab.getAuxiliaryId().toString());
        congealInfo.setStatus(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION));
        congealInfo.setType(String
            .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYTYPE));
        congealInfoDAO.insert(congealInfo);// ���붳���

        PlOperateLog plOperateLog = new PlOperateLog();// ������־����
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// ������Ϣ���_�����������Ϣ
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId().toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      } else {

        if (temp_addtypechg != null) {// ˵��������Ӱ�ť
          auxiliaryid = assistantBorrowerDAO
              .findAssistanBorrowerByContractidNameCardnum(contractid, name,
                  cardnum);
          if (auxiliaryid == null) {// �������
            AssistantBorrower ab = new AssistantBorrower();// �������������
            ab.setContractId(loanapplytbnewAF.getContractId().trim());
            if (!loanapplytbnewAF.getEmpId().trim().equals("")) {
              ab.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId().trim()));
            } else {
              BigDecimal bb = null;
              ab.setEmpId(bb);
            }
            ab.setName(loanapplytbnewAF.getName().trim());
            ab.setRelation(loanapplytbnewAF.getRelation().trim());
            ab.setSex(loanapplytbnewAF.getSexhidden().trim());
            ab.setCardKind(loanapplytbnewAF.getCardKindhidden().trim());
            ab.setCardNum(loanapplytbnewAF.getCardNum().trim());
            ab.setBirthday(loanapplytbnewAF.getBirthday().trim());
            if (!loanapplytbnewAF.getAge().trim().equals("")) {
              ab.setAge(new BigDecimal(loanapplytbnewAF.getAge().trim()));
            }
            ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
            ab.setTitle(loanapplytbnewAF.getTitle().trim());
            ab.setNation(loanapplytbnewAF.getNation().trim());
            ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
            ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
            ab.setDegree(loanapplytbnewAF.getDegree().trim());
            ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
            ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
            ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
            ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
            if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
              ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
            }
            ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
            ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
            ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
            ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
            if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
              ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce()
                  .trim()));
            }
            if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
              ab.setMonthSalary(new BigDecimal(loanapplytbnewAF
                  .getMonthSalary().trim()));
            }

            if (!loanapplytbnewAF.getEmpSt().trim().equals("")) {
              int empSt1 = BusiTools.getBusiKey(loanapplytbnewAF.getEmpSt()
                  .trim(), BusiConst.PLCONTRACTSTATUS);
              ab.setEmpSt(new Integer(empSt1).toString());
            }
            ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
            ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
            ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
            ab.setOperator(securityInfo.getUserName().trim());
            ab.setOpTime(new Date());
            assistantBorrowerDAO.insert(ab);// ���븨�������

            CongealInfo congealInfo = new CongealInfo();// �����
            congealInfo.setContractId(contractid);
            if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
              congealInfo.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId()
                  .trim()));
            }
            if (!loanapplytbnewAF.getEmpId().trim().equals("")) {
              congealInfo.setEmpId(new BigDecimal(loanapplytbnewAF.getEmpId()
                  .trim()));
            }
            congealInfo.setEmpName(loanapplytbnewAF.getName().trim());
            congealInfo.setCardKind(new Integer(loanapplytbnewAF
                .getCardKindhidden()).toString());// ��
            congealInfo.setCardNum(loanapplytbnewAF.getCardNum().trim());
            congealInfo.setPersonId(ab.getAuxiliaryId().toString());
            congealInfo.setStatus(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION));
            congealInfo.setType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYTYPE));
            congealInfoDAO.insert(congealInfo);// ���붳���

            PlOperateLog plOperateLog = new PlOperateLog();// ������־����
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// ������Ϣ���_�����������Ϣ
            plOperateLog.setOpButton(String
                .valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
            plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId()
                .toString()));
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpTime(new Date());
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setContractId(contractid);
            plOperateLogDAO.insert(plOperateLog);
          } else {// �Ѿ��Ǹ���������ˣ������������
            throw new BusinessException("��ְ���Ѿ�Ϊ���������");
          }
        } else { // ���¸����������Ϣ
          AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(
              auxiliaryid));
          ab.setAge(new BigDecimal(loanapplytbnewAF.getAge()));
          ab.setBirthday(loanapplytbnewAF.getBirthday());
          ab.setRelation(loanapplytbnewAF.getRelation());
          if (ab.getStatus().equals("1")) {
            ab.setStatus("0");
          }
          String temp_i = "0";
          // ����
          String nationst = ab.getNation();
          if (nationst == null) {
            nationst = "";
          }

          if (!nationst.equals(loanapplytbnewAF.getNation().trim())) {
            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("����");
            contractchg.setChgBefInfo(nationst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getNation().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setNation(loanapplytbnewAF.getNation().trim());
          }
          // �޸Ļ������ڵ�
          String nationplacest = ab.getNativePlace();
          if (nationplacest == null) {
            nationplacest = "";
          }
          if (!nationplacest.equals(loanapplytbnewAF.getNativePlace().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("�������ڵ�");
            contractchg.setChgBefInfo(nationplacest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getNativePlace().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
          }
          // ְ��
          String businessst = ab.getBusiness();
          if (businessst == null) {
            businessst = "";
          }
          if (!businessst.equals(loanapplytbnewAF.getBusiness().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("ְ��");
            contractchg.setChgBefInfo(businessst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getBusiness().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
          }
          // ְ��
          String titlest = ab.getTitle();
          if (titlest == null) {
            titlest = "";
          }
          if (!titlest.equals(loanapplytbnewAF.getTitle().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("ְ��");
            contractchg.setChgBefInfo(titlest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getTitle().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setTitle(loanapplytbnewAF.getTitle().trim());
          }
          // ����״��
          String marriages = ab.getMarriageSt();
          if (marriages == null) {
            marriages = "";
          }
          if (!marriages.equals(loanapplytbnewAF.getMarriageSt().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("����״��");
            contractchg.setChgBefInfo(marriages);
            contractchg.setChgEndInfo(loanapplytbnewAF.getMarriageSt().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
          }
          // �Ļ��̶�
          String degreest = ab.getDegree();
          if (degreest == null) {
            degreest = "";
          }
          if (!degreest.equals(loanapplytbnewAF.getDegree().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("�Ļ��̶�");
            contractchg.setChgBefInfo(degreest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getDegree().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setDegree(loanapplytbnewAF.getDegree().trim());
          }
          // ��ͥסַ
          String homeaddrst = ab.getHomeAddr();
          if (homeaddrst == null) {
            homeaddrst = "";
          }
          if (!homeaddrst.equals(loanapplytbnewAF.getHomeAddr().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("��ͥסַ");
            contractchg.setChgBefInfo(homeaddrst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHomeAddr().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
          }
          // ��������
          String homemailst = ab.getHomeMail();
          if (homemailst == null) {
            homemailst = "";
          }
          if (!homemailst.equals(loanapplytbnewAF.getHomeMail().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("��������");
            contractchg.setChgBefInfo(homemailst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHomeMail().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
          }
          // �ƶ��绰
          String homemobilest = ab.getHomeMobile();
          if (homemobilest == null) {
            homemobilest = "";
          }
          if (!homemobilest.equals(loanapplytbnewAF.getHomeMobile().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("�ƶ��绰");
            contractchg.setChgBefInfo(homemobilest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHomeMobile().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
          }
          // סլ�绰
          String housetel = ab.getHouseTel();
          if (housetel == null) {
            housetel = "";
          }
          if (!housetel.equals(loanapplytbnewAF.getHouseTel().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("סլ�绰");
            contractchg.setChgBefInfo(housetel);
            contractchg.setChgEndInfo(loanapplytbnewAF.getHouseTel().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
          }
          // ��λ����
          String orgnamest = ab.getOrgName();
          if (orgnamest == null) {
            orgnamest = "";
          }
          if (!orgnamest.equals(loanapplytbnewAF.getOrgName().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("��λ����");
            contractchg.setChgBefInfo(orgnamest);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgName().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
          }
          // ��λ��ַ
          String orgaddrst = ab.getOrgAddr();
          if (orgaddrst == null) {
            orgaddrst = "";
          }
          if (!orgaddrst.equals(loanapplytbnewAF.getOrgAddr().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("��λ��ַ");
            contractchg.setChgBefInfo(orgaddrst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgAddr().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
          }
          // ��λ�绰
          String orgtelst = ab.getOrgTel();
          if (orgtelst == null) {
            orgtelst = "";
          }
          if (!orgtelst.equals(loanapplytbnewAF.getOrgTel().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("��λ�绰");
            contractchg.setChgBefInfo(orgtelst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgTel().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
          }
          // ��λ�ʱ�
          String orgmailst = ab.getOrgMail();
          if (orgmailst == null) {
            orgmailst = "";
          }
          if (!orgmailst.equals(loanapplytbnewAF.getOrgMail().trim())) {

            ContractChg contractchg = new ContractChg();
            contractchg.setContractId(contractid);
            contractchg.setChgColumn("��λ�ʱ�");
            contractchg.setChgBefInfo(orgmailst);
            contractchg.setChgEndInfo(loanapplytbnewAF.getOrgMail().trim());
            contractchg.setOperator(securityInfo.getUserName());
            contractchg.setOpTime(new Date());
            contractchg.setContractType(String
                .valueOf(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO));
            contractChgDAO.insert(contractchg);
            temp_i = "1";
            ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
          }

          if (temp_i.equals("1")) {
            PlOperateLog plOperateLog = new PlOperateLog();// ������־����
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// ������Ϣ���_�����������Ϣ
            plOperateLog.setOpButton(String
                .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
            plOperateLog.setOpBizId(new BigDecimal(auxiliaryid));
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpTime(new Date());
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setContractId(contractid);
            plOperateLogDAO.insert(plOperateLog);
          }
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // ���ϸ��������
  public void canceledAssistanBorrower(String id, SecurityInfo securityInfo)
      throws BusinessException {

    try {
      AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(id));
      ab.setOpTime(new Date());
      if (ab.getStatus().equals("0")) {
        ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_CANCELED));// ���¸�������˱�����
        String contactid = ab.getContractId();
        String status = String
            .valueOf(BusiConst.PLPREPAYMENTFEES_CONGEALINFOTHAW);
        congealInfoDAO.updateCongealInfo(status, id, contactid);
        // �޸�pl400���ְ��״̬Ϊ���� ���ƽ
        BigDecimal empId = ab.getEmpId();
        if (empId != null && !empId.equals("")) {
          assistantBorrowerDAO.updateEmpStatus(empId, contactid);
        }
        if (ab.getName() != null && !ab.getName().equals("")) {
          assistantBorrowerDAO.updateEmpStatus_yg(ab.getName(),
              ab.getCardNum(), contactid);
        }
        PlOperateLog plOperateLog = new PlOperateLog();// ������־����
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
        plOperateLog
            .setOpModel(String
                .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO));// ������Ϣ���_�����������Ϣ
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
        plOperateLog.setOpBizId(new BigDecimal(id));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contactid);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        throw new BusinessException("������¼������");
      }
    } catch (BusinessException e) {
      throw e;
    }

  }

  // ��ʾ������Ϣ
  public LoanapplyTcNewAF showHouseInfo(String contranctid) throws Exception {
    LoanapplyTcNewAF lptcAF = new LoanapplyTcNewAF();
    try {
      String borrowerName = borrowerDAO
          .findBorrowerNameInfoByContractid(contranctid);
      lptcAF.setContractId(contranctid);
      lptcAF.setBorrowerName(borrowerName);

      Houses houses = housesDAO.queryById(contranctid);
      if (houses.getHouseType() != null) {
        lptcAF.setPhotoUrl(houses.getPhotoUrl());
        String headid = houses.getHeadId();
        if (headid != null) {// ��Ʒ��
          BuyHouserDTO buyDTO = new BuyHouserDTO();
          buyDTO = developerDAO.findHosesSomeInfo(headid);
          lptcAF.setOrgName(buyDTO.getDeveloperName());
          lptcAF.setDeveloperTel(houses.getDeveloperTel());
          lptcAF.setDeveloperPaybank(houses.getDeveloperPaybank());
          lptcAF.setDeveloperPaybankAAcc(houses.getDeveloperPaybankAAcc());
          String floorid = "";
          String floorname = "";
          String flooraddr = "";
          if (houses.getFloorId() != null) {
            floorid = houses.getFloorId().toString();// ������ID��Ҫ�õ�������Ҫͨ���������
            floorname = developProjectDAO.findFloorByPkid(floorid);
            flooraddr = developProjectDAO.findFloorByPkid_yg(floorid);
          }
          lptcAF.setFloorId(floorname);// ����Ҫ�����¥������
          if(houses.getFloorNum()!=null){
            if(houses.getFloorNum().indexOf("-")!=0){
              lptcAF.setFloorNum(houses.getFloorNum());
            }else{
              lptcAF.setFloorNum(flooraddr+houses.getFloorNum());
            }
          }
          lptcAF.setRoomNum(houses.getRoomNum());
          // lptcAF.setFirstPay(houses.getFirstPay().toString());
          lptcAF.setHouseTotlePrice(houses.getHouseTotlePrice().toString());
          lptcAF.setHouseArea(houses.getHouseArea().toString());
          lptcAF.setHousePrice(houses.getHouseTotlePrice().divide(
              houses.getHouseArea(), 0, BigDecimal.ROUND_HALF_UP)
              + "");
          lptcAF.setBuyHouseContractId(houses.getBuyHouseContractId());
//          lptcAF.setFirstTol(houses.getFirstPay().divide(
//              houses.getHouseTotlePrice(), 2, BigDecimal.ROUND_HALF_UP)
//              .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1), 0,
//                  BigDecimal.ROUND_HALF_UP)
//              + "" + "%");
          if (houses.getIsNowhouse() != null
              && !"".equals(houses.getIsNowhouse())) {

            lptcAF.setIsNowhouse(BusiTools.getBusiValue(Integer.parseInt(houses
                .getIsNowhouse()), BusiConst.YesNo));

          }
          lptcAF.setOverDate(houses.getOverDate());
          lptcAF.setBuildRightNum(houses.getBuildRightNum());
          lptcAF.setAgreementDate(houses.getAgreementDate());
          lptcAF.setHouseAddr(houses.getHouseAddr());
          lptcAF.setRemark1(houses.getRemark());
        } else {// ���ַ�
          lptcAF.setBargainorName(houses.getBargainorName());
          if (houses.getBargainorCardKind() != null
              && !"".equals(houses.getBargainorCardKind())) {
            lptcAF.setBargainorCardKind(BusiTools.getBusiValue(Integer
                .parseInt(houses.getBargainorCardKind()),
                BusiConst.DOCUMENTSSTATE));
          }

          lptcAF.setBargainorCardNum(houses.getBargainorCardNum());
          lptcAF.setBargainorTel(houses.getBargainorTel());
          lptcAF.setBargainorHousepropertyCode(houses
              .getBargainorHousepropertyCode());
          lptcAF.setBargainorMobile(houses.getBargainorMobile());
          lptcAF.setBargainorHouseAddr(houses.getBargainorHouseAddr());
          lptcAF.setBargainorPaybank(houses.getBargainorPaybank());
          lptcAF.setBargainorPaybankAcc(houses.getBargainorPaybankAcc());
          lptcAF.setBargainorHouseArea(houses.getBargainorHouseArea());
          lptcAF.setBargainorTotlePrice(houses.getBargainorTotlePrice() + "");
          lptcAF.setBargainorHouseAge(houses.getBargainorHouseAge() + "");
          lptcAF.setBargainorAgreementDate(houses.getBargainorAgreementDate());
          lptcAF.setRemark2(houses.getRemark());
        }
        if (houses.getHouseType().equals("01")) {// ��Ʒ��
          lptcAF.setHouseType("01");
          lptcAF.setHouseTypehidden("01");
        } else {// ���ַ�
          lptcAF.setHouseType("02");
          lptcAF.setHouseTypehidden("02");
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return lptcAF;
  }

  // �޸ĸ��������
  public void updateHousesInfo(LoanapplyTcNewAF loanapplytcnewAF,
      SecurityInfo securityInfo) throws Exception {
    try {
      String contractid = loanapplytcnewAF.getContractId();
      Houses houses = housesDAO.queryById(contractid);
      String housetype = houses.getHouseType();
      String temp_i = "0";
      if (housetype.equals("01")) {// ��Ʒ��
        // �޸Ĳ�Ȩ���
        String rightnum = houses.getBuildRightNum();
        if (rightnum == null) {
          rightnum = "";
        }
        if (!rightnum.equals(loanapplytcnewAF.getBuildRightNum().trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("��Ȩ֤����");
          contractchg.setChgBefInfo(rightnum);
          contractchg.setChgEndInfo(loanapplytcnewAF.getBuildRightNum().trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBuildRightNum(loanapplytcnewAF.getBuildRightNum().trim());
        }
      } else {// ���ַ�
        // �޸Ĺ̶��绰
        String bargainorTel = houses.getBargainorTel();
        if (bargainorTel == null) {
          bargainorTel = "";
        }
        if (!bargainorTel.equals(loanapplytcnewAF.getBargainorTel().trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("�̶��绰");
          contractchg.setChgBefInfo(bargainorTel);
          contractchg.setChgEndInfo(loanapplytcnewAF.getBargainorTel().trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBargainorTel(loanapplytcnewAF.getBargainorTel().trim());
        }
        // �޸�ԭ��Ȩ֤���
        String bargainorHousepropertyCode = houses
            .getBargainorHousepropertyCode();
        if (bargainorHousepropertyCode == null) {
          bargainorHousepropertyCode = "";
        }
        if (!bargainorHousepropertyCode.equals(loanapplytcnewAF
            .getBargainorHousepropertyCode().trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("ԭ��Ȩ֤���");
          contractchg.setChgBefInfo(bargainorHousepropertyCode);
          contractchg.setChgEndInfo(loanapplytcnewAF
              .getBargainorHousepropertyCode().trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBargainorHousepropertyCode(loanapplytcnewAF
              .getBargainorHousepropertyCode().trim());
        }
        // �޸��ƶ��绰
        String bargainorMobile = houses.getBargainorMobile();
        if (bargainorMobile == null) {
          bargainorMobile = "";
        }
        if (!bargainorMobile.equals(loanapplytcnewAF.getBargainorMobile()
            .trim())) {
          ContractChg contractchg = new ContractChg();
          contractchg.setContractId(contractid);
          contractchg.setChgColumn("�ƶ��绰");
          contractchg.setChgBefInfo(bargainorMobile);
          contractchg.setChgEndInfo(loanapplytcnewAF.getBargainorMobile()
              .trim());
          contractchg.setOperator(securityInfo.getUserName());
          contractchg.setOpTime(new Date());
          contractchg.setContractType(String
              .valueOf(BusiConst.PLPREPAYMENTFEES_FLOORINFO));
          contractChgDAO.insert(contractchg);
          temp_i = "1";
          houses.setBargainorMobile(loanapplytcnewAF.getBargainorMobile()
              .trim());
        }
      }
      if (temp_i.equals("1")) {
        PlOperateLog plOperateLog = new PlOperateLog();// ������־����
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO));// ������Ϣ���_�����������Ϣ
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(contractid));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
}
