package org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto;

public class BailpickupTaDTO {

  private String loanKouAcc = "";//�����˺�
  private String contractId = "";//��ͬ���
  private String borrowerName = "";//���������
  private String cardNum = "";//֤������
  private String bailBalance = "";//��֤�����
  private String pickUpInterest = "";//��ȡ��Ϣ
  private String pickSumMoney = "";//��ȡ�ܽ��
  private String overplusLoanMoney = "";//�������
  private String noBackMoney = "";//����δ�ջؽ��
  private String ovaerLoanRepay = "";//�������
  
  private String type = "";//���� �ж��Ƿ������ȡ
  private String isDisabled = "";//ȷ����ť����
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getIsDisabled() {
    return isDisabled;
  }
  public void setIsDisabled(String isDisabled) {
    this.isDisabled = isDisabled;
  }
  public String getBailBalance() {
    return bailBalance;
  }
  public void setBailBalance(String bailBalance) {
    this.bailBalance = bailBalance;
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
  public String getNoBackMoney() {
    return noBackMoney;
  }
  public void setNoBackMoney(String noBackMoney) {
    this.noBackMoney = noBackMoney;
  }
  public String getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(String ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public String getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(String overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getPickSumMoney() {
    return pickSumMoney;
  }
  public void setPickSumMoney(String pickSumMoney) {
    this.pickSumMoney = pickSumMoney;
  }
  public String getPickUpInterest() {
    return pickUpInterest;
  }
  public void setPickUpInterest(String pickUpInterest) {
    this.pickUpInterest = pickUpInterest;
  }
  
}
