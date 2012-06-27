package org.xpup.hafmis.sysloan.loancallback.loancallback.business;

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
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action.T;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.FundloanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.RestoreLoan;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTbDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackBS implements ILoancallbackBS {

  private BorrowerAccDAO borrowerAccDAO = null;

  private CollParaDAO collParaDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private LoanContractParaDAO loanContractParaDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private CollBankDAO collBankDAO = null;

  private LoanBankDAO loanBankDAO = null;

  private SecurityDAO securityDAO = null;

  private LoanRateDAO loanRateDAO = null;

  private FundloanInfoDAO fundloanInfoDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private HousesDAO housesDAO = null;

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setLoanContractParaDAO(LoanContractParaDAO loanContractParaDAO) {
    this.loanContractParaDAO = loanContractParaDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public String findBorrowerAcc(String loanKouAcc, String contractSt,
      SecurityInfo securityInfo) {
    String contractId = "";
    contractId = borrowerAccDAO.queryBorrowerAccByLoanKouAcc_LJ(loanKouAcc,
        contractSt, securityInfo);
    return contractId;
  }

  public boolean findContractSt(String contract_id, String contractSt) {
    String st = "";
    if(borrowerAccDAO.queryById(contract_id)!=null){
      st = borrowerAccDAO.queryById(contract_id).getContractSt();
    }
    if (st.equals(contractSt)) {
      return true;
    }
    return false;
  }

  /**
   * �жϻ�����������ڱ������һ�췵�ر������һ��
   * 
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth, String loanRepayDay) {
    String day = loanRepayDay;
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer
        .parseInt(month));
    if (Integer.parseInt(loanRepayDay) > days) {
      day = String.valueOf(days);
    }
    if (day.length() < 2 && Integer.parseInt(day) < 10) {
      day = "0" + day;
    }
    return day;
  }

  /**
   * ��ѯ���������б� ������µ������12��
   * 
   * @param securityInfo
   * @return
   */
  public List getYearMonthList(String loanRepayDay, String contractId,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String yearMonth = bizDate.substring(0, 6);
    List temp_list = restoreLoanDAO
        .countRestoreLoanListByContractId_sy(contractId);
    String loanDay = "";
    try {
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          PrintplanListDTO dto = (PrintplanListDTO) temp_list.get(i);
          ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
          // if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
          // //�����С�ڻ����գ�ȡδ������С�µ��¸���--����µ���һ����
          // if(dto.getLoanKouYearmonth().substring(4,6).equals("12")){
          // shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth().substring(0,4))+1)+"01");
          // }else{
          // shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth())+1));
          // }
          // }else{
          // ����մ��ڵ��ڻ����գ�ȡδ������С��--�����
          loanDay = this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
          if (i == 0) {
            if (Integer.parseInt(yearMonth) < Integer.parseInt(dto
                .getLoanKouYearmonth())) {
              shouldBackListDTO.setLoanKouYearmonth("");
            }
          }
          shouldBackListDTO.setLoanKouYearmonth(dto.getLoanKouYearmonth()
              + loanDay);
          // }
          list.add(shouldBackListDTO);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public LoancallbackTaAF getLoancallbackTaAF(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    List borrowerList = new ArrayList();// �˻���Ϣ
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    List shouldBackList = new ArrayList();
    String yearMonth = bizDate.substring(0, 6);
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    // ��PL110��PL111ȡ��Ϣ
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
    // ���û��ѡ�����������жϻ����պͻ���յĴ�С
    if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
      // �����С�ڻ�����
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
          contractId, yearMonth);
    } else {
      // ����մ��ڵ��ڻ�����
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
          contractId, yearMonth);
    }
    af = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
    return af;
  }

  /**
   * ����ҳ�棬���ݴ����˺Ų�ѯӦ������Ϣ
   */
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    String identifier = (String) pagination.getQueryCriterions().get(
        "identifier");// �����ж��Ƿ������Ի�����ѯ�ĵ���,"consultation"
    String callbackMonth = (String) pagination.getQueryCriterions().get(
        "callbackMonth");
    LoancallbackTaAF af = new LoancallbackTaAF();
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    List shouldBackList = null;// Ӧ����Ϣ
    List bizStList = null;// ���ڲ�ѯ�Ƿ����δ���˵�״̬
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// �������
    String pldebit = "";// �ۿʽ
    Integer loanBankId = null;// �ſ�����
    List borrowerList = new ArrayList();// �˻���Ϣ
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING + "";// ��ͬ״̬ 11.������
    String contractId = "";// ��ͬ���
    String loanRepayDay = "";// ������ ��ȡӦ������Ϣʱ�õ�
    String paramType = "A";// ��������
    int count = 0;// Ӧ�����б��¼��
    // ��ͬ�˺�
    contractId = (String) pagination.getQueryCriterions().get("contractId");
    // ҵ������
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String aheadMoney = (String) pagination.getQueryCriterions().get(
        "aheadMoney");// ��ǰ������
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String temp_bizDate = "";
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    if (callbackMonth != null && !callbackMonth.equals("")) {
      String temp_yearMonth = callbackMonth.substring(0, 6);
      // if(!temp_yearMonth.equals(yearMonth)){
      yearMonth = callbackMonth.substring(0, 6);
      // day=callbackMonth.substring(6,8);
      // }
    }
    String paramValue = "";
    String isAmend = "1";// �Ƿ���Ը���ʵ�ս��(0.�����ԣ�1.����)
    af.setBizType("2");
    String bankDate = "";// ��������
    if (contractId != null && !contractId.equals("")) {
      // �жϴ����˺���PL111���Ƿ���ڲ���״̬Ϊ�����С�
      if (!findContractSt(contractId, contractSt)) {
        throw new BusinessException("�˺�ͬ״̬���Ի򲻴��ڻ򲻿��԰������ҵ��");
      }
      pagination.getQueryCriterions().put("contractId", contractId);
      // �ô����˺��ڴ�����ˮ��ͷ��PL202�����Ƿ����BIZ_ST!=6(δ����)������������ˮ��β��PL203��
      if (identifier == null) {
        bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,
            null);
        if (!bizStList.isEmpty()) {
          throw new BusinessException("����δ���˵�ҵ��");
        }
      }
      
      // ��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      pagination.getQueryCriterions().put("loanKouAcc",
          borrowerInfoDTO.getLoanKouAcc());
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
      loanBankId = borrowerInfoDTO.getLoanBankId();
      int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
      String clearYear = this.getClearYear(String.valueOf(loanBankId));
      // ������-1���������PL002�е����������������
      Integer.parseInt(clearYear);
      if (identifier == null) {
        bankDate = loanBankDAO.queryPL002BizDate_jj(loanBankId.toString());
        if (!bizDate.equals(bankDate)) {
          throw new BusinessException("��¼����������ҵ�����ڲ�һ�£�������ҵ��");
        }
        if (iClearYear > Integer.parseInt(clearYear)) {
          throw new BusinessException(iClearYear + "����δ��ᣬ��������л���ҵ��");
        }
      }
      String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      List yearMonthList = this.getYearMonthList(loanRepayDay, contractId,
          securityInfo);
      // ��PL003�в�ѯ�ۿʽ(ȫ��ۿ���ۿ�)
      pldebit = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "1");
      // ��PL201�в�Ӧ����Ϣ
      // if(identifier!=null){
      // //������ѯ��ѯ����Ӧ����Ϣ
      // shouldBackList= restoreLoanDAO.queryRestoreLoanListByContractId_LJC(
      // contractId, yearMonth.substring(0,4));
      // }else
      if (callbackMonth == null) {
        // ���û��ѡ�����������жϻ����պͻ���յĴ�С
        if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
          // �����С�ڻ�����
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
              contractId, yearMonth);
        } else {
          // ����մ��ڵ��ڻ�����
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
              contractId, yearMonth);
        }
      } else {
        // ����մ��ڵ��ڻ�����
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
            contractId, yearMonth);
      }
      // ���ʻ���2
      if (bizType == null) {
        bizType = BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "";
      }
      // �õ������Ϣ������������list
      LoancallbackTaAF af1 = this.findCallbackList(shouldBackList,
          borrowerInfoDTO, bizDate);

      // ���ʻ���2
      if (bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
        // �������=������˻���PL111�й�����
        // A.�����������ڵ��ڱ����ܻ��������ʵ�ս��=0�����˷�����=�����ܻ�����
        // B.���������С�ڱ����ܻ��������ʵ�ս��=�����ܻ�����-������� �����PL003������Ϊ��������������Ϊ1�еĲ���ֵ=2��
        // ȫ��ۿ����ʵ�ս������޸ģ���Ҫ���ڵ���0С�ڵ���Ĭ����ʾ��ʵ�ս������˷�����=�������
        af.setSumCorpus(af1.getSumCorpus());
        af.setSumInterest(af1.getSumInterest());
        af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
        if (ovaerLoanRepay.doubleValue() >= af.getSumMoney().doubleValue()) {
          af.setRealMoney(new BigDecimal(0.00));
          af.setOverOccurMoney(af.getSumMoney());
        } else {
          af.setRealMoney(af.getSumMoney().subtract(ovaerLoanRepay));
          af.setOverOccurMoney(ovaerLoanRepay);
        }
        try{
          String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
          String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
          String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
          int fuzhusalaryBase=0;
          int borrowerSalaryBase=0;
          if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
            fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
          }
          if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//            if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//              emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//                  fuzhuOrgId.toString());
//            }
            fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
          }
          Borrower borrower_wsh = new Borrower();
          borrower_wsh = borrowerDAO.queryById(contractId);
          if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
            borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
          }
          if(borrowerSalaryBase<=0){
            borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
          }
          if(fuzhusalaryBase>0){
            af.setSumSalary(new BigDecimal(borrowerSalaryBase).add((new BigDecimal(fuzhusalaryBase))));
          }else{
            af.setSumSalary(new BigDecimal(borrowerSalaryBase));
          }
        }catch (Exception e) {
          e.printStackTrace();
        }
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_PARTRECOVER + "")) {
        // ������ǰ����3
        temp_bizDate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 6);
        // loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        String temp_loanRepayDay = this.getEndDay(temp_bizDate, loanRepayDay);
        if (Integer.parseInt(temp_bizDate) > Integer.parseInt(yearMonth)) {
          yearMonth = temp_bizDate;// ��ǰ����Ҫ�ж�Ӧ�����·��Ƿ��ѻ���
          if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
            // �����С�ڻ�����
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJA(contractId, yearMonth);
          } else {
            // ����մ��ڵ��ڻ�����
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJB(contractId, yearMonth);
          }
        }
        af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
        BigDecimal overplusCorpus = new BigDecimal(0.00);// ʣ�౾��
        overplusCorpus = borrowerInfoDTO.getOverplusLoanMoney().subtract(
            af1.getSumCorpus());
        if (!shouldBackList.isEmpty()) {
          ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
              .get(shouldBackList.size() - 1);
          yearMonth = dto.getLoanKouYearmonth();// �������û����������������ʾֵ
        }
        af1.setMonthYearList(yearMonthList);
        af = this.partAheadInfo(bizDate, borrowerInfoDTO, af1, aheadMoney,
            callbackMonth);
        af.setOverplusCorpus(overplusCorpus);
        try{
          String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
          String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
          String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
          int fuzhusalaryBase=0;
          int borrowerSalaryBase=0;
          if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
            fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
          }
          if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//            if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//              emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//                  fuzhuOrgId.toString());
//            }
            fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
          }
          Borrower borrower_wsh = new Borrower();
          borrower_wsh = borrowerDAO.queryById(contractId);
          if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
            borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
          }
          if(borrowerSalaryBase<=0){
            borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
          }
          if(fuzhusalaryBase>0){
            af.setSumSalary(new BigDecimal(borrowerSalaryBase).add((new BigDecimal(fuzhusalaryBase))));
          }else{
            af.setSumSalary(new BigDecimal(borrowerSalaryBase));
          }
        }catch (Exception e) {
          e.printStackTrace();
        }
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_ALLCLEAR + "")) {
        // һ���Ի���4
        temp_bizDate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 6);
        // loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        String temp_loanRepayDay = this.getEndDay(temp_bizDate, loanRepayDay);
        if (Integer.parseInt(temp_bizDate) > Integer.parseInt(yearMonth)) {
          yearMonth = temp_bizDate;
          if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
            // �����С�ڻ�����
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJA(contractId, yearMonth);
          } else {
            // ����մ��ڵ��ڻ�����
            shouldBackList = restoreLoanDAO
                .queryRestoreLoanListByContractId_LJB(contractId, yearMonth);
          }
        }
        if (!shouldBackList.isEmpty()) {
          ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
              .get(shouldBackList.size() - 1);
          yearMonth = dto.getLoanKouYearmonth();// �������û����������������ʾֵ
        }
        af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
        af1.setMonthYearList(yearMonthList);
        af = fullAheadInfo(borrowerInfoDTO, bizDate, af1, callbackMonth);
        try{
          String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
          String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
          String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
          int fuzhusalaryBase=0;
          int borrowerSalaryBase=0;
          if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
            fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
          }
          if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//            if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//              emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//                  fuzhuOrgId.toString());
