package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.dto;

import java.math.BigDecimal;

public class OrgbusinessflowHeadDTO {

  private Integer count = new Integer(0);//����
  private BigDecimal desumPay = new BigDecimal(0.00);//�跽������
  private BigDecimal cdsumPay = new BigDecimal(0.00);//����������
  private BigDecimal sumInterestPay = new BigDecimal(0.00);//��Ϣ
  
  public Integer getCount() {
    return count;
  }
  public void setCount(Integer count) {
    this.count = count;
  }
  public BigDecimal getSumInterestPay() {
    return sumInterestPay;
  }
  public void setSumInterestPay(BigDecimal sumInterestPay) {
    this.sumInterestPay = sumInterestPay;
  }
  public BigDecimal getCdsumPay() {
    return cdsumPay;
  }
  public void setCdsumPay(BigDecimal cdsumPay) {
    this.cdsumPay = cdsumPay;
  }
  public BigDecimal getDesumPay() {
    return desumPay;
  }
  public void setDesumPay(BigDecimal desumPay) {
    this.desumPay = desumPay;
  }
}
