package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-����
 * @author ����
 * 2007-10-6
 */
public class FnBalanceDirection extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.BALANCEDIRECTION_DEBIT,
      BusiConst.BALANCEDIRECTION_CREDIT,
      BusiConst.BALANCEDIRECTION_AVE};

   static final String[] values = { "��", "��","ƽ" };
  public FnBalanceDirection()
  {
    this.putValues_Str(keys,values);
  }
}
