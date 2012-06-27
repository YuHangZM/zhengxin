package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;


/**
 * 
 * @author ����
 *2007-6-27
 */
public interface IAdjustAccountBS { 
  //���������ʾ�б���
  public AdjustaccountShowAF findAdjustWrongAccountHeadAndTailIDByPagination(String temp_adjustWrongAccountHead_id,Pagination pagination,String temp_type,SecurityInfo securityInfo) throws Exception,BusinessException;
  //�ж�Ա���Ƿ��Ѿ������ص�315
  public boolean findHeadAndTailByCriterions(String orgId,String empId) throws Exception,BusinessException;
  //���ݸ��ݵ�λid�ź�״̬Ϊ1�Ĳ�ѯ����û����ɴ���
  public AdjustWrongAccountHead findOrgHAFAccountFlowByOrgAndStatus(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException;
  //����ƾ֤�Ų�ѯ������ʵ�� 
  public List findOrgHAFAccountFlowByCriterions(String orgid,String bizdate,String bizDocNum,SecurityInfo securityInfo) throws Exception,BusinessException;
 //����������ѯ����id
  public AdjustWrongAccountHead findAdjustWrongAccountHeadIDByCriterions(String orgId) throws Exception,BusinessException;
  //����������ѯ���˼�¼
  public AdjustaccountDTO findAdjustAccountListByCriterions(Pagination pagination) throws Exception,BusinessException;
 //���ݵ�λid���ص�λ
  public String findOrgInfoById(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException;
 // ����EmpHAFAccountFlow.orgHAFAccountFlow.id��orgHAFAccountFlow.id��������EmpHAFAccountFlow����
  public AdjustaccountDTO findEmpHAFAccountFlowListById(String orgId,Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //����Ա��id��ѯԱ����Ϣ
  public Emp findEmpInfoByEmpId(Integer empId) throws Exception,BusinessException;
  //�ж�Ա���Ƿ��ڸõ�λ�� 
  public Emp checkEmp(String orgId,String empId) throws Exception,BusinessException;
  //������������id��Ա��id ��ѯ���õ�λ�Ƿ��Ѿ��й����˵���
  public AdjustWrongAccountTail findAdjustWrongAccountTailByCriterions(String orgId,String empId) throws Exception,BusinessException;
  //��������������ж����޴���ͷ��AdjustWrongAccountHead
  public AdjustWrongAccountHead findAdjustWrongAccountHeadByOrgId(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException;
  //�������β��
  public Serializable insertAdjustWrongAccountTail(AdjustWrongAccountTail adjustWrongAccountTail,Integer empId,AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo,String orgId) throws Exception,BusinessException;
  //�������ͷβ��  
  public Serializable insertAdjustWrongAccountHeadAndTail(AdjustWrongAccountTail adjustWrongAccountTail,Integer empId,String orgId,String type,SecurityInfo securityInfo,String noteNum) throws Exception,BusinessException;
  //�޸Ĵ���ͷ��״̬
//  public Serializable updateAdjustWrongAccountHeadState(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo)throws BusinessException, Exception;
  public AdjustWrongAccountHead updateAdjustWrongAccountHeadState(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo,String noteNum)throws BusinessException, Exception;
  //��101��102���Ա���͵�λ��Ϣ--���뵽314��315��
//  public Serializable insertAdjustWrongHATByOrgHAT(List list,SecurityInfo securityInfo)throws BusinessException, Exception;
  public AdjustWrongAccountHead insertAdjustWrongHATByOrgHAT(List list,SecurityInfo securityInfo,String noteNum)throws BusinessException, Exception;
  //���ݴ���ͷ����β�����ӦԱ��
  public List findAdjustWrongAccountTailByHead(String HeadID) throws Exception,BusinessException;
  //������ͷ����β�����ӦԱ�����뵽ҵ����ˮͷ��
  public void insertOrgHAFAccountFlowByWrongHAT(AdjustWrongAccountHead adjustWrongAccountHead,List empList,SecurityInfo securityInfo);
  //���ݴ���ͷ��id����ͷ��
  public AdjustWrongAccountHead findAdjustWrongAccountHeadByID(Serializable id) throws Exception,BusinessException;
  //��ѯ��ˮͨ��ƾ֤��
//  public OrgHAFAccountFlow findOrgHAFAccountFlowByDocNumCriterions(String bizDocNum) throws Exception,BusinessException;
  //���Ĺ�
  public OrgHAFAccountFlow findOrgHAFAccountFlowByDocNumCriterions(String bizDocNum,String bizdate,String orgid) throws Exception,BusinessException;
  //ɾ��
  public String deleteAdjustWrongAccountTailByID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //�鿴Ա����Ϣ 
  public Emp findEmpById(String id) throws Exception,BusinessException;

  //ά�� 
  //��ѯ������ͷ��״̬Ϊ1��3�Ķ���ѯ����
  public AdjustaccountDTO findAdjustWrongAccountHeadByStatus(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //���ݲ�ѯ����ͷ��
  public AdjustaccountDTO findAdjustWrongAccountHeadByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //����ͷ��ڲ�ѯ
  public AdjustWrongAccountHead findOrgHAFAccountFlowById(String id) throws Exception,BusinessException;
  //ɾ��ͷβ���
  public String deleteAdjustWrongAccountHeadAndTailByID(String id) throws Exception,BusinessException;
  //�ж��Ƿ���ͬһ�ҹ�˾�в�ͬ��״̬
  public List findAdjustWrongAccountHeadIDByOrgIdAndStatus(String orgId) throws Exception,BusinessException;
  //�޸Ĵ���ͷ��
  public void updateAdjustWrongAccountHeadByID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //ɾ������ͷΪ��
  public void deleteAdjustWrongAccountHeadAndTailByHID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //ɾ��101��102����ؼ�¼
  public void deleteOrgHAFAccountFlowAndEmpByHID(String id,SecurityInfo securityInfo,String bis_id) throws Exception,BusinessException;
  //�������� ɾ��101��102����ؼ�¼
  public void deleteAdjustWrongAccountAllByHID(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo) throws Exception,BusinessException;
  //ͨ����λid��ҵ��״̬�Լ�ҵ�����Ͳ�ѯ��OrgHAFAccountFlow
  public OrgHAFAccountFlow findOrgHAFAccountFlowByCriterions1(String orgId,BigDecimal Status,String bizType,String bizDate) throws Exception,BusinessException;
  //������־003
  public void insertHafOperateLog(String bizId,String orgId,String ip,String oper) throws Exception,BusinessException;
  //��ӡ ����ͷβ��
  public List adjustWrongAccountAllByHID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //ά����ϸ��ʾ
  public AdjustaccountShowAF findAdjustWrongAccountHeadAndTailIDByPagination_sy(String string, Pagination pagination, String string2, SecurityInfo securityInfo)throws BusinessException;
  //ά����ϸ��ʾ
  public AdjustaccountShowAF findAdjustWrongAccount_sy(String ids,Pagination pagination)throws BusinessException;
  //��ʾ�Ƿ���Ϊ���˵�ҵ��,��ȡ��ת��.
  public String querySpecialPickAndTranOutHead(String orgId);
  public String[] queryOfficeBankNames(String orgId,String openStatus,String bizId,String bizType,SecurityInfo securityInfo ) throws Exception;
}

