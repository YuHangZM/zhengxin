package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto;

import java.math.BigDecimal;

/**
 * ��װ�˵������в�ѯ������DTO
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountPopFindDTO {
  
  /**
   * ��ͬ���
   */
  private String contractId = "";
  /**
   * �����ʺ�
   */
  private String loanKouAcc = "";
  /**
   * ҵ������
   */
  private String bizDate = "";
  /**
   * ҵ������
   */
  private String bizType = "";
  /**
   * �ſ�����
   */
  private String loanBankId = "";
  /**
   * ���������
   */
  private String borrowerName = "";
  
  /**
   * ƾ֤��
   */
  private String docNum = "";
  
  public String getBizDate() {
    return bizDate;
  }
  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }
  public String getBizType() {
    return bizType;
  }
  public void setBizType(String bizType) {
    this.bizType = bizType;
  }
  public String getBorrowerName() {
    return borrowerName;
  }
  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoanKouAcc() {
    return loanKouAcc;
  }
  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  
  
}
