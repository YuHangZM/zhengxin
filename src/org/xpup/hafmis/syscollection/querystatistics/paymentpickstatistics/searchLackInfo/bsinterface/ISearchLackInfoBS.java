package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoHeadDTO;

public interface ISearchLackInfoBS {
  // ����������ѯǷ���б�
  public List findSearchLackInfoByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����������ѯǷ��ȫ���б�
  public List findSearchLackInfoAllByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����������ѯǷ�ɵ�����Ϣ
  public SearchLackInfoContentDTO findSearchLackInfoOneByCriterions(
      String orgid, Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ��ʾ�ϼ���Ϣ
  public SearchLackInfoHeadDTO findSearchLackInfoListHead(List list)
      throws Exception, BusinessException;

  public void createSearchLackInfo();

  public List findSearchLackInfoListByCriterions_oldsys(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����(������)
  public List findOrgAddPayExpList_old(String id, String orgId, String orgName,
      String addPayMonth) throws Exception, BusinessException;
}
