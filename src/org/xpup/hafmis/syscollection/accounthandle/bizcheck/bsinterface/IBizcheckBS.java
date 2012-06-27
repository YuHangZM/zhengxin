package org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckAF;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckDetailAF;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountDetailAF;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

/**
 * 2007-07-11
 * @author �����
 *
 */
public interface IBizcheckBS {
  
  //Ĭ�ϲ�ѯ
  public BizcheckAF findOrgHAFAccountFlowListBydefault(Pagination pagination) throws Exception,
      BusinessException;
///������ҵ�񸴺ˡ�����ͨ��
  public void checkthroughOrgHAFAccountFlow(Integer id,String ip,String name,String officeCode,String moneyBank) throws Exception,BusinessException; 
//  ������-ҵ�񸴺�-��������
  public void delcheckthroughOrgHAFAccountFlow(Integer id,String ip,String name,String collectionBankId,String officeCode) throws Exception,BusinessException; 
//������ҵ�񸴺ˡ���������
  public void checkallOrgHAFAccountFlow(Pagination pagination,String moneyBank) throws Exception,BusinessException; 
  // ������ҵ�񸴺ˡ��������˳���
  public void delcheckallOrgHAFAccountFlow(Pagination pagination) throws Exception,BusinessException; 
//������ˮͷ��id����ͷ��
  public BizcheckDetailAF findOrgHAFAccountFlowCellByID(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //��ϸ��Ϣ��ѯ
  public BizcheckDetailAF findOrgHAFAccountFlowByID(Pagination pagination,SecurityInfo securityInfo, ILoanDocNumDesignBS loanDocNumDesignBS) throws Exception,
      BusinessException;
  //orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(id);
  public OrgHAFAccountFlow findOrgHAFAccountFlowByID_(Integer id) throws Exception,
  BusinessException;
  //����AA101������ID���ض�Ӧҵ���ҵ��ID
  public String queryBizIDByFlowID(String flowID) throws Exception;
  //���ݵ�λid�õ����ڰ��´�����ݰ��´���ѯaa412���е������޸�����
  public String queruIsBankModify(String orgId)throws Exception;
  //��ѯ�������˵ĵ�λ�����ڰ��´�����
  public int findOfficeCount_wsh(String orgIds)throws Exception;
  //����aa101������ѯ��λid
  public String queryOrgId(String id) throws Exception ;
  //���ݵ�λ��Ų�ѯ��λ���ڹ鼯����
  public String queryOrgCollectionBankId(String orgId) throws Exception ;
  //����AA101������ID��ѯת��ҵ���ת���Ƿ��Ѿ�����
  public String isTansInFive(String flowID) throws Exception,BusinessException;
  public String queryAA306ReserveaA(String doc_num,String note_num) throws Exception, BusinessException;
  public String queryAA306ReserveaB(String doc_num,String note_num) throws Exception, BusinessException;
}
