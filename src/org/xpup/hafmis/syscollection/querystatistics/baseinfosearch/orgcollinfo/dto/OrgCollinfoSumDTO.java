package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto;

import java.math.BigDecimal;

public class OrgCollinfoSumDTO {
  /** ����ܶ� */
  private BigDecimal paySum = new BigDecimal(0.00);
  /** ��������� */
  private BigDecimal balance = new BigDecimal(0.00);
  /** ������� */
  private BigDecimal overPay = new BigDecimal(0.00);
  /** ������� */
  private BigDecimal paybalance = new BigDecimal(0.00);
  /** ְ������ */
  private Integer empSum = new Integer(0);
  /** �����ܶ� */
  private BigDecimal salarySum = new BigDecimal(0.00);
  /** ��λ�ɶ� */
  private BigDecimal orgPay = new BigDecimal(0.00);
  /** ְ���ɶ� */
  private BigDecimal empPay = new BigDecimal(0.00);
  
  /** ��λ��� */
  private Integer orgId;
  /** ��λ���� */
  private String orgName;
  /** ���´� */
  private String officecode;
  /** �鼯���� */
  private String collectionBankId;
  /** ְ������ */
  private BigDecimal empRate = new BigDecimal(0.00);
  /** ��λ���� */
  private BigDecimal orgRate = new BigDecimal(0.00);
  /** ��λ�������� */
  private String orgPayMonth = "";
  /** ���˽������� */
  private String empPayMonth = "";
  /** �������� */
  private String openDate = "";
  /** ��λ���� */
  private String character = "";
  
  private Integer personCount = new Integer(0);
  
  public Integer getPersonCount() {
    return personCount;
  }
  public void setPersonCount(Integer personCount) {
    this.personCount = personCount;
  }
  public String getCollectionBankId() {
    return collectionBankId;
  }
  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }
  public String getEmpPayMonth() {
    return empPayMonth;
  }
  public void setEmpPayMonth(String empPayMonth) {
    this.empPayMonth = empPayMonth;
  }
  public BigDecimal getEmpRate() {
    return empRate;
  }
  public void setEmpRate(BigDecimal empRate) {
    this.empRate = empRate;
  }
  public String getOfficecode() {
    return officecode;
  }
  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }
  public String getOpenDate() {
    return openDate;
  }
  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }
  public Integer getOrgId() {
    return orgId;
  }
  public void setOrgId(Integer orgId) {
    this.orgId = orgId;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getOrgPayMonth() {
    return orgPayMonth;
  }
  public void setOrgPayMonth(String orgPayMonth) {
    this.orgPayMonth = orgPayMonth;
  }
  public BigDecimal getOrgRate() {
    return orgRate;
  }
  public void setOrgRate(BigDecimal orgRate) {
    this.orgRate = orgRate;
  }
  public BigDecimal getBalance() {
    return balance;
  }
  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
  public BigDecimal getEmpPay() {
    return empPay;
  }
  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }
  public Integer getEmpSum() {
    return empSum;
  }
  public void setEmpSum(Integer empSum) {
    this.empSum = empSum;
  }
  public BigDecimal getOrgPay() {
    return orgPay;
  }
  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }
  public BigDecimal getOverPay() {
    return overPay;
  }
  public void setOverPay(BigDecimal overPay) {
    this.overPay = overPay;
  }
  public BigDecimal getPaybalance() {
    return paybalance;
  }
  public void setPaybalance(BigDecimal paybalance) {
    this.paybalance = paybalance;
  }
  public BigDecimal getPaySum() {
    return paySum;
  }
  public void setPaySum(BigDecimal paySum) {
    this.paySum = paySum;
  }
  public BigDecimal getSalarySum() {
    return salarySum;
  }
  public void setSalarySum(BigDecimal salarySum) {
    this.salarySum = salarySum;
  }
  public String getCharacter() {
    return character;
  }
  public void setCharacter(String character) {
    this.character = character;
  }
}
