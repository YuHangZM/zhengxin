package org.xpup.hafmis.syscollection.paymng.agent.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Copy Right Information : ���۱��ActionForm Goldsoft Project : AgentChgAF
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.19
 */
public class AgentChgAF extends ActionForm {
  /** �������� */
  private String agentHeadId = "";

  /** �ɴ����� */
  private String agentYearMonth = "";

  /** �б����� */
  private List list;

  /** �жϰ�ť���õ�״̬ */
  private String agentStatus;

  /** ��λ��ϸ��ʾ����λ���ۺ�&��λID */
  private String agentOrgpopId = "";

  private String agentOrgpopkouCode = "";

  private String agentEmppopId = "";

  private String agentEmppopname = "";

  private String agentEmppopkouCode = "";

  private String agentEmppopCardID = "";

  List emplist = new ArrayList();

  /** �ϼƵ����� */
  private Integer empCount = new Integer(0);

  private Integer orgCount = new Integer(0);

  private BigDecimal sumAgentOrgPay = new BigDecimal(0.00);

  private BigDecimal sumAgentEmpPay = new BigDecimal(0.00);

  private BigDecimal sumAgentEmpSalary = new BigDecimal(0.00);

  public List getEmplist() {
    return emplist;
  }

  public void setEmplist(List emplist) {
    this.emplist = emplist;
  }

  public String getAgentEmppopCardID() {
    return agentEmppopCardID;
  }

  public void setAgentEmppopCardID(String agentEmppopCardID) {
    this.agentEmppopCardID = agentEmppopCardID;
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

  public String getAgentEmppopname() {
    return agentEmppopname;
  }

  public void setAgentEmppopname(String agentEmppopname) {
    this.agentEmppopname = agentEmppopname;
  }

  public String getAgentOrgpopkouCode() {
    return agentOrgpopkouCode;
  }

  public void setAgentOrgpopkouCode(String agentOrgpopkouCode) {
    this.agentOrgpopkouCode = agentOrgpopkouCode;
  }

  public String getAgentOrgpopId() {
    return agentOrgpopId;
  }

  public void setAgentOrgpopId(String agentOrgpopId) {
    this.agentOrgpopId = agentOrgpopId;
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

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Integer getEmpCount() {
    return empCount;
  }

  public void setEmpCount(Integer empCount) {
    this.empCount = empCount;
  }

  public BigDecimal getSumAgentEmpSalary() {
    return sumAgentEmpSalary;
  }

  public void setSumAgentEmpSalary(BigDecimal sumAgentEmpSalary) {
    this.sumAgentEmpSalary = sumAgentEmpSalary;
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

  public Integer getOrgCount() {
    return orgCount;
  }

  public void setOrgCount(Integer orgCount) {
    this.orgCount = orgCount;
  }

  public String getAgentStatus() {
    return agentStatus;
  }

  public void setAgentStatus(String agentStatus) {
    this.agentStatus = agentStatus;
  }
}
