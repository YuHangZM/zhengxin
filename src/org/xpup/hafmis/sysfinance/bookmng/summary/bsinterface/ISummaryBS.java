package org.xpup.hafmis.sysfinance.bookmng.summary.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.summary.dto.SummaryDTO;

/**
 * Copy Right Information   : ����ժҪ
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-25-2007
 */
public interface ISummaryBS {
  //���ز�ѯ���(List) ����ժҪ��Ϣ
  public List findSummaryList(Pagination pagination, String bookId) throws Exception;
  //���ز�ѯ�����¼��
  public int querySummaryCount(String bookId) throws Exception;
  //�ж�����ĳ���ժҪ��FN102.PARAM_NUM=4�ļ�¼��PARAM_EXPLAIN�Ƿ����(���ڲ���)
  public boolean is_SummaryParamExplainInsert(SummaryDTO summaryDTO) throws Exception;
  //����FN311   ����FN102
  public void insertSummaryInfo(SummaryDTO summaryDTO,SecurityInfo securityInfo)throws Exception;
  //����ID�жϼ�¼�Ƿ����
  public boolean isSummaryById(String paraId) throws Exception;
  //�ж�����ĳ���ժҪ��FN102.PARAM_NUM=4�ļ�¼��PARAM_EXPLAIN�Ƿ����(�޸���)
  public boolean is_SummaryParamExplainUpdate(SummaryDTO summaryDTO) throws Exception ;
  public boolean querySummaryInFn201(String bookId,String  summaryId) throws Exception ;
  //����FN311   ����FN102
  public void updateSummaryInfo(SummaryDTO summaryDTO, SecurityInfo securityInfo) throws Exception;
  //����ID ��ѯ����ժҪ
  public SummaryDTO querySummaryParamExplainInfo(String paraId) throws Exception;
  //�жϸü�¼��FN102.PARA_ID��FN201.SUMMAY or FN210.SUMMAY���Ƿ����
  public boolean isSummaryByParamValue(String paraId,String bookId) throws Exception;
  //ɾ�� FN102   ����FN311��־
  public void deleteSummaryInfo(String paraId,SecurityInfo securityInfo) throws Exception;
}
