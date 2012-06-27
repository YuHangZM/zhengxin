package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PLContractChangeInfo extends AbsBusiProMap {
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
      new Integer(BusiConst.PLPREPAYMENTFEES_BORROWERINFO),
      new Integer(BusiConst.PLPREPAYMENTFEES_AUXILIARYINFO),
      new Integer(BusiConst.PLPREPAYMENTFEES_FLOORINFO),
      new Integer(BusiConst.PLPREPAYMENTFEES_PLEDGEINFO),
      new Integer(BusiConst.PLPREPAYMENTFEES_IMPAWNINFO),
      new Integer(BusiConst.PLPREPAYMENTFEES_BIALINFO) };

  static final String[] values = { "�������Ϣ", "�����������Ϣ","������Ϣ","��Ѻ��ͬ��Ϣ","��Ѻ��ͬ��Ϣ","��֤��" };

  public PLContractChangeInfo() {
    this.putValues(keys, values);
  }

}