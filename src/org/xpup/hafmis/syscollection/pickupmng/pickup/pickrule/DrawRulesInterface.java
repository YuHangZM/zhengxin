package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;

import java.io.Serializable;
import java.math.BigDecimal;

public interface DrawRulesInterface {//һ����ȡ����Ľӿ�
  public BigDecimal getMaxDarwMoney(int orgId,int empId,String reason);
  public boolean isDraw(String reason,Serializable empid);
}
