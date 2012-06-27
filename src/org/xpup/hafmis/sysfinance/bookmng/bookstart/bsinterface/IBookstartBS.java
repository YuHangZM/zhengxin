package org.xpup.hafmis.sysfinance.bookmng.bookstart.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information   : ��������
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-20-2007
 */
public interface IBookstartBS {

  //�����������
  public String getBookName(String bookId) throws Exception;
  //�����������ʱ��
  public String getUseYearmonth(String bookId) throws Exception;
  //�����������ʱ�����
  public String getUserYear(String bookId) throws Exception;
  //�����������ʱ�����
  public String getUserMonth(String bookId) throws Exception;
  //������׿�Ŀ����
  public String getParamValue(String bookId) throws Exception;
  //������״���ṹ
  public String getParamExplain(String bookId) throws Exception;
  //��������״̬
  public String getBookST(String bookId)throws Exception;
  //�ж�FN201.DEBIT-FN201.CREDIT�Ƿ�=0
  public boolean isDebitCredit(String bookId)throws Exception;
  //����FN101.USE_PERSON=����Ա FN101.BOOK_ST=1
  public void updateBook(SecurityInfo securityInfo, String bookId)throws Exception;
  //�ж�FN201���Ƿ���ڳ�ʼ���
  public String queryCredMessByOfficeID(SecurityInfo securityInfo, String officeid)throws Exception,BusinessException;
}
