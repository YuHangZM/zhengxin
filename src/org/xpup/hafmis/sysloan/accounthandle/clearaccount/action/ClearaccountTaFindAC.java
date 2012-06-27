package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearaccountAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;

public class ClearaccountTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ClearaccountAF clearaccountAF = (ClearaccountAF) form;
    HashMap criterions = makeCriterionsMap(clearaccountAF);
    Pagination pagination = new Pagination(0, 10, 1, "docnum", "DESC",
        criterions);

    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("clearaccountTaShowAC");
  }

  protected HashMap makeCriterionsMap(ClearaccountAF form) {
    HashMap m = new HashMap();

    String docNum = form.getDocNum().trim();// ƾ֤���
    if (docNum != null && !"".equals(docNum)) {
      m.put("docNum", docNum);
    }

    String contractId = form.getContractId().trim();// ��ͬ���
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId);
    }

    String loanKouAcc = form.getLoanKouAcc().trim();// �����˺�
    if (loanKouAcc != null && !"".equals(loanKouAcc)) {
      m.put("loanKouAcc", loanKouAcc);
    }

    String borrowerName = form.getBorrowerName().trim();// ���������
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }

    String makePerson = form.getMakePerson().trim();// //�Ƶ���
    if (makePerson != null && makePerson.length() > 0) {
      m.put("makePerson", makePerson);
    }

    String bizType = form.getBizType().trim();// ҵ������
    if (bizType != null && bizType.length() > 0) {
      m.put("bizType", bizType);
    }

    String bizSt = form.getBizSt().trim();// ҵ��״̬
    if (bizSt != null && bizSt.length() > 0) {
      m.put("bizSt", bizSt);
    }

    String loanBankName = form.getLoanBankName().trim();// �ſ�����
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }
    String beginBizDate = form.getBeginBizDate().trim();// ��������
    if (beginBizDate != null && beginBizDate.length() > 0) {
      m.put("beginBizDate", beginBizDate);
    }
    String endBizDate = form.getEndBizDate().trim();// ��������;
    if (endBizDate != null && endBizDate.length() > 0) {
      m.put("endBizDate", endBizDate);
    }

    return m;
  }

  protected String getPaginationKey() {
    return "org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.ClearaccountTaShowAC";
  }
}
