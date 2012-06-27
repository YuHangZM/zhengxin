package org.xpup.hafmis.sysloan.accounthandle.clearaccount.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author jj 2007-10-30
 */
public class ClearAccountBalanceAF extends ActionForm {

  private static final long serialVersionUID = 157830469042818336L;

  // ҵ������
  private String bizType = "";

  private String mydate = "";

  // �ſ�����
  private String loanBankId = "";

  // ��ʼ����
  private String startDate = "";

  // ��������
  private String endDate = "";

  // �Ƶ���
  private String makePerson = "";

  private Map typeMap = new HashMap();

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public Map getTypeMap() {
    return typeMap;
  }

  public void setTypeMap(Map typeMap) {
    this.typeMap = typeMap;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    this.bizType = "";
    this.endDate = "";
    this.loanBankId = "";
    this.makePerson = "";
    this.startDate = "";
  }

  public String getMydate() {
    return mydate;
  }

  public void setMydate(String mydate) {
    this.mydate = mydate;
  }
}
