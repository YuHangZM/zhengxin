package org.xpup.hafmis.syscollection.accounthandle.bizcheck.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 2007-07-11
 * 
 * @author �����
 */
public class BizcheckAF extends ActionForm {

  private List list = null;

  private List bizchectotlallist = null;

  private List bankList1 = null;// �鼯����list

  private List operList1 = null;// �Ƶ���list

  private Map bis_Type = new HashMap();

  private Map map = null;

  private String noteNum = "";// Ʊ�ݱ��

  private String docNum = "";// ƾ֤���

  private String orgId = "";// ��λID

  private String orgName = "";// ��λ����

  private String operator = "";// �Ƶ���

  private String collectionBank = "";// �鼯����

  private Integer bizStatus = new Integer(0);// �ɴ�״̬(ҵ��״̬)

  private String startDate = "";// ��ʼ����

  private String endDate = "";// ��ʼ����

  private String bizType = "";// �ɴ�����(ҵ������)

  private String biz_Type = "";// �ɴ�����(ҵ������)

  private int totalCount; // �ܷ�������

  private BigDecimal totalInterest = new BigDecimal(0);// ��Ϣ

  private BigDecimal totalDcitsum = new BigDecimal(0);// �ܷ������

  private String settDate = "";// ��������

  private String type = "1";

  private String ListCount;

  // �ж�ҵ��״̬0������ȷ�Ϻ͸��� 1��ֻ��ȷ�� 2��ֻ�и��ˣ���ҳ������ʾ��ť��
  private String statusType;

  public String getListCount() {
    return ListCount;
  }

  public void setListCount(String listCount) {
    ListCount = listCount;
  }

  public Integer getBizStatus() {
    return bizStatus;
  }

  public void setBizStatus(Integer bizStatus) {
    this.bizStatus = bizStatus;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getCollectionBank() {
    return collectionBank;
  }

  public void setCollectionBank(String collectionBank) {
    this.collectionBank = collectionBank;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
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

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
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

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void reset(Map map, List list, String noteNum, String docNum,
      String orgId, String orgName, String operator, String collectionBank,
      String startDate, String endDate, Integer bizStatus, String bizType,
      Integer totalCount, BigDecimal totalInterest, BigDecimal totalDcitsum) {
    // TODO Auto-generated method stub
    this.list = null;
    this.map = null;
    this.noteNum = "";
    this.docNum = "";
    this.orgId = "";
    this.orgName = "";
    this.operator = "";
    this.collectionBank = "";
    this.startDate = "";
    this.endDate = "";
    this.bizStatus = new Integer(0);
    this.totalDcitsum = new BigDecimal(0);
    this.totalInterest = new BigDecimal(0);
    this.totalCount = 0;
    this.bizType = "";
    this.settDate = "";
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public BigDecimal getTotalDcitsum() {
    return totalDcitsum;
  }

  public void setTotalDcitsum(BigDecimal totalDcitsum) {
    this.totalDcitsum = totalDcitsum;
  }

  public BigDecimal getTotalInterest() {
    return totalInterest;
  }

  public void setTotalInterest(BigDecimal totalInterest) {
    this.totalInterest = totalInterest;
  }

  public String getStatusType() {
    return statusType;
  }

  public void setStatusType(String statusType) {
    this.statusType = statusType;
  }

  public List getBizchectotlallist() {
    return bizchectotlallist;
  }

  public void setBizchectotlallist(List bizchectotlallist) {
    this.bizchectotlallist = bizchectotlallist;
  }

  public List getBankList1() {
    return bankList1;
  }

  public void setBankList1(List bankList1) {
    this.bankList1 = bankList1;
  }

  public List getOperList1() {
    return operList1;
  }

  public void setOperList1(List operList1) {
    this.operList1 = operList1;
  }

  public Map getBis_Type() {
    return bis_Type;
  }

  public void setBis_Type(Map bis_Type) {
    this.bis_Type = bis_Type;
  }

  public String getBiz_Type() {
    return biz_Type;
  }

  public void setBiz_Type(String biz_Type) {
    this.biz_Type = biz_Type;
  }

}
