package org.xpup.hafmis.signjoint.entity;

public class Sign {

  private Integer id;//��Ӧ��RQ001�е�ID����
  private Integer sign;//ǩԼ���ɵ�Ψһ���
  private String name;//ְ������
  private String card_num;//���֤��
  private String empid;//ְ����
  private String bank_card;//���п���
  public Sign()
  {
  }
  public Sign( Integer sign, String name, String card_num, String empid, String bank_card) {    this.sign = sign;
    this.name = name;
    this.card_num = card_num;
    this.empid = empid;
    this.bank_card = bank_card;
  }
  public String getBank_card() {
    return bank_card;
  }
  public void setBank_card(String bank_card) {
    this.bank_card = bank_card;
  }
  public String getCard_num() {
    return card_num;
  }
  public void setCard_num(String card_num) {
    this.card_num = card_num;
  }
  public String getEmpid() {
    return empid;
  }
  public void setEmpid(String empid) {
    this.empid = empid;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getSign() {
    return sign;
  }
  public void setSign(Integer sign) {
    this.sign = sign;
  }
  
  
  
  
  
  
  
  
  
}
