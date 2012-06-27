package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

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
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * ͨ�������ʺŵ�����ajax Action
 * 
 * @author ���Ʒ�
 */
public class AdjustAccountLoanKouAccShowAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = "";
    /** ������Ϣ */
    String error = "";

    String resFlowHeadId = "";
    String bizType = "";
    String strBizType = "";
    String callbackMoney = "";
    String callbackInterest = "";
    String punishInterest = "";
    String borrowerName = "";
    String makePerson = "";
    String putOutMoney = "";

    try {

      String loanKouAcc = (String) request.getParameter("id");
      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustAccountBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      List temp_loanbankList = null;

      // ȡ���û�Ȩ�޷ſ�����
      List loanbankList = securityInfo.getDkUserBankList();
      loanbankList = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr = loanbankList.iterator();
      while (itr.hasNext()) {
        bank = (Userslogincollbank) itr.next();
        temp_loanbankList.add(bank.getCollBankId());
      }

      // �õ���Ҫ����ҵ�����Ϣ
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = adjustAccountBS
          .judgeLoanKouAcc(loanKouAcc, temp_loanbankList,securityInfo);

      resFlowHeadId = adjustAccountTaInfoDTO.getFlowHeadId();
      bizType = adjustAccountTaInfoDTO.getBizType();
      callbackMoney = adjustAccountTaInfoDTO.getCallbackMoney().setScale(2)
          .toString();
      putOutMoney = adjustAccountTaInfoDTO.getPutOutMoney().setScale(2)
          .toString();
      callbackInterest = adjustAccountTaInfoDTO.getCallbackInterest().setScale(
          2).toString();
      punishInterest = adjustAccountTaInfoDTO.getPunishInterest().setScale(2)
          .toString();
      borrowerName = adjustAccountTaInfoDTO.getBorrowerName();
      makePerson = adjustAccountTaInfoDTO.getMakePerson();
      strBizType = adjustAccountTaInfoDTO.getStrBizType();

    } catch (BusinessException bex) {
      error = bex.getLocalizedMessage();
    } catch (Exception e) {
      error = "��ѯʧ�ܣ�";
      e.printStackTrace();
    }

    text = "displaysIn('" + resFlowHeadId + "','" + bizType + "','"
        + strBizType + "','" + callbackMoney + "','" + putOutMoney + "','"
        + callbackInterest + "','" + punishInterest + "','" + borrowerName
        + "','" + makePerson + "','" + error + "')";
    response.getWriter().write(text);
    response.getWriter().close();

    return null;
  }
}
