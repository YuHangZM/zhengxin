package org.xpup.hafmis.syscollection.paymng.orgaddpay.form;

import org.apache.struts.action.ActionForm;

public class OrgaddpayTbPickupdataWindowAF extends ActionForm {
  private static final long serialVersionUID = 0L;

  private String addpayDateSt = "";// ���ɿ�ʼ����

  private String addpayDateEnd = "";// ���ɽ�������

  private String orgId = "";// ��λ���

  private String noteNum = "";// ֮ǰƱ�ݱ��

  private String noteNumB = "";// ֮�������Ʊ�ݱ��

  private String orgName = "";// ��λ����

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getAddpayDateEnd() {
    return addpayDateEnd;
  }

  public void setAddpayDateEnd(String addpayDateEnd) {
    this.addpayDateEnd = addpayDateEnd;
  }

  public String getAddpayDateSt() {
    return addpayDateSt;
  }

  public void setAddpayDateSt(String addpayDateSt) {
    this.addpayDateSt = addpayDateSt;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getNoteNumB() {
    return noteNumB;
  }

  public void setNoteNumB(String noteNumB) {
    this.noteNumB = noteNumB;
  }
}
