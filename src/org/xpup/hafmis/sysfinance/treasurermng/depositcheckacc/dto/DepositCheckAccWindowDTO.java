package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto;

import java.math.BigDecimal;

public class DepositCheckAccWindowDTO {
  private BigDecimal bdcBalanceSum=new BigDecimal(0.00);//�����ռ������
  private BigDecimal bcaBalanceSum=new BigDecimal(0.00);//���ж��˵����
  private BigDecimal bankMoneySum=new BigDecimal(0.00);//������������δ�ս���ܼ�
  private BigDecimal officeMoneySum=new BigDecimal(0.00);//������������δ�ս���ܼ�
  private BigDecimal bankOutMoneySum=new BigDecimal(0.00);//�����Ѹ�����δ������ܼ�
  private BigDecimal officeOutMoneySum=new BigDecimal(0.00);//�����Ѹ�����δ������ܼ�
  private BigDecimal adjustOfficeBalance=new BigDecimal(0.00);//���ں������ģ�
  private BigDecimal adjustBankBalance=new BigDecimal(0.00);//���ں������У�
  public BigDecimal getAdjustBankBalance() {
    return adjustBankBalance;
  }
  public void setAdjustBankBalance(BigDecimal adjustBankBalance) {
    this.adjustBankBalance = adjustBankBalance;
  }
  public BigDecimal getAdjustOfficeBalance() {
    return adjustOfficeBalance;
  }
  public void setAdjustOfficeBalance(BigDecimal adjustOfficeBalance) {
    this.adjustOfficeBalance = adjustOfficeBalance;
  }
  public BigDecimal getBankMoneySum() {
    return bankMoneySum;
  }
  public void setBankMoneySum(BigDecimal bankMoneySum) {
    this.bankMoneySum = bankMoneySum;
  }
  public BigDecimal getBankOutMoneySum() {
    return bankOutMoneySum;
  }
  public void setBankOutMoneySum(BigDecimal bankOutMoneySum) {
    this.bankOutMoneySum = bankOutMoneySum;
  }
  public BigDecimal getBcaBalanceSum() {
    return bcaBalanceSum;
  }
  public void setBcaBalanceSum(BigDecimal bcaBalanceSum) {
    this.bcaBalanceSum = bcaBalanceSum;
  }
  public BigDecimal getBdcBalanceSum() {
    return bdcBalanceSum;
  }
  public void setBdcBalanceSum(BigDecimal bdcBalanceSum) {
    this.bdcBalanceSum = bdcBalanceSum;
  }
  public BigDecimal getOfficeMoneySum() {
    return officeMoneySum;
  }
  public void setOfficeMoneySum(BigDecimal officeMoneySum) {
    this.officeMoneySum = officeMoneySum;
  }
  public BigDecimal getOfficeOutMoneySum() {
    return officeOutMoneySum;
  }
  public void setOfficeOutMoneySum(BigDecimal officeOutMoneySum) {
    this.officeOutMoneySum = officeOutMoneySum;
  }
}
