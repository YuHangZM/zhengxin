package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.dto;

public class AssurepledgechgTaDTO {
  private String contractId = "";//��ͬ���
  private String debitter = "";//��������� PL110 
  private String loanMoney = "";//�����
  private String startDate = "";//��ʼʱ��
  private String endDate = "";//����ʱ��
  private String loanTimeLimit = "";//�������
  private String loanMonthRate = "";//������
  private String corpusInterest = "";//�»���Ϣ
  private String bank = "";//�ſ�����
  private String scanInfo = "";//�鿴ɨ����Ϣ
  public String getBank() {
    return bank;
  }
  public void setBank(String bank) {
    this.bank = bank;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public String getDebitter() {
    return debitter;
  }
  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getLoanMoney() {
    return loanMoney;
  }
  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }
  public String getLoanMonthRate() {
    return loanMonthRate;
  }
  public void setLoanMonthRate(String loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }
  public String getScanInfo() {
    return scanInfo;
  }
  public void setScanInfo(String scanInfo) {
    this.scanInfo = scanInfo;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  
}
