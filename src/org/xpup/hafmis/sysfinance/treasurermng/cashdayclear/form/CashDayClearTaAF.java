package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form;



import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTaDTO;
/**
 * �����д���ռ��˹���
 * @author guojingping
 *
 */
public class CashDayClearTaAF extends ActionForm{
  private CashDayClearTaDTO cashDayClearTaDTO=new CashDayClearTaDTO();
  /**
   * �����ж������Ƿ�����
   */
  private String bookSt="";
  private String type="";
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public CashDayClearTaDTO getCashDayClearTaDTO() {
    return cashDayClearTaDTO;
  }

  public void setCashDayClearTaDTO(CashDayClearTaDTO cashDayClearTaDTO) {
    this.cashDayClearTaDTO = cashDayClearTaDTO;
  }

  public String getBookSt() {
    return bookSt;
  }

  public void setBookSt(String bookSt) {
    this.bookSt = bookSt;
  }
}
