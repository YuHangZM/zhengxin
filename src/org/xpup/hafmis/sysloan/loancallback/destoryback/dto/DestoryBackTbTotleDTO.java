package org.xpup.hafmis.sysloan.loancallback.destoryback.dto;

import java.math.BigDecimal;

public class DestoryBackTbTotleDTO {
  private int count = 0;// �б��ܼ�¼��

  private BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// ���ս��-�ܶ�

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }
}
