package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto;

public class SubjectrelationTbDTO {
  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ��Ŀ����
   */
  private String subjectName = "";

  /*
   * һ����Ŀ
   */
  private String firstSubjectCode = "";

  /*
   * �������
   */
  private String calculRelaType = "";

  /*
   * �����ϵֵ
   */
  private String calculRelaValue = "";

  /*
   * �����ϵֵ����
   */
  private String calculRelaName = "";

  /*
   * fn111 ����
   */
  private String subjectreleid = "";

  public String getCalculRelaName() {
    return calculRelaName;
  }

  public void setCalculRelaName(String calculRelaName) {
    this.calculRelaName = calculRelaName;
  }

  public String getCalculRelaType() {
    return calculRelaType;
  }

  public void setCalculRelaType(String calculRelaType) {
    this.calculRelaType = calculRelaType;
  }

  public String getCalculRelaValue() {
    return calculRelaValue;
  }

  public void setCalculRelaValue(String calculRelaValue) {
    this.calculRelaValue = calculRelaValue;
  }

  public String getFirstSubjectCode() {
    return firstSubjectCode;
  }

  public void setFirstSubjectCode(String firstSubjectCode) {
    this.firstSubjectCode = firstSubjectCode;
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

  public String getSubjectreleid() {
    return subjectreleid;
  }

  public void setSubjectreleid(String subjectreleid) {
    this.subjectreleid = subjectreleid;
  }
}
