package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ��������˹�ϵ 1����ż��2����ĸ��3����Ů ��� 20090404 �ڼ�
 */
public class Relation extends AbsBusiProMap {
  private static final long serialVersionUID = 1L;

  static final Integer[] keys = { new Integer(BusiConst.RELATION_CONSORT),
      new Integer(BusiConst.RELATION_PARENT),
      new Integer(BusiConst.RELATION_CHILD) };

  static final String[] values = { "��ż", "��ĸ", "��Ů" };

  public Relation() {
    this.putValues(keys, values);
  }

}
