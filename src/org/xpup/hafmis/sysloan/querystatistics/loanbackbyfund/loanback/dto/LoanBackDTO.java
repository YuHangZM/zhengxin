package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.dto;

/**
 * @author ��Ұ 2007-10-15
 */
public class LoanBackDTO {

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String yearMonth = "";// ҵ������

  private String benjin = null;// ԭʼҵ������

  private String interest = "";// �Ƶ���

  private String punishInterest = "";// ҵ��״̬

  private String sum = null;// �ſ�����

  private String bizDate = null;// ��ʼ��������

  private String borrowerEmpId = null;// ��ֹ��������

  private String kouPersonName = null;

  private String orgId = "";

  private String orgName = "";

  private String batchNum = "";

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public String getBenjin() {
    return benjin;
  }

  public void setBenjin(String benjin) {
    this.benjin = benjin;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBorrowerEmpId() {
    return borrowerEmpId;
  }

  public void setBorrowerEmpId(String borrowerEmpId) {
    this.borrowerEmpId = borrowerEmpId;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getInterest() {
    return interest;
  }

  public void setInterest(String interest) {
    this.interest = interest;
  }

  public String getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(String punishInterest) {
    this.punishInterest = punishInterest;
  }

  public String getSum() {
    return sum;
  }

  public void setSum(String sum) {
    this.sum = sum;
  }

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }

  public String getKouPersonName() {
    return kouPersonName;
  }

  public void setKouPersonName(String kouPersonName) {
    this.kouPersonName = kouPersonName;
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

}
