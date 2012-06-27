package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;

import java.math.BigDecimal;

public class BizCheckShowListDTO {
  private String flowHeadId = "";// ͷ����ˮ��

  private String docNum = "";// ƾ֤���

  private String loanKouAcc = "";// �����˺�

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String bizType = "";// ת��ҵ������
  
  private String originalitybizType = "";// ԭʼҵ������

  private String wrongBizType = "";//���˵�������

  private String occurMoney = "";// ���Ž��

  private String reclaimCorpus = "";// ���ձ���

  private String reclaimAccrual = "";// ������Ϣ

  private String realPunishInterest = "";// ���շ�Ϣ
  
  private String badDebt ="";// ���˺������

  private String putUpMoney = "";// ���˽��

  private String bail = "";// ��֤��

  private String bailAccrual = "";// ��֤����Ϣ

  private String bizSt = "";// ҵ��״̬

  private String bizDate = "";// ҵ��ʱ��
  
  private BigDecimal reclaim = new BigDecimal(0.00);// ����Ӧ�����
  
  private BigDecimal reclaimback = new BigDecimal(0.00);// ����ʵ�����
  
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }


  public String getWrongBizType() {
    return wrongBizType;
  }

  public void setWrongBizType(String wrongBizType) {
    this.wrongBizType = wrongBizType;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
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

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
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

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getOriginalitybizType() {
    return originalitybizType;
  }

  public void setOriginalitybizType(String originalitybizType) {
    this.originalitybizType = originalitybizType;
  }

  public BigDecimal getReclaim() {
    return reclaim;
  }

  public void setReclaim(BigDecimal reclaim) {
    this.reclaim = reclaim;
  }

  public BigDecimal getReclaimback() {
    return reclaimback;
  }

  public void setReclaimback(BigDecimal reclaimback) {
    this.reclaimback = reclaimback;
  }
}
