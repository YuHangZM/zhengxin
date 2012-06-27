package org.xpup.hafmis.common.util.bizlog;

public class OpModeFinance extends BusiLogProMap {

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
      new Integer(BusiLogConst.FN_OP_BOOKMNG_CREATEBOOK),

      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEFILLIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_AUTOTRANSFERS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CARRYOVERPROFITLOSS),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCEMAINTAIN),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECHECK),
      new Integer(BusiLogConst.FN_OP_ACCOUNTHANDLE_CREDENCECLEAR),
     
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
      new Integer(BusiLogConst.FN_OP_TREASURERMNG_CLEARACCOUNT),

      new Integer(BusiLogConst.FN_OP_ACCMNG_TOTLEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_LISTACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SEQUENCEACC),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTMUSTER),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTDAYREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_CASHDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_BANKDAYCLEARREPORT),
      new Integer(BusiLogConst.FN_OP_ACCMNG_SUBJECTBALANCE),

      new Integer(BusiLogConst.FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT)
      
      };

  static final String[] values = { "���׹���-��ƿ�Ŀ", "���׹���-��ʼ����", "���׹���-��������",
                                   "���׹���-ƾ֤��", "���׹���-���㷽ʽ", "���׹���-����ժҪ",
                                   "���׹���-��Ŀ��ϵ����", "���׹���-ƾ֤ģʽ����", "���׹���-�����ת����"
                                   ,"���׹���-��������",
                                   
                                   "������-ƾ֤¼��-ƾ֤¼��", "������-ƾ֤¼��-�Զ�ת��", "������-ƾ֤¼��-�����ת",
                                   "������-ƾ֤¼��-ƾ֤ά��", "������-ƾ֤���", "������-ƾ֤����",
                                   
                                   "���ɹ���-����ʼ", "���ɹ���-�ֽ��ռ���-�ֽ��ռ���", "���ɹ���-�ֽ��ռ���-�Զ�ת��",
                                   "���ɹ���-�ֽ��ռ���-�ֽ��ռ���ά��", "���ɹ���-���д���ռ���-���д���ռ���", "���ɹ���-���д���ռ���-�Զ�ת��" ,
                                   "���ɹ���-���д���ռ���-���д���ռ���ά��", "���ɹ���-���ж��˵�-���ж��˵�", "���ɹ���-���ж��˵�-���ж��˵�ά��" ,
                                   "���ɹ���-���д����˵�", "���ɹ���-��������",
                                   
                                   "�˲�����-����", "�˲�����-��ϸ��", "�˲�����-��ʱ��",
                                   "�˲�����-ƾ֤���ܱ�", "�˲�����-��Ŀ�ձ���", "�˲�����-�ֽ��ռ���" ,
                                   "�˲�����-���д���ռ���", "�˲�����-��Ŀ����",
                                   
                                   "�������-���񱨱�-��������"
                                   
                                 };

  public OpModeFinance() {
    this.putValues(keys, values);
  }

}
