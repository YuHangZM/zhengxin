package org.xpup.hafmis.sysloan.loanapply.loanvipcheck.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.form.LoanVIPCheckShowAF;

/**
 * @author ��Ұ 2007-09-27
 */
public interface ILoanVIPCheckBS {

  // ���������б�
  public LoanVIPCheckShowAF queryBorrowerListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // ����ͨ�� update BorrowerAcc
  public void updateContractSTApprovalPass(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ����ͨ�� update BorrowerAcc
  public void updateContractSTlastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��������ͨ�� update BorrowerAcc
  public void updateContractSTdellastsure(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  //������ͨ�� update BorrowerAcc
  public void updateContractSTApprovalNotPass(String contractId, String reason,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  // ������ͨ�� update BorrowerAcc
  public void updateContractSTApprovalNotPass(String[] rowArray, String reason,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // �������� update BorrowerAcc
  public void updateContractSTApprovalQuash(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ת�����´�
  public String changeOffice(String office) throws Exception;

  // �ٴ�����ͨ��
  public void updateContractStApprovalPassAgain(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // �����ٴ�����
  public void updateContractStApprovalQuashAgain(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
