package org.xpup.hafmis.sysloan.loancallback.consultation.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.loancallback.consultation.bsinterface.IConsultationBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class ConsultationBS implements IConsultationBS{
  private BorrowerAccDAO borrowerAccDAO = null;

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


  // ����ҳ�棬���ݴ����˺Ų�ѯӦ������Ϣ
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    String bizDate = securityInfo.getUserInfo().getBizDate();// �������
    List shouldBackList = null;// Ӧ����Ϣ
    List bizStList = null;// ���ڲ�ѯ�Ƿ����δ���˵�״̬
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// �������
    String pldebit = "";// �ۿʽ
    Integer loanBankId = null;// �ſ�����
    List borrowerList = new ArrayList();// �˻���Ϣ
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// ��ͬ״̬ 11.������
    String contractId = "";// ��ͬ���
    String loanRepayDay = "";// ������ ��ȡӦ������Ϣʱ�õ�
    String paramType = "A";// ��������
    // ��PL003�в�ѯ�ۿʽ(ȫ��ۿ���ۿ�)
    pldebit = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "1");
    // �����˺�
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    // ҵ������
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String aheadMoney = (String)pagination.getQueryCriterions().get("aheadMoney");//��ǰ������
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String paramValue = "";
    String isAmend = "1";//�Ƿ���Ը���ʵ�ս��(0.�����ԣ�1.����)
    af.setBizType("2");
    if (loanKouAcc != null && !loanKouAcc.equals("")) {
      // �жϴ����˺���PL111���Ƿ���ڲ���״̬Ϊ�����С�
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("�˴����˺Ų����ڣ�");
      }
      pagination.getQueryCriterions().put("contractId", contractId);
      // �ô����˺��ڴ�����ˮ��ͷ��PL202�����Ƿ����BIZ_ST!=6(δ����)������������ˮ��β��PL203��
    //  bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId);
      bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,null);
      if (!bizStList.isEmpty()) {
        throw new BusinessException("����δ���˵�ҵ��");
      } 
      // ��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
      loanBankId = borrowerInfoDTO.getLoanBankId();
      // ��PL201�в�Ӧ����Ϣ
      if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay)) {
        // �����С�ڻ�����
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
            contractId, yearMonth);
      } else {
        // ����մ��ڵ��ڻ�����
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
            contractId, yearMonth);
      }
      // ���ʻ���2
      if (bizType==null) {
        bizType = BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "";
      }
      LoancallbackTaAF af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
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
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_PARTRECOVER + "")) {
        // ������ǰ����3
        af = this.partAheadInfo(bizDate, borrowerInfoDTO,af1,aheadMoney);
      } else if (bizType.equals(BusiConst.PLBUSINESSTYPE_ALLCLEAR + "")) {
        // һ���Ի���4
        af = fullAheadInfo(borrowerInfoDTO, bizDate, af1);
      }
      if(!shouldBackList.isEmpty()){
          ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList.get(shouldBackList.size()-1);
          af.setMonthYear(dto.getLoanKouYearmonth());
        }
      //�жϸô����˺ŵ��������������д������PL003���в�������PARAM_TYPE=A:����ά�����������and �������PARAM_NUM=1�Ĳ���ֵPARAM_VALUE�Ƿ�=1:���ۿ�
      paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "1");
      if(paramValue.equals(BusiConst.PLDEBITTYPE_SUFF+"")){
        isAmend = "0";
      }else if(!bizType.equals(BusiConst.PLBUSINESSTYPE_SINGLERECOVER + "")){
        isAmend = "0";
      }
      af.setIsAmend(isAmend);
      af.setOvaerLoanRepay(ovaerLoanRepay);
      af.setPldebit(pldebit);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(af1.getShouldBackList());
      af.setBizType(bizType);
      af.setLoanBalance(borrowerInfoDTO.getOverplusLoanMoney());
    }
    return af;
  }
  /**
   * �ж��Ƿ���������ǰ
   * @param bizDate
   * @param borrowerInfoDTO
   * @param af1
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  //������ǰ
  public LoancallbackTaAF partAheadInfo(String bizDate,BorrowerInfoDTO borrowerInfoDTO,LoancallbackTaAF af1,String aheadMoney)throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";//��������
    String paramValue = "";//����ֵ
    String paramExplain = "";//����˵��
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();//��������
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//�ſ�����
    String contractId = borrowerInfoDTO.getContractId();//��ͬ���
    BigDecimal aheadCorpus = new BigDecimal(0.00);//��ǰ�����
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);//ʣ�౾��
    overplusLoanMoney= borrowerInfoDTO.getOverplusLoanMoney();
    if(aheadMoney != null){
      aheadCorpus = new BigDecimal(aheadMoney);
    }else{
      aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    }
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "2",contractId);
    //�жϸô�����ں�ͬ�������PL004���в������=2�Ĳ���ֵ�Ƿ�=1(��������ǰ����)
    if(paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW+"")){
      //throw new BusinessException("��������ǰ���");
      //ȡ���ò�����Ӧ�Ĳ���˵��PARAM_EXPLAIN���жϣ��������-PL111���з�������LOAN_START_DATE���������Ƿ���ڵ��ڸ�ֵ
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "2",contractId);
      int temp_monthCounts=BusiTools.monthInterval(loanStartDate, bizDate);
      if(temp_monthCounts>=Integer.parseInt(paramExplain)){
        //ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
        //���������ǰ�������
        int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
        if(aheadCounts<Integer.parseInt(paramExplain)){
          //ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
          paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
          //������������ǰ����
          int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
          if(lineCounts<Integer.parseInt(paramExplain)){
            //����
            af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate, aheadCorpus);
          }else{
            throw new BusinessException("��������ǰ���");
          }
        }else{
          throw new BusinessException("��������ǰ���");
        }
      }else{
        throw new BusinessException("��������ǰ���");
      }
    }else{//�������ж�
//    ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
      //���������ǰ�������
      int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
      if(aheadCounts<Integer.parseInt(paramExplain)){
        //ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
        //������������ǰ����
        int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
        if(lineCounts<Integer.parseInt(paramExplain)){
          //����
          af = findPartAheadInfo(borrowerInfoDTO, af1, bizDate, aheadCorpus);
        }else{
          throw new BusinessException("��������ǰ���");
        }
      }else{
        throw new BusinessException("��������ǰ���");
      }
    }
    if(aheadMoney == null){
      af.setDeadLine("0");
    }
    return af;
  }
  /**
   * ������ǰ������Ϣ
   * @param borrowerInfoDTO
   * @param af1
   * @param bizDate
   * @param aheadMoney
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findPartAheadInfo(BorrowerInfoDTO borrowerInfoDTO,LoancallbackTaAF af1,String bizDate,BigDecimal aheadMoney)throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";//����˵��
    String paramType = "B";//��������
    String paramValue = "";//����ֵ
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
    String days = "";//ռ������ 
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//�ſ�����
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);//ʣ�౾��
    overplusLoanMoney= borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal aheadCorpus = new BigDecimal(0.00);//��ǰ�����
    BigDecimal aheadInterest = new BigDecimal(0.00);//��ǰ������Ϣ
    BigDecimal loanRate = new BigDecimal(0.00);//������
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);//�����ѽ��
    BigDecimal corpusInterest = new BigDecimal(0.00);//��ǰ������»���Ϣ
    String deadLine = "";//��ǰ�����ʣ������
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    BigDecimal sumMoney = new BigDecimal(0.00);//�ܻ����
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// �������
    BigDecimal realMoney = new BigDecimal(0.00);//ʵ�ս��
    List tempList = new ArrayList();
    String contractId = borrowerInfoDTO.getContractId();//��ͬ���
    //��ǰ������ͻ�����Ϊ
    paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "4",contractId);
    BigDecimal minMoney = new BigDecimal(paramExplain);
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = year + "-"
    + month + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    String temp_date = "";
    String callbackMonth=yearMonth;//��������
    String isAmendLine = "0";//�Ƿ�����޸�ʣ�����ޣ�0.�����ԣ�1.���ԣ�
    //ռ������
    if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
      if(Integer.parseInt(month)==1){
        month = "12";
        year=Integer.parseInt(year)-1+"";
      }else{
        month = Integer.parseInt(month)-1+"";
      }
      temp_date = year + "-" + month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";
    }else{
      temp_date = year + "-"+ month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";      
    }
    aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    //�����ѽ�������������жϸô�����ں�ͬ�������PL004���в������=7�Ĳ���ֵ��
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "7",contractId);
    //����ֵΪ1:������=��ǰ�����*����˵��/100
    if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT+"")){
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
    }else if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT+"")){
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    }else{
      loanPoundageMoney = new BigDecimal(0.00);
    }
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, yearMonth);
    if(!tempList.isEmpty()){
       shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
    }else{
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, callbackMonth);
      if(!tempList.isEmpty()){
        shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
     }
    }
    loanRate = shouldBackListDTO.getLoanRate();//�����ÿ������    
    aheadInterest = aheadCorpus.multiply(new BigDecimal(days)).multiply(loanRate).divide(new BigDecimal(30),2,BigDecimal.ROUND_HALF_UP);
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "1",contractId);
    if(paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEPAY+"")){//����ֵΪ1
      double dOverplusLoanMoney=overplusLoanMoney.subtract(af1.getSumCorpus().add(af1.getSumInterest())).subtract(aheadMoney).doubleValue();
      double dMonthIngerest=shouldBackListDTO.getShouldCorpus().add(shouldBackListDTO.getShouldInterest()).doubleValue();
      double dLine=Math.abs(Math.log((dMonthIngerest-dOverplusLoanMoney*loanRate.doubleValue())/dMonthIngerest)/Math.log(1+loanRate.doubleValue()));
      deadLine = String.valueOf(dLine);
      if(deadLine.indexOf('.',0)!=-1){
        int i=deadLine.lastIndexOf(".");
        deadLine = deadLine.substring(0,i);
      }
    }else if(paramValue.equals(BusiConst.PLRECOVERPARASCHG_SAMEMONTHS+"")){//����ֵΪ2
      //��ǰ�����ʣ������=��������-��������ºͽ�����˻���PL111�з�������֮��������
      deadLine = Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.monthInterval(bizDate, borrowerInfoDTO.getLoanStartDate()))+"";      
    }else if(paramValue.equals(BusiConst.PLRECOVERPARASCHG_CHGMONTHS+"")){//����ֵΪ3
      //Ĭ���������ǰ�����ʣ������=��������-��������ºͽ�����˻���PL111�з�������֮���������������޸ĸô������ޡ�
      deadLine = Integer.parseInt(borrowerInfoDTO.getLoanTimeLimit())-(BusiTools.monthInterval(bizDate, borrowerInfoDTO.getLoanStartDate()))+""; 
      af.setLine(paramValue);
      isAmendLine = "1";
    }
    //��ǰ������»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
    overplusLoanMoney=overplusLoanMoney.subtract(af1.getSumCorpus()).subtract(aheadMoney);
    corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney, loanRate, deadLine);
    //���λ����ܱ���=Ӧ������ϼ�+��ǰ������
    sumCorpus = af1.getSumCorpus().add(aheadMoney);
    //���λ�������Ϣ=��ǰ������Ϣ+Ӧ����Ϣ�ϼ�+��Ϣ�ϼ�
    sumInterest = aheadInterest.add(af1.getSumInterest());
    //���λ����ܽ��=�����ܻ����+�����ܻ�����Ϣ��
    sumMoney = sumCorpus.add(sumInterest);
    //�������=������˻���PL111�й������
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    //����ʵ�ս��:A�����������ڵ��ڱ����ܻ��������ʵ�ս��=0 ����B����ʵ�ս��=�����ܻ�����-�������
    if(ovaerLoanRepay.equals(sumMoney)){
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(af.getSumMoney());
    }else{
      realMoney = sumMoney.subtract(ovaerLoanRepay);
      af.setOverOccurMoney(ovaerLoanRepay);
    }
    af.setAheadCorpus(aheadCorpus);//��ǰ�����
    af.setMinMoney(minMoney);//��ǰ������ͻ����� Ϊ���ж���ǰ����ı�����޸ķ�Χ�ڴ��ڴ�ֵ��С��Ĭ�ϵ���ǰ�����
    af.setAheadInterest(aheadInterest);
    af.setDays(days);
    af.setLoanPoundageMoney(loanPoundageMoney);
    af.setDeadLine(deadLine);
    af.setCorpusInterest(corpusInterest);
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setSumMoney(sumMoney);
    af.setOvaerLoanRepay(ovaerLoanRepay);
    af.setRealMoney(realMoney);
    af.setIsAmendLine(isAmendLine);
    return af;
  }
  

  /**
   * �ж��Ƿ������廹
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  //һ�����廹
  public LoancallbackTaAF fullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,String bizDate,LoancallbackTaAF af1) throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    String paramType = "B";//��������
    String paramValue = "";//����ֵ
    String paramExplain = "";//����˵��
    String loanStartDate = borrowerInfoDTO.getLoanStartDate();//��������
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//�ſ�����
    String contractId = borrowerInfoDTO.getContractId();//��ͬ���
    //�жϸô�����ں�ͬ�������PL004���в������=3�Ĳ���ֵ�Ƿ�=1��������һ�����廹��
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "3",contractId);
    if(paramValue.equals(BusiConst.PLCOMMONSTATUS_2_NOTALLOW+"")){
      //ȡ���ò�����Ӧ�Ĳ���˵��PARAM_EXPLAIN���жϣ��������-PL111���з�������LOAN_START_DATE���������Ƿ���ڵ��ڸ�ֵ
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "3",contractId);
      int temp_monthCounts=BusiTools.monthInterval(loanStartDate, bizDate);
      if(temp_monthCounts>=Integer.parseInt(paramExplain)){
        //ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
        //���������ǰ�������
        int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
        if(aheadCounts<Integer.parseInt(paramExplain)){
          //ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
          paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
//        ������������ǰ����
          int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
          if(lineCounts<Integer.parseInt(paramExplain)){
            af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1);
          }else{
            throw new BusinessException("������һ���Խ��壡");
          }
        }else{
          throw new BusinessException("������һ���Խ��壡");
        }
      }else{
        throw new BusinessException("������һ���Խ��壡");
      }
    }else{//�������ж�
//    ȡ���ô�����ں�ͬ�������PL004���в������=5�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202��(�������1-12)��ǰ����Ĵ����Ƿ�С�ڸ�ֵ
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "5",contractId);
      //���������ǰ�������
      int aheadCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, bizDate).intValue();
      if(aheadCounts<Integer.parseInt(paramExplain)){
        //ȡ���ô�����ں�ͬ�������PL004���в������=6�Ĳ���˵�����жϸô������ڴ�����ˮ��ͷ��PL202������ǰ����Ĵ����Ƿ�С�ڸ�ֵ
        paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "6",contractId);
//      ������������ǰ����
        int lineCounts=loanFlowTailDAO.queryCallbackAheadCounts_LJ(contractId, null).intValue();;
        if(lineCounts<Integer.parseInt(paramExplain)){
          af = findFullAheadInfo(borrowerInfoDTO, bizDate, af1);
        }else{
          throw new BusinessException("������һ���Խ��壡");
        }
      }else{
        throw new BusinessException("������һ���Խ��壡");
      }
    }
    return af;
  }

  /**
   * һ�����廹Ӧ����Ϣ
   * @param borrowerInfoDTO
   * @param bizDate
   * @param af1
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findFullAheadInfo(BorrowerInfoDTO borrowerInfoDTO,String bizDate,LoancallbackTaAF af1) throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
    String paramExplain = "";//����˵��
    String paramType = "B";//��������
    String paramValue = "";//����ֵ
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();//�ſ�����
    BigDecimal aheadCorpus = new BigDecimal(0.00);//��ǰ�����
    BigDecimal overplusLoanMoney = borrowerInfoDTO.getOverplusLoanMoney();//ʣ�౾��
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
    BigDecimal aheadInterest = new BigDecimal(0.00);//��ǰ������Ϣ
    BigDecimal loanPoundageMoney = new BigDecimal(0.00);//�����ѽ��
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    BigDecimal sumMoney = new BigDecimal(0.00);//�ܻ����
    BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// �������
    BigDecimal realMoney = new BigDecimal(0.00);//ʵ�ս��
    String days = "";//ռ������ 
    String contractId = borrowerInfoDTO.getContractId();//��ͬ���
    BigDecimal monthInterest = new BigDecimal(0.00);//������
    List tempList = null;
    //��ǰ�����Ĭ�������=ʣ�౾��-Ӧ������ϼƣ��������޸ģ�
    aheadCorpus = overplusLoanMoney.subtract(af1.getSumCorpus());
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = year + "-"
    + month + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    String temp_date = "";
    String callbackMonth=yearMonth;//��������
    //  ռ������
    if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
      if(Integer.parseInt(month)==1){
        month = "12";
        year=Integer.parseInt(year)-1+"";
      }else{
        month = Integer.parseInt(month)-1+"";
      }
      temp_date = year + "-" + month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";
    }else{
      temp_date = year + "-"+ month + "-" + loanRepayDay;
      days = BusiTools.minusDate(temp_bizDate, temp_date)+"";      
    }
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, yearMonth);
    if(!tempList.isEmpty()){
       shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
    }else{
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, callbackMonth);
      if(!tempList.isEmpty()){
        shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
     }
    }
    monthInterest = shouldBackListDTO.getLoanRate();//�����ÿ������
    //��ǰ������Ϣ=��ǰ�����*ռ������*������/30
    aheadInterest = aheadCorpus.multiply(new BigDecimal(days)).multiply(monthInterest).divide(new BigDecimal(30),2,BigDecimal.ROUND_HALF_UP);
    //�����ѽ�������������жϸô�����ں�ͬ�������PL004���в������=6�Ĳ���ֵ��
    paramValue = loanContractParaDAO.queryParamValue_LJ(loanBankId, paramType, "7",contractId);
    //  ����ֵΪ1:������=��ǰ�����*����˵��/100
    if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PREPAYMENT+"")){
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = aheadCorpus.multiply(new BigDecimal(paramExplain)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
    }else if(paramValue.equals(BusiConst.PLPREPAYMENTFEES_PAYMENT+"")){//����ֵΪ2:������=����˵��
      paramExplain = loanContractParaDAO.queryParamExplain_LJ(loanBankId, paramType, "7",contractId);
      loanPoundageMoney = new BigDecimal(paramExplain);
    }else{//����ֵΪ3:������=0
      loanPoundageMoney = new BigDecimal(0.00);
    }
    //���λ����ܱ���=Ӧ������ϼ�+��ǰ������
    sumCorpus = af1.getSumCorpus().add(aheadCorpus);
    //���λ�������Ϣ=��ǰ������Ϣ+Ӧ����Ϣ�ϼ�+��Ϣ�ϼ�
    sumInterest = af1.getSumInterest().add(aheadInterest);
    //���λ����ܽ��=�����ܻ����+�����ܻ�����Ϣ��
    sumMoney = sumCorpus.add(sumInterest);
    //�������=������˻���PL111�й������
    ovaerLoanRepay = borrowerInfoDTO.getOvaerLoanRepay();
    //����ʵ�ս��:A�����������ڵ��ڱ����ܻ��������ʵ�ս��=0���˷�����=�����ܻ�����
    if(ovaerLoanRepay.doubleValue()>=sumMoney.doubleValue()){
      realMoney = new BigDecimal(0.00);
      af.setOverOccurMoney(af.getSumMoney());
    }else{//����B����ʵ�ս��=�����ܻ�����-�������
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
   * ����ҳ�������ʣ�����޼����»���Ϣ
   * @param pagination
   * @param securityInfo
   * @return
   */
  public BigDecimal getCorpusInterest(Pagination pagination, SecurityInfo securityInfo)throws Exception{
    BigDecimal corpusInterest = new BigDecimal(0.00);
    String bizDate = securityInfo.getUserInfo().getBizDate();
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String callbackMonth=yearMonth;//��������
    yearMonth = BusiTools.addMonth(yearMonth, 1);
    List tempList = null;
    ShouldBackListDTO shouldBackListDTO = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    String contractId = (String) pagination.getQueryCriterions().get("contractId");
    String deadLine = (String) pagination.getQueryCriterions().get("deadLine");
    String cIMoney = (String) pagination.getQueryCriterions().get("cIMoney");
    String aheadMoney = (String) pagination.getQueryCriterions().get("aheadMoney");
    tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, yearMonth);
    if(!tempList.isEmpty()){
       shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
    }else{
      tempList = restoreLoanDAO.queryRestoreLoanInfoByContractId_LJ(contractId, callbackMonth);
      if(!tempList.isEmpty()){
        shouldBackListDTO = (ShouldBackListDTO)tempList.get(0);
     }
    }
    //  ��PL110��PL111ȡ��Ϣ
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    BigDecimal overplusLoanMoney = new BigDecimal(0.00);//ʣ�౾��
    overplusLoanMoney= borrowerInfoDTO.getOverplusLoanMoney();
    BigDecimal loanRate = shouldBackListDTO.getLoanRate();//�����ÿ������
    overplusLoanMoney = overplusLoanMoney.subtract(new BigDecimal(cIMoney)).subtract(new BigDecimal(aheadMoney));
    //��ǰ������»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
    corpusInterest = CorpusinterestBS.getCorpusInterest(overplusLoanMoney, loanRate, deadLine);
    return corpusInterest;
  }
  
  /**
   * Ӧ����Ϣ�б�
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findCallbackList(List shouldBackList,BorrowerInfoDTO borrowerInfoDTO,String bizDate)throws Exception{
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
    String paramExplain = "";//����˵��
    String allowdays = "";// ��������
    BigDecimal temp_interest = new BigDecimal(0.00);// ��ʱ��Ϣ
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
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
    if (!shouldBackList.isEmpty()) {
      for (int i = 0; i < shouldBackList.size(); i++) {
        ShouldBackListDTO dto1 = (ShouldBackListDTO) shouldBackList.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// ��ʾ��������
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay;
        // ��������
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        if (days > 0) {
          dto2.setLoanKouType("����");// ��ʾ��������
        } else {
          dto2.setLoanKouType("����");
        }
        dto2.setShouldCorpus(dto1.getShouldCorpus().subtract(
            dto1.getRealCorpus()));// ��ʾӦ������
        dto2.setShouldInterest(dto1.getShouldInterest().subtract(
            dto1.getRealInterest()));// ��ʾӦ����Ϣ
        dto2.setDays(days + "");// ��ʾ��������
        // ��Ϣ��ס�ˡ�
        // ��������ÿ�²����ķ�Ϣ
        // �жϻ����(Ӧ������-����+Ӧ����Ϣ-��Ϣ)�Ƿ�=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // ������0�ж��Ƿ��ڿ��������ڼ�Ϣ
          // �������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���ֵPARAM_VALUE�Ƿ�=0(���������ڼ�Ϣ)
          if (isRate.equals(BusiConst.YES + "")) {// ��Ϣ
            // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����
            accountDate = dto1.getBizDate();
            String temp_day = accountDate.substring(6, 8);// �������ڵ���
            if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay)) {// С�ڵ��ڻ�����
              if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestA(bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain);
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestB(bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, dto1.getLoanRate());
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestC(bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, paramExplain);
              }
            } else {// ���ڻ�����
              if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestA(bizDate,
                    dto1.getBizDate(), loanRepayDay, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain);
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestB(bizDate,
                    dto1.getBizDate(), loanRepayDay, dto1.getShouldCorpus(),
                    dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                        .getRealInterest(), paramExplain, dto1.getLoanRate());
              } else if (interestMode
                  .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE + "")) {
                temp_interest = PunishInterestBS.getTempInterestC(bizDate,
                    dto1.getBizDate(), loanRepayDay, paramExplain);
              }
              temp_interest = temp_interest.add(dto1.getPunishInterest())
                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// �ӻ������δ����Ϣ
            }
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
              String temp_day = dto1.getBizDate().substring(6, 8);// �������ڵ���
              // ����������+�������������ɵ�������
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay, Integer.parseInt(allowdays));
              temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
              if (Integer.parseInt(temp_day) <= Integer
                  .parseInt(temp_loanRepayDay)) {// С�ڵ��ڻ�����+��������
                if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                  temp_interest = PunishInterestBS.getTempInterestD(bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                          .getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, allowdays);
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestE(bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                          .getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, dto1.getLoanRate(), allowdays);
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestF(bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay, paramExplain,
                      allowdays);
                }
              } else {// ���ڻ�����+��������
                if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
                  temp_interest = PunishInterestBS.getTempInterestA(bizDate,
                      dto1.getBizDate(), loanRepayDay,
                      dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain);
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestB(bizDate,
                      dto1.getBizDate(), loanRepayDay,
                      dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, dto1.getLoanRate());
                } else if (interestMode
                    .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE
                        + "")) {
                  temp_interest = PunishInterestBS.getTempInterestC(bizDate,
                      dto1.getBizDate(), loanRepayDay, paramExplain);
                }
                // �ӻ������δ����Ϣ
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest()));// ��ʾӦ����Ϣ�ϼ�
        dto2.setLoanRate(dto1.getLoanRate());// ��ʾÿ������
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
}