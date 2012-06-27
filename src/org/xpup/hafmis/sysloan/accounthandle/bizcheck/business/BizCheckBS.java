package org.xpup.hafmis.sysloan.accounthandle.bizcheck.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.bsinterface.IBizCheckBS;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.AdjustAccountDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BailDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.BizCheckTotalDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.dto.OverPayDTO;
import org.xpup.hafmis.sysloan.accounthandle.bizcheck.form.BizCheckShowListAF;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;

public class BizCheckBS implements IBizCheckBS {

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private CollBankDAO collBankDAO = null;

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  /**
   * ҵ�񸴺��б�
   * 
   * @author ��� 2007-09-29
   * @param pagination
   * @param securityInfo
   * @return
   */
  public BizCheckShowListAF queryShowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    BizCheckShowListAF bizCheckShowListAF = new BizCheckShowListAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    BigDecimal occurMoneyTotle = new BigDecimal(0.00);// ���Ž��-�ܶ�

    BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// ���ձ���-�ܶ�

    BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// ������Ϣ-�ܶ��ܶ�

    BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// ���շ�Ϣ-�ܶ�

    BigDecimal badDebtTotle = new BigDecimal(0.00);// ���˺������-�ܶ�

    BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// ���˽��-�ܶ�

    BigDecimal bailTotle = new BigDecimal(0.00);// ��֤��-�ܶ�

    BigDecimal bailAccrualTotle = new BigDecimal(0.00);// ��֤����Ϣ-�ܶ�

    BigDecimal reclaimtotle = new BigDecimal(0.00);// ����Ӧ�����

    BigDecimal reclaimbacktotle = new BigDecimal(0.00);// ����ʵ�����
    int affirmbizSt = 0;// ȷ��״̬����

    int checkbizSt = 0;// ����״̬����

    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String makePerson = (String) pagination.getQueryCriterions().get(
        "makePerson");
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");

    List templist = new ArrayList();
    templist = loanFlowHeadDAO.queryBizCheckShowListByCriterions(start,
        orderBy, order, pageSize, page, securityInfo, docNum, contractId,
        loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName,
        beginBizDate, endBizDate);
    BizCheckTotalDTO bizCheckTotalDTO = (BizCheckTotalDTO) loanFlowHeadDAO
        .queryBizCheckShowListCountByCriterions(securityInfo, docNum,
            contractId, loanKouAcc, borrowerName, makePerson, bizType, bizSt,
            loanBankName, beginBizDate, endBizDate);

    bizCheckShowListAF.setList(templist);

    count = bizCheckTotalDTO.getCount();

    occurMoneyTotle = bizCheckTotalDTO.getOccurMoneyTotle(); // ���Ž��-�ܶ�

    reclaimCorpusTotle = bizCheckTotalDTO.getReclaimCorpusTotle();// ���ձ���-�ܶ�

    reclaimAccrualTotle = bizCheckTotalDTO.getReclaimAccrualTotle();// ������Ϣ-�ܶ��ܶ�

    realPunishInterestTotle = bizCheckTotalDTO.getRealPunishInterestTotle();// ���շ�Ϣ-�ܶ�

    badDebtTotle = bizCheckTotalDTO.getBadDebtTotle();// ���˺������-�ܶ�

    putUpMoneyTotle = bizCheckTotalDTO.getPutUpMoneyTotle();// ���˽��-�ܶ�

    bailTotle = bizCheckTotalDTO.getBailTotle();// ��֤��-�ܶ�

    bailAccrualTotle = bizCheckTotalDTO.getBailAccrualTotle();// ��֤����Ϣ-�ܶ�

    affirmbizSt = bizCheckTotalDTO.getAffirmbizSt();// ȷ��״̬����

    checkbizSt = bizCheckTotalDTO.getCheckbizSt();// ����״̬����

    reclaimtotle = bizCheckTotalDTO.getReclaimtotle();// ����ʵ�����

