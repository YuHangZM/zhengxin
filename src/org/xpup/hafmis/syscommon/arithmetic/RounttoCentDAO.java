package org.xpup.hafmis.syscommon.arithmetic;

import java.math.BigDecimal;

/**
 * �������뵽��
 * 
 *@author ���
 *2007-6-21
 */
public class RounttoCentDAO implements ArithmeticInterface{

  /** ���ݹ��ʻ����ͽ��ʰ���Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * salaryBase ���ʻ���
   * rate ����
   */
  public BigDecimal getPay(BigDecimal salaryBase, BigDecimal rate) {
    // TODO Auto-generated method stub
    BigDecimal pay = new BigDecimal(0.00);
    pay = salaryBase.multiply(rate);
    pay = pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
    return pay;
  }
  
  /**���ݽɶ��Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * pay �ɶ�
   */
  public BigDecimal getPay(BigDecimal pay) {
    // TODO Auto-generated method stub
    pay = pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
    return pay;
  }
  
}