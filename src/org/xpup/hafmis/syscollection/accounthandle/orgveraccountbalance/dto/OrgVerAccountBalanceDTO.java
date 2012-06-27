/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.dto;

import java.math.BigDecimal;

public class OrgVerAccountBalanceDTO {

  private String id = null;

  private String empId = "";// ְ�����
  
  private String empName = "";// ְ������
  
  private BigDecimal preBalanceCen = new BigDecimal(0.00);// �����������
  
  private BigDecimal curBalanceCen = new BigDecimal(0.00);// ���ı������
  
  private BigDecimal preBalanceOrg = new BigDecimal(0.00);// ��λ�������
  
  private BigDecimal curBalanceOrg = new BigDecimal(0.00);// ��λ�������
  
  private BigDecimal preInterest = new BigDecimal(0.00);// ��λ������Ϣ
  
  private BigDecimal curInterest = new BigDecimal(0.00);// ��λ������Ϣ
  
  private BigDecimal orgPaySum = new BigDecimal(0.00);// SUM��λ�ɶ�
  
  private BigDecimal empPaySum = new BigDecimal(0.00);// SUMְ���ɶ�
  
  private String orgId = "";// ��λ���
  
  private String orgName = "";// ��λ����
  
  private String accYear = "";// ��ת���

  public String getAccYear() {
    return accYear;
  }

  public void setAccYear(String accYear) {
    this.accYear = accYear;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public BigDecimal getCurBalanceCen() {
    return curBalanceCen;
  }

  public void setCurBalanceCen(BigDecimal curBalanceCen) {
    this.curBalanceCen = curBalanceCen;
  }

  public BigDecimal getCurBalanceOrg() {
    return curBalanceOrg;
  }

  public void setCurBalanceOrg(BigDecimal curBalanceOrg) {
    this.curBalanceOrg = curBalanceOrg;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getPreBalanceCen() {
    return preBalanceCen;
  }

  public void setPreBalanceCen(BigDecimal preBalanceCen) {
    this.preBalanceCen = preBalanceCen;
  }

  public BigDecimal getPreBalanceOrg() {
    return preBalanceOrg;
  }

  public void setPreBalanceOrg(BigDecimal preBalanceOrg) {
    this.preBalanceOrg = preBalanceOrg;
  }

  public BigDecimal getCurInterest() {
    return curInterest;
  }

  public void setCurInterest(BigDecimal curInterest) {
    this.curInterest = curInterest;
  }

  public BigDecimal getPreInterest() {
    return preInterest;
  }

  public void setPreInterest(BigDecimal preInterest) {
    this.preInterest = preInterest;
  }

  public BigDecimal getEmpPaySum() {
    return empPaySum;
  }

  public void setEmpPaySum(BigDecimal empPaySum) {
    this.empPaySum = empPaySum;
  }

  public BigDecimal getOrgPaySum() {
    return orgPaySum;
  }

  public void setOrgPaySum(BigDecimal orgPaySum) {
    this.orgPaySum = orgPaySum;
  }


  
}
