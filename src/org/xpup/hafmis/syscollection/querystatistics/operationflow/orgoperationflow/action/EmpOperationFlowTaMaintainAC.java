package org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action;

import java.math.BigDecimal;
import java.util.Date;
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
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonaddpayTbWindowShowAC;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.ApplyBookDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.NameAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupGetCompanyIdAF;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.bsinterface.IEmpOperationFlowBS;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.bsinterface.IOrgbusinessflowBS;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF;

public class EmpOperationFlowTaMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.exports", "exports");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {

      Pagination pagination = (Pagination) request.getSession().getAttribute(
          EmpOperationFlowTaShowAC.PAGINATION_KEY);
      List expList = (List) pagination.getQueryCriterions().get("pageList");
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�����ɹ���",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=empOperationFlow_exp");
        return null;
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("û�����ݣ�",
            false));
        saveErrors(request, messages);
      }

    } catch (Exception bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("����ʧ�ܣ�"
          + bex.getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("show_empOperationFlow");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IOrgbusinessflowBS orgbusinessflowBS = (IOrgbusinessflowBS) BSUtils
          .getBusinessService("orgbusinessflowBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils.getBusinessService(
          "monthpayBS", this, mapping.getModuleConfig());
      String orgbusinessflowHeadID = (String) request.getSession()
          .getAttribute("orgbusinessflowHeadID");
      OrgHAFAccountFlow orgHAFAccountFlow = orgbusinessflowBS
          .findOrgHAFAccountFlow(orgbusinessflowHeadID);
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String userName = "";
      String name = monthpayBS.queryMakerPara();

      if (name.equals("1")) {
        userName = securityInfo.getUserName();
      } else {
        userName = securityInfo.getRealName();
      }
      if (orgHAFAccountFlow != null) {
        if (orgHAFAccountFlow.getBiz_Type().equals("A")) {
          Pagination pagination = new Pagination();
          pagination.getQueryCriterions().put("paymentid",
              orgHAFAccountFlow.getBizId().toString());
          List list = monthpayBS.findTaillistMXPrint(pagination);
          request.getSession().setAttribute("cellList", list);

          return new ActionForward(
              "/../../../paymng/monthpay/monthpay_mx_cell.jsp");

        } else if (orgHAFAccountFlow.getBiz_Type().equals("M")) {
          IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils.getBusinessService(
              "orgaddpayBS", this, mapping.getModuleConfig());
          List list = orgaddpayBS.findOrgaddpayListPring(orgHAFAccountFlow
              .getBizId().toString());
          String collectionBankName = "";
          collectionBankName = orgaddpayBS.queryCollectionBankNameById(
              orgHAFAccountFlow.getOrg().getId().toString(), securityInfo);
          request.getSession().setAttribute("cellList", list);
          request.getSession().setAttribute("collectionBankName",
              collectionBankName);

          return new ActionForward(
              "/../../../paymng/orgaddpay/orgaddpay_mx_cell.jsp");

        } else if (orgHAFAccountFlow.getBiz_Type().equals("B")) {
          Pagination pagination = new Pagination();
          PaginationUtils.updatePagination(pagination, request);
          saveToken(request);
          IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
              .getBusinessService("personAddPayBS", this, mapping
                  .getModuleConfig());

          String paymentid = orgHAFAccountFlow.getBizId().toString();
          EmpAddPay empAddPay = personAddPayBS.findEmpAddPay(paymentid);
          pagination.getQueryCriterions().put("paymentHeadId", paymentid);
          pagination.getQueryCriterions().put("orgId",
              empAddPay.getOrg().getId().toString());
          pagination.getQueryCriterions().put("unitName",
              empAddPay.getOrg().getOrgInfo().getName());
          List list2 = personAddPayBS.findPersonAddPayPrintList(pagination);
          PersonAddPayAF personAddPayAF = new PersonAddPayAF();
          Org org = personAddPayBS.findOrgInfo(empAddPay.getOrg().getId()
              .toString(), "2", securityInfo);
          String office = "";
          String collbankname = "";
          String bankid = "";
          String bankname = "";
          String str[] = new String[2];

          String receivables_orgname = "";
          str = personAddPayBS.queryOfficeBankNames(empAddPay.getOrg().getId()
              .toString(), "2", paymentid, "B", securityInfo);
          if (str[0] != null) {
            office = str[0];
          }
          if (str[1] != null) {
            collbankname = str[1];
          }
          if (org.getOrgInfo().getPayBank() != null) {
            bankid = org.getOrgInfo().getPayBank().getAccountNum();
            bankname = org.getOrgInfo().getPayBank().getName();
          }

          personAddPayAF.setReceivables_bank_acc(empAddPay
              .getReceivables_bank_acc());
          personAddPayAF.setReceivables_bank_name(empAddPay
              .getReceivables_bank_name());
          personAddPayAF.setReceivables_orgname(receivables_orgname);
          personAddPayAF.setPayment_bank_acc(empAddPay.getPayment_bank_acc());
          personAddPayAF.setPayment_bank_name(empAddPay.getPayment_bank_name());
          personAddPayAF.setPayment_orgname(org.getOrgInfo().getName());
          personAddPayAF.setPayWay(empAddPay.getPay_way());
          personAddPayAF.setPayKind(empAddPay.getPay_kind());
          personAddPayAF.setBizDate(securityInfo.getUserInfo().getBizDate());
          personAddPayAF.setOffice(office);
          personAddPayAF.setCollbankname(collbankname);
          personAddPayAF.setBankid(bankid);
          personAddPayAF.setBankname(bankname);
          personAddPayAF.setPersonAddPayList(list2);
          if (org.getOrgInfo().getTransactor() != null) {
            personAddPayAF.setTransactorName(org.getOrgInfo().getTransactor()
                .getName());
            personAddPayAF.setTransactorTel(org.getOrgInfo().getTransactor()
                .getTelephone());
          }
          request.getSession().setAttribute("f", personAddPayAF);

          return new ActionForward(
              "/../../../paymng/personaddpay/empaddpay_qc_cell.jsp");

        } else if (orgHAFAccountFlow.getBiz_Type().equals("D")) {
          List print = null;
          IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS",
              this, mapping.getModuleConfig());
          PickupGetCompanyIdAF af = new PickupGetCompanyIdAF();

          Date bizdate = BusiTools.stringToUDate(securityInfo.getUserInfo()
              .getBizDate(), "yyyyMMdd");
          String date = BusiTools.dateToString(bizdate, "yyyy-MM-dd");

          print = pbs.getPrintAllEmployeeList(orgHAFAccountFlow.getBizId()
              .toString());// ��ô�ӡ�ļ���
          System.out.println("�˵�λ��һ����" + print.size() + "����¼");
          af.setList(print);// ���List����һ��������....

          List list = pbs.getPrintAllEmployeeList(orgHAFAccountFlow.getBizId()
              .toString());// ���Ҫ��ӡ�ļ���..
          // System.out.println("��ӡ�ļ��ϳ�����......."+list.size());

          ApplyBookDTO apply = new ApplyBookDTO();// ��ȡƾ֤..
          PickTail pick = null;
          if (list.size() > 0) {
            pick = (PickTail) list.get(0);

          }
          NameAF nameAF = new NameAF();
          nameAF = pbs.findName(pick.getPickHead().getOrg().getId().toString());

          String office = "";
          String collbankname = "";
          String str[] = new String[2];

          str = pbs.queryOfficeBankNames(pick.getPickHead().getOrg().getId()
              .toString(), "2", orgHAFAccountFlow.getBizId().toString(), "D",
              securityInfo);

          if (str[0] != null) {
            office = str[0];
          }
          if (str[1] != null) {
            collbankname = str[1];
          }
          apply.setOrgid(BusiTools.convertTenNumber(pick.getPickHead().getOrg()
              .getId().toString()));
          apply.setSOrgName(nameAF.getOrgName());// �տλ����
          apply.setSOrgNumber(nameAF.getPayBankNum());// �տλ�˺�(��н����)
          apply.setSOrgBank(nameAF.getPayBank());// �տλ����
          apply.setFOrgName(office);// ���λ����(����)
          apply.setFOrgNumber(" ");// ���λ�˺�(����)
          apply.setFOrgBank(collbankname);// ���λ(���Ĺ鼯����)
          apply.setPickBalance(pick.getPickHead().getPickBalance().add(
              pick.getPickHead().getDistroyInterest()).divide(
              new BigDecimal(1), 2, BigDecimal.ROUND_HALF_DOWN));// ����ת������λС��
          apply.setBizdate(date);
          apply.setDocnum(pick.getPickHead().getDocNum());
          apply.setNote_num(pick.getPickHead().getNoteNum());
          apply.setOther_card_num(pick.getPickHead().getReserveaA());
          userName = pbs.getpickup_oper(pick.getPickHead().getId().toString());
          userName = pbs.find_user_realname(userName);
          String checkPerson = pbs.getpickup_check(pick.getPickHead().getId()
              .toString());
          checkPerson = pbs.find_user_realname(checkPerson);
          apply.setOperater(userName + "");
          apply.setCheckperson(checkPerson + "");
          date = pick.getPickHead().getSettDate();
          date = date.substring(0, 4) + "��" + date.substring(4, 6) + "��"
              + date.substring(6, 8) + "��";
          request.setAttribute("apply", apply);
          request.setAttribute("employee", list);
          request.setAttribute("date", date);
          request.setAttribute("PRINT", "D");

          if (print.size() == 1) {
            return new ActionForward("/../../../pickupmng/pickup/printDoc.jsp");
          } else {
            return new ActionForward(
                "/../../../pickupmng/pickup/emplist_mx_cell.jsp");
          }
        } else if (orgHAFAccountFlow.getBiz_Type().equals("E")) {// ת��
          List list = null;
          ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
              "tranoutBS", this, mapping.getModuleConfig());
          TranTbPrintAF tranTbPrintAF = tranoutBS
              .printCredence(orgHAFAccountFlow.getBizId().toString());
          list = tranTbPrintAF.getList();
          request.setAttribute("bizDate", bizDate);
          request.setAttribute("tranTbPrintAF", tranTbPrintAF);
          if (list.size() == 1) {
            return new ActionForward(
                "/../../../tranmng/tranout/printCredence_cell.jsp");
          } else {
            return new ActionForward(
                "/../../../tranmng/tranout/tranout_cell.jsp");
          }
        } else if (orgHAFAccountFlow.getBiz_Type().equals("F")) {// ת��
          org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF tranTbPrintAF = null;
          ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService(
              "traninBS", this, mapping.getModuleConfig());
          TranInHead tranInHead = traninBS.queryTranInHead(orgHAFAccountFlow
              .getBizId().toString());
          if (tranInHead.getTranOutHeadId() != null
              && tranInHead.getTranOutHeadId().intValue() != 0) {
            tranTbPrintAF = traninBS.printCredence_yg(tranInHead
                .getTranOutHeadId().toString());
            request.setAttribute("bizDate", bizDate);
            request.setAttribute("tranTbPrintAF", tranTbPrintAF);
            request.setAttribute("tranTailcelllist", tranTbPrintAF.getList());
          } else {
            tranTbPrintAF = traninBS.printCredence_yga(tranInHead.getId()
                .toString());
            request.setAttribute("bizDate", bizDate);
            request.setAttribute("tranTbPrintAF", tranTbPrintAF);
            request.setAttribute("tranTailcelllist", tranTbPrintAF.getList());
            if (tranTbPrintAF.getList().size() == 1) {
              return new ActionForward(
                  "/../../../tranmng/tranin/printCredence_cella.jsp");
            } else {
              return new ActionForward(
                  "/../../../tranmng/tranin/tranin_cell_ygb.jsp");
            }
          }
          if (tranTbPrintAF.getList().size() == 1) {
            return new ActionForward(
                "/../../../tranmng/tranin/printCredence_cell.jsp");
          } else {
            return new ActionForward("/../../../tranmng/tranin/tranin_cell.jsp");
          }

        } else if (orgHAFAccountFlow.getBiz_Type().equals("C")) {
          IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
              .getBusinessService("orgoverpayBS", this, mapping
                  .getModuleConfig());
          OrgoverpayAF orgoverpayAF = orgoverpayBS
              .printReport(orgHAFAccountFlow.getBizId().toString());
          orgoverpayAF.setBizDate(bizDate);
          // ���Ʒ��޸ģ��������ԭ����ʱ��ʾnull����
          if (orgoverpayAF.getReason() == null) {
            orgoverpayAF.setReason("");
          }
          Org org = orgoverpayBS.findOrgInfo(orgoverpayAF.getOrgId(), "2",
              securityInfo);
          String office = "";
          String collbankname = "";
          String bankid = "";
          String bankname = "";
          String str[] = new String[2];
          // List officlist=securityInfo.getAllCenterList();
          // String collBankid=org.getOrgInfo().getCollectionBankId();
          // if(officlist != null){
          // OfficeDto dto=(OfficeDto)officlist.get(0);
          // office=dto.getOfficeName();
          // }
          str = orgoverpayBS.queryOfficeBankNames(orgoverpayAF.getOrgId(), "2",
              orgHAFAccountFlow.getBizId().toString(), "C", securityInfo);
          if (str[0] != null) {
            office = str[0];
          }
          if (str[1] != null) {
            collbankname = str[1];
          }
          if (org.getOrgInfo().getPayBank() != null) {
            bankid = org.getOrgInfo().getPayBank().getAccountNum();
            bankname = org.getOrgInfo().getPayBank().getName();
          }
          orgoverpayAF.setBankid(bankid);
          orgoverpayAF.setBankname(bankname);
          orgoverpayAF.setOffice(office);
          orgoverpayAF.setCollbankname(collbankname);
          request.setAttribute("ForwardUrl", "C");
          request.setAttribute("orgoverpayAF", orgoverpayAF);
          return new ActionForward(
              "/../../../paymng/orgoverpay/orgoverpay_mx_cell.jsp");
        } else if (orgHAFAccountFlow.getBiz_Type().equals("K")
            || orgHAFAccountFlow.getBiz_Type().equals("L")
            || orgHAFAccountFlow.getBiz_Type().equals("G")) {

          IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
              .getBusinessService("adjustaccountBS", this, mapping
                  .getModuleConfig());
          request.setAttribute("PRINT", "PRINT");
          List list = adjustaccountBS.adjustWrongAccountAllByHID(
              orgHAFAccountFlow.getBizId().toString(), securityInfo);
          request.setAttribute("printlist", list);
          request.setAttribute("bizDate", bizDate);
          request.setAttribute("userName", userName);
          request.setAttribute("URL", "L");
          if (!list.isEmpty()) {
            if (list.size() > 1)
              return new ActionForward(
                  "/../../../accounthandle/adjustaccount/adjustaccount_list_cell.jsp");
          }
          return new ActionForward(
              "/../../../accounthandle/adjustaccount/adjustaccount_single_cell.jsp");
        }
        request.setAttribute("type", orgHAFAccountFlow.getBiz_Type());
        request.getSession().setAttribute("bizDate", bizDate);
        request.getSession().setAttribute("userName", userName);
        request
            .getSession()
            .setAttribute(
                "URL",
                "/syscollection/querystatistics/operationflow/orgoperationflow/empOperationFlowTaShowAC.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("show_print");
  }
}
