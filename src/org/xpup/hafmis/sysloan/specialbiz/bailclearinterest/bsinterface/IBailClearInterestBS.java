package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

/**
 * @author ��Ұ 2007-10-05
 */
public interface IBailClearInterestBS {

  // ������ѯ��ͨ���ſ�����
  public BailClearInterestTaAF findBailClearInterestTaListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // ����֤���Ϣ-ȫ����Ϣ
  public void bailClearInterestTa(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��֤���Ϣά��-��ʾ�б�
  public BailClearInterestTbAF queryBailClearInterestTbListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;
}
