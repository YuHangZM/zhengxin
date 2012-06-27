package org.xpup.hafmis.common.util.bizlog;

/**
 * ����ģ��-����-���մ���
 * 
 * @author ���� 2007-9-13
 */
public class PlOpLoanRecover extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
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
      new Integer(BusiLogConst.PL_OP_LOANRECOVER_LIVING_MAINTAIN)
      };

  static final String[] values = { "���մ���-������ѯ", "���մ���-�������", "���մ���-����ά��",
                                   "���մ���-���д��۵���", "���մ���-���д��۵���", "���մ���-���˺���-������˺���",
                                   "���մ���-���˺���-���˺���ά��", "���մ���-�����ջ�-��������ջ�", "���մ���-�����ջ�-�����ջ�ά��",
                                   "���մ���-��Ѻ��Ѻ���-�����Ѻ��Ѻ���", "���մ���-��Ѻ��Ѻ���-��Ѻ��Ѻ���ά��","���մ���-���ע��-���ע������","���մ���-���ע��-���ע��ά��"};

  public PlOpLoanRecover() {
    this.putValues(keys, values);
  }

}
