package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class DepositCheckAccAF extends ActionForm {
  /**
   * ��ѯ����
   */
  private String settDateSt="";//�������ڿ�ʼ
  private String settDateEnd="";//�������ڽ���
  private String subjectCode="";//��Ŀ
  /**
   * �б�
   */
  private List bankDayClearList=new ArrayList();//�����ռ����б�
  private List bankCheckAccList=new ArrayList();//���ж��˵��б�
  public void clear(){
    this.settDateSt="";
    this.settDateEnd="";
    this.subjectCode="";
  }
  public List getBankCheckAccList() {
    return bankCheckAccList;
  }
  public void setBankCheckAccList(List bankCheckAccList) {
    this.bankCheckAccList = bankCheckAccList;
  }
  public List getBankDayClearList() {
    return bankDayClearList;
  }
  public void setBankDayClearList(List bankDayClearList) {
    this.bankDayClearList = bankDayClearList;
  }
  public String getSettDateEnd() {
    return settDateEnd;
  }
  public void setSettDateEnd(String settDateEnd) {
    this.settDateEnd = settDateEnd;
  }
  public String getSettDateSt() {
    return settDateSt;
  }
  public void setSettDateSt(String settDateSt) {
    this.settDateSt = settDateSt;
  }
  public String getSubjectCode() {
    return subjectCode;
  }
  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }
}
