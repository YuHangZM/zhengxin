package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.dto;

public class InterestAccInfDTO {
  // ��������ڷ�װpl202�е�����

  private String loanBankId;

  private String loanBank;

  private String year;

  private String month;

  private String date;

  private String bgnInterest = "";// �ڳ���Ϣ

  private String bgnOverdueInterest = "";// �ڳ�������Ϣ

  private String endInterest = "";// ��ĩ��Ϣ

  private String endOverdueInterest = "";// ��ĩ������Ϣ

  private String realInterest;

  private String realOverdueInterest;

  private String bizType;

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(String realInterest) {
    this.realInterest = realInterest;
  }

  public String getRealOverdueInterest() {
    return realOverdueInterest;
  }

  public void setRealOverdueInterest(String realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }



  public String getBgnInterest() {
    return bgnInterest;
  }

  public void setBgnInterest(String bgnInterest) {
    this.bgnInterest = bgnInterest;
  }

  public String getBgnOverdueInterest() {
    return bgnOverdueInterest;
  }

  public void setBgnOverdueInterest(String bgnOverdueInterest) {
    this.bgnOverdueInterest = bgnOverdueInterest;
  }

  public String getEndInterest() {
    return endInterest;
  }

  public void setEndInterest(String endInterest) {
    this.endInterest = endInterest;
  }

  public String getEndOverdueInterest() {
    return endOverdueInterest;
  }

  public void setEndOverdueInterest(String endOverdueInterest) {
    this.endOverdueInterest = endOverdueInterest;
  }

}
