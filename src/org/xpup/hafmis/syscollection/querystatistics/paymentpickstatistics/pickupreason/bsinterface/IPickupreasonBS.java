package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IPickupreasonBS {
  //��Ҫ���������
  public List findPickupreasonList_sy(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
}
