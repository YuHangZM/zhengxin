package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

import java.math.BigDecimal;

public class SubjectrelationInfoTaDTO {
  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ��Ŀ����
   */
  private String subjectName = "";

  /*
   * �������
   */
  private String balanceDirection = "";

  /*
   * ��Ŀ���
   */
  private BigDecimal balance = new BigDecimal(0.00);

  /*
   * һ����Ŀ
   */
  private String firstSubjectCode = "";

  /*
   * �������
   */
  private String calculRelaType = "";

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getBalanceDirection() {
    return balanceDirection;
  }

  public void setBalanceDirection(String balanceDirection) {
    this.balanceDirection = balanceDirection;
  }

  public String getCalculRelaType() {
    return calculRelaType;
  }

  public void setCalculRelaType(String calculRelaType) {
    this.calculRelaType = calculRelaType;
  }

  public String getFirstSubjectCode() {
    return firstSubjectCode;
  }

  public void setFirstSubjectCode(String firstSubjectCode) {
    this.firstSubjectCode = firstSubjectCode;
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

}
