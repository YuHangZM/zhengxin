package org.xpup.hafmis.sysloan.loanaccord.printplan.dto;

import java.math.BigDecimal;

public class PrintplanListDTO {
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
  //��ʾ�õ�ÿ������
  private String temploanRate ; 
  //ʵ������
  private BigDecimal realCorpus = new BigDecimal(0.00);
  //ʵ����Ϣ
  private BigDecimal realInterest = new BigDecimal(0.00);
  //��ʱ��Ϣ
  private BigDecimal tempInterest = new BigDecimal(0.00);
  //��������
  private String bizDate = "";
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
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
  public String getTemploanRate() {
    return temploanRate;
  }
  public void setTemploanRate(String temploanRate) {
    this.temploanRate = temploanRate;
  }
}
