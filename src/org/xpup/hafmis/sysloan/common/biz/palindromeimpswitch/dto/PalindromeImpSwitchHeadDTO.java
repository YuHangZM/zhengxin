package org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class PalindromeImpSwitchHeadDTO extends impDto{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String payDate = null;//��������
  private String bankId = null;//���б��
  private String bizType = null;//ҵ������
  public String getBankId() {
    return bankId;
  }
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }
  public String getBizType() {
    return bizType;
  }
  public void setBizType(String bizType) {
    this.bizType = bizType;
  }
  public String getPayDate() {
    return payDate;
  }
  public void setPayDate(String payDate) {
    this.payDate = payDate;
  }
}
