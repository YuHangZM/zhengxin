/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAccountPopAF
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-11-02
 * �޸�����                   :2007-11-13���Ӳ�ѯ������ְ����š���λ���
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class EmpAccountPopAF extends CriterionsAF {

  private static final long serialVersionUID = 6561833979666200055L;

  private List list = new ArrayList();

  private String id = null;// AA101.ID

  private String docNum = null;// ƾ֤���

  private String bizType = null;// ҵ������

  private String settDateA = null;// ����ʱ��
  private String settDateB = null;// ����ʱ��

  private String empId = null;// ְ�����

  private String orgId = null;// ��λ���

  private Map bstypeMap=new HashMap();
  private BigDecimal debit = new BigDecimal(0.00);// �跽������

  private BigDecimal credit = new BigDecimal(0.00);// ����������

  private BigDecimal interest = new BigDecimal(0.00);// ��Ϣ

  private BigDecimal debitTotal = new BigDecimal(0.00);// �跽������

  private BigDecimal creditTotal = new BigDecimal(0.00);// ����������

  private BigDecimal interestTotal = new BigDecimal(0.00);// ��Ϣ

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getCreditTotal() {
    return creditTotal;
  }

  public void setCreditTotal(BigDecimal creditTotal) {
    this.creditTotal = creditTotal;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public BigDecimal getDebitTotal() {
    return debitTotal;
  }

  public void setDebitTotal(BigDecimal debitTotal) {
    this.debitTotal = debitTotal;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public BigDecimal getInterestTotal() {
    return interestTotal;
  }

  public void setInterestTotal(BigDecimal interestTotal) {
    this.interestTotal = interestTotal;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }



  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.bizType = "";
    this.settDateA = "";
    this.settDateB = "";
  }

  public Map getBstypeMap() {
    return bstypeMap;
  }

  public void setBstypeMap(Map bstypeMap) {
    this.bstypeMap = bstypeMap;
  }

  public String getSettDateA() {
    return settDateA;
  }

  public void setSettDateA(String settDateA) {
    this.settDateA = settDateA;
  }

  public String getSettDateB() {
    return settDateB;
  }

  public void setSettDateB(String settDateB) {
    this.settDateB = settDateB;
  }

}
