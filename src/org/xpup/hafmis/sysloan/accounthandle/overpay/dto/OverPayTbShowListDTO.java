package org.xpup.hafmis.sysloan.accounthandle.overpay.dto;

import java.math.BigDecimal;

public class OverPayTbShowListDTO {
  private String loankouacc="";//�����˺�
  private String docNum="";//ƾ֤���
  private String contractId="";//��ͬ���
  private String borrowerName="";//���������
  private String cardNum="";//֤������
  private BigDecimal occurMoney=new BigDecimal(0.00);//���˷�����
  private String bizSt="";//ҵ��״̬
  private String temp_bizSt="";
  private String flowHeadId="";//202���id
  
  public String getFlowHeadId() {
    return flowHeadId;
  }
  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }
  public String getTemp_bizSt() {
    return temp_bizSt;
  }
  public void setTemp_bizSt(String temp_bizSt) {
    this.temp_bizSt = temp_bizSt;
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
  public String getLoankouacc() {
    return loankouacc;
  }
  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }
  public BigDecimal getOccurMoney() {
    return occurMoney;
  }
  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }
}
