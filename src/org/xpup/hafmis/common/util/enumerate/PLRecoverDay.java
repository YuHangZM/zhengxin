package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-������
 * @author ����
 * 2007-9-13
 */
public class PLRecoverDay extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRECOVERDAY_ACCOUNT),
                                  new Integer(BusiConst.PLRECOVERDAY_DAY) };

  static final String[] values = { "��������", "ͳһ����" };

  public PLRecoverDay() {
    this.putValues(keys, values);
  }
}
