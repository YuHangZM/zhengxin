package org.xpup.hafmis.syscollection.chgbiz.autochangeparam.bsinterface;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.dto.AutoChangeParamDTO;

/**
 * Copy Right Information : �Զ������������BS�Ľ�� Goldsoft Project :
 * AutoChangeParamSaveAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.3.17
 */
public interface IAutoChangeParamBS {

  /**
   * �Զ������������
   * @param autoChangeParamDTO
   * @param securityInfo
   * @throws Exception
   * @author ���Ʒ�
   */
  public void saveAutoChangeParam(AutoChangeParamDTO autoChangeParamDTO,
      SecurityInfo securityInfo) throws Exception;
  /**
   * ��ѯ�Զ��������ֵ
   * @return ����ֵ0��1
   * @throws Exception
   * @author ���Ʒ�
   */
  public String findAutoChangeParam() throws Exception;
}
