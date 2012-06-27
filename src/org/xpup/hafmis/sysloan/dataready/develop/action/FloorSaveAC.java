package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.form.FloorFindAF;
import org.xpup.hafmis.sysloan.dataready.develop.form.FloorNewAF;

public class FloorSaveAC extends DispatchAction {

  /**
   * ���¥����Ϣ�ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveFloorInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�벻Ҫ�ظ��ύ��",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        FloorNewAF floorNewAF = (FloorNewAF) form;
        DevelopProject developProject = floorNewAF.getDevelopProject();

        // ��pagination��ȡ��PL005��id
        Pagination pagination = (Pagination) request.getSession().getAttribute(
            FloorShowAC.PAGINATION_KEY);
        String develop_id = (String) pagination.getQueryCriterions().get("id");

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());
        // ����PL005��id
        developProject.setHeadId(develop_id);
        
        developBS.saveFloorInfo(developProject, securityInfo);

      } catch(BusinessException be){
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
        be.printStackTrace();
        // �������쳣ʱ����ת�����ҳ
        FloorNewAF floorNewAF = new FloorNewAF();
        FloorFindAF floorFindAF = new FloorFindAF();
        Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
        floorNewAF.setRegionMap(regionMap);
        request.setAttribute("floorNewAF", floorNewAF);
        request.setAttribute("floorFindAF", floorFindAF);
        return mapping.findForward("to_floor_new");
      } catch (Exception e) {
        // ����������ʧ��ʱ�����ظ��ύ���ƶ�����Ĳ����������
        saveToken(request);
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("���ʧ�ܣ�",
            false));
        saveErrors(request, messages);
        e.printStackTrace();
        // �������쳣ʱ����ת�����ҳ
        FloorNewAF floorNewAF = new FloorNewAF();
        FloorFindAF floorFindAF = new FloorFindAF();
        Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
        floorNewAF.setRegionMap(regionMap);
        request.setAttribute("floorNewAF", floorNewAF);
        request.setAttribute("floorFindAF", floorFindAF);
        return mapping.findForward("to_floor_new");
      }
    }
    return mapping.findForward("floorShowAC");
  }

  /**
   * �޸�¥����Ϣ�ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward modifyFloorInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DevelopProject old_developProject = null;
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�벻Ҫ�ظ��ύ��",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        // ��session�еĵ���Ҫ�޸ĵ�¥����Ϣ
        old_developProject = (DevelopProject) request.getSession()
            .getAttribute("developProject_FYF");

        // �õ������޸ĺ����Ϣ
        FloorNewAF floorNewAF = (FloorNewAF) form;
        DevelopProject new_developProject = floorNewAF.getDevelopProject();

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());

        developBS.modifyFloorInfo(old_developProject, new_developProject,
            securityInfo);

      } catch (Exception e) {
        // ��������޸�ʧ��ʱ�����ظ��ύ���ƶ�����Ĳ����޸�����
        saveToken(request);

        e.printStackTrace();

        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�޸�ʧ�ܣ�",
            false));
        saveErrors(request, messages);
        // �������쳣ʱ����ת���޸�ҳ
        FloorNewAF floorNewAF = new FloorNewAF();
        floorNewAF.setDevelopProject(old_developProject);
        floorNewAF.setType("1");
        Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
        floorNewAF.setRegionMap(regionMap);

        request.setAttribute("floorNewAF", floorNewAF);
        return mapping.findForward("to_floor_new");

      } finally {
        // �޸Ľ��������session,�ڵ���˼�ʱ���Ҳ�����session��ֵ
        // request.getSession().removeAttribute("developProject_FYF");
      }
    }
    return mapping.findForward("floorShowAC");
  }

  /**
   * ����¥����Ϣ�б�
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward backFloorInfoList(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    return mapping.findForward("floorShowAC");
  }

}
