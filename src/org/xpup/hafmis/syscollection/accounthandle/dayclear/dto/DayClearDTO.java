package org.xpup.hafmis.syscollection.accounthandle.dayclear.dto;

import java.util.Map;
 
public class DayClearDTO {
  
  /**
   * ���б��
   */
  private String collBankId = "";
  /**
   * ��������
   */
  private String collBankName = "";
  /**
   * ��������
   */
  private String collBankDate = "";
  
  private Map map=null;
  
  
  public String getCollBankDate() {
    return collBankDate;
  }
  public void setCollBankDate(String collBankDate) {
    this.collBankDate = collBankDate;
  }
  public String getCollBankId() {
    return collBankId;
  }
  public void setCollBankId(String collBankId) {
    this.collBankId = collBankId;
  }
  public String getCollBankName() {
    return collBankName;
  }
  public void setCollBankName(String collBankName) {
    this.collBankName = collBankName;
  }
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }




}
