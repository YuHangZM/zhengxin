package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;


public class CheckSubiectCodeAAC extends Action {
  /**
   * �жϸÿ�Ŀ�Ƿ���ڲ����Ƿ�Ϊĩ����Ŀ
   * ���ֽ��ռ��˺����д���ռ���ģ�鹫��
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String subjectCode=request.getParameter("subjectCode");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String credenceType="1";//Ĭ��Ϊ1����Ҫ��Ϊ�������ж��˵���ʹ��
      //�����ֽ��ռ��˺����д���ռ���ģ�飬��0��Ϊ�ֽ��ռ��ˡ���1��Ϊ���д���ռ���
      if(request.getSession().getAttribute("credenceType_gjp")!=null){
        credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      }
      
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      String subjectId=bankCheckAccBS.checkSubjectCode(subjectCode, credenceType, securityInfo);
      String text="show("+subjectId+");";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
