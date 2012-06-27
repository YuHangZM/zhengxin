package org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;

public interface IMonthpayBS {
  public String getSeq_aa300() ;
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException;
  public MonthpayJYAF findMonthpayInfo(Serializable orgid,SecurityInfo securityInfo) 
  throws BusinessException,Exception;
  public List addPaymentInfo(MonthpayJYAF f,SecurityInfo securityInfo) throws Exception, BusinessException;// wangy 2008-02-27 �׳�BusinessException
  public MonthpayJYAF findMonthpayInfoCheck(MonthpayJYAF monthpayJYAF,SecurityInfo securityInfo) throws BusinessException,Exception;
  public List findPaymentList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public void deletePaymentInfo(Serializable paymentId,SecurityInfo securityInfo) throws Exception;
  public MonthpayJYAF findPringInfo(String paymentId) throws Exception ;
  public List findTaillistMX(Pagination pagination)throws Exception;
  public List findTailTotal(String paymentid) throws Exception;
  public BigDecimal findMonthpayTotalmoney(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public List findTaillistMXPrint(Pagination pagination)throws Exception;
  public String findCollBank(String collBankid);
  public String[] queryOfficeBankNames(String orgId,String openStatus,String bizId,String bizType,SecurityInfo securityInfo ) throws Exception;
//  //��ѯ�������������������
//  public String[] queryCheckkNames(String bizId,String bizType) throws Exception;
  public List findSearchLackInfoAllByCriterions(String orgId,SecurityInfo securityInfo) throws Exception;
  /**
   * ��ȡ��λְ���Ľ�������
   * @return
   */
  
  public String getOrgPaymentMonth(String orgId,String status)throws Exception;
  /**
   * �õ���λ�Ĺ��˽��
   */
  public BigDecimal queryOverPay(String orgId) throws Exception;
  /**
   * �жϽɴ�ĵ�λ�Ƿ���Ϊ���˵Ĺ���ҵ��
   * @param orgId
   * @return
   * @throws Exception
   */
  public boolean isOverPay(String orgId) throws Exception;
  
  public List queryPaymentBankNameList (Integer orgid) throws Exception;
  
  public List queryCollBankInfo(String officecode,String bankid) throws Exception;
  
  public String queryPaymentBankName(String id)  throws Exception;
  
  public String queryCeterName(String officecode,String bankCode,String collbankid) throws Exception;
  
  public String queryMakerPara() throws Exception;
  
  public String getOrgPayMonth(Serializable orgid);
  public List findPaymentListPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public List findTaillistMXExport(Pagination pagination,String[] orderby)throws Exception;
  public MonthpayJYAF findPayInfo(String paymentId) throws Exception ;
  public void updatePaymentInfo(Serializable paymentId,String bankName,String bankAcc,SecurityInfo securityInfo) throws Exception;
}