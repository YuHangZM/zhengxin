package org.xpup.hafmis.common.util.enumerate;
/**
 * ��λ������ҵ
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class Industry extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.INDUSTRY_A,
      BusiConst.INDUSTRY_B,
      BusiConst.INDUSTRY_C,
      BusiConst.INDUSTRY_D,
      BusiConst.INDUSTRY_E,
      BusiConst.INDUSTRY_F,
      BusiConst.INDUSTRY_G,
      BusiConst.INDUSTRY_H,
      BusiConst.INDUSTRY_I,
      BusiConst.INDUSTRY_J,
      BusiConst.INDUSTRY_K,
      BusiConst.INDUSTRY_L,
      BusiConst.INDUSTRY_M,
      BusiConst.INDUSTRY_N,
      BusiConst.INDUSTRY_O,
      BusiConst.INDUSTRY_P,
      BusiConst.INDUSTRY_Q,
      BusiConst.INDUSTRY_R,
      BusiConst.INDUSTRY_S,
      BusiConst.INDUSTRY_T,
      BusiConst.INDUSTRY_Z
      };

   static final String[] values = { "ũ���֡�������ҵ", "�ɾ�ҵ", "����ҵ","������ȼ����ˮ�������͹�Ӧҵ","����ҵ","��ͨ���䡢�ִ�������ҵ","��Ϣ���䡢�������������ҵ","����������ҵ","ס�޺Ͳ���ҵ","����ҵ","���ز�ҵ","���޺��������ҵ","��ѧ�о�����������ҵ�͵��ʿ���ҵ","ˮ���������͹�����ʩ����ҵ","����������������ҵ","����","��������ᱣ�Ϻ���ḣ��ҵ","�Ļ�������������ҵ","��������������֯","������֯","δ֪" };
  public Industry()
  {
    this.putValues_Str(keys,values);
  }
}
