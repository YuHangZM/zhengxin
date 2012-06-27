package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

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
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTaAF;

/**
 * @author ��Ұ 2007-10-02
 */
public class BailenRolTaFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    BailenRolTaAF bailenRolTaAF = new BailenRolTaAF();
    String text = null;
    String message = "";
    String contractId = "";
    String borrowerName = "";
    String cardKind = "";
    String cardNum = "";
    String loanBankName = "";
    String loanKouAcc = "";
    String loanKouAccHidden = "";// 2007-11-12�޸�
    String bizDate = "";
    try {
      String id = (String) request.getParameter("contractId").trim();
      IBailenRolBS bailenRolBS = (IBailenRolBS) BSUtils.getBusinessService(
          "bailenRolBS", this, mapping.getModuleConfig());
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      bailenRolTaAF = bailenRolBS.queryContractInfo(id, pagination,
          securityInfo);
      contractId = bailenRolTaAF.getContractId();// ��ͬID
      borrowerName = bailenRolTaAF.getBorrowerName();// ���������
      cardKind = bailenRolTaAF.getCardKind();// ֤������
      cardNum = bailenRolTaAF.getCardNum();// ֤������
      loanBankName = bailenRolTaAF.getLoanBankName();// �տ�����ID
      loanKouAcc = bailenRolTaAF.getLoanKouAcc();// �տ������˺�PL002.LOAN_ACCί�д����˺�
      loanKouAccHidden = bailenRolTaAF.getLoanKouAccHidden();// ���������ʺ�
      // 2007-11-12�޸�
      bizDate = securityInfo.getUserInfo().getPlbizDate();// ��ȡ����������--��ҳ����ȡ����
    } catch (BusinessException bex) {
      message = bex.getMessage();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString()));
      saveErrors(request, messages);
    }
    text = "display('" + contractId + "','" + borrowerName + "','" + cardKind
        + "','" + cardNum + "','" + loanBankName + "','" + loanKouAcc + "','"
        + loanKouAccHidden + "','" + bizDate + "'";
    text += ",'" + message + "');";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }

  protected String getPaginationKey() {
    return BailenRolTaShowAC.PAGINATION_KEY;
  }
}
