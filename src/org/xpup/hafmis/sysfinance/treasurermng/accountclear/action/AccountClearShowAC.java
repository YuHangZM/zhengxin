package org.xpup.hafmis.sysfinance.treasurermng.accountclear.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.accountclear.bsinterface.IAccountClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTcAF;

public class AccountClearShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.treasurermng.accountclear.action.AccountClearShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CashDayClearTcAF cashDayClearTcAF=new CashDayClearTcAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      IAccountClearBS accountClearBS = (IAccountClearBS) BSUtils
      .getBusinessService("accountClearBS", this, mapping.getModuleConfig());
      //�����ݿ��в��ժҪ��list����ʾ�������˵���
      List summaryList=accountClearBS.findSummaryList(securityInfo);
      List summrayList1=new ArrayList();
      if(summaryList.size()>0){
        BookParameterDTO bookParameterDTODTO = null;
        Iterator itr1 = summaryList.iterator();
        while (itr1.hasNext()) {
          bookParameterDTODTO = (BookParameterDTO) itr1.next();
          summrayList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTODTO.getBookParameterName()
              , bookParameterDTODTO.getBookParameterId()));
        }
      }
      Pagination pagination = getPagination(AccountClearShowAC.PAGINATION_KEY, request); 
      Object[] listObj=new Object[3]; 
      PaginationUtils.updatePagination(pagination, request);
      listObj=accountClearBS.findAccountClearList(pagination, securityInfo);
      List list=new ArrayList();
      list=(List)listObj[0];
      if(list.size()>0){
        cashDayClearTcAF.setList(list);
        CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)listObj[1];
        CashDayClearTcFindDTO temp_cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
        temp_cashDayClearTcFindDTO.setCredenceStMap(
            BusiTools.listBusiProperty(BusiConst.CREDSTATE));
        temp_cashDayClearTcFindDTO.getCredenceStMap().remove(new Integer(1));
        temp_cashDayClearTcFindDTO.setCreditSum(cashDayClearTcFindDTO.getCreditSum());
        temp_cashDayClearTcFindDTO.setDebitSum(cashDayClearTcFindDTO.getDebitSum());
        cashDayClearTcAF.setCashDayClearTcFindDTO(temp_cashDayClearTcFindDTO);
      }
      List countList=(List)listObj[2];
      request.getSession().setAttribute("countList", countList);
      request.setAttribute("summrayList1", summrayList1);
      request.setAttribute("cashDayClearTcAF", cashDayClearTcAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_accountclear_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 10, 1, "fn210.credence_id", "DESC",
           map);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
