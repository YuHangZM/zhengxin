/*�������  ְ�����  ְ������  ֤������ �����ְ��״̬ ���ʻ��� ��λ�ɶ� ְ���ɶ� �ɶ�ϼ� ���ԭ�� ��*//*��λ��š���λ���ơ��������¡�*/
//�����2008616
package org.xpup.hafmis.syscollection.chgbiz.chgperson.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ChgPersonCellListListExportDTO implements ExpDto {

  private static final long serialVersionUID = 0L;

  private String chgType;// �������

  private String empId;// ְ�����

  private String name;// ְ������

  private String cardNum;// ֤������

  private String temp_oldPayStatus;// �����ְ��״̬

  private String salaryBase;// ���ʻ���

  private String orgPay;// ��λ�ɶ�

  private String empPay;// ְ���ɶ�

  private String sumPay;// �ɶ�ϼ�

  private String temp_chgreason;// ���ԭ��

  private String orgId;

  private String orgName;

  private String chgMonth;

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getEmpPay() {
    return empPay;
  }

  public void setEmpPay(String empPay) {
    this.empPay = empPay;
  }

  public String getOrgPay() {
    return orgPay;
  }

  public void setOrgPay(String orgPay) {
    this.orgPay = orgPay;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getSalaryBase() {
    return salaryBase;
  }

  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }

  public String getInfo(String s) {
    if (s.equals("empId"))
      return empId;
    if (s.equals("name"))
      return name;
    if (s.equals("cardNum"))
      return cardNum;

    if (s.equals("temp_oldPayStatus"))
      return temp_oldPayStatus;
    if (s.equals("salaryBase"))
      return salaryBase;
    if (s.equals("temp_chgreason"))
      return temp_chgreason;

    if (s.equals("salaryBase"))
      return salaryBase;
    if (s.equals("orgPay"))
      return orgPay;
    if (s.equals("empPay"))
      return empPay;
    if (s.equals("chgType"))
      return chgType;
    if (s.equals("sumPay"))
      return sumPay;
    
    else
      return null;
  }

  public String getChgType() {
    return chgType;
  }

  public void setChgType(String chgType) {
    this.chgType = chgType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSumPay() {
    return sumPay;
  }

  public void setSumPay(String sumPay) {
    this.sumPay = sumPay;
  }

  public String getTemp_chgreason() {
    return temp_chgreason;
  }

  public void setTemp_chgreason(String temp_chgreason) {
    this.temp_chgreason = temp_chgreason;
  }

  public String getTemp_oldPayStatus() {
    return temp_oldPayStatus;
  }

  public void setTemp_oldPayStatus(String temp_oldPayStatus) {
    this.temp_oldPayStatus = temp_oldPayStatus;
  }

}
