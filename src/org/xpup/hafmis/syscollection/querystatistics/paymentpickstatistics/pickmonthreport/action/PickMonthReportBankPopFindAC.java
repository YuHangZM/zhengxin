package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.form.PickMonthReportBankPopAF;
/**
 * 
 * @author yqf
 * ��ȡͳ���±���  ���е�����
 * 20080925
 */
public class PickMonthReportBankPopFindAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    PickMonthReportBankPopAF pickMonthReportBankPopAF = (PickMonthReportBankPopAF)form;
    HashMap criterions = makeCriterionsMap(pickMonthReportBankPopAF);
    Pagination pagination = new Pagination(0, 10, 1, "a.org_id", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("pickMonthReportBankPopShowAC");
  }

  private String getPaginationKey() {
    // TODO Auto-generated method stub
    return PickMonthReportBankPopShowAC.PAGINATION_KEY;
  }

  private HashMap makeCriterionsMap(PickMonthReportBankPopAF form) {
    // TODO Auto-generated method stub
    HashMap m = new HashMap();
    String orgId = form.getOrgId();//��λ���
    if(orgId!=null && !"".equals(orgId)){
      m.put("orgId", orgId.trim());
    }
    String orgName = form.getOrgName();//��λ����
    if(orgName!=null && !"".equals(orgName)){
      m.put("orgName", orgName.trim());
    }
    String empId = form.getEmpId();//ְ�����
    if(empId!=null && !"".equals(empId)){
      m.put("empId", empId.trim());
    }
    String empName = form.getEmpName();//ְ������
    if(empName!=null && !"".equals(empName)){
      m.put("empName", empName.trim());
    }
    String pickReason = form.getPickReason();//��ȡԭ��
    if(pickReason!=null && !"".equals(pickReason)){
      m.put("pickReason", pickReason.trim());
    }
    return m;
  }

}
