package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ���״̬
 * @author ����
 * 2007-7-8
 */
public class ChgTypeStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CHGTYPESTATUS_1),
      new Integer(BusiConst.CHGTYPESTATUS_2)};

   static final String[] values = { "δ����", "����"};
  public ChgTypeStatus()
  {
    this.putValues(keys,values);
  }
}
