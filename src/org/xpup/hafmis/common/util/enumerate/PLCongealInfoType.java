package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PLCongealInfoType extends AbsBusiProMap {
  /**
   * 
   */
  private static final long serialVersionUID = -2637814656795169943L;

  /**
   * ��ͬ�����Ϣ
   * �޸ĺ�ͬ����1.�������Ϣ��2.�����������Ϣ3.������Ϣ4.��Ѻ��ͬ��Ϣ��5.��Ѻ��ͬ��Ϣ6.��֤��
   * ʯ��
   */
 

  static final Integer[] keys = { 
      new Integer(BusiConst.PLPREPAYMENTFEES_BORROWERTYPE),
      new Integer(BusiConst.PLPREPAYMENTFEES_AUXILIARYTYPE),
      new Integer(BusiConst.PLPREPAYMENTFEES_BIALTYPE) };

  static final String[] values = { "�����", "���������","��֤��" };

  public PLCongealInfoType() {
    this.putValues(keys, values);
  }

}
