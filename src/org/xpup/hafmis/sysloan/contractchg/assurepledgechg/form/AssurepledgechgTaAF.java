package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
/**
 * 
 * @author yuqf
 *2007-10-08
 */
public class AssurepledgechgTaAF extends ActionForm{
  
  private List list = new ArrayList();
  private Map Map = new HashMap();//��������������map
  
  private String contractId = ""; //��ͬID
  private String debitter = "";//��������� PL110 
  private String empId = "";//ְ�����
  private String cardNum = "";//֤������
  private String houseType = "";//��������
  
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getDebitter() {
    return debitter;
  }
  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getHouseType() {
    return houseType;
  }
  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public Map getMap() {
    return Map;
  }
  public void setMap(Map map) {
    Map = map;
  }
  
  
}
