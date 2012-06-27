package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ����-�������
 * 
 * @author ���� 2007-10-6
 */
public class FnMoneyType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = {
      new Integer(BusiConst.FNMONEYTYPE_COLL_PAYMENT),
      new Integer(BusiConst.FNMONEYTYPE_COLL_PICK),
      new Integer(BusiConst.FNMONEYTYPE_COLL_CLEARACCOUNTINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_COLL_TRANSOUT),
      new Integer(BusiConst.FNMONEYTYPE_COLL_TTRANSIN),

      new Integer(BusiConst.FNMONEYTYPE_COLL_TRANSOUTINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_COLL_CLEARINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_COLL_OVERPAYMENT),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_ACCORD),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_RECEVERNORMALPRINCIPAL),

      new Integer(BusiConst.FNMONEYTYPE_LOAN_RECEVEROVERDUEPRINCIPAL),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_RECEVERINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_CLEARRECOVERMONEY),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_OVERPAYMENT),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_MARGIN),

      new Integer(BusiConst.FNMONEYTYPE_LOAN_MARGININTEREST),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_REALRECEVER),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_PUNISHINTEREST),
      new Integer(BusiConst.FNMONEYTYPE_LOAN_PICKMONEY) };

  static final String[] values = { "������_�ɴ���", "������_��ȡ���", "������_������Ϣ",
      "������_ת�����", "������_ת����", "������_ת����Ϣ", "������_��Ϣ��Ϣ", "������_���˽��", "����_���Ž��",
      "����_������������", "����_�������ڱ���", "����_������Ϣ", "����_�����ջؽ��", "����_���˽��", "����_��֤���",
      "����_��֤����Ϣ", "����_ʵ�ս��", "����_���շ�Ϣ", "��ȡ���" };

  public FnMoneyType() {
    this.putValues(keys, values);
  }
}
