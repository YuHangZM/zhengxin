package org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanfirstcheck.form.LoanFirstCheckShowAF;

/**
 * @author wangshuang 2007-09-22
 */
public interface ILoanFirstCheckBS {

  // ��������б�
  public LoanFirstCheckShowAF queryLoanInfoListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����ͨ�� update BorrowerAcc
  public void updateContractStFirCheckPass(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  
  public void updateContractStFirCheckPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ������� update BorrowerAcc
  public void updateContractStFirCheckQuash(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateContractStFirCheckQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

}
