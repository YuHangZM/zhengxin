package org.xpup.hafmis.signjoint.dto;

public class TempDTO {

//  Orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�empname ְ��������
//cardnum���֤�ţ�bankcardid ���п��ţ�biz_date �������ڣ�operater ������Ա
  String orgid;
  String orgname;
  String empid;
  String empname;
  String cardnum;
  String bankcardid;
  String biz_date;
  String operater;
  
  public TempDTO(){
    orgid="";
    orgname="";
    empid="";
    empname="";
    cardnum="";
    bankcardid="";
    biz_date="";
    operater="";
  }
  
  public TempDTO(String orgid, String orgname, String empid, String empname, String cardnum, String bankcardid, String biz_date, String operater) {
    this.orgid = orgid;
    this.orgname = orgname;
    this.empid = empid;
    this.empname = empname;
    this.cardnum = cardnum;
    this.bankcardid = bankcardid;
    this.biz_date = biz_date;
    this.operater = operater;
  }
  public String getBankcardid() {
    return bankcardid;
  }
  public void setBankcardid(String bankcardid) {
    this.bankcardid = bankcardid;
  }
  public String getBiz_date() {
    return biz_date;
  }
  public void setBiz_date(String biz_date) {
    this.biz_date = biz_date;
  }
  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getEmpname() {
    return empname;
  }
  public void setEmpname(String empname) {
    this.empname = empname;
  }
  public String getOperater() {
    return operater;
  }
  public void setOperater(String operater) {
    this.operater = operater;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  

}
