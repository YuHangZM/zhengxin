package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����ҵ������
 * @author ����
 *Jul 3, 2007
 */
public class ChgBusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.CHGBUSINESSTYPE_K,
      BusiConst.CHGBUSINESSTYPE_L,
      BusiConst.CHGBUSINESSTYPE_G,
      };

   static final String[] values = { "���ɴ�", "����ȡ", "������" };
  public ChgBusinessType()
  {
    this.putValues_Str(keys,values);
  }
}
