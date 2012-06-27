/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTeNewAF;

/**
 * MyEclipse Struts Creation date: 10-03-2007 XDoclet definition:
 * 
 * @struts.action path="/loanapplytefindAC" name="loanapplytenewAF"
 *                scope="request" validate="true"
 */
public class LoanapplyTeFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    LoanapplyTeNewAF loanapplytenewAF = (LoanapplyTeNewAF) form;

    HashMap criterions = makeCriterionsMap(loanapplytenewAF);
    Pagination pagination = new Pagination(0, 10, 1, "p4.op_time", "DESC",criterions);
    String paginationKey = LoanapplyTeShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);

    return mapping.findForward("loanapplyteshowAC");
  }

  protected HashMap makeCriterionsMap(LoanapplyTeNewAF loanapplytenewAF) {
    HashMap map = new HashMap();
    String contractId = loanapplytenewAF.getContractId().trim();
    if (!(contractId == null || "".equals(contractId.trim()))) {
      map.put("contractId", contractId);
    }
    String borrowerName = loanapplytenewAF.getBorrowerName().trim();
    if (!(borrowerName == null || "".equals(borrowerName.trim()))) {
      map.put("borrowerName", borrowerName);
    }
    String empId = loanapplytenewAF.getEmpId().trim();
    if (!(empId == null || "".equals(empId.trim()))) {
      map.put("empId", empId);
    }
    String cardNum = loanapplytenewAF.getCardNum().trim();
    if (!(cardNum == null || "".equals(cardNum.trim()))) {
      map.put("cardNum", cardNum);
    }
    String buyHouseType = loanapplytenewAF.getBuyHouseType();
    if (!(buyHouseType == null || "".equals(buyHouseType.trim()))) {
      map.put("buyHouseType", buyHouseType);
    }
    String contranct_st = loanapplytenewAF.getContranct_st();
    if (!(contranct_st == null || "".equals(contranct_st.trim()))) {
      map.put("contranct_st", contranct_st);
    }
   
    return map;
  }
}