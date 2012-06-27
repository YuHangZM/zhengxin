package org.xpup.hafmis.syscollection.paymng.agent.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IAgentBS {
  /**
   * ���뵼���ļ�
   * 
   * @param agentImportTitle
   * @param agentImport
   * @param securityInfo
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] saveAgent(List agentImportTitle, List agentImport,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  /**
   * ���ݽɴ����²����Ƿ���δʹ�õ�ҵ��
   * 
   * @param agentYearMonth
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object findAgentYearMonthCount(String agentYearMonth)
      throws BusinessException, Exception;

  /**
   * ��ѯ�����б�ķ���
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] findAgentInfo(Pagination pagination,
      SecurityInfo securityInfo, String agentDetailId)
      throws BusinessException, Exception;

  /**
   * ɾ�����۵�����Ϣ�ķ���
   * 
   * @param agentDetailId
   * @param orgAgentNum
   * @throws BusinessException
   * @throws Exception
   */
  public void deleteAgentInfo(String agentDetailId, String orgAgentNum)
      throws BusinessException, Exception;

  /**
   * ȫ��ɾ���ķ���
   * 
   * @param agentDetailId
   * @param orgAgentNum
   * @throws BusinessException
   * @throws Exception
   */
  public void deleteAllAgentInfo(String agentDetailId, String orgAgentNum)
      throws BusinessException, Exception;

  /**
   * ��ѯ���۱���б�ķ���
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List findAgentChgInfoList(Pagination pagination,SecurityInfo info)
      throws BusinessException, Exception;

  /**
   * ���ɴ��۱���ķ���
   * 
   * @param agentDetailId
   * @param securityInfo
   * @throws BusinessException
   * @throws Exception
   */
  public void createAgentChange(String agentHeadId, SecurityInfo securityInfo)
      throws BusinessException, Exception;

  /**
   * ��������-�������
   * 
   * @param agentHeadId
   * @throws BusinessException
   * @throws Exception
   */
  public void backAgentChagneInfo(String agentHeadId, SecurityInfo securityInfo)
      throws BusinessException, Exception;

  /**
   * ��ѯ��λ��ϸ
   * 
   * @param pagination
   * @param agentHeadId
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] findAgentOrgInfoList(Pagination pagination, String agentHeadId)
      throws BusinessException, Exception;
  /**
   * ɾ��ְ��
   * @param empAgentId
   */
  public void deleteEmpAgentInfo(String empAgentId);
  /**
   * ��ѯְ����ϸ
   * 
   * @param pagination
   * @param orgAgentId
   * @param orgId
   * @param payMode
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] findAgentEmpInfoList(Pagination pagination,
      String orgAgentId, String orgId, String payMode) throws BusinessException, Exception;

  // ��ô�����Ϣ���� LIST
  public List queryAgentInfoTaList(Pagination pagination,SecurityInfo securityInfo) throws Exception;

  // ��ô�����Ϣ count
  public int queryAgentInfoTaListCount(Pagination pagination,SecurityInfo securityInfo) throws Exception;

  // ��ô�����Ϣ(��λ)���� LIST
  public List queryAgentInfoTbList(Pagination pagination) throws Exception;

  // ��ô�����Ϣ(��λ) count
  public int queryAgentInfoTbListCount(Pagination pagination) throws Exception;

  // ��ô�����Ϣ(ְ��)���� LIST
  public List queryAgentInfoTcList(Pagination pagination) throws Exception;

  // ��ô�����Ϣ(ְ��) count
  public int queryAgentInfoTcListCount(Pagination pagination) throws Exception;
}
