package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.form.SubjectrelationTaPop2AF;

public class SubjectRelationTaPop2AC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action.SubjectRelationTaPop2AC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      Pagination pagination = getPagination(
          SubjectRelationTaPop2AC.PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      List list = new ArrayList();
      List countList = new ArrayList();
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
//      list = subjectrelationBS.querySubejectRelationTaPop2List(pagination, securityInfo);
      list = subjectrelationBS.querySubejectRelationTaPop2List(pagination, securityInfo);
      countList = subjectrelationBS.querySubejectRelationTaPop2CountList(securityInfo);
      pagination.setNrOfElements(countList.size());
      SubjectrelationTaPop2AF subjectrelaionTaPop2AF = new SubjectrelationTaPop2AF();
      subjectrelaionTaPop2AF.setList(list);
      request.setAttribute("subjectrelaionTaPop2AF", subjectrelaionTaPop2AF);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    request.setAttribute("count", "1");
    String count1=(String)request.getAttribute("count1");
    if("1".equals(count1)){
      request.setAttribute("count", "2");
    }
    return mapping.findForward("to_pop_subjectrelationta");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      pagination = new Pagination(0, 10, 1, "", "", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
