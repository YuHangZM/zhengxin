package org.xpup.hafmis.sysloan.dataready.collloanbackpara.dto;

import java.math.BigDecimal;

public class CollLoanbackParaDTO {
 private String office="";//���´�
 private String pickMoneyType="";//1����ȡ��� 
 private BigDecimal balance=new BigDecimal(0.00);//1��A.�������___ Ԫ
 private String monthMoney="";//1��B.�����»���Ϣ___ ��
 private String monthPayMoney="";//1�� C.�����½ɴ��___ ��
 private String isDeduct="";//2���ɿ۹���������ʱ�Ƿ�ۿ�
// private String isOverPay="";//2���Ƿ�ɹ���
 private String isPreOnly="";//3��ֻ���������
 private String isPickLessThanPay="";//4������ȡ������½ɴ��
 private String isOtherDeduct="";//5������������Ƿ���Կۿ�
public BigDecimal getBalance() {
  return balance;
}
public void setBalance(BigDecimal balance) {
  this.balance = balance;
}
public String getIsDeduct() {
  return isDeduct;
}
public void setIsDeduct(String isDeduct) {
  this.isDeduct = isDeduct;
}
public String getIsOtherDeduct() {
  return isOtherDeduct;
}
public void setIsOtherDeduct(String isOtherDeduct) {
  this.isOtherDeduct = isOtherDeduct;
}
//public String getIsOverPay() {
//  return isOverPay;
//}
//public void setIsOverPay(String isOverPay) {
//  this.isOverPay = isOverPay;
//}
public String getIsPreOnly() {
  return isPreOnly;
}
public void setIsPreOnly(String isPreOnly) {
  this.isPreOnly = isPreOnly;
}
public String getOffice() {
  return office;
}
public void setOffice(String office) {
  this.office = office;
}
public String getPickMoneyType() {
  return pickMoneyType;
}
public void setPickMoneyType(String pickMoneyType) {
  this.pickMoneyType = pickMoneyType;
}
public String getIsPickLessThanPay() {
  return isPickLessThanPay;
}
public void setIsPickLessThanPay(String isPickLessThanPay) {
  this.isPickLessThanPay = isPickLessThanPay;
}
public String getMonthMoney() {
  return monthMoney;
}
public void setMonthMoney(String monthMoney) {
  this.monthMoney = monthMoney;
}
public String getMonthPayMoney() {
  return monthPayMoney;
}
public void setMonthPayMoney(String monthPayMoney) {
  this.monthPayMoney = monthPayMoney;
}
}
