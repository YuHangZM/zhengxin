package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;
/**
 * ����-�˲�����-���ֺϼ�����
 * @author ����
 * 2007-10-30
 */
public class FnSummary extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = { 
      BusiConst.FNSUMMARY_LASTYEARCLEAR,
      BusiConst.FNSUMMARY_BGNBLAN,
      BusiConst.FNSUMMARY_DAYSUM,
      BusiConst.FNSUMMARY_TERMSUM,
      BusiConst.FNSUMMARY_YEARSUM
      };

   static final String[] values = { "�� �� �� ת","�� �� �� ��", "�� �� �� ��", "�� �� �� ��", "�� �� �� ��"
                                  };
  public FnSummary()
  {
    this.putValues_Str(keys,values);
  }
}

