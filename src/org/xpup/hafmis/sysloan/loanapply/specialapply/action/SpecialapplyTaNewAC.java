package org.xpup.hafmis.sysloan.loanapply.specialapply.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.specialapply.bsinterface.ISpecialapplyBS;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.form.SpecialapplyNewAF;

/**
 * MyEclipse Struts Creation date: 09-27-2007 XDoclet definition:
 * 
 * @struts.action path="/specialapplyTaNewAC" name="SpecialapplyNewAF"
 *                scope="request" validate="true"
 */
public class SpecialapplyTaNewAC extends Action {
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

    SpecialapplyNewAF specialapplyNewAF = (SpecialapplyNewAF) form;
    try {
      String orgId = request.getParameter("org_Id");
      ISpecialapplyBS specialapplyBS = (ISpecialapplyBS) BSUtils
          .getBusinessService("specialapplyBS", this, mapping.getModuleConfig());

      // Session �����ó�Ȩ�޺�����
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String operator = securityInfo.getUserName();// ����Ա
      String userId = securityInfo.getUserIp();// Ip

      SpecialapplyDTO specialapplyDTO = null;
      ActionMessages messages = null;
      SpecialBorrower specialBorrower = null;
      BigDecimal maxMoney = securityInfo.getUserInfo().getCheckMoney();
      System.out.println("�ò���Ա��Ԥ����Ϊ==========>" + maxMoney);
      if (maxMoney == null
          || Float.parseFloat(specialapplyNewAF.getLoanMoney().toString()) > maxMoney
              .floatValue()) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "����Ľ��ܳ���Ԥ����! ", false));
        saveErrors(request, messages);
        return mapping.findForward("specialapplyTaShowAC");
      }
      // �㰴ť��������ֵ
      if (specialapplyNewAF.getPrivilegeBorrowerId().trim() != null
          && !specialapplyNewAF.getPrivilegeBorrowerId().trim().equals("")) {
        // ����������Ϣ��PL112�Ƿ���ڽ����������֤���������¼��ļ�¼
        boolean is_temp = specialapplyBS.isCheckNameANDCardNum_SpecialBorrower(
            specialapplyNewAF.getBorrowerName().trim(),
            specialapplyNewAF.getCardNum().trim()).booleanValue();
        // PL112����û������
        if (!is_temp) {
          // AA002����鿴 �Ƿ���ڽ����������֤���������¼��ļ�¼
          specialapplyDTO = specialapplyBS.findSpecialapplyInfoById(
              specialapplyNewAF.getPrivilegeBorrowerId().trim(), orgId);
          // AA002�����м�¼
          if (specialapplyDTO != null) {
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            specialapplyDTO.setEmpId(specialapplyNewAF.getPrivilegeBorrowerId()
                .trim());
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // ���� ����������Ϣ��PL112�� ���룺������־PL021
            specialapplyBS
                .insertSpecialApplyInfo(specialapplyDTO, securityInfo);

          } else {// AA002����û�м�¼
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "�û�����û���û���д����Ϣ��", false));
            saveErrors(request, messages);
            return mapping.findForward("specialapplyTaShowAC");
          }
        } else {// PL112����������
          specialBorrower = specialapplyBS.findSpecialBorrowerStutasByEmpId(
              specialapplyNewAF.getPrivilegeBorrowerId().trim(),
              specialapplyNewAF.getOrgId().toString());
          // PL112���ݼ�¼ ״̬��Ϊ��
          if (specialBorrower == null) {
            // ��ʾ ״̬Ϊһ �����ݼ�¼
            specialBorrower = specialapplyBS
                .findSpecialBorrowerStutasByEmpIdTop1(specialapplyNewAF
                    .getPrivilegeBorrowerId().trim(), specialapplyNewAF
                    .getOrgId().toString());

            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialBorrower.getBorrowerName());
            specialapplyDTO.setCardKind(specialBorrower.getCardKind());
            specialapplyDTO.setCardNum(specialBorrower.getCardNum());
            specialapplyDTO.setOrgId(specialBorrower.getOrgId().toString());
            specialapplyDTO.setOrgName(specialBorrower.getOrgName());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialapplyNewAF.getPrivilegeBorrowerId()
                .trim());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // �����������ơ������˺�
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // ���� ����������Ϣ��PL112�� ���룺������־PL021
            specialapplyBS
                .insertSpecialApplyInfo(specialapplyDTO, securityInfo);
          } else {// PL112���ݼ�¼ ״̬������
            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialapplyNewAF.getBorrowerName()
                .trim());
            specialapplyDTO.setCardKind(specialapplyNewAF.getCardKind().trim());
            specialapplyDTO.setCardNum(specialapplyNewAF.getCardNum().trim());
            specialapplyDTO.setOrgId(specialapplyNewAF.getOrgId().toString());
            specialapplyDTO.setOrgName(specialapplyNewAF.getOrgName().trim());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialapplyNewAF.getPrivilegeBorrowerId()
                .trim());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // �����������ơ������˺�
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // ���£�����������Ϣ��PL112���ݽ����������֤���������ϲ�ѯ��λ ���룺������־PL021
            specialapplyBS.updateSpecialapply(specialapplyDTO);
          }
        }
      } else {// �������ֵ
        // ����������Ϣ��PL112�Ƿ���ڽ����������֤���������¼��ļ�¼
        boolean is_temp = specialapplyBS.isCheckNameANDCardNum_SpecialBorrower(
            specialapplyNewAF.getBorrowerName().trim(),
            specialapplyNewAF.getCardNum().trim()).booleanValue();
        // PL112����û������
        if (!is_temp) {
          specialapplyDTO = new SpecialapplyDTO();
          specialapplyDTO.setBorrowerName(specialapplyNewAF.getBorrowerName()
              .trim());
          specialapplyDTO.setCardKind(specialapplyNewAF.getCardKind().trim());
          specialapplyDTO.setCardNum(specialapplyNewAF.getCardNum().trim());
          specialapplyDTO.setOrgName(specialapplyNewAF.getOrgName().trim());
          specialapplyDTO.setLoanTimeLimit(specialapplyNewAF.getLoanTimeLimit()
              .trim());
          specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
              .getLoanMoney().toString().trim()));
          specialapplyDTO.setEmpId("0");
          specialapplyDTO.setOrgId("0");
          specialapplyDTO.setStutas("0");
          specialapplyDTO.setOperator(operator);
          specialapplyDTO.setUserIp(userId);
          // �����������ơ������˺�
          specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
          specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
              .trim());
          specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
          specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
          specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
          specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
          // ���� ����������Ϣ��PL112�� ���룺������־PL021
          specialapplyBS.insertSpecialApplyInfo(specialapplyDTO, securityInfo);
        } else {// PL112����������
          specialBorrower = specialapplyBS
              .findSpecialBorrowerStutasByNameAndNum(specialapplyNewAF
                  .getBorrowerName().trim(), specialapplyNewAF.getCardNum()
                  .trim());
          // PL112���ݼ�¼ ״̬��Ϊ��
          if (specialBorrower == null) {
            // ��ʾ ״̬Ϊһ �����ݼ�¼
            specialBorrower = specialapplyBS
                .findSpecialBorrowerStutasByNameAndNumTop1(specialapplyNewAF
                    .getBorrowerName().trim(), specialapplyNewAF.getCardNum()
                    .trim());
            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialBorrower.getBorrowerName());
            specialapplyDTO.setCardKind(specialBorrower.getCardKind());
            specialapplyDTO.setCardNum(specialBorrower.getCardNum());
            specialapplyDTO.setOrgId(specialBorrower.getOrgId().toString());
            specialapplyDTO.setOrgName(specialBorrower.getOrgName());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialBorrower.getEmpId().toString());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // �����������ơ������˺�
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // ���� ����������Ϣ��PL112�� ���룺������־PL021
            specialapplyBS
                .insertSpecialApplyInfo(specialapplyDTO, securityInfo);
          } else {// PL112���ݼ�¼ ״̬������
            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialBorrower.getBorrowerName());
            specialapplyDTO.setCardKind(specialBorrower.getCardKind());
            specialapplyDTO.setCardNum(specialBorrower.getCardNum());
            specialapplyDTO.setOrgId(specialBorrower.getOrgId().toString());
            specialapplyDTO.setOrgName(specialBorrower.getOrgName());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialBorrower.getEmpId().toString());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // �����������ơ������˺�
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // ���£�����������Ϣ��PL112���ݽ����������֤���������ϲ�ѯ��λ ���룺������־PL021
            specialapplyBS.updateSpecialapply(specialapplyDTO);
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return mapping.findForward("specialapplyTaShowAC");
  }
}