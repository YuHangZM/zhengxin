package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopFindDTO;

/**
 * ��װ�˴��ʵ���������Ĳ�ѯ�����Լ��б����ݵ�ActionForm
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountPopFindAF extends ActionForm {

  private AdjustAccountPopFindDTO adjustAccountPopFindDTO = new AdjustAccountPopFindDTO();
  
  private List list;
  
  private Map bizTypeMap = new HashMap();

  public AdjustAccountPopFindDTO getAdjustAccountPopFindDTO() {
    return adjustAccountPopFindDTO;
  }

  public void setAdjustAccountPopFindDTO(
      AdjustAccountPopFindDTO adjustAccountPopFindDTO) {
    this.adjustAccountPopFindDTO = adjustAccountPopFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }
  
}
