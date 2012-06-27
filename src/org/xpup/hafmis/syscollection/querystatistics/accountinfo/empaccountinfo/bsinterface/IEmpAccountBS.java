package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.HafInterestRate;

public interface IEmpAccountBS {
  //��Ҫ���������
  public List findEmpAccountList_sy(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  //��Ҫ��ֶε��²�������
  public List findEmpAccountMonthList_sy(Pagination pagination, SecurityInfo securityInfo);
  //��Ҫ��ֶε��ղ�������
  public List findEmpAccountDayList_sy(Pagination pagination, SecurityInfo securityInfo);
}
