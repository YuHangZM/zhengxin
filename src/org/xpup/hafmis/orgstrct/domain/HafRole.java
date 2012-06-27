package org.xpup.hafmis.orgstrct.domain;

import java.io.Serializable;

import org.xpup.security.common.domain.Role;

public class HafRole extends Role {

  private static final long serialVersionUID = 3237984769505328006L;

  private String description = null;

  /**
   * ��������֯��Ԫ
   */
  private OrganizationUnit organizationUnit = new OrganizationUnit();

  /**
   * ���´�ID
   */
  private Serializable officeId = null;

  /**
   * ����ID
   */
  private Serializable departmentId = null;

  /**
   * ����ԱID
   */
  private Serializable operatorId = null;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OrganizationUnit getOrganizationUnit() {
    return organizationUnit;
  }

  public void setOrganizationUnit(OrganizationUnit organizationUnit) {
    this.organizationUnit = organizationUnit;
  }

  public Serializable getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Serializable departmentId) {
    this.departmentId = departmentId;
  }

  public Serializable getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Serializable officeId) {
    this.officeId = officeId;
  }

  public Serializable getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Serializable operatorId) {
    this.operatorId = operatorId;
  }

}
