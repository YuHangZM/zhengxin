package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;

/**
 * ����״̬
 * @author ���Ʒ�
 *
 */
public class AgentStatus extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.AGENTSTATUS_NO,
      BusiConst.AGENTSTATUS_YES};

   static final String[] values = { "δʹ��", "��ʹ��"};
  public AgentStatus()
  {
    this.putValues_Str(keys,values);
  }
}
