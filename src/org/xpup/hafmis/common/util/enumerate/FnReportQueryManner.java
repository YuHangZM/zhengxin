package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-�����ѯ��ʽ
 * @author ����
 * 2007-10-6
 */
public class FnReportQueryManner extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final Integer[] keys = { 
      new Integer(BusiConst.REPORTQUERYMANNER_YEAR),
      new Integer(BusiConst.REPORTQUERYMANNER_MONTH),
      new Integer(BusiConst.REPORTQUERYMANNER_DAY)};

   static final String[] values = { "����", "����","����" };
  public FnReportQueryManner()
  {
    this.putValues(keys,values);
  }
}

