package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto;

public class AssistantOrgQueryDTO {
  private Integer id = null;// Э����λ���

  private String assistantOrgName = "";// Э����λ����

  private String assistantOrgAddr = "";// Э����λ��ַ

  private String arear = "";// ��������

  private String paybank = "";// ��������

  private String contactPrsn = "";// ��ϵ��

  private String contactTel = "";// ��ϵ�绰

  private String assistantOrgType = "";// Э����λ����
  
  private String photoUrl = "";

  public String getAssistantOrgType() {
    return assistantOrgType;
  }

  public void setAssistantOrgType(String assistantOrgType) {
    this.assistantOrgType = assistantOrgType;
  }

  public String getArear() {
    return arear;
  }

  public void setArear(String arear) {
    this.arear = arear;
  }

  public String getAssistantOrgAddr() {
    return assistantOrgAddr;
  }

  public void setAssistantOrgAddr(String assistantOrgAddr) {
    this.assistantOrgAddr = assistantOrgAddr;
  }

  public String getAssistantOrgName() {
    return assistantOrgName;
  }

  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }

  public String getContactPrsn() {
    return contactPrsn;
  }

  public void setContactPrsn(String contactPrsn) {
    this.contactPrsn = contactPrsn;
  }

  public String getContactTel() {
    return contactTel;
  }

  public void setContactTel(String contactTel) {
    this.contactTel = contactTel;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPaybank() {
    return paybank;
  }

  public void setPaybank(String paybank) {
    this.paybank = paybank;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

}
