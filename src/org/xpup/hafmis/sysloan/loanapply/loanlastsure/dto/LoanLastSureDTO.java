package org.xpup.hafmis.sysloan.loanapply.loanlastsure.dto;

/**
 * @author ��Ұ 2007-09-27
 */
public class LoanLastSureDTO {

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String cardNum = "";// ֤������

  private String loanMoney = "";// �����

  private String loanTimeLimit = "";// �������

  private String loanBankName = "";// �ſ�����

  private String orgName = "";// ��λ����

  private String houseArea = "";// ���������M2��

  private String houseType = "";// ��������

  private String contractSt = "";// ��ͬ״̬
  
  private String assistantborrowerName = "";//��ż����
  
  private String type="";//�Ƿ�Ϊ�������ˡ���Ϊ�������ˣ�����Ϊ��������

  public String getAssistantborrowerName() {
    return assistantborrowerName;
  }

  public void setAssistantborrowerName(String assistantborrowerName) {
    this.assistantborrowerName = assistantborrowerName;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(String houseArea) {
    this.houseArea = houseArea;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(String loanMoney) {
    this.loanMoney = loanMoney;
  }

  public String getLoanTimeLimit() {
    return loanTimeLimit;
  }

  public void setLoanTimeLimit(String loanTimeLimit) {
    this.loanTimeLimit = loanTimeLimit;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  
}
