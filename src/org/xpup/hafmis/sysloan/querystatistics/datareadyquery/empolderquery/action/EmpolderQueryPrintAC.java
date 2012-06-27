package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;

/**
 * �����̲�ѯ��ӡAction
 * 
 * @author ���Ʒ�
 */
public class EmpolderQueryPrintAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          EmpolderQueryShowAC.PAGINATION_KEY);

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      list = developBS.findDevelopPrintList_print(pagination, securityInfo);

      // �õ�����Ա
      String userName = securityInfo.getUserInfo().getRealName();
      // �õ��������
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("bizDate", bizDate);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    // ��ӡ���ص�URL
    String url = "empolderQueryShowAC.do";
    request.setAttribute("url", url);
    request.setAttribute("developTbListDTOList", list);
    return mapping.findForward("to_develop_print");
  }

}
