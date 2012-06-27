package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IChgslarybaseBS {
  //����������ѯChgslarybaseListAF��¼
  public ChgslarybaseListAF findChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
  //����orgid��ѯOrg��¼ 
  public String findOrgidById(String id)throws Exception,BusinessException;
//ɾ����¼
  public void deleteChgPaymentTail(Integer id,String pkid,String orgid,String ip,String nrOfElements,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
  //ɾ����¼��ȫ����
  public void deleteAllChgPaymentTail(Integer id,String pkid,String orgid,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
// ����������ѯChgPaymentTail��¼ 
  public ChgPaymentTail findEmpinfo(String empid,String orgid) throws Exception,BusinessException;
  //��Ӽ�¼
  public void addChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  //�޸ļ�¼
  public void updateChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  //����������ѯChgslarybaseListAF��¼
  public ChgPaymentTail findChgPaymentTailWuht(String id,Pagination pagination) throws Exception,BusinessException;
  //������������
  public void useChgPaymentSalaryBase(Pagination pagination) throws Exception,BusinessException; 
  public void setAa202(Pagination pagination) throws Exception,BusinessException;
  //����������ѯChgslarybaseListAF��¼
  public ChgslarybaseListAF findOrgChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
  
//ɾ����¼��ά����
  public void deleteAllChgPaymentTailMaintain(Integer id,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
//��������������ά����
  public void useChgPaymentSalaryBaseMaintain(Pagination pagination) throws Exception,BusinessException; 
//������������������ά����
  public boolean deluseChgPaymentSalaryBaseMaintain(Pagination pagination) throws Exception,BusinessException;
//����������ѯ��¼
  public List queryEmpOrgList(Pagination pagination) throws Exception,BusinessException;
//����������Ӽ�¼
  public List addChgslarybaseListBatch(List addchgslarybaseHeadImportList,List addchgslarybaseListImportList,String orgId,String chgMonth,String ip,String name,String date,SecurityInfo securityInfo) throws Exception,BusinessException;
  public ChgslarybaseListAF findChgslarybaseWindow(Pagination pagination) throws Exception, BusinessException;
  //����202��id��ѯOrgid��¼ 
  public String queryOrgidByChgPaymentHeadID(String chgPaymentHeadID)throws Exception,BusinessException;
 //�ύ��¼
  public void PickInChgPaymentTailMaintain(String id,String orgid,SecurityInfo securityInfo,String flag) throws Exception,BusinessException;
 //�����ύ��¼
  public void removePickInChgPaymentTailMaintain(String id,String orgid,SecurityInfo securityInfo,String flag) throws Exception,BusinessException;
 //��ȡ����
  public void pickUpChgPaymentChgslarybase(String orgid,String chgMonth,SecurityInfo securityInfo) throws Exception,BusinessException;
  //�����2008616
  public List chgslarybaseCellList(List chgslarybaseCellList,String orgid,String chgMonth,SecurityInfo securityInfo,Pagination pagination) throws Exception,BusinessException;
  public void setAa202_wsh(String pkid) throws Exception,
  BusinessException;
}
