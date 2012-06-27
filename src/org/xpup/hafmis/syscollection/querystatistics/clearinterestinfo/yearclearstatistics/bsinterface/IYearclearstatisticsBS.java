package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;

public interface IYearclearstatisticsBS {
  // ��ʾ�ϼ���Ϣ
  public YearclearstatisticsHeadDTO findYearclearstatisticsListHead(List list) throws Exception, BusinessException;
  // ����������ѯ���ս�Ϣȫ���б�
  public List findYearclearstatisticsAllByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  // ��ʾְ���б�ϼ���Ϣ
  public YearclearstatisticsHeadDTO findYearclearEmpListHead(List list) throws Exception, BusinessException;
  // ����������ѯְ���б����ս�Ϣȫ���б�
  public List findYearclearEmpAllByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  // ����������ѯ����ְ���б����ս�Ϣ
  public List findYearclearEmpByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  public String findOrg(String orgId) throws Exception, BusinessException;
  public String clearperson(String bizId) throws Exception, BusinessException;
  public String queryMakerPara() throws Exception;
}
