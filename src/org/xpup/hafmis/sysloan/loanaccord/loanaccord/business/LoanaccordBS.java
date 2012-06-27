package org.xpup.hafmis.sysloan.loanaccord.loanaccord.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.bsinterface.ILoanaccordBS;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordShowAF;

public class LoanaccordBS implements ILoanaccordBS {
  private BorrowerAccDAO borrowerAccDAO = null;

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private CollBankDAO collBankDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollParaDAO collParaDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null; // �����Ƿ����pl002

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public String getLoanBankId(String contractId) throws BusinessException {

    return null;
  }

  // ���Һ�ͬ�����Ϣ��ʾ��
  public LoanaccordNewAF queryLoanaccordInfo(String contractId,
      SecurityInfo securityInfo) throws BusinessException {
    LoanaccordNewAF loanaccordNewAF = new LoanaccordNewAF();
    try {
      // ��ͬ��״̬
      String contractSt = "9";
      String bizDate = securityInfo.getUserInfo().getPlbizDate();// ҵ������
      List list = borrowerLoanInfoDAO.queryBorrowerLoanInfo_sy(contractSt,
          contractId, securityInfo);
      if (!list.isEmpty()) {
        String is = loanBankDAO.queryIsBoKuan(contractId);
        if ("0".equals(is)) {
          throw new BusinessException("�ñʺ�ͬδ������ܷ��ţ�");
        }
        LoanaccordDTO loanaccordDTO = (LoanaccordDTO) list.get(0);
        // //��ȡ���µ����ʣ������ж������Ƿ�������µ����ʡ����û�в���֤���������õ�ʱ��û�и��µ�pl115����Ϣ������Ҫ�ж��Ƿ����»�ȡ���ʡ�
        // //�鿴�����Ƿ�����µ�����
        // List islist=loanBankParaDAO.queryLoanBankPara_sy(new
        // Integer(loanaccordDTO.getLoanBankId()));
        // if(islist.isEmpty()){
        // List
        // islistNewMonthRate=borrowerAccDAO.findUseMontRate_sy(loanaccordDTO.getLoanBankId(),loanaccordDTO.getLoanRateType());
        // Object infonewmonthrate[] = (Object[]) islistNewMonthRate.get(0);
        // if ((infonewmonthrate[1]+"").equals("1")){
        // loanaccordDTO.setLoanMonthRate(new
        // BigDecimal(infonewmonthrate[0]+""));
        // }
        // }
        // ��ʾ��������
        loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
            .multiply(new BigDecimal(100.00)).toString()
            + "%");
        // ��ͬ���
        loanaccordDTO.setContractId(contractId);
        // ֤�����Ͷ�Ӧ������
        loanaccordDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(loanaccordDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // ͨ��bankId�������е�����
        String bankId = loanaccordDTO.getLoanBankId();
        String bankDate = "";// ��������
        bankDate = loanBankDAO.queryPL002BizDate_jj(bankId);
        if (!bizDate.equals(bankDate)) {
          throw new BusinessException("��¼����������ҵ�����ڲ�һ�£�������ҵ��");
        }
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
        loanaccordDTO.setLoanBankName(collBank.getCollBankName());
        // ��Ӧ���ж�Ӧ�İ��´�
        loanaccordDTO.setOffice(collBank.getOffice());
        // ���ʽ
        loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        if (Integer.parseInt(loanaccordDTO.getLoanMode()) > 3) {
          loanaccordDTO.setIsEntire("1");
        } else {
          loanaccordDTO.setIsEntire(null);
        }
        // ��������
        loanaccordDTO.setLoanStartDate(securityInfo.getUserInfo()
            .getPlbizDate());
        // ���㷢������
        String tempLoanStartDate = securityInfo.getUserInfo().getPlbizDate();
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
        // �ж���ͳһ���գ����ǰ�������
        List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
            "2", "A");
        Object Info[] = (Object[]) loanBankParaInfo.get(0);
        String loanRepayDay = "";
        if ((Info[0] + "").equals("1")) {
          loanRepayDay = tempLoanStartDate.substring(6, 8);
          loanaccordDTO.setLoanRepayDay(loanRepayDay);
          loanaccordDTO.setLoanRepayDayInfo("1");
        } else {
          loanRepayDay = Info[1] + "";
          loanaccordDTO.setLoanRepayDay(loanRepayDay);
          loanaccordDTO.setLoanRepayDayInfo("2");
        }
        // �������»�����
        String firstLoanMoney = "";
        int temploanRepayDay = new Integer(loanaccordDTO.getLoanRepayDay())
            .intValue();
        // ��������������ܹ�������:����µ�����-��������+�¸�������
        int tempmonth_day = BusiTools.daysOfMonth((Integer
            .parseInt(tempLoanStartDate.substring(0, 4))), (Integer
            .parseInt(tempLoanStartDate.substring(4, 6))));
        if (temploanRepayDay > 28) {
          // �жϵ�һ���µû������ǲ�������һ�졣
          int tempnextday = 0;
          if (Integer.parseInt(tempLoanStartDate.substring(4, 6)) == 12) {
            tempnextday = BusiTools.daysOfMonth((Integer
                .parseInt(tempLoanStartDate.substring(0, 4))), Integer
                .parseInt("01"));
          } else {
            tempnextday = BusiTools.daysOfMonth((Integer
                .parseInt(tempLoanStartDate.substring(0, 4))), (Integer
                .parseInt(tempLoanStartDate.substring(4, 6))) + 1);
          }
          if (temploanRepayDay > tempnextday) {
            temploanRepayDay = tempnextday;
          }
        }
        int fristday = tempmonth_day
            - Integer.parseInt(tempLoanStartDate.substring(6, 8))
            + temploanRepayDay;
        if (loanaccordDTO.getLoanMode().equals("2")) {
          // �ȶϢ
          // ��������
          if (loanaccordDTO.getLoanRepayDayInfo().equals("1")) {
            // �»���Ϣ
            firstLoanMoney = loanaccordDTO.getCorpusInterest().toString();
          }
          // ͳһ����
          else {
            // Ӧ����Ϣ+Ӧ������
            firstLoanMoney = ((loanaccordDTO.getLoanMoney()
                .multiply(new BigDecimal(fristday + "")))
                .multiply(loanaccordDTO.getLoanMonthRate()).divide(
                new BigDecimal("30"), 2, BigDecimal.ROUND_HALF_UP)).add(
                loanaccordDTO.getCorpusInterest().subtract(
                    loanaccordDTO.getLoanMoney().multiply(
                        loanaccordDTO.getLoanMonthRate()))).divide(
                new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP).toString();
          }
        } else {
          // �ȶ��

          // ��������
          if (loanaccordDTO.getLoanRepayDayInfo().equals("1")) {
            // ����ʣ�౾��*������+�»�����
            firstLoanMoney = (loanaccordDTO.getLoanMoney()
                .multiply(loanaccordDTO.getLoanMonthRate())).add(
                loanaccordDTO.getLoanMoney().divide(
                    new BigDecimal(tempLoanTimeLimit), 2,
                    BigDecimal.ROUND_HALF_UP)).divide(new BigDecimal(1.00), 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
          // ͳһ����
          else {

            //
            firstLoanMoney = ((loanaccordDTO.getLoanMoney()
                .multiply(loanaccordDTO.getLoanMonthRate()))
                .multiply(new BigDecimal(fristday + "")).divide(new BigDecimal(
                "30"), 2, BigDecimal.ROUND_HALF_UP)).add(
                loanaccordDTO.getLoanMoney().divide(
                    new BigDecimal(tempLoanTimeLimit), 2,
                    BigDecimal.ROUND_HALF_UP)).divide(new BigDecimal(1.00), 2,
                BigDecimal.ROUND_HALF_UP).toString();
          }
        }
        loanaccordDTO.setFirstLoanMoney(new BigDecimal(firstLoanMoney));
        loanaccordDTO.setOverTime(overTime);
        loanaccordNewAF.setLoanaccordDTO(loanaccordDTO);
      } else {
        throw new BusinessException("�˺�ͬ���������ʵ���ٷ���");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanaccordNewAF;
  }

  // ȷ�Ϻ�ͬ������
  public String updateBorrowerAccInfo(LoanaccordDTO loanaccordDTO,
      SecurityInfo securityInfo) throws BusinessException {
    String flowHeadId = "";
    try {
      // �¿ۿ��˺�

      // String newLoanKouAcc = loanaccordDTO.getLoanKouAcc();
      // Integer count = borrowerAccDAO
      // .findBorrowerAccByLoanKouAcc_sy(newLoanKouAcc);
      // if (count.intValue() > 0) {
      // throw new BusinessException("�����˺���ʹ�ã�ȷ�Ϻ�ʵ�˺ţ�");
      // }
      // ���¶�Ӧ�ĺ�ͬ��
      String contractId = loanaccordDTO.getContractId();
      BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
      if (borrowerAcc.getContractSt().equals("10")) {
        throw new BusinessException("�˺�ͬ�Ѿ��������ʵ��");
      }
      if(borrowerAcc.getMarkA()!=null && !borrowerAcc.getMarkA().equals("1")){
        throw new BusinessException("�˺�ͬ����δ��ˣ�");
      }
      String tempyear = new Integer(securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 4)).intValue()
          - 1 + ""; // �鿴��ת��Ƚ�תʱ��
      String yearClear = "";
      yearClear = loanBankDAO.queryYearClearByBankId_sy(loanaccordDTO
          .getLoanBankId());
      if (!yearClear.equals(tempyear)) {
        throw new BusinessException("��һ��û����᲻�ܷ��ţ�");
      }
      // ����pl111��¼
      // �����ʺ�
      borrowerAcc.setLoanKouAcc(loanaccordDTO.getLoanKouAcc());
      // ������
      borrowerAcc.setLoanMoney(loanaccordDTO.getLoanMoney());
      // ��������
      borrowerAcc.setLoanTimeLimit(loanaccordDTO.getLoanTimeLimit());
      // ��������
      borrowerAcc.setLoanRateType(loanaccordDTO.getLoanRateType());
      // ���ʽ
      borrowerAcc.setLoanMode(loanaccordDTO.getLoanMode());
      // ��������
      borrowerAcc.setLoanStartDate(loanaccordDTO.getLoanStartDate());
      // ������
      borrowerAcc.setLoanRepayDay(new BigDecimal(loanaccordDTO
          .getLoanRepayDay()));
      // ʣ����
      borrowerAcc.setOverplusLoanMoney(loanaccordDTO.getLoanMoney());
      // ʣ������
      borrowerAcc.setOverplusLimite(loanaccordDTO.getLoanTimeLimit());
      // �����µ�״̬
      borrowerAcc.setContractSt(BusiConst.PLCONTRACTSTATUS_ISSUING + "");
      // ����pl115�����»�����
      BorrowerLoanInfo borrowerLoanInfo = new BorrowerLoanInfo();
      borrowerLoanInfo = borrowerLoanInfoDAO.queryById(contractId);
      borrowerLoanInfo.setFirstLoanMoney(loanaccordDTO.getFirstLoanMoney());
      // borrowerLoanInfo.setLoanMonthRate(loanaccordDTO.getLoanMonthRate());
      // ���������ˮ��ͷ��
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      // �������
      loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());
      // ҵ������.����
      loanFlowHead.setBizType(BusiConst.PLBUSINESSTYPE_ISSUED + "");
      // Ӧ����������
      loanFlowHead.setShouldCorpus(new BigDecimal(0.00));
      // Ӧ����Ϣ
      loanFlowHead.setShouldInterest(new BigDecimal(0.00));
      // Ӧ�����ڱ���
      loanFlowHead.setShouldOverdueCorpus(new BigDecimal(0.00));
      // Ӧ��������Ϣ
      loanFlowHead.setShouldOverdueInterest(new BigDecimal(0.00));
      // Ӧ����Ϣ
      loanFlowHead.setShouldPunishInterest(new BigDecimal(0.00));
      // ʵ������
      loanFlowHead.setRealCorpus(new BigDecimal(0.00));
      // ʵ����Ϣ
      loanFlowHead.setRealInterest(new BigDecimal(0.00));
      // ʵ�����ڱ���
      loanFlowHead.setRealOverdueCorpus(new BigDecimal(0.00));
      // ʵ��������Ϣ
      loanFlowHead.setRealOverdueInterest(new BigDecimal(0.00));
      // ʵ����Ϣ
      loanFlowHead.setRealPunishInterest(new BigDecimal(0.00));
      // ������Ϣ
      loanFlowHead.setOtherInterest(new BigDecimal(0.00));
      // �������
      loanFlowHead.setOccurMoney(loanaccordDTO.getLoanMoney());
      // ȡƾ֤��
      String officeId = "";
      // String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      // if(loanDocNumDocument.equals("1")){
      officeId = loanaccordDTO.getOffice();
      // }else{
      // officeId=loanaccordDTO.getLoanBankId();
      // }
      String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 6);
      String loanbankid = borrowerAccDAO.getLoanBankId(contractId);
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanbankid,
          bizYearmonth.substring(0, 4));
      Map office = securityInfo.getOfficeInnerCodeMap();
      String officecode = office.get(officeId).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      docNum = bizYearmonth.substring(0, 4) + officecode + "0" + loanbankid
          + docNum;
      loanFlowHead.setDocNum(docNum);
      // �ſ�����
      loanFlowHead.setLoanBankId(new BigDecimal(loanaccordDTO.getLoanBankId()));
      // ҵ��״̬
      loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      // �Ƶ���
      loanFlowHead.setMakePerson(securityInfo.getUserInfo().getUsername());
      // �����Ƿ�ȡ��
      loanFlowHead.setIsFinance(new Integer("1"));
      flowHeadId = loanFlowHeadDAO.insert(loanFlowHead) + "";
      // �����Լ�����Ʊ�ݱ��
      // ϵͳ�Զ����ɽ���ţ�ҵ������+��ˮ��
      String noteNum = "";
      String bizDate = securityInfo.getUserInfo().getBizDate();
      noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
      LoanFlowHead loanFlowHeadUpdate = loanFlowHeadDAO.queryById(new Integer(
          flowHeadId));
      loanFlowHeadUpdate.setNoteNum(noteNum);
      // ���������ˮ��β��
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      // �������
      loanFlowTail.setOccurMoney(loanaccordDTO.getLoanMoney());
      // ͷ����ˮ��
      loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
      // �����˺�
      loanFlowTail.setLoanKouAcc(loanaccordDTO.getLoanKouAcc());
      // �����ͬ���
      loanFlowTail.setContractId(contractId);
      loanFlowTailDAO.insert(loanFlowTail);
      // ���������־
      String opModel = BusiLogConst.PL_OP_LOANISSUED_DO + "";
      String opButton = BusiLogConst.BIZLOG_ACTION_CONFIRM + "";
      this.addPlOperateLog_sy(contractId, opModel, opButton, flowHeadId,
          securityInfo);
      // ҵ����־
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
      plBizActiveLog.setAction(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(securityInfo.getUserInfo().getUsername());
      plBizActiveLog.setType(String.valueOf(BusiConst.PLBUSINESSTYPE_ISSUED));
      plBizActiveLogDAO.insert(plBizActiveLog);
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return flowHeadId;
  }

  // ����pl021������־
  public void addPlOperateLog_sy(String contractId, String opModel,
      String opButton, String opBizId, SecurityInfo securityInfo) {
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(opModel);
    plOperateLog.setOpButton(opButton);
    plOperateLog.setOpBizId(new BigDecimal(opBizId));
    plOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setContractId(contractId);
    plOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
    plOperateLogDAO.insert(plOperateLog);
  }

  // ������ˮ����ҵ���ʼ״̬Ϊ4,5,6�ļ�¼
  public LoanaccordShowAF queryLoanaccordList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    LoanaccordShowAF loanaccordShowAF = new LoanaccordShowAF();
    try {
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List returnList = new ArrayList();
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
      String loanStartDate = (String) pagination.getQueryCriterions().get(
          "loanStartDate");
      String loanEndDate = (String) pagination.getQueryCriterions().get(
          "loanEndDate");
      
      List loanaccordList = borrowerLoanInfoDAO.queryBorrowerLoanList(bizSt,
          loanBankId, contractId, "1", borrowerName, cardNum, orderBy, order,
          start, pageSize, securityInfo, page, loanStartDate, loanEndDate);
      if (!loanaccordList.isEmpty()) {
        for (int i = 0; i < loanaccordList.size(); i++) {
          LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
          loanaccordDTO = (LoanaccordDTO) loanaccordList.get(i);
          // ��ʾ�õ�������
          loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
              .multiply(new BigDecimal(100.00)).toString()
              + "%");
          // ֤�����Ͷ�Ӧ������
          loanaccordDTO
              .setCardKindName(BusiTools.getBusiValue(Integer
                  .parseInt(loanaccordDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
          // ͨ��bankId�������е�����
          String bankId = loanaccordDTO.getLoanBankId();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          loanaccordDTO.setLoanBankName(collBank.getCollBankName());

          // ���ʽ
          loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
          //
          loanaccordDTO.setBizStName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // ��ȡ�������
          String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
          String tempLoanStartDate = loanaccordDTO.getLoanStartDate();
          int temp_addYear = (Integer.parseInt(tempLoanStartDate
              .substring(4, 6)) + Integer.parseInt(tempLoanTimeLimit)) / 12;
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
            if (temp_moth == 0) {
              overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4))
                  + temp_addYear - 1)
                  + "" + "12" + "" + overDay;
            } else {
              overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                  + "" + "0" + temp_moth + "" + overDay;
            }
          } else {
            overTime = (Integer.parseInt(tempLoanStartDate.substring(0, 4)) + temp_addYear)
                + "" + temp_moth + "" + overDay;
          }
          loanaccordDTO.setOverTime(overTime);
          // �ж���ͳһ���գ����ǰ�������
          List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
              "2", "A");
          Object Info[] = (Object[]) loanBankParaInfo.get(0);
          String loanRepayDay = "";
          if ((Info[0] + "").equals("1")) {
            loanRepayDay = tempLoanStartDate.substring(6, 8);
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("1");
          } else {
            loanRepayDay = Info[1] + "";
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("2");
          }
          returnList.add(loanaccordDTO);
        }
      }
      List loanaccordAllList = borrowerLoanInfoDAO.countBorrowerLoanList(bizSt,
          loanBankId, contractId, "1", borrowerName, cardNum, securityInfo,
          loanStartDate, loanEndDate);
      // �ϼƴ�����ȡ����
      BigDecimal sumloanMoney = new BigDecimal(0.00);
      LoanaccordDTO loanaccordDTO = null;
      if (!loanaccordAllList.isEmpty()) {
        for (int i = 0; i < loanaccordAllList.size(); i++) {
          loanaccordDTO = (LoanaccordDTO) loanaccordAllList.get(i);
          // ��ʾ�õ�������
          loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
              .multiply(new BigDecimal(100.00)).toString()
              + "%");
          // ֤�����Ͷ�Ӧ������
          loanaccordDTO
              .setCardKindName(BusiTools.getBusiValue(Integer
                  .parseInt(loanaccordDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
          // ͨ��bankId�������е�����
          String bankId = loanaccordDTO.getLoanBankId();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          loanaccordDTO.setLoanBankName(collBank.getCollBankName());

          // ���ʽ
          loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
          //
          loanaccordDTO.setBizStName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // ��ȡ�������
          String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
          String tempLoanStartDate = loanaccordDTO.getLoanStartDate();
          int temp_addYear = (Integer.parseInt(tempLoanStartDate
              .substring(4, 6)) + Integer.parseInt(tempLoanTimeLimit)) / 12;
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
          loanaccordDTO.setOperson(securityInfo.getRealName());
          loanaccordDTO.setBizDate(securityInfo.getUserInfo().getPlbizDate());
          loanaccordDTO.setOverTime(overTime);
          BigDecimal totalInterest = new BigDecimal(0.00);
          totalInterest = loanaccordDTO.getCorpusInterest().multiply(
              new BigDecimal(loanaccordDTO.getLoanTimeLimit())).subtract(
              loanaccordDTO.getLoanMoney());
          loanaccordDTO.setInterestTotal(totalInterest);
          // �ж���ͳһ���գ����ǰ�������
          List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
              "2", "A");
          Object Info[] = (Object[]) loanBankParaInfo.get(0);
          String loanRepayDay = "";
          if ((Info[0] + "").equals("1")) {
            loanRepayDay = tempLoanStartDate.substring(6, 8);
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("1");
          } else {
            loanRepayDay = Info[1] + "";
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("2");
            securityInfo.getUserInfo().getPlbizDate();
          }
          sumloanMoney = sumloanMoney.add(loanaccordDTO.getLoanMoney());
        }
      }
      loanaccordShowAF.setSumloanMoney(sumloanMoney);
      pagination.setNrOfElements(loanaccordAllList.size());
      loanaccordShowAF.setList(returnList);
      loanaccordShowAF.setPrintList(loanaccordAllList);
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanaccordShowAF;
  }

  // ����ά��ɾ��
  public void removeLoanaccordInfo(String flowHeadId, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      // ����Ҫɾ����ˮͷβ��
      List temp_FlowHeadInfo = new ArrayList();
      temp_FlowHeadInfo = loanFlowTailDAO.queryLoanFlowHeadInfo(flowHeadId);
      if (!temp_FlowHeadInfo.isEmpty()) {
        Object[] info = (Object[]) temp_FlowHeadInfo.get(0);
        String contractId = info[0] + "";
        // ������ƾ֤��
        String cancelcredenceid = info[1] + "";
        // ƾ֤����������
        String yearMonth = info[2] + "";
        // ���´�
        String officeCode = info[3] + "";
        // ����pl111��¼
        BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
        // �����ʺ�
        // borrowerAcc.setLoanKouAcc("");
        // ������
        borrowerAcc.setLoanMoney(new BigDecimal(0.00));
        // ��������
        borrowerAcc.setLoanTimeLimit("");
        // ��������
        borrowerAcc.setLoanRateType("");
        // ���ʽ
        borrowerAcc.setLoanMode("");
        // ��������
        borrowerAcc.setLoanStartDate("");
        // ������
        borrowerAcc.setLoanRepayDay(new BigDecimal(0.00));
        // ʣ����
        borrowerAcc.setOverplusLoanMoney(new BigDecimal(0.00));
        // ʣ������
        borrowerAcc.setOverplusLimite("0");
        // �����µ�״̬
        borrowerAcc
            .setContractSt(BusiConst.PLCONTRACTSTATUS_ISSUEDNOTICES + "");
        // ����pl115�����»�����
        BorrowerLoanInfo borrowerLoanInfo = new BorrowerLoanInfo();
        borrowerLoanInfo = borrowerLoanInfoDAO.queryById(contractId);
        BigDecimal firstLoanMoney = null;
        borrowerLoanInfo.setFirstLoanMoney(firstLoanMoney);
        // ����ƾ֤��
        String officeId = "";
        String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
        if (loanDocNumDocument.equals("1")) {
          officeId = officeCode;
        } else {
          officeId = info[4] + "";
        }
        // plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid, officeId,
        // yearMonth.substring(0, 6));
        plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid
            .substring(8, 12), cancelcredenceid.substring(7, 8),
            cancelcredenceid.substring(0, 4));
        // ɾ����ˮβ��
        loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);
        // ɾ����ˮͷ��
        loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
        // ����ҵ����־
        String opModel = BusiLogConst.PL_OP_LOANISSUED_MAINTAIN + "";
        String opButton = BusiLogConst.BIZLOG_ACTION_DELETE + "";
        this.addPlOperateLog_sy(contractId, opModel, opButton, flowHeadId,
            securityInfo);
        // ɾ�����־
        plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_FYF(flowHeadId, "1");
      } else {
        throw new BusinessException("ɾ��ʧ��,��¼������");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public LoanaccordDTO printLoanaccordInfo(String flowHeadId,
      SecurityInfo securityInfo) throws BusinessException {
    LoanaccordDTO loanaccordDTO = new LoanaccordDTO();
    try {
      List temp_FlowHeadInfo = new ArrayList();
      temp_FlowHeadInfo = loanFlowTailDAO.queryLoanFlowHeadInfo(flowHeadId);
      Object[] info = (Object[]) temp_FlowHeadInfo.get(0);
      // ��ͬ���
      String contractId = info[0] + "";
      List loanaccordAllList = borrowerLoanInfoDAO.countBorrowerLoanList_sy(
          null, null, null, null, null, null, securityInfo,flowHeadId);
      if (!loanaccordAllList.isEmpty()) {
        for (int i = 0; i < loanaccordAllList.size(); i++) {

          loanaccordDTO = (LoanaccordDTO) loanaccordAllList.get(0);
          // ��ʾ�õ�������
          loanaccordDTO.setTemploanMonthRate(loanaccordDTO.getLoanMonthRate()
              .multiply(new BigDecimal(100.00)).toString()
              + "%");
          // ֤�����Ͷ�Ӧ������
          loanaccordDTO
              .setCardKindName(BusiTools.getBusiValue(Integer
                  .parseInt(loanaccordDTO.getCardKind()),
                  BusiConst.DOCUMENTSSTATE));
          // ͨ��bankId�������е�����
          String bankId = loanaccordDTO.getLoanBankId();
          CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
          loanaccordDTO.setLoanBankName(collBank.getCollBankName());

          // ���ʽ
          loanaccordDTO.setLoanModeName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
          //
          loanaccordDTO.setBizStName(BusiTools.getBusiValue(Integer
              .parseInt(loanaccordDTO.getBizSt()), BusiConst.PLBUSINESSSTATE));
          // ��ȡ�������
          String tempLoanTimeLimit = loanaccordDTO.getLoanTimeLimit();
          String tempLoanStartDate = loanaccordDTO.getLoanStartDate();
          int temp_addYear = (Integer.parseInt(tempLoanStartDate
              .substring(4, 6)) + Integer.parseInt(tempLoanTimeLimit)) / 12;
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
          loanaccordDTO.setFlowHeadId(new Integer(flowHeadId));
          loanaccordDTO.setOperson(securityInfo.getRealName());
          loanaccordDTO.setBizDate(securityInfo.getUserInfo().getPlbizDate());
          loanaccordDTO.setDocNum(info[1] + "");
          loanaccordDTO.setOverTime(overTime);
          BigDecimal totalInterest = new BigDecimal(0.00);
          totalInterest = loanaccordDTO.getCorpusInterest().multiply(
              new BigDecimal(loanaccordDTO.getLoanTimeLimit())).subtract(
              loanaccordDTO.getLoanMoney());
          loanaccordDTO.setInterestTotal(totalInterest);
          // �ж���ͳһ���գ����ǰ�������
          List loanBankParaInfo = loanBankParaDAO.queryLoanBankPara_sy(bankId,
              "2", "A");
          Object Info[] = (Object[]) loanBankParaInfo.get(0);
          String loanRepayDay = "";
          if ((Info[0] + "").equals("1")) {
            loanRepayDay = tempLoanStartDate.substring(6, 8);
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("1");
          } else {
            loanRepayDay = Info[1] + "";
            loanaccordDTO.setLoanRepayDay(loanRepayDay);
            loanaccordDTO.setLoanRepayDayInfo("2");
            securityInfo.getUserInfo().getPlbizDate();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("��ӡʧ��");
    }
    return loanaccordDTO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

}
