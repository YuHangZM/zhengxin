package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����ҵ������
 * @author ����
 *2007-7-10
 */
public class ClearAccountBusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.CLEARACCOUNTBUSINESSTYPE_A,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_B,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_M,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_C,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_D,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_E,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_F,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_G,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_K,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_L,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_H,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_I,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_J};

   static final String[] values = { "���", "���˲���","��λ����","����","��ȡ","ת��", "ת��","������","���ɴ�","����ȡ","��Ϣ","����������ת","��������ת" };
  public ClearAccountBusinessType()
  {
    this.putValues_Str(keys,values);
  }
}
