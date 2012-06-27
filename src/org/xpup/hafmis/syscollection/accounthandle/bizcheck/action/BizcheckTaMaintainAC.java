package org.xpup.hafmis.syscollection.accounthandle.bizcheck.action;

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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface.IBizcheckBS;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.dto.BizcheckDTO;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckAF;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;

/**
 * @author ����� 2007-6-1
 */
public class BizcheckTaMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.check.through", "checkthrough");
    map.put("button.checkall", "checkall");
    map.put("button.del.check", "delcheck");
    map.put("button.del.checkall", "delcheckall");

    return map;
  }

  // /������ҵ�񸴺ˡ�����ͨ��
  public ActionForward checkthrough(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idaf = (IdAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();

      String id = idaf.getId().toString();
      request.getSession().setAttribute("id", id.toString());
      IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils.getBusinessService(
          "bizcheckBS", this, mapping.getModuleConfig());
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      orgHAFAccountFlow = bizcheckBS
          .findOrgHAFAccountFlowByID_(new Integer(id));
      if(orgHAFAccountFlow.getBiz_Type().equals("D")){
        String res = bizcheckBS.queryAA306ReserveaA(orgHAFAccountFlow.getDocNum(), orgHAFAccountFlow.getNoteNum());
        String resb = bizcheckBS.queryAA306ReserveaB(orgHAFAccountFlow.getDocNum(), orgHAFAccountFlow.getNoteNum());
        if(res.equals("1")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "��ȡҵ��δ���ͨ�����������ˣ�", false));
          saveErrors(request, messages);
          return mapping.findForward("to_bizcheckTaShowAC.do");
        }
        if(resb.equals("1")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "��ȡҵ��δ����ͨ�����������ˣ�", false));
          saveErrors(request, messages);
          return mapping.findForward("to_bizcheckTaShowAC.do");
        }
      }
      String bizStatus = orgHAFAccountFlow.getBizStatus().toString();
      String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();
      if (bizStatus.equals("4")) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "�ñ�ҵ���Ѿ����й����˲���,���ʵ��", false));
        saveErrors(request, messages);
        return mapping.findForward("to_bizcheckTaShowAC.do");
      } else {
        if (!bizStatus.equals("3")) {
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "�ñ�ҵ��״̬����ȷ��״̬,�޷����и���,���ʵ��", false));
          saveErrors(request, messages);
          request.getSession().setAttribute("checkOver", "0");
        } else if (securityInfo.getUserName().equals(userName)) {
          String moneyBank = "";
          if (request.getSession().getAttribute("moneyType") != null) {
            moneyBank = (String) request.getSession().getAttribute("moneyType");
          } else {
            moneyBank = null;
          }
          request.getSession().setAttribute("checkOver", "1");
          request.getSession().setAttribute("id", id.toString());
          String orgId = "";
          orgId = bizcheckBS.queryOrgId(id);
          String isModify = "";
          isModify = bizcheckBS.queruIsBankModify(orgId);
          request.getSession().setAttribute("isModify", isModify);
          request.getSession().setAttribute("orgId", orgId);
        } else {
          String moneyBank = "";
          if (request.getSession().getAttribute("moneyType") != null) {
            moneyBank = (String) request.getSession().getAttribute("moneyType");
          } else {
            moneyBank = null;
          }
          request.getSession().setAttribute("checkOver", "1");
          request.getSession().setAttribute("id", id.toString());
          String orgId = "";
          orgId = bizcheckBS.queryOrgId(id);
          String isModify = "";
          isModify = bizcheckBS.queruIsBankModify(orgId);
          request.getSession().setAttribute("isModify", isModify);
          request.getSession().setAttribute("orgId", orgId);
        }
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("����ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("to_bizcheckTaShowAC.do");
  }

  // ������ҵ�񸴺ˡ���������
  public ActionForward checkall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String bizStatus = "";
      String userName = "";
      BizcheckAF bizcheckAF = null;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      pagination.getQueryCriterions().put("ip", ip);
      pagination.getQueryCriterions().put("name", name);
      // ����������ʼ
      String moneyBank = "";
      if (request.getSession().getAttribute("moneyType") != null) {
        moneyBank = (String) request.getSession().getAttribute("moneyType");
      } else {
        moneyBank = null;
      }
      // ����
      IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils.getBusinessService(
          "bizcheckBS", this, mapping.getModuleConfig());
      bizcheckAF = bizcheckBS.findOrgHAFAccountFlowListBydefault(pagination);
      List list = bizcheckAF.getBizchectotlallist();
      // List list = (List)
      // pagination.getQueryCriterions().get("bizchectotlallist");
      for (int i = 0; i < list.size(); i++) {

        BizcheckDTO bizcheckDTO = (BizcheckDTO) list.get(i);
         if(bizcheckDTO.getBizType().equals("D")){
           OrgHAFAccountFlow orgHAFAccountFlow = bizcheckBS
           .findOrgHAFAccountFlowByID_(new Integer(bizcheckDTO.getId()));
           String res = bizcheckBS.queryAA306ReserveaA(orgHAFAccountFlow.getDocNum(), orgHAFAccountFlow.getNoteNum());
           String resb = bizcheckBS.queryAA306ReserveaB(orgHAFAccountFlow.getDocNum(), orgHAFAccountFlow.getNoteNum());
           if(res.equals("1")){
             messages = new ActionMessages();
             messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                 "����ȡҵ��δ���ͨ�����������ˣ�", false));
             saveErrors(request, messages);
             return mapping.findForward("to_bizcheckTaShowAC.do");
           }
           if(resb.equals("1")){
             messages = new ActionMessages();
             messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                 "����ȡҵ��δ����ͨ�����������ˣ�", false));
             saveErrors(request, messages);
             return mapping.findForward("to_bizcheckTaShowAC.do");
           }
         }
        bizStatus = bizcheckDTO.getBizStatus().toString();
        userName = bizcheckDTO.getReserveaA().toLowerCase();
        // Ӫ��������ת��Ҫ��ת��������˺���ܸ��ˣ�
        // if("E".equals(bizcheckDTO.getBizType())&&!bizStatus.equals("4")&&!bizStatus.equals("5")){
        // String transInStatus="";
        // transInStatus=bizcheckBS.isTansInFive(bizcheckDTO.getId());
        // if(!"5".equals(transInStatus)){
        // messages = new ActionMessages();
        // messages.add(ActionMessages.GLOBAL_MESSAGE, new
        // ActionMessage("�ñ�ת��ҵ���Ӧ��ת��ҵ��δ���ˣ�",
        // false));
        // saveErrors(request, messages);
        // return mapping.findForward("to_bizcheckTaShowAC.do");
        // }
        // }
        // Ӫ��������ת��Ҫ��ת��������˺���ܸ��ˣ�
        if (bizStatus.equals("4")) {
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "�����Ѿ����й����˲�����ҵ��,���ʵ��", false));
          saveErrors(request, messages);
          return mapping.findForward("to_bizcheckTaShowAC.do");
        }
      }
      if (!bizStatus.equals("3")) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "���ڲ���ȷ��״̬��ҵ��,�޷����и���,���ʵ��", false));
        saveErrors(request, messages);
      } else if (securityInfo.getUserName().equals(userName)) {
        // �ж��Ƿ��ǵ�λ��
        if (false) {
          // messages = new ActionMessages();
          // messages.add(ActionMessages.GLOBAL_MESSAGE, new
          // ActionMessage("�Ƶ����븴���˲���Ϊͬһ��!",
          // false));
          // saveErrors(request, messages);
        } else {
          bizcheckBS.checkallOrgHAFAccountFlow(pagination, moneyBank);
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "����ͨ���ɹ���", false));
          saveErrors(request, messages);
        }
      } else {
        bizcheckBS.checkallOrgHAFAccountFlow(pagination, moneyBank);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "����ͨ���ɹ���", false));
        saveErrors(request, messages);
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("����ͨ��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("to_bizcheckTaShowAC.do");
  }

  // ������-ҵ�񸴺�-��������
  public ActionForward delcheck(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      IdAF idaf = (IdAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      String id = idaf.getId().toString();
      IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils.getBusinessService(
          "bizcheckBS", this, mapping.getModuleConfig());
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      orgHAFAccountFlow = bizcheckBS
          .findOrgHAFAccountFlowByID_(new Integer(id));
      String bizStatus = orgHAFAccountFlow.getBizStatus().toString();
      String userName = orgHAFAccountFlow.getReserveaA().toLowerCase();

      if (bizStatus.equals("3")) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "�ñ�ҵ���Ѿ����й��������˲���,���ʵ��", false));
        saveErrors(request, messages);
        return mapping.findForward("to_bizcheckTaShowAC.do");
      } else {
        if (!bizStatus.equals("4")) {
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "�ñ�ҵ��״̬���Ǹ���״̬,�޷����г�������,���ʵ��", false));
          saveErrors(request, messages);
        } else if (securityInfo.getUserName().equals(userName)) {
          // �ж��Ƿ��ǵ�λ��
          if (false) {
            // messages = new ActionMessages();
            // messages.add(ActionMessages.GLOBAL_MESSAGE, new
            // ActionMessage("�Ƶ����븴���˲���Ϊͬһ��!",
            // false));
            // saveErrors(request, messages);
          } else {
            // ����������ʼ
            String collectionBankId = "";
            String officeCode = "";
            collectionBankId = orgHAFAccountFlow.getOrg().getOrgInfo()
                .getCollectionBankId();
            officeCode = orgHAFAccountFlow.getOrg().getOrgInfo()
                .getOfficecode();
            // collectionBankId=bizcheckBS.queryOrgCollectionBankId(orgId);
            bizcheckBS.delcheckthroughOrgHAFAccountFlow(new Integer(id), ip,
                name, collectionBankId, officeCode);
            // ����
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "��������ͨ���ɹ���", false));
            saveErrors(request, messages);
          }
        } else {
          // ����������ʼ
          String collectionBankId = "";
          String officeCode = "";
          collectionBankId = orgHAFAccountFlow.getOrg().getOrgInfo()
              .getCollectionBankId();
          officeCode = orgHAFAccountFlow.getOrg().getOrgInfo().getOfficecode();
          collectionBankId = orgHAFAccountFlow.getOrg().getOrgInfo()
              .getCollectionBankId();
          // collectionBankId=bizcheckBS.queryOrgCollectionBankId(orgId);
          bizcheckBS.delcheckthroughOrgHAFAccountFlow(new Integer(id), ip,
              name, collectionBankId, officeCode);
          // ����
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "��������ͨ���ɹ���", false));
          saveErrors(request, messages);
        }
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "��������ͨ��ʧ�ܣ�", false));
      saveErrors(request, messages);
    }

    return mapping.findForward("to_bizcheckTaShowAC.do");
  }

  // ������ҵ�񸴺ˡ��������˳���
  public ActionForward delcheckall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String bizStatus = "";
    String userName = "";
    BizcheckAF bizcheckAF = null;
    try {

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String ip = securityInfo.getUserInfo().getUserIp();
      String name = securityInfo.getUserInfo().getUsername();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          PAGINATION_KEY);
      pagination.getQueryCriterions().put("ip", ip);
      pagination.getQueryCriterions().put("name", name);
      IBizcheckBS bizcheckBS = (IBizcheckBS) BSUtils.getBusinessService(
          "bizcheckBS", this, mapping.getModuleConfig());
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      bizcheckAF = bizcheckBS.findOrgHAFAccountFlowListBydefault(pagination);
      List list = bizcheckAF.getBizchectotlallist();
      // List list = (List)
      // pagination.getQueryCriterions().get("bizchectotlallist");
      for (int i = 0; i < list.size(); i++) {
        // orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
        BizcheckDTO bizcheckDTO = (BizcheckDTO) list.get(i);
        bizStatus = bizcheckDTO.getBizStatus().toString();
        userName = bizcheckDTO.getReserveaA().toLowerCase();
        if (bizStatus.equals("3")) {
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "�����Ѿ��������˵�ҵ��,���ʵ��", false));
          saveErrors(request, messages);
          return mapping.findForward("to_bizcheckTaShowAC.do");
        }
      }
      if (!bizStatus.equals("4")) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "���ڲ��Ǹ���״̬��ҵ��,�޷����г�������,���ʵ��", false));
        saveErrors(request, messages);

      } else if (name.equals(userName)) {
        // �ж��Ƿ��ǵ�λ��
        if (false) {
          // messages = new ActionMessages();
          // messages.add(ActionMessages.GLOBAL_MESSAGE, new
          // ActionMessage("�Ƶ����븴���˲���Ϊͬһ��!",
          // false));
          // saveErrors(request, messages);
        } else {
          bizcheckBS.delcheckallOrgHAFAccountFlow(pagination);
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "�������˳���ͨ���ɹ���", false));
          saveErrors(request, messages);
        }
      } else {
        bizcheckBS.delcheckallOrgHAFAccountFlow(pagination);
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "�������˳���ͨ���ɹ���", false));
        saveErrors(request, messages);
      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "�������˳���ͨ��ʧ�ܣ�", false));
      saveErrors(request, messages);
    }

    return mapping.findForward("to_bizcheckTaShowAC.do");
  }

  protected String getPaginationKey() {
    return BizcheckTaShowAC.PAGINATION_KEY;
  }
}
