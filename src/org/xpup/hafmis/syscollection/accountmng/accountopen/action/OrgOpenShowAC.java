package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscommon.domain.entity.PayBank;
import org.xpup.security.common.domain.Userslogincollbank;

public class OrgOpenShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String officecodeID = request.getParameter("officecode");
      OrgkhAF af = (OrgkhAF) form;

      // ����λ�޸�ʱ����������еõ���λ��ţ��Խ��ѡ����´�ʱ��ˢ�����⡣
      String orgid = null;
      if (request.getParameter("orgid") != null) {
        orgid = request.getParameter("orgid");
        // ���õ����û�id�Ż�request�У���ֹ�û����޸�ʱ��Ϊ���ѡ����´����޷���������ݡ�
        request.setAttribute("orgid", orgid);
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
            .getBusinessService("orgOpenAccountBS", this, mapping
                .getModuleConfig());
        Org org = orgOpenAccountBS.findOPA_zl(new Integer(orgid), securityInfo);
        if (org.getOrgInfo().getPayBank() == null) {
          org.getOrgInfo().setPayBank(new PayBank());
        }
        af.setOrg(org);
        af.setType("2");
        af.getOrg().setPayMode(org.getPayMode());
        request.setAttribute("state", "1");
        request.setAttribute("openstatus", org.getOrgInfo().getOpenstatus());
        request.setAttribute("payMode", org.getPayMode().toString());
      } else {
        af.setType("1");
      }

      af.getOrg().setFirstPayMonth(bizDate.substring(0, 6));
      Map industyMap = BusiTools.listBusiProperty(BusiConst.INDUSTRY);
      af.setIndustyMap(industyMap);
      Map orgpaywayMap = BusiTools.listBusiProperty(BusiConst.ORGPAYWAY);
      af.setOrgpaywayMap(orgpaywayMap);
      Map paymentaccuracyMap = BusiTools
          .listBusiProperty(BusiConst.PAYMENTACCURACY);
      af.setPaymentaccuracyMap(paymentaccuracyMap);
      Map inareaMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      af.setInareaMap(inareaMap);
      Map natureofunitsMap = BusiTools
          .listBusiProperty(BusiConst.NATUREOFUNITS);
      af.setNatureofunitsMap(natureofunitsMap);
      Map authoritiesMap = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      af.setAuthoritiesMap(authoritiesMap);
      if (request.getParameter("officecode") != null) {
        af.getOrg().getOrgInfo().setOfficecode(officecodeID);
      }

      // ȡ���û�Ȩ�ް��´�,��ʾ�������˵���
      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }

      // �õ����´��µĹ鼯����
      OfficeDto officeDtoTest = null;
      List collBankListTest = null;
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      // ����������еõ����´�id
      if (request.getParameter("officecode") != null) {
        collBankListTest = orgOpenAccountBS.queryCollBank(officecodeID);
      } else {
        officeDtoTest = (OfficeDto) officeList.get(0);
        collBankListTest = orgOpenAccountBS.queryCollBank(officeDtoTest
            .getOfficeCode());
      }

      // �жϸ�Ȩ���µ�ĳ�����´���Ӧ�Ĺ鼯����
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        for (int i = 0; i < collBankListTest.size(); i++) {
          CollBank collBank = (CollBank) collBankListTest.get(i);
          if (userslogincollbank.getCollBankId().equals(
              collBank.getCollBankId())) {
            collBankList1.add(new org.apache.struts.util.LabelValueBean(
                userslogincollbank.getCollBankName().toString(),
                userslogincollbank.getCollBankId().toString()));
          }
        }
      }
      /*
       * �ڽ��뵥λ����ǰ�����session�����е�OrgOpenShowListAC�е�pagination��
       * �Ӷ���֤ÿ���ɽڵ����ʱֻ����ʾ��״̬Ϊ�������С��ĵ�λ�б�
       */
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          OrgOpenShowListAC.PAGINATION_KEY);
      if (pagination != null) {
        HttpSession session = request.getSession();
        session.removeAttribute(OrgOpenShowListAC.PAGINATION_KEY);
      }

      saveToken(request);
      request.getSession(true).setAttribute("officeList1", officeList1);
      request.getSession(true).setAttribute("collBankList1", collBankList1);
      af.reset(mapping, request);
      request.setAttribute("orgkhAF", af);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_org_open_new");
  }

}