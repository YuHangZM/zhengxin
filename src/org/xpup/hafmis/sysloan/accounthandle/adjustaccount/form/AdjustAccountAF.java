package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;

/**
 * ��װ�˰�����ʵ���ҵ��ı�
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountAF extends ActionForm {

  AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = new AdjustAccountTaInfoDTO();
  
  /**
   * �Զ����˵�Map
   */
  private Map autoOverPayMap = new HashMap();


  public Map getAutoOverPayMap() {
    return autoOverPayMap;
  }

  public void setAutoOverPayMap(Map autoOverPayMap) {
    this.autoOverPayMap = autoOverPayMap;
  }

  public AdjustAccountTaInfoDTO getAdjustAccountTaInfoDTO() {
    return adjustAccountTaInfoDTO;
  }

  public void setAdjustAccountTaInfoDTO(
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO) {
    this.adjustAccountTaInfoDTO = adjustAccountTaInfoDTO;
  }

}
