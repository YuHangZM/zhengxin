package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.dto;

public class QueryOperationLogDTO {
  private String bizid="";//�����˺�
  private String type="";//��ͬ���
  private String operator="";//�����˺�
  private String opTime="";//��ͬ���
  private String action="";//�����˺�
  private String typenum="";//��ͬ���
  private String contract="";//��ͬ���
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }
  public String getBizid() {
    return bizid;
  }
  public void setBizid(String bizid) {
    this.bizid = bizid;
  }
  public String getOperator() {
    return operator;
  }
  public void setOperator(String operator) {
    this.operator = operator;
  }
  public String getOpTime() {
    return opTime;
  }
  public void setOpTime(String opTime) {
    this.opTime = opTime;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getTypenum() {
    return typenum;
  }
  public void setTypenum(String typenum) {
    this.typenum = typenum;
  }
  public String getContract() {
    return contract;
  }
  public void setContract(String contract) {
    this.contract = contract;
  }
  
  
  
}
