package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class CollFnComparisonMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.orgaccount", "queryOrgAccount");
    map.put("button.empinfo", "queryEmpInfo");
    return map;
  }
  
  public ActionForward queryOrgAccount(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    try {
      
//    �õ���λ����뵥λ����
      String str = request.getParameter("id");
      String[] strArray = str.split(",");
      String orgId = strArray[0];
      String orgName = strArray[1];
      // ����λ�������������session
      request.setAttribute("orgId_gjp", orgId);
      request.setAttribute("orgName_gjp", orgName);
      
      request.getSession().removeAttribute(CollFnComparisonOrgAccountShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonOrgAccountShowAC");
  }
  
  public ActionForward queryEmpInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    try {
      
      // �õ���λ����뵥λ����
      String str = request.getParameter("id");
      String[] strArray = str.split(",");
      String orgId = strArray[0];
      String orgName = strArray[1];
      // ����λ�������������session
      request.getSession().setAttribute("CollFnComparison_orgId", orgId);
      request.getSession().setAttribute("CollFnComparison_orgName", orgName);
      request.getSession().removeAttribute(CollFnComparisonEmpInfoShowAC.PAGINATION_KEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collFnComparisonEmpInfoShowAC");
  }
}
