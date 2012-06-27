package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-������
 * @author ����
 * 2007-9-13
 */
public class PlOpAccountMange extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_OPERATIONCHECK),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_ACCOUNTING_DO),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_DO),
      new Integer(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_EXCESSPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_LATECARRYOVER),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_DAYCLEANING),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_MONTHLATECARRYOVER),
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_CARRYFORWARD)
      };

  static final String[] values = { "������-ҵ�񸴺�", "������-����-����", "������-����-���㵥��ѯ",
                                   "������-���˳���-������˵���", "������-���˳���-���˵���ά��", "������-����-�������",
                                   "������-����-����ά��", "������-���ڽ�ת", "������-�ս�",
                                   "������-��ĩ��ת","������-���ս�ת" };

  public PlOpAccountMange() {
    this.putValues(keys, values);
  }

}
