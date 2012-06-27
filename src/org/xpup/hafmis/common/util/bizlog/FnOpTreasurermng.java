package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-���ɹ���
 * @author ����
 * 2007-10-8
 */
public class FnOpTreasurermng extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BALANCEINITIALIZE),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_AUTOCASHDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CASHDAYCLEAR_CASHDAYCLEARMAINTAIN),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_AUTOBANKDAYCLEAR),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKDAYCLEAR_BANKDAYCLEARMAINTAIN),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACC),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_BANKCHECKACC_BANKCHECKACCMAINTAIN),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_DEPOSITCHECKACC),
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CLEARACCOUNT)
      };

  static final String[] values = { "���ɹ���-����ʼ", "���ɹ���-�ֽ��ռ���-�ֽ��ռ���", "���ɹ���-�ֽ��ռ���-�Զ�ת��",
                                   "���ɹ���-�ֽ��ռ���-�ֽ��ռ���ά��", "���ɹ���-���д���ռ���-���д���ռ���", "���ɹ���-���д���ռ���-�Զ�ת��" ,
                                   "���ɹ���-���д���ռ���-���д���ռ���ά��", "���ɹ���-���ж��˵�-���ж��˵�", "���ɹ���-���ж��˵�-���ж��˵�ά��" ,
                                   "���ɹ���-���д����˵�", "���ɹ���-��������"
                                   };

  public FnOpTreasurermng() {
    this.putValues(keys, values);
  }

}
