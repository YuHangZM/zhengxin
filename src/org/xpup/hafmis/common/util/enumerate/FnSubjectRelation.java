package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-�����ϵ����
 * @author ����
 * 2007-10-6
 */
public class FnSubjectRelation extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.SUBRELATION_OFFICE),
      new Integer(BusiConst.SUBRELATION_BANK),
      new Integer(BusiConst.SUBRELATION_ORG),
      new Integer(BusiConst.SUBRELATION_OTHERS)};

   static final String[] values = { "���´�", "����","��λ","����" };
  public FnSubjectRelation()
  {
    this.putValues(keys,values);
  }
}
