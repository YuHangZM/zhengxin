package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��ǰ�����Ի��������ĸ�������
 * @author ����
 * 2007-9-14
 */
public class PLRecoverParasChg extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLRECOVERPARASCHG_SAMEPAY),
                                  new Integer(BusiConst.PLRECOVERPARASCHG_SAMEMONTHS),
                                  new Integer(BusiConst.PLRECOVERPARASCHG_CHGMONTHS)
                                };

  static final String[] values = { "����ԭ���»����","����ʣ������","����ı�ʣ������"
                                 };

  public PLRecoverParasChg() {
    this.putValues(keys, values);
  }
}


