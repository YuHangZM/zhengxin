package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

import java.math.BigDecimal;

public class BorrowerInfoDTO {
  //����
  private String borrowerName = "";
  //֤������
  private String cardKind = "";
  //֤������
  private String cardNum = "";
  //ʣ�౾��//�������
  private BigDecimal overplusLoanMoney = new BigDecimal(0.00); 
  //���ʽ
  private String loanMode = "";
  //��ͬ���
  private String contractId = "";
  //�����˺�
  private String loanKouAcc = "";
  //������
  private String loanRepayDay = "";
  //�������
  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);
  //�ſ�����
  private Integer loanBankId = new Integer(0);
  //��������
  private String loanStartDate = "";
  //��������
  private String loanTimeLimit = "";
  //���´�
  private String officeCode = "";
  
  // ������޸�//2007-3-11 
  String loanInterestMonth = "";// ����Ϣ
  // ������޸�//2007-3-11 
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getLoanRepayDay() {
    return loanRepayDay;
  }
  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }
  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public Integer getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(Integer loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanStartDate() {
    return loanStartDate;
  }
  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }
  public String getLoanInterestMonth() {
    return loanInterestMonth;
  }
  public void setLoanInterestMonth(String loanInterestMonth) {
    this.loanInterestMonth = loanInterestMonth;
  }

  
}