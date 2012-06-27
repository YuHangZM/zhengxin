/*ת����λ��š�ת����λ���ơ�ת�뵥λ��š�ת�뵥λ���ơ�Ʊ�ݱ�š�ְ����š�ְ����������������͡����֤���롢�Ƿ��Ϣ */

package org.xpup.hafmis.syscollection.tranmng.tranout.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;


public class TranoutHeadExportDTO implements ExpDto{

  private static final long serialVersionUID = 0L;

  private String orgOutid; 
  private String orgOutName;
  private String orgInid; 
  private String orgInName;
  private String noteNum;
  private String expalanation;
  



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





  public String getInfo(String s) {
    if(s.equals("orgOutid"))
      return orgOutid;
    if(s.equals("orgOutName"))
        return orgOutName;
    if(s.equals("orgInid"))
        return orgInid;
    if(s.equals("orgInName"))
      return orgInName;
    if(s.equals("noteNum"))
      return noteNum;
    if(s.equals("expalanation"))
      return expalanation;
    else
        return null;
  }

  public String getExpalanation() {
    return expalanation;
  }

  public void setExpalanation(String expalanation) {
    this.expalanation = expalanation;
  }

  
}
