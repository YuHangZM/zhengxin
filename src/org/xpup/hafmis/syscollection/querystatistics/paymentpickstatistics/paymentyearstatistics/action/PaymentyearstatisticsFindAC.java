package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.form.PaymentyearstatisticsAF;

/**
 * ͳ�Ʋ�ѯ -- �ɴ���ȡͳ�� -- ������ɴ�����걨��
 * @author yqf
 *  20080920
 *
 */
public class PaymentyearstatisticsFindAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    PaymentyearstatisticsAF paymentyearstatisticsAF = (PaymentyearstatisticsAF)form;
    HashMap criterions = makeCriterionsMap(paymentyearstatisticsAF);
    Pagination pagination = new Pagination(0, 10, 1, "null", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("to_paymentyearstatisticsShowAC");
  }

  private String getPaginationKey() {
    // TODO Auto-generated method stub
    return PaymentyearstatisticsShowAC.PAGINATION_KEY;
  }

  private HashMap makeCriterionsMap(PaymentyearstatisticsAF form) {
    // TODO Auto-generated method stub
    HashMap m = new HashMap();
    
    String officeCode = form.getOfficeCode();//���´�
    if(officeCode!=null && !"".equals(officeCode)){
      m.put("officeCode", officeCode.trim());
    }
    String bizDate = form.getBizDate();//ҵ�����ڣ���λ��
    if(bizDate!=null && !"".equals(bizDate)){
      m.put("bizDate", bizDate.trim());
    }
    m.put("key", "key");
    return m;
  }
}
