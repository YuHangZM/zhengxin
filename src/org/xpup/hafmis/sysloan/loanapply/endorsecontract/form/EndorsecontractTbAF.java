package org.xpup.hafmis.sysloan.loanapply.endorsecontract.form;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContract;

public class EndorsecontractTbAF extends ActionForm{

  private List list = new ArrayList();
  private Map map = new HashMap();//���֤������map
  
  private String id = "";//
  private String contractId = "";//��ͬID
  private String debitter = "";//��������� PL110 
  private String pledgePerson = "";//��Ѻ������
  private String office = "";//��ѺȨ�ˣ����������ģ�
  private String pledgeContractId = "";//��Ѻ��ͬ���
  private String assistantOrgName = "";//������˾����
  private String pledgeMatterName = "����";//��Ѻ������
  private String paperNum = "";//����Ȩ֤���
  private String paperName = "";//����Ȩ֤����
  private String paperPersonName = "";//����Ȩ������
  private String cardKind = "";//����Ȩ��֤������
  private String carNum = "";//����Ȩ��֤������
  private String tel = "";//����Ȩ�˹̶��绰
  private String mobile = "";//����Ȩ���ƶ��绰
  private String pledgeAddr = "";//��Ѻ���ַ
  private String area = "";//�������
  private String buyHouseContractId = "";//������ͬ���
  private String pledgeValue = "";//��Ѻֵ
  private String evaluateValue = "";//����ֵ
  private String status = "";//��ͬ״̬
  private String loanassistantorgId = "";//������˾���
  private String photoUrl = "";//·��
  
  private String isButtonForbid = "";//��ť�Ƿ�������ԡ�0��ֹ��1����
  private String paramValue = "";//����ֵAB or BA
  private String isComeFormAdd = "";//�Ƿ����ӹ���
  private String isReadOnly = "";//�Ƿ�ֻ��

  private String IsNeedDel = "";
  private String rebate = "";//�ۿ���
  
  private String sex = "";//������Ա�
  private String age = "";//����
  private String orgName = "";//������λ����
  private String orgAddress = "";//��λ��ַ
  private String orgTel = "";//��λ�绰
  private String sfz = "";//���֤
  
  private String debitMoney = "";//������
  private String houseType = "";//��������
  private Borrower borrower=new Borrower();
  private BorrowerAcc borrowerAcc=new BorrowerAcc();
  private BorrowerLoanInfo borrowerLoanInfo=new BorrowerLoanInfo();
  private String bankName="";
  
  private String isPl121 = "";//PL121�Ƿ����
  private String dyrgddh = "";//��Ѻ�˹̶��绰
  private String dyryddh = "";//��Ѻ���ƶ��绰
  private LoanContract loanContract=new LoanContract();
  
  

  public String getDyrgddh() {
    return dyrgddh;
  }

  public void setDyrgddh(String dyrgddh) {
    this.dyrgddh = dyrgddh;
  }

  public String getDyryddh() {
    return dyryddh;
  }

  public void setDyryddh(String dyryddh) {
    this.dyryddh = dyryddh;
  }

  public String getIsPl121() {
    return isPl121;
  }

  public void setIsPl121(String isPl121) {
    this.isPl121 = isPl121;
  }

  public String getHouseType() {
    return houseType;
  }

  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  public String getSfz() {
    return sfz;
  }

  public void setSfz(String sfz) {
    this.sfz = sfz;
  }

  public String getRebate() {
    return rebate;
  }

  public void setRebate(String rebate) {
    this.rebate = rebate;
  }

