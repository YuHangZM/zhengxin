package org.xpup.hafmis.common.util.bizlog;

/**
 * 
 * ����ҵ��ϵͳ����
 * @author ����
 *2007-6-2
 */
public class OpSystemType extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { new Integer(BusiLogConst.OP_SYSTEM_TYPE_MANAGER),new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION),
      new Integer(BusiLogConst.OP_SYSTEM_TYPE_LOAN),new Integer(BusiLogConst.OP_SYSTEM_TYPE_FINANCE)};

  static final String[] values = {"ס��������ϵͳ������ϵͳ","ס��������鼯��ϵͳ", "ס�������������ϵͳ" ,"ס�������������ϵͳ"};
  public OpSystemType()
  {
    this.putValues(keys,values);
  }

}
