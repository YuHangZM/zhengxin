package org.xpup.hafmis.sysfinance.treasurermng.bankdayclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action.CashDayClearTcShowAC;

public class BankDayClearTcForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    //ά��ҳ��ֱ���������ֽ��ռ����е�ά��ҳ�档
    request.getSession().removeAttribute(CashDayClearTcShowAC.PAGINATION_KEY);
    request.getSession().removeAttribute("countList");
    return mapping.findForward("cashdaycleartc_show");
  }
}
