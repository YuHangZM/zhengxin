package org.xpup.hafmis.syscollection.common.domain.entity;

/**
 * @author ¬�� 2007-6-20
 */
public class ChgPaymentSalaryBase extends ChgPaymentHead {

  private static final long serialVersionUID = -3655195047700033988L;

  // �ϼƣ���������oldSalaryBaseSum
  // ����ǰ���ʻ���oldSalaryBase;
  // �������ʻ���salaryBase;
  // ��λ�ɶ�orgPaySum;
  // ְ���ɶ�empPaySum;
  // �ɶ�ϼ�totalAmount;
  // ����ǰ�ܽɶ�oldPaySum;
  // �������ܽɶ�paySum
  // ԭ�ܹ��ʻ���oldSlarayBase
  // ���ܹ��ʻ���salaryBaseSum
  // ����percentagerate
  // �ύ״̬

  private int oldSalaryBaseSum;

  private Double olddSalaryBase;

  private Double salaryBase;

  private Double orgPaySum;

  private Double empPaySum;

  private Double totalAmount;

  private Double paySum;

  private Double salaryBaseSum;

  private String chgtype;

  private String wuhtChgStatus;

  private String percentagerate;

  private String temp_pick;

  private Double orgRate;

  private Double empRate;

  private String month;

  public String getChgtype() {
    return "���ʻ�������";
  }

  public Double getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(Double empPaySum) {
    this.empPaySum = empPaySum;
  }

  public Double getOlddSalaryBase() {
    return olddSalaryBase;
  }

  public void setOlddSalaryBase(Double olddSalaryBase) {
    this.olddSalaryBase = olddSalaryBase;
  }

  public int getOldSalaryBaseSum() {
    return oldSalaryBaseSum;
  }

  public void setOldSalaryBaseSum(int oldSalaryBaseSum) {
    this.oldSalaryBaseSum = oldSalaryBaseSum;
  }

  public Double getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(Double orgPaySum) {
    this.orgPaySum = orgPaySum;
  }

  public Double getPaySum() {
    return paySum;
  }

  public void setPaySum(Double paySum) {
    this.paySum = paySum;
  }

  public Double getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(Double salaryBase) {
    this.salaryBase = salaryBase;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getWuhtChgStatus() {
    return wuhtChgStatus;
  }

  public void setWuhtChgStatus(String wuhtChgStatus) {
    this.wuhtChgStatus = wuhtChgStatus;
  }

  public Double getSalaryBaseSum() {
    return salaryBaseSum;
  }

  public void setSalaryBaseSum(Double salaryBaseSum) {
    this.salaryBaseSum = salaryBaseSum;
  }

  public String getPercentagerate() {
    return percentagerate;
  }

  public void setPercentagerate(String percentagerate) {
    this.percentagerate = percentagerate;
  }

  public String getTemp_pick() {
    return temp_pick;
  }

  public void setTemp_pick(String temp_pick) {
    this.temp_pick = temp_pick;
  }

  public Double getEmpRate() {
    return empRate;
  }

  public void setEmpRate(Double empRate) {
    this.empRate = empRate;
  }

  public Double getOrgRate() {
    return orgRate;
  }

  public void setOrgRate(Double orgRate) {
    this.orgRate = orgRate;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

}
