package org.xpup.hafmis.common.util.bizlog;
/**
 * ����ģ��-����-�������
 * @author ����
 * 2007-10-8
 */
public class FnOpReportmng extends BusiLogProMap {

  private static final long serialVersionUID = 4722697578277011765L;

  static final Integer[] keys = {
      new Integer(BusiLogConst.FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT)
      };

  static final String[] values = { "�������-���񱨱�-��������"
                                 };

  public FnOpReportmng() {
    this.putValues(keys, values);
  }

}
