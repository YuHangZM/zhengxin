package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.EmpAccountPrintPopAF;
/**
 * ְ���˴�ӡ����Action
 * Copy Right Information :
 * Goldsoft Project : 
 *
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008-5-13 ����10:10:53
 */
public class EmpAccountPrintPopForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      EmpAccountPrintPopAF empAccountPrintPopAF = new EmpAccountPrintPopAF();
      request.setAttribute("empAccountPrintPopAF", empAccountPrintPopAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_empaccountprintpop_show");
  }

}
