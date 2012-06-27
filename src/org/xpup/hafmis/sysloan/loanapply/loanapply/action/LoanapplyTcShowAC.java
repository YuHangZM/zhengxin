package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.develop.dto.HouseAddListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.BankListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.FloorListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.FloorNumListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;

public class LoanapplyTcShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    LoanapplyTcNewAF loanapplytcnewAF = new LoanapplyTcNewAF();
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    request.getSession().setAttribute(LoanapplyTeShowAC.PAGINATION_KEY, null);
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String contractIdHL = (String) request.getSession().getAttribute(
        "contractIdHL");// �ж��Ƿ��Ǵ�ά���ĳ����ӹ�����
    String contractid = (String) request.getSession()
        .getAttribute("contractid");
    if (contractIdHL != null) {
      contractid = contractIdHL;
    }
    String buyhouseorgid = (String) request.getParameter("buyhouseorgid");// ����ȡ���۷���λId
                                                                          // ����pl005�ı�������
    String temp_buyhouseorgid = (String) request.getSession().getAttribute(
        "buyhouseorgida");
    request.getSession().setAttribute("buyhouseorgida", null);
    if (temp_buyhouseorgid != null) {
      buyhouseorgid = null;
    }
    String remend = (String) request.getSession().getAttribute("remend");// �ж��Ƿ���ά��������
    if (contractid != null) {
      try {
        loanapplyBS.isBothHaveGjj(contractid,  securityInfo);
        loanapplytcnewAF = loanapplyBS.showBuyHouseInfoBycontractid(contractid,
            buyhouseorgid);
        if (buyhouseorgid != null) {
          loanapplytcnewAF.setBuyhouseorgid(buyhouseorgid);
        }
        if (remend != null) {
          loanapplytcnewAF.setTemp_type_remind("8");
        }
      } catch (BusinessException e) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
            .getLocalizedMessage().toString(), false));
        saveErrors(request, messages);
      }
    } else {
      loanapplytcnewAF.setHouseTypehidden("01");
      loanapplytcnewAF.setHouseTypetemp("");
      loanapplytcnewAF.setHouseType("01");
    }
    // ���2��list��
    List bankOList = new ArrayList();
    List floorOList = new ArrayList();
    List floorOListnum = new ArrayList();
    List bankList = loanapplytcnewAF.getBanklist();// ����
    for (int i = 0; i < bankList.size(); i++) {
      BankListDTO bDto = (BankListDTO) bankList.get(i);
      bankOList.add(new org.apache.struts.util.LabelValueBean(bDto
          .getBankValue(), bDto.getBankKey()));
    }
    List floorList = loanapplytcnewAF.getFloorlist();// ¥��
    request.getSession().setAttribute("floorListAAC", floorList);
    for (int i = 0; i < floorList.size(); i++) {
      FloorListDTO fDto = (FloorListDTO) floorList.get(i);
      floorOList.add(new org.apache.struts.util.LabelValueBean(fDto
          .getFloorValue(), fDto.getFloorValue()));
    }
    List floorNumList = loanapplytcnewAF.getFloornumlist();// ¥��
    for (int i = 0; i < floorNumList.size(); i++) {
      org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO dto = (org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO) floorNumList
          .get(i);
      String temp_floornum = dto.getFloorNum();
      if (temp_floornum != null) {
        FloorNumListDTO floornumlistDto = new FloorNumListDTO();
        floornumlistDto.setFloornumKey(dto.getFloorId());
        floornumlistDto.setFloornumValue(dto.getFloorNum());
        floorOListnum.add(new org.apache.struts.util.LabelValueBean(
            floornumlistDto.getFloornumValue(), floornumlistDto
                .getFloornumKey()));
      }
    }
    loanapplytcnewAF.setIsNowhouseMap(BusiTools
        .listBusiProperty(BusiConst.YesNo));
    loanapplytcnewAF.setBargainorCardKindMap(BusiTools
        .listBusiProperty(BusiConst.DOCUMENTSSTATE));
    if (contractIdHL != null) {
      loanapplytcnewAF.setCoodm("15");
    }
    request.getSession().setAttribute("sessonbankOList", bankList);
    request.setAttribute("bankOList", bankOList);
    request.setAttribute("floorOList", floorOList);
    request.setAttribute("floorOListnum", floorOListnum);
    request.setAttribute("loanapplytcnewAF", loanapplytcnewAF);
    return mapping.findForward("loanapplytc_new");
  }
}