package org.xpup.hafmis.common.util.enumerate;
/**
 * �弶����״̬
 * @author ����
 *2007-6-22
 */
import org.xpup.hafmis.common.util.BusiConst;

public class FiveCategoryAssetsClassification extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_NORMAL),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_ATTENTION),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_SECONDARY),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_SHADINESS),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_DAMNIFY),
      new Integer(BusiConst.FIVECATEGORYASSETSCLASSIFICATION_UNKOWN)
      };

   static final String[] values = { "����", "��ע","�μ�","����","��ʧ","δ֪","ũ������","����" };
  public FiveCategoryAssetsClassification()
  {
    this.putValues(keys,values);
  }
}
