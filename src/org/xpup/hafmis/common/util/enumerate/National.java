package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����
 * @author ����
 * 2007-9-13
 */
public class National extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.NATIONAL_HAN),
                                  new Integer(BusiConst.NATIONAL_MAN) };

  static final String[] values = { "����","����" };

  public National() {
    this.putValues(keys, values);
  }
}
