package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto;

import java.math.BigDecimal;

public class ListDTO {
  
  private Integer orgid;//��λ���
 
  private String orgname; //��λ����
  
  private BigDecimal jzbalance=new BigDecimal(0.00);//��ת���
  
  private BigDecimal jzgzbalance=new BigDecimal(0.00);//��ת�������
  
  private BigDecimal jzzmbalance=new BigDecimal(0.00);//��ת�������
  
  private BigDecimal gzpay=new BigDecimal(0.00);//���ʽ��
  
  private BigDecimal payment=new BigDecimal(0.00);//���
  
  private BigDecimal addpay=new BigDecimal(0.00);//����
  
  private BigDecimal adjustaccount=new BigDecimal(0.00);//����
  
  private BigDecimal tanin=new BigDecimal(0.00);//ת��
  
  private BigDecimal interest=new BigDecimal(0.00);//��Ϣ
  
  private BigDecimal pick=new BigDecimal(0.00);//��ȡ
  
  private BigDecimal tranout=new BigDecimal(0.00);//ת��
  
  private BigDecimal currentbalance=new BigDecimal(0.00);//��ǰ���
  
  private BigDecimal gzbalance=new BigDecimal(0.00);//�������
  
  private BigDecimal zmbalance=new BigDecimal(0.00);//�������
  public BigDecimal getAddpay() {
    return addpay;
  }
  public void setAddpay(BigDecimal addpay) {
    this.addpay = addpay;
  }
  public BigDecimal getAdjustaccount() {
    return adjustaccount;
  }
  public void setAdjustaccount(BigDecimal adjustaccount) {
    this.adjustaccount = adjustaccount;
  }
  public BigDecimal getCurrentbalance() {
    return currentbalance;
  }
  public void setCurrentbalance(BigDecimal currentbalance) {
    this.currentbalance = currentbalance;
  }
  public BigDecimal getGzbalance() {
    return gzbalance;
  }
  public void setGzbalance(BigDecimal gzbalance) {
    this.gzbalance = gzbalance;
  }
  public BigDecimal getGzpay() {
    return gzpay;
  }
  public void setGzpay(BigDecimal gzpay) {
    this.gzpay = gzpay;
  }
  public BigDecimal getInterest() {
    return interest;
  }
  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }
  public BigDecimal getJzbalance() {
    return jzbalance;
  }
  public void setJzbalance(BigDecimal jzbalance) {
    this.jzbalance = jzbalance;
  }
  public BigDecimal getJzgzbalance() {
    return jzgzbalance;
  }
  public void setJzgzbalance(BigDecimal jzgzbalance) {
    this.jzgzbalance = jzgzbalance;
  }
  public BigDecimal getJzzmbalance() {
    return jzzmbalance;
  }
  public void setJzzmbalance(BigDecimal jzzmbalance) {
    this.jzzmbalance = jzzmbalance;
  }
  public Integer getOrgid() {
    return orgid;
  }
  public void setOrgid(Integer orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  public BigDecimal getPayment() {
    return payment;
  }
  public void setPayment(BigDecimal payment) {
    this.payment = payment;
  }
  public BigDecimal getPick() {
    return pick;
  }
  public void setPick(BigDecimal pick) {
    this.pick = pick;
  }
  public BigDecimal getTanin() {
    return tanin;
  }
  public void setTanin(BigDecimal tanin) {
    this.tanin = tanin;
  }
  public BigDecimal getTranout() {
    return tranout;
  }
  public void setTranout(BigDecimal tranout) {
    this.tranout = tranout;
  }
  public BigDecimal getZmbalance() {
    return zmbalance;
  }
  public void setZmbalance(BigDecimal zmbalance) {
    this.zmbalance = zmbalance;
  }
}
