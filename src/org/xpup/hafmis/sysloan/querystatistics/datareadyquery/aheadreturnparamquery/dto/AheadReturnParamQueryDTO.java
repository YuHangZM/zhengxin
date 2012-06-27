package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.dto;

import java.math.BigDecimal;

public class AheadReturnParamQueryDTO {
  private String loanBankId="";//����
  private String temp_loanBankId="";
  private String aheadReturnAfter="";//1����ǰ�����
  private String partReturnMonthLT="";//2������ʱ��С�ڶ�����
  private String isPartAheadReturn="";//2���Ƿ���������ǰ����
  private String allReturnMonthLT="";//3������ʱ��С�ڶ�����
  private String isAllReturn="";//3���Ƿ�����һ���Խ��廹��
  private BigDecimal leastReturnMoney=new BigDecimal(0.00);//4����ͻ�����
  private String mostAheadReturnYearTimes="";//5����������������ǰ�������
  private String mostAheadReturnTimes="";//6���������������������ǰ�������
  private String isAccept="";//7����ǰ�����Ƿ���ȡ������
  private String chargeMode="";//7���շѷ�ʽ
  private BigDecimal aheadReturnMoneyPercent=new BigDecimal(0.00);//7����ǰ�����ٷֱ�
  private BigDecimal money=new BigDecimal(0.00);//7������
  private String row[]=new String[7];//��ѡ��
  public String getAheadReturnAfter() {
    return aheadReturnAfter;
  }
  public void setAheadReturnAfter(String aheadReturnAfter) {
    this.aheadReturnAfter = aheadReturnAfter;
  }
  public BigDecimal getAheadReturnMoneyPercent() {
    return aheadReturnMoneyPercent;
  }
  public void setAheadReturnMoneyPercent(BigDecimal aheadReturnMoneyPercent) {
    this.aheadReturnMoneyPercent = aheadReturnMoneyPercent;
  }
  public String getAllReturnMonthLT() {
    return allReturnMonthLT;
  }
  public void setAllReturnMonthLT(String allReturnMonthLT) {
    this.allReturnMonthLT = allReturnMonthLT;
  }
  public String getChargeMode() {
    return chargeMode;
  }
  public void setChargeMode(String chargeMode) {
    this.chargeMode = chargeMode;
  }
  public String getIsAccept() {
    return isAccept;
  }
  public void setIsAccept(String isAccept) {
    this.isAccept = isAccept;
  }
  public String getIsAllReturn() {
    return isAllReturn;
  }
  public void setIsAllReturn(String isAllReturn) {
    this.isAllReturn = isAllReturn;
  }
  public String getIsPartAheadReturn() {
    return isPartAheadReturn;
  }
  public void setIsPartAheadReturn(String isPartAheadReturn) {
    this.isPartAheadReturn = isPartAheadReturn;
  }
  public BigDecimal getLeastReturnMoney() {
    return leastReturnMoney;
  }
  public void setLeastReturnMoney(BigDecimal leastReturnMoney) {
    this.leastReturnMoney = leastReturnMoney;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public BigDecimal getMoney() {
    return money;
  }
  public void setMoney(BigDecimal money) {
    this.money = money;
  }
  public String getMostAheadReturnTimes() {
    return mostAheadReturnTimes;
  }
  public void setMostAheadReturnTimes(String mostAheadReturnTimes) {
    this.mostAheadReturnTimes = mostAheadReturnTimes;
  }
  public String getMostAheadReturnYearTimes() {
    return mostAheadReturnYearTimes;
  }
  public void setMostAheadReturnYearTimes(String mostAheadReturnYearTimes) {
    this.mostAheadReturnYearTimes = mostAheadReturnYearTimes;
  }
  public String getPartReturnMonthLT() {
    return partReturnMonthLT;
  }
  public void setPartReturnMonthLT(String partReturnMonthLT) {
    this.partReturnMonthLT = partReturnMonthLT;
  }
  public String[] getRow() {
    return row;
  }
  public void setRow(String[] row) {
    this.row = row;
  }
  public String getTemp_loanBankId() {
    return temp_loanBankId;
  }
  public void setTemp_loanBankId(String temp_loanBankId) {
    this.temp_loanBankId = temp_loanBankId;
  }
}
