/*ת����λ��š�ת����λ���ơ�ת�뵥λ��š�ת�뵥λ���ơ�Ʊ�ݱ�š�ְ����š�ְ����������������͡����֤���롢�Ƿ��Ϣ */

package org.xpup.hafmis.syscollection.tranmng.tranout.dto;


import org.xpup.common.util.imp.domn.interfaces.impDto;


public class TranoutHeadImportDTO extends impDto{

  private static final long serialVersionUID = 0L;

  private String orgOutid; 
  private String orgOutName;
  private String orgInid; 
  private String orgInName;
  private String noteNum;
  private String orgheadid;
  private String expalanation;



  public String getOrgheadid() {
    return orgheadid;
  }

  public void setOrgheadid(String orgheadid) {
    this.orgheadid = orgheadid;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getOrgInid() {
    return orgInid;
  }

  public void setOrgInid(String orgInid) {
    this.orgInid = orgInid;
  }

  public String getOrgInName() {
    return orgInName;
  }

  public void setOrgInName(String orgInName) {
    this.orgInName = orgInName;
  }

  public String getOrgOutid() {
    return orgOutid;
  }

  public void setOrgOutid(String orgOutid) {
    this.orgOutid = orgOutid;
  }

  public String getOrgOutName() {
    return orgOutName;
  }

  public void setOrgOutName(String orgOutName) {
    this.orgOutName = orgOutName;
  }

  public String getExpalanation() {
    return expalanation;
  }

  public void setExpalanation(String expalanation) {
    this.expalanation = expalanation;
  }



  
}
