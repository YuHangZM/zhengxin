package org.xpup.hafmis.sysloan.common.biz.contractpop.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class ContractpopAF extends CriterionsAF{

  private String contractId = ""; //��ͬID
  private String borrowerName = "";  //BORROWER_NAME ���������
  private String cardNum = "";  //֤������
  private String empId = ""; //ְ��ID
  
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
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    contractId = "";
    borrowerName = "";
    cardNum = "";
    empId = "";
  }
}
