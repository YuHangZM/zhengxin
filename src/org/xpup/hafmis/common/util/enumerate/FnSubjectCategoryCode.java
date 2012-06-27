package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-��Ŀ������
 * @author ����
 * 2007-10-6
 */
public class FnSubjectCategoryCode extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.SUBCATEGORYCODE_ASSETS),
      new Integer(BusiConst.SUBCATEGORYCODE_LIABILITIES),
      new Integer(BusiConst.SUBCATEGORYCODE_INTERESTS),
      new Integer(BusiConst.SUBCATEGORYCODE_COST),
      new Integer(BusiConst.SUBCATEGORYCODE_PROFIT_LOSS)};

   static final String[] values = { "�ʲ�", "��ծ","Ȩ��","�ɱ�","����" };
  public FnSubjectCategoryCode()
  {
    this.putValues(keys,values);
  }
}
