package org.xpup.hafmis.sysfinance.treasurermng.bankdayclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BankDayClearTaForwardAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    //�ֽ��ռ��˺����д���ռ��˹���ͬһ������ҳ�桢ת��ҳ�桢ά��ҳ��  
    request.getSession().removeAttribute("office_gjp");
    request.getSession().removeAttribute("credenceDate_gjp");
    //"credenceType_gjp"Ϊ1�ͱ�ʾ���Ǵ����д���ռ���������ҳ���
    request.getSession().setAttribute("credenceType_gjp", "1");
    request.getSession().removeAttribute("cashDayClearTaAF");
    return mapping.findForward("cashdayclearta_show");
  }
}
