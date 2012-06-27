package org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChangeAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IOrgOpenAccountBS {
  public Serializable saveOrgOpenAccount(Org org, SecurityInfo securityInfo,
      String isOrgorcenter) throws Exception;

  public Serializable saveOrgOpenAccount_yg(Org org, SecurityInfo securityInfo,
      String isOrgorcenter) throws Exception;

  public List findOrganizationsDwkh(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;

  public Org findOPA(Integer id) throws BusinessException;

  public Org findOPA_zl(Integer id, SecurityInfo info) throws BusinessException;

  public Serializable modifyOpen(Org org, SecurityInfo securityInfo)
      throws BusinessException;

  public List findEmployee(Pagination pagination) throws BusinessException;

  public EmpkhAF findEmployee(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException;

  public int saveEmployee(String orgId, Emp emp, String emppaymonth, int flag)
      throws Exception, BusinessException;

  public Emp findEmp(Integer id) throws BusinessException;

  public boolean modifyEmployee(String id, Emp emp, SecurityInfo securityInfo)
      throws BusinessException;

  public void removeEmp(String empId, SecurityInfo securityInfo)
      throws BusinessException;

  public boolean removeEmpAll(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  public void openOver(String id, SecurityInfo securityInfo) throws Exception;

  public int removeOrg(String orgId) throws BusinessException;

  public void removeOrgEmp(String id, SecurityInfo securityInfo)
      throws BusinessException;

  public List queryEmpListByOrgid(String orgId) throws BusinessException;

  public List modifyOrgOpenBatch(List empOpenImpTitle, List empOpenImpContent,
      String orgId, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  public void ExpInsert(String orgid, SecurityInfo securityInfo)
      throws BusinessException;

  public void saveEmployeeInfo(String orgId, Emp emp, String emppaymonth,
      String empid, SecurityInfo securityInfo) throws Exception;

  public List getEmpFromOthers(String orgid, Emp emp) throws Exception;

  public List queryCollBank(String office);

  public boolean validateOpenStatus(String id);

  public boolean queryEmpCount(String orgID, String empID);

  /**
   * ��ѯ��λ���۵����б�
   * 
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findOrgAgentList(SecurityInfo securityInfo) throws Exception;

  /**
   * ��λ���۵���
   * 
   * @param orgAgentImportList
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List saveOrgAgentList(List orgAgentImportList,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  /**
   * ְ�����۵���
   * 
   * @param orgId
   * @return
   * @throws Exception
   */
  public List findEmpAgentList(String orgId) throws Exception;

  /**
   * ְ�����۵���
   * 
   * @param empAgentImportTitleList
   * @param empAgentImportList
   * @param securityInfo
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List saveEmpAgentList(List empAgentImportTitleList,
      List empAgentImportList, SecurityInfo securityInfo)
      throws BusinessException, Exception;

  /**
   * ��ѯ������ͬ�������֤����ͬ��ְ����Ϣ
   * 
   * @param empName
   * @param cardNum
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumSame(String empName, String cardNum)
      throws BusinessException, Exception;

  /**
   * �޸�ʱ�ж�������ͬ�������֤����ͬ��ְ��
   * 
   * @param empName
   * @param cardNum
   * @param empId
   * @param orgId
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumUpdateSame(String empName, String cardNum, String empId)
      throws BusinessException, Exception;

  /**
   * ������ȡ����
   */
  // ɾ����ʱ��ഫ��һ������
  public void removeEmp(String empId, SecurityInfo securityInfo, String orgId)
      throws BusinessException;

  public void pickupDateAll(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // ��λ�泷������
  public void pprovalDataInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // ��λ���ύ����
  public void referringDataInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // ��λ�泷������ְ������ҳ��
  public void pprovalDataOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // ��λ���ύ����ְ������ҳ��
  public void referringDataOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  public EmpChangeAF changeEmpIdInfo(String orgId, SecurityInfo securityInfo,
      String empId) throws BusinessException;

  /**
   * �޸�ְ�����
   */
  public void changeEmpid(String empId, String orgId, String oldEmpId)
      throws BusinessException;

  // bit add
  public List queryprintlist(String orgid, String orgname)
      throws BusinessException;

  public String querycollbankid(Integer orgid, String orgname)
      throws BusinessException;

  public void updateba001_tijiao(String orgId) throws BusinessException;
  
  public boolean queryEmployee(String orgId, String empid )
  throws BusinessException;
}
