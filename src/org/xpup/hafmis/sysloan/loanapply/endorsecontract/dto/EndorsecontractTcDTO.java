package org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndorsecontractTcDTO {
  private List list = new ArrayList();
  private Map map = new HashMap();//���֤������map
  
  private String id = "";//
  private String contractId = "";//��ͬID
  private String debitter = "";//��������� PL110 
  private String impawnContractId = "";//��Ѻ��ͬ���
  private String assistantOrgName = "";//������˾����
  private String impawnPerson = "";//��Ѻ��
  private String office = "";//��ѺȨ�ˣ����������ģ�
  private String impawnMatterName = "";//��Ѻ������
  private String impawnValue = "";//��Ѻ���ֵ
  private String paperPersonName = "";//����Ȩ������
  private String cardKind = "";//����Ȩ��֤������
  private String carNum = "";//����Ȩ��֤������
  private String paperNum = "";//����Ȩ֤���
  private String paperName = "";//����Ȩ֤����
  private String tel = "";//����Ȩ�˹̶��绰
  private String mobile = "";//����Ȩ���ƶ��绰
  private String status = "";//��ͬ״̬
  
  private String isButtonForbid = "";//��ť�Ƿ�������ԡ�0��ֹ��1����
  private String paramValue = "";//����ֵAB or BA
  private String isComeFormAdd = "";//�Ƿ����ӹ���
  private String isReadOnly = "";//�Ƿ�ֻ��
  public String getAssistantOrgName() {
    return assistantOrgName;
  }
  public void setAssistantOrgName(String assistantOrgName) {
    this.assistantOrgName = assistantOrgName;
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
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getImpawnContractId() {
    return impawnContractId;
  }
  public void setImpawnContractId(String impawnContractId) {
    this.impawnContractId = impawnContractId;
  }
  public String getImpawnMatterName() {
    return impawnMatterName;
  }
  public void setImpawnMatterName(String impawnMatterName) {
    this.impawnMatterName = impawnMatterName;
  }
  public String getImpawnPerson() {
    return impawnPerson;
  }
  public void setImpawnPerson(String impawnPerson) {
    this.impawnPerson = impawnPerson;
  }
  public String getImpawnValue() {
    return impawnValue;
  }
  public void setImpawnValue(String impawnValue) {
    this.impawnValue = impawnValue;
  }
  public String getIsButtonForbid() {
    return isButtonForbid;
  }
  public void setIsButtonForbid(String isButtonForbid) {
    this.isButtonForbid = isButtonForbid;
  }
  public String getIsComeFormAdd() {
    return isComeFormAdd;
  }
  public void setIsComeFormAdd(String isComeFormAdd) {
    this.isComeFormAdd = isComeFormAdd;
  }
  public String getIsReadOnly() {
    return isReadOnly;
  }
  public void setIsReadOnly(String isReadOnly) {
    this.isReadOnly = isReadOnly;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public Map getMap() {
    return map;
  }
  public void setMap(Map map) {
    this.map = map;
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
  public String getParamValue() {
    return paramValue;
  }
  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  
}
