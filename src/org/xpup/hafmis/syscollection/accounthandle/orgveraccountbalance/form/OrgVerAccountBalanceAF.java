/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceAF
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class OrgVerAccountBalanceAF extends ActionForm {

  private static final long serialVersionUID = -1619275823308491522L;
  
  private List list = null;

  private String id = null;
  
  private String orgId = "";// ��λ���
  
  private String orgName = "";// ��λ����
  
  private String accYear = "";// ��ת���

  private List accYearList = new ArrayList();// ��ת���������
  
  private String empId = "";// ְ�����
  
  private String empName = "";// ְ������
  
  private BigDecimal preBalanceCen = new BigDecimal(0.00);// �����������
  
  private BigDecimal curBalanceCen = new BigDecimal(0.00);// ���ı������
  
  private BigDecimal preBalanceOrg = new BigDecimal(0.00);// ��λ�������
  
  private BigDecimal curBalanceOrg = new BigDecimal(0.00);// ��λ�������

  public String getAccYear() {
    return accYear;
  }

  public void setAccYear(String accYear) {
    this.accYear = accYear;
  }

  public List getAccYearList() {
    return accYearList;
  }

  public void setAccYearList(List accYearList) {
    this.accYearList = accYearList;
  }

  public BigDecimal getCurBalanceCen() {
    return curBalanceCen;
  }

  public void setCurBalanceCen(BigDecimal curBalanceCen) {
    this.curBalanceCen = curBalanceCen;
  }

  public BigDecimal getCurBalanceOrg() {
    return curBalanceOrg;
  }

  public void setCurBalanceOrg(BigDecimal curBalanceOrg) {
    this.curBalanceOrg = curBalanceOrg;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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

  public BigDecimal getPreBalanceCen() {
    return preBalanceCen;
  }

  public void setPreBalanceCen(BigDecimal preBalanceCen) {
    this.preBalanceCen = preBalanceCen;
  }

  public BigDecimal getPreBalanceOrg() {
    return preBalanceOrg;
  }

  public void setPreBalanceOrg(BigDecimal preBalanceOrg) {
    this.preBalanceOrg = preBalanceOrg;
  }

  
}
