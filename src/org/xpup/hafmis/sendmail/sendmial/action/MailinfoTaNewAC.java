package org.xpup.hafmis.sendmail.sendmial.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sendmail.sendmial.bsinterface.IMailinfoBS;
import org.xpup.hafmis.sendmail.sendmial.form.MailinfoAF;

public class MailinfoTaNewAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    MailinfoAF mailinfoAF=(MailinfoAF)form;
    ActionMessages messages = null;
    try{
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    IMailinfoBS mailinfoBS = (IMailinfoBS) BSUtils.getBusinessService("mailinfoBS", this,
        mapping.getModuleConfig());
    String mailId=mailinfoAF.getMailId();  //����ʹ�õ���Ϣid
    
    String addresserMail=mailinfoAF.getAddresserMail();// �������ʻ�

    String addresserPassword=mailinfoAF.getAddresserPassword();// �������ʻ�����

    String addresseeA=mailinfoAF.getAddresseeA();// �ռ�������A

    String addresseeB=mailinfoAF.getAddresseeB();// �ռ�������B

    String subjectName=mailinfoAF.getSubjectName();// ��������
    String info=mailinfoBS.addMailinfo(mailId,addresserMail,addresserPassword,addresseeA,addresseeB,subjectName,securityInfo);
    if(info.equals("pass")){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("���ռ��˺�ʵ�Ƿ��ܵ��ʼ���", false));
      saveErrors(request, messages);
    }
  } catch (BusinessException bex) {
    messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
        .getMessage(), false));
    saveErrors(request, messages);
  } catch (Exception e) {
    e.printStackTrace();
  }
    return mapping.findForward("to_mailinfoshow");
  }
}