package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto;

/**
 * @author ��Ұ 2007-10-05
 */
public class BailClearInterestTaDTO {

  private String loanBankName = null; // �ſ�����

  private String paramExplainInterestD = null; // ��������

  private String paramExplainInterestL = null; // ��������

  private String loanKouAcc = null; // �����ʺ�

  private String contractId = null; // ��ͬID

  private String borrowerName = null; // ���������

  private String cardNum = null; // ֤������

  private String bailBalance = null;// ��Ϣǰ��֤��
  
  private String preIntegral = null;// �������
  
  private String curIntegral = null;// �������

  public String getCurIntegral() {
    return curIntegral;
  }

  public void setCurIntegral(String curIntegral) {
    this.curIntegral = curIntegral;
  }

  public String getPreIntegral() {
    return preIntegral;
  }

  public void setPreIntegral(String preIntegral) {
    this.preIntegral = preIntegral;
  }

  public String getBailBalance() {
    return bailBalance;
  }

  public void setBailBalance(String bailBalance) {
    this.bailBalance = bailBalance;
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getParamExplainInterestD() {
    return paramExplainInterestD;
  }

  public void setParamExplainInterestD(String paramExplainInterestD) {
    this.paramExplainInterestD = paramExplainInterestD;
  }

  public String getParamExplainInterestL() {
    return paramExplainInterestL;
  }

  public void setParamExplainInterestL(String paramExplainInterestL) {
    this.paramExplainInterestL = paramExplainInterestL;
  }

}
