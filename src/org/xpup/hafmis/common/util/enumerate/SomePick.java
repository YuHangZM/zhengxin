package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

public class SomePick extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
//      new Integer(BusiConst.BUYHOUSE),
//      new Integer(BusiConst.GIVEMONEY),
//      new Integer(BusiConst.BUILD)
    new Integer(BusiConst.BUYHOUSE),
    new Integer(BusiConst.GIVEMONEYBYMON),
    new Integer(BusiConst.GIVEMONEYClEAR),
    new Integer(BusiConst.DISEASE),
    new Integer(BusiConst.DISTRESS),
    new Integer(BusiConst.PARTREST)
    
      };
  //static final String[] values = { "����", "����","����" };
  static final String[] values = { "����", "�������»���","������һ���Ի�����","�ش󼲲�","����","������ȡ����" };
  public SomePick()
  {
    this.putValues(keys,values);
  }
}
