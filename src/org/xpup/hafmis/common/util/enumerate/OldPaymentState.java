package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ���ǰ�ɴ�״̬
 * @author ����
 *2007-7-6
 */
public class OldPaymentState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.OLDPAYMENTSTATE_1),
      new Integer(BusiConst.OLDPAYMENTSTATE_2),
      new Integer(BusiConst.OLDPAYMENTSTATE_3),
      new Integer(BusiConst.OLDPAYMENTSTATE_4),
      new Integer(BusiConst.OLDPAYMENTSTATE_5),
      new Integer(BusiConst.OLDPAYMENTSTATE_6)
      };

   static final String[] values = { "����", "���", "����", "����", "ɾ��", "���˻�" };
  public OldPaymentState()
  {
    this.putValues(keys,values);
  }
}

