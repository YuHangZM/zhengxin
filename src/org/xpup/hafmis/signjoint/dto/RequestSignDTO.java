package org.xpup.hafmis.signjoint.dto;
/**
 * ��װ��������
 * @author Administrator
 *
 */
public class RequestSignDTO  {
  private String name="";//ְ������
  private String cardnum="";//���֤��
  private String empid="";//ְ����
  private String banknum="";//���п���

  public RequestSignDTO()
  {
  }
  public RequestSignDTO(String banknum,String name,String cardnum,String empid)
  {
    this.banknum=banknum;
    this.name=name;
    this.cardnum=cardnum;
    this.empid=empid;
  }
  public String getBanknum() {
    return banknum;
  }
  public void setBanknum(String banknum) {
    this.banknum = banknum;
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
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

}
