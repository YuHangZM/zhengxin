package org.xpup.hafmis.sysloan.loancallback.destoryback.form;
import org.apache.struts.action.ActionForm;
public class DestoryBackTbWindowAF extends ActionForm {
  private String loanKouAcc = "";// �����˺�

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String cardKindName = ""; // ��ʾ֤�����Ͷ�Ӧ������

  private String cardNum = ""; // ֤������

  private String loanModeName="";//���ʽ
  

  
  private String backUnit= "";//�ջص�λ
  
  private String backunitName= "";//�ջص�λ����
  
  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
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

 
  public String getLoanModeName() {
    return loanModeName;
  }

  public void setLoanModeName(String loanModeName) {
    this.loanModeName = loanModeName;
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
  
}
