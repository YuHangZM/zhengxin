package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.dto;

public class ParticularglDTO {
//�������  �ڳ��������  ���ڽ跽  ���ڴ���  ������Ϣ  ���ڷ�Ϣ  ���˽��  ��֤��  ���˺������  ��ĩ�������  
  private String ocyear;//������Կ������꣬�£��� �е�һ��
  private String firstcorpus;
  private String thisborrower;
    private String bocmoney="0.00";//���ڽ跽 ������� ���Ǵ������͵�
    private String bwocmoney="0.00";//���ڽ跽 ������� �������͵�
  private String thispaymoney;
  private String thisinterest;
  private String thispunishinterest;
  private String thisloanrepay;
    private String locmoney="0.00";//���˽�� ������� ���Ǵ������͵�
    private String lwocmoney="0.00";//���˽�� ������� �������͵�
  private String thisballbalance="0.00";
  private String thisbaddebtmoney;
    private String badocmoney="0.00";//���˺������ ������� ���Ǵ������͵�
    private String badwocmoney="0.00";//���˺������ ������� �������͵�
  private String lastcorpus;
  private String type="";
  
  
  //��ʼ
  private String docNum="";//ƾ֤��
  private String bizType="";//ҵ������
  private String derection="";//����
  private String contractId="";//��ͬ���
  private String summary="";//ժҪ
  //����
  public String getFirstcorpus() {
    return firstcorpus;
  }
  public void setFirstcorpus(String firstcorpus) {
    this.firstcorpus = firstcorpus;
  }
  public String getLastcorpus() {
    return lastcorpus;
  }
  public void setLastcorpus(String lastcorpus) {
    this.lastcorpus = lastcorpus;
  }
  public String getOcyear() {
    return ocyear;
  }
  public void setOcyear(String ocyear) {
    this.ocyear = ocyear;
  }
  public String getThisbaddebtmoney() {
    return thisbaddebtmoney;
  }
  public void setThisbaddebtmoney(String thisbaddebtmoney) {
    this.thisbaddebtmoney = thisbaddebtmoney;
  }
  public String getThisballbalance() {
    return thisballbalance;
  }
  public void setThisballbalance(String thisballbalance) {
    this.thisballbalance = thisballbalance;
  }
  public String getThisborrower() {
    return thisborrower;
  }
  public void setThisborrower(String thisborrower) {
    this.thisborrower = thisborrower;
  }
  public String getThisinterest() {
    return thisinterest;
  }
  public void setThisinterest(String thisinterest) {
    this.thisinterest = thisinterest;
  }
  public String getThisloanrepay() {
    return thisloanrepay;
  }
  public void setThisloanrepay(String thisloanrepay) {
    this.thisloanrepay = thisloanrepay;
  }
  public String getThispaymoney() {
    return thispaymoney;
  }
  public void setThispaymoney(String thispaymoney) {
    this.thispaymoney = thispaymoney;
  }
  public String getThispunishinterest() {
    return thispunishinterest;
  }
  public void setThispunishinterest(String thispunishinterest) {
    this.thispunishinterest = thispunishinterest;
  }
  public String getBadocmoney() {
    return badocmoney;
  }
  public void setBadocmoney(String badocmoney) {
    this.badocmoney = badocmoney;
  }
  public String getBadwocmoney() {
    return badwocmoney;
  }
  public void setBadwocmoney(String badwocmoney) {
    this.badwocmoney = badwocmoney;
  }
  public String getBocmoney() {
    return bocmoney;
  }
  public void setBocmoney(String bocmoney) {
    this.bocmoney = bocmoney;
  }
  public String getBwocmoney() {
    return bwocmoney;
  }
  public void setBwocmoney(String bwocmoney) {
    this.bwocmoney = bwocmoney;
  }
  public String getLocmoney() {
    return locmoney;
  }
  public void setLocmoney(String locmoney) {
    this.locmoney = locmoney;
  }
  public String getLwocmoney() {
    return lwocmoney;
  }
  public void setLwocmoney(String lwocmoney) {
    this.lwocmoney = lwocmoney;
  }
  public String getBizType() {
    return bizType;
  }
  public void setBizType(String bizType) {
    this.bizType = bizType;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  public String getDerection() {
    return derection;
  }
  public void setDerection(String derection) {
    this.derection = derection;
  }
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  
}

