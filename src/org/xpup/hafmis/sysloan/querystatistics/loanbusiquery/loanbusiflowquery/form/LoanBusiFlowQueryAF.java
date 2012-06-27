package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO;

/**
 * @author ��Ұ 2007-10-15
 */
public class LoanBusiFlowQueryAF extends ActionForm {

  private static final long serialVersionUID = 2531807195056023196L;

  LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();

  List list = null;// ��ʾ�б�
  
  List printList = null;// ��ӡ�б�

  private Map bizTypeMap = new HashMap();// ҵ������

  private List operList = new ArrayList();// �Ƶ���

  private Map bizStMap = new HashMap();// ҵ��״̬����
  
  private Map isGjjLoanbackMap = new HashMap();// �Ƿ�Ϊ�����𻹴�
  
  private String isGjjLoanback;// �Ƿ�Ϊ�����𻹴�

  private List loanBankNameList = new ArrayList();// �ſ�����

  private String docNum = null;// ƾ֤���

  private String loanKouAcc = null;// �����˺�

  private String contractId = null;// ��ͬ���

  private String borrowerName = null;// ���������

  private String bizType = null;// ҵ������

  private String makePerson = null;// �Ƶ���

  private String bizSt = null;// ҵ��״̬

  private String loanBankName = null;// �ſ�����

  private String beginBizDate = null;// ��ʼ��������

  private String endBizDate = null;// ��ֹ��������

  private BigDecimal occurMoneyTotle = new BigDecimal(0.00);// ���Ž��-�ܶ�

  private BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// ���ձ���-�ܶ�

  private BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// ������Ϣ-�ܶ��ܶ�

  private BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// ���շ�Ϣ-�ܶ�

  private BigDecimal badDebtTotle = new BigDecimal(0.00);// ���˺������-�ܶ�

  private BigDecimal reclaimTotle = new BigDecimal(0.00);// �����ܽ��-�ܶ�

  private BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// ���˽��-�ܶ�

  private BigDecimal bailTotle = new BigDecimal(0.00);// ��֤��-�ܶ�

  private BigDecimal bailAccrualTotle = new BigDecimal(0.00);// ��֤����Ϣ-�ܶ�
  
  private String loanType="";//�������� ���� ���

  public BigDecimal getBadDebtTotle() {
    return badDebtTotle;
  }

  public void setBadDebtTotle(BigDecimal badDebtTotle) {
    this.badDebtTotle = badDebtTotle;
  }

  public BigDecimal getBailAccrualTotle() {
    return bailAccrualTotle;
  }

  public void setBailAccrualTotle(BigDecimal bailAccrualTotle) {
    this.bailAccrualTotle = bailAccrualTotle;
  }

  public BigDecimal getBailTotle() {
    return bailTotle;
  }

  public void setBailTotle(BigDecimal bailTotle) {
    this.bailTotle = bailTotle;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
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

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
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

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
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

  public LoanBusiFlowQueryDTO getLoanBusiFlowQueryDTO() {
    return loanBusiFlowQueryDTO;
  }

  public void setLoanBusiFlowQueryDTO(LoanBusiFlowQueryDTO loanBusiFlowQueryDTO) {
    this.loanBusiFlowQueryDTO = loanBusiFlowQueryDTO;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public BigDecimal getOccurMoneyTotle() {
    return occurMoneyTotle;
  }

  public void setOccurMoneyTotle(BigDecimal occurMoneyTotle) {
    this.occurMoneyTotle = occurMoneyTotle;
  }

  public List getOperList() {
    return operList;
  }

  public void setOperList(List operList) {
    this.operList = operList;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public BigDecimal getPutUpMoneyTotle() {
    return putUpMoneyTotle;
  }

  public void setPutUpMoneyTotle(BigDecimal putUpMoneyTotle) {
    this.putUpMoneyTotle = putUpMoneyTotle;
  }

  public BigDecimal getRealPunishInterestTotle() {
    return realPunishInterestTotle;
  }

  public void setRealPunishInterestTotle(BigDecimal realPunishInterestTotle) {
    this.realPunishInterestTotle = realPunishInterestTotle;
  }

  public BigDecimal getReclaimAccrualTotle() {
    return reclaimAccrualTotle;
  }

  public void setReclaimAccrualTotle(BigDecimal reclaimAccrualTotle) {
    this.reclaimAccrualTotle = reclaimAccrualTotle;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }

  public BigDecimal getReclaimTotle() {
    return reclaimTotle;
  }

  public void setReclaimTotle(BigDecimal reclaimTotle) {
    this.reclaimTotle = reclaimTotle;
  }

  public String getIsGjjLoanback() {
    return isGjjLoanback;
  }

  public void setIsGjjLoanback(String isGjjLoanback) {
    this.isGjjLoanback = isGjjLoanback;
  }

  public Map getIsGjjLoanbackMap() {
    return isGjjLoanbackMap;
  }

  public void setIsGjjLoanbackMap(Map isGjjLoanbackMap) {
    this.isGjjLoanbackMap = isGjjLoanbackMap;
  }

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  
}
