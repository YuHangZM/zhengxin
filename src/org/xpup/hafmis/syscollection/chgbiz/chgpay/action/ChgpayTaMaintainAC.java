package org.xpup.hafmis.syscollection.chgbiz.chgpay.action;

import java.math.BigDecimal;
import java.util.HashMap;
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
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface.IChgpayBS;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayImportTaAF;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;

/**
 * @author ����� 2007-7-5
 */
public class ChgpayTaMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTaSouwAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "remove");
    map.put("button.deleteall", "removeAll");
    map.put("button.exports", "exports");
    map.put("button.imports", "imports");
    map.put("button.use", "use");
    map.put("button.referring.data", "referring");
    map.put("button.pproval.data", "pproval");
    map.put("button.pickup.data", "pickup");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    ChgpayNewAF chgpayNewAF = new ChgpayNewAF();
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    String pkid = request.getParameter("PKID");
    String orgid = request.getParameter("org.id");
    String chgMonth = request.getParameter("chgMonth2");
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    String date=securityInfo.getUserInfo().getBizDate();
    pagination.getQueryCriterions().put("pkid", pkid);
    pagination.getQueryCriterions().put("org.id", orgid);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("name", name);
    pagination.getQueryCriterions().put("date", date);
    
    pagination.getQueryCriterions().put("ip", ip);
    String orgRate = request.getParameter("orgRate");
    String empRate = request.getParameter("empRate");
    Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
    Map documentsstateMap = BusiTools
        .listBusiProperty(BusiConst.DOCUMENTSSTATE);
    chgpayNewAF.setDocumentsstateMap(documentsstateMap);
    chgpayNewAF.setSexMap(sexMap);
    chgpayNewAF.setType("1");
    chgpayNewAF.setOrgRate(orgRate);
    chgpayNewAF.setOrgId(orgid);
    chgpayNewAF.setEmpRate(empRate);
    request.setAttribute("chgpayNewAF", chgpayNewAF);
    saveToken(request);
    return mapping.findForward("to_chgpay_new.jsp");
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idaf = (IdAF) form;

    ChgpayNewAF chgpayNewAF = new ChgpayNewAF();
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    String orgid = request.getParameter("org.id");
    pagination.getQueryCriterions().put("org.id", orgid);
    String pkid = request.getParameter("PKID");
    String chgMonth = request.getParameter("chgMonth2");
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    pagination.getQueryCriterions().put("name", name);
    pagination.getQueryCriterions().put("pkid", pkid);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);
    String orgRate = request.getParameter("orgRate");
    String empRate = request.getParameter("empRate");

    IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
        mapping.getModuleConfig());
    ChgPaymentTail chgPaymentTail = chgpayBS.findChgPaymentTailWuht(idaf
        .getId().toString(), pagination);
    Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
    Map documentsstateMap = BusiTools
        .listBusiProperty(BusiConst.DOCUMENTSSTATE);
    chgpayNewAF.setSexMap(sexMap);
    chgpayNewAF.setDocumentsstateMap(documentsstateMap);
    chgpayNewAF.setType("2");
    chgpayNewAF.setChgPaymentTail(chgPaymentTail);
    chgpayNewAF.getChgPaymentTail().getEmp().setSalaryBase(chgPaymentTail.getSalaryBase());
    chgpayNewAF.setOrgRate(orgRate);
    chgpayNewAF.setEmpRate(empRate);
    request.setAttribute("chgpayNewAF", chgpayNewAF);
    return mapping.findForward("to_chgpay_new.jsp");
  }

  public ActionForward use(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    ActionMessages messages =null;
    try{
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        PAGINATION_KEY);
    String orgid = request.getParameter("org.id");
    pagination.getQueryCriterions().put("org.id", orgid);
    String pkid = request.getParameter("PKID");
    String chgMonth = request.getParameter("chgMonth2");
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    String ip=securityInfo.getUserInfo().getUserIp();
    String name=securityInfo.getUserInfo().getUsername();
    pagination.getQueryCriterions().put("pkid", pkid);
    pagination.getQueryCriterions().put("name", name);
    pagination.getQueryCriterions().put("chgMonth", chgMonth);
    pagination.getQueryCriterions().put("ip", ip);

    IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
        mapping.getModuleConfig());

    chgpayBS.useChgPaymentPayment(pagination);
    messages=new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("���óɹ���",
        false));
    saveErrors(request, messages);
    }catch(BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgpayTaSouwAC.do");
    }
    return mapping.findForward("chgpayTaSouwAC.do");
  }

  // ɾ��AA203���м�¼�������ǣ�id=ѡ�е�id������BA003������ҵ��=AA202�иñʱ����id
  // ip�� IP��ַ id��aa203��ID pkid:aa202��ID nrOfElements:��ʡ����203���������� orgid����λ���
  public ActionForward remove(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idaf = (IdAF) form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      String id = idaf.getId().toString();
      String pkid = request.getParameter("PKID");
      String orgid = request.getParameter("org.id");
      String nrOfElements = request.getParameter("nrOfElements");

      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      chgpayBS.deleteChgPaymentTail(new Integer(id), pkid, orgid, ip,
          nrOfElements,name,securityInfo); 
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ɾ���ɹ���",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("chgpayTaSouwAC.do");
  }

  public ActionForward removeAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idaf = (IdAF) form;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      String id = idaf.getId().toString();
      String pkid = request.getParameter("PKID");
      String orgid = request.getParameter("org.id");
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      chgpayBS.deleteAllChgPaymentTail(new Integer(id), pkid, orgid, ip,name,securityInfo);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ȫ��ɾ���ɹ���",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("chgpayTaSouwAC.do");
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      HttpSession session = request.getSession(false);
      Pagination pagination=new Pagination();
      
      String orgId=request.getParameter("org.id");
      pagination.getQueryCriterions().put("org.id", orgId);
      String orgName=request.getParameter("org.orgInfo.name");
      String chgMonth=request.getParameter("chgMonth2");
      pagination.getQueryCriterions().put("org.orgInfo.name", orgName);
      pagination.getQueryCriterions().put("chgMonth", chgMonth);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      pagination.getQueryCriterions().put("ip", ip);
      pagination.getQueryCriterions().put("name", name);
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      List expList = chgpayBS.queryEmpOrgList(pagination);
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�����ɹ���",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=chgpay_exp");
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("û�����ݣ�",
            false));
        saveErrors(request, messages);
      }
      return null;
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("chgpayTaSouwAC.do");
  }

  public ActionForward imports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String orgId=request.getParameter("org.id");
    String chgMonth=request.getParameter("chgMonth2");
   
    ChgpayImportTaAF chgpayImportTaAF = new ChgpayImportTaAF();
    chgpayImportTaAF.setOrgId(orgId);
    chgpayImportTaAF.setChgMonth(chgMonth);
    request.setAttribute("chgpayImportTaAF", chgpayImportTaAF);
    return mapping.findForward("to_chgpay_imports.jsp");
  }

  public ActionForward maintainReport(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      HttpSession session = request.getSession(false);
      Pagination pagination = (Pagination) session.getAttribute(PAGINATION_KEY);

      IDemoBS demoBS = (IDemoBS) BSUtils.getBusinessService("demoBS", this,
          mapping.getModuleConfig());
      List expList = demoBS.findDemoListAll(pagination);
      request.setAttribute("cellList", expList);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("��ʾʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_demo_report");
  }

  protected String getPaginationKey() {
    return ChgpayTaSouwAC.PAGINATION_KEY;
  }
  
  /**
   * ��ȡ����
   * @author ������
   * @2008-02-26
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward pickup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    ActionMessages messages =null;
   
    try{
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");     
      String orgid=request.getParameter("org.id");
      String chgMonth=request.getParameter("chgMonth2");
      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      chgpayBS.pickUp(orgid, chgMonth,securityInfo);
    }  catch(BusinessException e) {
       messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("chgpayTaSouwAC.do");
    }
   
    return mapping.findForward("chgpayTaSouwAC.do");
  }
  
  /**
   * ����ĳ����ύ
   * @author ������
   * @2007-02-27
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward pproval(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)throws Exception{
    
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String pkid = request.getParameter("PKID");
      String orgid = request.getParameter("org.id");

      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      //��c��˵���ǰ���ҳ��ĳ����ύ
      //pkidΪAA202.id
      chgpayBS.removePickInChgPaymentTailMaintain(pkid, orgid,securityInfo,"c1"); 
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�����ύ�ɹ���",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
            messages = new ActionMessages();
        if ((!bex.getMessage().equals("��ҵ���ѱ�������ȡ�������Գ�����")) && (!bex.getMessage().equals("�ñ�ҵ��û���ύ�������Գ�����"))){
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�����ύʧ�ܣ�",
            false));
            saveErrors(request, messages);
        }
        else{
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
            false));
            saveErrors(request, messages); 
        }
    }
    return mapping.findForward("chgpayTaSouwAC.do");   
  }
  
  /**
   * �����ύ
   * @author ������
   * @2008-02-26
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward referring(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)throws Exception{
    
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String pkid = request.getParameter("PKID");
      String orgid = request.getParameter("org.id");

      IChgpayBS chgpayBS = (IChgpayBS) BSUtils.getBusinessService("chgpayBS", this,
          mapping.getModuleConfig());
      //��t��˵���ǰ���ҳ����ύ
      //pkidΪAA202.id
      chgpayBS.PickInChgPaymentTailMaintain(pkid, orgid,securityInfo,"t1"); 
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�ύ�ɹ���",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
            messages = new ActionMessages();
        if (!bex.getMessage().equals("�ñ�ҵ�����ύ��")){
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�ύʧ�ܣ�",
            false));
            saveErrors(request, messages);
        }
        else{
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
            false));
            saveErrors(request, messages); 
        }
    }
    return mapping.findForward("chgpayTaSouwAC.do");   
  }

}
