package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupMaintainAF;

public class PickupExportTaAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    Pagination pagination=new Pagination();
    try{
//      System.out.println("����");
     
      IPickupBS pbs = (IPickupBS)BSUtils.getBusinessService("pickupBS",this,mapping.getModuleConfig());
      Integer orgId = (Integer)request.getSession().getAttribute("orgId");
//      System.out.println("expoerts:"+orgId);
      //���IP...���������Ĺ̶�д��
      SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip = sInfo.getUserIp();
      String empIds=request.getParameter("empIds");
      String empNames=request.getParameter("empNames");
      String cardNums=request.getParameter("cardNums");
      String cardKinds=request.getParameter("cardKinds");
      String tatol=request.getParameter("tatol");
      int finalTatol=(Integer.parseInt(tatol)-1);
      System.out.println(finalTatol);
      String order[]=new String [finalTatol] ;
      
      if((Integer.parseInt(empIds))!=0){
        order[(Integer.parseInt(empIds)-1)]="emp.empId";
      }
      if((Integer.parseInt(empNames))!=0){
        order[(Integer.parseInt(empNames)-1)]="emp.empInfo.name";
      } 
      if((Integer.parseInt(cardNums))!=0){
        order[(Integer.parseInt(cardNums)-1)]="emp.empInfo.cardNum";
      }   
      if((Integer.parseInt(cardKinds))!=0){
        order[(Integer.parseInt(cardKinds)-1)]="emp.empInfo.cardKind";
      }   
      pagination.getQueryCriterions().put("orderArray", order);
      
      
      List list = pbs.getExportData(orgId.intValue(),ip,sInfo.getUserName(), pagination);
    
      /*
       *if(list!=null && list.isEmpty()){//������Ա�㵼����ʱ��,����û������('���ǲ�����û�����ݡ�����˵��..')
       *   messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("���ݿ���û�����ݿ��Ե���",false));
       *   saveErrors(request, messages);
       *   PickupGetCompanyIdAF af = new PickupGetCompanyIdAF();
       *   request.setAttribute("af", af);
       *   return new ActionForward("/pickup_transactionlist.jsp");
        *}
       */
      request.getSession().setAttribute("explist",list);//β���ֵ......��Ϊ����β����Ե�����ͷ��
      //EXP_NAME��ֵ�����Ǹ�xml�ļ�����ֵ
      response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=pickup_exp");
      return null;//����д..��Ϊ����Ƿ��͵�...
    }catch(Exception s){
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("���ִ���,����ʧ��",false));
    }
        
    return mapping.findForward("to_pickup_showAC");
  
  }
   

}
