package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.FileReader;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTaImportAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaImportAF;

public class BadDebtDestroyTaImportAC extends Action {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    BadDebtDestroyTaImportAF forms = (BadDebtDestroyTaImportAF) form;
    ActionMessages messages = null;
    FormFile file = forms.getTheFile();
    String filename = file.getFileName();
    if (filename.equals("")) { // �ж��Ƿ�Ϊ��
      return mapping.findForward("impFail");
    } else if (!filename.endsWith(".txt")) {
      return mapping.findForward("impFail");
    } else if (filename.toString().length() < 1) {
      return (mapping.findForward("�ļ��޺�׺"));
    }
    InputStream stream = null;
    try {
      stream = file.getInputStream();
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Pagination pagination=(Pagination)request.getSession().getAttribute(BadDebtDestroyTaShowAC.PAGINATION_KEY);
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      List importList = checkData(stream); // ********ͨ�������������Ϣ
      Integer headId = badDebtDestroyBS.adCallbackBatch(importList, securityInfo);
      pagination.getQueryCriterions().put("headId", headId.toString());
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("����ʧ�ܣ�"
          + b.getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("badDebtDestroyTaShowAC");
  }

  public List checkData(InputStream inputstream) throws Exception {
    int line = 1;
    List result = new ArrayList();
    try {
      FileReader reader = new FileReader(inputstream);
      reader.setDelimiter("|");
      List list = reader.getList();
      Iterator it = null;
      if (list != null) {
        it = list.iterator();
        while (it.hasNext()) {
          LoancallbackTaImportDTO dto = new LoancallbackTaImportDTO();
          List slist = (List) (it.next());
          if (line == 1) {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null) {
                 dto.setBizDate(slist.get(0).toString().trim()); //�ۿ�����
                 dto.setLoanBankId(slist.get(1).toString().trim()); //�����к�
                 dto.setBizType(slist.get(2).toString().trim());//ҵ������
              }
            }
          } else {
            if (slist != null) {
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null && slist.get(3) != null
                  && slist.get(4) != null&& slist.get(5) != null
                  && slist.get(6) != null && slist.get(7) != null
                  && slist.get(8) != null&& slist.get(9) != null
                  && slist.get(10) != null && slist.get(11) != null
                  && slist.get(12) != null&& slist.get(13) != null) {
                 dto.setLoanKouAcc(slist.get(0).toString().trim()); //�ۿ��ʺ�
                 dto.setBorrowerName(slist.get(1).toString().trim()); //���������
                 dto.setYearMonth(slist.get(2).toString().trim()); //��������
                 dto.setRealCorpus(slist.get(3).toString().trim());//ʵ����������
                 dto.setRealOverdueCorpus(slist.get(4).toString().trim());//ʵ�����ڱ���
                 dto.setRealInterest(slist.get(5).toString().trim());//ʵ��������Ϣ
                 dto.setRealOverdueInterest(slist.get(6).toString().trim());//ʵ��������Ϣ
                 dto.setRealPunishInterest(slist.get(7).toString().trim());//ʵ�۷�Ϣ
                 dto.setNobackCorpus(slist.get(8).toString().trim());//δ����������
                 dto.setNobackOverdueCorpus(slist.get(9).toString().trim());//δ�����ڱ���
                 dto.setNobackInterest(slist.get(10).toString().trim());//δ��������Ϣ
                 dto.setNobackOverdueInterest(slist.get(11).toString().trim());//δ��������Ϣ
                 dto.setNobackPunishInterest(slist.get(12).toString().trim());//δ����Ϣ
                 dto.setDeadLine(slist.get(13).toString().trim());//��ǰ�����ʣ������
              }
            }
          }
          result.add(dto);
          line++;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}