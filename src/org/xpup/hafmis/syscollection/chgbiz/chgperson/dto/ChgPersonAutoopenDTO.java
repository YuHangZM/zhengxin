package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

/**
 * Copy Right Information :�����Զ����ⰴť������Ϊ������󣬵������ڣ���ʾ�õ�λ״̬Ϊת�����ְ�������Խ���ѡ�񣨿�ȫѡ����ѡ�����ȷ���󣬽�ѡ��ְ�����뵽�������У��������Ϊ���⡣
 * AutoChangePopAF
 * 
 * @Version : v1.0
 * @author : �����
 * @Date : 2008.6.18
 */
public class ChgPersonAutoopenDTO {
  
  private String id = "";
  /**
   * ְ�����
   */
  private String empId = "";
  /**
   * ְ������
   */
  private String empName = "";
  /**
   * ֤������
   */
  private String cardNum = "";
  /**
   * �ɴ�״̬
   */
  private String payStatus = "";
  
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
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
  public String getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
}
