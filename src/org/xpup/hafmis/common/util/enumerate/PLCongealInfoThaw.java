package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PLCongealInfoThaw extends AbsBusiProMap {
  /**
   * ��ͬ�����
   * ʯ��
   */
  private static final long serialVersionUID = -2713181004952743102L;

  static final Integer[] keys = { new Integer(BusiConst.PLPREPAYMENTFEES_CONGEALINFOTHAW),
      new Integer(BusiConst.PLPREPAYMENTFEES_CONGEALINFOGELATION) };

  static final String[] values = { "�ⶳ","����"};

  public PLCongealInfoThaw() {
    this.putValues(keys, values); 
  }

}