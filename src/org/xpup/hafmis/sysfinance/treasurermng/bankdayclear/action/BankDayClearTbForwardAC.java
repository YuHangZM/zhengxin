package org.xpup.hafmis.sysfinance.treasurermng.bankdayclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action.CashDayClearTbShowAC;

public class BankDayClearTbForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    //ת��ҳ��ֱ���������ֽ��ռ����е�ת��ҳ�档
    request.getSession().removeAttribute(CashDayClearTbShowAC.PAGINATION_KEY);
    request.getSession().removeAttribute("countList");
    return mapping.findForward("cashdaycleartb_show");
  }
}
