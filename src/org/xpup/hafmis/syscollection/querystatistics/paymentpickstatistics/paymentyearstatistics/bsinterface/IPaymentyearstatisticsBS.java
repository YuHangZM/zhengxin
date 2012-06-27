package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto.PaymentyearstatisticsDTO;

public interface IPaymentyearstatisticsBS {
//�б���
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO1(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
//��ʯ��
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO2(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //����
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO3(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //����Ȧ
  public PaymentyearstatisticsDTO queryPaymentyearstatisticsDTO4(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
}
