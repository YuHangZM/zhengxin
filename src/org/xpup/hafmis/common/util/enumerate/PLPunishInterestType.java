package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��Ϣ��������
 * @author ����
 * 2007-9-13
 */
public class PLPunishInterestType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE),
                                  new Integer(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE),
                                  new Integer(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE)
                                };

  static final String[] values = { "����Ϣ������", "����ͬ������","����ÿ��" };

  public PLPunishInterestType() {
    this.putValues(keys, values);
  }
}
