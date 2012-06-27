package org.xpup.hafmis.sysloan.loanapply.giveacc.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface.IGiveaccBS;
import org.xpup.security.common.domain.Userslogincollbank;

public class GiveaccTaFindAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("Cache-Control", "no-cache");
      String contractId = "";
      if (!((String)request.getParameter("contractId") == null || "".equals((String)request.getParameter("contractId").trim()))) {
        contractId = (String)request.getParameter("contractId").trim();
      }
      request.getSession().setAttribute("contractId", contractId);
      String text = null;
      String houseType = "";
      List loanbankList1 = null;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      try {
        // ȡ���û�Ȩ�޷ſ�����,��ʾ�������˵���
        List loanbankList = securityInfo.getDkUserBankList();
        loanbankList1 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      IGiveaccBS giveaccBS = (IGiveaccBS) BSUtils.getBusinessService(
          "giveaccBS", this, mapping.getModuleConfig());
      giveaccBS.findGiveaccInfoExist(contractId.toString(),loanbankList1);
      // ��Ҫ���ú�ͬ�ʺ��µ�pl114��ס�����ͻ��
     
      houseType = giveaccBS.findHouseType(contractId);
      request.getSession().setAttribute("houseType", houseType);
      text = "displays('" + contractId + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (BusinessException e) {
      e.printStackTrace();
      String text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
