package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto;

import java.math.BigDecimal;
/**
 * �����д���ռ��˹���
 * @author guojingping
 *
 */
public class CashDayClearTbFindDTO {
  /**
   * ���ڿ�ʼ
   */
  private String credenceDateSt="";
  /**
   * ���ڽ���
   */
  private String credenceDateEnd="";
  /**
   * �������ڿ�ʼ
   */
  private String settDateSt="";
  /**
   * �������ڽ���
   */
  private String settDateEnd="";
  /**
   * ժҪ
   */
  private String summary="";
  /**
   * ��Ŀ
   */
  private String subjectCode="";
  /**
   * ��Ŀ����
   */
  private String subjectName="";
  /**
   * ��ʼ
   */
  private String moneySt="";
  /**
   * ������
   */
  private String moneyEnd="";
  /**
   * ƾ֤�ſ�ʼ
   */
  private String credenceNumSt="";
  /**
   * ƾ֤�Ž���
   */
  private String credenceNumEnd="";
  /**
   * ƾ֤��
   */
  private String credenceCharacter="";
  /**
   * ���㷽ʽ
   */
  private String settType="";
  /**
   * �����
   */
  private String settNum="";
  /**
   * ���´�
   */
  private String office="";
  /**
   * �跽����ܼ�
   */
  private BigDecimal debitSum=new BigDecimal(0.00);
  /**
   * ��������ܼ�
   */
  private BigDecimal creditSum=new BigDecimal(0.00);
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
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
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
}
