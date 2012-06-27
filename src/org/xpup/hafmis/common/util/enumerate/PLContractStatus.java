package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ����-��ͬ״̬
 * 
 * @author ���� 2007-9-13
 */
public class PLContractStatus extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.PLCONTRACTSTATUS_APPL),
      new Integer(BusiConst.PLCONTRACTSTATUS_THROUGHAUDIT),
      new Integer(BusiConst.PLCONTRACTSTATUS_THROUGHAPPROVAL),
      new Integer(BusiConst.PLCONTRACTSTATUS_NOTHROUGHAUDIT),
      new Integer(BusiConst.PLCONTRACTSTATUS_NOTHROUGHAPPROVAL),
      new Integer(BusiConst.PLCONTRACTSTATUS_SECAUDIT),
      new Integer(BusiConst.PLCONTRACTSTATUS_SECAPPROVAL),
      new Integer(BusiConst.PLCONTRACTSTATUS_ISSUEDNOTICES),
      new Integer(BusiConst.PLCONTRACTSTATUS_ISSUING),
      new Integer(BusiConst.PLCONTRACTSTATUS_RECOVING),
      new Integer(BusiConst.PLCONTRACTSTATUS_RECOVERCLEAR),
      new Integer(BusiConst.PLCONTRACTSTATUS_CANCELLATION),
      new Integer(BusiConst.PLCONTRACTSTATUS_FINALJUDGMENT),
      new Integer(BusiConst.PLCONTRACTSTATUS_FIRSTCHECK),
      new Integer(BusiConst.PLCONTRACTSTATUS_REDATESURE),
      new Integer(BusiConst.PLCONTRACTSTATUS_CHKAGAIN),
      new Integer(BusiConst.PLCONTRACTSTATUS_APPROVALAGAIN),
      new Integer(BusiConst.PLCONTRACTSTATUS_CHKAGAIN_NOTPASS),
      new Integer(BusiConst.PLCONTRACTSTATUS_APPROVALAGAIN_NOTPASS)};

  static final String[] values = { "����", "���ͨ��", "����ͨ��", "���δͨ��",
      "����δͨ��", "�ٴ����", "�ٴ�����", "��ӡ���", "�ѷ���", "������", "�ѻ���", "ע��", "����ͨ��",
      "�ύ���", "�ؼ�ȷ��", "�������ͨ��", "��������ͨ��", "�������δͨ��", "��������δͨ��" };

  public PLContractStatus() {
    this.putValues(keys, values);
  }
}
