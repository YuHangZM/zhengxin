package org.xpup.hafmis.sysfinance.common.biz.credencepop.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ICredencePopBS {
  /**
   * ��ѯƾ֤�ķ���
   * @param docNum ƾ֤��
   * @param securityInfo
   * @return obj[0]�б�obj[1]ƾ֤��Ϣ��obj[2]���ϼƣ�obj[3]����ϼ�
   * @throws Exception
   * @throws BusinessException
   */
  public Object[] findAccountantCredence(String docNum,SecurityInfo securityInfo, String credenceDate, String office)throws Exception;
  
  /**
   * ��ѯƾ֤��˵��˵��
   * @param credenceCharacter ƾ֤��
   * @param securityInfo
   * @return ƾ֤��˵��˵��
   * @throws Exception
   */
  public String findParamExplain(String credenceCharacter,SecurityInfo securityInfo) throws Exception;
}
