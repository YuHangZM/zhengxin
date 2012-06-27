/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.dataready.develop.form.BuildFindAF;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2008
 * 
 * XDoclet definition:
 * @struts.action path="/buildFindAC" name="buildFindAF" scope="request" validate="true"
 */
public class BuildFindAC extends Action {
	/*
	 * Generated Methods
	 */
  
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BuildFindAF buildFindAF = (BuildFindAF) form;// TODO Auto-generated method stub
    try {
      HashMap criterions = makeCriterionsMap(buildFindAF);
      Pagination pagination = new Pagination(0, 10, 1, " t.build_id ", "DESC",criterions);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
      modifyPagination(pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
		return mapping.findForward("buildShowAC");
	}
  
   protected String getPaginationKey() {

      return BuildShowAC.PAGINATION_KEY;
    }
    protected HashMap makeCriterionsMap(BuildFindAF buildFindAF) {
      HashMap m = new HashMap();
      String buildId = buildFindAF.getBuildDTO().getBuildId();
      if (buildId != null && !"".equals(buildId)) {
        m.put("buildId", buildId.trim());
      }
      String buildNum = buildFindAF.getBuildDTO().getBuildNum();
      if (buildNum != null && !"".equals(buildNum)) {
        m.put("buildNum", buildNum.trim());
      }
      
      String developerId = buildFindAF.getBuildDTO().getDeveloperId();
      m.put("developerId", developerId);
      
      String developerName = buildFindAF.getBuildDTO().getDeveloperName();
      m.put("developerName", developerName);
      
      String floorNum = buildFindAF.getBuildDTO().getFloorNum();
      m.put("floorNum", floorNum);
      
      String floorName = buildFindAF.getBuildDTO().getFloorName();
      m.put("floorName", floorName);
      
      return m;
    }
  protected void modifyPagination(Pagination pagination) {
  }
}