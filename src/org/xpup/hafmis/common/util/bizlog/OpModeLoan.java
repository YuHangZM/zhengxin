package org.xpup.hafmis.common.util.bizlog;

public class OpModeLoan extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_RATE),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_BANK),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PARAMETERS),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PREPAYMENTPARAM),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_DEVELOPERS),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_GUARANTEECORP),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_AGENCIES),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_INSURANCECOMP),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_ASSESSMENTAGEN),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE_FLOOR),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_COLLLOANBACKPARA),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_BANKPALINDROME),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PALINDROMFORMAT),

      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_SUPPLEBORROWERINFO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_HOUSEINFO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_BORROWERINFOLIMITED),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPL_LOANAPPLMAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_SPECIALAPPL_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAUDIT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_LOANAPPROVAL),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_LOANCONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_PLEDGECONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_IMPAWNCONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_ASSURER),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_CONTRACTSIGN_CONTRACTMAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GATHERINGACC_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GIVEACC_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_GIVEACC_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_ISSUEDNOTICE_DO),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_ISSUEDNOTICE_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANAPPL_DELCONTRACT),
      new Integer(BusiLogConst.PL_OP_LOANRETURN_MAINTAIN),
      
      new Integer(BusiLogConst.PL_OP_LOANISSUED_DO),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_PRINTRESTORELOAN),
      
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_RECOVERQUIRY),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LIVING_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LIVING_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_DO),
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LOANERLOGOUT_MAINTAIN),
      
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
      new Integer(BusiLogConst.PL_OP_ACCOUNTMANAGE_CARRYFORWARD),

      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_CONTRACTMAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_SPECIALINFOCHG),

      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_FIVELEVAL_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO),
      new Integer(BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_MAINTAIN)
  };

  static final String[] values = { "����׼��-����ά��", "����׼��-����ά��" ,"����׼��-����ά��",
                                   "����׼��-��ǰ�������ά��","����׼��-������ά��","����׼��-������˾ά��",
                                   "����׼��-�������ά��","����׼��-���չ�˾ά��","����׼��-��������ά��",
                                   "����׼��-��֤��ά��","����׼��-������ά��-¥��","����׼��-�����𻹴���������","����׼��-���л��ĸ�ʽ����","����׼��-���л��Ķ�Ӧ��ʽ����",
                                   
                                   "�������-�������-�������Ϣ", "�������-�������-�����������Ϣ" ,"�������-�������-������Ϣ",
                                   "�������-�������-����˶����Ϣ","�������-�������-�������ά��","�������-��������-������������",
                                   "�������-��������-��������ά��","�������-��˴���","�������-��������",
                                   "�������-ǩ����ͬ-����ͬ��Ϣ","�������-ǩ����ͬ-��Ѻ��ͬ��Ϣ","�������-ǩ����ͬ-��Ѻ��ͬ��Ϣ",
                                   "�������-ǩ����ͬ-��֤����Ϣ","�������-ǩ����ͬ-ǩ����ͬά��","�������-�տ��˺��޸�-�տ��˺��޸�",
                                   "�������-�տ��˺��޸�-�տ��˺�ά��","�������-�����˺��޸�-�����˺��޸�","�������-�����˺��޸�-�����˺�ά��",
                                   "�������-�´﷢��֪ͨ��-�´﷢��֪ͨ��","�������-�´﷢��֪ͨ��-����֪ͨ��ά��","�������-ɾ�������ͬ","�������-�����𻹴�ǩ����ͬ",
                                   
                                   "���Ŵ���-������", "���Ŵ���-����ά��", "���Ŵ���-��ӡ����ƻ���" ,
                                   
                                   "���մ���-������ѯ", "���մ���-�������", "���մ���-����ά��",
                                   "���մ���-���д��۵���", "���մ���-���д��۵���", "���մ���-���˺���-������˺���",
                                   "���մ���-���˺���-���˺���ά��", "���մ���-�����ջ�-��������ջ�", "���մ���-�����ջ�-�����ջ�ά��",
                                   "���մ���-��Ѻ��Ѻ���-�����Ѻ��Ѻ���", "���մ���-��Ѻ��Ѻ���-��Ѻ��Ѻ���ά��","���մ���-���ע��-���ע������","���մ���-���ע��-���ע��ά��",
                                   
                                   "������-ҵ�񸴺�", "������-����-����", "������-����-���㵥��ѯ",
                                   "������-���˳���-������˵���", "������-���˳���-���˵���ά��", "������-����-�������",
                                   "������-����-����ά��", "������-���ڽ�ת", "������-�ս�",
                                   "������-��ĩ��ת","������-���ս�ת",
                                   
                                   "��ͬ���-������Ϣ���-�������Ϣ", "��ͬ���-������Ϣ���-�����������Ϣ", "��ͬ���-������Ϣ���-������Ϣ",
                                   "��ͬ���-������Ϣ���-������Ϣ���ά��", "��ͬ���-������Ѻ���-��Ѻ��ͬ��Ϣ", "��ͬ���-������Ѻ���-��Ѻ��ͬ��Ϣ",
                                   "��ͬ���-������Ѻ���-��֤����Ϣ", "��ͬ���-������Ѻ���-������Ѻ���ά��", "��ͬ���-������Ϣ���",
                                   
                                   "����ҵ����-�弶�����޸�-ҵ�����", "����ҵ����-�弶�����޸�-ҵ��ά��", "����ҵ����-��֤��Ǽ�-ҵ�����",
                                   "����ҵ����-��֤��Ǽ�-ҵ��ά��","����ҵ����-��֤���Ϣ-ҵ�����","����ҵ����-��֤���Ϣ-ҵ��ά��"
                                   
                                 };
  public OpModeLoan()
  {
    this.putValues(keys,values);
  }

}
