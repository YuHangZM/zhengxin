package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.form;

import java.util.ArrayList;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author ��Ұ 2007-10-15
 */
public class LoanXieYiAF extends ActionForm {

  private static final long serialVersionUID = 2531807195056023196L;

  List list = null;// ��ʾ�б�

  List printList = null;// ��ӡ�б�

  private String office = "";// �Ƶ���

  private String officename = "";
  
  private String borrowerName = "";// ���������
  
  private String contractId = "";// ��ͬ���

  private List loanBankNameList = new ArrayList();// �ſ�����

  private String loanBankName = null;// �ſ�����

  private String beginBizDate = null;// ��ʼ��������

  private String endBizDate = null;// ��ֹ��������

  private String beginDelDate = null;// ��ʼ��������

  private String endDelDate = null;// ��ֹ��������

  private String count = "0";// ����

  private String isDelete = "";// Э���Ƿ���

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public String getBeginDelDate() {
    return beginDelDate;
  }

  public void setBeginDelDate(String beginDelDate) {
    this.beginDelDate = beginDelDate;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
  }

  public String getEndDelDate() {
    return endDelDate;
  }

  public void setEndDelDate(String endDelDate) {
    this.endDelDate = endDelDate;
  }

  public String getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(String isDelete) {
    this.isDelete = isDelete;
  }

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

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public String getOfficename() {
    return officename;
  }

  public void setOfficename(String officename) {
    this.officename = officename;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

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

}
