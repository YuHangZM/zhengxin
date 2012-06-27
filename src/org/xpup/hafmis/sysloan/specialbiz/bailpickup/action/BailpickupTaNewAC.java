package org.xpup.hafmis.sysloan.specialbiz.bailpickup.action;

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
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.action.BailenRolTaShowAC;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.bsinterface.IBailpickupBS;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTaAF;

/**
 * 
 * @author yuqf
 * 2007-10-15
 *
 */
public class BailpickupTaNewAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    BailpickupTaDTO bailpickupTaDTO = new BailpickupTaDTO();
    BailpickupTaAF bailpickupTaAF = (BailpickupTaAF)form;
    
    String contractId = bailpickupTaAF.getContractId();//��ͬ���
    String bailBalance = bailpickupTaAF.getBailBalance();//��֤��
    String overplusLoanMoney = bailpickupTaAF.getOverplusLoanMoney();//�������
    String noBackMoney = bailpickupTaAF.getNoBackMoney();//����
    String ovaerLoanRepay = bailpickupTaAF.getOvaerLoanRepay();//���˽��
    String pickUpInterest = bailpickupTaAF.getPickUpInterest();//��ȡ��Ϣ
    String loanKouAcc = bailpickupTaAF.getLoanKouAcc();//�����˺�
    
    bailpickupTaDTO.setContractId(contractId);
    bailpickupTaDTO.setBailBalance(bailBalance);
    bailpickupTaDTO.setOverplusLoanMoney(overplusLoanMoney);
    bailpickupTaDTO.setNoBackMoney(noBackMoney);
    bailpickupTaDTO.setOvaerLoanRepay(ovaerLoanRepay);
    bailpickupTaDTO.setPickUpInterest(pickUpInterest);
    bailpickupTaDTO.setLoanKouAcc(loanKouAcc);
    
    IBailpickupBS bailpickupBS = (IBailpickupBS) BSUtils
    .getBusinessService("bailpickupBS", this, mapping
        .getModuleConfig());
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String report = request.getParameter("report");
    try{
      String docNum = bailpickupBS.save(bailpickupTaDTO, securityInfo);
      bailpickupTaAF.setDocNum(docNum);
      // ��ӡ��֤��Ǽ�ƾ֤
      if ("print".equals(report)) {
        String userName = securityInfo.getRealName();
        String plbizDate = securityInfo.getUserInfo().getPlbizDate();
        request.setAttribute("userName", userName);
        request.setAttribute("plbizDate", plbizDate);
        request.setAttribute("theprintbailpickupTaAF", bailpickupTaAF);
        bailpickupTaDTO = new BailpickupTaDTO();
        bailpickupTaDTO.setIsDisabled("0");// ȷ����ť����
        request.setAttribute("theBailpickupTaDTO", bailpickupTaDTO);
        request.setAttribute("URL", "bailpickupTaShowAC.do");
        return mapping.findForward("to_bailpickupTa_cell");
      }
    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    bailpickupTaDTO = new BailpickupTaDTO();
    bailpickupTaDTO.setIsDisabled("0");// ȷ����ť����
    request.setAttribute("theBailpickupTaDTO", bailpickupTaDTO);
    return mapping.findForward("to_bailpickupTa");
  }

}
