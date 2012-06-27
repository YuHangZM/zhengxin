package org.xpup.hafmis.sysloan.accounthandle.overpay.dto;

import java.util.HashMap;
import java.util.Map;

public class OverPayTbFindDTO {
  private String loankouacc="";//�����˺�
  private String contractId="";//��ͬ���
  private String borrowerName="";//���������
  private String cardNum="";//֤������
  private String docNum="";//ƾ֤���
  private String loanBankId="";//�ſ�����
  private String bizSt="";//ҵ��״̬
  private Map bizStMap=new HashMap();
  public Map getBizStMap() {
    return bizStMap;
  }
  public void setBizStMap(Map bizStMap) {
    this.bizStMap = bizStMap;
  }
  public String getBizSt() {
    return bizSt;
  }
  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
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
  public String getDocNum() {
    return docNum;
  }
  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }
  public String getLoanBankId() {
    return loanBankId;
  }
  public void setLoanBankId(String loanBankId) {
    this.loanBankId = loanBankId;
  }
  public String getLoankouacc() {
    return loankouacc;
  }
  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }
  public void reset() {
    this.loankouacc = "";
    this.contractId = "";
    this.borrowerName = "";
    this.cardNum = "";
    this.loanBankId = "";
    this.docNum = "";
    this.bizSt = "";
  }
}
