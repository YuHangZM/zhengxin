package org.xpup.hafmis.sysloan.loanapply.receiveacc.form;

import org.apache.struts.action.ActionForm;

public class ReceiveaccModifyAF extends ActionForm {
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
  
  private String newloanBankName="";

  // ԭ�ۿ��ʺ�
  private String oldLoanKouAcc = "";

  // �¿ۿ��ʺ�
  private String newLoanKouAcc = "";

  // �ж���ν���showAC
  private String type = "";

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

  public String getNewLoanKouAcc() {
    return newLoanKouAcc;
  }

  public void setNewLoanKouAcc(String newLoanKouAcc) {
    this.newLoanKouAcc = newLoanKouAcc;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNewloanBankName() {
    return newloanBankName;
  }

  public void setNewloanBankName(String newloanBankName) {
    this.newloanBankName = newloanBankName;
  }
}
