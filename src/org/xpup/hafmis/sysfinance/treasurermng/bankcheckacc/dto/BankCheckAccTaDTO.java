package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto;

import java.math.BigDecimal;

public class BankCheckAccTaDTO {
  private String office="";//�������´�
  private String temp_office="";
  private String subjectCode="";//��Ŀ
  private String summary="";//ժҪ
  private BigDecimal debit=new BigDecimal(0.00);//���н跽���
  private BigDecimal credit=new BigDecimal(0.00);//���д������
  private String settType="";//���㷽ʽ
  private String settNum="";//�����
  private String dopsn="";//������
  private String settDate="";//��������
  private String type="";//������ʶ�Ƿ��ά��ҳ�������
  private String credenceId="";//����id��Ϊ�޸�׼����
  public String getCredenceId() {
    return credenceId;
  }
  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }
  public String getTemp_office() {
    return temp_office;
  }
  public void setTemp_office(String temp_office) {
    this.temp_office = temp_office;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
}
