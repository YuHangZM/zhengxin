package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;

public class EmpAccountAF extends IdAF{

  /**
   * 
   */
  private static final long serialVersionUID = -301137330523833310L;

  private String startDate="";   //��������
  private String lastDate="";    //��---����
  private String orgIdaa101="";  //��λ���
  private String nameba001="";   //��λ����
  private String empIdaa102="";  //ְ�����
  private String nameba002="";   //ְ������
  private List list;
  private String loadsMassage="";
  private String temp_credit="";//���ڷ������
  private String temp_debit="";//���ڷ������
  private String curInterest=""; //������Ϣ
  private EmpHAFAccountFlow empHAFAccountFlow=new EmpHAFAccountFlow();
 public void reset(ActionMapping mapping, ServletRequest request) {
    empHAFAccountFlow=new EmpHAFAccountFlow();
    startDate=""; 
    lastDate=""; 
    orgIdaa101="";  
    nameba001="";  
    empIdaa102="";
    nameba002="";
    list=new ArrayList();
    loadsMassage="";
    curInterest="";
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getLoadsMassage() {
    return loadsMassage;
  }
  public void setLoadsMassage(String loadsMassage) {
    this.loadsMassage = loadsMassage;
  }
  public String getEmpIdaa102() {
    return empIdaa102;
  }
  public void setEmpIdaa102(String empIdaa102) {
    this.empIdaa102 = empIdaa102;
  }
  public String getLastDate() {
    return lastDate;
  }
  public void setLastDate(String lastDate) {
    this.lastDate = lastDate;
  }
  public String getNameba001() {
    return nameba001;
  }
  public void setNameba001(String nameba001) {
    this.nameba001 = nameba001;
  }
  public String getNameba002() {
    return nameba002;
  }
  public void setNameba002(String nameba002) {
    this.nameba002 = nameba002;
  }
  public String getOrgIdaa101() {
    return orgIdaa101;
  }
  public void setOrgIdaa101(String orgIdaa101) {
    this.orgIdaa101 = orgIdaa101;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public EmpHAFAccountFlow getEmpHAFAccountFlow() {
    return empHAFAccountFlow;
  }
  public void setEmpHAFAccountFlow(EmpHAFAccountFlow empHAFAccountFlow) {
    this.empHAFAccountFlow = empHAFAccountFlow;
  }
  public String getCurInterest() {
    return curInterest;
  }
  public void setCurInterest(String curInterest) {
    this.curInterest = curInterest;
  }
  public String getTemp_credit() {
    return temp_credit;
  }
  public void setTemp_credit(String temp_credit) {
    this.temp_credit = temp_credit;
  }
  public String getTemp_debit() {
    return temp_debit;
  }
  public void setTemp_debit(String temp_debit) {
    this.temp_debit = temp_debit;
  }
}
