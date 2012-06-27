package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgorgrate.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChgorgrateAF extends ActionForm{

  private List collBankList=new ArrayList();
  private List officeList=new ArrayList();
  private List list = null;
  private List alllist = null;
  private Map map = null;
  private Map officeMap = null;
  private Map bankMap = null;
  private String officeCode = "";//���´�
  private String collectionBank = "";//�鼯����
  private String orgId = "";//��λ���
  private String orgName = "";// ��λ����
  private BigDecimal preOrgRate = new BigDecimal(0.00);//����ǰ��λ����
  private BigDecimal preEmpRate = new BigDecimal(0.00);//����ǰְ������
  private BigDecimal orgRate = new BigDecimal(0.00);//������λ����
  private BigDecimal empRate = new BigDecimal(0.00);//������ְ������
  private BigDecimal sumPreOrgEmp = new BigDecimal(0.00);//����ǰӦ���ܶ�
  private BigDecimal sumOrgEmp = new BigDecimal(0.00);//������Ӧ���ܶ�
  private String chgMonth = "";//��������
  private String bizDate = "";//��������
  private Integer chgStatus = new Integer(0);//״̬
  
  private String chgMonthStart = "";//��form�������¿�ʼ
  private String chgMonthEnd = "";//��form�������½���
  private String chgDateStart = "";//��form�������ڿ�ʼ
  private String chgDateEnd = "";//��form�������ڽ���
  
  private Integer orgCount = new Integer(0);//������λ��
  private BigDecimal sumPre = new BigDecimal(0.00);//����ǰӦ���ܶ�
  private BigDecimal sumSith = new BigDecimal(0.00);//������Ӧ���ܶ�
  private Integer counts = new Integer(0);//�������
 
  public Integer getCounts() {
    return counts;
  }
  public void setCounts(Integer counts) {
    this.counts = counts;
  }
  public Integer getOrgCount() {
    return orgCount;
  }
  public void setOrgCount(Integer orgCount) {
    this.orgCount = orgCount;
  }
  public BigDecimal getSumPre() {
    return sumPre;
  }
  public void setSumPre(BigDecimal sumPre) {
    this.sumPre = sumPre;
  }
  public BigDecimal getSumSith() {
    return sumSith;
  }
  public void setSumSith(BigDecimal sumSith) {
    this.sumSith = sumSith;
  }
  public String getChgDateEnd() {
    return chgDateEnd;
  }
  public void setChgDateEnd(String chgDateEnd) {
    this.chgDateEnd = chgDateEnd;
  }
  public String getChgDateStart() {
    return chgDateStart;
  }
  public void setChgDateStart(String chgDateStart) {
    this.chgDateStart = chgDateStart;
  }
  public String getChgMonthEnd() {
    return chgMonthEnd;
  }
  public void setChgMonthEnd(String chgMonthEnd) {
    this.chgMonthEnd = chgMonthEnd;
  }
  public String getChgMonthStart() {
    return chgMonthStart;
  }
  public void setChgMonthStart(String chgMonthStart) {
    this.chgMonthStart = chgMonthStart;
  }
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getChgMonth() {
    return chgMonth;
  }
  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }
  public Integer getChgStatus() {
    return chgStatus;
  }
  public void setChgStatus(Integer chgStatus) {
    this.chgStatus = chgStatus;
  }
  public String getCollectionBank() {
    return collectionBank;
  }
  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }
  public BigDecimal getEmpRate() {
    return empRate;
  }
  public void setEmpRate(BigDecimal empRate) {
    this.empRate = empRate;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }
  public String getOfficeCode() {
    return officeCode;
  }
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
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
  public BigDecimal getOrgRate() {
    return orgRate;
  }
  public void setOrgRate(BigDecimal orgRate) {
    this.orgRate = orgRate;
  }
  public BigDecimal getPreEmpRate() {
    return preEmpRate;
  }
  public void setPreEmpRate(BigDecimal preEmpRate) {
    this.preEmpRate = preEmpRate;
  }
  public BigDecimal getPreOrgRate() {
    return preOrgRate;
  }
  public void setPreOrgRate(BigDecimal preOrgRate) {
    this.preOrgRate = preOrgRate;
  }
  public BigDecimal getSumOrgEmp() {
    return sumOrgEmp;
  }
  public void setSumOrgEmp(BigDecimal sumOrgEmp) {
    this.sumOrgEmp = sumOrgEmp;
  }
  public BigDecimal getSumPreOrgEmp() {
    return sumPreOrgEmp;
  }
  public void setSumPreOrgEmp(BigDecimal sumPreOrgEmp) {
    this.sumPreOrgEmp = sumPreOrgEmp;
  }
  public List getAlllist() {
    return alllist;
  }
  public void setAlllist(List alllist) {
    this.alllist = alllist;
  }
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    super.reset(mapping, request);
    officeCode = "";//���´�
    collectionBank = "";//�鼯����
    orgId = "";//��λ���
    orgName = "";// ��λ����
   // this.chgMonth = "";//��������
   // this.bizDate = "";//��������
    chgStatus = new Integer(0);//״̬
    
    chgMonthStart = "";//��form�������¿�ʼ
    chgMonthEnd = "";//��form�������½���
    chgDateStart = "";//��form�������ڿ�ʼ
    chgDateEnd = "";//��form�������ڽ���
    
  }
  public Map getBankMap() {
    return bankMap;
  }
  public void setBankMap(Map bankMap) {
    this.bankMap = bankMap;
  }
  public Map getOfficeMap() {
    return officeMap;
  }
  public void setOfficeMap(Map officeMap) {
    this.officeMap = officeMap;
  }
  public List getCollBankList() {
    return collBankList;
  }
  public void setCollBankList(List collBankList) {
    this.collBankList = collBankList;
  }
  public List getOfficeList() {
    return officeList;
  }
  public void setOfficeList(List officeList) {
    this.officeList = officeList;
  }
  
  
}
