package org.xpup.hafmis.sysloan.loanapply.endorsecontract.form;

import java.math.BigDecimal;

public class EndorsecontractTaChangeLoanMonthRateAF{

  /**
   * ����ͨ�����б任�������ʣ��»���Ϣ
   * shiy
   * 2007.11.14
   */
  private static final long serialVersionUID = 8822349740458010093L;

  private BigDecimal loanMonthRate = new BigDecimal(0.00);

  private String corpusInterest = "";

  public String getCorpusInterest() {
    return corpusInterest;
  }

  public void setCorpusInterest(String corpusInterest) {
    this.corpusInterest = corpusInterest;
  }

  public BigDecimal getLoanMonthRate() {
    return loanMonthRate;
  }

  public void setLoanMonthRate(BigDecimal loanMonthRate) {
    this.loanMonthRate = loanMonthRate;
  }
}
