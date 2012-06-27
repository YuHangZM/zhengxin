package org.xpup.hafmis.common.util.enumerate;
/**
 * ֤������
 * @author ����
 *2007-6-21
 */
import org.xpup.hafmis.common.util.BusiConst;

public class DocumentsState extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.DOCUMENTSSTATE_IDCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_HOUSEHOLDREGISTER),
      new Integer(BusiConst.DOCUMENTSSTATE_PASSPORTCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_OFFICERCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_SOLDIERCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_HKANDMACAO),
      new Integer(BusiConst.DOCUMENTSSTATE_TAIWAN),
      new Integer(BusiConst.DOCUMENTSSTATE_TEMPCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_FOREIGNERRESIDENTIALCARD),
      new Integer(BusiConst.DOCUMENTSSTATE_POLICECARD)
//      new Integer(BusiConst.DOCUMENTSSTATE_OTHERSCARD)
      };

   static final String[] values = { "���֤","���ڲ�","����", "����֤","ʿ��֤","�۰ľ��������ڵ�ͨ��֤","̨��ͬ�������ڵ�ͨ��֤","��ʱ���֤","����˾���֤","����֤",
//     "����֤��"
     };
  public DocumentsState()
  {
    this.putValues(keys,values);
  }
}
