package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class AppStatus extends AbsBusiProMap {
  /**
   * ���״̬
   * ��ǰ�ı��״̬1��2������������ҵ��Ҫ��������Ӵ�ö���״̬��0��1��
   * ʯ��
   */
  private static final long serialVersionUID = -2713181004952743102L;

  static final Integer[] keys = { new Integer(BusiConst.APPSTATUS_1),
      new Integer(BusiConst.APPSTATUS_2) };

  static final String[] values = { "δ����", "����" };

  public AppStatus() {
    this.putValues(keys, values); 
  }

}
