package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.dto;

import java.util.List;

//wuht
//�б�����
//��ͬ��ţ�������������������ޣ���Ϣ�ܶ�����ܶ�������ڣ��������ڣ��������ڡ�

public class QueryPayOffRecordsTaListDTO {
  


  private String loanKouAcc = "";//�����ʺ�

  private String contractId =  "";

  private String borrowerName = "";

  private String loanMoney = "";// ������Ԫ����С���㱣��һλ��

  private String loanTimeLimit = "";// �������

  private String loanStartDate = "";// ��������

  private String loanRepayDay =  "";// ����������

  private String loanPayOffDate = "";// ���������ڡ�
  

  private String interest = "";//��Ϣ�ܶ 

  private String corpus = "";//�������ܶ�

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanPayOffDate() {
    return loanPayOffDate;
  }

  public void setLoanPayOffDate(String loanPayOffDate) {
    this.loanPayOffDate = loanPayOffDate;
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getCorpus() {
    return corpus;
  }

  public void setCorpus(String corpus) {
    this.corpus = corpus;
  }

  public String getInterest() {
    return interest;
  }

  public void setInterest(String interest) {
    this.interest = interest;
  }

 
 

}
