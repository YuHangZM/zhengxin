package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-������
 * @author ����
 * 2007-10-8
 */
public class FnOpAccounthandle extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_AUTOTRANSFERS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CARRYOVERPROFITLOSS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECLEAR)
      };

  static final String[] values = { "������-ƾ֤¼��-ƾ֤¼��", "������-ƾ֤¼��-�Զ�ת��", "������-ƾ֤¼��-�����ת",
                                   "������-ƾ֤¼��-ƾ֤ά��", "������-ƾ֤���", "������-ƾ֤����" };

  public FnOpAccounthandle() {
    this.putValues(keys, values);
  }

}
