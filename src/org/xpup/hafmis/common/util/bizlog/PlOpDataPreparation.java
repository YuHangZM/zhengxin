package org.xpup.hafmis.common.util.bizlog;

/**
 * ����ģ��-����-����׼��
 * @author ����
 * 2007-9-13
 */
public class PlOpDataPreparation extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_RATE),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_BANK),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PARAMETERS),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PREPAYMENTPARAM),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_DEVELOPERS),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_GUARANTEECORP),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_AGENCIES),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_INSURANCECOMP),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_ASSESSMENTAGEN),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_NOTARIZATIONOFFICE),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_COLLLOANBACKPARA),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_BANKPALINDROME),
      new Integer(BusiLogConst.PL_OP_DATAPREPARATION_PALINDROMFORMAT)
  };

  static final String[] values = { "����׼��-����ά��", "����׼��-����ά��" ,"����׼��-����ά��",
                                   "����׼��-��ǰ�������ά��","����׼��-������ά��","����׼��-������˾ά��",
                                   "����׼��-�������ά��","����׼��-���չ�˾ά��","����׼��-��������ά��",
                                   "����׼��-��֤��ά��","����׼��-������ά��-¥��","����׼��-�����𻹴���������","����׼��-���л��ĸ�ʽ����","����׼��-���л��Ķ�Ӧ��ʽ����"};
  public PlOpDataPreparation()
  {
    this.putValues(keys,values);
  }

}
