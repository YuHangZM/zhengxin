package org.xpup.hafmis.orgstrct.domain;

import org.xpup.common.domain.DomainObject;

public class OrgUnitProperty extends DomainObject {

  private static final long serialVersionUID = 1L;

  /**
   * �Ƿ��Ѿ�����
   */
  private boolean saved = true;

  /**
   * ���Ի������ȡֵ
   */
  private String value = null;

  /**
   * ������������֯��Ԫ
   */
  private OrganizationUnit organizationUnit = new OrganizationUnit();

  /**
   * ��������Ӧ��ģ����
   */
  private OuptItem ouptItem = new OuptItem();

  public boolean isSaved() {
    return saved;
  }

  public void setSaved(boolean saved) {
    this.saved = saved;
  }

  public OuptItem getOuptItem() {
    return ouptItem;
  }

  public void setOuptItem(OuptItem ouptItem) {
    this.ouptItem = ouptItem;
  }

  public OrganizationUnit getOrganizationUnit() {
    return organizationUnit;
  }

  public void setOrganizationUnit(OrganizationUnit organizationUnit) {
    this.organizationUnit = organizationUnit;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
