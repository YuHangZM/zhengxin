package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-�ۿʽ
 * @author ����
 * 2007-9-13
 */
public class PLDebitType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLDEBITTYPE_SUFF),
                                  new Integer(BusiConst.PLDEBITTYPE_ALL) };

  static final String[] values = { "���ۿ�", "ȫ��ۿ�" };

  public PLDebitType() {
    this.putValues(keys, values);
  }
}
