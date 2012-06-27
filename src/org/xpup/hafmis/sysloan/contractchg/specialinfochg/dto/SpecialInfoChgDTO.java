package org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto;

import java.math.BigDecimal;

public class SpecialInfoChgDTO {
  private String contractId="";//��ͬ���
  private String borrowerName="";//���������
  private BigDecimal loanMoney=new BigDecimal(0.00);//������
  private String loanTimeLimit="";//��������
  private BigDecimal loanMonthRate=new BigDecimal(0.00);//ÿ������
  private String loanMode="";//���ʽ
  private String temp_loanMode="";
  private BigDecimal corpusInterest=new BigDecimal(0.00);//�»���Ϣ
  private BigDecimal firstLoanMoney=new BigDecimal(0.00);//�״λ�����
  private BigDecimal loanPoundage=new BigDecimal(0.00);//��������
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
  private String loanBankId="";//����
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
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
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getChargeMode() {
    return chargeMode;
  }
  public void setChargeMode(String chargeMode) {
    this.chargeMode = chargeMode;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }
  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
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
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }
  public BigDecimal getLoanMoney() {
    return loanMoney;
  }
  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }
  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }
  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }
  public BigDecimal getLoanPoundage() {
    return loanPoundage;
  }
  public void setLoanPoundage(BigDecimal loanPoundage) {
    this.loanPoundage = loanPoundage;
  }
  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }
  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
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
  public String getTemp_loanMode() {
    return temp_loanMode;
  }
  public void setTemp_loanMode(String temp_loanMode) {
    this.temp_loanMode = temp_loanMode;
  }
}
