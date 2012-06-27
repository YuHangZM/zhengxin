package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public class EndorsecontractTdTopMaintainAC extends LookupDispatchAction{

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.edit", "edit");
    return map;
  }
  /**
   * ��Ӱ�ť
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
    public ActionForward add(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      request.getSession().setAttribute("pl123Id", null);
      EndorsecontractTdAF endorsecontractTdAF = (EndorsecontractTdAF)form;
      EndorsecontractTdAF esAF = new EndorsecontractTdAF();
      String key = EndorsecontractTdShowAC.PAGINATION_KEY;
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          key);
      List list = (List)pagination.getQueryCriterions().get("list");
      String contractId = endorsecontractTdAF.getContractId();
      String debitter = endorsecontractTdAF.getDebitter();
      esAF.setContractId(contractId);
      esAF.setDebitter(debitter);
      esAF.setList(list);
      esAF.setIsReadOnly("0");
      // ֤������������,�Ա�������
      Map cardMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
      esAF.setCardMap(cardMap);
      esAF.setSexMap(sexMap);
      request.getSession().setAttribute("theEndorsecontractTdAF", esAF);
      return mapping.findForward("to_endorsecontractTd");
    }
    /**
     * ȷ����ť
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      ActionMessages messages = null;
      String pl123Id = (String)request.getSession().getAttribute("pl123Id");
      EndorsecontractTdAF endorsecontractTdAF = (EndorsecontractTdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
      .getBusinessService("endorsecontractBS", this, mapping
          .getModuleConfig());
      try{
      endorsecontractBS.sureTd(pl123Id, securityInfo, endorsecontractTdAF);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�����ɹ���",
          false));
      saveErrors(request, messages);
      }catch (BusinessException bex) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getMessage(), false));
        saveErrors(request, messages);
      }      
      return mapping.findForward("to_endorsecontractTdShowAC");
    }
}
