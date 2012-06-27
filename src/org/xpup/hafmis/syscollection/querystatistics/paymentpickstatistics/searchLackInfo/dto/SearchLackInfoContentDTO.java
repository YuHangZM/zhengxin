package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto;

import java.math.BigDecimal;

import org.xpup.common.domain.DomainObject;

public class SearchLackInfoContentDTO extends DomainObject{
  private String orgcode = "";//��λ���
  private String orgname = "";//��λ����
  private String lackMonths = "";//��λ���
  private String orgpayLack = "";//Ƿ��λ�ɶ�
  private String emppayLack = "";//Ƿְ���ɶ�
  private String sumpayLack = "";//Ƿְ���ɶ�
  private String org_pay_month = "";//��λ��������
  private String emp_pay_month = "";//ְ����������
  private String org_rate = "";//��λ����
  private String emp_rate = "";//ְ������
  private String orgyue = "";//��λ���
  private String yjehj = "";//�½ɶ�ϼ�
  
  private String zcjcrs = "";//�����ɴ�����
  private BigDecimal orgRate = new BigDecimal(0.00);//��λ����
  private BigDecimal empRate = new BigDecimal(0.00);//ְ������
  private BigDecimal salaryBase = new BigDecimal(0.00);//���ʻ���
  private BigDecimal orgPay = new BigDecimal(0.00);//��λ�ɶ�
  private BigDecimal empPay = new BigDecimal(0.00);//ְ���ɶ�
  private BigDecimal sumPay = new BigDecimal(0.00);//�ɶ�ϼ�
  private BigDecimal sumPay_1 = new BigDecimal(0.00);//�ɶ�ϼ�
  private String orgMonth = "";//��λǷ������
  private String empMonth = "";//ְ��Ƿ������
  private String orgPayMonth = "";//��λ��������
  private String empPayMonth = "";//ְ����������
  private String character = "";//��λ����
  private String transactor_name = "";//��λ������
  private String tel = "";//��λ�绰
  private String address = "";//��λ��ַ
  private BigDecimal orgBalance = new BigDecimal(0.00);//��λ���
  private String artificialPerson = "";//��λ����
  private String paybankName = "";//����
  private String paybankAcc = "";//�����˺�
  private String postalcode = "";//��������
  private String paydate = "";//��н��
  
  private String orgid_old="";
  private String orgname_old="";
  private String lack_month_old="";
  private String lack_pay_old="";
  private String lack_status_old="";
  
