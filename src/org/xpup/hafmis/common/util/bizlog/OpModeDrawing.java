package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-��ȡ����
 * @author ����
 *2007-6-20 
 */
public class OpModeDrawing extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK)
      };

  static final String[] values = { "��ȡ����-������ȡ-������ȡ", "��ȡ����-������ȡ-��ȡά��","��ȡ����-����������ȡ-������ȡ","��ȡ����-����������ȡ-��ȡά��","�����𻹴�"};

  public OpModeDrawing() {
    this.putValues(keys, values);
  }

}
