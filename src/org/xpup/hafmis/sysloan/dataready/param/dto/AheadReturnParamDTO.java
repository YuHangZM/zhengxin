package org.xpup.hafmis.sysloan.dataready.param.dto;

import java.math.BigDecimal;

public class AheadReturnParamDTO {
  private String loanBankId="";//����
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
  private String maleAge=""; //��������
  private String femaleAge="";//Ů������
  private String timeMax_1="";//��Ʒ������
  private String timeMax_2="";//���ַ�����
  private String salaryRate="";//���ʱ���

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
  public String getFemaleAge() {
    return femaleAge;
  }
  public void setFemaleAge(String femaleAge) {
    this.femaleAge = femaleAge;
  }
  public String getMaleAge() {
    return maleAge;
  }
  public void setMaleAge(String maleAge) {
    this.maleAge = maleAge;
  }
  public String getSalaryRate() {
    return salaryRate;
  }
  public void setSalaryRate(String salaryRate) {
    this.salaryRate = salaryRate;
  }
  public String getTimeMax_1() {
    return timeMax_1;
  }
  public void setTimeMax_1(String timeMax_1) {
    this.timeMax_1 = timeMax_1;
  }
  public String getTimeMax_2() {
    return timeMax_2;
  }
  public void setTimeMax_2(String timeMax_2) {
    this.timeMax_2 = timeMax_2;
  }
}
