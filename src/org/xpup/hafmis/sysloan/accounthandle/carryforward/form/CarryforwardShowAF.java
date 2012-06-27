package org.xpup.hafmis.sysloan.accounthandle.carryforward.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;

public class CarryforwardShowAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = -5346910245042750833L;

  private String loanBankId = ""; // �鿴��Ӧ������id
  
  private String loanBankIdf =""; // ��������ʹ�õ�����id

  private String contractId = ""; // ���ά����ѯ�ĺ�ͬ���

  private String borrowerName = ""; // ���������

  private String loanKouAcc = "";// �����տ��˺�(�����˺�)

  private String carryforwardYear = ""; // ��Ϣ��

  private List list = new ArrayList(); // �б�
  
  private String pl111id="";       //Ҫ��ת�ĺ�ͬ���
  
  public void reset(ActionMapping mapping, ServletRequest request) {
    loanBankId="";
    contractId="";
    borrowerName="";
    loanKouAcc="";
    carryforwardYear="";
    list = new ArrayList();
    pl111id=""; 
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

  public String getPl111id() {
    return pl111id;
  }

  public void setPl111id(String pl111id) {
    this.pl111id = pl111id;
  }

  public String getLoanBankIdf() {
    return loanBankIdf;
  }

  public void setLoanBankIdf(String loanBankIdf) {
    this.loanBankIdf = loanBankIdf;
  }
}
