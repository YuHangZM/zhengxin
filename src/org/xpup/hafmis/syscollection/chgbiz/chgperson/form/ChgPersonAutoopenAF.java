package org.xpup.hafmis.syscollection.chgbiz.chgperson.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Copy Right Information :�����Զ����ⰴť������Ϊ������󣬵������ڣ���ʾ�õ�λ״̬Ϊת�����ְ�������Խ���ѡ�񣨿�ȫѡ����ѡ�����ȷ���󣬽�ѡ��ְ�����뵽�������У��������Ϊ���⡣
 * AutoChangePopAF
 * 
 * @Version : v1.0
 * @author : �����
 * @Date : 2008.6.18
 */
public class ChgPersonAutoopenAF extends ActionForm {
  
  /**
   * �б���ʾ������
   */
  private List list;
  
  private List listAll;

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

  public List getListAll() {
    return listAll;
  }

  public void setListAll(List listAll) {
    this.listAll = listAll;
  }
  
}
