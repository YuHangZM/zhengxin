package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import java.math.BigDecimal;

public class BankCheckAccTbFindDTO {
  private String subjectCode="";//��Ŀ
  private String subjectName="";//��Ŀ����
  private String settDateSt="";//�������ڿ�ʼ
  private String settDateEnd="";//�������ڽ���
  private String settNumSt="";//����ſ�ʼ
  private String settNumEnd="";//����Ž���
  private String summary="";//ժҪ
  private String moneySt="";//��ʼ
  private String moneyEnd="";//������
  private BigDecimal debitSum=new BigDecimal(0.00);//���н跽����ܼ�
  private BigDecimal creditSum=new BigDecimal(0.00);//���д�������ܼ�
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
  public String getSettNumEnd() {
    return settNumEnd;
  }
  public void setSettNumEnd(String settNumEnd) {
    this.settNumEnd = settNumEnd;
  }
  public String getSettNumSt() {
    return settNumSt;
  }
  public void setSettNumSt(String settNumSt) {
    this.settNumSt = settNumSt;
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
