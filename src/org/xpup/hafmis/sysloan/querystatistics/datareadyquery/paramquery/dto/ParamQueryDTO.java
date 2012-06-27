package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.dto;

import java.math.BigDecimal;

public class ParamQueryDTO {
  private String loanBankId="";//����
  private String temp_loanBankId="";
  private String kouAccMode="";//1���ۿʽ
  private String decideDateMode="";//2�����շ�ʽ
  private String uniteDate="";//2��ͳһ����
  private String corpus="";//3����������
  private String interest="";//3��������Ϣ
  private String overdueCorpus="";//3�����ڱ���
  private String overdueInterest="";//3��������Ϣ
  private String punishInterest="";//3����Ϣ
  private String punishInterestRateMode="";//4����Ϣ���ʷ�ʽ
  private BigDecimal punishInterestDateRate=new BigDecimal(0.00);//4������Ϣ������
  private BigDecimal contractDateRate=new BigDecimal(0.00);//4������ͬ������
  private BigDecimal moneyDateInterest=new BigDecimal(0.00);//4������ÿ����Ϣ
  private String permitDateNum="";//5����������
  private String isRecord="";//5���Ƿ�Ƿ�Ϣ
  private String commonMonthNum="";//6������
  private String attentionMonthNum="";//6����ע
  private String subMonthNum="";//6���μ�
  private String shadinessMonthNum="";//6������
  private String lossMonthNum="";//6����ʧ
  private String loanVipCheck="";//7����������
  private String endorseLoan="";//7��ǩ������
  private BigDecimal currentRate=new BigDecimal(0.00);//8����������
  private BigDecimal terminalRate=new BigDecimal(0.00);//8����������
  private String isAdopt="";//9���Ƿ����������
  private String type="";//��������ҳ�水ť���õ�
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getAttentionMonthNum() {
    return attentionMonthNum;
  }
  public void setAttentionMonthNum(String attentionMonthNum) {
    this.attentionMonthNum = attentionMonthNum;
  }
  public String getCommonMonthNum() {
    return commonMonthNum;
  }
  public void setCommonMonthNum(String commonMonthNum) {
    this.commonMonthNum = commonMonthNum;
  }
  public BigDecimal getContractDateRate() {
    return contractDateRate;
  }
  public void setContractDateRate(BigDecimal contractDateRate) {
    this.contractDateRate = contractDateRate;
  }
  public String getCorpus() {
    return corpus;
  }
  public void setCorpus(String corpus) {
    this.corpus = corpus;
  }
  public BigDecimal getCurrentRate() {
    return currentRate;
  }
  public void setCurrentRate(BigDecimal currentRate) {
    this.currentRate = currentRate;
  }
  public String getDecideDateMode() {
    return decideDateMode;
  }
  public void setDecideDateMode(String decideDateMode) {
    this.decideDateMode = decideDateMode;
  }
  public String getEndorseLoan() {
    return endorseLoan;
  }
  public void setEndorseLoan(String endorseLoan) {
    this.endorseLoan = endorseLoan;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
  }
  public String getIsAdopt() {
    return isAdopt;
  }
  public void setIsAdopt(String isAdopt) {
    this.isAdopt = isAdopt;
  }
  public String getIsRecord() {
    return isRecord;
  }
  public void setIsRecord(String isRecord) {
    this.isRecord = isRecord;
  }
  public String getKouAccMode() {
    return kouAccMode;
  }
  public void setKouAccMode(String kouAccMode) {
    this.kouAccMode = kouAccMode;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanVipCheck() {
    return loanVipCheck;
  }
  public void setLoanVipCheck(String loanVipCheck) {
    this.loanVipCheck = loanVipCheck;
  }
  public String getLossMonthNum() {
    return lossMonthNum;
  }
  public void setLossMonthNum(String lossMonthNum) {
    this.lossMonthNum = lossMonthNum;
  }
  public BigDecimal getMoneyDateInterest() {
    return moneyDateInterest;
  }
  public void setMoneyDateInterest(BigDecimal moneyDateInterest) {
    this.moneyDateInterest = moneyDateInterest;
  }
  public String getOverdueCorpus() {
    return overdueCorpus;
  }
  public void setOverdueCorpus(String overdueCorpus) {
    this.overdueCorpus = overdueCorpus;
  }
  public String getOverdueInterest() {
    return overdueInterest;
  }
  public void setOverdueInterest(String overdueInterest) {
    this.overdueInterest = overdueInterest;
  }
  public String getPermitDateNum() {
    return permitDateNum;
  }
  public void setPermitDateNum(String permitDateNum) {
    this.permitDateNum = permitDateNum;
  }
  public String getPunishInterest() {
    return punishInterest;
  }
  public void setPunishInterest(String punishInterest) {
    this.punishInterest = punishInterest;
  }
  public BigDecimal getPunishInterestDateRate() {
    return punishInterestDateRate;
  }
  public void setPunishInterestDateRate(BigDecimal punishInterestDateRate) {
    this.punishInterestDateRate = punishInterestDateRate;
  }
  public String getPunishInterestRateMode() {
    return punishInterestRateMode;
  }
  public void setPunishInterestRateMode(String punishInterestRateMode) {
    this.punishInterestRateMode = punishInterestRateMode;
  }
  public String getShadinessMonthNum() {
    return shadinessMonthNum;
  }
  public void setShadinessMonthNum(String shadinessMonthNum) {
    this.shadinessMonthNum = shadinessMonthNum;
  }
  public String getSubMonthNum() {
    return subMonthNum;
  }
  public void setSubMonthNum(String subMonthNum) {
    this.subMonthNum = subMonthNum;
  }
  public BigDecimal getTerminalRate() {
    return terminalRate;
  }
  public void setTerminalRate(BigDecimal terminalRate) {
    this.terminalRate = terminalRate;
  }
  public String getUniteDate() {
    return uniteDate;
  }
  public void setUniteDate(String uniteDate) {
    this.uniteDate = uniteDate;
  }
  public String getTemp_loanBankId() {
    return temp_loanBankId;
  }
  public void setTemp_loanBankId(String temp_loanBankId) {
    this.temp_loanBankId = temp_loanBankId;
  }
}
