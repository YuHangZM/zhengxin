package org.xpup.hafmis.sysloan.loanapply.receiveacc.dto;

public class GatheringAccInfoDTO {

  // ��ͬ���
  private String contractId = "";

  // ���������
  private String borrowerName = "";

  // ֤������
  private String cardNum = "";

  // �ۿ�����id
  private String loanBankId = "";

  // ԭ�ۿ��ʺ�
  private String oldBankAcc = "";

  // �¿ۿ��ʺ�
  private String newBankAcc = "";

  // �����T
  private String oprator = "";

  // pl130���I
  private String receiveBankModifyId = "";

  // ����Ա��ʵ����
  private String name = "";

  // �ۿ��˺��޸�����

  private String modifyDate = "";

  public String getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
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

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getNewBankAcc() {
    return newBankAcc;
  }

  public void setNewBankAcc(String newBankAcc) {
    this.newBankAcc = newBankAcc;
  }

  public String getOldBankAcc() {
    return oldBankAcc;
  }

  public void setOldBankAcc(String oldBankAcc) {
    this.oldBankAcc = oldBankAcc;
  }

  public String getOprator() {
    return oprator;
  }

  public void setOprator(String oprator) {
    this.oprator = oprator;
  }

  public String getReceiveBankModifyId() {
    return receiveBankModifyId;
  }

  public void setReceiveBankModifyId(String receiveBankModifyId) {
    this.receiveBankModifyId = receiveBankModifyId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
