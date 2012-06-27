package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
/**
 * �ͳ������ˡ����д���ռ��ˡ��˲������е��ֽ��ռ��ˡ����д���ռ��˹���
 * @author guojingping
 *
 */
public class CashDayClearTcFindDTO {
  /**
   * ���ڿ�ʼ
   */
  private String credenceDateSt="";
  /**
   * ���ڽ���
   */
  private String credenceDateEnd="";
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
   * ״̬
   */
  private String credenceSt="";
  private Map credenceStMap=new HashMap();
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
   * �������ڿ�ʼ
   */
  private String settDateSt="";
  /**
   * �������ڽ���
   */
  private String settDateEnd="";
  /**
   * �跽����ܼ�
   */
  private BigDecimal debitSum=new BigDecimal(0.00);
  /**
   * ��������ܼ�
   */
  private BigDecimal creditSum=new BigDecimal(0.00);
  /**
   * ������־�Ƿ���ѯ��ť
   */
  private String type="";
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
  public String getCredenceSt() {
    return credenceSt;
  }
  public void setCredenceSt(String credenceSt) {
    this.credenceSt = credenceSt;
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
  public Map getCredenceStMap() {
    return credenceStMap;
  }
  public void setCredenceStMap(Map credenceStMap) {
    this.credenceStMap = credenceStMap;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
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
  public String getCredenceCharacter() {
    return credenceCharacter;
  }
  public void setCredenceCharacter(String credenceCharacter) {
    this.credenceCharacter = credenceCharacter;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
}
