package org.xpup.hafmis.syscollection.chgbiz.autochangeparam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.bsinterface.IAutoChangeParamBS;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.form.AutoChangeParamAF;

/**
 * Copy Right Information : ��ʾ�Զ�����������õ�Action Goldsoft Project :
 * AutoChangeParamShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.3.17
 */
public class AutoChangeParamShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {

      AutoChangeParamAF autoChangeParamAF = new AutoChangeParamAF();

      IAutoChangeParamBS officeParaBS = (IAutoChangeParamBS) BSUtils
          .getBusinessService("autoChangeParamBS", this, mapping
              .getModuleConfig());
      // ��ѯAA412�в�����ŵ���10�ļ�¼
      String param = officeParaBS.findAutoChangeParam();
      autoChangeParamAF.getAutoChangeParamDTO().setIsAutoChange(param);
      
      request.setAttribute("autoChangeParamAF", autoChangeParamAF);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_autochangeparam_show");
  }
}
