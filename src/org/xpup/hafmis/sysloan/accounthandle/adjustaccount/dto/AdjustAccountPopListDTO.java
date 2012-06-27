package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * ��װ�˴��ʵ������������б���Ϣ��DTO
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountPopListDTO {

  /**
   * PL202id
   */
  private Integer flowHeadId = new Integer(0);

  /**
   * �����ʺ�
   */
  private String loanKouAcc = "";

  /**
   * ƾ֤���
   */
  private String docNum = "";

  /**
   * ��ͬ���
   */
  private String contractId = "";

  /**
   * ���������
   */
  private String borrowerName = "";

  /**
   * ֤������
   */
  private String cardNum = "";

  /**
   * ���Ž��
   */
  private BigDecimal occurMoney = new BigDecimal(0.00);

  /**
   * ���λ����
   */
  private BigDecimal loanBackPay = new BigDecimal(0.00);

  /**
   * ���˷�����
   */
  private BigDecimal overPay = new BigDecimal(0.00);

  /**
   * ʵ�ս��
   */
  private BigDecimal factPay = new BigDecimal(0.00);

  /**
   * ҵ������
   */
  private String bizType = "";

  /**
   * �ſ�����id
   */
  private String loanbankid = "";
  
  /**
   * ҵ������
   */
  private String bizdate = "";

  public String getBizdate() {
    return bizdate;
  }

  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
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

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public BigDecimal getFactPay() {
    return factPay;
  }

  public void setFactPay(BigDecimal factPay) {
    this.factPay = factPay;
  }

  public BigDecimal getLoanBackPay() {
    return loanBackPay;
  }

  public void setLoanBackPay(BigDecimal loanBackPay) {
    this.loanBackPay = loanBackPay;
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

  public BigDecimal getOverPay() {
    return overPay;
  }

  public void setOverPay(BigDecimal overPay) {
    this.overPay = overPay;
  }

  public Integer getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(Integer flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getLoanbankid() {
    return loanbankid;
  }

  public void setLoanbankid(String loanbankid) {
    this.loanbankid = loanbankid;
  }

}
