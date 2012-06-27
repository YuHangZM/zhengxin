package org.xpup.hafmis.sysloan.accounthandle.bizcheck.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BizCheckShowListAF  extends ActionForm {

  private List list=new ArrayList();//��ʾ�б�
  
  private String docNum= "";// ƾ֤���
  
  private String loanKouAcc= "";// �����˺�

  private String contractId= "";// ��ͬ���

  private String borrowerName= "";// ���������
  
  private String bizType= "";// ҵ������
  
  private String makePerson= "";//�Ƶ���
  
  private Map bizStMap = new HashMap();// ҵ��״̬����
  
  private String bizSt= "";// ҵ��״̬
  
  private String loanBankName = "";// �ſ�����
  
  private List loanBankNameList = new ArrayList();//�ſ�����
  
  private String beginBizDate= "";//��������
  
  private String endBizDate= "";//��������
  
  private BigDecimal occurMoneyTotle= new BigDecimal(0.00);// ���Ž��-�ܶ�

  private BigDecimal reclaimCorpusTotle= new BigDecimal(0.00);// ���ձ���-�ܶ�

  private BigDecimal reclaimAccrualTotle= new BigDecimal(0.00);// ������Ϣ-�ܶ��ܶ�

  private BigDecimal realPunishInterestTotle= new BigDecimal(0.00);// ���շ�Ϣ-�ܶ�
  
  private BigDecimal badDebtTotle= new BigDecimal(0.00);// ���˺������-�ܶ�

  private BigDecimal putUpMoneyTotle= new BigDecimal(0.00);// ���˽��-�ܶ�

  private BigDecimal bailTotle= new BigDecimal(0.00);// ��֤��-�ܶ�

  private BigDecimal bailAccrualTotle= new BigDecimal(0.00);// ��֤����Ϣ-�ܶ�
  
  private Map bizTypeMap=new HashMap();// ҵ������
 
  private int affirmbizSt=0;//ȷ��״̬����
  
  private int checkbizSt=0;//����״̬����
  
  private BigDecimal reclaimtotle = new BigDecimal(0.00);// ����Ӧ�����

  private BigDecimal reclaimbacktotle = new BigDecimal(0.00);// ����ʵ�����
  
  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }
  
  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
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

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
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

  public BigDecimal getOccurMoneyTotle() {
    return occurMoneyTotle;
  }

  public void setOccurMoneyTotle(BigDecimal occurMoneyTotle) {
    this.occurMoneyTotle = occurMoneyTotle;
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
  public int getAffirmbizSt() {
    return affirmbizSt;
  }

  public void setAffirmbizSt(int affirmbizSt) {
    this.affirmbizSt = affirmbizSt;
  }

  public int getCheckbizSt() {
    return checkbizSt;
  }

  public void setCheckbizSt(int checkbizSt) {
    this.checkbizSt = checkbizSt;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    
    list =new ArrayList();// ��ʾ�б�

    docNum = "";// ƾ֤���

    loanKouAcc = "";// �����˺�

    contractId = "";// ��ͬ���

    borrowerName = "";// ���������

    bizType = "";// ҵ������

    makePerson = "";// �Ƶ���

    bizStMap = new HashMap();// ҵ��״̬����

    bizSt = "";// ҵ��״̬

    loanBankName = "";// �ſ�����

    loanBankNameList = new ArrayList();// �ſ�����

    beginBizDate = "";// ��������

    endBizDate = "";// ��������

    occurMoneyTotle = new BigDecimal(0.00);// ���Ž��-�ܶ�

    reclaimCorpusTotle = new BigDecimal(0.00);// ���ձ���-�ܶ�

    reclaimAccrualTotle = new BigDecimal(0.00);// ������Ϣ-�ܶ��ܶ�

    realPunishInterestTotle = new BigDecimal(0.00);// ���շ�Ϣ-�ܶ�

    badDebtTotle = new BigDecimal(0.00);// ���˺������-�ܶ�

    putUpMoneyTotle = new BigDecimal(0.00);// ���˽��-�ܶ�

    bailTotle = new BigDecimal(0.00);// ��֤��-�ܶ�

    bailAccrualTotle = new BigDecimal(0.00);// ��֤����Ϣ-�ܶ�

    bizTypeMap = new HashMap();// ҵ������
    
    affirmbizSt=0;
    
    checkbizSt=0;
  }

  public BigDecimal getReclaimbacktotle() {
    return reclaimbacktotle;
  }

  public void setReclaimbacktotle(BigDecimal reclaimbacktotle) {
    this.reclaimbacktotle = reclaimbacktotle;
  }

  public BigDecimal getReclaimtotle() {
    return reclaimtotle;
  }

  public void setReclaimtotle(BigDecimal reclaimtotle) {
    this.reclaimtotle = reclaimtotle;
  }

}
