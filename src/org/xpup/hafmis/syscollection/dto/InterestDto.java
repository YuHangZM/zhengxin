package org.xpup.hafmis.syscollection.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * @author ����
 * 2007-6-26
 * �÷������ڷ��ؼ���������Ϣ��������Ϣ��������Ϣ��
 */
public class InterestDto implements Serializable{

  private static final long serialVersionUID = 0L;
  
  private BigDecimal  curInterest = new BigDecimal(0.00);//������Ϣ
  private BigDecimal  preInterest = new BigDecimal(0.00);//������Ϣ
  
  public BigDecimal getCurInterest() {
    return curInterest;
  }
  public void setCurInterest(BigDecimal curInterest) {
    this.curInterest = curInterest;
  }
  public BigDecimal getPreInterest() {
    return preInterest;
  }
  public void setPreInterest(BigDecimal preInterest) {
    this.preInterest = preInterest;
  }

}
