package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-���������Ϣ
 * @author ����
 * 2007-9-13
 */
public class PLAllMess extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {   
    BusiConst.PLALLMESS_CORPUS,
    BusiConst.PLALLMESS_INTEREST,
    BusiConst.PLALLMESS_OVERDUECORPUS,
    BusiConst.PLALLMESS_OVERDUEINTEREST,
    BusiConst.PLALLMESS_PUNISHINTEREST
  };

 static final String[] values = { "��������","������Ϣ","���ڱ���","������Ϣ","��Ϣ"};
public PLAllMess()
{
  this.putValues_Str(keys,values);
}
}

