package org.xpup.hafmis.sysfinance.accounthandle.credenceclear.dto;

public class CredenceclearModifyDTO {
  /*
   * ƾ֤��
   */
  private String credenceNum = "";

  /*
   * ƾ֤����
   */
  private String credenceDate = "";

  /*
   * ���´�
   */
  private String office = "";

  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ժҪ
   */
  private String summary = "";

  public String getCredenceDate() {
    return credenceDate;
  }

  public void setCredenceDate(String credenceDate) {
    this.credenceDate = credenceDate;
  }

  public String getCredenceNum() {
    return credenceNum;
  }

  public void setCredenceNum(String credenceNum) {
    this.credenceNum = credenceNum;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
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
