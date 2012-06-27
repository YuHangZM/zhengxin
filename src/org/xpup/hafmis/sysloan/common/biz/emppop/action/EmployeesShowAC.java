package org.xpup.hafmis.sysloan.common.biz.emppop.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.sysloan.common.biz.emppop.bsinterface.IEmpPopBS;

public class EmployeesShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.common.biz.emppop.action.EmployeesShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String orgid = null;
      String[] status = null;
      String statuss = null;
      String cardnum = null;
      String empname = null;
      String objInput = null;
      String objInputf = null;
      HttpSession session = request.getSession(false);
      /** *������޸ģ���indexs�õ�if�ж����** */
      String indexs = request.getParameter("indexs");
      objInput = request.getParameter("objInput");
      objInputf = request.getParameter("objInputf");

      if (indexs != null)
        request.getSession().setAttribute("indexs", indexs);

      if (objInput != null) {
        request.getSession().setAttribute("objInput", objInput);
      }
      if (objInputf != null) {
        request.getSession().setAttribute("objInputf", objInputf);
      }
      /** ********************************* */
      // �����½��뵯����ʱ���session
      if (indexs != null && request.getSession().getAttribute("indexs") != null) {
        request.getSession().removeAttribute(EmployeesShowAC.PAGINATION_KEY);
      }
      if (request.getParameter("orgid") != null) {
        // ���Ʒ��޸ģ��������ť����������pagination�е�������
        request.getSession().removeAttribute(EmployeesShowAC.PAGINATION_KEY);
        orgid = (String) request.getParameter("orgid");
        statuss = request.getParameter("st");
        cardnum = request.getParameter("cardnum");
        empname = request.getParameter("empname");
        // String indexs=request.getParameter("indexs");
        // if(indexs != null)
        // request.getSession().setAttribute("indexs", indexs);
        if (!statuss.equals("null")) {
          status = new String[statuss.length()];
          for (int i = 0; i < statuss.length(); i++) {
            status[i] = statuss.substring(i, i + 1);
          }
        }
        if (cardnum.equals("null")) {

          cardnum = null;
        }
        if (empname.equals("null")) {
          empname = null;
        }
        request.getSession().setAttribute("cardnumm", cardnum);
        // ���Ʒ��޸ģ������·�װ��String[]��ְ��״̬����session��
        request.getSession().setAttribute("status", status);
        request.getSession().setAttribute("empnamee", empname);
        session.setAttribute("SL_org_id", orgid);

      }
      orgid = (String) session.getAttribute("SL_org_id");
      String cardnummm = (String) session.getAttribute("cardnumm");
      String empnameee = (String) session.getAttribute("empnamee");
      // ���Ʒ��޸ģ�ʹ��session����������ʾְ����״̬����״̬��39�д���������õ���
      status = (String[]) session.getAttribute("status");
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      pagination.getQueryCriterions().put("id", orgid);
      pagination.getQueryCriterions().put("status", status);
      pagination.getQueryCriterions().put("cardnum", cardnummm);
      pagination.getQueryCriterions().put("empname", empnameee);
      // ���Ʒ��޸�: ÿ��ͨ��ְ�������ְ��������ѯ��Ҫ���pagination�еĲ�ѯ������
      if (request.getParameter("orgid") != null) {
        pagination.getQueryCriterions().put("empid", null);
        pagination.getQueryCriterions().put("name", null);
      }
      modifyPagination(pagination);
      PaginationUtils.updatePagination(pagination, request);
      request.getSession().setAttribute(paginationKey, pagination);
      IEmpPopBS empPopBS = (IEmpPopBS) BSUtils.getBusinessService("empPopBS",
          this, mapping.getModuleConfig());
      List employees = empPopBS.findEmployee(pagination);
      request.setAttribute("employees", employees);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "employees";
  }

  protected void modifyPagination(Pagination pagination) {
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "epa.Employee.id", "ASC",
          new HashMap(0));

      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
