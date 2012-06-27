package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.dto;



public class OrgBaseInfoDTO {
  /** ��λ��� */
  private String id;
  /** ��λ���� */
  private String orgName;
  /** ���´� */
  private String officecode;
  /** �鼯���� */
  private String collectionBankId;

  /** ��λ�������� */
  private String character = "";
  /** ���˽������� */
  private String deptInCharge = "";
  /** �������� */
  private String paybankName = "";
  /** ��λ���� */
  private String transactorName = ""; 
  private String transactorTel = "";
  /** ���˽������� */
  private String transactorMobile = "";
  /** �������� */
  private String openDate = "";
  /** ��λ���� */
  private String openStatus = "";
  
  private String orgCount="";
  public String getCharacter() {
    return character;
  }
  public void setCharacter(String character) {
    this.character = character;
  }
  public String getCollectionBankId() {
    return collectionBankId;
  }
  public void setCollectionBankId(String collectionBankId) {
    this.collectionBankId = collectionBankId;
  }
  public String getDeptInCharge() {
    return deptInCharge;
  }
  public void setDeptInCharge(String deptInCharge) {
    this.deptInCharge = deptInCharge;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getOfficecode() {
    return officecode;
  }
  public void setOfficecode(String officecode) {
    this.officecode = officecode;
  }
  public String getOpenDate() {
    return openDate;
  }
  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }
  public String getOpenStatus() {
    return openStatus;
  }
  public void setOpenStatus(String openStatus) {
    this.openStatus = openStatus;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getPaybankName() {
    return paybankName;
  }
  public void setPaybankName(String paybankName) {
    this.paybankName = paybankName;
  }
  public String getTransactorMobile() {
    return transactorMobile;
  }
  public void setTransactorMobile(String transactorMobile) {
    this.transactorMobile = transactorMobile;
  }
  public String getTransactorName() {
    return transactorName;
  }
  public void setTransactorName(String transactorName) {
    this.transactorName = transactorName;
  }
  public String getTransactorTel() {
    return transactorTel;
  }
  public void setTransactorTel(String transactorTel) {
    this.transactorTel = transactorTel;
  }
  public String getOrgCount() {
    return orgCount;
  }
  public void setOrgCount(String orgCount) {
    this.orgCount = orgCount;
  }
}
