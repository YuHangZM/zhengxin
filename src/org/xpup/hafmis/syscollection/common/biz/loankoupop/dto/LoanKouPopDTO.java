package org.xpup.hafmis.syscollection.common.biz.loankoupop.dto;

import java.math.BigDecimal;

public class LoanKouPopDTO {
  private String id = "";// ����

  private String empId = "";// ְ�����

  private String orgId = "";// ��λ���

  private BigDecimal kouPreBalance = new BigDecimal(0.00);// ��������

  private BigDecimal kouCurBalance = new BigDecimal(0.00);// �۱�����
  
  private BigDecimal kouBalance = new BigDecimal(0.00);//��������+�۱�����

  private String contractId = "";// ��ͬ���

  private String loanKouAcc = "";// �����˺�

  private BigDecimal shouldKouBalance = new BigDecimal(0.00);// Ӧ�۽��

  public BigDecimal getKouCurBalance() {
    return kouCurBalance;
  }

  public void setKouCurBalance(BigDecimal kouCurBalance) {
    this.kouCurBalance = kouCurBalance;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public BigDecimal getKouPreBalance() {
    return kouPreBalance;
  }

  public void setKouPreBalance(BigDecimal kouPreBalance) {
    this.kouPreBalance = kouPreBalance;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getShouldKouBalance() {
    return shouldKouBalance;
  }

  public void setShouldKouBalance(BigDecimal shouldKouBalance) {
    this.shouldKouBalance = shouldKouBalance;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getKouBalance() {
    return kouBalance;
  }

  public void setKouBalance(BigDecimal kouBalance) {
    this.kouBalance = kouBalance;
  }
}
