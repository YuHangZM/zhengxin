package org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface;



import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayListAF;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;


public interface IChgpayBS {
  //����������ѯChgslarybaseListAF��¼
  public ChgpayListAF findChgpayList(Pagination pagination) throws Exception,BusinessException;
 
//����������ѯChgPaymentTail��¼ 
  public ChgPaymentTail findEmpinfo(String empid,String orgid) throws Exception,BusinessException;
  
  //��Ӽ�¼
  public void addChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  
  //����������ѯChgslarybaseListAF��¼
  public ChgPaymentTail findChgPaymentTailWuht(String id,Pagination pagination) throws Exception,BusinessException;
  
  //�޸ļ�¼
  public void updateChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  
  //ɾ����¼
  public void deleteChgPaymentTail(Integer id,String pkid,String orgid,String ip,String nrOfElements,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
//ɾ����¼��ȫ����
  public void deleteAllChgPaymentTail(Integer id,String pkid,String orgid,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
  
  //������������
  public void useChgPaymentPayment(Pagination pagination) throws Exception,BusinessException;
  
  //����������ѯChgslarybaseListAF��¼
  public ChgpayListAF findOrgChgpayList(Pagination pagination) throws Exception,BusinessException;
  //����orgid��ѯOrg��¼ 
  public String findOrgidById(String id)throws Exception,BusinessException;

//ɾ����¼��ά����
  public void deleteAllChgPaymentTailMaintain(Integer id,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;

//��������������ά����
  public void useChgPaymentPaymentMaintain(Pagination pagination) throws Exception,BusinessException; 

//������������������ά����
  public boolean deluseChgPaymentPaymentMaintain(Pagination pagination) throws Exception,BusinessException;

//����������ѯ��¼��������
  public List queryEmpOrgList(Pagination pagination) throws Exception,BusinessException;
//����������Ӽ�¼�����룩
  public List addChgpayListBatch(List addchgpayHeadImportList,List addchgpayListImportList,String orgId,String chgMonth,String ip,String name,String date,SecurityInfo securityInfo) throws Exception,BusinessException;
// ���ά�������򷽷�
  public ChgpayListAF findChgpayWindowList(Pagination pagination) throws Exception,BusinessException;
//�����ά�����ύ
  public void PickInChgPaymentTailMaintain(String id, String orgid, SecurityInfo securityInfo,String flag) throws Exception,BusinessException;
  //�����ά���ĳ����ύ
  void removePickInChgPaymentTailMaintain(String id, String orgid, SecurityInfo securityInfo,String flag) throws Exception, BusinessException;
  //������ȡ
  public void pickUp(String orgid,String chgMonth, SecurityInfo securityInfo) throws Exception, BusinessException;
  //��ѯ��λID
  public String  queryOrgidByChgPaymentHeadID(String chgPaymentHeadID) throws Exception, BusinessException;
}
