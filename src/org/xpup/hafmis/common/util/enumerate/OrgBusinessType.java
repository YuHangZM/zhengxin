package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ��λ��ҵ������
 * @author jj
 */
public class OrgBusinessType extends AbsBusiProMap {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  static final String[] keys = { 
      BusiConst.ORGBUSINESSTYPE_A,
      BusiConst.ORGBUSINESSTYPE_B,
      BusiConst.ORGBUSINESSTYPE_C,
      BusiConst.ORGBUSINESSTYPE_D,
      BusiConst.ORGBUSINESSTYPE_E,
      BusiConst.ORGBUSINESSTYPE_F,
      BusiConst.ORGBUSINESSTYPE_G,
      BusiConst.ORGBUSINESSTYPE_H,
      BusiConst.ORGBUSINESSTYPE_I,
      BusiConst.ORGBUSINESSTYPE_J,
      BusiConst.ORGBUSINESSTYPE_K,
      BusiConst.ORGBUSINESSTYPE_L,
      BusiConst.ORGBUSINESSTYPE_M,
      BusiConst.ORGBUSINESSTYPE_N,
      BusiConst.ORGBUSINESSTYPE_O,
      BusiConst.ORGBUSINESSTYPE_P,
      BusiConst.ORGBUSINESSTYPE_Q};

   static final String[] values = { "���", "��λ����","����","��ȡ","ת��","ת��", "����","��Ϣ","������ȡ","���ʵ���","���˲���","��ɱ�������","���ʻ�������","�ɶ����","��Ա���","��λ����","��λ���" };
  public OrgBusinessType()
  {
    this.putValues_Str(keys,values);
  }
}
