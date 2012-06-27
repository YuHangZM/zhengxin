package org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;

public interface ILoanaccordBS {
  public String getLoanBankId(String contractId) throws BusinessException;
  //��ѯ����ŵ������Ϣ�Ա��ʵ
  public LoanaccordNewAF queryLoanaccordInfo(String contractId,SecurityInfo securityInfo)throws BusinessException;
  //����pl111
  public String updateBorrowerAccInfo(LoanaccordDTO loanaccordDTO,SecurityInfo securityInfo)throws BusinessException;
  //����ά������
  public LoanaccordShowAF queryLoanaccordList(Pagination pagination,SecurityInfo securityInfo) throws BusinessException;
  //ɾ�����ŵļ�¼
  public void removeLoanaccordInfo(String contractId,SecurityInfo securityInfo)throws BusinessException;
  //��ӡƾ֤
  public LoanaccordDTO printLoanaccordInfo(String contractId,SecurityInfo securityInfo)throws BusinessException;
}
