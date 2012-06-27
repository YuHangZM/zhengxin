package org.xpup.hafmis.sysloan.loancallback.contractchange.dto;

import java.math.BigDecimal;

public class ContractchangeDTO {
  
  private String id = "";
  private String contractId = "";//��ͬ��� ����˺�ͬ���
  private String borrowerName = "";//��������� ���������
  private String cardNum = "";//֤������ ֤������
  private String new_term = "";//      ��ʣ������
  private String type = "";//����
  private String date = "";//��������
  
  private BigDecimal aheadmoney = new BigDecimal(0.00);//��ǰ������
  private BigDecimal monthPay = new BigDecimal(0.00);//�»���Ϣ
  private BigDecimal loanMoney = new BigDecimal(0.00);//������
  private BigDecimal overplusLoanMoney = new BigDecimal(0.00);//������� OVERPLUS_LOAN_MONEY
  private String agreementDate = "";//��ͬǩ������
  private String shengyunian = "";//ʣ����
  private String shengyuyue = "";//ʣ����
  private String daoqidate = "";//������
  private String yuanlimit="";//ԭʣ������
  private String loanlimit="";//��������
  
  private String mark_a="";
  private String mark_b="";
  private String mark_c="";
  private String mark_d="";
  
  private String money_1="";
  private String money_2="";
  
  private String flowid="";
  
  
  public String getDaoqidate() {
    return daoqidate;
  }
  public void setDaoqidate(String daoqidate) {
    this.daoqidate = daoqidate;
  }
  public BigDecimal getAheadmoney() {
    return aheadmoney;
  }
  public void setAheadmoney(BigDecimal aheadmoney) {
    this.aheadmoney = aheadmoney;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getNew_term() {
    return new_term;
  }
  public void setNew_term(String new_term) {
    this.new_term = new_term;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getAgreementDate() {
    return agreementDate;
  }
  public void setAgreementDate(String agreementDate) {
    this.agreementDate = agreementDate;
  }
  public BigDecimal getLoanMoney() {
    return loanMoney;
  }
  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }
  public BigDecimal getMonthPay() {
    return monthPay;
  }
  public void setMonthPay(BigDecimal monthPay) {
    this.monthPay = monthPay;
  }
  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getShengyunian() {
    return shengyunian;
  }
  public void setShengyunian(String shengyunian) {
    this.shengyunian = shengyunian;
  }
  public String getShengyuyue() {
    return shengyuyue;
  }
  public void setShengyuyue(String shengyuyue) {
    this.shengyuyue = shengyuyue;
  }
  public String getMark_a() {
    return mark_a;
  }
  public void setMark_a(String mark_a) {
    this.mark_a = mark_a;
  }
  public String getMark_b() {
    return mark_b;
  }
  public void setMark_b(String mark_b) {
    this.mark_b = mark_b;
  }
  public String getMark_c() {
    return mark_c;
  }
  public void setMark_c(String mark_c) {
    this.mark_c = mark_c;
  }
  public String getMark_d() {
    return mark_d;
  }
  public void setMark_d(String mark_d) {
    this.mark_d = mark_d;
  }
  public String getMoney_1() {
    return money_1;
  }
  public void setMoney_1(String money_1) {
    this.money_1 = money_1;
  }
  public String getMoney_2() {
    return money_2;
  }
  public void setMoney_2(String money_2) {
    this.money_2 = money_2;
  }
  public String getFlowid() {
    return flowid;
  }
  public void setFlowid(String flowid) {
    this.flowid = flowid;
  }
  public String getLoanlimit() {
    return loanlimit;
  }
  public void setLoanlimit(String loanlimit) {
    this.loanlimit = loanlimit;
  }
  public String getYuanlimit() {
    return yuanlimit;
  }
  public void setYuanlimit(String yuanlimit) {
    this.yuanlimit = yuanlimit;
  }
}
