package org.xpup.hafmis.syscommon.arithmetic;

import java.math.BigDecimal;

/**
 * �������
 * 
 *@author ���
 *2007-6-21
 */
public class DiscardChiaoDAO implements ArithmeticInterface{

  /** ���ݹ��ʻ����ͽ��ʰ���Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * salaryBase ���ʻ���
   * rate ����
   */
  public BigDecimal getPay(BigDecimal salaryBase, BigDecimal rate) {
    // TODO Auto-generated method stub
    if(salaryBase.doubleValue()==0){
//      return new BigDecimal(0.00);
      return salaryBase.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
    }
    BigDecimal pay = new BigDecimal(0.00);
    double p =0;
    p = salaryBase.doubleValue() * rate.doubleValue();
    pay = new BigDecimal(p+0.05);
    pay = pay.subtract(new BigDecimal(0.1)).divide(new BigDecimal(1),1,BigDecimal.ROUND_HALF_UP);
    return pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
  }
  
  /**���ݽɶ��Ӧ�Ľɴ澫�ȷ��ؽɶ�
   * pay �ɶ�
   */
  public BigDecimal getPay(BigDecimal pay) {
    // TODO Auto-generated method stub
    pay = pay.add(new BigDecimal(0.05));
    pay = pay.subtract(new BigDecimal(0.1)).divide(new BigDecimal(1),1,BigDecimal.ROUND_HALF_UP);

    return pay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_UP);
  }
  
}