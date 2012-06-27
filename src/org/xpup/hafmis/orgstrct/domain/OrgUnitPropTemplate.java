package org.xpup.hafmis.orgstrct.domain;

import org.xpup.common.domain.DomainObject;

public class OrgUnitPropTemplate extends DomainObject {

  private static final long serialVersionUID = 1L;

  /**
   * ģ������
   */
  private String name = null;

  /**
   * �ڲ�����
   */
  private String innerName = null;

  /**
   * ģ������
   */
  private String description = null;

  public String getInnerName() {
    return innerName;
  }

  public void setInnerName(String innerName) {
    this.innerName = innerName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
