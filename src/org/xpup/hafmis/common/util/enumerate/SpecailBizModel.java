package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����ҵ��ƾ֤ģʽ
 * @author ����
 */
public class SpecailBizModel extends AbsBusiProMap{
  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.SPECAILBIZMODEL_OFFICE,
      BusiConst.SPECAILBIZMODEL_BANK,
      BusiConst.SPECAILBIZMODEL_ORG};

   static final String[] values = { "���´�", "����","��λ"};
  public SpecailBizModel()
  {
    this.putValues_Str(keys,values);
  }
}
