package org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceForm;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountDetailAF;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;


/**
 * 
 * @author ����
 * 2007-7-10
 */
public interface IclearAccountBS {
  //�б���ʾ����(Ĭ��)
  public ClearAccountShowAF findOrgHAFAccountFlowDefByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //��ʾ�б�����
  public ClearAccountShowAF findOrgHAFAccountFlowTotalByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //Ĭ��ȫ���������˵��б�
  public List queryOrgHAFAccountFlowDefByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //���������ѯȫ���������˵��б�
  public List queryOrgHAFAccountFlowTotalByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //������ˮͷ��id����ͷ��
  public ClearAccountDetailAF findOrgHAFAccountFlowByID(Pagination pagination,SecurityInfo securityInfo ,ILoanDocNumDesignBS loanDocNumDesignBS) throws Exception,BusinessException;
//������ˮͷ��id����ͷ��
  public ClearAccountDetailAF findOrgHAFAccountFlowCellByID(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //����
  public String dealwithClearAccount(String[] rowArray,String busiDate,String oper,String ip,String officeid,Pagination pagination,String flag,SecurityInfo securityInfo) throws BusinessException, HibernateException, SQLException;
  //���㵥��ѯ����
  public ClearAccountBalanceDTO findClearAccountBalanceByDefault(SecurityInfo securityInfo) throws Exception;
  
  public ClearAccountBalanceForm findClearAccountBalance(Pagination pagination,
      SecurityInfo securityInfo) throws Exception ;
  public String findCollBank(String collBankid);
  //����AA101������ID���ض�Ӧҵ���ҵ��ID
  public String queryBizIDByFlowID(String flowID) throws Exception;
  //����δ����ת��ȷ�ϵ�ת���������ˣ�ϵͳӦ������ʾ
  public String checktraininBytrainout(String rowarray) throws Exception;
  //����δ����ת��ȷ�ϵ�ת����������,�õ�ת����λ���
  public String getTrainoutorgid(String rowarray) throws Exception;
  //����δ����ת��ȷ�ϵ�ת����������,�õ�ת�뵥λ���
  public String getTraininorgid(String rowarray) throws Exception;
  //�޸�����ʱ�ı�ʶ״̬
  public String updateOrgHAFAccountFlow(String rowarray,String flag) throws Exception;
  //ͨ������id��ѯ���˱�ʶ
  public OrgHAFAccountFlow queryIsClearAccountById(String rowarray) throws Exception;
  // ����ҵ��id�����Ͳ�ѯ��ˮ��ʵ��
  public OrgHAFAccountFlow queryIsClearAccountByBizId(String bizId,String type) throws Exception;;
  public String queryBankName(String flowID) throws Exception;
  public String queryMakerPara() throws Exception;
  public String queryUserName(String userName) throws Exception;
}

