package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.dto;



/**
 * @author ��Ұ 2007-10-15
 */
public class LoanXieYiDTO {

  private String xieYiNum = null;// ͷ����ˮ��

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String borrowerCardNum = "";// ҵ������

  private String borrowerEmpId = null;// ԭʼҵ������

  private String fuzhuName = "";// �Ƶ���

  private String fuzhuCardNum = "";// ҵ��״̬

  private String fuzhuEmpId = null;// �ſ�����

  private String bizDate = null;// ��ʼ��������

  private String stopDate= null;// ��ֹ��������

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBorrowerCardNum() {
    return borrowerCardNum;
  }

  public void setBorrowerCardNum(String borrowerCardNum) {
    this.borrowerCardNum = borrowerCardNum;
  }

  public String getBorrowerEmpId() {
    return borrowerEmpId;
  }

  public void setBorrowerEmpId(String borrowerEmpId) {
    this.borrowerEmpId = borrowerEmpId;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getFuzhuCardNum() {
    return fuzhuCardNum;
  }

  public void setFuzhuCardNum(String fuzhuCardNum) {
    this.fuzhuCardNum = fuzhuCardNum;
  }

  public String getFuzhuEmpId() {
    return fuzhuEmpId;
  }

  public void setFuzhuEmpId(String fuzhuEmpId) {
    this.fuzhuEmpId = fuzhuEmpId;
  }

  public String getFuzhuName() {
    return fuzhuName;
  }

  public void setFuzhuName(String fuzhuName) {
    this.fuzhuName = fuzhuName;
  }

  public String getStopDate() {
    return stopDate;
  }

  public void setStopDate(String stopDate) {
    this.stopDate = stopDate;
  }

  public String getXieYiNum() {
    return xieYiNum;
  }

  public void setXieYiNum(String xieYiNum) {
    this.xieYiNum = xieYiNum;
  }
  
}
