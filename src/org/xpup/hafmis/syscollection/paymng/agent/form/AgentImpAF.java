package org.xpup.hafmis.syscollection.paymng.agent.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Copy Right Information : ���۵���ActionForm Goldsoft Project : AgentImpAF
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentImpAF extends ActionForm {
  /** �ɴ����� */
  private String agentYearMonth = "";

  /** Ʊ�ݱ�� */
  private String noteNum = "";

  /** ������ */
  private String id = "";
  
  /** ��λ�ɶ�ϼ� */
  private BigDecimal sumAgentOrgPay = new BigDecimal(0.00);
  
  /** ְ���ɶ�ϼ� */
  private BigDecimal sumAgentEmpPay = new BigDecimal(0.00);
  
  /** �����ܶ�ϼ� */
  private BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);
  
  private Integer orgCount = new Integer(0);

  /** �б� */
  List list;

  public String getAgentYearMonth() {
    return agentYearMonth;
  }

  public void setAgentYearMonth(String agentYearMonth) {
    this.agentYearMonth = agentYearMonth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getNoteNum() {
    return noteNum;
  }

  public void setNoteNum(String noteNum) {
    this.noteNum = noteNum;
  }

  public Integer getOrgCount() {
    return orgCount;
  }

  public void setOrgCount(Integer orgCount) {
    this.orgCount = orgCount;
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
}
