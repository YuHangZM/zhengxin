package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : ��װ�˴��۱���б����ݵ�DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentChgListDTO {
  /** �������� */
  private String agentHeadId = "";

  /** Ʊ�ݱ�� */
  private String noteNum = "";

  /** ������� */
  private String agentYearMonth = "";

  /** ��λ�� */
  private Integer countOrg = new Integer(0);

  /** ְ���� */
  private Integer countEmp = new Integer(0);

  /** ��λ�ܽɶ� */
  private BigDecimal sumAgentOrgPay = new BigDecimal(0.00);

  /** ְ���ܽɶ� */
  private BigDecimal sumAgentEmpPay = new BigDecimal(0.00);

  /** ְ���ܹ��� */
  private BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);

  /** ״̬ */
  private String status = "";
  
  /** ״̬(����) */
  private String strStatus = "";
  
  /** ְ���������id */
  private String empAgentId = "";
  
  /** �ɴ淽ʽ */
  private String payMode = "";
  
  //ְ����ϸ��Ϣ
  private String agentEmppopId = "";
  private String agentEmppopname = "";
  private String agentEmppopkouCode = "";
  private String agentEmppopCardID = "";
  private BigDecimal agentEmppoporgpay = new BigDecimal(0.00);
  private BigDecimal agentEmppopemppay = new BigDecimal(0.00);
  private BigDecimal agentEmppopmonthsalary = new BigDecimal(0.00);

  public String getAgentEmppopCardID() {
    return agentEmppopCardID;
  }

  public void setAgentEmppopCardID(String agentEmppopCardID) {
    this.agentEmppopCardID = agentEmppopCardID;
  }

  public BigDecimal getAgentEmppopemppay() {
    return agentEmppopemppay;
  }

  public void setAgentEmppopemppay(BigDecimal agentEmppopemppay) {
    this.agentEmppopemppay = agentEmppopemppay;
  }

  public String getAgentEmppopId() {
    return agentEmppopId;
  }

  public void setAgentEmppopId(String agentEmppopId) {
    this.agentEmppopId = agentEmppopId;
  }

  public String getAgentEmppopkouCode() {
    return agentEmppopkouCode;
  }

  public void setAgentEmppopkouCode(String agentEmppopkouCode) {
    this.agentEmppopkouCode = agentEmppopkouCode;
  }

  public BigDecimal getAgentEmppopmonthsalary() {
    return agentEmppopmonthsalary;
  }

  public void setAgentEmppopmonthsalary(BigDecimal agentEmppopmonthsalary) {
    this.agentEmppopmonthsalary = agentEmppopmonthsalary;
  }

  public String getAgentEmppopname() {
    return agentEmppopname;
  }

  public void setAgentEmppopname(String agentEmppopname) {
    this.agentEmppopname = agentEmppopname;
  }

  public BigDecimal getAgentEmppoporgpay() {
    return agentEmppoporgpay;
  }

  public void setAgentEmppoporgpay(BigDecimal agentEmppoporgpay) {
    this.agentEmppoporgpay = agentEmppoporgpay;
  }

  public String getAgentHeadId() {
    return agentHeadId;
  }

  public void setAgentHeadId(String agentHeadId) {
    this.agentHeadId = agentHeadId;
  }

  public String getAgentYearMonth() {
    return agentYearMonth;
  }

  public void setAgentYearMonth(String agentYearMonth) {
    this.agentYearMonth = agentYearMonth;
  }

  public Integer getCountEmp() {
    return countEmp;
  }

  public void setCountEmp(Integer countEmp) {
    this.countEmp = countEmp;
  }

  public Integer getCountOrg() {
    return countOrg;
  }

  public void setCountOrg(Integer countOrg) {
    this.countOrg = countOrg;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BigDecimal getSumAgentEmpPay() {
    return sumAgentEmpPay;
  }

  public void setSumAgentEmpPay(BigDecimal sumAgentEmpPay) {
    this.sumAgentEmpPay = sumAgentEmpPay;
  }

  public BigDecimal getSumAgentEmpSalary() {
    return sumAgentEmpSalary;
  }

  public void setSumAgentEmpSalary(BigDecimal sumAgentEmpSalary) {
    this.sumAgentEmpSalary = sumAgentEmpSalary;
  }

  public BigDecimal getSumAgentOrgPay() {
    return sumAgentOrgPay;
  }

  public void setSumAgentOrgPay(BigDecimal sumAgentOrgPay) {
    this.sumAgentOrgPay = sumAgentOrgPay;
  }

  public String getEmpAgentId() {
    return empAgentId;
  }

  public void setEmpAgentId(String empAgentId) {
    this.empAgentId = empAgentId;
  }

  public String getStrStatus() {
    return strStatus;
  }

  public void setStrStatus(String strStatus) {
    this.strStatus = strStatus;
  }

  public String getPayMode() {
    return payMode;
  }

  public void setPayMode(String payMode) {
    this.payMode = payMode;
  }
}
