package org.xpup.hafmis.sysfinance.common.biz.credencepop.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : ��װ�˵�������ժҪ������ժҪ���������б����Ϣ��DTO Goldsoft Project :
 * CredencePopListDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.11.3
 */
public class CredencePopListDTO {

  /** ժҪ */
  private String summay = "";

  /** ����ժҪ */
  private String freeSummay = "";

  /** ��Ŀ���� */
  private String subjectCode = "";

  /** �跽��� */
  private BigDecimal debit = new BigDecimal(0.00);

  /** ������� */
  private BigDecimal credit = new BigDecimal(0.00);
  
  /** ��Ŀ���� */
  private String subjectName = "";

  private String settnum="";
  
  public String getSettnum() {
    return settnum;
  }

  public void setSettnum(String settnum) {
    this.settnum = settnum;
  }

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

  public String getFreeSummay() {
    return freeSummay;
  }

  public void setFreeSummay(String freeSummay) {
    this.freeSummay = freeSummay;
  }

  public String getSubjectCode() {
    return subjectCode;
  }

  public void setSubjectCode(String subjectCode) {
    this.subjectCode = subjectCode;
  }

  public String getSummay() {
    return summay;
  }

  public void setSummay(String summay) {
    this.summay = summay;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }
}
