/**
 * Copy Right Information   : Goldsoft 
 * Project                  : FiveLevelQueryAF
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class FiveLevelQueryAF extends ActionForm {

  private static final long serialVersionUID = -8833963490850405189L;

  private String mode = "";// ��ѡ��ť

  private List list = null;

  private List printList = null;// ��ӡlist

  private String id = null;

  private String office = ""; // ���´�

  private String loanBankName = ""; // �ſ�����

  private String developerName = ""; // PL005 ������

  private String buyhouseorgid = null; // PL114 ������ͷ��ID headId

  private String floorid = null;// ¥��ID

  private String floorId = ""; // ¥��

  private String assistantOrgId = null; // ������˾ID

  private String assistantOrgName = ""; // ������˾����(Э����λ����)

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

  private String badRateCount = null; // �����ʻ���

  private BigDecimal badRateValue = new BigDecimal(0.00);// �����ʽ��

  private List officeList = new ArrayList();

  private List loanBankNameList = new ArrayList();

  private List assistantOrgNameList = new ArrayList();

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

  public List getAssistantOrgNameList() {
    return assistantOrgNameList;
  }

  public void setAssistantOrgNameList(List assistantOrgNameList) {
    this.assistantOrgNameList = assistantOrgNameList;
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

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
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

  public String getFloorid() {
    return floorid;
  }

  public void setFloorid(String floorid) {
    this.floorid = floorid;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
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

  public List getOfficeList() {
    return officeList;
  }

  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
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
