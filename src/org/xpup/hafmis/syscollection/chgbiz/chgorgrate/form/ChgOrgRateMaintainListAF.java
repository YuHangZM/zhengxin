package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;

public class ChgOrgRateMaintainListAF extends ActionForm{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String type = "";// ����

  private String id = "";// ��λ���

  private String name = "";// ��λ����

  private String date = "";// �������
  
  private String endDate = "";// �������

  private ChgOrgRate chgOrgRate = new ChgOrgRate();

  private List list = new ArrayList();

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public void reset(ActionMapping actionMapping,
      HttpServletRequest httpServletRequest) {
    this.type = "";// ����
    this.id = "";// ��λ���
    this.name = "";// ��λ����
    this.date = "";// �������
    this.chgOrgRate = new ChgOrgRate();
    this.list = new ArrayList();
  }

  public ChgOrgRate getChgOrgRate() {
    return chgOrgRate;
  }

  public void setChgOrgRate(ChgOrgRate chgOrgRate) {
    this.chgOrgRate = chgOrgRate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
