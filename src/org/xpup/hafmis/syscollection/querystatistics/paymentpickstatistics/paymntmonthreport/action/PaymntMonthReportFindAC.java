package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.form.PaymntMonthReportFindAF;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form.PickMonthReportFindAF;

public class PaymntMonthReportFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PaymntMonthReportFindAF af = (PaymntMonthReportFindAF) form;
    HashMap criterions = makeCriterionsMap(af);
    Pagination pagination = new Pagination(0, 10, 1, "", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("paymntMonthReportShowAC");
  }

  private String getPaginationKey() {
    return PaymntMonthReportShowAC.PAGINATION_KEY;
  }

  private HashMap makeCriterionsMap(PaymntMonthReportFindAF form) {
    HashMap m = new HashMap();

    String officeCode = form.getOfficeCode();// ���´�
    if (officeCode != null && !"".equals(officeCode)) {
      m.put("office", officeCode.trim());
    }
    String bank = form.getCollBank();
    if (bank != null && !"".equals(bank)) {
      m.put("bank", bank.trim());
    }
    String year = form.getYear();
    if (year != null && !"".equals(year)) {
      m.put("year", year.trim());
    }
    String month = form.getMonth();
    if (month != null && !"".equals(month)) {
      m.put("month", month.trim());
    }
    return m;
  }
}
