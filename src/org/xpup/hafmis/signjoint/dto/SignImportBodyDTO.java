package org.xpup.hafmis.signjoint.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class SignImportBodyDTO extends impDto{

  //empid ְ����ţ�empname ְ��������cardnum���֤�ţ�bankcardid ���п���
  private String empid="";
  private String empname="";
  private String cardnum="";
  private String bankcardid="";
  public String getBankcardid() {
    return bankcardid;
  }
  public void setBankcardid(String bankcardid) {
    this.bankcardid = bankcardid;
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
  
  
}
