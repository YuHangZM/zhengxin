package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class AddPayType extends AbsBusiProMap {
  /**
   * ����״̬ 
   * 1�������� 2���� 3�����ڲ��� 
   * yqf
   */
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {
      BusiConst.ADDPAYTYPE_A,
      BusiConst.ADDPAYTYPE_B,
      BusiConst.ADDPAYTYPE_C
     };

  static final String[] values = { "��������", "����", "�����ڲ���" };

  public AddPayType() {
    this.putValues_Str(keys, values);
  }
}
