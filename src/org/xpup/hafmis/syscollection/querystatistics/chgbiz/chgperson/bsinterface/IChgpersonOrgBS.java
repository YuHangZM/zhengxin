package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonEmpHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.dto.ChgpersonOrgHeadDTO;
/**
 * 
 * @author ����
 *2007-6-27
 */
public interface IChgpersonOrgBS {
  //����������ѯ��Ա�����¼
  public List findChgpersonOrgListByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //����������ѯ��Ա�����¼��λ�ĺϼ�
  public ChgpersonOrgHeadDTO findChgpersonOrgHeadByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //��ӡ��λ��Ϣ
  public ChgHeadDTO queryChgpersonOrgListAll(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;

  //����������ѯ��Ա���ְ����¼
  public List findChgpersonEmpListByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //����ͷ��ID������ѯ��Ա���ְ����¼
  public List findChgpersonEmpListByChgpersonHeadID(Pagination pagination,String chgpersonHeadID,SecurityInfo securityInfo) throws Exception,BusinessException;
  //�����ѯ��Ա�����¼ְ���ĺϼ�
  public ChgpersonEmpHeadDTO findChgpersonEmpHeadByCriterions(List list) throws Exception,BusinessException;
  //��ӡְ����Ϣ
  public List queryChgpersonEmpListAll(Pagination pagination,String chgpersonHeadID,SecurityInfo securityInfo) throws Exception,BusinessException;
  // ����������ѯ��Ա�����¼�ϼ���
  public ChgPersonHeadFormule findChgpersonHeadByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception,BusinessException;
  public List getChgpersonQueryList(Pagination pagination) throws Exception, BusinessException;
}