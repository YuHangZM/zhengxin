package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.dto;

/**
 * @author ��Ұ 2007-10-11
 */
public class AssureModeDTO {

  private String office = null; // ���´�

  private String loanBankName = null; // �ſ�����

  private String developerName = null; // PL005 ������

  private String headId = null; // PL114 ������ͷ��ID

  private String floorNum = null; // ¥��

  private String assistantOrgId = null; // ������˾ID

  private String assistantOrgName = null;// ������˾(Э����λ����)

  private String pledgeCount = null; // ��Ѻ����

  private String pledgeValue = null;// PL121 ��Ѻ���

  private String impawnCount = null; // ��Ѻ����

  private String impawnValue = null;// PL122 ��Ѻ���

  private String pledgeAssurerCount = null; // ��Ѻ+��֤ ����

  private String pledgeAssurerValue = null;// ��Ѻ+��֤

  private String impawnAssurerCount = null; // ��Ѻ+��֤ ����

  private String impawnAssurerValue = null;// ��Ѻ+��֤

  private String totalCount = null; // �ϼƻ���

  private String totalValue = null;// �ϼƽ��

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

  public String getFloorNum() {
    return floorNum;
  }

  public String getDeveloperName() {
    return developerName;
  }

  public void setDeveloperName(String developerName) {
    this.developerName = developerName;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getImpawnAssurerCount() {
    return impawnAssurerCount;
  }

  public void setImpawnAssurerCount(String impawnAssurerCount) {
    this.impawnAssurerCount = impawnAssurerCount;
  }

  public String getImpawnAssurerValue() {
    return impawnAssurerValue;
  }

  public void setImpawnAssurerValue(String impawnAssurerValue) {
    this.impawnAssurerValue = impawnAssurerValue;
  }

  public String getImpawnCount() {
    return impawnCount;
  }

  public void setImpawnCount(String impawnCount) {
    this.impawnCount = impawnCount;
  }

  public String getImpawnValue() {
    return impawnValue;
  }

  public void setImpawnValue(String impawnValue) {
    this.impawnValue = impawnValue;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getPledgeAssurerCount() {
    return pledgeAssurerCount;
  }

  public void setPledgeAssurerCount(String pledgeAssurerCount) {
    this.pledgeAssurerCount = pledgeAssurerCount;
  }

  public String getPledgeAssurerValue() {
    return pledgeAssurerValue;
  }

  public void setPledgeAssurerValue(String pledgeAssurerValue) {
    this.pledgeAssurerValue = pledgeAssurerValue;
  }

  public String getPledgeCount() {
    return pledgeCount;
  }

  public void setPledgeCount(String pledgeCount) {
    this.pledgeCount = pledgeCount;
  }

  public String getPledgeValue() {
    return pledgeValue;
  }

  public void setPledgeValue(String pledgeValue) {
    this.pledgeValue = pledgeValue;
  }

  public String getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(String totalCount) {
    this.totalCount = totalCount;
  }

  public String getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(String totalValue) {
    this.totalValue = totalValue;
  }

}
