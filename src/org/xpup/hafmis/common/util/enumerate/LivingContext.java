package org.xpup.hafmis.common.util.enumerate;
/**
 * ��ס���
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class LivingContext extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.LIVINGCONTEXT_REHOUSINGYOURSELF),
      new Integer(BusiConst.LIVINGCONTEXT_MORTGAGE),
      new Integer(BusiConst.LIVINGCONTEXT_RELATIVEHOME),
      new Integer(BusiConst.LIVINGCONTEXT_COLLECTIVEQUARTERS),
      new Integer(BusiConst.LIVINGCONTEXT_RENTHOME),
      new Integer(BusiConst.LIVINGCONTEXT_PUBLICHOME),
      new Integer(BusiConst.LIVINGCONTEXT_OTHERS),
      new Integer(BusiConst.LIVINGCONTEXT_UNKNOW)
      };

   static final String[] values = { "����", "����", "����¥��", "��������", "�ⷿ", "����סլ", "����", "δ֪" };
  public LivingContext()
  {
    this.putValues(keys,values);
  }
}
