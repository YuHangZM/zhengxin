/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.dataready.assistantorg.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 09-29-2007
 * 
 * XDoclet definition:
 * @struts.form name="notarialAF"
 */
public class NotarialAF extends ActionForm {
  private String assistantOrgId="";
  private String assistantOrgName="";
  private String artfclprsn="";
  private String code="";
  private String assistantOrgAddr="";
  private String basedDate="";
  private String artfclprsnCardKind="";
  private String artfclprsnCardNum="";
  private String allowDept="";
  private String allowId="";
  private String agreementStartDate="";
  private String agreementEndDate="";
  private BigDecimal registerFund=new BigDecimal(0.00);
  private String paybank="";
  private String payacc="";
  private String contactPrsn="";
  private String contactTel="";
  private String business="";
  private String remark="";
  private String arear="";
  private String type="";
  private String assistantOrgType="";
  private Map map=null;
  
  /**
   * 所属地区
   * 张列改  头
   */
  private Map regionMap = new HashMap();
  //张列改 尾
  
  /**
   * 状态
   * @return
   */
  public String getType() {
    return type;
  }
  /**
   * 状态
   * @return
   */
  public void setType(String type) {
    this.type = type;
  }
  /**
   * 所属地区
   * @return
   */
  public String getArear() {
    return arear;
  }
  /**
   * 所属地区
   * @return
   */
  public void setArear(String arear) {
    this.arear = arear;
  }
  /**
   * 备注
   * @return
   */
  public String getRemark() {
    return remark;
  }
  /**
   * 备注
   * @return
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }
  /**
   * 职务
   * @return
   */
  public String getBusiness() {
    return business;
  }
  /**
   * 职务
   * @return
   */
  public void setBusiness(String business) {
    this.business = business;
  }
  /**
   * 联系人 
   * @return
   */
  public String getContactPrsn() {
    return contactPrsn;
  }
  /**
   * 联系人 
   * @return
   */
  public void setContactPrsn(String contactPrsn) {
    this.contactPrsn = contactPrsn;
  }
  /**
   * 开户行帐号
   * @return
   */
  public String getPayacc() {
    return payacc;
  }
  /**
   * 开户行帐号
   * @return
   */
  public void setPayacc(String payacc) {
    this.payacc = payacc;
  }
  /**
   * 开户银行
   * @return
   */
  public String getPaybank() {
    return paybank;
  }
  /**
   * 开户银行
   * @return
   */
  public void setPaybank(String paybank) {
    this.paybank = paybank;
  }
  /**
   * 协议到期日期
   * @return
   */
  public String getAgreementEndDate() {
    return agreementEndDate;
  }
  /**
   * 协议到期日期
   * @return
   */
  public void setAgreementEndDate(String agreementEndDate) {
    this.agreementEndDate = agreementEndDate;
  }
  /**
   * 法人证件号码
   * @return
   */
  public String getArtfclprsnCardNum() {
    return artfclprsnCardNum;
  }
  /**
   * 法人证件号码
   * @return
   */
  public void setArtfclprsnCardNum(String artfclprsnCardNum) {
    this.artfclprsnCardNum = artfclprsnCardNum;
  }
  /**
   * 法人证件类型
   * @return
   */
  public String getArtfclprsnCardKind() {
    return artfclprsnCardKind;
  }
  /**
   * 法人证件类型
   * @return
   */
  public void setArtfclprsnCardKind(String artfclprsnCardKind) {
    this.artfclprsnCardKind = artfclprsnCardKind;
  }
  /**
   * 成立日期
   * @return
   */
public String getBasedDate() {
    return basedDate;
  }
/**
 * 成立日期
 * @return
 */
  public void setBasedDate(String basedDate) {
    this.basedDate = basedDate;
  }
/**
 * 法人代表
 * @return
 */
  public String getArtfclprsn() {
    return artfclprsn;
  }
  /**
   * 法人代表
   * @return
   */
  public void setArtfclprsn(String artfclprsn) {
    this.artfclprsn = artfclprsn;
  }
/**
 * 协作单位id
 * @return
 */
  public String getAssistantOrgId() {
    return assistantOrgId;
  }
  /**
   * 协作单位id
   * @return
   */
  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }
/**
 * 协作单位名称
 * @return
 */
  public String getAssistantOrgName() {
    return assistantOrgName;
  }
  /**
   * 协作单位名称
   * @return
   */
  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }

  public ActionErrors validate(ActionMapping mapping,
      HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  /** 
   * Method reset
   * @param mapping
   * @param request
   */
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
  }
  /**
   * 组织机构代码
   */
  public String getCode() {
    return code;
  }
  /**
   * 组织机构代码
   */
  public void setCode(String code) {
    this.code = code;
  }
  /**
   * 协作单位地址
   * @return
   */
  public String getAssistantOrgAddr() {
    return assistantOrgAddr;
  }
  /**
   * 协作单位地址
   * @return
   */
  public void setAssistantOrgAddr(String assistantOrgAddr) {
    this.assistantOrgAddr = assistantOrgAddr;
  }
  /**
   * 批准机关
   * @return
   */
  public String getAllowDept() {
    return allowDept;
  }
  /**
   * 批准机关
   * @return
   */
  public void setAllowDept(String allowDept) {
    this.allowDept = allowDept;
  }
  /**
   * 批准文号
   * @return
   */
  public String getAllowId() {
    return allowId;
  }
  /**
   * 批准文号
   * @return
   */
  public void setAllowId(String allowId) {
    this.allowId = allowId;
  }
  /**
   * 协议签订日期
   * @return
   */
  public String getAgreementStartDate() {
    return agreementStartDate;
  }
  /**
   * 协议签订日期
   * @return
   */
  public void setAgreementStartDate(String agreementStartDate) {
    this.agreementStartDate = agreementStartDate;
  }
  /**
   * 注册资金
   * @return
   */
  public BigDecimal getRegisterFund() {
    return registerFund;
  }
  /**
   * 注册资金
   * @return
   */
  public void setRegisterFund(BigDecimal registerFund) {
    this.registerFund = registerFund;
  }
  /**
   * 联系电话
   * @return
   */
  public String getContactTel() {
    return contactTel;
  }
  /**
   * 联系电话
   * @return
   */
  public void setContactTel(String contactTel) {
    this.contactTel = contactTel;
  }
  /**
   * 协作单位类型
   * @return
   */
  public String getAssistantOrgType() {
    return assistantOrgType;
  }
  /**
   * 协作单位类型
   * @return
   */
  public void setAssistantOrgType(String assistantOrgType) {
    this.assistantOrgType = assistantOrgType;
  }
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
  }
  public Map getRegionMap() {
    return regionMap;
  }
  public void setRegionMap(Map regionMap) {
    this.regionMap = regionMap;
  }
}