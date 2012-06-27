package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.from;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChgpersonOrgListAF extends ActionForm{

  public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    
    this.orgId = "";//��λ���
    this.orgName = "";// ��λ����
    this.officeCode="";//���´�
    this.collectionBank="";//�鼯����
    this.chgMonthStart = "";//������¿�ʼ
    this.chgMonthEnd = "";//������½���
    this.chgDateStart = "";//������ڿ�ʼ
    this.chgDateEnd = "";//������ڽ���
    this.chgStatus=new Integer(0);//״̬
    this.map=null;
    this.list=null;
  }

  private static final long serialVersionUID = 1L;
  private String officeCode = "";//���´�
  private String collectionBank = "";//�鼯����
  private String orgId = "";//��λ���
  private String orgName = "";// ��λ����
  private String chgMonthStart = "";//������¿�ʼ
  private String chgMonthEnd = "";//������½���
  private String chgDateStart = "";//������ڿ�ʼ
  private String chgDateEnd = "";//������ڽ���
  private Map map = null;
  private Integer chgStatus = new Integer(0);//״̬
  private List list = null;

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

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

  public Integer getChgStatus() {
    return chgStatus;
  }

  public void setChgStatus(Integer chgStatus) {
    this.chgStatus = chgStatus;
  }

  public String getCollectionBank() {
    return collectionBank;
  }

  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
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

}
