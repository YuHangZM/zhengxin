package org.xpup.hafmis.syscollection.chgbiz.chgperson.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Copy Right Information : �Զ�����������ActionForm Goldsoft Project :
 * AutoChangePopAF
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.3.18
 */
public class AutoChangePopAF extends ActionForm {
  
  /**
   * �б���ʾ������
   */
  private List list;

  private String empId = "";
  
  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
  
}
