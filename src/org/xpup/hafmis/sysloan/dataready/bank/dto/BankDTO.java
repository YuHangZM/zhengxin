package org.xpup.hafmis.sysloan.dataready.bank.dto;

import java.math.BigDecimal;

public class BankDTO {
  private Integer id=null;
  /**
  * ��������
  */
 private BigDecimal bankName = new BigDecimal(0.0);
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
 private String loanBnakSt=null;
 /**
  * ��ȡ�����г�
  */
 public String getBankPrisident() {
   return bankPrisident;
 }
 /**
  * ���������г�
  */
 public void setBankPrisident(String bankPrisident) {
   this.bankPrisident = bankPrisident;
 }
 /**
  * ��ȡ����ί�д����˺�
  */
 public String getLoanAcc() {
   return loanAcc;
 }
 /**
  * ��������ί�д����˺�
  */
 public void setLoanAcc(String loanAcc) {
   this.loanAcc = loanAcc;
 }
 /**
  * �����������İ��´�
  */
 public String getOffice() {
   return office;
 }
 /**
  * ��ȡ�������İ��´�
  */
 public void setOffice(String office) {
   this.office = office;
 }

 
public BigDecimal getBankName() {
  return bankName;
}
public void setBankName(BigDecimal bankName) {
  this.bankName = bankName;
}
/**
  * ��ȡ������Ϣ�˺�
  */
 public String getInterestAcc() {
   return interestAcc;
 }
 /**
  * ����������Ϣ�˺�
  */
 public void setInterestAcc(String interestAcc) {
   this.interestAcc = interestAcc;
 }
 /**
  * ��ȡ�г��绰
  */
 public String getBankPrisidentTel() {
   return bankPrisidentTel;
 }
 /**
  * �����г��绰
  */
 public void setBankPrisidentTel(String bankPrisidentTel) {
   this.bankPrisidentTel = bankPrisidentTel;
 }
 /**
  *��ȡ��ϵ��
  */
 public String getContactPrsn() {
   return contactPrsn;
 }
 /**
  * ������ϵ��
  */
 public void setContactPrsn(String contactPrsn) {
   this.contactPrsn = contactPrsn;
 }
 /**
  * ��ȡ��ϵ�˵绰
  * @return
  */
 public String getContactTel() {
   return contactTel;
 }
 /**
  * ������ϵ�˵绰
  * @param contactTel
  */
 public void setContactTel(String contactTel) {
   this.contactTel = contactTel;
 }
 /**
  * ��ȡ��ϵ��ְ��
  */
 public String getBusiness() {
   return business;
 }
 /**
  * ������ϵ��ְ��
  */
 public void setBusiness(String business) {
   this.business = business;
 }
 /**
  * ��ȡ����״̬
  */
 public String getLoanBnakSt() {
   return loanBnakSt;
 }
 /**
  * ��������״̬
  */
 public void setLoanBnakSt(String loanBnakSt) {
   this.loanBnakSt = loanBnakSt;
 }
public Integer getId() {
  return id;
}
public void setId(Integer id) {
  this.id = id;
}
}
