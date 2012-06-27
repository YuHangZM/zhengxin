package org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.TranInHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TranTbPrintAF;

/**
 * shiyan
 */
public interface ITraninBS {
  // ��ӡ������
  public TraninVidAF print_sy(String tranInHeadId,SecurityInfo securityInfo) throws BusinessException;

  // ����������ѯ��¼
  public List findTraninListByCriterions(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException;
  public List queryTraninVid_sy_yg(Pagination pagination,SecurityInfo securityInfo)
  throws BusinessException;
  // ����id����Ʊ�ݱ��
  public TraninAddAF findTranInHeadById(Integer id, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  public String FindAA103_DayTime(String collbankid) throws Exception;
  public String queryNoteNum() throws Exception;
  // ����״̬����ת����Ϣ
  public List queryTranInHeandInOrgId(String inOrgId, String tranStatus)
      throws Exception, BusinessException;

  // ����ת��β��
  public void insertTranInTail(TranInTail tranInTail) throws Exception,
      BusinessException;

  // ����ת��ͷ��
  public Serializable insertTranInHead(TranInHead tranInHead) throws Exception,
      BusinessException;

  // ����β��id
  public TranInTail queryTranInTailById(Integer id,SecurityInfo securityInfo) throws Exception,
      BusinessException;
  public TranInHead queryTranInHead(String id) throws Exception,
  BusinessException;

  // ����β��
  public void updataTranInTail_sy(TranInTail tranInTail, String tranInHeadId,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ɾ��β��
  public void deleteTranInTail_sy(Integer integer, String tranInHeadById,
      String orgId,SecurityInfo securityInfo) throws Exception, BusinessException;

  // ͳ��ת��β�����
  public int countTraninListByCriterions(String id,SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // ɾ��ͷ���¼
  public void deleteTranInHead_sy(String tranInHeadById, String orgId,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ת��ɾ��ҳ��
  public void deletePageList_sy(String tranInHeadById,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ɾ��ҳ����Ϣ
  public void deletePageList(String tranInHeadById,SecurityInfo securityInfo)
      throws BusinessException;

  // ����org��Ϣ
  public Org queryOrgDAO(Integer id,SecurityInfo securityInfo) throws Exception, BusinessException;
  public Org queryOrg_yg(Integer id) throws Exception, BusinessException;

  // ����ת�뵥λ
  public TranInOrg queryTranInOrg(Integer id) throws Exception,
      BusinessException;

  // ͳ�����ת���ܺ�
  public Pagination countTraninListAll(Pagination pagination,SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // ��������
  public void modifyTraninBatch(List traninImportList1, List traninImportList2,
      String inOrgId,SecurityInfo securityInfo) throws Exception, BusinessException;

  // �ı�ͷ��״̬
  public void updataTranInHead(String tranInHeadId,SecurityInfo securityInfo,String noteNum)
      throws Exception, BusinessException;

  // �Զ�����id
  public Integer makeEmpIdSL_sy() throws Exception, BusinessException;

  // �ж�ͬһְ��������֤����������Ƿ��ڸõ�λ����
  public List getEmp_sy(String orgID, String empName, String cardNum)
      throws Exception, BusinessException;

  // Ϊ��ת�����׼������
  public List queryTranOutListByCriterionsAll_sy(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ת��ά����½��ѯҳ��
  public List queryTraninVid_sy(Pagination pagination,SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // ��ת�����
  public String saveTranin_sy(String inOrgId, String tranOutHeadId,
      String tranOutOrgid,SecurityInfo securityInfo) throws BusinessException;

  // ������������BA003
  public void insertHafOperateLog_sy(String orgId,SecurityInfo securityInfo);

  // ά���޸Ĳ���BA003
  public String modiftHafOperateLog_sy(String tranInHeadId,SecurityInfo securityInfo);

  // ת�����
  public String addTranInTail_sy(String inOrgId, String noteNum,
      TranInTail tranInTail,SecurityInfo securityInfo) throws BusinessException;

//ƾ֤��ӡ(�״�)
  public TranTbPrintAF printCredence(String headid) throws BusinessException,Exception;
  public org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printCredence_yg(String headid) throws BusinessException,Exception;
  public org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF printCredence_yga(String headid) throws BusinessException,Exception;
  //������ӡ
  public List printAll(Pagination pagination,SecurityInfo securityInfo) throws BusinessException,Exception;
  
  // ���й�˾���������֤��ͬ����emp
  public List queryEmp_sy(String empName, String cardNum);
  //����λ��������emp
  public List querySameCompanyEmp_sy(String inOrgId,String empName, String cardNum);
  // ������������ͬ��emp
  public void addTranInTail2_sy(String inOrgId, String noteNum,
      TranInTail tranInTail1, String empId,SecurityInfo securityInfo);

  // ת��ά����������ת��Ǽ�
  public void adjustTranin_sy(String tranInHeadId,SecurityInfo securityInfo) throws BusinessException;

  public List findEmployee(Pagination pagination);
  //������Ϊ����ʾת�����ϸҳ��
  public TraninAF findTraninListByCriterionsAAC(Pagination pagination, SecurityInfo securityInfo);
  //ת��ά�����
  public void updataTranInVidHead(String tranInHeadId, SecurityInfo securityInfo, String noteNum)throws BusinessException;
  /**
   * ��ѯ������ͬ�������֤����ͬ��ְ����Ϣ
   * @param empName
   * @param cardNum
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumSame(String empName, String cardNum) throws BusinessException, Exception ;
  public List tranoutTailReason(String tranheadid) throws BusinessException, Exception ;
  /**
   * �޸�ʱ��ѯ������ͬ�������֤����ͬ��ְ����Ϣ
   * @param empName
   * @param cardNum
   * @param empId
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isUpdateCardNumSame(String empName, String cardNum, String empId) throws BusinessException, Exception ;
//���İ��ʱ����ȡ����
  public void pickupDateAll(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
  //��λ���ʱ���ύ����
  public void referringDataInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
  //��λ���ʱ�����ύ����
  public void pprovalDataInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
//��λ���ʱ���ύ����
  public void referringDataFirstInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
  //��λ���ʱ�����ύ����
  public void pprovalDataFirstInfo(String tranInHeadById,SecurityInfo securityInfo)throws BusinessException;
}
