package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;


/**
 * 
 * @author yuqf
 *
 */
public class EndorsecontractTbFindAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
//  TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
     String text = null;
     String message = "";
     String contractId = "";//��ͬID
     String debitter = "";//��������� PL110 
     String pledgePerson = "";//��Ѻ������
     String office = "";//��ѺȨ�ˣ����������ģ�
     String pledgeContractId = "";//��Ѻ��ͬ���
     String assistantOrgName = "";//������˾����
     String pledgeMatterName = "";//��Ѻ������
     String paperNum = "";//����Ȩ֤���
     String paperName = "";//����Ȩ֤����
     String paperPersonName = "";//����Ȩ������
     String cardKind = "";//����Ȩ��֤������
     String carNum = "";//����Ȩ��֤������
     String tel = "";//����Ȩ�˹̶��绰
     String mobile = "";//����Ȩ���ƶ��绰
     String pledgeAddr = "";//��Ѻ���ַ
     String area = "";//�������
     String buyHouseContractId = "";//������ͬ���
     String pledgeValue = "";//��Ѻֵ
     String evaluateValue = "";//����ֵ
     try{
       String id = (String) request.getParameter("contractId");
       IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
           .getBusinessService("endorsecontractBS", this, mapping
               .getModuleConfig());
       String paginationKey = getPaginationKey();
       Pagination pagination = (Pagination) request.getSession().getAttribute(
           paginationKey);
       endorsecontractTbAF = endorsecontractBS.queryPledgeContractList(id, pagination, securityInfo, request);
       
       contractId = endorsecontractTbAF.getContractId();
       debitter = endorsecontractTbAF.getDebitter();
       pledgePerson = endorsecontractTbAF.getPledgePerson();
       office = endorsecontractTbAF.getOffice();
       pledgeContractId = endorsecontractTbAF.getPledgeContractId();
       assistantOrgName = endorsecontractTbAF.getAssistantOrgName();
       pledgeMatterName = endorsecontractTbAF.getPledgeMatterName();
       paperNum = endorsecontractTbAF.getPaperNum();
       paperName = endorsecontractTbAF.getPaperName();
       paperPersonName = endorsecontractTbAF.getPaperPersonName();
       cardKind = endorsecontractTbAF.getCardKind();
       carNum = endorsecontractTbAF.getCarNum();
       tel = endorsecontractTbAF.getTel();
       mobile = endorsecontractTbAF.getMobile();
       pledgeAddr = endorsecontractTbAF.getPledgeAddr();
       area = endorsecontractTbAF.getArea();
       buyHouseContractId = endorsecontractTbAF.getBuyHouseContractId();
       pledgeValue = endorsecontractTbAF.getPledgeValue();
       evaluateValue = endorsecontractTbAF.getEvaluateValue();
       request.getSession().setAttribute("contractId", contractId);
     }catch(BusinessException bex){
       message = bex.getMessage();
       messages = new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
           .getLocalizedMessage().toString()));
       saveErrors(request, messages);
     }
     text = "display('" + contractId + "','" + debitter +"','"+ pledgePerson +"','"+
     office +"','"+pledgeContractId+"','"+assistantOrgName+"','"+pledgeMatterName+"','"
     +paperNum+"','"+paperName+"','"+paperPersonName+"','"+cardKind+"','"+carNum+"','"+
     tel+"','"+mobile+"','"+pledgeAddr+"','"+"','"+area+"','"+buyHouseContractId+pledgeValue+"','"+evaluateValue+",";
     text += ",'" + message + "');";
     response.getWriter().write(text);
     response.getWriter().close();
    return null;
  }
  protected String getPaginationKey() {
    return EndorsecontractTbShowAC.PAGINATION_KEY;
  }
}
