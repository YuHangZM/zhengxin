package org.xpup.hafmis.common.util.enumerate;
import org.xpup.hafmis.common.util.BusiConst;
public class PickupReason extends AbsBusiProMap{
  private static final long serialVersionUID = 2003445450075369723L;
  static final Integer[] keys = {
    
    new Integer(BusiConst.PICKUPREASON_BUYHOUSE),
    new Integer(BusiConst.PICKUPREASON_GIVEMONEYBYMON),
    new Integer(BusiConst.PICKUPREASON_GIVEMONEYClEAR),
    new Integer(BusiConst.PICKUPREASON_DISEASE),
    new Integer(BusiConst.PICKUPREASON_DISTRESS),
    new Integer(BusiConst.PICKUPREASON_PARTREST),
    new Integer(BusiConst.PICKUPREASON_BOWOUT),
    new Integer(BusiConst.PICKUPREASON_DEATH),
    new Integer(BusiConst.PICKUPREASON_DECRUITMENT),
    new Integer(BusiConst.PICKUPREASON_JOBLESS),
    new Integer(BusiConst.PICKUPREASON_DISTORY),
    new Integer(BusiConst.PICKUPREASON_SETTLE)
      };
 // static final String[] values = { "����Ͷ���ͬ", "����","����Ǩ��" };
  static final String[] values = { "����", "�������»���","������һ���Ի�����","�ش󼲲�","����","������ȡ����","����", "����","��������","ʧҵ�¸�����","�Ǳ��л��ڽ����ͬ","��������"};
  public PickupReason()
  {
    this.putValues(keys,values);
  }
}
