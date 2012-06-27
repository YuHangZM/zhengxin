package org.xpup.hafmis.sysloan.common.biz.orgpop.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IOrgpopBS{
  /**
   * ���ݵ�λ��š���λ���ơ���λ״̬��ѯ��λ��Ϣ
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List findOrgpopList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  
}