package org.xpup.hafmis.syscollection.chgbiz.autochangeparam.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.dto.AutoChangeParamDTO;

/**
 * Copy Right Information : �Զ�����������õ�ActionForm Goldsoft Project :
 * AutoChangeParamAF
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.3.17
 */
public class AutoChangeParamAF extends ActionForm {
  
  /**
   * ������Ϣ��DTO
   */
  private AutoChangeParamDTO autoChangeParamDTO = new AutoChangeParamDTO();

  public AutoChangeParamDTO getAutoChangeParamDTO() {
    return autoChangeParamDTO;
  }

  public void setAutoChangeParamDTO(AutoChangeParamDTO autoChangeParamDTO) {
    this.autoChangeParamDTO = autoChangeParamDTO;
  }
}
