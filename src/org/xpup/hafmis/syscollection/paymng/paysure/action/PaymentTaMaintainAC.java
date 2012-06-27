package org.xpup.hafmis.syscollection.paymng.paysure.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IDocNumBS;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IPaymentHeadBS;

/**
 * @author ����� 2006-07-4
 */
public class PaymentTaMaintainAC extends LookupDispatchAction {

  private int type = 0;

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure.account", "sure");
    map.put("button.del.account", "del");
    return map;
  }

  /**
   * ����ȷ��
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    // ��ȡȨ�� ����Ա,�������
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String bizDate = securityInfo.getUserInfo().getBizDate();// �������
    IPaymentHeadBS paymentHeadBS = (IPaymentHeadBS) BSUtils.getBusinessService(
        "paymentHeadBS", this, mapping.getModuleConfig());
    IDocNumBS docNumBS = (IDocNumBS) BSUtils.getBusinessService("docNumBS",
        this, mapping.getModuleConfig());
    ActionMessages messages = null;
    String docNum = "";
    try {
      messages = new ActionMessages();
      if (!isTokenValid(request)) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�벻Ҫ��ˮ��",
            false));
        saveErrors(request, messages);
      }
      IdAF idAF = (IdAF) form;
      String id = idAF.getId().toString();
      // ���ݵ�ѡid��ѯ��һ��ʵ��
      // aa301
      PaymentHead paymentHead = paymentHeadBS.findPaymentListById(new Integer(
          id));
     // String officeCode = paymentHead.getOrg().getOrgInfo().getOfficecode();
      try{
        String docNumDocument=docNumBS.getDocNumDesignPara();
        String officeCode="";
        if(docNumDocument.equals("1")){
          if(paymentHead!=null){
          officeCode = paymentHead.getOrg().getOrgInfo().getOfficecode();
          }
        }else{
          if(paymentHead!=null){
          officeCode = paymentHead.getOrg().getOrgInfo().getCollectionBankId();
          }
        }
        System.out.println("f......"+officeCode);
        System.out.println("g......"+bizDate.substring(0,6));
       docNum = docNumBS.getDocNumdocNum(officeCode, bizDate.substring(0,6),securityInfo);
      }catch(Exception e){
        throw new BusinessException("����ƾ֤��ʧ�ܣ�");
      }
      String payStatus = paymentHead.getPayStatus().toString();
      if(!payStatus.equals("2")){
         if(payStatus.equals("3")){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�õ�λҵ���Ѿ���ɣ����ʵ������",
              false));
          saveErrors(request, messages);
        }
         else{
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�ñ�ҵ��״̬���ǵǼ�״̬,�޷����е���ȷ��,���ʵ��",
            false));
        saveErrors(request, messages);
         }
      }
      else{
        try{
          type = paymentHeadBS.SureType(paymentHead, docNum, securityInfo);
        }catch(BusinessException e){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getLocalizedMessage(),
              false));
          saveErrors(request, messages);
          return mapping.findForward("paySuerShowOrgAC.do");// ʧ��
        }
      
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("���˳ɹ���",
          false));
      saveErrors(request, messages);
      }
    } catch (BusinessException b) {
      messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getLocalizedMessage().toString()));
        saveErrors(request, messages);
     }

    if (type == 0) {
      return mapping.findForward("to_otherjsp");// ʧ��
    }
    if (type == 1) {
      return mapping.findForward("paySuerShowOrgAC.do");// �ɹ�
    }
    return null;
  }

  /**
   * ��������ȷ��
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward del(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    IPaymentHeadBS paymentHeadBS = (IPaymentHeadBS) BSUtils.getBusinessService(
        "paymentHeadBS", this, mapping.getModuleConfig());
//  ��ȡȨ�� ����Ա,�������
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo"); 
    String bizDate = securityInfo.getUserInfo().getBizDate();// �������
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      if (!isTokenValid(request)) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�벻Ҫ��ˮ��",
            false));
        saveErrors(request, messages);
      }
      resetToken(request);
      IdAF idAF = (IdAF) form;
      String id = idAF.getId().toString();
      // ���ݵ�ѡid��ѯ��һ��ʵ��
      // aa301
      PaymentHead paymentHead = paymentHeadBS.findPaymentListById(new Integer(
          id));
      String officeCode = paymentHead.getOrg().getOrgInfo().getOfficecode();
      type = paymentHeadBS.DelType(paymentHead, securityInfo);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�������˳ɹ���",
          false));
      saveErrors(request, messages);
    } catch (BusinessException b) {
      b.printStackTrace();
      messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getLocalizedMessage().toString(),
            false));
        saveErrors(request, messages);
     } catch (Exception e) {
       e.printStackTrace();
      }

    if (type == 0) {
      return mapping.findForward("to_otherjsp");// ʧ��
    }
    if (type == 1) {
 
      return mapping.findForward("paySuerShowOrgAC.do");// �ɹ�
    }
    return null;
  }

  /**
   * ��ӡ
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */

}
