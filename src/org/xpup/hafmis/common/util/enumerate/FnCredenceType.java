package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-ƾ֤����
 * @author ����
 * 2007-10-6
 */
public class FnCredenceType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CREDENCETYPE_CASH),
      new Integer(BusiConst.CREDENCETYPE_BANK)};

   static final String[] values = { "�ֽ�ƾ֤", "���д��ƾ֤" };
  public FnCredenceType()
  {
    this.putValues(keys,values);
  }
}
