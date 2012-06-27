package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.business.tranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;

public class Tran_addMaintainAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "save");   //���
    map.put("button.back", "back"); // ����
    return map;
  }

  public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages = null;
    try{
        saveToken(request);
//        if(!isTokenValid(request))   {   
//          System.out.println("--���ܹ�ˮ!!"); 
//       }   
//       resetToken(request); 
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
//        String ip = securityInfo.getUserIp();
//        String setDates = securityInfo.getUserInfo().getBizDate();
        
        
        //------------------------------------
//        String empid = request.getParameter("emp.empId");   //Ա�����
//        String orgid = request.getParameter("orgid");       //Ա����λ���
//        String orginid = request.getParameter("orgInId");   //ת����λ
//        String preRatea = request.getParameter("preRatea"); //�������
//        String curRatea = request.getParameter("curRatea"); //�������
//        String reteaSum = request.getParameter("reteaSum"); //��Ϣ�ϼ�
//        String transum = request.getParameter("transum");   //ת���ܶ�
//        String salary = request.getParameter("salary");     //���
        TranAddAF tranAddAF = (TranAddAF)form;
        TranAddAF af=new TranAddAF();
        String type=(String)request.getAttribute("type");
        int typetran=securityInfo.getIsOrgEdition();
        tranAddAF.setTypetran(typetran+"");
        Map map = new HashMap();
        map.put("2", "��");
        map.put("1", "��");
//        System.out.println(tranAddAF.getYesNo()+"=============>");
        af.setHeadId(tranAddAF.getHeadId());
        af.setOutOrgId(tranAddAF.getOutOrgId());
        af.setInOrgId(tranAddAF.getInOrgId());
        af.setNoteNum(tranAddAF.getNoteNum());
        af.setHeadId(tranAddAF.getHeadId());
//        System.out.println("ffs....."+tranAddAF.getTranin_empId());
//        af.setTranin_empId(tranAddAF.getTranin_empId());
        af.setMap(map);
        af.setTranReason(tranAddAF.getTranReason());
        ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
        af.setTranin_empId(null);
        request.getSession().setAttribute("tranAddAF", af);
        request.setAttribute("type",type);
        request.setAttribute("typetran", typetran+"");
        tranoutBS.addTranTail(tranAddAF, securityInfo);
     
     }catch(BusinessException bex){
       messages=new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(), false));
       saveErrors(request, messages);
     }
    
    return mapping.findForward("to_addjsp");
  }
  
  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

     String type=request.getParameter("types");
     request.setAttribute("type",type);
    request.getSession().setAttribute("tranAddAF", null);
    return mapping.findForward("to_showAC");
  }
  

}
