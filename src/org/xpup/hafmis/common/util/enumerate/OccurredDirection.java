package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * �����������
 * @author ����
 *
 */
public class OccurredDirection extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
      new Integer(BusiConst.OCCURREDDIRECTION_DEBIT),
      new Integer(BusiConst.OCCURREDDIRECTION_CREDIT),
      new Integer(BusiConst.OCCURREDDIRECTION_PARALLEL)
      };
  static final String[] values = { "��", "��","ƽ" };
  public OccurredDirection()
  {
    this.putValues(keys,values);
  }
}
