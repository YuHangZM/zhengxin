package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.form.PaymntPickAF;

public interface IPaymntPickBS {
  /*
   * ��ѯ�ɴ�֧ȡ��ϸ
   */
  public PaymntPickAF queryPaymntPickDetail(Pagination pagination, SecurityInfo securityInfo) 
    throws Exception, BusinessException;
  /*
   * ��ѯ�ɴ�֧ȡ����ͳ����Ϣ
   */
  public PaymntPickAF queryPaymntPickMonthRept(Pagination pagination, SecurityInfo securityInfo) 
    throws Exception, BusinessException;
}
