package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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
import org.xpup.common.util.imp.Factory;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.bsinterface.IPalindromeImpSwitchBS;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.OneTailDTO;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.PalindromeImpSwitchHeadDTO;
import org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.TwoTailDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaImportAF;

public class LoancallbackTaImportAC extends Action {
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
    LoancallbackTaImportAF forms = (LoancallbackTaImportAF) form;
    ActionMessages messages = null;
    ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
        .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
    IPalindromeImpSwitchBS palindromeImpSwitchBS = (IPalindromeImpSwitchBS) BSUtils
        .getBusinessService("palindromeImpSwitchBS", this, mapping
            .getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        LoancallbackTaShowAC.PAGINATION_KEY);
    FormFile file = forms.getTheFile();
    String filename = file.getFileName();
    String fileType = file.getFileName().substring(
        file.getFileName().lastIndexOf(".") + 1);// ---------------�����¼�
    if (filename.equals("")) { // �ж��Ƿ�Ϊ��
      return mapping.findForward("impFail");
    } else if (!(filename.endsWith(".txt") || filename.endsWith(".xls"))) {// --------�����ѱ�
      return mapping.findForward("impFail");
    } else if (filename.toString().length() < 1) {
      return (mapping.findForward("�ļ��޺�׺"));
    }
    InputStream stream = null;
    if ("txt".equals(fileType)) {// ---------------�����¼� (�ж��ļ���ʽ txt)

      try {
        List headList = null;
        List infoList = null;
        stream = file.getInputStream();
        List importList = checkData(stream); // ********ͨ�������������Ϣ
        LoancallbackTaImportDTO dto = new LoancallbackTaImportDTO();
        dto = (LoancallbackTaImportDTO) importList.get(0);
        String bankId = dto.getLoanBankId();
        headList = new ArrayList();
        headList.add(dto);
//        infoList = (List) importList.remove(0);//ȥ��ͷ��Ϣ
//        List list = palindromeImpSwitchBS.switchImpList(bankId, headList,
//            infoList, fileType);// ͨ�����нӿںϲ�
        Integer headId = loancallbackBS.adCallbackBatch(importList, securityInfo);
        pagination.getQueryCriterions().put("headId", headId.toString());

      } catch (BusinessException b) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("����ʧ�ܣ�"
            + b.getMessage(), false));
        saveErrors(request, messages);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else { // ---------------�����¼� (�ж��ļ���ʽ xsl)
      ServletContext context = request.getSession().getServletContext();// ---------------�����¼�
      String jasperSrc = context.getRealPath("/impXml/"
          + "palindromeImpSwitchHead_imp.xml");// ---------------�����¼�
      // ����.xml DTO
      Factory faxtory = new Factory();// ---------------�����¼�
      File xmlfile = new File(jasperSrc);// ---------------�����¼�
      Map info = null;// ---------------�����¼�
      try {// ---------------�����¼�
        info = faxtory.getInfomation(xmlfile, file.getInputStream(),
            "org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.");// ---------------�����¼�
        List palindromeImpSwitchHead = new ArrayList();// ---------------�����¼�
        palindromeImpSwitchHead = (List) info.get("PalindromeImpSwitchHead");// ---------------�����¼�
                                                                              // ͷ��Ϣ

        PalindromeImpSwitchHeadDTO palindromeImpSwitchHeadDTO = (PalindromeImpSwitchHeadDTO) palindromeImpSwitchHead
            .get(1);
        String bankId = palindromeImpSwitchHeadDTO.getBankId().trim();
        /**
         * BusiTools.getBusiValue(i+1, BusiConst.TENNUMBER) bankId+"_imp.xml"
         * Tail
         */
//         int str = BusiTools.getBusiKey(bankId,BusiConst.TENNUMBER);
        String str = BusiTools.getBusiValue(Integer.parseInt(bankId),
            BusiConst.TENNUMBER);
        String newJasperSrc = context
            .getRealPath("/impXml/" + str + "_imp.xml");
        Factory newFaxtory = new Factory();
        File newXmlfile = new File(newJasperSrc);
        Map newInfo = null;

        newInfo = newFaxtory.getInfomation(newXmlfile, file.getInputStream(),
            "org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto.");
        List headList = new ArrayList();
        headList = (List) newInfo.get("PalindromeImpSwitchHead");
        PalindromeImpSwitchHeadDTO palindromeImpSwitchHeadT = (PalindromeImpSwitchHeadDTO) headList
            .get(1);
        List infoList = new ArrayList();
        infoList = (List) newInfo.get(str + "Tail");
        // if("One".equals(str)){
        // OneTailDTO oneTailDTO = new OneTailDTO();
        // System.out.println("--------"+infoList.size());
        // oneTailDTO = (OneTailDTO)infoList.get(1);
        // String emocond = oneTailDTO.getEmpcode();
        // System.out.println("======:"+emocond);
        // }else if("Two".equals(str)){
        // TwoTailDTO twoTailDTO = new TwoTailDTO();
        // twoTailDTO = (TwoTailDTO)infoList.get(1);
        // String emocond = twoTailDTO.getOne();
        // System.out.println("======:"+emocond);
        // }

        List list = palindromeImpSwitchBS.switchImpList(bankId, headList,
            infoList, fileType);
        Integer headId = loancallbackBS.adCallbackBatch(list, securityInfo);
      } catch (Exception e) {// ---------------�����¼�
        e.printStackTrace();
      }// ---------------�����¼�
    }

