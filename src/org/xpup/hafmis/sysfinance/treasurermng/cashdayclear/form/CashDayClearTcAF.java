package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
/**
 * �ͳ������ˡ����д���ռ��ˡ��˲������е��ֽ��ռ��ˡ����д���ռ��˹���
 * @author guojingping
 *
 */
public class CashDayClearTcAF extends ActionForm {
  private List list=new ArrayList();
  private CashDayClearTcFindDTO cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
  public CashDayClearTcFindDTO getCashDayClearTcFindDTO() {
    return cashDayClearTcFindDTO;
  }
  public void setCashDayClearTcFindDTO(CashDayClearTcFindDTO cashDayClearTcFindDTO) {
    this.cashDayClearTcFindDTO = cashDayClearTcFindDTO;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
}
