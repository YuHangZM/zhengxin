package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class DistoryPick extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
//      new Integer(BusiConst.DISTORY),
//      new Integer(BusiConst.END),
//      new Integer(BusiConst.USA)
    new Integer(BusiConst.BOWOUT),
    new Integer(BusiConst.DEATH),
    new Integer(BusiConst.DECRUITMENT),
    new Integer(BusiConst.JOBLESS),
    new Integer(BusiConst.DISTORY),
    new Integer(BusiConst.SETTLE)
   
      };
 // static final String[] values = { "����Ͷ���ͬ", "����","����Ǩ��" };
  static final String[] values = { "����", "����","��������","ʧҵ�¸�����","�Ǳ��л��ڽ����ͬ","��������" };
  public DistoryPick()
  {
    this.putValues(keys,values);
  }
}
