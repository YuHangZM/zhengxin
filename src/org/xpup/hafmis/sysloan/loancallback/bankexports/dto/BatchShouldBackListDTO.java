package org.xpup.hafmis.sysloan.loancallback.bankexports.dto;

import java.math.BigDecimal;

public class BatchShouldBackListDTO {

  //��������
  private String loanKouYearmonth = "";
  //��������
  private String loanKouType = "";
  //Ӧ������
  private BigDecimal shouldCorpus = new BigDecimal(0.00);
  //Ӧ����Ϣ
  private BigDecimal shouldInterest = new BigDecimal(0.00);
  //��������
  private String days = "";
  //δ����Ϣ
  private BigDecimal punishInterest = new BigDecimal(0.00);
  //Ӧ����Ϣ�ϼ�
  private BigDecimal ciMoney = new BigDecimal(0.00);
  //ÿ������
  private BigDecimal loanRate = new BigDecimal(0.00); 
  //ʵ������
  private BigDecimal realCorpus = new BigDecimal(0.00);
  //ʵ����Ϣ
  private BigDecimal realInterest = new BigDecimal(0.00);
  //ʵ����Ϣ
  private BigDecimal realPunishInterest = new BigDecimal(0.00);
  //��ʱ��Ϣ
  private BigDecimal tempInterest = new BigDecimal(0.00);
  //��������
  private String bizDate = "";
  //��ͬ���
  private String contractId = "";
  //�����˺�
  private String loanKouAcc ="";
  //�������
  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);
  //����
  private String borrowerName = "";
  //֤������
  private String cardNum = "";
  //�ܽ��
  private BigDecimal sumMoney = new BigDecimal(0.00);
  //������
  private BigDecimal occurMoney = new BigDecimal(0.00);
  //ʵ�ս��
  private BigDecimal realMoney = new BigDecimal(0.00);
  //��������
  private String loanType = "";
  //β��ID
  private String id = "";
  //������
  private String loanRepayDay = "";
  //Ӧ������
  private String shouldCount = "";
  //ʵ������
  private String realCount = "";
  //ͷ��ID
  private String headId = "";
  
  private String reserveaA = "";//��������
  private String orgId="";//��λid
  
  private String collFlag="";
  
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getReserveaA() {
    return reserveaA;
  }
  public void setReserveaA(String reserveaA) {
    this.reserveaA = reserveaA;
  }
  public String getRealCount() {
    return realCount;
  }
  public void setRealCount(String realCount) {
    this.realCount = realCount;
  }
  public BigDecimal getRealPunishInterest() {
    return realPunishInterest;
  }
  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }
  public String getHeadId() {
    return headId;
  }
  public void setHeadId(String headId) {
    this.headId = headId;
  }
  public String getShouldCount() {
    return shouldCount;
  }
  public void setShouldCount(String shouldCount) {
    this.shouldCount = shouldCount;
  }
  public String getLoanRepayDay() {
    return loanRepayDay;
  }
  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getLoanType() {
    return loanType;
  }
  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }
  public BigDecimal getRealMoney() {
    return realMoney;
  }
  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }
  public BigDecimal getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public BigDecimal getCiMoney() {
    return ciMoney;
  }
  public void setCiMoney(BigDecimal ciMoney) {
    this.ciMoney = ciMoney;
  }
  public String getDays() {
    return days;
  }
  public void setDays(String days) {
    this.days = days;
  }
  public String getLoanKouType() {
    return loanKouType;
  }
  public void setLoanKouType(String loanKouType) {
    this.loanKouType = loanKouType;
  }
  public String getLoanKouYearmonth() {
    return loanKouYearmonth;
  }
  public void setLoanKouYearmonth(String loanKouYearmonth) {
    this.loanKouYearmonth = loanKouYearmonth;
  }
  public BigDecimal getLoanRate() {
    return loanRate;
  }
  public void setLoanRate(BigDecimal loanRate) {
    this.loanRate = loanRate;
  }
  public BigDecimal getPunishInterest() {
    return punishInterest;
  }
  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }
  public BigDecimal getRealCorpus() {
    return realCorpus;
  }
  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }
  public BigDecimal getRealInterest() {
    return realInterest;
  }
  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
  }
  public BigDecimal getShouldCorpus() {
    return shouldCorpus;
  }
  public void setShouldCorpus(BigDecimal shouldCorpus) {
    this.shouldCorpus = shouldCorpus;
  }
  public BigDecimal getShouldInterest() {
    return shouldInterest;
  }
  public void setShouldInterest(BigDecimal shouldInterest) {
    this.shouldInterest = shouldInterest;
  }
  public BigDecimal getTempInterest() {
    return tempInterest;
  }
  public void setTempInterest(BigDecimal tempInterest) {
    this.tempInterest = tempInterest;
  }
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public BigDecimal getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  public String getCollFlag() {
    return collFlag;
  }
  public void setCollFlag(String collFlag) {
    this.collFlag = collFlag;
  }
  
}