package org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto;

public class CredencecheckModifyDTO {
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

  private String makeBill = "";

  public String getMakeBill() {
    return makeBill;
  }

  public void setMakeBill(String makeBill) {
    this.makeBill = makeBill;
  }

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

}