  public String getIsNeedDel() {
    return IsNeedDel;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public void setIsNeedDel(String isNeedDel) {
    IsNeedDel = isNeedDel;
  }

  public String getIsReadOnly() {
    return isReadOnly;
  }

  public void setIsReadOnly(String isReadOnly) {
    this.isReadOnly = isReadOnly;
  }

  public String getIsComeFormAdd() {
    return isComeFormAdd;
  }

  public void setIsComeFormAdd(String isComeFormAdd) {
    this.isComeFormAdd = isComeFormAdd;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getAssistantOrgName() {
    return assistantOrgName;
  }

  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
  }

  public String getBuyHouseContractId() {
    return buyHouseContractId;
  }

  public void setBuyHouseContractId(String buyHouseContractId) {
    this.buyHouseContractId = buyHouseContractId;
  }

  public String getCardKind() {
    return cardKind;
  }

  public void setCardKind(String cardKind) {
    this.cardKind = cardKind;
  }

  public String getCarNum() {
    return carNum;
  }

  public void setCarNum(String carNum) {
    this.carNum = carNum;
  }

  public String getEvaluateValue() {
    return evaluateValue;
  }

  public void setEvaluateValue(String evaluateValue) {
    this.evaluateValue = evaluateValue;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getPaperName() {
    return paperName;
  }

  public void setPaperName(String paperName) {
    this.paperName = paperName;
  }

  public String getPaperNum() {
    return paperNum;
  }

  public void setPaperNum(String paperNum) {
    this.paperNum = paperNum;
  }

  public String getPaperPersonName() {
    return paperPersonName;
  }

  public void setPaperPersonName(String paperPersonName) {
    this.paperPersonName = paperPersonName;
  }

  public String getPledgeAddr() {
    return pledgeAddr;
  }

  public void setPledgeAddr(String pledgeAddr) {
    this.pledgeAddr = pledgeAddr;
  }

  public String getPledgeContractId() {
    return pledgeContractId;
  }

  public void setPledgeContractId(String pledgeContractId) {
    this.pledgeContractId = pledgeContractId;
  }

  public String getPledgeMatterName() {
    return pledgeMatterName;
  }

  public void setPledgeMatterName(String pledgeMatterName) {
    this.pledgeMatterName = pledgeMatterName;
  }

  public String getPledgePerson() {
    return pledgePerson;
  }

  public void setPledgePerson(String pledgePerson) {
    this.pledgePerson = pledgePerson;
  }

  public String getPledgeValue() {
    return pledgeValue;
  }

  public void setPledgeValue(String pledgeValue) {
    this.pledgeValue = pledgeValue;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
  }

  public String getParamValue() {
    return paramValue;
  }

  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getDebitter() {
    return debitter;
  }

  public void setDebitter(String debitter) {
    this.debitter = debitter;
  }

  public String getIsButtonForbid() {
    return isButtonForbid;
  }

  public void setIsButtonForbid(String isButtonForbid) {
    this.isButtonForbid = isButtonForbid;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getLoanassistantorgId() {
    return loanassistantorgId;
  }

  public void setLoanassistantorgId(String loanassistantorgId) {
    this.loanassistantorgId = loanassistantorgId;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getOrgAddress() {
    return orgAddress;
  }

  public void setOrgAddress(String orgAddress) {
    this.orgAddress = orgAddress;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getOrgTel() {
    return orgTel;
  }

  public void setOrgTel(String orgTel) {
    this.orgTel = orgTel;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getDebitMoney() {
    return debitMoney;
  }

  public void setDebitMoney(String debitMoney) {
    this.debitMoney = debitMoney;
  }

  public Borrower getBorrower() {
    return borrower;
  }

  public void setBorrower(Borrower borrower) {
    this.borrower = borrower;
  }

  public BorrowerAcc getBorrowerAcc() {
    return borrowerAcc;
  }

  public void setBorrowerAcc(BorrowerAcc borrowerAcc) {
    this.borrowerAcc = borrowerAcc;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public LoanContract getLoanContract() {
    return loanContract;
  }

  public void setLoanContract(LoanContract loanContract) {
    this.loanContract = loanContract;
  }

  public BorrowerLoanInfo getBorrowerLoanInfo() {
    return borrowerLoanInfo;
  }

  public void setBorrowerLoanInfo(BorrowerLoanInfo borrowerLoanInfo) {
    this.borrowerLoanInfo = borrowerLoanInfo;
  }

  
}
