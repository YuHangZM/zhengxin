package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ��λ����
 * @author ����
 *2007-6-29
 */
public class NatureOfUnits extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
     new Integer(BusiConst.NATUREOFUNITS_1),
     new Integer(BusiConst.NATUREOFUNITS_2),
     new Integer(BusiConst.NATUREOFUNITS_3),
     new Integer(BusiConst.NATUREOFUNITS_4),
     new Integer(BusiConst.NATUREOFUNITS_5),
     new Integer(BusiConst.NATUREOFUNITS_6),
     new Integer(BusiConst.NATUREOFUNITS_7),
     new Integer(BusiConst.NATUREOFUNITS_8),
     new Integer(BusiConst.NATUREOFUNITS_9),
     new Integer(BusiConst.NATUREOFUNITS_10),
     new Integer(BusiConst.NATUREOFUNITS_11),
     new Integer(BusiConst.NATUREOFUNITS_12),
     new Integer(BusiConst.NATUREOFUNITS_13),
     new Integer(BusiConst.NATUREOFUNITS_14),
     new Integer(BusiConst.NATUREOFUNITS_15),
     new Integer(BusiConst.NATUREOFUNITS_16),
     new Integer(BusiConst.NATUREOFUNITS_17),
     new Integer(BusiConst.NATUREOFUNITS_18),
     new Integer(BusiConst.NATUREOFUNITS_19)
     };

   static final String[] values = { "���������ҵ","ʡ������ҵ","���ػ�����ҵ","������ҵ","ʡ��ȫ����ҵ","ʡ��������ҵ","�м�ȫ����ҵ","���ؼ�������ҵ",
     "��ʡ�е�λ","����","ȫ��������ҵ", "���������ҵ", "������֧������ҵ", "������ҵ", "������ҵ","�ɷ���","˽����Ӫ��ҵ","������ҵ", "����"};
  public NatureOfUnits()
  {
    this.putValues(keys,values);
  }
}