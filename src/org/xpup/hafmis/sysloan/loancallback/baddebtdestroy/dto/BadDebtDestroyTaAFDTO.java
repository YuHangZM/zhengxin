package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;

public class BadDebtDestroyTaAFDTO {
  
  private BorrowerInfoDTO borrowerInfoDTO=new BorrowerInfoDTO();

  // ��������
  private List monthYearList = new ArrayList();

  // Ӧ����Ϣ
  private List shouldBackList = new ArrayList();
  
  //��������
  private String monthYear = "";

  // �����ܻ����
  private BigDecimal sumCorpus = new BigDecimal(0.00);

  // �����ܻ�����Ϣ
  private BigDecimal sumInterest = new BigDecimal(0.00);

  // �����ܻ�����
  private BigDecimal sumMoney = new BigDecimal(0.00);
  
  // ����ʵ�ս��
  private BigDecimal realMoney = new BigDecimal(0.00);
  
  //������λ����
  private String orgType = "";
  
  //������λ����
  private String orgName = "";
  
  //������λ���
  private String loanassistantorgId ="";
  
  //����ί�д����˺�
  private String loanAcc = "";
  
  //������Ϣ�˺�
  private String interestAcc = "";
  
  //ʵ����Ϣ
  private BigDecimal realInterest = new BigDecimal(0.00);
  
  //������Ϣ
  private BigDecimal overdueInterest = new BigDecimal(0.00);
  
  //ʵ����Ϣ
  private BigDecimal punishInterest = new BigDecimal(0.00);
  
  //�Ƶ���
  private String makeOP = "";
  
  //������
  private String clearOP = "";
  
  
  //��������
  private String bankName = "";
  
  //ҵ������
  private String bizType = "";
  
  //ҵ������
  private String bizDate = "";
  
  //ƾ֤��
  private String docNum = "";
  
  //Ʊ�ݺ�
  private String noteNum = "";
  
  //ͷ��ID
  private String headId = "";
  
  
  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getClearOP() {
    return clearOP;
  }

  public void setClearOP(String clearOP) {
    this.clearOP = clearOP;
  }

  public String getMakeOP() {
    return makeOP;
  }

  public void setMakeOP(String makeOP) {
    this.makeOP = makeOP;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public BigDecimal getOverdueInterest() {
    return overdueInterest;
  }

  public void setOverdueInterest(BigDecimal overdueInterest) {
    this.overdueInterest = overdueInterest;
  }

  public BigDecimal getRealInterest() {
    return realInterest;
  }

  public void setRealInterest(BigDecimal realInterest) {
    this.realInterest = realInterest;
  }

  public String getInterestAcc() {
    return interestAcc;
  }

  public void setInterestAcc(String interestAcc) {
    this.interestAcc = interestAcc;
  }

  public String getLoanAcc() {
    return loanAcc;
  }

  public void setLoanAcc(String loanAcc) {
    this.loanAcc = loanAcc;
  }

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgType() {
    return orgType;
  }

  public void setOrgType(String orgType) {
    this.orgType = orgType;
  }

  public BorrowerInfoDTO getBorrowerInfoDTO() {
    return borrowerInfoDTO;
  }

  public void setBorrowerInfoDTO(BorrowerInfoDTO borrowerInfoDTO) {
    this.borrowerInfoDTO = borrowerInfoDTO;
  }

  public String getMonthYear() {
    return monthYear;
  }

  public void setMonthYear(String monthYear) {
    this.monthYear = monthYear;
  }

  public List getMonthYearList() {
    return monthYearList;
  }

  public void setMonthYearList(List monthYearList) {
    this.monthYearList = monthYearList;
  }

  public BigDecimal getRealMoney() {
    return realMoney;
  }

  public void setRealMoney(BigDecimal realMoney) {
    this.realMoney = realMoney;
  }

  public List getShouldBackList() {
    return shouldBackList;
  }

  public void setShouldBackList(List shouldBackList) {
    this.shouldBackList = shouldBackList;
  }

  public BigDecimal getSumCorpus() {
    return sumCorpus;
  }

  public void setSumCorpus(BigDecimal sumCorpus) {
    this.sumCorpus = sumCorpus;
  }

  public BigDecimal getSumInterest() {
    return sumInterest;
  }

  public void setSumInterest(BigDecimal sumInterest) {
    this.sumInterest = sumInterest;
  }

  public BigDecimal getSumMoney() {
    return sumMoney;
  }

  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }

}