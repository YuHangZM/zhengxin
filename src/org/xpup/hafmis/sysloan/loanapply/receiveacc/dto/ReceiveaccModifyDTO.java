package org.xpup.hafmis.sysloan.loanapply.receiveacc.dto;

public class ReceiveaccModifyDTO {
  // ��ͬ���
  private String contractId = "";

  // ���������
  private String borrowerName = "";

  // ֤������
  private String cardKind = "";

  // ֤������
  private String cardNum = "";

  // ��λ����
  private String orgName = "";

  // �ۿ�����id
  private String loanankId = "";

  // ԭ�ۿ��ʺ�
  private String oldLoanKouAcc = "";

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

  public String getLoanankId() {
    return loanankId;
  }

  public void setLoanankId(String loanankId) {
    this.loanankId = loanankId;
  }

  public String getOldLoanKouAcc() {
    return oldLoanKouAcc;
  }

  public void setOldLoanKouAcc(String oldLoanKouAcc) {
    this.oldLoanKouAcc = oldLoanKouAcc;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
