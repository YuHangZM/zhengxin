package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ��ȡ״̬
 * @author ����
 *
 */
public class PickStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.PICKSTATUS_NO),
      new Integer(BusiConst.PICKSTATUS_YES)
      };

   static final String[] values = { "δʹ��", "��ʹ��" };
  public PickStatus()
  {
    this.putValues(keys,values);
  }
}
