package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;

/**
 * 
 * @author ����
 *2007-6-27
 */
public interface IChgorgrateBS {
  public List queryOrgChgListCheck(Pagination pagination, SecurityInfo securityInfo) throws BusinessException ;
  public Org queryOrgByorgIDYg(String orgID, SecurityInfo securityInfo) throws BusinessException, Exception;
  public int queryOrgChgListAllCheck(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;
  public void submitOrgChgById(String id) throws BusinessException;
  public void delSubmitOrgChgById(String id) throws BusinessException;
  public void passOrgChgById(String id,SecurityInfo securityInfo) throws BusinessException;
  public void delPassOrgChgById(String id) throws BusinessException;
  public void deleteOrgChg(OrgChg orgChg) throws BusinessException;
  public void updateOrgChg(OrgChg orgChg) throws BusinessException;
  public OrgChg queryOrgChgById(String id) throws BusinessException;
  public List queryOrgChgList(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;
  public int queryOrgChgListAll(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;
  public void saveOrgChg(OrgChg orgChg) throws BusinessException;
  public int queryPersonCountByOrgID(String orgID) throws BusinessException, Exception;
  //�жϸõ�λ�Ƿ��ǰ��ʽɴ�
  public ChgOrgRate checkOrgMessage(String orgID,SecurityInfo securityInfo) throws BusinessException,Exception;
  //���ݵ�λID ��ѯ��λ��Ϣ
  public Org queryOrgByorgID(String orgID) throws BusinessException, Exception ;
  //���ݵ�λID ��ѯ��λӦ�ɽ��
  public BigDecimal querySumPayByOrgID(String orgID) throws BusinessException, Exception;
  //����ɱ�������ʱ����ѯ������£��Ȳ�AA201���޾Ͳ�AA302�����ٲ�AA001�ĳ�������
  public String getChgMonth(ChgOrgRate chgOrgRate,String orgID) throws BusinessException, Exception;
  //����AA201����������¼��
  public BigDecimal saveChgOrgRate(ChgOrgRate chgOrgRate,SecurityInfo securityInfo) throws BusinessException, Exception;
  //����AA201����������¼��
  public BigDecimal updateChgOrgRate(ChgOrgRate chgOrgRate,SecurityInfo securityInfo) throws BusinessException, Exception;
  //ɾ����¼
  public void deleteChgOrgRate(Integer id,SecurityInfo securityInfo) throws BusinessException;  
  //������������
  public void useChgOrgRate(ChgOrgRate chgOrgRate,SecurityInfo securityInfo) throws BusinessException, Exception;
  //���� ά�� ��һ�ν���ʱȡ���б���Ϣ
  public List findChgpersonMaintainList(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  //���ά��������������ѯ�б���Ϣ
  public List findChgorgrateMaintainListByCriterions(Pagination pagination,SecurityInfo securityInfo)  throws Exception, BusinessException;
  //����ͷ��ID ��ѯAA201����Ϣ
  public ChgOrgRate queryChgorgrateMessage(String id) throws Exception, BusinessException;  
  //����AA201ͷ��ID��������У��,�Ƿ���Խ���ҵ����
  public void checkDelUseMessage(String chgorgrateID,SecurityInfo securityInfo) throws Exception, BusinessException ;
  // ��������
  public void deluserChgPersonMessage(String chgorgrateID,SecurityInfo securityInfo)  throws BusinessException;
  //ȡ��ְ��״̬Ϊ(1,3,4)�Ĺ��ʻ����б�
  public List queryEmpSalaryByTIAOJIAN(String orgID)  throws BusinessException;
  
  
  //������޸�  2008��3��19
  public ChgOrgRate queryChgorgrate_OrgEdition(String orgId,String chgMonth,SecurityInfo securityInfo) throws Exception, BusinessException;  
  
}