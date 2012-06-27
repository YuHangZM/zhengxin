package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface.IAdvancepayloanBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto.AdvancepayloanTaDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.form.RelieveContractTaAF;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.BusiConst;

public class AdvancepayloanTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    AdvancepayloanTaDTO advancepayloanTaDTO = null;
    String loanKouAcc = "";//�����ʺ�
    String contractId = "";//��ͬ���
    String borrowerName = "";//���������
    String cardKind = "";//֤������
    String cardNum = "";//֤������
    String corpusInterest = "";//�»���Ϣ
    String overplusLoanMoney = "";//ʣ�౾�� OVERPLUS_LOAN_MONEY
    String creditType = "";//���ʽ
    String pre_term = "";//ԭ�������
    String text = "";
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try{
      String loadKouAcc=request.getParameter("loadKouAcc");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IAdvancepayloanBS advancepayloanBS = (IAdvancepayloanBS) BSUtils
      .getBusinessService("advancepayloanBS", this, mapping
          .getModuleConfig());
      advancepayloanTaDTO = advancepayloanBS.findAdvancepayloanTaDTO(loadKouAcc,securityInfo);
      if(advancepayloanTaDTO!=null){
         loanKouAcc = advancepayloanTaDTO.getLoanKouAcc();//�����ʺ�
         contractId = advancepayloanTaDTO.getContractId();//��ͬ���
         borrowerName = advancepayloanTaDTO.getBorrowerName();//���������
         cardKind = BusiTools.getBusiValue(Integer.parseInt(advancepayloanTaDTO.getCardKind()), BusiConst.DOCUMENTSSTATE) ;//֤������
         cardNum = advancepayloanTaDTO.getCardNum();//֤������
         corpusInterest = advancepayloanTaDTO.getCorpusInterest();//�»���Ϣ
         overplusLoanMoney = advancepayloanTaDTO.getOverplusLoanMoney();//ʣ�౾��
         creditType = BusiTools.getBusiValue(Integer.parseInt(advancepayloanTaDTO.getCreditType()), BusiConst.PLRECOVERTYPE);//���ʽ
         pre_term = advancepayloanTaDTO.getPre_term();//ԭ�������
         
         text="displays('"+loanKouAcc+"','"+contractId+"'" +
        ",'"+borrowerName+"','"+cardKind+"'" +
            ",'"+cardNum+"'" +
                ",'"+corpusInterest+"'" +
                    ",'"+overplusLoanMoney+"'" +
                        ",'"+creditType+"'" +
                            ",'"+pre_term+"')";
        response.getWriter().write(text);
        response.getWriter().close();
      }
     }catch (BusinessException bex) {
        System.err.println(bex.getLocalizedMessage().toString());
        text="reportErrors('"+bex.getLocalizedMessage()+"')";
        response.getWriter().write(text);
        response.getWriter().close();
      } catch(Exception e){
        e.printStackTrace();
      }
    return null;
  }
}
