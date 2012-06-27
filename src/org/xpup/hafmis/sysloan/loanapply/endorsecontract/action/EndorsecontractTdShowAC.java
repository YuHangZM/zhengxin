package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.HashMap;
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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
/**
 * 
 * @author yuqf
 *
 */
public class EndorsecontractTdShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTdShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages = null;
    String paramValue = "";
    Pagination pagination = null;
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      pagination = getPagination(PAGINATION_KEY, request);
      String contractId = "";
      String empId = "";
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());
      paramValue = endorsecontractBS.queryParamValue(securityInfo);// ����ֵ('AB'
                                                                    // or 'BA')
      contractId = (String) request.getParameter("contractId");//��ͬ���
//      empId = (String )request.getParameter("empId");//ְ�����
      if (contractId != null) {
        request.getSession().setAttribute("contractId", null);// ��������ţ������session
        endorsecontractTdAF = endorsecontractBS.queryAssurerList(contractId, pagination, securityInfo, request);
      } else {
        // �ж��Ƿ�Ϊ����
        contractId = (String) request.getSession().getAttribute("contractId");// ����
        pagination.getQueryCriterions().put("contractId", contractId);                                                                     // ȡ��session�к�ͬ���
        String comeFromType = (String) request.getSession().getAttribute(
            "comeFromType");// �����ж��Ƿ�Ϊά��������״̬
        // �ж��Ƿ��Ǵ�ά������������
        if (comeFromType != null) {// ��:��ͬ��Ű�ť���� Ĭ����ʾ��ͬ��š����������
          endorsecontractTdAF.setIsButtonForbid("0");// 0��ֹ 1����
          endorsecontractTdAF = endorsecontractBS.queryAssurerList(contractId, pagination, securityInfo, request);
        } else {
          endorsecontractTdAF.setIsButtonForbid("1");
          endorsecontractTdAF = endorsecontractBS.queryAssurerList(contractId, pagination, securityInfo, request);
        }
      }
//      if(empId != null && !"".equals(empId) && (contractId == null || "".equals(contractId) )){
//      if(empId != null && !"".equals(empId)){
//        String orgId = request.getParameter("orgId");
//        endorsecontractTdAF = endorsecontractBS.queryAssurerListByEmpId(empId,orgId, pagination, securityInfo, request);
//      }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    pagination.getQueryCriterions().put("list", endorsecontractTdAF.getList());// ���б����pagination��,���޸İ�ť��ʱ��ȡ����
    try {
      // ֤������������,�Ա�������
      Map cardMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
      endorsecontractTdAF.setCardMap(cardMap);
      endorsecontractTdAF.setSexMap(sexMap);
      endorsecontractTdAF.setParamValue(paramValue);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    String tdIsNeedDel=(String)request.getAttribute("tdIsNeedDel");
    endorsecontractTdAF.setIsNeedDel(tdIsNeedDel);
    request.getSession().setAttribute("theEndorsecontractTdAF", endorsecontractTdAF);
    return mapping.findForward("to_endorsecontractTd");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
