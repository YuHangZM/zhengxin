package org.xpup.hafmis.sysloan.dataready.develop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ������ά���Ľڵ�Action���������뿪����ά��ҳ
 * 
 * @author ���Ʒ�
 */
public class DevelopTbForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    request.getSession().removeAttribute(DevelopTbShowAC.PAGINATION_KEY);
    return mapping.findForward("developTbShowAC");
  }

}
