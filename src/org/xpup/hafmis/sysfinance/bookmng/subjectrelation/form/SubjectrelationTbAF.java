package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class SubjectrelationTbAF extends ActionForm {
  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ��Ŀ����
   */
  private String subjectName = "";

  /*
   * �������
   */
  private String calculRelaType = "";

  /*
   * �����ϵֵ
   */
  private String calculRelaValue = "";

  /*
   * �����ϵֵ��λ
   */
  private String orgid = "";

  /*
   * �����ϵֵ���´�
   */
  private String office = "";

  /*
   * �����ϵֵ����
   */
  private String bankid = "";

  List list = null;

  /*
   * �����ϵ����ö��
   */
  private Map calculRelaTypeMap = null;

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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getCalculRelaTypeMap() {
    return calculRelaTypeMap;
  }

  public void setCalculRelaTypeMap(Map calculRelaTypeMap) {
    this.calculRelaTypeMap = calculRelaTypeMap;
  }

  public String getBankid() {
    return bankid;
  }

  public void setBankid(String bankid) {
    this.bankid = bankid;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
}