    return mapping.findForward("loancallbackTaShowAC");
  }

  public List checkData(InputStream inputstream) throws Exception {
    // Session sess=ThreadLocalSessionManager.getSession();
    int line = 1;

    List result = new ArrayList();

    try {
      FileReader reader = new FileReader(inputstream);// ?

      reader.setDelimiter("|");
      List list = reader.getList();
      // System.out.println("---->" + list.get(0));
      // System.out.println("---->" + list.get(2));
      Iterator it = null;
      if (list != null) {
        it = list.iterator();
        while (it.hasNext()) {
          LoancallbackTaImportDTO dto = new LoancallbackTaImportDTO();
          List slist = (List) (it.next());
          // System.out.println("---->" + slist.size());
          if (line == 1) {
            if (slist != null) {
              if (slist.size() != 14) {
                // System.out.println("�ļ�ͷ��Ϣ���ܴ������⣡");
              }
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null) {
                dto.setBizDate(slist.get(0).toString().trim()); // �ۿ�����
                dto.setLoanBankId(slist.get(1).toString().trim()); // �����к�
                dto.setBizType(slist.get(2).toString().trim());// ҵ������
                // System.out.println("�ļ�ͷ��Ϣ��" + slist.get(0).toString().trim()
                // + "|" + slist.get(1).toString().trim() + "|"
                // + slist.get(2).toString().trim());
              }
            }
          } else {
            if (slist != null) {
              // log.info("�м���=" + slist.size());
              // System.out.println(slist.size());
              if (slist.size() != 14) {
                // System.out.println("��Ϣ������������");
              }
              if (slist.get(0) != null && slist.get(1) != null
                  && slist.get(2) != null && slist.get(3) != null
                  && slist.get(4) != null && slist.get(5) != null
                  && slist.get(6) != null && slist.get(7) != null
                  && slist.get(8) != null && slist.get(9) != null
                  && slist.get(10) != null && slist.get(11) != null
                  && slist.get(12) != null && slist.get(13) != null) {
                dto.setLoanKouAcc(slist.get(0).toString().trim()); // �ۿ��ʺ�
                // System.out.println("�ۿ��ʺ�" + slist.get(0).toString().trim());
                dto.setBorrowerName(slist.get(1).toString().trim()); // ���������
                dto.setYearMonth(slist.get(2).toString().trim()); // ��������
                dto.setRealCorpus(slist.get(3).toString().trim());// ʵ����������
                dto.setRealOverdueCorpus(slist.get(4).toString().trim());// ʵ�����ڱ���
                dto.setRealInterest(slist.get(5).toString().trim());// ʵ��������Ϣ
                dto.setRealOverdueInterest(slist.get(6).toString().trim());// ʵ��������Ϣ
                dto.setRealPunishInterest(slist.get(7).toString().trim());// ʵ�۷�Ϣ
                dto.setNobackCorpus(slist.get(8).toString().trim());// δ����������
                dto.setNobackOverdueCorpus(slist.get(9).toString().trim());// δ�����ڱ���
                dto.setNobackInterest(slist.get(10).toString().trim());// δ��������Ϣ
                dto.setNobackOverdueInterest(slist.get(11).toString().trim());// δ��������Ϣ
                dto.setNobackPunishInterest(slist.get(12).toString().trim());// δ����Ϣ
                dto.setDeadLine(slist.get(13).toString().trim());// ��ǰ�����ʣ������
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