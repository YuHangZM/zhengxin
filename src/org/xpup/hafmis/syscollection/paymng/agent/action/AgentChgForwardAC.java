package org.xpup.hafmis.syscollection.paymng.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Copy Right Information : ���۱��ForwardAction Goldsoft Project : AgentChgFindAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.19
 */
public class AgentChgForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    request.getSession().removeAttribute(AgentChgShowAC.PAGINATION_KEY);
    return mapping.findForward("to_agentChgShowAC");
  }

}
