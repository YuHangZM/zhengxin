package org.xpup.hafmis.sysloan.loancallback.bankexports.dto;

import java.math.BigDecimal;

public class BankExportsDTO {

  //�������
  private String bizDate = "";
  //��������
  private String loanKouYearmonth = "";
  //Ӧ�۽��
  private BigDecimal shouldMoney = new BigDecimal(0.00);
  //ʵ�۽��
  private BigDecimal realMoney = new BigDecimal(0.00);
  //����
  private String borrowerName = "";
  //�ſ�����
  private String loanBankId = "";
  //�ۿ��˺�
  private String loanKouAcc ="";
  //�ۿ��ʶ
  private String identifier = "";
  //���κ�
  private String batchNum = "";
  public String getBizDate() {
    return bizDate;
  }
  
  
  public BigDecimal getRealMoney() {
    return realMoney;
  }


  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }


  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getIdentifier() {
    return identifier;
  }
  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getLoanKouYearmonth() {
    return loanKouYearmonth;
  }
  public void setLoanKouYearmonth(String loanKouYearmonth) {
    this.loanKouYearmonth = loanKouYearmonth;
  }
  public BigDecimal getShouldMoney() {
    return shouldMoney;
  }
  public void setShouldMoney(BigDecimal shouldMoney) {
    this.shouldMoney = shouldMoney;
  }


  public String getBatchNum() {
    return batchNum;
  }


  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }
  
  
  
}