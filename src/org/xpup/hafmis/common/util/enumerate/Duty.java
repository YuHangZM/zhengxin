package org.xpup.hafmis.common.util.enumerate;
/**
 * ְ��
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class Duty extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.DUTY_SENIORLEADER),
      new Integer(BusiConst.DUTY_INTERMEDIATELEADER),
      new Integer(BusiConst.DUTY_GENERALSTAFF),
      new Integer(BusiConst.DUTY_OTHERS),
      new Integer(BusiConst.DUTY_UNKNOW)
      };

   static final String[] values = { "�߼��쵼", "�м��쵼", "һ��Ա��", "����","δ֪" };
  public Duty()
  {
    this.putValues(keys,values);
  }
}
