package org.xpup.hafmis.common.util.bizlog;

/**
 * ����ģ��-�ɴ����
 * @author ����
 *2007-6-20 
 */
public class OpModePaymentManage extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT)
      };

  static final String[] values = { "�ɴ����-�������-����ɴ�", "�ɴ����-�������-�ɴ�ά��","�ɴ����-��λ����-����ɴ�","�ɴ����-��λ����-�ɴ�ά��","�ɴ����-���˲���-����ɴ�","�ɴ����-���˲���-�ɴ�ά��","�ɴ����-��λ����-�������","�ɴ����-��λ����-����ά��","�ɴ����-�ɴ浽��ȷ��","�ɴ����-��������"};

  public OpModePaymentManage() {
    this.putValues(keys, values);
  }

}
