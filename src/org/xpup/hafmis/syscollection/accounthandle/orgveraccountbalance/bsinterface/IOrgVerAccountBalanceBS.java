/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.bsinterface;

import java.io.Serializable;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.form.OrgVerAccountBalanceAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IOrgVerAccountBalanceBS {

  // ��ѯ��λ��Ϣ
  public Org queryOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;
  
  // ��ѯ��λ����
  public Org findOrgInfo(String orgid, SecurityInfo securityInfo)
      throws BusinessException;

  // ��ѯ��λ������ת�б�
  public OrgVerAccountBalanceAF queryOVAccountBalanceListByCriterions(
      String accYear, Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ��ת���
  public void doOrgVerAccountBalance(Serializable id, String docNum, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

}
