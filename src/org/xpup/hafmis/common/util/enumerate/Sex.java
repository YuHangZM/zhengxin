
package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;


/**
 * �Ա�
 * @author ����
 *2007-6-2
 */
 public class Sex extends AbsBusiProMap {

   static final long serialVersionUID = -6831497426265030966L;

   static final Integer[] keys = { 
      new Integer(BusiConst.SEX_UNKNOW),
			new Integer(BusiConst.SEX_MALE),
      new Integer(BusiConst.SEX_FEMALE),
      new Integer(BusiConst.SEX_ILLUSTRATED)
      };

	 static final String[] values = { "δ֪���Ա�", "��", "Ů", "δ֪˵���Ա�" };
	public Sex()
	{
		this.putValues(keys,values);
	}
}
