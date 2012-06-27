package org.xpup.hafmis.common.util.enumerate;
/**
 * ������ʽ
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class GuaranteeType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.GUARANTEETYPE_IMPAWN),
      new Integer(BusiConst.GUARANTEETYPE_MORTAGAGE),
      new Integer(BusiConst.GUARANTEETYPE_NATURALPERSONHYPOTHECATE),
      new Integer(BusiConst.GUARANTEETYPE_CREDIT),
      new Integer(BusiConst.GUARANTEETYPE_COMBINATIONINNATURAL),
      new Integer(BusiConst.GUARANTEETYPE_COMBINATIONOUTNATURAL),
      new Integer(BusiConst.GUARANTEETYPE_JOINTGUARANTY),
      new Integer(BusiConst.GUARANTEETYPE_OTHERS)
      };

   static final String[] values = { "��Ѻ������֤��", "��Ѻ","��Ȼ�˵���","����/�ⵣ��","��ϣ�����Ȼ�˱�֤��","��ϣ�������Ȼ�˱�֤��","ũ������","����" };
  public GuaranteeType()
  {
    this.putValues(keys,values);
  }
}
