package org.xpup.hafmis.sysloan.loanapply.giveacc.form;

import org.apache.struts.action.ActionForm;

public class GiveaccModifyAF extends ActionForm {
  // ��ͬ���
  private String contractId = "";

  // ���������
  private String borrowerName = "";

  // ֤������
  private String cardKind = "";

  // ֤������
  private String cardNum = "";

  // ԭ��������
  private String oldSellerPayBank = "";

  // �»�������
  private String newSellerPayBank = "";

  // ԭ���������ʺ�
  private String oldPayBankAcc = "";

  // �»��������ʺ�
  private String newPayBankAcc = "";

  // �۷�������
  private String sellerName = "";

  // ��ע
  private String remark = "";

  // ס������
  private String hourseType = "";

  // �ж���ν���show
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

  public String getHourseType() {
    return hourseType;
  }

  public void setHourseType(String hourseType) {
    this.hourseType = hourseType;
  }

  public String getNewPayBankAcc() {
    return newPayBankAcc;
  }

  public void setNewPayBankAcc(String newPayBankAcc) {
    this.newPayBankAcc = newPayBankAcc;
  }

  public String getNewSellerPayBank() {
    return newSellerPayBank;
  }

  public void setNewSellerPayBank(String newSellerPayBank) {
    this.newSellerPayBank = newSellerPayBank;
  }

  public String getOldPayBankAcc() {
    return oldPayBankAcc;
  }

  public void setOldPayBankAcc(String oldPayBankAcc) {
    this.oldPayBankAcc = oldPayBankAcc;
  }

  public String getOldSellerPayBank() {
    return oldSellerPayBank;
  }

  public void setOldSellerPayBank(String oldSellerPayBank) {
    this.oldSellerPayBank = oldSellerPayBank;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
