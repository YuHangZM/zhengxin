package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.ArrayList;
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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChoIdAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpKhCriteronsAF;

/**
 * Copy Right Information : ��ְ������ʱ������ͬ���֤�Ų�ͬ����ʱ�����ѡ����������ʹ�����Action Goldsoft
 * Project : EmpSaveAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2008.2.19
 */
public class EmpSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    EmpKhCriteronsAF af = (EmpKhCriteronsAF) form;
    if(!isTokenValid(request,true))
    {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�벻Ҫ�ظ��ύ��",false));         
      saveErrors(request, messages);
      saveToken(request);
    }else{
      saveToken(request);
      String id = null;
      String orgId = (String) request.getSession().getAttribute("org.id").toString();
      String emppaymonth = (String) request.getSession().getAttribute("org.emppaymonth");
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
        .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
        // �жϴ˵�λ�Ƿ��ѿ���
        if (orgOpenAccountBS.validateOpenStatus(orgId)) {
          // ������������Լ����Ա��
          try {
              int flag = 0;
              flag = orgOpenAccountBS.saveEmployee(orgId, af.getEmp(), emppaymonth,
                  flag);
              if (flag == 1) {
                List tochooselist = new ArrayList();
                tochooselist = orgOpenAccountBS.getEmpFromOthers(orgId, af.getEmp());
                EmpChoIdAF empchoidaf = new EmpChoIdAF();
                empchoidaf.setList(tochooselist);
                saveToken(request);
                request.setAttribute("empChoIdAF", empchoidaf);
                request.getSession().setAttribute("empkhAF", af);
                return mapping.findForward("emp_choose");
              } else {
                orgOpenAccountBS.saveEmployeeInfo(orgId, af.getEmp(), emppaymonth, id,securityInfo);
              }
  
              EmpKhCriteronsAF aff = new EmpKhCriteronsAF();
              Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
              aff.setSexMap(sexMap);
              Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
              aff.setCardKindMap(cardKindMap);
              aff.setType("1");
              request.setAttribute("empKhCriteronsAF", aff);
              request.setAttribute("flag", "0");
              return mapping.findForward("emp_open_new");
  
          } catch (BusinessException bex) {
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
                .getLocalizedMessage().toString(), false));
            saveErrors(request, messages);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }else{
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�õ�λ�Ѿ��ǿ���״̬���������Ա��!", false));
          saveErrors(request, messages);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    EmpKhCriteronsAF aff = new EmpKhCriteronsAF();
    Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
    af.setSexMap(sexMap);
    Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    af.setCardKindMap(cardKindMap);
    af.setType("1");
    request.setAttribute("empKhCriteronsAF", af);
    request.setAttribute("flag", "0");
    return mapping.findForward("emp_open_new");
  }

}
