package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-������λ
 * @author ����
 * 2007-9-14
 */
public class PLCancelORG extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLCANCELORG_CENTRE),
                                  new Integer(BusiConst.PLCANCELORG_GUARANTEECORP),
                                  new Integer(BusiConst.PLCANCELORG_OTHERS)
                                };

  static final String[] values = { "����","������˾","����"
                                 };

  public PLCancelORG() {
    this.putValues(keys, values);
  }
}


