package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-����ҵ����
 * @author ����
 * 2007-9-13
 */
public class PlOpSpecialBuss extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_MAINTAIN)
      };

  static final String[] values = { "����ҵ����-�弶�����޸�-ҵ�����", "����ҵ����-�弶�����޸�-ҵ��ά��", "����ҵ����-��֤��Ǽ�-ҵ�����",
                                   "����ҵ����-��֤��Ǽ�-ҵ��ά��","����ҵ����-��֤���Ϣ-ҵ�����","����ҵ����-��֤���Ϣ-ҵ��ά��"
                                 };

  public PlOpSpecialBuss() {
    this.putValues(keys, values);
  }

}