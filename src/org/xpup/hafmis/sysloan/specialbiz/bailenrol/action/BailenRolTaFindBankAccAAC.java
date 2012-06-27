package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;

public class BailenRolTaFindBankAccAAC extends Action {

  /**
   * ͨ������ID�����ſ������˺�(PL002.LOAN_ACCί�д����ʺ�)
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = null;
    String bankAcc = "";// �տ������˺�
    String loanAcc = null;
    try {
      String bankId = (String) request.getParameter("loanBankIdACC").trim();
      IBailenRolBS bailenRolBS = (IBailenRolBS) BSUtils.getBusinessService(
          "bailenRolBS", this, mapping.getModuleConfig());
      if (!"".equals(bankId)) {
        loanAcc = bailenRolBS.queryBailenRolTaBankAccByBankId(bankId);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (loanAcc != null && !"".equals(loanAcc)) {
      bankAcc = loanAcc;
    }
    text = "displayAcc('" + bankAcc + "')";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }
}