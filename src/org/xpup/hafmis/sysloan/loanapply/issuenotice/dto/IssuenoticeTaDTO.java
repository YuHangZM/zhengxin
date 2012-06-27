package org.xpup.hafmis.sysloan.loanapply.issuenotice.dto;

import java.math.BigDecimal;

public class IssuenoticeTaDTO {
  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String cardKind = "";// ֤������

  private String cardNum = "";// ֤������

  private String loanBankId = "";// �ſ�����

  private BigDecimal loanMoney = new BigDecimal(0.00);// �����

  private String loanTimeLimit = "";// �������

  private BigDecimal loanMonthRate = new BigDecimal(0.00);// ÿ������

  private BigDecimal corpusInterest = new BigDecimal(0.00);// �»���Ϣ

  private String loanMode = "";// ���ʽ

  private String sellerPayBank = "";// ��������

  private String sellerPayBankAcc = "";// ���������˺�

  private String bizDate = "";// �������ڣ�Ĭ��Ϊ�������

  private String temp_loanMode = "";

  private String temp_cardKind = "";
  
  private String loanKouAcc="";//�ۿ��˺�
  
  private String houseTotalPrice="";//�����ܽ��
  
  private String houseArea="";//�������
  
  private String houseAddr="";//������ַ
  
  private String orgName="";//������λ
  
  private String orgTel="";//��ϵ�绰
  
  private String house="";//�۷���λ
  
  private BigDecimal firstLoanMoney = new BigDecimal(0.00);// �״λ�����
  
  private String bailName="";
  
  private String headId="";
  
  private String empId="";
  
  private String redatePerson = "";

  private String chkAgainPerson = "";

  private String vipChkAgainPerson = "";

  private String lastChkPerson = "";
  
  private String finChkPerson="";
 

  public String getChkAgainPerson() {
    return chkAgainPerson;
  }

  public void setChkAgainPerson(String chkAgainPerson) {
    this.chkAgainPerson = chkAgainPerson;
  }

  public String getLastChkPerson() {
    return lastChkPerson;
  }

  public void setLastChkPerson(String lastChkPerson) {
    this.lastChkPerson = lastChkPerson;
  }

  public String getRedatePerson() {
    return redatePerson;
  }

  public void setRedatePerson(String redatePerson) {
    this.redatePerson = redatePerson;
  }

  public String getVipChkAgainPerson() {
    return vipChkAgainPerson;
  }

  public void setVipChkAgainPerson(String vipChkAgainPerson) {
    this.vipChkAgainPerson = vipChkAgainPerson;
  }

  public String getHouseAddr() {
    return houseAddr;
  }

  public void setHouseAddr(String houseAddr) {
    this.houseAddr = houseAddr;
  }

  public String getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(String houseArea) {
    this.houseArea = houseArea;
  }

  public String getHouseTotalPrice() {
    return houseTotalPrice;
  }

  public void setHouseTotalPrice(String houseTotalPrice) {
    this.houseTotalPrice = houseTotalPrice;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
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

  public String getLoanBankId() {
    return loanBankId;
  }

  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }



  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getSellerPayBank() {
    return sellerPayBank;
  }

  public void setSellerPayBank(String sellerPayBank) {
    this.sellerPayBank = sellerPayBank;
  }

  public String getSellerPayBankAcc() {
    return sellerPayBankAcc;
  }

  public void setSellerPayBankAcc(String sellerPayBankAcc) {
    this.sellerPayBankAcc = sellerPayBankAcc;
  }

  public String getTemp_cardKind() {
    return temp_cardKind;
  }

  public void setTemp_cardKind(String temp_cardKind) {
    this.temp_cardKind = temp_cardKind;
  }

  public String getTemp_loanMode() {
    return temp_loanMode;
  }

  public void setTemp_loanMode(String temp_loanMode) {
    this.temp_loanMode = temp_loanMode;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getBailName() {
    return bailName;
  }

  public void setBailName(String bailName) {
    this.bailName = bailName;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public BigDecimal getFirstLoanMoney() {
    return firstLoanMoney;
  }

  public void setFirstLoanMoney(BigDecimal firstLoanMoney) {
    this.firstLoanMoney = firstLoanMoney;
  }

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
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

  public String getFinChkPerson() {
    return finChkPerson;
  }

  public void setFinChkPerson(String finChkPerson) {
    this.finChkPerson = finChkPerson;
  }

}
