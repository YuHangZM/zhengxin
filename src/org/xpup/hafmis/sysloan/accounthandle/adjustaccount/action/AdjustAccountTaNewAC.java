package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

/**
 * ���д��ʵ���ҵ���action
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountTaNewAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�벻Ҫ�ظ��ύ��",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        // �õ��Ƿ��ӡ��״̬
        String isPrint = request.getParameter("isPrint");
        // ����������������ڴ�ӡƾ֤ʱ��ʹ�ô˴����ʺ�
        String printLoanKouAcc = request.getParameter("loanKouAcc");

        AdjustAccountAF adjustAccountAF = (AdjustAccountAF) form;

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        String flowHeadId = adjustAccountAF.getAdjustAccountTaInfoDTO()
            .getFlowHeadId();
        String bizType = adjustAccountAF.getAdjustAccountTaInfoDTO()
            .getBizType();
        String autoOverPay = adjustAccountAF.getAdjustAccountTaInfoDTO()
            .getAutoOverPay();
        AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = adjustAccountAF
            .getAdjustAccountTaInfoDTO();

        IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
            .getBusinessService("adjustAccountBS", this, mapping
                .getModuleConfig());

        String headId = adjustAccountBS.saveAdjustAccountInfo(flowHeadId, "",
            bizType, autoOverPay, securityInfo, adjustAccountTaInfoDTO);

        // �ж��Ƿ��ӡƾ֤
        if (isPrint.equals("print")) {
          // �ɱ��õ���ӡ����
          LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF();
          loancallbackTaAF = adjustAccountBS.findPrintCallbackInfo(headId);
          String bizDate = "";
          String temp_bizDate = securityInfo.getUserInfo().getPlbizDate();
          bizDate = temp_bizDate.substring(0, 4)+"-"+temp_bizDate.substring(4, 6)+"-"+temp_bizDate.substring(6, 8);
          request.setAttribute("bizDate",bizDate);
          request.setAttribute("LoancallbackTaAF", loancallbackTaAF);
          if(bizType.equals("1")){
            // ���뷢��ƾ֤
            return mapping.findForward("to_adjustaccount_putout_print");
          }else{
            return mapping.findForward("to_adjustaccount_print");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "ҵ�����ʧ�ܣ�", false));
        saveErrors(request, messages);
      }
    }
    return mapping.findForward("adjustAccountTaShowAC");
  }

}
