package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principalgl.form.PrincipalglTaAF;

public interface IPrincipalglBS {
  public String getMydate() throws Exception;

  // ��ȡ������˾��Ϣ��������ʾ������
  public List getAssistantOrgNameList() throws Exception;

  // ������ʽͳ�Ʋ�ѯ�б�
  public PrincipalglTaAF queryListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // �����²�ѯ
  public PrincipalglTaAF queryListByMonth(String id, String date,
      Pagination pagination, SecurityInfo securityInfo, String radioValue,
      String floorname) throws Exception, BusinessException;

  // �����ղ�ѯ
  public PrincipalglTaAF queryListByDay(String id, String date,
      Pagination pagination, SecurityInfo securityInfo, String radioValue,
      String floorname) throws Exception, BusinessException;
}
