package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class EspecialOperationType extends AbsBusiProMap{

  /**
   * ����
   * ����ҵ������
   */
  private static final long serialVersionUID = 1L;

  static final Integer[] keys = { new Integer(BusiConst.ESPECIALOPERATIONTYPE_AGENT),
    new Integer(BusiConst.ESPECIALOPERATIONTYPE_LOANBACK) };

static final String[] values = { "��������", "�����𻹴�" };

public EspecialOperationType() {
  this.putValues(keys, values);
}
}
