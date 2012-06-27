package org.xpup.hafmis.sysloan.loancallback.destoryback.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DestoryBackTbAF extends ActionForm {
  private List list = new ArrayList();//��ʾ�б�

  private String docNum = "";// ƾ֤���

  private String loanKouAcc = "";// �����˺�

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private String cardNum = "";// ֤������

  private Map bizStMap = new HashMap();// ҵ��״̬����

  private String bizSt = "";// ҵ��״̬

  private String loanBankName = "";// �ſ�����

  private List loanBankNameList = new ArrayList();//�ſ�����

  private BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// ���ս��-�ܶ�

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
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

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

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

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    list = new ArrayList();// ��ʾ�б�

    docNum = "";// ƾ֤���

    loanKouAcc = "";// �����˺�

    contractId = "";// ��ͬ���

    borrowerName = "";// ���������

    bizStMap = new HashMap();// ҵ��״̬����

    bizSt = "";// ҵ��״̬

    loanBankName = "";// �ſ�����

    loanBankNameList = new ArrayList();// �ſ�����

    cardNum = "";// ֤������
  }

}
