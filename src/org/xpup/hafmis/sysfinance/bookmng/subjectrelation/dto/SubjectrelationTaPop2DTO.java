package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

public class SubjectrelationTaPop2DTO {
  /*
   * ��������
   */
  private String collBankId = "";

 
  /*
   * ���´�����
   */
  private String name = "";

  /*
   * ��������
   */
  private String collBankName = "";

  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCollBankId() {
    return collBankId;
  }

  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }

  public String getCollBankName() {
    return collBankName;
  }

  public void setCollBankName(String collBankName) {
    this.collBankName = collBankName;
  }
}
