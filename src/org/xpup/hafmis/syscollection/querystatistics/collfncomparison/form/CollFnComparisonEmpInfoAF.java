package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CollFnComparisonEmpInfoAF extends ActionForm {
  /**
   * ��λ���
   */
  private String orgId = "";

  /**
   * ��λ����
   */
  private String orgName = "";

  /**
   * ְ�����
   */
  private String empId = "";

  /**
   * ְ������
   */
  private String empName = "";

  /**
   * ֤������
   */
  private String cardNum = "";

  /**
   * �б�����
   */
  private List list;

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }
}
