package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-ƾ֤״̬&ҵ����־����
 * @author ����
 * 2007-10-6
 */
public class FnCredenceState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CREDSTATE_SIGN),
      new Integer(BusiConst.CREDSTATE_CHECK),
      new Integer(BusiConst.CREDSTATE_BOOKKEEPING)};

   static final String[] values = { "ȷ��", "����","����" };
  public FnCredenceState()
  {
    this.putValues(keys,values);
  }
}
