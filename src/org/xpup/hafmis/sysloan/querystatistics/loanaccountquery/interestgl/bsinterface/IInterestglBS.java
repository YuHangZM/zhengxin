package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.form.InterestglTaAF;

public interface IInterestglBS {
  public String getMydate() throws Exception;
  // ��ȡ������˾��Ϣ��������ʾ������
  public List getAssistantOrgNameList() throws Exception;
  // ������ʽͳ�Ʋ�ѯ�б�
  public InterestglTaAF queryListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
//�����²�ѯ
  public InterestglTaAF queryListByMonth(String id,String date,Pagination pagination,
      SecurityInfo securityInfo,String radioValue,String floorname) throws Exception, BusinessException;
//�����ղ�ѯ
  public InterestglTaAF queryListByDay(String id,String date,Pagination pagination,
      SecurityInfo securityInfo,String radioValue,String floorname) throws Exception, BusinessException;
}
