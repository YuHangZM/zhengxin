package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.AssureModeAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.FloorPOPNewAF;

/**
 * @author ��Ұ 2007-10-11
 */
public interface IAssureModeBS {

  // ������ʽͳ�Ʋ�ѯ�б�
  public AssureModeAF queryAssureModeListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // ��ȡ������˾��Ϣ��������ʾ������
  public List getAssistantOrgNameList() throws Exception;

  // ���¥������
  public FloorPOPNewAF findFloorInfoList(Pagination pagination) throws Exception,
      BusinessException;
}
