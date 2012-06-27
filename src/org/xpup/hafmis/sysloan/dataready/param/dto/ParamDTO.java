package org.xpup.hafmis.sysloan.dataready.param.dto;

import java.math.BigDecimal;

public class ParamDTO {
  private String loanBankId="";//����
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
  private String isAdopt1="";//10�������ڴ����Ƿ����������
  private String punishInterestRateMode1="";//11�������ڴ��Ϣ���ʷ�ʽ
  private BigDecimal punishInterestDateRate1=new BigDecimal(0.00);//11�������ڴ����Ϣ������
  private BigDecimal contractDateRate1=new BigDecimal(0.00);//11�������ڴ����ͬ������
  private BigDecimal moneyDateInterest1=new BigDecimal(0.00);//11�������ڴ����ÿ����Ϣ
  private String permitDateNum1="";//12�������ڴ����������
  private String isRecord1="";//12�������ڴ����Ƿ�Ƿ�Ϣ
  //����
  private String punishInterestRateMode_1="";//13����Ϣ���ʷ�ʽ
  private BigDecimal punishInterestDateRate_1=new BigDecimal(0.00);//13������Ϣ������
  private BigDecimal contractDateRate_1=new BigDecimal(0.00);//13������ͬ������
  private BigDecimal moneyDateInterest_1=new BigDecimal(0.00);//13������ÿ����Ϣ
  //����
  //������
  private String shifouqiyong="";
  //������
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
  public String getPermitDateNum() {
    return permitDateNum;
  }
  public void setPermitDateNum(String permitDateNum) {
    this.permitDateNum = permitDateNum;
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
  public String getCorpus() {
    return corpus;
  }
  public void setCorpus(String corpus) {
    this.corpus = corpus;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
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
  public String getPunishInterest() {
    return punishInterest;
  }
  public void setPunishInterest(String punishInterest) {
    this.punishInterest = punishInterest;
  }
  public BigDecimal getContractDateRate1() {
    return contractDateRate1;
  }
  public void setContractDateRate1(BigDecimal contractDateRate1) {
    this.contractDateRate1 = contractDateRate1;
  }
  public String getIsAdopt1() {
    return isAdopt1;
  }
  public void setIsAdopt1(String isAdopt1) {
    this.isAdopt1 = isAdopt1;
  }
  public String getIsRecord1() {
    return isRecord1;
  }
  public void setIsRecord1(String isRecord1) {
    this.isRecord1 = isRecord1;
  }
  public BigDecimal getMoneyDateInterest1() {
    return moneyDateInterest1;
  }
  public void setMoneyDateInterest1(BigDecimal moneyDateInterest1) {
    this.moneyDateInterest1 = moneyDateInterest1;
  }
  public String getPermitDateNum1() {
    return permitDateNum1;
  }
  public void setPermitDateNum1(String permitDateNum1) {
    this.permitDateNum1 = permitDateNum1;
  }
  public BigDecimal getPunishInterestDateRate1() {
    return punishInterestDateRate1;
  }
  public void setPunishInterestDateRate1(BigDecimal punishInterestDateRate1) {
    this.punishInterestDateRate1 = punishInterestDateRate1;
  }
  public String getPunishInterestRateMode1() {
    return punishInterestRateMode1;
  }
  public void setPunishInterestRateMode1(String punishInterestRateMode1) {
    this.punishInterestRateMode1 = punishInterestRateMode1;
  }
  public BigDecimal getContractDateRate_1() {
    return contractDateRate_1;
  }
  public void setContractDateRate_1(BigDecimal contractDateRate_1) {
    this.contractDateRate_1 = contractDateRate_1;
  }
  public BigDecimal getMoneyDateInterest_1() {
    return moneyDateInterest_1;
  }
  public void setMoneyDateInterest_1(BigDecimal moneyDateInterest_1) {
    this.moneyDateInterest_1 = moneyDateInterest_1;
  }
  public BigDecimal getPunishInterestDateRate_1() {
    return punishInterestDateRate_1;
  }
  public void setPunishInterestDateRate_1(BigDecimal punishInterestDateRate_1) {
    this.punishInterestDateRate_1 = punishInterestDateRate_1;
  }
  public String getPunishInterestRateMode_1() {
    return punishInterestRateMode_1;
  }
  public void setPunishInterestRateMode_1(String punishInterestRateMode_1) {
    this.punishInterestRateMode_1 = punishInterestRateMode_1;
  }
  public String getShifouqiyong() {
    return shifouqiyong;
  }
  public void setShifouqiyong(String shifouqiyong) {
    this.shifouqiyong = shifouqiyong;
  }
}
