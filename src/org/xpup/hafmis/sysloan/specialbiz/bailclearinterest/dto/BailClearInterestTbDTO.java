package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto;

/**
 * @author ��Ұ 2007-10-08
 */
public class BailClearInterestTbDTO {

  private String bizYear = null; // ��Ϣ���
  
  private String loanBankName = null; // �ſ�����

  private String loanKouAcc = null; // PL203 LOAN_KOU_ACC �����ʺ�

  private String borrowerName = null; // ���������

  private String bailBalance = null;// PL111 BAIL_BALANCE ��Ϣǰ��֤��
  
  private String occurMoney = null;// PL203 OCCUR_MONEY ��Ϣ��Ϣ  

  private String lastBalance = null;// ��Ϣ��֤��

  public String getBailBalance() {
    return bailBalance;
  }

  public void setBailBalance(String bailBalance) {
    this.bailBalance = bailBalance;
  }

  public String getBizYear() {
    return bizYear;
  }

  public void setBizYear(String bizYear) {
    this.bizYear = bizYear;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getLastBalance() {
    return lastBalance;
  }

  public void setLastBalance(String lastBalance) {
    this.lastBalance = lastBalance;
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

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
  }
  
  
}
