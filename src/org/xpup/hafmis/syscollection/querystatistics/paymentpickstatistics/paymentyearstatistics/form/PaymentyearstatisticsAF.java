package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class PaymentyearstatisticsAF extends ActionForm{
  
  private List officeList=new ArrayList();//������--���´�
  private String officeCode = "";//���´�
  private String bizDate = "";//ҵ�����ڣ���λ��
  
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }
  public List getOfficeList() {
    return officeList;
  }
  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }
  
}
