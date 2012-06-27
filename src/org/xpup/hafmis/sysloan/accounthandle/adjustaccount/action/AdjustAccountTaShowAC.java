package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountAF;

/**
 * ��ʾ������ʵ���ҳ��Action
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    AdjustAccountAF adjustAccountAF = new AdjustAccountAF();
    adjustAccountAF.getAdjustAccountTaInfoDTO().setAutoOverPay("0");
    int plLoanReturnType = 0;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      // ���������1.����Ϊ��2.����Ϊ��
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      // ����Ȩ���еĻ��������ж�����Ϊ��
      if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
        plLoanReturnType = 1;// ����Ϊ��
      } else if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
        plLoanReturnType = 2;// ����Ϊ��
      }
      Map autoOverPayMap = BusiTools.listBusiProperty(BusiConst.YesNo);
      adjustAccountAF.setAutoOverPayMap(autoOverPayMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("adjustAccountAF", adjustAccountAF);
    request.getSession().setAttribute("plLoanReturnType", new Integer(plLoanReturnType));
    return mapping.findForward("to_adjustaccountta_show");
  }

}
