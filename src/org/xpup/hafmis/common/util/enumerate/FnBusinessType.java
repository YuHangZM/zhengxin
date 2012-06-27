package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ����-ҵ������
 * 
 * @author ���� 2007-10-6
 */
public class FnBusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = {
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_PAYMENT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_SOMEPICK),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_ALLPICK),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OUTTRANSOUT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OUTTRANSIN),

      new Integer(BusiConst.FNBUSINESSTYPE_COLL_INTRANSOUT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_INTRANSIN),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_INTRANS),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OVERPAYMENT),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_OVERPICKPAYMENT),

      new Integer(BusiConst.FNBUSINESSTYPE_COLL_CLEARINTEREST),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_ERRACCOUNTUP),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_ERRACCOUNTDOWN),
      new Integer(BusiConst.FNBUSINESSTYPE_COLL_GJJBACK),
      new Integer(BusiConst.FNBUSINESSTYPE_LOAN_ACCORD),
      new Integer(BusiConst.FNBUSINESSTYPE_LOAN_CALLBACK), };

  static final String[] values = { "������_�ɴ�", "��ȡ����", "������_��ȡ", "������_�ⲿת��", "������_�ⲿת��",
      "������_�ڲ�ת��", "������_�ڲ�ת��", "������_�ڲ�ת��ת��", "������_���ˣ��գ�", "������_���ˣ���ȡ��",
      "������_��Ϣ", "������_���˵���", "������_���˵���", "������_����", "����_����", "����_����" };

  public FnBusinessType() {
    this.putValues(keys, values);
  }
}
