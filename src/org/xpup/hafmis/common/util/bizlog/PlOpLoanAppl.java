package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-�������
 * @author ����
 * 2007-9-13
 */
public class PlOpLoanAppl extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
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
      new Integer(BusiLogConst.PL_OP_LOANRETURN_MAINTAIN)
  };

  static final String[] values = { "�������-�������-�������Ϣ", "�������-�������-�����������Ϣ" ,"�������-�������-������Ϣ",
                                   "�������-�������-����˶����Ϣ","�������-�������-�������ά��","�������-��������-������������",
                                   "�������-��������-��������ά��","�������-��˴���","�������-��������",
                                   "�������-ǩ����ͬ-����ͬ��Ϣ","�������-ǩ����ͬ-��Ѻ��ͬ��Ϣ","�������-ǩ����ͬ-��Ѻ��ͬ��Ϣ",
                                   "�������-ǩ����ͬ-��֤����Ϣ","�������-ǩ����ͬ-ǩ����ͬά��","�������-�տ��˺��޸�-�տ��˺��޸�",
                                   "�������-�տ��˺��޸�-�տ��˺�ά��","�������-�����˺��޸�-�����˺��޸�","�������-�����˺��޸�-�����˺�ά��",
                                   "�������-�´﷢��֪ͨ��-�´﷢��֪ͨ��","�������-�´﷢��֪ͨ��-����֪ͨ��ά��","�������-ɾ�������ͬ","�������-�����𻹴�ǩ����ͬ"};
  public PlOpLoanAppl()
  {
    this.putValues(keys,values);
  }

}
