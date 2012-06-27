package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;

import java.math.BigDecimal;

import org.xpup.hafmis.syscollection.common.domain.entity.Emp;


public class OrgAddpayInfoDTO {
  
  /*β��ID*/
  private Integer id;
  /*ְ�����*/
  private Integer empId;
  /*ְ������*/
  private String empName="";
  /*��ʼʱ��*/
  private String beginMonth="";
  /*��ֹʱ��*/
  private String endMonth="";
  /*��λ���ɽ��*/
  private BigDecimal orgAddMoney=new BigDecimal(0.00);
  /*ְ�����ɽ��*/
  private BigDecimal empAddMoney=new BigDecimal(0.00);
  /*��λ���ɽ��*/
  private BigDecimal orgAddPaySum=new BigDecimal(0.00);
  /*ְ�����ɽ��*/
  private BigDecimal empAddPaySum=new BigDecimal(0.00);
  /*���ɽ��*/
  private BigDecimal addPaySum=new BigDecimal(0.00);
  
  private Emp emp = new Emp();
  public String getBeginMonth() {
    return beginMonth;
  }
  public void setBeginMonth(String beginMonth) {
    this.beginMonth = beginMonth;
  }
  public BigDecimal getEmpAddMoney() {
    return empAddMoney;
  }
  public void setEmpAddMoney(BigDecimal empAddMoney) {
    this.empAddMoney = empAddMoney;
  }
  public Integer getEmpId() {
    return empId;
  }
  public void setEmpId(Integer empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getEndMonth() {
    return endMonth;
  }
  public void setEndMonth(String endMonth) {
    this.endMonth = endMonth;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public BigDecimal getOrgAddMoney() {
    return orgAddMoney;
  }
  public void setOrgAddMoney(BigDecimal orgAddMoney) {
    this.orgAddMoney = orgAddMoney;
  }
  public BigDecimal getEmpAddPaySum() {
    return empAddPaySum;
  }
  public void setEmpAddPaySum(BigDecimal empAddPaySum) {
    this.empAddPaySum = empAddPaySum;
  }
  public BigDecimal getOrgAddPaySum() {
    return orgAddPaySum;
  }
  public void setOrgAddPaySum(BigDecimal orgAddPaySum) {
    this.orgAddPaySum = orgAddPaySum;
  }
  public BigDecimal getAddPaySum() {
    return addPaySum;
  }
  public void setAddPaySum(BigDecimal addPaySum) {
    this.addPaySum = addPaySum;
  }
  public Emp getEmp() {
    return emp;
  }
  public void setEmp(Emp emp) {
    this.emp = emp;
  }
  

  

}