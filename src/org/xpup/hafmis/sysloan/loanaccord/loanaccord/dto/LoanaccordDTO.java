package org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto;

import java.math.BigDecimal;

public class LoanaccordDTO {
  private String contractId = ""; // ��ͬ���

  private String borrowerName = ""; // ���������

  private String cardKind = ""; // ֤������

  private String cardKindName = ""; // ��ʾ֤�����Ͷ�Ӧ������

  private String cardNum = ""; // ֤������

  private String loanBankId = ""; // �ſ�����

  private String loanBankName = ""; // �ſ���������

  private String office = ""; // ���ж�Ӧ�İ��´�

  private BigDecimal loanMoney = new BigDecimal(0.00); // �����

  private String loanTimeLimit = ""; // ��������

  private BigDecimal loanMonthRate = new BigDecimal(0.00);// ÿ������
  
  private String  temploanMonthRate ;// ��ʱ��ʾ�õ�ÿ������

  private String loanMode = ""; // ���ʽ

  private String loanModeName = ""; // ���ʽ����

  private BigDecimal corpusInterest = new BigDecimal(0.00);// �»���Ϣ

  private String loanKouAcc = "";// �����տ��˺�(�����˺�)

  private String loanStartDate = "";// ��������

  private String overTime = "";// ��������

  private String loanRepayDay = ""; // ������

  private String loanRepayDayInfo = ""; // �����շ�ʽ���������գ�ͳһ����

  private String loanRateType = ""; // ��������

  private String bizSt = ""; // pl202ҵ��״̬
  
  private String bizStName = ""; // pl202ҵ��״̬
  
  private Integer flowHeadId;   // pl202��ͷ��id
  
  private BigDecimal firstLoanMoney = new BigDecimal(0.00);// ���»�����
  
  private String operson="";   //������
  
  private String bizDate="";   //��ӡ�Ļ������
  
  private String docNum="";   //ƾ֤��
  
  private BigDecimal empId = new BigDecimal(0.00);// ְ�����
  
  private BigDecimal orgId = new BigDecimal(0.00);//��λ���
  
  private String isEntire = "";//�Ƿ��������ڻ���
  
  private  BigDecimal interestTotal = new BigDecimal(0.00);// Ӧ����Ϣ�ϼ�
  
  private String noteNum = "";//�����
 
  
  

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public BigDecimal getEmpId() {
    return empId;
  }

  public void setEmpId(BigDecimal empId) {
    this.empId = empId;
  }

  public BigDecimal getOrgId() {
    return orgId;
  }

  public void setOrgId(BigDecimal orgId) {
    this.orgId = orgId;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getOperson() {
    return operson;
  }

  public void setOperson(String operson) {
    this.operson = operson;
  }

//  public BigDecimal getFirstLoanMoney() {
//    return firstLoanMoney;
//  }
//
//  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
//    this.firstLoanMoney = firstLoanMoney;
//  }

  public Integer getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(Integer flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getLoanRateType() {
    return loanRateType;
  }

  public void setLoanRateType(String loanRateType) {
    this.loanRateType = loanRateType;
  }

  public String getLoanRepayDayInfo() {
    return loanRepayDayInfo;
  }

  public void setLoanRepayDayInfo(String loanRepayDayInfo) {
    this.loanRepayDayInfo = loanRepayDayInfo;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public String getLoanRepayDay() {
    return loanRepayDay;
  }

  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOverTime() {
    return overTime;
  }

  public void setOverTime(String overTime) {
    this.overTime = overTime;
  }

  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
  }

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public String getBizStName() {
    return bizStName;
  }

  public void setBizStName(String bizStName) {
    this.bizStName = bizStName;
  }

  public String getTemploanMonthRate() {
    return temploanMonthRate;
  }

  public void setTemploanMonthRate(String temploanMonthRate) {
    this.temploanMonthRate = temploanMonthRate;
  }

  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }

  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
  }

  public String getIsEntire() {
    return isEntire;
  }

  public void setIsEntire(String isEntire) {
    this.isEntire = isEntire;
  }

}
