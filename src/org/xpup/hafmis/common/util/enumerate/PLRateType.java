package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��������
 * @author ����
 * 2007-9-13
 */
public class PLRateType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRATETYPE_SUDTDEMAND),
                                  new Integer(BusiConst.PLRATETYPE_SUBTREGULAR) };

  static final String[] values = { "��������","��������" };

  public PLRateType() {
    this.putValues(keys, values);
  }
}
