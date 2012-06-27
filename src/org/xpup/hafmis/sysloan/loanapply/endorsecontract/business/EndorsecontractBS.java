package org.xpup.hafmis.sysloan.loanapply.endorsecontract.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.AssurerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;

import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.ImpawnContractDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PledgeContractDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.common.domain.entity.Assurer;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.ImpawnContract;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContract;

import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PledgeContract;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTeDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaChangeLoanMonthRateAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTeAF;
import org.xpup.security.common.domain.Userslogincollbank;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;

public class EndorsecontractBS implements IEndorsecontractBS {

  private LoanBankParaDAO loanBankParaDAO = null;// PL003

  private BorrowerDAO borrowerDAO = null;// PL110

  private BorrowerAccDAO borrowerAccDAO = null;// PL111

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;// PL115

  private LoanContractDAO loanContractDAO = null;// PL120

  private PlOperateLogDAO plOperateLogDAO = null;// PL021

  private CollBankDAO collBankDAO = null;// BB105

  private PledgeContractDAO pledgeContractDAO = null;// pl121

  private AssistantOrgDAO assistantOrgDAO = null;// pl007 ������˾����

  private HousesDAO housesDAO = null;// pl114 ������Ϣ

  private ImpawnContractDAO impawnContractDAO = null;// PL122

  private AssurerDAO assurerDAO = null;// PL123

  private EmpDAO empDAO = null;// AA002

  private LoanBankDAO loanBankDAO = null;

  private LoanRateDAO loanRateDAO = null; // pl001

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setAssurerDAO(AssurerDAO assurerDAO) {
    this.assurerDAO = assurerDAO;
  }

  public void setImpawnContractDAO(ImpawnContractDAO impawnContractDAO) {
    this.impawnContractDAO = impawnContractDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setPledgeContractDAO(PledgeContractDAO pledgeContractDAO) {
    this.pledgeContractDAO = pledgeContractDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setLoanContractDAO(LoanContractDAO loanContractDAO) {
    this.loanContractDAO = loanContractDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /** ****************************************************************** */
  /** **** ����ͬ��Ϣ **** */
  /** ****************************************************************** */
  /**
   * ��ѯ���´������������� 'AB' or 'BA'
   */
  public String queryParamValue(SecurityInfo securityInfo) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    return loanBankParaDAO.queryParamvalueYU();
  }

  /**
   * ��ѯPL003�в��������ֶ�Ϊ"��������"�ļ�¼�еĲ���ֵ
   * 
   * @param id
   * @param securityInfo
   * @return
   */
  // public String queryParamValue_() {
  // return loanBankParaDAO.queryParamvalueYU_();
  // }
  /**
   * �ж�PL120���Ƿ���ڴ���ĺ�ͬ���(Ta)
   * 
   * @param id
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void check(String id, HttpServletRequest request) throws Exception,
      BusinessException {

    request.getSession().setAttribute("contractId", null);// ���session�к�ͬ���
    String contractId = loanContractDAO.queryByIdYU(id);
    if (contractId != null) {
      throw new BusinessException("�ú�ͬ�Ѿ����ڣ�");
    } else {
      request.getSession().setAttribute("contractId", id);
    }
  }
  /**
   * �ж�PL121���Ƿ���ڴ���ĺ�ͬ���(Ta)״̬Ϊ0
   * 
   * @param id
   * @throws Exception
   * @throws BusinessException
   */
  public String queryPL121Contract(String contractid) throws Exception,BusinessException {
    return loanContractDAO.queryById_yg(contractid);
  }

  /**
   * �ж�PL120���Ƿ���ڴ���ĺ�ͬ���(Tb)
   * 
   * @param id
   * @throws Exception
   * @throws BusinessException
   */
  public void checkPL120(String id) throws Exception, BusinessException {
    String contractId = loanContractDAO.queryByIdYU(id);
    if (contractId != null) {
      List list = this.checkPL121(id);//

    } else {
      throw new BusinessException("����¼�����ͬ��");
    }
  }

  /**
   * �ж�PL121���Ƿ���ڴ���ĺ�ͬ���(Tb)
   * 
   * @param id
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List checkPL121(String id) throws Exception, BusinessException {
    List list = pledgeContractDAO.queryIdByContractIdYU(id);
    return list;
  }

  /**
   * �ж�PL122���Ƿ���ڴ���ĺ�ͬ���(Tc)
   * 
   * @return
   * @throws Excetption
   * @throws BusinessException
   */
  public List checkPL122(String id) throws Exception, BusinessException {
    List list = impawnContractDAO.queryIdByContractIdYU(id);
    return list;
  }

  /**
   * �ж�PL123���Ƿ���ڴ���ĺ�ͬ���(Td)
   * 
   * @param id
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List checkPL123(String id) throws Exception, BusinessException {
    List list = assurerDAO.queryIdByContractIdYU(id);
    return list;
  }

  /**
   * ��Ȩ���л�����ģ���ѺȨ�ˣ�ί�з���
   * 
   * @param securityInfo
   * @return
   */
  public String getCenter(SecurityInfo securityInfo) {
    String center = "";
    OfficeDto officeDto = new OfficeDto();
    List list = securityInfo.getAllCenterList();
    if (list != null) {
      officeDto = (OfficeDto) list.get(0);
      center = officeDto.getOfficeName();
    }
    return center;
  }

  /**
   * ҳ����ʾ�Ĳ�ѯ
   */
  public EndorsecontractTaAF queryContractInfo(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request, String insert) throws Exception,
      BusinessException {
    String tempmonthInterest = "";
    // TODO Auto-generated method stub
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    // EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    if (id != null && !"".equals(id)) {
      request.getSession().setAttribute("contractId", id);
      String entruster = this.getCenter(securityInfo);// ί�з�**����

      EndorsecontractTaDTO endorsecontractTaDTOPL110 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOpl111 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOpl115 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOpl120 = new EndorsecontractTaDTO();

      // ��ѯPL110
      endorsecontractTaDTOPL110 = borrowerDAO.queryBorrowerInfoYU(id,
          securityInfo);
      String debitter = endorsecontractTaDTOPL110.getDebitter();// �����
      String certificateType = endorsecontractTaDTOPL110.getCertificateType();// ֤������
      String certificateNum = endorsecontractTaDTOPL110.getCertificateNum();// ֤������

      // ��ѯPL111
      endorsecontractTaDTOpl111 = borrowerAccDAO.queryBorrowerAccInfoYU(id,
          securityInfo);
      String beentruster = endorsecontractTaDTOpl111.getBeentruster();//
      // ���з����ſ�����ID��
      // String debitMoney = endorsecontractTaDTOpl111.getDebitMoney();// �����
      // String term = endorsecontractTaDTOpl111.getTerm();// �������
      String isWrite = endorsecontractTaDTOpl111.getIsWrite();// �Ƿ�ǩ����ͬ
      String contractSt = endorsecontractTaDTOpl111.getContractSt();// ��ͬ״̬
      // String beentrusterName = this.queryBankName(beentruster);// ���з�����

      // ��ѯPL115
      endorsecontractTaDTOpl115 = borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
          id, securityInfo);
      tempmonthInterest = endorsecontractTaDTOpl115.getMonthInterest();// ÿ������
      BigDecimal hundred = new BigDecimal(100);
      BigDecimal tempBig = new BigDecimal(0.00);
      if (tempmonthInterest != null && !"".equals(tempmonthInterest)) {
        tempBig = new BigDecimal(tempmonthInterest).multiply(hundred);
      }
      String monthInterest = tempBig.toString() + "%";
      String creditType = endorsecontractTaDTOpl115.getCreditType();// ���ʽ
      String debitMoney = endorsecontractTaDTOpl115.getDebitMoney();// �����
      String term = endorsecontractTaDTOpl115.getTerm();// �������
      String corpusInterest = endorsecontractTaDTOpl115.getCorpusInterest();// �»���Ϣ
      String tempCreditType = "";
      if (creditType != null && !"".equals(creditType)) {
        tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
            + creditType), BusiConst.PLRECOVERTYPE);
      }

      // �����ж�
      String paramValue = this.queryParamValue(securityInfo);
      if (insert == null) {// �ж��Ƿ�Ϊ���ȷ����ť�����жϣ��ǣ����ж�
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.����ͨ�� 0 δǩ����ͬ
            this.check(id, request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        } else if ("BA".equals(paramValue)) {
          if ("15".equals(contractSt) && "0".equals(isWrite)) {
            this.check(id, request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        }
      }

      // ��ѯPL120
      endorsecontractTaDTOpl120 = loanContractDAO.queryLoanContractInfoYU(id,
          securityInfo);
      String assurer = "";
      String photoUrl = "";
      if (endorsecontractTaDTOpl120 != null
          && !"".equals(endorsecontractTaDTOpl120)) {
        assurer = endorsecontractTaDTOpl120.getAssurer();// ��֤��
        photoUrl = endorsecontractTaDTOpl120.getPhotoUrl();// ·��
      }
      // String contractSureDate =
      // endorsecontractTaDTOpl120.getContractSureDate();// ��ͬǩ������
      // String debitMoneyStaDate = endorsecontractTaDTOpl120
      // .getDebitMoneyStaDate();// �����ʼ����
      // String assistantOrgId =
      // endorsecontractTaDTOpl120.getAssistantOrgId();//������˾����

      // String assurer = "";// ��֤��
      String tempcontractSureDate = BusiTools.dateToString(new Date(),
          "yyyy-MM-dd");// ��ͬǩ������
      String contractSureDate = tempcontractSureDate.substring(0, 4)
          + tempcontractSureDate.substring(5, 7)
          + tempcontractSureDate.substring(8, 10);
      String tempdebitMoneyStaDate = BusiTools.dateToString(new Date(),
          "yyyy-MM-dd");// ��ͬǩ������
      String debitMoneyStaDate = tempdebitMoneyStaDate.substring(0, 4)
          + tempdebitMoneyStaDate.substring(5, 7)
          + tempdebitMoneyStaDate.substring(8, 10);// �����ʼ����
      String tempAsistantOrgId = endorsecontractTaDTOpl120.getAssistantOrgId();
      String assistantOrgId = "";
      if (tempAsistantOrgId != null && !"".equals(tempAsistantOrgId)) {
        assistantOrgId = assistantOrgDAO.queryOrgName_(tempAsistantOrgId);// ������˾����
      }
      // ���ݽ����ʼ���ڣ�������޼��㻹����ֹ����
      String debitMoneyEndDate = "";
      String postfix = "";
      if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          && term != null && !"".equals(term)) {
        postfix = debitMoneyStaDate.substring(6, 8);
        int tempTerm = new Integer(term).intValue();
        debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate, tempTerm);
        term = String.valueOf(Integer.parseInt(term) / 12);
      }
      // ��ѯPL114 ����֤��ţ����ݵ�ַ���������ͣ�01����Ʒ�� 02�����ַ�
      Houses houses = housesDAO.queryById(id);
      String copyrightCode = "";
      String address = "";
      String houseType = "";
      String bargainorName = "";
      String headId = "";
      if (houses != null) {
        copyrightCode = houses.getBargainorHousepropertyCode();
        address = houses.getHouseAddr();
        houseType = houses.getHouseType();
        bargainorName = houses.getBargainorName();
        headId = houses.getHeadId();// ������ͷ��ID
        if ("01".equals(houseType)) {// �������� ��01 ��Ʒ�� ��֤��Ϊ������
          // ���¿�����ͷ���ѯ PL005 ����������
          if (headId != null) {
            assurer = housesDAO.queryDevelper(headId);
          }
        } else {// �������� ��02 ���ַ� ��֤��Ϊ��
          assurer = "";
        }
      }
      /** ----------------------------------------------- */
      // if (isWrite != null && !"".equals(isWrite)) {
      // if ("0".equals(isWrite)) {
      // endorsecontractTaAF.setWriteType("0");//
      // �ж��Ƿ�ǩ������0δǩ��������֤��,�ſ����У���ͬǩ�����ڣ������ʼ���ڿ��޸ģ�����ֻ��
      // } else {// ����1��ǩ����ȫ��ֻ����ȷ����ť����
      // endorsecontractTaAF.setWriteType("1");
      // }
      // }
      endorsecontractTaAF.setLoanKouAcc(endorsecontractTaDTOpl111.getLoanKouAcc());// ҳ����ʾ�ۿ��˺�
      endorsecontractTaAF.setEntruster(entruster);// ҳ����ʾ����ί�з�**����
      endorsecontractTaAF.setContractId(id);// ҳ����ʾ������ͬ���
      endorsecontractTaAF.setDebitter(debitter);// ҳ����ʾ���������
      endorsecontractTaAF.setCertificateType(BusiTools.getBusiValue(Integer
          .parseInt(certificateType), BusiConst.DOCUMENTSSTATE));// ҳ����ʾ����֤������
      endorsecontractTaAF.setCertificateNum(certificateNum);// ҳ����ʾ����֤������
      endorsecontractTaAF.setBeentruster(beentruster);// ҳ����ʾ�������з������У�
      endorsecontractTaAF.setDebitMoney(debitMoney);// ҳ����ʾ���������
      endorsecontractTaAF.setTerm(term);// ҳ����ʾ�����������
      endorsecontractTaAF.setMonthInterest(monthInterest);// ҳ����ʾ����ÿ������
      endorsecontractTaAF.setCreditType(tempCreditType);// ҳ����ʾ�������ʽ
      endorsecontractTaAF.setDebitMoneyStaDate(debitMoneyStaDate);// ҳ����ʾ���������ʼ����
      endorsecontractTaAF.setContractSureDate(contractSureDate);// ҳ����ʾ������ͬǩ������
      endorsecontractTaAF.setAssurer(assurer);// ҳ����ʾ������֤��
      endorsecontractTaAF.setDebitMoneyEndDate(debitMoneyEndDate + postfix);// ҳ����ʾ����������ֹ����
      endorsecontractTaAF.setAssistantOrgId(assistantOrgId);
      endorsecontractTaAF.setPhotoUrl(photoUrl);// ·��
      endorsecontractTaAF.setRealMonthInt(tempmonthInterest);
      endorsecontractTaAF.setHiddenloanMode(creditType);// ʯ���û��ʽ��INT���ͣ�
      endorsecontractTaAF.setWriteType("0");
      endorsecontractTaAF.setCorpusInterest(corpusInterest);

      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(id);
      String contractSthl = borrowerAcc.getContractSt();// ��ͬ״̬

      String paramv = loanBankParaDAO.queryParam_value_hanl();
      String contractidhl = loanContractDAO.queryByIdYU(id);
      if (paramv.equals("BA") && contractidhl == null
          && contractSthl.equals("2")) {
        endorsecontractTaAF.setIscontactid("1");// ����ҳ���ϵĲ�����ǩ����ť���� ��ʾ
      } else {
        endorsecontractTaAF.setIscontactid("2");// ����ҳ���ϵĲ�����ǩ����ť����ʾ
      }

