package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-�˲�����
 * @author ����
 * 2007-10-8
 */
public class FnOpAccmng extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_ACCMNG_TOTLEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_LISTACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SEQUENCEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTMUSTER),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTDAYREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_CASHDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_BANKDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTBALANCE)
      };

  static final String[] values = { "�˲�����-����", "�˲�����-��ϸ��", "�˲�����-��ʱ��",
                                   "�˲�����-ƾ֤���ܱ�", "�˲�����-��Ŀ�ձ���", "�˲�����-�ֽ��ռ���" ,
                                   "�˲�����-���д���ռ���", "�˲�����-��Ŀ����"
                                   };

  public FnOpAccmng() {
    this.putValues(keys, values);
  }

}
