package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.dto;

public class SettleincanddecListDTO {
  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ��Ŀ����
   */
  private String subjectName = "";

  /*
   * ҵ������
   */
  private String bySubjectCode = "";

  /*
   * �������
   */
  private String bySubjectName = "";

  /*
   * fn202����
   */
  private String id = "";

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBySubjectCode() {
    return bySubjectCode;
  }

  public void setBySubjectCode(String bySubjectCode) {
    this.bySubjectCode = bySubjectCode;
  }

  public String getBySubjectName() {
    return bySubjectName;
  }

  public void setBySubjectName(String bySubjectName) {
    this.bySubjectName = bySubjectName;
  }
}
