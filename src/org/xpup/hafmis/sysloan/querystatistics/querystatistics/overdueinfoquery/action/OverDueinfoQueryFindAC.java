package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryShowListAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.action.OverDueinfoQueryShowListAC;

public class OverDueinfoQueryFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    OverDueinfoQueryShowListAF overDueinfoQueryShowListAF = (OverDueinfoQueryShowListAF) form;
    HashMap criterions = makeCriterionsMap(overDueinfoQueryShowListAF);
    Pagination pagination = new Pagination(0, 10, 1,  "p111.contract_id", "DESC",
        criterions); 
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("overDueinfoQueryShowListAC");
  }

  protected HashMap makeCriterionsMap(OverDueinfoQueryShowListAF form) {
    HashMap m = new HashMap(0);
    String offic=form.getOffic();// ���´�
    if (offic != null && !"".equals(offic.trim())) {
      m.put("offic", offic.trim());
    }
    String loanBankName = form.getLoanBankName();// �ſ�����
    if (loanBankName != null && !"".equals(loanBankName.trim())) {
      m.put("loanBankName", loanBankName.trim());
    }
    String  buyhouseorgid = form.getBuyhouseorgid();// ������
    if ( buyhouseorgid != null && !"".equals(buyhouseorgid.trim())) {
      m.put("buyhouseorgid", buyhouseorgid.trim());
    }
    String  headid= form.getBuyhouseorgid();
    if (!( headid== null || "".equals(headid.trim()))) {
      m.put("headid", headid.trim());
    }
    String  floorId = form.getFloorId();// ¥��
    if ( floorId != null && !"".equals(floorId.trim())) {
      m.put("floorId", floorId.trim());
    }
//    String  floorId = form.getFloorId().trim();// ¥��
//    if ( floorId != null && !"".equals(floorId)) {
//      m.put("floorId", floorId);
//    }
    String  floorid = form.getFloorid();// ¥��
    if ( floorid != null && !"".equals(floorid.trim())) {
      m.put("floorid", floorid.trim());
    }
    String floorName = form.getFloorName();
    if (!( floorName == null || "".equals(floorName.trim()))) {
      m.put("floorName", floorName.trim());
    }
    String floorNum = form.getFloorNum_();
    if (!( floorNum == null || "".equals(floorNum.trim()))) {
      m.put("floorNum", floorNum.trim());
    }
    String  housetype= form.getHouseType();
    if (!( housetype== null || "".equals(housetype.trim()))) {
      m.put("housetype", housetype.trim());
    }
    String roomNum = form.getRoomNum();
    if (!( roomNum == null || "".equals(roomNum.trim()))) {
      m.put("roomNum", roomNum.trim());
    }
    String  assistantorg = form.getAssistantorg();// ������˾
    if ( assistantorg != null && !"".equals(assistantorg.trim())) {
      m.put("assistantorg", assistantorg.trim());
    }
    String contractId=form.getContractId();// ��ͬ���
    if (contractId != null && !"".equals(contractId)) {
      m.put("contractId", contractId.trim());
    }
    
    String loanKouAcc=form.getLoanKouAcc();// �����˺�
    if (loanKouAcc!= null && !"".equals(loanKouAcc.trim())) {
      m.put("loanKouAcc", loanKouAcc.trim());
    }
    
    String borrowerName = form.getBorrowerName();//���������
    if (borrowerName != null && !"".equals(borrowerName.trim())) {
      m.put("borrowerName", borrowerName.trim());
    }
    String cardNum = form.getCardNum();//֤������
    if (cardNum != null && !"".equals(cardNum)) {
      m.put("cardNum", cardNum.trim());
    }
    String beginoweMonth=form.getBeginoweMonth();// ��������
    if (beginoweMonth != null && !"".equals(beginoweMonth)) {
     m.put("beginoweMonth", beginoweMonth.trim());
    }
    String endoweMonth=form.getEndoweMonth();// ��������
    if (endoweMonth!= null && !"".equals(endoweMonth)) {
     m.put("endoweMonth", endoweMonth.trim());
    }
    String buildAreaMin=form.getBuildAreaMin();// ���
    if (buildAreaMin!= null && !"".equals(buildAreaMin)) {
      m.put("buildAreaMin", buildAreaMin.trim());
    }
    String buildAreaMax=form.getBuildAreaMax();// ���
    if (buildAreaMax!= null && !"".equals(buildAreaMax)) {
      m.put("buildAreaMax", buildAreaMax.trim());
    }
    String applyDate=form.getApplyDate();// ���пۿ����� 
    if (applyDate!= null && !"".equals(applyDate)) {
      m.put("applyDate", applyDate.trim());
    }
    String agreement=form.getAgreement();// ������Э�� 
    if (agreement!= null && !"".equals(agreement)) {
      m.put("agreement", agreement.trim());
    }
    String otherMoney=form.getOtherMoney();// ����Ƿ�� 
    if (otherMoney!= null && !"".equals(otherMoney)) {
      m.put("otherMoney", otherMoney.trim());
    }
    String startDateMin=form.getStartDateMin();// ��������
    if (startDateMin!= null && !"".equals(startDateMin)) {
      m.put("startDateMin", startDateMin.trim());
    }
    String startDateMax=form.getStartDateMax();// �������� 
    if (startDateMax!= null && !"".equals(startDateMax)) {
      m.put("startDateMax", startDateMax.trim());
    }
    String isNoAcquittance=form.getIsNoAcquittance();// ���ڵ���δ�廹
    if (isNoAcquittance!= null && !"".equals(isNoAcquittance)) {
     m.put("isNoAcquittance", isNoAcquittance.trim());
    }
    m.put("temp_check","1");
    return m;
  }

  protected String getPaginationKey() {
    return OverDueinfoQueryShowListAC.PAGINATION_KEY;
  }
}