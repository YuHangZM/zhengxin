package org.xpup.hafmis.syscollection.paymng.agent.dto;

/**
 * Copy Right Information : ��װ������Ա���ְ����Ϣ��DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.21
 */
public class PersonChgInfoDTO {
  /** ��Ա�����ͷ��id */
  private String id = "";

  /** ��λid */
  private String orgId = "";

  /** ְ��id */
  private String empId = "";

  /** ���ǰ״̬s */
  private String chgType = "";

  private String oldPayStatus = "";

  public String getOldPayStatus() {
    return oldPayStatus;
  }

  public void setOldPayStatus(String oldPayStatus) {
    this.oldPayStatus = oldPayStatus;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getChgType() {
    return chgType;
  }

  public void setChgType(String chgType) {
    this.chgType = chgType;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }
}
