package org.xpup.hafmis.syscommon.arithmetic;

import java.math.BigDecimal;

import org.xpup.common.exception.BusinessException;

public interface ArithmeticInterface{
  //���ݹ��ʻ����ͽ��ʰ���Ӧ�Ľɴ澫�ȷ��ؽɶ�
  public BigDecimal getPay(BigDecimal salaryBase,BigDecimal rate) ;
  //���ݽɶ��Ӧ�Ľɴ澫�ȷ��ؽɶ�
  public BigDecimal getPay(BigDecimal pay) ;
  
}