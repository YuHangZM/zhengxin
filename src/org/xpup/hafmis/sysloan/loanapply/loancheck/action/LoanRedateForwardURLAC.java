/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanCheckForwardURLAC
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2007-10-25
 **/
package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoanRedateForwardURLAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      request.setAttribute("url", "redate");
      request.getSession().setAttribute(LoanCheckShowAC.PAGINATION_KEY, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loancheckShowAC");
  }
}
