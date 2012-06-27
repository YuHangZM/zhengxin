package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.dto.OrgbusinessflowHeadDTO;

public interface IOrgbusinessflowBS {
  // ����������ѯ��λҵ����ˮ�б�
  public List findOrgFlowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����������ѯ��λҵ����ˮȫ���б�
  public List findOrgFlowAllListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��ʾ�ϼ���Ϣ
  public OrgbusinessflowHeadDTO findOrgFlowListHead(List list)
      throws Exception, BusinessException;

  public List findEmpHAFAccountFlowList(Pagination pagination,
      SecurityInfo securityInfo);

  public List findEmpHAFAccountFlowTotalList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  public OrgHAFAccountFlow findOrgHAFAccountFlow(String orgbusinessflowHeadID);
}
