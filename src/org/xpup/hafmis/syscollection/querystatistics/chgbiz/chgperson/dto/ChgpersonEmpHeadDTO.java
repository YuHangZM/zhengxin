package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto;

import java.math.BigDecimal;

public class ChgpersonEmpHeadDTO {
  
  private int sumCount=0;//���������
  private int insCount=0;//��������
  private int mulCount=0;//��������
  private BigDecimal insPayment=new BigDecimal(0.00);//���ӽɶ�
  private BigDecimal mulPayment=new BigDecimal(0.00);//���ٽɶ�
  private BigDecimal orgPayment=new BigDecimal(0.00);//��λ�ɶ�
  private BigDecimal empPayment=new BigDecimal(0.00);//ְ���ɶ�
  private BigDecimal sumPayment=new BigDecimal(0.00);//�ɶ�ϼ�
  public BigDecimal getEmpPayment() {
    return empPayment;
  }
  public void setEmpPayment(BigDecimal empPayment) {
    this.empPayment = empPayment;
  }
  public int getInsCount() {
    return insCount;
  }
  public void setInsCount(int insCount) {
    this.insCount = insCount;
  }
  public BigDecimal getInsPayment() {
    return insPayment;
  }
  public void setInsPayment(BigDecimal insPayment) {
    this.insPayment = insPayment;
  }
  public int getMulCount() {
    return mulCount;
  }
  public void setMulCount(int mulCount) {
    this.mulCount = mulCount;
  }
  public BigDecimal getMulPayment() {
    return mulPayment;
  }
  public void setMulPayment(BigDecimal mulPayment) {
    this.mulPayment = mulPayment;
  }
  public BigDecimal getOrgPayment() {
    return orgPayment;
  }
  public void setOrgPayment(BigDecimal orgPayment) {
    this.orgPayment = orgPayment;
  }
  public int getSumCount() {
    return sumCount;
  }
  public void setSumCount(int sumCount) {
    this.sumCount = sumCount;
  }
  public BigDecimal getSumPayment() {
    return sumPayment;
  }
  public void setSumPayment(BigDecimal sumPayment) {
    this.sumPayment = sumPayment;
  }
  
  

}
