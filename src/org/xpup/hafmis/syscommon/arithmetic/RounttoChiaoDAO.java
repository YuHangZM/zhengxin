package org.xpup.hafmis.syscommon.arithmetic;

import java.math.BigDecimal;

/**
 * �������뵽��
 * 
 *@author ���
 *2007-6-21
 */
public class RounttoChiaoDAO implements ArithmeticInterface{

  /** ���ݹ��ʻ����ͽ��ʰ���Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * salaryBase ���ʻ���
   * rate ����
   */
  public BigDecimal getPay(BigDecimal salaryBase, BigDecimal rate) {
    // TODO Auto-generated method stub
    BigDecimal pay = new BigDecimal(0.00);
    double p =0;
    p = salaryBase.doubleValue() * rate.doubleValue();
    pay = new BigDecimal(p);
    pay = pay.divide(new BigDecimal(1),1,BigDecimal.ROUND_HALF_UP);
    return pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
  }
  
  /**���ݽɶ��Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * pay �ɶ�
   */
  public BigDecimal getPay(BigDecimal pay) {
    // TODO Auto-generated method stub
    pay = pay.divide(new BigDecimal(1),1,BigDecimal.ROUND_HALF_UP);
   
    return pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
  }
  
}