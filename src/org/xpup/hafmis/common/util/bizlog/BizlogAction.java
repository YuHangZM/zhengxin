package org.xpup.hafmis.common.util.bizlog;

/**
 * ҵ����־��Ĳ�����Ϊ
 * @author ����
 *2007-6-21 
 */
public class BizlogAction extends BusiLogProMap{
  
  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = { 
      new Integer(BusiLogConst.BIZLOG_ACTION_ADD),
      new Integer(BusiLogConst.BIZLOG_ACTION_MODIFY),
      new Integer(BusiLogConst.BIZLOG_ACTION_DELETE),
      new Integer(BusiLogConst.BIZLOG_ACTION_QUERY),
      new Integer(BusiLogConst.BIZLOG_ACTION_EXP),
      new Integer(BusiLogConst.BIZBLOG_ACTION_IMP),
      new Integer(BusiLogConst.BIZLOG_ACTION_OPENING),
      new Integer(BusiLogConst.BIZLOG_ACTION_REVOCATION),
      new Integer(BusiLogConst.BIZLOG_ACTION_DELETEALL),
      new Integer(BusiLogConst.BIZLOG_ACTION_CHECKS),
      new Integer(BusiLogConst.BIZLOG_ACTION_CONFIRM),
      new Integer(BusiLogConst.BIZLOG_ACTION_INTEREST),
      new Integer(BusiLogConst.BIZLOG_ACTION_ACCOUNTIN),
      new Integer(BusiLogConst.BIZLOG_ACTION_ENDCHG),
      new Integer(BusiLogConst.BIZLOG_ACTION_AUDITINGPASS),
      new Integer(BusiLogConst.BIZLOG_ACTION_AUDITING),
      new Integer(BusiLogConst.BIZLOG_ACTION_AUDITINGQUASH),
      new Integer(BusiLogConst.BIZLOG_ACTION_PPROVALPASS),
      new Integer(BusiLogConst.BIZLOG_ACTION_PPROVAL),
      new Integer(BusiLogConst.BIZLOG_ACTION_PPROVALQUASH),
      new Integer(BusiLogConst.BIZLOG_ACTION_CARRYFORWARD)
  };

  static final String[] values = { "���", "�޸�" ,"ɾ��","��ѯ","����","����","����","����","ȫ��ɾ��","����","ȷ��","��Ϣ","����","��ɵ���","���ͨ��","��˲�ͨ��","�������","����ͨ��","������ͨ��","��������","���ս�ת"};
  public BizlogAction()
  {
    this.putValues(keys,values);
  }

}
