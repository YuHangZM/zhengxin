package org.xpup.hafmis.syscommon.arithmetic;

import java.math.BigDecimal;

import org.xpup.common.exception.BusinessException;

/**
 * ���ǽ�Ԫ
 * 
 *@author ���
 *2007-6-21
 */
public class SeekokonYuanDAO implements ArithmeticInterface{

  /** ���ݹ��ʻ����ͽ��ʰ���Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * salaryBase ���ʻ���
   * rate ����
   */
  public BigDecimal getPay(BigDecimal salaryBase, BigDecimal rate) {
    // TODO Auto-generated method stub
    BigDecimal pay = new BigDecimal(0.00);
    double p =0;
    p = salaryBase.doubleValue() * rate.doubleValue();
    pay = new BigDecimal(p+0.4);
    pay = pay.divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
    return pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
  }
  
  /**���ݽɶ��Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * pay �ɶ�
   */
  public BigDecimal getPay(BigDecimal pay) {
    // TODO Auto-generated method stub
    pay = pay.add(new BigDecimal(0.4));
    pay = pay.divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
    return pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
  }
  
}