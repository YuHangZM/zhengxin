package org.xpup.hafmis.common.util.enumerate;
/**
 * �ɴ澫��
 * @author ����
 *2007-6-21
 */
import org.xpup.hafmis.common.util.BusiConst;


public class PaymentAccuracy extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.PAYMENTACCURACY_ROUNDTOYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_DISCARDTOYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_SEEKOKONYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_SEECENTSONKOK),
      new Integer(BusiConst.PAYMENTACCURACY_ROUNDTOKOK),
      new Integer(BusiConst.PAYMENTACCURACY_DISCARDTOKOK),
      new Integer(BusiConst.PAYMENTACCURACY_SEEKOKCENTSONYUAN),
      new Integer(BusiConst.PAYMENTACCURACY_ROUNDTOCENT)};

   static final String[] values = { "�������뵽Ԫ", "��Ԫ����","���ǽ�Ԫ","���ֽ���","�������뵽��","�������","���Ƿֽ�Ԫ","�������뵽��" };
  public PaymentAccuracy()
  {
    this.putValues(keys,values);
  }
}
