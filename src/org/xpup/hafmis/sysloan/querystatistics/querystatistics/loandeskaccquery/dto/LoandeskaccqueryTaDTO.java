package org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto;

import java.math.BigDecimal;

public class LoandeskaccqueryTaDTO {
  private String id = ""; // �������ú�ͬ��ŵ�

  private String contactid = "";// ��ͬ���

  private String loankouacc = "";// �����˺�

  private String borrowername = "";// ���������

  private String cardnum = "";// ֤������

  private String paybank = "";// �ſ�����

  private String loanmoney = "";// ������

  private String loanBalance = "";// �������

  private String loanlimit = "";// ��������

  private String loanstartdate = "";// ��������

  private String contact_st = "";// ��ͬ״̬

  private String ovareloanrepay = "";// �������

  private String nobackmoney = "";// ����δ�ջؽ��

  private String ballbalance = "";// ��֤�����

  private String monthpay = "0";// �»����

  private String realcorpus = "0";// �ܻ�����

  private String realinterest = "0";// �ܻ���Ϣ

  private String realpunishinterest = "0";// �ܻ���Ϣ��Ϣ

  private String owemonth = "";// ��������

  private String payday = ""; // �������û����յ�

  private String overlimited = "";

  private BigDecimal corpusInterest = new BigDecimal(0.00);

  private String loanRepayDay = "";

  private BigDecimal sumCorpusInterest = new BigDecimal(0.00);

  private String orgId = ""; // ��λ���

  private String orgName = ""; // ��λ����

  private BigDecimal areaFrist = new BigDecimal("0.00"); // ���(��)

  private BigDecimal areaSecond = new BigDecimal("0.00"); // ���(����)

  private String isloanPay = "";// �����𻹴�

  private BigDecimal minaji = new BigDecimal(0.00);// ��� 22

  private BigDecimal daikyue = new BigDecimal(0.00);// ��� 24

  private BigDecimal fangwuzongjia = new BigDecimal(0.00);// �����ܼ� 23

  private String otherArrearage;

  public String getOtherArrearage() {
    return otherArrearage;
  }

  public void setOtherArrearage(String otherArrearage) {
    this.otherArrearage = otherArrearage;
  }

  public BigDecimal getDaikyue() {
    return daikyue;
  }

  public void setDaikyue(BigDecimal daikyue) {
    this.daikyue = daikyue;
  }

  public BigDecimal getFangwuzongjia() {
    return fangwuzongjia;
  }

  public void setFangwuzongjia(BigDecimal fangwuzongjia) {
    this.fangwuzongjia = fangwuzongjia;
  }

  public BigDecimal getMinaji() {
    return minaji;
  }

  public void setMinaji(BigDecimal minaji) {
    this.minaji = minaji;
  }

  public String getIsloanPay() {
    return isloanPay;
  }

  public void setIsloanPay(String isloanPay) {
    this.isloanPay = isloanPay;
  }

  public String getBallbalance() {
    return ballbalance;
  }

  public void setBallbalance(String ballbalance) {
    this.ballbalance = ballbalance;
  }

  public String getBorrowername() {
    return borrowername;
  }

  public void setBorrowername(String borrowername) {
    this.borrowername = borrowername;
  }

  public String getCardnum() {
    return cardnum;
  }

  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }

  public String getContact_st() {
    return contact_st;
  }

  public void setContact_st(String contact_st) {
    this.contact_st = contact_st;
  }

  public String getContactid() {
    return contactid;
  }

  public void setContactid(String contactid) {
    this.contactid = contactid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLoankouacc() {
    return loankouacc;
  }

  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }

  public String getLoanlimit() {
    return loanlimit;
  }

  public void setLoanlimit(String loanlimit) {
    this.loanlimit = loanlimit;
  }

  public String getLoanmoney() {
    return loanmoney;
  }

  public void setLoanmoney(String loanmoney) {
    this.loanmoney = loanmoney;
  }

  public String getLoanstartdate() {
    return loanstartdate;
  }

  public void setLoanstartdate(String loanstartdate) {
    this.loanstartdate = loanstartdate;
  }

  public String getMonthpay() {
    return monthpay;
  }

  public void setMonthpay(String monthpay) {
    this.monthpay = monthpay;
  }

  public String getNobackmoney() {
    return nobackmoney;
  }

  public void setNobackmoney(String nobackmoney) {
    this.nobackmoney = nobackmoney;
  }

  public String getOvareloanrepay() {
    return ovareloanrepay;
  }

  public void setOvareloanrepay(String ovareloanrepay) {
    this.ovareloanrepay = ovareloanrepay;
  }

  public String getOwemonth() {
    return owemonth;
  }

  public void setOwemonth(String owemonth) {
    this.owemonth = owemonth;
  }

  public String getPaybank() {
    return paybank;
  }

  public void setPaybank(String paybank) {
    this.paybank = paybank;
  }

  public String getRealcorpus() {
    return realcorpus;
  }

  public void setRealcorpus(String realcorpus) {
    this.realcorpus = realcorpus;
  }

  public String getRealinterest() {
    return realinterest;
  }

  public void setRealinterest(String realinterest) {
    this.realinterest = realinterest;
  }

  public String getRealpunishinterest() {
    return realpunishinterest;
  }

  public void setRealpunishinterest(String realpunishinterest) {
    this.realpunishinterest = realpunishinterest;
  }

  public String getPayday() {
    return payday;
  }

  public void setPayday(String payday) {
    this.payday = payday;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public BigDecimal getAreaFrist() {
    return areaFrist;
  }

  public void setAreaFrist(BigDecimal areaFrist) {
    this.areaFrist = areaFrist;
  }

  public BigDecimal getAreaSecond() {
    return areaSecond;
  }

  public void setAreaSecond(BigDecimal areaSecond) {
    this.areaSecond = areaSecond;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getOverlimited() {
    return overlimited;
  }

  public void setOverlimited(String overlimited) {
    this.overlimited = overlimited;
  }

  public String getLoanRepayDay() {
    return loanRepayDay;
  }

  public void setLoanRepayDay(String loanRepayDay) {
    this.loanRepayDay = loanRepayDay;
  }

  public BigDecimal getSumCorpusInterest() {
    return sumCorpusInterest;
  }

  public void setSumCorpusInterest(BigDecimal sumCorpusInterest) {
    this.sumCorpusInterest = sumCorpusInterest;
  }

  public String getLoanBalance() {
    return loanBalance;
  }

  public void setLoanBalance(String loanBalance) {
    this.loanBalance = loanBalance;
  }

}
