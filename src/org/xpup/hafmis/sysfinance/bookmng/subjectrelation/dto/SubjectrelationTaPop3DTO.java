package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

public class SubjectrelationTaPop3DTO {
  /*
   * ��λ����
   */
  private String id = "";

  /*
   * ���´�����
   */
  private String name = "";

  /*
   * ��������
   */
  private String bankName = "";

  /*
   * ��λ����
   */
  private String orgName = "";

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
