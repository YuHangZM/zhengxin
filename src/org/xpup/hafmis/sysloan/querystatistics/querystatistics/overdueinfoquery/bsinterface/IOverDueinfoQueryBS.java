package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryShowListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryShowListAF;

public interface IOverDueinfoQueryBS {
  /**
   * ͳ�Ʋ�ѯ-���ڴ߻���ѯ��Ϣ�б�
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public OverDueinfoQueryShowListAF queryShowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * ͳ�Ʋ�ѯ-���ڴ߻���ѯ��Ϣ�б��ӡ
   * @param pagination  
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  
  public List findoverDueinfoQueryPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
 
  /**
   * ͳ�Ʋ�ѯ-���ڴ߻���ѯ��Ϣ���ʴ�ӡ
   * @param contractId
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  
  public OverDueinfoQueryShowListDTO findoverDueinfoQueryPrintone(String  id,SecurityInfo securityInfo) throws Exception, BusinessException;
  public void createOverdueData(SecurityInfo securityInfo,String loanBankId) throws Exception;
  public void importOverdueData(List importList, SecurityInfo securityInfo)throws Exception;
 
}
