package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
/**
 * ƾ֤¼��ҳ�������ں�ƾ֤�ŵ�Action
 * @author ���� 
 *
 */
public class CredenceFillinNumAndDateAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String office="";
    String text="";
    try {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    int settleType = securityInfo.getFnSettleType();
    String bookId=securityInfo.getBookId();
    ICredenceFillinBS credenceFillinBS=(ICredenceFillinBS) BSUtils.getBusinessService("credenceFillinBS", this, mapping
        .getModuleConfig());
    String officeCode = request.getParameter("office");
    // �˴���officeCode��Ϊ�������뷽������ȡƾ֤�ŵķ�������İ��´���ͬ
    String credenceDate=credenceFillinBS.getCredenceDate(officeCode,securityInfo);
    if (settleType == 0) {
      office = null;
    } else if (settleType == 1) {
      office = officeCode;
    }
    String credenceNum=credenceFillinBS.getCredenceNum(office, credenceDate.substring(0, 6), "0", bookId);
    response.setContentType("text/html;charset=UTF-8");
     text="displaysNumDate('"+credenceDate+"','"+credenceNum+"')";   
    response.getWriter().write(text);
    response.getWriter().close();
  }
    catch (BusinessException bex) {
      System.err.println(bex.getLocalizedMessage().toString());
     text="reportErrors('"+bex.getLocalizedMessage()+"')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      e.printStackTrace();
    }
    return null; 
  }
}

