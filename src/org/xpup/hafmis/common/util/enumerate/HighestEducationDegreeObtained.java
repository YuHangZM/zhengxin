package org.xpup.hafmis.common.util.enumerate;
/**
 * ���ѧλ
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class HighestEducationDegreeObtained extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_OTHERS),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_HONORARYDOCTOR),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_DOCTOR),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_MASTER),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_BACHELOR),
      new Integer(BusiConst.HIGHESTEDUCATIONALDEGREEOBTAINED_UNKNOW)
      };

   static final String[] values = { "����", "������ʿ", "��ʿ", "˶ʿ","ѧʿ","δ֪"};
  public HighestEducationDegreeObtained()
  {
    this.putValues(keys,values);
  }
}
