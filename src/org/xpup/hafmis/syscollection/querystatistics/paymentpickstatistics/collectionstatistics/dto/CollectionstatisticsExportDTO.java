package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto;

import java.math.BigDecimal;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class CollectionstatisticsExportDTO implements ExpDto{
 
  /**
   * ���´� �鼯���� ��λ��� ��λ���� ��λ���� ���ܲ��� ���ڵ��� ���¹鼯 ������� ��λ���� ���˲��� ��λ���� ���ɴ� ���¹鼯 ����
   */
  private static final long serialVersionUID = 0L;
  
  private String officeCode = "";//���´�
  private String collectionBank = "";//�鼯����
  private String orgId = "";//��λ���
  private String orgName = "";// ��λ����
  private String orgCharacter = "";//��λ����
  private String deptInCharge = "";//���ܲ���
  private String startDate = "";//��ʼ����
  private String endDate = "";//��������
  private String region = "";//���ڵ���
  private String lastMonthCollect = "";//���¹鼯
  private String monthPay = "";//�����������
  private String orgAddPay = "";//���µ�λ����
  private String personAddPay = "";//���¸��˲���
  private String orgOverPay = "";//���µ�λ����
  private String chgPay = "";//���µ��ɴ�
  private String thisMonthCollect = "";//���¹鼯
  private String rate = "";//����
  
  
  private String sumlastMonth = "";//�ϼ����¹鼯
  private String sumthisMonth = "";//�ϼƱ���
  private String summonthPay = "";
  private String sumorgAddPay = "";
  private String sumpersonAddPay = "";
  private String sumorgOverPay = "";
  private String sumChgPay = "";
  private String sumRate = "";
  
  
  public String getSumChgPay() {
    return sumChgPay;
  }

  public void setSumChgPay(String sumChgPay) {
    this.sumChgPay = sumChgPay;
  }

  public String getSumlastMonth() {
    return sumlastMonth;
  }

  public void setSumlastMonth(String sumlastMonth) {
    this.sumlastMonth = sumlastMonth;
  }

  public String getSummonthPay() {
    return summonthPay;
  }

  public void setSummonthPay(String summonthPay) {
    this.summonthPay = summonthPay;
  }

  public String getSumorgAddPay() {
    return sumorgAddPay;
  }

  public void setSumorgAddPay(String sumorgAddPay) {
    this.sumorgAddPay = sumorgAddPay;
  }

  public String getSumorgOverPay() {
    return sumorgOverPay;
  }

  public void setSumorgOverPay(String sumorgOverPay) {
    this.sumorgOverPay = sumorgOverPay;
  }

  public String getSumpersonAddPay() {
    return sumpersonAddPay;
  }

  public void setSumpersonAddPay(String sumpersonAddPay) {
    this.sumpersonAddPay = sumpersonAddPay;
  }

  public String getSumRate() {
    return sumRate;
  }

  public void setSumRate(String sumRate) {
    this.sumRate = sumRate;
  }

  public String getSumthisMonth() {
    return sumthisMonth;
  }

  public void setSumthisMonth(String sumthisMonth) {
    this.sumthisMonth = sumthisMonth;
  }

  public String getChgPay() {
    return chgPay;
  }

  public void setChgPay(String chgPay) {
    this.chgPay = chgPay;
  }

  public String getCollectionBank() {
    return collectionBank;
  }

  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }

  public String getDeptInCharge() {
    return deptInCharge;
  }

  public void setDeptInCharge(String deptInCharge) {
    this.deptInCharge = deptInCharge;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getLastMonthCollect() {
    return lastMonthCollect;
  }

  public void setLastMonthCollect(String lastMonthCollect) {
    this.lastMonthCollect = lastMonthCollect;
  }

  public String getMonthPay() {
    return monthPay;
  }

  public void setMonthPay(String monthPay) {
    this.monthPay = monthPay;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getOrgAddPay() {
    return orgAddPay;
  }

  public void setOrgAddPay(String orgAddPay) {
    this.orgAddPay = orgAddPay;
  }

  public String getOrgCharacter() {
    return orgCharacter;
  }

  public void setOrgCharacter(String orgCharacter) {
    this.orgCharacter = orgCharacter;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgOverPay() {
    return orgOverPay;
  }

  public void setOrgOverPay(String orgOverPay) {
    this.orgOverPay = orgOverPay;
  }

  public String getPersonAddPay() {
    return personAddPay;
  }

  public void setPersonAddPay(String personAddPay) {
    this.personAddPay = personAddPay;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getThisMonthCollect() {
    return thisMonthCollect;
  }

  public void setThisMonthCollect(String thisMonthCollect) {
    this.thisMonthCollect = thisMonthCollect;
  }

  public String getInfo(String s) {
    // TODO Auto-generated method stub
    if(s.equals("officeCode"))
      return officeCode;
    if(s.equals("collectionBank"))
        return collectionBank;
    if(s.equals("orgId"))
        return orgId;
    if(s.equals("orgName"))
      return orgName;
    if(s.equals("orgCharacter"))
      return orgCharacter;
    if(s.equals("deptInCharge"))
      return deptInCharge;
    if(s.equals("startDate"))
      return startDate;
    if(s.equals("endDate"))
      return endDate;
    if(s.equals("region"))
      return region;
    if(s.equals("lastMonthCollect"))
      return lastMonthCollect;
    if(s.equals("monthPay"))
      return monthPay;
    if(s.equals("orgAddPay"))
      return orgAddPay;
    if(s.equals("personAddPay"))
      return personAddPay;
    if(s.equals("orgOverPay"))
      return orgOverPay;
    if(s.equals("chgPay"))
      return chgPay;
    if(s.equals("thisMonthCollect"))
      return thisMonthCollect;
    if(s.equals("rate"))
      return rate;
    else
        return null;
   
  }

}
