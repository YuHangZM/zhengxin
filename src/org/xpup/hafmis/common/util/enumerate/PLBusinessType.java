package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ����-ҵ������
 * 
 * @author ���� 2007-9-14
 */
public class PLBusinessType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.PLBUSINESSTYPE_ISSUED),
      new Integer(BusiConst.PLBUSINESSTYPE_SINGLERECOVER),
      new Integer(BusiConst.PLBUSINESSTYPE_PARTRECOVER),
      new Integer(BusiConst.PLBUSINESSTYPE_ALLCLEAR),
      new Integer(BusiConst.PLBUSINESSTYPE_SOMERECOVER),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER),
      new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER),
      new Integer(BusiConst.PLBUSINESSTYPE_CARRYOVERDUE),
      new Integer(BusiConst.PLBUSINESSTYPE_CARRYPAY),
      new Integer(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG),
      new Integer(BusiConst.PLBUSINESSTYPE_OVERPAY),
      new Integer(BusiConst.PLBUSINESSTYPE_MARGIN),
      new Integer(BusiConst.PLBUSINESSTYPE_CLEARINTEREST),
      new Integer(BusiConst.PLBUSINESSTYPE_INITDATA)
  };

  static final String[] values = { "����", "���ʻ���", "������ǰ����", "һ���Ի���", "��������",
      "���˺��������ģ�", "���˺�����������", "�����ջأ����ģ�", "�����ջأ�������", "��ת����", "��ת���", "���˵���",
      "����", "��֤��", "��Ϣ", "��ʼ������"};

  public PLBusinessType() {
    this.putValues(keys, values);
  }
}
