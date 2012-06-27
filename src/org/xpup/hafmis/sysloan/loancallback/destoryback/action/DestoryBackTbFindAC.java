package org.xpup.hafmis.sysloan.loancallback.destoryback.action;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTbAF;

public class DestoryBackTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DestoryBackTbAF destoryBackTbAF = (DestoryBackTbAF) form;
    HashMap criterions = makeCriterionsMap(destoryBackTbAF);
    Pagination pagination = new Pagination(0, 10, 1,  "docnum", "DESC",
        criterions);
   
   
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("destoryBackTbShowAC");
  }

  protected HashMap makeCriterionsMap(DestoryBackTbAF form) {
    HashMap m = new HashMap();
  
    String docNum=form.getDocNum().trim();// ƾ֤���
    if (docNum != null && !"".equals(docNum)) {
      m.put("docNum", docNum);
    }
    
    String contractId=form.getContractId().trim();// ��ͬ���
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId);
    }
    
    String loanKouAcc=form.getLoanKouAcc().trim();// �����˺�
    if (loanKouAcc!= null && !"".equals(loanKouAcc)) {
      m.put("loanKouAcc", loanKouAcc);
    }
    
    String borrowerName = form.getBorrowerName().trim();//���������
    if (borrowerName != null && borrowerName.length() > 0) {
      m.put("borrowerName", borrowerName);
    }
    
    String bizSt=form.getBizSt().trim();// ҵ��״̬
    if (bizSt!= null && bizSt.length() > 0) {
      m.put("bizSt",bizSt);
    }
    
    String loanBankName = form.getLoanBankName().trim();// �ſ�����
    if (loanBankName != null && loanBankName.length() > 0) {
      m.put("loanBankName", loanBankName);
    }
    
    String cardNum = form.getCardNum().trim();//֤������
    if (cardNum != null && cardNum.length() > 0) {
      m.put("cardNum", cardNum);
    }

    return m;
  }

  protected String getPaginationKey() {
    return DestoryBackTbShowAC.PAGINATION_KEY;
  }
}