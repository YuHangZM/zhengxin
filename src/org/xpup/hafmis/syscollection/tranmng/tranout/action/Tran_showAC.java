package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.dto.TranoutiveFDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAF;

public class Tran_showAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      /**
       * ��ҳ
       */
      saveToken(request);

      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      int typetran = securityInfo.getIsOrgEdition();
      request.setAttribute("typetran", typetran + "");
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      String orgid = (String) pagination.getQueryCriterions().get("id");
      // List list = new ArrayList();
      TranAF tranAF = new TranAF();
      // String note_Num = null;
      // String tranStatus = null;//״̬
      // TranOutHead tranOutHead = null;
      //      
      // if(orgid!=null && orgid.equals("")){
      // list = tranoutBS.FindNot_num(orgid);
      // if(list.size()>0 && list!=null){
      // tranOutHead = (TranOutHead)list.get(0);
      // tranStatus = tranOutHead.getTranStatus().toString();
      // // note_Num = tranOutHead.getNoteNum();
      // System.out.println("Tran_show �в��309 ״̬==>"+tranStatus);
      // }else{
      // System.out.println("û�д˵�λ!!");
      // }
      // }
      // if(orgid!=null&&orgid.length()>0){
      // // if(list.size()>0){
      // // tranOutTail = (TranOutTail)list.get(0);
      // // note_Num = tranOutTail.getTranOutHead().getNoteNum();
      // // System.out.println("Ʊ�ݱ��===>>"+note_Num);
      // // }
      // // note_Num = tranOutTail.getTranOutHead().getNoteNum();
      //        
      // }
      TranOutTail tranOutTail = new TranOutTail();
      tranAF = tranoutBS.findtranoutOrgName(orgid, pagination, securityInfo);
      String type = (String) request.getAttribute("type");
      if (type == null)
        type = "1";
      tranAF.setType(type);
      if (tranAF.getList() != null) {
        tranOutTail = (TranOutTail) tranAF.getList().get(0);
        if (!"".equals(tranOutTail.getTranOutHead().getTemp_pickState())) {
          request.setAttribute("statetype", tranOutTail.getTranOutHead()
              .getTemp_pickState());
        }
      }
      if (orgid != null && !orgid.equals("")) {
        if (tranAF.getNoteNumber() == null || tranAF.getNoteNumber().equals("")) {
          tranAF.setNoteNumber(securityInfo.getUserInfo().getBizDate()
              + tranoutBS.queryNoteNum());
        }
      }
      pagination.getQueryCriterions().put("pageList", tranAF.getList());

      // tranAF.setId((String) pagination.getQueryCriterions().get("id"));
      // tranAF.setName((String) pagination.getQueryCriterions().get("name"));
      // tranAF.setNote_Num((String)
      // pagination.getQueryCriterions().get("note_Num"));
      // tranAF.setTranStatus((String)
      // pagination.getQueryCriterions().get("tranStatus"));
      // tranAF.setMonth_income((String)pagination.getQueryCriterions().get("month_income"));
      //      
      // tranAF.setTranStatus(tranStatus);//״̬

      request.setAttribute("tranAF", tranAF);

      pagination.getQueryCriterions().put("pageList", tranAF.getList());
      String alert = "";
      List list = tranoutBS.queryTransInIsFiveList(securityInfo);
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          TranoutiveFDTO tranoutiveFDTO = (TranoutiveFDTO) list.get(i);
          alert += tranoutiveFDTO.getOrgOutName() + "ת��ƾ֤��Ϊ"
              + tranoutiveFDTO.getNoteNum() + "��Ӧת��ҵ���Ѿ����ˣ�\\r";
        }
        throw new BusinessException(alert);
      }

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_show_jsp");
  }

  private Pagination getPagination(String paginationKey,// ��ʼ��
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, " tranOutTail.id ", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
