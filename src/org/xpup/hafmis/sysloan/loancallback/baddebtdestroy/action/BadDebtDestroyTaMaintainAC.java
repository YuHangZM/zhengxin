package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 


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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTaAF;

public class BadDebtDestroyTaMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.import", "imports");
    map.put("button.delete", "delete");
    map.put("button.sure", "sure");
    return map;
  }
  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    return mapping.findForward("baddebtdestroy_import");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    Pagination pagination=(Pagination)request.getSession().getAttribute(BadDebtDestroyTaShowAC.PAGINATION_KEY);
    try{
      messages=new ActionMessages();
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      String headId = (String)pagination.getQueryCriterions().get("headId");
      badDebtDestroyBS.deleteCallbackInfoByBank(headId, securityInfo);
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    pagination.getQueryCriterions().put("loanKouAcc",null);
    pagination.getQueryCriterions().put("headId",null);
    return mapping.findForward("badDebtDestroyTaShowAC");
  }
  public ActionForward sure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    BadDebtDestroyTaAF af = (BadDebtDestroyTaAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");//����ҳ����Ҫ��Ȩ�޵ģ���Ȩ����ȡ����������Ϊ������������Ϊ�����Դ������ư�ť���ԣ�Ҫ��action������״̬��
    try{
      messages=new ActionMessages();;
      Pagination pagination=(Pagination)request.getSession().getAttribute(BadDebtDestroyTaShowAC.PAGINATION_KEY);
      String loanKouAcc=(String)pagination.getQueryCriterions().get("loanKouAcc");
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      List list = (List)pagination.getQueryCriterions().get("shouldBackList");
      String report = request.getParameter("report");  
      String headId = (String)pagination.getQueryCriterions().get("headId");
      BadDebtDestroyTaAFDTO dto = new BadDebtDestroyTaAFDTO();
      dto.setShouldBackList(list);
      dto.setBorrowerInfoDTO(af.getBorrowerInfoDTO());
      dto.setOrgType(af.getOrgType());
      dto.getBorrowerInfoDTO().setLoanKouAcc(loanKouAcc);
      dto.setLoanassistantorgId(af.getLoanassistantorgId());
      //���������1.����Ϊ��2.����Ϊ��
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      //����Ȩ���еĻ��������ж�����Ϊ��
      if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER){
        //����Ϊ�� ������ˮ��
        headId = badDebtDestroyBS.addCallbackInfo(dto, securityInfo);
        pagination.getQueryCriterions().put("loanKouAcc",null);
      }else if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK){
        //����Ϊ�� ������ˮ��
        headId = badDebtDestroyBS.addCallbackInfoByLoanBankId(headId, af.getBorrowerInfoDTO().getContractId(), securityInfo);
        pagination.getQueryCriterions().put("headId",null);
      }
      if(report.equals("print")){  
        BadDebtDestroyTaAFDTO badDebtDestroyTaAFDTO = new BadDebtDestroyTaAFDTO();
        badDebtDestroyTaAFDTO = badDebtDestroyBS.findPrintCallbackInfo(headId);
        request.setAttribute("badDebtDestroyTaAFDTO", badDebtDestroyTaAFDTO);
        request.setAttribute("URL", "badDebtDestroyTaShowAC.do");
        return mapping.findForward("baddebtdestroy_cell");
      }
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    af.reset(mapping,request);
    return mapping.findForward("badDebtDestroyTaShowAC");
  }
}