package org.xpup.hafmis.sysloan.specialbiz.bailenrol.form;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author ��Ұ 2007-09-30
 */
public class BailenRolTaAF extends ActionForm {

  private static final long serialVersionUID = -8965013081352843971L;

  private String contractId = ""; // ��ͬID

  private String borrowerName = ""; // ���������

  private String cardKind = ""; // ֤������

  private String cardNum = ""; // ֤������

  private String loanBankName = ""; // �տ�����

  private String loanBankId = ""; // �տ�����(������)

  private String loanKouAcc = ""; // �տ������˺�PL002.LOAN_ACCί�д����˺�

  private String loanKouAccHidden = ""; // ���������˺�

  private String bizDate = ""; // ��ȡ����

  private BigDecimal occurMoney = new BigDecimal(0.00);// ��֤����

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    this.contractId = "";
    this.borrowerName = "";
    this.cardKind = "";
    this.cardNum = "";
    this.loanBankName = "";
    this.loanBankId = "";
    this.loanKouAcc = "";
    this.loanKouAccHidden = "";
    this.bizDate = "";
    this.occurMoney = new BigDecimal(0.00);
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

  public String getLoanKouAccHidden() {
    return loanKouAccHidden;
  }

  public void setLoanKouAccHidden(String loanKouAccHidden) {
    this.loanKouAccHidden = loanKouAccHidden;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

}
