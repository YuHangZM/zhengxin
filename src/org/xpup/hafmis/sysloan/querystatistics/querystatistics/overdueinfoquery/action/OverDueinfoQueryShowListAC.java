package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface.IOverDueinfoQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryShowListAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class OverDueinfoQueryShowListAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action.OverDueinfoQueryShowListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      IOverDueinfoQueryBS overDueinfoQueryBS = (IOverDueinfoQueryBS) BSUtils
          .getBusinessService("overDueinfoQueryBS", this, mapping
              .getModuleConfig());
      OverDueinfoQueryShowListAF overDueinfoQueryShowListAF = new OverDueinfoQueryShowListAF();

      // ����ҳ����Ҫ��Ȩ�޵ģ���Ȩ����ȡ����������Ϊ������������Ϊ�����Դ������ư�ť���ԣ�Ҫ��action������״̬��
      // ���������1.����Ϊ��2.����Ϊ��
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      // ����Ȩ���еĻ��������ж�����Ϊ��
      String plLoanReturnType = "";
      if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
        plLoanReturnType = "1";// ����Ϊ��
      } else if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
        plLoanReturnType = "2";// ����Ϊ��
      }
      overDueinfoQueryShowListAF = overDueinfoQueryBS
          .queryShowListByCriterions(pagination, securityInfo);

      // ����´�
      List temp_officeList = securityInfo.getOfficeList();
      List li = new ArrayList();
      for (int i = 0; i < temp_officeList.size(); i++) {
        OfficeDto officedto = null;
        officedto = (OfficeDto) temp_officeList.get(i);
        li.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList", li);

      // �ſ�����������
      List loanBankNameList = new ArrayList();
      List bangkList = securityInfo.getCollBankList();
      Userslogincollbank userslogincollbank = null;
      Iterator bank = bangkList.iterator();
      while (bank.hasNext()) {
        userslogincollbank = (Userslogincollbank) bank.next();
        loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
            userslogincollbank.getCollBankName(), userslogincollbank
                .getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("loanBankNameList",
          loanBankNameList);

      // �󵣱���˾
      List temp_assistantorgList = overDueinfoQueryShowListAF
          .getAssistantorgList();
      List assistantorgList = new ArrayList();
      for (int i = 0; i < temp_assistantorgList.size(); i++) {
        AssistantOrg assistantOrg = null;
        assistantOrg = (AssistantOrg) temp_assistantorgList.get(i);
        assistantorgList.add(new org.apache.struts.util.LabelValueBean(
            assistantOrg.getAssistantOrgName(), assistantOrg
                .getAssistantOrgId().toString()));
      }
      Map yesNoMap = BusiTools.listBusiProperty(BusiConst.YesNo);
      overDueinfoQueryShowListAF.setYesNoMap(yesNoMap);
      overDueinfoQueryShowListAF.setHousetypemap(BusiTools
          .listBusiProperty(BusiConst.PLHOUSETYPE));
      // ���ڵ���δ�廹
      List isNoAcquittanceList = new ArrayList();
      isNoAcquittanceList.add(new org.apache.struts.util.LabelValueBean("��",
          "��"));
      isNoAcquittanceList.add(new org.apache.struts.util.LabelValueBean("��",
          "��"));
      request.getSession(true).setAttribute("isNoAcquittanceList",
          isNoAcquittanceList);

      request.getSession(true).setAttribute("assistantorgList",
          assistantorgList);
      request.setAttribute("overDueinfoQueryShowListAF",
          overDueinfoQueryShowListAF);

      request.setAttribute("plLoanReturnType", plLoanReturnType);
      overDueinfoQueryShowListAF.reset(mapping, request);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_overdueinfoquery_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      Map m = new HashMap(0);

      pagination = new Pagination(0, 10, 1, "p111.contract_id", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
