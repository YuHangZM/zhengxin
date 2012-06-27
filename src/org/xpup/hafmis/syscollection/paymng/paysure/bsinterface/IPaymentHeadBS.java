package org.xpup.hafmis.syscollection.paymng.paysure.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;
import org.xpup.hafmis.syscollection.paymng.paysure.dto.AddPayTailDTO;
import org.xpup.hafmis.syscollection.paymng.paysure.form.PaymentAF;


public interface IPaymentHeadBS {
 
  // ����ID��ѯaa301
  public PaymentHead findPaymentListById(Integer id) throws Exception,
      BusinessException;
  //����ID��ѯaa302
  public List findMonthPaymentHeadById(Integer id)throws Exception,
  BusinessException;
  //����ID��ѯaa30��
  public List findMonthPaymentTailById(Integer id)throws Exception,
  BusinessException;
  //����empId���ܽ��
  public List findMonthPaymentTailSumPayByEmpId(Integer id)throws Exception,
  BusinessException;
  

  // ����paymentFindAF,pagination��ѯ
  public PaymentAF findPaymentListByPaymentFindAF(Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // Ĭ�ϲ�ѯ
  public PaymentAF findPaymentListBydefault(Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;
  //ͳһ��ѯ
  public PaymentAF queryPaymentListBydefault(Pagination pagination, SecurityInfo securityInfo) throws Exception,
  BusinessException;
  
  //�ж�ҵ������(����ȷ��)
  public int SureType(PaymentHead paymentHead,String docNum, SecurityInfo securityInfo)throws Exception,
  BusinessException;
  
  //�ж�ҵ�����ͣ���������ȷ�ϣ�
  public int DelType(PaymentHead paymentHead, SecurityInfo securityInfo)throws Exception,
  BusinessException;

 
  /**
   * ����orgId������Ϊ���ˣĲ�ѯaa101
   */
  public PaymentAF findOrgHAFAccountFlowByIdAndType(String id ,String type) 
     throws Exception;
  public PaymentAF findPersonAddPayListByCriterions(Pagination pagination) throws Exception;
  
  public PaymentAF findOrgaddpayMX(Pagination pagination) throws Exception;
  
  public OrgaddpayTaAF findOrgaddpayMX_(Pagination pagination) throws Exception;
  
  public List queryByHeadId(Integer paymentHeadId)throws Exception;
  public String findPayHeadIdInAA201AA202AA204(String id) throws Exception, BusinessException;
}
