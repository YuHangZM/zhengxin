/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 11-02-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoancontractqueryForwardURLAC extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {


    request.getSession().setAttribute(LoancontractqueryShowAC.PAGINATION_KEY,null);
    request.getSession().setAttribute("url", "forward");
		return mapping.findForward("loancontractqueryshowAC");
	}
}