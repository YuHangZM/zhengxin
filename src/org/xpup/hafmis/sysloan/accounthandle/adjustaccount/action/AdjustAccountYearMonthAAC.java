package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;

/**
 * �õ����ʵ���ʱ��������Ϣ�ķ���
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountYearMonthAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = "";
    /** ������Ϣ */
    String error = "";
    String corpus = "";
    String interest = "";
    String punishInterest = "";

    try {

      String yearMonth = request.getParameter("yearMonth");
      String loanKouAcc = request.getParameter("loanKouAcc");
      
      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
      .getBusinessService("adjustAccountBS", this, mapping
          .getModuleConfig());
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      int plLoanReturnType = 0;
      // ���������1.����Ϊ��2.����Ϊ��
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      // ����Ȩ���еĻ��������ж�����Ϊ��
      if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
        plLoanReturnType = 1;// ����Ϊ��
      } else if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
        plLoanReturnType = 2;// ����Ϊ��
      }
      
      Object[] obj = adjustAccountBS.findCorpusAndInterest(yearMonth, loanKouAcc, plLoanReturnType);
      if (obj[0]!=null) {
        corpus=obj[0].toString();
      }
      if(obj[1]!=null){
        interest=obj[1].toString();
      }
      if(obj[2]!=null){
        punishInterest=obj[2].toString();
      }
    } catch (BusinessException bex) {
      error = bex.getLocalizedMessage();
    } catch (Exception e) {
      e.printStackTrace();
    }
    text = "displaysYear('" + error + "','" + corpus + "','" + interest + "','" + punishInterest + "')";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }

}
