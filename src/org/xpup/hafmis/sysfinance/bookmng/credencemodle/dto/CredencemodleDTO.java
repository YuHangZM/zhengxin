package org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto;

public class CredencemodleDTO {
  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ��Ŀ����
   */
  private String subjectName = "";
  
  /*
   * ��Ŀ����
   */
  private String subjectDirection = "";

  /*
   * ҵ������
   */
  private String bizType = "";

  /*
   * �������
   */
  private String moneyType = "";

  /*
   * ժҪ
   */
  private String summray = "";

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

  public String getSubjectDirection() {
    return subjectDirection;
  }

  public void setSubjectDirection(String subjectDirection) {
    this.subjectDirection = subjectDirection;
  }

  public String getSummray() {
    return summray;
  }

  public void setSummray(String summray) {
    this.summray = summray;
  }
}
