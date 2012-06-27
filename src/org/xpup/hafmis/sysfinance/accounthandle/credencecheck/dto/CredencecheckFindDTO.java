package org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto;

import java.math.BigDecimal;

public class CredencecheckFindDTO {
  /**
   * ���ڿ�ʼ
   */
  private String credenceDateSt = "";

  /**
   * ���ڽ���
   */
  private String credenceDateEnd = "";

  /**
   * �������ڿ�ʼ
   */
  private String settDateSt = "";

  /**
   * �������ڽ���
   */
  private String settDateEnd = "";

  /**
   * ժҪ
   */
  private String summary = "";

  /**
   * ��Ŀ
   */
  private String subjectCode = "";

  /**
   * ��Ŀ����
   */
  private String subjectName = "";

  /**
   * ��ʼ
   */
  private String moneySt = "";

  /**
   * ������
   */
  private String moneyEnd = "";

  /**
   * ƾ֤�ſ�ʼ
   */
  private String credenceNumSt = "";

  /**
   * ƾ֤�Ž���
   */
  private String credenceNumEnd = "";

  /**
   * ƾ֤��
   */
  private String credenceCharacter = "";

  /**
   * ���㷽ʽ
   */
  private String settType = "";

  /**
   * �����
   */
  private String settNum = "";

  /**
   * ƾ֤״̬
   */
  private String credenceSt = "";
  
  private String operator = "";

  /**
   * �跽����ܼ�
   */
  private BigDecimal debitSum = new BigDecimal(0.00);

  /**
   * ��������ܼ�
   */
  private BigDecimal creditSum = new BigDecimal(0.00);

  /*
   * ���o����1
   */
  private String buttonPromise1 = "";

  /*
   * ���o����2
   */
  private String buttonPromise2 = "";

  /*
   * ���´�
   */
  private String office = "";
  /** ��ʶ�����жϵ�û�����ѯ��ť **/
  private String flag = "";
    
  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public void reset() {
    this.credenceDateSt = "";
    this.credenceDateEnd = "";
    this.settDateSt = "";
    this.settDateEnd = "";
    this.summary = "";
    this.subjectCode = "";
    this.subjectName = "";
    this.moneySt = "";
    this.moneyEnd = "";
    this.credenceNumSt = "";
    this.credenceNumEnd = "";
    this.credenceCharacter = "";
    this.settType = "";
    this.settNum = "";
    this.credenceSt = "";
    this.office="";
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

  public String getSettDateEnd() {
    return settDateEnd;
  }

  public void setSettDateEnd(String settDateEnd) {
    this.settDateEnd = settDateEnd;
  }

  public String getSettDateSt() {
    return settDateSt;
  }

  public void setSettDateSt(String settDateSt) {
    this.settDateSt = settDateSt;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public String getSettType() {
    return settType;
  }

  public void setSettType(String settType) {
    this.settType = settType;
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

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getMoneyEnd() {
    return moneyEnd;
  }

  public void setMoneyEnd(String moneyEnd) {
    this.moneyEnd = moneyEnd;
  }

  public String getMoneySt() {
    return moneySt;
  }

  public void setMoneySt(String moneySt) {
    this.moneySt = moneySt;
  }

  public String getCredenceSt() {
    return credenceSt;
  }

  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }

  public String getButtonPromise1() {
    return buttonPromise1;
  }

  public void setButtonPromise1(String buttonPromise1) {
    this.buttonPromise1 = buttonPromise1;
  }

  public String getButtonPromise2() {
    return buttonPromise2;
  }

  public void setButtonPromise2(String buttonPromise2) {
    this.buttonPromise2 = buttonPromise2;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
