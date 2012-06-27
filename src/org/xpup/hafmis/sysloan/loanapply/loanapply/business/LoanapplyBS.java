package org.xpup.hafmis.sysloan.loanapply.loanapply.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.ContractNumMngDAO;
import org.xpup.hafmis.sysloan.common.dao.DevelopProjectDAO;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanConditionsSetDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.SpecialBorrowerDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.ContractNumCancel;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.common.loanconditionsset.LoanConditionsParamSetDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.AssistantBorrowerDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BankListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BuyHouserDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyNewDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyTeListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.SpecialBankListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.DeveleperNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTeNewAF;

public class LoanapplyBS implements ILoanapplyBS {

  private BorrowerDAO borrowerDAO = null;

  private DeveloperDAO developerDAO = null;

  private LoanRateDAO loanRateDAO = null;

  private AssistantBorrowerDAO assistantBorrowerDAO = null;

  private ContractNumMngDAO contractNumMngDAO = null;

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private SpecialBorrowerDAO specialBorrowerDAO = null;

  private EmpDAO empDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private DevelopProjectDAO developProjectDAO = null;

  private HousesDAO housesDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private ContractNumCancelDAO contractNumCancelDAO = null;

  private OrgDAO orgDAO = null;

  private SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setContractNumCancelDAO(ContractNumCancelDAO contractNumCancelDAO) {
    this.contractNumCancelDAO = contractNumCancelDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setDevelopProjectDAO(DevelopProjectDAO developProjectDAO) {
    this.developProjectDAO = developProjectDAO;
  }

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
    this.assistantBorrowerDAO = assistantBorrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setContractNumMngDAO(ContractNumMngDAO contractNumMngDAO) {
    this.contractNumMngDAO = contractNumMngDAO;
  }

  public void setSpecialBorrowerDAO(SpecialBorrowerDAO specialBorrowerDAO) {
    this.specialBorrowerDAO = specialBorrowerDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  /**
   * hanl ����ְ����ź͵�λ������ְ������Ϣ
   */
  public LoanapplyNewAF findEmpInfo(String empid, String orgid)
      throws BusinessException {
    LoanapplyNewAF loanaf = new LoanapplyNewAF();
    try {
      EmpInfo empInfo = empDAO.findEmpInfoByEmpid(empid, orgid);// ��ѯְ�����������֤
      String empname = empInfo.getName();
      String cardnum = empInfo.getCardNum();
      List lls = borrowerDAO.findEmpinborrowByEmpid(empname, cardnum);// ���ݽ��������֤���Ų�ѯ��ͬ��
      // �ж��Ƿ����������Ҳ�������˻�
      SpecialBorrower s = specialBorrowerDAO.querySpeBorrowerByNameAndNum(empname, cardnum);
      if(s != null && s.getReserveaB().equals("1"))
        loanaf.setIsPsnalAcc("0");
      if (lls.isEmpty()) {
        LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empid, orgid);// ��ѯ������������Ա�֤�����ͣ����룬���������գ�סլ�绰���ƶ��绰,��λ��ţ���λ���Ƶ�
        loanaf.setEmpid(empid);
        loanaf.getBorrower().setBorrowerName(inf.getEmpname());
        loanaf.getBorrower().setSex(inf.getSex().toString());
        loanaf.getBorrower().setCardKind(inf.getCardking());
        loanaf.getBorrower().setCardNum(inf.getCardnum());
        loanaf.getBorrower().setBirthday(inf.getBirthday());
        loanaf.getBorrower().setHouseTel(inf.getTel());
        loanaf.getBorrower().setHomeMobile(inf.getMobiletle());
        loanaf.getBorrower().setOrgId(new BigDecimal(inf.getOrgid()));
        loanaf.getBorrower().setOrgName(inf.getOrgname());
        loanaf.getBorrower().setOrgTel(inf.getOrgtel());
        loanaf.getBorrower().setOrgMail(inf.getPostalcode());
        loanaf.getBorrower().setAccBlnce(new BigDecimal(inf.getCardmoney()));
        loanaf.getBorrower().setOrgAddr(inf.getAddress());
        loanaf.getBorrower().setMonthSalary(
            new BigDecimal(inf.getMonth_income()));
        loanaf.getBorrower().setPay_oldyear(inf.getPay_oldyear());
        loanaf.getBorrower().setMonthPay(new BigDecimal(inf.getMonthpay()));
        try {
          inf.setPay_status(BusiTools.getBusiValue(Integer.parseInt(inf
              .getPay_status()), BusiConst.OLDPAYMENTSTATE));
        } catch (NumberFormatException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }
        loanaf.getBorrower().setEmpSt_(inf.getPay_status());
        loanaf.getBorrower().setBgnDate(inf.getFirst_pay_month());
        loanaf.getBorrower().setEndDate(inf.getOrg_pay_month());
        loanaf.getBorrower().setOffice(inf.getOffices());

      } else {
        boolean b = false;
        for (int i = 0; i < lls.size(); i++) {

          String contrid = (String) lls.get(i);

          b = borrowerAccDAO.isCheckBorrowByContractid(contrid);// �жϸú�ͬ�Ƿ���12.13(״̬Ϊ�����еĺ�ͬ��ǰ���������õ��ж�)

          if (b) {
            LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empid, orgid);// ��ѯ������������Ա�֤�����ͣ����룬���������գ�סլ�绰���ƶ��绰,��λ��ţ���λ���Ƶ�
            loanaf.setEmpid(empid);
            loanaf.getBorrower().setBorrowerName(inf.getEmpname());
            loanaf.getBorrower().setSex(inf.getSex().toString());
            loanaf.getBorrower().setCardKind(inf.getCardking());
            loanaf.getBorrower().setCardNum(inf.getCardnum());
            loanaf.getBorrower().setBirthday(inf.getBirthday());
            loanaf.getBorrower().setHouseTel(inf.getTel());
            loanaf.getBorrower().setHomeMobile(inf.getMobiletle());
            loanaf.getBorrower().setOrgId(new BigDecimal(inf.getOrgid()));
            loanaf.getBorrower().setOrgName(inf.getOrgname());
            loanaf.getBorrower().setOrgTel(inf.getOrgtel());
            loanaf.getBorrower().setOrgMail(inf.getPostalcode());
            loanaf.getBorrower()
                .setAccBlnce(new BigDecimal(inf.getCardmoney()));
            loanaf.getBorrower().setOrgAddr(inf.getAddress());
            loanaf.getBorrower().setMonthSalary(
                new BigDecimal(inf.getMonth_income()));
            loanaf.getBorrower().setMonthPay(new BigDecimal(inf.getMonthpay()));
            loanaf.getBorrower().setPay_oldyear(inf.getPay_oldyear());
            try {
              inf.setPay_status(BusiTools.getBusiValue(Integer.parseInt(inf
                  .getPay_status()), BusiConst.OLDPAYMENTSTATE));
            } catch (NumberFormatException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            loanaf.getBorrower().setEmpSt_(inf.getPay_status());
            loanaf.getBorrower().setBgnDate(inf.getFirst_pay_month());
            loanaf.getBorrower().setEndDate(inf.getOrg_pay_month());
            loanaf.getBorrower().setOffice(inf.getOffices());
          } else {
            LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empid, orgid);
            String empName = inf.getEmpname();
            String cardNum = inf.getCardnum();
            String empEleven = empDAO.queryempLoanIsEleven(empName, cardNum);
            if (Integer.parseInt(empEleven) > 0) {
              throw new BusinessException("��ְ�����ں�ͬ״̬Ϊ�����еĴ���");
            }
            throw new BusinessException("�ú�ͬ���ڰ�����");
          }
        }
      }
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return loanaf;
  }

  /**
   * hanl ��ʾ�������Ϣ
   */
  public LoanapplyNewAF showLoanapplyInfoBycontractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException {
    LoanapplyNewAF loanaf = new LoanapplyNewAF();
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contractid);// ��ѯpl110
    try {
      if (borrower == null) {
        throw new BusinessException("�ú�ͬ������");
      }
      if (borrower.getEmpSt() == null) {
        borrower.setEmpSt_("");
      } else {
        borrower.setEmpSt_(BusiTools.getBusiValue(Integer.parseInt(borrower
            .getEmpSt()), BusiConst.OLDPAYMENTSTATE));
      }
    } catch (BusinessException be) {
      throw be;
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (borrower.getPrivilegeBorrowerId() != null
        && borrower.getPrivilegeBorrowerId().intValue() != 0) {
      loanaf.setPrivilege_borrower_id(borrower.getPrivilegeBorrowerId()
          .toString());
      SpecialBorrower s = specialBorrowerDAO.queryByHeadID(Integer.valueOf(borrower.getPrivilegeBorrowerId().toString()));
      if("1".equals(s.getReserveaB())) {
        loanaf.setIsPsnalAcc("0");
        loanaf.setPayBank(s.getPerBank());
        loanaf.setPayBankAcc(s.getPerBankAcc());
      }
    } else {
      loanaf.setPrivilege_borrower_id("0");
    }
    BigDecimal empid = borrower.getEmpId();
    if (empid == null) {
      loanaf.setType1("2");
      boolean flag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// �ж��Ƿ�¼�������Ϣ
      if (flag) {
        loanaf.setType1("3");
      } else {
        loanaf.setType1("4");
      }
    } else {
      loanaf.setType1("1");
    }
    // ��������֧ȡ���
    // �ʻ����-�½ɴ��*12
    BigDecimal zdjzqje = new BigDecimal(0.00);
    if (borrower.getPay_oldyear() != null
        && !"".equals(borrower.getPay_oldyear())) {
      zdjzqje = borrower.getAccBlnce().subtract(
          new BigDecimal(borrower.getPay_oldyear())
              .multiply(new BigDecimal(12)));
    } else if (borrower.getAccBlnce() != null && borrower.getMonthPay() != null) {
      zdjzqje = borrower.getAccBlnce().subtract(
          borrower.getMonthPay().multiply(new BigDecimal(12)));
    }
    if (zdjzqje.doubleValue() < 0) {
      zdjzqje = new BigDecimal(0.00);
    }
    String orgRate = "";
    String empRate = "";
    // �ɴ����
    if (borrower.getOrgId() != null
        && !"".equals(borrower.getOrgId().toString())) {
      Org org = orgDAO.queryById(new Integer(borrower.getOrgId().toString()));
      if (org != null) {
        orgRate = org.getOrgRate().toString();
        empRate = org.getEmpRate().toString();
        loanaf.setOrgRate(orgRate);
        loanaf.setEmpRate(empRate);
      }
    }
    loanaf.setZdjzqje(zdjzqje);
    borrower.setOtherArrearage_(borrower.getOtherArrearage().equals("0") ? "��"
        : "��");
    loanaf.setSexc(borrower.getSex());
    loanaf.setCardkingc(borrower.getCardKind());
    loanaf.setBorrower(borrower);
    // ����Ա,�����,������
    if (borrower.getOperator() != null && !"".equals(borrower.getOperator())) {
      loanaf.setOperator(securityDAO.queryByUserid(borrower.getOperator()));
    }
    if (borrower.getChkPerson() != null && !"".equals(borrower.getChkPerson())) {
      loanaf.setChkPerson(securityDAO.queryByUserid(borrower.getChkPerson()));
    }
    if (borrower.getVipChkPerson() != null
        && !"".equals(borrower.getVipChkPerson())) {
      loanaf.setVipChkPerson(securityDAO.queryByUserid(borrower
          .getVipChkPerson()));
    }
    Houses houses = housesDAO.queryById(contractid);// ���ݺ�ͬ��Ų�ѯ������Ϣ
    if (houses != null && houses.getHouseType() != null
        && !"".equals(houses.getHouseType())) {
      loanaf.setHouseType(houses.getHouseType());
    } else {
      loanaf.setHouseType(contractid.substring(6, 8));
    }

    return loanaf;
  }

  /**
   * hanl ��ӽ������Ϣ
   */
  public String saveBorrowerInfo(LoanapplyNewAF loanapplyaf, String contractid,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    String contractId = contractid;
    try {
      Borrower borrower = loanapplyaf.getBorrower();
      Borrower borrowernew = new Borrower();
      borrowernew.setSex(loanapplyaf.getSexc().trim());
      borrowernew.setCardKind(loanapplyaf.getCardkingc().trim());
      if (contractId == null) {
        List lls = borrowerDAO.findEmpinborrowByEmpid(borrower
            .getBorrowerName().trim(), borrower.getCardNum().trim());// ���ݽ��������֤���Ų�ѯ��ͬ��
        boolean flag;
        String temp_id = "";
        if (!lls.isEmpty()) {
          for (int i = 0; i < lls.size(); i++) {
            String contrid = (String) lls.get(i);
            // ���ݽ���˺�ͬ��Ų�ѯ��ͬ״̬����12.13�ĺ�ͬ��
            flag = borrowerAccDAO.isCheckBorrowByContractid(contrid);
            if (!flag) {
              temp_id = "1";
              String empEleven = empDAO.queryempLoanIsEleven(borrower
                  .getBorrowerName().trim(), borrower.getCardNum().trim());
              if (Integer.parseInt(empEleven) > 0) {
                throw new BusinessException("��ְ�����ں�ͬ״̬Ϊ�����еĴ���");
              }
              throw new BusinessException("�ú�ͬ���ڰ�����");
            }
          }
        }
        if ("0".equals(borrower.getEmpId().toString())) {
          int count = empDAO.queryEmpCount_yg(borrower.getBorrowerName().trim(),
              borrower.getCardNum().trim());
          if (count != 0)
            throw new BusinessException("ϵͳ�д��ڸ�ְ��,��ѡ��ְ���˺�!");
        }
        if (lls.isEmpty() || temp_id.equals("")) {// ������������
          // ���ݽ��������֤���Ų�ѯ�Ƿ����������˲�����δ����״̬
          String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(

          borrower.getBorrowerName().trim(), borrower.getCardNum().trim());

          String bizYearall = securityInfo.getUserInfo().getPlbizDate();
          String bizYear = bizYearall.substring(0, 4);
          contractId = contractNumMngDAO.getContractId(borrower.getOffice()
              .trim(), bizYear, loanapplyaf.getHouseType(), securityInfo
              .getOfficeInnerCodeMap());// ���ɺ�ͬ���
          if (specialid != null) {
            SpecialBorrower sp = specialBorrowerDAO
                .queryByHeadID(new Integer(specialid));
            sp.setStatus(String.valueOf(BusiConst.APPSTATUS_2));
            sp.setPerBank(loanapplyaf.getPayBank().trim());
            sp.setPerBankAcc(loanapplyaf.getPayBankAcc().trim());
            borrowernew.setPrivilegeBorrowerId(new BigDecimal(specialid));
          }
          borrowernew.setContractId(contractId);
          borrowernew.setOperator(securityInfo.getUserName());
          borrowernew.setOpTime(new Date());
          if (borrower.getEmpId().toString().trim().equals("0")) {
            BigDecimal empid = null;
            borrowernew.setEmpId(empid);
          } else {
            borrowernew.setEmpId(new BigDecimal(borrower.getEmpId().toString()
                .trim()));
          }
          borrowernew.setOffice(borrower.getOffice().trim());
          borrowernew.setBorrowerName(borrower.getBorrowerName().trim());
          borrowernew.setCardNum(borrower.getCardNum().trim());
          borrowernew.setBirthday(borrower.getBirthday().trim());
          borrowernew
              .setAge(new BigDecimal(borrower.getAge().toString().trim()));
          borrowernew.setBusiness(borrower.getBusiness().trim());
          borrowernew.setTitle(borrower.getTitle().trim());
          borrowernew.setNation(borrower.getNation().trim());
          borrowernew.setNativePlace(borrower.getNativePlace().trim());
          borrowernew.setMarriageSt(borrower.getMarriageSt());
          borrowernew.setDegree(borrower.getDegree());
          borrowernew.setHomeAddr(borrower.getHomeAddr().trim());
          borrowernew.setHomeMail(borrower.getHomeMail().trim());
          borrowernew.setHouseTel(borrower.getHouseTel().trim());
          borrowernew.setHomeMobile(borrower.getHomeMobile().trim());
          borrowernew.setRemark(borrower.getRemark().trim());
          if (!borrower.getOrgId().toString().trim().equals("0")) {
            borrowernew.setOrgId(new BigDecimal(borrower.getOrgId().toString()
                .trim()));
          }
          borrowernew.setOrgName(borrower.getOrgName().trim());
          borrowernew.setOrgTel(borrower.getOrgTel().trim());
          borrowernew.setOrgMail(borrower.getOrgMail().trim());
          borrowernew.setOrgAddr(borrower.getOrgAddr().trim());
          borrowernew.setAccBlnce(new BigDecimal(borrower.getAccBlnce()
              .toString().trim()));
          borrowernew.setMonthSalary(new BigDecimal(borrower.getMonthSalary()
              .toString().trim()));
          borrowernew.setMonthPay(new BigDecimal(borrower.getMonthPay()
              .toString().trim()));
          if (!borrower.getEmpSt_().trim().equals("")) {

            borrowernew.setEmpSt(BusiTools.getBusiKey(borrower.getEmpSt_()
                .trim(), BusiConst.OLDPAYMENTSTATE)
                + "");
          } else {
            borrowernew.setEmpSt("");
          }
          borrowernew.setBgnDate(borrower.getBgnDate().trim());
          borrowernew.setEndDate(borrower.getEndDate().trim());
          borrowernew.setContactAName(borrower.getContactAName().trim());
          borrowernew.setRelationA(borrower.getRelationA().trim());
          borrowernew.setContactAOrgName(borrower.getContactAOrgName().trim());
          borrowernew.setContactAOrgTel(borrower.getContactAOrgTel().trim());
          borrowernew
              .setContactAHouseTel(borrower.getContactAHouseTel().trim());
          borrowernew.setContactAMobile(borrower.getContactAMobile().trim());
          borrowernew.setContactBName(borrower.getContactBName().trim());
          borrowernew.setRelationB(borrower.getRelationB().trim());
          borrowernew.setContactBOrgName(borrower.getContactBOrgName().trim());
          borrowernew.setContactBOrgTel(borrower.getContactBOrgTel().trim());
          borrowernew
              .setContactBHouseTel(borrower.getContactBHouseTel().trim());
          borrowernew.setContactBMobile(borrower.getContactBMobile().trim());
          // ������ϵ��C
          borrowernew.setContactCName(borrower.getContactCName().trim());
          borrowernew.setRelationC(borrower.getRelationC().trim());
          borrowernew.setContactCOrgName(borrower.getContactCOrgName().trim());
          borrowernew.setContactCOrgTel(borrower.getContactCOrgTel().trim());
          borrowernew
              .setContactCHouseTel(borrower.getContactCHouseTel().trim());
          borrowernew.setContactCMobile(borrower.getContactCMobile().trim());
          borrowernew.setLoanType(borrower.getLoanType());
          if (borrower.getFundCity() != null
              && !"".equals(borrower.getFundCity())) {
            borrowernew.setFundCity(borrower.getFundCity());
          }
          // ����pl110
          borrowerDAO.insert(borrowernew);
          // �����ʺű�
          BorrowerAcc acc = new BorrowerAcc();
          acc.setContractId(contractId);
          acc.setContractSt(String.valueOf(BusiConst.PLCONTRACTSTATUS_APPL));
          BigDecimal curIntegral = null;
          BigDecimal preIntegral = null;
          acc.setCurIntegral(curIntegral);
          acc.setPreIntegral(preIntegral);
          acc.setIsContractWrite("0");
          acc.setManualAuto("0");
          acc.setMarkA("0");
          acc.setMarkB("0");
          borrowerAccDAO.insert(acc);
          // ���빺����Ϣ��
          Houses houses = new Houses();
          houses.setContractId(contractId);
          housesDAO.insert(houses);

          PlOperateLog plOperateLog = new PlOperateLog();// ������־����
          plOperateLog
              .setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
          plOperateLog.setOpModel(String
              .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO));// ��������_�������Ϣ
          plOperateLog.setOpButton(String
              .valueOf(BusiLogConst.BIZLOG_ACTION_ADD));

          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLog.setContractId(contractId);
          plOperateLogDAO.insert(plOperateLog);
        } else {
          throw new BusinessException("�ú�ͬ���ڰ�����");
        }

      } else {
        // ���ݽ��������֤���Ų�ѯ�Ƿ����������˲�����δ����״̬
        String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(

        borrower.getBorrowerName().trim(), borrower.getCardNum().trim());

        if (specialid != null) {
          SpecialBorrower sp = specialBorrowerDAO
              .queryByHeadID(new Integer(specialid));
          System.out.println(sp);
          System.out.println(loanapplyaf.getPayBank()+"=============>");
          System.out.println(loanapplyaf.getPayBankAcc()+"=============>");
          sp.setPerBank(loanapplyaf.getPayBank().trim());
          sp.setPerBankAcc(loanapplyaf.getPayBankAcc().trim());
        }
        Borrower bor = borrowerDAO.queryById(contractid);
        bor.setSex(loanapplyaf.getSexc().trim());
        bor.setCardKind(loanapplyaf.getCardkingc().trim());
        bor.setBirthday(borrower.getBirthday().trim());
        bor.setAge(borrower.getAge());
        bor.setMonthSalary(borrower.getMonthSalary());
        bor.setMonthPay(borrower.getMonthPay());
        bor.setBusiness(borrower.getBusiness().trim());
        bor.setTitle(borrower.getTitle().trim());
        bor.setNation(borrower.getNation().trim());
        bor.setNativePlace(borrower.getNativePlace().trim());
        bor.setMarriageSt(borrower.getMarriageSt().trim());
        bor.setDegree(borrower.getDegree().trim());
        bor.setHomeAddr(borrower.getHomeAddr().trim());
        bor.setHomeMail(borrower.getHomeMail().trim());
        bor.setHomeMobile(borrower.getHomeMobile().trim());
        bor.setHouseTel(borrower.getHouseTel().trim());
        bor.setOrgName(borrower.getOrgName().trim());
        bor.setOrgTel(borrower.getOrgTel().trim());
        bor.setOrgMail(borrower.getOrgMail().trim());
        bor.setOrgAddr(borrower.getOrgAddr().trim());
        bor.setContactAName(borrower.getContactAName().trim());
        bor.setRelationA(borrower.getRelationA().trim());
        bor.setContactAOrgName(borrower.getContactAOrgName().trim());
        bor.setContactAOrgTel(borrower.getContactAOrgTel().trim());
        bor.setContactAHouseTel(borrower.getContactAHouseTel().trim());
        bor.setContactAMobile(borrower.getContactAMobile().trim());
        bor.setContactBName(borrower.getContactBName().trim());
        bor.setRelationB(borrower.getRelationB().trim());
        bor.setContactBOrgName(borrower.getContactBOrgName().trim());
        bor.setContactBOrgTel(borrower.getContactBOrgTel().trim());
        bor.setContactBHouseTel(borrower.getContactBHouseTel().trim());
        bor.setContactBMobile(borrower.getContactBMobile().trim());
        bor.setContactCName(borrower.getContactCName().trim());
        bor.setRelationC(borrower.getRelationC().trim());
        bor.setContactCOrgName(borrower.getContactCOrgName().trim());
        bor.setContactCOrgTel(borrower.getContactCOrgTel().trim());
        bor.setContactCHouseTel(borrower.getContactCHouseTel().trim());
        bor.setContactCMobile(borrower.getContactCMobile().trim());

        PlOperateLog plOperateLog = new PlOperateLog();// ������־����
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO));// ��������_�������Ϣ
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(contractid));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException e) {
      throw e;
    } catch (Exception ee) {
      ee.printStackTrace();
    }
    return contractId;
  }

  /**
   * hanl ��ʾ�����������Ϣ
   */
  public LoanapplyTbNewAF findAssistanBorrowerInfo(String contractid,
      SecurityInfo securityInfo, String auxiliaryid) throws BusinessException {
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
      Borrower borrower =borrowerDAO.queryById(contractid);
      loanapplytbnewAF.setLoanType(borrower.getLoanType());
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
        if(ab.getStatus().equals("1")){
          loanapplytbnewAF.setRelationStatus("����(���)");
        }else{
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

  /**
   * hanl ����ͬ����Ƿ�����Ҫ��
   */
  public void checkContractIdByContractId(String contractId)
      throws BusinessException {
    try {
      String contractid = borrowerAccDAO.findContractidByContractid(contractId);// ���ݺ�ͬ�ţ���ѯ�Ƿ��д���1.5.6������״̬�ļ�¼
      if (contractid == null) {
        throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬����ȷ��");
      } else {
        boolean flag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractId);// �ж��Ƿ�¼�����
        if (flag) {
          throw new BusinessException("�ý�����Ѿ�¼������Ϣ��������Ӹ����������Ϣ��");
        }
      }
    } catch (BusinessException be) {

      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * ���ݺ�ͬ��ѯ����ˣ���������ˣ���ͬ��Ϣ���ȵ�ͼƬ·��
   * 
   * @author yangg
   * @param contractid
   * @return List
   */
  public List queryPhotoURLByContractID(String contractId)
      throws BusinessException {
    List photoURLList = null;
    try {
      photoURLList = borrowerAccDAO.queryPhotoURLByContractID(contractId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return photoURLList;
  }

  /**
   * hanl ��ѯ�����������Ϣ
   */
  public LoanapplyTbNewAF findAssistantBorrowerInfoByempId(String empId,
      String orgId, String contractaId) throws BusinessException {
    LoanapplyTbNewAF loanapplytbnewAF = new LoanapplyTbNewAF();
    if (contractaId != null && !"".equals(contractaId)) {
      try {
        boolean flag = empDAO
            .getEmpidCount_AssistantBorrower(new Integer(empId));// �ж�AA002�����Ƿ�����뽫Ҫɾ����ְ��empid��ͬ�ļ�¼
        // ���������ͬ��empid����true�����򷵻�false
        if (flag) {
          EmpInfo empinfo = empDAO.findEmpInfoByEmpid(empId, orgId);// ����ְ����Ų�ѯְ�����������֤
          String empname = empinfo.getName();
          String empcardnum = empinfo.getCardNum();
          String auxiliaryid = assistantBorrowerDAO
              .findAuxiliaryidByNameCardnum(empname, empcardnum, contractaId);// ���ݽ��������֤���Ų�ѯ�����˵�ID
          if (auxiliaryid == null) {
            LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empId, orgId);// ��ѯ������������Ա�֤�����ͣ����룬���������գ�סլ�绰���ƶ��绰,��λ��ţ���λ���Ƶ�
            loanapplytbnewAF.setEmpId(empId);
            loanapplytbnewAF.setName(inf.getEmpname());
            loanapplytbnewAF.setSex(inf.getSex().toString());
            loanapplytbnewAF.setCardKind(inf.getCardking());
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
            loanapplytbnewAF.setPay_oldyear(inf.getPay_oldyear());
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
            AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(
                auxiliaryid));// ��������� ����
            String contractid = ab.getContractId();
            boolean checkcontractid = borrowerAccDAO
                .isCheckBorrowByContractid(contractid);// �жϺ�ͬ�Ƿ���12.13
            if (checkcontractid) {

              if (ab.getEmpId() == null) {
                loanapplytbnewAF.setEmpId("");
              } else {
                loanapplytbnewAF.setEmpId(ab.getEmpId().toString());
              }
              if (ab.getName() == null) {
                loanapplytbnewAF.setName("");
              } else {
                loanapplytbnewAF.setName(ab.getName());
              }
              if (ab.getRelation() == null) {
                loanapplytbnewAF.setRelation("");
              } else {
                loanapplytbnewAF.setRelation(ab.getRelation());
              }
              if (ab.getSex() == null) {
                loanapplytbnewAF.setSex("");
              } else {
                loanapplytbnewAF.setSex(ab.getSex());
              }
              if (ab.getCardKind() == null) {
                loanapplytbnewAF.setCardKind("");
              } else {
                loanapplytbnewAF.setCardKind(ab.getCardKind());
              }
              if (ab.getCardNum() == null) {
                loanapplytbnewAF.setCardNum("");
              } else {
                loanapplytbnewAF.setCardNum(ab.getCardNum());
              }
              if (ab.getBirthday() == null) {
                loanapplytbnewAF.setBirthday("");
              } else {
                loanapplytbnewAF.setBirthday(ab.getBirthday());
              }

              if (ab.getAge() == null) {
                loanapplytbnewAF.setAge("");
              } else {
                loanapplytbnewAF.setAge(ab.getAge().toString());
              }
              if (ab.getBusiness() == null) {
                loanapplytbnewAF.setBusiness("");
              } else {
                loanapplytbnewAF.setBusiness(ab.getBusiness());
              }
              if (ab.getTitle() == null) {
                loanapplytbnewAF.setTitle("");
              } else {
                loanapplytbnewAF.setTitle(ab.getTitle());
              }
              if (ab.getNation() == null) {
                loanapplytbnewAF.setNation("");
              } else {
                loanapplytbnewAF.setNation(ab.getNation());
              }
              if (ab.getNativePlace() == null) {
                loanapplytbnewAF.setNativePlace("");
              } else {
                loanapplytbnewAF.setNativePlace(ab.getNativePlace());
              }
              if (ab.getMarriageSt() == null) {
                loanapplytbnewAF.setMarriageSt("");
              } else {
                loanapplytbnewAF.setMarriageSt(ab.getMarriageSt());
              }
              if (ab.getDegree() == null) {
                loanapplytbnewAF.setDegree("");
              } else {
                loanapplytbnewAF.setDegree(ab.getDegree());
              }
              if (ab.getHomeAddr() == null) {
                loanapplytbnewAF.setHomeAddr("");
              } else {
                loanapplytbnewAF.setHomeAddr(ab.getHomeAddr());
              }
              if (ab.getHomeMail() == null) {
                loanapplytbnewAF.setHomeMail("");
              } else {
                loanapplytbnewAF.setHomeMail(ab.getHomeMail());
              }
              if (ab.getHouseTel() == null) {
                loanapplytbnewAF.setHouseTel("");
              } else {
                loanapplytbnewAF.setHouseTel(ab.getHouseTel());
              }
              if (ab.getHomeMobile() == null) {
                loanapplytbnewAF.setHomeMobile("");
              } else {
                loanapplytbnewAF.setHomeMobile(ab.getHomeMobile());
              }

              if (ab.getOrgId() == null) {
                loanapplytbnewAF.setOrgId("");
              } else {
                loanapplytbnewAF.setOrgId(ab.getOrgId().toString());
              }
              if (ab.getOrgName() == null) {
                loanapplytbnewAF.setOrgName("");
              } else {
                loanapplytbnewAF.setOrgName(ab.getOrgName());
              }
              if (ab.getOrgTel() == null) {
                loanapplytbnewAF.setOrgTel("");
              } else {
                loanapplytbnewAF.setOrgTel(ab.getOrgTel());
              }
              if (ab.getOrgMail() == null) {
                loanapplytbnewAF.setOrgMail("");
              } else {
                loanapplytbnewAF.setOrgMail(ab.getOrgMail());
              }
              if (ab.getOrgAddr() == null) {
                loanapplytbnewAF.setOrgAddr("");
              } else {
                loanapplytbnewAF.setOrgAddr(ab.getOrgAddr());
              }

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
              // ����������ȡ���
              if (ab.getMaxPickBalance() != null) {
                loanapplytbnewAF.setZdjzqje(ab.getMaxPickBalance().toString());
              } else {
                loanapplytbnewAF.setZdjzqje("0");
              }
              if (ab.getEmpSt() != null) {
                try {
                  loanapplytbnewAF.setEmpSt(BusiTools.getBusiValue(Integer
                      .parseInt(ab.getEmpSt()), BusiConst.OLDPAYMENTSTATE));
                } catch (NumberFormatException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
              } else {
                loanapplytbnewAF.setEmpSt("");
              }
              if (ab.getBgnDate() == null) {
                loanapplytbnewAF.setBgnDate("");
              } else {
                loanapplytbnewAF.setBgnDate(ab.getBgnDate());
              }
              if (ab.getEndDate() == null) {
                loanapplytbnewAF.setEndDate("");
              } else {
                loanapplytbnewAF.setEndDate(ab.getEndDate());
              }

            } else {
              String empEleven = empDAO.queryempLoanIsEleven(ab.getName(), ab
                  .getCardNum());
              if (Integer.parseInt(empEleven) > 0) {
                throw new BusinessException("��ְ�����ں�ͬ״̬Ϊ�����еĴ���");
              }
              throw new BusinessException("�ú�ͬ���ڰ�����");
            }
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
    } else {
      throw new BusinessException("�������ͬ���");
    }
    return loanapplytbnewAF;
  }

  /**
   * hanl ��Ӹ��������
   */
  public void addAssistantBorrowerInfo(LoanapplyTbNewAF loanapplytbnewAF,
      SecurityInfo securityInfo, String temp_addtype) throws BusinessException {
    try {
      String contractid = loanapplytbnewAF.getContractId().trim();
      String name = loanapplytbnewAF.getName().trim();
      String cardnum = loanapplytbnewAF.getCardNum().trim();
      String auxiliaryid = assistantBorrowerDAO
          .findAssistanBorrowerByContractidNameCardnum(contractid, name,
              cardnum);// ���ݺ�ͬ��ţ����������ţ������������˵ı��
      if (auxiliaryid == null) {
        List list = assistantBorrowerDAO.findAuxiliaryidListByNameCardnum(name,
            cardnum);// �������֣����������ͬ���
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            String oldcontracntid = (String) list.get(i);
            boolean isflag = borrowerAccDAO
                .isCheckBorrowByContractid(oldcontracntid);// �жϸú�ͬ����Ƿ���11.12.13
            if (!isflag) {
              throw new BusinessException("�ú�ͬ���ڰ�����");
            }
          }
        }
        if (loanapplytbnewAF.getEmpId() == null || loanapplytbnewAF.getEmpId().equals("")) {
          int count = empDAO.queryEmpCount_yg(name, cardnum);
          if (count != 0)
            throw new BusinessException("ϵͳ�д��ڸ�ְ��,��ѡ��ְ���˺�!");
        }
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
        // ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
        ab.setDegree(loanapplytbnewAF.getDegree().trim());
        ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
        ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
        ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
        ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
        BigDecimal temp_orgs = null;
        if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
          ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
        } else {
          ab.setOrgId(temp_orgs);
        }
        ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
        ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
        ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
        ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
        // ����֧ȡ���
        if (loanapplytbnewAF.getZdjzqje() != null
            && !"".equals(loanapplytbnewAF.getZdjzqje())) {
          ab.setMaxPickBalance(new BigDecimal(loanapplytbnewAF.getZdjzqje()));
        }
        if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
          ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce().trim()));
        } else {
          ab.setAccBlnce(temp_orgs);
        }
        if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
          ab.setMonthSalary(new BigDecimal(loanapplytbnewAF.getMonthSalary()
              .trim()));
        }
        if (!loanapplytbnewAF.getMonthPay().trim().equals("")) {
          ab.setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay().trim()));
        } else {
          ab.setMonthPay(temp_orgs);
        }
        ab.setEmpSt(loanapplytbnewAF.getEmpSt());
        ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
        ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
        ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
        ab.setOperator(securityInfo.getUserName());
        ab.setOpTime(new Date());
        ab.setReserveaC("0");//˵��ԭ��
        assistantBorrowerDAO.insert(ab);// ���븨�������

        PlOperateLog plOperateLog = new PlOperateLog();// ������־����
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// ��������_�������Ϣ
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId().toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(contractid);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        if (temp_addtype != null) {// ˵���߹���Ӱ�ť
          auxiliaryid = assistantBorrowerDAO
              .findAssistanBorrowerByContractidNameCardnum(contractid, name,
                  cardnum);
          if (auxiliaryid == null) {// �������
            List list = assistantBorrowerDAO.findAuxiliaryidListByNameCardnum(
                name, cardnum);// �������֣����������ͬ���
            if (list.size() != 0) {
              for (int i = 0; i < list.size(); i++) {
                String oldcontracntid = (String) list.get(i);
                boolean isflag = borrowerAccDAO
                    .isCheckBorrowByContractid(oldcontracntid);// �жϸú�ͬ����Ƿ���11.12.13
                if (!isflag) {
                  throw new BusinessException("�ú�ͬ���ڰ�����");
                }
              }
            }
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
            BigDecimal temp_org = null;
            if (!loanapplytbnewAF.getOrgId().trim().equals("")) {
              ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
            } else {
              ab.setOrgId(temp_org);
            }
            ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
            ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
            ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
            ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
            if (!loanapplytbnewAF.getAccBlnce().trim().equals("")) {
              ab.setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce()
                  .trim()));
            } else {
              ab.setAccBlnce(temp_org);
            }
            if (!loanapplytbnewAF.getMonthSalary().trim().equals("")) {
              ab.setMonthSalary(new BigDecimal(loanapplytbnewAF
                  .getMonthSalary().trim()));
            }
            if (!loanapplytbnewAF.getMonthPay().trim().equals("")) {
              ab.setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay()
                  .trim()));
            } else {
              ab.setMonthPay(temp_org);
            }
            if (!loanapplytbnewAF.getEmpSt().trim().equals("")) {
              int empSt1 = 0;
              try {
                empSt1 = BusiTools.getBusiKey(loanapplytbnewAF.getEmpSt()
                    .trim(), BusiConst.OLDPAYMENTSTATE);
              } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
              ab.setEmpSt(new Integer(empSt1).toString());
            }

            ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
            ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
            ab.setStatus(String.valueOf(BusiConst.PLCOMMONSTATUS_1_NORMAL));
            ab.setOperator(securityInfo.getUserName());
            ab.setOpTime(new Date());
            ab.setReserveaC("0");//˵��ԭ��
            assistantBorrowerDAO.insert(ab);// ���븨�������

            PlOperateLog plOperateLog = new PlOperateLog();// ������־����
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// ��������_�������Ϣ
            plOperateLog.setOpButton(String
                .valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
            plOperateLog.setOpBizId(new BigDecimal(ab.getAuxiliaryId()
                .toString()));
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpTime(new Date());
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setContractId(contractid);
            plOperateLogDAO.insert(plOperateLog);
          } else {
            throw new BusinessException("��ְ���Ѿ�Ϊ���������");
          }
        } else {// ���¸����������Ϣ
          AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(
              auxiliaryid));
          ab.setReserveaC("0");//˵��ԭ��
          ab.setRelation(loanapplytbnewAF.getRelation().trim());
          ab.setSex(loanapplytbnewAF.getSexhidden().trim());
          ab.setCardKind(loanapplytbnewAF.getCardKindhidden().trim());
          ab.setBirthday(loanapplytbnewAF.getBirthday().trim());
          if (loanapplytbnewAF.getAge() != null
              && !"".equals(loanapplytbnewAF.getAge())) {
            ab.setAge(new BigDecimal(loanapplytbnewAF.getAge().trim()));
          }
          ab.setNation(loanapplytbnewAF.getNation().trim());
          ab.setNativePlace(loanapplytbnewAF.getNativePlace().trim());
          ab.setBusiness(loanapplytbnewAF.getBusiness().trim());
          ab.setTitle(loanapplytbnewAF.getTitle().trim());
          // ab.setMarriageSt(loanapplytbnewAF.getMarriageSt().trim());
          ab.setDegree(loanapplytbnewAF.getDegree().trim());
          ab.setHomeAddr(loanapplytbnewAF.getHomeAddr().trim());
          ab.setHomeMail(loanapplytbnewAF.getHomeMail().trim());
          ab.setHomeMobile(loanapplytbnewAF.getHomeMobile().trim());
          ab.setHouseTel(loanapplytbnewAF.getHouseTel().trim());
          ab.setMonthSalary(new BigDecimal(loanapplytbnewAF.getMonthSalary()
              .trim()));
          ab.setOrgName(loanapplytbnewAF.getOrgName().trim());
          ab.setOrgAddr(loanapplytbnewAF.getOrgAddr().trim());
          ab.setOrgTel(loanapplytbnewAF.getOrgTel().trim());
          ab.setOrgMail(loanapplytbnewAF.getOrgMail().trim());
          if (loanapplytbnewAF.getOrgId().trim().equals("")) {
            BigDecimal orgidn = null;
            ab.setOrgId(orgidn);
          } else {
            ab.setOrgId(new BigDecimal(loanapplytbnewAF.getOrgId().trim()));
          }
          if (loanapplytbnewAF.getAccBlnce().trim().equals("")) {
            BigDecimal orgidn = null;
            ab.setAccBlnce(orgidn);
          } else {
            ab
                .setAccBlnce(new BigDecimal(loanapplytbnewAF.getAccBlnce()
                    .trim()));
          }
          if (loanapplytbnewAF.getMonthPay().trim().equals("")) {
            BigDecimal orgidn = null;
            ab.setMonthPay(orgidn);
          } else {
            ab
                .setMonthPay(new BigDecimal(loanapplytbnewAF.getMonthPay()
                    .trim()));
          }
          if (loanapplytbnewAF.getEmpSt().trim().equals("")) {

            ab.setEmpSt("");
          } else {
            ab.setEmpSt(loanapplytbnewAF.getEmpSt().trim());
          }
          if (loanapplytbnewAF.getBgnDate().trim().equals("")) {

            ab.setBgnDate("");
          } else {
            ab.setBgnDate(loanapplytbnewAF.getBgnDate().trim());
          }
          if (loanapplytbnewAF.getEndDate().trim().equals("")) {

            ab.setEndDate("");
          } else {
            ab.setEndDate(loanapplytbnewAF.getEndDate().trim());
          }

          PlOperateLog plOperateLog = new PlOperateLog();// ������־����
          plOperateLog
              .setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
          plOperateLog
              .setOpModel(String
                  .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// �����������Ϣ
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
    } catch (BusinessException e) {
      // TODO Auto-generated catch block
      throw e;
    }
  }

  /**
   * hanl ɾ�����������
   */
  public void deleteAsistantBorrowerInfo(String idaf, SecurityInfo securityInfo)
      throws BusinessException {

    try {
      String contractid = assistantBorrowerDAO
          .findContractidByAuxiliaryid(idaf);// ����������ѯ��ͬ���
      if (contractid == null) {
        throw new BusinessException("�ü�¼�Ѿ���ɾ����");
      } else {
        String contrid = borrowerAccDAO.findContractidByContractid(contractid);// ���ݺ�ͬ�ţ���ѯ�Ƿ��д���1.5.6������״̬�ļ�¼
        if (contrid == null) {
          throw new BusinessException("�ú�ͬ״̬�²���ɾ�������������Ϣ��");
        } else {
          boolean isflag = borrowerLoanInfoDAO
              .isFindContractidByContractid(contrid);// �ж��Ƿ�¼�����
          if (!isflag) {
            assistantBorrowerDAO.deleteAsistantBorrowerInfoByAuxiliaryid(idaf);// ɾ�����������

            PlOperateLog plOperateLog = new PlOperateLog();// ������־����
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
            plOperateLog
                .setOpModel(String
                    .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO));// �����������Ϣ
            plOperateLog.setOpButton(String
                .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
            plOperateLog.setOpBizId(new BigDecimal(idaf));
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpTime(new Date());
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setContractId(contractid);
            plOperateLogDAO.insert(plOperateLog);
          } else {
            throw new BusinessException("����ɾ�������������Ϣ��");
          }
        }
      }
    } catch (BusinessException e) {
      // TODO Auto-generated catch block
      throw e;
    }

  }

  /**
   * hanl ��ʾ������Ϣ
   */
  public LoanapplyTcNewAF showBuyHouseInfoBycontractid(String contractid,
      String buyhouseorgid) throws BusinessException {
    LoanapplyTcNewAF lptcAF = new LoanapplyTcNewAF();
    try {
      SpecialBankListDTO specialBankListDTO = housesDAO
          .findBankPerson(contractid);
      Houses houses = housesDAO.queryById(contractid);// ���ݺ�ͬ��Ų�ѯ������Ϣ
      lptcAF.setPhotoUrl(houses.getPhotoUrl());
      boolean isflag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// �ж��Ƿ�¼�����
      String borrowerName = borrowerDAO
          .findBorrowerNameInfoByContractid(contractid);// ���ݺ�ͬ�ţ���ѯ���������
      String headid = null;
      if (houses.getHouseType() != null) {
        headid = houses.getHeadId();
      }
      if (headid != null
          || (buyhouseorgid != null && !buyhouseorgid.equals(""))) {
        // �ܽ�����ǰ�����к�ͬ��ţ�����������۷���λ
        // ������������if���ǳ����ġ�if������˵���б���������pl115����headid
        String pkid = "";
        if (buyhouseorgid != null && !buyhouseorgid.equals("")) {
          pkid = buyhouseorgid;
        } else {
          pkid = houses.getHeadId();
        }

        lptcAF.setContractId(contractid);
        lptcAF.setBorrowerName(borrowerName);
        BuyHouserDTO buyDTO = developerDAO.findHosesSomeInfo(pkid);
        lptcAF.setOrgName(buyDTO.getDeveloperName());
        lptcAF.setDeveloperTel(buyDTO.getDeveloperTel());
        List bankList = new ArrayList();
        if (specialBankListDTO.getBankName() != null
            && !"".equals(specialBankListDTO.getBankName())) {
          BankListDTO bdto = new BankListDTO();
          bdto.setBankKey(specialBankListDTO.getBankCard());
          bdto.setBankValue(specialBankListDTO.getBankName());
          bdto.setBankacc(specialBankListDTO.getBankCard());
          bankList.add(bdto);
        } else {
          bankList = developerDAO.findBankListById(pkid);// ��ѯ����
        }
        lptcAF.setBanklist(bankList);
        lptcAF.setFloorlist(developerDAO.findSomeFloorListById(pkid));
        
        lptcAF.setHouseType(contractid.substring(6, 8));
        lptcAF.setHouseTypehidden(contractid.substring(6, 8));
        lptcAF.setHouseTypetemp("");// ��Ϊhouses.getHouseType() == null
      } else if (houses.getHouseType() == null) {
        //  �ж��Ƿ�Ϊ��������
        Borrower borrower = borrowerDAO.queryById(contractid);
        
        SpecialBorrower s = null;
        if(borrower.getPrivilegeBorrowerId() != null)
          s = specialBorrowerDAO.queryByHeadID(Integer.valueOf(borrower.getPrivilegeBorrowerId().toString()));
        lptcAF.setContractId(contractid);
        lptcAF.setBorrowerName(borrowerName);
        // ���ݺ�ͬ�ŵĵ�7,8λȡ��������
        lptcAF.setHouseType(contractid.substring(6, 8));
        lptcAF.setHouseTypehidden(contractid.substring(6, 8));
        if (s != null) {
          // ���Ϊ��������ʱ������Ϣ������ķ���ȡ��
          if (contractid.substring(6, 8).equals("01")) {
            List bankList = new ArrayList();
            if (s.getPerBank() != null && !"".equals(s.getPerBank())) {
              lptcAF.setDeveloperPaybank(s.getPerBankAcc());
              lptcAF.setBanNamehidden(s.getPerBank());
              lptcAF.setDeveloperPaybankAAcc(s.getPerBankAcc());
              BankListDTO bdto = new BankListDTO();
              bdto.setBankKey(s.getPerBankAcc());
              bdto.setBankValue(s.getPerBank());
              bdto.setBankacc(s.getPerBankAcc());
              bankList.add(bdto);
            } else if (s.getHeadId() != null) {
              bankList = developerDAO.findBankListById(s.getHeadId());
            }
            lptcAF.setBanklist(bankList);
            lptcAF.setBuyhouseorgid(s.getHeadId());
            if (s.getFloorId() != null) {
              // �����������¼����¥�̺�¥�̺Ÿ��ù�����Ϣҳ�����
              lptcAF.setSpeAppFlag("0");
              String floorName = developProjectDAO.queryById(
                  Integer.valueOf(s.getFloorId())).getFloorName();
              lptcAF.setFloorId(floorName);
              // ¥�̺�����
              List floorNumList = this.findAllBuildNumList(s.getHeadId(),
                  floorName);
              lptcAF.setFloornumlist(floorNumList);
              lptcAF.setFloorNum(s.getFloorId());
            }
            if (s.getHeadId() != null) {
              lptcAF.setFloorlist(developerDAO.findSomeFloorListById(s
                  .getHeadId()));
              lptcAF.setOrgName(developerDAO.findHosesSomeInfo(s.getHeadId())
                  .getDeveloperName());
            }

          }
        }
        lptcAF.setHouseTypetemp("");// ��Ϊhouses.getHouseType() == null
        // ˵��û�����������Ϣ HouseTypetemp�ǿ�
      } 
      if(houses.getHouseType() != null) {
        if (isflag) {
          String housestype = houses.getHouseType();
          if (housestype.equals("01")) {
            lptcAF.setTemp_type("5");
            lptcAF.setRemark1(houses.getRemark());
          } else {
            lptcAF.setTemp_type("6");
            lptcAF.setRemark2(houses.getRemark());
          }
        }
        lptcAF.setContractId(contractid);
        lptcAF.setBorrowerName(borrowerName);
        if (buyhouseorgid != null && !buyhouseorgid.equals("")) {
          BuyHouserDTO buyDTO = developerDAO.findHosesSomeInfo(buyhouseorgid);
          lptcAF.setDeveloperTel(buyDTO.getDeveloperTel());
        } else {
          lptcAF.setDeveloperTel(houses.getDeveloperTel());
        }
        
        if (houses.getHeadId() != null) {
          lptcAF.setBuyhouseorgid(houses.getHeadId());
        }
        if (houses.getHouseType().equals("01")) {
          
          lptcAF.setDeveloperPaybank(houses.getDeveloperPaybankAAcc());
          lptcAF.setBanNamehidden(houses.getDeveloperPaybank());
          lptcAF.setDeveloperPaybankAAcc(houses.getDeveloperPaybankAAcc());
          if (houses.getFloorId() != null) {
            if (developProjectDAO.queryById(Integer.valueOf(houses.getFloorId()
                .toString())) != null) {
              lptcAF.setFloorId(developProjectDAO.queryById(
                  Integer.valueOf(houses.getFloorId().toString()))
                  .getFloorName());
            }
          } else {
              lptcAF.setFloorId("");
          }
          lptcAF.setFloorNum(houses.getFloorId().toString());// ������Զ�Ӧ����¥�̺�������key(floorId)
          lptcAF.setRoomNum(houses.getRoomNum());
          lptcAF.setFirstPay(houses.getFirstPay() + "");
          lptcAF.setHouseTotlePrice(houses.getHouseTotlePrice() + "");
          lptcAF.setHouseArea(houses.getHouseArea() + "");
          if (houses.getHouseTotlePrice() != null) {
            lptcAF.setHousePrice(houses.getHouseTotlePrice().divide(
                houses.getHouseArea(), 0, BigDecimal.ROUND_HALF_UP)
                + "");
          } else {
            lptcAF.setHousePrice("");
          }

          lptcAF.setBuyHouseContractId(houses.getBuyHouseContractId());
          if (houses.getFirstPay() != null) {
            lptcAF.setFirstTol(houses.getFirstPay().divide(
                houses.getHouseTotlePrice(), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1), 0,
                    BigDecimal.ROUND_HALF_UP)
                + "" + "%");
          } else {

            lptcAF.setFirstTol("");
          }

          lptcAF.setIsNowhouse(houses.getIsNowhouse());
          lptcAF.setOverDate(houses.getOverDate());
          lptcAF.setBuildRightNum(houses.getBuildRightNum());
          lptcAF.setAgreementDate(houses.getAgreementDate());
          lptcAF.setHouseAddr(houses.getHouseAddr());
          lptcAF.setHouseType(houses.getHouseType());
          if (houses.getHouseType().equals("01")) {
            lptcAF.setHouseTypehidden("01");
            lptcAF.setRemark1(houses.getRemark());
          } else {
            lptcAF.setHouseTypehidden("02");
            lptcAF.setRemark2(houses.getRemark());
          }
          // ¥�̺�����
          List floorNumList = this.findAllBuildNumList(headid, lptcAF
              .getFloorId());
          lptcAF.setFloornumlist(floorNumList);
        } else {
          lptcAF.setBargainorName(houses.getBargainorName());
          lptcAF.setBargainorCardKind(houses.getBargainorCardKind());
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
          lptcAF.setHouseType(houses.getHouseType());
          if (houses.getHouseType().equals("01")) {
            lptcAF.setHouseTypehidden("01");
            lptcAF.setRemark1(houses.getRemark());
          } else {
            lptcAF.setHouseTypehidden("02");
            lptcAF.setRemark2(houses.getRemark());
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return lptcAF;
  }

  /**
   * hanl ����ͬ���
   */
  public String checkTCContractIdByContractId(String contractId)
      throws BusinessException {
    String id = null;
    try {
      String contractid = borrowerAccDAO.findContractidByContractid(contractId);// ���ݺ�ͬ�ţ���ѯ�Ƿ��д���1.5.6������״̬�ļ�¼
      if (contractid == null) {
        throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬����ȷ��");
      } else {
        String pkid = housesDAO.findPkidByContractid(contractId);// �жϸú�ͬ����Ƿ������pl114
        if (pkid == null) {
          boolean isflag = borrowerLoanInfoDAO
              .isFindContractidByContractid(contractId);// �ж��Ƿ�¼�����
          if (isflag) {
            throw new BusinessException("�ý�����Ѿ�¼������Ϣ��������ӹ�����Ϣ");
          } else {
            id = borrowerDAO.findBorrowerNameInfoByContractid(contractId);// ���ݺ�ͬ�ţ���ѯ���������
          }
        } else {
          throw new BusinessException("�ú�ͬ������Ϣ�Ѿ����ڣ�������ӹ�����Ϣ");
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return id;
  }

  /**
   * hanl ��ӹ�����Ϣ
   */
  public void saveHouseInfo(LoanapplyTcNewAF loanapplytcnewAF,
      SecurityInfo securityInfo) throws BusinessException {
    String contractid = loanapplytcnewAF.getContractId();
    String conid = housesDAO.checkcontractid(contractid);// �жϺ�ͬ����Ƿ����
    if (conid == null) {
      throw new BusinessException("�ú�ͬ��Ų�����");
    } else {
      String pkid = housesDAO.findPkidByContractid(contractid);// �˴�ȡ����pkid��pl114�еĹ������ͣ�
      // ����գ���˵��û�й�����Ϣ��������һ��������Ϊ����pl114
      if (pkid == null) {// ����pl114
        housesDAO.deleteHousesInfo(contractid);// ��Ϊ��¼������ʱ����pl114������˺�ͬ��ţ����Դ˴�Ҫ���빺����Ϣ��¼�ǣ��ȰѸ�����¼ɾ��
        Houses houses = new Houses();
        houses.setContractId(contractid);
        if (loanapplytcnewAF.getHouseTypehidden().equals("01")) {// ��Ʒ��
          houses.setHeadId(loanapplytcnewAF.getBuyhouseorgid());
          houses.setDeveloperTel(loanapplytcnewAF.getDeveloperTel());
          houses.setDeveloperPaybank(loanapplytcnewAF.getBanNamehidden());// ����������ȡ����
          houses.setDeveloperPaybankAAcc(loanapplytcnewAF
              .getDeveloperPaybankAAcc());
          // ����¥�̺Ϳ�����id ������

          String floornums = loanapplytcnewAF.getFloorNumber();
          String floorId = loanapplytcnewAF.getFloorNum();
          // ����
          houses.setFloorId(new BigDecimal(floorId));
          houses.setFloorNum(floornums);
          houses.setRoomNum(loanapplytcnewAF.getRoomNum());
          // houses.setFirstPay(new BigDecimal(loanapplytcnewAF.getFirstPay()));
          houses.setHouseTotlePrice(new BigDecimal(loanapplytcnewAF
              .getHouseTotlePrice()));
          houses
              .setBuyHouseContractId(loanapplytcnewAF.getBuyHouseContractId());
          houses.setHouseArea(new BigDecimal(loanapplytcnewAF.getHouseArea()));
          houses.setIsNowhouse(loanapplytcnewAF.getIsNowhouse());
          houses.setOverDate(loanapplytcnewAF.getOverDate());
          houses.setBuildRightNum(loanapplytcnewAF.getBuildRightNum());
          houses.setAgreementDate(loanapplytcnewAF.getAgreementDate());
          houses.setHouseAddr(loanapplytcnewAF.getHouseAddr());
          houses.setRemark(loanapplytcnewAF.getRemark1());
          // ���� ������У�������˺�
          houses.setFundBank(loanapplytcnewAF.getFundBank());
          houses.setFundBankAcc(loanapplytcnewAF.getFundBankAcc());
        } else {// ���ַ�
          houses.setBargainorName(loanapplytcnewAF.getBargainorName());
          houses.setBargainorCardKind(loanapplytcnewAF.getBargainorCardKind());
          houses.setBargainorCardNum(loanapplytcnewAF.getBargainorCardNum());
          houses.setBargainorTel(loanapplytcnewAF.getBargainorTel());
          houses.setBargainorHousepropertyCode(loanapplytcnewAF
              .getBargainorHousepropertyCode());
          houses.setBargainorMobile(loanapplytcnewAF.getBargainorMobile());
          houses
              .setBargainorHouseAddr(loanapplytcnewAF.getBargainorHouseAddr());
          houses.setBargainorPaybank(loanapplytcnewAF.getBargainorPaybank());
          houses.setBargainorPaybankAcc(loanapplytcnewAF
              .getBargainorPaybankAcc());
          houses
              .setBargainorHouseArea(loanapplytcnewAF.getBargainorHouseArea());
          houses.setBargainorTotlePrice(new BigDecimal(loanapplytcnewAF
              .getBargainorTotlePrice()));
          houses.setBargainorHouseAge(new BigDecimal(loanapplytcnewAF
              .getBargainorHouseAge()));
          houses.setBargainorAgreementDate(loanapplytcnewAF
              .getBargainorAgreementDate());
          houses.setRemark(loanapplytcnewAF.getRemark2());
        }
        houses.setOperator(securityInfo.getUserName());
        houses.setOpTime(new Date());
        houses.setHouseType(loanapplytcnewAF.getHouseTypehidden());
        housesDAO.insert(houses);

      } else {// ����pl114
        Houses houses = housesDAO.queryById(contractid);
        if (loanapplytcnewAF.getHouseTypehidden().equals("01")) {// ��Ʒ��
          houses.setHeadId(loanapplytcnewAF.getBuyhouseorgid());
          houses.setDeveloperTel(loanapplytcnewAF.getDeveloperTel());
          houses.setDeveloperPaybank(loanapplytcnewAF.getBanNamehidden());
          houses.setDeveloperPaybankAAcc(loanapplytcnewAF
              .getDeveloperPaybankAAcc());
          // ����¥�̺Ϳ�����id ������
          String floornums = loanapplytcnewAF.getFloorNumber();
          String floorId = loanapplytcnewAF.getFloorNum();
          // ����
          houses.setFloorId(new BigDecimal(floorId));
          houses.setFloorNum(floornums);
          houses.setRoomNum(loanapplytcnewAF.getRoomNum());
          // houses.setFirstPay(new BigDecimal(loanapplytcnewAF.getFirstPay()));
          houses.setHouseTotlePrice(new BigDecimal(loanapplytcnewAF
              .getHouseTotlePrice()));
          houses
              .setBuyHouseContractId(loanapplytcnewAF.getBuyHouseContractId());
          houses.setHouseArea(new BigDecimal(loanapplytcnewAF.getHouseArea()));
          houses.setIsNowhouse(loanapplytcnewAF.getIsNowhouse());
          houses.setOverDate(loanapplytcnewAF.getOverDate());
          houses.setBuildRightNum(loanapplytcnewAF.getBuildRightNum());
          houses.setAgreementDate(loanapplytcnewAF.getAgreementDate());
          houses.setHouseAddr(loanapplytcnewAF.getHouseAddr());
          houses.setRemark(loanapplytcnewAF.getRemark1());
          // ���� ������У�������˺�
          houses.setFundBank(loanapplytcnewAF.getFundBank());
          houses.setFundBankAcc(loanapplytcnewAF.getFundBankAcc());
        } else {// ���ַ�
          houses.setBargainorName(loanapplytcnewAF.getBargainorName());
          houses.setBargainorCardKind(loanapplytcnewAF.getBargainorCardKind());
          houses.setBargainorCardNum(loanapplytcnewAF.getBargainorCardNum());
          houses.setBargainorTel(loanapplytcnewAF.getBargainorTel());
          houses.setBargainorHousepropertyCode(loanapplytcnewAF
              .getBargainorHousepropertyCode());
          houses.setBargainorMobile(loanapplytcnewAF.getBargainorMobile());
          houses
              .setBargainorHouseAddr(loanapplytcnewAF.getBargainorHouseAddr());
          houses.setBargainorPaybank(loanapplytcnewAF.getBargainorPaybank());
          houses.setBargainorPaybankAcc(loanapplytcnewAF
              .getBargainorPaybankAcc());
          houses
              .setBargainorHouseArea(loanapplytcnewAF.getBargainorHouseArea());
          houses.setBargainorTotlePrice(new BigDecimal(loanapplytcnewAF
              .getBargainorTotlePrice()));
          houses.setBargainorHouseAge(new BigDecimal(loanapplytcnewAF
              .getBargainorHouseAge()));
          houses.setBargainorAgreementDate(loanapplytcnewAF
              .getBargainorAgreementDate());
          houses.setRemark(loanapplytcnewAF.getRemark2());
        }
      }
      PlOperateLog plOperateLog = new PlOperateLog();// ������־����
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_HOUSEINFO));// ������Ϣ
      if (pkid == null) {// ����
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      } else {// ����
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      }
      plOperateLog.setOpBizId(new BigDecimal(contractid));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setContractId(contractid);
      plOperateLogDAO.insert(plOperateLog);
    }

  }

  /**
   * hanl ��ʾ�����Ϣ
   */
  public LoanapplyTdNewAF showBorrowerLoanInfoByContractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException {

    LoanapplyTdNewAF tdAF = new LoanapplyTdNewAF();

    try {
      String housetype = housesDAO.findPkidByContractid(contractid);// �����ֵ��˵���������Ϣ�͹�����Ϣ�Ѿ�¼����ϡ�Ҳֻ���������ܽ���
      if (housetype != null) {
        EndorsecontractTaDTO ectaAF = borrowerDAO
            .queryBorrowerInfoHl(contractid);// ��ѯPL110�ֶΣ����ڽ���ͬ��Ϣ
        tdAF.setContractId(contractid);
        tdAF.setBorrowerName(ectaAF.getDebitter());
        // ��ʾ�����ܼ�
        Houses houses = housesDAO.queryById(contractid);
        if (houses.getHouseType().equals("01")) {
          BigDecimal houseTotlePrice = new BigDecimal(0.00);
          houseTotlePrice = houses.getHouseTotlePrice();
          if (houseTotlePrice != null) {
            tdAF.setHouseTotlePrice(houseTotlePrice.toString());
          }
        } else {
          tdAF.setHouseTotlePrice(houses.getBargainorTotlePrice().toString());
        }
        if (ectaAF.getCertificateType() != null
            && !"".equals(ectaAF.getCertificateType())) {
          tdAF.setCardKind(BusiTools.getBusiValue(Integer.parseInt(ectaAF
              .getCertificateType()), BusiConst.DOCUMENTSSTATE));
        }

        tdAF.setCardNum(ectaAF.getCertificateNum());
        tdAF.setOffice(ectaAF.getOffice());
        String privilegeBorrowerId = borrowerDAO
            .findPrivilegeBorrowerIdByContractid(contractid);// �Ƿ���������
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractid);// �ж��Ƿ�¼�����
        if (isflag) {// ����Ѿ��ж���ˣ��ͰѶ����ʾ����������ֻ��ʾ�������Ϣ
          if (privilegeBorrowerId != null) {
            tdAF.setSpid("1");
          }
          BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO
              .queryById(contractid);// ��ѯ�����Ϣ
          tdAF.setLoanMoney(borrowerloan.getLoanMoney() + "");// ������
          tdAF.setLoantimeLimit(Integer.parseInt(borrowerloan
              .getLoanTimeLimit())
              + "");// ����������
          tdAF.setLoanMood(borrowerloan.getLoanMode());// ������ʽ
          tdAF.setLoanMonthRate(borrowerloan.getLoanMonthRate() + "");// ÿ����Ϣ
          tdAF.setLoanMonthRatess(borrowerloan.getLoanMonthRate().multiply(
              new BigDecimal(100))
              + "%");// ÿ����Ϣ
          if (borrowerloan.getLoanPoundage() == null) {
            tdAF.setLoanPoundage("");
          } else {
            tdAF.setLoanPoundage(borrowerloan.getLoanPoundage() + ""); // ������
          }
          if (borrowerloan.getFirstLoanMoney() == null) {
            tdAF.setFirstLoanMoney("");
          } else {
            tdAF.setFirstLoanMoney(borrowerloan.getFirstLoanMoney() + "");// �״λ�
          }
          if (borrowerloan.getCorpusInterest() == null) {
            tdAF.setCorpusInterest("");
          } else {
            tdAF.setCorpusInterest(borrowerloan.getCorpusInterest() + "");// �±�Ϣ
            tdAF.setEntireYearMoney(borrowerloan.getCorpusInterest() + "");// �±�Ϣ
          }
          tdAF.setPhotoUrl(borrowerloan.getPhotoUrl());
          // �׸���ռ�����ܼ۰ٷֱ�
          if (borrowerloan.getLoanMoney() != null) {
            tdAF
                .setFirstTol((houses.getHouseTotlePrice() == null ? houses
                    .getBargainorTotlePrice() : houses.getHouseTotlePrice())
                    .subtract(borrowerloan.getLoanMoney())
                    .divide(
                        (houses.getHouseTotlePrice() == null ? houses
                            .getBargainorTotlePrice() : houses
                            .getHouseTotlePrice()), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1),
                        0, BigDecimal.ROUND_HALF_UP)
                    + "%");
          } else {
            tdAF.setFirstTol("");
          }

        } else {// ���û�ж�ȣ����ж�һ���Ƿ����������ˣ��Ǿ���д��������ޣ������ʣ��»���Ϣ,���ҷŸ�����������˵�����������˵ģ�ҳ����ֻ��

          if (privilegeBorrowerId != null) {

            SpecialBorrower sb = specialBorrowerDAO
                .findLoanMoneyAndLimitByPrivilegeBorrowerId(privilegeBorrowerId);// ����������������
            tdAF.setLoanMoney(sb.getLoanMoney().toString());
            tdAF.setLoantimeLimit(Integer.parseInt(sb.getLoanTimeLimit()) + "");
            String monthRate = this.findMonthRate(ectaAF.getOffice(), sb
                .getLoanTimeLimit(), "");
            tdAF.setLoanMonthRate(monthRate);
            BigDecimal bmonthrate = new BigDecimal(monthRate);
            tdAF.setLoanMonthRatess(bmonthrate.multiply(new BigDecimal(100))
                + "%");
            tdAF.setCorpusInterest(CorpusinterestBS.getCorpusInterest(
                sb.getLoanMoney(), bmonthrate, sb.getLoanTimeLimit())
                .toString());
            tdAF.setSpid("1");
          }
        }

      } else {
        throw new BusinessException("����δ¼�빺����Ϣ");
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tdAF;
  }

  public LoanapplyTdNewAF showBorrowerLoanInfoByContractid1(String contractid,
      SecurityInfo securityInfo) throws BusinessException {
    LoanapplyTdNewAF tdAF = new LoanapplyTdNewAF();
    try {
      String housetype = housesDAO.findPkidByContractid(contractid);// �����ֵ��˵���������Ϣ�͹�����Ϣ�Ѿ�¼����ϡ�Ҳֻ���������ܽ���
      if (housetype != null) {
        EndorsecontractTaDTO ectaAF = borrowerDAO
            .queryBorrowerInfoHl(contractid);// ��ѯPL110�ֶΣ����ڽ���ͬ��Ϣ
        tdAF.setContractId(contractid);
        tdAF.setBorrowerName(ectaAF.getDebitter());
        // ��ʾ�����ܼ�
        Houses houses = housesDAO.queryById(contractid);
        if (houses.getHouseType().equals("01")) {
          BigDecimal houseTotlePrice = new BigDecimal(0.00);
          houseTotlePrice = houses.getHouseTotlePrice();
          if (houseTotlePrice != null) {
            tdAF.setHouseTotlePrice(houseTotlePrice.toString());
          }
        } else {
          tdAF.setHouseTotlePrice(houses.getBargainorTotlePrice().toString());
        }
        if (ectaAF.getCertificateType() != null
            && !"".equals(ectaAF.getCertificateType())) {
          tdAF.setCardKind(BusiTools.getBusiValue(Integer.parseInt(ectaAF
              .getCertificateType()), BusiConst.DOCUMENTSSTATE));
        }

        tdAF.setCardNum(ectaAF.getCertificateNum());
        tdAF.setOffice(ectaAF.getOffice());
        String privilegeBorrowerId = borrowerDAO
            .findPrivilegeBorrowerIdByContractid(contractid);// �Ƿ���������
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractid);// �ж��Ƿ�¼�����
        if (isflag) {// ����Ѿ��ж���ˣ��ͰѶ����ʾ����������ֻ��ʾ�������Ϣ
          if (privilegeBorrowerId != null) {
            tdAF.setSpid("1");
          }
          BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO
              .queryById(contractid);// ��ѯ�����Ϣ
          tdAF.setLoanMoney(borrowerloan.getLoanMoney() + "");// ������

          tdAF.setLoantimeLimit(Integer.parseInt(borrowerloan
              .getLoanTimeLimit())
              / 12 + "");// ����������
          tdAF.setLoanMood(borrowerloan.getLoanMode());// ������ʽ
          tdAF.setLoanMonthRate(borrowerloan.getLoanMonthRate() + "");// ÿ����Ϣ
          tdAF.setLoanMonthRatess(borrowerloan.getLoanMonthRate().multiply(
              new BigDecimal(100))
              + "%");// ÿ����Ϣ
          if (borrowerloan.getLoanPoundage() == null) {
            tdAF.setLoanPoundage("");
          } else {
            tdAF.setLoanPoundage(borrowerloan.getLoanPoundage() + ""); // ������
          }
          if (borrowerloan.getFirstLoanMoney() == null) {
            tdAF.setFirstLoanMoney("");
          } else {
            tdAF.setFirstLoanMoney(borrowerloan.getFirstLoanMoney() + "");// �״λ�
          }
          if (borrowerloan.getCorpusInterest() == null) {
            tdAF.setCorpusInterest("");
          } else {
            tdAF.setCorpusInterest(borrowerloan.getCorpusInterest() + "");// �±�Ϣ
            tdAF.setEntireYearMoney(borrowerloan.getCorpusInterest() + "");// �±�Ϣ
          }
          tdAF.setPhotoUrl(borrowerloan.getPhotoUrl());
          // �׸���ռ�����ܼ۰ٷֱ�
          if (borrowerloan.getLoanMoney() != null) {
            tdAF
                .setFirstTol((houses.getHouseTotlePrice() == null ? houses
                    .getBargainorTotlePrice() : houses.getHouseTotlePrice())
                    .subtract(borrowerloan.getLoanMoney())
                    .divide(
                        (houses.getHouseTotlePrice() == null ? houses
                            .getBargainorTotlePrice() : houses
                            .getHouseTotlePrice()), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100.00)).divide(new BigDecimal(1),
                        0, BigDecimal.ROUND_HALF_UP)
                    + "%");
          } else {
            tdAF.setFirstTol("");
          }
        } else {// ���û�ж�ȣ����ж�һ���Ƿ����������ˣ��Ǿ���д��������ޣ������ʣ��»���Ϣ,���ҷŸ�����������˵�����������˵ģ�ҳ����ֻ��

          if (privilegeBorrowerId != null) {

            SpecialBorrower sb = specialBorrowerDAO
                .findLoanMoneyAndLimitByPrivilegeBorrowerId(privilegeBorrowerId);// ����������������
            tdAF.setLoanMoney(sb.getLoanMoney().toString());
            tdAF.setLoantimeLimit(Integer.parseInt(sb.getLoanTimeLimit()) / 12
                + "");
            String monthRate = this.findMonthRate(ectaAF.getOffice(), sb
                .getLoanTimeLimit(), "");
            tdAF.setLoanMonthRate(monthRate);
            BigDecimal bmonthrate = new BigDecimal(monthRate);
            tdAF.setLoanMonthRatess(bmonthrate.multiply(new BigDecimal(100))
                + "%");
            tdAF.setCorpusInterest(CorpusinterestBS.getCorpusInterest(
                sb.getLoanMoney(), bmonthrate, sb.getLoanTimeLimit())
                .toString());
            tdAF.setSpid("1");
          }
        }

      } else {
        throw new BusinessException("����δ¼�빺����Ϣ");
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tdAF;
  }

  /**
   * hanl ���������
   */
  public String findMonthRate(String office, String loantimeLimit,
      String loanMood) throws BusinessException {
    String monthRate = "";
    try {
      String loantype = "0";
      int i = Integer.parseInt(loantimeLimit);
      if (!loanMood.equals("")) {
        if (loanMood.equals("4")) {
          loantype = "2";
        } else if (loanMood.equals("5")) {
          loantype = "3";
        } else {
          if (i > 60) {
            loantype = "1";
          }
        }
      } else {
        if (i > 60) {
          loantype = "1";
        }
      }
      BigDecimal rate = loanRateDAO.findMontRate(office, loantype);// ������
      if (rate == null) {
        monthRate = "";
      } else {
        monthRate = rate.toString();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return monthRate;
  }

  /**
   * hanl ����ͬ���
   */
  public void checkTdContractIdByContractId(String contractId)
      throws BusinessException {
    try {
      String id = borrowerAccDAO.findContractidByContractid(contractId);// ���ݺ�ͬ�ţ���ѯ�Ƿ��д���1.5.6������״̬�ļ�¼
      if (id == null) {
        throw new BusinessException("�ú�ͬ��Ų����ڻ�ú�ͬ���ڰ����У�");
      } else {
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractId);// �ж��Ƿ�¼����
        if (isflag) {
          throw new BusinessException("�ú�ͬ�����Ϣ�Ѿ����ڣ�������Ӷ����Ϣ");
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * hanl ��Ӷ����Ϣ
   */
  public void saveBorrowerLoanInfo(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {
    try {
      String contractid = loanapplytdnewAF.getContractId();
      String privilegeBorrowerId = borrowerDAO
          .findPrivilegeBorrowerIdByContractid(contractid);// �Ƿ���������
      // ҳ������Ľ�������
      int loanMonth = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
      float loanMoney = Float.parseFloat(loanapplytdnewAF.getLoanMoney());
      // ���ܴ��������������
      int loanMonthLimit = loanapplytdnewAF.getLoanMonthLimit();
      BigDecimal loanMoneyLimit = loanapplytdnewAF.getLoanMoneyLimit();
      if (privilegeBorrowerId == null) {
        if (loanMonth > loanMonthLimit) {
          throw new BusinessException("���������޲��ó���" + loanMonthLimit + "����");
        }
        if (loanMoney > loanMoneyLimit.floatValue()) {
          throw new BusinessException("��������ó���" + loanMoneyLimit + "Ԫ");
        }
      }
      boolean isflag = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractid);// �ж��Ƿ�¼�����
      if (isflag) {// ����pl115
        BorrowerLoanInfo bl = borrowerLoanInfoDAO.queryById(contractid);
        bl.setLoanMoney(new BigDecimal(loanapplytdnewAF.getLoanMoney()));
        bl.setLoanTimeLimit(loanapplytdnewAF.getLoantimeLimit());
        if (loanapplytdnewAF.getLoanMood().equals("4")) {
          bl.setLoanRateType("2");
        }
        if (loanapplytdnewAF.getLoanMood().equals("5")) {
          bl.setLoanRateType("3");
        }
        bl.setLoanMode(loanapplytdnewAF.getLoanMood());
        bl
            .setLoanMonthRate(new BigDecimal(loanapplytdnewAF
                .getLoanMonthRate()));
        bl.setLoanPoundage(new BigDecimal(loanapplytdnewAF.getLoanPoundage()));
        if (loanapplytdnewAF.getCorpusInterest().equals("")) {
          bl.setCorpusInterest(new BigDecimal(0.00));
        } else {
          // ������ʽΪ�����ڴ���
          if (loanapplytdnewAF.getLoanMood().equals("4")
              || loanapplytdnewAF.getLoanMood().equals("5")) {
            bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
                .getEntireYearMoney()));
          } else {
            bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
                .getCorpusInterest()));
          }
        }
        if (!loanapplytdnewAF.getLoanMood().equals("4")
            && !loanapplytdnewAF.getLoanMood().equals("5")) {
          int i = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
          String type = "0";
          if (i > 60) {
            type = "1";
          }
          bl.setLoanRateType(type);
        }
      } else {// ����pl115
        BorrowerLoanInfo bl = new BorrowerLoanInfo();
        bl.setContractId(contractid);
        bl.setLoanMoney(new BigDecimal(loanapplytdnewAF.getLoanMoney()));
        bl.setLoanTimeLimit(loanapplytdnewAF.getLoantimeLimit());
        if (loanapplytdnewAF.getLoanMood().equals("4")) {
          bl.setLoanRateType("2");
        }
        if (loanapplytdnewAF.getLoanMood().equals("5")) {
          bl.setLoanRateType("3");
        }
        bl.setLoanMode(loanapplytdnewAF.getLoanMood());
        bl.setLoanMonthRate(new BigDecimal(loanapplytdnewAF
                .getLoanMonthRate()));
        bl.setLoanPoundage(new BigDecimal(loanapplytdnewAF.getLoanPoundage()));
        // ������ʽΪ�����ڴ���
        if (loanapplytdnewAF.getLoanMood().equals("4")
            || loanapplytdnewAF.getLoanMood().equals("5")) {
          bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
              .getEntireYearMoney()));
        } else {
          bl.setCorpusInterest(new BigDecimal(loanapplytdnewAF
              .getCorpusInterest()));
        }
        if (!loanapplytdnewAF.getLoanMood().equals("4")
            && !loanapplytdnewAF.getLoanMood().equals("5")) {
          int i = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
          String type = "0";
          if (i > 60) {
            type = "1";
          }
          bl.setLoanRateType(type);
        }
        bl.setOperator(securityInfo.getUserName());
        bl.setOpTime(new Date());
        borrowerLoanInfoDAO.insert(bl);
      }
      PlOperateLog plOperateLog = new PlOperateLog();// ������־����
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
      plOperateLog.setOpModel(String
          .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFOLIMITED));// ������Ϣ
      if (!isflag) {// ����
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
      } else {// ����
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
      }
      plOperateLog.setOpBizId(new BigDecimal(contractid));
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setContractId(contractid);
      plOperateLogDAO.insert(plOperateLog);
    } catch (BusinessException e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * hanl ��ʾά���б�
   */
  public LoanapplyTeNewAF showBorrowerList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
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
      String contranct_st = (String) pagination.getQueryCriterions().get(
          "contranct_st");

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List newlist = new ArrayList();
      List list = new ArrayList();
      list = borrowerAccDAO.findBorrowerAccList(contractId, borrowerName,
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
          listDto.setContractid(obj[0].toString());// obj[0]�Ǻ�ͬ��ţ��ڸ��ݺ�ͬ��ţ���pl115����������Ϣ���ֵ�������������
          LoanapplyTeListDTO moneylimitDTO = new LoanapplyTeListDTO();// ����ֻ����������������
          moneylimitDTO = borrowerAccDAO.findLoanmoneyLimitInfo(obj[0]
              .toString());
          if (obj[1] != null) {
            String str = "";
            for (int i = 6; i > obj[1].toString().length(); i--) {
              str += "0";
            }
            listDto.setEmpid(str + obj[1].toString());
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
          listDto.setLoanmoney(moneylimitDTO.getLoanmoney());
          listDto.setLoanlimit(moneylimitDTO.getLoanlimit());
          if (obj[7] != null) {
            listDto.setTemp_c_st(obj[7].toString());
            String temp_st = BusiTools.getBusiValue(Integer.parseInt(obj[7]
                .toString()), BusiConst.PLCONTRACTSTATUS);
            listDto.setContract_st(temp_st);
            if (temp_st.equals("���δͨ��") || temp_st.equals("����δͨ��")) {
              listDto.setTemp_contract_st("1");
            } else {
              listDto.setTemp_contract_st("2");
            }
          } else {
            listDto.setContract_st("");
          }
          if (obj[8] != null) {
            if (obj[8].toString().equals("0")) {
              listDto.setIsPrintApply("��");
            } else {
              listDto.setIsPrintApply("��");
            }
          }
          if (obj[9] != null) {
            listDto.setRedate(obj[9].toString());
          }
          newlist.add(listDto);
        }
        loanapplytenewAF.setList(newlist);
      } else {
        loanapplytenewAF.setList(list);
      }
      // �б���Ϣ����
      List newallLsit = new ArrayList();
      List lis = new ArrayList();
      lis = borrowerAccDAO.findBorrowerAccListNum(contractId, borrowerName,
          empId, cardNum, buyHouseType, contranct_st, securityInfo);
      // ���ȫ���б���Ϣ��
      if (lis != null) {
        Object obj[] = null;
        Iterator it = lis.iterator();
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          LoanapplyTeListDTO lisDto = new LoanapplyTeListDTO();
          lisDto.setId(obj[0].toString());
          lisDto.setContractid(obj[0].toString());
          LoanapplyTeListDTO moneylimitDTO = new LoanapplyTeListDTO();// ����ֻ����������������
          moneylimitDTO = borrowerAccDAO.findLoanmoneyLimitInfo(obj[0]
              .toString());
          if (obj[1] != null) {
            lisDto.setEmpid(obj[1].toString());
          } else {
            lisDto.setEmpid("");
          }
          lisDto.setBorrowername(obj[2].toString());
          lisDto.setCardnum(obj[3].toString());
          if (obj[4] != null) {
            lisDto.setBuyhousetype(BusiTools.getBusiValue_WL(obj[4].toString(),
                BusiConst.PLHOUSETYPE));
          } else {
            lisDto.setBuyhousetype("");
          }
          lisDto.setLoanmoney(moneylimitDTO.getLoanmoney());
          lisDto.setLoanlimit(moneylimitDTO.getLoanlimit());
          if (obj[7] != null) {
            lisDto.setContract_st(BusiTools.getBusiValue(Integer
                .parseInt(obj[7].toString()), BusiConst.PLCONTRACTSTATUS));
          } else {
            lisDto.setContract_st("");
          }
          newallLsit.add(lisDto);
        }
        loanapplytenewAF.setLis(newallLsit);
      } else {
        loanapplytenewAF.setLis(lis);
      }
      // �б���Ϣ����
      pagination.setNrOfElements(lis.size());
      loanapplytenewAF.setCount(lis.size() + "");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanapplytenewAF;
  }

  /**
   * hanl ɾ��ά���б���ļ�¼
   */
  public void deleteBorrowerInfo(String id, SecurityInfo securityInfo)
      throws BusinessException, Exception {
    try {
      EndorsecontractTaDTO dtol = borrowerDAO.queryBorrowerInfoHl(id);
      String contranctid = borrowerAccDAO.findContractidByContractid(id);// ���ݺ�ͬ�ţ���ѯ�Ƿ��д���1.5.6������״̬�ļ�¼
      Borrower borrower = borrowerDAO.queryById(contranctid);
      if (contranctid == null) {
        throw new BusinessException("����Ϣ�Ѿ�ɾ����");
      } else {
        
        if (borrower.getPrivilegeBorrowerId() == null || borrower.getPrivilegeBorrowerId().compareTo(new BigDecimal(0))==0) {// ����������
          borrowerDAO.deleteBorrowerInfo(id);// ɾ���������Ϣ
          borrowerAccDAO.deleteBorrowerAccInfo(id);// ɾ���˻���Ϣ��
          assistantBorrowerDAO.deleteAsistantBorrowerList(id);// ɾ�����������
          housesDAO.deleteHousesInfo(id);// ɾ��������Ϣ
          borrowerLoanInfoDAO.deleteBorrowerLoanInfo(id);// ɾ�����
        } else {// ����������
          borrowerDAO.deleteBorrowerInfo(id);// ɾ���������Ϣ
          borrowerAccDAO.deleteBorrowerAccInfo(id);// ɾ���˻���Ϣ��
          assistantBorrowerDAO.deleteAsistantBorrowerList(id);// ɾ�����������
          housesDAO.deleteHousesInfo(id);// ɾ��������Ϣ
          borrowerLoanInfoDAO.deleteBorrowerLoanInfo(id);// ɾ�����

          SpecialBorrower sb = specialBorrowerDAO
              .queryappstatusById(new Integer(borrower.getPrivilegeBorrowerId().toString()));// ����������ˣ������õ�
          if(sb!=null){
            sb.setStatus(String.valueOf(BusiConst.APPSTATUS_1));// ������״̬
          }

        }
        ContractNumCancel contractNumCancel = new ContractNumCancel();
        String cancelcontractid = id.substring(8, id.length());
        contractNumCancel.setCancelcontractid(cancelcontractid);
        contractNumCancel.setOffice(dtol.getOffice());
        contractNumCancel.setReserveaA(id.substring(6, 8));
        contractNumCancelDAO.insert(contractNumCancel);// ����ͬ��źͰ��´���Ų������ϱ�
        PlOperateLog plOperateLog = new PlOperateLog();// ������־����
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ����ϵͳ
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_LOANAPPLMAINTAIN));// ά��
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));// ��ʾɾ��
        plOperateLog.setOpBizId(new BigDecimal(id));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLog.setContractId(id);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {

      e.printStackTrace();
    }

  }

  /**
   * hanl ά���б��е��ύ���
   */
  public void truepproval(String id, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      String pkid = borrowerAccDAO.findContractidByContractid(id);// ���ݺ�ͬ�ţ���ѯ�Ƿ��д���1.5.6������״̬�ļ�¼
      if (pkid == null) {
        throw new BusinessException("�Ѿ��ύ��");
      } else {
        boolean isflag = borrowerLoanInfoDAO.isFindContractidByContractid(id);// �ж��Ƿ�¼�����
        if (isflag) {// ���˶��
          BorrowerAcc bacc = borrowerAccDAO.queryById(id);
          bacc.setContractSt("15");// �����˻���Ϣ״̬
        } else {// û������
          throw new BusinessException("δ¼������Ϣ�����ύ��ˣ�");
        }
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  public void chexiaoshenqing(String id, SecurityInfo securityInfo)
    throws BusinessException {
    try {
        BorrowerAcc bacc = borrowerAccDAO.queryById(id);
        if(!bacc.getContractSt().equals("15")){
          throw new BusinessException("״̬��Ϊ�ύ��ˣ�����������");
        }
        if(bacc.getIsContractWrite().equals("1")){
          throw new BusinessException("�˺�ͬ�Ѿ�ǩ��������������");
        }
        bacc.setContractSt("1");// �����˻���Ϣ״̬
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * hanl ���ݰ��´�Id��ѯ���´�
   */
  public String findOfficeName(String office) throws Exception {

    String name = "";
    try {
      name = organizationUnitDAO.queryOrganizationName_LY(office);// ���´�ID ��ѯ���´�
    } catch (Exception e) {
      e.printStackTrace();
    }
    return name;
  }

  /**
   * hanl ��¥��
   */
  public String findFloorMun(String floorId, String buyhouseorgid)
      throws Exception {

    String floornum = null;
    try {
      floornum = developProjectDAO.findFloorNumByflooridheid(floorId,
          buyhouseorgid);// ����¥��id�Ϳ�����id���¥��
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
    return floornum;
  }

  /**
   * wangshuang ���ݿ����̺�¥�����Ʋ�ѯ��¥�����е�¥��
   */
  public List findAllBuildNumList(final String developId, final String floorName)
      throws Exception {
    List list = null;
    try {
      list = developProjectDAO.queryAllBuildNumList(developId, floorName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  //���ݿ����̲�ѯ¥��
  public List findFloorList(final String developerId) throws Exception {
    List list = null;
    try {
      list = developerDAO.findAllFloorListById(developerId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * @author wangshuang ����¥�̺Ų�ѯ����ݺ�(���ݵ�ַ)
   */
  public List findHouseAddListByFloorId(final String floorId) throws Exception {
    List list = null;
    try {
      list = developProjectDAO.queryHouseAddListByFloorId(floorId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * hanl ����������б�
   */
  public DeveleperNewAF findDeveleperList(Pagination pagination,
      SecurityInfo securityInfo, String flag) throws Exception {
    DeveleperNewAF develeperNewAF = new DeveleperNewAF();
    try {
      String developername = (String) pagination.getQueryCriterions().get(
          "developername");
      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      List list = developProjectDAO.findDeveloperList(developername, orderBy,
          orderother, start, pageSize, securityInfo, flag);// �������б�
      int count = developProjectDAO.findDeveloperCount(developername,
          securityInfo, flag);
      pagination.setNrOfElements(count);
      develeperNewAF.setList(list);

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return develeperNewAF;
  }

  /**
   * hanl ����������δͨ����ԭ��
   */
  public String findNotPassReasons(String contractId, String type)
      throws Exception {
    String reason = "";
    try {
      reason = borrowerAccDAO.findReasonBycontractId(contractId, type);// ����������δͨ����ԭ��
    } catch (RuntimeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return reason;
  }

  /**
   * hanl ��ʾ�����ϢΪ�˴�ӡ�õ�
   */
  public LoanapplyTdNewAF printBorrowerLoanInfoByContractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException {

    LoanapplyTdNewAF tdAF = new LoanapplyTdNewAF();

    try {
      Houses houses = housesDAO.queryById(contractid);// �����ֵ��˵���������Ϣ�͹�����Ϣ�Ѿ�¼����ϡ�Ҳֻ���������ܽ���

      if (houses != null) {
        boolean isflag = borrowerLoanInfoDAO
            .isFindContractidByContractid(contractid);// �ж��Ƿ�¼�����
        if (isflag) {// ����Ѿ��ж���ˣ��ͰѶ����ʾ����������ֻ��ʾ�������Ϣ
          BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO
              .queryById(contractid);// ��ѯ�����Ϣ
          tdAF.setLoanMoney(borrowerloan.getLoanMoney() + "");// ������
          tdAF.setLoantimeLimit(borrowerloan.getLoanTimeLimit());// ����������
          tdAF.setLoanMood(borrowerloan.getLoanMode());// ������ʽ
          tdAF.setLoanMonthRate(borrowerloan.getLoanMonthRate() + "");// ÿ����Ϣ

          if (borrowerloan.getLoanPoundage() == null) {
            tdAF.setLoanPoundage("");
          } else {
            tdAF.setLoanPoundage(borrowerloan.getLoanPoundage() + ""); // ������
          }
          if (borrowerloan.getFirstLoanMoney() == null) {
            tdAF.setFirstLoanMoney("");
          } else {
            tdAF.setFirstLoanMoney(borrowerloan.getFirstLoanMoney() + "");// �״λ�
          }
          if (borrowerloan.getCorpusInterest() == null) {
            tdAF.setCorpusInterest("");
          } else {
            tdAF.setCorpusInterest(borrowerloan.getCorpusInterest() + "");// �±�Ϣ
          }

        }
        EndorsecontractTaDTO ectaAF = borrowerDAO.queryBorrowerInfoYU(
            contractid, securityInfo);// ��ѯPL110�ֶΣ����ڽ���ͬ��Ϣ
        tdAF.setContractId(contractid);
        tdAF.setBorrowerName(ectaAF.getDebitter());
        tdAF.setCardKind(BusiTools.getBusiValue(Integer.parseInt(ectaAF
            .getCertificateType()), BusiConst.DOCUMENTSSTATE));
        tdAF.setCardNum(ectaAF.getCertificateNum());
        tdAF.setOffice(ectaAF.getOffice());
        if (houses.getHouseType() != null) {
          if (houses.getHouseType().equals("01")) {
            tdAF.setDeveloperName(developerDAO
                .queryDeveloperNameByDeveloperId(houses.getHeadId()));
          }
        }
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tdAF;
  }

  /**
   * ����pl110 ֻ��Ϊ�˰�path��ӵ�pl110��
   */

  public void updateBorrowerInfo(String contractId, String path)
      throws Exception {
    try {
      Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contractId);// ��ѯpl110
      if (borrower != null) {
        borrower.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * ����pl113 ֻ��Ϊ�˰�path��ӵ�pl113��
   */
  public void updateAssistantBorrowe(String idaf, String path) throws Exception {

    try {
      AssistantBorrower ab = assistantBorrowerDAO.queryById(new Integer(idaf));// �����������ˣ�
      if (ab != null) {
        ab.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * ����pl114 ֻ��Ϊ�˰�path��ӵ�pl114��
   */
  public void updateHouseInfo(String contractId, String path) throws Exception {
    try {
      Houses houses = housesDAO.queryById(contractId);
      if (houses != null) {
        houses.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * ����pl115 ֻ��Ϊ�˰�path��ӵ�pl115��
   */
  public void updateBorrowerLoanInfo(String contractId, String path)
      throws Exception {
    try {
      BorrowerLoanInfo borrowerloan = borrowerLoanInfoDAO.queryById(contractId);// ��ѯ�����Ϣ
      if (borrowerloan != null) {
        borrowerloan.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void deletedBorrowerLoanInfo(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    try {
      String id = borrowerAccDAO.findContractidByContractid(contractId);// ���ݺ�ͬ�ţ���ѯ�Ƿ��д���1.5.6������״̬�ļ�¼
      if (id != null) {
        borrowerLoanInfoDAO.deleteBorrowerLoanInfo(contractId);
      } else {
        throw new BusinessException("�ú�ͬ״̬����ȷ");
      }
    } catch (BusinessException e) {
      throw e;
    }
  }

  /**
   * hanl ���¥�����ƺ���������
   */
  public LoanapplyTcNewAF findSomeInfo(String headid, String floorId,
      String developerPaybankAAcc) throws Exception {
    LoanapplyTcNewAF loanapplyTcNewAF = new LoanapplyTcNewAF();
    // String floorName = developerDAO.findfloorName(floorId);
    String floorName = floorId;
    List bankList = developerDAO.findBankListById(headid);// ��ѯ���� headid���۷���λ������
    String bankname = "";
    for (int i = 0; i < bankList.size(); i++) {
      BankListDTO bdto = (BankListDTO) bankList.get(i);
      if (bdto.getBankKey().equals(developerPaybankAAcc)) {
        bankname = bdto.getBankValue();
      }
    }
    loanapplyTcNewAF.setDeveloperPaybank(bankname);
    loanapplyTcNewAF.setFloorId(floorName);
    return loanapplyTcNewAF;
  }

  public String findOrgRate(String orgid) throws BusinessException {
    // TODO Auto-generated method stub
    String orgRate = empDAO.findOrgRate(orgid);// ��ѯְ�����������֤
    return orgRate;
  }

  public String findEmpRate(String orgid) throws BusinessException {
    // TODO Auto-generated method stub
    String orgRate = empDAO.findOrgRate(orgid);// ��ѯְ�����������֤
    return orgRate;
  }

  public String findFloorIdByCriterions(String developerId, String floorName,
      String floorNum) throws BusinessException {
    String floorId = "";
    try {
      floorId = developProjectDAO.queryFloorIdByCriterion(developerId,
          floorName, floorNum);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return floorId;
  }

  /**
   * ��ѯ��ְ���Ƿ����״̬Ϊ�����еļ�¼(���������ֱ���׳��쳣)
   * 
   * @param empName
   * @param cardNum
   * @throws BusinessException
   */
  public void findEmpIsPayingback(String empName, String cardNum)
      throws BusinessException {
    try {
      String empEleven = empDAO.queryempLoanIsEleven(empName, cardNum);
      if (Integer.parseInt(empEleven) > 0) {
        throw new BusinessException("��ְ�����ں�ͬ״̬Ϊ�����еĴ���");
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      throw be;
    }
  }

  /**
   * ����pl110��is_print_apply�Ƿ��Ѵ�ӡ
   */
  public void updatePrintStatus(String contractId) throws BusinessException {
    Borrower borrower = borrowerDAO.queryById(contractId);
    borrower.setIsPrintApply("1");
  }

  /**
   * ����pl110��re_date(�ؼ�����Ϊҵ������)
   */
  public void updateRedate(String contractId, SecurityInfo securityInfo)
      throws BusinessException {
    Borrower borrower = borrowerDAO.queryById(contractId);
    borrower.setRedate(securityInfo.getUserInfo().getPlbizDate());
  }

  /**
   * �Ƿ�˫�����й�����
   * 
   * @param contractId
   * @return true:������һ���� false:˫����û��
   */
  public boolean isBothHaveGjj(String contractId, SecurityInfo securityInfo) throws BusinessException {
    boolean b = true;
    int flag = 0;// ��ʶ�жϸ���������б����Ƿ����empidΪ�յ�(Ĭ��Ϊ0)
    int flag_1 = 0;// ��ʶ�жϽ���˺͸��������������һ�˽ɴ湫����Ҫ����6����(Ĭ��Ϊ0)
    int flag_2 = 0;// ��ʶ�жϽ���˺͸��������������һ�˿���ʱ����ڣ�����(Ĭ��Ϊ0)
    int flag_3 = 0;// ��ʶ�жϽ���˺͸��������������һ��Ƿ��С�ڣ�����(Ĭ��Ϊ0)
    String privilegeBorrowerId = borrowerDAO
    .findPrivilegeBorrowerIdByContractid(contractId);// �Ƿ���������
    int monthi2;
    int monthi3;
    int monthi4;
    int lianxuJiao = 0;
    int openMonth = 0;
    int qianJiao = 0;
    String plbizdate = securityInfo.getUserInfo().getPlbizDate();
    Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contractId);
    String office = borrower.getOffice();
    String empid = "";
    if (borrower.getEmpId() == null) {
      empid = "0";
    } else {
      empid = borrower.getEmpId().toString();
    }
    String orgid = "";
    if (borrower.getOrgId() == null) {
      orgid = "0";
    } else {
      orgid = borrower.getOrgId().toString();
    }
    
    // �����������������Ӧ����?����
    LoanConditionsParamSetDTO loanConditionsParamSetDTO2 = empDAO
        .querySyscollectionMonth(office);
    LoanConditionsParamSetDTO loanConditionsParamSetDTO3 = empDAO
        .querySyscollectionOpenAccMonth(office);
    
    LoanConditionsParamSetDTO loanConditionsParamSetDTO6 = empDAO
    .querySyscollectionOpenAccMonth_wsh(office);
    if(loanConditionsParamSetDTO2 != null
        && loanConditionsParamSetDTO2.getParamExplain() != null){
      monthi2 = Integer
      .parseInt(loanConditionsParamSetDTO2.getParamExplain());
    }else{
       monthi2=0;
    }
    if(loanConditionsParamSetDTO3 != null
        && loanConditionsParamSetDTO3.getParamExplain() != null){
      monthi3 = Integer
      .parseInt(loanConditionsParamSetDTO3.getParamExplain());
    }else{
       monthi3=0;
    }
    if(loanConditionsParamSetDTO6 != null
        && loanConditionsParamSetDTO6.getParamExplain() != null){
      monthi4 = Integer
      .parseInt(loanConditionsParamSetDTO6.getParamExplain());
    }else{
       monthi4=0;
    }
    
    if ((!empid.equals("") && !empid.equals("0"))
        && ((!orgid.equals("") && !orgid.equals("0")))) {// ˵���Ǵӹ������������
      String opendate = empDAO.findEmpInfoOpenDate_ws(empid, orgid);
      String orgPayMonth = empDAO.findEmpInfoOpenDate_wsh(empid, orgid);
      String empPayMonth = empDAO.findEmpInfoOpenDate_wsh_emp(empid, orgid);
      String month_1 = "";
      if (Integer.parseInt(orgPayMonth) - Integer.parseInt(empPayMonth) > 0) {
        month_1 = empPayMonth;
      } else {
        month_1 = orgPayMonth;
      }
      try {
        lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6), month_1);
        openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
            plbizdate.substring(0, 6));// �������ٸ���
        qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
            plbizdate.substring(0, 6));// Ƿ�ɶ��ٸ���
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if (loanConditionsParamSetDTO2 != null
          && loanConditionsParamSetDTO2.getParamExplain() != null) {// ˵����������Ч
        // //�����������,���صĶԴ�����Ƿ���ϲ�������
        if (lianxuJiao > monthi2) {
          flag_1 = 1;
        }
      }
      if (loanConditionsParamSetDTO3 != null
          && loanConditionsParamSetDTO3.getParamExplain() != null) {// ˵����������Ч
        // //�����������,���صĶԴ�����Ƿ���ϲ�������
        if (openMonth > monthi3) {
          flag_2 = 1;
        }
      }
      if (loanConditionsParamSetDTO6 != null
          && loanConditionsParamSetDTO6.getParamExplain() != null) {// ˵����������Ч
        // //�����������,���صĶԴ�����Ƿ���ϲ�������
        if (qianJiao < monthi4) {
          flag_3 = 1;
        }
      }
    }
    List list = assistantBorrowerDAO
        .findAssistanBorrowerListByContractid(contractId);
    if (flag_1 == 0||flag_2 == 0||flag_3 == 0) {
      for (int i = 0; i < list.size(); i++) {
        AssistantBorrowerDTO dto = (AssistantBorrowerDTO) list.get(i);
        if (dto.getEmpid() == null) {
          empid = "0";
        } else {
          empid = dto.getEmpid().toString();
        }
        if (dto.getOrgid() == null) {
          orgid = "0";
        } else {
          orgid = dto.getOrgid().toString();
        }
        if ((!empid.equals("") && !empid.equals("0"))
            && ((!orgid.equals("") && !orgid.equals("0")))) {// ˵���Ǵӹ������������
          String opendate = empDAO.findEmpInfoOpenDate_ws(empid, orgid);
          String orgPayMonth = empDAO.findEmpInfoOpenDate_wsh(empid, orgid);
          String empPayMonth = empDAO.findEmpInfoOpenDate_wsh_emp(empid, orgid);
          String month_1 = "";
          if (Integer.parseInt(orgPayMonth) - Integer.parseInt(empPayMonth) > 0) {
            month_1 = empPayMonth;
          } else {
            month_1 = orgPayMonth;
          }
          try {
            lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6),
                month_1);
            openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
                plbizdate.substring(0, 6));// �������ٸ���
            qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
                plbizdate.substring(0, 6));// Ƿ�ɶ��ٸ���
          } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }

          // //�����������,���صĶԴ�����Ƿ���ϲ�������
          if (lianxuJiao > monthi2) {
            flag_1 = 1;
          }
          if (openMonth > monthi3) {
            flag_2 = 1;
          }
          if (qianJiao < monthi4) {
            flag_3 = 1;
          }
        }
      }
    }
    if (flag_1 == 0&& privilegeBorrowerId == null&&loanConditionsParamSetDTO2!=null&&loanConditionsParamSetDTO2.getParamExplain()!=null&&!"1".equals(borrower.getLoanType())) {
      throw new BusinessException("����˫��������һ�˹����������������Ӧ����" + monthi2 + "��");
    }
    if (flag_2 == 0&& privilegeBorrowerId == null&&loanConditionsParamSetDTO3!=null&&loanConditionsParamSetDTO3.getParamExplain()!=null&&!"1".equals(borrower.getLoanType())) {
      throw new BusinessException("����˫��������һ�˹����𿪻�����Ӧ����" + monthi3 + "��");
    }
    if (flag_3 == 0&& privilegeBorrowerId == null&&loanConditionsParamSetDTO6!=null&&loanConditionsParamSetDTO6.getParamExplain()!=null&&!"1".equals(borrower.getLoanType())) {
      throw new BusinessException("����˫��������һ�˹�����Ƿ������ӦС��" + monthi4 + "��");
    }
    for (int i = 0; i < list.size(); i++) {
      AssistantBorrowerDTO dto = (AssistantBorrowerDTO) list.get(i);
      if (dto.getEmpid() != null) {
        flag = 1;
        break;
      }
    }
    // ��ش���������Ƿ����˫����Ӫ�ڶ��ֹ�����
    if (borrower.getLoanType() != null && borrower.getLoanType().equals("1")) {
      flag = 1;
    }
    if (privilegeBorrowerId == null && borrower.getEmpId() == null && flag == 0) {
      b = false;
      throw new BusinessException("����˫������һ���й�����!");
    }
    return b;
  }
  private LoanConditionsSetDAO loanConditionsSetDAO = null;

  public void setLoanConditionsSetDAO(LoanConditionsSetDAO loanConditionsSetDAO) {
    this.loanConditionsSetDAO = loanConditionsSetDAO;
  }
}
