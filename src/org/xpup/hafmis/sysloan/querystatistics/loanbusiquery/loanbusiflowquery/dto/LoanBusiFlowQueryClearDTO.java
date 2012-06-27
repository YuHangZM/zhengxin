/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanBusiFlowQueryClearDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-10-18
 **/
package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto;

import java.math.BigDecimal;

public class LoanBusiFlowQueryClearDTO {
  private String contractId = ""; // ��ͬ���

  private String borrowerName = ""; // ���������

  private String cardKind = ""; // ֤������

  private String cardKindName = ""; // ֤����������

  private String cardNum = ""; // ֤������

  private String loanBankId = ""; // �ſ�����

  private String loanBankName = ""; // �ſ���������

  private String loanKouAcc = "";// ���пۿ��˺�(�����˺�)

  private BigDecimal loanMoney = new BigDecimal(0.00); // �����

  private BigDecimal realCorpus = new BigDecimal(0.00); // ʵ������

  private BigDecimal surplusCorpus = new BigDecimal(0.00); // ʣ�౾��

  private String loanTimeLimit = ""; // �������

  private String loanMonthRate = "";// ������

  public BigDecimal getRealCorpus() {
    return realCorpus;
  }

  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;
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

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
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

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(String loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public BigDecimal getSurplusCorpus() {
    return surplusCorpus;
  }

  public void setSurplusCorpus(BigDecimal surplusCorpus) {
    this.surplusCorpus = surplusCorpus;
  }

}
