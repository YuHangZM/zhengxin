package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : ��װ�������ת�б����ݵ�DTO Goldsoft Project :
 * CredenceFillinTcListDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.25
 */
public class CredenceFillinTcListDTO {
  /** ��Ŀ���� */
  private String subjectcode = "";

  /** ��Ŀ���� */
  private String subjectName = "";

  /** �跽��� */
  private BigDecimal credit = new BigDecimal(0.00);

  /** ������� */
  private BigDecimal debit = new BigDecimal(0.00);

  /** ��FN202�еõ���ͬ��Ŀ���룬ͬ���´��µĽ���� */
  private BigDecimal sumCredit = new BigDecimal(0.00);

  /** ��FN202�еõ���ͬ��Ŀ���룬ͬ���´��µĴ����� */
  private BigDecimal sumDebit = new BigDecimal(0.00);

  /** ���´� */
  private String office = "";

  /** ���´�str */
  private String strOffice = "";

  /** PL202����ת���� */
  private String bySubjectDirection = "";
  
  /** PL110�ķ��� */
  private String balanceDirection = "";

  public BigDecimal getCredit() {
    return credit;
  }

  public void setCredit(BigDecimal credit) {
    this.credit = credit;
  }

  public BigDecimal getDebit() {
    return debit;
  }

  public void setDebit(BigDecimal debit) {
    this.debit = debit;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
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

  public String getBySubjectDirection() {
    return bySubjectDirection;
  }

  public void setBySubjectDirection(String bySubjectDirection) {
    this.bySubjectDirection = bySubjectDirection;
  }

  public String getStrOffice() {
    return strOffice;
  }

  public void setStrOffice(String strOffice) {
    this.strOffice = strOffice;
  }

  public BigDecimal getSumCredit() {
    return sumCredit;
  }

  public void setSumCredit(BigDecimal sumCredit) {
    this.sumCredit = sumCredit;
  }

  public BigDecimal getSumDebit() {
    return sumDebit;
  }

  public void setSumDebit(BigDecimal sumDebit) {
    this.sumDebit = sumDebit;
  }

  public String getBalanceDirection() {
    return balanceDirection;
  }

  public void setBalanceDirection(String balanceDirection) {
    this.balanceDirection = balanceDirection;
  }

}
