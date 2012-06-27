package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;

/**
 * Copy Right Information : �Զ�ת��MainTainAction Goldsoft Project :
 * CredenceFillinTbShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.19
 */
public class CredenceFillinTbMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.createcredence.all", "saveAllCredenceInfo");
    map.put("button.createcredence", "saveCredenceInfo");
    map.put("button.income", "income");
    map.put("button.expense", "expense");
    return map;
  }

  /**
   * ����ƾ֤�ŵķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveCredenceInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      // �õ�Ʊ�ݺ��Լ�ҵ��״̬ settNum��Ʊ�ݱ����ҵ��״̬�����','����
      String[] settNum = null;
      String rowArray = request.getParameter("rowArrayHid");
      if (rowArray == null) {
        throw new BusinessException("��ѡ��Ҫת�˵�ҵ��");
      } else {
        settNum = rowArray.split("-");
      }

      String date = request.getParameter("sett_date");
      String oldCredenceNum = request.getParameter("oldCreNum");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      // ����ƾ֤�ķ���
      credenceFillinBS.saveCredenceInfo(settNum, date, oldCredenceNum,
          securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ת��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("credenceFillinTbShowAC");
  }

  /**
   * ȫ������ƾ֤�ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveAllCredenceInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      // �õ�Ʊ�ݺ��Լ�ҵ��״̬ settNum��Ʊ�ݱ����ҵ��״̬�����','����
      String[] settNum = (String[]) request.getSession().getAttribute(
          "allSettNum");

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String date = request.getParameter("sett_date");
      String oldCredenceNum = request.getParameter("oldCreNum");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      // ����ƾ֤�ķ���
      credenceFillinBS.saveCredenceInfo(settNum, date, oldCredenceNum,
          securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ת��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinTbShowAC");
  }

  /**
   * ��ѯ����ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward income(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CredenceFillinTbShowAC.PAGINATION_KEY);
      CredenceFillinTbFindDTO credenceFillinTbFindDTO = (CredenceFillinTbFindDTO) pagination
          .getQueryCriterions().get("credenceFillinTbFindDTO");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      List list = credenceFillinBS.getIncomeList(credenceFillinTbFindDTO);
      request.setAttribute("main", "yes");// ����ShowAC �ж��Ƿ���MainTainAC��ȥ��
      request.setAttribute("list", list);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("��ѯ����ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinIncomeExpenseShowAC");
  }

  /**
   * ��ѯ֧���ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward expense(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CredenceFillinTbShowAC.PAGINATION_KEY);
      CredenceFillinTbFindDTO credenceFillinTbFindDTO = (CredenceFillinTbFindDTO) pagination
          .getQueryCriterions().get("credenceFillinTbFindDTO");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      List list = credenceFillinBS.getExpenseList(credenceFillinTbFindDTO);
      request.setAttribute("main", "yes");// ����ShowAC �ж��Ƿ���MainTainAC��ȥ��
      request.setAttribute("list", list);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("��ѯ֧��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinIncomeExpenseShowAC");
  }

}
