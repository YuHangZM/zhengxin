package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * @author ����
 *2007-7-23
 */
public class ChgpersonEmpListAF extends ActionForm{

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {

    this.orgId = "";//��λ���
    this.orgName = "";// ��λ����
    this.empId="";//ְ�����
    this.empName="";//ְ������
    this.chgMonthStart = "";//������¿�ʼ
    this.chgMonthEnd = "";//������½���
    this.chgDateStart = "";//������ڿ�ʼ
    this.chgDateEnd = "";//������ڽ���
    this.list=null;
  }
  private static final long serialVersionUID = 1L;

  private String orgId = "";//��λ���
  private String orgName = "";// ��λ����
  private String empId="";//ְ�����
  private String empName="";//ְ������
  private String chgMonthStart = "";//������¿�ʼ
  private String chgMonthEnd = "";//������½���
  private String chgDateStart = "";//������ڿ�ʼ
  private String chgDateEnd = "";//������ڽ���
  private List list=null;
  
  public String getChgDateEnd() {
    return chgDateEnd;
  }
  public void setChgDateEnd(String chgDateEnd) {
    this.chgDateEnd = chgDateEnd;
  }
  public String getChgDateStart() {
    return chgDateStart;
  }
  public void setChgDateStart(String chgDateStart) {
    this.chgDateStart = chgDateStart;
  }
  public String getChgMonthEnd() {
    return chgMonthEnd;
  }
  public void setChgMonthEnd(String chgMonthEnd) {
    this.chgMonthEnd = chgMonthEnd;
  }
  public String getChgMonthStart() {
    return chgMonthStart;
  }
  public void setChgMonthStart(String chgMonthStart) {
    this.chgMonthStart = chgMonthStart;
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

}
