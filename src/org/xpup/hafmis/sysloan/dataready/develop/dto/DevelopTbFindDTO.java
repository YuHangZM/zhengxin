package org.xpup.hafmis.sysloan.dataready.develop.dto;

/**
 * ��װ������ά��ҳ��ѯ������DTO
 * 
 * @author ���Ʒ�
 */
public class DevelopTbFindDTO {

  /** �����̱�� */
  private String developerId = "";

  /** ���������� */
  private String developerName = "";

  /** Э��ǩ�����ڿ�ʼ */
  private String startAgreementStartDate = "";

  /** Э��ǩ�����ڽ��� */
  private String endAgreementStartDate = "";

  /** Э�鵽�����ڿ�ʼ */
  private String startAgreementEndDate = "";

  /** Э�鵽�����ڽ��� */
  private String endAgreementEndDate = "";

  /** ���´� */
  private String office = "";

  /** ������״̬ */
  private String developerSt = "";

  /** ��ϵ�� */
  private String contactPrsnA = "";

  /** ��֯�������� */
  private String code = "";

  /** ���˴��� */
  private String artfclprsn = "";

  /** ������ */
  private String paybank = "";

  /** �������˺� */
  private String paybankacc = "";

  private String developerPaybankA = "";// ������A

  private String developerPaybankB = "";// ������B
  
  private String buyhouseorgid; // ���������ƣ�������ѯ��

  private String floorName; // ¥������

  private String floorNum; // ¥��
  
  private String isSpecial;

  public String getIsSpecial() {
    return isSpecial;
  }

  public void setIsSpecial(String isSpecial) {
    this.isSpecial = isSpecial;
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

  public String getEndAgreementEndDate() {
    return endAgreementEndDate;
  }

  public void setEndAgreementEndDate(String endAgreementEndDate) {
    this.endAgreementEndDate = endAgreementEndDate;
  }

  public String getEndAgreementStartDate() {
    return endAgreementStartDate;
  }

  public void setEndAgreementStartDate(String endAgreementStartDate) {
    this.endAgreementStartDate = endAgreementStartDate;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getStartAgreementEndDate() {
    return startAgreementEndDate;
  }

  public void setStartAgreementEndDate(String startAgreementEndDate) {
    this.startAgreementEndDate = startAgreementEndDate;
  }

  public String getStartAgreementStartDate() {
    return startAgreementStartDate;
  }

  public void setStartAgreementStartDate(String startAgreementStartDate) {
    this.startAgreementStartDate = startAgreementStartDate;
  }

  public String getContactPrsnA() {
    return contactPrsnA;
  }

  public void setContactPrsnA(String contactPrsnA) {
    this.contactPrsnA = contactPrsnA;
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

  public String getPaybank() {
    return paybank;
  }

  public void setPaybank(String paybank) {
    this.paybank = paybank;
  }

  public String getPaybankacc() {
    return paybankacc;
  }

  public void setPaybankacc(String paybankacc) {
    this.paybankacc = paybankacc;
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

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }
}
