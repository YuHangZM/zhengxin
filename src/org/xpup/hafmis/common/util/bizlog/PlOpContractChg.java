package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-��ͬ���
 * @author ����
 * 2007-9-13
 */
public class PlOpContractChg extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_BORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_SUPPLEBORROWERINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_HOUSEINFO),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_BASEMESSINFOCHG_MAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_PLEDGCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_IMPAWNCONTRACT),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_ASSURER),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_GUARANTEECHG_CONTRACTMAINTAIN),
      new Integer(BusiLogConst.PL_OP_CONTRACTCHG_SPECIALINFOCHG)
      };

  static final String[] values = { "��ͬ���-������Ϣ���-�������Ϣ", "��ͬ���-������Ϣ���-�����������Ϣ", "��ͬ���-������Ϣ���-������Ϣ",
                                   "��ͬ���-������Ϣ���-������Ϣ���ά��", "��ͬ���-������Ѻ���-��Ѻ��ͬ��Ϣ", "��ͬ���-������Ѻ���-��Ѻ��ͬ��Ϣ",
                                   "��ͬ���-������Ѻ���-��֤����Ϣ", "��ͬ���-������Ѻ���-������Ѻ���ά��", "��ͬ���-������Ϣ���"
                                 };

  public PlOpContractChg() {
    this.putValues(keys, values);
  }

}