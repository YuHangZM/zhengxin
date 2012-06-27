package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class EndorsecontractTdFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setHeader("Cache-Control", "no-cache");
    response.setContentType("text/html;charset=UTF-8");
   
    ActionMessages messages = null;
    List loanBankNameList = new ArrayList();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    String text = null;
    String message = "";
    String empId = "";//ְ�����
    String empName = "";//ְ������
    String cardKind = "";//֤������
    String cardNum = "";//֤������
    String sex = "";//�Ա�
    String birthday = "";//��������
    String salary = "";//�¹��ʶ�
    String monthPay = "";//�½ɴ��
    String balance = "";//�˻����
    String empSt = "";//�˻�״̬
    String tel = "";//�̶��绰
    String mobile = "";//�ж��绰
    String homeTel = "";//��ͥ�绰
    String homeAddr = "";//��ͥסַ
    String homeMai = "";//��ͥ�ʱ�
    String orgId = "";//��λ���
    String orgName = "";//��λ����
    String orgAddr = "";//��λ��ַ
    String orgTel = "";//��λ�绰
    String orgMail = "";//��λ�������
    String writeType = "";
    Userslogincollbank userslogincollbank = null;
    PrintWriter out = null;
    try {
      String empid = (String )request.getParameter("empId");//ְ�����
      String orgid = request.getParameter("orgId");
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());

      //____________________________________________________________
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      endorsecontractTdAF = endorsecontractBS.queryAssurerListByEmpId(empid,orgid, pagination, securityInfo, request);
       empId = endorsecontractTdAF.getEmpId();//ְ�����
       empName = endorsecontractTdAF.getEmpName();//ְ������
       cardKind = endorsecontractTdAF.getCardKind();//֤������
       cardNum = endorsecontractTdAF.getCardNum();//֤������
       sex = endorsecontractTdAF.getSex();//�Ա�
       birthday = endorsecontractTdAF.getBirthday();//��������
       salary = endorsecontractTdAF.getSalary();//�¹��ʶ�
       monthPay = endorsecontractTdAF.getMonthPay();//�½ɴ��
       balance = endorsecontractTdAF.getBalance();//�˻����
       empSt = endorsecontractTdAF.getEmpSt();//�˻�״̬
       tel = endorsecontractTdAF.getTel();//�̶��绰
       mobile = endorsecontractTdAF.getMobile();//�ж��绰
       homeTel = endorsecontractTdAF.getHomeTel();//��ͥ�绰
       homeAddr = endorsecontractTdAF.getHomeAddr();//��ͥסַ
       homeMai = endorsecontractTdAF.getHomeMai();//��ͥ�ʱ�
       orgId = endorsecontractTdAF.getOrgId();//��λ���
       orgName = endorsecontractTdAF.getOrgName();//��λ����
       orgAddr = endorsecontractTdAF.getOrgAddr();//��λ��ַ
       orgTel = endorsecontractTdAF.getOrgTel();//��λ�绰
       orgMail = endorsecontractTdAF.getOrgMail();//��λ�������
     
    } catch (BusinessException bex) {
      message = bex.getMessage();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString()));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    text = "display('" + empId + "','" + empName + "','"
        + cardKind + "','" + cardNum + "'," + "'" + sex
        + "','" + birthday + "','" + salary + "','" + monthPay + "','"
        + balance + "'," + "'" + empSt + "','" + tel
        + "','" + mobile + "','" + homeTel + "','"
        + homeAddr + "','" + homeMai +"','" +orgId+"','" +orgName+"','" +orgAddr+"','" +orgTel+"','" +orgMail+"'";
    text += ",'" + message + "');";
    
//    text = "display('" + empId + "','" + empName +"'";
//text += ",'" + message + "');";
//    out.flush();
//    out.close();
    response.getWriter().write(text);
    response.getWriter().close();
    
    return null;
  }

  protected String getPaginationKey() {
    return EndorsecontractTaShowAC.PAGINATION_KEY;
  }
}
