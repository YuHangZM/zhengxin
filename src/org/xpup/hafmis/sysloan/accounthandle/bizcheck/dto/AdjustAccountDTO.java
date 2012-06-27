package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;

import java.math.BigDecimal;

public class AdjustAccountDTO {
  private String flowHeadId = ""; // pl202ͷ��Id
  
  private String docNum = "";// ����ƾ֤���

  private String loanKouAcc = "";// �����˺�

  private String bizType = "";// ת��ҵ������

  private BigDecimal occurMoney = new BigDecimal(0.00);// ���Ž��

  private BigDecimal reclaimCorpus = new BigDecimal(0.00);// ���ձ���

  private BigDecimal reclaimAccrual = new BigDecimal(0.00);// ������Ϣ

  private BigDecimal realPunishInterest = new BigDecimal(0.00);// ��Ϣ��Ϣ

  private String borrowerName ="";// ���������

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
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

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public BigDecimal getRealPunishInterest() {
    return realPunishInterest;
  }

  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }

  public BigDecimal getReclaimAccrual() {
    return reclaimAccrual;
  }

  public void setReclaimAccrual(BigDecimal reclaimAccrual) {
    this.reclaimAccrual = reclaimAccrual;
  }

  public BigDecimal getReclaimCorpus() {
    return reclaimCorpus;
  }

  public void setReclaimCorpus(BigDecimal reclaimCorpus) {
    this.reclaimCorpus = reclaimCorpus;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }
}
