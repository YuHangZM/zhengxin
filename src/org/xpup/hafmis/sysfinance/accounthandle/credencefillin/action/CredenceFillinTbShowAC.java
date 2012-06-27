package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.math.BigDecimal;
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
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbFindDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTbListDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTbAF;

/**
 * Copy Right Information : ��ʾ�Զ�ת���б��Action Goldsoft Project :
 * CredenceFillinTbShowAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.10.17
 */
public class CredenceFillinTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Object[] obj = new Object[3];
    CredenceFillinTbAF credenceFillinTbAF = new CredenceFillinTbAF();
    List banklist = new ArrayList();
    List collbank = new ArrayList();
    String rowArray = request.getParameter("rowArray");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      List temp_officeList = new ArrayList();
      // �õ����㷽ʽ
      int settType = securityInfo.getFnSettleType();
      if (settType == 1) {
        // ���Ϊ�������㣬ȡ������ԱȨ�ް��´�
        List officeList = securityInfo.getOfficeList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          temp_officeList.add(officedto.getOfficeCode());
        }
      } else {
        // ���Ϊͬһ���㣬�����ް��´�
        List officeList = securityInfo.getAllOfficeList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          temp_officeList.add(officedto.getOfficeCode());
        }
      }
      // ��ù鼯����
      collbank = credenceFillinBS.getCollBank();
      Iterator it = collbank.iterator();
      while (it.hasNext()) {
        CollBank bank = (CollBank) it.next();
        banklist.add(new org.apache.struts.util.LabelValueBean(bank
            .getCollBankName().toString(), bank.getCollBankId().toString()));
      }
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      if (pagination == null) {
        pagination = getPagination(paginationKey, request);
      } else {
        PaginationUtils.updatePagination(pagination, request);
        obj = credenceFillinBS.findCredenceFillinTbList(pagination,
            temp_officeList, securityInfo);
      }

      // ��ö���еõ�ҵ��״̬
      Map bizStMap = BusiTools.listBusiProperty(BusiConst.CREDSTATE);
      // ��ö���еõ�ҵ��״̬
      Map bizTypeMap = BusiTools.listBusiProperty(BusiConst.FNBUSINESSTYPE);
      // ��ʼ����Ĭ��Ϊ����ƾ֤����
      String fnDate = credenceFillinBS.getCredenceDate(null, securityInfo);
      credenceFillinTbAF.getCredenceFillinTbFindDTO().setSettDateStart(fnDate);
      // wshuang add
      List selectedList = new ArrayList();
      String ids = "";
      if (rowArray != null) {
        String[] str = rowArray.split("-");
        for (int i = 0; i < str.length; i++) {
          ids += str[i] + "-";
          selectedList.add(str[i]);
        }
      }
      if (ids.equals("-"))
        ids = "";
      request.setAttribute("ids", ids);
      request.setAttribute("selectedArray", selectedList);
      // end
      credenceFillinTbAF.setBizStMap(bizStMap);
      credenceFillinTbAF.setBizTypeMap(bizTypeMap);
      credenceFillinTbAF.setList((List) obj[0]);
      BigDecimal moneyall = new BigDecimal(0.00);
      if (obj[2] != null) {
        List slist = (List) obj[2];
        for (int i = 0; i < slist.size(); i++) {
          CredenceFillinTbListDTO dto = new CredenceFillinTbListDTO();
          dto = (CredenceFillinTbListDTO) slist.get(i);
          moneyall = moneyall.add(dto.getSumOccurMoney());
        }
      }
      credenceFillinTbAF.setMoneyall(moneyall);

    } catch (Exception e) {
      e.printStackTrace();
    }
    // ȡ���б���ȫ���Ľ���ţ�����ȫ��ת��
    request.setAttribute("bankList", banklist);
    request.getSession().setAttribute("allSettNum", (String[]) obj[1]);
    request.setAttribute("credenceFillinTbAF", credenceFillinTbAF);
    return mapping.findForward("to_credencefillintb_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "res.notenum", "asc",
          new HashMap(0));
      // �����һ�ν�������ʾĬ�ϵ��б�
      CredenceFillinTbFindDTO credenceFillinTbFindDTO = new CredenceFillinTbFindDTO();
      credenceFillinTbFindDTO.setBizSt("A");
      pagination.getQueryCriterions().put("credenceFillinTbFindDTO",
          credenceFillinTbFindDTO);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
