package org.xpup.hafmis.common.util.bizlog;

/**
 * ����ģ��-���ҵ��
 * @author ����
 *2007-6-20 
 */
public class OpModeChange extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_MAINTAIN)
      };

  static final String[] values = { "���ҵ��-��ɱ�������-������", "���ҵ��-��ɱ�������-���ά��","���ҵ��-���ʻ�������-������","���ҵ��-���ʻ�������-���ά��","���ҵ��-�ɶ����-������","���ҵ��-�ɶ����-���ά��","���ҵ��-��Ա���-������","���ҵ��-��Ա���-���ά��" };

  public OpModeChange() {
    this.putValues(keys, values);
  }

}
