/*��λ��š���λ���ơ��������¡�Ʊ�ݱ�š�ְ����š�ְ����������λӦ�ɽ�ְ��Ӧ�ɽ���λ���ɽ�ְ�����ɽ�������ʱ����һ����0��*/
package org.xpup.hafmis.syscollection.paymng.orgaddpay.dto;


import org.xpup.common.util.imp.domn.interfaces.impDto;


public class OrgaddpayHeadImportDTO extends impDto{

  private static final long serialVersionUID = 0L;

  private String orgid; 

  private String orgName;

  private String addpayMonth;
  
  private String addStartPayMonth;

  private String noteNum;

  public String getAddpayMonth() {
    return addpayMonth;
  }

  public void setAddpayMonth(String addpayMonth) {
    this.addpayMonth = addpayMonth;
  }


  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }


  public String getOrgid() {
    return orgid;
  }

  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public String getAddStartPayMonth() {
    return addStartPayMonth;
  }

  public void setAddStartPayMonth(String addStartPayMonth) {
    this.addStartPayMonth = addStartPayMonth;
  }

  
}
