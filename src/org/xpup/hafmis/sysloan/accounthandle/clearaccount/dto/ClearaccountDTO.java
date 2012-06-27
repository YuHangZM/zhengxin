package org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto;

public class ClearaccountDTO {
  private String flowHeadId = null;// ͷ����ˮ��

  private String docNum = null;// ƾ֤���

  private String loanKouAcc = null;// �����˺�

  private String contractId = null;// ��ͬ���

  private String borrowerName = null;// ���������

  private String bizType = null;// ת��ҵ������

  private String originalitybizType = null;// ԭʼҵ������

  private String wrongBizType = null;

  private String occurMoney = null;// ���Ž��

  private String reclaimCorpus = null;// ���ձ���

  private String reclaimAccrual = null;// ������Ϣ

  private String realPunishInterest = null;// ���շ�Ϣ

  private String badDebt = null;// ���˺������

  private String putUpMoney = null;// ���˽��

  private String bail = null;// ��֤��

  private String bailAccrual = null;// ��֤����Ϣ

  private String bizSt = null;// ҵ��״̬

  private String bizDate = null;// ҵ��ʱ��
  
  private String sumReclaimMoney=""; //���ս��
  
  private String realCorpus = null;//ʵ������
  
  private String realInterest = null;//ʵ����Ϣ
  
  private String realPunish_interest = null;//ʵ����Ϣ
  
  private String loanBank = null;//�ſ�����(ί������)
  
  private String makeBillPerson = "";
  
  private String checkPerson = "";
  
  private String clearAccPerson = "";
  
  private String isGjjLoanBack = "";

  public String getCheckPerson() {
    return checkPerson;
  }

  public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
  }

  public String getClearAccPerson() {
    return clearAccPerson;
  }

  public void setClearAccPerson(String clearAccPerson) {
    this.clearAccPerson = clearAccPerson;
  }

  public String getIsGjjLoanBack() {
    return isGjjLoanBack;
  }

  public void setIsGjjLoanBack(String isGjjLoanBack) {
    this.isGjjLoanBack = isGjjLoanBack;
  }

  public String getMakeBillPerson() {
    return makeBillPerson;
  }

  public void setMakeBillPerson(String makeBillPerson) {
    this.makeBillPerson = makeBillPerson;
  }

  public String getSumReclaimMoney() {
    return sumReclaimMoney;
  }

  public void setSumReclaimMoney(String sumReclaimMoney) {
    this.sumReclaimMoney = sumReclaimMoney;
  }

  public String getBadDebt() {
    return badDebt;
  }

  public void setBadDebt(String badDebt) {
    this.badDebt = badDebt;
  }

  public String getBail() {
    return bail;
  }

  public void setBail(String bail) {
    this.bail = bail;
  }

  public String getBailAccrual() {
    return bailAccrual;
  }

  public void setBailAccrual(String bailAccrual) {
    this.bailAccrual = bailAccrual;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
  }

  public String getOriginalitybizType() {
    return originalitybizType;
  }

  public void setOriginalitybizType(String originalitybizType) {
    this.originalitybizType = originalitybizType;
  }

  public String getPutUpMoney() {
    return putUpMoney;
  }

  public void setPutUpMoney(String putUpMoney) {
    this.putUpMoney = putUpMoney;
  }

  public String getRealPunishInterest() {
    return realPunishInterest;
  }

  public void setRealPunishInterest(String realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }

  public String getReclaimAccrual() {
    return reclaimAccrual;
  }

  public void setReclaimAccrual(String reclaimAccrual) {
    this.reclaimAccrual = reclaimAccrual;
  }

  public String getReclaimCorpus() {
    return reclaimCorpus;
  }

  public void setReclaimCorpus(String reclaimCorpus) {
    this.reclaimCorpus = reclaimCorpus;
  }

  public String getWrongBizType() {
    return wrongBizType;
  }

  public void setWrongBizType(String wrongBizType) {
    this.wrongBizType = wrongBizType;
  }

  public String getLoanBank() {
    return loanBank;
  }

  public void setLoanBank(String loanBank) {
    this.loanBank = loanBank;
  }

  public String getRealCorpus() {
    return realCorpus;
  }

  public void setRealCorpus(String realCorpus) {
    this.realCorpus = realCorpus;
  }

  public String getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(String realInterest) {
    this.realInterest = realInterest;
  }

  public String getRealPunish_interest() {
    return realPunish_interest;
  }

  public void setRealPunish_interest(String realPunish_interest) {
    this.realPunish_interest = realPunish_interest;
  }


}
