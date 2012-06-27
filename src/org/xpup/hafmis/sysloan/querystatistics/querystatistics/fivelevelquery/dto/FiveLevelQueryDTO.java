/**
 * Copy Right Information   : Goldsoft 
 * Project                  : FiveLevelQueryDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.dto;

import java.math.BigDecimal;

public class FiveLevelQueryDTO {

  private String office = ""; // ���´�

  private String loanBankName = ""; // �ſ�����

  private String developerName = ""; // PL005 ������

  private String headId = null; // PL114 ������ͷ��ID

  private String floorId = ""; // ¥��

  private String assistantOrgId = null; // ������˾ID

  private String assistantOrgName = "";// ������˾(Э����λ����)

  private String normalCount = null; // ��������

  private BigDecimal normalValue = new BigDecimal(0.00);// PL121 �������

  private String attentionCount = null; // ��ע����

  private BigDecimal attentionValue = new BigDecimal(0.00);// PL122 ��ע���

  private String secondaryCount = null; // �μ�����

  private BigDecimal secondaryValue = new BigDecimal(0.00);// �μ�PL121 123

  private String shadinessCount = null; // ���ɻ���

  private BigDecimal shadinessValue = new BigDecimal(0.00);// ����PL122 123

  private String damnifyCount = null; // ��ʧ����

  private BigDecimal damnifyValue = new BigDecimal(0.00);// ��ʧPL122 123

  private String totalCount = null; // �ϼƻ���

  private BigDecimal totalValue = new BigDecimal(0.00);// �ϼƽ��

  private String badRateCount = "0"; // �����ʻ���

  private BigDecimal badRateValue = new BigDecimal(0.00);// �����ʽ��

  private int loanRepayDay = 0; // ������

  private String loanStartDate = null; // ������

  private String loanTimeLimit = null; // ������

  private String overplusLimite = null; // ������

  private BigDecimal overplusLoanMoney = new BigDecimal(0.00);// �������

  public int getLoanRepayDay() {
    return loanRepayDay;
  }

  public void setLoanRepayDay(int loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOverplusLimite() {
    return overplusLimite;
  }

  public void setOverplusLimite(String overplusLimite) {
    this.overplusLimite = overplusLimite;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

  public String getAssistantOrgId() {
    return assistantOrgId;
  }

  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }

  public String getAssistantOrgName() {
    return assistantOrgName;
  }

  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }

  public String getAttentionCount() {
    return attentionCount;
  }

  public void setAttentionCount(String attentionCount) {
    this.attentionCount = attentionCount;
  }

  public BigDecimal getAttentionValue() {
    return attentionValue;
  }

  public void setAttentionValue(BigDecimal attentionValue) {
    this.attentionValue = attentionValue;
  }

  public String getBadRateCount() {
    return badRateCount;
  }

  public void setBadRateCount(String badRateCount) {
    this.badRateCount = badRateCount;
  }

  public BigDecimal getBadRateValue() {
    return badRateValue;
  }

  public void setBadRateValue(BigDecimal badRateValue) {
    this.badRateValue = badRateValue;
  }

  public String getDamnifyCount() {
    return damnifyCount;
  }

  public void setDamnifyCount(String damnifyCount) {
    this.damnifyCount = damnifyCount;
  }

  public BigDecimal getDamnifyValue() {
    return damnifyValue;
  }

  public void setDamnifyValue(BigDecimal damnifyValue) {
    this.damnifyValue = damnifyValue;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getNormalCount() {
    return normalCount;
  }

  public void setNormalCount(String normalCount) {
    this.normalCount = normalCount;
  }

  public BigDecimal getNormalValue() {
    return normalValue;
  }

  public void setNormalValue(BigDecimal normalValue) {
    this.normalValue = normalValue;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getSecondaryCount() {
    return secondaryCount;
  }

  public void setSecondaryCount(String secondaryCount) {
    this.secondaryCount = secondaryCount;
  }

  public BigDecimal getSecondaryValue() {
    return secondaryValue;
  }

  public void setSecondaryValue(BigDecimal secondaryValue) {
    this.secondaryValue = secondaryValue;
  }

  public String getShadinessCount() {
    return shadinessCount;
  }

  public void setShadinessCount(String shadinessCount) {
    this.shadinessCount = shadinessCount;
  }

  public BigDecimal getShadinessValue() {
    return shadinessValue;
  }

  public void setShadinessValue(BigDecimal shadinessValue) {
    this.shadinessValue = shadinessValue;
  }

  public String getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(String totalCount) {
    this.totalCount = totalCount;
  }

  public BigDecimal getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(BigDecimal totalValue) {
    this.totalValue = totalValue;
  }

}
