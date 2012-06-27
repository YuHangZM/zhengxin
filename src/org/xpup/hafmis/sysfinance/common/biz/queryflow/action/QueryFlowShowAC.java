package org.xpup.hafmis.sysfinance.common.biz.queryflow.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface.IQueryFlowBS;
import org.xpup.hafmis.sysfinance.common.domain.entity.AccountantCredence;

/**
 * Copy Right Information : ��ʾƾ֤��ˮ��ShowAction Goldsoft Project : QueryFlowShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.8
 */
public class QueryFlowShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.common.biz.queryflow.QueryFlowShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      String credenceId = (String) request.getParameter("credenceId");
      IQueryFlowBS queryFlowBS = (IQueryFlowBS) BSUtils.getBusinessService(
          "queryFlowBS", this, mapping.getModuleConfig());
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils.getBusinessService(
          "credenceFillinBS", this, mapping.getModuleConfig());
      String settNum = "";
      request.getSession().removeAttribute(CollectionFlowShowAC.PAGINATION_KEY);
      request.getSession().removeAttribute(LoanFlowShowAC.PAGINATION_KEY);
      // ���credenceId��Ϊ��˵���Ǵ�ƾ֤ά���еĳ����ӹ�����
      // �������Զ�ת��ҳ���й�����
      if(credenceId!=null){
        AccountantCredence accountantCredence = credenceFillinBS.queryById(new Integer(credenceId));
        if(accountantCredence.getReserveC()!=null&&!"".equals(accountantCredence.getReserveC())){
          settNum = accountantCredence.getReserveC();
        }else{
          settNum = accountantCredence.getSettNum();
        }
      }else{
        settNum = request.getParameter("settNum");
      }
      System.out.println(settNum+"====================================>");
      request.setAttribute("settNum", settNum);
      // �ж�ҵ�������ڹ鼯ҵ���Ǹ���ҵ��
      if(settNum == null) {
        return mapping.findForward("");
      }else if(settNum.indexOf(",")!=-1)
        settNum = settNum.split(",")[0];
      boolean is_Biz = queryFlowBS.IssettNum(settNum);
      if (is_Biz) {
        return mapping.findForward("collectionFlowShowAC");
      } else {
        return mapping.findForward("loanFlowShowAC");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return mapping.findForward("");
    }
  }
}
