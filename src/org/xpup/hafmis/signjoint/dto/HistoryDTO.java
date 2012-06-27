package org.xpup.hafmis.signjoint.dto;

public class HistoryDTO {
  //id ������orgid ��λ��ţ�orgname ��λ���ƣ�empid ְ����ţ�
  //empname ְ��������cardnum ���֤�ţ�bankcardid ���п��ţ�
  //succ_fail �ɹ���ʶ��biz_date ����ʱ�䣬bank_sure_date ����ȷ��ʱ�䣬
  //operater ������Ա,sign Ψһ��ʶ
  private String id;
  private String orgid;
  private String orgname;
  private String empid;
  private String empname;
  private String cardnum;
  private String bankcardid;
  private String succ_fail;
  private String biz_date;
  private String bank_sure_date;
  private String operater;
  private String sign;
  public HistoryDTO() {
    this.id = "";
    this.orgid = "";
    this.orgname = "";
    this.empid = "";
    this.empname = "";
    this.cardnum = "";
    this.bankcardid = "";
    this.succ_fail = "";
    this.biz_date = "";
    this.bank_sure_date = "";
    this.operater = "";
    this.sign = "";
  }

  public HistoryDTO(String id, String orgid, String orgname, String empid, String empname, String cardnum, String bankcardid, String succ_fail, String biz_date, String bank_sure_date, String operater, String sign) {
    this.id = id;
    this.orgid = orgid;
    this.orgname = orgname;
    this.empid = empid;
    this.empname = empname;
    this.cardnum = cardnum;
    this.bankcardid = bankcardid;
    this.succ_fail = succ_fail;
    this.biz_date = biz_date;
    this.bank_sure_date = bank_sure_date;
    this.operater = operater;
    this.sign = sign;
  }
  public String getBank_sure_date() {
    return bank_sure_date;
  }
  public void setBank_sure_date(String bank_sure_date) {
    this.bank_sure_date = bank_sure_date;
  }
  public String getBankcardid() {
    return bankcardid;
  }
  public void setBankcardid(String bankcardid) {
    this.bankcardid = bankcardid;
  }
  public String getBiz_date() {
    return biz_date;
  }
  public void setBiz_date(String biz_date) {
    this.biz_date = biz_date;
  }
  public String getCardnum() {
    return cardnum;
  }
  public void setCardnum(String cardnum) {
    this.cardnum = cardnum;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public String getEmpname() {
    return empname;
  }
  public void setEmpname(String empname) {
    this.empname = empname;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getOperater() {
    return operater;
  }
  public void setOperater(String operater) {
    this.operater = operater;
  }
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
  public String getSign() {
    return sign;
  }
  public void setSign(String sign) {
    this.sign = sign;
  }
  public String getSucc_fail() {
    return succ_fail;
  }
  public void setSucc_fail(String succ_fail) {
    this.succ_fail = succ_fail;
  }
  
  
  
}
