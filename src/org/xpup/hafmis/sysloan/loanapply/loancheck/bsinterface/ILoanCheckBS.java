package org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckShowAF;

/**
 * @author ��Ұ 2007-09-22
 */
public interface ILoanCheckBS {

  // ��˴����б�
  public LoanCheckShowAF queryBorrowerListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ���ͨ�� update BorrowerAcc
  public void updateContractSTCheckPass(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateContractSTCheckPassrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��˲�ͨ�� update BorrowerAcc
  public void updateContractSTCheckNotPass(String contractId, String reason,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateContractSTCheckNotPassrowArray(String[] rowArray,
      String reason, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // ������� update BorrowerAcc
  public void updateContractSTCheckQuash(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateContractSTCheckQuashrowArray(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ת�����´�
  public String changeOffice(String office) throws Exception;
  // �ؼ�ȷ��
  public void updateContractStRedateSure(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  public void updateContractStRedateSureDel(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  // �ٴ����ͨ��
  public void updateContractStChkAgainPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // �����ٴ����
  public void updateContractStChkAgainQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  
}
