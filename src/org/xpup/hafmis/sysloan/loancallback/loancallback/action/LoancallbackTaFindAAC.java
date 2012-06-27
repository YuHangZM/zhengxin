package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class LoancallbackTaFindAAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
  
          //����ҳ����Ҫ��Ȩ�޵ģ���Ȩ����ȡ����������Ϊ������������Ϊ�����Դ������ư�ť���ԣ�Ҫ��action������״̬��
          //���������1.����Ϊ��2.����Ϊ��
          int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
          //����Ȩ���еĻ��������ж�����Ϊ��
          int plLoanReturnType = 0;
          if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER){
            plLoanReturnType = 1;//����Ϊ��
          }else if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK){
            plLoanReturnType = 2;//����Ϊ��
          }
          String id=(String)request.getParameter("id");
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("contractId", id);
          pagination.getQueryCriterions().put("callbackMonth",null);
          text = "display('"+id+"');";
          request.getSession().setAttribute("plLoanReturnType", String.valueOf(plLoanReturnType));
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return LoancallbackTaShowAC.PAGINATION_KEY;
 }
}