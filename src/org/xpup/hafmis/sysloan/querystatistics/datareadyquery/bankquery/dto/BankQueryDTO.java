package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.dto;

public class BankQueryDTO {
  /**
   * id����
   */
  private String id = "";

  /**
   * �����������
   */
  private String loanBankId = "";

  /**
   * ί�д����˺�
   */
  private String loanAcc = "";

  /**
   * ��Ϣ�˺�
   */
  private String interestAcc = "";

  /**
   * �����г�
   */
  private String bankPrisident = "";

  /**
   * �г��绰
   */
  private String bankPrisidentTel = "";

  /**
   * ��ϵ��
   */
  private String contactPrsn = "";

  /**
   * ��ϵ�˵绰
   */
  private String contactTel = "";

  /**
   * ��ϵ��ְ��
   */
  private String business = "";

  /**
   * ���´�
   */
  private String office = "";

  /**
   * ����״̬
   */
  private String loanBnakSt = "";

  public String getBankPrisident() {
    return bankPrisident;
  }

  public void setBankPrisident(String bankPrisident) {
    this.bankPrisident = bankPrisident;
  }

  public String getBankPrisidentTel() {
    return bankPrisidentTel;
  }

  public void setBankPrisidentTel(String bankPrisidentTel) {
    this.bankPrisidentTel = bankPrisidentTel;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getContactPrsn() {
    return contactPrsn;
  }

  public void setContactPrsn(String contactPrsn) {
    this.contactPrsn = contactPrsn;
  }

  public String getContactTel() {
    return contactTel;
  }

  public void setContactTel(String contactTel) {
    this.contactTel = contactTel;
  }

  public String getInterestAcc() {
    return interestAcc;
  }

  public void setInterestAcc(String interestAcc) {
    this.interestAcc = interestAcc;
  }

  public String getLoanAcc() {
    return loanAcc;
  }

  public void setLoanAcc(String loanAcc) {
    this.loanAcc = loanAcc;
  }

  public String getLoanBnakSt() {
    return loanBnakSt;
  }

  public void setLoanBnakSt(String loanBnakSt) {
    this.loanBnakSt = loanBnakSt;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
}
