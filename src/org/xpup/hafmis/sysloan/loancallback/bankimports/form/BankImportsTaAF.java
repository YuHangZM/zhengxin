package org.xpup.hafmis.sysloan.loancallback.bankimports.form;


import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class BankImportsTaAF extends CriterionsAF {
  
  private static final long serialVersionUID = 157830469042818336L;

  // ��ͬ���
  private String contractId = "";
  
  // ����
  private String borrowerName = "";

  // ֤������
  private String cardNum = "";
      
  //�����˺�
  private String loanKouAcc = "";
  
  //�ſ�����
  private String loanBankId ="";
  
  private String loanBankName = "";
  
  //��������
  private String monthYear = "";

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getMonthYear() {
    return monthYear;
  }

  public void setMonthYear(String monthYear) {
    this.monthYear = monthYear;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
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


  public void reset(ActionMapping mapping, ServletRequest request) {
    this.loanBankId="";
    this.borrowerName = "";
    this.cardNum = "";
    this.contractId = "";
    this.loanKouAcc = "";
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

}