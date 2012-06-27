package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-����ʽ��ʶ
 * @author ����
 * 2007-10-6
 */
public class FnReportLogo extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.REPORTLOGO_BEGBALANCE_DEBIT,
      BusiConst.REPORTLOGO_BEGBALANCE_CREDIT,
      BusiConst.REPORTLOGO_ENDBALANCE_DEBIT,
      BusiConst.REPORTLOGO_ENDBALANCE_CREDIT,
      
      BusiConst.REPORTLOGO_CURFIGURES_DEBIT,
      BusiConst.REPORTLOGO_CURFIGURES_CREDIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_DEBIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_CREDIT,
      
      BusiConst.REPORTLOGO_CURFIGURES_SUMDEBIT,
      BusiConst.REPORTLOGO_CURFIGURES_SUMCREDIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT,
      BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT,
      BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT,
      
      BusiConst.REPORTLOGO_COL,

      BusiConst.REPORTLOGO_REPAIR_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_REPAIR_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_REPAIR_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_RETIREMENT_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_RETIREMENT_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_LOSEABILITY_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_LOSEABILITY_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_ABROAD_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_ABROAD_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_ABROAD_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_OTHERS_CURTERMAMOUNT,
      BusiConst.REPORTLOGO_OTHERS_CURYEARAMOUNT,
      BusiConst.REPORTLOGO_OTHERS_CURYEARSUMAMOUNT,
      
      BusiConst.REPORTLOGO_FORMULA
      };

   static final String[] values = { "������", "�ڳ����", "��ĩ�����ã�", "��ĩ���", 
                                    "���ڷ�����跽��" , "���ڷ����������", "�����ۼƷ�����跽��","�����ۼƷ����������" ,"�����ۼƷ�����跽��","�����ۼƷ����������" ,
                                    "�����ۼƷ�����跽�������ã�" , "�����ۼƷ���������������ã�", "��ĩ�ۼƷ�����跽��","��ĩ�ۼƷ����������" ,"�����ۼƷ�����跽�������ã�","�����ۼƷ���������������ã�" ,
                                    "��" ,
                                    
                                    "��ȡ�����򡢽��졢����������ס����ȡ�����ڷ�����", "��ȡ�����򡢽��졢����������ס����ȡ�������ۼƷ�����", "��ȡ�����򡢽��졢����������ס����ȡ����ĩ�ۼƷ�����",
                                    "��ȡ����������ȡ�����ڷ�����", "��ȡ����������ȡ�������ۼƷ�����", "��ȡ����������ȡ����ĩ�ۼƷ�����", 
                                    "��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ�����ڷ�����", "��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ�������ۼƷ�����", "��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ����ĩ�ۼƷ�����", 
                                    "��ȡ������������ȡ�����ڷ�����", "��ȡ������������ȡ�������ۼƷ�����", "��ȡ������������ȡ����ĩ�ۼƷ�����",
                                    "��ȡ�������������Ϣ��ȡ�����ڷ�����", "��ȡ�������������Ϣ��ȡ�������ۼƷ�����", "��ȡ�������������Ϣ��ȡ����ĩ�ۼƷ�����",
                                    "��ȡ��֧��������ȡ�����ڷ�����", "��ȡ��֧��������ȡ�������ۼƷ�����", "��ȡ��֧��������ȡ����ĩ�ۼƷ�����",
                                    "��ȡ��������ȡ�����ڷ�����", "��ȡ��������ȡ�������ۼƷ�����", "��ȡ��������ȡ����ĩ�ۼƷ�����", 
                                    
                                    "!��ʽ��"
                                  };
  public FnReportLogo()
  {
    this.putValues_Str(keys,values);
  }
}

