package org.xpup.hafmis.syscollection.common.domain.entity;


/**
 *  �ɴ�-���A
 * 
 *@author ���
 *2007-6-20
 */
public class MonthPayment extends PaymentHead{
  
  private static final long serialVersionUID = -3745806964079944938L;

  private Integer payModel = new Integer(0);
  
  private String minMaxMonth="";
  
  /** persistent field */
  private String payType;

  public String getPayType() {
    return "���";
  }

  public String getMinMaxMonth() {
    return minMaxMonth;
  }

  public void setMinMaxMonth(String minMaxMonth) {
    this.minMaxMonth = minMaxMonth;
  }

  public Integer getPayModel() {
    return payModel;
  }

  public void setPayModel(Integer payModel) {
    this.payModel = payModel;
  }
 
}