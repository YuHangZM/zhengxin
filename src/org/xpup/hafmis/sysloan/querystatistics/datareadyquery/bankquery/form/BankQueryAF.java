package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionForm;

public class BankQueryAF extends ActionForm {
  /**
   * �����������
   */
  private String loanBankId = "";

  /**
   * �����г�
   */
  private String bankPrisident = "";

  /**
   * ��ϵ��
   */
  private String contactPrsn = "";

  /**
   * ���´�
   */
  private String office = "";

  List list = new ArrayList();

  public String getBankPrisident() {
    return bankPrisident;
  }

  public void setBankPrisident(String bankPrisident) {
    this.bankPrisident = bankPrisident;
  }

  public String getContactPrsn() {
    return contactPrsn;
  }

  public void setContactPrsn(String contactPrsn) {
    this.contactPrsn = contactPrsn;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
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

}
