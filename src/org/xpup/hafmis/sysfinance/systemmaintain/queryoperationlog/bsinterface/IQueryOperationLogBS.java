package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information : ��ѯҵ����־��BS�ӿ� Goldsoft Project :
 * IQueryOperationLogBS
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.1.29
 */
public interface IQueryOperationLogBS {
  /**
   * ����ҵ����־�ķ���
   * @param pagination
   * @throws Exception
   * @throws BusinessException
   */
  public Object[] findOperationLogList(Pagination pagination,SecurityInfo securityInfo,List list)throws Exception, BusinessException ;
}
