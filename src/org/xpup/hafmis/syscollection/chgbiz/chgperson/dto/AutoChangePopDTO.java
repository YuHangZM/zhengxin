package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

/**
 * Copy Right Information : ��װ���Զ�����������м�¼��Ϣ��DTO Goldsoft Project :
 * AutoChangePopDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.3.18
 */
public class AutoChangePopDTO {
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
}
