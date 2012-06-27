/**
 * Copy Right Information   : Goldsoft 
 * Project                  : IFiveLevelQueryBS
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.form.FiveLevelQueryAF;

public interface IFiveLevelQueryBS {

  // �弶����ͳ�Ʋ�ѯ�б�
  public FiveLevelQueryAF queryFiveLevelQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // ��ȡ������˾��Ϣ��������ʾ������
  public List getAssistantOrgNameList() throws Exception;
}
