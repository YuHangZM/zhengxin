package org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.dto;



import java.math.BigDecimal;

public class QueryorgversionintDTO {
  private String pid="";//����
  private String empId = "";//ְ�����
  private String empName = "";//ְ������
  private String clearInterestType = "";//��Ϣ����
  private BigDecimal money = new BigDecimal(0.00);//���
  private BigDecimal interest = new BigDecimal(0.00);//��Ϣ
  private BigDecimal sumMoney = new BigDecimal(0.00);//�ܶ�
  public String getClearInterestType() {
    return clearInterestType;
  }
  public void setClearInterestType(String clearInterestType) {
    this.clearInterestType = clearInterestType;
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
  public BigDecimal getInterest() {
    return interest;
  }
  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public BigDecimal getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  public String getPid() {
    return pid;
  }
  public void setPid(String pid) {
    this.pid = pid;
  }
  
}
