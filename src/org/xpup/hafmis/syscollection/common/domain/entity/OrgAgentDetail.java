package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;

public class OrgAgentDetail {
  /** ��λ�������id */
  private Serializable orgAgentId;

  /** ��λ���ۺ� */
  private String orgAgentNum = "";

  /** �������id */
  private Integer agentHeadId = new Integer(0);

  public Integer getAgentHeadId() {
    return agentHeadId;
  }

  public void setAgentHeadId(Integer agentHeadId) {
    this.agentHeadId = agentHeadId;
  }

  public Serializable getOrgAgentId() {
    return orgAgentId;
  }

  public void setOrgAgentId(Serializable orgAgentId) {
    this.orgAgentId = orgAgentId;
  }

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
  }
}
