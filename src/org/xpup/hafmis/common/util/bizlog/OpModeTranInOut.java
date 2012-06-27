package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-ת��ת��
 * @author ����
 *2007-6-20 
 */
public class OpModeTranInOut extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_PREPARE),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN)
      };

  static final String[] values = { "ת��ת��-����ת��-����ת��", "ת��ת��-����ת��-ת��ά��","ת��ת��-����ת��-��ת��Ǽ�","ת��ת��-����ת��-ת��Ǽ�","ת��ת��-����ת��-ת��ά��"};

  public OpModeTranInOut() {
    this.putValues(keys, values);
  }

}
