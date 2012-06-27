package org.xpup.hafmis.sysfinance.bookmng.datainitialize.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information   : ��ʼ����
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-16-2007
 */
public interface IDatainitializeBS {
  //��ÿ�Ŀ����Ϳ�Ŀ����
  public List getDatainitialize(String bookId) throws Exception;
  //����ۼƽ跽���ۼƴ���
  public List getLendsMoney(String bookId,String officeName) throws Exception;
  //ɾ��FN201
  public void deleteSummaryOffice(List list,SecurityInfo securityInfo) throws Exception;
  // ����FN311
  public void insertSummaryOffice(List list,SecurityInfo securityInfo) throws Exception;
  //FN101 ����״̬ 0���ã�1Ϊ����
  public String getBookST(String bookId)throws Exception;
  //�ж�FN201���Ƿ����SUMMAY=1 and OFFICE=��ѡ���´��ļ�¼
  public List is_SummayOffice(final String bookId,final String officeName) throws Exception;
}
