package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ҵ��״̬
 * @author ����
 *Jul 3, 2007
 */
public class BusinessState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.BUSINESSSTATE_1),
      new Integer(BusiConst.BUSINESSSTATE_2),
      new Integer(BusiConst.BUSINESSSTATE_3),
      new Integer(BusiConst.BUSINESSSTATE_4),
      new Integer(BusiConst.BUSINESSSTATE_5)};

   static final String[] values = { "¼�����", "�Ǽ�","ȷ��","����","����" };
  public BusinessState()
  {
    this.putValues(keys,values);
  }
}
