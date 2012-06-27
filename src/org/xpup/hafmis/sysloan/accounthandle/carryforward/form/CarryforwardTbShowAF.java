package org.xpup.hafmis.sysloan.accounthandle.carryforward.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CarryforwardTbShowAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = -5346910245042750833L;

  private String loanBankId = ""; // �鿴��Ӧ������id

  private String contractId = ""; // ���ά����ѯ�ĺ�ͬ���

  private String borrowerName = ""; // ���������

  private String loanKouAcc = "";// �����տ��˺�(�����˺�)

  private String carryforwardYear = ""; // ��Ϣ��

  private List list = new ArrayList(); // �б�
  
  public void reset(ActionMapping mapping, ServletRequest request) {
    loanBankId="";
    contractId="";
    borrowerName="";
    loanKouAcc="";
    carryforwardYear="";
    list = new ArrayList();
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCarryforwardYear() {
    return carryforwardYear;
  }

  public void setCarryforwardYear(String carryforwardYear) {
    this.carryforwardYear = carryforwardYear;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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
