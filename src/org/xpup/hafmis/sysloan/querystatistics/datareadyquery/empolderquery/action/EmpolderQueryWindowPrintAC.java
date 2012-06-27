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
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;

/**
 * �����̲�ѯ�������ӡAction
 * 
 * @author ���Ʒ�
 */
public class EmpolderQueryWindowPrintAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String id = "";
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          EmpolderQueryWindowShowAC.PAGINATION_KEY);

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      List list = developBS.findFloorPrintList(pagination);
      // �õ�PL005������
      if (pagination.getQueryCriterions().get("id") != null) {
        id = (String) pagination.getQueryCriterions().get("id");
      }
      request.setAttribute("floorListDTOList", list);

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    // ��ӡ���ص�URL
    String url = "empolderQueryWindowShowAC.do?HEAD_ID=" + id;
    request.setAttribute("url", url);
    return mapping.findForward("to_floor_print");
  }

}
