package org.xpup.hafmis.sysloan.accounthandle.clearaccount.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class PrincipalDayAccAF extends ActionForm {
  private static final long serialVersionUID = 8426481028295934393L;

  private List list = new ArrayList(); // ��ʾ�б�

  private String searchDate = ""; // ��ѯ����

  private String loanBankName = ""; // �ſ�����

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getSearchDate() {
    return searchDate;
  }

  public void setSearchDate(String searchDate) {
    this.searchDate = searchDate;
  }
}
