package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-����״̬-����or����
 * @author ����
 * 2007-9-13
 */
public class PLCommonStatus_FIR extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
                                  new Integer(BusiConst.PLCOMMONSTATUS_1_NORMAL),
                                  new Integer(BusiConst.PLCOMMONSTATUS_1_CANCELED)
                                //  new Integer(BusiConst.PLCOMMONSTATUS_1_RELIEVE)
                                  };

  static final String[] values = { "����", "����" };//,"���"

  public PLCommonStatus_FIR() {
    this.putValues(keys, values);
  }
}
