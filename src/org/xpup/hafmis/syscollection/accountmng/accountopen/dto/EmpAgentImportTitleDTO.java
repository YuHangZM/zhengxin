package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.imp.domn.interfaces.impDto;

/**
 * Copy Right Information : ְ�����۵���-��ͷDTO Goldsoft Project :
 * EmpAgentImportTitleDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.13
 */
public class EmpAgentImportTitleDTO extends impDto {
  private String orgId = "";

  private String orgName = "";

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

}
