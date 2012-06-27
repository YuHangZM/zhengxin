package org.xpup.hafmis.syscollection.paymng.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Copy Right Information : ��λ�����ӵ�ForwardAction Goldsoft Project :
 * AgentOrgpopForwardAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentOrgpopForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      request.getSession().removeAttribute(AgentOrgpopShowAC.PAGINATION_KEY);
      request.getSession().removeAttribute("org_returnType");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentOrgpopShowAC");
  }

}
