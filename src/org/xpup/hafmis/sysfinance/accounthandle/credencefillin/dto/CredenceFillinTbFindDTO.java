package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

/**
 * Copy Right Information : ��װ���Զ�ת�ʲ�ѯ������DTO 
 * Goldsoft Project : CredenceFillinTbFindDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.17
 */
public class CredenceFillinTbFindDTO {

  /** ��������Start */
  private String settDateStart = "";

  /** ��������End */
  private String settDateEnd = "";

  /** ����� */
  private String settNum = "";

  /** ҵ��״̬ */
  private String bizSt = "";

  /** ҵ������ */
  private String bizType = "";
  
  /** ���� */
  private String bankId = "";

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getSettDateEnd() {
    return settDateEnd;
  }

  public void setSettDateEnd(String settDateEnd) {
    this.settDateEnd = settDateEnd;
  }

  public String getSettDateStart() {
    return settDateStart;
  }

  public void setSettDateStart(String settDateStart) {
    this.settDateStart = settDateStart;
  }

  public String getSettNum() {
    return settNum;
  }

  public void setSettNum(String settNum) {
    this.settNum = settNum;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

}
