/**
 * Copy Right Information   : Goldsoft 
 * Project                  : IPreLoanRefrBS
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.form.PreLoanRefrShowAF;

public interface IPreLoanRefrBS {

  // ��ǰ��ѯ�б�
  public PreLoanRefrShowAF queryPreLoanRefrListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

}
