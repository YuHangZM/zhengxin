package org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto;

public class CredencemodleListDTO {
  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ��Ŀ����
   */
  private String subjectName = "";

  /*
   * ҵ������
   */
  private String bizType = "";

  /*
   * �������
   */
  private String moneyType = "";

  /*
   * �������
   */
  private String balanceDirection = "";

  /*
   * fn120����
   */
  private String id = "";

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getMoneyType() {
    return moneyType;
  }

  public void setMoneyType(String moneyType) {
    this.moneyType = moneyType;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBalanceDirection() {
    return balanceDirection;
  }

  public void setBalanceDirection(String balanceDirection) {
    this.balanceDirection = balanceDirection;
  }
}
