package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto;
/**
 * Copy Right Information : ��ѯҵ���շ�װ�˲�ѯ������DTO Goldsoft Project :
 * QueryOperationLogFindDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.1.29
 */
public class QueryOperationLogFindDTO {
  /** ƾ֤���� */
  private String credenceType = "";

  /** ����Ա */
  private String operator = "";

  /** ƾ֤���ڿ�ʼ */
  private String credenceDateStart = "";

  /** ƾ֤���ڽ��� */
  private String credenceDateEnd = "";

  /** ���� */
  private String action = "";

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getCredenceDateEnd() {
    return credenceDateEnd;
  }

  public void setCredenceDateEnd(String credenceDateEnd) {
    this.credenceDateEnd = credenceDateEnd;
  }

  public String getCredenceDateStart() {
    return credenceDateStart;
  }

  public void setCredenceDateStart(String credenceDateStart) {
    this.credenceDateStart = credenceDateStart;
  }

  public String getCredenceType() {
    return credenceType;
  }

  public void setCredenceType(String credenceType) {
    this.credenceType = credenceType;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
