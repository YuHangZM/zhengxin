package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ͳ�Ʋ�ѯ -- �ɴ���ȡͳ�� -- ������ɴ�����±���
 * 
 * @author wangshuang 2009-04-16
 */
public class PaymntMonthReportForwardURLAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    request.getSession().setAttribute(PaymntMonthReportShowAC.PAGINATION_KEY, null);
    return mapping.findForward("paymntMonthReportShowAC");
  }
}
