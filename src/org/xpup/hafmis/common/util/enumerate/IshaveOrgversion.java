package org.xpup.hafmis.common.util.enumerate;
/**
 * �Ƿ���ڵ�λ��
 * @author ����
 */
import org.xpup.hafmis.common.util.BusiConst;

public class IshaveOrgversion extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.IS_NOHAVE,
      BusiConst.IS_HAVE};

   static final String[] values = {"������", "����"};
  public IshaveOrgversion()
  {
    this.putValues_Str(keys,values);
  }
}
