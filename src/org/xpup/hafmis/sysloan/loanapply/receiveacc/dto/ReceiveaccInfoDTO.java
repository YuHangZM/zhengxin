package org.xpup.hafmis.sysloan.loanapply.receiveacc.dto;

public class ReceiveaccInfoDTO {
  // ��ͬ���
  private String contract_id = "";

  // ���������
  private String borrower_name = "";

  // ֤������
  private String card_kind = "";

  // ֤������
  private String card_num = "";

  // ��λ����
  private String org_name = "";

  // �ۿ�����id
  private String loan_bank_id = "";

  // ԭ�ۿ��ʺ�
  private String loan_kou_acc = "";

  public String getBorrower_name() {
    return borrower_name;
  }

  public void setBorrower_name(String borrower_name) {
    this.borrower_name = borrower_name;
  }

  public String getCard_kind() {
    return card_kind;
  }

  public void setCard_kind(String card_kind) {
    this.card_kind = card_kind;
  }

  public String getCard_num() {
    return card_num;
  }

  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }

  public String getContract_id() {
    return contract_id;
  }

  public void setContract_id(String contract_id) {
    this.contract_id = contract_id;
  }

  public String getLoan_bank_id() {
    return loan_bank_id;
  }

  public void setLoan_bank_id(String loan_bank_id) {
    this.loan_bank_id = loan_bank_id;
  }

  public String getLoan_kou_acc() {
    return loan_kou_acc;
  }

  public void setLoan_kou_acc(String loan_kou_acc) {
    this.loan_kou_acc = loan_kou_acc;
  }

  public String getOrg_name() {
    return org_name;
  }

  public void setOrg_name(String org_name) {
    this.org_name = org_name;
  }
}
