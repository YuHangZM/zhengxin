package org.xpup.hafmis.sysloan.loancallback.loancallback.dto;

import java.math.BigDecimal;

public class LoancallbackTbDTO {
  //����
  private String borrowerName = "";
  //֤������
  private String cardNum = "";
  //��ͬ���
  private String contractId = "";
  //�����˺�
  private String loanKouAcc = "";
  //ƾ֤��
  private String docNum = "";
  //����ʵ����������
  private BigDecimal realCorpus = new BigDecimal(0.00);
  //����ʵ��������Ϣ
  private BigDecimal realInterest = new BigDecimal(0.00);
  //����ʵ�����ڱ���
  private BigDecimal realOverdueCorpus = new BigDecimal(0.00);
  //����ʵ��������Ϣ
  private BigDecimal realOverdueInterest = new BigDecimal(0.00);
  //ʵ����Ϣ
  private BigDecimal realPunishInterest = new BigDecimal(0.00);
  //������
  private BigDecimal occurMoney = new BigDecimal(0.00);
  //ҵ������
  private String bizType = "";
  //ҵ��״̬
  private String bizSt = "";
  //ͷ��ID
  private String id = "";
  //ʵ�����
  private BigDecimal realbackMoney = new BigDecimal(0.00);
  //ʵ�ս��
  private BigDecimal realMoney = new BigDecimal(0.00);
  //��������ʱ���õ�ҵ������
  private String type = "";
  //ҵ������
  private String bizDate = "";
  //�Ƿ񹫻��𻹴�
  private String yesOrNo = "";
  
  //ʵ������
  private String realCount = "";
  
  private String mark="";
  
  public String getRealCount() {
    return realCount;
  }
  public void setRealCount(String realCount) {
    this.realCount = realCount;
  }
  public String getYesOrNo() {
    return yesOrNo;
  }
  public void setYesOrNo(String yesOrNo) {
    this.yesOrNo = yesOrNo;
  }
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public BigDecimal getRealbackMoney() {
    return realbackMoney;
  }
  public void setRealbackMoney(BigDecimal realbackMoney) {
    this.realbackMoney = realbackMoney;
  }
  public BigDecimal getRealMoney() {
    return realMoney;
  }
  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }
  public String getBizSt() {
    return bizSt;
  }
  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }
  public String getBizType() {
    return bizType;
  }
  public void setBizType(String bizType) {
    this.bizType = bizType;
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
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public BigDecimal getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }
  public BigDecimal getRealCorpus() {
    return realCorpus;
  }
  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }
  public BigDecimal getRealInterest() {
    return realInterest;
  }
  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
  }
  public BigDecimal getRealOverdueCorpus() {
    return realOverdueCorpus;
  }
  public void setRealOverdueCorpus(BigDecimal realOverdueCorpus) {
    this.realOverdueCorpus = realOverdueCorpus;
  }
  public BigDecimal getRealOverdueInterest() {
    return realOverdueInterest;
  }
  public void setRealOverdueInterest(BigDecimal realOverdueInterest) {
    this.realOverdueInterest = realOverdueInterest;
  }
  public BigDecimal getRealPunishInterest() {
    return realPunishInterest;
  }
  public void setRealPunishInterest(BigDecimal realPunishInterest) {
    this.realPunishInterest = realPunishInterest;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getMark() {
    return mark;
  }
  public void setMark(String mark) {
    this.mark = mark;
  }

  
 
  
}