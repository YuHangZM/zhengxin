package org.xpup.hafmis.sendmail.sendmial.form;

import org.apache.struts.action.ActionForm;

public class MailinfoAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = 5159742678721582921L;

  private String addresser;// �������ʻ�

  private String addresserMail; //����������
  
  private String addresserPassword;// �������ʻ�����

  private String mailBoxType;// ��������������

  private String addresseeA;// �ռ�������A

  private String addresseeB;// �ռ�������B

  private String subjectName;// ��������
  
  private String mailId;  //����ʹ�õ���Ϣid

  public String getAddresseeA() {
    return addresseeA;
  }

  public void setAddresseeA(String addresseeA) {
    this.addresseeA = addresseeA;
  }

  public String getAddresseeB() {
    return addresseeB;
  }

  public void setAddresseeB(String addresseeB) {
    this.addresseeB = addresseeB;
  }

  public String getAddresser() {
    return addresser;
  }

  public void setAddresser(String addresser) {
    this.addresser = addresser;
  }

  public String getAddresserPassword() {
    return addresserPassword;
  }

  public void setAddresserPassword(String addresserPassword) {
    this.addresserPassword = addresserPassword;
  }

  public String getMailBoxType() {
    return mailBoxType;
  }

  public void setMailBoxType(String mailBoxType) {
    this.mailBoxType = mailBoxType;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getMailId() {
    return mailId;
  }

  public void setMailId(String mailId) {
    this.mailId = mailId;
  }

  public String getAddresserMail() {
    return addresserMail;
  }

  public void setAddresserMail(String addresserMail) {
    this.addresserMail = addresserMail;
  }
}
