package org.xpup.hafmis.sysloan.loanapply.loanlastsure.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.form.LoanLastSureShowAF;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;

/**
 * @author ��Ұ 2007-09-27
 */
public interface ILoanLastSureBS {

  // ���������б�
  public LoanLastSureShowAF queryBorrowerListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  
  // ����ͨ�� update BorrowerAcc
  public void updateContractSTlastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  // ��������ͨ�� update BorrowerAcc
  public void updateContractSTdellastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  

  // ת�����´�
  public String changeOffice(String office) throws Exception;
}
