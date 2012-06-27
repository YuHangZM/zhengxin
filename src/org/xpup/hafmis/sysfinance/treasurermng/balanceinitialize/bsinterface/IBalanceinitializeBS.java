package org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information   : ����ʼ
 * Project                  : �ļ���
 * @Version                 : 1.0
 * @author                  : ����
 * ��������                   : 10-19-2007
 */
public interface IBalanceinitializeBS {
  //��ÿ�Ŀ����Ϳ�Ŀ���� 
  public List getSubjectCodeName(String bookId,String officeName) throws Exception ;
  //����ʼ ͬһ���´��µ�SUBJECT_CODE�д�����ͬ��ֵ ����INT ����1����ͬ��ֵ
  public int getBalanceinitializeBT(String bookId,String officeName) throws Exception ;
  //�ж�FN210�����Ƿ����SUMMAY=3 and OFFICE=��ѡ���´��ļ�¼
  public int is_Balanceinitialize_ZL(String bookId,String officeName)throws Exception;
  //����FN311 ����FN210
  public void insertBalanceinitialize(SecurityInfo securityInfo,List list) throws Exception;
  //ɾ��FN210 ����FN311 ����FN210
  public void deleteBalanceinitialize(SecurityInfo securityInfo,List list) throws Exception;
}
