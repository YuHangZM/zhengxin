package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��������
 * @author ����
 * 2007-9-13
 */
public class PLLoanProcess extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {   
    BusiConst.PLLOANPROCESS_LOANAPPROVAL,
    BusiConst.PLLOANPROCESS_CONTRACTSIGN
  };

 static final String[] values = { "��������","ǩ������" };
public PLLoanProcess()
{
  this.putValues_Str(keys,values);
}
}
