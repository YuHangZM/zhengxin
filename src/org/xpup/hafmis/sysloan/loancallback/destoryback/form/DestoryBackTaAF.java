package org.xpup.hafmis.sysloan.loancallback.destoryback.form;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DestoryBackTaAF extends ActionForm {
  private String loanKouAcc = "";// �����˺�

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String cardKind = ""; // ֤������

  private String cardKindName = ""; // ��ʾ֤�����Ͷ�Ӧ������

  private String cardNum = ""; // ֤������

  private BigDecimal overplusLoanMoney = new BigDecimal(0.00);//ʣ�౾��

  private String loanMode = "";//���ʽ

  private String loanModeName = "";//���ʽ

  private BigDecimal noBackMoney = new BigDecimal(0.00);//����δ�ջؽ��

  private BigDecimal backMoney = new BigDecimal(0.00);//�ջؽ��

  private String backUnit = "";//�ջص�λ
  
  private String assistantOrgId = "";//�ջص�λ����

  private String loanassistantorgId = "";// �ջص�λID

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

  public String getCardKindName() {
    return cardKindName;
  }

  public void setCardKindName(String cardKindName) {
    this.cardKindName = cardKindName;
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

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
  }

  public BigDecimal getNoBackMoney() {
    return noBackMoney;
  }

  public void setNoBackMoney(BigDecimal noBackMoney) {
    this.noBackMoney = noBackMoney;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
  }

  public BigDecimal getBackMoney() {
    return backMoney;
  }

  public void setBackMoney(BigDecimal backMoney) {
    this.backMoney = backMoney;
  }

  public String getBackUnit() {
    return backUnit;
  }

  public void setBackUnit(String backUnit) {
    this.backUnit = backUnit;
  }

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    loanKouAcc = "";// �����˺�

    contractId = "";// ��ͬ���

    borrowerName = "";// ���������

    cardKind = ""; // ֤������

    cardKindName = ""; // ��ʾ֤�����Ͷ�Ӧ������

    cardNum = ""; // ֤������

    overplusLoanMoney = new BigDecimal(0.00);//ʣ�౾��

    loanMode = "";//���ʽ

    loanModeName = "";//���ʽ

    noBackMoney = new BigDecimal(0.00);//����δ�ջؽ��

    backMoney = new BigDecimal(0.00);//�ջؽ��

    backUnit = "";//�ջص�λ

    assistantOrgId = "";//�ջص�λ����

    loanassistantorgId = "";// �ջص�λID
  }

  public String getAssistantOrgId() {
    return assistantOrgId;
  }

  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }

}
