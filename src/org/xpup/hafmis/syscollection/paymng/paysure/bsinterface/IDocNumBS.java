package org.xpup.hafmis.syscollection.paymng.paysure.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * 
 * @author �����
 * 2007-6-26  
 */
public interface IDocNumBS {
  
  public String getDocNumDesignPara() throws Exception;
  //��������officeCode��bizYearmonth����ƾ֤��
  public String getDocNumdocNum(String officeCode,String bizYearmonth,SecurityInfo securityInfo) throws Exception,BusinessException;
  //��Ӽ�¼
  public void insertDocNumCancel(String docNum,String officeCode,String bizYearmonth) throws Exception, BusinessException;
}
