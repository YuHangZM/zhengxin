package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto;

import java.math.BigDecimal;

public class OverDueinfoQueryTotleDTO {
  private  int count = 0;

  private BigDecimal oweCorpusTotle = new BigDecimal(0.00);// Ƿ������-�ܶ�

  private BigDecimal oweInterestTotle = new BigDecimal(0.00);// Ƿ����Ϣ-�ܶ�

  private BigDecimal punishInterest = new BigDecimal(0.00);// Ƿ����Ϣ��Ϣ-�ܶ�



  public BigDecimal getOweCorpusTotle() {
    return oweCorpusTotle;
  }

  public void setOweCorpusTotle(BigDecimal oweCorpusTotle) {
    this.oweCorpusTotle = oweCorpusTotle;
  }

  public BigDecimal getOweInterestTotle() {
    return oweInterestTotle;
  }

  public void setOweInterestTotle(BigDecimal oweInterestTotle) {
    this.oweInterestTotle = oweInterestTotle;
  }

  public BigDecimal getPunishInterest() {
    return punishInterest;
  }

  public void setPunishInterest(BigDecimal punishInterest) {
    this.punishInterest = punishInterest;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
