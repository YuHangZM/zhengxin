package org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto;

public class AdvancepayloanTaDTO {
  private String loanKouAcc = "";//�����ʺ�
  private String contractId = "";//��ͬ���
  private String borrowerName = "";//���������
  private String cardKind = "";//֤������
  private String cardNum = "";//֤������
  private String corpusInterest = "";//�»���Ϣ
  private String overplusLoanMoney = "";//ʣ�౾�� OVERPLUS_LOAN_MONEY
  private String creditType = "";//���ʽ
  private String pre_term = "";//ԭ�������
  private String loanKouYearmonth = "";//t3.loan_kou_yearmonth������
  
  public String getLoanKouYearmonth() {
    return loanKouYearmonth;
  }
  public void setLoanKouYearmonth(String loanKouYearmonth) {
    this.loanKouYearmonth = loanKouYearmonth;
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
  public String getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public String getCreditType() {
    return creditType;
  }
  public void setCreditType(String creditType) {
    this.creditType = creditType;
  }
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(String overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getPre_term() {
    return pre_term;
  }
  public void setPre_term(String pre_term) {
    this.pre_term = pre_term;
  }
  
  
}
