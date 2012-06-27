package org.xpup.hafmis.orgstrct.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.xpup.common.domain.DomainObject;
import org.xpup.hafmis.orgstrct.domain.enums.OUTypeEnum;

public class OrganizationUnit extends DomainObject {

  private static final long serialVersionUID = 1L;

  /**
   * ��֯��Ԫ����
   */
  private String name = null;

  /**
   * ��֯��Ԫ����
   */
  private String description = null;

  /**
   * ��֯����
   */
  private int type = 1;

  /**
   * λ��ͬ�������е�λ��
   */
  private int position = 0;

  /**
   * ��ʹ�õ���֯��Ԫ����ģ��
   */
  private String ouptId = null;

  private List attributes = new ArrayList();

  private List parameters = new ArrayList();

  /**
   * ��֯��Ԫ���ϼ���֯��Ԫ
   */
  private OrganizationUnit parent = null;

  /**
   * ��֯��λ���¼���֯��Ԫ
   */
  private Set subOrgUnits = new LinkedHashSet();

  public List getAttributes() {
    return attributes;
  }

  public void setAttributes(List attributes) {
    this.attributes = attributes;
  }

  public List getParameters() {
    return parameters;
  }

  public void setParameters(List parameters) {
    this.parameters = parameters;
  }

  public List getOrgUnitProperties() {
    List all = new ArrayList();
    all.addAll(getParameters());
    all.addAll(getAttributes());
    return all;
  }

  public String getOuptId() {
    return ouptId;
  }

  public void setOuptId(String ouptId) {
    this.ouptId = ouptId;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public Set getSubOrgUnits() {
    return subOrgUnits;
  }

  public void setSubOrgUnits(Set subOrgUnits) {
    this.subOrgUnits = subOrgUnits;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OrganizationUnit getParent() {
    return parent;
  }

  public void setParent(OrganizationUnit parent) {
    this.parent = parent;
  }

  public boolean isRoot() {
    return getParent() != null ? false : true;
  }

  public boolean isLeaf() {
    int count = getSubOrgUnits().size();
    return count > 0 ? false : true;
  }

  public OUTypeEnum getType() {
    return OUTypeEnum.getEnum(type);
  }

  public void setType(OUTypeEnum type) {
    this.type = type.getValue();
  }

}
