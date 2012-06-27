package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CashDayClearTaForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    request.getSession().removeAttribute("cashDayClearTaAF");
    request.getSession().removeAttribute("office_gjp");
    request.getSession().removeAttribute("credenceDate_gjp");
    //�ֽ��ռ��˺����д���ռ��˹���ͬһ������ҳ�桢ת��ҳ�桢ά��ҳ��  
    //"credenceType_gjp"Ϊ0�ͱ�ʾ���Ǵ��ֽ��ռ���������ҳ���
    request.getSession().setAttribute("credenceType_gjp", "0");
    return mapping.findForward("cashdayclearta_show");
  }
}
