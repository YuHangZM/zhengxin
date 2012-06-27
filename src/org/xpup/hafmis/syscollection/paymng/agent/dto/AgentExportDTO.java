package org.xpup.hafmis.syscollection.paymng.agent.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;
/**
 * Copy Right Information : ����ģ�浼����DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentExportDTO implements ExpDto {

  public String getInfo(String info) {
    if (info.equals("orgAgentNum")) {
      return this.orgAgentNum;

    }
    if (info.equals("orgName")) {
      return this.orgName;

    }
    if (info.equals("empAgentNum")) {
      return this.empAgentNum;

    }
    if (info.equals("empName")) {
      return this.empName;

    }
    if (info.equals("cardNum")) {
      return this.cardNum;

    }
    if (info.equals("agentOrgPay")) {
      return this.agentOrgPay;

    }
    if (info.equals("agentEmpPay")) {
      return this.agentEmpPay;

    }
    if (info.equals("agentEmpSalary")) {
      return this.agentEmpSalary;

    }
    return null;
  }
  /** ��λ���ۺ� */
  private String orgAgentNum = "";

  /** ��λ���� */
  private String orgName = "";

  /** ְ�����ۺ� */
  private String empAgentNum = "";

  /** ְ������ */
  private String empName = "";

  /** ���֤ */
  private String cardNum = "";

  /** ��λ�ɶ� */
  private String agentOrgPay = "";

  /** ְ���ɶ� */
  private String agentEmpPay = "";

  /** ���� */
  private String agentEmpSalary = "";

  public String getAgentEmpPay() {
    return agentEmpPay;
  }

  public void setAgentEmpPay(String agentEmpPay) {
    this.agentEmpPay = agentEmpPay;
  }

  public String getAgentEmpSalary() {
    return agentEmpSalary;
  }

  public void setAgentEmpSalary(String agentEmpSalary) {
    this.agentEmpSalary = agentEmpSalary;
  }

  public String getAgentOrgPay() {
    return agentOrgPay;
  }

  public void setAgentOrgPay(String agentOrgPay) {
    this.agentOrgPay = agentOrgPay;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getEmpAgentNum() {
    return empAgentNum;
  }

  public void setEmpAgentNum(String empAgentNum) {
    this.empAgentNum = empAgentNum;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}
