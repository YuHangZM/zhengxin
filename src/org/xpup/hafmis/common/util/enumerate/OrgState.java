package org.xpup.hafmis.common.util.enumerate;
/**
 * ��λ״̬
 * @author ����
 *2007-6-21
 */
import org.xpup.hafmis.common.util.BusiConst;

public class OrgState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { new Integer(BusiConst.ORGSTATE_OPENING),
      new Integer(BusiConst.ORGSTATE_NORMAL),
      new Integer(BusiConst.ORGSTATE_SEAL),
      new Integer(BusiConst.ORGSTATE_CANCEL)};

   static final String[] values = { "������", "����","���","ע��" };
  public OrgState()
  {
    this.putValues(keys,values);
  }
}
