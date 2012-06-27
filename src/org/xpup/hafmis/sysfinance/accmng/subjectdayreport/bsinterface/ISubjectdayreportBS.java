package org.xpup.hafmis.sysfinance.accmng.subjectdayreport.bsinterface;

import java.util.List;

import org.xpup.hafmis.sysfinance.accmng.subjectdayreport.dto.SubjectdayreportDTO;

/**
 * Copy Right Information   : ��Ŀ�ձ���
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-26-2007
 */
public interface ISubjectdayreportBS {
  // ���FN102���� ���Ŀ�Ŀ����
  public String querySubjectdayreportParamValue(String bookId) throws Exception;

  // �жϿ�Ŀ�����Ƿ���һ����Ŀ
  public boolean isSubjectCodeOneLevel(String bookId, String subjectCode)
      throws Exception;

  // �����������subject_code
  public String[] querySubjectdayreportSubjectCodes(String f110_bookId,
      String subjectCodeStart, String subjectCodeEnd, String subjectLevelEnd)
      throws Exception;

  // ���ݿ�Ŀ�����bookid ��ÿ�Ŀ����
  public String querySubjectNameBySubjectCode(String bookId, String subjectCode)
      throws Exception;

  // ����������� �������
  public String queryYesterdayRemainingSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // ����������� ���ս跽
  public String queryTodayDebitBySubjectCode(String subjectCode, String bookId,
      String credenceDate, String officeName) throws Exception;

  // ����������� ���մ���
  public String queryTodayCreditBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // ����������� �������
  public String queryTodayRemainingSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // ����������� �������
  public String queryDirectionBySubjectCode(String subjectCode, String bookId,
      String credenceDate, String officeName) throws Exception;

  // ����������� ���ս跽����
  public String queryTodayDebitSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // ����������� ���մ�������
  public String queryTodayCreditSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;
  
  //����������� һ������
  public SubjectdayreportDTO findSubjectdayreportInfo(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;
  
  //����������� ȫ������
  public List findSubjectdayreportAllInfo(String bookId, String credenceDate,
      String officeName, String subjectCodeStart, String subjectCodeEnd,
      String subjectLevelEnd) throws Exception;
}
