package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PrincipalglTaAF extends ActionForm{
  
  private String mydate = "";
  private String id = "";
  private String radioValue = "";//��ѡ��ť
  private String office = "";//���´�
  private String loanBank = "";//�ſ�����
  private String developerName = "";//������
  private String floorNum = "";//¥��
  private String assistantOrgName = "";//������˾
  private String startDate = "";//��ѯ��ʼʱ��
  private String endDate = "";//��ѯ����ʱ��
  
  private String buyhouseorgid = "";//������
  private String floorid = "";//������
  private List list = new ArrayList();
  private List printlist = new ArrayList();
  private List officeList = new ArrayList();
  private List loanBankNameList = new ArrayList();
  private List assistantOrgNameList = new ArrayList();
  
  private BigDecimal thisYearDebitSum = new BigDecimal(0.00);//���ڽ跽������
  private BigDecimal thisYearLenderSum = new BigDecimal(0.00);//���ڴ���������
  private int thisYearDebitDegree = 0;//���ڽ跽����
  private int thisYearLenderDegree = 0;//���ڴ�������

  public List getPrintlist() {
    return printlist;
  }

  public void setPrintlist(List printlist) {
    this.printlist = printlist;
  }

  public int getThisYearDebitDegree() {
    return thisYearDebitDegree;
  }

  public void setThisYearDebitDegree(int thisYearDebitDegree) {
    this.thisYearDebitDegree = thisYearDebitDegree;
  }

  public BigDecimal getThisYearDebitSum() {
    return thisYearDebitSum;
  }

  public void setThisYearDebitSum(BigDecimal thisYearDebitSum) {
    this.thisYearDebitSum = thisYearDebitSum;
  }

  public int getThisYearLenderDegree() {
    return thisYearLenderDegree;
  }

  public void setThisYearLenderDegree(int thisYearLenderDegree) {
    this.thisYearLenderDegree = thisYearLenderDegree;
  }

  public BigDecimal getThisYearLenderSum() {
    return thisYearLenderSum;
  }

  public void setThisYearLenderSum(BigDecimal thisYearLenderSum) {
    this.thisYearLenderSum = thisYearLenderSum;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFloorid() {
    return floorid;
  }

  public void setFloorid(String floorid) {
    this.floorid = floorid;
  }

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
  }

  public String getAssistantOrgName() {
    return assistantOrgName;
  }

  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }

  public List getAssistantOrgNameList() {
    return assistantOrgNameList;
  }

  public void setAssistantOrgNameList(List assistantOrgNameList) {
    this.assistantOrgNameList = assistantOrgNameList;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public List getOfficeList() {
    return officeList;
  }

  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getRadioValue() {
    return radioValue;
  }

  public void setRadioValue(String radioValue) {
    this.radioValue = radioValue;
  }
  
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    this.radioValue = "";//��ѡ��ť
    this.office = "";//���´�
    this.loanBank = "";//�ſ�����
    this.developerName = "";//������
    this.floorNum = "";//¥��
    this.assistantOrgName = "";//������˾
    this.startDate = "";//��ѯ��ʼʱ��
    this.endDate = "";//��ѯ����ʱ��
//    this.loanassistantorgId = "";//������˾���
    this.buyhouseorgid = "";//������
    this.floorid = "";//������
  }

  public String getMydate() {
    return mydate;
  }

  public void setMydate(String mydate) {
    this.mydate = mydate;
  }
}
