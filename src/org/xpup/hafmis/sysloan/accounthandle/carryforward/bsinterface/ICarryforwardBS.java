package org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardShowAF;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.form.CarryforwardTbShowAF;

public interface ICarryforwardBS {
  // ������ʾ��Ҫ���ļ�¼
  public CarryforwardShowAF queryBorrowerAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;

  // ���ս�ת������Ϊ��
  public String useCarryforward(String loanBankId,
      SecurityInfo securityInfo) throws BusinessException;
  
  // ���ս�ת������Ϊ��
  public String useBankCarryforward(String loanBankId,
      SecurityInfo securityInfo) throws BusinessException;
  
  //������Ϊ����ʱ�򣬽�����֤�Ĳ�ѯ
  public String queryCarrayforwardInfo(String loanBankId,
      SecurityInfo securityInfo)throws BusinessException;
}
