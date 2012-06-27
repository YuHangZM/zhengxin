package org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto;

import java.math.BigDecimal;

public class BailenRolTbDTO {

  private Integer count = new Integer(0);// ��¼��

  private BigDecimal occurMoneyTotal = new BigDecimal(0.00);// �ϼƣ���֤����

  private String contractId = null; // ��ͬID

  private String borrowerName = null; // ���������

  private String cardNum = null; // ֤������

  private String loanBankName = null; // �ſ�����

  private String bizDate = null; // ��ȡ����

  private String occurMoney = null;// ��֤����

  private String bizSt = null; // ҵ��״̬

  private String flowHeadId = null;// ������ˮ�� ͷ��ID

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getOccurMoneyTotal() {
    return occurMoneyTotal;
  }

  public void setOccurMoneyTotal(BigDecimal occurMoneyTotal) {
    this.occurMoneyTotal = occurMoneyTotal;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
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

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(String occurMoney) {
    this.occurMoney = occurMoney;
  }

}
