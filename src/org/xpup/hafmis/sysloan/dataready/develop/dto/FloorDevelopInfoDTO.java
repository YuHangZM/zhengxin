package org.xpup.hafmis.sysloan.dataready.develop.dto;

import java.math.BigDecimal;

/**
 * ��װ¥���еĿ�������Ϣ
 * 
 * @author fuyunfeng
 */
public class FloorDevelopInfoDTO {
  /**
   * �����̱��
   */
  private String developerId = "";

  /**
   * ����������
   */
  private String developerName = "";

  /**
   * ��������ʹ��Ȩ֤���
   */
  private String landUseId = "";

  /**
   * ��Ʒ���������֤���
   */
  private String salePermis = "";

  /**
   * ��֯��������
   */
  private String code = "";

  /**
   * ���´�
   */
  private String office = "";

  /**
   * Э��ǩ������
   */
  private String agreementStartDate = "";

  /**
   * Э�鵽������
   */
  private String agreementEndDate = "";

  /**
   * ��������A
   */
  private String developerPaybankA = "";

  /**
   * �����ʺ�A
   */
  private String developerPaybankAAcc = "";

  /**
   * ��������B
   */
  private String developerPaybankB = "";

  /**
   * �����ʺ�B
   */
  private String developerPaybankBAcc = "";
  /**
   * �����̵�ַ
   */
  private String developerAddr = "";

  private String fundStatus = "";

  private String artfclprsn = "";

  private BigDecimal registerFundNumber = new BigDecimal("0.00");

  public String getArtfclprsn() {
    return artfclprsn;
  }

  public void setArtfclprsn(String artfclprsn) {
    this.artfclprsn = artfclprsn;
  }

  public BigDecimal getRegisterFundNumber() {
    return registerFundNumber;
  }

  public void setRegisterFundNumber(BigDecimal registerFundNumber) {
    this.registerFundNumber = registerFundNumber;
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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDeveloperAddr() {
    return developerAddr;
  }

  public void setDeveloperAddr(String developerAddr) {
    this.developerAddr = developerAddr;
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

  public String getDeveloperPaybankA() {
    return developerPaybankA;
  }

  public void setDeveloperPaybankA(String developerPaybankA) {
    this.developerPaybankA = developerPaybankA;
  }

  public String getDeveloperPaybankAAcc() {
    return developerPaybankAAcc;
  }

  public void setDeveloperPaybankAAcc(String developerPaybankAAcc) {
    this.developerPaybankAAcc = developerPaybankAAcc;
  }

  public String getLandUseId() {
    return landUseId;
  }

  public void setLandUseId(String landUseId) {
    this.landUseId = landUseId;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getSalePermis() {
    return salePermis;
  }

  public void setSalePermis(String salePermis) {
    this.salePermis = salePermis;
  }

  public String getFundStatus() {
    return fundStatus;
  }

  public void setFundStatus(String fundStatus) {
    this.fundStatus = fundStatus;
  }

  public String getDeveloperPaybankB() {
    return developerPaybankB;
  }

  public void setDeveloperPaybankB(String developerPaybankB) {
    this.developerPaybankB = developerPaybankB;
  }

  public String getDeveloperPaybankBAcc() {
    return developerPaybankBAcc;
  }

  public void setDeveloperPaybankBAcc(String developerPaybankBAcc) {
    this.developerPaybankBAcc = developerPaybankBAcc;
  }
}
