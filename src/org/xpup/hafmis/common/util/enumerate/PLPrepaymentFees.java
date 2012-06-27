package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class PLPrepaymentFees extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLPREPAYMENTFEES_PREPAYMENT),
                                  new Integer(BusiConst.PLPREPAYMENTFEES_PAYMENT),
                                  new Integer(BusiConst.PLPREPAYMENTFEES_NO)
                                };

  static final String[] values = { "�ǣ�����ǰ�����","�ǣ�����","��"
                                 };

  public PLPrepaymentFees() {
    this.putValues(keys, values);
  }
}

