package org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.action;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;

public class LoadMessageAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
   try{
     response.setContentType("text/xml; charset=utf-8");
     PrintWriter out = response.getWriter();
     out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    out.println("<options>");

    Map maplogo=BusiTools.listBusiProperty(BusiConst.REPORTLOGO);   
    maplogo.remove(BusiConst.REPORTLOGO_ENDBALANCE_DEBIT);  //��ĩ�����ã� 
    maplogo.remove(BusiConst.REPORTLOGO_CURFIGURES_SUMDEBIT);//�����ۼƷ�����跽�������ã�
    maplogo.remove(BusiConst.REPORTLOGO_CURFIGURES_SUMCREDIT);//�����ۼƷ���������������ã�
    maplogo.remove(BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT);//�����ۼƷ�����跽�������ã�
    maplogo.remove(BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT);//�����ۼƷ���������������ã�
    maplogo.remove(BusiConst.REPORTLOGO_FORMULA);//!��ʽ
    Iterator itlogo=maplogo.keySet().iterator();

    out.println("<value>"+"0"+"</value>");
    out.println("<text>"+"."+"</text>"); 
    while(itlogo.hasNext()){
        Object objlogo=(Object)itlogo.next();
        out.println("<value>"+objlogo.toString()+"</value>");
        out.println("<text>"+BusiTools.getBusiValue_WL(objlogo.toString(), BusiConst.REPORTLOGO)+"</text>"); 
    }

    out.println("</options>");
    out.flush();
    out.close();
     
   }catch(Exception e){
     e.printStackTrace();
   }
    return null;
  }
}