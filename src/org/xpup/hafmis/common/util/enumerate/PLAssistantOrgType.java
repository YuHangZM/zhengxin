package org.xpup.hafmis.common.util.enumerate;

import org.xpup.hafmis.common.util.BusiConst;


/**
 * Э����λ����
 * @author JJ
 *2007-10-06
 */
public class PLAssistantOrgType extends AbsBusiProMap {

  static final long serialVersionUID = -6831497426265030966L;

  static final String[] keys = {
      BusiConst.PLASSURE_ORG,
      BusiConst.PLSURROGATE_ORG,
      BusiConst.PLSPONSION_ORG,
      BusiConst.PLINSURANCE_ORG,
      BusiConst.PLNOTARIZATION_ORG};

   static final String[] values = { "������˾","�������","���չ�˾","��������","��֤��"};
  public PLAssistantOrgType()
  {
    this.putValues_Str(keys,values);
  }
}
