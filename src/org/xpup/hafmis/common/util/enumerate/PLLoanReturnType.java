package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-���������
 * @author ����
 * 2007-9-13
 */
public class PLLoanReturnType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = {   
    new Integer(BusiConst.PLLOANRETURNTYPE_CENTER),
    new Integer(BusiConst.PLLOANRETURNTYPE_BANK)
  };

 static final String[] values = { "������Ϊ��","������Ϊ��" };
public PLLoanReturnType()
{
  this.putValues(keys,values);
}
}