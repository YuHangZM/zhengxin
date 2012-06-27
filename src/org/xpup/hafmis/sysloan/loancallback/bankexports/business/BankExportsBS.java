package org.xpup.hafmis.sysloan.loancallback.bankexports.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.bankexports.bsinterface.IBankExportsBS;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;

public class BankExportsBS implements IBankExportsBS {

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollParaDAO collParaDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null;

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
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

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  /**
   * �жϻ�����������ڱ������һ�췵�ر������һ��
   * 
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth, String loanRepayDay) {
    String day = loanRepayDay;
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer
        .parseInt(month));
    if (Integer.parseInt(loanRepayDay) > days) {
      day = String.valueOf(days);
    }
    if (day.length() < 2 && Integer.parseInt(day) < 10) {
      day = "0" + day;
    }
    return day;
  }

  /**
   * ��ѯ���������б� ������µ������12��
   * 
   * @param securityInfo
   * @return
   */
  public List getYearMonthList(SecurityInfo securityInfo) {
    List list = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String year = bizDate.substring(0, 4);
    String month = bizDate.substring(4, 6);
    int iMonth = Integer.parseInt(month) - 1;
    int temp_month = iMonth;
    String smonth = "";
    for (int i = iMonth; i < 12; i++) {
      BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
      temp_month += 1;
      if (temp_month < 10) {
        smonth = "0" + temp_month;
      } else {
        smonth = String.valueOf(temp_month);
      }
      dto.setLoanKouYearmonth(year + smonth);
      list.add(dto);
    }
    return list;
  }

