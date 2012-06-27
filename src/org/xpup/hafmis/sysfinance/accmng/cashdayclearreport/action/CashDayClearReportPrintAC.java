package org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;

public class CashDayClearReportPrintAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    List list = null;
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CashDayClearReportShowAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      //��credenceType���ж����ֽ��ռ��˻������д���ռ���
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      list = cashDayClearBS.findCashDayClearTcPrintList(pagination,credenceType,securityInfo);
      //type��Ϊ�˵�ҳ��������ʶ��0Ϊ�ֽ��ռ��ˣ�1Ϊ���д���ռ���
      if(credenceType.equals("0")){
        request.setAttribute("type", "0");
      }
      if(credenceType.equals("1")){
        request.setAttribute("type", "1");
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    String url = "cashDayClearReportShowAC.do";
    request.setAttribute("printList", list);
    request.setAttribute("url", url);
    return mapping.findForward("to_cashdayclearreport_print");
  }
}
