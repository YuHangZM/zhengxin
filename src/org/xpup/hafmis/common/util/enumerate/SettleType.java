package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class SettleType extends AbsBusiProMap {

  /**
   * ���� ��������
   */
  private static final long serialVersionUID = 1L;

  static final Integer[] keys = { new Integer(BusiConst.SETTLETYPE_ALL),
      new Integer(BusiConst.SETTLETYPE_ORG),
      new Integer(BusiConst.SETTLETYPE_BANK),
      new Integer(BusiConst.SETTLETYPE_OFFICE) };

  static final String[] values = { "ȫ��", "��λ", "����", "���´�" };

  public SettleType() {
    this.putValues(keys, values);
  }
}
