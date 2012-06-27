package org.xpup.hafmis.sysloan.accounthandle.carryforward.dto;

import java.math.BigDecimal;

public class CarryforwardTaDTO {
  
  private String contractId = ""; // ��ͬ���

  private String loanKouAcc = ""; // �����˺�

  private String borrowerName = ""; // ���������

  private BigDecimal newLoanLastMoney = new BigDecimal(0.00); // �´������

  private BigDecimal newLoanlastTimeLimit = new BigDecimal(0.00); // ��ʣ������

  private BigDecimal loanMonthRate = new BigDecimal(0.00); // ������
  
  private String temploanMonthRate ; // ��ʾ�õ�������

  private String newLoanInterest = ""; // �»���Ϣ
  
  private String loanMode="";   //���ʽ
  
  private String loanBankName=""; //��������

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getNewLoanInterest() {
    return newLoanInterest;
  }

  public void setNewLoanInterest(String newLoanInterest) {
    this.newLoanInterest = newLoanInterest;
  }

  public BigDecimal getNewLoanLastMoney() {
    return newLoanLastMoney;
  }

  public void setNewLoanLastMoney(BigDecimal newLoanLastMoney) {
    this.newLoanLastMoney = newLoanLastMoney;
  }

  public BigDecimal getNewLoanlastTimeLimit() {
    return newLoanlastTimeLimit;
  }

  public void setNewLoanlastTimeLimit(BigDecimal newLoanlastTimeLimit) {
    this.newLoanlastTimeLimit = newLoanlastTimeLimit;
  }

  public String getTemploanMonthRate() {
    return temploanMonthRate;
  }

  public void setTemploanMonthRate(String temploanMonthRate) {
    this.temploanMonthRate = temploanMonthRate;
  }
}
