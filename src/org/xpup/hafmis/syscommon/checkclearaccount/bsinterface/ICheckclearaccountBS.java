package org.xpup.hafmis.syscommon.checkclearaccount.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
/**
 * 
 * @author ����
 */
public interface ICheckclearaccountBS {
  //��֤��ǰϵͳ��ǰ�����Ƿ�����
  public String checkclearaccount(SecurityInfo securityInfo) throws Exception, BusinessException;
}