package org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto;

import java.math.BigDecimal;

public class LoandeskaccqueryTbDTO {

  private String id = "";// ҵ������

  private String bizdate = "";// ҵ������

  private String yearmonth = "";// ��������

  private String docnum = "";// ƾ֤���

  private String biztype = "";// ҵ������

  private String accordmoney = "";// ���Ž��

  private String overloanmoney = "";// ���˽��

  private String baddebtmoney = "";// ���˺������

  private String bailmoney = "";// ��֤��

  private String shouldcorpus = "";// Ӧ������

  private String shouldinterest = "";// Ӧ����Ϣ

  private String shouldpunishinterest = "";// Ӧ����Ϣ

  private String realcorpus = "";// ʵ������

  private String realinterest = "";// ʵ����Ϣ

  private String realpunishinterest = "";// ʵ����Ϣ

  private String loantype = "";// ��������

  private String occurmoney = "";// �������

  private String wrongbiztype = "";// ��������

  private String batchNum;

  private BigDecimal sumCorpusInterest = new BigDecimal(0.00);

  public String getWrongbiztype() {
    return wrongbiztype;
  }

  public void setWrongbiztype(String wrongbiztype) {
    this.wrongbiztype = wrongbiztype;
  }

  public String getAccordmoney() {
    return accordmoney;
  }

  public void setAccordmoney(String accordmoney) {
    this.accordmoney = accordmoney;
  }

  public String getBaddebtmoney() {
    return baddebtmoney;
  }

  public void setBaddebtmoney(String baddebtmoney) {
    this.baddebtmoney = baddebtmoney;
  }

  public String getBailmoney() {
    return bailmoney;
  }

  public void setBailmoney(String bailmoney) {
    this.bailmoney = bailmoney;
  }

  public String getBizdate() {
    return bizdate;
  }

  public void setBizdate(String bizdate) {
    this.bizdate = bizdate;
  }

  public String getBiztype() {
    return biztype;
  }

  public void setBiztype(String biztype) {
    this.biztype = biztype;
  }

  public String getDocnum() {
    return docnum;
  }

  public void setDocnum(String docnum) {
    this.docnum = docnum;
  }

  public String getLoantype() {
    return loantype;
  }

  public void setLoantype(String loantype) {
    this.loantype = loantype;
  }

  public String getOccurmoney() {
    return occurmoney;
  }

  public void setOccurmoney(String occurmoney) {
    this.occurmoney = occurmoney;
  }

  public String getOverloanmoney() {
    return overloanmoney;
  }

  public void setOverloanmoney(String overloanmoney) {
    this.overloanmoney = overloanmoney;
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

  public String getShouldcorpus() {
    return shouldcorpus;
  }

  public void setShouldcorpus(String shouldcorpus) {
    this.shouldcorpus = shouldcorpus;
  }

  public String getShouldinterest() {
    return shouldinterest;
  }

  public void setShouldinterest(String shouldinterest) {
    this.shouldinterest = shouldinterest;
  }

  public String getShouldpunishinterest() {
    return shouldpunishinterest;
  }

  public void setShouldpunishinterest(String shouldpunishinterest) {
    this.shouldpunishinterest = shouldpunishinterest;
  }

  public String getYearmonth() {
    return yearmonth;
  }

  public void setYearmonth(String yearmonth) {
    this.yearmonth = yearmonth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getSumCorpusInterest() {
    return sumCorpusInterest;
  }

  public void setSumCorpusInterest(BigDecimal sumCorpusInterest) {
    this.sumCorpusInterest = sumCorpusInterest;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

}