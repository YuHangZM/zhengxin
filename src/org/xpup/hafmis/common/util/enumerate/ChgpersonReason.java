package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ��Ա����ı��ԭ��
 * @author ����
 *
 */
public class ChgpersonReason extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CHGPERSONREASON_OTHER),
      new Integer(BusiConst.CHGPERSONREASON_OUT),
      new Integer(BusiConst.CHGPERSONREASON_IN),
      new Integer(BusiConst.CHGPERSONREASON_DEL)};

   static final String[] values = { "����", "ת��","ת��","����"};
  public ChgpersonReason()
  {
    this.putValues(keys,values);
  }
}
