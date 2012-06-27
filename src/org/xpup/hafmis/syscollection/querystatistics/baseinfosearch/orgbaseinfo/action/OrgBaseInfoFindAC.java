package org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.action;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.form.OrgInfoSearchAF;

public class OrgBaseInfoFindAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      OrgInfoSearchAF orgInfoSearchAF = (OrgInfoSearchAF) form;
      request.getSession().removeAttribute(OrgBaseInfoShowAC.PAGINATION_KEY);
      //�ڵ��ѯ��ʱ����뱣�����Pagination����new�Ķ���...��Ȼ�������ѯ������ʱ�� ҳ��ᱣ����һ����ѯ������ҳ��
      Pagination pagination = getPagination(OrgBaseInfoShowAC.PAGINATION_KEY, request);
      pagination.getQueryCriterions().put("search", orgInfoSearchAF);
    }catch(Exception s){
      s.printStackTrace();
    }
    return new ActionForward("/orgBaseInfoShowAC.do");
  }
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a001.id","DESC",new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
