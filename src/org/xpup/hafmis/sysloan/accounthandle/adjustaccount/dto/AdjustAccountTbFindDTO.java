package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * ��װ�˴���ά����ѯ������DTO
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountTbFindDTO {
  /**
   * �����ʺ�
   */
  private String loanKouAcc = "";

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
   * ƾ֤���
   */
  private String docNum = "";

  /**
   * ҵ������
   */
  private String bizType = "";

  /**
   * �ſ�����
   */
  private String loanBankId = "";

  /**
   * ҵ��״̬
   */
  private String bizSt = "";

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
}