    reclaimbacktotle = bizCheckTotalDTO.getReclaimbacktotle();// ����ʵ�ս��
    bizCheckShowListAF.setAffirmbizSt(affirmbizSt);
    bizCheckShowListAF.setCheckbizSt(checkbizSt);
    bizCheckShowListAF.setOccurMoneyTotle(occurMoneyTotle);
    bizCheckShowListAF.setReclaimCorpusTotle(reclaimCorpusTotle);
    bizCheckShowListAF.setReclaimAccrualTotle(reclaimAccrualTotle);
    bizCheckShowListAF.setRealPunishInterestTotle(realPunishInterestTotle);
    bizCheckShowListAF.setBadDebtTotle(badDebtTotle);
    bizCheckShowListAF.setPutUpMoneyTotle(putUpMoneyTotle);
    bizCheckShowListAF.setBailTotle(bailTotle);
    bizCheckShowListAF.setBailAccrualTotle(bailAccrualTotle);
    bizCheckShowListAF.setReclaimtotle(reclaimtotle);
    bizCheckShowListAF.setReclaimbacktotle(reclaimbacktotle);
    pagination.setNrOfElements(count);
    return bizCheckShowListAF;
  }

  // ������ҵ�񸴺ˡ�����ͨ��

  public void updateBizSTcheckthrough(String[] rowArray,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String flowHeadId = "";
    try {
      for (int i = 0; i < rowArray.length; i++) {
        flowHeadId = rowArray[i];
        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(
            flowHeadId));
        String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
        if (loanFlowHead.getBizSt().equals("4")) {
          loanFlowHead.setBizSt("5");
          loanFlowHead.setCheckPerson(operateName);
          loanFlowHeadDAO.update(loanFlowHead);
        } else {
          throw new BusinessException("ƾ֤��Ϊ" + loanFlowHead.getDocNum()
              + "��ҵ��״̬����ȷ��״̬�������Խ��и���!");
        }
        // ����ҵ������PL020
        PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
        plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
        plBizActiveLog.setAction("5");
        plBizActiveLog.setOpTime(new Date());
        plBizActiveLog.setOperator(operateName);
        plBizActiveLog.setType(loanFlowHead.getBizType());
        plBizActiveLogDAO.insert(plBizActiveLog);

        // ������־ pl021

        String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_LOAN).toString()));// ���� 2
        plOperateLog.setOpModel(new Integer(
            BusiLogConst.PL_OP_ACCOUNTMANAGE_OPERATIONCHECK).toString());// ������_ҵ�񸴺�
        // 51
        plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_CHECKS)
            .toString());
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
        plOperateLog.setOpIp(userIp);
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(operateName);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException bex) {
      bex.printStackTrace();
      throw bex;
    }
  }

  // ������ҵ�񸴺ˡ���������
  public void updateBizSTdelcheck(String[] rowArray, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String flowHeadId = "";
    try {
      for (int i = 0; i < rowArray.length; i++) {
        flowHeadId = rowArray[i];
        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(flowHeadId));
        String operateName = securityInfo.getUserInfo().getUsername();

        if (loanFlowHead.getBizSt().equals("5")) {
          loanFlowHead.setBizSt("4");
          loanFlowHead.setCheckPerson(operateName);
          loanFlowHeadDAO.update(loanFlowHead);
        } else {
          throw new BusinessException("ƾ֤��Ϊ" + loanFlowHead.getDocNum()
              + "��ҵ��״̬���Ǹ���״̬�������Խ��г�������!");
        }
        // ɾ���ñ�ҵ������PL020
        plBizActiveLogDAO.deletePlBizActiveLogByCriterions_WU(flowHeadId,
            loanFlowHead.getBizType());

        // //����ƾ֤���PL221
        // String officeCode =
        // loanFlowHeadDAO.queryOfficeByBank(loanFlowHead.getLoanBankId().toString());
        // String yearMonth =
        // securityInfo.getUserInfo().getBizDate().substring(0,
        // 6);// �鼯ҵ������
        // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
        // officeCode,yearMonth);

        // ������־ pl021
        String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
        operateName = securityInfo.getUserInfo().getUsername();// ����Ա

        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(new Integer(
            BusiLogConst.OP_SYSTEM_TYPE_LOAN).toString()));// ���� 2
        plOperateLog.setOpModel(new Integer(
            BusiLogConst.PL_OP_ACCOUNTMANAGE_OPERATIONCHECK).toString());// ������_ҵ�񸴺�
        // 51
        plOperateLog.setOpButton(new Integer(
            BusiLogConst.BIZLOG_ACTION_REVOCATION).toString());
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
        plOperateLog.setOpIp(userIp);
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(operateName);
        plOperateLogDAO.insert(plOperateLog);
      }
    } catch (BusinessException bex) {
      bex.printStackTrace();
      throw bex;
    }
  }

  // ������ҵ�񸴺ˡ���������

  public void updateBizSTcheckall(SecurityInfo securityInfo,
      Pagination pagination) throws Exception, BusinessException {
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String makePerson = (String) pagination.getQueryCriterions().get(
        "makePerson");
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String bizSt = "4";
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    List checkalllist = new ArrayList();
    checkalllist = loanFlowHeadDAO.queryFlowHeadIdByCriterions(securityInfo,
        docNum, contractId, loanKouAcc, borrowerName, makePerson, bizType,
        bizSt, loanBankName, beginBizDate, endBizDate);

    for (int i = 0; i < checkalllist.size(); i++) {
      String id = checkalllist.get(i).toString();
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(id));
      if (loanFlowHead.getBizSt().equals("4")) {
        loanFlowHead.setBizSt("5");
        loanFlowHead.setCheckPerson(operateName);
        loanFlowHeadDAO.update(loanFlowHead);
      } else {
        throw new BusinessException("ƾ֤��Ϊ" + loanFlowHead.getDocNum()
            + "��ҵ��״̬����ȷ��״̬�������Խ��и���!");
      }
      // ����ҵ������PL020
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      plBizActiveLog.setBizid(new BigDecimal(id));
      plBizActiveLog.setAction("5");
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(operateName);
      plBizActiveLog.setType(loanFlowHead.getBizType());
      plBizActiveLogDAO.insert(plBizActiveLog);

      // ������־ pl021

      String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(new Integer(
          BusiLogConst.OP_SYSTEM_TYPE_LOAN).toString()));// ���� 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_ACCOUNTMANAGE_OPERATIONCHECK).toString());// ������_ҵ�񸴺�
      // 51
      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_CHECKS)
          .toString());
      plOperateLog.setOpBizId(new BigDecimal(id));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    }

  }

  // ������ҵ�񸴺ˡ�������������
  public void updateBizSTdelcheckall(SecurityInfo securityInfo,
      Pagination pagination) throws Exception, BusinessException {
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String makePerson = (String) pagination.getQueryCriterions().get(
        "makePerson");
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String bizSt = "5";
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP
    List delcheckall = new ArrayList();
    delcheckall = loanFlowHeadDAO.queryFlowHeadIdByCriterions(securityInfo,
        docNum, contractId, loanKouAcc, borrowerName, makePerson, bizType,
        bizSt, loanBankName, beginBizDate, endBizDate);
    System.out.println(delcheckall.size()+"===========>");
    for (int i = 0; i < delcheckall.size(); i++) {

      String id = delcheckall.get(i).toString();
      
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(id));
      if (loanFlowHead.getBizSt().equals("5")) {
        loanFlowHead.setBizSt("4");
        loanFlowHead.setCheckPerson(operateName);
        loanFlowHeadDAO.update(loanFlowHead);
      } else {
        throw new BusinessException("ƾ֤��Ϊ" + loanFlowHead.getDocNum()
            + "��ҵ��״̬���Ǹ���״̬�������Խ��г�������!");
      }
      // ɾ���ñ�ҵ������PL020
      plBizActiveLogDAO.deletePlBizActiveLogByCriterions_WU(id, loanFlowHead
          .getBizType());

      // //����ƾ֤���PL221
      // String officeCode =
      // loanFlowHeadDAO.queryOfficeByBank(loanFlowHead.getLoanBankId().toString());
      // String yearMonth = securityInfo.getUserInfo().getBizDate().substring(0,
      // 6);// �鼯ҵ������
      // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
      // officeCode,yearMonth);
      // ������־ pl021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(new Integer(
          BusiLogConst.OP_SYSTEM_TYPE_LOAN).toString()));// ���� 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_ACCOUNTMANAGE_OPERATIONCHECK).toString());// ������_ҵ�񸴺�
      // // 51
      plOperateLog.setOpButton(new Integer(
          BusiLogConst.BIZLOG_ACTION_REVOCATION).toString());
      plOperateLog.setOpBizId(new BigDecimal(id));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    }
  }

  // ������ҵ�񸴺ˡ�������Ϣ
  public LoanaccordDTO queryLoanaccordById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
    try {
      List list = collBankDAO.queryLoanaccorById(flowHeadId, securityInfo);
      if (!list.isEmpty()) {
        loanaccordDTO = (LoanaccordDTO) list.get(0);
        // ��ʾ��������
        loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
            .multiply(new BigDecimal(100.00)).toString()
            + "%");
        // ֤�����Ͷ�Ӧ������
        loanaccordDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(loanaccordDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // ͨ��bankId�������е�����
        String bankId = loanaccordDTO.getLoanBankId();
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
        loanaccordDTO.setLoanBankName(collBank.getCollBankName());
        // ���㷢������
        String tempLoanStartDate = loanaccordDTO.getLoanStartDate();
        // ���ʽ
        loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        // ��ȡ�������
        String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
        int temp_addYear = (Integer.parseInt(tempLoanStartDate.substring(4, 6)) + Integer
            .parseInt(tempLoanTimeLimit)) / 12;
        int temp_moth = (Integer.parseInt(tempLoanStartDate.substring(4, 6)) + Integer
            .parseInt(tempLoanTimeLimit)) % 12;
        // �ж��ǲ������һ���µ�����������ǿ�������һ����
        int month_day = BusiTools.daysOfMonth((Integer
            .parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear),
            temp_moth);
        // ������������
        String overDay = "";
        if (month_day < Integer.parseInt(tempLoanStartDate.substring(6, 8))) {
          overDay = month_day + "";
        } else {
          overDay = tempLoanStartDate.substring(6, 8);
        }
        // ��������
        String overTime = "";
        if (temp_moth < 10) {
          overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
              + "" + "0" + temp_moth + "" + overDay;
        } else {
          overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
              + "" + temp_moth + "" + overDay;
        }
        loanaccordDTO.setOverTime(overTime);
      } else {
        throw new BusinessException("�˺�ͬ�����ڷ�����Ϣ");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanaccordDTO;
  }

  // ������ҵ�񸴺ˡ� ������Ϣ
  public OverPayDTO queryOverPayById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    OverPayDTO overPayDTO = new OverPayDTO();
    try {
      List list = loanFlowHeadDAO.queryOverPayById(flowHeadId, securityInfo);
      if (!list.isEmpty()) {
        overPayDTO = (OverPayDTO) list.get(0);
        // ֤�����Ͷ�Ӧ������
        overPayDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(overPayDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // ���ʽ
        overPayDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(overPayDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      } else {
        throw new BusinessException("�˺�ͬ�����ڹ�����Ϣ");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return overPayDTO;
  }

  // ������ҵ�񸴺ˡ� ��֤������
  public BailDTO queryBailById(String flowHeadId, SecurityInfo securityInfo)
      throws Exception, BusinessException {

    BailDTO bailDTO = new BailDTO();
    try {
      List list = loanFlowHeadDAO.queryBailById(flowHeadId, securityInfo);
      if (!list.isEmpty()) {
        bailDTO = (BailDTO) list.get(0);

      } else {
        throw new BusinessException("�˺�ͬ�����ڱ�֤��������Ϣ");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bailDTO;
  }

  // ������ҵ�񸴺ˡ� ���ʵ�����Ϣ
  public AdjustAccountDTO queryAdjustAccountById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    AdjustAccountDTO adjustAccountDTO = new AdjustAccountDTO();
    try {
      List list = loanFlowHeadDAO.queryAdjustAccountById(flowHeadId,
          securityInfo);
      if (!list.isEmpty()) {
        adjustAccountDTO = (AdjustAccountDTO) list.get(0);
        if (adjustAccountDTO.getBizType() != null
            && !adjustAccountDTO.getBizType().equals("")) { // ö��ת��ҵ������
          try {
            adjustAccountDTO.setBizType(BusiTools.getBusiValue(Integer
                .parseInt("" + adjustAccountDTO.getBizType()),
                BusiConst.PLBUSINESSTYPE));
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

      } else {
        throw new BusinessException("�˺�ͬ�����ڴ��ʵ�����Ϣ");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return adjustAccountDTO;
  }
}
