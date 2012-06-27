package org.xpup.hafmis.sysfinance.accmng.subjectmuster.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information : ƾ֤���� Project : �ļ���
 * 
 * @Version : 1.0
 * @author : ���� �������� : 11-06-2007
 */
public interface ISubjectmusterBS {

  // ���ƾ֤����������Ϣ
  public List findSubjectmusterInfo(String bookId, String credenceDateStart,
      String credenceDateEnd, String officeName, String credenceNumStart,
      String credenceNumEnd,String subjectLevel,SecurityInfo securityInfo) throws Exception;
  public String queryMaxCredenceNum(String office, String yearmonth, String bookid)
  throws Exception ;
  //������׿�Ŀ����
  public String querySubjectbalanceParamValue(String bookId) throws Exception;
  public String getNamePara() throws Exception ;
}
