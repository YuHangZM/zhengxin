package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto;

import java.math.BigDecimal;

public class CredenceInspectionFindDTO {
  /**
   * ����
   */
  private String credenceDate = "";

  /**
   * ƾ֤��
   */
  private String credenceCharacter = "";

  /**
   * �跽���
   */
  private BigDecimal debitSum = new BigDecimal(0.00);

  /**
   * �������
   */
  private BigDecimal creditSum = new BigDecimal(0.00);

  /**
   * ���ڿ�ʼ
   */
  private String credenceDateSt = "";

  /**
   * ���ڽ���
   */
  private String credenceDateEnd = "";

  /**
   * ƾ֤�ſ�ʼ
   */
  private String credenceNumSt = "";

  /**
   * ƾ֤�Ž���
   */
  private String credenceNumEnd = "";

  /*
   * ���´�
   */
  private String office = "";

  /*
   * j��¼����
   */
  private String count = "0";

  /**
   * fn201�������
   */
  private String credenceId = "";

  public String getCredenceDate() {
    return credenceDate;
  }

  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
  }

  public String getCredenceId() {
    return credenceId;
  }

  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }

  public String getCredenceCharacter() {
    return credenceCharacter;
  }

  public void setCredenceCharacter(String credenceCharacter) {
    this.credenceCharacter = credenceCharacter;
  }

  public String getCredenceDateEnd() {
    return credenceDateEnd;
  }

  public void setCredenceDateEnd(String credenceDateEnd) {
    this.credenceDateEnd = credenceDateEnd;
  }

  public String getCredenceDateSt() {
    return credenceDateSt;
  }

  public void setCredenceDateSt(String credenceDateSt) {
    this.credenceDateSt = credenceDateSt;
  }

  public String getCredenceNumEnd() {
    return credenceNumEnd;
  }

  public void setCredenceNumEnd(String credenceNumEnd) {
    this.credenceNumEnd = credenceNumEnd;
  }

  public String getCredenceNumSt() {
    return credenceNumSt;
  }

  public void setCredenceNumSt(String credenceNumSt) {
    this.credenceNumSt = credenceNumSt;
  }

  public void reset() {
    this.credenceDateSt = "";
    this.credenceDateEnd = "";
    this.credenceNumSt = "";
    this.credenceNumEnd = "";
    this.credenceCharacter = "";
    this.office = "";
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public BigDecimal getCreditSum() {
    return creditSum;
  }

  public void setCreditSum(BigDecimal creditSum) {
    this.creditSum = creditSum;
  }

  public BigDecimal getDebitSum() {
    return debitSum;
  }

  public void setDebitSum(BigDecimal debitSum) {
    this.debitSum = debitSum;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }
}
