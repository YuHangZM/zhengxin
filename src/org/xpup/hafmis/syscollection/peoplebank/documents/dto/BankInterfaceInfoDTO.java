package org.xpup.hafmis.syscollection.peoplebank.documents.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : ��װ�����нӿڲ�ѯ��Ϣ��DTO Goldsoft Project :
 * BankInterfaceInfoDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.2.14
 */
public class BankInterfaceInfoDTO {
  /** ʶ���� */
  private String identifyCode="";

  /** �����˺� */
  private String accounts="";

  /** �����ص� */
  private String happenLocus="";

  /** ���ݷ������� */
  private String organization="";

  /** ְ������ */
  private String empName="";

  /** ���֤�� */
  private String cardNum="";

  /** ��λ���� */
  private String orgName="";

  /** ��λ���� */
  private String character="";

  /** �������� YYYYMMDD */
  private String openDate="";

  /** �������� YYYYMM */
  private String payMonth="";

  /** �������� YYYYMM */
  private String payOverMonth="";

  /** �ɴ�״̬ */
  private String payStatus="";

  /** ����ɴ����� YYYYMMDD */
  private String lastPayMonth="";

  /** �ɶ� */
  private String pay="";

  public String getAccounts() {
    return accounts;
  }

  public void setAccounts(String accounts) {
    this.accounts = accounts;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getIdentifyCode() {
    return identifyCode;
  }

  public void setIdentifyCode(String identifyCode) {
    this.identifyCode = identifyCode;
  }

  public String getLastPayMonth() {
    return lastPayMonth;
  }

  public void setLastPayMonth(String lastPayMonth) {
    this.lastPayMonth = lastPayMonth;
  }

  public String getOpenDate() {
    return openDate;
  }

  public void setOpenDate(String openDate) {
    this.openDate = openDate;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getPayMonth() {
    return payMonth;
  }

  public void setPayMonth(String payMonth) {
    this.payMonth = payMonth;
  }

  public String getPayOverMonth() {
    return payOverMonth;
  }

  public void setPayOverMonth(String payOverMonth) {
    this.payOverMonth = payOverMonth;
  }

  public String getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public String getHappenLocus() {
    return happenLocus;
  }

  public void setHappenLocus(String happenLocus) {
    this.happenLocus = happenLocus;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getPay() {
    return pay;
  }

  public void setPay(String pay) {
    this.pay = pay;
  }
}
