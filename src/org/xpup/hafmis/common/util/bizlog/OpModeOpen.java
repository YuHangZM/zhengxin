package org.xpup.hafmis.common.util.bizlog;

/**
 * ����ģ��-��������
 * @author ����
 *2007-6-20 
 */
public class OpModeOpen extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_OPEN_EMPOPEN),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_MAINTAIN) };

  static final String[] values = { "��λ����-������", "��λ����-����ά��", "ְ������","��λ���-������","��λ���-���ά��" };

  public OpModeOpen() {
    this.putValues(keys, values);
  }

}
