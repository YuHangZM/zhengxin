package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import java.math.BigDecimal;

public class BankCheckAccTbShowListDTO {
 private String credenceId="";//fn211����
 private String settDate="";//��������
 private String summary="";//ժҪ
 private String temp_summary="";
 private String subjectCode="";//��Ŀ
 private String subjectName="";//��Ŀ����
 private BigDecimal debit=new BigDecimal(0.00);//���н跽���
 private BigDecimal credit=new BigDecimal(0.00);//���д������
 private String settType="";//���㷽ʽ
 private String temp_settType="";
 private String settNum="";//�����
 private String dopsn="";//������
public String getCredenceId() {
  return credenceId;
}
public void setCredenceId(String credenceId) {
  this.credenceId = credenceId;
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
public String getTemp_summary() {
  return temp_summary;
}
public void setTemp_summary(String temp_summary) {
  this.temp_summary = temp_summary;
}
public String getTemp_settType() {
  return temp_settType;
}
public void setTemp_settType(String temp_settType) {
  this.temp_settType = temp_settType;
}
}
