package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;
/**
 * ��װ�˰�����ʵ���ҳ����Ϣ��DTO
 * @author ���Ʒ�
 *
 */
public class AdjustAccountTaInfoDTO {
  /**
   * PL202ͷ��id
   */
  private String flowHeadId = "";

  /**
   * �����ʺ�
   */
  private String loanKouAcc = "";

  /**
   * ƾ֤���
   */
  private String docNum = "";

  /**
   * ҵ������
   */
  private String bizType = "";
  /**
   * ҵ������String��
   */
  private String strBizType = "";

  /**
   * ���Ž��
   */
  private BigDecimal putOutMoney = new BigDecimal(0.00);

  /**
   * ���ս��
   */
  private BigDecimal callbackMoney = new BigDecimal(0.00);

  /**
   * ������Ϣ
   */
  private BigDecimal callbackInterest = new BigDecimal(0.00);

  /**
   * ��Ϣ��Ϣ
   */
  private BigDecimal punishInterest = new BigDecimal(0.00);

  /**
   * ���������
   */
  private String borrowerName = "";

  /**
   * �Ƶ���
   */
  private String makePerson = "";

  /**
   * �Զ�����
   */
  private String autoOverPay = "";
  
  /**
   * ��������
   */
  private String yearMonth = "";

  public String getYearMonth() {
    return yearMonth;
  }

  public void setYearMonth(String yearMonth) {
    this.yearMonth = yearMonth;
  }

  public String getAutoOverPay() {
    return autoOverPay;
  }

  public void setAutoOverPay(String autoOverPay) {
    this.autoOverPay = autoOverPay;
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

  public BigDecimal getCallbackInterest() {
    return callbackInterest;
  }

  public void setCallbackInterest(BigDecimal callbackInterest) {
    this.callbackInterest = callbackInterest;
  }

  public BigDecimal getCallbackMoney() {
    return callbackMoney;
  }

  public void setCallbackMoney(BigDecimal callbackMoney) {
    this.callbackMoney = callbackMoney;
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

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public BigDecimal getPutOutMoney() {
    return putOutMoney;
  }

  public void setPutOutMoney(BigDecimal putOutMoney) {
    this.putOutMoney = putOutMoney;
  }

  public String getStrBizType() {
    return strBizType;
  }

  public void setStrBizType(String strBizType) {
    this.strBizType = strBizType;
  }
}
