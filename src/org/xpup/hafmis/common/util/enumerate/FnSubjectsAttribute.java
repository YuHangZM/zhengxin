package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��Ŀ����
 * @author ����
 * 2007-10-6
 */
public class FnSubjectsAttribute extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.SUBATTRIBUTE_CASH),
      new Integer(BusiConst.SUBATTRIBUTE_BANK),
      new Integer(BusiConst.SUBATTRIBUTE_OTHERS)};

   static final String[] values = { "�ֽ�", "����","����" };
  public FnSubjectsAttribute()
  {
    this.putValues(keys,values);
  }
}
