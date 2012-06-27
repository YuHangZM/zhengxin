package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-���׹���
 * @author ����
 * 2007-10-8
 */
public class FnOpBookmng extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SUBJECT),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_DATAINITIALIZE),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_BOOKSTART),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREDENCECHAR),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SETTLEMODLE),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SUMMARY),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SUBJECTRELATION),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREDENCEMODLE),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_SETTLEINCADDEC),
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREATEBOOK)
      };

  static final String[] values = { "���׹���-��ƿ�Ŀ", "���׹���-��ʼ����", "���׹���-��������",
                                   "���׹���-ƾ֤��", "���׹���-���㷽ʽ", "���׹���-����ժҪ",
                                   "���׹���-��Ŀ��ϵ����", "���׹���-ƾ֤ģʽ����", "���׹���-�����ת����"
                                   ,"���׹���-��������"};

  public FnOpBookmng() {
    this.putValues(keys, values);
  }

}
