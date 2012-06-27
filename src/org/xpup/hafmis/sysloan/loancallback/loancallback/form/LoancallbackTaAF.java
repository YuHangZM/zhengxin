package org.xpup.hafmis.sysloan.loancallback.loancallback.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;

public class LoancallbackTaAF extends ActionForm {

  private static final long serialVersionUID = 157830469042818336L;

  private BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();

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

  // ������ʼ��
  private String begainMonthYear = "";

  // ��������
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

  private BigDecimal aheadInterest1 = new BigDecimal(0.00);

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

  // ���˷�����
  private BigDecimal overOccurMoney = new BigDecimal(0.00);

  // ����ʵ�ս��
  private BigDecimal realMoney = new BigDecimal(0.00);

  // �ۿʽ
  private String pldebit = "";

  // ��ͻ�����
  private BigDecimal minMoney = new BigDecimal(0.00);

  // ����ʵ�ս��
  private BigDecimal sumSalary = new BigDecimal(0.00);

  // �����޸�����
  private String line = "";

  private String param = "";

  // �������
  private BigDecimal loanBalance = new BigDecimal(0.00);

  private String plLoanReturnType = "";

  private String date = null;

  // ͷ��ID
  private String headId = "";

  private String isAmend = "";

  private String loanAcc = "";

  private String interestAcc = "";

  private String docNum = "";

  private String noteNum = "";

  private String bankName = "";

  private String makeOP = "";

  private String clearOP = "";

  private String clearAccountOP = "";

  private BigDecimal interest = new BigDecimal(0.00);

  private BigDecimal overdueInterest = new BigDecimal(0.00);

  private BigDecimal punishInterest = new BigDecimal(0.00);

  private String months = "";

  private String bizDate = "";

  private String isAmendLine = "";

  private String dead = "0";

  private String chgMonth = "0";

  private String lastlimit = "0";

  private BigDecimal tqhklx = new BigDecimal(0.00);// ��ǰ������Ϣ

  private BigDecimal tqhkbj = new BigDecimal(0.00);// ��ǰ�����

  // ʣ�౾��
  private BigDecimal overplusCorpus = new BigDecimal(0.00);

  private BigDecimal realCorpus = new BigDecimal(0.00);

  private BigDecimal realOverduCorpus = new BigDecimal(0.00);

  // ������޸�//2007-3-11

  private BigDecimal overplusInterestAll = new BigDecimal(0.00);// ʣ����Ϣ

  private BigDecimal interestAll = new BigDecimal(0.00);// ����Ϣ

  // ������޸�//2007-3-11

  private String batchNum = "";// ���κ� yqf 20080625

  private String yga = "";

  private String ygb = "";

  private String ygc = "";

  private String ygd = "";

  private String yge = "";

  // ��ǰ��������
  private String aheadType = "";

  // ��ǰ��Ϣ��ID
  private String aheadCheckId = "";

  private String aheadTypeS = "";

  private String data_1 = "";

  private String data_2 = "";

  private String data_3 = "";

  private String data_4 = "";

  private String data_5 = "";

  private String data_6 = "";

  private String data_7 = "";

  private String data_8 = "";

  private String data_9 = "";

  private String data_10 = "";

  private String data_11 = "";

  public String getAheadTypeS() {
    return aheadTypeS;
  }

  public void setAheadTypeS(String aheadTypeS) {
    this.aheadTypeS = aheadTypeS;
  }

  public String getAheadCheckId() {
    return aheadCheckId;
  }

  public void setAheadCheckId(String aheadCheckId) {
    this.aheadCheckId = aheadCheckId;
  }

  public String getAheadType() {
    return aheadType;
  }

  public void setAheadType(String aheadType) {
    this.aheadType = aheadType;
  }

  public String getBatchNum() {
    return batchNum;
  }

  public void setBatchNum(String batchNum) {
    this.batchNum = batchNum;
  }

  public BigDecimal getRealCorpus() {
    return realCorpus;
  }

  public void setRealCorpus(BigDecimal realCorpus) {
    this.realCorpus = realCorpus;
  }

  public BigDecimal getRealOverduCorpus() {
    return realOverduCorpus;
  }

  public BigDecimal getInterestAll() {
    return interestAll;
  }

