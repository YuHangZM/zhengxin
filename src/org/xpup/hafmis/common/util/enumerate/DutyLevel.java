package org.xpup.hafmis.common.util.enumerate;
/**
 * ְ��
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class DutyLevel extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.DUTYLEVEL_NOT),
      new Integer(BusiConst.DUTYLEVEL_SENIOR),
      new Integer(BusiConst.DUTYLEVEL_INTERMEDIATE),
      new Integer(BusiConst.DUTYLEVEL_GENERAL),
      new Integer(BusiConst.DUTYLEVEL_UNKNOW)
      };

   static final String[] values = { "��", "�߼�", "�м�", "����","δ֪" };
  public DutyLevel()
  {
    this.putValues(keys,values);
  }
}
