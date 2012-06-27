package org.xpup.hafmis.sysloan.loanapply.loancheck.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * @author ��Ұ 2007-09-22
 */
public class LoanCheckShowAF extends ActionForm {

  private static final long serialVersionUID = 847908045009297328L;

  private List list = null;

  private String id = null;

  private String contractId = null;

  private String borrowerName = null;

  private String cardNum = null;

  private List loanBankNameList = new ArrayList();
  
  private Map houseTypeMap = new HashMap();// ��������������

  private String beginBizDate = null;// ��ʼ����ʱ��

  private String endBizDate = null;// ��ֹ����ʱ��
  
  private String beginBackDate = null;// ��ʼ�ؼ�����

  private String endBackDate = null;// ��ֹ�ؼ�����

  private Map contractStMap = new HashMap();// ��ͬ״̬������

  private String contractStFind = null;// ��ͬ״̬��ѯ����

  private String orgName = null;
  
  private String officeCode;

  private String loanBankName = null;// �ſ�����

  private String houseType = null;// ��������

  private String contractSt = null;

  private Integer count = new Integer(0);

  private BigDecimal loanTotleMoney = new BigDecimal(0.00);// �����-�ܶ�
  //wuht
  private BigDecimal totlePriceAll = new BigDecimal(0.00);// �ϼƷ���
  
  private BigDecimal houseAreaAll = new BigDecimal(0.00);// �ϼƽ������
  
  private BigDecimal loanTotleMoneyYearSum = new BigDecimal(0.00);// �����-�ܶ���ۼ�

  private BigDecimal totlePriceAllYearSum = new BigDecimal(0.00);// �ϼƷ��۵����ۼ�
  
  private BigDecimal houseAreaAllYearSum = new BigDecimal(0.00);// �ϼƽ�����������ۼ�
  
  private List listAll = null;
 
 

  public List getListAll() {
    return listAll;
  }

  public void setListAll(List listAll) {
    this.listAll = listAll;
  }

  public BigDecimal getHouseAreaAll() {
    return houseAreaAll;
  }

  public void setHouseAreaAll(BigDecimal houseAreaAll) {
    this.houseAreaAll = houseAreaAll;
  }

  public BigDecimal getTotlePriceAll() {
    return totlePriceAll;
  }

  public void setTotlePriceAll(BigDecimal totlePriceAll) {
    this.totlePriceAll = totlePriceAll;
  }

  public String getContractStFind() {
    return contractStFind;
  }

  public void setContractStFind(String contractStFind) {
    this.contractStFind = contractStFind;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public Map getContractStMap() {
    return contractStMap;
  }

  public void setContractStMap(Map contractStMap) {
    this.contractStMap = contractStMap;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
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

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public Map getHouseTypeMap() {
    return houseTypeMap;
  }

  public void setHouseTypeMap(Map houseTypeMap) {
    this.houseTypeMap = houseTypeMap;
  }

  public List getList() {
    return list;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
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

  public BigDecimal getLoanTotleMoney() {
    return loanTotleMoney;
  }

  public void setLoanTotleMoney(BigDecimal loanTotleMoney) {
    this.loanTotleMoney = loanTotleMoney;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getContractSt() {
    return contractSt;
  }

  public void setContractSt(String contractSt) {
    this.contractSt = contractSt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BigDecimal getHouseAreaAllYearSum() {
    return houseAreaAllYearSum;
  }

  public void setHouseAreaAllYearSum(BigDecimal houseAreaAllYearSum) {
    this.houseAreaAllYearSum = houseAreaAllYearSum;
  }

  public BigDecimal getLoanTotleMoneyYearSum() {
    return loanTotleMoneyYearSum;
  }

  public void setLoanTotleMoneyYearSum(BigDecimal loanTotleMoneyYearSum) {
    this.loanTotleMoneyYearSum = loanTotleMoneyYearSum;
  }

  public BigDecimal getTotlePriceAllYearSum() {
    return totlePriceAllYearSum;
  }

  public void setTotlePriceAllYearSum(BigDecimal totlePriceAllYearSum) {
    this.totlePriceAllYearSum = totlePriceAllYearSum;
  }

  public String getBeginBackDate() {
    return beginBackDate;
  }

  public void setBeginBackDate(String beginBackDate) {
    this.beginBackDate = beginBackDate;
  }

  public String getEndBackDate() {
    return endBackDate;
  }

  public void setEndBackDate(String endBackDate) {
    this.endBackDate = endBackDate;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

}
