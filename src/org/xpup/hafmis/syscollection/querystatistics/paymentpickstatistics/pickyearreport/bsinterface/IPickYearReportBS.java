package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickyearreport.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IPickYearReportBS {
  /*
   * ��ѯ��ȡ�걨����Ϣ
   */
  public List queryPickYearRepInfo(Pagination pagination, SecurityInfo securityInfo) 
    throws Exception, BusinessException;
}
