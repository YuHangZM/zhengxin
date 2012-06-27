package org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonEmpInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgPersonAutoopenAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;

/**
 * @author ���� 2007-6-27
 */
public interface IChgpersonDoBS {
  // ����������ѯ��Ա�����¼
  public List findChgpersonDoListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����������ѯ��Ա�����¼�ϼ���
  public ChgPersonHeadFormule findChgpersonHeadByCriterions(
      Pagination pagination, SecurityInfo securityInfo, String headId)
      throws Exception, BusinessException;

  public ChgPersonHeadFormule findChgpersonHeadByCriterions_wsh(
      Pagination pagination, SecurityInfo securityInfo, String headId)
      throws Exception, BusinessException;

  // AJAXAction���õ���ѯ��λ
  public Org queryOrgById(Integer id, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ������Ա�����Ϣ
  public String saveChgpersonDO(String orgID, String empID, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i, SecurityInfo securityInfo) throws Exception, BusinessException;

  // ������Ա�����Ϣ
  public void insertChgpersonDO(String orgID, String empID, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��ѯ :����Ա�������Ϊ�������ʱ���������ݲ���
  public ChgPersonTail getChgPersonTail_WL(String orgID, String empID,
      String chgDate, String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i) throws Exception, BusinessException;

  // ��ѯ�Ƿ���������λ���ڸ�ְ��
  public List getOtherOrgMessage_WL(String orgID, ChgPersonTail chgPersonTail);

  // ����AA002������ѯְ����Ϣ
  public Emp queryEmpByID(String id);

  // ɾ����¼
  public void deleteChgPersonTail(Integer id, SecurityInfo securityInfo)
      throws BusinessException;

  // ɾ����ǰҳ
  public void deletePageList(String chgpersonHeadID, SecurityInfo securityInfo)
      throws BusinessException;

  // ���ݵ�λID ��ְ����Ų�ѯְ����Ϣ
  public Emp getEmpMessage(String orgID, String empID) throws BusinessException;

  // ����
  public void useChgPerson(String orgID, String chgDate,
      SecurityInfo securityInfo) throws BusinessException;

  // ��������
  public List getChgpersonListAll(Pagination pagination, String orgID,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����������ѯ��¼�����룩
  public List modifyChgpersonBatch(List chgpersonExpTitle,
      List chgpersonExpContent, String orgID, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ���� ��Ա���ά�� ��һ�ν���ʱȡ���б���Ϣ
  public List findChgpersonMaintainList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ���ά��������������ѯ�б���Ϣ
  public List findChgpersonMaintainListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����AA204ͷ��ID ��ѯ��λID ��Ϣ
  public String getOrgID(String chgPersonHeadID) throws Exception,
      BusinessException;

  // ����AA204ͷ��ID��ѯβ��LIST Ȼ�����this.deletePageList��������ά��ҳ��ɾ��
  public void deleteChgPersonALL(String chgPersonHeadID,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����AA204ͷ��ID��������У��,�Ƿ���Խ���ҵ����
  public void checkDelUseMessage(String chgorgrateID, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ��������
  public void deluserChgPersonMessage(String chgorgrateID,
      SecurityInfo securityInfo) throws BusinessException;

  // ����β��ID��ѯ��Ӧͷ��ID
  public String queryChgpersonHeadID(String tailID);

  // ����ͷ��ID��ѯ��Ա�����¼��ϸ
  public List findChgpersonDoListByHeadID(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // �ж��ڱ���λ��ת��ҵ�����Ƿ����Ҫ������ְ��
  public void checkTranInBusiness(String orgID, String empName,
      String documentNum) throws BusinessException;

  // �жϱ�������������� �Ƿ�Ϊ0
  public boolean isChgperson(String empid, String orgid) throws Exception;

  // ��Ա��ǰ��״̬
  public String getChgpersonStatus(String empid, String orgid) throws Exception;

  /**
   * ��ѯ������ͬ�������֤����ͬ��ְ����Ϣ
   * 
   * @param empName
   * @param cardNum
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumSame(String orgId, String empName, String cardNum)
      throws BusinessException, Exception;

  // �ύ��¼
  public void PickInDate(String id, SecurityInfo securityInfo, String flag)
      throws Exception, BusinessException;

  // �����ύ��¼
  public void removePickInDate(String id, SecurityInfo securityInfo, String flag)
      throws Exception, BusinessException;

  // ��ȡ����
  public void pickUpDate(String orgid, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * �жϸĵ�λ�Ƿ���Խ����Զ����
   * 
   * @param orgId ��λid
   * @return ���0�����Խ����Զ����1����
   * @throws Exception
   * @throws BusinessException
   * @author ���Ʒ�
   */
  public String isAutoChange(String orgId) throws Exception, BusinessException;

  /**
   * ��ѯ�Զ����������ķ���
   * 
   * @param pagination
   * @return �б�����
   * @throws Exception
   * @throws BusinessException
   * @author ���Ʒ�
   */
  public List findAutoChangePopList(Pagination pagination) throws Exception,
      BusinessException;

  public List findAutoChangePopListALL(Pagination pagination) throws Exception, BusinessException;

  /**
   * ���ݵ�λ�����ְ����Ų�ѯ׼��������˵���Ϣ
   * 
   * @param orgId
   * @param empId
   * @return
   * @throws Exception
   * @throws BusinessException
   * @author ���Ʒ�
   */
  public ChgpersonEmpInfoDTO findChgpersonEmpInfo(String orgId, String empId)
      throws Exception, BusinessException;
  /**
   * ��ѯ�õ�λ���Ƿ������֤ͬ���������ְͬ��������ְ������
   * @param orgId
   * @param empName
   * @param cardNum
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public int isExistSameEmpInfo(String orgId, String empName, String cardNum)
      throws Exception, BusinessException;

  // //����� 2008-6-16
  public List findChgpersonDoListAllByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public String queryCollectionBankNameById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  public String getNamePara() throws Exception;

  public ChgPersonAutoopenAF findChgPersonAutoopenAF(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateTranInTail(String orgID, String empId, String type)
      throws Exception, BusinessException;

  public String saveChgpersonDO_wsh(String orgID, String empID, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i, SecurityInfo securityInfo) throws Exception, BusinessException;

  public List findChgpersonDoListAllByCriterions_wsh(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  public void deleteFnTempTable(String type) throws Exception, BusinessException;
}