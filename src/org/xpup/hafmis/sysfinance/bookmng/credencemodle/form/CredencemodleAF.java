package org.xpup.hafmis.sysfinance.bookmng.credencemodle.form;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class CredencemodleAF extends ActionForm {
  /*
   * ��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ��Ŀ����(��ѯ)
   */
  private String subjectCode1 = "";

  /*
   * ��Ŀ����
   */
  private String subjectName = "";

  /*
   * ��Ŀ����(��ѯ)
   */
  private String subjectName1 = "";

  /*
   * ��Ŀ����(���)
   */
  private String subjectDirection = "";

  /*
   * ��Ŀ����(��ѯ)
   */
  private String subjectDirection1 = "";

  /*
   * ��Ŀ����(���)
   */
  private String subjectDirection3 = "";

  /*
   * ��Ŀ����(��ѯ)
   */
  private String subjectDirection4 = "";

  /*
   * ҵ������
   */
  private String bizType = "";

  /*
   * ҵ������(��ѯ)
   */
  private String bizType1 = "";

  /*
   * �������
   */
  private String moneyType = "";
  
  /*
   * �������
   */
  private String moneyType1 = "";

  /*
   * ժҪ
   */
  private String summray = "";

  /*
   * �б�List
   */
  List list = null;

  private Map bizTypeMap = null;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getMoneyType() {
    return moneyType;
  }

  public void setMoneyType(String moneyType) {
    this.moneyType = moneyType;
  }

  public String getSubjectDirection() {
    return subjectDirection;
  }

  public void setSubjectDirection(String subjectDirection) {
    this.subjectDirection = subjectDirection;
  }

  public String getSummray() {
    return summray;
  }

  public void setSummray(String summray) {
    this.summray = summray;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }

  public String getBizType1() {
    return bizType1;
  }

  public void setBizType1(String bizType1) {
    this.bizType1 = bizType1;
  }

  public String getSubjectCode1() {
    return subjectCode1;
  }

  public void setSubjectCode1(String subjectCode1) {
    this.subjectCode1 = subjectCode1;
  }

  public String getSubjectName1() {
    return subjectName1;
  }

  public void setSubjectName1(String subjectName1) {
    this.subjectName1 = subjectName1;
  }

  public String getSubjectDirection1() {
    return subjectDirection1;
  }

  public void setSubjectDirection1(String subjectDirection1) {
    this.subjectDirection1 = subjectDirection1;
  }

  public String getSubjectDirection3() {
    return subjectDirection3;
  }

  public void setSubjectDirection3(String subjectDirection3) {
    this.subjectDirection3 = subjectDirection3;
  }

  public String getSubjectDirection4() {
    return subjectDirection4;
  }

  public void setSubjectDirection4(String subjectDirection4) {
    this.subjectDirection4 = subjectDirection4;
  }

  public String getMoneyType1() {
    return moneyType1;
  }

  public void setMoneyType1(String moneyType1) {
    this.moneyType1 = moneyType1;
  }

}
