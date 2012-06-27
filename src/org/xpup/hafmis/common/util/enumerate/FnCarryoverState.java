package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class FnCarryoverState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.CARRYOVERSTATE_NO),
      new Integer(BusiConst.CARRYOVERSTATE_YESNOKEEPING),
      new Integer(BusiConst.CARRYOVERSTATE_BOOKKEEPING)};

   static final String[] values = { "δ��ת", "�ѽ�תδ����","�Ѽ���" };
  public FnCarryoverState()
  {
    this.putValues(keys,values);
  }
}

