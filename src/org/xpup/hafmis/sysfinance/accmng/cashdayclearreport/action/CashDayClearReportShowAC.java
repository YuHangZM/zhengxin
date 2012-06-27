package org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.action;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.bsinterface.ICashDayClearReportBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTcAF;

public class CashDayClearReportShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.action.CashDayClearReportShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CashDayClearTcAF cashDayClearTcAF=new CashDayClearTcAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      List officeList1 = null;
      List credenceCharacterList1=null;
      List summrayList1=null;
      List settTypeList1=null;
      try {
        // ȡ���û�Ȩ�ް��´�,��ʾ�������˵���
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      ICashDayClearReportBS cashDayClearReportBS = (ICashDayClearReportBS) BSUtils
      .getBusinessService("cashDayClearReportBS", this, mapping.getModuleConfig());
      Object[] obj=cashDayClearReportBS.findCredenceCharacterList(securityInfo);
      if(obj.length>0){
        //�����ݿ��в��ƾ֤�ֵ�list����ʾ�������˵���
        credenceCharacterList1=new ArrayList();
        List credenceCharacterList=(List)obj[0];
        if(credenceCharacterList.size()>0){
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = credenceCharacterList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            credenceCharacterList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTODTO.getBookParameterName()
                , bookParameterDTODTO.getBookParameterId()));
          }
        }
        //�����ݿ��в��ժҪ��list����ʾ�������˵���
        summrayList1=new ArrayList();
        List summrayList=(List)obj[1];
        if(summrayList.size()>0){
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = summrayList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            summrayList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTODTO.getBookParameterName()
                , bookParameterDTODTO.getBookParameterId()));
          }
        }
        //�����ݿ��в�����㷽ʽ��list����ʾ�������˵���
        settTypeList1=new ArrayList();
        List settTypeList=(List)obj[2];
        if(settTypeList.size()>0){
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = settTypeList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            settTypeList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTODTO.getBookParameterName()
                , bookParameterDTODTO.getBookParameterId()));
          }
        }
      }
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      Pagination pagination = getPagination(CashDayClearReportShowAC.PAGINATION_KEY, request); 
      Object[] listObj=new Object[3]; 
      PaginationUtils.updatePagination(pagination, request);
      listObj=cashDayClearReportBS.findCashDayClearReportList(credenceType, pagination, securityInfo);
      List list=new ArrayList();
      list=(List)listObj[0];
      if(list.size()>0){
        cashDayClearTcAF.setList(list);
        CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)listObj[1];
        CashDayClearTcFindDTO temp_cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
        temp_cashDayClearTcFindDTO.setCreditSum(cashDayClearTcFindDTO.getCreditSum());
        temp_cashDayClearTcFindDTO.setDebitSum(cashDayClearTcFindDTO.getDebitSum());
        cashDayClearTcAF.setCashDayClearTcFindDTO(temp_cashDayClearTcFindDTO);
      }
      //type��Ϊ�˵�ҳ��������ʶ�� 0Ϊ�ֽ��ռ��ˣ�1Ϊ���д���ռ���
      if(credenceType.equals("0")){
        request.setAttribute("type", "0");
      }
      if(credenceType.equals("1")){
        request.setAttribute("type", "1");
      }
      request.setAttribute("cashDayClearTcAF", cashDayClearTcAF);
      request.setAttribute("officeList1", officeList1);
      request.setAttribute("credenceCharacterList1", credenceCharacterList1);
      request.setAttribute("summrayList1", summrayList1);
      request.setAttribute("settTypeList1", settTypeList1);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_cashdayclearreport_show");
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
