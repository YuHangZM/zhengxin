package org.xpup.hafmis.sysloan.loancallback.loanerlogout.dto;

import java.math.BigDecimal;

public class LoanerlogoutTaDTO {
  private String loadKouAcc="";//�����˺�
  private String contractId="";//��ͬ���
  private String borrowerName="";//���������
  private String cardKind="";//֤������
  private String temp_cardKind="";
  private String cardNum="";//֤������
  private BigDecimal overplusLoanMoney=new BigDecimal(0.00);//ʣ�౾��
  private BigDecimal noBackMoney=new BigDecimal(0.00);//����δ�ջؽ��
  private BigDecimal ovaerLoanRepay=new BigDecimal(0.00);//���ʽ��
  private BigDecimal bailBalance=new BigDecimal(0.00);//��֤�����
  private String loanMode="";//���ʽ
  private String temp_loanMode="";
  public String getTemp_loanMode() {
    return temp_loanMode;
  }
  public void setTemp_loanMode(String temp_loanMode) {
    this.temp_loanMode = temp_loanMode;
  }
  public BigDecimal getBailBalance() {
    return bailBalance;
  }
  public void setBailBalance(BigDecimal bailBalance) {
    this.bailBalance = bailBalance;
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
  public String getLoadKouAcc() {
    return loadKouAcc;
  }
  public void setLoadKouAcc(String loadKouAcc) {
    this.loadKouAcc = loadKouAcc;
  }
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }
  public BigDecimal getNoBackMoney() {
    return noBackMoney;
  }
  public void setNoBackMoney(BigDecimal noBackMoney) {
    this.noBackMoney = noBackMoney;
  }
  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getTemp_cardKind() {
    return temp_cardKind;
  }
  public void setTemp_cardKind(String temp_cardKind) {
    this.temp_cardKind = temp_cardKind;
  }
}
