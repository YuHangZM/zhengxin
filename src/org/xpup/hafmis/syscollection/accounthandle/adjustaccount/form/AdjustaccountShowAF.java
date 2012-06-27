package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;


/**
 * 
 * @author ����
 *2007-6-27
 */
public class AdjustaccountShowAF extends ActionForm{

  private static final long serialVersionUID = 0L;
  
  private EmpHAFAccountFlow  empHAFAccountFlow=new EmpHAFAccountFlow ();
  private AdjustWrongAccountTail adjustWrongAccountTail=new AdjustWrongAccountTail();
  private String bizDocNum="";//����ҵ��ƾ֤��
  private String bizNoteNum="";//����ҵ��Ʊ�ݺ�
  private String date=""; //����
  private String orgId=""; //��λid
  private String orgName=""; //��λ��
  private String type=""; //��ť���
  private BigDecimal total=new BigDecimal(0.00); //���
  private List list;
  private List adjustAccountlist;
  private String noteNum = "";//�����
  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getBizDocNum() {
    return bizDocNum;
  }

  public void setBizDocNum(String bizDocNum) {
    this.bizDocNum = bizDocNum;
  }

  public String getBizNoteNum() {
    return bizNoteNum;
  }

  public void setBizNoteNum(String bizNoteNum) {
    this.bizNoteNum = bizNoteNum;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    this.bizDocNum="";
    this.bizNoteNum="";
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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

  public EmpHAFAccountFlow getEmpHAFAccountFlow() {
    return empHAFAccountFlow;
  }

  public void setEmpHAFAccountFlow(EmpHAFAccountFlow empHAFAccountFlow) {
    this.empHAFAccountFlow = empHAFAccountFlow;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public List getAdjustAccountlist() {
    return adjustAccountlist;
  }

  public void setAdjustAccountlist(List adjustAccountlist) {
    this.adjustAccountlist = adjustAccountlist;
  }

  public AdjustWrongAccountTail getAdjustWrongAccountTail() {
    return adjustWrongAccountTail;
  }

  public void setAdjustWrongAccountTail(
      AdjustWrongAccountTail adjustWrongAccountTail) {
    this.adjustWrongAccountTail = adjustWrongAccountTail;
  }
  
  
  
}
