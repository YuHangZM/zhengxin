package org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest;

import java.math.BigDecimal;

public abstract class CorpusinterestBS {

  /**
   * �»���Ϣ
   * 
   * @param overplusLoanMoney ʣ�౾��
   * @param loanRate ������
   * @param timelimit ʣ������
   * @return
   */
  public static BigDecimal getCorpusInterest(BigDecimal overplusLoanMoney,
      BigDecimal loanRate, String timelimit) {
    // �»���Ϣ=ʣ�౾��*��1+�����ʣ�^ʣ������*������/(1+������)^ʣ������-1
    BigDecimal corpusInterest = new BigDecimal(0.00);
    try {
      if (overplusLoanMoney.doubleValue() > 0) {
        BigDecimal temp_loanRate = new BigDecimal(1.00).add(loanRate);// ��1+�����ʣ�
        BigDecimal tempMoney = new BigDecimal(Math.pow(temp_loanRate
            .doubleValue(), Double.parseDouble(timelimit)));// ��1+�����ʣ�^ʣ������
        BigDecimal temp = tempMoney.subtract(new BigDecimal(1.00));// (1+������)^ʣ������-1
        corpusInterest = overplusLoanMoney.multiply(tempMoney).multiply(
            loanRate).divide(temp, 2, BigDecimal.ROUND_HALF_UP);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return corpusInterest;
  }
}