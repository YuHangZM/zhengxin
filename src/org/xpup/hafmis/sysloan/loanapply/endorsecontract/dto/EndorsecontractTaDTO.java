package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

import java.util.HashMap;
import java.util.Map;

public class EndorsecontractTaDTO {
  private Map beentrusterMap = new HashMap();

  private String contractId = ""; // ��ͬID

  private String entruster = ""; // ί�з� (�׷� ��������)

  private String beentruster = "";// ���з�

  private String assurer = "";// ��֤��

  private String debitter = "";// ��

  private String certificateType = "";// ֤������

  private String certificateNum = "";// ֤������

  private String debitMoney = "";// �����

  private String term = "";// �������

  private String monthInterest = "";// ÿ������

  private String creditType = "";// ���ʽ

  private String contractSureDate = "";// ��ͬǩ������

  private String debitMoneyStaDate = "";// �����ʼ����

  private String debitMoneyEndDate = "";// �����ֹ����

  private String assistantOrgId = "";// ������˾���

  private String office = "";// ���´�

  private String photoUrl = "";// ·��

  private String corpusInterest = "";// �»���Ϣ

  private String isWrite = "";// �Ƿ�ǩ����ͬ

  private String contractSt = "";// ��ͬ״̬

  private String loanKouAcc = "";

  private String paramValue = "";// ����ֵAB or BA

  private String writeType = "";// �����ж��Ƿ�ǩ����ͬ�����õ�����

  private String isComeFromT5 = "";// �����ж��Ƿ�����ά��������

  public String getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getAssistantOrgId() {
    return assistantOrgId;
  }

  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }

  public String getAssurer() {
    return assurer;
  }

  public void setAssurer(String assurer) {
    this.assurer = assurer;
  }

  public String getBeentruster() {
    return beentruster;
  }

  public void setBeentruster(String beentruster) {
    this.beentruster = beentruster;
  }

  public Map getBeentrusterMap() {
    return beentrusterMap;
  }

  public void setBeentrusterMap(Map beentrusterMap) {
    this.beentrusterMap = beentrusterMap;
  }

  public String getCertificateNum() {
    return certificateNum;
  }

  public void setCertificateNum(String certificateNum) {
    this.certificateNum = certificateNum;
  }

  public String getCertificateType() {
    return certificateType;
  }

  public void setCertificateType(String certificateType) {
    this.certificateType = certificateType;
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

  public String getContractSureDate() {
    return contractSureDate;
  }

  public void setContractSureDate(String contractSureDate) {
    this.contractSureDate = contractSureDate;
  }

  public String getCreditType() {
    return creditType;
  }

  public void setCreditType(String creditType) {
    this.creditType = creditType;
  }

  public String getDebitMoney() {
    return debitMoney;
  }

  public void setDebitMoney(String debitMoney) {
    this.debitMoney = debitMoney;
  }

  public String getDebitMoneyEndDate() {
    return debitMoneyEndDate;
  }

  public void setDebitMoneyEndDate(String debitMoneyEndDate) {
    this.debitMoneyEndDate = debitMoneyEndDate;
  }

  public String getDebitMoneyStaDate() {
    return debitMoneyStaDate;
  }

  public void setDebitMoneyStaDate(String debitMoneyStaDate) {
    this.debitMoneyStaDate = debitMoneyStaDate;
  }

  public String getDebitter() {
    return debitter;
  }

  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }

  public String getEntruster() {
    return entruster;
  }

  public void setEntruster(String entruster) {
    this.entruster = entruster;
  }

  public String getIsComeFromT5() {
    return isComeFromT5;
  }

  public void setIsComeFromT5(String isComeFromT5) {
    this.isComeFromT5 = isComeFromT5;
  }

  public String getIsWrite() {
    return isWrite;
  }

  public void setIsWrite(String isWrite) {
    this.isWrite = isWrite;
  }

  public String getMonthInterest() {
    return monthInterest;
  }

  public void setMonthInterest(String monthInterest) {
    this.monthInterest = monthInterest;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getParamValue() {
    return paramValue;
  }

  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getWriteType() {
    return writeType;
  }

  public void setWriteType(String writeType) {
    this.writeType = writeType;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }
}
