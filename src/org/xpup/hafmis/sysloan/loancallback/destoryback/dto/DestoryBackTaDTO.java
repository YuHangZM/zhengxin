package org.xpup.hafmis.sysloan.loancallback.destoryback.dto;

import java.math.BigDecimal;

public class DestoryBackTaDTO {

  private String flowHeadId = ""; // pl202ͷ��Id
  
  private String loanKouAcc = "";// �����˺�

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������
  
  private String cardKind = ""; // ֤������

  private String cardKindName = ""; // ��ʾ֤�����Ͷ�Ӧ������

  private String cardNum = ""; // ֤������
  
  private BigDecimal overplusLoanMoney=new BigDecimal(0.00);//ʣ�౾��
  
  private String loanMode="";//���ʽ
  
  private String loanModeName="";//���ʽ
  
  private BigDecimal noBackMoney=new BigDecimal(0.00);//����δ�ջؽ��
  
  private BigDecimal backMoney=new BigDecimal(0.00);//�ջؽ��
  
  private String backUnit= "";//�ջص�λ
  
  private String backunitName= "";//�ջص�λ����
  
  private String loanassistantorgId= "";// �ջص�λID

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

  public String getBackunitName() {
    return backunitName;
  }

  public void setBackunitName(String backunitName) {
    this.backunitName = backunitName;
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

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }
  
}
