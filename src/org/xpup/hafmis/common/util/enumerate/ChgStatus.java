package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ��Ա����еı��״̬
 * @author ����
 *2007-6-28
 */
public class ChgStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CHGSTATUS_OPEN),
      new Integer(BusiConst.CHGSTATUS_QF),
      new Integer(BusiConst.CHGSTATUS_FC)};

   static final String[] values = { "����", "����","���"};
  public ChgStatus()
  {
    this.putValues(keys,values);
  }
}
