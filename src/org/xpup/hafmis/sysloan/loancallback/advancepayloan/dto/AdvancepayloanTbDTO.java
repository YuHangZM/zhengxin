package org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto;

public class AdvancepayloanTbDTO {
  private String id = "";
  private String contractId = "";//��ͬ��� ����˺�ͬ���
  private String borrowerName = "";//��������� ���������
  private String cardNum = "";//֤������ ֤������
  private String operator = "";//������Ա
  private String date = "";//��������
  private String status = "";//״̬
  private String new_term = "";//      ��ʣ������
  private String type = "";//����
  
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
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getNew_term() {
    return new_term;
  }
  public void setNew_term(String new_term) {
    this.new_term = new_term;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
}
