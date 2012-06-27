package org.xpup.hafmis.common.util.bizlog;

public class OpModeColl extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
    
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGOPEN_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_OPEN_EMPOPEN),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_DO),
      new Integer(BusiLogConst.OP_MODE_OPEN_ORGCHG_MAINTAIN),
    
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGRATE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGSALARYBASE_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_DO),
      new Integer(BusiLogConst.OP_MODE_CHANGE_CHGPERSON_MAINTAIN),
    
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_ADDPERSONPAY_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_DO),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_EXCESSPAYMENT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENTCONFIRM),
      new Integer(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_AGENT),
    
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_DRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_DO),
      new Integer(BusiLogConst.OP_MODE_DRAWING_SPECIALDRAWING_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_DRAWING_COLLLOANBACK),
    
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_DO),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANOUT_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_PREPARE),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_CHECKIN),
      new Integer(BusiLogConst.OP_MODE_TRANINOUT_TRANIN_MAINTAIN),
    
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_OPERATIONCHECK),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_ACCOUNTING_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_ACCOUNTING_INQUIRIES),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_DAYCLEANING_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_DAYCLEANING_INQUIRIES),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_DO),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_MONTHCLEANING_INQUIRIES),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_INTEREST),
      new Integer(BusiLogConst.OP_MODE_ACCOUNTMANAGE_MAINTAINRATE),
      new Integer(BusiLogConst.OP_MODE_BANKDATAEXP_RECORDPARAMSETTING),
      new Integer(BusiLogConst.OP_MODE_BANKDATAEXP_RECORDHEADSETTING),
      new Integer(BusiLogConst.OP_MODE_BANKDATAEXP_RECORD)
      
      };

  static final String[] values = { "��λ����-������", "��λ����-����ά��","ְ������",
                                   "��λ���-������","��λ���-���ά��",
                                   
                                   "���ҵ��-��ɱ�������-������", "���ҵ��-��ɱ�������-���ά��","���ҵ��-���ʻ�������-������",
                                   "���ҵ��-���ʻ�������-���ά��","���ҵ��-�ɶ����-������","���ҵ��-�ɶ����-���ά��",
                                   "���ҵ��-��Ա���-������","���ҵ��-��Ա���-���ά��" ,
                                   
                                   "�ɴ����-�������-����ɴ�","�ɴ����-�������-�ɴ�ά��", "�ɴ����-��λ����-����ɴ�","�ɴ����-��λ����-�ɴ�ά��",
                                   "�ɴ����-���˲���-����ɴ�","�ɴ����-���˲���-�ɴ�ά��","�ɴ����-��λ����-�������",
                                   "�ɴ����-��λ����-����ά��","�ɴ����-�ɴ浽��ȷ��" ,"�ɴ����-��������",
                                   
                                   "��ȡ����-������ȡ-������ȡ", "��ȡ����-������ȡ-��ȡά��","��ȡ����-����������ȡ-������ȡ",
                                   "��ȡ����-����������ȡ-��ȡά��","�����𻹴�",
                                   
                                   "ת��ת��-����ת��-����ת��", "ת��ת��-����ת��-ת��ά��","ת��ת��-����ת��-��ת��Ǽ�",
                                   "ת��ת��-����ת��-ת��Ǽ�","ת��ת��-����ת��-ת��ά��",
                                   
                                   "������-ҵ�񸴺�", "������-���˵���-������˵���","������-���˵���-���˵���ά��",
                                   "������-����-����","������-����-���㵥��ѯ","������-�ս�-����",
                                   "������-�ս�-���㵥��ѯ","������-�½�-����" ,"������-�½�-���㵥��ѯ",
                                   "������-���ս�Ϣ", "������-ά������","�����������ݵ���-�������Ĳ�������",
                                   "�����������ݵ���-��������ͷ����","�����������ݵ���-��������"
                                   
                                 };

  public OpModeColl() {
    this.putValues(keys, values);
  }

}
