package org.xpup.hafmis.signjoint.dto;

public class RecieveFileDTO {

  //���|�ֿ��˿���|�ֿ�������|�ֿ������֤����|�������ʻ���|��ע|�ɹ���־|
  String id;
  String bankcardid;
  String name;
  String cardnum;
  String empid;
  String sign;
  String s_f;
  public RecieveFileDTO(){
    this.id = "";
    this.bankcardid = "";
    this.name = "";
    this.cardnum = "";
    this.empid = "";
    this.sign = "";
    this.s_f = "";
  }
  
  public RecieveFileDTO(String id, String bankcardid, String name, String cardnum, String empid, String sign, String s_f) {
    this.id = id;
    this.bankcardid = bankcardid;
    this.name = name;
    this.cardnum = cardnum;
    this.empid = empid;
    this.sign = sign;
    this.s_f = s_f;
  }
  public String getBankcardid() {
    return bankcardid;
  }
  public void setBankcardid(String bankcardid) {
    this.bankcardid = bankcardid;
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
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getS_f() {
    return s_f;
  }
  public void setS_f(String s_f) {
    this.s_f = s_f;
  }
  public String getSign() {
    return sign;
  }
  public void setSign(String sign) {
    this.sign = sign;
  }
  
  
  
  
  
}
