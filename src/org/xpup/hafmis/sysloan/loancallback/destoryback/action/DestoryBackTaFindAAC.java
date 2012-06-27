package org.xpup.hafmis.sysloan.loancallback.destoryback.action;

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
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTaAF;
import org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface.IDestoryBackBS;



public class DestoryBackTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    DestoryBackTaAF destoryBackTaAF = new DestoryBackTaAF();
    String text = "";
    String message = "";
    String contractId = "";// ��ͬ���
    String borrowerName = "";// ���������
    String cardKindName = "";// ��ʾ֤�����Ͷ�Ӧ������
    String cardNum = ""; // ֤������
    BigDecimal overplusLoanMoney=new BigDecimal(0.00);//ʣ�౾��
    String loanModeName="";//���ʽ
    BigDecimal noBackMoney=new BigDecimal(0.00);//����δ�ջؽ��
    try {
      String loanKouAcc = (String) request.getParameter("loanKouAcc");
      IDestoryBackBS destoryBackBS = (IDestoryBackBS) BSUtils.getBusinessService(
          "destoryBackBS", this, mapping.getModuleConfig());
      destoryBackTaAF = destoryBackBS.queryContractInfo(loanKouAcc, 
          securityInfo);   
      contractId = destoryBackTaAF.getContractId();// ��ͬ���
      borrowerName = destoryBackTaAF.getBorrowerName();// ���������
      cardKindName = destoryBackTaAF.getCardKindName();// ֤������
      cardNum  = destoryBackTaAF.getCardNum();// ֤������
      overplusLoanMoney  = destoryBackTaAF.getOverplusLoanMoney();//ʣ�౾��
      loanModeName = destoryBackTaAF.getLoanModeName();//���ʽ
      noBackMoney=destoryBackTaAF.getNoBackMoney();//����δ�ջؽ��  
    } catch (BusinessException bex) {
      message = bex.getMessage();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    text = "display('" + contractId + "','" + borrowerName + "','" + cardKindName
        + "','" + cardNum + "'," + "'" + overplusLoanMoney + "','" + loanModeName + "','" + noBackMoney
        + "'";
    text += ",'" + message + "');";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }
}
