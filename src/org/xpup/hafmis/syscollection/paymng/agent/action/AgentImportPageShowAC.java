package org.xpup.hafmis.syscollection.paymng.agent.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.paymng.agent.bsinterface.IAgentBS;
import org.xpup.hafmis.syscollection.paymng.agent.form.AgentImpAF;

/**
 * Copy Right Information : ���۵�����ʾ����ҳAction Goldsoft Project :
 * AgentImportPageShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.12.17
 */
public class AgentImportPageShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse arg3) throws Exception {
    ActionMessages messages = null;
    try {
      AgentImpAF agentImpAF = (AgentImpAF) form;
      // �ж��Ƿ�ô��������Ƿ����δʹ�õļ�¼�������������ʾ�ü�¼�б�������뵼��ҳ
      IAgentBS agentBS = (IAgentBS) BSUtils.getBusinessService("agentBS", this,
          mapping.getModuleConfig());
      Object agentDetailId = agentBS.findAgentYearMonthCount(agentImpAF
          .getAgentYearMonth());
      if (agentDetailId != null) {
        request.setAttribute("agentDetailId", agentDetailId.toString());
        return mapping.findForward("to_agentImpShowAC");
      }
    } catch (BusinessException be) {
      be.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_agentimp_imports");
  }

}
