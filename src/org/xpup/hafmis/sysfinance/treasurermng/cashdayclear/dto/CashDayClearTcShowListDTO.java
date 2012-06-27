package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto;

import java.math.BigDecimal;
/**
 * �ͳ������ˡ����д���ռ��ˡ��˲������е��ֽ��ռ��ˡ����д���ռ��˹���
 * @author guojingping
 *
 */
public class CashDayClearTcShowListDTO {
  /**
   * ����
   */
  private String credenceDate="";
  /**
   * ƾ֤��
   */
  private String credenceNum="";
  /**
   * ƾ֤��
   */
  private String credenceCharacter="";
  /**
   * ƾ֤�ֺ�
   */
  private String credenceChaNum="";
  private String temp_credenceChaNum="";
  /**
   * ժҪ
   */
  private String summary="";
  private String temp_summary="";
  /**
   * ��Ŀ
   */
  private String subjectCode="";
  /**
   * ��Ŀ����
   */
  private String subjectName="";
  /**
   * �跽���
   */
  private BigDecimal debit=new BigDecimal(0.00);
  /**
   * �������
   */
  private BigDecimal credit=new BigDecimal(0.00);
  /**
   * ���
   */
  private BigDecimal balance=new BigDecimal(0.00);
  /**
   * �����
   */
  private String settNum="";
  /**
   * ���㷽ʽ
   */
  private String settType="";
  private String temp_settType="";
  /**
   * ������
   */
  private String dopsn="";
  /**
   * �Ƶ���
   */
  private String makebill="";
  /**
   * ״̬
   */
  private String credenceSt="";
  /**
   * ��������
   */
  private String settDate="";
  /**
   * fn210�������
   */
  private String credenceId="";
  /**
   * fn210����ƾ֤��ˮ��
   */
  private String acredenceId="";
  /**
   * ���´�
   */
  private String office="";
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
  public String getCredenceNum() {
    return credenceNum;
  }
  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }
  public String getCredenceSt() {
    return credenceSt;
  }
  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
  }
  public BigDecimal getCredit() {
    return credit;
  }
  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }
  public BigDecimal getDebit() {
    return debit;
  }
  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }
  public String getDopsn() {
    return dopsn;
  }
  public void setDopsn(String dopsn) {
    this.dopsn = dopsn;
  }
  public String getMakebill() {
    return makebill;
  }
  public void setMakebill(String makebill) {
    this.makebill = makebill;
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
  public String getCredenceCharacter() {
    return credenceCharacter;
  }
  public void setCredenceCharacter(String credenceCharacter) {
    this.credenceCharacter = credenceCharacter;
  }
  public String getCredenceChaNum() {
    return credenceChaNum;
  }
  public void setCredenceChaNum(String credenceChaNum) {
    this.credenceChaNum = credenceChaNum;
  }
  public String getTemp_settType() {
    return temp_settType;
  }
  public void setTemp_settType(String temp_settType) {
    this.temp_settType = temp_settType;
  }
  public String getTemp_summary() {
    return temp_summary;
  }
  public void setTemp_summary(String temp_summary) {
    this.temp_summary = temp_summary;
  }
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }
  public String getTemp_credenceChaNum() {
    return temp_credenceChaNum;
  }
  public void setTemp_credenceChaNum(String temp_credenceChaNum) {
    this.temp_credenceChaNum = temp_credenceChaNum;
  }
  public String getAcredenceId() {
    return acredenceId;
  }
  public void setAcredenceId(String acredenceId) {
    this.acredenceId = acredenceId;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public BigDecimal getBalance() {
    return balance;
  }
  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
