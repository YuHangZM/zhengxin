package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.dto;

import java.math.BigDecimal;

public class YearloanDTO {
  private String month = "";//�·�
  
  private int ffhs = 0;//���Ż���
  private BigDecimal ffje = new BigDecimal(0.00);//���Ž��
  private BigDecimal jjmj = new BigDecimal(0.00);//�������
  private BigDecimal ffzj = new BigDecimal(0.00);//�����ܼ�
  public int getFfhs() {
    return ffhs;
  }
  public void setFfhs(int ffhs) {
    this.ffhs = ffhs;
  }
  public BigDecimal getFfje() {
    return ffje;
  }
  public void setFfje(BigDecimal ffje) {
    this.ffje = ffje;
  }
  public BigDecimal getFfzj() {
    return ffzj;
  }
  public void setFfzj(BigDecimal ffzj) {
    this.ffzj = ffzj;
  }
  public BigDecimal getJjmj() {
    return jjmj;
  }
  public void setJjmj(BigDecimal jjmj) {
    this.jjmj = jjmj;
  }
  public String getMonth() {
    return month;
  }
  public void setMonth(String month) {
    this.month = month;
  }
  
}
