package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PLGiveBack extends AbsBusiProMap{
  /**
   * ��������
   * ʯ��
   */
  private static final long serialVersionUID = -2713181004952743102L;

  static final Integer[] keys = { new Integer(BusiConst.PLPREPAYMENTFEES_NORMAL),
      new Integer(BusiConst.PLPREPAYMENTFEES_OVERDUE),
      new Integer(BusiConst.PLPREPAYMENTFEES_OTHERS)};

  static final String[] values = { "����","����","����"};

  public PLGiveBack() {
    this.putValues(keys, values); 
  }
}
