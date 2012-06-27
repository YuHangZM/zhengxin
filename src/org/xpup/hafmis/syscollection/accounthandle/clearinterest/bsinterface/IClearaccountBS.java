package org.xpup.hafmis.syscollection.accounthandle.clearinterest.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IClearaccountBS {
  //����������ѯ���԰����Ϣ�ĵ�λ�б�  
  public List findClearaccountListByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  //����������ѯȫ�����԰����Ϣ�ĵ�λ�б�
  public List findClearaccountAllListByCriterions(Pagination pagination,SecurityInfo securityInfo)  throws Exception, BusinessException ;
  //����
  public String clearacount(String[] rowArray,String officecode,String bizDate,String ip,String oper, SecurityInfo securityInfo,String flag)throws BusinessException ,Exception;
}
