package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��������
 * @author ����
 * 2007-9-13
 */
public class PLHouseType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
                                  BusiConst.PLHOUSETYPE_HOUSING,
                                  BusiConst.PLHOUSETYPE_SECHOUSING };

  static final String[] values = { "��Ʒ��","���ַ�" };

  public PLHouseType() {
    this.putValues_Str(keys, values);
  }
}
