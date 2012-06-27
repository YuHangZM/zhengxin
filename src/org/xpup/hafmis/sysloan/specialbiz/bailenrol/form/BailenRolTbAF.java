package org.xpup.hafmis.sysloan.specialbiz.bailenrol.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * @author ��Ұ 2007-10-03
 */
public class BailenRolTbAF extends ActionForm {

  private static final long serialVersionUID = 3534675385369455818L;

  private List list = null;

  private String id = null;

  private String contractId = null; // ��ͬID

  private String borrowerName = null; // ���������

  private String cardNum = null; // ֤������

  private String loanBankName = null; // �ſ�����

  private String bizDate = null; // ��ȡ����

  private BigDecimal occurMoney = new BigDecimal(0.00);// ��֤����

  private String bizSt = null; // ҵ��״̬

  private List loanBankNameList = new ArrayList();

  private Map bizStMap = new HashMap();

  private BigDecimal occurTotleMoney = new BigDecimal(0.00);// ��֤����-�ܶ�

  private Integer count = new Integer(0);

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getOccurTotleMoney() {
    return occurTotleMoney;
  }

  public void setOccurTotleMoney(BigDecimal occurTotleMoney) {
    this.occurTotleMoney = occurTotleMoney;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public Map getBizStMap() {
    return bizStMap;
  }

  public void setBizStMap(Map bizStMap) {
    this.bizStMap = bizStMap;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public BigDecimal getOccurMoney() {
    return occurMoney;
  }

  public void setOccurMoney(BigDecimal occurMoney) {
    this.occurMoney = occurMoney;
  }

}
