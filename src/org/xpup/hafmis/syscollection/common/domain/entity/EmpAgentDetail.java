package org.xpup.hafmis.syscollection.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

public class EmpAgentDetail extends DomainObject {
  private Serializable empAgentId;

  /** ְ�����ۺ� */
  private String empAgentNum = "";

  /** ְ������ */
  private String empName = "";

  /** ���֤ */
  private String cardNum = "";

  /** ���۵�λ�ɶ� */
  private BigDecimal agentOrgPay = new BigDecimal(0.00);

  /** ����ְ���ɶ� */
  private BigDecimal agentEmpPay = new BigDecimal(0.00);

  /** ְ������ */
  private BigDecimal agentEmpSalary = new BigDecimal(0.00);

  /** ��λ�������id */
  private Integer orgAgentId = new Integer(0);

  public BigDecimal getAgentEmpPay() {
    return agentEmpPay;
  }

  public void setAgentEmpPay(BigDecimal agentEmpPay) {
    this.agentEmpPay = agentEmpPay;
  }

  public BigDecimal getAgentEmpSalary() {
    return agentEmpSalary;
  }

  public void setAgentEmpSalary(BigDecimal agentEmpSalary) {
    this.agentEmpSalary = agentEmpSalary;
  }

  public BigDecimal getAgentOrgPay() {
    return agentOrgPay;
  }

  public void setAgentOrgPay(BigDecimal agentOrgPay) {
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

  public Integer getOrgAgentId() {
    return orgAgentId;
  }

  public void setOrgAgentId(Integer orgAgentId) {
    this.orgAgentId = orgAgentId;
  }

  public Serializable getEmpAgentId() {
    return empAgentId;
  }

  public void setEmpAgentId(Serializable empAgentId) {
    this.empAgentId = empAgentId;
  }
}
