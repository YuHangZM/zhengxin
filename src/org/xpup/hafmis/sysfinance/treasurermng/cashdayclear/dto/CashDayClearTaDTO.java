package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto;

import java.math.BigDecimal;

/**
 * �����д���ռ��˹���
 * @author guojingping
 *
 */

public class CashDayClearTaDTO {
  /**
   * �������´�
   */
  private String office="";
  /**
   * ��Ŀ
   */
  private String subjectCode="";
  /**
   * ƾ֤����
   */
  private String credenceDate="";
  /**
   * ƾ֤��
   */
  private String credenceCharacter="";
  /**
   * ժҪ
   */
  private String summray="";
  /**
   * �跽���
   */
  private BigDecimal debit=new BigDecimal(0.00);
  /**
   * �������
   */
  private BigDecimal credit=new BigDecimal(0.00);
  /**
   * ���㷽ʽ
   */
  private String settType="";
  /**
   * �����
   */
  private String settNum="";
  /**
   * ������
   */
  private String dopsn="";
  /**
   * ��������
   */
  private String settDate="";
  /**
   * �����ж����޸�ҳ�滹�����ҳ��
   */
  private String type="";
  /**
   * fn210����
   */
  private String credenceId="";
  /**
   * ƾ֤��
   */
  private String credenceNum="";
  /**
   * ���ƾ֤��ˮ��
   */
  private String acredenceId="";
  public String getCredenceNum() {
    return credenceNum;
  }
  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
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
  public String getCredenceDate() {
    return credenceDate;
  }
  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
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
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getSettDate() {
    return settDate;
  }
  public void setSettDate(String settDate) {
    this.settDate = settDate;
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
  public String getSummray() {
    return summray;
  }
  public void setSummray(String summray) {
    this.summray = summray;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getAcredenceId() {
    return acredenceId;
  }
  public void setAcredenceId(String acredenceId) {
    this.acredenceId = acredenceId;
  }
}
