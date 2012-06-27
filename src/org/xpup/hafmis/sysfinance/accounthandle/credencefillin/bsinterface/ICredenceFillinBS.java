package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;

public interface ICredenceFillinBS {

  public AccountantCredence queryById(Serializable credenceId);
  /**
   * ��ѯƾ֤¼��Tb-�Զ�����ҳ����ʾ���б�
   * 
   * @param pagination
   * @param officeList ���´�List
   * @return �б������
   * @throws Exception
   */
  public Object[] findCredenceFillinTbList(Pagination pagination,
      List officeList, SecurityInfo securityInfo) throws Exception;
  public List getIncomeList(CredenceFillinTbFindDTO credenceFillinTbFindDTO) throws Exception ;
  public List getExpenseList(CredenceFillinTbFindDTO credenceFillinTbFindDTO) throws Exception ;
  /**
   * �жϺ��㷽ʽ
   * 
   * @return 0Ϊͬһ���㣬1��������
   * @throws Exception
   */
  public int findFnSettleType() throws Exception;

  /**
   * ��������ĺ�����ʾ��Ӧ��ժҪ�б�
   * 
   * @author ����
   * @throws Exception
   */
  public String findCredenceCharacterList(String search,
      SecurityInfo securityInfo) throws Exception;

  /**
   * ����ƾ֤�ķ���
   * 
   * @param settnum Ʊ�ݺ��Լ�ҵ��״̬
   * @param securityInfo
   * @throws Exception
   */
  public void saveCredenceInfo(String[] settNum, String credenceDate, String oldCredenceNum, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * ��ѯTc-�����ת�б�ķ���
   * 
   * @param pagination
   * @param officeList
   * @return
   * @throws Exception
   */
  public Object[] findCredenceFillinTcList(Pagination pagination,
      List officeList, String bookId) throws Exception;

  /**
   * �����ת�ķ���
   * 
   * @param settNum �б�����(������Ŀ���룬���´����跽���������)
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void saveSettleIncAndDecInfo(String[] SettleIncAndDecInfo,
      SecurityInfo securityInfo, String credenceDateStart, String credenceDateEnd) throws Exception, BusinessException;

  /**
   * ƾ֤ά����ɾ��ƾ֤�ķ���
   * 
   * @param credenceId FN201�������
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void delectCredenceInfo(String credenceId, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * ɾ������ƾ֤�ķ���
   * 
   * @param countList �б��е�ȫ������
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void delectAllCredenceInfo(List countList, SecurityInfo securityInfo)
      throws Exception, BusinessException;
  
  /**
   * ��ѯ�����б�ķ���
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findContinuumPrintList(Pagination pagination, String type,
      SecurityInfo securityInfo) throws Exception;

  /**
   * ƾ֤¼��ȡ������
   * 
   * @author ���� 2007-11-1 ��ѯfn201����ID�����ļ������� ��ѯ������officeCode��ѡ���´���bookId
   */
  public String getCredenceDate(String officeCode, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * ƾ֤¼��������������ж�FN201�����Ƿ���ڴ˽���ŵļ�¼
   * 
   * @author ���� 2007-11-5
   */
  public String queryIsExistSettNum(String settNum,String bookId) throws Exception,
      BusinessException;

  /**
   * ����ƾ֤����
   * 
   * @author ����
   * @throws Exception
   */
  public void insertCredenceFillinTa(
      CredenceFillinTaShowDTO credenceFillinTaShowDTO,
      SecurityInfo securityInfo, String listAllContent) throws Exception;

  /**
   * ������-ƾ֤¼��
   * 
   * @author ���� 2007-11-7 �޸�ƾ֤¼��
   */
  public void updateCredenceFillinTa(
      CredenceFillinTaShowDTO credenceFillinTaShowDTO,
      SecurityInfo securityInfo, String listAllContent) throws Exception;

  /**
   * ��������ĺ�����ʾ��Ӧ��ժҪ�б�
   * 
   * @author ����
   * @throws Exception
   */
  public String findSummayList(String search, SecurityInfo securityInfo)
      throws Exception;

  /**
   * �õ�ƾ֤���б�
   * 
   * @author ����
   * @throws Exception
   */
  public List findCredenceCharacterList(SecurityInfo securityInfo)
      throws Exception;

  /**
   * ȡ�ý��㷽ʽ�б�
   * 
   * @author ���� 2007-11-1 ��ѯfn102����paramExplain�ֶε����� ��ѯ������paramNum
   */
  public List findSettTypeList(SecurityInfo securityInfo) throws Exception;

  /**
   * �ж���ĩ����Ŀ����
   * 
   * @author ����
   * @throws Exception
   */
  public Object[] isSubjectCodeEnd(String subjectCode, SecurityInfo securityInfo)
  throws Exception, BusinessException;

  /**
   * ƾ֤¼�������ѡ���´�ȡ�ö�Ӧ��ƾ֤��
   * 
   * @author ���� 2007-11-1
   */
  public String getCredenceNum(String officeCode, String bizYearmonth,
      String credenceNumType, String bookId) throws Exception;

  /**
   * �ж����ݿ����Ƿ���ڴ�ժҪ
   * 
   * @author ���� 2007-11-9
   */
  public boolean isExistSummay(String summay,SecurityInfo securityInfo) throws Exception,
      BusinessException;
  /**
   * ȡ��ƾ֤¼��ժҪ�б�
   * 
   * @author ���� 2007-11-10 ��ѯfn102����paramExplain�ֶε�����
   */
  public List getSummayList(SecurityInfo securityInfo) throws Exception;
  /**
   * �õ�ƾ֤¼��Ļ�ƿ�Ŀ�����Ӧ�Ŀ�Ŀ������
   * 
   * @author ���� 2007-11-23
   */
  public Object[] getBalanceDir(String subjectcode, String office,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * ƾ֤¼���ж�����״̬
   * @author ����
   * 2007-11-26
   * ����bookid��ѯ����״̬
   */
  public String findBookSt(SecurityInfo securityInfo) throws Exception;
  /**
   * ��ѯƾ֤��Ϣ�ķ��������������޸�
   * 
   * @param docNum ƾ֤��
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public Object[] findAccountantCredence(String docNum,
      SecurityInfo securityInfom, String credenceDate, String office) throws Exception;
  /**
   * ��ѯɾ���к��б�ķ���
   * @param list
   * @return
   * @throws Exception
   */
  public Object[] findDelRowList(List list)throws Exception;
  //��ù鼯����
  public List getCollBank();
}
