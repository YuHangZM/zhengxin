package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto;

public class OverDueinfoQueryImportDTO {
  //���д���
  private String loanBankId = "";
  //�����˺�
  private String loanKouAcc = "";
  //����
  private String borrowerName = "";
  //δ������
  private String nobackCorpus = "";
  //δ����Ϣ
  private String nobackInterest = "";
  //δ����Ϣ
  private String nobackPunishInterest = "";
  //��������
  private String monthsCount = "";
  //�»���Ϣ
  private String corpusInterest = "";
  //�ۿ�����
  private String bizDate = "";

  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public String getMonthsCount() {
    return monthsCount;
  }
  public void setMonthsCount(String monthsCount) {
    this.monthsCount = monthsCount;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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

  public String getNobackCorpus() {
    return nobackCorpus;
  }
  public void setNobackCorpus(String nobackCorpus) {
    this.nobackCorpus = nobackCorpus;
  }
  public String getNobackInterest() {
    return nobackInterest;
  }
  public void setNobackInterest(String nobackInterest) {
    this.nobackInterest = nobackInterest;
  }
 
  public String getNobackPunishInterest() {
    return nobackPunishInterest;
  }
  public void setNobackPunishInterest(String nobackPunishInterest) {
    this.nobackPunishInterest = nobackPunishInterest;
  }  
  
}