package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : ������ϢDTO Goldsoft Project : AgentInfoDTO
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentInfoDTO {
  /** ��λid */
  private String orgId = "";

  /** ��λ���� */
  private String orgName = "";

  /** ��λ���ۺ� */
  private String orgAgentNum = "";

  /** ��λ�������id */
  private String orgAgentId = "";

  /** ���� */
  private Integer countEmpId = new Integer(0);

  /** ��λ�ܽɶ� */
  private BigDecimal sumAgentOrgPay = new BigDecimal(0.00);

  /** ְ���ܽɶ� */
  private BigDecimal sumAgentEmpPay = new BigDecimal(0.00);

  /** ְ���ܹ��� */
  private BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);

  /** ְ��id */
  private String empId = "";

  /** ְ�����ۺ� */
  private String empAgentNum = "";

  /** ְ������ */
  private String empName = "";

  /** ���֤ */
  private String cardNum = "";

  /** ��λ�ɶ� */
  private BigDecimal agentOrgPay = new BigDecimal(0.00);

  /** ְ���ɶ� */
  private BigDecimal agentEmpPay = new BigDecimal(0.00);

  /** ְ������ */
  private BigDecimal agentEmpSalary = new BigDecimal(0.00);
  
  /** �ɴ淽ʽ */
  private String payMode = "";

  public BigDecimal getSumAgentEmpSalary() {
    return sumAgentEmpSalary;
  }

  public void setSumAgentEmpSalary(BigDecimal sumAgentEmpSalary) {
    this.sumAgentEmpSalary = sumAgentEmpSalary;
  }

  public String getOrgAgentNum() {
    return orgAgentNum;
  }

  public void setOrgAgentNum(String orgAgentNum) {
    this.orgAgentNum = orgAgentNum;
  }

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

  public BigDecimal getSumAgentEmpPay() {
    return sumAgentEmpPay;
  }

  public void setSumAgentEmpPay(BigDecimal sumAgentEmpPay) {
    this.sumAgentEmpPay = sumAgentEmpPay;
  }

  public BigDecimal getSumAgentOrgPay() {
    return sumAgentOrgPay;
  }

  public void setSumAgentOrgPay(BigDecimal sumAgentOrgPay) {
    this.sumAgentOrgPay = sumAgentOrgPay;
  }

  public Integer getCountEmpId() {
    return countEmpId;
  }

  public void setCountEmpId(Integer countEmpId) {
    this.countEmpId = countEmpId;
  }

  public String getOrgAgentId() {
    return orgAgentId;
  }

  public void setOrgAgentId(String orgAgentId) {
    this.orgAgentId = orgAgentId;
  }

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

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
}
