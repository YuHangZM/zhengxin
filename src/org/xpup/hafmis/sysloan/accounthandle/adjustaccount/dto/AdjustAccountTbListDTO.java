package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * ��װ�˴���ά���б����ݵ�DTO
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountTbListDTO {

  /**
   * ƾ֤���
   */
  private String docNum = "";

  /**
   * ��ͬ���
   */
  private String contractId = "";

  /**
   * �����ʺ�
   */
  private String loanKouAcc = "";

  /**
   * ���������
   */
  private String borrowerName = "";

  /**
   * ҵ������
   */
  private String bizType = "";

  /**
   * ������ʾ������ҵ������
   */
  private String bizTypeStr = "";

  /**
   * ���Ž��
   */
  private BigDecimal occurMoney = new BigDecimal(0.00);

  /**
   * ���ձ���
   */
  private BigDecimal callbackCorpus = new BigDecimal(0.00);

  /**
   * ������Ϣ
   */
  private BigDecimal callbackInterest = new BigDecimal(0.00);

  /**
   * ��Ϣ��Ϣ
   */
  private BigDecimal punishInterest = new BigDecimal(0.00);
  
  /**
   * ʵ���ܶ�
   */
  private BigDecimal callbackTotal = new BigDecimal(0.00);

  /**
   * ҵ��״̬
   */
  private String bisSt = "";
  
  /**
   * ԭ��ˮ��
   */
  private String wrongFlowNum = "";

  /**
   * PL202ͷ��id
   */
  private String flowHeadId = "";

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getBisSt() {
    return bisSt;
  }

  public void setBisSt(String bisSt) {
    this.bisSt = bisSt;
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

  public BigDecimal getCallbackCorpus() {
    return callbackCorpus;
  }

  public void setCallbackCorpus(BigDecimal callbackCorpus) {
    this.callbackCorpus = callbackCorpus;
  }

  public BigDecimal getCallbackInterest() {
    return callbackInterest;
  }

  public void setCallbackInterest(BigDecimal callbackInterest) {
    this.callbackInterest = callbackInterest;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getBizTypeStr() {
    return bizTypeStr;
  }

  public void setBizTypeStr(String bizTypeStr) {
    this.bizTypeStr = bizTypeStr;
  }

  public String getWrongFlowNum() {
    return wrongFlowNum;
  }

  public void setWrongFlowNum(String wrongFlowNum) {
    this.wrongFlowNum = wrongFlowNum;
  }

  public BigDecimal getCallbackTotal() {
    return callbackTotal;
  }

  public void setCallbackTotal(BigDecimal callbackTotal) {
    this.callbackTotal = callbackTotal;
  }
}
