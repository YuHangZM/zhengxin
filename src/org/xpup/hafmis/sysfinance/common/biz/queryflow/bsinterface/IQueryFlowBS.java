package org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;

public interface IQueryFlowBS {
  /**
   * �жϽ������������ҵ��
   * 
   * @param settNum �����
   * @return true(�鼯ҵ��) false(����ҵ��)
   * @throws Exception
   */
  public boolean IssettNum(String settNum) throws Exception;

  /**
   * ����������ѯ��λҵ����ˮ�б�
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public OrgbusinessflowAF findOrgFlowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  /**
   * Description ����ҵ����ˮͳ�Ʋ�ѯ�б�
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public LoanBusiFlowQueryAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;
}