  /**
   * �������д�������
   */
  public String createDataBankCallbackList(Pagination pagination,
      SecurityInfo securityInfo, String type_gjp) throws Exception {
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String yearMonth = (String) pagination.getQueryCriterions()
        .get("yearMonth");
    String day = (String) pagination.getQueryCriterions().get("day");
    String batchNum = "";
    if (day != null && !day.equals("")) {
      if (Integer.parseInt(day) < 10) {
        day = "0" + day;
      }
    }
    String date = yearMonth + day;
    List temp_list = null;
    List callbackList = null;
    List bizStlist = null;
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    // String day = "";
    BigDecimal overLoanRepay = new BigDecimal(0.00);// �������
    BigDecimal temp_money = new BigDecimal(0.00);// ���������Ƚ��ж��Ƿ����
    int shouldCount = 0;// Ӧ������
    int realCount = 0;// ʵ������
    BigDecimal shouldCorpus = new BigDecimal(0.00);// Ӧ����������
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// Ӧ�����ڱ���
    BigDecimal shouldInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// Ӧ����Ϣ
    BigDecimal realCorpus = new BigDecimal(0.00);// Ӧ����������
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// Ӧ�����ڱ���
    BigDecimal realInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal realPunishInterest = new BigDecimal(0.00);// Ӧ����Ϣ
    BigDecimal occurMoney = new BigDecimal(0.00);// ������
    String office = securityInfo.getUserInfo().getOfficeId().toString();
    String loanRepayDay = "";// ������
    // day = bizDate.substring(6,8);
    String headId = "";
    String bankDate = "";// ��������
    // �жϸ��������ڴ�����ˮ��ͷ��PL202���Ƿ����ҵ������BIZ_TYPE=5.�������գ�ҵ��״̬=1�������ļ�¼
    if (loanBankId != null && !loanBankId.equals("")) {
      bankDate = loanBankDAO.queryPL002BizDate_jj(loanBankId.toString());
      if (!bizDate.equals(bankDate)) {
        throw new BusinessException("��¼����������ҵ�����ڲ�һ�£�������ҵ��");
      }
      int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
      String clearYear = this.getClearYear(String.valueOf(loanBankId));
      // ������-1���������PL002�е����������������
      if (iClearYear > Integer.parseInt(clearYear)) {
        throw new BusinessException(iClearYear + "����δ��ᣬ��������л���ҵ��");
      }
      List idList = null;
      if (type_gjp.equals("gjj")) {// ���ɹ����𻹴�����
        List contractIdList = loanFlowHeadDAO.queryContractId_GJP();
        if (contractIdList != null) {
          idList = new ArrayList();
          for (int i = 0; i < contractIdList.size(); i++) {
            String contract = (String) contractIdList.get(i);
            idList.add(contract);
          }
        }
      }
      List temp_list_yga = new ArrayList();
      List temp_list_ygb = new ArrayList();
      if (type_gjp.equals("gjj")) {// ���ɹ����𻹴�����
        // ��ѯ�����𻹴��Ƿ�������
        temp_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_GJP(loanBankId,
            String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), idList);
        // ��ѯ���д����Ƿ�������
        temp_list_yga = loanFlowHeadDAO.queryExportListByLoanKouAcc_GJP_yg(
            loanBankId, String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), idList);
      } else {// �������д�������
        // ��ѯ���д����Ƿ�������
        temp_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ(loanBankId,
            String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), "");
        // ��ѯ�����𻹴��Ƿ�������
        temp_list_ygb = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ_yg(
            loanBankId, String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), "");
      }
      if (!temp_list.isEmpty()) {
        Object obj[] = null;
        Iterator it = temp_list.iterator();
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj[1] != null) {
            if (date != null && !date.equals("")) {
              if (!date.equals(obj[1].toString())) {
                pagination.getQueryCriterions().put("yearMonth", null);
                pagination.getQueryCriterions().put("day", null);
                throw new BusinessException("���������Ѿ�����δ���յ����ݣ�����������");
              }
            }
          }
        }
      }
      // ��������ɹ����𻹴����ݣ������Ƿ��������������ݻ�δ���˵ģ�
      if (!temp_list_yga.isEmpty() && type_gjp.equals("gjj")) {
        pagination.getQueryCriterions().put("yearMonth", null);
        pagination.getQueryCriterions().put("day", null);
        pagination.getQueryCriterions().put("batchNum", "sun_no_batch_num");
        throw new BusinessException("���������Ѿ��������д���δ���յ����ݣ�����������");
      }
      // ����������������ݣ������Ƿ������ɹ����𻹴����ݻ�δ���˵ģ�
      if (!temp_list_ygb.isEmpty() && type_gjp.equals("bank")) {
        pagination.getQueryCriterions().put("yearMonth", null);
        pagination.getQueryCriterions().put("day", null);
        pagination.getQueryCriterions().put("batchNum", null);
        throw new BusinessException("���������Ѿ����ڹ����𻹴�δ���յ����ݣ�����������");
      }

      if (temp_list.isEmpty()) {// ���Ϊ��(202���޼�¼)��ȡ��201�����ݲ�����ˮ��
        // �жϸ��������ڴ�����ˮ��ͷ��PL202���Ƿ������������δ���˵�ҵ��
        bizStlist = loanFlowHeadDAO.queryBizStListByLoanBank_LJ(loanBankId);
        if (!bizStlist.isEmpty()) {
          throw new BusinessException("�������´���δ���˵�ҵ��");
        } else {
          String num = "";
          if (type_gjp.equals("gjj")) {// ���ɹ����𻹴�����
            List list = this.getNum_yg(loanBankId);
            for (int i = 0; i < list.size(); i++) {
              if (list.get(i) != null && !list.get(i).equals("")) {
                num = (String) list.get(i);
              }
            }
            if (num.equals("")) {
              String num_601 = this
                  .getPL601Num(office, bizDate.substring(0, 4));
              if (num_601.equals("")) {
                num = this.getPL600Num(office, bizDate.substring(0, 4));
                this.updatePL600Num(office, bizDate.substring(0, 4), num);
              } else {
                num = num_601;
                this.deletePL601Num(office, bizDate.substring(0, 4), num);
              }
              Map officeMap = securityInfo.getOfficeInnerCodeMap();
              String officecode = officeMap.get(office).toString();
              if (officecode.length() == 1) {
                officecode = "0" + officecode;
              }
              num = officecode + bizDate.substring(0, 4) + num;
            }
          }
          batchNum = num;
          if (batchNum != null && !batchNum.equals("")) {
            callbackList = restoreLoanDAO.queryRestoreLoanListByBank_LJ_gjj(
                loanBankId, yearMonth, day);
          } else {
            // ѡ�������� ������ɿۿ�����
            callbackList = restoreLoanDAO.queryRestoreLoanListByBank_LJ(
                loanBankId, yearMonth, day);
          }
          if (!callbackList.isEmpty()) {
            // ������ˮͷ��
            String officeId = "";
            String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
            if (loanDocNumDocument.equals("1")) {
              officeId = loanBankDAO.queryOfficeCodeByBankId_LJ(loanBankId);
            } else {
              officeId = loanBankId.toString();
            }
            String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanBankId,
                bizDate.substring(0, 4));
            Map office_yg = securityInfo.getOfficeInnerCodeMap();
            String officecode = office_yg.get(officeId).toString();
            if (officecode.length() == 1) {
              officecode = "0" + officecode;
            }
            docNum = bizDate.substring(0, 4) + officecode + "0" + loanBankId
                + docNum;
            // office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanBankId);
            LoanFlowHead loanFlowHead = new LoanFlowHead();
            loanFlowHead.setDocNum(docNum);
            loanFlowHead.setBizDate(bizDate);
            loanFlowHead.setBizType(String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER));
            loanFlowHead
                .setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_EXP));
            loanFlowHead.setRealCount(new BigDecimal(0.00));
            loanFlowHead.setRealCorpus(new BigDecimal(0.00));
            loanFlowHead.setRealInterest(new BigDecimal(0.00));
            loanFlowHead.setRealOverdueCorpus(new BigDecimal(0.00));
            loanFlowHead.setRealOverdueInterest(new BigDecimal(0.00));
            loanFlowHead.setRealPunishInterest(new BigDecimal(0.00));
            loanFlowHead.setOtherInterest(new BigDecimal(0.00));
            loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
            loanFlowHead.setMakePerson(securityInfo.getUserName());
            loanFlowHead.setIsFinance(new Integer(1));
            loanFlowHead.setReserveaA(yearMonth + day);
            if (batchNum != null && !batchNum.equals("")) {
              loanFlowHead.setBatchNum(batchNum.trim());
            }
            loanFlowHeadDAO.insert(loanFlowHead);
            headId = loanFlowHead.getFlowHeadId().toString();
            String temp_contractId = "0";
            callbackList = this.getCallbackList(callbackList, new Integer(
                loanBankId), date);
            BigDecimal last_temp_compare_money = new BigDecimal(0.00);// �ϴ��Ѿ�set����Ӧ������+Ӧ����Ϣ+��Ϣ
            // PL003�в�ѯ��������
            int allowdays = Integer.parseInt(loanBankParaDAO
                .queryParamExplain_LJ(Integer.valueOf(loanBankId), "A", "5"));
            for (int i = 0; i < callbackList.size(); i++) {
              BatchShouldBackListDTO dto = (BatchShouldBackListDTO) callbackList
                  .get(i);

              // ������ˮβ��
              LoanFlowTail loanFlowTail = new LoanFlowTail();
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setContractId(dto.getContractId());
              loanFlowTail.setYearMonth(dto.getLoanKouYearmonth());
              loanFlowTail.setShouldCorpus(dto.getShouldCorpus().subtract(
                  dto.getRealCorpus()));
              loanFlowTail.setShouldInterest(dto.getShouldInterest().subtract(
                  dto.getRealInterest()));
              loanFlowTail.setShouldPunishInterest(dto.getPunishInterest());
              loanFlowTail.setLoanRate(dto.getLoanRate());
              if (!temp_contractId.equals(dto.getContractId())) {
                temp_contractId = loanFlowTail.getContractId();
                overLoanRepay = dto.getOvaerLoanRepay();
                shouldCount++;
              }
              temp_money = loanFlowTail.getShouldCorpus().add(
                  loanFlowTail.getShouldInterest()).add(
                  loanFlowTail.getShouldPunishInterest());
              // ������������ڵ���Ӧ������+Ӧ����Ϣ+Ӧ����Ϣ���ڵ���ʱֱ��setβ��ʵ���ֶΣ��ڵ���ʱ������������¼���ڵ���ʱҲ�����¡�
              if (overLoanRepay.doubleValue() >= temp_money.doubleValue()) {
                loanFlowTail.setRealCorpus(loanFlowTail.getShouldCorpus());
                loanFlowTail.setRealInterest(loanFlowTail.getShouldInterest());
                loanFlowTail.setRealPunishInterest(loanFlowTail
                    .getShouldPunishInterest());
                loanFlowTail.setTempPunishInterest(dto.getTempInterest()
                    .subtract(loanFlowTail.getRealPunishInterest()));
              } else {
                loanFlowTail.setRealCorpus(new BigDecimal(0.00));
                loanFlowTail.setRealInterest(new BigDecimal(0.00));
                loanFlowTail.setRealPunishInterest(new BigDecimal(0.00));
                loanFlowTail.setTempPunishInterest(dto.getTempInterest());
              }
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanRepayDay = dto.getLoanRepayDay();
              String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
                  loanRepayDay);
              int days = this.getDays(date, dto.getLoanKouYearmonth(),
                  loanRepayDay1);
              days = days - allowdays;
              if (days <= 0) {
                loanFlowTail.setLoanType("1");
                shouldCorpus = shouldCorpus.add(loanFlowTail.getShouldCorpus());
                shouldInterest = shouldInterest.add(loanFlowTail
                    .getShouldInterest());
                realCorpus = realCorpus.add(loanFlowTail.getRealCorpus());
                realInterest = realInterest.add(loanFlowTail.getRealInterest());
              } else {
                loanFlowTail.setLoanType("2");
                shouldOverdueCorpus = shouldOverdueCorpus.add(loanFlowTail
                    .getShouldCorpus());
                shouldOverdueInterest = shouldOverdueInterest.add(loanFlowTail
                    .getShouldInterest());
                realOverdueCorpus = realOverdueCorpus.add(loanFlowTail
                    .getRealCorpus());
                realOverdueInterest = realOverdueInterest.add(loanFlowTail
                    .getRealInterest());
              }
              shouldPunishInterest = shouldPunishInterest.add(loanFlowTail
                  .getShouldPunishInterest());
              realPunishInterest = realPunishInterest.add(loanFlowTail
                  .getRealPunishInterest());
              BigDecimal temp_compare_money = new BigDecimal(0.00);// Ӧ������+Ӧ����Ϣ+��Ϣ
              // �������䷢����
              temp_compare_money = loanFlowTail.getShouldCorpus().add(
                  loanFlowTail.getShouldInterest().add(
                      loanFlowTail.getShouldPunishInterest()));
              if (overLoanRepay.intValue() <= temp_compare_money.intValue()) {
                loanFlowTail.setOccurMoney(overLoanRepay
                    .multiply(new BigDecimal(-1.00)));
                overLoanRepay = new BigDecimal(0.00);
              } else {
                loanFlowTail.setOccurMoney(temp_compare_money
                    .multiply(new BigDecimal(-1.00)));
                overLoanRepay = overLoanRepay.subtract(temp_compare_money);
              }
              last_temp_compare_money = temp_compare_money;
              occurMoney = occurMoney.add(loanFlowTail.getOccurMoney());
              loanFlowTailDAO.insert(loanFlowTail);
            }
            // ϵͳ�Զ����ɽ���ţ�ҵ������+��ˮ��
            String noteNum = "";
            noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
            // �õ�ʵ������
            realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(headId);
            LoanFlowHead loanFlowHead2 = loanFlowHeadDAO.queryById(loanFlowHead
                .getFlowHeadId());
            loanFlowHead2.setShouldCount(new BigDecimal(shouldCount));
            loanFlowHead2.setShouldCorpus(shouldCorpus);
            loanFlowHead2.setShouldInterest(shouldInterest);
            loanFlowHead2.setShouldOverdueCorpus(shouldOverdueCorpus);
            loanFlowHead2.setShouldOverdueInterest(shouldOverdueInterest);
            loanFlowHead2.setShouldPunishInterest(shouldPunishInterest);
            loanFlowHead2.setNoteNum(noteNum);
            loanFlowHead2.setOccurMoney(occurMoney);
            loanFlowHead2.setRealCorpus(realCorpus);
            loanFlowHead2.setRealCount(new BigDecimal(realCount));
            loanFlowHead2.setRealInterest(realInterest);
            loanFlowHead2.setRealOverdueCorpus(realOverdueCorpus);
            loanFlowHead2.setRealOverdueInterest(realOverdueInterest);
            loanFlowHead2.setRealPunishInterest(realPunishInterest);
            if (!type_gjp.equals("")) {
              loanFlowHead2.setBatchNum(batchNum.trim());
            }
            headId = loanFlowHead.getFlowHeadId().toString();
            // ����ҵ����־
            PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
            plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_EXP + "");
            plBizActiveLog.setBizid(new BigDecimal(headId));
            plBizActiveLog.setOperator(securityInfo.getUserName());
            plBizActiveLog.setOpTime(new Date());
            plBizActiveLog.setType(loanFlowHead.getBizType());
            plBizActiveLogDAO.insert(plBizActiveLog);

            // ���������־
            PlOperateLog plOperateLog = new PlOperateLog();
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));
            plOperateLog.setContractId(headId);
            plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_EXP + "");
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP
                + "");
            plOperateLog.setOpTime(new Date());
            plOperateLogDAO.insert(plOperateLog);
          }
        }
      } else {
        if (type_gjp.equals("gjj")) {// ���ɹ����𻹴�����
          List list = this.getNum_yg(loanBankId);
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && !list.get(i).equals("")) {
              batchNum = (String) list.get(i);
            }
          }
        }
      }
    }
    System.out.println("batchNum..." + batchNum);
    return batchNum;
  }

  /**
   * ��ѯ�б���Ϣ
   */
  public List findBankCallbackList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String yearMonth = (String) pagination.getQueryCriterions()
        .get("yearMonth");
    String day = (String) pagination.getQueryCriterions().get("day");
    String batchNum = (String) pagination.getQueryCriterions().get("batchNum");
    // String fund_st = (String)pagination.getQueryCriterions().get("fund_st");
    // String type_gjp =
    // (String)pagination.getQueryCriterions().get("type_gjp");
    String temp_yearMonth = "";
    if (day != null && !day.equals("")) {
      if (day.length() < 2) {
        if (Integer.parseInt(day) < 10) {
          day = "0" + day;
        }
      }
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List temp_list = null;
    int count = 0;
    String loanRepayDay = "";// ������
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    if (loanBankId != null && !loanBankId.equals("")) {
      // PL003�в�ѯ��������
      int allowdays = Integer.parseInt(loanBankParaDAO.queryParamExplain_LJ(
          Integer.valueOf(loanBankId), "A", "5"));
      if (batchNum != null && !batchNum.equals("")) {
        temp_list = loanFlowTailDAO.queryFlowTailByLoanBankId_GJP(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), orderBy, order, start,
            pageSize, page, yearMonth, day, bizDate, batchNum);
      } else {
        temp_list = loanFlowTailDAO.queryFlowTailByLoanBankId_LJ(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), orderBy, order, start,
            pageSize, page, yearMonth, day, bizDate);
      }
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) temp_list
              .get(i);
          headId = dto.getHeadId();
          if (dto.getLoanType().equals("1")) {
            dto.setLoanType("����");
          } else if (dto.getLoanType().equals("2")) {
            dto.setLoanType("����");
          } else {
            dto.setLoanType("����");
          }
          dto.setOccurMoney(dto.getOccurMoney());
          loanRepayDay = dto.getLoanRepayDay();
          String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
              loanRepayDay);
          temp_yearMonth = dto.getReserveaA();
          int days = this.getDays(yearMonth + day, dto.getLoanKouYearmonth(),
              loanRepayDay1);
          if (days <= allowdays) {
            dto.setDays(String.valueOf(0));
          } else {
            dto.setDays(String.valueOf(days));
          }
          list.add(dto);
        }
        pagination.getQueryCriterions().put("yearMonth",
            temp_yearMonth.substring(0, 6));
        pagination.getQueryCriterions().put("day",
            temp_yearMonth.substring(6, 8));
      }
      pagination.getQueryCriterions().put("headId", headId);
      if (batchNum != null && !batchNum.equals("")) {
        count = loanFlowTailDAO.queryFlowTailCountsByLoanBankId_GJP(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
            bizDate, batchNum);
      } else {
        pagination.getQueryCriterions().put("batchNum", null);
        count = loanFlowTailDAO.queryFlowTailCountsByLoanBankId_LJ(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
            bizDate);
      }
    }
    yearMonth = (String) pagination.getQueryCriterions().get("yearMonth");
    day = (String) pagination.getQueryCriterions().get("day");
    if (batchNum != null && batchNum.equals("sun_no_batch_num")) {
      pagination.getQueryCriterions().put("batchNum", null);
    }
    pagination.setNrOfElements(count);
    return list;
  }

  /**
   * �б�ϼƲ�ѯ
   * 
   * @param pagination
   * @return
   */
  public BatchShouldBackListDTO findTotalBankCallback(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String loanKouAcc = (String) pagination.getQueryCriterions().get(
          "loanKouAcc");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String yearMonth = (String) pagination.getQueryCriterions().get(
          "yearMonth");
      String day = (String) pagination.getQueryCriterions().get("day");
      // String type_gjp =
      // (String)pagination.getQueryCriterions().get("type_gjp");
      String batchNum = (String) pagination.getQueryCriterions()
          .get("batchNum");
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      String date = yearMonth + day;
      if (day != null && !day.equals("")) {
        if (day.length() < 2) {
          if (Integer.parseInt(day) < 10) {
            day = "0" + day;
          }
        }
      }
      if (loanBankId != null && !loanBankId.equals("")) {
        List list = null;
        // System.out.println("total..batchNum="+batchNum);
        if (batchNum != null && !batchNum.equals("")) {
          // System.out.println("if..total..batchNum="+batchNum);
          list = loanFlowTailDAO.queryFlowTailTotalByLoanBankId_GJP(loanBankId,
              null, loanKouAcc, contractId, borrowerName, cardNum, String
                  .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                  .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
              bizDate, batchNum);

        } else {
          list = loanFlowTailDAO.queryFlowTailTotalByLoanBankId_LJ(loanBankId,
              null, loanKouAcc, contractId, borrowerName, cardNum, String
                  .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                  .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
              bizDate);

        }
        if (!list.isEmpty()) {
          dto = (BatchShouldBackListDTO) list.get(0);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  /**
   * �õ����㷣Ϣ���б�
   * 
   * @param callbackList
   * @param loanBankId
   * @param bizDate
   * @return
   * @throws Exception
   */
  public List getCallbackList(List callbackList, Integer loanBankId,
      String bizDate) throws Exception {
    List temp_list = new ArrayList();
    String isRate = "";// �Ƿ��Ϣ
    String accountDate = "";// ��������
    String paramType = "A";// ��������
    String interestMode = "";// ���㷣Ϣ��ʽ
    String paramExplain = "";// ����˵��
    String allowdays = "";// ��������
    BigDecimal temp_interest = new BigDecimal(0.00);// ��ʱ��Ϣ
    String loanRepayDay = "";// ������
    // ��PL003�в�ѯ�����������Ƿ��Ϣ
    isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "5");
    // ��PL003��ȡ���㷣Ϣ��ʽ��������Ϣ�����ʡ�����ͬ�����ʡ�����ÿ��XXԪ���㣩
    interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
        "4");
    // ��PL003��ȡ��Ӧ�Ĳ���˵��
    paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
        "4");
    // PL003�в�ѯ��������
    allowdays = loanBankParaDAO
        .queryParamExplain_LJ(loanBankId, paramType, "5");
    if (!callbackList.isEmpty()) {
      for (int i = 0; i < callbackList.size(); i++) {
        BatchShouldBackListDTO dto1 = (BatchShouldBackListDTO) callbackList
            .get(i);
        BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) callbackList
            .get(i);
        loanRepayDay = this.getEndDay(dto1.getLoanKouYearmonth(), dto1
            .getLoanRepayDay());
        int days = this.getDays(bizDate, dto1.getLoanKouYearmonth(),
            loanRepayDay);// ��������
        // ��������ÿ�²����ķ�Ϣ
        // �жϻ����(Ӧ������-����+Ӧ����Ϣ-��Ϣ)�Ƿ�=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else if (days <= 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // loanRepayDay = dto1.getLoanRepayDay();
          // ������0�ж��Ƿ��ڿ��������ڼ�Ϣ
          // �������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���ֵPARAM_VALUE�Ƿ�=0(���������ڼ�Ϣ)
          if (isRate.equals(BusiConst.YES + "")) {// ��Ϣ
            temp_interest = PunishInterestBS.getTempInterestByYearMonth(
                interestMode, bizDate, dto1.getLoanKouYearmonth(),
                loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                dto1.getShouldInterest(), dto1.getRealInterest(),
                paramExplain, dto1.getLoanRate());
            // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����
//            accountDate = dto1.getBizDate();
            // String temp_day = accountDate.substring(6, 8);// �������ڵ���
            // if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay))
            // {// С�ڵ��ڻ�����
//            if (accountDate == null || accountDate.equals("")) {// �ж��Ƿ��м������ڣ�û�У����������£��У�����������
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else if (Integer.parseInt(accountDate) <= Integer.parseInt(dto1
//                .getLoanKouYearmonth()
//                + loanRepayDay)) {// С�ڵ��ڻ�����
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else {// ���ڻ�����
//              temp_interest = PunishInterestBS.getTempInterestByClearDate(
//                  interestMode, bizDate, dto1.getBizDate(), dto1
//                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
//                      .getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//              temp_interest = temp_interest.add(dto1.getPunishInterest())
//                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// �ӻ������δ����Ϣ
//            }
            // �Ȳ�ѯ�ô����˺ŵ��������������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���˵��PARAM_EXPLAIN��ֵ���������������жϣ�������ڷֱ��ȥÿ���µĻ����գ��Ƿ�<=��ֵ������������
            // �жϿ�������
            if (days <= Integer.parseInt(allowdays)) {// ��������С�ڵ��ڿ�������
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(temp_interest);
            } else {
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          } else {// ����Ϣ
            // �Ȳ�ѯ�ô����˺ŵ��������������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���˵��PARAM_EXPLAIN��ֵ���������������жϣ�������ڷֱ��ȥÿ���µĻ����գ��Ƿ�<=��ֵ������������
            // �жϿ�������
            if (days <= Integer.parseInt(allowdays)) {// ��������С�ڵ��ڿ�������
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(new BigDecimal(0.00));
            } else {// �Ѿ�����
              // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����+��������
              // String temp_day = dto1.getBizDate().substring(6, 8);// �������ڵ���
              // ����������+�������������ɵ�������
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay, Integer.parseInt(allowdays));
              temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
                  loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                  dto1.getShouldInterest(), dto1.getRealInterest(),
                  paramExplain, allowdays, dto1.getLoanRate());
              // temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
              // if (Integer.parseInt(temp_day) <=
              // Integer.parseInt(temp_loanRepayDay)) {// С�ڵ��ڻ�����+��������
//              if (dto1.getBizDate() == null || dto1.getBizDate().equals("")) {// �ж��Ƿ��м�������
//                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
//                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                    loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                    dto1.getShouldInterest(), dto1.getRealInterest(),
//                    paramExplain, allowdays, dto1.getLoanRate());
//              } else if (Integer.parseInt(dto1.getBizDate()) <= Integer
//                  .parseInt(temp_loanRepayDay)) {// С�ڵ��ڻ�����+��������
//                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
//                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                    loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                    dto1.getShouldInterest(), dto1.getRealInterest(),
//                    paramExplain, allowdays, dto1.getLoanRate());
//              } else {// ���ڻ�����+��������
//                temp_interest = PunishInterestBS.getTempInterestByClearDate(
//                    interestMode, bizDate, dto1.getBizDate(), dto1
//                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
//                        .getShouldInterest(), dto1.getRealInterest(),
//                    paramExplain, dto1.getLoanRate());
//                // �ӻ������δ����Ϣ
//                temp_interest = temp_interest.add(dto1.getPunishInterest())
//                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
//              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setLoanRate(dto1.getLoanRate());// ��ʾÿ������
        dto2.setDays(String.valueOf(days));
        temp_list.add(dto2);
      }
    }
    return temp_list;
  }

  /**
   * ȡ����������
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findExportList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    List list = null;
    List list_fund = null;
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String yearMonth = (String) pagination.getQueryCriterions()
        .get("yearMonth");
    String day = (String) pagination.getQueryCriterions().get("day");
    String batchNum = (String) pagination.getQueryCriterions().get("batchNum");
    String date = yearMonth.substring(0, 4) + "-" + yearMonth.substring(4, 6)
        + "-" + day;
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // �жϸô����˺ŵ��������������д������PL003������Ϊ������������������Ϊ1�в���ֵ�Ƿ�=1:���ۿ�
    String paramValue = this.getBackMode(loanBankId);
    List exportlist = new ArrayList();
    paramValue = this.getBackMode(loanBankId);
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    try {
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // ���ۿ�
        // ���µ���ÿ�������˺ŵĻ����¼
        // ���Ϊÿ��Ӧ�ս��=��Ӧ���µģ�Ӧ������SHOULD_CORPUS+Ӧ����ϢSHOULD_INTEREST+Ӧ����ϢSHOULD_PUNISH_INTEREST��+������
        list = loanFlowTailDAO.queryExportFlowTail_LJA(headId);
        list_fund = loanFlowTailDAO.queryExportFlowTail_fund(headId);
        BigDecimal money = new BigDecimal(0.00);
        if (!list.isEmpty()) {
          Vector vectorHead = new Vector();
          // bankExportsDTO.setBizDate(bizDate);
          // bankExportsDTO.setLoanBankId(loanBankId);
          vectorHead.add(bizDate.toString());
          vectorHead.add(loanBankId.toString());
          if (batchNum != null && !batchNum.equals("")) {
            vectorHead.add(batchNum);

            vectorHead.add("|");
            vectorHead.add("");
            vectorHead.add("");
            exportlist.add(vectorHead);
            for (int i = 0; i < list_fund.size(); i++) {
              // BankExportsDTO dto1 = new BankExportsDTO();
              Vector vector = new Vector();
              BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list_fund
                  .get(i);
              vector.add(dto2.getLoanKouAcc().toString());
              vector.add(dto2.getBorrowerName().toString());
              vector.add(dto2.getLoanKouYearmonth().toString());
              vector.add(dto2.getRealMoney().toString());
              vector.add("0");
              money = money.add(dto2.getRealMoney());
              if (dto2.getRealMoney().doubleValue() > 0) {
                exportlist.add(vector);
              }
            }
            Vector vectorEnd = new Vector();
            vectorEnd.add("�����ϼƣ�" + loanFlowHead.getShouldCount().toString()
                + "   ");
            vectorEnd.add("�����ϼƣ�" + list_fund.size() + "   ");
            vectorEnd.add("���ϼƣ�" + money.toString() + "   ");

            vectorEnd.add("");
            vectorEnd.add("");
            exportlist.add(vectorEnd);
          } else {
            vectorHead.add("|");
            vectorHead.add("");
            vectorHead.add("");
            exportlist.add(vectorHead);
            for (int i = 0; i < list.size(); i++) {
              // BankExportsDTO dto1 = new BankExportsDTO();
              Vector vector = new Vector();
              BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list
                  .get(i);
              vector.add(dto2.getLoanKouAcc().toString());
              vector.add(dto2.getBorrowerName().toString());
              vector.add(dto2.getLoanKouYearmonth().toString());
              vector.add(dto2.getRealMoney().toString());
              System.out.println(dto2.getRealMoney().toString());
              vector.add("0");
              money = money.add(dto2.getRealMoney());
              if (dto2.getRealMoney().doubleValue() > 0) {
                exportlist.add(vector);
              }
            }
            Vector vectorEnd = new Vector();
            vectorEnd.add("�����ϼƣ�" + loanFlowHead.getShouldCount().toString()
                + "   ");
            vectorEnd.add("���ϼƣ�" + money.toString() + "   ");
            vectorEnd.add("");
            vectorEnd.add("");
            vectorEnd.add("");
            exportlist.add(vectorEnd);
          }

        }
      } else {
        // ȫ��ۿ�
        // һ�������˺ŵ���һ�������¼
        // ���=�ô����˺ű���ҵ�������µ�ʵ�ս��ϼ�=sum��Ӧ������SHOULD_CORPUS+Ӧ����ϢSHOULD_INTEREST+Ӧ����ϢSHOULD_PUNISH_INTEREST��+������
        list = loanFlowTailDAO.queryExportFlowTail_LJB(headId);
        if (!list.isEmpty()) {
          Vector vectorHead = new Vector();
          BigDecimal money = new BigDecimal(0.00);
          // bankExportsDTO.setBizDate(bizDate);
          // bankExportsDTO.setLoanBankId(loanBankId);
          vectorHead.add(bizDate.toString());
          vectorHead.add(loanBankId.toString());
          if (batchNum != null && !batchNum.equals("")) {
            vectorHead.add(batchNum);
          }
          vectorHead.add("|");
          vectorHead.add("|");
          vectorHead.add("|");
          exportlist.add(vectorHead);
          for (int i = 0; i < list.size(); i++) {
            // BankExportsDTO dto1 = new BankExportsDTO();
            Vector vector = new Vector();
            BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list.get(i);
            vector.add(dto2.getLoanKouAcc().toString());
            vector.add(dto2.getBorrowerName().toString());
            vector.add(dto2.getRealMoney().toString());
            money = money.add(dto2.getRealMoney());
            if (dto2.getRealMoney().doubleValue() > 0) {
              exportlist.add(vector);
            }
          }
          Vector vectorEnd = new Vector();
          vectorEnd.add("�����ϼƣ�" + loanFlowHead.getShouldCount().toString()
              + "   ");
          vectorEnd.add("���ϼƣ�" + money.toString() + "   ");
          vectorEnd.add("");
          exportlist.add(vectorEnd);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("����ʧ�ܣ�");
    }
    return exportlist;
  }

  /**
   * �õ��ۿʽ
   * 
   * @param loanBankId
   * @return
   */
  public String getBackMode(String loanBankId) throws Exception {
    String paramValue = "";
    try {
      paramValue = loanBankParaDAO.queryParamValue_LJ(new Integer(loanBankId),
          "A", "1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  /**
   * ������������
   * 
   * @param bizDate ҵ������
   * @param yearMonth ��������
   * @param loanRepayDay ������
   * @return
   */
  public int getDays(String bizDate, String yearMonth, String loanRepayDay) {
    int days = 0;
    String temp_date = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + loanRepayDay;
    String temp_bizDate = bizDate.substring(0, 4) + "-"
        + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
    // ��������
    days = BusiTools.minusDate(temp_bizDate, temp_date);
    return days;
  }

  /**
   * ��ѡɾ��
   * 
   * @param tailId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteTailInfo(String tailId, SecurityInfo securityInfo)
      throws Exception {
    // �жϸñ�ҵ���ڴ�����ˮ��ͷ��PL203�е�ҵ��״̬BIZ_ST�Ƿ�=1������
    // ����PL202����FLOW_HEAD_ID������??�б���ֻ����ʾ����״̬�ļ�¼�������ж���
    int shouldCount = 0;// Ӧ������
    BigDecimal shouldCorpus = new BigDecimal(0.00);// Ӧ����������
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// Ӧ�����ڱ���
    BigDecimal shouldInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// Ӧ����Ϣ
    BigDecimal occurMoney = new BigDecimal(0.00);// ������
    String temp_contractId = "";
    String office = "";// ���´�����
    LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(tailId));
    String headId = loanFlowTail.getFlowHeadId().toString();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String bizSt = loanFlowHead.getBizSt();
    if (!bizSt.equals(String.valueOf(BusiConst.PLBUSINESSSTATE_EXP))) {
      throw new BusinessException("������¼���ǵ���״̬������ɾ����");
    }
    try {

      loanFlowTailDAO.insertPL601Num(securityInfo.getUserInfo().getOfficeId()
          .toString(), securityInfo.getUserInfo().getBizDate().substring(0, 4),
          loanFlowHead.getBatchNum().substring(6, 10));
      loanFlowTailDAO.delete(loanFlowTail);
      // �ж�PL203����FLOW_HEAD_ID�ڴ�����ˮ��β��PL203���Ƿ񻹴��ڼ�¼
      List list = loanFlowTailDAO.queryBankLoanFlowTailByHeadId_LJ(headId);
      if (list.isEmpty()) {// �����ڼ�¼
        loanFlowTailDAO.delete(loanFlowTail);
        // ����ƾ֤��
        office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
            .getLoanBankId().toString());
        String officeId = "";
        String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
        if (loanDocNumDocument.equals("1")) {
          officeId = office;
        } else {
          officeId = loanFlowHead.getLoanBankId().toString();
        }
        // office =
        // loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
        // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
        // officeId, loanFlowHead.getBizDate().substring(0, 6));
        plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
            .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
            loanFlowHead.getDocNum().substring(0, 4));
        // ɾ��ҵ������־
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead
            .getBizSt());
        // ɾ���ñ�ҵ�����ڴ�����ˮ��ͷ��PL202�м�¼������������PL202����FLOW_HEAD_ID��
        loanFlowHeadDAO.delete(loanFlowHead);
      } else {// ����ͷ��
        // ���¸ñ�ҵ������ˮ��ͷ��PL202�е�Ӧ����������Ӧ�����ڱ���Ӧ��������Ϣ��Ӧ��������Ϣ��Ӧ����Ϣ��Ӧ������
        for (int i = 0; i < list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) list.get(i);
          if (!temp_contractId.equals(dto.getContractId())) {
            temp_contractId = dto.getContractId();
            shouldCount++;
          }
          if (dto.getLoanType().equals("1")) {
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          } else if (dto.getLoanType().equals("2")) {
            shouldOverdueCorpus = shouldOverdueCorpus
                .add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto
                .getShouldInterest());
          }
          shouldPunishInterest = shouldPunishInterest.add(dto
              .getPunishInterest());
          occurMoney = occurMoney.add(dto.getOccurMoney());
        }
        loanFlowHead.setShouldCount(new BigDecimal(shouldCount));
        loanFlowHead.setShouldCorpus(shouldCorpus);
        loanFlowHead.setShouldInterest(shouldInterest);
        loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
        loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
        loanFlowHead.setShouldPunishInterest(shouldPunishInterest);
        loanFlowHead.setOccurMoney(occurMoney);
      }
      // ���������־
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP + "");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      throw new BusinessException("ɾ��ʧ�ܣ�");
    }
  }

  /**
   * ȫ��ɾ��
   * 
   * @param pagination
   * @param securityInfo
   */
  public void deleteTailList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    int shouldCount = 0;// Ӧ������
    BigDecimal shouldCorpus = new BigDecimal(0.00);// Ӧ����������
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// Ӧ�����ڱ���
    BigDecimal shouldInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// Ӧ����Ϣ
    BigDecimal occurMoney = new BigDecimal(0.00);// ������
    String temp_contractId = "";
    String office = "";// ���´�����
    try {
      loanFlowTailDAO.deleteFlowTailByLoanBankId_LJ(headId, loanKouAcc,
          contractId, borrowerName, cardNum, String
              .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
              .valueOf(BusiConst.PLBUSINESSSTATE_EXP));
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      if (loanFlowHead.getBatchNum() != null
          && !loanFlowHead.getBatchNum().equals("")) {
        loanFlowTailDAO.insertPL601Num(securityInfo.getUserInfo().getOfficeId()
            .toString(), securityInfo.getUserInfo().getBizDate()
            .substring(0, 4), loanFlowHead.getBatchNum().substring(6, 10));
      }
      String bizSt = loanFlowHead.getBizSt();
      if (!bizSt.equals(String.valueOf(BusiConst.PLBUSINESSSTATE_EXP))) {
        throw new BusinessException("��¼���ǵ���״̬������ɾ����");
      }
      // �ж�PL203����FLOW_HEAD_ID�ڴ�����ˮ��β��PL203���Ƿ񻹴��ڼ�¼
      List list = loanFlowTailDAO.queryBankLoanFlowTailByHeadId_LJ(headId);
      if (list.isEmpty()) {// �����ڼ�¼
        loanFlowTailDAO.deleteFlowTailByLoanBankId_LJ(headId, loanKouAcc,
            contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP));
        // ����ƾ֤��
        office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
            .getLoanBankId().toString());
        String officeId = "";
        String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
        if (loanDocNumDocument.equals("1")) {
          officeId = office;
        } else {
          officeId = loanFlowHead.getLoanBankId().toString();
        }
        // office =
        // loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
        // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
        // officeId, loanFlowHead.getBizDate().substring(0, 6));
        plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
            .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
            loanFlowHead.getDocNum().substring(0, 4));
        // ɾ��ҵ������־
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead
            .getBizSt());
        // ɾ���ñ�ҵ�����ڴ�����ˮ��ͷ��PL202�м�¼������������PL202����FLOW_HEAD_ID��
        loanFlowHeadDAO.delete(loanFlowHead);
      } else {// ����ͷ��
        // ���¸ñ�ҵ������ˮ��ͷ��PL202�е�Ӧ����������Ӧ�����ڱ���Ӧ��������Ϣ��Ӧ��������Ϣ��Ӧ����Ϣ��Ӧ������
        for (int i = 0; i < list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) list.get(i);
          if (!temp_contractId.equals(dto.getContractId())) {
            temp_contractId = dto.getContractId();
            shouldCount++;
          }
          if (dto.getLoanType().equals("1")) {
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          } else if (dto.getLoanType().equals("2")) {
            shouldOverdueCorpus = shouldOverdueCorpus
                .add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto
                .getShouldInterest());
          }
          shouldPunishInterest = shouldPunishInterest.add(dto
              .getPunishInterest());
          occurMoney = occurMoney.add(dto.getOccurMoney());
        }
        loanFlowHead.setShouldCount(new BigDecimal(shouldCount));
        loanFlowHead.setShouldCorpus(shouldCorpus);
        loanFlowHead.setShouldInterest(shouldInterest);
        loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
        loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
        loanFlowHead.setShouldPunishInterest(shouldPunishInterest);
        loanFlowHead.setOccurMoney(occurMoney);
      }
      // ���������־
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETEALL + "");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP + "");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("ɾ��ʧ�ܣ�");
    }

  }

  /**
   * ��ӡ�б���Ϣ
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List getPrintList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    List list = new ArrayList();
    List temp_list = null;
    String loanRepayDay = "";
    try {
      temp_list = loanFlowTailDAO.queryPrintFlowTailByLoanBankId_LJ(loanBankId,
          headId, loanKouAcc, contractId, borrowerName, cardNum, String
              .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
              .valueOf(BusiConst.PLBUSINESSSTATE_EXP));
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) temp_list
              .get(i);
          headId = dto.getHeadId();
          if (dto.getLoanType().equals("1")) {
            dto.setLoanType("����");
          } else if (dto.getLoanType().equals("2")) {
            dto.setLoanType("����");
          } else {
            dto.setLoanType("����");
          }
          dto.setOccurMoney(dto.getOccurMoney());
          loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
              .getLoanRepayDay());
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
              loanRepayDay);
          if (days <= 0) {
            days = 0;
          }
          dto.setDays(String.valueOf(days));
          list.add(dto);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public String getPL600Num(String office, String bizDate) throws Exception {
    String num = "";
    String s = "";
    try {
      num = loanFlowTailDAO.queryPL600Num(office, bizDate);
      int len1 = num.length();
      num = (Integer.parseInt(num) + 1) + "";
      int len2 = num.length();
      for (int i = 0; i < len1 - len2; i++) {
        s += "0";
      }
      num = s + num;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return num;
  }

  public String getPL601Num(String office, String bizDate) throws Exception {
    String num = "";
    try {
      num = loanFlowTailDAO.queryPL601Num(office, bizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return num;
  }

  public List getNum_yg(String bankid) throws Exception {
    List list = new ArrayList();
    try {
      list = loanFlowTailDAO.queryNum_yg(bankid);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void updatePL600Num(String office, String bizDate, String num)
      throws Exception {
    try {
      loanFlowTailDAO.updatePL600Num(office, bizDate, num);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deletePL601Num(String office, String bizDate, String num)
      throws Exception {
    try {
      loanFlowTailDAO.deletePL601Num(office, bizDate, num);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void insertPL601Num(String office, String bizDate, String num)
      throws Exception {
    try {
      loanFlowTailDAO.insertPL601Num(office, bizDate, num);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ȡ��������
   * 
   * @param loanBankId
   * @return
   */
  public String getClearYear(String loanBankId) {
    String year = "";// ������
    year = loanBankDAO.queryYearClearByBankId_sy(loanBankId);
    return year;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public String getNamePara() throws Exception {
    String name = "";
    name = collParaDAO.getNamePara();
    return name;
  }
}
