package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.bsinterface.IInterestglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.form.InterestglTaAF;
/**
 * 
 * @author yuqf
 *2007-11-01
 */
public class InterestglTbShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.action.InterestglTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    String id = request.getParameter("id");
    String date = request.getParameter("date");
    Pagination pagination1 = (Pagination)request.getSession().getAttribute(InterestglTaShowAC.PAGINATION_KEY);
    HashMap map = (HashMap) pagination1.getQueryCriterions();
    String radioValue = (String)map.get("radioValue");
    String floorname = (String)map.get("floorNum");
    String startDate = (String) map.get("startDate");//查询开始时间
    String endDate = (String) map.get("endDate");//查询结束时间
    if(date!=null){
      request.getSession().setAttribute("date", date);
    }else{
      date = (String)request.getSession().getAttribute("date");
    }
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    IInterestglBS interestglBS = (IInterestglBS) BSUtils.getBusinessService(
        "interestglBS", this, mapping.getModuleConfig());
    InterestglTaAF interestglTaAF = new InterestglTaAF();
    Pagination pagination2 = this.getPagination(PAGINATION_KEY, request);
    pagination2.getQueryCriterions().put("startDate", startDate);
    pagination2.getQueryCriterions().put("endDate", endDate);
    if(id != null && !"".equals(id)){
      pagination2.getQueryCriterions().put("id", id);
    }
    String tempId= (String)pagination2.getQueryCriterions().get("id");
    PaginationUtils.updatePagination(pagination2, request);
    
    interestglTaAF = interestglBS.queryListByMonth(tempId, date, pagination2, securityInfo,radioValue,floorname);
    request.getSession().setAttribute("theinterestglTaAFlist", interestglTaAF.getPrintlist());
    request.setAttribute("theinterestglTaAF", interestglTaAF);
//    request.getSession().setAttribute(PAGINATION_KEY, pagination2);
    return mapping.findForward("to_interestglTb_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination2 = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination2 == null) {
      HashMap m = new HashMap();
      pagination2 = new Pagination(0, 12, 1, null, "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination2);
    }
    return pagination2;
  }
}
