package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;
import java.math.BigDecimal;
public class LoanaccordDTO {
  private String flowHeadId=""; //pl202ͷ��Id
  
  private String contractId = ""; // ��ͬ���

  private String borrowerName = ""; // ���������

  private String cardKind = ""; // ֤������

  private String cardKindName = ""; // ��ʾ֤�����Ͷ�Ӧ������

  private String cardNum = ""; // ֤������

  private String loanBankId = ""; // �ſ�����

  private String loanBankName = ""; // �ſ���������

  private BigDecimal loanMoney = new BigDecimal(0.00); // ������

  private String loanTimeLimit = ""; // ��������

  private BigDecimal loanMonthRate = new BigDecimal(0.00);// ÿ������

  private String loanMode = ""; // ���ʽ

  private String loanModeName = ""; // ���ʽ����

  private BigDecimal corpusInterest = new BigDecimal(0.00);// �»���Ϣ
  
  private String  temploanMonthRate ;// ��ʱ��ʾ�õ�ÿ������

  private String loanKouAcc = "";// ���пۿ��˺�(�����˺�)

  private String loanStartDate = "";// ��������

  private String overTime = "";// ��������

  private String loanRepayDay = ""; // ������
  
  private BigDecimal firstLoanMoney = new BigDecimal(0.00); // �״λ�����

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

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }

  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
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

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getLoanRepayDay() {
    return loanRepayDay;
  }

  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOverTime() {
    return overTime;
  }

  public void setOverTime(String overTime) {
    this.overTime = overTime;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

  public String getTemploanMonthRate() {
    return temploanMonthRate;
  }

  public void setTemploanMonthRate(String temploanMonthRate) {
    this.temploanMonthRate = temploanMonthRate;
  }
 
}
