package org.xpup.hafmis.sysloan.dataready.develop.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;

/**
 * ��װ����ӿ��������ݵ�Form
 * 
 * @author ���Ʒ�
 */
public class DevelopNewAF extends ActionForm {

  /** ������ʵ�� */
  private Developer developer = new Developer();

  /** �ô˱������ж��Ƿ�Ϊ�޸� */
  private String type_button = "";

  /** �ô˱������ж��Ƿ�Ϊ������ */
  private String type_window = "";

  public Developer getDeveloper() {
    return developer;
  }

  public void setDeveloper(Developer developer) {
    this.developer = developer;
  }

  public String getType_button() {
    return type_button;
  }

  public void setType_button(String type_button) {
    this.type_button = type_button;
  }

  public String getType_window() {
    return type_window;
  }

  public void setType_window(String type_window) {
    this.type_window = type_window;
  }

}