      /** ----------------------------------------------- */
    }
    return endorsecontractTaAF;
  }

  // ��ӡ�͵�����Ĳ�ѯ(ά������)
  public EndorsecontractTaAF queryContractInfo_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String tempmonthInterest = "";
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    if (id != null && !"".equals(id)) {
      request.getSession().setAttribute("contractId", id);
      request.getSession().setAttribute("comeFromType", "yes");
      String entruster = this.getCenter(securityInfo);// ί�з�**����

      EndorsecontractTaDTO endorsecontractTaDTOPL110 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL115 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL120 = new EndorsecontractTaDTO();

      // ��ѯPL110
      endorsecontractTaDTOPL110 = borrowerDAO.queryBorrowerInfoYU(id,
          securityInfo);
      String debitter = endorsecontractTaDTOPL110.getDebitter();// �����
      String certificateType = endorsecontractTaDTOPL110.getCertificateType();// ֤������
      String certificateNum = endorsecontractTaDTOPL110.getCertificateNum();// ֤������

      // ��ѯPL111
      endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
          securityInfo);
      String beentruster = endorsecontractTaDTO.getBeentruster();// ���з����ſ�����ID��
      String bankName = this.queryBankName(beentruster);
      // String debitMoney = endorsecontractTaDTO.getDebitMoney();// �����
      // String term = endorsecontractTaDTO.getTerm();// �������
      String isWrite = endorsecontractTaDTO.getIsWrite();// �Ƿ�ǩ����ͬ

      // ��ѯPL115
      // endorsecontractTaDTOPL115 =
      // borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
      // id, securityInfo);
      // String monthInterest = endorsecontractTaDTOPL115.getMonthInterest();//
      // ÿ������
      // String creditType = endorsecontractTaDTOPL115.getCreditType();// ���ʽ
      // String tempCreditType = "";
      // if (creditType != null && !"".equals(creditType)) {
      // tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
      // + creditType), BusiConst.PLRECOVERTYPE);
      // }
      endorsecontractTaDTOPL115 = borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
          id, securityInfo);
      tempmonthInterest = endorsecontractTaDTOPL115.getMonthInterest();// ÿ������
      BigDecimal hundred = new BigDecimal(100);
      BigDecimal temBig = new BigDecimal(0.00);
      if (tempmonthInterest != null && !"".equals(tempmonthInterest)) {
        temBig = new BigDecimal(tempmonthInterest).multiply(hundred);
      }
      String monthInterest = temBig.toString() + "%";
      String creditType = endorsecontractTaDTOPL115.getCreditType();// ���ʽ
      String debitMoney = endorsecontractTaDTOPL115.getDebitMoney();// �����
      String term = endorsecontractTaDTOPL115.getTerm();// �������
      String corpusInterest = endorsecontractTaDTOPL115.getCorpusInterest();// �»���Ϣ
      String tempCreditType = "";
      if (creditType != null && !"".equals(creditType)) {
        tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
            + creditType), BusiConst.PLRECOVERTYPE);
      }
      // ��ѯPL120
      endorsecontractTaDTOPL120 = loanContractDAO.queryLoanContractInfoYU(id,
          securityInfo);
      // String assurer = endorsecontractTaDTOPL120.getAssurer();// ��֤��
      String photoUrl = endorsecontractTaDTOPL120.getPhotoUrl();// ·��
      String contractSureDate = endorsecontractTaDTOPL120.getContractSureDate();// ��ͬǩ������
      String debitMoneyStaDate = endorsecontractTaDTOPL120
          .getDebitMoneyStaDate();// �����ʼ����
      String assistantOrgId = endorsecontractTaDTOPL120.getAssistantOrgId();// ������˾id
      String assistantOrgName = assistantOrgDAO.queryOrgName_(assistantOrgId);// ������˾����
      // ���ݽ����ʼ���ڣ�������޼��㻹����ֹ����
      String debitMoneyEndDate = "";
      String postfix = "";
      if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          && term != null && !"".equals(term)) {
        postfix = debitMoneyStaDate.substring(6, 8);
        int tempTerm = new Integer(term).intValue();
        debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate, tempTerm);
        term = String.valueOf(Integer.parseInt(term) / 12);
      }

      /** ----------------------------------------------- */
      if (isWrite != null && !"".equals(isWrite)) {
        if ("0".equals(isWrite)) {
          endorsecontractTaAF.setIsview("0");// �ж��Ƿ�ǩ������0δǩ��������֤��,�ſ����У���ͬǩ�����ڣ������ʼ���ڿ��޸ģ�����ֻ��
        } else {// ����1��ǩ����ȫ��ֻ����ȷ����ť����
          endorsecontractTaAF.setIsview("1");
        }
      }
      // ��ѯPL114 ����֤��ţ����ݵ�ַ���������ͣ�01����Ʒ�� 02�����ַ�
      Houses houses = housesDAO.queryById(id);
      String copyrightCode = "";
      String address = "";
      String houseType = "";
      String bargainorName = "";
      String assurer = "";
      String headId = "";
      if (houses != null) {
        copyrightCode = houses.getBargainorHousepropertyCode();
        address = houses.getHouseAddr();
        houseType = houses.getHouseType();
        bargainorName = houses.getBargainorName();
        headId = houses.getHeadId();// ������ͷ��ID
        if ("01".equals(houseType)) {// �������� ��01 ��Ʒ�� ��֤��Ϊ������
          // ���¿�����ͷ���ѯ PL005 ����������
          if (headId != null) {
            assurer = housesDAO.queryDevelper(headId);
          }
        } else {// �������� ��02 ���ַ� ��֤��Ϊ��
          assurer = "";
        }
      }
      // ��Ұ�޸ģ�8��ҳ��ĵ�����-����ͬ��Ϣ����ѯ֮ǰҪ�ж��Ƿ�ǩ����ͬ�����δǩ��������ʾ��Ϣ
      // if (isWrite != null && !"".equals(isWrite)) {
      // if ("1".equals(isWrite)) {
      
      endorsecontractTaAF.setEntruster(entruster);// ҳ����ʾ����ί�з�**����
      endorsecontractTaAF.setContractId(id);// ҳ����ʾ������ͬ���
      endorsecontractTaAF.setDebitter(debitter);// ҳ����ʾ���������
      if (certificateType != null && !"".equals(certificateType)) {
        endorsecontractTaAF.setCertificateType(BusiTools.getBusiValue(Integer
            .parseInt("" + certificateType), BusiConst.DOCUMENTSSTATE));// ҳ����ʾ����֤������
      }
      
      endorsecontractTaAF.setLoanKouAcc(endorsecontractTaDTO.getLoanKouAcc());// ҳ����ʾ�ۿ��˺�
      endorsecontractTaAF.setCertificateNum(certificateNum);// ҳ����ʾ����֤������
      // endorsecontractTaAF.setBeentruster(beentrusterName);// ҳ����ʾ�������з������У�
      endorsecontractTaAF.setBeentruster(beentruster);
      endorsecontractTaAF.setTemp_beentruster(beentruster);// ҳ����ʾ�������з������У�
      // endorsecontractTaAF.setBeentruster(bankName);// ҳ����ʾ�������з������У�
      endorsecontractTaAF.setDebitMoney(debitMoney);// ҳ����ʾ���������
      endorsecontractTaAF.setTerm(term);// ҳ����ʾ�����������
      endorsecontractTaAF.setMonthInterest(monthInterest);// ҳ����ʾ����ÿ������
      endorsecontractTaAF.setCreditType(tempCreditType);// ҳ����ʾ�������ʽ
      endorsecontractTaAF.setDebitMoneyStaDate(debitMoneyStaDate);// ҳ����ʾ���������ʼ����
      endorsecontractTaAF.setContractSureDate(contractSureDate);// ҳ����ʾ������ͬǩ������
      endorsecontractTaAF.setAssurer(assurer);// ҳ����ʾ������֤��
      endorsecontractTaAF.setPhotoUrl(photoUrl);// ·��
      endorsecontractTaAF.setDebitMoneyEndDate(debitMoneyEndDate + postfix);// ҳ����ʾ����������ֹ����
      endorsecontractTaAF.setAssistantOrgId(assistantOrgName);
      endorsecontractTaAF.setBankName(bankName);
      endorsecontractTaAF.setCorpusInterest(corpusInterest);
      endorsecontractTaAF.setRealMonthInt(tempmonthInterest);
      endorsecontractTaAF.setHiddenloanMode(creditType);// ʯ���û��ʽ��INT���ͣ�
    }
    // }
    /** ----------------------------------------------- */
    // }
    return endorsecontractTaAF;
  }

  // ��Ұר�� 8-������
  public EndorsecontractTaAF queryContractInfo_wy(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String tempmonthInterest = "";
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    if (id != null && !"".equals(id)) {
      request.getSession().setAttribute("contractId", id);
      request.getSession().setAttribute("comeFromType", "yes");
      String entruster = this.getCenter(securityInfo);// ί�з�**����

      EndorsecontractTaDTO endorsecontractTaDTOPL110 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL115 = new EndorsecontractTaDTO();
      EndorsecontractTaDTO endorsecontractTaDTOPL120 = new EndorsecontractTaDTO();

      // ��ѯPL110
      endorsecontractTaDTOPL110 = borrowerDAO.queryBorrowerInfoYU(id,
          securityInfo);
      String debitter = endorsecontractTaDTOPL110.getDebitter();// �����
      String certificateType = endorsecontractTaDTOPL110.getCertificateType();// ֤������
      String certificateNum = endorsecontractTaDTOPL110.getCertificateNum();// ֤������

      // ��ѯPL111
      endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
          securityInfo);
      String beentruster = endorsecontractTaDTO.getBeentruster();// ���з����ſ�����ID��
      String loanKouAcc = endorsecontractTaDTO.getLoanKouAcc();
      String bankName = this.queryBankName(beentruster);
      // String debitMoney = endorsecontractTaDTO.getDebitMoney();// �����
      // String term = endorsecontractTaDTO.getTerm();// �������
      String isWrite = endorsecontractTaDTO.getIsWrite();// �Ƿ�ǩ����ͬ
      String contractSt = endorsecontractTaDTO.getContractSt();// ��ͬ״̬
      String beentrusterName = this.queryBankName(beentruster);// ���з�����

      // ��ѯPL115
      // endorsecontractTaDTOPL115 =
      // borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
      // id, securityInfo);
      // String monthInterest = endorsecontractTaDTOPL115.getMonthInterest();//
      // ÿ������
      // String creditType = endorsecontractTaDTOPL115.getCreditType();// ���ʽ
      // String tempCreditType = "";
      // if (creditType != null && !"".equals(creditType)) {
      // tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
      // + creditType), BusiConst.PLRECOVERTYPE);
      // }
      endorsecontractTaDTOPL115 = borrowerLoanInfoDAO.queryBorrowerLoanInfoYU(
          id, securityInfo);
      tempmonthInterest = endorsecontractTaDTOPL115.getMonthInterest();// ÿ������
      BigDecimal hundred = new BigDecimal(100);
      BigDecimal temBig = new BigDecimal(0.00);
      if (tempmonthInterest != null && !"".equals(tempmonthInterest)) {
        temBig = new BigDecimal(tempmonthInterest).multiply(hundred);
      }
      String monthInterest = temBig.toString() + "%";
      String creditType = endorsecontractTaDTOPL115.getCreditType();// ���ʽ
      String debitMoney = endorsecontractTaDTOPL115.getDebitMoney();// �����
      String term = endorsecontractTaDTOPL115.getTerm();// �������
      String tempCreditType = "";
      if (creditType != null && !"".equals(creditType)) {
        tempCreditType = BusiTools.getBusiValue(Integer.parseInt(""
            + creditType), BusiConst.PLRECOVERTYPE);
      }
      // ��ѯPL120
      endorsecontractTaDTOPL120 = loanContractDAO.queryLoanContractInfoYU(id,
          securityInfo);
      String assurer = endorsecontractTaDTOPL120.getAssurer();// ��֤��
      String contractSureDate = endorsecontractTaDTOPL120.getContractSureDate();// ��ͬǩ������
      String debitMoneyStaDate = endorsecontractTaDTOPL120
          .getDebitMoneyStaDate();// �����ʼ����
      String photoUrl = endorsecontractTaDTOPL120.getPhotoUrl();
      String assistantOrgId = endorsecontractTaDTOPL120.getAssistantOrgId();// ������˾id
      String assistantOrgName = assistantOrgDAO.queryOrgName_(assistantOrgId);// ������˾����

      // ���ݽ����ʼ���ڣ�������޼��㻹����ֹ����
      String debitMoneyEndDate = "";
      String postfix = "";
      if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          && term != null && !"".equals(term)) {
        postfix = debitMoneyStaDate.substring(6, 8);
        int tempTerm = new Integer(term).intValue();
        debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate, tempTerm);
      }

      /** ----------------------------------------------- */
      if (isWrite != null && !"".equals(isWrite)) {
        if ("0".equals(isWrite)) {
          endorsecontractTaAF.setIsview("0");// �ж��Ƿ�ǩ������0δǩ��������֤��,�ſ����У���ͬǩ�����ڣ������ʼ���ڿ��޸ģ�����ֻ��
        } else {// ����1��ǩ����ȫ��ֻ����ȷ����ť����
          endorsecontractTaAF.setIsview("1");
        }
      }
      // ��Ұ�޸ģ�8��ҳ��ĵ�����-����ͬ��Ϣ����ѯ֮ǰҪ�ж��Ƿ�ǩ����ͬ�����δǩ��������ʾ��Ϣ
      if (isWrite != null && !"".equals(isWrite)) {
        if ("1".equals(isWrite)) {
          endorsecontractTaAF.setEntruster(entruster);// ҳ����ʾ����ί�з�**����
          endorsecontractTaAF.setContractId(id);// ҳ����ʾ������ͬ���
          endorsecontractTaAF.setDebitter(debitter);// ҳ����ʾ���������
          endorsecontractTaAF.setCertificateType(BusiTools.getBusiValue(Integer
              .parseInt("" + certificateType), BusiConst.DOCUMENTSSTATE));// ҳ����ʾ����֤������
          endorsecontractTaAF.setCertificateNum(certificateNum);// ҳ����ʾ����֤������
          endorsecontractTaAF.setBeentruster(beentrusterName);// ҳ����ʾ�������з������У�
          // endorsecontractTaAF.setBeentruster(bankName);// ҳ����ʾ�������з������У�
          endorsecontractTaAF.setDebitMoney(debitMoney);// ҳ����ʾ���������
          endorsecontractTaAF.setTerm(term);// ҳ����ʾ�����������
          endorsecontractTaAF.setMonthInterest(monthInterest);// ҳ����ʾ����ÿ������
          endorsecontractTaAF.setCreditType(tempCreditType);// ҳ����ʾ�������ʽ
          endorsecontractTaAF.setDebitMoneyStaDate(debitMoneyStaDate);// ҳ����ʾ���������ʼ����
          endorsecontractTaAF.setContractSureDate(contractSureDate);// ҳ����ʾ������ͬǩ������
          endorsecontractTaAF.setAssurer(assurer);// ҳ����ʾ������֤��
          endorsecontractTaAF.setDebitMoneyEndDate(debitMoneyEndDate + postfix);// ҳ����ʾ����������ֹ����
          endorsecontractTaAF.setAssistantOrgId(assistantOrgName);
          endorsecontractTaAF.setBankName(bankName);
          endorsecontractTaAF.setRealMonthInt(tempmonthInterest);
          endorsecontractTaAF.setPhotoUrl(photoUrl);
          endorsecontractTaAF.setLoanKouAcc(loanKouAcc);
        }
      }
      /** ----------------------------------------------- */
    }
    return endorsecontractTaAF;
  }

  /**
   * Taȷ�ϰ�ť
   */
  public void endorsecontractTaMaitainSure(String loanassistantorgId,
      EndorsecontractTaAF endorsecontractTaAF, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_LOANCONTRACT + "";
    
    String beentruster = endorsecontractTaAF.getBeentruster();// �ҷ�(����)
    String contractId = endorsecontractTaAF.getContractId();
    String assurer = endorsecontractTaAF.getAssurer();// ��֤��
    String contractSureDate = endorsecontractTaAF.getContractSureDate();// ��ͬǩ������
    String debitMoneyStaDate = endorsecontractTaAF.getDebitMoneyStaDate();// �����ʼ����
    String corpusInterest = endorsecontractTaAF.getCorpusInterest();// �»���Ϣ
    String loanMonthRate = endorsecontractTaAF.getRealMonthInt();// ���µ�����

    LoanContract loanContract = loanContractDAO.queryById(contractId);
    Integer count = borrowerAccDAO.findBorrowerAccByLoanKouAcc_sy(endorsecontractTaAF.getLoanKouAcc());
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
    if (borrowerAcc.getLoanKouAcc() == null
        || (borrowerAcc.getLoanKouAcc() != null && !borrowerAcc.getLoanKouAcc()
            .equals(endorsecontractTaAF.getLoanKouAcc()))) {
      if (count.intValue() > 0) {
        throw new BusinessException("�ۿ��˺���ʹ�ã�ȷ�Ϻ�ʵ�˺ţ�");
      }
    }
    if (loanContract != null) {// �ǣ�����
      loanContract.setAssurer(assurer);
      loanContract.setAgreementDate(contractSureDate);
      loanContract.setLoanStartDate(debitMoneyStaDate);
      loanContract.setAssistantOrgId(loanassistantorgId);
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = contractId;
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    } else {// �񣬲���
      LoanContract lContract = new LoanContract();
      lContract.setContractId(contractId);
      lContract.setAssurer(assurer);
      lContract.setAgreementDate(contractSureDate);
      lContract.setLoanStartDate(debitMoneyStaDate);
      lContract.setOperator(operator);
      lContract.setAssistantOrgId(loanassistantorgId);
      lContract.setOpTime(new Date());
      Serializable newContractId = loanContractDAO.insert(lContract);
      String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
      String bizId = newContractId.toString();
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    }
    // ����PL111������
    if (borrowerAcc != null && beentruster != null) {
      borrowerAcc.setLoanBankId(new BigDecimal(beentruster));
      borrowerAcc.setLoanKouAcc(endorsecontractTaAF.getLoanKouAcc());
    }
    // ����PL115�����ʣ��»���Ϣ
    BigDecimal tempCorpusInterest = new BigDecimal(0.00);
    if (corpusInterest != null && !corpusInterest.equals("")) {
      tempCorpusInterest = new BigDecimal(corpusInterest);
    } else {
      tempCorpusInterest = null;
    }
    borrowerLoanInfoDAO.updateBorrowerLoanInfo_sy(
        new BigDecimal(loanMonthRate), tempCorpusInterest, contractId);
  }

  /**
   * Taɨ�谴ť
   */
  public void endorsecontractTaMaitainScan(
      EndorsecontractTaAF endorsecontractTaAF, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub

  }

  /**
   * ���������־pl201
   * 
   * @param securityInfo
   * @return
   */
  public void addPlOperateLog(String opSys, String model, String button,
      String bizId, String opIp, String operator, String opBizId) {

    PlOperateLog plOperateLog = new PlOperateLog();
    try {
      plOperateLog.setOpSys(new BigDecimal(opSys));
      plOperateLog.setOpModel(model);
      plOperateLog.setOpButton(button);
      // if (opBizId != null && !"".equals(opBizId)) {
      // plOperateLog.setOpBizId(new BigDecimal(opBizId));
      // }
      plOperateLog.setOpIp(opIp);
      plOperateLog.setContractId(bizId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operator);
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ���ݷſ�����ID��ѯ��������
   * 
   * @param id
   * @return
   */
  public String queryBankName(String id) {
    String bankName = "";
    if (id != null && !id.equals("")) {
      try {
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(id);
        if (collBank != null) {
          bankName = collBank.getCollBankName();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return bankName;
  }

  /** ****************************************************************** */
  /** **** ��Ѻ��ͬ��Ϣ **** */
  /** ****************************************************************** */

  /**
   * ��ѯ120
   */

  public String queryPL120(String id) throws BusinessException {
    return loanContractDAO.queryByIdYU(id);
  }

  public EndorsecontractTbAF queryPledgeContractList(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    String debitter = "";
    String entruster = "";// ί�з�**����
    String buyHouseContractId = "";// ������ͬ���
    Borrower borrower = null;
    String sex = "";
    String orgName = "";
    String orgAddress = "";
    String orgTel = "";
    String sfz = "";
    String pledgeValues = "";// ��Ѻֵ
    Houses houses = null;
    String houseType = "";
    try {
      if (id != null && !"".equals(id)) {
        entruster = this.getCenter(securityInfo);// ί�з�**����
        String office = this.getCenter(securityInfo);
        debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������

        // ��ѯPL111
        endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
            securityInfo);
        String isWrite = endorsecontractTaDTO.getIsWrite();// �Ƿ�ǩ����ͬ
        String contractSt = endorsecontractTaDTO.getContractSt();// ��ͬ״̬
        // �����ж�
        String paramValue = this.queryParamValue(securityInfo);
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.����ͨ�� 0 δǩ����ͬ
            // �ж�120�Ƿ����
            endorsecontractTbAF = this.isQuery(id, debitter, office, request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        } else if ("BA".equals(paramValue)) {
          if ("15".equals(contractSt) && "0".equals(isWrite)) {
            // �ж�120
            endorsecontractTbAF = this.isQuery(id, debitter, office, request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        }
        // ��ѯPL110
        borrower = borrowerDAO.queryById(id);
        if (borrower != null) {
          String tempsex = borrower.getSex();
          if (tempsex != null) {
            sex = BusiTools.getBusiValue(Integer.parseInt(tempsex),
                BusiConst.SEX);
          }
          sfz = borrower.getCardNum();
          orgName = borrower.getOrgName();
          orgAddress = borrower.getOrgAddr();
          orgTel = borrower.getOrgTel();
        }
      }
    } catch (BusinessException e) {
      throw e;
    }
    request.getSession().setAttribute("contractId", id);// ���������ѡ�е�ID�ŵ�SESSION��
    endorsecontractTbAF.setContractId(id);
    endorsecontractTbAF.setDebitter(debitter);
    String paperPersonName = endorsecontractTbAF.getPaperPersonName();// ����Ȩ��
    if (paperPersonName == null || "".equals(paperPersonName)) {// ����Ȩ��Ϊ��ʱ������Ȩ��=��Ѻ��=�����
      endorsecontractTbAF.setPaperPersonName(debitter);
      endorsecontractTbAF.setPledgePerson(debitter);
    } else {// ����Ȩ�˲�Ϊ��ʱ,��Ѻ��=����Ȩ��
      endorsecontractTbAF.setPledgePerson(paperPersonName);
    }
    endorsecontractTbAF.setOffice(entruster);
    String tempbuyHouseContractId = endorsecontractTbAF.getBuyHouseContractId();
    if (tempbuyHouseContractId == null || "".equals(tempbuyHouseContractId)) {
      if (id != null && !"".equals(id)) {
        houses = housesDAO.queryById(id);// PL114 ������Ϣ

        if (houses != null) {
          buyHouseContractId = houses.getBuyHouseContractId();// ������ͬ���
          houseType = houses.getHouseType();// ��������
          EndorsecontractTaDTO endorsecontractTaDTOPL115 = borrowerLoanInfoDAO
              .queryBorrowerLoanInfoYU(id, securityInfo);
          String debitMoney = endorsecontractTaDTOPL115.getDebitMoney();// ������
          endorsecontractTbAF.setDebitMoney(debitMoney);
          if ("02".equals(houseType)) {// �������� ��02 ���ַ� //�ۿ��� = ������/��Ѻֵ

            // ��Ѻֵ = ���ַ�����ֵ
            if (endorsecontractTbAF.getPledgeValue() == null
                || "".equals(endorsecontractTbAF.getPledgeValue())) {
              if (houses.getBargainorTotlePrice() != null) {
                pledgeValues = houses.getBargainorTotlePrice().toString();
                endorsecontractTbAF.setPledgeValue(pledgeValues);
              }
            }

            String rebate = "";// �ۿ��� //�ۿ��� = ������/��Ѻֵ
            BigDecimal tempvalue = new BigDecimal(0.00);
            BigDecimal tempdebitMoney = new BigDecimal(0.00);
            String temppledgeValue = endorsecontractTbAF.getPledgeValue();// ��Ѻֵ
            if (temppledgeValue != null && !"".equals(temppledgeValue)) {
              tempvalue = new BigDecimal(temppledgeValue);
            }
            if (debitMoney != null) {
              tempdebitMoney = new BigDecimal(debitMoney);
            }
            if (tempvalue.compareTo(new BigDecimal(0)) != 0) {
              rebate = tempdebitMoney.divide(tempvalue, 4,
                  BigDecimal.ROUND_HALF_UP).toString();
              if(rebate!=null&&!"".equals(rebate)){
                endorsecontractTbAF.setRebate(rebate);
              }
             
            }

          } else {// ��Ʒ��
          // ��Ѻֵ=��Ʒ���ܼ�
            if (endorsecontractTbAF.getPledgeValue() == null
                || "".equals(endorsecontractTbAF.getPledgeValue())) {
              if (houses.getBargainorTotlePrice() != null) {
                pledgeValues = houses.getHouseTotlePrice().toString();
              }
            }
          }
          endorsecontractTbAF.setHouseType(houseType);

          endorsecontractTbAF.setBuyHouseContractId(buyHouseContractId);
        }
        if (houses.getRebate() != null) {
          endorsecontractTbAF.setRebate(houses.getRebate().toString());
        }

      }
    }
    if ("0".equals(endorsecontractTbAF.getIsPl121())) {// û��¼�����Ѻ�ˣ���Ѻ����ϢĬ����ʾΪ�������Ϣ
      // Ĭ����ʾ����˹̶��绰 �ƶ��绰 ֤������ ֤������
      if (borrower != null) {
        endorsecontractTbAF.setCardKind(borrower.getCardKind());
        endorsecontractTbAF.setCarNum(borrower.getCardNum());
        endorsecontractTbAF.setTel(borrower.getHouseTel());
        endorsecontractTbAF.setMobile(borrower.getHomeMobile());
        endorsecontractTbAF.setDyrgddh(borrower.getHouseTel());
        endorsecontractTbAF.setDyryddh(borrower.getHomeMobile());
      }
      String houseAddrs = "";
      String paperNum = "";
      BigDecimal pledgeValuess = new BigDecimal(0.00);
      if ("02".equals(houseType)) {// �������� ��02 ���ַ�
        houseAddrs = houses.getBargainorHouseAddr();
        endorsecontractTbAF.setArea(houses.getBargainorHouseArea().toString());
        // ��Ѻֵ = ���ַ�����ֵ
        pledgeValuess = houses.getBargainorTotlePrice();
        if (pledgeValuess != null) {
          pledgeValues = pledgeValuess.toString();
        }
        // ����������
        paperNum = houses.getBargainorHousepropertyCode();

      } else {// ��Ʒ��
        houseAddrs = houses.getHouseAddr();
        endorsecontractTbAF.setArea(houses.getHouseArea().toString());
        // ��Ѻֵ=��Ʒ���ܼ�
        pledgeValuess = houses.getHouseTotlePrice();
        if (pledgeValuess != null) {
          pledgeValues = pledgeValuess.toString();
        }
        // ����������
        paperNum = houses.getBuildRightNum();
      }
      System.out.println("--------->" + paperNum);
      endorsecontractTbAF.setPledgeAddr(houseAddrs);
      endorsecontractTbAF.setPledgeValue(pledgeValues);
      endorsecontractTbAF.setPaperNum(paperNum);
    }
    endorsecontractTbAF.setSex(sex);
    endorsecontractTbAF.setOrgName(orgName);
    endorsecontractTbAF.setOrgAddress(orgAddress);
    endorsecontractTbAF.setOrgTel(orgTel);
    endorsecontractTbAF.setSfz(sfz);
    return endorsecontractTbAF;
  }

  // ��ӡ
  public EndorsecontractTbAF queryPledgeContractList_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    List list = new ArrayList();
    String office = this.getCenter(securityInfo);
    String pkId = pledgeContractDAO.queryMaxId(id);
    String debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������
    Borrower borrower = borrowerDAO.queryById(id);// ���ݺ�ͬID��ѯ���������
    BorrowerLoanInfo borrowerLoanInfo = borrowerLoanInfoDAO.queryById(id);
    BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(id);// ���ݺ�ͬID��ѯ���������
    LoanContract loanContract = loanContractDAO.queryById(id);
    if (pkId != null && !"".equals(pkId)) {
      endorsecontractTbAF = this.queryPledgeContractById(pkId, office);
      list = pledgeContractDAO.queryPledgeListYU(id);// ��ѯ�б�
      endorsecontractTbAF.setList(list);
      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          EndorsecontractTbAF esTbAF = (EndorsecontractTbAF) list.get(i);
          String statuss = "";
          String tempStatuss = esTbAF.getStatus();
          if (tempStatuss != null && !"".equals(tempStatuss)) {
            statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                BusiConst.PLCOMMONSTATUS_1);
          }
          esTbAF.setStatus(statuss);
          esTbAF.setDebitter(debitter);// ��Ѻ��
          esTbAF.setOffice(office);// ��ѺȨ��
          if (esTbAF.getCardKind() != null && !esTbAF.getCardKind().equals("")) {
            esTbAF.setCardKind((BusiTools.getBusiValue(Integer.parseInt(esTbAF
                .getCardKind()), BusiConst.DOCUMENTSSTATE)));
          }
        }
      }
      endorsecontractTbAF.setList(list);
    }
    endorsecontractTbAF.setBorrower(borrower);
    endorsecontractTbAF.setBorrowerAcc(borrowerAcc);
    endorsecontractTbAF.setLoanContract(loanContract);
    String bankId = borrowerAcc.getLoanBankId().toString();
    endorsecontractTbAF.setBorrowerLoanInfo(borrowerLoanInfo);
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
    String bankName = collBank.getCollBankName();
    endorsecontractTbAF.setBankName(bankName);
    request.getSession().setAttribute("pl121Id", pkId);
    String tempCardkind = endorsecontractTbAF.getCardKind();
    if (tempCardkind != null && !"".equals(tempCardkind)) {
      endorsecontractTbAF.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// ҳ����ʾ����֤������
    }
    return endorsecontractTbAF;
  }

  // ��Ѻ��ͬ���ר��
  public EndorsecontractTbAF queryPledgeContractList_Chg(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    List list = new ArrayList();
    String office = this.getCenter(securityInfo);
    String pkId = "";
    pkId = (String) request.getSession().getAttribute("pl121Id");
    if (pkId == null || "".equals(pkId)) {
      pkId = pledgeContractDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������
    if (pkId != null && !"".equals(pkId)) {
      endorsecontractTbAF = this.queryPledgeContractById(pkId, office);
      list = pledgeContractDAO.queryPledgeListYU(id);// ��ѯ�б�
      endorsecontractTbAF.setList(list);
      if (list.size() != 0) {
        for (int i = 0; i < list.size(); i++) {
          EndorsecontractTbAF esTbAF = (EndorsecontractTbAF) list.get(i);
          String statuss = "";
          String tempStatuss = esTbAF.getStatus();
          if (tempStatuss != null && !"".equals(tempStatuss)) {
            statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                BusiConst.PLCOMMONSTATUS_1);
          }
          esTbAF.setStatus(statuss);
          esTbAF.setDebitter(debitter);// ��Ѻ��
          esTbAF.setOffice(office);// ��ѺȨ��
        }
      }
      endorsecontractTbAF.setList(list);
    }
    request.getSession().setAttribute("pl121Id", pkId);
    endorsecontractTbAF.setContractId(id);
    endorsecontractTbAF.setDebitter(debitter);// ��Ѻ��
    endorsecontractTbAF.setOffice(office);// ��ѺȨ��
    endorsecontractTbAF.setPledgePerson(debitter);
    return endorsecontractTbAF;
  }

  // ��ӡ
  // public EndorsecontractTbAF queryPledgeContractList_(String id,
  // Pagination pagination, SecurityInfo securityInfo,
  // HttpServletRequest request) throws Exception, BusinessException {
  // // TODO Auto-generated method stub
  // EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
  // String office = this.getCenter(securityInfo);
  // String pkId = pledgeContractDAO.queryMaxId(id);
  // endorsecontractTbAF = this.queryPledgeContractById(pkId, office);
  // return endorsecontractTbAF;
  // }

  /**
   * ����123Id��ѯ123��Ϣ��Ĭ�ϴ����IDֵ��
   * 
   * @param id
   * @return
   */
  public EndorsecontractTdAF queryAssurerById(String id) {
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    Assurer assurer = assurerDAO.queryById(new Integer(id));
    String name = "";
    if (assurer != null && !"".equals(assurer)) {
      name = borrowerDAO.queryBorrowerName(assurer.getContractId());
      endorsecontractTdAF.setContractId(assurer.getContractId());// ��ͬ���

      endorsecontractTdAF.setDebitter(name);// �����
      BigDecimal tempEmpId = assurer.getEmpId();
      if (tempEmpId != null) {
        endorsecontractTdAF.setEmpId(tempEmpId.toString());// ְ�����
      }
      endorsecontractTdAF.setEmpName(assurer.getEmpName());// ְ������
      endorsecontractTdAF.setCardKind(assurer.getCardKind());// ֤������
      endorsecontractTdAF.setCardNum(assurer.getCardNum());// ֤������
      endorsecontractTdAF.setSex(assurer.getSex());// �Ա�
      endorsecontractTdAF.setBirthday(assurer.getBirthday());// ��������
      BigDecimal tempSalary = assurer.getSalary();
      if (tempSalary != null) {
        endorsecontractTdAF.setSalary(tempSalary.toString());// �¹��ʶ�
      }
      BigDecimal tempMonthPay = assurer.getMonthPay();
      if (tempMonthPay != null) {
        endorsecontractTdAF.setMonthPay(tempMonthPay.toString());// �½ɴ��
      }
      BigDecimal tempBalance = assurer.getBalance();
      if (tempBalance != null) {
        endorsecontractTdAF.setBalance(tempBalance.toString());// �˻����
      }
      endorsecontractTdAF.setStatus(assurer.getStatus());// ״̬
      endorsecontractTdAF.setTel(assurer.getTel());// �̶��绰
      endorsecontractTdAF.setMobile(assurer.getMobile());// �ƶ��绰
      endorsecontractTdAF.setHomeTel(assurer.getHomeTel());// ��ͥ�绰
      endorsecontractTdAF.setHomeAddr(assurer.getHomeAddr());// ��ͥ��ַ
      endorsecontractTdAF.setHomeMai(assurer.getHomeMail());// ��ͥ�ʱ�
      BigDecimal tempOrgId = assurer.getOrgId();
      if (tempOrgId != null) {
        endorsecontractTdAF.setOrgId(tempOrgId.toString());// ��λ���
      }
      endorsecontractTdAF.setOrgName(assurer.getOrgName());// ��λ����
      endorsecontractTdAF.setOrgAddr(assurer.getOrgAddr());// ��λ��ַ
      endorsecontractTdAF.setOrgTel(assurer.getOrgTel());// ��λ�绰
      endorsecontractTdAF.setOrgMail(assurer.getOrgMail());// ��λ�ʱ�
      String tempEmpSt = assurer.getEmpSt();
      if (tempEmpSt != null) {
        try {
          endorsecontractTdAF.setEmpSt(BusiTools.getBusiValue(Integer
              .parseInt("" + tempEmpSt), BusiConst.OLDPAYMENTSTATE));
        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }// �˻�״̬
      }
    }
    return endorsecontractTdAF;
  }

  /**
   * ����122Id��ѯ122��Ϣ��Ĭ�ϴ����IDֵ��
   * 
   * @param id
   * @return
   */
  public EndorsecontractTcAF queryImpawnContractById(String id, String office) {
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    if (id != null) {
      ImpawnContract impawnContract = impawnContractDAO.queryById(new Integer(
          id));
      String name = borrowerDAO.queryBorrowerName(impawnContract
          .getContractId());
      String paperPersonName = "";
      paperPersonName = impawnContract.getName();

      endorsecontractTcAF.setContractId(impawnContract.getContractId());// ��ͬ���
      endorsecontractTcAF.setDebitter(name);// �����
      endorsecontractTcAF.setImpawnContractId(impawnContract
          .getImpawnContractId());// ��Ѻ��ͬ���
      endorsecontractTcAF.setAssistantOrgName(assistantOrgDAO
          .queryOrgName(impawnContract.getContractId()));// ������˾����
      if (paperPersonName != null && !"".equals(paperPersonName)) {
        endorsecontractTcAF.setImpawnPerson(paperPersonName);// ��Ѻ��
        endorsecontractTcAF.setPaperPersonName(paperPersonName);// ����Ȩ������
      } else {
        endorsecontractTcAF.setImpawnPerson(name);// ��Ѻ��
        endorsecontractTcAF.setPaperPersonName(name);// ����Ȩ������
      }
      endorsecontractTcAF.setOffice(office);// ��ѺȨ��
      endorsecontractTcAF.setImpawnMatterName(impawnContract
          .getImpawnMatterName());// ��Ѻ������
      BigDecimal tempValue = impawnContract.getImpawnValue();

      if (tempValue != null) {
        endorsecontractTcAF.setImpawnValue(tempValue.toString());// ��Ѻ���ֵ
      }

      endorsecontractTcAF.setCardKind(impawnContract.getCardKind());// ֤������
      endorsecontractTcAF.setCarNum(impawnContract.getCardNum());// ֤������
      endorsecontractTcAF.setPaperNum(impawnContract.getPaperNum());// ����Ȩ֤���
      endorsecontractTcAF.setPaperName(impawnContract.getPaperName());// ����Ȩ֤����
      endorsecontractTcAF.setTel(impawnContract.getTel());// �̶��绰
      endorsecontractTcAF.setMobile(impawnContract.getMobile());// �ƶ��绰
    }
    return endorsecontractTcAF;
  }

  /**
   * ����121Id��ѯ121��Ϣ��Ĭ�ϴ����IDֵ��
   * 
   * @param id
   * @return
   */
  public EndorsecontractTbAF queryPledgeContractById(String id, String office) {
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    PledgeContract pledgeContract = pledgeContractDAO
        .queryById(new Integer(id));

    String debitter = "";// �����
    String paperPersonName = "";// ����Ȩ������
    debitter = borrowerDAO.queryBorrowerName(pledgeContract.getContractId());
    Houses hourses = housesDAO.queryById(pledgeContract.getContractId());
    paperPersonName = pledgeContract.getName();
    if (hourses != null && hourses.getRebate() != null
        && !"".equals(hourses.getRebate())) {
      endorsecontractTbAF.setRebate(hourses.getRebate().toString());
    }
    endorsecontractTbAF.setContractId(pledgeContract.getContractId());// ��ͬ���
    endorsecontractTbAF.setDebitter(debitter);// �����
    if (paperPersonName != null && !"".equals(paperPersonName)) {
      endorsecontractTbAF.setPledgePerson(paperPersonName);
      endorsecontractTbAF.setPaperPersonName(paperPersonName);// ����Ȩ������
    } else {
      endorsecontractTbAF.setPledgePerson(debitter);// ��Ѻ��
      endorsecontractTbAF.setPaperPersonName(debitter);// ����Ȩ������
    }
    endorsecontractTbAF.setOffice(office);// ��ѺȨ��
    endorsecontractTbAF.setPledgeContractId(pledgeContract
        .getPledgeContractId());// ��Ѻ��ͬ���
    endorsecontractTbAF.setAssistantOrgName(assistantOrgDAO
        .queryOrgName(pledgeContract.getContractId()));// ������˾����
    endorsecontractTbAF.setPledgeMatterName(pledgeContract
        .getPledgeMatterName());// ��Ѻ������
    endorsecontractTbAF.setPaperNum(pledgeContract.getPaperNum());// ����Ȩ֤���
    endorsecontractTbAF.setPaperName(pledgeContract.getPaperName());// ����Ȩ֤����

    endorsecontractTbAF.setCarNum(pledgeContract.getCardNum());// ����Ȩ��֤������
    endorsecontractTbAF.setTel(pledgeContract.getTel());// �̶��绰
    endorsecontractTbAF.setMobile(pledgeContract.getMobile());// �ж��绰
    endorsecontractTbAF.setDyrgddh(pledgeContract.getTel());// ��Ѻ�˹̶��绰
    endorsecontractTbAF.setDyryddh(pledgeContract.getMobile());// ��Ѻ���ж��绰
    endorsecontractTbAF.setPledgeAddr(pledgeContract.getPledgeAddr());// ��Ѻ���ַ
    BigDecimal tempBig = pledgeContract.getArea();
    if (tempBig != null && !"".equals(tempBig)) {
      endorsecontractTbAF.setArea(tempBig.toString());// ���
    }
    endorsecontractTbAF.setBuyHouseContractId(housesDAO
        .queryBuyHouseContractId(pledgeContract.getContractId()));// ������ͬ���
    BigDecimal tempBig2 = pledgeContract.getPledgeValue();
    if (tempBig2 != null && !"".equals(tempBig2)) {
      endorsecontractTbAF.setPledgeValue(tempBig2.toString());// ��Ѻֵ
    }
    BigDecimal tempBig3 = pledgeContract.getEvaluateValue();
    if (tempBig3 != null && !"".equals(tempBig3)) {
      endorsecontractTbAF.setEvaluateValue(tempBig3.toString());// ����ֵ
    }
    endorsecontractTbAF.setCardKind(pledgeContract.getCardKind());// ֤������
    String buyHouseContractId = pledgeContract.getReserveaA();
    if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
      endorsecontractTbAF.setBuyHouseContractId(buyHouseContractId);// ������ͬ���
    }
    return endorsecontractTbAF;
  }

  /**
   * TB�ַ�ȷ����ť
   */
  public void sure(Integer id, String loanassistantorgId,
      SecurityInfo securityInfo, EndorsecontractTbAF endorsecontractTbAF)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // ����ID��pl121Id������ѯPL121
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT + "";

    String contractId = endorsecontractTbAF.getContractId().trim();// ��ͬID
    String debitter = endorsecontractTbAF.getDebitter().trim();// ��������� PL110
    String pledgePerson = endorsecontractTbAF.getPledgePerson().trim();// ��Ѻ������
    String office = endorsecontractTbAF.getOffice().trim();// ��ѺȨ�ˣ����������ģ�
    String pledgeContractId = endorsecontractTbAF.getPledgeContractId().trim();// ��Ѻ��ͬ���
    String assistantOrgName = endorsecontractTbAF.getAssistantOrgName().trim();// ������˾����
    // String loanassistantorgId =
    // endorsecontractTbAF.getLoanassistantorgId();//������˾���
    String pledgeMatterName = endorsecontractTbAF.getPledgeMatterName().trim();// ��Ѻ������
    String paperNum = endorsecontractTbAF.getPaperNum().trim();// ����Ȩ֤���
    String paperName = endorsecontractTbAF.getPaperName().trim();// ����Ȩ֤����
    String paperPersonName = endorsecontractTbAF.getPaperPersonName().trim();// ����Ȩ������
    String cardKind = endorsecontractTbAF.getCardKind().trim();// ����Ȩ��֤������
    String carNum = endorsecontractTbAF.getCarNum().trim();// ����Ȩ��֤������
    String tel = endorsecontractTbAF.getTel().trim();// ����Ȩ�˹̶��绰
    String mobile = endorsecontractTbAF.getMobile().trim();// ����Ȩ���ƶ��绰
    String pledgeAddr = endorsecontractTbAF.getPledgeAddr().trim();// ��Ѻ���ַ
    String area = endorsecontractTbAF.getArea().trim();// �������
    String buyHouseContractId = endorsecontractTbAF.getBuyHouseContractId()
        .trim();// ������ͬ���
    String pledgeValue = endorsecontractTbAF.getPledgeValue().trim();// ��Ѻֵ
    String evaluateValue = endorsecontractTbAF.getEvaluateValue().trim();// ����ֵ
    String rebate = endorsecontractTbAF.getRebate();
    PledgeContract pledgeContract = null;
    try {
      System.out.println("sd...."+id);
      if (id != null && !"".equals(id)) {
        // ********************************************************************************//
        // ����PL114��ѯ���Ĺ�����ͬ��ţ����뵽pl121 ��עA�У��Ժ�Ҳ�������ѯ����
        pledgeContract = pledgeContractDAO.queryById(id);// ����
      }
      if (pledgeContract != null) {// ���ڣ�����

        System.out.println("contractId...."+contractId);
        if (contractId != null && !"".equals(contractId)) {
          // // ���¹�����ͬ���
          Houses houses = housesDAO.queryById(contractId);
          // houses.setBuyHouseContractId(buyHouseContractId);
          if(rebate!=null&&!"".equals(rebate)){
            houses.setRebate(new BigDecimal(rebate));
          }
         
          // pledgeContract.setReserveaA(buyHouseContractId);
        }
        // ********************************************************************************//
        // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
        // // ���µ�����˾
        // // String assistantOrgId = assistantOrgDAO.queryId(contractId);
        // // if(assistantOrgId != null && !"".equals(assistantOrgId)){
        // AssistantOrg assistantOrg = assistantOrgDAO.queryById(new Integer(
        // loanassistantorgId));
        // assistantOrg.setAssistantOrgName(assistantOrgName);
        // // }
        // }
        if (contractId != null && !"".equals(contractId)) {
          pledgeContract.setContractId(contractId);
        }
        if (pledgeContractId != null && !"".equals(pledgeContractId)) {
          pledgeContract.setPledgeContractId(pledgeContractId);
        }
        if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
          pledgeContract.setPledgeMatterName(pledgeMatterName);
        }
        if (pledgeValue != null && !"".equals(pledgeValue)) {
          pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
        }
        if (paperNum != null && !"".equals(paperNum)) {
          pledgeContract.setPaperNum(paperNum);
        }
        if (paperName != null && !"".equals(paperName)) {
          pledgeContract.setPaperName(paperName);
        }
        if (paperPersonName != null && !"".equals(paperPersonName)) {
          pledgeContract.setName(paperPersonName);
        }
        if (cardKind != null && !"".equals(cardKind)) {
          pledgeContract.setCardKind(cardKind);
        }
        if (carNum != null && !"".equals(carNum)) {
          pledgeContract.setCardNum(carNum);
        }
        if (tel != null && !"".equals(tel)) {
          pledgeContract.setTel(tel);
        }
        if (mobile != null && !"".equals(mobile)) {
          pledgeContract.setMobile(mobile);
        }
        if (pledgeAddr != null && !"".equals(pledgeAddr)) {
          pledgeContract.setPledgeAddr(pledgeAddr);
        }
        if (area != null && !"".equals(area)) {
          pledgeContract.setArea(new BigDecimal(area));
        }
        if (evaluateValue != null && !"".equals(evaluateValue)) {
          pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
        }
        if (rebate != null && !"".equals(rebate)) {
          pledgeContract.setRebate(rebate);
        }
        String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
        String bizId = pledgeContract.getId().toString();
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {// �����ڣ�����

        if (contractId != null && !"".equals(contractId)) {
          // ���¹�����ͬ���

          Houses houses = housesDAO.queryById(contractId);
          if(rebate!=null&&!"".equals(rebate)){
            houses.setRebate(new BigDecimal(rebate));
          }
          

        }
        // // ���뵣����˾
        // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
        // AssistantOrg assistantOrg = new AssistantOrg();
        // assistantOrg.setAssistantOrgName(assistantOrgName);
        // assistantOrgDAO.insert(assistantOrg);
        // }
        pledgeContract = new PledgeContract();
        if (contractId != null && !"".equals(contractId)) {
          pledgeContract.setContractId(contractId);
        }
        if (buyHouseContractId != null && !"".equals(buyHouseContractId)) {
          pledgeContract.setReserveaA(buyHouseContractId);
        }
        if (pledgeContractId != null && !"".equals(pledgeContractId)) {
          pledgeContract.setPledgeContractId(pledgeContractId);
        }
        if (pledgeMatterName != null && !"".equals(pledgeMatterName)) {
          pledgeContract.setPledgeMatterName(pledgeMatterName);
        }
        if (pledgeValue != null && !"".equals(pledgeValue)) {
          pledgeContract.setPledgeValue(new BigDecimal(pledgeValue));
        }
        if (paperNum != null && !"".equals(paperNum)) {
          pledgeContract.setPaperNum(paperNum);
        }
        if (paperName != null && !"".equals(paperName)) {
          pledgeContract.setPaperName(paperName);
        }
        if (paperPersonName != null && !"".equals(paperPersonName)) {
          pledgeContract.setName(paperPersonName);
        }
        if (cardKind != null && !"".equals(cardKind)) {
          pledgeContract.setCardKind(cardKind);
        }
        if (carNum != null && !"".equals(carNum)) {
          pledgeContract.setCardNum(carNum);
        }
        if (tel != null && !"".equals(tel)) {
          pledgeContract.setTel(tel);
        }
        if (mobile != null && !"".equals(mobile)) {
          pledgeContract.setMobile(mobile);
        }
        if (pledgeAddr != null && !"".equals(pledgeAddr)) {
          pledgeContract.setPledgeAddr(pledgeAddr);
        }
        if (area != null && !"".equals(area)) {
          pledgeContract.setArea(new BigDecimal(area));
        }
        if (evaluateValue != null && !"".equals(evaluateValue)) {
          pledgeContract.setEvaluateValue(new BigDecimal(evaluateValue));
        }
        if (rebate != null && !"".equals(rebate)) {
          pledgeContract.setRebate(rebate);
        }
        pledgeContract.setOperator(operator);
        pledgeContract.setOpTime(new Date());
        pledgeContract.setStatus("0");
        String pContractId = pledgeContractDAO.insert(pledgeContract)
            .toString();
        String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
        String bizId = pContractId;
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * �ַ������޸İ�ť
   */
  public EndorsecontractTbAF updatePledgeContract(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    String office = this.getCenter(securityInfo);// ����
    endorsecontractTbAF = this.queryPledgeContractById(id, office);
    return endorsecontractTbAF;
  }

  /**
   * �ַ�����ɾ����ť
   */
  public void deletePledgeContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    PledgeContract pledgeContract = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT + "";
    String opBizId = "";
    if (id != null && !"".equals(id)) {
      pledgeContract = pledgeContractDAO.queryById(new Integer(id));
      String bizId = pledgeContract.getContractId();
      if (pledgeContract != null) {
        pledgeContractDAO.deleteById(id);
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        // if (bizId != null && !"".equals(bizId)) {
        // opBizId = loanFlowTailDAO.queryHeadId(bizId);
        // }
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {
        throw new BusinessException("�ü�¼�Ѿ���ɾ����");
      }
    }

  }

  /** ****************************************************************** */
  /** **** ��Ѻ��ͬ��Ϣ **** */
  /** ****************************************************************** */

  public EndorsecontractTcAF queryImpawnContractList(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String contractId = id;// ��ͬ���
    String debitter = "";
    String assistantOrgName = null;
    // �ж�003�� "AB" or "BA"
    String entruster = null;
    EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    if (id != null && !"".equals(id)) {
      assistantOrgName = loanContractDAO.queryAssOrgByIdYU(id);
    }
    try {
      if (id != null && !"".equals(id)) {
        entruster = this.getCenter(securityInfo);// ί�з�**����
        String office = this.getCenter(securityInfo);
        debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������

        // ��ѯPL111
        endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
            securityInfo);
        String isWrite = endorsecontractTaDTO.getIsWrite();// �Ƿ�ǩ����ͬ
        String contractSt = endorsecontractTaDTO.getContractSt();// ��ͬ״̬
        // �����ж�
        String paramValue = this.queryParamValue(securityInfo);
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.����ͨ�� 0 δǩ����ͬ
            // �ж�120�Ƿ����
            endorsecontractTcAF = this.isQueryPL122(id, debitter, office,
                request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        } else if ("BA".equals(paramValue)) {
          if ("2".equals(contractSt) && "0".equals(isWrite)) {
            // �ж�120
            endorsecontractTcAF = this.isQueryPL122(id, debitter, office,
                request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        }
      }
    } catch (BusinessException e) {
      throw e;
    }
    request.getSession().setAttribute("contractId", id);// ���������ѡ�е�ID�ŵ�SESSION��
    endorsecontractTcAF.setContractId(contractId);
    endorsecontractTcAF.setDebitter(debitter);
    String paperPersonName = endorsecontractTcAF.getPaperPersonName();// ����Ȩ��
    if (paperPersonName == null || "".equals(paperPersonName)) {// ����Ȩ��Ϊ��ʱ������Ȩ��=��Ѻ��=�����
      endorsecontractTcAF.setPaperPersonName(debitter);
      endorsecontractTcAF.setImpawnPerson(debitter);
    } else {// ����Ȩ�˲�Ϊ��ʱ,��Ѻ��=����Ȩ��
      endorsecontractTcAF.setImpawnPerson(paperPersonName);
    }
    endorsecontractTcAF.setOffice(entruster);
    endorsecontractTcAF.setAssistantOrgName(assistantOrgName);
    return endorsecontractTcAF;
  }

  // ���ڴ�ӡ
  public EndorsecontractTcAF queryImpawnContractList_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String office = this.getCenter(securityInfo);
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String maxId = "";
    Object obj = (Object) request.getSession().getAttribute("pl122Id");
    if (obj != null) {
      maxId = obj.toString();
    }
    if (maxId == null || "".equals(maxId)) {
      maxId = impawnContractDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������
    endorsecontractTcAF = this.queryImpawnContractById(maxId, office);// �������ID����pl122
    // ��ѯ�б�
    List list = impawnContractDAO.queryImpawnListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTcAF esTcAF = (EndorsecontractTcAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTcAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
        }
        esTcAF.setStatus(statuss);
        esTcAF.setImpawnPerson(debitter);// ��Ѻ��
        esTcAF.setOffice(office);// ��ѺȨ��
        if (esTcAF.getCardKind() != null && !esTcAF.getCardKind().equals("")) {
          // ת��֤������
          esTcAF.setCardKind((BusiTools.getBusiValue(Integer.parseInt(esTcAF
              .getCardKind()), BusiConst.DOCUMENTSSTATE)));
        }
      }
    }
    endorsecontractTcAF.setList(list);
    String tempCardkind = endorsecontractTcAF.getCardKind();
    if (tempCardkind != null && !"".equals(tempCardkind)) {
      endorsecontractTcAF.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// ҳ����ʾ����֤������
    }
    endorsecontractTcAF.setContractId(id);
    endorsecontractTcAF.setImpawnPerson(debitter);// ��Ѻ��
    endorsecontractTcAF.setDebitter(debitter);
    return endorsecontractTcAF;
  }

  /**
   * ������Ѻ���ר��
   */
  public EndorsecontractTcAF queryImpawnContractList_Chg(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String office = this.getCenter(securityInfo);
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String maxId = impawnContractDAO.queryMaxId(id);
    String debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������
    endorsecontractTcAF = this.queryImpawnContractById(maxId, office);// �������ID����pl122
    // ��ѯ�б�
    List list = impawnContractDAO.queryImpawnListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTcAF esTcAF = (EndorsecontractTcAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTcAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
        }
        esTcAF.setStatus(statuss);
        esTcAF.setImpawnPerson(debitter);// ��Ѻ��
        esTcAF.setOffice(office);// ��ѺȨ��
      }
    }
    endorsecontractTcAF.setList(list);
    endorsecontractTcAF.setContractId(id);
    endorsecontractTcAF.setDebitter(debitter);
    return endorsecontractTcAF;
  }

  /**
   * ���ж�PL120,��PL123
   * 
   * @param id
   * @param debitter
   * @param office
   * @param request
   * @return
   */
  public EndorsecontractTdAF isQueryPL123(String id, String debitter,
      String office, HttpServletRequest request) throws Exception,
      BusinessException {
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    List list = new ArrayList();
    String pl120id = this.queryPL120(id);
    List pl123IdList = new ArrayList();
    if (pl120id != null) {
      // �ж�PL123
      pl123IdList = this.checkPL123(id);
      if (pl123IdList.size() != 0) {
        // ��ѯ�б�
        list = assurerDAO.queryAssurerListYU(id);
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            EndorsecontractTdAF esTdAF = (EndorsecontractTdAF) list.get(i);
            String statuss = "";
            String tempStatuss = esTdAF.getStatus();
            if (tempStatuss != null && !"".equals(tempStatuss)) {
              statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                  BusiConst.PLCOMMONSTATUS_1);
              esTdAF.setStatus(statuss);
            }
          }
        }
        // ��ѯPL123��ͬ���Ϊ������ͬ��ţ�IDΪ���ֵ����Ϣ
        String maxId = assurerDAO.queryMaxId(id);
        request.getSession().setAttribute("pl123Id", maxId);
        endorsecontractTdAF = this.queryAssurerById(maxId);// �������ID��ѯpl123
        endorsecontractTdAF.setList(list);
      }
    } else {
      throw new BusinessException("����¼�����ͬ��");
    }
    return endorsecontractTdAF;
  }

  /**
   * ���ж�PL120,��PL122
   * 
   * @param id
   * @param debitter
   * @param office
   * @param request
   * @return
   */
  public EndorsecontractTcAF isQueryPL122(String id, String debitter,
      String office, HttpServletRequest request) throws Exception,
      BusinessException {
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    List list = new ArrayList();
    String pl120id = this.queryPL120(id);
    List pl122IdList = new ArrayList();
    if (pl120id != null) {
      // �ж�PL122
      pl122IdList = this.checkPL122(id);
      if (pl122IdList.size() != 0) {
        // ��ѯ�б�
        list = impawnContractDAO.queryImpawnListYU(id);
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            EndorsecontractTcAF esTcAF = (EndorsecontractTcAF) list.get(i);
            String statuss = "";
            String tempStatuss = esTcAF.getStatus();
            if (tempStatuss != null && !"".equals(tempStatuss)) {
              statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                  BusiConst.PLCOMMONSTATUS_1);
            }
            esTcAF.setStatus(statuss);
            esTcAF.setImpawnPerson(debitter);// ��Ѻ��
            esTcAF.setOffice(office);// ��ѺȨ��
          }
        }
        // ��ѯPL122��ͬ���Ϊ������ͬ��ţ�IDΪ���ֵ����Ϣ
        String maxId = impawnContractDAO.queryMaxId(id);
        request.getSession().setAttribute("pl122Id", maxId);
        endorsecontractTcAF = this.queryImpawnContractById(maxId, office);// �������ID����pl122
        endorsecontractTcAF.setList(list);
        endorsecontractTcAF.setDebitter(debitter);
        endorsecontractTcAF.setOffice(office);
      }
    } else {
      throw new BusinessException("����¼�����ͬ��");
    }
    return endorsecontractTcAF;
  }

  /**
   * ���ж�PL120,��PL121
   * 
   * @param id
   * @param debitter
   * @param office
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTbAF isQuery(String id, String debitter, String office,
      HttpServletRequest request) throws Exception, BusinessException {
    List list = new ArrayList();
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    String pl120id = this.queryPL120(id);
    List pl121IdList = new ArrayList();
    if (pl120id != null) {
      String assistantOrgName = loanContractDAO.queryAssOrgByIdYU(id);
      // �ж�PL121
      pl121IdList = this.checkPL121(id);
      if (pl121IdList.size() != 0) {
        list = pledgeContractDAO.queryPledgeListYU(id);// ��ѯ�б�
        if (list.size() != 0) {
          for (int i = 0; i < list.size(); i++) {
            EndorsecontractTbAF esTbAF = (EndorsecontractTbAF) list.get(i);
            String statuss = "";
            String tempStatuss = esTbAF.getStatus();
            if (tempStatuss != null && !"".equals(tempStatuss)) {
              statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
                  BusiConst.PLCOMMONSTATUS_1);
            }
            esTbAF.setStatus(statuss);
            esTbAF.setDebitter(debitter);// ��Ѻ��
            esTbAF.setOffice(office);// ��ѺȨ��

          }
        }
        // ��ѯPL121��ͬ���Ϊ������ͬ��ţ�IDΪ���ֵ����Ϣ
        String maxId = pledgeContractDAO.queryMaxId(id);
        /** ************************************************** */
        /* �˷���������������� this.queryPledgeContractById */
        /** *************************************************** */
        request.getSession().setAttribute("pl121Id", new Integer(maxId));
        endorsecontractTbAF = this.queryPledgeContractById(maxId, office);// �������ID��ѯpl121
        endorsecontractTbAF.setList(list);
        endorsecontractTbAF.setAssistantOrgName(assistantOrgName);
      } else {// ��ֻ��ʾ��ͬ��ţ���Ѻ�ˣ�����ˣ����ģ�����ʾ�б�,������˾
        endorsecontractTbAF.setContractId(id);
        endorsecontractTbAF.setDebitter(debitter);
        endorsecontractTbAF.setPledgePerson(debitter);
        endorsecontractTbAF.setOffice(office);
        endorsecontractTbAF.setAssistantOrgName(assistantOrgName);
        endorsecontractTbAF.setIsPl121("0");
      }
    } else {
      throw new BusinessException("����¼�����ͬ��");
    }
    return endorsecontractTbAF;
  }

  /**
   * �ַ�ȷ����ť TC
   */
  public void sureTc(String id, String loanassistantorgId,
      SecurityInfo securityInfo, EndorsecontractTcAF endorsecontractTcAF)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // ����ID��pl121Id������ѯPL121
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_IMPAWNCONTRACT + "";

    String contractId = endorsecontractTcAF.getContractId().trim();// ��ͬID
    String debitter = endorsecontractTcAF.getDebitter().trim();// ��������� PL110
    String impawnContractId = endorsecontractTcAF.getImpawnContractId().trim();// ��Ѻ��ͬ���
    String assistantOrgName = endorsecontractTcAF.getAssistantOrgName().trim();// ������˾����
    String impawnPerson = endorsecontractTcAF.getImpawnPerson().trim();// ��Ѻ��
    String office = endorsecontractTcAF.getOffice().trim();// ��ѺȨ�ˣ����������ģ�
    String impawnMatterName = endorsecontractTcAF.getImpawnMatterName().trim();// ��Ѻ������
    String impawnValue = endorsecontractTcAF.getImpawnValue().trim();// ��Ѻ���ֵ
    String paperPersonName = endorsecontractTcAF.getPaperPersonName().trim();// ����Ȩ������
    String cardKind = endorsecontractTcAF.getCardKind().trim();// ����Ȩ��֤������
    String carNum = endorsecontractTcAF.getCarNum().trim();// ����Ȩ��֤������
    String paperNum = endorsecontractTcAF.getPaperNum().trim();// ����Ȩ֤���
    String paperName = endorsecontractTcAF.getPaperName().trim();// ����Ȩ֤����
    String tel = endorsecontractTcAF.getTel().trim();// ����Ȩ�˹̶��绰
    String mobile = endorsecontractTcAF.getMobile().trim();// ����Ȩ���ƶ��绰

    ImpawnContract impawnContract = null;
    if (id != null && !"".equals(id)) {
      impawnContract = impawnContractDAO.queryById(new Integer(id));// ����
      if (impawnContract != null) {// ���ڣ�����
      // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
      // // ���µ�����˾
      // // String assistantOrgId = assistantOrgDAO.queryId(contractId);
      // AssistantOrg assistantOrg = assistantOrgDAO.queryById(new Integer(
      // loanassistantorgId));
      // assistantOrg.setAssistantOrgName(assistantOrgName);
      // }
        if (contractId != null && !"".equals(contractId)) {
          impawnContract.setContractId(contractId);
        }
        if (impawnContractId != null && !"".equals(impawnContractId)) {
          impawnContract.setImpawnContractId(impawnContractId);
        }
        if (impawnMatterName != null && !"".equals(impawnMatterName)) {
          impawnContract.setImpawnMatterName(impawnMatterName);
        }
        if (impawnValue != null && !"".equals(impawnValue)) {
          impawnContract.setImpawnValue(new BigDecimal(impawnValue));
        }
        if (paperPersonName != null && !"".equals(paperPersonName)) {
          impawnContract.setName(paperPersonName);
        }
        if (cardKind != null && !"".equals(cardKind)) {
          impawnContract.setCardKind(cardKind);
        }
        if (carNum != null && !"".equals(carNum)) {
          impawnContract.setCardNum(carNum);
        }
        if (paperNum != null && !"".equals(paperNum)) {
          impawnContract.setPaperNum(paperNum);
        }
        if (paperName != null && !"".equals(paperName)) {
          impawnContract.setPaperName(paperName);
        }
        if (tel != null && !"".equals(tel)) {
          impawnContract.setTel(tel);
        }
        if (mobile != null && !"".equals(mobile)) {
          impawnContract.setMobile(mobile);
        }
        if (operator != null && !"".equals(operator)) {
          impawnContract.setOperator(operator);
        }
        impawnContract.setOpTime(new Date());
        String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
        String bizId = impawnContract.getId().toString();
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      }
    } else {// �����ڣ�����

      // ���뵣����˾
      // if (assistantOrgName != null && !"".equals(assistantOrgName)) {
      // AssistantOrg assistantOrg = new AssistantOrg();
      // assistantOrg.setAssistantOrgName(assistantOrgName);
      // assistantOrgDAO.insert(assistantOrg);
      // }
      impawnContract = new ImpawnContract();
      if (contractId != null && !"".equals(contractId)) {
        impawnContract.setContractId(contractId);
      }
      if (impawnContractId != null && !"".equals(impawnContractId)) {
        impawnContract.setImpawnContractId(impawnContractId);
      }
      if (impawnMatterName != null && !"".equals(impawnMatterName)) {
        impawnContract.setImpawnMatterName(impawnMatterName);
      }
      if (impawnValue != null && !"".equals(impawnValue)) {
        impawnContract.setImpawnValue(new BigDecimal(impawnValue));
      }
      if (paperPersonName != null && !"".equals(paperPersonName)) {
        impawnContract.setName(paperPersonName);
      }
      if (cardKind != null && !"".equals(cardKind)) {
        impawnContract.setCardKind(cardKind);
      }
      if (carNum != null && !"".equals(carNum)) {
        impawnContract.setCardNum(carNum);
      }
      if (paperNum != null && !"".equals(paperNum)) {
        impawnContract.setPaperNum(paperNum);
      }
      if (paperName != null && !"".equals(paperName)) {
        impawnContract.setPaperName(paperName);
      }
      if (tel != null && !"".equals(tel)) {
        impawnContract.setTel(tel);
      }
      if (mobile != null && !"".equals(mobile)) {
        impawnContract.setMobile(mobile);
      }
      if (operator != null && !"".equals(operator)) {
        impawnContract.setOperator(operator);
      }
      impawnContract.setOpTime(new Date());
      impawnContract.setStatus("0");
      String iContractId = impawnContractDAO.insert(impawnContract).toString();
      String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
      String bizId = iContractId;
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    }
  }

  /**
   * TC �ַ��޸İ�ť
   */
  public EndorsecontractTcAF updateImpawnContract(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    String office = this.getCenter(securityInfo);// ����
    endorsecontractTcAF = this.queryImpawnContractById(id, office);
    return endorsecontractTcAF;
  }

  /**
   * TC ɾ����ť
   */
  public void deleteImpawnContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    ImpawnContract impawnContract = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT + "";
    if (id != null && !"".equals(id)) {
      impawnContract = impawnContractDAO.queryById(new Integer(id));
      String bizId = impawnContract.getContractId();
      if (impawnContract != null) {
        impawnContractDAO.deleteById(id);
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {
        throw new BusinessException("�ü�¼�Ѿ���ɾ����");
      }
    }
  }

  /** ****************************************************************** */
  /** **** ��֤����Ϣ **** */
  /** ****************************************************************** */

  /**
   * TD��ѯ
   */
  public EndorsecontractTdAF queryAssurerList(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    // �ж�003�� "AB" or "BA"
    EndorsecontractTaDTO endorsecontractTaDTO = new EndorsecontractTaDTO();
    String debitter = "";
    try {
      if (id != null && !"".equals(id)) {
        String office = this.getCenter(securityInfo);
        debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������

        // ��ѯPL111
        endorsecontractTaDTO = borrowerAccDAO.queryBorrowerAccInfoYU(id,
            securityInfo);
        String isWrite = endorsecontractTaDTO.getIsWrite();// �Ƿ�ǩ����ͬ
        String contractSt = endorsecontractTaDTO.getContractSt();// ��ͬ״̬
        // �����ж�
        String paramValue = this.queryParamValue(securityInfo);
        if ("AB".equals(paramValue)) {
          if ("4".equals(contractSt) && "0".equals(isWrite)) {// 4.����ͨ�� 0 δǩ����ͬ
            // �ж�120�Ƿ����
            endorsecontractTdAF = this.isQueryPL123(id, debitter, office,
                request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        } else if ("BA".equals(paramValue)) {
          if ("2".equals(contractSt) && "0".equals(isWrite)) {
            // �ж�120
            endorsecontractTdAF = this.isQueryPL123(id, debitter, office,
                request);
          } else {
            throw new BusinessException("�ú�ͬ��Ų����ڻ�״̬���ԣ�");
          }
        }
      }
    } catch (BusinessException e) {
      throw e;
    }
    request.getSession().setAttribute("contractId", id);// ���������ѡ�е�ID�ŵ�SESSION��
    endorsecontractTdAF.setContractId(id);
    endorsecontractTdAF.setDebitter(debitter);
    return endorsecontractTdAF;
  }

  // EMPID ��ѯ
  public EndorsecontractTdAF queryAssurerListByEmpId(String id, String orgId,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    endorsecontractTdAF = empDAO.queryListByEmpId(id, orgId);
    request.getSession().setAttribute("contractId", id);// ���������ѡ�е�ID�ŵ�SESSION��
    return endorsecontractTdAF;
  }

  // ���ڴ�ӡ
  public EndorsecontractTdAF queryAssurerList_(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    // ��ѯPL123��ͬ���Ϊ������ͬ��ţ�IDΪ���ֵ����Ϣ
    String maxId = "";
    maxId = (String) request.getSession().getAttribute("pl123Id");
    if (maxId == null || "".equals(maxId)) {
      maxId = assurerDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������
    if (maxId != null && !"".equals(maxId)) {
      request.getSession().setAttribute("pl123Id", maxId);
      endorsecontractTdAF = this.queryAssurerById(maxId);// �������ID��ѯpl123
      String sex = endorsecontractTdAF.getSex();
      String tempSex = "";
      if (sex != null && !"".equals(sex)) {
        tempSex = BusiTools.getBusiValue(Integer.parseInt(sex), BusiConst.SEX);
      }
      endorsecontractTdAF.setSex(tempSex);
    }
    // ��ѯ�б�
    List list = assurerDAO.queryAssurerListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTdAF esTdAF = (EndorsecontractTdAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTdAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
          esTdAF.setStatus(statuss);
        }
        if (esTdAF.getCardKind() != null && !esTdAF.getCardKind().equals("")) {
          // ת��֤������
          esTdAF.setCardKind((BusiTools.getBusiValue(Integer.parseInt(esTdAF
              .getCardKind()), BusiConst.DOCUMENTSSTATE)));
        }
        // ת���Ա�
        if (esTdAF.getSex() != null && !esTdAF.getSex().equals("")) {
          // ת��֤������
          esTdAF.setSex((BusiTools.getBusiValue(Integer.parseInt(esTdAF
              .getSex()), BusiConst.SEX)));
        }
      }
    }
    endorsecontractTdAF.setContractId(id);
    endorsecontractTdAF.setDebitter(debitter);
    endorsecontractTdAF.setList(list);
    String tempCardkind = endorsecontractTdAF.getCardKind();
    if (tempCardkind != null && !"".equals(tempCardkind)) {
      endorsecontractTdAF.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// ҳ����ʾ����֤������
    }
    String tempSex = endorsecontractTdAF.getSex();
    if (tempSex != null && !"".equals(tempSex)) {
      endorsecontractTdAF.setSex(tempSex);
    }
    return endorsecontractTdAF;
  }

  // ���
  public EndorsecontractTdAF queryAssurerList_Chg(String id,
      Pagination pagination, SecurityInfo securityInfo,
      HttpServletRequest request) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    // ��ѯPL123��ͬ���Ϊ������ͬ��ţ�IDΪ���ֵ����Ϣ
    String maxId = "";
    maxId = (String) request.getSession().getAttribute("pl123Id");
    if (maxId == null || "".equals(maxId)) {
      maxId = assurerDAO.queryMaxId(id);
    }
    String debitter = borrowerDAO.queryBorrowerName(id);// ���ݺ�ͬID��ѯ���������
    if (maxId != null && !"".equals(maxId)) {
      request.getSession().setAttribute("pl123Id", maxId);
      endorsecontractTdAF = this.queryAssurerById(maxId);// �������ID��ѯpl123
      // String sex = endorsecontractTdAF.getSex();
      // String tempSex = "";
      // if (sex != null && !"".equals(sex)) {
      // tempSex = BusiTools.getBusiValue(Integer.parseInt(sex), BusiConst.SEX);
      // }
      // endorsecontractTdAF.setSex(sex);
    }
    // ��ѯ�б�
    List list = assurerDAO.queryAssurerListYU(id);
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++) {
        EndorsecontractTdAF esTdAF = (EndorsecontractTdAF) list.get(i);
        String statuss = "";
        String tempStatuss = esTdAF.getStatus();
        if (tempStatuss != null && !"".equals(tempStatuss)) {
          statuss = BusiTools.getBusiValue(Integer.parseInt(tempStatuss),
              BusiConst.PLCOMMONSTATUS_1);
          esTdAF.setStatus(statuss);
        }
      }
    }
    endorsecontractTdAF.setContractId(id);
    endorsecontractTdAF.setDebitter(debitter);
    endorsecontractTdAF.setList(list);
    // String tempCardkind=endorsecontractTdAF.getCardKind();
    // if(tempCardkind != null && !"".equals(tempCardkind)){
    // endorsecontractTdAF.setCardKind(BusiTools.getBusiValue(Integer
    // .parseInt("" + tempCardkind), BusiConst.DOCUMENTSSTATE));// ҳ����ʾ����֤������
    // }

    return endorsecontractTdAF;
  }

  /**
   * �ַ� ȷ�ϰ�ť
   */
  public void sureTd(String id, SecurityInfo securityInfo,
      EndorsecontractTdAF endorsecontractTdAF) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    // ����ID��pl121Id������ѯPL121
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER + "";

    String contractId = endorsecontractTdAF.getContractId().trim();// ��ͬID
    String debitter = endorsecontractTdAF.getDebitter().trim();// ��������� PL110
    String empId = endorsecontractTdAF.getEmpId().trim();// ְ�����
    String empName = endorsecontractTdAF.getEmpName().trim();// ְ������
    String cardKind = endorsecontractTdAF.getCardKind().trim();// ֤������
    String cardNum = endorsecontractTdAF.getCardNum().trim();// ֤������
    String sex = endorsecontractTdAF.getSex().trim();// �Ա�
    String birthday = endorsecontractTdAF.getBirthday().trim();// ��������
    String salary = endorsecontractTdAF.getSalary().trim();// �¹��ʶ�
    String monthPay = endorsecontractTdAF.getMonthPay().trim();// �½ɴ��
    String balance = endorsecontractTdAF.getBalance().trim();// �˻����
    String empSt = endorsecontractTdAF.getEmpSt().trim();// �˻�״̬
    String tel = endorsecontractTdAF.getTel().trim();// �̶��绰
    String mobile = endorsecontractTdAF.getMobile().trim();// �ж��绰
    String homeTel = endorsecontractTdAF.getHomeTel().trim();// ��ͥ�绰
    String homeAddr = endorsecontractTdAF.getHomeAddr().trim();// ��ͥסַ
    String homeMail = endorsecontractTdAF.getHomeMai().trim();// ��ͥ�ʱ�
    String orgId = endorsecontractTdAF.getOrgId().trim();// ��λ���
    String orgName = endorsecontractTdAF.getOrgName().trim();// ��λ����
    String orgAddr = endorsecontractTdAF.getOrgAddr().trim();// ��λ��ַ
    String orgTel = endorsecontractTdAF.getOrgTel().trim();// ��λ�绰
    String orgMail = endorsecontractTdAF.getOrgMail().trim();// ��λ�������

    Assurer assurer = null;
    // if (id != null && !"".equals(id)) {
    // assurer = assurerDAO.queryById(new Integer(id));// ������ѯ

    // }
    assurer = assurerDAO.queryCautionerInfo(contractId, empName, cardNum);
    if (assurer != null) {// ���ڣ�����
      if (empId != null && !"".equals(empId)) {
        assurer.setEmpId(new BigDecimal(empId));
      }
      if (empName != null && !"".equals(empName)) {
        assurer.setEmpName(empName);
      }
      if (cardKind != null && !"".equals(cardKind)) {
        assurer.setCardKind(cardKind);
      }
      if (cardNum != null && !"".equals(cardNum)) {
        assurer.setCardNum(cardNum);
      }
      if (sex != null && !"".equals(sex)) {
        assurer.setSex(sex);
      }
      if (birthday != null && !"".equals(birthday)) {
        assurer.setBirthday(birthday);
      }
      if (salary != null && !"".equals(salary)) {
        assurer.setSalary(new BigDecimal(salary));
      }
      if (monthPay != null && !"".equals(monthPay)) {
        assurer.setMonthPay(new BigDecimal(monthPay));
      }
      if (balance != null && !"".equals(balance)) {
        assurer.setBalance(new BigDecimal(balance));
      }
      if (empSt != null && !"".equals(empSt)) {
        int tempEmpSt = BusiTools.getBusiKey(empSt, BusiConst.OLDPAYMENTSTATE);
        assurer.setEmpSt(new Integer(tempEmpSt).toString());
      }
      if (tel != null && !"".equals(tel)) {
        assurer.setTel(tel);
      }
      if (mobile != null && !"".equals(mobile)) {
        assurer.setMobile(mobile);
      }
      if (homeTel != null && !"".equals(homeTel)) {
        assurer.setHomeTel(homeTel);
      }
      if (homeAddr != null && !"".equals(homeAddr)) {
        assurer.setHomeAddr(homeAddr);
      }
      if (homeMail != null && !"".equals(homeMail)) {
        assurer.setHomeMail(homeMail);
      }
      if (orgId != null && !"".equals(orgId)) {
        assurer.setOrgId(new BigDecimal(orgId));
      }
      if (orgName != null && !"".equals(orgName)) {
        assurer.setOrgName(orgName);
      }
      if (orgAddr != null && !"".equals(orgAddr)) {
        assurer.setOrgAddr(orgAddr);
      }
      if (orgTel != null && !"".equals(orgTel)) {
        assurer.setOrgTel(orgTel);
      }
      if (orgMail != null && !"".equals(orgMail)) {
        assurer.setOrgMail(orgMail);
      }
      assurer.setOperator(operator);// ����Ա
      assurer.setOpTime(new Date());// ��������
      String button = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
      String bizId = assurer.getId().toString();
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);

    } else {// �����ڣ�����

      assurer = new Assurer();
      if (contractId != null && !"".equals(contractId)) {
        assurer.setContractId(contractId);
      }
      // if(debitter != null && !"".equals(debitter)){
      // Borrower borrower = new Borrower();
      // borrower.setContractId(contractId);
      // borrower.setBorrowerName(debitter);
      // borrowerDAO.insert(borrower);
      // }
      if (empId != null && !"".equals(empId)) {
        assurer.setEmpId(new BigDecimal(empId));
      }
      if (empName != null && !"".equals(empName)) {
        assurer.setEmpName(empName);
      }
      if (cardKind != null && !"".equals(cardKind)) {
        assurer.setCardKind(cardKind);
      }
      if (cardNum != null && !"".equals(cardNum)) {
        assurer.setCardNum(cardNum);
      }
      if (sex != null && !"".equals(sex)) {
        assurer.setSex(sex);
      }
      if (birthday != null && !"".equals(birthday)) {
        assurer.setBirthday(birthday);
      }
      if (salary != null && !"".equals(salary)) {
        assurer.setSalary(new BigDecimal(salary));
      }
      if (monthPay != null && !"".equals(monthPay)) {
        assurer.setMonthPay(new BigDecimal(monthPay));
      }
      if (balance != null && !"".equals(balance)) {
        assurer.setBalance(new BigDecimal(balance));
      }
      if (empSt != null && !"".equals(empSt)) {
        int tempEmpSt = BusiTools.getBusiKey(empSt, BusiConst.OLDPAYMENTSTATE);
        assurer.setEmpSt(new Integer(tempEmpSt).toString());
      }
      if (tel != null && !"".equals(tel)) {
        assurer.setTel(tel);
      }
      if (mobile != null && !"".equals(mobile)) {
        assurer.setMobile(mobile);
      }
      if (homeTel != null && !"".equals(homeTel)) {
        assurer.setHomeTel(homeTel);
      }
      if (homeAddr != null && !"".equals(homeAddr)) {
        assurer.setHomeAddr(homeAddr);
      }
      if (homeMail != null && !"".equals(homeMail)) {
        assurer.setHomeMail(homeMail);
      }
      if (orgId != null && !"".equals(orgId)) {
        assurer.setOrgId(new BigDecimal(orgId));
      }
      if (orgName != null && !"".equals(orgName)) {
        assurer.setOrgName(orgName);
      }
      if (orgAddr != null && !"".equals(orgAddr)) {
        assurer.setOrgAddr(orgAddr);
      }
      if (orgTel != null && !"".equals(orgTel)) {
        assurer.setOrgTel(orgTel);
      }
      if (orgMail != null && !"".equals(orgMail)) {
        assurer.setOrgMail(orgMail);
      }
      assurer.setStatus("0");// ��֤��״̬ 0����
      assurer.setOperator(operator);// ����Ա
      assurer.setOpTime(new Date());// ��������
      String assurerId = assurerDAO.insert(assurer).toString();
      String button = BusiLogConst.BIZLOG_ACTION_ADD + "";
      String bizId = assurerId;
      this.addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
    }
  }

  /**
   * �ַ��޸�
   */
  public EndorsecontractTdAF updateAssurer(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    endorsecontractTdAF = this.queryAssurerById(id);
    return endorsecontractTdAF;
  }

  /**
   * �ַ�ɾ��
   */
  public void deleteAssurer(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    Assurer assurer = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER + "";
    if (id != null && !"".equals(id)) {
      assurer = assurerDAO.queryById(new Integer(id));
      String bizId = assurer.getContractId();
      if (assurer != null) {
        assurerDAO.deleteById(id);
        String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this
            .addPlOperateLog(opSys, model, button, bizId, opIp, operator, bizId);
      } else {
        throw new BusinessException("�ü�¼�Ѿ���ɾ����");
      }
    }
  }

  /**
   * ͨ��ID��EMPID
   */
  public String selectAssurerEmpIdById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String empId = assurerDAO.queryEmpId(id);
    return empId;
  }

  /** ****************************************************************** */
  /** **** ά�� **** */
  /** ****************************************************************** */
  /**
   * ά����ѯ
   */
  public EndorsecontractTeAF queryList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    int count = 0;
    List list = new ArrayList();
    EndorsecontractTeAF endorsecontractTeAF = new EndorsecontractTeAF();
    HashMap queryCriterions = new HashMap();
    String type = this.queryParamValue(securityInfo);
    queryCriterions = (HashMap) pagination.getQueryCriterions();
    /**
     * Ӫ��ΪAB (�����޸�BA���������޸Ĳ���AB����)
     */
    if (type != null) {
      if (!type.equals("AB")) {
        if (queryCriterions.size() != 0) {// ��������ѯ
          list = this.queryTeList(pagination, securityInfo);
          count = this.queryCountTelist(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
        } else {// ��������ѯ
          // ��ѯPL111 �Ƿ����Ƿ�ǩ����0 ��ͬ״̬��2 �ļ�¼
          // pl111list = borrowerAccDAO.queryBorrowerListYU(securityInfo);
          // if (pl111list.size() != 0) {// ����
          list = this.queryTe02List(pagination, securityInfo);
          count = this.queryCountTe02list(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
          // } else {
          // return new EndorsecontractTeAF();
          // }
        }
      } else {
        // ��ѯPL111 �Ƿ����Ƿ�ǩ����0 ��ͬ״̬��4 �ļ�¼
        if (queryCriterions.size() != 0) {// ��������ѯ
          list = this.queryTeList(pagination, securityInfo);
          count = this.queryCountTelist(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
        } else {// ��������ѯ
        // pl111list = borrowerAccDAO.queryBorrowerListYU_(securityInfo);
        // if (pl111list.size() != 0) {// ����
          list = this.queryTe04List(pagination, securityInfo);
          count = this.queryCountTe04list(pagination, securityInfo);
          pagination.setNrOfElements(count);
          endorsecontractTeAF.setList(list);
          // } else {
          // return new EndorsecontractTeAF();
          // }
        }
      }
    }
    List templist = new ArrayList();
    templist = endorsecontractTeAF.getList();
    if (templist != null) {
      EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
      for (int i = 0; i < templist.size(); i++) {
        endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
        // ת����ͬ״̬
        endorsecontractTeDTO.setStrContractSt((BusiTools.getBusiValue(Integer
            .parseInt(endorsecontractTeDTO.getContractSt()),
            BusiConst.PLCONTRACTSTATUS)));
        String paramv = loanBankParaDAO.queryParam_value_hanl();
        String contractidhl = loanContractDAO.queryByIdYU(endorsecontractTeDTO
            .getContractId());
        if (paramv.equals("BA") && contractidhl == null) {
          endorsecontractTeDTO.setIsContract("1");// ά��ҳ���ϵĲ�����ǩ����ť����
        } else {
          endorsecontractTeDTO.setIsContract("2");// ά��ҳ���ϵĲ�����ǩ����ť������
        }

        String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
        String term = endorsecontractTeDTO.getLoanTimeLimit();
        int tempDebitMoneyEndDate = 0;
        String postfix = "";
        String debitMoneyEndDate = "";
        BigDecimal tempBig = new BigDecimal(0.00);
        BigDecimal hundred = new BigDecimal(100);
        int intPostfix = 0;
        if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
            && term != null && !"".equals(term)) {
          postfix = debitMoneyStaDate.substring(6, 8);
          int temp_addYear = (Integer.parseInt(debitMoneyStaDate
              .substring(4, 6)) + Integer.parseInt(term)) / 12;
          int temp_moth = (Integer.parseInt(debitMoneyStaDate.substring(4, 6)) + Integer
              .parseInt(term)) % 12;
          intPostfix = Integer.parseInt(postfix);
          Integer tempInteger1 = new Integer(debitMoneyStaDate.substring(0, 6));
          Integer tempInteger2 = new Integer(term);
          // int tempInt1 = tempInteger1.intValue();
          // int tempInt2 = tempInteger2.intValue();
          // tempDebitMoneyEndDate = tempInt1 + tempInt2;
          if (temp_moth < 10) {
            if (temp_moth == 0) {
              tempDebitMoneyEndDate = new Integer((Integer
                  .parseInt(debitMoneyStaDate.substring(0, 4))
                  + temp_addYear - 1)
                  + "" + "12").intValue();
            } else {
              tempDebitMoneyEndDate = new Integer((Integer
                  .parseInt(debitMoneyStaDate.substring(0, 4)) + temp_addYear)
                  + "" + "0" + temp_moth).intValue();
            }
          } else {
            tempDebitMoneyEndDate = new Integer((Integer
                .parseInt(debitMoneyStaDate.substring(0, 4)) + temp_addYear)
                + "" + temp_moth).intValue();
          }
          String subString = new Integer(tempDebitMoneyEndDate).toString()
              .substring(4, 6);
          if ("01".equals(subString) || "03".equals(subString)
              || "05".equals(subString) || "07".equals(subString)
              || "08".equals(subString) || "10".equals(subString)
              || "12".equals(subString)) {
            if (intPostfix <= 31) {
              debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                  + postfix;
            }
          } else if ("04".equals(subString) || "06".equals(subString)
              || "09".equals(subString) || "11".equals(subString)) {
            if (intPostfix <= 30) {// �����С�£��ж������Ƿ�С�ڵ���30���Ǿͼ���Ĭ�ϵģ���ͼ�30
              debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                  + postfix;
            } else {
              debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                  + "30";
            }
          } else if ("02".equals(subString)) {
            debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()
                + "28";
          }
          // debitMoneyEndDate = new
          // Integer(tempDebitMoneyEndDate).toString()+postfix;
          endorsecontractTeDTO.setEndDate(debitMoneyEndDate);
        }
        String loanMonthRate = endorsecontractTeDTO.getLoanMonthRate();
        if (loanMonthRate != null && !"".equals(loanMonthRate)) {
          tempBig = new BigDecimal(loanMonthRate).multiply(hundred);
        }
        endorsecontractTeDTO.setTemploanMonthRate(tempBig.toString() + "%");
      }
    }
    return endorsecontractTeAF;
  }

  // 0,2ʱ����
  public List queryTe02List(Pagination pagination, SecurityInfo securityInfo) {
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");// ��ͬ���
      String debitter = (String) pagination.getQueryCriterions()
          .get("debitter");// ���������

      String empId = (String) pagination.getQueryCriterions().get("empId");// ְ�����
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// ֤������
      String bank = (String) pagination.getQueryCriterions().get("bank");// �ſ�����
      String loanTimeLimit = (String) pagination.getQueryCriterions().get(
          "loanTimeLimit");// �������
      String startSDate = (String) pagination.getQueryCriterions().get(
          "startSDate");// ��ʼ1����
      String startEDate = (String) pagination.getQueryCriterions().get(
          "startEDate");// ��ʼ2����
      String endSDate = (String) pagination.getQueryCriterions()
          .get("endSDate");// ��������
      String endEDate = (String) pagination.getQueryCriterions()
          .get("endEDate");// ��������

      list = borrowerDAO.queryTeList02YU(contractId, debitter, empId, cardNum,
          bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate,
          start, orderBy, order, pageSize, page, securityInfo);
      if (list.size() != 0) {
        EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
        for (int i = 0; i < list.size(); i++) {
          endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
          String bankId = endorsecontractTeDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          String bankName = collBank.getCollBankName();
          endorsecontractTeDTO.setBank(bankName);
          endorsecontractTeDTO.setBankid(bankId);
          // ��ͬ״̬ö��ת��
          String ContractSt = endorsecontractTeDTO.getContractStatus();
          endorsecontractTeDTO.setContractStatus(BusiTools.getBusiValue(Integer
              .parseInt(ContractSt + ""), BusiConst.PLISCONTRACTWRITE));
          // // ���ݽ����ʼ���ڣ�������޼��㻹����ֹ����
          // String debitMoneyEndDate = "";
          // String postfix = "";
          // int tempDebitMoneyEndDate = 0;
          // String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
          // String term = endorsecontractTeDTO.getLoanTimeLimit();
          // if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          // && term != null && !"".equals(term)) {
          // Integer tempInteger1 = new Integer(debitMoneyStaDate.substring(0,
          // 6));
          // Integer tempInteger2 = new Integer(term);
          // int tempInt1 = tempInteger1.intValue();
          // int tempInt2 = tempInteger2.intValue();
          // tempDebitMoneyEndDate = tempInt1 + tempInt2;
          // debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString();
          // endorsecontractTeDTO.setEndDate(debitMoneyEndDate
          // + debitMoneyStaDate.substring(6, 8));
          // }
          if (Integer.parseInt(endorsecontractTeDTO.getLoanMode()) > 3) {
            endorsecontractTeDTO.setEntireYearMoney(endorsecontractTeDTO
                .getCorpusInterest());
            endorsecontractTeDTO.setCorpusInterest("0");
          } else {
            endorsecontractTeDTO.setEntireYearMoney("����������������");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // count ��¼
  public int queryCountTe02list(Pagination pagination, SecurityInfo securityInfo) {
    int i = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");// ��ͬ���
    String debitter = (String) pagination.getQueryCriterions().get("debitter");// ���������

    String empId = (String) pagination.getQueryCriterions().get("empId");// ְ�����
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// ֤������
    String bank = (String) pagination.getQueryCriterions().get("bank");// �ſ�����
    String loanTimeLimit = (String) pagination.getQueryCriterions().get(
        "loanTimeLimit");// �������
    String startSDate = (String) pagination.getQueryCriterions().get(
        "startSDate");// ��ʼ1����
    String startEDate = (String) pagination.getQueryCriterions().get(
        "startEDate");// ��ʼ2����
    String endSDate = (String) pagination.getQueryCriterions().get("endSDate");// ��������
    String endEDate = (String) pagination.getQueryCriterions().get("endEDate");// ��������

    i = borrowerDAO.queryCountTeList02YU(contractId, debitter, empId, cardNum,
        bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate, start,
        orderBy, order, pageSize, page, securityInfo);
    return i;
  }

  // 0��4ʱ����
  public List queryTe04List(Pagination pagination, SecurityInfo securityInfo) {
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");// ��ͬ���
      String debitter = (String) pagination.getQueryCriterions()
          .get("debitter");// ���������

      String empId = (String) pagination.getQueryCriterions().get("empId");// ְ�����
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// ֤������
      String bank = (String) pagination.getQueryCriterions().get("bank");// �ſ�����
      String loanTimeLimit = (String) pagination.getQueryCriterions().get(
          "loanTimeLimit");// �������
      String startSDate = (String) pagination.getQueryCriterions().get(
          "startSDate");// ��ʼ1����
      String startEDate = (String) pagination.getQueryCriterions().get(
          "startEDate");// ��ʼ2����
      String endSDate = (String) pagination.getQueryCriterions()
          .get("endSDate");// ��������
      String endEDate = (String) pagination.getQueryCriterions()
          .get("endEDate");// ��������

      list = borrowerDAO.queryTeList04YU(contractId, debitter, empId, cardNum,
          bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate,
          start, orderBy, order, pageSize, page, securityInfo);
      if (list.size() != 0) {
        EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
        for (int i = 0; i < list.size(); i++) {
          endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
          String bankId = endorsecontractTeDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          String bankName = "";
          if (collBank != null && !"".equals(collBank)) {
            bankName = collBank.getCollBankName();
          }
          endorsecontractTeDTO.setBank(bankName);
          endorsecontractTeDTO.setBankid(bankId);
          // ��ͬ״̬ö��ת��
          String ContractSt = endorsecontractTeDTO.getContractStatus();
          endorsecontractTeDTO.setContractStatus(BusiTools.getBusiValue(Integer
              .parseInt(ContractSt + ""), BusiConst.PLISCONTRACTWRITE));
          // ���ݽ����ʼ���ڣ�������޼��㻹����ֹ����
          // String debitMoneyEndDate = "";
          // String postfix = "";
          // String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
          // String term = endorsecontractTeDTO.getLoanTimeLimit();
          // if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          // && term != null && !"".equals(term)) {
          // postfix = debitMoneyStaDate.substring(6, 8);
          // int tempTerm = new Integer(term).intValue();
          // debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate,
          // tempTerm);
          // endorsecontractTeDTO.setEndDate(debitMoneyEndDate
          // + postfix);
          // }
          if (Integer.parseInt(endorsecontractTeDTO.getLoanMode()) > 3) {
            endorsecontractTeDTO.setEntireYearMoney(endorsecontractTeDTO
                .getCorpusInterest());
            endorsecontractTeDTO.setCorpusInterest("0");
          } else {
            endorsecontractTeDTO.setEntireYearMoney("����������������");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // count ��¼
  public int queryCountTe04list(Pagination pagination, SecurityInfo securityInfo) {
    int i = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");// ��ͬ���
    String debitter = (String) pagination.getQueryCriterions().get("debitter");// ���������

    String empId = (String) pagination.getQueryCriterions().get("empId");// ְ�����
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// ֤������
    String bank = (String) pagination.getQueryCriterions().get("bank");// �ſ�����
    String loanTimeLimit = (String) pagination.getQueryCriterions().get(
        "loanTimeLimit");// �������
    String startSDate = (String) pagination.getQueryCriterions().get(
        "startSDate");// ��ʼ1����
    String startEDate = (String) pagination.getQueryCriterions().get(
        "startEDate");// ��ʼ2����
    String endSDate = (String) pagination.getQueryCriterions().get("endSDate");// ��������
    String endEDate = (String) pagination.getQueryCriterions().get("endEDate");// ��������

    i = borrowerDAO.queryCountTeList04YU(contractId, debitter, empId, cardNum,
        bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate, start,
        orderBy, order, pageSize, page, securityInfo);
    return i;
  }

  // ��������ѯ
  public List queryTeList(Pagination pagination, SecurityInfo securityInfo) {
    List list = new ArrayList();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");// ��ͬ���
      String debitter = (String) pagination.getQueryCriterions()
          .get("debitter");// ���������

      String empId = (String) pagination.getQueryCriterions().get("empId");// ְ�����
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// ֤������
      String bank = (String) pagination.getQueryCriterions().get("bank");// �ſ�����
      String loanTimeLimit = (String) pagination.getQueryCriterions().get(
          "loanTimeLimit");// �������
      String startSDate = (String) pagination.getQueryCriterions().get(
          "startSDate");// ��ʼ1����
      String startEDate = (String) pagination.getQueryCriterions().get(
          "startEDate");// ��ʼ2����
      String endSDate = (String) pagination.getQueryCriterions()
          .get("endSDate");// ��������
      String endEDate = (String) pagination.getQueryCriterions()
          .get("endEDate");// ��������
      String contractSt = (String) pagination.getQueryCriterions().get(
          "contractSt");// ��ͬ״̬
      list = borrowerDAO.queryTeListYU(contractId, debitter, empId, cardNum,
          bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate,
          start, orderBy, order, pageSize, page, securityInfo, contractSt);
      if (list.size() != 0) {
        EndorsecontractTeDTO endorsecontractTeDTO = new EndorsecontractTeDTO();
        for (int i = 0; i < list.size(); i++) {
          endorsecontractTeDTO = (EndorsecontractTeDTO) list.get(i);
          String bankId = endorsecontractTeDTO.getBank();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          String bankName = "";
          if (collBank != null && !"".equals(collBank)) {
            bankName = collBank.getCollBankName();
          }
          endorsecontractTeDTO.setBank(bankName);
          endorsecontractTeDTO.setBankid(bankId);
          // ��ͬ״̬ö��ת��
          String ContractSt = endorsecontractTeDTO.getContractStatus();
          endorsecontractTeDTO.setContractStatus(BusiTools.getBusiValue(Integer
              .parseInt(ContractSt + ""), BusiConst.PLISCONTRACTWRITE));
          // ���ݽ����ʼ���ڣ�������޼��㻹����ֹ����
          // String debitMoneyEndDate = "";
          // String postfix = "";
          // int tempDebitMoneyEndDate = 0;
          // String debitMoneyStaDate = endorsecontractTeDTO.getStartDate();
          // String term = endorsecontractTeDTO.getLoanTimeLimit();
          // if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          // && term != null && !"".equals(term)) {
          // postfix = debitMoneyStaDate.substring(6, 8);
          // int tempTerm = new Integer(term).intValue();
          // debitMoneyEndDate = BusiTools.addMonth(debitMoneyStaDate,
          // tempTerm);
          // endorsecontractTeDTO.setEndDate(debitMoneyEndDate
          // + postfix);
          // }
          if (Integer.parseInt(endorsecontractTeDTO.getLoanMode()) > 3) {
            endorsecontractTeDTO.setEntireYearMoney(endorsecontractTeDTO
                .getCorpusInterest());
            endorsecontractTeDTO.setCorpusInterest("0");
          } else {
            endorsecontractTeDTO.setEntireYearMoney("����������������");
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // count ��¼��������ѯ
  public int queryCountTelist(Pagination pagination, SecurityInfo securityInfo) {
    int i = 0;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");// ��ͬ���
    String debitter = (String) pagination.getQueryCriterions().get("debitter");// ���������

    String empId = (String) pagination.getQueryCriterions().get("empId");// ְ�����
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");// ֤������
    String bank = (String) pagination.getQueryCriterions().get("bank");// �ſ�����
    String loanTimeLimit = (String) pagination.getQueryCriterions().get(
        "loanTimeLimit");// �������
    String startSDate = (String) pagination.getQueryCriterions().get(
        "startSDate");// ��ʼ1����
    String startEDate = (String) pagination.getQueryCriterions().get(
        "startEDate");// ��ʼ2����
    String endSDate = (String) pagination.getQueryCriterions().get("endSDate");// ��������
    String endEDate = (String) pagination.getQueryCriterions().get("endEDate");// ��������
    String contractSt = (String) pagination.getQueryCriterions().get(
        "contractSt");// ��ͬ״̬
    i = borrowerDAO.queryCountTeListYU(contractId, debitter, empId, cardNum,
        bank, loanTimeLimit, startSDate, startEDate, endSDate, endEDate, start,
        orderBy, order, pageSize, page, securityInfo, contractSt);
    return i;
  }

  /**
   * ɾ��
   */
  public void deleteContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN
        + "";
    String button = BusiLogConst.BIZLOG_ACTION_DELETE + "";
    String opBizId = id;
    String bizId = id;
    BigDecimal zero = new BigDecimal(0);
    try {
      if (id != null && !"".equals(id)) {
        borrowerAcc = borrowerAccDAO.queryById(id);// ��ѯPL111
        if (borrowerAcc != null) {
          String isWrite = borrowerAcc.getIsContractWrite();
          if (isWrite.equals("0")) {
            borrowerAcc.setLoanBankId(zero);// ��PL111 ��������Ϊ0
            borrowerAcc.setLoanKouAcc("");
            loanContractDAO.deleteById(id);// ɾ��pl120
            List list = pledgeContractDAO.queryIdByContractIdYU(id);// ��ѯpl121ID
            if (list.size() != 0) {
              for (int i = 0; i < list.size(); i++) {
                String pl121Id = (String) list.get(i);
                pledgeContractDAO.deleteById(pl121Id);// ɾ��PL121
              }
            }
            List list2 = impawnContractDAO.queryIdByContractIdYU(id);
            if (list2.size() != 0) {
              for (int i = 0; i < list2.size(); i++) {
                String pl122Id = (String) list2.get(i);
                impawnContractDAO.deleteById(pl122Id);// ɾ��pl122
              }
            }
            List list3 = assurerDAO.queryIdByContractIdYU(id);
            if (list3.size() != 0) {
              for (int i = 0; i < list3.size(); i++) {
                BigDecimal pl123Id = (BigDecimal) list3.get(i);
                assurerDAO.deleteById(pl123Id.toString());// ɾ��PL123
              }
            }

            this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
                opBizId);
          } else if (isWrite.equals("1")) {
            throw new BusinessException("��ͬ��ǩ��������ɾ����");
          }
        } else {
          throw new BusinessException("�ü�¼�Ѿ���ɾ����");
        }
      }
    } catch (Exception e) {
      throw new BusinessException("ɾ��ʧ�ܣ�");
    }
  }

  /**
   * ǩ����ͬ
   */
  public void sureContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN
        + "";
    String button = BusiLogConst.BIZLOG_ACTION_OPENING + "";
    String opBizId = id;
    String bizId = id;
    if (id != null && !"".equals(id)) {
      borrowerAcc = borrowerAccDAO.queryById(id);// ��ѯPL111
      if (borrowerAcc != null) {
        String isWrite = borrowerAcc.getIsContractWrite();
        if (isWrite.equals("0")) {
          borrowerAcc.setIsContractWrite("1");
          this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
              opBizId);
        } else {
          throw new BusinessException("�Ѿ�ǩ���ˣ�");
        }
      } else {
        throw new BusinessException("�Ѿ�ǩ���ˣ�");
      }
    }
  }

  /**
   * ����ǩ����ͬ
   */
  public void delContract(String id, Pagination pagination,
      SecurityInfo securityInfo, HttpServletRequest request) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    BorrowerAcc borrowerAcc = null;
    String operator = securityInfo.getUserName();
    String opIp = securityInfo.getUserIp();
    String opSys = BusiLogConst.OP_SYSTEM_TYPE_LOAN + "";
    String model = BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN
        + "";
    String button = BusiLogConst.BIZLOG_ACTION_REVOCATION + "";
    String opBizId = id;
    String bizId = id;
    // �жϲ�����������̡��Ƿ���AB��A��������Bǩ�����
    // ����ǣ���ͬ״̬��4�Ŀ��Գ���
    // ���򣺺�ͬ״̬��2�Ŀ��Գ���
    borrowerAcc = borrowerAccDAO.queryById(id);// ��ѯPL111
    Integer loanBankId = new Integer(borrowerAcc.getLoanBankId().toString());
    int contractSt = Integer.parseInt(borrowerAcc.getContractSt());// ��ͬ״̬
    String temp_contractSt = BusiTools.getBusiValue(contractSt,
        BusiConst.PLCONTRACTSTATUS);
    String paramValue = loanBankParaDAO
        .queryParamValue_LJ(loanBankId, "A", "7");
    try {
      if (paramValue.equals(BusiConst.PLLOANPROCESS_LOANAPPROVAL
          + BusiConst.PLLOANPROCESS_CONTRACTSIGN)) {
        if (contractSt != BusiConst.PLCONTRACTSTATUS_THROUGHAPPROVAL) {
          throw new BusinessException("��ͬ״̬Ϊ" + temp_contractSt + "�����ܳ�����");
        } else {
          borrowerAcc.setIsContractWrite("0");
          this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
              opBizId);
        }
      } else {
        if (contractSt != BusiConst.PLCONTRACTSTATUS_FIRSTCHECK) {
          throw new BusinessException("��ͬ״̬Ϊ" + temp_contractSt + "�����ܳ�����");
        } else {
          borrowerAcc.setIsContractWrite("0");
          this.addPlOperateLog(opSys, model, button, bizId, opIp, operator,
              opBizId);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // ���ݺ�ͬ��Ų�ѯ����´�������LIST
  public List queryBankList(String contractId, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    Borrower borrower = null;
    String office = null;
    try {
      // List loanBankNameList = new ArrayList();//����
      List bankList = securityInfo.getDkUserBankList();
      if (bankList.size() != 0) {
        // Userslogincollbank userslogincollbank = null;
        // Iterator bank1 = bankList.iterator();
        if (contractId != null && !"".equals(contractId)) {
          borrower = borrowerDAO.queryById(contractId);
        }
        if (borrower != null) {
          office = borrower.getOffice();
          List banklist = loanBankDAO.queryBank_yqf(office, securityInfo);
          if (!banklist.isEmpty()) {
            Object obj[] = null;
            Iterator it = banklist.iterator();
            while (it.hasNext()) {
              obj = (Object[]) it.next();
              list.add(new org.apache.struts.util.LabelValueBean(obj[1]
                  .toString(), obj[0].toString()));
            }
          }
        }
        // while (bank1.hasNext()) {
        // userslogincollbank = (Userslogincollbank) bank1.next();
        // Integer bankId = userslogincollbank.getCollBankId();
        // String bankName = userslogincollbank.getCollBankName();
        // if(bankId!=null && !"".equals(bankId)){
        // CollBank cb = collBankDAO.getCollBankByCollBankid(bankId.toString());
        // if(cb!=null){
        // String tempoffice = cb.getOffice();
        // if(office!=null){
        // if(office.equals(tempoffice)){
        // list.add(new org.apache.struts.util.LabelValueBean(bankName,
        // bankId.toString()));
        // }
        // }
        // }
        // }
        // // loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
        // // userslogincollbank.getCollBankName(), userslogincollbank
        // // .getCollBankId().toString()));
        // }
      }
      // ----------------------------------------------------------------------------------------
      // List list = new ArrayList();
      // List temp_list = null;
      // Borrower borrower = null;
      // String office = null;
      //
      // if(contractId!=null && !"".equals(contractId)){
      // borrower = borrowerDAO.queryById(contractId);
      // }
      // if (borrower != null) {
      // office = borrower.getOffice();
      // temp_list = collBankDAO.getOfficeCollBankList_yu(office,securityInfo);
      // Iterator bank = temp_list.iterator();
      // while (bank.hasNext()) {
      // CollBank collBank = (CollBank) bank.next();
      // list.add(new org.apache.struts.util.LabelValueBean(collBank
      // .getCollBankName(), collBank.getCollBankId().toString()));
      // }
      // }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ����ta�е�path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTa_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      LoanContract loanContract = loanContractDAO.queryById(contractId);
      if (loanContract != null) {
        loanContract.setPhotoUrl2(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * ����tb�е�path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTb_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      PledgeContract pledgeContract = pledgeContractDAO.queryById(new Integer(contractId));
      if (pledgeContract != null) {
        pledgeContract.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * ����tc�е�path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTc_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      ImpawnContract impawnContract = impawnContractDAO.queryById(new Integer(
          contractId));
      if (impawnContract != null) {
        impawnContract.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * ����td�е�path
   * 
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTd_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      Assurer assurer = assurerDAO.queryById(new Integer(contractId));
      if (assurer != null) {
        assurer.setPhotoUrl(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * ������ѡ�����ж��Ƿ�������ʣ������»���Ϣ�����������ֻΪ����ʾ����������� 2007.11.14 shiy
   */
  public EndorsecontractTaChangeLoanMonthRateAF queryLoanMonthRate(
      String bankId, String term, String tempLoanMonthRate, String loanMode,
      String loanMoney) {
    EndorsecontractTaChangeLoanMonthRateAF ectcAF = new EndorsecontractTaChangeLoanMonthRateAF();
    BigDecimal loanMonthRate = new BigDecimal(0.00);
    BigDecimal corpusInterest = new BigDecimal(0.00);
    try {
      // �鿴�����Ƿ�����µ�����
      List list = loanBankParaDAO.queryLoanBankPara_sy(new Integer(bankId));
      // �鿴�Ǵ��ʽ
      if (!list.isEmpty()) {
        if (new Integer(term).intValue() > 60) {
          String loantype = "1"; // 5������
          loanMonthRate = loanRateDAO.findMontRate_sy(bankId, loantype);
        } else {
          String loantype = "0"; // 1��5��
          loanMonthRate = loanRateDAO.findMontRate_sy(bankId, loantype);
        }
      } else {
        loanMonthRate = new BigDecimal(tempLoanMonthRate);
      }
      // �������µ��»���Ϣ
      if (loanMode.equals("2")) {
        corpusInterest = CorpusinterestBS.getCorpusInterest(new BigDecimal(
            loanMoney), loanMonthRate, term);
        ectcAF.setCorpusInterest(corpusInterest.toString());
      }
      ectcAF.setLoanMonthRate(loanMonthRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ectcAF;
  }

  public void updateContractSt(String contractId) throws Exception {
    // TODO Auto-generated method stub
    try {
      BorrowerAcc bacc = borrowerAccDAO.queryById(contractId);
      bacc.setContractSt(String.valueOf(BusiConst.PLCONTRACTSTATUS_APPL));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void updateScanTe_Yqf(String contractId, String path) throws Exception {
    // TODO Auto-generated method stub
    try {
      LoanContract loanContract = loanContractDAO.queryById(contractId);
      if (loanContract != null) {
        loanContract.setPhotoUrl2(path);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
