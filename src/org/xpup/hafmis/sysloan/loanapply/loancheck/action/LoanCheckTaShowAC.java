package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;

/**
 * @author ��Ұ 2007-09-26
 */
public class LoanCheckTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    LoanapplyNewAF loanapplynewAF = new LoanapplyNewAF();
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
        "loanCheckBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String contractId = null;
    try {
      String contractid = (String) request.getSession().getAttribute(
          "contractIdWY");
      contractId = (String) request.getParameter("contractIdWY");
      if (contractId != null && !contractId.equals("")) {
        contractid = contractId;
        request.getSession().setAttribute("contractIdWY", contractId);
      }
      loanapplynewAF = loanapplyBS.showLoanapplyInfoBycontractid(contractid,
          securityInfo);
//    ��ú�ͬ�������ͼƬ·��
      List photoURLList = loanapplyBS.queryPhotoURLByContractID(contractid);
      request.getSession().setAttribute("photoURLList",photoURLList);
      // ����´�
      String office = loanCheckBS.changeOffice(loanapplynewAF.getBorrower()
          .getOffice().toString());
      loanapplynewAF.getBorrower().setOffice(office);
      String sex = BusiTools.getBusiValue(Integer.parseInt(loanapplynewAF
          .getBorrower().getSex()), BusiConst.SEX);
      loanapplynewAF.getBorrower().setSex(sex);
      String cardKind = BusiTools.getBusiValue(Integer.parseInt(loanapplynewAF
          .getBorrower().getCardKind()), BusiConst.DOCUMENTSSTATE);
      loanapplynewAF.getBorrower().setCardKind(cardKind);
      request.setAttribute("loanapplynewAF", loanapplynewAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_loancheckta_show");
  }
}