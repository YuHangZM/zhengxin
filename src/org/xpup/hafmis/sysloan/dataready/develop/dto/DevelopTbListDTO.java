package org.xpup.hafmis.sysloan.dataready.develop.dto;

/**
 * ��װ�б����ݵ�DTO
 * 
 * @author ���Ʒ�
 */
public class DevelopTbListDTO {

  /** ���� */
  private String id = "";

  /** �����̱�� */
  private String developerId = "";

  /** ���������� */
  private String developerName = "";

  /** Э��ǩ������ */
  private String agreementStartDate = "";

  /** Э�鵽�����ڽ��� */
  private String agreementEndDate = "";

  /** ���´� */
  private String office = "";

  /** ������״̬ */
  private String developerSt = "";

  /** ��ϵ�� */
  private String contactPrsnA = "";

  /** ��ϵ�绰 */
  private String contactTelA = "";

  /** ¥���� */
  private int sumFloor = 0;

  /** ¥���� */
  private int sumFloorNum = 0;

  private String code = "";// ��֯��������

  private String floorName = "";// ¥������

  private String developerPaybankA = "";// ������A

  private String developerPaybankB = "";// ������B

  private String artfclprsn = "";// ���˴���

  private String photoUrl = "";

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getAgreementEndDate() {
    return agreementEndDate;
  }

  public void setAgreementEndDate(String agreementEndDate) {
    this.agreementEndDate = agreementEndDate;
  }

  public String getAgreementStartDate() {
    return agreementStartDate;
  }

  public void setAgreementStartDate(String agreementStartDate) {
    this.agreementStartDate = agreementStartDate;
  }

  public String getContactPrsnA() {
    return contactPrsnA;
  }

  public void setContactPrsnA(String contactPrsnA) {
    this.contactPrsnA = contactPrsnA;
  }

  public String getContactTelA() {
    return contactTelA;
  }

  public void setContactTelA(String contactTelA) {
    this.contactTelA = contactTelA;
  }

  public String getDeveloperId() {
    return developerId;
  }

  public void setDeveloperId(String developerId) {
    this.developerId = developerId;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getDeveloperSt() {
    return developerSt;
  }

  public void setDeveloperSt(String developerSt) {
    this.developerSt = developerSt;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public int getSumFloor() {
    return sumFloor;
  }

  public void setSumFloor(int sumFloor) {
    this.sumFloor = sumFloor;
  }

  public int getSumFloorNum() {
    return sumFloorNum;
  }

  public void setSumFloorNum(int sumFloorNum) {
    this.sumFloorNum = sumFloorNum;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getArtfclprsn() {
    return artfclprsn;
  }

  public void setArtfclprsn(String artfclprsn) {
    this.artfclprsn = artfclprsn;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDeveloperPaybankA() {
    return developerPaybankA;
  }

  public void setDeveloperPaybankA(String developerPaybankA) {
    this.developerPaybankA = developerPaybankA;
  }

  public String getDeveloperPaybankB() {
    return developerPaybankB;
  }

  public void setDeveloperPaybankB(String developerPaybankB) {
    this.developerPaybankB = developerPaybankB;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }
}
