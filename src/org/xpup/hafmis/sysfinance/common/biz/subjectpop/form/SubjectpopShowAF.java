package org.xpup.hafmis.sysfinance.common.biz.subjectpop.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SubjectpopShowAF extends ActionForm {
  
  private static final long serialVersionUID = 7957498854719480098L;
  
  
  private List list=new ArrayList();
  private String id="";//��Ŀ��ˮ��
  private String code="";//��Ŀ����
  private String name="";//��Ŀ����
  private String direction="";//����
  private String sortcode="";//��Ŀ���
  private Map sortcodeMap=new HashMap();
  private String property="";//��Ŀ����
  private Map propertyMap=new HashMap();
  private String sortcodeflag="";//��Ŀ����ʶ:0~4
  private String actionflag="";//����������ʶ:0����ӣ�1���޸ģ�
  private String radioname="";//��ѡ��ť

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.code="";
    this.name="";
    this.direction="";
    this.sortcode="";
    this.property="";
  }
  public String getRadioname() {
    return radioname;
  }
  public void setRadioname(String radioname) {
    this.radioname = radioname;
  }
  public String getActionflag() {
    return actionflag;
  }
  public void setActionflag(String actionflag) {
    this.actionflag = actionflag;
  }
  public String getSortcodeflag() {
    return sortcodeflag;
  }
  public void setSortcodeflag(String sortcodeflag) {
    this.sortcodeflag = sortcodeflag;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getDirection() {
    return direction;
  }
  public void setDirection(String direction) {
    this.direction = direction;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getProperty() {
    return property;
  }
  public void setProperty(String property) {
    this.property = property;
  }
  public String getSortcode() {
    return sortcode;
  }
  public void setSortcode(String sortcode) {
    this.sortcode = sortcode;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Map getPropertyMap() {
    return propertyMap;
  }
  public void setPropertyMap(Map propertyMap) {
    this.propertyMap = propertyMap;
  }
  public Map getSortcodeMap() {
    return sortcodeMap;
  }
  public void setSortcodeMap(Map sortcodeMap) {
    this.sortcodeMap = sortcodeMap;
  }
  

}
