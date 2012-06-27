package org.xpup.hafmis.sysloan.loancallback.consultation.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;

public class ConsultationTaAF extends ActionForm {
  
  private static final long serialVersionUID = 157830469042818336L;
  
  private BorrowerInfoDTO borrowerInfoDTO=new BorrowerInfoDTO();
  // ����
  private String borrowerName = "";

  // ֤������
  private String cardKind = "";

  // ֤������
  private String cardNum = "";

  // ʣ�౾��
  private BigDecimal overplusLoanMoney = new BigDecimal(0.00);

  // ʣ������
  private String overplusLimite = "";

  // ���ʽ
  private String loanMode = "";

  // ��ͬ���
  private String contractId = "";

  // �����˺�
  private String loanKouAcc = "";

  // ��������
  private List monthYearList = new ArrayList();
  
  //��������
  private String monthYear = "";

  // Ӧ����Ϣ
  private List shouldBackList = new ArrayList();

  // ҵ������
  private String bizType = "";

  // ��ǰ�����
  private BigDecimal aheadCorpus = new BigDecimal(0.00);

  // ռ������
  private String days = "0";

  // ��ǰ������Ϣ
  private BigDecimal aheadInterest = new BigDecimal(0.00);

  // �����ѽ��
  private BigDecimal loanPoundageMoney = new BigDecimal(0.00);

  // ��ǰ�����ʣ������
  private String deadLine = "0";

  // ��ǰ������»���Ϣ
  private BigDecimal corpusInterest = new BigDecimal(0.00);

  // �����ܻ����
  private BigDecimal sumCorpus = new BigDecimal(0.00);

  // �����ܻ�����Ϣ
  private BigDecimal sumInterest = new BigDecimal(0.00);

  // �����ܻ�����
  private BigDecimal sumMoney = new BigDecimal(0.00);

  // �������
  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);

  //���˷�����
  private BigDecimal overOccurMoney = new BigDecimal(0.00);
  
  // ����ʵ�ս��
  private BigDecimal realMoney = new BigDecimal(0.00);

  //�ۿʽ
  private String  pldebit = "";
  
  //��ͻ�����
  private BigDecimal minMoney = new BigDecimal(0.00);
  
  //�����޸�����
  private String line = "";
  
  //�������
  private BigDecimal loanBalance = new BigDecimal(0.00);
  
  
  private String plLoanReturnType = "";
  
  //ͷ��ID
  private String headId = "";
  
  private String isAmend = "";
  private String loanAcc = "";
  private String interestAcc = "";
  private String docNum = "";
  private String noteNum = "";
  private String bankName = "";
  private String makeOP = "";
  private String clearOP = "";
  private BigDecimal interest = new BigDecimal(0.00);
  private BigDecimal overdueInterest = new BigDecimal(0.00);
  private BigDecimal punishInterest = new BigDecimal(0.00);
  private String months = "";
  private String bizDate = "";
  private String isAmendLine = "";
  
  public String getIsAmendLine() {
    return isAmendLine;
  }

  public void setIsAmendLine(String isAmendLine) {
    this.isAmendLine = isAmendLine;
  }

  public String getIsAmend() {
    return isAmend;
  }

  public void setIsAmend(String isAmend) {
    this.isAmend = isAmend;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getMonths() {
    return months;
  }

  public void setMonths(String months) {
    this.months = months;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public BigDecimal getOverdueInterest() {
    return overdueInterest;
  }

  public void setOverdueInterest(BigDecimal overdueInterest) {
    this.overdueInterest = overdueInterest;
  }

  public String getClearOP() {
    return clearOP;
  }

  public void setClearOP(String clearOP) {
    this.clearOP = clearOP;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getMakeOP() {
    return makeOP;
  }

  public void setMakeOP(String makeOP) {
    this.makeOP = makeOP;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
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

  public String getHeadId() {
    return headId;
  }

  public void setHeadId(String headId) {
    this.headId = headId;
  }

  public String getPlLoanReturnType() {
    return plLoanReturnType;
  }

  public void setPlLoanReturnType(String plLoanReturnType) {
    this.plLoanReturnType = plLoanReturnType;
  }

  public BigDecimal getLoanBalance() {
    return loanBalance;
  }

  public void setLoanBalance(BigDecimal loanBalance) {
    this.loanBalance = loanBalance;
  }

  public BigDecimal getMinMoney() {
    return minMoney;
  }

  public void setMinMoney(BigDecimal minMoney) {
    this.minMoney = minMoney;
  }

  public BigDecimal getAheadCorpus() {
    return aheadCorpus;
  }

  public void setAheadCorpus(BigDecimal aheadCorpus) {
    this.aheadCorpus = aheadCorpus;
  }

  public BigDecimal getAheadInterest() {
    return aheadInterest;
  }

  public void setAheadInterest(BigDecimal aheadInterest) {
    this.aheadInterest = aheadInterest;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
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

  public BigDecimal getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(BigDecimal corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public String getDays() {
    return days;
  }

  public void setDays(String days) {
    this.days = days;
  }

  public String getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(String deadLine) {
    this.deadLine = deadLine;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getLoanMode() {
    return loanMode;
  }

  public void setLoanMode(String loanMode) {
    this.loanMode = loanMode;
  }

  public BigDecimal getLoanPoundageMoney() {
    return loanPoundageMoney;
  }

  public void setLoanPoundageMoney(BigDecimal loanPoundageMoney) {
    this.loanPoundageMoney = loanPoundageMoney;
  }

  public List getMonthYearList() {
    return monthYearList;
  }

  public void setMonthYearList(List monthYearList) {
    this.monthYearList = monthYearList;
  }

  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }

  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }

  public String getOverplusLimite() {
    return overplusLimite;
  }

  public void setOverplusLimite(String overplusLimite) {
    this.overplusLimite = overplusLimite;
  }

  public BigDecimal getOverplusLoanMoney() {
    return overplusLoanMoney;
  }

  public void setOverplusLoanMoney(BigDecimal overplusLoanMoney) {
    this.overplusLoanMoney = overplusLoanMoney;
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

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public BigDecimal getOverOccurMoney() {
    return overOccurMoney;
  }

  public void setOverOccurMoney(BigDecimal overOccurMoney) {
    this.overOccurMoney = overOccurMoney;
  }

  public String getPldebit() {
    return pldebit;
  }

  public void setPldebit(String pldebit) {
    this.pldebit = pldebit;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    borrowerName = "";
    cardKind = "";
    cardNum = "";
    overplusLoanMoney = new BigDecimal(0.00);
    overplusLimite = "";
    loanMode = "";
    contractId = "";
    loanKouAcc = "";
    monthYearList = new ArrayList();
    shouldBackList = new ArrayList();
    bizType = "";
    aheadCorpus = new BigDecimal(0.00);
    days = "";
    aheadInterest = new BigDecimal(0.00);
    loanPoundageMoney = new BigDecimal(0.00);
    deadLine = "";
    corpusInterest = new BigDecimal(0.00);
    sumCorpus = new BigDecimal(0.00);
    sumInterest = new BigDecimal(0.00);
    sumMoney = new BigDecimal(0.00);
    ovaerLoanRepay = new BigDecimal(0.00);
    realMoney = new BigDecimal(0.00);
    monthYear = "";
    overOccurMoney = new BigDecimal(0.00);
    pldebit = "";
  }

}