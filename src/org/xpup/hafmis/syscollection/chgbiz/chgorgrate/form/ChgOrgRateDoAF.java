package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;

public class ChgOrgRateDoAF extends ActionForm {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String type="1";//�������ҳ���ҵ����1��ȷ�����հף���2���޸ģ���ֵ��
  private String salary="";//�湤�ʻ���
  private ChgOrgRate chgOrgRate=new ChgOrgRate();

  public ChgOrgRate getChgOrgRate() {
    return chgOrgRate;
  }

  public void setChgOrgRate(ChgOrgRate chgOrgRate) {
    this.chgOrgRate = chgOrgRate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }
  
}
