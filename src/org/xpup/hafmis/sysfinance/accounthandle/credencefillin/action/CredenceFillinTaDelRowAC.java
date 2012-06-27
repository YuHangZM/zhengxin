package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.dto.CredenceFillinTaShowDTO;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTaAF;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopListDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;

public class CredenceFillinTaDelRowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {  
    try {
      // �õ�Ҫɾ���е�����
      String index = request.getParameter("index");
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
      .getBusinessService("credenceFillinBS", this, mapping
          .getModuleConfig());
      CredenceFillinTaAF credenceFillinTaAF=(CredenceFillinTaAF)form;
      String listAllContent=credenceFillinTaAF.getListAllContent(); 
      // �жϽ�Ҫɾ������
      List list = new ArrayList();
      String[] listContent = listAllContent.split(";");
      for (int i = 0; i < listContent.length; i++) {
        String[] listProperty = listContent[i].split(",");
        CredencePopListDTO credencePopListDTO = new CredencePopListDTO();
        credencePopListDTO.setSummay(listProperty[0]);
        credencePopListDTO.setFreeSummay(listProperty[1]);
        credencePopListDTO.setSubjectCode(listProperty[2]);
        credencePopListDTO.setDebit(new BigDecimal(listProperty[3]));
        credencePopListDTO.setCredit(new BigDecimal(listProperty[4]));
        list.add(credencePopListDTO);
      }
      if (index!=null&&index!=""&&!index.equals("0")) {
        list.remove(new Integer(index).intValue()-1);
      }
      
      Object[] obj = credenceFillinBS.findDelRowList(list);
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

      request.setAttribute("list", (List)obj[0]);
      request.setAttribute("sumDebit", (BigDecimal) obj[1]);
      request.setAttribute("sumCredit", (BigDecimal) obj[2]);
      request.setAttribute("type", "3");
      
      request.setAttribute("credenceFillinTaAF", credenceFillinTaAF);
      request.setAttribute("officeList", officeList);
      request.setAttribute("credenceCharacterList", credenceCharacterList);
      request.setAttribute("settTypeList", settTypeList);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencefillinta_show");
  }

}
