package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OverDueinfoQueryShowListAF extends ActionForm{
  private List list = new ArrayList();// ��ʾ�б�
 
  private String loanKouAcc = "";// �����˺�

  private String contractId = "";// ��ͬ���
  
  private String borrowerName = "";// ���������

  private String cardNum = ""; // ֤������

  private String beginoweMonth = "";// ��������
  
  private String endoweMonth = "";
  
  private String offic="";//���´�
  
  private String loanBankName="";//�ſ�����
  
  private String developer="";//������
  
  private String buyhouseorgid="";//������ID
  
  private String assistantorg="";//������˾
  
  private List assistantorgList=new ArrayList();//������˾
  
  private String floorId="";
  
  private String count="";
  
  private BigDecimal oweCorpusTotle = new BigDecimal(0.00);// Ƿ������-�ܶ�

  private BigDecimal oweInterestTotle = new BigDecimal(0.00);// Ƿ����Ϣ-�ܶ�

  private BigDecimal punishInterest = new BigDecimal(0.00);// Ƿ����Ϣ��Ϣ-�ܶ�
  
  private String isNoAcquittance="";//���ڵ���δ�廹
  
  private String headname="";                               // ���������ƣ�������ʾ��
  private String floorid="";                              // ¥��
  private String floorName=""; //¥������
  private String floorNum="";  //¥��
  private String floorNum_="";  //¥����������ʾ��
  private String roomNum="";   //����Һ�
  private String houseType="";
  private String buildAreaMin="";
  private String buildAreaMax="";
  private String startDateMin="";
  private String startDateMax="";
  private String applyDate="";//�ſ�����
  private String agreement="";//������Э��
  private String otherMoney="";//����Ƿ��
  private Map yesNoMap=new HashMap();
  private Map housetypemap=new HashMap();  
  
  
  public Map getHousetypemap() {
    return housetypemap;
  }

  public void setHousetypemap(Map housetypemap) {
    this.housetypemap = housetypemap;
  }

  public String getAgreement() {
    return agreement;
  }

  public void setAgreement(String agreement) {
    this.agreement = agreement;
  }

  public String getApplyDate() {
    return applyDate;
  }

  public void setApplyDate(String applyDate) {
    this.applyDate = applyDate;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getOtherMoney() {
    return otherMoney;
  }

  public void setOtherMoney(String otherMoney) {
    this.otherMoney = otherMoney;
  }

  public String getIsNoAcquittance() {
    return isNoAcquittance;
  }

  public void setIsNoAcquittance(String isNoAcquittance) {
    this.isNoAcquittance = isNoAcquittance;
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

 

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }




  public String getOffic() {
    return offic;
  }

  public void setOffic(String offic) {
    this.offic = offic;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public String getAssistantorg() {
    return assistantorg;
  }

  public void setAssistantorg(String assistantorg) {
    this.assistantorg = assistantorg;
  }

  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  public String getBuyhouseorgid() {
    return buyhouseorgid;
  }

  public void setBuyhouseorgid(String buyhouseorgid) {
    this.buyhouseorgid = buyhouseorgid;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getFloorId() {
    return floorId;
  }

  public void setFloorId(String floorId) {
    this.floorId = floorId;
  }

  public String getBeginoweMonth() {
    return beginoweMonth;
  }

  public void setBeginoweMonth(String beginoweMonth) {
    this.beginoweMonth = beginoweMonth;
  }

  public String getEndoweMonth() {
    return endoweMonth;
  }

  public void setEndoweMonth(String endoweMonth) {
    this.endoweMonth = endoweMonth;
  }

  public BigDecimal getOweCorpusTotle() {
    return oweCorpusTotle;
  }

  public void setOweCorpusTotle(BigDecimal oweCorpusTotle) {
    this.oweCorpusTotle = oweCorpusTotle;
  }

  public BigDecimal getOweInterestTotle() {
    return oweInterestTotle;
  }

  public void setOweInterestTotle(BigDecimal oweInterestTotle) {
    this.oweInterestTotle = oweInterestTotle;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }
  public List getAssistantorgList() {
    return assistantorgList;
  }

  public void setAssistantorgList(List assistantorgList) {
    this.assistantorgList = assistantorgList;
  }
  public void reset(ActionMapping mapping, ServletRequest request) {
    list = new ArrayList();// ��ʾ�б�

    loanKouAcc = "";// �����˺�

    contractId = "";// ��ͬ���

    borrowerName = "";// ���������

    cardNum = ""; // ֤������

    beginoweMonth = "";// ��������

    endoweMonth = "";

    offic = "";// ���´�

    loanBankName = "";// �ſ�����

    developer = "";// ������

    buyhouseorgid = "";// ������ID

    assistantorg = "";// ������˾

    floorId = "";

    count = "";
    oweCorpusTotle = new BigDecimal(0.00);// Ƿ������-�ܶ�

    oweInterestTotle = new BigDecimal(0.00);// Ƿ����Ϣ-�ܶ�

    punishInterest = new BigDecimal(0.00);// Ƿ����Ϣ��Ϣ-�ܶ�

    assistantorgList = new ArrayList();// ������˾

  }

  public String getFloorid() {
    return floorid;
  }

  public void setFloorid(String floorid) {
    this.floorid = floorid;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public String getFloorNum() {
    return floorNum;
  }

  public void setFloorNum(String floorNum) {
    this.floorNum = floorNum;
  }

  public String getFloorNum_() {
    return floorNum_;
  }

  public void setFloorNum_(String floorNum_) {
    this.floorNum_ = floorNum_;
  }

  public String getHeadname() {
    return headname;
  }

  public void setHeadname(String headname) {
    this.headname = headname;
  }

  public String getRoomNum() {
    return roomNum;
  }

  public void setRoomNum(String roomNum) {
    this.roomNum = roomNum;
  }

  public String getBuildAreaMax() {
    return buildAreaMax;
  }

  public void setBuildAreaMax(String buildAreaMax) {
    this.buildAreaMax = buildAreaMax;
  }

  public String getBuildAreaMin() {
    return buildAreaMin;
  }

  public void setBuildAreaMin(String buildAreaMin) {
    this.buildAreaMin = buildAreaMin;
  }

  public String getStartDateMax() {
    return startDateMax;
  }

  public void setStartDateMax(String startDateMax) {
    this.startDateMax = startDateMax;
  }

  public String getStartDateMin() {
    return startDateMin;
  }

  public void setStartDateMin(String startDateMin) {
    this.startDateMin = startDateMin;
  }

  public Map getYesNoMap() {
    return yesNoMap;
  }

  public void setYesNoMap(Map yesNoMap) {
    this.yesNoMap = yesNoMap;
  }
}
