package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��ǰ������ͽ��
 * @author ����
 * 2007-9-14
 */
public class PLMinsPrepayment extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLMINSPREPAYMENT_1) 
                                };

  static final String[] values = { "��ǰ������ͽ��" };

  public PLMinsPrepayment() {
    this.putValues(keys, values);
  }
}

