package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
/**
 * ͳ�Ʋ�ѯ -- �ɴ���ȡͳ�� -- ������ɴ�����걨��
 * @author yqf
 *  20080920
 *
 */
public class PaymentyearstatisticsPrintAC  extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action.PaymentyearstatisticsShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    PaginationUtils.updatePagination(pagination, request);
    String officeCode = (String) pagination.getQueryCriterions().get("officeCode");
    if ("4028810c120af23c01120b14ed840005".equals(officeCode)) {// �б���
      
      return mapping.findForward("to_paymentyearstatistics1_cell.jsp");
    }
    if ("402881651bde207d011bde2453110002".equals(officeCode)) {// ��ʯ��
      
      return mapping.findForward("to_paymentyearstatistics2_cell.jsp");
    }
    if ("402881651bde207d011bde24dcd20003".equals(officeCode)) {// ����
      
      return mapping.findForward("to_paymentyearstatistics3_cell.jsp");
    }
    if ("402881651bde207d011bde25f7990019".equals(officeCode)) {// ����Ȧ
      
      return mapping.findForward("to_paymentyearstatistics4_cell.jsp");
    }
    return null;
  }
}