//            }
            fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
          }
          Borrower borrower_wsh = new Borrower();
          borrower_wsh = borrowerDAO.queryById(contractId);
          if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
            borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
          }
          if(borrowerSalaryBase<=0){
            borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
          }
          if(fuzhusalaryBase>0){
            af.setSumSalary(new BigDecimal(borrowerSalaryBase).add((new BigDecimal(fuzhusalaryBase))));
          }else{
            af.setSumSalary(new BigDecimal(borrowerSalaryBase));
          }
        }catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (!shouldBackList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
            .get(shouldBackList.size() - 1);
        yearMonth = dto.getLoanKouYearmonth();
      }
      // �жϸô����˺ŵ��������������д������PL003���в�������PARAM_TYPE=A:����ά�����������and
      // �������PARAM_NUM=1�Ĳ���ֵPARAM_VALUE�Ƿ�=1:���ۿ�
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "1");
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        isAmend = "0";
      } else if (!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
        isAmend = "0";
      }
      loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      count = af1.getShouldBackList().size();
      pagination.setNrOfElements(count);
      af.setIsAmend(isAmend);
      af.setOvaerLoanRepay(ovaerLoanRepay);
      af.setPldebit(pldebit);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(af1.getShouldBackList());
      af.setBizType(bizType);
      af.setLoanBalance(borrowerInfoDTO.getOverplusLoanMoney());// �������
      af.setMonthYearList(yearMonthList);
      af.setMonthYear(yearMonth + loanRepayDay1);
      pagination.getQueryCriterions().put("callbackMonth", af.getMonthYear());
      if (count <= 0) {
        af.setMonthYear("");
        pagination.getQueryCriterions().put("callbackMonth", null);
      }

      pagination.setNrOfElements(count);
    }
    // ������޸�//2007-3-11

    try {
      if (af != null) {
        if (contractId != "") {
          if (af.getBorrowerInfoDTO().getLoanMode() == "�ȶ��") {

            if (af.getBizType().equals(
                BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
              // ���ʻ���
              String monthYear = af.getMonthYear();
              if (monthYear != "") {
                String contractID = af.getBorrowerInfoDTO().getContractId();
                String shouldCorpus = "0";
                List listRestoreLoan = loanBankDAO
                    .queryRestoreLoanbyCriterions_wuht(contractID, monthYear
                        .substring(0, 6));
                if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
                  RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan
                      .get(0);
                  shouldCorpus = restoreLoan.getShouldCorpus().toString();
                }
                String deadLine = af.getDeadLine();
                List listBorrowerAcc = loanBankDAO
                    .queryBorrowerAccByCcontractId_wuht(contractID);
                if (listBorrowerAcc != null && listBorrowerAcc.size() > 0) {
                  BorrowerAcc borrowerAcc = (BorrowerAcc) listBorrowerAcc
                      .get(0);
                  deadLine = borrowerAcc.getOverplusLimite().toString();
                  if (af.getShouldBackList() != null
                      && af.getShouldBackList().size() > 0) {
                    deadLine = new BigDecimal(deadLine).subtract(
                        new BigDecimal(af.getShouldBackList().size() + ""))
                        .toString();
                  }
                }
                String sumCorpus = af.getSumCorpus().toString();
                LoancallbackTaAF loancallbackTaAF = this
                    .queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht2(
                        shouldCorpus, monthYear, contractID, deadLine,
                        sumCorpus, securityInfo);
                af.setOverplusInterestAll(loancallbackTaAF
                    .getOverplusInterestAll());// ʣ����Ϣ
                List listLoanFlowTail = loanBankDAO
                    .queryLoanFlowTail_wuht(contractID);
                BigDecimal realInterest = new BigDecimal(0.0);
                BigDecimal interestAll = new BigDecimal(0.0);
                BigDecimal sumInterest = af.getSumInterest();
                if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                  for (int i = 0; i < listLoanFlowTail.size(); i++) {
                    LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                        .get(i);
                    realInterest = realInterest.add(loanFlowTail
                        .getRealInterest());
                  }
                }
                interestAll = interestAll.add(realInterest).add(sumInterest)
                    .add(loancallbackTaAF.getOverplusInterestAll());// ����Ϣ
                af.setInterestAll(interestAll);// ����Ϣ
              }
            } else {
              // ������ǰ����һ���Ի���

              String corpusInterest = af.getCorpusInterest().toString();
              String monthYear = af.getMonthYear();
              String contractID = af.getBorrowerInfoDTO().getContractId();
              String deadLine = af.getDeadLine();
              String sumCorpus = af.getSumCorpus().toString();

              List listBorrowerAcc = loanBankDAO
                  .queryBorrowerAccByCcontractId_wuht(contractID);
              if (listBorrowerAcc != null && listBorrowerAcc.size() > 0) {
                BorrowerAcc borrowerAcc = (BorrowerAcc) listBorrowerAcc.get(0);
                deadLine = borrowerAcc.getOverplusLimite().toString();
                if (af.getShouldBackList() != null
                    && af.getShouldBackList().size() > 0) {
                  deadLine = new BigDecimal(deadLine).subtract(
                      new BigDecimal(af.getShouldBackList().size() + ""))
                      .toString();
                }
              }
              LoancallbackTaAF loancallbackTaAF = this
                  .queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht(
                      corpusInterest, monthYear, contractID, deadLine,
                      sumCorpus, securityInfo);
              af.setOverplusInterestAll(loancallbackTaAF
                  .getOverplusInterestAll());// ʣ����Ϣ
              List listLoanFlowTail = loanBankDAO
                  .queryLoanFlowTail_wuht(contractID);
              BigDecimal realInterest = new BigDecimal(0.0);
              BigDecimal interestAll = new BigDecimal(0.0);
              BigDecimal sumInterest = af.getSumInterest();
              if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                for (int i = 0; i < listLoanFlowTail.size(); i++) {
                  LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                      .get(i);
                  realInterest = realInterest.add(loanFlowTail
                      .getRealInterest());
                }
              }
              interestAll = interestAll.add(realInterest).add(sumInterest).add(
                  loancallbackTaAF.getOverplusInterestAll());// ����Ϣ
              af.setInterestAll(interestAll);// ����Ϣ
            }

          } else {

            if (af.getBizType().equals(
                BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
              // ���ʻ���
              String monthYear = af.getMonthYear();
              if (monthYear != "") {
                String corpusInterest = af.getCorpusInterest().toString();
                String contractID = af.getBorrowerInfoDTO().getContractId();
                List listRestoreLoan = loanBankDAO
                    .queryRestoreLoanbyCriterions_wuht(contractID, monthYear
                        .substring(0, 6));
                if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
                  RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan
                      .get(0);
                  corpusInterest = restoreLoan.getShouldCorpus().add(
                      restoreLoan.getShouldInterest()).toString();
                }
                String deadLine = af.getDeadLine();
                List listBorrowerAcc = loanBankDAO
                    .queryBorrowerAccByCcontractId_wuht(contractID);
                if (listBorrowerAcc != null && listBorrowerAcc.size() > 0) {
                  BorrowerAcc borrowerAcc = (BorrowerAcc) listBorrowerAcc
                      .get(0);
                  deadLine = borrowerAcc.getOverplusLimite().toString();
                  if (af.getShouldBackList() != null
                      && af.getShouldBackList().size() > 0) {
                    deadLine = new BigDecimal(deadLine).subtract(
                        new BigDecimal(af.getShouldBackList().size() + ""))
                        .toString();
                  }
                }
                String sumCorpus = af.getSumCorpus().toString();
                LoancallbackTaAF loancallbackTaAF = this
                    .queryTdShow_loancallbackByCriterions_wuht2(corpusInterest,
                        monthYear, contractID, deadLine, sumCorpus,
                        securityInfo);
                af.setOverplusInterestAll(loancallbackTaAF
                    .getOverplusInterestAll());// ʣ����Ϣ
                List listLoanFlowTail = loanBankDAO
                    .queryLoanFlowTail_wuht(contractID);
                BigDecimal realInterest = new BigDecimal(0.0);
                BigDecimal interestAll = new BigDecimal(0.0);
                BigDecimal sumInterest = af.getSumInterest();
                if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                  for (int i = 0; i < listLoanFlowTail.size(); i++) {
                    LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                        .get(i);
                    realInterest = realInterest.add(loanFlowTail
                        .getRealInterest());
                  }
                }
                interestAll = interestAll.add(realInterest).add(sumInterest)
                    .add(loancallbackTaAF.getOverplusInterestAll());// ����Ϣ
                af.setInterestAll(interestAll);// ����Ϣ
              }
            } else {
              // ������ǰ����һ���Ի���

              String corpusInterest = af.getCorpusInterest().toString();
              String monthYear = af.getMonthYear();
              String contractID = af.getBorrowerInfoDTO().getContractId();
              String deadLine = af.getDeadLine();
              String sumCorpus = af.getSumCorpus().toString();

              LoancallbackTaAF loancallbackTaAF = this
                  .queryTdShow_loancallbackByCriterions_wuht(corpusInterest,
                      monthYear, contractID, deadLine, sumCorpus, securityInfo);
              af.setOverplusInterestAll(loancallbackTaAF
                  .getOverplusInterestAll());// ʣ����Ϣ
              List listLoanFlowTail = loanBankDAO
                  .queryLoanFlowTail_wuht(contractID);
              BigDecimal realInterest = new BigDecimal(0.0);
              BigDecimal interestAll = new BigDecimal(0.0);
              BigDecimal sumInterest = af.getSumInterest();
              if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
                for (int i = 0; i < listLoanFlowTail.size(); i++) {
                  LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail
                      .get(i);
                  realInterest = realInterest.add(loanFlowTail
                      .getRealInterest());
                }
              }
              interestAll = interestAll.add(realInterest).add(sumInterest).add(
                  loancallbackTaAF.getOverplusInterestAll());// ����Ϣ
              af.setInterestAll(interestAll);// ����Ϣ
            }
          }
        }
      }
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    // ������޸�//2007-3-11

    return af;
  }

  /**
   * �ж��Ƿ���������ǰ
   * 
   * @param bizDate
   * @param borrowerInfoDTO
   * @param af1
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  // ������ǰ
  public LoancallbackTaAF partAheadInfo(String bizDate,
      BorrowerInfoDTO borrowerInfoDTO, LoancallbackTaAF af1, String aheadMoney,
      String yearMonth) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";// ��������
    String paramValue = "";// ����ֵ
    String paramExplain = "";// ����˵��
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// ��������
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// �ſ�����
    String contractId = borrowerInfoDTO.getContractId();// ��ͬ���
    BigDecimal aheadCorpus = new BigDecimal(0.00);// ��ǰ�����
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);// ʣ�౾��
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
    String temp_loanRepayDay = "";
    String day = bizDate.substring(6, 8);
    List shouldBackList = null;
    List shouldBackList1 = af1.getShouldBackList();
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6),
        loanRepayDay);// ������ڵĻ�����
    overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();
    String chgType = af1.getAheadType();
    if (aheadMoney != null) {
      aheadCorpus = new BigDecimal(aheadMoney);
    } else {
      if (!chgType.equals("3") && !"".equals(chgType)) {
        aheadCorpus = new BigDecimal(0.00);
      } else {
        aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
      }
    }
    String endDate = "";// ��������
    if (!shouldBackList1.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList1
          .get(shouldBackList1.size() - 1);
      endDate = dto.getLoanKouYearmonth().substring(0, 6);// �������û����������������ʾֵ
    }
    if (endDate == "") {
      List monthYearList = af1.getMonthYearList();
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        endDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(endDate, -1);
        }
      } else {
        // ȡ��һ��1��
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          endDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      endDate = BusiTools.addMonth(endDate, 1);
    }
    // ��ǰ����δ���岻������ǰ
    temp_loanRepayDay = this.getEndDay(bizDate.substring(0, 6), loanRepayDay);
    if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
      // �����С�ڻ�����(���ڵ���)
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJD(
          contractId, endDate);
    } else {
      // ����մ��ڵ��ڻ�����
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJE(
          contractId, endDate);
    }
    if (!shouldBackList.isEmpty()) {
      throw new BusinessException("��ǰ����δ���壬��������ǰ���");
    } else {
      paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId,
          paramType, "2", contractId);
      // �жϸô�����ں�ͬ�������PL004���в������=2�Ĳ���ֵ�Ƿ�=1(��������ǰ����)
      if (paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW + "")) {
        // throw new BusinessException("��������ǰ���");
        // ȡ���ò�����Ӧ�Ĳ���˵��PARAM_EXPLAIN���жϣ��������-PL111���з�������LOAN_START_DATE���������Ƿ���ڵ��ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "2", contractId);
        int temp_monthCounts = BusiTools.getDisMonths(bizDate.substring(0, 4)
            + "-" + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8),
            loanStartDate.substring(0, 4) + "-" + loanStartDate.substring(4, 6)
                + "-" + loanStartDate.substring(6, 8));
        if (temp_monthCounts >= Integer.parseInt(paramExplain)) {
          // ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "5", contractId);
          // ���������ǰ�������
          int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, bizDate.substring(0, 4)).intValue();
          if (aheadCounts < Integer.parseInt(paramExplain)) {
            // ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
            paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
                paramType, "6", contractId);
            // ������������ǰ����
            int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
                contractId, null).intValue();
            ;
            if (lineCounts < Integer.parseInt(paramExplain)) {
              // ����
              af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate,
                  aheadCorpus, yearMonth);
            } else {
              throw new BusinessException("��������ǰ���");
            }
          } else {
            throw new BusinessException("��������ǰ���");
          }
        } else {
          throw new BusinessException("��������ǰ���");
        }
      } else {// �������ж�
        // ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "5", contractId);
        // ���������ǰ�������
        int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
            contractId, bizDate.substring(0, 4)).intValue();
        if (aheadCounts < Integer.parseInt(paramExplain)) {
          // ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "6", contractId);
          // ������������ǰ����
          int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, null).intValue();
          ;
          if (lineCounts < Integer.parseInt(paramExplain)) {
            // ����
            af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate, aheadCorpus,
                yearMonth);
          } else {
            throw new BusinessException("��������ǰ���");
          }
        } else {
          throw new BusinessException("��������ǰ���");
        }
      }
    }
    // if(aheadMoney == null){
    // af.setDeadLine("0");
    // }
    af.setAheadType(af1.getAheadType());
    return af;
  }

  /**
   * �ж��Ƿ���������ǰ ������ʵ������Ӵ������޲����� ����������� ����������뻹�����
   * 
   * @param bizDate
   * @param borrowerInfoDTO
   * @param af1
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  // ������ǰ
  public String partAheadInfo_wsh(LoancallbackTaAF loancallbackTaAF,
      SecurityInfo securityInfo) throws Exception {
    String paramType = "B";// ��������
    String paramExplain = "";// ����˵��
    String matter = "";
    String changeType="";
    changeType=loancallbackTaAF.getAheadTypeS();
    if("2".equals(changeType)||"4".equals(changeType)){
      int age = 0;
      String contractId = loancallbackTaAF.getBorrowerInfoDTO().getContractId();// ��ͬ���
      BorrowerAcc borrowerAcc_wsh = new BorrowerAcc();
      borrowerAcc_wsh = borrowerAccDAO.queryById(contractId);
      Integer loanBankId = new Integer(borrowerAcc_wsh.getLoanBankId().toString());// �ſ�����
      Borrower borrower_wsh = new Borrower();
      borrower_wsh = borrowerDAO.queryById(contractId);
      Houses houses = housesDAO.queryById(contractId);
      if ("1".equals(borrower_wsh.getSex())) {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "8", contractId, "1");
        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 4);
        if (borrower_wsh.getBirthday() != null && !"".equals(borrower_wsh.getBirthday())) {
//          age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//              - Integer.parseInt(opdate);
          age = Integer.parseInt(nowdate)
          - Integer.parseInt(borrower_wsh.getBirthday().substring(0,4));
        }
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter = "������ʵ������Ӵ������޲�����" + paramExplain + "��";
        }
      } else {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "8", contractId, "2");
        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 4);
        if (borrower_wsh.getBirthday() != null && !"".equals(borrower_wsh.getBirthday())) {
//        age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//            - Integer.parseInt(opdate);
        age = Integer.parseInt(nowdate)
        - Integer.parseInt(borrower_wsh.getBirthday().substring(0,4));
      }
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter = "������ʵ������Ӵ������޲�����" + paramExplain + "��";
        }
      }
      if ("01".equals(houses.getHouseType())) {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "9", contractId, "1");
        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(0,4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(0,4);
        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter += "��Ʒ������������޲�����" + paramExplain + "��";
        }
      } else {
        paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
            paramType, "9", contractId, "2");
        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(0,4);
        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(0,4);
        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
            .parseInt(paramExplain)) {
          matter += "���ַ�����������޲�����" + paramExplain + "��";
        }
      }
//      paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//          paramType, "10", contractId, null);
//      String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contractId);
//      String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contractId);
//      Emp emp_fuzhu = borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//          fuzhuOrgId.toString());
//      if(emp_fuzhu!=null){
//        if ((loancallbackTaAF.getCorpusInterest().add(emp_fuzhu.getSalaryBase())).compareTo(
//            borrower_wsh.getMonthSalary().multiply(
//                new BigDecimal(paramExplain).divide(new BigDecimal(100), 2))) > 0) {
//          matter += "����������뻹�����������" + paramExplain + "%";
//        }
//      }else{
//        if (loancallbackTaAF.getCorpusInterest().compareTo(
//            borrower_wsh.getMonthSalary().multiply(
//                new BigDecimal(paramExplain).divide(new BigDecimal(100), 2))) > 0) {
//          matter += "����������뻹�����������" + paramExplain + "%";
//        }
//      }
    }
    if("3".equals(changeType)||"5".equals(changeType)){
      int age = 0;
      String contractId = loancallbackTaAF.getBorrowerInfoDTO().getContractId();// ��ͬ���
      BorrowerAcc borrowerAcc_wsh = new BorrowerAcc();
      borrowerAcc_wsh = borrowerAccDAO.queryById(contractId);
      Integer loanBankId = new Integer(borrowerAcc_wsh.getLoanBankId().toString());// �ſ�����
      Borrower borrower_wsh = new Borrower();
      borrower_wsh = borrowerDAO.queryById(contractId);
//      Houses houses = housesDAO.queryById(contractId);
//      if ("1".equals(borrower_wsh.getSex())) {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "8", contractId, "1");
//        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate()
//            .substring(0, 4);
//        if (borrower_wsh.getAge() != null && !"".equals(borrower_wsh.getAge())) {
//          age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//              - Integer.parseInt(opdate);
//        }
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt("65")) {
//          matter = "������ʵ������Ӵ������޲�����" + paramExplain + "��";
//        }
//      } else {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "8", contractId, "2");
//        String opdate = borrower_wsh.getOpTime().toString().substring(0, 4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate()
//            .substring(0, 4);
//        if (borrower_wsh.getAge() != null && !"".equals(borrower_wsh.getAge())) {
//          age = borrower_wsh.getAge().intValue() + Integer.parseInt(nowdate)
//              - Integer.parseInt(opdate);
//        }
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt(paramExplain)) {
//          matter = "������ʵ������Ӵ������޲�����" + paramExplain + "��";
//        }
//      }
//      if ("1".equals(houses.getHouseType())) {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "9", contractId, "1");
//        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(4);
//        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt("20")) {
//          matter += "��Ʒ������������޲�����" + paramExplain + "��";
//        }
//      } else {
//        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
//            paramType, "9", contractId, "2");
//        String opdate = borrowerAcc_wsh.getLoanStartDate().substring(4);
//        String nowdate = securityInfo.getUserInfo().getPlbizDate().substring(4);
//        age = Integer.parseInt(nowdate) - Integer.parseInt(opdate);
//        if ((age + Integer.parseInt(loancallbackTaAF.getDeadLine()) / 12) > Integer
//            .parseInt("10")) {
//          matter += "���ַ�����������޲�����" + paramExplain + "��";
//        }
//      }
      paramExplain = loanContractParaDAO.queryParamExplain_wsh_1(loanBankId,
          paramType, "10", contractId, null);
      String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contractId);
      String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contractId);
      String fuzhuEmpName = borrowerDAO.countPeopleNum_EmpName(contractId);
      String fuzhuEmpCardNum = borrowerDAO.countPeopleNum_EmpCardNum(contractId);
      String fuzhuEmpsalary = borrowerDAO.countPeopleNum_EmpSalary(contractId);
      Emp emp_fuzhu =null;
      int fuzhusalaryBase=0;
      int borrowerSalaryBase=0;
      if(fuzhuEmpName!=null&&!"".equals(fuzhuEmpName)&&fuzhuEmpCardNum!=null&&!"".equals(fuzhuEmpCardNum)){
        fuzhusalaryBase=borrowerDAO.queryEmpSalary(fuzhuEmpName, fuzhuEmpCardNum);
      }
      if(fuzhusalaryBase<=0&&!"".equals(fuzhuEmpsalary)){
//        if(!"".equals(fuzhuEmpId)&&!"".equals(fuzhuOrgId)){
//          emp_fuzhu=borrowerDAO.queryByCriterions(fuzhuEmpId.toString(),
//              fuzhuOrgId.toString());
//        }
        fuzhusalaryBase=Integer.parseInt(fuzhuEmpsalary);
      }
      if(borrower_wsh.getBorrowerName()!=null&&!"".equals(borrower_wsh.getBorrowerName())&&borrower_wsh.getCardNum()!=null&&!"".equals(borrower_wsh.getCardNum())){
        borrowerSalaryBase=borrowerDAO.queryEmpSalary(borrower_wsh.getBorrowerName(), borrower_wsh.getCardNum());
      }
      if(borrowerSalaryBase<=0){
        borrowerSalaryBase=borrower_wsh.getMonthSalary().intValue();
      }
      if(fuzhusalaryBase>0){
        if ((loancallbackTaAF.getCorpusInterest()).compareTo((
            (new BigDecimal(borrowerSalaryBase)).add((new BigDecimal(fuzhusalaryBase)))).multiply(
                new BigDecimal(paramExplain)).divide(new BigDecimal(100), 2)) > 0) {
          matter += "����������뻹�����������" + paramExplain + "%";
        }
      }else{
        if (loancallbackTaAF.getCorpusInterest().compareTo(
            (new BigDecimal(borrowerSalaryBase)).multiply(
                new BigDecimal(paramExplain)).divide(new BigDecimal(100), 2)) > 0) {
          matter += "����������뻹�����������" + paramExplain + "%";
        }
      }
      System.out.println(borrower_wsh.getMonthSalary().multiply(
          new BigDecimal(paramExplain)).divide(new BigDecimal(100), 2));
    }
   
    
    return matter;
  }

  /**
   * ������ǰ������Ϣ
   * 
   * @param borrowerInfoDTO
   * @param af1
   * @param bizDate
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findPartAheadInfo(BorrowerInfoDTO borrowerInfoDTO,
      LoancallbackTaAF af1, String bizDate, BigDecimal aheadMoney,
      String yearMonths) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";// ����˵��
    String paramType = "B";// ��������
    String paramValue = "";// ����ֵ
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
    String days = "";// ռ������
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// �ſ�����
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);// ʣ�౾��
    overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal temp_overplusLoanMoney = overplusLoanMoney;
    BigDecimal aheadCorpus = new BigDecimal(0.00);// ��ǰ�����
    BigDecimal aheadInterest = new BigDecimal(0.00);// ��ǰ������Ϣ
    BigDecimal loanRate = new BigDecimal(0.00);// ������
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);// �����ѽ��
    BigDecimal corpusInterest = new BigDecimal(0.00);// ��ǰ������»���Ϣ
    String deadLine = "";// ��ǰ�����ʣ������
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    BigDecimal sumMoney = new BigDecimal(0.00);// �ܻ����
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// �������
    BigDecimal realMoney = new BigDecimal(0.00);// ʵ�ս��
    List tempList = new ArrayList();
    String contractId = borrowerInfoDTO.getContractId();// ��ͬ���
    // ��ǰ������ͻ�����Ϊ
    paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
        paramType, "4", contractId);
    BigDecimal minMoney = new BigDecimal(paramExplain);
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    String callbackMonth = yearMonths;// �������¾�����������ʾ������
    String isAmendLine = "0";// �Ƿ�����޸�ʣ�����ޣ�0.�����ԣ�1.���ԣ�
    String loanRepayDay1 = "";
    String loanRepayDayBiz = this.getEndDay(yearMonth, loanRepayDay);// ������ڵĻ�����
    List shouldBackList = af1.getShouldBackList();
    String endDate = "";// ��������
    int temp_monthCounts = 0;// �������� ���ж�ռ�������ķ���ȡ��������������
    if (!shouldBackList.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
          .get(shouldBackList.size() - 1);
      endDate = dto.getLoanKouYearmonth().substring(0, 6);// �������û����������������ʾֵ
    }
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// ������
    if (yearMonths != null && !yearMonths.equals("")) {
      // ��������ղ�Ϊ����һ���ж�
      loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
      // ռ������
      // �û����պͻ�������Ƚϣ�1�������մ��ڻ���� ռ������=(��������+1+�����)-�����ա�
      // 2��������С�ڵ��ڻ���� ռ������=��������+�����-������
      if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
        String temp_yearMonths = BusiTools.addMonth(yearMonths, 1);
        days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
            + temp_yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
        temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(0,
            4)
            + "-" + temp_yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      } else if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
        days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
            + yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
        temp_monthCounts = BusiTools.getDisMonths(yearMonths.substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      }
    } else {
      // ������Ϊ��
      List yearList = af1.getMonthYearList();
      if (!yearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) yearList.get(0);
        yearMonths = dto.getLoanKouYearmonth();
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(yearMonths, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else {
          // loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay); ww�ĵ�
          String temp_month = BusiTools.addMonth(yearMonths, -1);
          loanRepayDay1 = this.getEndDay(temp_month, loanRepayDay);// ww�ĵ�
          days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
              + yearMonths.substring(4, 6) + "-" + day, temp_month.substring(0,
              4)
              + "-" + temp_month.substring(4, 6) + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(yearMonths.substring(0, 4)
              + "-" + yearMonths.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));
        }
      } else {
        // ȡ��һ��1��
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(temp_yearMonth, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
          loanRepayDay1 = this.getEndDay(bizDate, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonth.substring(0, 4) + "-"
              + temp_yearMonth.substring(4, 6) + "-" + day, year + "-" + "12"
              + "-" + loanRepayDay1)
              + "";
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonth.substring(0,
              4)
              + "-" + temp_yearMonth.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));
        }
      }
    }
    // if(Integer.parseInt(bizDate)<Integer.parseInt(endDate+loanRepayDay1)){
    // days="0";
    // }else{
    // if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay1)){
    // //�������С�ڸú�ͬ�Ļ����գ�ռ������=�������-���������-1�Ļ����գ�
    // String temp_yearMonths=BusiTools.addMonth(yearMonths, -1);
    // String temp_loanRepayDay=this.getEndDay(temp_yearMonths, loanRepayDay);
    // days =
    // BusiTools.minusDate(temp_bizDate,temp_yearMonths.substring(0,4)+"-"+temp_yearMonths.substring(4,6)+"-"+temp_loanRepayDay)+"";
    // }else{
    // //������ڴ��ڵ��ڸú�ͬ�Ļ�����ռ������=�������-��������µĻ����գ�
    // days =
    // BusiTools.minusDate(temp_bizDate,yearMonths.substring(0,4)+"-"+yearMonths.substring(4,6)+"-"+loanRepayDay1)+"";
    // }
    // }
    if (Integer.parseInt(days) <= 0) {
      days = "0";
    }

    // /**
    // * ��ѯPL112_1(����:��ͬ��ţ�status=1������)
    // */
    // String chgType = "3";//��ǰ��������
    // String overplusLimite = "";//ʣ������
    // String aheadCheckId = "";//��ǰ������Ϣ��ID
    // List list = loanBankParaDAO.queryPL112Info_jj(contractId, "0");
    // if(!list.isEmpty()){
    // Object obj[]=null;
    // Iterator it = list.iterator();
    // while(it.hasNext()){
    // obj = (Object[])it.next();
    // aheadCheckId = obj[0].toString();
    // overplusLimite = obj[1].toString();
    // chgType = obj[2].toString();
    // }
    // af.setDeadLine(overplusLimite);
    // // af.setAheadCorpus(new BigDecimal(0.00));
    // af.setAheadCheckId(aheadCheckId);
    // // aheadMoney=new BigDecimal(0.00);
    // }
    aheadCorpus = aheadMoney;// overplusLoanMoney.subtract(af1.getSumCorpus());
    // �����ѽ�������������жϸô�����ں�ͬ�������PL004���в������=7�Ĳ���ֵ��
    paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId, paramType,
        "7", contractId);
    // ����ֵΪ1:������=��ǰ�����*����˵��/100
    if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT + "")) {
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain))
          .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    } else if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT + "")) {
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    } else {
      loanPoundageMoney = new BigDecimal(0.00);
    }
    if (yearMonths != null && !yearMonths.equals("")) {
      yearMonths = BusiTools.addMonth(yearMonths, 1);// ����ȡ������л������µ���һ����
    } else {
      yearMonths = bizDate.substring(0, 6);
    }

    if (endDate == "") {
      List monthYearList = af1.getMonthYearList();
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        endDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(endDate, -1);
        }
      } else {
        // ȡ��һ��1��
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          endDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      endDate = BusiTools.addMonth(endDate, 1);
    }
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
        endDate);
    if (!tempList.isEmpty()) {
      shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
    } else {
      endDate = BusiTools.addMonth(endDate, -1);
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
          endDate.substring(0, 6));
      if (!tempList.isEmpty()) {
        shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
      }
    }
    String loanTimeLimit = borrowerInfoDTO.getLoanTimeLimit();// ԭ��������
    // if(Integer.parseInt(loanTimeLimit)>60){
    // �ǡ�������ȡPL001��loan_rate_type=1�����µ�����
    // if(Integer.parseInt(loanTimeLimit)>60){
    // loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
    // String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    // }else{
    // //�񡪡�������ǰ�����»���Ϣȡ���ʵ�PL001��loan_rate_type=0����������
    // loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
    // String.valueOf(BusiConst.PLLOANTYPE_FIVE));
    // }
    loanRate = loanRateDAO.findMontRate_wsh(contractId, yearMonth);
    deadLine = loanRateDAO.findpl111_timelimit(contractId);
    // }
    // ��ǰ������Ϣ=ʣ�౾��ʣ�౾��-�б���ϼƣ�*ռ������*������/30
    aheadInterest = temp_overplusLoanMoney.subtract(af1.getSumCorpus())
        .multiply(new BigDecimal(days)).multiply(loanRate).divide(
            new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
    paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId, paramType,
        "1", contractId);
    // if(!overplusLimite.equals("")){
    // paramValue = BusiConst.PLRECOVERPARASCHG_CHGMONTHS+"";
    // }
    String loanMode = borrowerInfoDTO.getLoanMode();
    // ��PL003�в�ѯ�ۿʽ(ȫ��ۿ���ۿ�)
    // String pldebit = loanBankParaDAO.queryParamValue_LJ(loanBankId,
    // paramType, "1");
    // if(list.isEmpty()){
    if (paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEPAY + "")) {// ����ֵΪ1
      if (loanMode.equals(String.valueOf(BusiConst.PLRECOVERTYPE_CORPUS))) {
        // ��ǰ�����ʣ������=��������-��������ºͽ�����˻���PL111�з�������֮��������
//        deadLine = String.valueOf(Integer.parseInt(borrowerInfoDTO
//            .getLoanTimeLimit())
//            - temp_monthCounts);
        // Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.getDisMonths
        // (bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8),
        // borrowerInfoDTO.getLoanStartDate().substring(0,4)+"-"+borrowerInfoDTO.getLoanStartDate().substring(4,6)+"-"+
        // borrowerInfoDTO.getLoanStartDate().substring(6,8)))+"";
        // ��ǰ������»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
        overplusLoanMoney = overplusLoanMoney.subtract(af1.getSumCorpus())
            .subtract(aheadMoney);
        if (Integer.parseInt(deadLine) > 0) {
          corpusInterest = CorpusinterestBS.getCorpusInterest(
              overplusLoanMoney, loanRate, deadLine);
        }
      } else {
        double dOverplusLoanMoney = overplusLoanMoney.subtract(
            af1.getSumCorpus()).subtract(aheadMoney).doubleValue();
        double dMonthIngerest = shouldBackListDTO.getShouldCorpus().add(
            shouldBackListDTO.getShouldInterest()).doubleValue();
        double dLine = Math.abs(Math.log((dMonthIngerest - dOverplusLoanMoney
            * loanRate.doubleValue())
            / dMonthIngerest)
            / Math.log(1 + loanRate.doubleValue()));
        BigDecimal bLine = new BigDecimal(dLine);
        // ʣ��������������
        bLine = bLine.divide(new BigDecimal(1), 0, BigDecimal.ROUND_HALF_UP);
//        deadLine = bLine.toString();
        // ʣ������ȡ��
        // if(deadLine.indexOf('.',0)!=-1){
        // int i=deadLine.lastIndexOf(".");
        // deadLine = deadLine.substring(0,i);
        // }
        if (bLine.doubleValue() > 0) {
          corpusInterest = shouldBackListDTO.getShouldCorpus().add(
              shouldBackListDTO.getShouldInterest());
        }
      }
      af.setDeadLine(deadLine);
    } else if (paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEMONTHS + "")) {// ����ֵΪ2
      // ��ǰ�����ʣ������=��������-��������ºͽ�����˻���PL111�з�������֮��������
//      deadLine = String.valueOf(Integer.parseInt(borrowerInfoDTO
//          .getLoanTimeLimit())
//          - temp_monthCounts);
      // Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.getDisMonths
      // (bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8),
      // borrowerInfoDTO.getLoanStartDate().substring(0,4)+"-"+borrowerInfoDTO.getLoanStartDate().substring(4,6)+"-"
      // +borrowerInfoDTO.getLoanStartDate().substring(6,8)))+"";
      // ��ǰ������»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
      overplusLoanMoney = overplusLoanMoney.subtract(af1.getSumCorpus())
          .subtract(aheadMoney);
      if (Integer.parseInt(deadLine) > 0) {
        corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
            loanRate, deadLine);
      }
      af.setDeadLine(deadLine);

    } else if (paramValue.equals(BusiConst.PLRECOVERPARASCHG_CHGMONTHS + "")) {// ����ֵΪ3
      // Ĭ���������ǰ�����ʣ������=��������-��������ºͽ�����˻���PL111�з�������֮���������������޸ĸô������ޡ�
//      deadLine = String.valueOf(Integer.parseInt(borrowerInfoDTO
//          .getLoanTimeLimit())
//          - temp_monthCounts);
      // deadLine = overplusLimite;
      // (BusiTools.getDisMonths
      // (bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8),
      // borrowerInfoDTO.getLoanStartDate().substring(0,4)+"-"+borrowerInfoDTO.getLoanStartDate().substring(4,6)+"-"
      // +borrowerInfoDTO.getLoanStartDate().substring(6,8)))+"";
      // af.setLine(paramValue);
      // isAmendLine = "1";
      // ȡ���ʻ�Ҫ�ж��´�������+�Ѿ������ģ��Ѿ�������=PL203�����������-�����գ��Ƿ�>60���� and ԭ��������<=60���£�
      // List
      // maxyearMonthlist=loanFlowTailDAO.queryYearMonth_LJ(borrowerInfoDTO.getLoanKouAcc());//��ѯ���������
      // if(!maxyearMonthlist.isEmpty()){
      // ShouldBackListDTO dto=(ShouldBackListDTO)maxyearMonthlist.get(0);
      // maxYearMonth = dto.getLoanKouYearmonth();
      // }
      // if(maxYearMonth!=null && !maxYearMonth.equals("")){
      // temp_loanRepayDay=this.getEndDay(maxYearMonth,
      // borrowerInfoDTO.getLoanRepayDay());
      // temp_monthCounts=BusiTools.getDisMonths(maxYearMonth.substring(0,4)+"-"+maxYearMonth.substring(4,6)+"-"+temp_loanRepayDay,
      // loanStartDate.substring(0,4)+"-"+loanStartDate.substring(4,6)+"-"+loanStartDate.substring(6,8));
      // }
      if (Integer.parseInt(loanTimeLimit) <= 60) {
        // �ǡ�������ȡPL001��loan_rate_type=1�����µ�����
        if (Integer.parseInt(deadLine) + temp_monthCounts > 60) {
          loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
              String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
        } else {
          // �񡪡�������ǰ�����»���Ϣȡ���ʵ�PL001��loan_rate_type=0����������
          loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
              String.valueOf(BusiConst.PLLOANTYPE_FIVE));
        }
      } else {
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
      }
      // ��ǰ������»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
      overplusLoanMoney = overplusLoanMoney.subtract(af1.getSumCorpus())
          .subtract(aheadMoney);
      if (Integer.parseInt(deadLine) > 0)
        corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
            loanRate, deadLine);
      af.setDeadLine(deadLine);
    }
    // }
    // ���λ����ܱ���=Ӧ������ϼ�+��ǰ������
    sumCorpus = af1.getSumCorpus().add(aheadMoney);
    // ���λ�������Ϣ=��ǰ������Ϣ+Ӧ����Ϣ�ϼ�+��Ϣ�ϼ�
    sumInterest = aheadInterest.add(af1.getSumInterest());
    // ���λ����ܽ��=�����ܻ����+�����ܻ�����Ϣ��
    sumMoney = sumCorpus.add(sumInterest);
    // �������=������˻���PL111�й������
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    // ����ʵ�ս��:A�����������ڵ��ڱ����ܻ��������ʵ�ս��=0���˷�����=�����ܻ�����
    if (ovaerLoanRepay.doubleValue() >= sumMoney.doubleValue()) {
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(sumMoney);
    } else {// ����B����ʵ�ս��=�����ܻ�����-�������
      realMoney = sumMoney.subtract(ovaerLoanRepay);
      af.setOverOccurMoney(ovaerLoanRepay);
    }
    af.setAheadCorpus(aheadCorpus);// ��ǰ�����
    af.setMinMoney(minMoney);// ��ǰ������ͻ����� Ϊ���ж���ǰ����ı�����޸ķ�Χ�ڴ��ڴ�ֵ��С��Ĭ�ϵ���ǰ�����
    af.setAheadInterest(aheadInterest);
    af.setDays(days);
    af.setLoanPoundageMoney(loanPoundageMoney);
    af.setCorpusInterest(corpusInterest);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(sumMoney);
    af.setOvaerLoanRepay(ovaerLoanRepay);
    af.setRealMoney(realMoney);
    af.setIsAmendLine(isAmendLine);
    af.setDeadLine(deadLine);
    af.setDead(deadLine);
    if (af.getSumCorpus().doubleValue() == temp_overplusLoanMoney.doubleValue()) {
      af.setDeadLine("0");
    }
    if (af.getDeadLine().equals("0")) {
      af.setCorpusInterest(new BigDecimal(0.00));
    }
    // af.setAheadTypeS(BusiTools.getBusiValue(Integer.parseInt(chgType),
    // BusiConst.AHEADTYPE));
    // af.setAheadType(chgType);
    return af;
  }

  /**
   * ����ҳ�������ʣ�����޼����»���Ϣ
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public BigDecimal getCorpusInterest(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    BigDecimal corpusInterest = new BigDecimal(0.00);
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String temp_yearMonth = yearMonth;// ��������
    List tempList = null;
    ShouldBackListDTO shouldBackListDTO = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String deadLine = (String) pagination.getQueryCriterions().get("deadLine");
    String cIMoney = (String) pagination.getQueryCriterions().get("cIMoney");
    String aheadMoney = (String) pagination.getQueryCriterions().get(
        "aheadMoney");
    String callbackMonth = (String) pagination.getQueryCriterions().get(
        "callbackMonth");
    String qiankuanbenjin = (String) pagination.getQueryCriterions().get(
    "qiankuanbenjin");
    if(qiankuanbenjin==null||qiankuanbenjin.equals("")){
      qiankuanbenjin="0";
    }
    String temp_month = "";
    List temp_list = (List) pagination.getQueryCriterions().get("YearList");
    if (callbackMonth != null && !callbackMonth.equals("")) {
      temp_yearMonth = callbackMonth.substring(0, 6);
      // if(!temp_yearMonth.equals(yearMonth)){
      yearMonth = callbackMonth.substring(0, 6);
      // day=callbackMonth.substring(6,8);
      // }
    }
    // else{
    // callbackMonth=temp_month;
    // }
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
        yearMonth);
    if (!tempList.isEmpty()) {
      shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
    } else {
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
          temp_yearMonth);
      if (!tempList.isEmpty()) {
        shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
      }
    }
    // ��PL110��PL111ȡ��Ϣ
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);// ʣ�౾��
    overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal loanRate = shouldBackListDTO.getLoanRate();// �����ÿ������
    overplusLoanMoney = overplusLoanMoney.subtract(new BigDecimal(qiankuanbenjin)).subtract(new BigDecimal(cIMoney));
    // ȡ���ʻ�Ҫ�ж��´�������+�Ѿ������ģ��Ѿ�������=PL203�����������-�����գ��Ƿ�>60���� and ԭ��������<=60���£�
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// ������
    // List
    // maxyearMonthlist=loanFlowTailDAO.queryYearMonth_LJ(borrowerInfoDTO.getLoanKouAcc());
    String loanTimeLimit = borrowerInfoDTO.getLoanTimeLimit();// ԭ��������
    // String maxYearMonth = "";//PL203�����������
    // int temp_monthCounts=0;
    // if(!maxyearMonthlist.isEmpty()){
    // ShouldBackListDTO dto=(ShouldBackListDTO)maxyearMonthlist.get(0);
    // maxYearMonth = dto.getLoanKouYearmonth();
    // }
    // if(maxYearMonth!=null && !maxYearMonth.equals("")){
    // loanRepayDay=this.getEndDay(maxYearMonth,
    // borrowerInfoDTO.getLoanRepayDay());
    // temp_monthCounts=BusiTools.getDisMonths(maxYearMonth.substring(0,4)+"-"+maxYearMonth.substring(4,6)+"-"+loanRepayDay,
    // loanStartDate.substring(0,4)+"-"+loanStartDate.substring(4,6)+"-"+loanStartDate.substring(6,8));
    // }
    // �û����պͻ�������Ƚϣ�1�������մ��ڻ���� ռ������=(��������+1+�����)-�����ա�
    // 2��������С�ڵ��ڻ���� ռ������=��������+�����-������
    // ������ȡ��������Ϊ�˼��㻹�������ޡ�
    // ���Ĭ�ϵĻ�����Ϊ����ȡ�������е�һ�����жϻ����պͻ���յĴ�С����������մ���
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6),
        borrowerInfoDTO.getLoanRepayDay());
    String day = bizDate.substring(6, 8);
    int temp_monthCounts = 0;
    if (callbackMonth != null && !callbackMonth.equals("")) {
      String yearMonths = callbackMonth.substring(0, 6);
      if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
        String temp_yearMonths = BusiTools.addMonth(callbackMonth.substring(0,
            6), 1);
        temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(0,
            4)
            + "-" + temp_yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      } else if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
        temp_monthCounts = BusiTools.getDisMonths(yearMonths.substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + day, loanStartDate
            .substring(0, 4)
            + "-"
            + loanStartDate.substring(4, 6)
            + "-"
            + loanStartDate.substring(6, 8));
      }
    } else {
      // ������Ϊ��
      if (!temp_list.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) temp_list.get(0);
        temp_month = dto.getLoanKouYearmonth();
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(temp_month, -1);
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else {
          temp_monthCounts = BusiTools.getDisMonths(temp_month.substring(0, 4)
              + "-" + temp_month.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));
        }
      } else {
        // ȡ��һ��1��
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String next_yearMonth = String.valueOf(iYear) + "01";
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(next_yearMonth, -1);
          temp_monthCounts = BusiTools.getDisMonths(temp_yearMonths.substring(
              0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + day,
              loanStartDate.substring(0, 4) + "-"
                  + loanStartDate.substring(4, 6) + "-"
                  + loanStartDate.substring(6, 8));
        } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
          temp_monthCounts = BusiTools.getDisMonths(next_yearMonth.substring(0,
              4)
              + "-" + next_yearMonth.substring(4, 6) + "-" + day, loanStartDate
              .substring(0, 4)
              + "-"
              + loanStartDate.substring(4, 6)
              + "-"
              + loanStartDate.substring(6, 8));

        }

      }
    }
    BorrowerAcc borrowerAcc=new BorrowerAcc();
    borrowerAcc=borrowerAccDAO.queryById(contractId);
    if (Integer.parseInt(loanTimeLimit)-Integer.parseInt(borrowerAcc.getOverplusLimite())+Integer.parseInt(deadLine) <= 60) {
      // �ǡ�������ȡPL001��loan_rate_type=1�����µ�����
//      if (Integer.parseInt(deadLine) + temp_monthCounts > 60) {
//        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
//            String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
//      } else {
        // �񡪡�������ǰ�����»���Ϣȡ���ʵ�PL001��loan_rate_type=0����������
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVE));
//      }
    } else {
      loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
          String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    }
    // ��ǰ������»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
    corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
        loanRate, deadLine);
    return corpusInterest;
  }

  /**
   * �ж��Ƿ������廹
   * 
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  // һ�����廹
  public LoancallbackTaAF fullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,
      String bizDate, LoancallbackTaAF af1, String yearMonth) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";// ��������
    String paramValue = "";// ����ֵ
    String paramExplain = "";// ����˵��
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();// ��������
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// �ſ�����
    String contractId = borrowerInfoDTO.getContractId();// ��ͬ���
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
    List shouldBackList = null;
    String day = bizDate.substring(6, 8);
    String endDate = "";// ��������
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6),
        loanRepayDay);// ������ڵĻ�����
    List shouldBackList1 = af1.getShouldBackList();
    if (!shouldBackList1.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList1
          .get(shouldBackList1.size() - 1);
      endDate = dto.getLoanKouYearmonth().substring(0, 6);// �������û����������������ʾֵ
    }
    if (endDate == "") {
      List monthYearList = af1.getMonthYearList();
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        endDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(endDate, -1);
        }
      } else {
        // ȡ��һ��1��
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          endDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          endDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      endDate = BusiTools.addMonth(endDate, 1);
    }
    // ��ǰ����δ���岻������ǰ
    String temp_loanRepayDay = this.getEndDay(bizDate.substring(0, 6),
        loanRepayDay);
    if (Integer.parseInt(day) < Integer.parseInt(temp_loanRepayDay)) {
      // �����С�ڻ�����(���ڵ���)
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJD(
          contractId, endDate.substring(0, 6));
    } else {
      // ����մ��ڵ��ڻ�����
      shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJE(
          contractId, endDate.substring(0, 6));
    }
    if (!shouldBackList.isEmpty()) {
      throw new BusinessException("��ǰ����δ���壬��������ǰ���");
    } else {
      // �жϸô�����ں�ͬ�������PL004���в������=3�Ĳ���ֵ�Ƿ�=1��������һ�����廹��
      paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId,
          paramType, "3", contractId);
      if (paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW + "")) {
        // ȡ���ò�����Ӧ�Ĳ���˵��PARAM_EXPLAIN���жϣ��������-PL111���з�������LOAN_START_DATE���������Ƿ���ڵ��ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "3", contractId);

        // int temp_monthCounts=BusiTools.getDisMonths(loanStartDate, bizDate);
        int temp_monthCounts = BusiTools.getDisMonths(bizDate.substring(0, 4)
            + "-" + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8),
            loanStartDate.substring(0, 4) + "-" + loanStartDate.substring(4, 6)
                + "-" + loanStartDate.substring(6, 8));
        if (temp_monthCounts >= Integer.parseInt(paramExplain)) {
          // ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "5", contractId);
          // ���������ǰ�������
          int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, bizDate.substring(0, 4)).intValue();
          if (aheadCounts < Integer.parseInt(paramExplain)) {
            // ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
            paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
                paramType, "6", contractId);
            // ������������ǰ����
            int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
                contractId, null).intValue();
            ;
            if (lineCounts < Integer.parseInt(paramExplain)) {
              af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1, yearMonth);
            } else {
              throw new BusinessException("������һ���Խ��壡");
            }
          } else {
            throw new BusinessException("������һ���Խ��壡");
          }
        } else {
          throw new BusinessException("������һ���Խ��壡");
        }
      } else {// �������ж�
        // ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
            paramType, "5", contractId);
        // ���������ǰ�������
        int aheadCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
            contractId, bizDate).intValue();
        if (aheadCounts < Integer.parseInt(paramExplain)) {
          // ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
          paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
              paramType, "6", contractId);
          // ������������ǰ����
          int lineCounts = loanFlowTailDAO.queryCallbackAheadCounts_LJ(
              contractId, null).intValue();
          ;
          if (lineCounts < Integer.parseInt(paramExplain)) {
            af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1, yearMonth);
          } else {
            throw new BusinessException("������һ���Խ��壡");
          }
        } else {
          throw new BusinessException("������һ���Խ��壡");
        }
      }
    }
    return af;
  }

  /**
   * һ�����廹Ӧ����Ϣ
   * 
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findFullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,
      String bizDate, LoancallbackTaAF af1, String yearMonths) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";// ����˵��
    String paramType = "B";// ��������
    String paramValue = "";// ����ֵ
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// �ſ�����
    BigDecimal aheadCorpus = new BigDecimal(0.00);// ��ǰ�����
    BigDecimal overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();// ʣ�౾��
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
    BigDecimal aheadInterest = new BigDecimal(0.00);// ��ǰ������Ϣ
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);// �����ѽ��
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    BigDecimal sumMoney = new BigDecimal(0.00);// �ܻ����
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// �������
    BigDecimal realMoney = new BigDecimal(0.00);// ʵ�ս��
    String days = "";// ռ������
    String contractId = borrowerInfoDTO.getContractId();// ��ͬ���
    BigDecimal loanRate = new BigDecimal(0.00);// ������
    List tempList = null;
    // ��ǰ�����Ĭ�������=ʣ�౾��-Ӧ������ϼƣ��������޸ģ�
    aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String callbackMonth = yearMonths;// ��������
    String loanRepayDay1 = "";
    String loanRepayDayBiz = this.getEndDay(yearMonth, loanRepayDay);// ������ڵĻ�����
    List shouldBackList = af1.getShouldBackList();
    String endDate = "";// ��������
    if (!shouldBackList.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList
          .get(shouldBackList.size() - 1);
      endDate = dto.getLoanKouYearmonth();// �������û����������������ʾֵ
    }
    if (yearMonths != null && !yearMonths.equals("")) {
      // ռ������
      // �û����պͻ�������Ƚϣ�1�������մ��ڻ���� ռ������=(��������+1+�����)-�����ա�
      // 2��������С�ڵ��ڻ���� ռ������=��������+�����-������
      loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
      if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
        String temp_yearMonths = BusiTools.addMonth(yearMonths, 1);
        days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
            + temp_yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
      } else if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
        loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
        days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
            + yearMonths.substring(4, 6) + "-" + day, yearMonths
            .substring(0, 4)
            + "-" + yearMonths.substring(4, 6) + "-" + loanRepayDay1)
            + "";
      }
    } else {
      // ������Ϊ��
      List yearList = af1.getMonthYearList();
      if (!yearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) yearList.get(0);
        yearMonths = dto.getLoanKouYearmonth();
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(yearMonths, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
        } else {
          loanRepayDay1 = this.getEndDay(yearMonths, loanRepayDay);
          String temp_month = BusiTools.addMonth(yearMonths, -1);
          days = BusiTools.minusDate(yearMonths.substring(0, 4) + "-"
              + yearMonths.substring(4, 6) + "-" + day, temp_month.substring(0,
              4)
              + "-" + temp_month.substring(4, 6) + "-" + loanRepayDay1)
              + "";
        }
      } else {
        // ȡ��һ��1��
        String bizyear = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(bizyear) + 1;
        String temp_yearMonth = iYear + "01";
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          String temp_yearMonths = BusiTools.addMonth(temp_yearMonth, -1);
          loanRepayDay1 = this.getEndDay(temp_yearMonths, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonths.substring(0, 4) + "-"
              + temp_yearMonths.substring(4, 6) + "-" + day, temp_yearMonths
              .substring(0, 4)
              + "-" + temp_yearMonths.substring(4, 6) + "-" + loanRepayDay1)
              + "";
        } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
          loanRepayDay1 = this.getEndDay(bizDate, loanRepayDay);
          days = BusiTools.minusDate(temp_yearMonth.substring(0, 4) + "-"
              + temp_yearMonth.substring(4, 6) + "-" + day, bizyear + "-"
              + "12" + "-" + loanRepayDay1)
              + "";
        }
      }
    }
    // if(Integer.parseInt(bizDate)<Integer.parseInt(endDate+loanRepayDay1)){
    // days="0";
    // }else{
    // if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay1)){
    // String temp_yearMonths=BusiTools.addMonth(yearMonth, -1);
    // String temp_loanRepayDay=this.getEndDay(temp_yearMonths, loanRepayDay);
    // days =
    // BusiTools.minusDate(temp_bizDate,temp_yearMonths.substring(0,4)+"-"+temp_yearMonths.substring(4,6)+"-"+temp_loanRepayDay)+"";
    // }else{
    // temp_date = year + "-"+ month + "-" + loanRepayDay1;
    // days = BusiTools.minusDate(temp_bizDate, temp_date)+"";
    // }
    // }
    if (yearMonths != null && !yearMonths.equals("")) {
      yearMonths = BusiTools.addMonth(yearMonths, 1);
    } else {
      yearMonths = bizDate.substring(0, 6);
    }
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
        yearMonths);
    if (!tempList.isEmpty()) {
      shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
    } else {
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId,
          callbackMonth);
      if (!tempList.isEmpty()) {
        shouldBackListDTO = (ShouldBackListDTO) tempList.get(0);
      }
    }
    // monthInterest = shouldBackListDTO.getLoanRate();//�����ÿ������
    String loanTimeLimit = borrowerInfoDTO.getLoanTimeLimit();// ԭ��������
    // �ǡ�������ȡPL001��loan_rate_type=1�����µ�����
    if (Integer.parseInt(loanTimeLimit) > 60) {
      loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
          String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    } else {
      // �񡪡�������ǰ�����»���Ϣȡ���ʵ�PL001��loan_rate_type=0����������
      loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
          String.valueOf(BusiConst.PLLOANTYPE_FIVE));
    }
    // ��ǰ������Ϣ=��ǰ�����*ռ������*������/30
    aheadInterest = aheadCorpus.multiply(new BigDecimal(days)).multiply(
        loanRate).divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_UP);
    // �����ѽ�������������жϸô�����ں�ͬ�������PL004���в������=6�Ĳ���ֵ��
    paramValue = loanContractParaDAO.queryParamValue_wsh(loanBankId, paramType,
        "7", contractId);
    // ����ֵΪ1:������=��ǰ�����*����˵��/100
    if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT + "")) {
      paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain))
          .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
    } else if (paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT + "")) {// ����ֵΪ2:������=����˵��
      paramExplain = loanContractParaDAO.queryParamExplain_wsh(loanBankId,
          paramType, "7", contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    } else {// ����ֵΪ3:������=0
      loanPoundageMoney = new BigDecimal(0.00);
    }
    // ���λ����ܱ���=Ӧ������ϼ�+��ǰ������
    sumCorpus = af1.getSumCorpus().add(aheadCorpus);
    // ���λ�������Ϣ=��ǰ������Ϣ+Ӧ����Ϣ�ϼ�+��Ϣ�ϼ�
    sumInterest = af1.getSumInterest().add(aheadInterest);
    // ���λ����ܽ��=�����ܻ����+�����ܻ�����Ϣ��
    sumMoney = sumCorpus.add(sumInterest);
    // �������=������˻���PL111�й������
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    // ����ʵ�ս��:A�����������ڵ��ڱ����ܻ��������ʵ�ս��=0���˷�����=�����ܻ�����
    if (ovaerLoanRepay.doubleValue() >= sumMoney.doubleValue()) {
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(sumMoney);
    } else {// ����B����ʵ�ս��=�����ܻ�����-�������
      realMoney = sumMoney.subtract(ovaerLoanRepay);
      af.setOverOccurMoney(ovaerLoanRepay);
    }
    af.setAheadCorpus(aheadCorpus);
    af.setDays(days);
    af.setAheadInterest(aheadInterest);
    af.setLoanPoundageMoney(loanPoundageMoney);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(sumMoney);
    af.setOvaerLoanRepay(ovaerLoanRepay);
    af.setRealMoney(realMoney);
    return af;
  }

  /**
   * Ӧ����Ϣ�б�
   * 
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackList(List shouldBackList,
      BorrowerInfoDTO borrowerInfoDTO, String bizDate) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    List temp_list = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// �ſ�����
    String isRate = "";// �Ƿ��Ϣ
    String accountDate = "";// ��������
    String loanRepayDay = "";// ������ ��ȡӦ������Ϣʱ�õ�
    String paramType = "A";// ��������
    String interestMode = "";// ���㷣Ϣ��ʽ
    String paramExplain = "";// ����˵��
    String allowdays = "";// ��������
    BigDecimal temp_interest = new BigDecimal(0.00);// ��ʱ��Ϣ
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();

    if (borrowerInfoDTO.getLoanMode().equals("һ����")
        || borrowerInfoDTO.getLoanMode().equals("������")) {
      // �����ڻ���
      // ��PL003�в�ѯ�����������Ƿ��Ϣ
      isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "12");
      // ��PL003��ȡ���㷣Ϣ��ʽ��������Ϣ�����ʡ�����ͬ�����ʡ�����ÿ��XXԪ���㣩
      interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "11");
      // ��PL003��ȡ��Ӧ�Ĳ���˵��
      paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "11");
      // PL003�в�ѯ��������
      allowdays = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
          "12");
    } else {
      // ��PL003�в�ѯ�����������Ƿ��Ϣ
      isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "5");
      // ��PL003��ȡ���㷣Ϣ��ʽ��������Ϣ�����ʡ�����ͬ�����ʡ�����ÿ��XXԪ���㣩
      interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "4");
      // ��PL003��ȡ��Ӧ�Ĳ���˵��
      paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "4");
      // PL003�в�ѯ��������
      allowdays = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
          "5");
    }
    if (!shouldBackList.isEmpty()) {
      for (int i = 0; i < shouldBackList.size(); i++) {
        ShouldBackListDTO dto1 = (ShouldBackListDTO) shouldBackList.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        String loanRepayDay1 = this.getEndDay(dto1.getLoanKouYearmonth(),
            loanRepayDay);
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// ��ʾ��������
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
        // ��������
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        
        if (days - Integer.parseInt(allowdays) > 0) {
          dto2.setLoanKouType("����");// ��ʾ��������
        } else {
          dto2.setLoanKouType("����");
        }
        dto2.setShouldCorpus(dto1.getShouldCorpus().subtract(
            dto1.getRealCorpus()));// ��ʾӦ������
        dto2.setShouldInterest(dto1.getShouldInterest().subtract(
            dto1.getRealInterest()));// ��ʾӦ����Ϣ
        dto2.setDays(days + "");// ��ʾ��������
        
        if (days > Integer.parseInt(allowdays))
          dto2.setDays(days + "");
        else
          dto2.setDays("0");
        // ��Ϣ��ס�ˡ�
        // ��������ÿ�²����ķ�Ϣ
        // �жϻ����(Ӧ������-����+Ӧ����Ϣ-��Ϣ)�Ƿ�=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else if (days <= 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // ������0�ж��Ƿ��ڿ��������ڼ�Ϣ
          // �������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���ֵPARAM_VALUE�Ƿ�=0(���������ڼ�Ϣ)
          if (isRate.equals(BusiConst.YES + "")) {// ��Ϣ
            temp_interest = PunishInterestBS.getTempInterestByYearMonth(
                interestMode, bizDate, dto1.getLoanKouYearmonth(),
                loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                dto1.getShouldInterest(), dto1.getRealInterest(),
                paramExplain, dto1.getLoanRate());
            // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����
//            accountDate = dto1.getBizDate();
            // String temp_day = accountDate.substring(6, 8);// �������ڵ���
            // if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay))
            // {// С�ڵ��ڻ�����
//            if (accountDate == null || accountDate.equals("")) {// �ж��Ƿ��м������ڣ�û�У����������£��У�����������
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else if (Integer.parseInt(accountDate) <= Integer.parseInt(dto1
//                .getLoanKouYearmonth()
//                + loanRepayDay1)) {// С�ڵ��ڻ�����
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay1, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else {// ���ڻ�����
//              temp_interest = PunishInterestBS.getTempInterestByClearDate(
//                  interestMode, bizDate, dto1.getBizDate(), dto1
//                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
//                      .getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//              temp_interest = temp_interest.add(dto1.getPunishInterest())
//                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// �ӻ������δ����Ϣ
//            }
            // �Ȳ�ѯ�ô����˺ŵ��������������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���˵��PARAM_EXPLAIN��ֵ���������������жϣ�������ڷֱ��ȥÿ���µĻ����գ��Ƿ�<=��ֵ������������
            // �жϿ�������
            if (days <= Integer.parseInt(allowdays)) {// ��������С�ڵ��ڿ�������
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(temp_interest);
            } else {
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          } else {// ����Ϣ
            // �Ȳ�ѯ�ô����˺ŵ��������������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���˵��PARAM_EXPLAIN��ֵ���������������жϣ�������ڷֱ��ȥÿ���µĻ����գ��Ƿ�<=��ֵ������������
            // �жϿ�������
            if (days <= Integer.parseInt(allowdays)) {// ��������С�ڵ��ڿ�������
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(new BigDecimal(0.00));
            } else {// �Ѿ�����
              // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����+��������
              // String temp_day = dto1.getBizDate().substring(6, 8);// �������ڵ���
              // ����������+�������������ɵ�������
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay1, Integer.parseInt(allowdays));
              // temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
              // if (Integer.parseInt(temp_day) <=
              // Integer.parseInt(temp_loanRepayDay)) {// С�ڵ��ڻ�����+��������
              if (dto1.getBizDate() == null || dto1.getBizDate().equals("")) {// �ж��Ƿ��м�������
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
                    loanRepayDay1, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, allowdays, dto1
                        .getLoanRate());
              } else if (Integer.parseInt(dto1.getBizDate()) <= Integer
                  .parseInt(temp_loanRepayDay)) {// С�ڵ��ڻ�����+��������
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
                    loanRepayDay1, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, allowdays, dto1
                        .getLoanRate());
              } else {// ���ڻ�����+��������
                temp_interest = PunishInterestBS.getTempInterestByClearDate(
                    interestMode, bizDate, dto1.getBizDate(), dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, dto1.getLoanRate());
                // �ӻ������δ����Ϣ
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest())
            .add(dto2.getPunishInterest()));// ��ʾӦ����Ϣ�ϼ�
        dto2.setLoanRate(dto1.getLoanRate());// ��ʾÿ������
        dto2.setShow_loanRate(dto1.getShow_loanRate());
        sumCorpus = sumCorpus.add(dto2.getShouldCorpus());
        sumInterest = sumInterest.add(dto2.getShouldInterest().add(
            dto2.getPunishInterest()));
        temp_list.add(dto2);
      }
    }
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setShouldBackList(temp_list);
    return af;
  }

  /**
   * ѡ�������½��в�ѯ
   */
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination)
      throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    String month = (String) pagination.getQueryCriterions()
        .get("callbackMonth");
    String ovaerLoanRepay = (String) pagination.getQueryCriterions().get(
        "ovaerLoanRepay");// �������
    // String pldebit =
    // (String)pagination.getQueryCriterions().get("pldebit");// �ۿʽ
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    List list = (List) pagination.getQueryCriterions().get("shouldBackList");
    List returnList = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
        if (Integer.parseInt(dto.getLoanKouYearmonth()) <= Integer
            .parseInt(month)) {
          sumCorpus = sumCorpus.add(dto.getShouldCorpus());
          sumInterest = sumInterest.add(dto.getShouldInterest().add(
              dto.getPunishInterest()));
          returnList.add(dto);
        }
      }
    }
    List borrowerList = new ArrayList();// �˻���Ϣ
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    // ��PL110��PL111ȡ��Ϣ
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer
        .parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
    borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
        .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
    af.setShouldBackList(returnList);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
    af.setOvaerLoanRepay(new BigDecimal(ovaerLoanRepay));
    // A.�����������ڵ��ڱ����ܻ��������ʵ�ս��=0�����˷�����=�����ܻ�����
    if (Double.parseDouble(ovaerLoanRepay) >= af.getSumMoney().doubleValue()) {
      af.setRealMoney(new BigDecimal(0.00));
      af.setOverOccurMoney(af.getSumMoney());
    } else {
      // ���������С�ڱ����ܻ��������ʵ�ս��=�����ܻ�����-������� �����PL003������Ϊ��������������Ϊ1�еĲ���ֵ=2��ȫ��ۿ
      // ����ʵ�ս������޸ģ���Ҫ���ڵ���0С�ڵ���Ĭ����ʾ��ʵ�ս������˷�����=�������
      af
          .setRealMoney(af.getSumMoney().subtract(
              new BigDecimal(ovaerLoanRepay)));
      af.setOverOccurMoney(new BigDecimal(ovaerLoanRepay));
    }
    af.setBorrowerInfoDTO(borrowerInfoDTO);
    af.setMonthYear(month);
    af.setBizType(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "");
    return af;
  }

  /**
   * ������Ϊ������
   * 
   * @param importList
   * @param securityInfo
   * @throws Exception
   */
  public Integer adCallbackBatch(List importList, SecurityInfo securityInfo)
      throws Exception {
    String loanKouAcc = "";// �����˺�
    String contractId = "";// ��ͬ���
    String loanBankId = "";// �ſ�����
    String loanBankIdim = "";// �����ļ��ſ�����
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    String bizDateim = "";// �����ļ��������
    String bizTypeim = "";// �����ļ�����
    BigDecimal shouldCorpus = new BigDecimal(0.00);// Ӧ����������
    BigDecimal shouldInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// Ӧ�����ڱ���
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// Ӧ����Ϣ
    BigDecimal realCorpus = new BigDecimal(0.00);// ʵ������
    BigDecimal realInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// ʵ�����ڱ���
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// ʵ��������Ϣ
    BigDecimal realPunishInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal temp_realCorpus = new BigDecimal(0.00);// ʵ������
    BigDecimal temp_realInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal temp_realOverdueCorpus = new BigDecimal(0.00);// ʵ�����ڱ���
    BigDecimal temp_realOverdueInterest = new BigDecimal(0.00);// ʵ��������Ϣ
    BigDecimal temp_realPunishInterest = new BigDecimal(0.00);// ʵ����Ϣ
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING + "";// ��ͬ״̬ 11.������
    String line = "";
    String loanRepayDay = "";// ������
    String temp_bizDate = bizDate.substring(0, 4) + "-"
        + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
    List bizStList = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    List bankList = null;// Ȩ���µ�����
    if (importList.isEmpty()) {
      throw new BusinessException("�����ļ�Ϊ�գ�");
    } else if (importList.size() > 1) {
      LoancallbackTaImportDTO dto1 = (LoancallbackTaImportDTO) importList
          .get(0);
      loanBankIdim = dto1.getLoanBankId();
      bizDateim = dto1.getBizDate();
      bizTypeim = dto1.getBizType();

      int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
      String clearYear = this.getClearYear(String.valueOf(loanBankIdim));
      // ������-1���������PL002�е����������������
      if (iClearYear > Integer.parseInt(clearYear)) {
        throw new BusinessException(iClearYear + "����δ��ᣬ��������л���ҵ��");
      }
      if (!temp_bizDate.equals(bizDateim)) {
        throw new BusinessException("������ڲ�һ�£�");
      }
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(),
          loanBankIdim);
      // �ж����޲��������е�Ȩ��
      if (bankList.isEmpty()) {
        throw new BusinessException("���߱������е�Ȩ�ޣ�");
      }
      if (bizTypeim == null) {
        throw new BusinessException("ҵ�����Ͳ���Ϊ�գ�");
      } else if (!bizTypeim.equals(String
          .valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER))
          && !bizTypeim.equals(String
              .valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))
          && !bizTypeim.equals(String
              .valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
        throw new BusinessException("��ҵ�����Ͳ����ڴ˵��룡");
      }
      LoancallbackTaImportDTO dto2 = (LoancallbackTaImportDTO) importList
          .get(1);
      loanKouAcc = dto2.getLoanKouAcc();

      // �жϴ����˺���PL111���Ƿ���ڲ���״̬Ϊ�����С�
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("�˴����˺Ų����ڣ�");
      }
      // �ô����˺��ڴ�����ˮ��ͷ��PL202�����Ƿ����BIZ_ST!=6(δ����)������������ˮ��β��PL203��
      bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,
          bizTypeim);
      if (!bizStList.isEmpty()) {
        throw new BusinessException("����δ���˵�ҵ��");
      }
      // ��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();

      loanBankId = borrowerInfoDTO.getLoanBankId().toString();
      if (!loanBankId.equals(loanBankIdim)) {
        throw new BusinessException("�ſ����в�һ�£�");
      }
      for (int i = 1; i < importList.size(); i++) {
        LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
            .get(i);

        if (Double.parseDouble(dto.getRealCorpus()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getRealOverdueCorpus()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getRealInterest()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getRealOverdueInterest()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getRealPunishInterest()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getNobackCorpus()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getNobackOverdueCorpus()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getNobackInterest()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getNobackOverdueInterest()) < 0) {
          throw new BusinessException("����С��0��");
        }
        if (Double.parseDouble(dto.getNobackPunishInterest()) < 0) {
          throw new BusinessException("����С��0��");
        }
        shouldCorpus = shouldCorpus.add(new BigDecimal(dto.getRealCorpus())
            .add(new BigDecimal(dto.getNobackCorpus())));
        shouldInterest = shouldInterest.add(new BigDecimal(dto
            .getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
        shouldOverdueCorpus = shouldOverdueCorpus.add(new BigDecimal(dto
            .getRealOverdueCorpus()).add(new BigDecimal(dto
            .getNobackOverdueCorpus())));
        shouldOverdueInterest = shouldOverdueInterest.add(new BigDecimal(dto
            .getRealOverdueInterest()).add(new BigDecimal(dto
            .getNobackOverdueInterest())));
        shouldPunishInterest = shouldPunishInterest.add(new BigDecimal(dto
            .getRealPunishInterest()).add(new BigDecimal(dto
            .getNobackPunishInterest())));
        realCorpus = realCorpus.add(new BigDecimal(dto.getRealCorpus()));
        realInterest = realInterest.add(new BigDecimal(dto.getRealInterest()));
        realOverdueCorpus = realOverdueCorpus.add(new BigDecimal(dto
            .getRealOverdueCorpus()));
        realOverdueInterest = realOverdueInterest.add(new BigDecimal(dto
            .getRealOverdueInterest()));
        realPunishInterest = realPunishInterest.add(new BigDecimal(dto
            .getRealPunishInterest()));
        if (i == importList.size() - 1) {
          line = dto.getDeadLine();
        }
      }
    }
    // ������ˮͷ��
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead.setBizDate(bizDate);
    String officeId = "";
    String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
    if (loanDocNumDocument.equals("1")) {
      officeId = borrowerInfoDTO.getOfficeCode();
    } else {
      officeId = borrowerInfoDTO.getLoanBankId().toString();
    }
    loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId,
        bizDate.substring(0, 6)));
    loanFlowHead.setBizType(bizTypeim);
    loanFlowHead.setShouldCount(new BigDecimal(1));
    loanFlowHead.setShouldCorpus(shouldCorpus);
    loanFlowHead.setShouldInterest(shouldInterest);
    loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
    loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
    loanFlowHead.setShouldPunishInterest(shouldPunishInterest);
    loanFlowHead.setRealCount(new BigDecimal(1));
    loanFlowHead.setRealCorpus(realCorpus);
    loanFlowHead.setRealInterest(realInterest);
    loanFlowHead.setRealOverdueCorpus(realOverdueCorpus);
    loanFlowHead.setRealOverdueInterest(realOverdueInterest);
    loanFlowHead.setRealPunishInterest(realPunishInterest);
    loanFlowHead.setOccurMoney(new BigDecimal(0.00));
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));
    loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_IMP + "");
    loanFlowHead.setMakePerson(securityInfo.getUserName());
    loanFlowHead.setIsFinance(new Integer(1));
    loanFlowHead.setDeadLine(line);
    loanFlowHeadDAO.insert(loanFlowHead);
    // ����Ʊ�ݺ�
    LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
        .getFlowHeadId());
    loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());

    // ������ˮβ��
    if (!importList.isEmpty()) {
      if (bizTypeim
          .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))
          || bizTypeim
              .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
        if (importList.size() == 2) {
          for (int i = 1; i < importList.size(); i++) {
            LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
                .get(i);
            LoanFlowTail loanFlowTail = new LoanFlowTail();
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                .getFlowHeadId().toString()));
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())).add(
                    new BigDecimal(dto.getRealOverdueCorpus())).add(
                    new BigDecimal(dto.getNobackOverdueCorpus())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getRealOverdueCorpus())));
            loanFlowTail
                .setShouldInterest(new BigDecimal(dto.getRealInterest()).add(
                    new BigDecimal(dto.getNobackInterest())).add(
                    new BigDecimal(dto.getRealOverdueInterest())).add(
                    new BigDecimal(dto.getNobackOverdueInterest())));
            loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest())
                .add(new BigDecimal(dto.getRealOverdueInterest())));
            loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()));
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("3");
            loanFlowTailDAO.insert(loanFlowTail);
          }
        } else {
          for (int i = 1; i < importList.size(); i++) {
            LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
                .get(i);
            temp_realCorpus = new BigDecimal(dto.getRealCorpus());
            temp_realInterest = new BigDecimal(dto.getRealInterest());
            temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
            temp_realOverdueInterest = new BigDecimal(dto
                .getRealOverdueInterest());
            temp_realPunishInterest = new BigDecimal(dto
                .getRealPunishInterest());
            LoanFlowTail loanFlowTail = new LoanFlowTail();

            // ����������ݺ��������ݶ�����0������ˮ���в����������ݣ�һ����������Ϊ1������һ��Ϊ2����
            if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0
                && temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
                    temp_realPunishInterest).doubleValue() > 0) {

              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(loanKouAcc);
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getNobackCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getNobackInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);

              LoanFlowTail loanFlowTail1 = new LoanFlowTail();
              loanFlowTail1.setContractId(contractId);
              loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail1.setLoanKouAcc(loanKouAcc);
              loanFlowTail1.setYearMonth(dto.getYearMonth());
              loanFlowTail1.setShouldCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()).add(new BigDecimal(dto
                  .getNobackOverdueCorpus())));
              loanFlowTail1.setShouldInterest(new BigDecimal(dto
                  .getRealOverdueInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail1.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail1.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail1.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail1.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail1.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail1.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail1);
              // �����������ݴ���0�������ݵ���0������һ����������Ϊ1����
            } else if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(loanKouAcc);
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getNobackCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getNobackInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);
              // �����������ݴ���0�������ݵ���0������һ����������Ϊ2����
            } else if (temp_realOverdueCorpus.add(temp_realOverdueInterest)
                .add(temp_realPunishInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(loanKouAcc);
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()).add(new BigDecimal(dto
                  .getNobackOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealOverdueInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail);
            }
            // String loanRepayDay1 = this.getEndDay(dto.getYearMonth(),
            // loanRepayDay);
            // String temp_date = dto.getYearMonth().substring(0, 4) + "-"
            // + dto.getYearMonth().substring(4, 6) + "-" + loanRepayDay1;
            // ��������
            // int days = BusiTools.minusDate(temp_bizDate, temp_date);
            // if(days<=0){
            // loanFlowTail.setLoanType("1");
            // }else{
            // loanFlowTail.setLoanType("2");
            // }
            if (i == importList.size() - 1) {
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getNobackCorpus())).add(
                      new BigDecimal(dto.getRealOverdueCorpus())).add(
                      new BigDecimal(dto.getNobackOverdueCorpus())));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getRealOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(
                  new BigDecimal(dto.getNobackInterest())).add(
                  new BigDecimal(dto.getRealOverdueInterest())).add(
                  new BigDecimal(dto.getNobackOverdueInterest())));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest())
                      .add(new BigDecimal(dto.getRealOverdueInterest())));
              loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setLoanType("3");
              loanFlowTailDAO.insert(loanFlowTail);
            }
          }
        }
      } else {
        for (int i = 1; i < importList.size(); i++) {
          LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
              .get(i);
          temp_realCorpus = new BigDecimal(dto.getRealCorpus());
          temp_realInterest = new BigDecimal(dto.getRealInterest());
          temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
          temp_realOverdueInterest = new BigDecimal(dto
              .getRealOverdueInterest());
          temp_realPunishInterest = new BigDecimal(dto.getRealPunishInterest());
          LoanFlowTail loanFlowTail = new LoanFlowTail();
          // ����������ݺ��������ݶ�����0������ˮ���в����������ݣ�һ����������Ϊ1������һ��Ϊ2����
          if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0
              && temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
                  temp_realPunishInterest).doubleValue() > 0) {

            loanFlowTail.setContractId(contractId);
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())));
            loanFlowTail
                .setShouldInterest(new BigDecimal(dto.getRealInterest())
                    .add(new BigDecimal(dto.getNobackInterest())));
            // loanFlowTail.setShouldPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()).add(new
            // BigDecimal(dto.getNobackPunishInterest())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
            loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
            // loanFlowTail.setRealPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()));
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("1");
            loanFlowTailDAO.insert(loanFlowTail);

            LoanFlowTail loanFlowTail1 = new LoanFlowTail();
            loanFlowTail1.setContractId(contractId);
            loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail1.setLoanKouAcc(loanKouAcc);
            loanFlowTail1.setYearMonth(dto.getYearMonth());
            loanFlowTail1.setShouldCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()).add(new BigDecimal(dto
                .getNobackOverdueCorpus())));
            loanFlowTail1.setShouldInterest(new BigDecimal(dto
                .getRealOverdueInterest()).add(new BigDecimal(dto
                .getNobackOverdueInterest())));
            loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            loanFlowTail1.setRealCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()));
            loanFlowTail1.setRealInterest(new BigDecimal(dto
                .getRealOverdueInterest()));
            loanFlowTail1.setRealPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()));
            loanFlowTail1.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail1.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail1.setLoanType("2");
            loanFlowTailDAO.insert(loanFlowTail1);
            // �����������ݴ���0��������С��0������һ����������Ϊ1����
          } else if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0) {
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())));
            loanFlowTail
                .setShouldInterest(new BigDecimal(dto.getRealInterest())
                    .add(new BigDecimal(dto.getNobackInterest())));
            // loanFlowTail.setShouldPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()).add(new
            // BigDecimal(dto.getNobackPunishInterest())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
            loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
            // loanFlowTail.setRealPunishInterest(new
            // BigDecimal(dto.getRealPunishInterest()));
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("1");
            loanFlowTailDAO.insert(loanFlowTail);
          } else if (temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
              temp_realPunishInterest).doubleValue() > 0) {
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1
                .getFlowHeadId().toString()));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setYearMonth(dto.getYearMonth());
            loanFlowTail.setShouldCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()).add(new BigDecimal(dto
                .getNobackOverdueCorpus())));
            loanFlowTail.setShouldInterest(new BigDecimal(dto
                .getRealOverdueInterest()).add(new BigDecimal(dto
                .getNobackOverdueInterest())));
            loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            loanFlowTail.setRealCorpus(new BigDecimal(dto
                .getRealOverdueCorpus()));
            loanFlowTail.setRealInterest(new BigDecimal(dto
                .getRealOverdueInterest()));
            loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                .getRealPunishInterest()));
            loanFlowTail.setOtherInterest(new BigDecimal(0.00));
            loanFlowTail.setOccurMoney(new BigDecimal(0.00));
            loanFlowTail.setLoanType("2");
            loanFlowTailDAO.insert(loanFlowTail);
          }
          // String loanRepayDay1 = this.getEndDay(dto.getYearMonth(),
          // loanRepayDay);
          // String temp_date = dto.getYearMonth().substring(0, 4) + "-"
          // + dto.getYearMonth().substring(4, 6) + "-" + loanRepayDay1;
          // // ��������
          // int days = BusiTools.minusDate(temp_bizDate, temp_date);
          // if(days<=0){
          // loanFlowTail.setLoanType("1");
          // }else{
          // loanFlowTail.setLoanType("2");
          // }
          // loanFlowTailDAO.insert(loanFlowTail);
        }
      }
    }
    // ����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_IMP + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_DO + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId();
  }

  /**
   * ���հ������е�����ѯ
   * 
   * @param pagination
   * @return
   */
  public LoancallbackTaAF findCallbacklistByLoanBank(Pagination pagination)
      throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    BorrowerInfoDTO borrowerInfoDTO = null;
    List borrowerList = null;
    String headId = (String) pagination.getQueryCriterions().get("headId");
    List list = new ArrayList();
    BigDecimal loanRate = null;
    String bizType = "";
    String line = "";
    if (headId != null) {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      line = loanFlowHead.getDeadLine();
      String bizDate = loanFlowHead.getBizDate();
      String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
      String year = yearMonth.substring(0, 4);
      String month = yearMonth.substring(4, 6);
      String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
      String temp_bizDate = year + "-" + month + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
      List tailList = loanFlowTailDAO.queryRealLoanFlowTailByHeadId_LJ(headId);
      String contractId = "";// ��ͬ���
      String loanRepayDay = "";// ������
      bizType = loanFlowHead.getBizType();
      if (!tailList.isEmpty()) {
        ShouldBackListDTO shouldBackListDTO = (ShouldBackListDTO) tailList
            .get(0);
        contractId = shouldBackListDTO.getContractId();
        borrowerList = borrowerAccDAO
            .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
        if (!borrowerList.isEmpty()) {
          borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
        }
        // ֤������
        borrowerInfoDTO
            .setCardKind(BusiTools.getBusiValue(Integer
                .parseInt(borrowerInfoDTO.getCardKind()),
                BusiConst.DOCUMENTSSTATE));
        // ���ʽ
        borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
            .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        if (bizType
            .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))
            || bizType
                .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
          for (int i = 0; i < tailList.size() - 1; i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) tailList.get(i);
            ShouldBackListDTO dto2 = new ShouldBackListDTO();
            dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
            String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
                loanRepayDay);
            // ��������
            int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
                loanRepayDay1);
            if (days < 0) {
              days = 0;
            }
            String type = dto.getLoanKouType();
            if (type.equals("1")) {
              dto2.setLoanKouType("����");
            } else if (type.equals("2")) {
              dto2.setLoanKouType("����");
            }
            dto2.setShouldCorpus(dto.getShouldCorpus());
            dto2.setShouldInterest(dto.getShouldInterest());
            dto2.setPunishInterest(dto.getPunishInterest());
            dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest())
                .add(dto.getPunishInterest()));
            dto2.setLoanRate(dto.getLoanRate());
            // dto2.setShow_loanRate(dto.getLoanRate().multiply(new
            // BigDecimal(100))+"%");
            dto2.setShow_loanRate("");
            if (dto.getLoanRate().doubleValue() == 0) {
              dto2.setLoanRate(loanRate);
            }
            dto2.setDays(days + "");
            list.add(dto2);
          }
          ShouldBackListDTO dto = (ShouldBackListDTO) tailList.get(tailList
              .size() - 1);
          af.setAheadCorpus(dto.getShouldCorpus());
          af.setAheadInterest(dto.getShouldInterest().add(
              dto.getPunishInterest()));
          af.setDeadLine(line);
        } else {
          for (int i = 0; i < tailList.size(); i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) tailList.get(i);
            ShouldBackListDTO dto2 = new ShouldBackListDTO();
            dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
            String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
                loanRepayDay);
            // ��������
            int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
                loanRepayDay1);
            if (days < 0) {
              days = 0;
            }
            String type = dto.getLoanKouType();
            if (type.equals("1")) {
              dto2.setLoanKouType("����");
            } else if (type.equals("2")) {
              dto2.setLoanKouType("����");
            }
            dto2.setShouldCorpus(dto.getShouldCorpus());
            dto2.setShouldInterest(dto.getShouldInterest());
            dto2.setPunishInterest(dto.getPunishInterest());
            dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest())
                .add(dto.getPunishInterest()));
            dto2.setLoanRate(dto.getLoanRate());
            // dto2.setShow_loanRate(dto.getLoanRate().multiply(new
            // BigDecimal(100))+"%");
            dto2.setShow_loanRate("");
            if (dto.getLoanRate().doubleValue() == 0) {
              dto2.setLoanRate(loanRate);
            }
            dto2.setDays(days + "");
            list.add(dto2);
          }
        }
      }
      int count = 0;
      if (!list.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(list.size() - 1);
        af.setMonthYear(dto.getLoanKouYearmonth());
        count = list.size();
      }

      af.setMonthYearList(list);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(list);
      af.setBizType(loanFlowHead.getBizType());
      af.setSumCorpus(loanFlowHead.getRealCorpus().add(
          loanFlowHead.getRealOverdueCorpus()));
      af.setSumInterest(loanFlowHead.getRealInterest().add(
          loanFlowHead.getRealOverdueInterest()).add(
          loanFlowHead.getRealPunishInterest()));
      af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
      af.setOvaerLoanRepay(new BigDecimal(0.00));
      af.setRealMoney(af.getSumMoney());
      pagination.setNrOfElements(count);
    }

    return af;
  }

  /**
   * �������ȷ��
   * 
   * @param af
   * @param securityInfo
   * @throws Exception
   * @return
   */
  public String addCallbackInfo(LoancallbackTaAF af, SecurityInfo securityInfo,
      String matter) throws Exception {
    // ������Ϊ��
    String contractId = af.getBorrowerInfoDTO().getContractId();// ��ͬ���
    String paramType = "A";// ��������
    String paramValue = "";// ����ֵ
    Integer loanBankId = af.getBorrowerInfoDTO().getLoanBankId();// �ſ�����
    BigDecimal ovaerLoanRepay = af.getOvaerLoanRepay();// �������
    BigDecimal sumMoney = af.getSumMoney();// �����ܻ�����
    BigDecimal realMoney = af.getRealMoney();// ʵ�ս��
    String bizType = af.getBizType();// ����
    List bizStList = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String operator = securityInfo.getUserName();
    Integer headId = null;
    // �жϴ����˺��ڱ�PL202���Ƿ����:BIZ_ST��=6������PL203��
    // �ô����˺��ڴ�����ˮ��ͷ��PL202�����Ƿ����BIZ_ST!=6(δ����)������������ˮ��β��PL203��
    bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId, null);
    if (bizStList.size() > 0) {
      throw new BusinessException("����δ���˵�ҵ��");
    }
    // �жϸñ�ҵ�������Ƿ�Ϊ������ǰ�����һ�����廹
    if (!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
      // ���ۿ�
      headId = addLoanFlowHeadFull(bizDate, operator, af, matter, securityInfo,
          loanBankId.toString());
    } else {
      // �жϸô����˺ŵ��������������д������PL003���в�������PARAM_TYPE=A:����ά�����������and
      // �������PARAM_NUM=1�Ĳ���ֵPARAM_VALUE�Ƿ�=1:���ۿ�
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "1");
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // ���ۿ�
        headId = addLoanFlowHeadFull(bizDate, operator, af, matter,
            securityInfo, loanBankId.toString());
      } else {
        // �жϸô����˺��ڽ�����˻���PL111�й�����OVAER_LOAN_REPAY�Ƿ���ڣ�ҳ���У������ܻ�����
        if (ovaerLoanRepay.doubleValue() > sumMoney.doubleValue()) {
          // ���ۿ�
          headId = addLoanFlowHeadFull(bizDate, operator, af, matter,
              securityInfo, loanBankId.toString());
        } else {
          // �ж�ҳ���б���ʵ�ս���Ƿ���ڣ������ܻ�����-������
          if (realMoney.doubleValue() == sumMoney.subtract(ovaerLoanRepay)
              .doubleValue()) {
            // ���ۿ�
            headId = addLoanFlowHeadFull(bizDate, operator, af, matter,
                securityInfo, loanBankId.toString());
          } else {
            // ȫ��ۿ�
            headId = addLoanFlowHeadALL(bizDate, operator, af);
          }
        }
      }
    }
    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(headId.toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_DO + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    // ����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN));
    plBizActiveLog.setBizid(new BigDecimal(headId.toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(af.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    return headId.toString();
  }

  // ���ۿ�
  public Integer addLoanFlowHeadFull(String bizDate, String operator,
      LoancallbackTaAF af, String matter, SecurityInfo securityInfo,
      String loanBankId) throws Exception {
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldInterest = new BigDecimal(0.00);
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);
    BigDecimal punishInterest = new BigDecimal(0.00);
    String temp_bizDate = "";// ������ǰ��������ʱ���õ�������
    String bizYearmonth = bizDate.substring(0, 6);
    String bizType = af.getBizType();
    List list = af.getShouldBackList();
    BigDecimal aheadCorpus = af.getAheadCorpus();
    BigDecimal aheadInterest = af.getAheadInterest();
    BigDecimal aheadInterest1 = af.getAheadInterest1();
    BigDecimal occurMoney = af.getSumMoney().subtract(af.getRealMoney())
        .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    BigDecimal temp_occurMoney = new BigDecimal(0.00);
    BigDecimal temp_money = occurMoney;
    List monthYearList = af.getMonthYearList();// �����б��list
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
        punishInterest = punishInterest.add(dto.getPunishInterest());
        if (Integer.parseInt(dto.getDays()) <= 0) {
          shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
          shouldInterest = shouldInterest.add(dto.getShouldInterest());
        } else {
          shouldOverdueCorpus = shouldOverdueCorpus.add(dto.getShouldCorpus());
          shouldOverdueInterest = shouldOverdueInterest.add(dto
              .getShouldInterest());
        }
      }
    }
    if (bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
      loanFlowHead.setShouldCorpus(shouldCorpus);
      loanFlowHead.setShouldInterest(shouldInterest);
    } else {
      loanFlowHead.setShouldCorpus(shouldCorpus.add(aheadCorpus));
      loanFlowHead.setShouldInterest(shouldInterest.add(aheadInterest));
    }
    // //������ˮͷ��
    // String officeId="";
    // String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
    // if(loanDocNumDocument.equals("1")){
    // officeId=af.getBorrowerInfoDTO().getOfficeCode();
    // }else{
    // officeId=af.getBorrowerInfoDTO().getLoanBankId().toString();
    // }
    // loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId,
    // bizYearmonth));
    String officeId = "";
    String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
    if (loanDocNumDocument.equals("1")) {
      officeId = af.getBorrowerInfoDTO().getOfficeCode();
    } else {
      officeId = af.getBorrowerInfoDTO().getLoanBankId().toString();
    }
    String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanBankId + "",
        bizYearmonth.substring(0, 4));
    Map office = securityInfo.getOfficeInnerCodeMap();
    String officecode = office.get(officeId).toString();
    if (officecode.length() == 1) {
      officecode = "0" + officecode;
    }
    docNum = bizYearmonth.substring(0, 4) + officecode + "0" + loanBankId
        + docNum;
    loanFlowHead.setDocNum(docNum);
    loanFlowHead.setBizDate(bizDate);
    loanFlowHead.setBizType(bizType);
    loanFlowHead.setShouldCount(new BigDecimal(1));
    loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
    loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
    loanFlowHead.setShouldPunishInterest(punishInterest);
    loanFlowHead.setRealCount(new BigDecimal(1));
    loanFlowHead.setRealCorpus(loanFlowHead.getShouldCorpus());
    loanFlowHead.setRealInterest(loanFlowHead.getShouldInterest());
    loanFlowHead.setRealOverdueCorpus(loanFlowHead.getShouldOverdueCorpus());
    loanFlowHead
        .setRealOverdueInterest(loanFlowHead.getShouldOverdueInterest());
    loanFlowHead.setRealPunishInterest(loanFlowHead.getShouldPunishInterest());
    loanFlowHead.setOccurMoney(af.getSumMoney().subtract(af.getRealMoney())
        .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).multiply(
            new BigDecimal(-1.00)));
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));
    loanFlowHead.setLoanBankId(new BigDecimal(af.getBorrowerInfoDTO()
        .getLoanBankId().toString()));
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN + "");
    loanFlowHead.setMakePerson(operator);
    loanFlowHead.setLoanPoundageMoney(af.getLoanPoundageMoney());
    if("1".equals(af.getAheadTypeS())){
      loanFlowHead.setDeadLine(af.getDead());
    }else{
      loanFlowHead.setDeadLine(af.getDeadLine());
    }
    loanFlowHead.setIsFinance(new Integer(1));
    if (af.getAheadCheckId() != null && !"".equals(af.getAheadCheckId()))
      loanFlowHead.setAheadCheckId(new Integer(af.getAheadCheckId()));
    loanFlowHead.setReserveaB(aheadInterest1+"");
    loanFlowHeadDAO.insert(loanFlowHead);

    // ϵͳ�Զ����ɽ���ţ�ҵ������+��ˮ��
    String noteNum = "";
    noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
    LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
        .getFlowHeadId());
    loanFlowHead1.setNoteNum(noteNum);

    // ������ˮβ��
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
        LoanFlowTail loanFlowTail = new LoanFlowTail();
        loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
        loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead.getFlowHeadId()
            .toString()));
        loanFlowTail.setLoanKouAcc(af.getBorrowerInfoDTO().getLoanKouAcc());
        loanFlowTail.setYearMonth(dto.getLoanKouYearmonth());
        loanFlowTail.setShouldCorpus(dto.getShouldCorpus());
        loanFlowTail.setShouldInterest(dto.getShouldInterest());
        loanFlowTail.setShouldPunishInterest(dto.getPunishInterest());
        loanFlowTail.setRealCorpus(dto.getShouldCorpus());
        loanFlowTail.setRealInterest(dto.getShouldInterest());
        loanFlowTail.setRealPunishInterest(dto.getPunishInterest());
        loanFlowTail.setTempPunishInterest(dto.getTempInterest());
        loanFlowTail.setOtherInterest(new BigDecimal(0.00));
        loanFlowTail.setLoanRate(dto.getLoanRate());
        if (Integer.parseInt(dto.getDays()) <= 0) {
          loanFlowTail.setLoanType("1");
        } else {
          loanFlowTail.setLoanType("2");
        }
        loanFlowTail.setTempPunishInterest(dto.getTempInterest().subtract(
            dto.getPunishInterest()));
        // ע�����뷢����
        temp_occurMoney = dto.getShouldCorpus().add(dto.getShouldInterest())
            .add(dto.getPunishInterest());
        if (temp_occurMoney.doubleValue() <= temp_money.doubleValue()) {
          loanFlowTail.setOccurMoney(temp_occurMoney.multiply(new BigDecimal(
              -1.00)));
          temp_money = temp_money.subtract(temp_occurMoney);
        } else {
          loanFlowTail
              .setOccurMoney(temp_money.multiply(new BigDecimal(-1.00)));
          temp_money = new BigDecimal(0.00);
        }
        if (i == list.size() - 1) {
          temp_bizDate = dto.getLoanKouYearmonth().substring(0, 6);
        }
        loanFlowTailDAO.insert(loanFlowTail);
      }
    }
    String loanRepayDayBiz = this.getEndDay(bizDate.substring(0, 6), af
        .getBorrowerInfoDTO().getLoanRepayDay());
    String day = bizDate.substring(6, 8);
    if (temp_bizDate == "") {
      if (!monthYearList.isEmpty()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) monthYearList.get(0);
        temp_bizDate = dto.getLoanKouYearmonth().substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          temp_bizDate = BusiTools.addMonth(temp_bizDate, -1);
        }
      } else {
        // ȡ��һ��1��
        String year = bizDate.substring(0, 4);
        int iYear = Integer.parseInt(year) + 1;
        String temp_yearMonth = String.valueOf(iYear) + "01";
        temp_yearMonth = temp_yearMonth.substring(0, 6);
        if (Integer.parseInt(loanRepayDayBiz) <= Integer.parseInt(day)) {
          temp_bizDate = BusiTools.addMonth(temp_yearMonth, -1);
        } else {
          temp_bizDate = temp_yearMonth;
        }
      }
    } else if (Integer.parseInt(loanRepayDayBiz) > Integer.parseInt(day)) {
      temp_bizDate = BusiTools.addMonth(temp_bizDate, 1);
    }
    if (!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead.getFlowHeadId()
          .toString()));
      loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
      loanFlowTail.setLoanKouAcc(af.getBorrowerInfoDTO().getLoanKouAcc());
      loanFlowTail.setShouldCorpus(af.getAheadCorpus());
      loanFlowTail.setRealCorpus(af.getAheadCorpus());
      loanFlowTail.setShouldInterest(af.getAheadInterest());
      loanFlowTail.setRealInterest(af.getAheadInterest());
      loanFlowTail.setShouldPunishInterest(new BigDecimal(0.00));
      loanFlowTail.setRealPunishInterest(new BigDecimal(0.00));
      loanFlowTail.setYearMonth(temp_bizDate);
      loanFlowTail.setOtherInterest(new BigDecimal(0.00));
      loanFlowTail.setOccurMoney(temp_money.multiply(new BigDecimal(-1.00)));
      loanFlowTail.setLoanType("3");
      loanFlowTailDAO.insert(loanFlowTail);
      if (bizType.equals(BusiConst.PLBUSINESSTYPE_PARTRECOVER + "")) {
        String type = af.getAheadType();
        BigDecimal money = af.getAheadCorpus();
        /**
         * 1.��ǰ���ֻ���2.�ӳ���������3.���̻�������4.��ǰ���ֻ���ӳ���������5.��ǰ���ֻ�����̻�������
         */
        if (money.intValue() > 0) {
          if (type.equals(BusiConst.AHEADTYPE_3)) {
            af.setAheadType("1");
          } else if (type.equals(BusiConst.AHEADTYPE_1)) {
            af.setAheadType("4");
          } else {
            af.setAheadType("5");
          }
        } else {
          if (type.equals(BusiConst.AHEADTYPE_1)) {
            af.setAheadType("2");
          } else {
            af.setAheadType("3");
          }
        }
        // ����PL101_1
        if("1".equals(af.getAheadTypeS())){
          loanFlowTailDAO.insertPL101_1_jj(af.getBorrowerInfoDTO()
              .getContractId(), af.getAheadTypeS(), bizDate, af.getAheadCorpus(),
              af.getDead(), af.getCorpusInterest(), af.getOverplusCorpus()
                  .subtract(af.getAheadCorpus()), matter, loanFlowHead
                  .getFlowHeadId().toString(),af.getDead());
        }else{
          loanFlowTailDAO.insertPL101_1_jj(af.getBorrowerInfoDTO()
              .getContractId(), af.getAheadTypeS(), bizDate, af.getAheadCorpus(),
              af.getDeadLine(), af.getCorpusInterest(), af.getOverplusCorpus()
                  .subtract(af.getAheadCorpus()), matter, loanFlowHead
                  .getFlowHeadId().toString(),af.getDead());
        }
        
        String chgid = loanFlowTailDAO.selectPL101_1_jj();
        loanFlowHead1.setChgId(new Integer(chgid));
        // ����PL112_1
        if (af.getAheadCheckId() != null && !"".equals(af.getAheadCheckId())) {
          loanFlowTailDAO.updatePL112_1_jjByContractId(af.getBorrowerInfoDTO()
              .getContractId());
        }
      }
    }
    return loanFlowHead.getFlowHeadId();
  }

  // ȫ��ۿ�
  public Integer addLoanFlowHeadALL(String bizDate, String operator,
      LoancallbackTaAF af) throws Exception {
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldInterest = new BigDecimal(0.00);
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);
    BigDecimal punishInterest = new BigDecimal(0.00);
    String bizYearmonth = bizDate.substring(0, 6);
    List list = af.getShouldBackList();
    BigDecimal occurMoney = af.getOvaerLoanRepay();// �������
    BigDecimal temp_occurMoney = new BigDecimal(0.00);
    BigDecimal temp_money = occurMoney;
    BigDecimal realMoney = af.getRealMoney().add(af.getOvaerLoanRepay());// ʵ��+�������
    String paramValue = "";// ����ֵ
    BigDecimal normalCorpus = new BigDecimal(0.00);// ��������
    BigDecimal normalIntersert = new BigDecimal(0.00);// ������Ϣ
    BigDecimal overdueCorpus = new BigDecimal(0.00);// ���ڱ���
    BigDecimal overdueInterest = new BigDecimal(0.00);// ������Ϣ
    BigDecimal punishInterests = new BigDecimal(0.00);// ��Ϣ
    try {
      if (!list.isEmpty()) {
        for (int i = 0; i < list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
          punishInterest = punishInterest.add(dto.getPunishInterest());
          if (Integer.parseInt(dto.getDays()) <= 0) {
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          } else {
            shouldOverdueCorpus = shouldOverdueCorpus
                .add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto
                .getShouldInterest());
          }
        }
      }
      // if(bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")){
      // loanFlowHead.setShouldCorpus(shouldCorpus);
      // loanFlowHead.setShouldInterest(shouldInterest);
      // }
      Integer loanBankId = af.getBorrowerInfoDTO().getLoanBankId();
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, "A", "3");
      // ������ˮͷ��
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = af.getBorrowerInfoDTO().getOfficeCode();
      } else {
        officeId = af.getBorrowerInfoDTO().getLoanBankId().toString();
      }
      loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId,
          bizYearmonth));
      loanFlowHead.setBizDate(bizDate);
      loanFlowHead.setBizType(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "");
      loanFlowHead.setShouldCount(new BigDecimal(1));
      loanFlowHead.setShouldCorpus(shouldCorpus);
      loanFlowHead.setShouldInterest(shouldInterest);
      loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
      loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
      loanFlowHead.setShouldPunishInterest(punishInterest);
      loanFlowHead.setRealCount(new BigDecimal(1));
      // loanFlowHead.setRealCorpus(loanFlowHead.getShouldCorpus());
      // loanFlowHead.setRealInterest(loanFlowHead.getShouldInterest());
      // loanFlowHead.setRealOverdueCorpus(loanFlowHead.getShouldOverdueCorpus());
      // loanFlowHead.setRealOverdueInterest(loanFlowHead.getShouldOverdueInterest());
      // loanFlowHead.setRealPunishInterest(loanFlowHead.getShouldPunishInterest());
      loanFlowHead.setOccurMoney(af.getOvaerLoanRepay().multiply(
          new BigDecimal(-1.00)));
      loanFlowHead.setOtherInterest(new BigDecimal(0.00));
      loanFlowHead.setLoanBankId(new BigDecimal(af.getBorrowerInfoDTO()
          .getLoanBankId().toString()));
      loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN + "");
      loanFlowHead.setMakePerson(operator);
      loanFlowHead.setIsFinance(new Integer(1));
      loanFlowHeadDAO.insert(loanFlowHead);

      // ������ˮβ��Ҫ���ſ�
      if (!list.isEmpty()) {
        List insertList = new ArrayList();
        boolean is_sub_A = false;
        boolean is_sub_B = false;
        boolean is_sub_C = false;
        boolean is_sub_D = false;
        boolean is_sub_E = false;
        for (int j = 0; j < paramValue.length(); j++) {
          int m = 0;
          if (BusiConst.PLALLMESS_CORPUS.equals(String.valueOf(paramValue
              .charAt(j)))) {// ��������
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              if (Integer.parseInt(dto.getDays()) <= 0) {
                if (dto.getShouldCorpus().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealCorpus(dto.getShouldCorpus());
                  // normalCorpus = normalCorpus.add(dto.getShouldCorpus());
                  realMoney = realMoney.subtract(dto.getShouldCorpus());
                } else {
                  dto1.setRealCorpus(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }
              insertList.add(dto1);
            }
            is_sub_A = true;

          } else if (BusiConst.PLALLMESS_INTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// ������Ϣ

            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (Integer.parseInt(dto.getDays()) <= 0) {
                if (dto.getShouldInterest().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealInterest(dto.getShouldInterest());
                  // normalIntersert =
                  // normalIntersert.add(dto.getShouldInterest());
                  realMoney = realMoney.subtract(dto.getShouldInterest());
                } else {
                  dto1.setRealInterest(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }
              insertList.add(dto1);
            }
            is_sub_B = true;
          } else if (BusiConst.PLALLMESS_OVERDUECORPUS.equals(String
              .valueOf(paramValue.charAt(j)))) {// ���ڱ���
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (Integer.parseInt(dto.getDays()) > 0) {
                if (dto.getShouldCorpus().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealOverdueCorpus(dto.getShouldCorpus());
                  // overdueCorpus = overdueCorpus.add(dto.getShouldCorpus());
                  realMoney = realMoney.subtract(dto.getShouldCorpus());
                } else {
                  dto1.setRealOverdueCorpus(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }

              insertList.add(dto1);
            }
            is_sub_C = true;
          } else if (BusiConst.PLALLMESS_OVERDUEINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// ������Ϣ
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(i);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (Integer.parseInt(dto.getDays()) > 0) {
                if (dto.getShouldInterest().doubleValue() <= realMoney
                    .doubleValue()) {
                  dto1.setRealOverdueInterest(dto.getShouldInterest());
                  // overdueInterest =
                  // overdueInterest.add(dto.getShouldInterest());
                  realMoney = realMoney.subtract(dto.getShouldInterest());
                } else {
                  dto1.setRealOverdueInterest(realMoney);
                  realMoney = new BigDecimal(0.00);
                }
              }
              insertList.add(dto1);
            }
            is_sub_D = true;
          } else if (BusiConst.PLALLMESS_PUNISHINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// ��Ϣ
            for (int i = 0; i < list.size(); i++) {
              ShouldBackListDTO dto1 = null;
              if (!is_sub_A && !is_sub_B && !is_sub_C && !is_sub_D && !is_sub_E) {
                dto1 = new ShouldBackListDTO();
              } else {
                dto1 = (ShouldBackListDTO) insertList.get(m);
                m = m + 1;
              }
              ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
              if (dto.getPunishInterest().doubleValue() <= realMoney
                  .doubleValue()) {
                dto1.setPunishInterest(dto.getPunishInterest());
                // punishInterests =
                // punishInterests.add(dto.getPunishInterest());
                realMoney = realMoney.subtract(dto.getPunishInterest());
              } else {
                dto1.setPunishInterest(realMoney);
                realMoney = new BigDecimal(0.00);
              }
              insertList.add(dto1);
            }
            is_sub_E = true;
          }
        }
        for (int i = 0; i < list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
          ShouldBackListDTO dto1 = (ShouldBackListDTO) insertList.get(i);
          LoanFlowTail loanFlowTail = new LoanFlowTail();
          loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
              .getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(af.getBorrowerInfoDTO().getLoanKouAcc());
          loanFlowTail.setYearMonth(dto.getLoanKouYearmonth());
          loanFlowTail.setShouldCorpus(dto.getShouldCorpus());
          loanFlowTail.setShouldInterest(dto.getShouldInterest());
          loanFlowTail.setShouldPunishInterest(dto.getPunishInterest());
          loanFlowTail.setRealCorpus(dto1.getRealCorpus().add(
              dto1.getRealOverdueCorpus()));
          loanFlowTail.setRealInterest(dto1.getRealInterest().add(
              dto1.getRealOverdueInterest()));
          loanFlowTail.setRealPunishInterest(dto1.getPunishInterest());
          loanFlowTail.setTempPunishInterest(dto.getTempInterest());
          loanFlowTail.setOtherInterest(new BigDecimal(0.00));
          loanFlowTail.setLoanRate(dto.getLoanRate());
          if (Integer.parseInt(dto.getDays()) <= 0) {
            loanFlowTail.setLoanType("1");
            normalCorpus = normalCorpus.add(loanFlowTail.getRealCorpus());
            normalIntersert = normalIntersert.add(loanFlowTail
                .getRealInterest());
          } else {
            loanFlowTail.setLoanType("2");
            overdueCorpus = overdueCorpus.add(loanFlowTail.getRealCorpus());
            overdueInterest = overdueInterest.add(loanFlowTail
                .getRealInterest());
          }
          punishInterests = punishInterests.add(loanFlowTail
              .getRealPunishInterest());
          loanFlowTail.setTempPunishInterest(dto.getTempInterest().subtract(
              dto1.getPunishInterest()));
          // ע�����뷢����
          temp_occurMoney = loanFlowTail.getRealCorpus().add(
              loanFlowTail.getRealInterest()).add(
              loanFlowTail.getRealPunishInterest());
          if (temp_occurMoney.doubleValue() <= temp_money.doubleValue()) {
            loanFlowTail.setOccurMoney(temp_occurMoney.multiply(new BigDecimal(
                -1.00)));
            temp_money = temp_money.subtract(temp_occurMoney);
          } else {
            loanFlowTail.setOccurMoney(temp_money
                .multiply(new BigDecimal(-1.00)));
            temp_money = new BigDecimal(0.00);
          }
          loanFlowTailDAO.insert(loanFlowTail);
        }
      }
      // ϵͳ�Զ����ɽ���ţ�ҵ������+��ˮ��
      String noteNum = "";
      noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
      // ��ͨ��β�����ͷ��
      LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
          .getFlowHeadId());
      loanFlowHead1.setRealCorpus(normalCorpus);
      loanFlowHead1.setRealInterest(normalIntersert);
      loanFlowHead1.setRealOverdueCorpus(overdueCorpus);
      loanFlowHead1.setRealOverdueInterest(overdueInterest);
      loanFlowHead1.setRealPunishInterest(punishInterests);
      loanFlowHead1.setNoteNum(noteNum);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanFlowHead.getFlowHeadId();
  }

  /**
   * ���հ���������Ϊ�������ȷ���ı���ˮ��ҵ��״̬������--�Ǽǣ�
   * 
   * @param headId
   * @param securityInfo
   */
  public String addCallbackInfoByLoanBankId(Pagination pagination,
      String contractId, SecurityInfo securityInfo) throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("���ܽ��еǼǲ�����");
    }
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN + "");
    // ����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_SIGN + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);

    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_DO + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId().toString();
  }

  /**
   * �������ɾ��
   * 
   * @param headId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfoByBank(String headId, SecurityInfo securityInfo)
      throws Exception {
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String office = "";// ���´�����
    // �жϸñ�ҵ����PL202�е�ҵ��״̬�Ƿ�=2
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("�ñ�ҵ��Ϊ" + loanFlowHead.getBizSt()
          + "״̬������ɾ����");
    } else {
      loanFlowTailDAO.deleteLoanFlowTailAll(headId.toString());
      // ɾ��ҵ������־
      plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId.toString(), loanFlowHead
          .getBizSt());
      // ����ƾ֤��
      office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
          .getLoanBankId().toString());
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = office;
      } else {
        officeId = loanFlowHead.getLoanBankId().toString();
      }

      // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
      // officeId, loanFlowHead.getBizDate().substring(0, 6));
      plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
          .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
          loanFlowHead.getDocNum().substring(0, 4));
      // ɾ��ͷ��
      loanFlowHeadDAO.delete(loanFlowHead);
    }
    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(headId);
    plOperateLog.setOpBizId(new BigDecimal(headId));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);

  }

  /**
   * ����ά���б���ʾ
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findCallbackList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    List list = new ArrayList();
    ;
    try {
      List tem_list = null;
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String loanKouAcc = (String) pagination.getQueryCriterions().get(
          "loanKouAcc");
      String name = (String) pagination.getQueryCriterions().get("name");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String docNum = (String) pagination.getQueryCriterions().get("docNum");
      String status = (String) pagination.getQueryCriterions().get("status");
      String type = (String) pagination.getQueryCriterions().get("type");
      String dateStart = (String) pagination.getQueryCriterions().get(
          "dateStart");
      String dateEnd = (String) pagination.getQueryCriterions().get("dateEnd");
      String yesOrNo = (String) pagination.getQueryCriterions().get("yesOrNo");
      String orderBy = (String) pagination.getOrderBy();
      ;
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      tem_list = loanFlowHeadDAO.queryCallbackList_LJ(loanKouAcc, contractId,
          name, cardNum, docNum, type, loanBankId, status, orderBy, order,
          start, pageSize, securityInfo, page, dateStart, dateEnd, yesOrNo);
      if (!tem_list.isEmpty()) {
        for (int i = 0; i < tem_list.size(); i++) {
          LoancallbackTbDTO dto = (LoancallbackTbDTO) tem_list.get(i);
          LoancallbackTbDTO dto2 = new LoancallbackTbDTO();
          // ת��ҵ��״̬
          dto2.setBizSt(BusiTools.getBusiValue(
              Integer.parseInt(dto.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // ת��ҵ������
          dto2.setBizType(BusiTools.getBusiValue(Integer.parseInt(dto
              .getBizType()), BusiConst.PLBUSINESSTYPE));
          dto2.setType(dto.getBizType());
          dto2.setBorrowerName(dto.getBorrowerName());
          dto2.setCardNum(dto.getCardNum());
          dto2.setContractId(dto.getContractId());
          dto2.setDocNum(dto.getDocNum());
          dto2.setId(dto.getId());
          dto2.setLoanKouAcc(dto.getLoanKouAcc());
          dto2.setRealCorpus(dto.getRealCorpus());
          dto2.setRealInterest(dto.getRealInterest());
          dto2.setRealbackMoney(dto2.getRealCorpus()
              .add(dto2.getRealInterest()).add(dto.getRealPunishInterest()));
          dto2.setRealMoney(dto2.getRealbackMoney().add(dto.getOccurMoney()));
          if (dto2.getRealMoney().doubleValue() <= 0) {
            dto2.setRealMoney(new BigDecimal(0.00));
          }
          dto2.setRealPunishInterest(dto.getRealPunishInterest());
          dto2.setOccurMoney(dto.getOccurMoney());
          dto2.setBizDate(dto.getBizDate());
          String yesOrNo2 = dto.getYesOrNo();
          if (yesOrNo2 != null && !"".equals(yesOrNo2)) {
            dto2.setYesOrNo("��");
          } else {
            dto2.setYesOrNo("��");
          }
          if (dto.getMark() != null && !"��".equals(dto.getMark())) {
            dto2.setMark("��");
          } else {
            dto2.setMark("��");
          }
          list.add(dto2);

        }
      }
      int count = 0;
      count = loanFlowHeadDAO.queryCallbackListCounts_LJ(loanKouAcc,
          contractId, name, cardNum, docNum, type, loanBankId, status,
          securityInfo, dateStart, dateEnd, yesOrNo);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ά���б�ϼ�
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public LoancallbackTbDTO findCallbackListTotal(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String name = (String) pagination.getQueryCriterions().get("name");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String status = (String) pagination.getQueryCriterions().get("status");
    String type = (String) pagination.getQueryCriterions().get("type");
    String dateStart = (String) pagination.getQueryCriterions()
        .get("dateStart");
    String dateEnd = (String) pagination.getQueryCriterions().get("dateEnd");
    BigDecimal realCorpus = new BigDecimal(0.00);
    BigDecimal realInterest = new BigDecimal(0.00);
    BigDecimal realPunishInterest = new BigDecimal(0.00);
    BigDecimal realbackMoney = new BigDecimal(0.00);
    BigDecimal occurMoney = new BigDecimal(0.00);
    int count = 0;
    list = loanFlowHeadDAO.queryCallbackTotal_LJ(loanKouAcc, contractId, name,
        cardNum, docNum, type, loanBankId, status, securityInfo, dateStart,
        dateEnd);
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        LoancallbackTbDTO dto = (LoancallbackTbDTO) list.get(i);
        realCorpus = realCorpus.add(dto.getRealCorpus());
        realInterest = realInterest.add(dto.getRealInterest());
        realPunishInterest = realPunishInterest
            .add(dto.getRealPunishInterest());
        realbackMoney = realbackMoney.add(dto.getRealCorpus().add(
            dto.getRealInterest()).add(dto.getRealPunishInterest()));
        occurMoney = occurMoney.add(dto.getOccurMoney());
        count = count + new Integer(dto.getRealCount()).intValue();
      }
    }
    LoancallbackTbDTO dto = new LoancallbackTbDTO();
    dto.setRealCorpus(realCorpus);
    dto.setRealInterest(realInterest);
    dto.setRealPunishInterest(realPunishInterest);
    dto.setRealbackMoney(realbackMoney);
    dto.setOccurMoney(occurMoney);
    dto.setRealMoney(realbackMoney.add(occurMoney));
    dto.setRealCount(String.valueOf(count));
    if (dto.getRealMoney().doubleValue() <= 0) {
      dto.setRealMoney(new BigDecimal(0.00));
    }
    return dto;
  }

  /**
   * ����ά��ɾ���б���Ϣ
   * 
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfos(String headId, SecurityInfo securityInfo)
      throws Exception {
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String office = "";// ���´�����
    // �жϸñ�ҵ����PL202�е�ҵ��״̬BIZ_ST�Ƿ�=2��3��4
    if (!(loanFlowHead.getBizSt().equals("2")
        || loanFlowHead.getBizSt().equals("3") || loanFlowHead.getBizSt()
        .equals("4"))) {
      String bizSt = BusiTools.getBusiValue(Integer.parseInt(loanFlowHead
          .getBizSt()), BusiConst.PLBUSINESSSTATE);
      throw new BusinessException(bizSt + "״̬������ɾ����");
    } else {
      loanFlowTailDAO.deleteLoanFlowTailAll(headId);
      // ɾ��ҵ������־
      plBizActiveLogDAO
          .deletePlBizActiveLog_LJ(headId, loanFlowHead.getBizSt());
      // ����ƾ֤��
      office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
          .getLoanBankId().toString());
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = office;
      } else {
        officeId = loanFlowHead.getLoanBankId().toString();
      }
      // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
      // officeId, loanFlowHead.getBizDate().substring(0, 6));
      plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
          .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
          loanFlowHead.getDocNum().substring(0, 4));
      // ��ѯ�Ƿ�����ǰ����Ԥ��ID
      String aheadCheckId = "";
      if (loanFlowHead.getAheadCheckId() != null) {
        aheadCheckId = loanFlowHead.getAheadCheckId().toString();
      }
      if (aheadCheckId != null && !"".equals(aheadCheckId)) {
        // ����PL112_1״̬Ϊδ����
        loanFlowTailDAO.updatePL112_1_jjByAheadCheckId(aheadCheckId);
      }
      // ɾ����ǰ��������Ϣ��PL101_1
      String chgId = "";
      if (loanFlowHead.getChgId() != null) {
        chgId = loanFlowHead.getChgId().toString();
      }
      if (chgId != null && !"".equals(chgId)) {
        loanFlowTailDAO.deletePL101_1_jj(chgId);
      }
      // ɾ��ͷ��
      loanFlowHeadDAO.delete(loanFlowHead);
    }

    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpBizId(new BigDecimal(headId));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);

  }

  /**
   * ����ά������
   * 
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void callbackCallbackInfo(String headId, SecurityInfo securityInfo,
      String contractid) throws Exception {
    try {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      if(contractid!=null){
        String count = borrowerAccDAO.queryIsPl101(contractid);
        if (count != null && !"".equals(count)) {
          if (Integer.parseInt(count) > 0) {
            throw new BusinessException("�ñʺ�ͬ����δ����ͨ���ĺ�ͬ�����");
          }
  
        }
      }
      // �жϸñ�ҵ���ڴ�����ˮ��ͷ��PL202ͷ���е�״̬BIZ_ST�Ƿ�=3.�Ǽ�(20071023�޸ĵ���״̬���Ի���)
      if (!loanFlowHead.getBizSt().equals(
          String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN))
          && !loanFlowHead.getBizSt().equals(
              String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
        String bizSt = BusiTools.getBusiValue(Integer.parseInt(loanFlowHead
            .getBizSt()), BusiConst.PLBUSINESSSTATE);
        throw new BusinessException(bizSt + "״̬�����ܻ��գ�");
      } else {
        loanFlowHead.setBizSt(BusiConst.BUSINESSSTATE_SURE + "");
        // ����ҵ����־
        PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
        plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE + "");
        plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
            .toString()));
        plBizActiveLog.setOperator(securityInfo.getUserName());
        plBizActiveLog.setOpTime(new Date());
        plBizActiveLog.setType(loanFlowHead.getBizType());
        plBizActiveLogDAO.insert(plBizActiveLog);
      }
      // ���������־
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM + "");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN + "");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * ����ά���������ڲ�ѯ(����)
   * 
   * @param pagination
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackInfoMX(Pagination pagination)
      throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    try {
      BorrowerInfoDTO borrowerInfoDTO = null;
      List borrowerList = null;
      List callbacklist = null;
      List taillist = new ArrayList();
      LoanFlowHead loanFlowHead = null;
      BigDecimal loanRate = null;
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String headId = (String) pagination.getQueryCriterions().get("headId");
      String types = (String) pagination.getQueryCriterions().get("type");
      String bizDate = "";
      String dateStart = "";
      String dateEnd = "";
      BigDecimal corpusInterest = new BigDecimal(0.00);// ��ǰ������»���Ϣ
      // ��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer
          .parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
      callbacklist = loanFlowTailDAO.queryLoanFlowTailByHeadId_LJ(headId);
      int count = callbacklist.size();
      if (!types.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {// ���ʻ���
        count = callbacklist.size() - 1;
      }
      loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      String olddeadline = null;
      String chgtype = null;
      if(types.equals("3")){
        olddeadline =loanFlowTailDAO.selectPL101_1_olddeadline_wsh(headId);
        chgtype=loanFlowTailDAO.selectPL101_1_type_wsh(headId);
      }
      
      if(olddeadline!=null&&!"".equals(olddeadline)){
        //ԭʣ������
        af.setDead(olddeadline);
        //�������
        af.setChgMonth((new BigDecimal((Integer.parseInt(olddeadline)-Integer.parseInt(loanFlowHead.getDeadLine()))).abs()).toString());
      }
      if(chgtype!=null&&!"".equals(chgtype)){
        /**
         * 1.��ǰ���ֻ���2.�ӳ���������3.���̻�������4.��ǰ���ֻ���ӳ���������5.��ǰ���ֻ�����̻�������
         */
        String aheadTypeS="";
        if("1".equals(chgtype)){
          aheadTypeS="��ǰ���ֻ���";
        }
        if("2".equals(chgtype)){
          aheadTypeS="�ӳ���������";
        }
        if("3".equals(chgtype)){
          aheadTypeS=".���̻�������";
        }
        if("4".equals(chgtype)){
          aheadTypeS="��ǰ���ֻ���ӳ���������";
        }
        if("5".equals(chgtype)){
          aheadTypeS="��ǰ���ֻ�����̻�������";
        }
        af.setAheadTypeS(aheadTypeS);
      }
      String allowdays = "";
      bizDate = loanFlowHead.getBizDate();
      String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
      String year = yearMonth.substring(0, 4);
      String month = yearMonth.substring(4, 6);
      String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
      String temp_bizDate = year + "-" + month + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
      if (!callbacklist.isEmpty()) {
        for (int i = 0; i < count; i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) callbacklist.get(i);
          // ��������
          if(i == 0)
            allowdays = loanBankParaDAO.queryParamExplain_LJ(Integer
                .valueOf(dto.getLoanBankId()), "A", "5");
          ShouldBackListDTO dto2 = new ShouldBackListDTO();
          dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
          String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
              loanRepayDay);
          String temp_date = dto.getLoanKouYearmonth().substring(0, 4) + "-"
              + dto.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
          // ��������
          int days = BusiTools.minusDate(temp_bizDate, temp_date);
          if (days <= 0) {
            days = 0;
          }
          String type = dto.getLoanKouType();
          if (type.equals("1")) {
            dto2.setLoanKouType("����");
          } else if (type.equals("2")) {
            dto2.setLoanKouType("����");
          }
          if (i == 0) {
            dateStart = dto.getLoanKouYearmonth();
          }
          if (i == (count - 1)) {
            dateEnd = dto.getLoanKouYearmonth();
          }
          dto2.setShouldCorpus(dto.getShouldCorpus());
          dto2.setShouldInterest(dto.getShouldInterest());
          dto2.setPunishInterest(dto.getPunishInterest());
          dto2.setRealCorpus(dto.getRealCorpus());
          dto2.setRealInterest(dto.getRealInterest());
          dto2.setRealPunishInterest(dto.getRealPunishInterest());
          dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest())
              .add(dto.getPunishInterest()));
          dto2.setRealCiMoney(dto.getRealCorpus().add(dto.getRealInterest())
              .add(dto.getRealPunishInterest()));
          dto2.setLoanRate(dto.getLoanRate());
          if (dto.getLoanRate().doubleValue() != 0) {
            dto2.setShow_loanRate(dto.getLoanRate().multiply(
                new BigDecimal(100))
                + "%");
          } else {
            dto2.setShow_loanRate("");
          }
          if (dto2.getLoanRate().doubleValue() == 0) {
            dto2.setLoanRate(loanRate);
          }
          if (days > Integer.parseInt(allowdays))
            dto2.setDays(days + "");
          else 
            dto2.setDays("0");
          taillist.add(dto2);
        }
      }
      if (!types.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")) {// ���ʻ���
        if (!callbacklist.isEmpty()) {
          ShouldBackListDTO dto = (ShouldBackListDTO) callbacklist
              .get(callbacklist.size() - 1);
          af.setAheadCorpus(dto.getShouldCorpus());
          af.setAheadInterest(dto.getShouldInterest());
        }
        String temp_date = "";
        String days = "";// ռ������
        String loanRepayDay1 = this.getEndDay(bizDate, loanRepayDay);
        // ռ������
        if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
          if (Integer.parseInt(month) == 1) {
            month = "12";
            year = Integer.parseInt(year) - 1 + "";
          } else {
            month = Integer.parseInt(month) - 1 + "";
          }
          temp_date = year + "-" + month + "-" + loanRepayDay1;
          days = BusiTools.minusDate(temp_bizDate, temp_date) + "";
        } else {
          temp_date = year + "-" + month + "-" + loanRepayDay1;
          days = BusiTools.minusDate(temp_bizDate, temp_date) + "";
        }
        af.setLoanPoundageMoney(loanFlowHead.getLoanPoundageMoney());
        af.setDeadLine(loanFlowHead.getDeadLine());
        af.setDays(days);
      } else {
        af.setAheadCorpus(new BigDecimal(0.00));
        af.setAheadInterest(new BigDecimal(0.00));
        af.setLoanPoundageMoney(new BigDecimal(0.00));
      }
      // //��ǰ������»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
      // corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney,
      // loanRate, loanFlowHead.getDeadLine());
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(taillist);
      af.setBizType(BusiTools.getBusiValue(Integer.parseInt(loanFlowHead
          .getBizType()), BusiConst.PLBUSINESSTYPE));
      af.setSumCorpus(loanFlowHead.getRealCorpus().add(
          loanFlowHead.getRealOverdueCorpus()));
      af.setSumInterest(loanFlowHead.getRealInterest().add(
          loanFlowHead.getRealOverdueInterest()).add(
          loanFlowHead.getRealPunishInterest()));
      af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
      af.setOverOccurMoney(loanFlowHead.getOccurMoney());
      af.setRealMoney(af.getSumMoney().add(loanFlowHead.getOccurMoney()));
      af.setMonthYear(dateEnd);
    if(headId!=null&&!"".equals(headId)&&"3".equals(types)){
      corpusInterest=new BigDecimal( loanFlowTailDAO.selectPL101_1_interest_wsh(headId));
    }
     
      af.setCorpusInterest(corpusInterest);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  /**
   * ����ά����ӡ��Ϣ
   * 
   * @param headId
   * @return
   */
  public LoancallbackTaAF findPrintCallbackInfo(String headId) {
    LoancallbackTaAF af = new LoancallbackTaAF();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    LoancallbackTaAF yearMonethAF = loanFlowTailDAO.queryYearMonth(headId);
    String beginyemonth = yearMonethAF.getBegainMonthYear();
    String endyemonth = yearMonethAF.getMonthYear();
    List taillist = loanFlowTailDAO.queryPrintLoanFlowTailByHeadId_LJ(headId);
    
    BorrowerAcc borrowerAcc=new BorrowerAcc();
    String contractId = "";
    BorrowerInfoDTO borrowerInfoDTO = null;
    String yearMonth = "";
    List borrowerList = null;
    
    if (!taillist.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) taillist.get(0);
      ShouldBackListDTO dto1 = (ShouldBackListDTO) taillist
          .get(taillist.size() - 1);
      contractId = dto.getContractId();
      borrowerAcc=borrowerAccDAO.queryById(contractId);
      af.setCardNum(dto.getCardNum());
      yearMonth = dto1.getLoanKouYearmonth();
      String temploanKouType = dto.getLoanKouType();
      String batchNum = dto.getBatchNum();
      // try {
      // if(temploanKouType!=null && !"".equals(temploanKouType)){
      // String loanKouType =
      // BusiTools.getBusiValue(Integer.parseInt(temploanKouType)
      // ,BusiConst.GIVEBACK);
      // // dto.setLoanKouType(loanKouType);
      // }
      // } catch (Exception e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      if (batchNum != null && !"".equals(batchNum)) {
        dto.setYesOrNo("��");
        af.setBatchNum("��");
      } else {
        dto.setYesOrNo("��");
        af.setBatchNum("��");
      }
      // ��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      BigDecimal yga = new BigDecimal(0.00);
      BigDecimal ygb = new BigDecimal(0.00);
      BigDecimal ygc = new BigDecimal(0.00);
      BigDecimal ygd = new BigDecimal(0.00);
      BigDecimal yge = new BigDecimal(0.00);
      for (int i = 0; i < taillist.size(); i++) {
        ShouldBackListDTO shouldBackListDTO = (ShouldBackListDTO) taillist
            .get(i);
        if(shouldBackListDTO.getLoanKouType().equals("1")){//����
          yga=yga.add(shouldBackListDTO.getRealCorpus());
          ygb=ygb.add(shouldBackListDTO.getRealInterest());
        }else if(shouldBackListDTO.getLoanKouType().equals("2")){//����
          ygc=ygc.add(shouldBackListDTO.getRealCorpus());
          ygd=ygd.add(shouldBackListDTO.getRealInterest());
        }
        yge=yge.add(shouldBackListDTO.getRealPunishInterest());
        String loanBankId = shouldBackListDTO.getLoanBankId();
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
        String loanBankName = collBank.getCollBankName();
        if (batchNum != null && !"".equals(batchNum)) {
          shouldBackListDTO.setYesOrNo("��");
          af.setBatchNum("��");
        } else {
          shouldBackListDTO.setYesOrNo("��");
          af.setBatchNum("��");
        }
        shouldBackListDTO.setLoanBankName(loanBankName);
      }
      af.setYga(yga.toString());
      af.setYgb(ygb.toString());
      af.setYgc(ygc.toString());
      af.setYgd(ygd.toString());
      af.setYge(yge.toString());
    }
    
    String loanBankId = loanFlowHead.getLoanBankId().toString();
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
    List acclist = loanBankDAO.queryLoanBankAccByBankId_LJ(loanBankId);
    Iterator it = acclist.iterator();
    Object obj[] = null;
    while (it.hasNext()) {
      obj = (Object[]) it.next();
      if (obj[0] != null) {
        af.setLoanAcc(obj[0].toString());
      }
      if (obj[1] != null) {
        af.setInterestAcc(obj[1].toString());
      }
    }
    af.setRealCorpus(loanFlowHead.getRealCorpus());
    af.setRealOverduCorpus(loanFlowHead.getRealOverdueCorpus());
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(
        loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(
        loanFlowHead.getRealOverdueInterest()).add(
        loanFlowHead.getRealPunishInterest()));
    af.setOverOccurMoney(loanFlowHead.getOccurMoney());
    af.setInterest(loanFlowHead.getRealInterest());
    af.setOverdueInterest(loanFlowHead.getRealOverdueInterest());
    af.setPunishInterest(loanFlowHead.getRealPunishInterest());
    af.setMakeOP(securityDAO.queryByUserid(loanFlowHead.getMakePerson()));
    af.setClearOP(securityDAO.queryByUserid(loanFlowHead.getCheckPerson()));
    af.setClearAccountOP(securityDAO.queryByUserid(loanFlowHead.getClearAccPerson()));
    af.setBankName(collBank.getCollBankName());
    af.setShouldBackList(taillist);
    String bizType = loanFlowHead.getBizType();
    if (bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER))) {
      af.setBizType("�������");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "��"
          + yearMonth.substring(4, 6) + "��");
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))) {
//      af.setBizType("������ǰ���");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      String str[]=loanFlowTailDAO.queryPl101_1Info(headId);
      af.setData_1(str[5]);
      if("1".equals(str[0])){
        af.setData_2("���ı�");
        af.setData_11(String.valueOf(Integer.parseInt(borrowerInfoDTO.getLoanStartDate().substring(0, 4))+Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())/12)+borrowerInfoDTO.getLoanStartDate().substring(4,6)+String.valueOf(str[1].subSequence(6, 8)));
      }else{
        af.setData_2("�ı�");
        
//          String data_11 = String.valueOf(Integer.parseInt(borrowerInfoDTO
//            .getLoanStartDate().substring(0, 4))
//            + (Integer.parseInt(str[6]) - Integer.parseInt(str[3]))
//            / 12)
//            + borrowerInfoDTO.getLoanStartDate().substring(4, 6)
//            + String.valueOf(str[1].subSequence(6, 8));
        //pl101-1��������+ʣ�������
        int year=(Integer.parseInt(str[3])% 12+Integer.parseInt(str[1].substring(4, 6)))/12;
        int month=(Integer.parseInt(str[3])% 12+Integer.parseInt(str[1].substring(4, 6)))%12;
        String data_11 = String.valueOf(Integer.parseInt(str[1].substring(0, 4))
            + Integer.parseInt(str[3])/ 12+year)
            + String.valueOf(month)
            + String.valueOf(str[1].subSequence(6, 8));
        af.setData_11(data_11);
      }
      if("1".equals(str[0])){
        af.setBizType("������ǰ����,");
      }
      if("2".equals(str[0])){
        af.setBizType("�ӳ���������,");
      }
      if("3".equals(str[0])){
        af.setBizType("���̻�������,");
      }
      if("4".equals(str[0])){
        af.setBizType("������ǰ����ӳ���������,");
      }
      if("5".equals(str[0])){
        af.setBizType("������ǰ������̻�����,");
      }
      af.setData_4(str[3]);
      af.setData_5(str[4]);
      af.setData_6(str[6]);
      af.setData_7(borrowerAcc.getLoanRepayDay().toString());
      System.out.println(borrowerAcc.getReserveaA());
      af.setData_8(new BigDecimal("0" + (borrowerAcc.getReserveaA() == null ? ""
          : borrowerAcc.getReserveaA())).multiply(new BigDecimal(1000))
          .toString());
      BigDecimal loanRate=new BigDecimal("0.00");
      if (Integer.parseInt(str[3])-Integer.parseInt(str[6])+ Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())> 60) {
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVEUP));
      } else {
        // �񡪡�������ǰ�����»���Ϣȡ���ʵ�PL001��loan_rate_type=0����������
        loanRate = loanRateDAO.findMontRate(borrowerInfoDTO.getOfficeCode(),
            String.valueOf(BusiConst.PLLOANTYPE_FIVE));
      }
      af.setData_9(loanRate.multiply(new BigDecimal(1000)).toString());
      if(String.valueOf(str[1].subSequence(6, 7)).equals("0")){
        af.setData_10(String.valueOf(str[1].subSequence(7, 8)));
      }else{
        af.setData_10(String.valueOf(str[1].subSequence(6, 8)));
      }
      
      af.setMonthYear(yearMonth.substring(0, 4) + "��"
          + yearMonth.substring(4, 6) + "��");
      af.setTqhklx(loanFlowHead.getRealInterest());
      af.setTqhkbj(loanFlowHead.getRealCorpus());
    } else if (bizType
        .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
      af.setBizType("һ�����廹��");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "��"
          + yearMonth.substring(4, 6) + "��");
      af.setTqhklx(loanFlowHead.getRealInterest());
      af.setTqhkbj(loanFlowHead.getRealCorpus());
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER))) {
      af.setBizType("�������գ�");
      af.setMonthYear(loanFlowHead.getBizDate().substring(0, 4) + "��"
          + loanFlowHead.getBizDate().substring(4, 6) + "��");
    }
    if (beginyemonth != null && !"".equals(beginyemonth)) {
      af.setBegainMonthYear(beginyemonth.substring(0, 4) + "��"
          + beginyemonth.substring(4, 6) + "��");
    }
    af.setMonths(taillist.size() + "");
    String bizDate = loanFlowHead.getBizDate();
    af.setBizDate(bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8));
    af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()).add(
        af.getOverOccurMoney()));
    af.setDocNum(loanFlowHead.getDocNum());
    af.setNoteNum(loanFlowHead.getNoteNum());
    return af;
  }

  /**
   * ������������
   * 
   * @param bizDate ҵ������
   * @param yearMonth ��������
   * @param loanRepayDay ������
   * @return
   */
  public int getDays(String bizDate, String yearMonth, String loanRepayDay) {
    int days = 0;
    loanRepayDay = this.getEndDay(yearMonth, loanRepayDay);
    String temp_date = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + loanRepayDay;
    String temp_bizDate = bizDate.substring(0, 4) + "-"
        + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
    // ��������
    days = BusiTools.minusDate(temp_bizDate, temp_date);
    return days;
  }

  /**
   * ����ά����ϸ�б�������
   * 
   * @param pagination
   * @return
   */
  public List findCallbackBatchMX(Pagination pagination) throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List temp_list = null;
    List list = new ArrayList();
    try {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      String bizDate = loanFlowHead.getBizDate();
      temp_list = loanFlowTailDAO.queryFlowTailByHeadIdMX_LJ(headId, orderBy,
          order, start, pageSize);
      
      if (!temp_list.isEmpty()) {
        String allowdays = "";
        for (int i = 0; i < temp_list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) temp_list.get(i);
          // ��������
          if(i == 0)
            allowdays = loanBankParaDAO.queryParamExplain_LJ(Integer
                .valueOf(dto.getLoanBankId()), "A", "5");
          String loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
              .getLoanRepayDay());
          if (dto.getLoanKouType().equals("1")) {
            dto.setLoanKouType("����");
          } else if (dto.getLoanKouType().equals("2")) {
            dto.setLoanKouType("����");
          }
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
              loanRepayDay);
          if (days <= 0) {
            days = 0;
          }
          if (dto.getMoney().doubleValue() <= 0) {
            dto.setMoney(new BigDecimal(0.00));
          }
          if(days > Integer.parseInt(allowdays)) 
            dto.setDays(days + "");
          else
            dto.setDays("0");
          list.add(dto);
        }
      }
      int count = loanFlowTailDAO.queryFlowTailCountsByHeadIdMX_LJ(headId);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ����ά����ϸ�б��ӡ��������
   * 
   * @param pagination
   * @return
   */
  public List findCallbackBatchMXPrint(Pagination pagination) throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String orderBy = (String) pagination.getOrderBy();
    ;
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List temp_list = null;
    List list = new ArrayList();
    try {
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      String bizDate = loanFlowHead.getBizDate();
      temp_list = loanFlowTailDAO.queryFlowTailByHeadIdMXPrint_LJ(headId,
          orderBy, order, start, pageSize);
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          ShouldBackListDTO dto = (ShouldBackListDTO) temp_list.get(i);
          String loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
              .getLoanRepayDay());
          if (dto.getLoanKouType().equals("1")) {
            dto.setLoanKouType("����");
          } else if (dto.getLoanKouType().equals("2")) {
            dto.setLoanKouType("����");
          }
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
              loanRepayDay);
          if (days <= 0) {
            days = 0;
          }
          if (dto.getMoney().doubleValue() <= 0) {
            dto.setMoney(new BigDecimal(0.00));
          }
          dto.setDays(String.valueOf(days));
          list.add(dto);
        }
      }
      int count = loanFlowTailDAO.queryFlowTailCountsByHeadIdMX_LJ(headId);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ����ά����ϸ�б�ϼƣ�������
   * 
   * @param pagination
   * @return
   */
  public ShouldBackListDTO findCallbackBatchMXTotal(Pagination pagination) {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    ShouldBackListDTO dto = null;
    List list = null;
    list = loanFlowTailDAO.queryFlowTailTotalByHeadIdMX_LJ(headId);
    if (!list.isEmpty()) {
      dto = (ShouldBackListDTO) list.get(0);
      if (dto.getMoney().doubleValue() <= 0) {
        dto.setMoney(new BigDecimal(0.00));
      }
    }
    return dto;
  }

  /**
   * ȡ��������
   * 
   * @param loanBankId
   * @return
   */
  public String getClearYear(String loanBankId) {
    String year = "";// ������
    year = loanBankDAO.queryYearClearByBankId_sy(loanBankId);
    return year;
  }

  public LoancallbackTaAF findShouldLoancallbackInfo_wuht(String loanMode,
      String suminterest, String corpusInterest, String monthYear,
      String contractId, String deadLine, String sumCorpus,
      SecurityInfo securityInfo) throws Exception {
    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    try {
      // ������޸�//2007-3-11
      if (loanMode == "�ȶ��") {

        loancallbackTaAF = this
            .queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht(
                corpusInterest, monthYear, contractId, deadLine, sumCorpus,
                securityInfo);
        loancallbackTaAF.setOverplusInterestAll(loancallbackTaAF
            .getOverplusInterestAll());// ʣ����Ϣ
        List listLoanFlowTail = loanBankDAO.queryLoanFlowTail_wuht(contractId);
        BigDecimal realInterest = new BigDecimal(0.0);
        BigDecimal interestAll = new BigDecimal(0.0);
        BigDecimal sumInterest = new BigDecimal(suminterest);
        if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
          for (int i = 0; i < listLoanFlowTail.size(); i++) {
            LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail.get(i);
            realInterest = realInterest.add(loanFlowTail.getRealInterest());
          }
        }
        interestAll = interestAll.add(realInterest).add(sumInterest).add(
            loancallbackTaAF.getOverplusInterestAll());// ����Ϣ
        loancallbackTaAF.setInterestAll(interestAll);// ����Ϣ

      } else {

        loancallbackTaAF = this.queryTdShow_loancallbackByCriterions_wuht(
            corpusInterest, monthYear, contractId, deadLine, sumCorpus,
            securityInfo);
        loancallbackTaAF.setOverplusInterestAll(loancallbackTaAF
            .getOverplusInterestAll());// ʣ����Ϣ
        List listLoanFlowTail = loanBankDAO.queryLoanFlowTail_wuht(contractId);
        BigDecimal realInterest = new BigDecimal(0.0);
        BigDecimal interestAll = new BigDecimal(0.0);
        BigDecimal sumInterest = new BigDecimal(suminterest);
        if (listLoanFlowTail != null && listLoanFlowTail.size() > 0) {
          for (int i = 0; i < listLoanFlowTail.size(); i++) {
            LoanFlowTail loanFlowTail = (LoanFlowTail) listLoanFlowTail.get(i);
            realInterest = realInterest.add(loanFlowTail.getRealInterest());
          }
        }
        interestAll = interestAll.add(realInterest).add(sumInterest).add(
            loancallbackTaAF.getOverplusInterestAll());// ����Ϣ
        loancallbackTaAF.setInterestAll(interestAll);// ����Ϣ
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // ������޸�//2007-3-11

    return loancallbackTaAF;
  }

  // ������޸�//2007-3-11�����У���һЩ����ҵ��ҳ���У�����ǰ������Ӧ������ʾ������˵�����Ϣ��ʣ����Ϣ�������и����㣬�ܹ������������ǰ�����ģ�����Ϣ��ʣ����Ϣ
  public LoancallbackTaAF queryTdShow_loancallbackByCriterions_wuht(
      String corpusinterest, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // String srealinterest =
    // (String)request.getParameter("srealinterest");//ʣ����Ϣ
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// ��ǰ�����ʣ������
    String loanRate = "";// ������
    String corpusInterestloancallback = corpusinterest;// ��ǰ������»���Ϣ
    // String monthYear = monthYear;// ��������
    // String contractId = (String) request.getParameter("contractId");// ��ͬ���
    String year = "";// ��������
    // String sumCorpus = (String) request.getParameter("sumCorpus");// �����ܻ����
    String overplusLoanMoney = "";// ʣ�౾��

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }

    // ������Ϊ��
    try {
      /**
       * ������Ϊ�� ��pl203�в���������£����ǻ������һ���£���������Ҫ�ӣ�1
       * ��pl203��δ���ֵ����˵�����Ǹոմ���Ļ�δ����������������¾�Ӧ�ô�pl201����С��
       */
      /** �����ղ�Ϊ�� ���¼ӣ�1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // ҳ������ʾ

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal corpusInterest = new BigDecimal(0.00);// �»���Ϣ
      BigDecimal loanInterestMonth = new BigDecimal(0.00);// ����Ϣ
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// �±���

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          BigDecimal corpusInterest2 = new BigDecimal(
              corpusInterestloancallback);// �»���Ϣ
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              new BigDecimal(loanRate)).divide(temp, 2,
              BigDecimal.ROUND_HALF_UP);

          // �±���
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);
            corpusInterest2 = loanMoneyMonth.add(loanInterestMonth);
          } else {
            loanMoneyMonth = corpusInterest2.subtract(loanInterestMonth)
                .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          }
          // ʣ�౾��
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // ����
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // �ڴ˺�ͬ�����pl201��û���ˡ�����һ�꿪ʼ
      BigDecimal maxRate = new BigDecimal(0.00);// �������
      String offince = null;// ���´�
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // ���ʣ������
      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }
      if (Integer.parseInt(timelimit) > 0) {
        corpusInterest = this.corpusInterest(new BigDecimal(overplusLoanMoney),
            maxRate, timelimit);

        for (int i = 0; i < Integer.parseInt(timelimit); i++) {
          BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // // ����Ϣ
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              maxRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // �±���
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          } else {
            loanMoneyMonth = corpusInterest.subtract(loanInterestMonth).divide(
                temp, 2, BigDecimal.ROUND_HALF_UP);
          }
          // ʣ�౾��
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // ����
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());

          list.add(borrowerInfoDTO);
        }
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));
        }
      }

      // bgQueryTdShowAF.setCount(Integer.parseInt(timelimit));
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  // ������޸�//2007-3-11���ʻ�����ʣ����Ϣ�ķ���
  public LoancallbackTaAF queryTdShow_loancallbackByCriterions_wuht2(
      String corpusinterest, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// ��ǰ�����ʣ������
    String loanRate = "";// ������
    String corpusInterestloancallback = corpusinterest;// ��ǰ������»���Ϣ
    String year = "";// ��������
    String overplusLoanMoney = "";// ʣ�౾��

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }
    // ������Ϊ��
    try {
      /**
       * ������Ϊ�� ��pl203�в���������£����ǻ������һ���£���������Ҫ�ӣ�1
       * ��pl203��δ���ֵ����˵�����Ǹոմ���Ļ�δ����������������¾�Ӧ�ô�pl201����С��
       */
      /** �����ղ�Ϊ�� ���¼ӣ�1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // ҳ������ʾ

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal corpusInterest = new BigDecimal(0.00);// �»���Ϣ
      BigDecimal loanInterestMonth = new BigDecimal(0.00);// ����Ϣ
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// �±���
      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          // BigDecimal corpusInterest2 = new BigDecimal(
          // corpusInterestloancallback);// �»���Ϣ
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
          // new BigDecimal(loanRate)).divide(temp, 2,
          // BigDecimal.ROUND_HALF_UP);
          // System.out.println(listRestoreLoan.size()+"------listRestoreLoan.size()");
          // // �±���
          // if (i == Integer.parseInt(timelimit) - 1) {
          // loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          // corpusInterest2 = loanMoneyMonth.add(loanInterestMonth);
          // } else {
          // loanMoneyMonth = corpusInterest2.subtract(loanInterestMonth)
          // .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // }

          // // ����
          List listRestoreLoan2 = loanBankDAO
              .queryRestoreLoanbyCriterions_wuht(contractId, monthYear
                  .substring(0, 6));
          RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan2.get(0);
          loanInterestMonth = restoreLoan.getShouldInterest();
          // ʣ�౾��
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                restoreLoan.getShouldCorpus()).divide(temp, 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }
          //            
          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // �ڴ˺�ͬ�����pl201��û���ˡ�����һ�꿪ʼ
      BigDecimal maxRate = new BigDecimal(0.00);// �������
      String offince = null;// ���´�
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // ���ʣ������

      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }
      if (Integer.parseInt(timelimit) > 0) {
        corpusInterest = this.corpusInterest(new BigDecimal(overplusLoanMoney),
            maxRate, timelimit);
        for (int i = 0; i < Integer.parseInt(timelimit); i++) {
          BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // // ����Ϣ
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              maxRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // �±���
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          } else {
            loanMoneyMonth = corpusInterest.subtract(loanInterestMonth).divide(
                temp, 2, BigDecimal.ROUND_HALF_UP);
          }
          // ʣ�౾��
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // ����
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());

          list.add(borrowerInfoDTO);
        }
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));

        }
      }
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  // ������޸�//2007-3-11�ȶ�� ���ʻ�����ʣ����Ϣ�ķ���
  public LoancallbackTaAF queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht2(
      String shouldCorpus, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// ��ǰ�����ʣ������
    String loanRate = "";// ������
    // String corpusInterestloancallback = corpusinterest;// ��ǰ������»���Ϣ
    String year = "";// ��������
    String overplusLoanMoney = "";// ʣ�౾��

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }
    System.out.println(overplusLoanMoney + "---333---overplusLoanMoney");
    // ������Ϊ��
    try {
      /**
       * ������Ϊ�� ��pl203�в���������£����ǻ������һ���£���������Ҫ�ӣ�1
       * ��pl203��δ���ֵ����˵�����Ǹոմ���Ļ�δ����������������¾�Ӧ�ô�pl201����С��
       */
      /** �����ղ�Ϊ�� ���¼ӣ�1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // ҳ������ʾ

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal loanInterestMonth = new BigDecimal(0.00);// ����Ϣ
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// �±���
      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          // BigDecimal corpusInterest2 = new BigDecimal(
          // corpusInterestloancallback);// �»���Ϣ
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          // loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
          // new BigDecimal(loanRate)).divide(temp, 2,
          // BigDecimal.ROUND_HALF_UP);
          // System.out.println(listRestoreLoan.size()+"------listRestoreLoan.size()");
          // // �±���
          // if (i == Integer.parseInt(timelimit) - 1) {
          // loanMoneyMonth = new BigDecimal(overplusLoanMoney);
          // corpusInterest2 = loanMoneyMonth.add(loanInterestMonth);
          // } else {
          // loanMoneyMonth = corpusInterest2.subtract(loanInterestMonth)
          // .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
          // }

          // // ����
          List listRestoreLoan2 = loanBankDAO
              .queryRestoreLoanbyCriterions_wuht(contractId, monthYear
                  .substring(0, 6));
          RestoreLoan restoreLoan = (RestoreLoan) listRestoreLoan2.get(0);
          loanInterestMonth = restoreLoan.getShouldInterest();
          // ʣ�౾��
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                restoreLoan.getShouldCorpus()).divide(temp, 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }
          //            
          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // �ڴ˺�ͬ�����pl201��û���ˡ�����һ�꿪ʼ
      BigDecimal maxRate = new BigDecimal(0.00);// �������
      String offince = null;// ���´�
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // ���ʣ������

      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }
      // corpusInterest=this.corpusInterest(new BigDecimal(overplusLoanMoney),
      // maxRate,
      // timelimit);
      for (int i = 0; i < Integer.parseInt(timelimit); i++) {
        BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
        BigDecimal temp = new BigDecimal(1.00);
        // // ����Ϣ
        loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(maxRate)
            .divide(temp, 2, BigDecimal.ROUND_HALF_UP);
        // �±���
        if (i == Integer.parseInt(timelimit) - 1) {
          loanMoneyMonth = new BigDecimal(overplusLoanMoney);
        } else {
          loanMoneyMonth = new BigDecimal(shouldCorpus);
        }
        // ʣ�౾��
        if (i == Integer.parseInt(timelimit) - 1) {

        } else {
          overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
              loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
              .toString();
        }
        // ����
        if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
        }
        borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());
        list.add(borrowerInfoDTO);
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));
        }
      }
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  // ������޸�//2007-3-11�ȶ�� �ǵ��ʻ�����ʣ����Ϣ�ķ���
  public LoancallbackTaAF queryTdShow_loancallbackByCriterionsPlrecovertype_corpus_wuht(
      String corpusinterest, String monthYear, String contractId,
      String deadLine, String sumCorpus, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub
    // String srealinterest =
    // (String)request.getParameter("srealinterest");//ʣ����Ϣ
    List list = new ArrayList();

    LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
    String timelimit = deadLine;// ��ǰ�����ʣ������
    String loanRate = "";// ������
    // String corpusInterestloancallback = corpusinterest;// ��ǰ������»���Ϣ
    // String monthYear = monthYear;// ��������
    // String contractId = (String) request.getParameter("contractId");// ��ͬ���
    String year = "";// ��������
    // String sumCorpus = (String) request.getParameter("sumCorpus");// �����ܻ����
    String overplusLoanMoney = "";// ʣ�౾��

    List listborrowerAcc = loanBankDAO
        .queryBorrowerAccByCcontractId_wuht(contractId);

    if (listborrowerAcc != null && listborrowerAcc.size() > 0) {
      BorrowerAcc borrowerAcc = (BorrowerAcc) listborrowerAcc.get(0);
      overplusLoanMoney = borrowerAcc.getOverplusLoanMoney().subtract(
          new BigDecimal(sumCorpus)).toString();
      year = borrowerAcc.getLoanTimeLimit();
    }

    // ������Ϊ��
    try {
      /**
       * ������Ϊ�� ��pl203�в���������£����ǻ������һ���£���������Ҫ�ӣ�1
       * ��pl203��δ���ֵ����˵�����Ǹոմ���Ļ�δ����������������¾�Ӧ�ô�pl201����С��
       */
      /** �����ղ�Ϊ�� ���¼ӣ�1 * */
      if (monthYear == null || monthYear.equals("")) {
        monthYear = loanBankDAO.queryLoanFlowTaiMaxlYearMonth_wuht(contractId);
        if (monthYear == null || monthYear.equals("")) {
          monthYear = loanBankDAO
              .queryRestoreLoanMinlYearMonth_wuht(contractId);
        } else {
          monthYear = monthYear.substring(0, 6);
          if (monthYear.substring(4, 6).equals("12")
              || monthYear.substring(4, 6) == "12") {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear) + 1 + "";
          }
        }
      } else {
        monthYear = monthYear.substring(0, 6);
        if (monthYear.substring(4, 6).equals("12")
            || monthYear.substring(4, 6) == "12") {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear) + 1 + "";
        }
      }
      List listRestoreLoan = loanBankDAO.queryRestoreLoanbyCriterions_wuht(
          contractId, monthYear.substring(0, 6));

      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        RestoreLoan borrowerAcc = (RestoreLoan) listRestoreLoan.get(0);
        loanRate = borrowerAcc.getLoanRate().toString();
        // ҳ������ʾ

      }
      // bgQueryTdShowAF.setSrealinterest(srealinterest);

      BigDecimal loanInterestMonth = new BigDecimal(0.00);// ����Ϣ
      BigDecimal loanMoneyMonth = new BigDecimal(0.00);// �±���
      loanMoneyMonth = new BigDecimal(overplusLoanMoney).divide(new BigDecimal(
          timelimit), 2, BigDecimal.ROUND_HALF_UP);
      if (listRestoreLoan != null && listRestoreLoan.size() > 0) {
        for (int i = Integer.parseInt(monthYear.substring(4, 6)); i <= 12; i++) {
          // BigDecimal corpusInterest2 = new BigDecimal(
          // corpusInterestloancallback);// �»���Ϣ
          BorrowerInfoDTO borrowerInfoDTO2 = new BorrowerInfoDTO();
          BigDecimal temp = new BigDecimal(1.00);
          loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(
              new BigDecimal(loanRate)).divide(temp, 2,
              BigDecimal.ROUND_HALF_UP);

          // �±���
          if (i == Integer.parseInt(timelimit) - 1) {
            loanMoneyMonth = new BigDecimal(overplusLoanMoney);

          }
          // ʣ�౾��
          if (i == Integer.parseInt(timelimit) - 1) {

          } else {
            overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
                loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
                .toString();
          }
          // ����
          if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
            monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
          } else {
            monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
          }

          borrowerInfoDTO2.setLoanInterestMonth(loanInterestMonth.toString());
          list.add(borrowerInfoDTO2);
        }
      }
      // �ڴ˺�ͬ�����pl201��û���ˡ�����һ�꿪ʼ
      BigDecimal maxRate = new BigDecimal(0.00);// �������
      String offince = null;// ���´�
      List listBorrower = loanBankDAO.queryBorrower_wuht(contractId);
      if (listBorrower != null && listBorrower.size() > 0) {
        Borrower borrower = (Borrower) listBorrower.get(0);
        offince = borrower.getOffice();
      }

      BorrowerAcc borrowerAccLoanRateType = (BorrowerAcc) listborrowerAcc
          .get(0);
      if (Integer.parseInt(borrowerAccLoanRateType.getLoanRateType()) == 1) {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "1");
      } else {
        maxRate = loanBankDAO.queryLoanLoanRateMaxRate_wuht(offince, "0");
      }

      // ���ʣ������
      if (list != null && list.size() > 0) {
        timelimit = Integer.parseInt(timelimit) - list.size() + "";
      }

      for (int i = 0; i < Integer.parseInt(timelimit); i++) {
        BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
        BigDecimal temp = new BigDecimal(1.00);
        // // ����Ϣ
        loanInterestMonth = new BigDecimal(overplusLoanMoney).multiply(maxRate)
            .divide(temp, 2, BigDecimal.ROUND_HALF_UP);

        // �±���
        if (i == Integer.parseInt(timelimit) - 1) {
          loanMoneyMonth = new BigDecimal(overplusLoanMoney);

        }
        // ʣ�౾��
        if (i == Integer.parseInt(timelimit) - 1) {

        } else {
          overplusLoanMoney = new BigDecimal(overplusLoanMoney).subtract(
              loanMoneyMonth).divide(temp, 2, BigDecimal.ROUND_HALF_UP)
              .toString();
        }

        // ����
        if (Integer.parseInt(monthYear.substring(4, 6)) == 12) {
          monthYear = Integer.parseInt(monthYear.substring(0, 4)) + 1 + "01";
        } else {
          monthYear = Integer.parseInt(monthYear.substring(0, 6)) + 1 + "";
        }

        borrowerInfoDTO.setLoanInterestMonth(loanInterestMonth.toString());

        list.add(borrowerInfoDTO);
      }

      BigDecimal loanInterestMonthSum = new BigDecimal(0);
      if (list != null && list.size() > 0) {
        for (int j = 0; j < list.size(); j++) {
          BorrowerInfoDTO borrowerInfoDTO = (BorrowerInfoDTO) list.get(j);
          loanInterestMonthSum = loanInterestMonthSum.add(new BigDecimal(
              borrowerInfoDTO.getLoanInterestMonth()));
        }
      }

      // bgQueryTdShowAF.setCount(Integer.parseInt(timelimit));
      loancallbackTaAF.setOverplusInterestAll(loanInterestMonthSum);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return loancallbackTaAF;
  }

  public BigDecimal corpusInterest(BigDecimal overplusLoanMoney,
      BigDecimal loanRate, String timelimit) {
    BigDecimal corpusInterest = new BigDecimal(0.00);// �»���Ϣ
    BigDecimal LoanMoneyMonth = new BigDecimal(0.00);// �±���
    BigDecimal LoanInterestMonth = new BigDecimal(0.00);// ����Ϣ
    BigDecimal AllLoanMonry = new BigDecimal(0.00);// ���е�δ�����»���Ϣ* ʣ�����ޣ�
    BigDecimal AllLoanInterest = new BigDecimal(0.00);// ����δ��������Ϣ�����е�δ�����-ʣ�౾��
    // BigDecimal AllLoanInterestMonry = new BigDecimal(0.00);//
    // ����δ��������Ϣ�����е�δ�����+����δ��������Ϣ��
    BigDecimal yi = new BigDecimal(1);
    //
    // BigDecimal overplusLoanMoney = new BigDecimal(70000);// ʣ�౾��
    // BigDecimal loanRate = new BigDecimal(0.003675);// ������
    // String timelimit = "120";// ʣ������
    try {
      if (overplusLoanMoney.doubleValue() > 0) {
        BigDecimal temp_loanRate = new BigDecimal(1.00).add(loanRate);// ��1+�����ʣ�
        BigDecimal tempMoney = new BigDecimal(Math.pow(temp_loanRate
            .doubleValue(), Double.parseDouble(timelimit)));// ��1+�����ʣ�^ʣ������
        BigDecimal temp = tempMoney.subtract(new BigDecimal(1.00));// (1+������)^ʣ������-1
        corpusInterest = overplusLoanMoney.multiply(tempMoney).multiply(
            loanRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
        LoanInterestMonth = overplusLoanMoney.multiply(loanRate).divide(yi, 2,
            BigDecimal.ROUND_HALF_UP);
        LoanMoneyMonth = corpusInterest.subtract(LoanInterestMonth).divide(yi,
            2, BigDecimal.ROUND_HALF_UP);
        AllLoanMonry = corpusInterest.multiply(new BigDecimal(timelimit));
        AllLoanInterest = AllLoanMonry.subtract(overplusLoanMoney);
        // AllLoanInterestMonry = AllLoanInterest.add(AllLoanMonry);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return corpusInterest;

  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public String selectPL202_BankId_wsh(String headId) {
    // TODO Auto-generated method stub
    String bankId=null;
    try {
      bankId=loanFlowTailDAO.selectPL202_BankId_wsh(headId);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return bankId;
  }
//����Сʣ������
  public String someBackTime(String contractId, String salary) throws Exception {
    // TODO Auto-generated method stub
    String time = null;
    try {
      BorrowerAcc borrowerAcc = new BorrowerAcc();
      borrowerAcc = borrowerAccDAO.queryById(contractId);
      BigDecimal corpusInterest = new BigDecimal(0.00);// �»���Ϣ
      corpusInterest = new BigDecimal(salary).divide(new BigDecimal(2), 2,
          BigDecimal.ROUND_HALF_UP);
      List list_1 = loanRateDAO.queryRate_wsh(
          "4028810c120af23c01120b14ed840005", "0");
      List list_2 = loanRateDAO.queryRate_wsh(
          "4028810c120af23c01120b14ed840005", "1");
      RateDTO rateDTO = new RateDTO();
      rateDTO = (RateDTO) list_1.get(0);
      BigDecimal temp_varable_1 = new BigDecimal(0.00);// 
      BigDecimal temp_varable_2 = new BigDecimal(0.00);// 
      BigDecimal temp_loanRate = new BigDecimal(1.00).add(rateDTO
          .getLoanMonthRate());// ��1+�����ʣ�
      BigDecimal loanMoney = borrowerAcc.getOverplusLoanMoney();
      temp_varable_2 = corpusInterest.subtract(loanMoney.multiply(rateDTO
          .getLoanMonthRate()));
      temp_varable_1 = corpusInterest.divide(temp_varable_2, 2,
          BigDecimal.ROUND_HALF_UP);
      double a = T.log(Double.parseDouble(String.valueOf(temp_varable_1)),
          Double.parseDouble(String.valueOf(temp_loanRate)));
      rateDTO = (RateDTO) list_2.get(0);
      temp_loanRate = new BigDecimal(1.00).add(rateDTO.getLoanMonthRate());// ��1+�����ʣ�
      temp_varable_2 = corpusInterest.subtract(loanMoney.multiply(rateDTO
          .getLoanMonthRate()));
      temp_varable_1 = corpusInterest.divide(temp_varable_2, 2,
          BigDecimal.ROUND_HALF_UP);
      double b = T.log(Double.parseDouble(String.valueOf(temp_varable_1)),
          Double.parseDouble(String.valueOf(temp_loanRate)));
      String date_1 = restoreLoanDAO.find(contractId);// ��������
      String date_2 = String.valueOf(borrowerAcc.getLoanStartDate()).substring(
          0, 6);
      String months = restoreLoanDAO.findMonthBetween(date_1, date_2);
      System.out.println(Integer.parseInt(String.valueOf(a).substring(0,
          String.valueOf(a).indexOf(".")))
          + Integer.parseInt(months));
      if (Integer.parseInt(String.valueOf(a).substring(0,
          String.valueOf(a).indexOf(".")))
          + Integer.parseInt(months) < 60) {
        time = String.valueOf(Integer.parseInt(String.valueOf(a).substring(0,
            String.valueOf(a).indexOf("."))) / 12 + 1);
      } else {
        time = String.valueOf(Integer.parseInt(String.valueOf(b).substring(0,
            String.valueOf(b).indexOf("."))) / 12 + 1);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return time;
  }

}