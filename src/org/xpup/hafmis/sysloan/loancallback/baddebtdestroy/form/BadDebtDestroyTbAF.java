package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class BadDebtDestroyTbAF extends CriterionsAF {
  
  private static final long serialVersionUID = 157830469042818336L;

  // ��ͬ���
  private String contractId = "";
  
  // ����
  private String borrowerName = "";

  // ֤������
  private String cardNum = "";
  
  //�ſ�����
  private String loanBankId = "";
  
  //ҵ��״̬
  private String status = "";
  
  //�����˺�
  private String loanKouAcc = "";
  
  //ƾ֤���
  private String docNum = "";
      
  private Map statusMap = new HashMap();
    
  public Map getStatusMap() {
    return statusMap;
  }

  public void setStatusMap(Map statusMap) {
    this.statusMap = statusMap;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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
    borrowerName = "";
    cardNum = "";
    contractId = "";
    loanBankId = "";
    status ="";
    docNum = "";
    loanKouAcc = "";
  }

}