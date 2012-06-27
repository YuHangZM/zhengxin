package org.xpup.hafmis.sysloan.accounthandle.carryforward.dto;

import java.math.BigDecimal;

public class CarryforwardTbDTO {
  
  private String loanBankId = ""; // �ſ�����
  
  private String loanBankName=""; // ��������

  private String loanKouAcc = ""; // �����˺�
  
  private String contractId = ""; // ��ͬ���

  private String borrowerName = ""; // ���������
  
  private String loanKouMonth = ""; //��������

  private BigDecimal shouldCorpus = new BigDecimal(0.00); // Ӧ������

  private BigDecimal shouldInterest = new BigDecimal(0.00); // Ӧ����Ϣ

  private BigDecimal newLoanInterest = new BigDecimal(0.00); // �»���Ϣ

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

  public String getLoanKouMonth() {
    return loanKouMonth;
  }

  public void setLoanKouMonth(String loanKouMonth) {
    this.loanKouMonth = loanKouMonth;
  }

  public BigDecimal getNewLoanInterest() {
    return newLoanInterest;
  }

  public void setNewLoanInterest(BigDecimal newLoanInterest) {
    this.newLoanInterest = newLoanInterest;
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

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

}
