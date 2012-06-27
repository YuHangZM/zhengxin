/**
 * Copy Right Information   : Goldsoft 
 * Project                  : BailenRolTaPrintDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   : 2007-10-30
 **/
package org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto;

import java.math.BigDecimal;

public class BailenRolTaPrintDTO {

  private String contractId = ""; // ��ͬID

  private String borrowerName = ""; // ���������

  private String cardKind = ""; // ֤������

  private String cardNum = ""; // ֤������

  private String loanBankName = ""; // �տ�����

  private String loanKouAcc = ""; // �տ������ʺ�

  private String bizDate = ""; // ��ȡ����

  private String docNum = ""; // ƾ֤��

  private String noteNum = ""; // Ʊ�ݺ�
  
  private String userName = ""; //�Ƶ���
  
  private String userBizDate = "";// ҵ������

  private BigDecimal occurMoney = new BigDecimal(0.00);// ��֤����

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getUserBizDate() {
    return userBizDate;
  }

  public void setUserBizDate(String userBizDate) {
    this.userBizDate = userBizDate;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