  public String getLack_month_old() {
    return lack_month_old;
  }
  public void setLack_month_old(String lack_month_old) {
    this.lack_month_old = lack_month_old;
  }
  public String getLack_pay_old() {
    return lack_pay_old;
  }
  public void setLack_pay_old(String lack_pay_old) {
    this.lack_pay_old = lack_pay_old;
  }
  public String getLack_status_old() {
    return lack_status_old;
  }
  public void setLack_status_old(String lack_status_old) {
    this.lack_status_old = lack_status_old;
  }
  public String getOrgid_old() {
    return orgid_old;
  }
  public void setOrgid_old(String orgid_old) {
    this.orgid_old = orgid_old;
  }
  public String getOrgname_old() {
    return orgname_old;
  }
  public void setOrgname_old(String orgname_old) {
    this.orgname_old = orgname_old;
  }
  public String getPaydate() {
    return paydate;
  }
  public void setPaydate(String paydate) {
    this.paydate = paydate;
  }
  public String getPostalcode() {
    return postalcode;
  }
  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }
  public String getPaybankAcc() {
    return paybankAcc;
  }
  public void setPaybankAcc(String paybankAcc) {
    this.paybankAcc = paybankAcc;
  }
  public String getPaybankName() {
    return paybankName;
  }
  public void setPaybankName(String paybankName) {
    this.paybankName = paybankName;
  }
  public String getArtificialPerson() {
    return artificialPerson;
  }
  public void setArtificialPerson(String artificialPerson) {
    this.artificialPerson = artificialPerson;
  }
  public String getCharacter() {
    return character;
  }
  public void setCharacter(String character) {
    this.character = character;
  }
  public String getEmpMonth() {
    return empMonth;
  }
  public void setEmpMonth(String empMonth) {
    this.empMonth = empMonth;
  }
  public BigDecimal getEmpPay() {
    return empPay;
  }
  public void setEmpPay(BigDecimal empPay) {
    this.empPay = empPay;
  }
  public String getEmpPayMonth() {
    return empPayMonth;
  }
  public void setEmpPayMonth(String empPayMonth) {
    this.empPayMonth = empPayMonth;
  }
  public BigDecimal getEmpRate() {
    return empRate;
  }
  public void setEmpRate(BigDecimal empRate) {
    this.empRate = empRate;
  }
  public BigDecimal getOrgBalance() {
    return orgBalance;
  }
  public void setOrgBalance(BigDecimal orgBalance) {
    this.orgBalance = orgBalance;
  }
  public String getOrgMonth() {
    return orgMonth;
  }
  public void setOrgMonth(String orgMonth) {
    this.orgMonth = orgMonth;
  }
  public BigDecimal getOrgPay() {
    return orgPay;
  }
  public void setOrgPay(BigDecimal orgPay) {
    this.orgPay = orgPay;
  }
  public String getOrgPayMonth() {
    return orgPayMonth;
  }
  public void setOrgPayMonth(String orgPayMonth) {
    this.orgPayMonth = orgPayMonth;
  }
  public BigDecimal getOrgRate() {
    return orgRate;
  }
  public void setOrgRate(BigDecimal orgRate) {
    this.orgRate = orgRate;
  }
  public BigDecimal getSalaryBase() {
    return salaryBase;
  }
  public void setSalaryBase(BigDecimal salaryBase) {
    this.salaryBase = salaryBase;
  }
  public BigDecimal getSumPay() {
    return sumPay;
  }
  public void setSumPay(BigDecimal sumPay) {
    this.sumPay = sumPay;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getEmppayLack() {
    return emppayLack;
  }
  public void setEmppayLack(String emppayLack) {
    this.emppayLack = emppayLack;
  }
  public String getLackMonths() {
    return lackMonths;
  }
  public void setLackMonths(String lackMonths) {
    this.lackMonths = lackMonths;
  }
  public Integer getOrgcode() {
    return new Integer(orgcode);
  }
  public void setOrgcode(String orgcode) {
    this.orgcode = orgcode;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  public String getOrgpayLack() {
    return orgpayLack;
  }
  public void setOrgpayLack(String orgpayLack) {
    this.orgpayLack = orgpayLack;
  }
  public String getSumpayLack() {
    return sumpayLack;
  }
  public void setSumpayLack(String sumpayLack) {
    this.sumpayLack = sumpayLack;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getTransactor_name() {
    return transactor_name;
  }
  public void setTransactor_name(String transactor_name) {
    this.transactor_name = transactor_name;
  }
  public String getEmp_pay_month() {
    return emp_pay_month;
  }
  public void setEmp_pay_month(String emp_pay_month) {
    this.emp_pay_month = emp_pay_month;
  }
  public String getEmp_rate() {
    return emp_rate;
  }
  public void setEmp_rate(String emp_rate) {
    this.emp_rate = emp_rate;
  }
  public String getOrg_pay_month() {
    return org_pay_month;
  }
  public void setOrg_pay_month(String org_pay_month) {
    this.org_pay_month = org_pay_month;
  }
  public String getOrg_rate() {
    return org_rate;
  }
  public void setOrg_rate(String org_rate) {
    this.org_rate = org_rate;
  }
 
  public String getOrgyue() {
    return orgyue;
  }
  public void setOrgyue(String orgyue) {
    this.orgyue = orgyue;
  }
  public String getYjehj() {
    return yjehj;
  }
  public void setYjehj(String yjehj) {
    this.yjehj = yjehj;
  }
  public String getZcjcrs() {
    return zcjcrs;
  }
  public void setZcjcrs(String zcjcrs) {
    this.zcjcrs = zcjcrs;
  }
  public BigDecimal getSumPay_1() {
    return sumPay_1;
  }
  public void setSumPay_1(BigDecimal sumPay_1) {
    this.sumPay_1 = sumPay_1;
  }

}
