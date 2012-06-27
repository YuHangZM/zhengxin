package org.xpup.hafmis.sysloan.common.arithmetic.entireyearmoney;



import java.math.BigDecimal;

public abstract class EntireYearMoneyBS {
  /**
   * �������ܻ����
   * @param loanMoney  ������
   * @param loanRate  ������
   * @param loanTime  ��������
   * @return
   */
  public static BigDecimal getEntireYearMoney(BigDecimal loanMoney,BigDecimal loanRate,String loanTime){
    //�������ܻ����=������*������������*�����ʣ�+1��
    BigDecimal entireYearMoney = new BigDecimal(0.00);
    try{
      if(loanMoney.doubleValue()>0){
        BigDecimal temp = new BigDecimal(loanTime).multiply(loanRate).add(new BigDecimal(1.00));//  ����������*�����ʣ�+1
        entireYearMoney = loanMoney.multiply(temp).setScale(2, BigDecimal.ROUND_HALF_UP);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return entireYearMoney;
  }
}
