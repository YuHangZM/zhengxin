package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * ������ȡǰ��¼��
 * 
 *@author ���ƽ
 *2007-12-06
 */
public class PartPickupCondition implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * �����ȡ�޶�
   */
  private BigDecimal pickMoneyMax=new BigDecimal(0.00);
  /**
   * �����ȡ����
   */
  private int pickTimeMax;
  /**
   * �½ɶ�ı���
   */
  private int multiple;
  /**
   * �������
   */
  private BigDecimal leavingsBalance=new BigDecimal(0.00);
  /**
   * ���´�
   */
  private String officeCode="";
  /** full constructor */
  public PartPickupCondition(BigDecimal pickMoneyMax, int pickTimeMax, BigDecimal leavingsBalance, String officeCode,int multiple) {
      this.pickMoneyMax=pickMoneyMax;
      this.pickTimeMax=pickTimeMax;
      this.leavingsBalance=leavingsBalance;
      this.officeCode=officeCode;
      this.multiple=multiple;
  }

  /** default constructor */
  public PartPickupCondition() {
  }

  public BigDecimal getLeavingsBalance() {
    return leavingsBalance;
  }

  public void setLeavingsBalance(BigDecimal leavingsBalance) {
    this.leavingsBalance = leavingsBalance;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public BigDecimal getPickMoneyMax() {
    return pickMoneyMax;
  }

  public void setPickMoneyMax(BigDecimal pickMoneyMax) {
    this.pickMoneyMax = pickMoneyMax;
  }

  public int getPickTimeMax() {
    return pickTimeMax;
  }

  public void setPickTimeMax(int pickTimeMax) {
    this.pickTimeMax = pickTimeMax;
  }

  public int getMultiple() {
    return multiple;
  }

  public void setMultiple(int multiple) {
    this.multiple = multiple;
  }
  
}
