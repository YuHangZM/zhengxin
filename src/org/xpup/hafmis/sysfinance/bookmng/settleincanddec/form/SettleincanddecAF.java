package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.form;

import java.util.List;
import org.apache.struts.action.ActionForm;

public class SettleincanddecAF extends ActionForm {
  /*
   * ��ת��Ŀ����
   */
  private String subjectCode = "";

  /*
   * ����ת��Ŀ����
   */
  private String bySubjectCode = "";

  /*
   * �б�List
   */
  List list = null;

  public String getBySubjectCode() {
    return bySubjectCode;
  }

  public void setBySubjectCode(String bySubjectCode) {
    this.bySubjectCode = bySubjectCode;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

}
