package org.xpup.hafmis.sysloan.loancallback.contractchange.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class ContractchangeAF extends ActionForm{
  private List list = new ArrayList();
  
  private String contractId = "";//��ͬ���
  private String borrowerName = "";//���������
  private String cardNum = "";//֤������
  private String startDate = "";//��ʼʱ��
  private String endDate = "";//����ʱ��
  
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
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  
  
}
