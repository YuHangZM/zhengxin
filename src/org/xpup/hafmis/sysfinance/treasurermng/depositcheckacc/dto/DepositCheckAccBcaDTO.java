package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto;

import java.math.BigDecimal;

public class DepositCheckAccBcaDTO {
  /**
   * ���ж��˵��б�
   */
  private String settDate="";//��������
  private String subjectCode="";//��Ŀ����
  private String settType="";//���㷽ʽ
  private String temp_settType="";
  private String settNum="";//�����
  private BigDecimal debit=new BigDecimal(0.00);//���н跽
  private BigDecimal credit=new BigDecimal(0.00);//���д���
  private String credenceId="";//fn211����
  private String type="";//������־ҳ�����ɫ��
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
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getCredenceId() {
    return credenceId;
  }
  public void setCredenceId(String credenceId) {
    this.credenceId = credenceId;
  }
  public String getTemp_settType() {
    return temp_settType;
  }
  public void setTemp_settType(String temp_settType) {
    this.temp_settType = temp_settType;
  }
}
