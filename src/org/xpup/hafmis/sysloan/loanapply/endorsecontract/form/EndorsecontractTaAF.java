package org.xpup.hafmis.sysloan.loanapply.endorsecontract.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EndorsecontractTaAF extends ActionForm {
  private String iscontactid = "";// �Ƿ���ǩ����ͬ������ҳ���ϵĲ�����ǩ����ť�Ƿ����

  private List bankList = new ArrayList();//

  private List loanBankNameList = new ArrayList();

  private Map beentrusterMap = new HashMap();

  private String contractId = ""; // ��ͬID

  private String entruster = ""; // ί�з� (�׷� ��������)

  private String bankName = "";

  private String beentruster = "";// ���з�

  private String assurer = "";// ��֤��

  private String debitter = "";// ��

  private String certificateType = "";// ֤������

  private String certificateNum = "";// ֤������

  private String debitMoney = "";// �����

  private String term = "";// �������

  private String monthInterest = "";// ÿ������

  private String realMonthInt = "";// ҳ�����ص���Ϣ

  private String creditType = "";// ���ʽ

  private String contractSureDate = "";// ��ͬǩ������

  private String debitMoneyStaDate = "";// �����ʼ����

  private String debitMoneyEndDate = "";// �����ֹ����

  private String assistantOrgId = "";// ������˾���

  private String office = "";// ���´�

  private String loanKouAcc = "";// �ۿ��˺�

  private String isWrite = "";// �Ƿ�ǩ����ͬ

  private String contractSt = "";// ��ͬ״̬

  private String temp_beentruster = "";// �ҷ�

  private String IsNeedDel = "";// ���ɨ����Ƿ�ɾ��ԭ��ͼƬ

  private String photoUrl = "";// ͼƬ��ַ

  private String loanassistantorgId = "";// ������˾���

  private String paramValue = "";// ����ֵAB or BA

  private String writeType = "";// �����ж��Ƿ�ǩ����ͬ�����õ�����

  private String isComeFromT5 = "";// �����ж��Ƿ�����ά��������

  private String isview = "";// ���ﰴť�Ƿ����

  private String corpusInterest = "";// �»���Ϣ

  private String hiddenloanMode = "";// ��������

  public String getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getHiddenloanMode() {
    return hiddenloanMode;
  }

  public void setHiddenloanMode(String hiddenloanMode) {
    this.hiddenloanMode = hiddenloanMode;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getIsNeedDel() {
    return IsNeedDel;
  }

  public void setIsNeedDel(String isNeedDel) {
    IsNeedDel = isNeedDel;
  }

  public String getRealMonthInt() {
    return realMonthInt;
  }

  public void setRealMonthInt(String realMonthInt) {
    this.realMonthInt = realMonthInt;
  }

  public String getTemp_beentruster() {
    return temp_beentruster;
  }

  public void setTemp_beentruster(String temp_beentruster) {
    this.temp_beentruster = temp_beentruster;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public String getIsview() {
    return isview;
  }

  public void setIsview(String isview) {
    this.isview = isview;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getIsComeFromT5() {
    return isComeFromT5;
  }

  public void setIsComeFromT5(String isComeFromT5) {
    this.isComeFromT5 = isComeFromT5;
  }

  public String getWriteType() {
    return writeType;
  }

  public void setWriteType(String writeType) {
    this.writeType = writeType;
  }

  public String getParamValue() {
    return paramValue;
  }

  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
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

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getMonthInterest() {
    return monthInterest;
  }

  public void setMonthInterest(String monthInterest) {
    this.monthInterest = monthInterest;
  }

  public Map getBeentrusterMap() {
    return beentrusterMap;
  }

  public void setBeentrusterMap(Map beentrusterMap) {
    this.beentrusterMap = beentrusterMap;
  }

  public String getIsWrite() {
    return isWrite;
  }

  public void setIsWrite(String isWrite) {
    this.isWrite = isWrite;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getAssistantOrgId() {
    return assistantOrgId;
  }

  public void setAssistantOrgId(String assistantOrgId) {
    this.assistantOrgId = assistantOrgId;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public List getBankList() {
    return bankList;
  }

  public void setBankList(List bankList) {
    this.bankList = bankList;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    this.contractId = "";
    this.entruster = "";
    this.temp_beentruster = "";
    this.assurer = "";
    this.debitter = "";
    this.certificateType = "";
    this.certificateNum = "";
    this.debitMoney = "";
    this.term = "";
    this.monthInterest = "";
    this.realMonthInt = "";
    this.creditType = "";
    this.contractSureDate = "";
    this.debitMoneyStaDate = "";
    this.debitMoneyEndDate = "";
    this.assistantOrgId = "";
    this.loanKouAcc = "";
  }

  public String getIscontactid() {
    return iscontactid;
  }

  public void setIscontactid(String iscontactid) {
    this.iscontactid = iscontactid;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

}
