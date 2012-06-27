package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class AheadType extends AbsBusiProMap {
  /**
   * ��ǰ��������
   * 1.�ӳ���2.���̣�3.���ı�����
   * jj
   */
  private static final long serialVersionUID = -2713181004952743102L;

  static final Integer[] keys = { new Integer(BusiConst.AHEADTYPE_1),
      new Integer(BusiConst.AHEADTYPE_2),
      new Integer(BusiConst.AHEADTYPE_3)};

  static final String[] values = { "�ӳ�", "����","���ı�����" };

  public AheadType() {
    this.putValues(keys, values); 
  }

}
