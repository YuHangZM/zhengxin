package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-�ص�����
 * @author ��˶
 * 20090409
 */
public class PLLoanWhereType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
                                  BusiConst.PL_LOANTYPE_LOCAL,
                                  BusiConst.PL_LOANTYPE_OTHERS };

  static final String[] values = { "����","���" };

  public PLLoanWhereType() {
    this.putValues_Str(keys, values);
  }
}