  public void setInterestAll(BigDecimal interestAll) {
    this.interestAll = interestAll;
  }

  public BigDecimal getOverplusInterestAll() {
    return overplusInterestAll;
  }

  public void setOverplusInterestAll(BigDecimal overplusInterestAll) {
    this.overplusInterestAll = overplusInterestAll;
  }

  public void setRealOverduCorpus(BigDecimal realOverduCorpus) {
    this.realOverduCorpus = realOverduCorpus;
  }

  public BigDecimal getOverplusCorpus() {
    return overplusCorpus;
  }

  public void setOverplusCorpus(BigDecimal overplusCorpus) {
    this.overplusCorpus = overplusCorpus;
  }

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

  public String getBegainMonthYear() {
    return begainMonthYear;
  }

  public void setBegainMonthYear(String begainMonthYear) {
    this.begainMonthYear = begainMonthYear;
  }

  public BigDecimal getTqhkbj() {
    return tqhkbj;
  }

  public void setTqhkbj(BigDecimal tqhkbj) {
    this.tqhkbj = tqhkbj;
  }

  public BigDecimal getTqhklx() {
    return tqhklx;
  }

  public void setTqhklx(BigDecimal tqhklx) {
    this.tqhklx = tqhklx;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  public String getDead() {
    return dead;
  }

  public void setDead(String dead) {
    this.dead = dead;
  }

  public String getChgMonth() {
    return chgMonth;
  }

  public void setChgMonth(String chgMonth) {
    this.chgMonth = chgMonth;
  }

  public String getLastlimit() {
    return lastlimit;
  }

  public void setLastlimit(String lastlimit) {
    this.lastlimit = lastlimit;
  }

  public String getYga() {
    return yga;
  }

  public void setYga(String yga) {
    this.yga = yga;
  }

  public String getYgb() {
    return ygb;
  }

  public void setYgb(String ygb) {
    this.ygb = ygb;
  }

  public String getYgc() {
    return ygc;
  }

  public void setYgc(String ygc) {
    this.ygc = ygc;
  }

  public String getYgd() {
    return ygd;
  }

  public void setYgd(String ygd) {
    this.ygd = ygd;
  }

  public String getYge() {
    return yge;
  }

  public void setYge(String yge) {
    this.yge = yge;
  }

  public String getClearAccountOP() {
    return clearAccountOP;
  }

  public void setClearAccountOP(String clearAccountOP) {
    this.clearAccountOP = clearAccountOP;
  }

  public String getData_1() {
    return data_1;
  }

  public void setData_1(String data_1) {
    this.data_1 = data_1;
  }

  public String getData_10() {
    return data_10;
  }

  public void setData_10(String data_10) {
    this.data_10 = data_10;
  }

  public String getData_2() {
    return data_2;
  }

  public void setData_2(String data_2) {
    this.data_2 = data_2;
  }

  public String getData_3() {
    return data_3;
  }

  public void setData_3(String data_3) {
    this.data_3 = data_3;
  }

  public String getData_4() {
    return data_4;
  }

  public void setData_4(String data_4) {
    this.data_4 = data_4;
  }

  public String getData_5() {
    return data_5;
  }

  public void setData_5(String data_5) {
    this.data_5 = data_5;
  }

  public String getData_6() {
    return data_6;
  }

  public void setData_6(String data_6) {
    this.data_6 = data_6;
  }

  public String getData_7() {
    return data_7;
  }

  public void setData_7(String data_7) {
    this.data_7 = data_7;
  }

  public String getData_8() {
    return data_8;
  }

  public void setData_8(String data_8) {
    this.data_8 = data_8;
  }

  public String getData_9() {
    return data_9;
  }

  public void setData_9(String data_9) {
    this.data_9 = data_9;
  }

  public String getData_11() {
    return data_11;
  }

  public void setData_11(String data_11) {
    this.data_11 = data_11;
  }

  public BigDecimal getSumSalary() {
    return sumSalary;
  }

  public void setSumSalary(BigDecimal sumSalary) {
    this.sumSalary = sumSalary;
  }

  public BigDecimal getAheadInterest1() {
    return aheadInterest1;
  }

  public void setAheadInterest1(BigDecimal aheadInterest1) {
    this.aheadInterest1 = aheadInterest1;
  }

}