package org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.bsinterface.IChgslarybaseBS;

import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.from.ChgslarybaseTbListAF;

/**
 * ����� 2007.6.27 @
 */
public class ChgslarybaseTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.action.ChgslarybaseTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ChgslarybaseTbListAF chgslarybaseTbListAF = null;
    List list = null;
    List totalorgChgPaymentSalaryBase=null;
    Pagination pagination = null;
    try {
      // ����������ύ����AA202���id
      String id = request.getParameter("id");
      pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      // �ɳ����������뵯����
      if (id!=null) {
        // ��ʹ�����ӽ��е�����ʱ�����ChgslarybaseTbShowAC��Session.
        request.getSession().removeAttribute(ChgslarybaseTbShowAC.PAGINATION_KEY);
        pagination = getPagination(PAGINATION_KEY, request);
        PaginationUtils.updatePagination(pagination, request);
        pagination.getQueryCriterions().put("chgPaymentHeadId", id);
        pagination.getQueryCriterions().put("orgId", ""); 
        pagination.getQueryCriterions().put("empId", "");  
        pagination.getQueryCriterions().put("empName", "");  
        pagination.getQueryCriterions().put("orgName", "");  
        pagination.getQueryCriterions().put("startChgMonth", "");  
        pagination.getQueryCriterions().put("endChgMonth", "");  
        pagination.getQueryCriterions().put("startBizDate", "");  
        pagination.getQueryCriterions().put("endBizDate", "");  
        pagination.getQueryCriterions().put("type", "1");
        // ���Ʒ��޸ģ�����״̬���ǵ���������ѯ�ʹ��ڴ�״̬
//        pagination.getQueryCriterions().put("temp_type", "0");
        
      }
      pagination.getQueryCriterions().put("SecurityInfo", securityInfo);
      IChgslarybaseBS chgslarybaseBS = (IChgslarybaseBS) BSUtils
          .getBusinessService("chgslarybaseBS", this, mapping.getModuleConfig());
      chgslarybaseTbListAF = chgslarybaseBS.findEmpChgslarybaseList(pagination);
      if (chgslarybaseTbListAF == null) {
        chgslarybaseTbListAF = new ChgslarybaseTbListAF();
      }
      request.setAttribute("chgslarybaseTbListAF", chgslarybaseTbListAF);
      list = chgslarybaseTbListAF.getList();
      totalorgChgPaymentSalaryBase=chgslarybaseTbListAF.getTotalempChgPaymentSalaryBase();
      
      if(list!=null && list.size()>0   ){
        chgslarybaseTbListAF.setListCount("1");
      }
      pagination.getQueryCriterions().put("pageList", list);
      HttpSession session=request.getSession();
      session.setAttribute("cellTbList", totalorgChgPaymentSalaryBase);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "û����Ҫ��ѯ����Ϣ��", false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_chgslarybase_tblist.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id ",
          "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
