package org.xpup.hafmis.common.util.enumerate;
/**
 * ��λ�ɴ淽ʽ
 * @author ����
 *2007-6-21
 */
import org.xpup.hafmis.common.util.BusiConst;

public class OrgPayWay extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.ORGPAYWAY_RATE),
      new Integer(BusiConst.ORGPAYWAY_PAYMENT)};

   static final String[] values = { "��һ����", "ְ���ɶ�" };
  public OrgPayWay()
  {
    this.putValues(keys,values);
  }
}
