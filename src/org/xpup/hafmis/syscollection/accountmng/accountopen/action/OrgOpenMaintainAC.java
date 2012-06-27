package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.bsinterface.IOrgBaseInfoBS;
import org.xpup.hafmis.syscommon.domain.entity.PayBank;
import org.xpup.hafmis.syscommon.domain.entity.Transactor;
import org.xpup.security.common.domain.Userslogincollbank;

public class OrgOpenMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.update", "modify");
    map.put("button.delete", "delete");
    map.put("button.tijiaodating", "tijiaodating");
    map.put("button.emp.open", "empOpen");
    map.put("button.open.over", "openOver");
    map.put("button.orgAgent.export", "orgAgentExport");
    map.put("button.orgAgent.import", "orgAgentImport");
    map.put("button.pproval.data", "pprovalDataInfo");
    map.put("button.referring.data", "referringDataInfo");
    map.put("button.printshenpibiao", "printshenpibiao");
    return map;
  }

  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      String id = request.getParameter("id");
      request.getSession().setAttribute("hcodeid", id);
      Org org = orgOpenAccountBS.findOPA(new Integer(id));
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      if (org.getOrgInfo().getPayBank() == null) {
        org.getOrgInfo().setPayBank(new PayBank());
      }
      OrgkhAF af = new OrgkhAF();
      if (org.getOrgInfo().getTransactor() == null) {
        // yul add �޸ĵ�λ��Ϣʱ�޾����˱���.
        org.getOrgInfo().setTransactor(new Transactor());
      }
      af.setOrg(org);

      af.setType("2");
      Map industyMap = BusiTools.listBusiProperty(BusiConst.INDUSTRY);
      af.setIndustyMap(industyMap);
      Map orgpaywayMap = BusiTools.listBusiProperty(BusiConst.ORGPAYWAY);
      af.setOrgpaywayMap(orgpaywayMap);
      Map paymentaccuracyMap = BusiTools
          .listBusiProperty(BusiConst.PAYMENTACCURACY);
      af.setPaymentaccuracyMap(paymentaccuracyMap);
      Map inareaMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      af.setInareaMap(inareaMap);
      Map natureofunitsMap = BusiTools
          .listBusiProperty(BusiConst.NATUREOFUNITS);
      af.setNatureofunitsMap(natureofunitsMap);
      Map authoritiesMap = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      af.setAuthoritiesMap(authoritiesMap);
      // �õ����´���Ӧ������
      OfficeDto officeDtoTest = null;
      List collBankListTest = null;
      collBankListTest = orgOpenAccountBS.queryCollBank(af.getOrg()
          .getOrgInfo().getOfficecode());
      // �жϸ�Ȩ���µ�ĳ�����´���Ӧ�Ĺ鼯����
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        for (int i = 0; i < collBankListTest.size(); i++) {
          CollBank collBank = (CollBank) collBankListTest.get(i);
          if (userslogincollbank.getCollBankId().equals(
              collBank.getCollBankId())) {
            collBankList1.add(new org.apache.struts.util.LabelValueBean(
                userslogincollbank.getCollBankName().toString(),
                userslogincollbank.getCollBankId().toString()));
          }
        }
      }
      request.getSession(true).setAttribute("collBankList1", collBankList1);
      // ����λ״̬���ǿ�����ʱ���´���鼯���б����ò��ܱ��޸ġ�
      String orgOpenstatus = af.getOrg().getOrgInfo().getOpenstatus();
      if (!orgOpenstatus.equals("1")) {
        request.getSession().setAttribute("orgOS", orgOpenstatus);
        request.getSession().setAttribute("CollectionBank",
            af.getOrg().getOrgInfo().getCollectionBankId());
        request.getSession().setAttribute("Officecode",
            af.getOrg().getOrgInfo().getOfficecode());
        // ��λ״̬Ϊ������ʱ�ɴ淽ʽ��ɴ澫��Ҳ���ܽ����޸�
        request.getSession().setAttribute("payMode_FYF",
            af.getOrg().getPayMode());
        request.getSession().setAttribute("payPrecision_FYF",
            af.getOrg().getPayPrecision());
      }

      request.setAttribute("openstatus", af.getOrg().getOrgInfo()
          .getOpenstatus());
      request.setAttribute("orgkhAF", af);
      request.setAttribute("payMode", af.getOrg().getPayMode().toString());
      // �������������˵���״̬
      request.setAttribute("state", "1");
      request.setAttribute("orgid", id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("organization_open_detail");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String orgId = request.getParameter("id");
    ActionMessages messages = new ActionMessages();
    try {
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      // �жϸõ�λ���Ƿ���ְ��1 ��ְ����2 û��ְ��
      int returnValue = orgOpenAccountBS.removeOrg(orgId);
      if (returnValue == 1) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "�˵�λ��ְ�����Ƿ�ͬʱɾ����λ��ְ����", false));
        saveErrors(request, messages);
        // ��׼��ɾ���ĵ�λid����request.
        request.setAttribute("orgId", orgId);
        return mapping.findForward("organization_open_show");
      }
      if (returnValue == 2) {
        saveErrors(request, messages);
        // ��׼��ɾ���ĵ�λid����request.
        request.setAttribute("orgId", orgId);
        return mapping.findForward("orgInfo_remove");
      }
    } catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ɾ��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("organization_open_show");
  }

  public ActionForward empOpen(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String orgId = request.getParameter("id");

      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      // request.getSession().setAttribute("orgId",id);
      Org org = orgOpenAccountBS.findOPA(new Integer(orgId));
      request.getSession().setAttribute("org.id", "0" + org.getId());
      request.getSession().setAttribute("org.name", org.getOrgInfo().getName());
      request.getSession()
          .setAttribute("org.emppaymonth", org.getEmpPayMonth());
      // ���ɴ����ͷ���session
      request.getSession().setAttribute("org.paymode", org.getPayMode());
      // ����λ״̬����session
      request.getSession().setAttribute("openstatus",
          org.getOrgInfo().getOpenstatus());
      // ���ɴ淽ʽת��Ϊ���ִ���emp_open_new.jsp��
      request.getSession().setAttribute(
          "org.payModeStr",
          BusiTools.getBusiValue(org.getPayMode().intValue(),
              BusiConst.ORGPAYWAY));
      request.getSession().setAttribute("orgcount", org.getOrgCount());
      // if(org.getSalaryBaseCount()==null){
      // request.getSession().setAttribute("salarybasecount", new
      // BigDecimal(0.00));
      // }else{
      // request.getSession().setAttribute("salarybasecount",
      // org.getSalaryBaseCount());
      // }
      // if(org.getOrgpaySumCount()==null){
      // request.getSession().setAttribute("orgpaysumcount", new
      // BigDecimal(0.00));
      // }else{
      // request.getSession().setAttribute("orgpaysumcount",
      // org.getOrgpaySumCount());
      // }
      // if(org.getEmppaySumCount()==null){
      // request.getSession().setAttribute("orgemppaycount", new
      // BigDecimal(0.00));
      // }else{
      // request.getSession().setAttribute("orgemppaycount",
      // org.getEmppaySumCount());
      // }
      // if(org.getSumCount()==null){
      // request.getSession().setAttribute("orgsumcount", new BigDecimal(0.00));
      // }else{
      // request.getSession().setAttribute("orgsumcount", org.getSumCount());
      // }
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          EmpOpenShowAC.PAGINATION_KEY);
      if (pagination != null) {
        HttpSession session = request.getSession();
        session.removeAttribute(EmpOpenShowAC.PAGINATION_KEY);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("employee_kh_show");
  }

  public ActionForward openOver(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    String orgId = request.getParameter("id");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      if (orgOpenAccountBS.validateOpenStatus(orgId)) {
        orgOpenAccountBS.openOver(orgId, securityInfo);
      } else {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "�õ�λ�Ѿ��ǿ���״̬", false));
        saveErrors(request, messages);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("organization_open_show");
  }

  /**
   * ��λ���۵���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward orgAgentExport(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      List expList = orgOpenAccountBS.findOrgAgentList(securityInfo);
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�����ɹ���",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=orgagent_exp");
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("û�����ݣ�",
            false));
        saveErrors(request, messages);
      }
      return null;
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("����ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("organization_open_show");
  }

  /**
   * ��λ���۵���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward orgAgentImport(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("to_orgagent_imports");
  }

  /**
   * ��λ�泷���ύ����
   */
  public ActionForward pprovalDataInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String orgId = request.getParameter("id");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      orgOpenAccountBS.pprovalDataInfo(orgId, securityInfo);
      ActionMessages messagesinfo = new ActionMessages();
      messagesinfo.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "�����ύ���ݳɹ�", false));
      saveErrors(request, messagesinfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("organization_open_show");
  }

  /**
   * ��λ���ύ����
   */
  public ActionForward referringDataInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String orgId = request.getParameter("id");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      orgOpenAccountBS.referringDataInfo(orgId, securityInfo);
      ActionMessages messagesinfo = new ActionMessages();
      messagesinfo.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "�ύ���ݳɹ�", false));
      saveErrors(request, messagesinfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("organization_open_show");
  }
  public ActionForward tijiaodating(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String orgId = request.getParameter("id");
    ActionMessages messages = new ActionMessages();
    try {
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      orgOpenAccountBS.updateba001_tijiao(orgId);
    } catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("���µ�λ��Ϣ��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("organization_open_show");
  }
  public ActionForward printshenpibiao(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
  
    ActionMessages messages = new ActionMessages();
    try {
      IdAF idaf = (IdAF) form;
      String id = (String)idaf.getId() ;
      Integer orgId = new Integer(id);
      IOrgBaseInfoBS orgBaseInfoBS = (IOrgBaseInfoBS) BSUtils.getBusinessService(
          "orgBaseInfoBS", this, mapping.getModuleConfig());
      Org org = orgBaseInfoBS.findOrgInfoById(orgId);
      request.setAttribute("org", org);
    } catch (Exception e) {
      e.printStackTrace();
      saveErrors(request, messages);
    }
    return mapping.findForward("to_orgBaseInfoprint");
  }
}
