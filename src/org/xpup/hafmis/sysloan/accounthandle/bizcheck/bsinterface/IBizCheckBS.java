package org.xpup.hafmis.sysloan.accounthandle.bizcheck.bsinterface;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.form.BizCheckShowListAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.OverPayDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BailDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.AdjustAccountDTO;

/**
 * @author ��� 2007-09-2��
 */
public interface IBizCheckBS {
  // ������ҵ�񸴺ˡ�ҵ�񸴺��б�
  public BizCheckShowListAF queryShowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ�����ͨ��
  public void updateBizSTcheckthrough(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ���������
  public void updateBizSTdelcheck(String[] rowArray, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ�������������
  public void updateBizSTcheckall(SecurityInfo securityInfo,
      Pagination pagination) throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ���������
  public void updateBizSTdelcheckall(SecurityInfo securityInfo,
      Pagination pagination) throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ�������Ϣ
  public LoanaccordDTO queryLoanaccordById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ�����
  public OverPayDTO queryOverPayById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ�����
  public BailDTO queryBailById(String flowHeadId, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // ������ҵ�񸴺ˡ����ʵ���
  public AdjustAccountDTO queryAdjustAccountById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
