package org.xpup.hafmis.sysfinance.accmng.subjectbalance.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information : ��Ŀ���� Project : �ļ���
 * 
 * @Version : 1.0
 * @author : ���� �������� : 11-02-2007
 */
public interface ISubjectbalanceBS {
  // ������׿�Ŀ����
  public String querySubjectbalanceParamValue(String bookId) throws Exception;

  // �жϿ�Ŀ�����Ƿ���һ����Ŀ
  public boolean isSubjectCodeOneLevel(String bookId, String subjectCode)
      throws Exception;

  // ���ݿ�Ŀ����ѯ ��Ŀ����
  public String[] findSubjectCodesInfoBySubjectSortCode(String bookId,
      String subjectCodeStart, String subjectCodeEnd, String subjectLevel)
      throws Exception;

  // �������� ���ؿ�Ŀ������Ϣ
  public List findSubjectbalanceInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  public String queryMakerPara() throws Exception;

  public String queryCountCredenceNum(String bookId, String yearMonth);
}
