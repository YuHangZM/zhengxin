package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����ҵ������
 * @author ����
 *2007-7-10
 *
 */
public class ClearAccountBusinessType_WL extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {   
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_A,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_B,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_M,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_C,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_D,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_E,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_F,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_G,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_K,
      BusiConst.CLEARACCOUNTBUSINESSTYPE_WL_L};

   static final String[] values = { "���", "���˲���","��λ����","����","��ȡ","ת��", "ת��","������","���ɴ�","����ȡ" };
  public ClearAccountBusinessType_WL()
  {
    this.putValues_Str(keys,values);
  }
}
