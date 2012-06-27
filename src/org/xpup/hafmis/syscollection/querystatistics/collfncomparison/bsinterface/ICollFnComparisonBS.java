package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface ICollFnComparisonBS {
  /**
   * ��ѯְ����Ϣ�ķ���
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws BusinessException
   */
  public Object[] findCollFnComparisonEmpInfo(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;

  /**
   * ��ѯְ���˵ķ���
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws BusinessException
   */
  public Object[] findCollFnComparisonEmpAccount(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  public Object[] findCollectionuseinfo(SecurityInfo securityInfo,
      Pagination pagination, String orgId_1) throws Exception, BusinessException;

  /**
   * ��ѯ������
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List queryEmpInfoPop(Pagination pagination, SecurityInfo securityInfo)
      throws Exception;
  /**
   * ��ѯ��λ����
   * @param orgId
   * @return
   * @throws Exception
   */
  public String findOrgName(String orgId) throws Exception;
  public List findOrgpopList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public Org queryOrgById(Integer id,SecurityInfo securityInfo) throws Exception,
  BusinessException;

  public List changePrintList(List list) throws Exception;


  public List findOrgAccountPrintList(String orgidst, String orgidend, String timest, String timeend, SecurityInfo securityInfo) throws Exception;
  public int findOrgpopListCount(String orgid,String orgname,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * ְ���˲�ѯ�������ѯlist�ķ���
   * @param pagination
   * @return
   * @throws Exception
   */
  public List findEmpPopList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  /**
   * ��ѯְ���˴�ӡ�б�
   * @param orgidst
   * @param orgidend
   * @param empidst
   * @param empidend
   * @param timeSt
   * @param timeEnd
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findEmpAccountPrint(String orgidst, String orgidend,
      String empidst, String empidend, String timeSt,
      String timeEnd,SecurityInfo securityInfo)throws Exception;
  public Object[] findOrgCollInfo(Pagination pagination);
  /**
   * ְ���˲�ѯ�����б�
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List queryqcye(Pagination pagination, SecurityInfo securityInfo)
  throws Exception ;
  /**
   * ��λ�˲�ѯ����б�
   * @param pagination
   * @param securityInfo
   * @param orgId_1
   * @return
   * @throws Exception
   */
  public List queryqcyeorg(Pagination pagination, SecurityInfo securityInfo,
      String orgId_1) throws Exception ;
}
