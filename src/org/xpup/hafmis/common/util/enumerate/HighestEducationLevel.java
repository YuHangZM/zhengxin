package org.xpup.hafmis.common.util.enumerate;
/**
 * ���ѧ��
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class HighestEducationLevel extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_GRADUATE),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_UNDERGRADUATE),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_SPECIALIST),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_SECONDARY),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_TECHNICAL),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_HIGHSCHOOL),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_JUNIOR),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_PRIMARY),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_ILLITERACY),
      new Integer(BusiConst.HIGHESTEDUCATIONLEVEL_UNKNOW)
      };

   static final String[] values = { "�о���", "��ѧ����", "��ѧר�ƺ�ר��ѧУ", "�е�רҵѧУ���еȼ���ѧУ","����ѧУ","����","����","Сѧ","��ä�����ä","δ֪"};
  public HighestEducationLevel()
  {
    this.putValues(keys,values);
  }
}
