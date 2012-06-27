package org.xpup.hafmis.common.util.bizlog;

/**
 * ����ģ��-����-���Ŵ���
 * 
 * @author ���� 2007-9-13
 */
public class PlOpLoanIssued extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_LOANISSUED_DO),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_LOANISSUED_PRINTRESTORELOAN) };

  static final String[] values = { "���Ŵ���-������", "���Ŵ���-����ά��", "���Ŵ���-��ӡ����ƻ���" };

  public PlOpLoanIssued() {
    this.putValues(keys, values);
  }

}
