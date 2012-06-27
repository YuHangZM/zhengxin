package org.xpup.hafmis.sysloan.dataready.bank.dto;

import java.io.Serializable;

/**
 * ����
 * @author Administrator
 *
 */
public class BankAFDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * ID
   */
  private Integer id=null;
  /**
   * ��������
   */
  private String bankName=null;
  /**
   * �������İ��´�
   */
  private String office=null;
  /**
   * ����ί�д����˺�
   */
  private String loanAcc=null;
  /**
   * ������Ϣ�˺�
   */
  private String interestAcc=null;
  /**
   * �����г�
   */
  private String bankPrisident=null;
  /**
   * �г��绰
   */
  private String bankPrisidentTel=null;
  /**
   * ��ϵ��
   */
  private String contactPrsn=null;
  /**
   * ��ϵ�˵绰
   */
  private String contactTel=null;
  /**
   * ��ϵ��ְ��
   */
  private String business=null;
  /**
   * ����״̬
   */
  private String loanBankSt=null;
  /**
   * �ַ�״̬
   */
  private String type=null;
  
  
  private String outAccount="";
  
  private String inAccount="";
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
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
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
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
  public String getLoanBankSt() {
    return loanBankSt;
  }
  public void setLoanBankSt(String loanBankSt) {
    this.loanBankSt = loanBankSt;
  }
  public String getOffice() {
    return office;
  }
  public void setOffice(String office) {
    this.office = office;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getInAccount() {
    return inAccount;
  }
  public void setInAccount(String inAccount) {
    this.inAccount = inAccount;
  }
  public String getOutAccount() {
    return outAccount;
  }
  public void setOutAccount(String outAccount) {
    this.outAccount = outAccount;
  } 

}
