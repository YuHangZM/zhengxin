package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

/**
 * Copy Right Information : ��װ�������תҳ��ѯ������DTO Goldsoft Project :
 * CredenceFillinTcFindDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.25
 */
public class CredenceFillinTcFindDTO {
  
  /** ��Ŀ���� */
  private String subjectcode = "";

  /** ��Ŀ���� */
  private String subjectName = "";

  /** start��� */
  private String startMoney = "";

  /** end��� */
  private String endMoney = "";

  /** ���´� */
  private String office = "";

  /** ƾ֤���ڿ�ʼ */
  private String credenceDateStart = "";
  
  /** ƾ֤���ڽ��� */
  private String credenceDateEnd = "";
  
  public String getEndMoney() {
    return endMoney;
  }

  public void setEndMoney(String endMoney) {
    this.endMoney = endMoney;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getStartMoney() {
    return startMoney;
  }

  public void setStartMoney(String startMoney) {
    this.startMoney = startMoney;
  }

  public String getSubjectcode() {
    return subjectcode;
  }

  public void setSubjectcode(String subjectcode) {
    this.subjectcode = subjectcode;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
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
}
