package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class YearclearstatisticsListAF extends ActionForm{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String officeCode = "";//���´�
  private String collectionBank = "";//�鼯����
  private String orgId = "";//��λ���
  private String orgName = "";// ��λ����
  private String chgYearStart = "";//�����꿪ʼ
  private String chgYearEnd = "";//���������

  private String empId = "";//ְ�����
  private String empName = "";// ְ������

  private String oldblance = "";//��Ϣǰ���
  private String interest = "";//��Ϣ 
  private String blance = "";//��Ϣ�����
  
  private String isZero="";
  
  private List list ;
  
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getChgYearEnd() {
    return chgYearEnd;
  }
  public void setChgYearEnd(String chgYearEnd) {
    this.chgYearEnd = chgYearEnd;
  }
  public String getChgYearStart() {
    return chgYearStart;
  }
  public void setChgYearStart(String chgYearStart) {
    this.chgYearStart = chgYearStart;
  }
  public String getCollectionBank() {
    return collectionBank;
  }
  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }
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
  public String getBlance() {
    return blance;
  }
  public void setBlance(String blance) {
    this.blance = blance;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
  }
  public String getOldblance() {
    return oldblance;
  }
  public void setOldblance(String oldblance) {
    this.oldblance = oldblance;
  }
  public String getIsZero() {
    return isZero;
  }
  public void setIsZero(String isZero) {
    this.isZero = isZero;
  }

}
