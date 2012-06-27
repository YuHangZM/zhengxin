package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndorsecontractTdDTO {
  private List list = new ArrayList();
  private Map cardMap = new HashMap();//���֤������map
  private Map sexMap = new HashMap();//�Ա�������map
  
  private String id = "";//
  private String contractId = "";//��ͬID
  private String debitter = "";//��������� PL110 
  private String empId = "";//ְ�����
  private String empName = "";//ְ������
  private String cardKind = "";//֤������
  private String cardNum = "";//֤������
  private String sex = "";//�Ա�
  private String birthday = "";//��������
  private String salary = "";//�¹��ʶ�
  private String monthPay = "";//�½ɴ��
  private String balance = "";//�˻����
  private String empSt = "";//�˻�״̬
  private String tel = "";//�̶��绰
  private String mobile = "";//�ж��绰
  private String homeTel = "";//��ͥ�绰
  private String homeAddr = "";//��ͥסַ
  private String homeMai = "";//��ͥ�ʱ�
  private String orgId = "";//��λ���
  private String orgName = "";//��λ����
  private String orgAddr = "";//��λ��ַ
  private String orgTel = "";//��λ�绰
  private String orgMail = "";//��λ�������
  private String status = "";//��ͬ״̬
  
  private String hiddenEmpId = "";//������ʾEMPID
  private String isButtonForbid = "";//��ť�Ƿ�������ԡ�0��ֹ��1����
  private String paramValue = "";//����ֵAB or BA
  private String isComeFormAdd = "";//�Ƿ����ӹ���
  private String isReadOnly = "";//�Ƿ�ֻ��
  public String getBalance() {
    return balance;
  }
  public void setBalance(String balance) {
    this.balance = balance;
  }
  public String getBirthday() {
    return birthday;
  }
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
  public String getCardKind() {
    return cardKind;
  }
  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }
  public Map getCardMap() {
    return cardMap;
  }
  public void setCardMap(Map cardMap) {
    this.cardMap = cardMap;
  }
  public String getCardNum() {
    return cardNum;
  }
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getDebitter() {
    return debitter;
  }
  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  public String getEmpSt() {
    return empSt;
  }
  public void setEmpSt(String empSt) {
    this.empSt = empSt;
  }
  public String getHiddenEmpId() {
    return hiddenEmpId;
  }
  public void setHiddenEmpId(String hiddenEmpId) {
    this.hiddenEmpId = hiddenEmpId;
  }
  public String getHomeAddr() {
    return homeAddr;
  }
  public void setHomeAddr(String homeAddr) {
    this.homeAddr = homeAddr;
  }
  public String getHomeMai() {
    return homeMai;
  }
  public void setHomeMai(String homeMai) {
    this.homeMai = homeMai;
  }
  public String getHomeTel() {
    return homeTel;
  }
  public void setHomeTel(String homeTel) {
    this.homeTel = homeTel;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getIsButtonForbid() {
    return isButtonForbid;
  }
  public void setIsButtonForbid(String isButtonForbid) {
    this.isButtonForbid = isButtonForbid;
  }
  public String getIsComeFormAdd() {
    return isComeFormAdd;
  }
  public void setIsComeFormAdd(String isComeFormAdd) {
    this.isComeFormAdd = isComeFormAdd;
  }
  public String getIsReadOnly() {
    return isReadOnly;
  }
  public void setIsReadOnly(String isReadOnly) {
    this.isReadOnly = isReadOnly;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getMobile() {
    return mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public String getMonthPay() {
    return monthPay;
  }
  public void setMonthPay(String monthPay) {
    this.monthPay = monthPay;
  }
  public String getOrgAddr() {
    return orgAddr;
  }
  public void setOrgAddr(String orgAddr) {
    this.orgAddr = orgAddr;
  }
  public String getOrgId() {
    return orgId;
  }
  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
  public String getOrgMail() {
    return orgMail;
  }
  public void setOrgMail(String orgMail) {
    this.orgMail = orgMail;
  }
  public String getOrgName() {
    return orgName;
  }
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  public String getOrgTel() {
    return orgTel;
  }
  public void setOrgTel(String orgTel) {
    this.orgTel = orgTel;
  }
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
  public String getSalary() {
    return salary;
  }
  public void setSalary(String salary) {
    this.salary = salary;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public Map getSexMap() {
    return sexMap;
  }
  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  
}
