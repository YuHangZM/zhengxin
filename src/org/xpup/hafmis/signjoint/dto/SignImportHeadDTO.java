package org.xpup.hafmis.signjoint.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

public class SignImportHeadDTO extends impDto{

  //Orgid ��λ��ţ�orgname ��λ����
  private String orgid="";
  private String orgname="";
  public String getOrgid() {
    return orgid;
  }
  public void setOrgid(String orgid) {
    this.orgid = orgid;
  }
  public String getOrgname() {
    return orgname;
  }
  public void setOrgname(String orgname) {
    this.orgname = orgname;
  }
}
