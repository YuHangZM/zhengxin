package org.xpup.hafmis.syscollection.paymng.agent.dto;

import java.math.BigDecimal;

/**
 * Copy Right Information : ��װ�����ɶ����ְ����Ϣ��DTO Goldsoft Project : AgentImpShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.21
 */
public class PayChgInfoDTO {
  /** �ɶ�����ͷ��id*/
  private String id = "";
  /** ְ����� */
  private String empId = "";
  /** ���ʻ��� */
  private BigDecimal oldSalaryBase = new BigDecimal(0.00);
  /** ��λ�ɶ� */
  private BigDecimal oldOrgPay = new BigDecimal(0.00);
  /** ְ������ */
  private BigDecimal oldEmpPay = new BigDecimal(0.00);
  public String getEmpId() {
    return empId;
  }
  public void setEmpId(String empId) {
    this.empId = empId;
  }
  public BigDecimal getOldEmpPay() {
    return oldEmpPay;
  }
  public void setOldEmpPay(BigDecimal oldEmpPay) {
    this.oldEmpPay = oldEmpPay;
  }
  public BigDecimal getOldOrgPay() {
    return oldOrgPay;
  }
  public void setOldOrgPay(BigDecimal oldOrgPay) {
    this.oldOrgPay = oldOrgPay;
  }
  public BigDecimal getOldSalaryBase() {
    return oldSalaryBase;
  }
  public void setOldSalaryBase(BigDecimal oldSalaryBase) {
    this.oldSalaryBase = oldSalaryBase;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
}
