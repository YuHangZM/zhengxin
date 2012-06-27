package org.xpup.hafmis.syscollection.paymng.paysure.form;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;

/**
 * @author ����� 2007-06-28
 */
public class PaymentAF extends ActionForm {

  private List list = null;

  private Map map = null;

  private Map other_map = null;

  private String id = "";

  private String orgId = "";// ����ID

  private String orgName = "";// ��������

  private String noteNum = "";// Ʊ�ݱ��

  private String docNum = "";// ƾ֤���

//  private BigDecimal payMoney = new BigDecimal(0);// �ɴ���
  
  private String payMoney = "";// �ɴ���

  private String settDate = "";// ҵ������
  private String settDate1 = "";// ҵ������

  private Integer payStatus = new Integer(0);// �ɴ�״̬(ҵ��״̬)

  private String payType = "";// �ɴ�����(ҵ������)

  private BigDecimal sumPayMoney = new BigDecimal(0);// ��ɽ��ϼ�
  
  private String reason = "";//����ԭ��
  
  private BigDecimal balance = new BigDecimal(0);//�������
  
  private BigDecimal money = new BigDecimal(0);//���˽��
  
  private BigDecimal orgAddPaySum = new BigDecimal(0);
  
  private BigDecimal empAddPaySum = new BigDecimal(0);
  
  private BigDecimal orgEmpPaySum = new BigDecimal(0);
  
  private Integer count = new Integer(0);
  
  private BigDecimal AddPayAmount = new BigDecimal(0);

  public BigDecimal getAddPayAmount() {
    return AddPayAmount;
  }

  public void setAddPayAmount(BigDecimal addPayAmount) {
    AddPayAmount = addPayAmount;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public BigDecimal getSumPayMoney() {
    return sumPayMoney;
  }

  public void setSumPayMoney(BigDecimal sumPayMoney) {
    this.sumPayMoney = sumPayMoney;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
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

//  public BigDecimal getPayMoney() {
//    return payMoney;
//  }
//
//  public void setPayMoney(BigDecimal payMoney) {
//    this.payMoney = payMoney;
//  }

  public Integer getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }

  public String getSettDate() {
    return settDate;
  }

  public void setSettDate(String settDate) {
    this.settDate = settDate;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public Map getOther_map() {
    return other_map;
  }

  public void setOther_map(Map other_map) {
    this.other_map = other_map;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void reset(ActionMapping mapping,HttpServletRequest request) {
    // TODO Auto-generated method stub
    
    orgId = "";// ����ID

    orgName = "";// ��������

    noteNum = "";// Ʊ�ݱ��

    docNum = "";// ƾ֤���

    payMoney = "";// �ɴ���

    settDate = "";// ҵ������

    payStatus = null;// �ɴ�״̬(ҵ��״̬)

    payType = null;// �ɴ�����(ҵ������)

    //sumPayMoney = new BigDecimal(0);
    
   // this.reason = "";//����ԭ��
    
   // this.balance = new BigDecimal(0);//�������
    
   // this.money = new BigDecimal(0);//���˽��
  }

  public BigDecimal getEmpAddPaySum() {
    return empAddPaySum;
  }

  public void setEmpAddPaySum(BigDecimal empAddPaySum) {
    this.empAddPaySum = empAddPaySum;
  }

  public BigDecimal getOrgAddPaySum() {
    return orgAddPaySum;
  }

  public void setOrgAddPaySum(BigDecimal orgAddPaySum) {
    this.orgAddPaySum = orgAddPaySum;
  }

  public BigDecimal getOrgEmpPaySum() {
    return orgEmpPaySum;
  }

  public void setOrgEmpPaySum(BigDecimal orgEmpPaySum) {
    this.orgEmpPaySum = orgEmpPaySum;
  }

  public String getPayMoney() {
    return payMoney;
  }

  public void setPayMoney(String payMoney) {
    this.payMoney = payMoney;
  }

  public String getSettDate1() {
    return settDate1;
  }

  public void setSettDate1(String settDate1) {
    this.settDate1 = settDate1;
  }

}
