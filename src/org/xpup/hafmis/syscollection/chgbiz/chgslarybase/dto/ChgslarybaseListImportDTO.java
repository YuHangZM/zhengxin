/*��λ��š���λ���ơ��������¡�ְ����š�ְ��������֤�����롢����ǰ���ʻ������������ʻ������գ���*/
package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;


public class ChgslarybaseListImportDTO extends impDto{

  private static final long serialVersionUID = 0L;

  private String empId;

  private String empName;

  private String cardNum;
  private String oldSalaryBase;
  private String salaryBase;

 
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


  public String getEmpName() {
    return empName;
  }


  public void setEmpName(String empName) {
    this.empName = empName;
  }


  public String getOldSalaryBase() {
    return oldSalaryBase;
  }


  public void setOldSalaryBase(String oldSalaryBase) {
    this.oldSalaryBase = oldSalaryBase;
  }


  public String getSalaryBase() {
    return salaryBase;
  }


  public void setSalaryBase(String salaryBase) {
    this.salaryBase = salaryBase;
  }




}
