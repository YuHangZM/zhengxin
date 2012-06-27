package org.xpup.hafmis.sysfinance.accmng.subjectbalance.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copy Right Information   : ��Ŀ����
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 11-02-2007
 */
public class SubjectbalanceDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private String subjectCode = "";
  private String subjectName = "";
  
  //������  
  private BigDecimal yearDebit = new BigDecimal("0.00");
  //�ڳ����  ��
  private BigDecimal firstRemainingDebit = new BigDecimal("0.00");
  //�ڳ����  ��
  private BigDecimal firstRemainingCredit = new BigDecimal("0.00");
  
  //���ڷ����� ��
  private BigDecimal thisIssueDebit = new BigDecimal("0.00");
  //���ڷ����� ��
  private BigDecimal thisIssueCredit = new BigDecimal("0.00");
  
  //�����ۼƷ����� ��
  private BigDecimal thisYearDebit = new BigDecimal("0.00");
  //�����ۼƷ����� ��
  private BigDecimal thisYearCredit = new BigDecimal("0.00");
  
  //��ĩ��� ��
  private BigDecimal lastRemainingDebit = new BigDecimal("0.00");
  //��ĩ��� ��
  private BigDecimal lastRemainingCredit = new BigDecimal("0.00");
  
  private String subjectSt = "";
  
  public BigDecimal getFirstRemainingCredit() {
    return firstRemainingCredit;
  }
  public void setFirstRemainingCredit(BigDecimal firstRemainingCredit) {
    this.firstRemainingCredit = firstRemainingCredit;
  }
  public BigDecimal getFirstRemainingDebit() {
    return firstRemainingDebit;
  }
  public void setFirstRemainingDebit(BigDecimal firstRemainingDebit) {
    this.firstRemainingDebit = firstRemainingDebit;
  }
  public BigDecimal getLastRemainingCredit() {
    return lastRemainingCredit;
  }
  public void setLastRemainingCredit(BigDecimal lastRemainingCredit) {
    this.lastRemainingCredit = lastRemainingCredit;
  }
  public BigDecimal getLastRemainingDebit() {
    return lastRemainingDebit;
  }
  public void setLastRemainingDebit(BigDecimal lastRemainingDebit) {
    this.lastRemainingDebit = lastRemainingDebit;
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
  public BigDecimal getThisIssueCredit() {
    return thisIssueCredit;
  }
  public void setThisIssueCredit(BigDecimal thisIssueCredit) {
    this.thisIssueCredit = thisIssueCredit;
  }
  public BigDecimal getThisIssueDebit() {
    return thisIssueDebit;
  }
  public void setThisIssueDebit(BigDecimal thisIssueDebit) {
    this.thisIssueDebit = thisIssueDebit;
  }
  public BigDecimal getThisYearCredit() {
    return thisYearCredit;
  }
  public void setThisYearCredit(BigDecimal thisYearCredit) {
    this.thisYearCredit = thisYearCredit;
  }
  public BigDecimal getThisYearDebit() {
    return thisYearDebit;
  }
  public void setThisYearDebit(BigDecimal thisYearDebit) {
    this.thisYearDebit = thisYearDebit;
  }
  public BigDecimal getYearDebit() {
    return yearDebit;
  }
  public void setYearDebit(BigDecimal yearDebit) {
    this.yearDebit = yearDebit;
  }
  public String getSubjectSt() {
    return subjectSt;
  }
  public void setSubjectSt(String subjectSt) {
    this.subjectSt = subjectSt;
  }
}
