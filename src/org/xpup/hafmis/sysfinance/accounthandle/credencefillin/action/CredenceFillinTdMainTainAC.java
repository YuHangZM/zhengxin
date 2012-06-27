package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTaAF;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;

/**
 * Copy Right Information : ��ʾƾ֤¼��ά���б��MainTain Goldsoft Project :
 * CredenceFillinTdMainTainAC
 * 
 * @Version : v1.0
 * @author : ���Ʒ�
 * @Date : 2007.11.1
 */
public class CredenceFillinTdMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delectCredenceInfo");
    map.put("button.deleteall", "delectAllCredenceInfo");
    map.put("button.print", "printCredenceInfo");
    map.put("button.update", "modifyCredenceInfo");
    map.put("button.continuum.print", "printContinuumCredenceInfo");
    return map;
  }

  /**
   * �޸�ƾ֤�ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward modifyCredenceInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String[] temp_array1 = idAF.getId().toString().split(",");
      // ƾ֤��
      String credenceNum = temp_array1[0];
      // ƾ֤����
      String credenceDate = temp_array1[3];
      // ���´�
      String office = temp_array1[4];
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      Object[] obj = credenceFillinBS.findAccountantCredence(credenceNum,
          securityInfo,credenceDate,office);
      List list = (List) obj[0];
      CredencePopInfoDTO credencePopInfoDTO = (CredencePopInfoDTO) obj[1];

      CredenceFillinTaAF credenceFillinTaAF = new CredenceFillinTaAF();
      // ���ݵ�ǰ�û��õ���Ȩ�ް��´�
      List temp_officeList = securityInfo.getOfficeList();
      List officeList = null;
      officeList = new ArrayList();
      OfficeDto officedto = null;
      Iterator it = temp_officeList.iterator();
      while (it.hasNext()) {
        officedto = (OfficeDto) it.next();
        officeList.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      // ����ƾ֤������ѡ����
      List credenceCharacterList = null;
      List temp_credenceCharacterList = credenceFillinBS
          .findCredenceCharacterList(securityInfo);
      credenceCharacterList = new ArrayList();
      if (temp_credenceCharacterList.size() > 0) {
        BookParameterDTO bookParameterDTO = null;
        Iterator itr1 = temp_credenceCharacterList.iterator();
        while (itr1.hasNext()) {
          bookParameterDTO = (BookParameterDTO) itr1.next();
          credenceCharacterList.add(new org.apache.struts.util.LabelValueBean(
              bookParameterDTO.getBookParameterName(), bookParameterDTO
                  .getBookParameterId()));
        }
      }
      // �������㷽ʽ����ѡ����
      List settTypeList = null;
      List temp_settTypeList = credenceFillinBS.findSettTypeList(securityInfo);
      settTypeList = new ArrayList();
      if (temp_settTypeList.size() > 0) {
        BookParameterDTO bookParameterDTO = null;
        Iterator itr1 = temp_settTypeList.iterator();
        while (itr1.hasNext()) {
          bookParameterDTO = (BookParameterDTO) itr1.next();
          settTypeList.add(new org.apache.struts.util.LabelValueBean(
              bookParameterDTO.getBookParameterName(), bookParameterDTO
                  .getBookParameterId()));
        }
      }
      // ������ǰ����Ա������
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setMakebill(
          credencePopInfoDTO.getMakebill());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setBookId(
          securityInfo.getBookId());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setOffice(
          credencePopInfoDTO.getOffice());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setCredenceCharacter(
          credencePopInfoDTO.getCredenceCharacter());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setCredenceNum(
          credencePopInfoDTO.getCredenceNum());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setOldCredenceNum(
          credencePopInfoDTO.getOldCredenceNum());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setChargeUpDate(
          credencePopInfoDTO.getCredenceDate());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setSettType(
          credencePopInfoDTO.getSettType());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setSettNum(
          credencePopInfoDTO.getSettNum());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setSettDate(
          credencePopInfoDTO.getSettDate());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setCheckpsn(
          credencePopInfoDTO.getCheckpsn());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setClearpsn(
          credencePopInfoDTO.getClearpsn());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setAccountpsn(
          credencePopInfoDTO.getAccountpsn());
      //���ת��ת���ʹ���ŵ�ҵ��������ѡ������ֵ���Ե���Ҫ�����ֵ��������
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setReserveA(
          credencePopInfoDTO.getReserveA());
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setReserveB(
          credencePopInfoDTO.getReserveB());
      //��ѡC�ֶ������������ʽ��ҳ�汣��
      credenceFillinTaAF.getCredenceFillinTaShowDTO().setReserveC(
          credencePopInfoDTO.getReserveC());
      credenceFillinTaAF.getCredenceFillinTaShowDTO()
          .setAccountCharge(credencePopInfoDTO.getAccountCharge());
      credenceFillinTaAF.setTypeButton("1");

      request.setAttribute("list", list);
      request.setAttribute("credencePopInfoDTO", (CredencePopInfoDTO) obj[1]);
      request.setAttribute("sumDebit", (BigDecimal) obj[2]);
      request.setAttribute("sumCredit", (BigDecimal) obj[3]);
      request.setAttribute("type", "1");

      request.setAttribute("credenceFillinTaAF", credenceFillinTaAF);
      request.setAttribute("officeList", officeList);
      request.setAttribute("credenceCharacterList", credenceCharacterList);
      request.setAttribute("settTypeList", settTypeList);
      // ���޸�ǰ������,ƾ֤��,���²��ڷ���session���������޸�ʱɾ��
      request.getSession().setAttribute("modify_chargeoldupcate", credencePopInfoDTO.getCredenceDate());
      request.getSession().setAttribute("modify_credencenum", credencePopInfoDTO.getCredenceNum());
      request.getSession().setAttribute("modify_oldoffice", credencePopInfoDTO.getOffice());
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�޸�ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_credencefillinta_show");
  }

  /**
   * ɾ��ƾ֤�ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward delectCredenceInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String[] temp_array = idAF.getId().toString().split(",");
      String credenceId = temp_array[1];
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());

      credenceFillinBS.delectCredenceInfo(credenceId, securityInfo);
    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ɾ��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinTdShowdAC");
  }

  /**
   * ȫ��ɾ���ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward delectAllCredenceInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {

      List countList = (List) request.getSession().getAttribute("countList");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());

      credenceFillinBS.delectAllCredenceInfo(countList, securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ɾ��ʧ�ܣ�",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("credenceFillinTdShowdAC");
  }
  /**
   * ��ӡ�ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printCredenceInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      request.setAttribute("url", "credenceFillinTdShowdAC.do");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencefillintd_print");
  }
  
  /**
   * ������ӡ�ķ���
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printContinuumCredenceInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try {
      // �õ���ӡҪ�õ���״̬
      String type = (String) request.getSession().getAttribute("print_type");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          CredenceFillinTdShowdAC.PAGINATION_KEY);
      List continuumPrintList = credenceFillinBS.findContinuumPrintList(
          pagination, type, securityInfo);
      String bizDate = securityInfo.getUserInfo().getFbizDate();
      request.setAttribute("continuumPrintList", continuumPrintList);
      request.setAttribute("bizDate", bizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("url", "credenceFillinTdShowdAC.do");
    String bookid = securityInfo.getBookId();
    if(bookid.equals("2")){
      return mapping.findForward("credencefillintd_continuumprint_jj");
    }
    return mapping.findForward("to_credencefillintd_continuumprint");
  }
  
}
