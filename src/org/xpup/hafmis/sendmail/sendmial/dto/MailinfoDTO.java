package org.xpup.hafmis.sendmail.sendmial.dto;

public class MailinfoDTO {
  
  private String mailId;  //����ʹ�õ���Ϣid
  
  private String addresser;// �������ʻ�
  
  private String addresserMail; //����������

  private String addresserPassword;// �������ʻ�����

  private String mailBoxType;// ��������������

  private String addresseeA;// �ռ�������A

  private String addresseeB;// �ռ�������B

  private String subjectName;// ��������

  private String isStart;// ����״̬

  private String operator;// ����Ա

  private String insertIp;// ����Աʹ�õ�ip

  private String insertDate;// ������ʱ��

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

  public String getInsertDate() {
    return insertDate;
  }

  public void setInsertDate(String insertDate) {
    this.insertDate = insertDate;
  }

  public String getInsertIp() {
    return insertIp;
  }

  public void setInsertIp(String insertIp) {
    this.insertIp = insertIp;
  }

  public String getIsStart() {
    return isStart;
  }

  public void setIsStart(String isStart) {
    this.isStart = isStart;
  }

  public String getMailBoxType() {
    return mailBoxType;
  }

  public void setMailBoxType(String mailBoxType) {
    this.mailBoxType = mailBoxType;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
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
