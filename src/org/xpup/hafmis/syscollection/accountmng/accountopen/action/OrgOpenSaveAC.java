package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.bsinterface.IOrgBaseInfoBS;

/**
 * ��������
 * 
 * @author
 */
public class OrgOpenSaveAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "save");
    map.put("button.update", "modify");
    map.put("button.back", "back");
    return map;
  }

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OrgkhAF af = (OrgkhAF) form;
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�벻Ҫ�ظ��ύ��",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
            .getBusinessService("orgOpenAccountBS", this, mapping
                .getModuleConfig());
        Org org = new Org();
        org = af.getOrg();
        // �ж��ǵ�λ�滹�����İ�
        String isOrgorcenter = af.getIsOrgorcenter();
        String org_id = orgOpenAccountBS.saveOrgOpenAccount_yg(org,
            securityInfo, isOrgorcenter).toString();
        request.setAttribute("id", org_id);
        // Integer orgId = new Integer(org.getId().toString());
        // IOrgBaseInfoBS orgBaseInfoBS = (IOrgBaseInfoBS)
        // BSUtils.getBusinessService(
        // "orgBaseInfoBS", this, mapping.getModuleConfig());
        // Org org_1 = orgBaseInfoBS.findOrgInfoById(orgId);

      } catch (BusinessException bex) {
        bex.printStackTrace();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    /*
     * �ڽ��뵥λ����ǰ�����session�����е�OrgOpenShowListAC�е�pagination��
     * �Ӷ���֤ÿ���ɽڵ����ʱֻ����ʾ��״̬Ϊ�������С��ĵ�λ�б�
     */
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        OrgOpenShowListAC.PAGINATION_KEY);
    if (pagination != null) {
      HttpSession session = request.getSession();
      session.removeAttribute(OrgOpenShowListAC.PAGINATION_KEY);
    }
    return mapping.findForward("show_organizations");
  }

  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OrgkhAF af = (OrgkhAF) form;
    // �жϵ�λ״̬
    if (request.getSession().getAttribute("orgOS") != null) {
      if (!request.getSession().getAttribute("orgOS").equals("1")) {
        af.getOrg().getOrgInfo().setOfficecode(
            (String) request.getSession().getAttribute("Officecode"));
        af.getOrg().getOrgInfo().setCollectionBankId(
            (String) request.getSession().getAttribute("CollectionBank"));
        // af.getOrg().setPayMode((BigDecimal)request.getSession().getAttribute("payMode_FYF"));
        //System.out.println(request.getSession().getAttribute("payPrecision_FYF"));
//        af.getOrg().setPayPrecision(
//            (Integer) request.getSession().getAttribute("payPrecision_FYF"));
        af.getOrg().setPayPrecision(
           af.getOrg().getPayPrecision());
      }
      // ʹ��֮�����session,��ֹ�ڴ��޸�ʱȡ��������Ϣ��
      request.getSession().removeAttribute("orgOS");
      request.getSession().removeAttribute("Officecode");
      request.getSession().removeAttribute("CollectionBank");
    }

    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      af.getOrg().setId(
          (Serializable) request.getSession().getAttribute("hcodeid"));
      orgOpenAccountBS.modifyOpen(af.getOrg(), securityInfo);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("organization_open_show");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("organization_open_show");
  }

}
