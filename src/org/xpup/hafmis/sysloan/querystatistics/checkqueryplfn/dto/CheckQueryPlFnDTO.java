package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto;

public class CheckQueryPlFnDTO {
  // ����˺�ͬ��Ϣ
  private String contractid;// ��ͬ���

  private String loankouacc;// �����˺�

  private String borrowername;// ���������

  private String cardnum;// ֤������

  private String loanbank;// �ſ�����

  private String loanbankname = "";// �ſ���������

  private String loanmoney;// ������

  private String loanlimit;// ��������

  private String loanmode;// ���ʽ
  
  private String temp_loanmode="";

  private String overplusloanmoney = "";// �������

  private String nobackmoney = "";// ����δ���ս��

  private String oveaerloanrepay = "";// �������

  private String ballbalance = "";// ��֤�����

  private String srealcorpus = "";// �ܻ�����

  private String srealinterest = "";// �ܻ���Ϣ

  private String srealpunishinterest = "";// �ܻ���Ϣ��Ϣ

  private String owercorpus = "0";// Ƿ������

  private String oweinterest = "0";// Ƿ����Ϣ

  private String owepunishinterest = "0";// Ƿ����Ϣ��Ϣ
  
  private String payday="";//������

  public String getPayday() {
    return payday;
  }

  public void setPayday(String payday) {
    this.payday = payday;
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

  public String getContractid() {
    return contractid;
  }

  public void setContractid(String contractid) {
    this.contractid = contractid;
  }

  public String getLoanbank() {
    return loanbank;
  }

  public void setLoanbank(String loanbank) {
    this.loanbank = loanbank;
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

  public String getLoanmode() {
    return loanmode;
  }

  public void setLoanmode(String loanmode) {
    this.loanmode = loanmode;
  }

  public String getLoanmoney() {
    return loanmoney;
  }

  public void setLoanmoney(String loanmoney) {
    this.loanmoney = loanmoney;
  }

  public String getNobackmoney() {
    return nobackmoney;
  }

  public void setNobackmoney(String nobackmoney) {
    this.nobackmoney = nobackmoney;
  }

  public String getOveaerloanrepay() {
    return oveaerloanrepay;
  }

  public void setOveaerloanrepay(String oveaerloanrepay) {
    this.oveaerloanrepay = oveaerloanrepay;
  }

  public String getOverplusloanmoney() {
    return overplusloanmoney;
  }

  public void setOverplusloanmoney(String overplusloanmoney) {
    this.overplusloanmoney = overplusloanmoney;
  }

  public String getOweinterest() {
    return oweinterest;
  }

  public void setOweinterest(String oweinterest) {
    this.oweinterest = oweinterest;
  }

  public String getOwepunishinterest() {
    return owepunishinterest;
  }

  public void setOwepunishinterest(String owepunishinterest) {
    this.owepunishinterest = owepunishinterest;
  }

  public String getOwercorpus() {
    return owercorpus;
  }

  public void setOwercorpus(String owercorpus) {
    this.owercorpus = owercorpus;
  }

  public String getSrealcorpus() {
    return srealcorpus;
  }

  public void setSrealcorpus(String srealcorpus) {
    this.srealcorpus = srealcorpus;
  }

  public String getSrealinterest() {
    return srealinterest;
  }

  public void setSrealinterest(String srealinterest) {
    this.srealinterest = srealinterest;
  }

  public String getSrealpunishinterest() {
    return srealpunishinterest;
  }

  public void setSrealpunishinterest(String srealpunishinterest) {
    this.srealpunishinterest = srealpunishinterest;
  }

  public String getLoanbankname() {
    return loanbankname;
  }

  public void setLoanbankname(String loanbankname) {
    this.loanbankname = loanbankname;
  }

  public String getTemp_loanmode() {
    return temp_loanmode;
  }

  public void setTemp_loanmode(String temp_loanmode) {
    this.temp_loanmode = temp_loanmode;
  }

}
