package org.xpup.hafmis.sysloan.loancallback.bankimports.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BankExportsDTO;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.bankimports.bsinterface.IBankImportsBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;

public class BankImportsBS implements IBankImportsBS {
  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollParaDAO collParaDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private CollBankDAO collBankDAO = null;

  private SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

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
   * ��������ID��ѯ����Name
   * 
   * @param loanBankId
   * @return
   * @throws Exception
   */
  public String getLoanBankName(String loanBankId) throws Exception {
    String loanBankName = "";
    try {
      if (loanBankId != null && !loanBankId.equals("")) {
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
        loanBankName = collBank.getCollBankName();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanBankName;
  }

  /**
   * ��ѯ��ͬ���
   * 
   * @param loanKouAcc
   * @param contractSt
   * @param securityInfo
   * @return
   */
  public String findBorrowerAcc(String loanKouAcc, String contractSt,
      SecurityInfo securityInfo) {
    String contractId = "";
    contractId = borrowerAccDAO.queryBorrowerAccByLoanKouAcc_LJ(loanKouAcc,
        contractSt, securityInfo);
    return contractId;
  }

  /**
   * ��ѯ�ô����˺ŵĻ�����
   * 
   * @param loanKouAcc
   * @param contractSt
   * @param securityInfo
   * @return
   */
  public String getLoanRepayDay(String loanKouAcc, String contractSt,
      SecurityInfo securityInfo) {
    String loanRepayDay = "";
    loanRepayDay = borrowerAccDAO.queryLoanRepayDayByLoanKouAcc_LJ(loanKouAcc,
        contractSt, securityInfo).toString();
    return loanRepayDay;
  }

  /**
   * ��ѯ���д��۵����б�
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findBankCallbackList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
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
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List temp_list = null;
      int count = 0;
      int allCount = 0;
      String loanRepayDay = "";
      if (loanBankId != null && !loanBankId.equals("")) {
        temp_list = loanFlowTailDAO.queryImportFlowTailByLoanBankId_LJ(
            loanBankId, null, loanKouAcc, contractId, borrowerName, cardNum,
            String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_IMP), orderBy, order, start,
            pageSize, page, securityInfo);
        // PL003�в�ѯ��������
        int allowdays = Integer.parseInt(loanBankParaDAO
            .queryParamExplain_LJ(Integer.valueOf(loanBankId), "A", "5"));
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
            if (dto.getRealMoney().doubleValue() < 0) {
              dto.setRealMoney(new BigDecimal(0.00));
            }
            loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
                .getLoanRepayDay());
            int days = this.getDays(dto.getBizDate(),
                dto.getLoanKouYearmonth(), loanRepayDay);
            if(days > allowdays)
              dto.setDays(String.valueOf(days));
            else 
              dto.setDays("0");
            list.add(dto);
          }
        }
        pagination.getQueryCriterions().put("headId", headId);
        count = loanFlowTailDAO.queryImportFlowTailCountsByLoanBankId_LJ(
            loanBankId, headId, loanKouAcc, contractId, borrowerName, cardNum,
            String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_IMP), securityInfo);
        List countList = loanFlowTailDAO
            .queryImportLoanFlowTailIDByHeadId_LJ(headId);
        allCount = countList.size();
      }
      pagination.setNrOfElements(count);
      pagination.getQueryCriterions().put("allCount", String.valueOf(allCount));
    } catch (Exception e) {
      e.printStackTrace();
    }
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
      if (loanBankId != null && !loanBankId.equals("")) {
        List list = loanFlowTailDAO.queryImportFlowTailTotalByLoanBankId_LJ(
            loanBankId, null, loanKouAcc, contractId, borrowerName, cardNum,
            String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_IMP), securityInfo);
        if (!list.isEmpty()) {
          dto = (BatchShouldBackListDTO) list.get(0);
          if (dto.getRealMoney().intValue() <= 0) {
            dto.setRealMoney(new BigDecimal(0.00));
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
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
    try {
      String temp_date = yearMonth.substring(0, 4) + "-"
          + yearMonth.substring(4, 6) + "-" + loanRepayDay;
      String temp_bizDate = bizDate.substring(0, 4) + "-"
          + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
      // ��������
      days = BusiTools.minusDate(temp_bizDate, temp_date);
      if (days <= 0) {
        days = 0;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return days;
  }

  /**
   * ������Ϊ��(û�ж�Ӧ�ĵ����ļ�) ���������ݲ���ҵ����ˮ��
   * 
   * @param importList
   * @param securityInfo
   */
  public String addBankImportsInfo(List importList, SecurityInfo securityInfo)
      throws Exception {
    String loanBankId = "";// ���д���
    String office = "";// ���´�����
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// ҵ������
    String bizType = "";// ҵ������
    String contractId = "";// ��ͬ���
    String loanRepayDay = "";// ������
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING + "";// ��ͬ״̬ 11.������
    BigDecimal shouldCorpus = new BigDecimal(0.00);// Ӧ����������
    BigDecimal shouldInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// Ӧ�����ڱ���
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// Ӧ��������Ϣ
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// Ӧ����Ϣ
    BigDecimal realCorpus = new BigDecimal(0.00);// ʵ������
    BigDecimal realInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// ʵ�����ڱ���
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// ʵ��������Ϣ
    BigDecimal realPunishInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal temp_realCorpus = new BigDecimal(0.00);// ʵ������
    BigDecimal temp_realInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal temp_realOverdueCorpus = new BigDecimal(0.00);// ʵ�����ڱ���
    BigDecimal temp_realOverdueInterest = new BigDecimal(0.00);// ʵ��������Ϣ
    BigDecimal temp_realPunishInterest = new BigDecimal(0.00);// ʵ����Ϣ
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    int realCount = 0;// ʵ������
    String bizDateim = "";
    List bankList = null;
    if (!importList.isEmpty()) {
      try {
        for (int i = 0; i < importList.size(); i++) {
          LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
              .get(i);
          if (i == 0) {
            loanBankId = dto.getLoanBankId();// ���д���

            int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
            String clearYear = this.getClearYear(String.valueOf(loanBankId));
            // ������-1���������PL002�е����������������
            if (iClearYear > Integer.parseInt(clearYear)) {
              throw new BusinessException(iClearYear + "����δ��ᣬ��������л���ҵ��");
            }
            bizType = dto.getBizType();
            if (bizType == null) {
              throw new BusinessException("ҵ�����Ͳ���Ϊ�գ�");
            } else if (!bizType.equals(String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER))) {
              throw new BusinessException("��ҵ�����Ͳ����ڴ˵��룡");
            }
            bizDateim = dto.getBizDate();// �����ļ��е�����
            // �ۿ������Ƿ�����������ͬ
            String temp_bizDate = bizDate.substring(0, 4) + "-"
                + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
            if (!temp_bizDate.equals(bizDateim)) {
              throw new BusinessException("�����ļ��е������������ڲ�һ�£�");
            }
            bankList = securityDAO.getDkUserBankList_LJ(securityInfo
                .getUserName(), loanBankId);
            // �ж����޲��������е�Ȩ��
            if (bankList.isEmpty()) {
              throw new BusinessException("���߱������е�Ȩ�ޣ�");
            }
            // �������ڴ�����ˮ��ͷ��PL202�����Ƿ����ҵ��״̬Ϊ2�����루����������ˮ��β��PL203��
            // List temp_list =
            // loanFlowHeadDAO.queryLoanFlowImportByLoanBankId_LJ(loanBankId,
            // String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
            // if(!temp_list.isEmpty()){
            // throw new BusinessException("���������Ѿ�����δ���˵ĵ����¼�����ܵ���");
            // }
            List temp_list = null;
            // �жϸ��������ڴ�����ˮ��ͷ��PL202���Ƿ������������δ���˵�ҵ��(
            // ҵ������Ϊ2.���ʻ��գ�3.������ǰ���4.һ�����廹��5.�������գ�6.���˺��������ģ���7.���˺�������������8.�����ջأ����ģ���9.�����ջأ�������12.���˵���13.���ˣ����ᣩ��ҵ��״̬=2.���룬3.�Ǽǣ�4.ȷ�ϣ�5.���˵�ҵ��)
            temp_list = loanFlowHeadDAO.queryBizStListImportByLoanBank_LJ(
                loanBankId, bizType);
            if (!temp_list.isEmpty()) {
              throw new BusinessException("�������´���δ���˵����������¼�����ܵ���");
            }
            // ��������
            office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanBankId);
            // ������ˮͷ��
            String officeId = "";
            String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
            if (loanDocNumDocument.equals("1")) {
              officeId = office;
            } else {
              officeId = loanBankId;
            }
            loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(
                officeId, bizDate.substring(0, 6)));
            loanFlowHead.setBizDate(bizDate);
            // ҵ�����͸��ݵ����ļ���ҵ�����ͱ�ʶ�����루5.�������գ���
            loanFlowHead.setBizType(bizType);
            loanFlowHead.setOtherInterest(new BigDecimal(0.00));
            loanFlowHead.setOccurMoney(new BigDecimal(0.00));
            loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
            loanFlowHead
                .setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
            loanFlowHead.setMakePerson(securityInfo.getUserName());
            loanFlowHead.setIsFinance(new Integer(1));
            loanFlowHeadDAO.insert(loanFlowHead);
          } else {
            contractId = this.findBorrowerAcc(dto.getLoanKouAcc(), contractSt,
                securityInfo);
            loanRepayDay = this.getLoanRepayDay(dto.getLoanKouAcc(),
                contractSt, securityInfo);
            if (contractId == null || contractId.equals("")) {
              throw new BusinessException("�ô����˺Ų����ڣ�");
            }
            // ������ˮβ��
            LoanFlowTail loanFlowTail = new LoanFlowTail();
            temp_realCorpus = new BigDecimal(dto.getRealCorpus());
            temp_realInterest = new BigDecimal(dto.getRealInterest());
            temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
            temp_realOverdueInterest = new BigDecimal(dto
                .getRealOverdueInterest());
            temp_realPunishInterest = new BigDecimal(dto
                .getRealPunishInterest());
            shouldCorpus = shouldCorpus.add(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())));
            shouldInterest = shouldInterest.add(new BigDecimal(dto
                .getRealInterest())
                .add(new BigDecimal(dto.getNobackInterest())));
            realCorpus = realCorpus.add(new BigDecimal(dto.getRealCorpus()));
            realInterest = realInterest.add(new BigDecimal(dto
                .getRealInterest()));
            shouldOverdueCorpus = shouldOverdueCorpus.add(new BigDecimal(dto
                .getRealOverdueCorpus()).add(new BigDecimal(dto
                .getNobackOverdueCorpus())));
            shouldOverdueInterest = shouldOverdueInterest.add(new BigDecimal(
                dto.getRealOverdueInterest()).add(new BigDecimal(dto
                .getNobackOverdueInterest())));
            realOverdueCorpus = realOverdueCorpus.add(new BigDecimal(dto
                .getRealOverdueCorpus()));
            realOverdueInterest = realOverdueInterest.add(new BigDecimal(dto
                .getRealOverdueInterest()));
            shouldPunishInterest = shouldPunishInterest.add(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            realPunishInterest = realPunishInterest.add(new BigDecimal(dto
                .getRealPunishInterest()));
            // ����������ݺ��������ݶ�����0������ˮ���в����������ݣ�һ����������Ϊ1������һ��Ϊ2����
            if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0
                && temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
                    temp_realPunishInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getRealOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getRealOverdueInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);

              LoanFlowTail loanFlowTail1 = new LoanFlowTail();
              loanFlowTail1.setContractId(contractId);
              loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail1.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail1.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail1.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail1.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail1.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail1.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail1.setShouldCorpus(new BigDecimal(dto
                  .getNobackCorpus()).add(new BigDecimal(dto
                  .getNobackOverdueCorpus())));
              loanFlowTail1.setShouldInterest(new BigDecimal(dto
                  .getNobackInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail1.setYearMonth(dto.getYearMonth());
              loanFlowTail1.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail1);
              // �����������ݴ���0�������ݵ���0������һ����������Ϊ1����
            } else if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getRealOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getRealOverdueInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);
              // �����������ݴ���0�������ݵ���0������һ����������Ϊ2����
            } else if (temp_realOverdueCorpus.add(temp_realOverdueInterest)
                .add(temp_realPunishInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail
                  .setShouldCorpus(new BigDecimal(dto.getNobackCorpus())
                      .add(new BigDecimal(dto.getNobackOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getNobackInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail);
            }
            // String loanRepayDay1 = this.getEndDay(dto.getYearMonth(),
            // loanRepayDay);
            // //��������
            // int days = this.getDays(bizDate, dto.getYearMonth(),
            // loanRepayDay1);
            // if(days<=0){
            // loanFlowTail.setLoanType("1");
            // }else{
            // loanFlowTail.setLoanType("2");
            // }
          }
        }
        // �õ�ʵ������
        realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(loanFlowHead
            .getFlowHeadId().toString());
        // ����ͷ����Ϣ
        LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
            .getFlowHeadId());
        loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());
        loanFlowHead1.setRealCorpus(realCorpus);
        loanFlowHead1.setRealCount(new BigDecimal(realCount));
        loanFlowHead1.setRealInterest(realInterest);
        loanFlowHead1.setRealOverdueCorpus(realOverdueCorpus);
        loanFlowHead1.setRealOverdueInterest(realOverdueInterest);
        loanFlowHead1.setRealPunishInterest(realPunishInterest);
        loanFlowHead1.setShouldCorpus(shouldCorpus);
        loanFlowHead1.setShouldCount(new BigDecimal(realCount));
        loanFlowHead1.setShouldInterest(shouldInterest);
        loanFlowHead1.setShouldOverdueCorpus(shouldOverdueCorpus);
        loanFlowHead1.setShouldOverdueInterest(shouldOverdueInterest);
        loanFlowHead1.setShouldPunishInterest(shouldPunishInterest);

        // ������־
        this.addLog(loanFlowHead1, securityInfo);

      } catch (Exception e) {
        e.printStackTrace();
        throw new BusinessException(e.getMessage());
      }
    } else {
      throw new BusinessException("�����ļ�Ϊ�գ�");
    }
    return loanBankId;
  }

  /**
   * ������Ϊ��(�ж�Ӧ�ĵ����ļ�) ���ݵ������ݸ���ҵ����ˮ��
   * 
   * @param importList
   * @param securityInfo
   * @throws Exception
   */
  public void updateBankImportsInfo(List importList, SecurityInfo securityInfo)
      throws Exception {
    String loanBankId = "";// ���д���
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// ҵ������
    String bizDateim = "";// �����ļ��е�����
    String batchNum = "";// ���κ�
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    String paramValue = "";
    String headId = "";
    List temp_list = null;
    List import_list = null;
    List bankList = null;
    String bankDate = "";// ��������
    if (!importList.isEmpty()) {
      // try{
      // �жϵ���������Ƿ�Ϸ������ҵ������ݵ������ڴ�����ˮ��ͷ��PL202�Ƿ����ҵ��״̬=1�������ļ�¼
      // 1�����д����Ƿ�Ϸ�2���ۿ������Ƿ�����������ͬ0
      BankExportsDTO dto = (BankExportsDTO) importList.get(0);
      loanBankId = String.valueOf(Integer.parseInt(dto.getLoanBankId()));
      bizDateim = dto.getBizDate();
      batchNum = dto.getBatchNum();
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(),
          loanBankId);
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
      // �ж����޲��������е�Ȩ��
      if (bankList.isEmpty()) {
        throw new BusinessException("���߱������е�Ȩ�ޣ�");
      }
      // �ж���û�е�������
      import_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ(loanBankId,
          String.valueOf(BusiConst.PLBUSINESSSTATE_IMP), batchNum);
      if (!import_list.isEmpty()) {
        throw new BusinessException("�������Ѿ����ڵ����¼��");
      }
      // �������ݵ������ڴ�����ˮ��ͷ��PL202�Ƿ����ҵ��״̬=1�������ļ�¼
      temp_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ(loanBankId,
          String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), batchNum);
      if (temp_list.isEmpty()) {
        throw new BusinessException("������û�ж�Ӧ�ĵ�����¼��");
      } else {
        // ȡ��ͷ��ID
        Iterator it = temp_list.iterator();
        Object[] obj = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          headId = obj[0].toString();
        }
      }
      // 2���ۿ������Ƿ�����������ͬ
      bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
          + bizDate.substring(6, 8);
      if (!bizDate.equals(bizDateim)) {
        throw new BusinessException("�����ļ��е������������ڲ�һ�£�");
      }
      // �жϸô����˺ŵ��������������д������PL003������Ϊ��A:����������������Ϊ1�в���ֵ�Ƿ�=1:���ۿ�
      paramValue = this.getBackMode(loanBankId);
      List tailList = loanFlowTailDAO.queryLoanFlowTailIDByHeadId_LJ(headId);
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // ���ۿ�
        loanFlowHead = this.addLoanFlowHeadFull(tailList, importList, headId,
            batchNum);
      } else {
        // ȫ��ۿ�
        loanFlowHead = this.addLoanFlowHeadALL(tailList, importList, headId,
            batchNum);
      }

      // ������־��
      this.addLog(loanFlowHead, securityInfo);
      // }catch(Exception e){
      // e.printStackTrace();
      // }
    } else {
      throw new BusinessException("�����ļ�Ϊ�գ�");
    }
  }

  /**
   * ���ۿ�
   * 
   * @param tailList
   * @param importList
   * @param headId
   * @throws Exception
   */
  public LoanFlowHead addLoanFlowHeadFull(List tailList, List importList,
      String headId, String batchNum) throws Exception {
    LoanFlowHead loanFlowHead = null;
    BigDecimal realCorpus = new BigDecimal(0.00);// ʵ������
    BigDecimal realInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// ʵ�����ڱ���
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// ʵ��������Ϣ
    BigDecimal realPunishInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal occurMoney = new BigDecimal(0.00);// ������
    int realCount = 0;// ʵ������
    // try{
    if (!tailList.isEmpty()) {
      Iterator it = tailList.iterator();
      Object obj = null;
      while (it.hasNext()) {
        obj = (Object) it.next();
        LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(obj
            .toString()));
        if (!importList.isEmpty()) {
          for (int i = 0; i < importList.size(); i++) {
            BankExportsDTO dto = (BankExportsDTO) importList.get(i);
            BankExportsDTO dto1 = (BankExportsDTO) importList.get(0);
            // ���õ����ļ��пۿ��־=1�ļ�¼ȥ���¸ñʵ���ҵ�񣬹�������Ϊ�������¡������˺ţ�
            if (dto.getLoanKouAcc().equals(loanFlowTail.getLoanKouAcc())
                && dto.getLoanKouYearmonth()
                    .equals(loanFlowTail.getYearMonth())
                && dto.getIdentifier().equals("1")) {
              // �жϵ����ļ���Ӧ���ֶ��Ƿ������ݿ���Ӧ������+Ӧ����Ϣ+Ӧ����Ϣһ��
              if (loanFlowTail.getShouldCorpus().add(
                  loanFlowTail.getShouldInterest()).add(
                  loanFlowTail.getShouldPunishInterest().add(
                      loanFlowTail.getOccurMoney())).doubleValue() != dto
                  .getShouldMoney().doubleValue()) {
                throw new BusinessException("�����ļ���Ӧ�۽�������ݿ���Ӧ����һ�£�");
              }
              loanFlowTail.setRealCorpus(loanFlowTail.getShouldCorpus());
              loanFlowTail.setRealInterest(loanFlowTail.getShouldInterest());
              loanFlowTail.setRealPunishInterest(loanFlowTail
                  .getShouldPunishInterest());
              loanFlowTail.setTempPunishInterest(loanFlowTail
                  .getTempPunishInterest().subtract(
                      loanFlowTail.getRealPunishInterest()));
              if (loanFlowTail.getLoanType().equals("1")) {
                // ����
                realCorpus = realCorpus.add(loanFlowTail.getRealCorpus());
                realInterest = realInterest.add(loanFlowTail.getRealInterest());
              } else if (loanFlowTail.getLoanType().equals("2")) {
                // ����
                realOverdueCorpus = realOverdueCorpus.add(loanFlowTail
                    .getRealCorpus());
                realOverdueInterest = realOverdueInterest.add(loanFlowTail
                    .getRealInterest());
              }
              realPunishInterest = realPunishInterest.add(loanFlowTail
                  .getRealPunishInterest());
            } else if (dto.getLoanKouAcc().equals(loanFlowTail.getLoanKouAcc())
                && dto.getLoanKouYearmonth()
                    .equals(loanFlowTail.getYearMonth())
                && dto.getIdentifier().equals("0")) {
              occurMoney = occurMoney.add(loanFlowTail.getOccurMoney());
              // ������κŲ�Ϊ��,��ô���߹���
              if (!batchNum.equals("")) {
                loanFlowTail.setOccurMoney(occurMoney);
              } else {
                loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              }
            }
            if (dto.getBatchNum() != null && !dto.getBatchNum().equals("")) {
              List batchNumList = loanFlowHeadDAO.queryBatchNum_GJP(dto1
                  .getBatchNum());
              if (batchNumList.size() == 0) {
                throw new BusinessException("�����ļ������κŲ����ڣ�");
              }
            }
          }
        }
      }
      // �õ�ʵ������
      realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(headId);
      // ����ͷ��
      loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      loanFlowHead.setRealCount(new BigDecimal(realCount));
      loanFlowHead.setRealCorpus(loanFlowHead.getRealCorpus().add(realCorpus));
      loanFlowHead.setRealInterest(loanFlowHead.getRealInterest().add(
          realInterest));
      loanFlowHead.setRealOverdueCorpus(loanFlowHead.getRealOverdueCorpus()
          .add(realOverdueCorpus));
      loanFlowHead.setRealOverdueInterest(loanFlowHead.getRealOverdueInterest()
          .add(realOverdueInterest));
      loanFlowHead.setRealPunishInterest(loanFlowHead.getRealPunishInterest()
          .add(realPunishInterest));
      loanFlowHead.setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
      loanFlowHead.setOccurMoney(loanFlowHead.getOccurMoney().subtract(
          occurMoney));
      loanFlowHead.setBatchNum(batchNum);
    }
    // }catch(Exception e){
    // e.printStackTrace();
    // }
    return loanFlowHead;
  }

  // ȫ��ۿ�
  public LoanFlowHead addLoanFlowHeadALL(List tailList, List importList,
      String headId, String batchNum) throws Exception {
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    BigDecimal realCorpus = new BigDecimal(0.00);// ʵ������
    BigDecimal realInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// ʵ�����ڱ���
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// ʵ��������Ϣ
    BigDecimal realPunishInterest = new BigDecimal(0.00);// ʵ����Ϣ
    BigDecimal realMoney = new BigDecimal(0.00);// ʵ�۽��
    String paramValue = "";// ����ֵ
    String loanKouAcc = "";// �����˺�
    int realCount = 0;// ʵ������
    BigDecimal temp_money = new BigDecimal(0.00);
    BigDecimal sumMoney = new BigDecimal(0.00);// ��ѯ���ݿ�����Ӧ�����
    BigDecimal temp_occurmonty = new BigDecimal(0.00);// �ж��Ƿ�Ҫ���´�����¼
    // ����ͷ��ID��ѯ��ˮͷ��
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    Integer loanBankId = new Integer(loanFlowHead.getLoanBankId().toString());
    paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, "A", "3");
    // try{
    if (!importList.isEmpty()) {
      for (int k = 1; k < importList.size(); k++) {
        BigDecimal occurMoney = new BigDecimal(0.00);// ������
        BankExportsDTO bankExportsDTO = (BankExportsDTO) importList.get(k);
        realMoney = bankExportsDTO.getRealMoney();// �����ļ��е�ʵ�۽��
        loanKouAcc = bankExportsDTO.getLoanKouAcc();// �ӵ����ļ��еõ������˺�
        List list = loanFlowTailDAO.queryExportFlowTailByHeadId_LJ(headId,
            loanKouAcc);
        // �ж�Ӧ���ܶ��Ƿ�С�ڵ��ڵ����ļ���ʵ�۽��
        if (!list.isEmpty()) {
          for (int i = 0; i < list.size(); i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
            sumMoney = sumMoney.add(dto.getShouldCorpus().subtract(
                dto.getRealCorpus()).add(
                dto.getShouldInterest().subtract(dto.getRealInterest()).add(
                    dto.getShouldPunishInterest().subtract(
                        dto.getRealPunishInterest()))));
            occurMoney = occurMoney.add(dto.getOccurMoney());
          }
        }
        if (realMoney.add(occurMoney).doubleValue() > sumMoney.doubleValue()) {
          throw new BusinessException("ʵ�۽��ܴ���Ӧ����");
        }
        realMoney = realMoney.subtract(occurMoney);
        for (int j = 0; j < paramValue.length(); j++) {
          if (BusiConst.PLALLMESS_CORPUS.equals(String.valueOf(paramValue
              .charAt(j)))) {// ��������
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("1")) {
                    if (dto.getShouldCorpus().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealCorpus(dto.getShouldCorpus());
                      // realCorpus = realCorpus.add(dto.getShouldCorpus());
                      realMoney = realMoney.subtract(dto.getShouldCorpus());
                    } else {
                      loanFlowTail.setRealCorpus(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realCorpus =
                    // realCorpus.add(loanFlowTail.getRealCorpus());
                  }
                }

              }
            }
          } else if (BusiConst.PLALLMESS_INTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// ������Ϣ
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("1")) {
                    if (dto.getShouldInterest().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealInterest(dto.getShouldInterest());
                      realMoney = realMoney.subtract(dto.getShouldInterest());
                    } else {
                      loanFlowTail.setRealInterest(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realInterest =
                    // realInterest.add(loanFlowTail.getRealInterest());
                  }
                }
              }
            }
          } else if (BusiConst.PLALLMESS_OVERDUECORPUS.equals(String
              .valueOf(paramValue.charAt(j)))) {// ���ڱ���
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("2")) {
                    if (dto.getShouldCorpus().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealCorpus(dto.getShouldCorpus());
                      realMoney = realMoney.subtract(dto.getShouldCorpus());
                    } else {
                      loanFlowTail.setRealCorpus(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realOverdueCorpus =
                    // realOverdueCorpus.add(loanFlowTail.getRealCorpus());
                  }
                }
              }
            }
          } else if (BusiConst.PLALLMESS_OVERDUEINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// ������Ϣ
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("2")) {
                    if (dto.getShouldInterest().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealInterest(dto.getShouldInterest());
                      realMoney = realMoney.subtract(dto.getShouldInterest());
                    } else {
                      loanFlowTail.setRealInterest(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realOverdueInterest =
                    // realOverdueInterest.add(loanFlowTail.getRealInterest());
                  }
                }
              }
            }
          } else if (BusiConst.PLALLMESS_PUNISHINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// ��Ϣ
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getShouldPunishInterest().doubleValue() <= realMoney
                      .doubleValue()) {
                    loanFlowTail.setRealPunishInterest(dto
                        .getShouldPunishInterest());
                    realMoney = realMoney.subtract(dto
                        .getShouldPunishInterest());
                  } else {
                    loanFlowTail.setRealPunishInterest(realMoney);
                    realMoney = new BigDecimal(0.00);
                  }
                }
                // realPunishInterest =
                // realPunishInterest.add(loanFlowTail.getRealPunishInterest());
                loanFlowTail.setTempPunishInterest(loanFlowTail
                    .getTempPunishInterest().subtract(
                        loanFlowTail.getRealPunishInterest()));
              }
            }
          }
        }
        List occur_list = loanFlowTailDAO.queryExportFlowTailByHeadId_LJ(
            headId, loanKouAcc);
        BigDecimal temp_occurMoney = occurMoney.multiply(new BigDecimal(-1.00));
        if (!occur_list.isEmpty()) {
          for (int i = 0; i < occur_list.size(); i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
            LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(
                dto.getId()));
            temp_money = loanFlowTail.getRealCorpus().add(
                loanFlowTail.getRealInterest()).add(
                loanFlowTail.getRealPunishInterest());
            if (temp_money.doubleValue() <= temp_occurMoney.doubleValue()) {
              loanFlowTail.setOccurMoney(temp_money.multiply(new BigDecimal(
                  -1.00)));
              temp_occurMoney = temp_occurMoney.subtract(temp_money);
            } else {
              loanFlowTail.setOccurMoney(temp_occurMoney
                  .multiply(new BigDecimal(-1.00)));
              temp_occurMoney = new BigDecimal(0.00);
            }
          }
        }
      }
    }
    // }catch(Exception e){
    // e.printStackTrace();
    // }
    List tail_list = loanFlowTailDAO.queryRealLoanFlowTailByHeadId_LJ(headId);
    if (!tail_list.isEmpty()) {
      for (int i = 0; i < tail_list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) tail_list.get(i);
        if (dto.getLoanKouType().equals("1")) {
          realCorpus = realCorpus.add(dto.getShouldCorpus());
          realInterest = realInterest.add(dto.getShouldInterest());
        } else if (dto.getLoanKouType().equals("2")) {
          realOverdueCorpus = realOverdueCorpus.add(dto.getShouldCorpus());
          realOverdueInterest = realOverdueInterest
              .add(dto.getShouldInterest());
        }
        realPunishInterest = realPunishInterest.add(dto.getPunishInterest());
      }
    }
    // �õ�ʵ������
    realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(headId);
    // ��ͨ��β�����ͷ��
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    loanFlowHead.setRealCount(new BigDecimal(realCount));
    loanFlowHead.setRealCorpus(realCorpus);
    loanFlowHead.setRealInterest(realInterest);
    loanFlowHead.setRealOverdueCorpus(realOverdueCorpus);
    loanFlowHead.setRealOverdueInterest(realOverdueInterest);
    loanFlowHead.setRealPunishInterest(realPunishInterest);
    loanFlowHead.setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
    loanFlowHead.setBatchNum(batchNum);
    return loanFlowHead;
  }

  /**
   * ������־
   * 
   * @param loanFlowHead
   * @param securityInfo
   */
  public void addLog(LoanFlowHead loanFlowHead, SecurityInfo securityInfo) {
    // ����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_IMP + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
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
   * ���ո�����ˮ��״̬
   * 
   * @param tailId
   * @param securityInfo
   * @throws Exception
   */
  public void loanCallback(String tailId, SecurityInfo securityInfo)
      throws Exception {
    // �жϸñ�ҵ���ڴ�����ˮ��ͷ��PL203�е�ҵ��״̬BIZ_ST�Ƿ�=2�����루��PL202����FLOW_HEAD_ID������
    LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(tailId));
    String headId = loanFlowTail.getFlowHeadId().toString();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("�ñ�ҵ���ǵ���״̬�����ܽ��л��ղ�����");
    }
    // ���¸ñ�ҵ��Ĵ�����ˮ��ͷ��PL202�е�ҵ��״̬BIZ_ST=4.ȷ�ϣ�����������PL202����FLOW_HEAD_ID��
    loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
    // ������־��
    // ����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
  }

  /**
   * ȫ��ɾ��
   * 
   * @param tailId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteAllCallbackInfo(String tailId, SecurityInfo securityInfo)
      throws Exception {
    // �жϸñ�ҵ���ڴ�����ˮ��ͷ��PL203�е�ҵ��״̬BIZ_ST�Ƿ�=2�����루��PL202����FLOW_HEAD_ID������
    LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(tailId));
    String headId = loanFlowTail.getFlowHeadId().toString();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String office = "";// ���´�����
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("�ñ�ҵ���ǵ���״̬�����ܽ���ȫ��ɾ��������");
    }
    // ɾ���ñ�ҵ���ڴ�����ˮ��ͷ��PL202��������ˮ��ͷβPL203�ļ�¼������������PL202����FLOW_HEAD_ID��PL203����FLOW_HEAD_ID�иô���ŵ�ҵ���¼��
    // ɾ��β��
    loanFlowTailDAO.deleteLoanFlowTailAll(headId);
    // ɾ��ҵ����־����������Ϊ������ˮ��ͷ��PL202��FLOW_HEAD_ID��ҵ����־PL020����BIZID and
    // ACTION=��2.���룩
    // ɾ��ҵ����־
    plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead.getBizSt());
    plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, String
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
    plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), officeId,
        loanFlowHead.getBizDate().substring(0, 6));
    // ɾ��ͷ��
    loanFlowHeadDAO.delete(loanFlowHead);

    // ���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
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
}