package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author ��Ұ 2007-10-11
 */
public class AssureModeAF extends ActionForm {

  private static final long serialVersionUID = -1606767891351787425L;

  private String mode = "";// ��ѡ��ť

  private List list = null;

  private List printList = null;// ��ӡlist

  private String id = null;

  private String office = null; // ���´�

  private String loanBankName = null; // �ſ�����

  private String developerName = null; // PL005 ������

  private String buyhouseorgid = null; // PL114 ������ͷ��ID headId

  private String floorid = null;// ¥��ID

  private String floorNum = null; // ¥��

  private String assistantOrgId = null; // ������˾ID

  private String assistantOrgName = null; // ������˾����(Э����λ����)

  private String pledgeCount = null; // ��Ѻ����

  private BigDecimal pledgeValue = new BigDecimal(0.00);// PL121 ��Ѻ���

  private String impawnCount = null; // ��Ѻ����

  private BigDecimal impawnValue = new BigDecimal(0.00);// PL122 ��Ѻ���

  private String pledgeAssurerCount = null; // ��Ѻ+��֤ ����

  private BigDecimal pledgeAssurerValue = new BigDecimal(0.00);// PL121 123

  // ��Ѻ+��֤

  private String impawnAssurerCount = null; // ��Ѻ+��֤ ����

  private BigDecimal impawnAssurerValue = new BigDecimal(0.00);// PL122 123

  // ��Ѻ+��֤

  private String totalCount = null; // �ϼƻ���

  private BigDecimal totalValue = new BigDecimal(0.00);// �ϼƽ��

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

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
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

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImpawnAssurerCount() {
    return impawnAssurerCount;
  }

  public void setImpawnAssurerCount(String impawnAssurerCount) {
    this.impawnAssurerCount = impawnAssurerCount;
  }

  public BigDecimal getImpawnAssurerValue() {
    return impawnAssurerValue;
  }

  public void setImpawnAssurerValue(BigDecimal impawnAssurerValue) {
    this.impawnAssurerValue = impawnAssurerValue;
  }

  public String getImpawnCount() {
    return impawnCount;
  }

  public void setImpawnCount(String impawnCount) {
    this.impawnCount = impawnCount;
  }

  public BigDecimal getImpawnValue() {
    return impawnValue;
  }

  public void setImpawnValue(BigDecimal impawnValue) {
    this.impawnValue = impawnValue;
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

  public String getPledgeAssurerCount() {
    return pledgeAssurerCount;
  }

  public void setPledgeAssurerCount(String pledgeAssurerCount) {
    this.pledgeAssurerCount = pledgeAssurerCount;
  }

  public BigDecimal getPledgeAssurerValue() {
    return pledgeAssurerValue;
  }

  public void setPledgeAssurerValue(BigDecimal pledgeAssurerValue) {
    this.pledgeAssurerValue = pledgeAssurerValue;
  }

  public String getPledgeCount() {
    return pledgeCount;
  }

  public void setPledgeCount(String pledgeCount) {
    this.pledgeCount = pledgeCount;
  }

  public BigDecimal getPledgeValue() {
    return pledgeValue;
  }

  public void setPledgeValue(BigDecimal pledgeValue) {
    this.pledgeValue = pledgeValue;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
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
