package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoancallbackTaDTO {
  //����
  private String borrowerName = "";
  //֤������
  private String cardKind = "";
  //֤������
  private String cardNum = "";
  //ʣ�౾��
  private BigDecimal overplusLoanMoney = new BigDecimal(0.00); 
  //ʣ������
  private String overplusLimite = "";
  //���ʽ
  private String loanMode = "";
  //��ͬ���
  private String contractId = "";
  //�����˺�
  private String loanKouAcc = "";
  //��������
  private List monthYearList = new ArrayList();
  //Ӧ����Ϣ
  private List shouldBackList = new ArrayList();
  //��������
  private String payType = "";
  //��ǰ�����
  private BigDecimal aheadCorpus = new BigDecimal(0.00);
  //ռ������
  private String days = "";
  //��ǰ������Ϣ
  private BigDecimal aheadInterest = new BigDecimal(0.00);
  //�����ѽ��
  private BigDecimal loanPoundageMoney = new BigDecimal(0.00);
  //��ǰ�����ʣ������
  private String deadLine = "";
  //��ǰ������»���Ϣ
  private BigDecimal corpusInterest = new BigDecimal(0.00);
  //�����ܻ����
  private BigDecimal sumCorpus = new BigDecimal(0.00);
  //�����ܻ�����Ϣ
  private BigDecimal sumInterest = new BigDecimal(0.00);
  //�����ܻ�����
  private BigDecimal sumMoney = new BigDecimal(0.00);
  //������� 
  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);
  //����ʵ�ս��
  private BigDecimal realMoney = new BigDecimal(0.00);
  public BigDecimal getAheadCorpus() {
    return aheadCorpus;
  }
  public void setAheadCorpus(BigDecimal aheadCorpus) {
    this.aheadCorpus = aheadCorpus;
  }
  public BigDecimal getAheadInterest() {
    return aheadInterest;
  }
  public void setAheadInterest(BigDecimal aheadInterest) {
    this.aheadInterest = aheadInterest;
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
  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }
  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }
  public String getDays() {
    return days;
  }
  public void setDays(String days) {
    this.days = days;
  }
  public String getDeadLine() {
    return deadLine;
  }
  public void setDeadLine(String deadLine) {
    this.deadLine = deadLine;
  }
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getLoanMode() {
    return loanMode;
  }
  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }
  public BigDecimal getLoanPoundageMoney() {
    return loanPoundageMoney;
  }
  public void setLoanPoundageMoney(BigDecimal loanPoundageMoney) {
    this.loanPoundageMoney = loanPoundageMoney;
  }
  public List getMonthYearList() {
    return monthYearList;
  }
  public void setMonthYearList(List monthYearList) {
    this.monthYearList = monthYearList;
  }
  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }
  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }
  public String getOverplusLimite() {
    return overplusLimite;
  }
  public void setOverplusLimite(String overplusLimite) {
    this.overplusLimite = overplusLimite;
  }
  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }
  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }
  public String getPayType() {
    return payType;
  }
  public void setPayType(String payType) {
    this.payType = payType;
  }
  public BigDecimal getRealMoney() {
    return realMoney;
  }
  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }

  public List getShouldBackList() {
    return shouldBackList;
  }
  public void setShouldBackList(List shouldBackList) {
    this.shouldBackList = shouldBackList;
  }
  public BigDecimal getSumCorpus() {
    return sumCorpus;
  }
  public void setSumCorpus(BigDecimal sumCorpus) {
    this.sumCorpus = sumCorpus;
  }
  public BigDecimal getSumInterest() {
    return sumInterest;
  }
  public void setSumInterest(BigDecimal sumInterest) {
    this.sumInterest = sumInterest;
  }
  public BigDecimal getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  
}