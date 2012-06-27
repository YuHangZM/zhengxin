package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-������
 * @author ����
 *2007-6-20 
 */
public class OpModeAccountManage extends BusiLogProMap {

  private static final long serialVersionUID = 4328243096078030108L;

  static final Integer[] keys = {
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

  static final String[] values = { "������-ҵ�񸴺�", "������-���˵���-������˵���","������-���˵���-���˵���ά��","������-����-����","������-����-���㵥��ѯ","������-�ս�-����","������-�ս�-���㵥��ѯ","������-�½�-����","������-�½�-���㵥��ѯ","������-���ս�Ϣ","������-ά������","�����������ݵ���-�������Ĳ�������","�����������ݵ���-��������ͷ����","�����������ݵ���-��������"};

  public OpModeAccountManage() {
    this.putValues(keys, values);
  }

}
