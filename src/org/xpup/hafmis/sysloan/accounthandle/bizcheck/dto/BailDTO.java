package org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto;

import java.math.BigDecimal;

public class BailDTO {
  private String flowHeadId = ""; // pl202ͷ��Id

  private String loankouacc = "";// �����˺�

  private String contractId = "";// ��ͬ���

  private String borrowerName = "";// ���������

  private BigDecimal bailMoney = new BigDecimal(0.00);// ��֤��������

  private BigDecimal accrual = new BigDecimal(0.00);// ��ȡ��Ϣ

  private BigDecimal overpusLoanMoney = new BigDecimal(0.00);// �������

  private BigDecimal noBackMoney = new BigDecimal(0.00);// ����δ�ջؽ��

  private BigDecimal ovaerLoanRepay = new BigDecimal(0.00);// �������

  public BigDecimal getAccrual() {
    return accrual;
  }

  public void setAccrual(BigDecimal accrual) {
    this.accrual = accrual;
  }

  public BigDecimal getBailMoney() {
    return bailMoney;
  }

  public void setBailMoney(BigDecimal bailMoney) {
    this.bailMoney = bailMoney;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getLoankouacc() {
    return loankouacc;
  }

  public void setLoankouacc(String loankouacc) {
    this.loankouacc = loankouacc;
  }

  public BigDecimal getNoBackMoney() {
    return noBackMoney;
  }

  public void setNoBackMoney(BigDecimal noBackMoney) {
    this.noBackMoney = noBackMoney;
  }

  public BigDecimal getOvaerLoanRepay() {
    return ovaerLoanRepay;
  }

  public void setOvaerLoanRepay(BigDecimal ovaerLoanRepay) {
    this.ovaerLoanRepay = ovaerLoanRepay;
  }

  public BigDecimal getOverpusLoanMoney() {
    return overpusLoanMoney;
  }

  public void setOverpusLoanMoney(BigDecimal overpusLoanMoney) {
    this.overpusLoanMoney = overpusLoanMoney;
  }

  public String getFlowHeadId() {
    return flowHeadId;
  }

  public void setFlowHeadId(String flowHeadId) {
    this.flowHeadId = flowHeadId;
  }

}
