package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ����-��������������ǰ����
 * @author ����
 * 2007-9-14
 */
public class PLPrepaymentYearTimes extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRPREPAYMENTYEARTIMES_1) 
                                };

  static final String[] values = { "��������������ǰ����" };

  public PLPrepaymentYearTimes() {
    this.putValues(keys, values);
  }
}


