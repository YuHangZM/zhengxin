package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class CredenceNumType extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {   
      BusiConst.CREDENCE_NUM_TYPE_ACCOUNTANT,
      BusiConst.CREDENCE_NUM_TYPE_TREASURER};

   static final String[] values = { "���ƾ֤","����ƾ֤"};
  public CredenceNumType()
  {
    this.putValues_Str(keys,values);
  }
}
