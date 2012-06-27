package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * ������װ��������Ϣ���������Ϣ��DTO
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountTaSaveDTO {

  // PL202
  /**
   * Ӧ������
   */
  private BigDecimal shouldCount = new BigDecimal(0.00);

  /**
   * Ӧ����������
   */
  private BigDecimal shouldCorpus = new BigDecimal(0.00);

  /**
   * Ӧ����Ϣ
   */
  private BigDecimal shouldInterest = new BigDecimal(0.00);

  /**
   * Ӧ�����ڱ���
   */
  private BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);

  /**
   * Ӧ��������Ϣ
   */
  private BigDecimal shouldOverdueInterest = new BigDecimal(0.00);

  /**
   * Ӧ����Ϣ
   */
  private BigDecimal shouldPunishInterest = new BigDecimal(0.00);

  /**
   * ʵ������
   */
  private BigDecimal realCount = new BigDecimal(0.00);

  /**
   * ʵ������
   */
  private BigDecimal realCorpus = new BigDecimal(0.00);

  /**
   * ʵ����Ϣ
   */
  private BigDecimal realInterest = new BigDecimal(0.00);

  /**
   * ʵ�����ڱ���
   */
  private BigDecimal realOverdueCorpus = new BigDecimal(0.00);

  /**
   * ʵ��������Ϣ
   */
  private BigDecimal realOverdueInterest = new BigDecimal(0.00);

  /**
   * ʵ����Ϣ
   */
  private BigDecimal realPunishInterest = new BigDecimal(0.00);

  /**
   * �������
   */
  private BigDecimal occurMoney = new BigDecimal(0.00);

  /**
   * �ſ�����
   */
  private String loanBankId = "";

  /**
   * �˴���λ
   */
  private String hedaiOrg = "";

  /**
   * ��ˮ��
   */
  private String flowHeadId = "";

  /**
   * ҵ������
   */
  private String bizType = "";

  /**
   * �Ƶ���
   */
  private String makePerson = "";

  // PL203
  /**
   * ��ͬ���
   */
  private String contractId = "";

  /**
   * �����˺�
   */
  private String loanKouAcc = "";

  /**
   * ��������
   */
  private String yearMonth = "";

  /**
   * Ӧ������
   */
  private BigDecimal shouldCorpusTail = new BigDecimal(0.00);

  /**
   * Ӧ����Ϣ
   */
  private BigDecimal shouldInterestTail = new BigDecimal(0.00);

  /**
   * Ӧ����Ϣ
   */
  private BigDecimal shouldPunishInterestTail = new BigDecimal(0.00);

  /**
   * ʵ������
   */
  private BigDecimal realCorpusTail = new BigDecimal(0.00);

  /**
   * ʵ����Ϣ
   */
  private BigDecimal realInterestTail = new BigDecimal(0.00);

  /**
   * ʵ����Ϣ
   */
  private BigDecimal realPunishInterestTail = new BigDecimal(0.00);

  /**
   * �������
   */
  private BigDecimal occurMoneyTail = new BigDecimal(0.00);
  
  /**
   * ��ʱ��Ϣ
   */
  private BigDecimal tempPunishInterest = new BigDecimal(0.00);

  /**
   * ��������
   */
  private String loanType = "";

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getHedaiOrg() {
    return hedaiOrg;
  }

  public void setHedaiOrg(String hedaiOrg) {
    this.hedaiOrg = hedaiOrg;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public BigDecimal getOccurMoneyTail() {
    return occurMoneyTail;
  }

  public void setOccurMoneyTail(BigDecimal occurMoneyTail) {
    this.occurMoneyTail = occurMoneyTail;
  }

  public BigDecimal getRealCorpus() {
    return realCorpus;
  }

  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }

  public BigDecimal getRealCorpusTail() {
    return realCorpusTail;
  }

  public void setRealCorpusTail(BigDecimal realCorpusTail) {
    this.realCorpusTail = realCorpusTail;
  }

  public BigDecimal getRealCount() {
    return realCount;
  }

  public void setRealCount(BigDecimal realCount) {
    this.realCount = realCount;
  }

  public BigDecimal getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
  }

  public BigDecimal getRealInterestTail() {
    return realInterestTail;
  }

  public void setRealInterestTail(BigDecimal realInterestTail) {
    this.realInterestTail = realInterestTail;
  }

  public BigDecimal getRealOverdueCorpus() {
    return realOverdueCorpus;
  }

  public void setRealOverdueCorpus(BigDecimal realOverdueCorpus) {
    this.realOverdueCorpus = realOverdueCorpus;
  }

  public BigDecimal getRealOverdueInterest() {
    return realOverdueInterest;
  }

  public void setRealOverdueInterest(BigDecimal realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
  }

  public BigDecimal getRealPunishInterest() {
    return realPunishInterest;
  }

  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }

  public BigDecimal getRealPunishInterestTail() {
    return realPunishInterestTail;
  }

  public void setRealPunishInterestTail(BigDecimal realPunishInterestTail) {
    this.realPunishInterestTail = realPunishInterestTail;
  }

  public BigDecimal getShouldCorpus() {
    return shouldCorpus;
  }

  public void setShouldCorpus(BigDecimal shouldCorpus) {
    this.shouldCorpus = shouldCorpus;
  }

  public BigDecimal getShouldCorpusTail() {
    return shouldCorpusTail;
  }

  public void setShouldCorpusTail(BigDecimal shouldCorpusTail) {
    this.shouldCorpusTail = shouldCorpusTail;
  }

  public BigDecimal getShouldCount() {
    return shouldCount;
  }

  public void setShouldCount(BigDecimal shouldCount) {
    this.shouldCount = shouldCount;
  }

  public BigDecimal getShouldInterest() {
    return shouldInterest;
  }

  public void setShouldInterest(BigDecimal shouldInterest) {
    this.shouldInterest = shouldInterest;
  }

  public BigDecimal getShouldInterestTail() {
    return shouldInterestTail;
  }

  public void setShouldInterestTail(BigDecimal shouldInterestTail) {
    this.shouldInterestTail = shouldInterestTail;
  }

  public BigDecimal getShouldOverdueCorpus() {
    return shouldOverdueCorpus;
  }

  public void setShouldOverdueCorpus(BigDecimal shouldOverdueCorpus) {
    this.shouldOverdueCorpus = shouldOverdueCorpus;
  }

  public BigDecimal getShouldOverdueInterest() {
    return shouldOverdueInterest;
  }

  public void setShouldOverdueInterest(BigDecimal shouldOverdueInterest) {
    this.shouldOverdueInterest = shouldOverdueInterest;
  }

  public BigDecimal getShouldPunishInterest() {
    return shouldPunishInterest;
  }

  public void setShouldPunishInterest(BigDecimal shouldPunishInterest) {
    this.shouldPunishInterest = shouldPunishInterest;
  }

  public BigDecimal getShouldPunishInterestTail() {
    return shouldPunishInterestTail;
  }

  public void setShouldPunishInterestTail(BigDecimal shouldPunishInterestTail) {
    this.shouldPunishInterestTail = shouldPunishInterestTail;
  }

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }

  public BigDecimal getTempPunishInterest() {
    return tempPunishInterest;
  }

  public void setTempPunishInterest(BigDecimal tempPunishInterest) {
    this.tempPunishInterest = tempPunishInterest;
  }

}
