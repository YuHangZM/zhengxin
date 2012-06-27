package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

import java.math.BigDecimal;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaPrintDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTaAF;

/**
 * @author ��Ұ 2007-10-02
 */
public class BailenRolTaSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    BailenRolTaPrintDTO bailenRolTaPrintDTO = new BailenRolTaPrintDTO();
    BailenRolTaDTO bailenRolTaDTO = new BailenRolTaDTO();
    BailenRolTaAF bailenRolTaAF = (BailenRolTaAF) form;
    IBailenRolBS bailenRolBS = (IBailenRolBS) BSUtils.getBusinessService(
        "bailenRolBS", this, mapping.getModuleConfig());
    try {
      messages = new ActionMessages();
      if (!isTokenValid(request)) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "�벻Ҫ�ظ��ύ��", false));
        saveErrors(request, messages);
      } else {
        resetToken(request);
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        String contractId = bailenRolTaAF.getContractId().trim();
        String borrowerName = bailenRolTaAF.getBorrowerName().trim();
        String cardKind = bailenRolTaAF.getCardKind().trim();
        String cardNum = bailenRolTaAF.getCardNum().trim();
        String loanBankName = bailenRolTaAF.getLoanBankName().trim();// ��ҳ���ȡ���տ�����ID
        String loanBankId = bailenRolTaAF.getLoanBankId().trim();// ��ҳ���ȡ���տ�����ID 2007-11-12�޸�
        String loanKouAcc = bailenRolTaAF.getLoanKouAcc().trim();
        String loanKouAccHidden = bailenRolTaAF.getLoanKouAccHidden().trim();// ��ҳ���������ȡ�Ĵ����˺� 2007-11-12�޸�
        String bizDate = bailenRolTaAF.getBizDate().trim();
        String occurMoney = bailenRolTaAF.getOccurMoney().toString();// ��֤����
        // form to dto
        bailenRolTaDTO.setContractId(contractId);
        bailenRolTaDTO.setBorrowerName(borrowerName);
        bailenRolTaDTO.setCardKind(cardKind);
        bailenRolTaDTO.setCardNum(cardNum);
        bailenRolTaDTO.setLoanBankName(loanBankName);
        bailenRolTaDTO.setLoanBankId(loanBankId);// 2007-11-12�޸�
        bailenRolTaDTO.setLoanKouAcc(loanKouAcc);
        bailenRolTaDTO.setLoanKouAccHidden(loanKouAccHidden);// 2007-11-12�޸�
        bailenRolTaDTO.setBizDate(bizDate);
        bailenRolTaDTO.setOccurMoney(new BigDecimal(occurMoney));
        // ��ӱ�֤��Ǽ���Ϣ��������PL202ͷ��ID
        bailenRolTaPrintDTO = bailenRolBS.saveBailenRol(bailenRolTaDTO, securityInfo);
        bailenRolTaAF.reset(mapping, request);
        request.setAttribute("printInfo", "print");
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }   
    request.getSession().setAttribute("bailenRolTaPrintDTO", bailenRolTaPrintDTO);
    return mapping.findForward("bailenRolTaShowAC");
  }
}
