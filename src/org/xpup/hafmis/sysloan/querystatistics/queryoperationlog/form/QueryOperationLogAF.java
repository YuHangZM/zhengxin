package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO;

public class QueryOperationLogAF extends ActionForm{


  private static final long serialVersionUID = 2531807195056023196L;

//  LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();

  private List list = null;// ��ʾ�б�
  
  private List printList = null;// ��ӡ�б�

  private Map bizType = new HashMap();// ҵ������

  private Map bizStatus = new HashMap();// ;/ҵ��״̬
  
  private String bizTypeValue = null;// ����
  
  private String bizStatusValue = null;// ״̬
  
  private List operatorList = null;// ;/����Ա
  
  private String operatorValue = null;// ;/����Ա
  
  private String beginTime = null;// ��ʼʱ��
  
  private String endTime = null;// ����ʱ��
  
  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.bizStatusValue="";
    this.bizTypeValue="";
    this.beginTime="";
    this.endTime="";
    this.operatorValue="";
  }

  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public Map getBizStatus() {
    return bizStatus;
  }

  public void setBizStatus(Map bizStatus) {
    this.bizStatus = bizStatus;
  }

  public String getBizStatusValue() {
    return bizStatusValue;
  }

  public void setBizStatusValue(String bizStatusValue) {
    this.bizStatusValue = bizStatusValue;
  }

  public Map getBizType() {
    return bizType;
  }

  public void setBizType(Map bizType) {
    this.bizType = bizType;
  }

  public String getBizTypeValue() {
    return bizTypeValue;
  }

  public void setBizTypeValue(String bizTypeValue) {
    this.bizTypeValue = bizTypeValue;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }



  public List getOperatorList() {
    return operatorList;
  }

  public void setOperatorList(List operatorList) {
    this.operatorList = operatorList;
  }

  public String getOperatorValue() {
    return operatorValue;
  }

  public void setOperatorValue(String operatorValue) {
    this.operatorValue = operatorValue;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }
  
}
