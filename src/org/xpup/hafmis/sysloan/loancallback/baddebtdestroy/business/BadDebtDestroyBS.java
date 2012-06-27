package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.business;

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
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
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
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTbDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;

public class BadDebtDestroyBS implements IBadDebtDestroyBS {

  private BorrowerAccDAO borrowerAccDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;
  
  private PlOperateLogDAO plOperateLogDAO = null;
  private CollParaDAO collParaDAO = null;
  private PlBizActiveLogDAO plBizActiveLogDAO = null;
  
  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;
  
  private PlDocNumCancelDAO plDocNumCancelDAO = null;
  
  private CollBankDAO collBankDAO = null;
  
  private LoanBankDAO loanBankDAO = null;
  
  private AssistantOrgDAO assistantOrgDAO = null;
  
  private SecurityDAO securityDAO = null;

  
  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
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

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public String findBorrowerAcc(String loanKouAcc, String contractSt,
      SecurityInfo securityInfo) {
    String contractId = "";
    contractId = borrowerAccDAO.queryBorrowerAccByLoanKouAcc_LJ(loanKouAcc,
        contractSt, securityInfo);
    return contractId;
  }
  /**
   * �жϻ�����������ڱ������һ�췵�ر������һ��
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth,String loanRepayDay){
    String day = loanRepayDay;
    String year = yearMonth.substring(0,4);
    String month = yearMonth.substring(4,6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer.parseInt(month));
    if(Integer.parseInt(loanRepayDay)>days){
      day = String.valueOf(days);
    }     
    if(day.length()<2&&Integer.parseInt(day)<10){
      day = "0"+day;
    }
    return day;
  }
  /**
   * ��ѯ���������б�
   * ������µ������12��
   * @param securityInfo
   * @return
   */
  public List getYearMonthList(String loanRepayDay,String contractId,SecurityInfo securityInfo)throws Exception{
    List list = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String day = bizDate.substring(6,8);
    List temp_list = restoreLoanDAO.countRestoreLoanListByContractId_sy(contractId);  
    String loanDay="";
    try{
      if(!temp_list.isEmpty()){
        for(int i=0;i<temp_list.size();i++){
          PrintplanListDTO dto = (PrintplanListDTO)temp_list.get(i);
          ShouldBackListDTO shouldBackListDTO = new ShouldBackListDTO();
//        if(Integer.parseInt(day)<Integer.parseInt(loanRepayDay)){
//        //�����С�ڻ����գ�ȡδ������С�µ��¸���--����µ���һ����
//        if(dto.getLoanKouYearmonth().substring(4,6).equals("12")){
//          shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth().substring(0,4))+1)+"01");
//        }else{
//          shouldBackListDTO.setLoanKouYearmonth(String.valueOf(Integer.parseInt(dto.getLoanKouYearmonth())+1));
//        }
//      }else{
        // ����մ��ڵ��ڻ����գ�ȡδ������С��--�����
          loanDay = this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
          shouldBackListDTO.setLoanKouYearmonth(dto.getLoanKouYearmonth()+loanDay);
//      }
          list.add(shouldBackListDTO);
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ������Ϊ��
   * ����ҳ�棬���ݴ����˺Ų�ѯӦ������Ϣ
   */
  public BadDebtDestroyTaAFDTO findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    String callbackMonth = (String)pagination.getQueryCriterions().get("callbackMonth");
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    List shouldBackList = new ArrayList();// Ӧ����Ϣ
    List bizStList = null;// ���ڲ�ѯ�Ƿ����δ���˵�״̬
    List borrowerList = new ArrayList();// �˻���Ϣ
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// ��ͬ״̬ 11.������
    String contractId = "";// ��ͬ���
    String loanRepayDay = "";// ������ ��ȡӦ������Ϣʱ�õ�
    int count = 0;
    // �����˺�
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    if(callbackMonth!=null&&!callbackMonth.equals("")){
      String temp_yearMonth = callbackMonth.substring(0,6);
      if(!temp_yearMonth.equals(yearMonth)){
        yearMonth=callbackMonth.substring(0,6);
        //day=callbackMonth.substring(6,8);
      }
    }
    
    if (loanKouAcc != null && !loanKouAcc.equals("")) {
      // �жϴ����˺���PL111���Ƿ���ڲ���״̬Ϊ�����С�
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("�˴����˺Ų����ڻ򲻿��԰������ҵ��");
      }
      pagination.getQueryCriterions().put("contractId", contractId);
      // �ô����˺��ڴ�����ˮ��ͷ��PL202�����Ƿ����BIZ_ST!=6(δ����)������������ˮ��β��PL203��
      bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,null);
      if (!bizStList.isEmpty()) {
        throw new BusinessException("����δ���˵�ҵ��");
      } 
      // ��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      //֤������
      borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
      //���ʽ
      borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      Integer loanBankId=borrowerInfoDTO.getLoanBankId();
      int iClearYear=Integer.parseInt(bizDate.substring(0,4))-1;
      String clearYear=this.getClearYear(String.valueOf(loanBankId));
      //������-1���������PL002�е����������������
      if(iClearYear>Integer.parseInt(clearYear)){
        throw new BusinessException(iClearYear+"����δ��ᣬ��������д���ҵ��");
      }
      String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      // ��PL201�в�Ӧ����Ϣ
      if(callbackMonth==null){
        if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
          // �����С�ڻ�����
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
              contractId, yearMonth);
        } 
        else {
          // ����մ��ڵ��ڻ�����
          shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
              contractId, yearMonth);
        }
      }else {
        // ����մ��ڵ��ڻ�����
        shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
            contractId, yearMonth);
      }
      if(!shouldBackList.isEmpty()){
        ShouldBackListDTO dto = (ShouldBackListDTO) shouldBackList.get(shouldBackList.size()-1);
        yearMonth = dto.getLoanKouYearmonth();
      }
      loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
      BadDebtDestroyTaAFDTO af1 = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate);
      List yearMonthList = this.getYearMonthList(loanRepayDay, contractId, securityInfo);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(af1.getShouldBackList());
      af.setMonthYearList(yearMonthList);
      af.setMonthYear(yearMonth+loanRepayDay1);
      af.setSumCorpus(af1.getSumCorpus());
      af.setSumInterest(af1.getSumInterest());
      af.setSumMoney(af1.getSumMoney());
      af.setRealMoney(af1.getRealMoney());
    }
    count = shouldBackList.size();
    if(count<=0){
      af.setMonthYear("");
    }
    pagination.setNrOfElements(count);
    return af;
  }
  
  
  /**
   * Ӧ����Ϣ�б�
   * @param shouldBackList
   * @param borrowerInfoDTO
   * @param bizDate
   * @return
   * @throws Exception
   */
  public BadDebtDestroyTaAFDTO findCallbackList(List shouldBackList,BorrowerInfoDTO borrowerInfoDTO,String bizDate)throws Exception{
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    List temp_list = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    Integer loanBankId = borrowerInfoDTO.getLoanBankId();// �ſ�����
    String isRate = "";// �Ƿ��Ϣ
    String accountDate = "";// ��������
    String loanRepayDay = "";// ������ ��ȡӦ������Ϣʱ�õ�
    String paramType = "A";// ��������
    String interestMode = "";// ���㷣Ϣ��ʽ
    String paramExplain = "";//����˵��
    String allowdays = "";// ��������
    BigDecimal temp_interest = new BigDecimal(0.00);// ��ʱ��Ϣ
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    if(borrowerInfoDTO.getLoanMode().equals("һ����")||borrowerInfoDTO.getLoanMode().equals("������")){
      //�����ڻ���
      //��PL003�в�ѯ�����������Ƿ��Ϣ
      isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "12");
      // ��PL003��ȡ���㷣Ϣ��ʽ��������Ϣ�����ʡ�����ͬ�����ʡ�����ÿ��XXԪ���㣩
      interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "11");
      // ��PL003��ȡ��Ӧ�Ĳ���˵��
      paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "11");
      // PL003�в�ѯ��������
      allowdays = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
          "12");
    }else{
      //��PL003�в�ѯ�����������Ƿ��Ϣ
      isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "5");
      // ��PL003��ȡ���㷣Ϣ��ʽ��������Ϣ�����ʡ�����ͬ�����ʡ�����ÿ��XXԪ���㣩
      interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
          "4");
      // ��PL003��ȡ��Ӧ�Ĳ���˵��
      paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId,
          paramType, "4");
      // PL003�в�ѯ��������
      allowdays = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
          "5");
    }
    
    if (!shouldBackList.isEmpty()) {
      for (int i = 0; i < shouldBackList.size(); i++) {
        ShouldBackListDTO dto1 = (ShouldBackListDTO) shouldBackList.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        String loanRepayDay1 = this.getEndDay(dto1.getLoanKouYearmonth(), loanRepayDay);
        dto2.setLoanKouYearmonth(dto1.getLoanKouYearmonth());// ��ʾ��������
        String temp_date = dto1.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto1.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
        // ��������
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        if (days > 0) {
          dto2.setLoanKouType("����");// ��ʾ��������
        } else {
          dto2.setLoanKouType("����");
        }
        dto2.setShouldCorpus(dto1.getShouldCorpus().subtract(
            dto1.getRealCorpus()));// ��ʾӦ������
        dto2.setShouldInterest(dto1.getShouldInterest().subtract(
            dto1.getRealInterest()));// ��ʾӦ����Ϣ
        dto2.setDays(days + "");// ��ʾ��������
        if(days<0){
          dto2.setDays("0");
        }
        // ��Ϣ��ס�ˡ�
        // ��������ÿ�²����ķ�Ϣ
        // �жϻ����(Ӧ������-����+Ӧ����Ϣ-��Ϣ)�Ƿ�=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // ������0�ж��Ƿ��ڿ��������ڼ�Ϣ
          // �������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���ֵPARAM_VALUE�Ƿ�=0(���������ڼ�Ϣ)
          if (isRate.equals(BusiConst.YES + "")) {// ��Ϣ
            // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����
            accountDate = dto1.getBizDate();
       //     String temp_day = accountDate.substring(6, 8);// �������ڵ���
      //      if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay)) {// С�ڵ��ڻ�����
            if (accountDate==null || accountDate.equals("")) {//�ж��Ƿ��м������ڣ�û�У����������£��У�����������
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
                      .getShouldInterest(), dto1.getRealInterest(),
                  paramExplain ,dto1.getLoanRate());
          }else if(Integer.parseInt(accountDate) <= Integer.parseInt(dto1.getLoanKouYearmonth()+loanRepayDay1)){// С�ڵ��ڻ�����
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                  .getShouldCorpus(), dto1.getRealCorpus(), dto1
                  .getShouldInterest(), dto1.getRealInterest(),
              paramExplain ,dto1.getLoanRate());
          } else {// ���ڻ�����
              temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                  dto1.getBizDate(), dto1.getShouldCorpus(),
                  dto1.getRealCorpus(), dto1.getShouldInterest(), dto1
                      .getRealInterest(), paramExplain,dto1.getLoanRate());
            temp_interest = temp_interest.add(dto1.getPunishInterest())
                .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// �ӻ������δ����Ϣ
          }
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
            } else if(days<0){
              dto2.setPunishInterest(dto1.getPunishInterest());
            } else {// �Ѿ�����
              // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����+��������
             
              // ����������+�������������ɵ�������
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay1, Integer.parseInt(allowdays));
              //temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
//              if (Integer.parseInt(temp_day) <= Integer.parseInt(temp_loanRepayDay)) {// С�ڵ��ڻ�����+��������
              if (dto1.getBizDate()==null || dto1.getBizDate().equals("")) {//�ж��Ƿ��м�������
                  temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                      dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                          .getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain, allowdays,dto1.getLoanRate());
              } else if(Integer.parseInt(dto1.getBizDate())<=Integer.parseInt(temp_loanRepayDay)){//С�ڵ��ڻ�����+��������
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay1, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, allowdays,dto1.getLoanRate());
              }else {// ���ڻ�����+��������
                  temp_interest = PunishInterestBS.getTempInterestByClearDate(interestMode,bizDate,
                      dto1.getBizDate(),
                      dto1.getShouldCorpus(), dto1.getRealCorpus(), dto1
                          .getShouldInterest(), dto1.getRealInterest(),
                      paramExplain,dto1.getLoanRate());               
                // �ӻ������δ����Ϣ
                temp_interest = temp_interest.add(dto1.getPunishInterest())
                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
              
              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setCiMoney(dto2.getShouldCorpus().add(dto2.getShouldInterest()).add(dto2.getPunishInterest()));// ��ʾӦ����Ϣ�ϼ�
        dto2.setLoanRate(dto1.getLoanRate());// ��ʾÿ������
        dto2.setShow_loanRate(dto1.getShow_loanRate());
        sumCorpus = sumCorpus.add(dto2.getShouldCorpus());
        sumInterest = sumInterest.add(dto2.getShouldInterest().add(
            dto2.getPunishInterest()));
        temp_list.add(dto2);
      }
    }
    //�ܻ����
    af.setSumCorpus(sumCorpus);
    //�ܻ�����Ϣ
    af.setSumInterest(sumInterest);
    //�ܻ�����
    af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
    //ʵ���ܶ�
    af.setRealMoney(af.getSumMoney());
    //Ӧ����Ϣ�б�
    af.setShouldBackList(temp_list);
    return af;
  }

/**
 * ������Ϊ�����������ˮ��
 * @param importList
 * @param securityInfo
 * @throws Exception
 */
  public Integer adCallbackBatch(List importList,SecurityInfo securityInfo)throws Exception{
    String loanKouAcc = "";//�����˺�
    String contractId = "";//��ͬ���
    String loanBankId = "";//�ſ�����
    String loanBankIdim="";//�����ļ��ſ�����
    String bizDate = securityInfo.getUserInfo().getPlbizDate();//�������
    String bizDateim = "";//�����ļ��������
    String bizTypeim = "";//�����ļ�����
    BigDecimal shouldCorpus = new BigDecimal(0.00);//Ӧ����������
    BigDecimal shouldInterest = new BigDecimal(0.00);//Ӧ��������Ϣ
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);//Ӧ�����ڱ���
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);//Ӧ��������Ϣ
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);//Ӧ����Ϣ
    BigDecimal realCorpus = new BigDecimal(0.00);//ʵ������
    BigDecimal realInterest = new BigDecimal(0.00);//ʵ����Ϣ
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);//ʵ�����ڱ���
    BigDecimal realOverdueInterest = new BigDecimal(0.00);//ʵ��������Ϣ
    BigDecimal realPunishInterest = new BigDecimal(0.00);//ʵ����Ϣ
    BigDecimal temp_realCorpus = new BigDecimal(0.00);//ʵ������
    BigDecimal temp_realInterest = new BigDecimal(0.00);//ʵ����Ϣ
    BigDecimal temp_realOverdueCorpus = new BigDecimal(0.00);//ʵ�����ڱ���
    BigDecimal temp_realOverdueInterest = new BigDecimal(0.00);//ʵ��������Ϣ
    BigDecimal temp_realPunishInterest = new BigDecimal(0.00);//ʵ����Ϣ
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// ��ͬ״̬ 11.������
    String loanRepayDay = "";// ������ 
    String temp_bizDate = bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8);
    List bizStList = null;
    List borrowerList = null;
    BorrowerInfoDTO borrowerInfoDTO = null;
    List bankList = null;
    if(importList.isEmpty()){
      throw new BusinessException("�����ļ�Ϊ�գ�");
    }else if(importList.size()>1){
      LoancallbackTaImportDTO dto1 = (LoancallbackTaImportDTO)importList.get(0);
      loanBankIdim = dto1.getLoanBankId();
      bizDateim = dto1.getBizDate();
      bizTypeim = dto1.getBizType();
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(), loanBankIdim);
      //�ж����޲��������е�Ȩ��
      if(bankList.isEmpty()){
        throw new BusinessException("���߱������е�Ȩ�ޣ�");
      }
      int iClearYear=Integer.parseInt(bizDate.substring(0,4))-1;
      String clearYear=this.getClearYear(String.valueOf(loanBankIdim));
      //������-1���������PL002�е����������������
      if(iClearYear>Integer.parseInt(clearYear)){
        throw new BusinessException(iClearYear+"����δ��ᣬ��������д���ҵ��");
      }
      if(!temp_bizDate.equals(bizDateim)){
        throw new BusinessException("������ڲ�һ�£�");
      }
      if(bizTypeim==null){
        throw new BusinessException("ҵ�����Ͳ���Ϊ�գ�");
      }else if(!bizTypeim.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE)) 
          && !bizTypeim.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER))){
        throw new BusinessException("��ҵ�����Ͳ����ڴ˵��룡");
      }
      LoancallbackTaImportDTO dto2 = (LoancallbackTaImportDTO)importList.get(1);
      loanKouAcc = dto2.getLoanKouAcc();
      
      //�жϴ����˺���PL111���Ƿ���ڲ���״̬Ϊ�����С�
      contractId = findBorrowerAcc(loanKouAcc, contractSt, securityInfo);
      if (contractId == null) {
        throw new BusinessException("�˴����˺Ų����ڣ�");
      }
      // �ô����˺��ڴ�����ˮ��ͷ��PL202�����Ƿ����BIZ_ST!=6(δ����)������������ˮ��β��PL203��
      bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,bizTypeim);
      if (!bizStList.isEmpty()) {
        throw new BusinessException("����δ���˵�ҵ��");
      } 
      // ��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO.queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
      loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
      loanBankId = borrowerInfoDTO.getLoanBankId().toString();
      if(!loanBankId.equals(loanBankIdim)){
        throw new BusinessException("�ſ����в�һ�£�");
      }
      for(int i=1;i<importList.size();i++){
        LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO)importList.get(i);
        
        if(Double.parseDouble(dto.getRealCorpus())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getRealOverdueCorpus())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getRealInterest())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getRealOverdueInterest())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getRealPunishInterest())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getNobackCorpus())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getNobackOverdueCorpus())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getNobackInterest())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getNobackOverdueInterest())<0){
          throw new BusinessException("����С��0��");
        }
        if(Double.parseDouble(dto.getNobackPunishInterest())<0){
          throw new BusinessException("����С��0��");
        }
        shouldCorpus = shouldCorpus.add(new BigDecimal(dto.getRealCorpus()).add(new BigDecimal(dto.getNobackCorpus())));
        shouldInterest = shouldInterest.add(new BigDecimal(dto.getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
        shouldOverdueCorpus = shouldOverdueCorpus.
        add(new BigDecimal(dto.getRealOverdueCorpus()).add(new BigDecimal(dto.getNobackOverdueCorpus())));
        shouldOverdueInterest = shouldOverdueInterest.
        add(new BigDecimal(dto.getRealOverdueInterest()).add(new BigDecimal(dto.getNobackOverdueInterest())));
        shouldPunishInterest = shouldPunishInterest.
        add(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
        realCorpus = realCorpus.add(new BigDecimal(dto.getRealCorpus()));
        realInterest = realInterest.add(new BigDecimal(dto.getRealInterest()));
        realOverdueCorpus = realOverdueCorpus.add(new BigDecimal(dto.getRealOverdueCorpus()));
        realOverdueInterest = realOverdueInterest.add(new BigDecimal(dto.getRealOverdueInterest()));
        realPunishInterest = realPunishInterest.add(new BigDecimal(dto.getRealPunishInterest()));
      }
    }
    //������ˮͷ��
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead.setBizDate(bizDate);
    String officeId="";
    String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
    if(loanDocNumDocument.equals("1")){
      officeId=borrowerInfoDTO.getOfficeCode();
    }else{
      officeId=borrowerInfoDTO.getLoanBankId().toString();
    }
    loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId, bizDate.substring(0,6)));
    loanFlowHead.setBizType(bizTypeim);
    loanFlowHead.setShouldCount(new BigDecimal(1));
    loanFlowHead.setShouldCorpus(shouldCorpus);
    loanFlowHead.setShouldInterest(shouldInterest);
    loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
    loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
    loanFlowHead.setShouldPunishInterest(shouldPunishInterest);
    loanFlowHead.setRealCount(new BigDecimal(1));
    loanFlowHead.setRealCorpus(realCorpus);
    loanFlowHead.setRealInterest(realInterest);
    loanFlowHead.setRealOverdueCorpus(realOverdueCorpus);
    loanFlowHead.setRealOverdueInterest(realOverdueInterest);
    loanFlowHead.setRealPunishInterest(realPunishInterest);
    loanFlowHead.setOccurMoney(realCorpus.add(realInterest).add(realOverdueCorpus).add(realOverdueInterest).add(realPunishInterest));
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));
    loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_IMP+"");
    loanFlowHead.setMakePerson(securityInfo.getUserName());  
    loanFlowHead.setIsFinance(new Integer(1));
    loanFlowHeadDAO.insert(loanFlowHead);
    //����Ʊ�ݺ�
    LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead.getFlowHeadId());
    loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());

    //������ˮβ��
    if(!importList.isEmpty()){
      for(int i=1;i<importList.size();i++){
        LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList.get(i);
        temp_realCorpus = new BigDecimal(dto.getRealCorpus());
        temp_realInterest = new BigDecimal(dto.getRealInterest());
        temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
        temp_realOverdueInterest = new BigDecimal(dto.getRealOverdueInterest());
        temp_realPunishInterest = new BigDecimal(dto.getRealPunishInterest());
        LoanFlowTail loanFlowTail = new LoanFlowTail();
//      ����������ݺ��������ݶ�����0������ˮ���в����������ݣ�һ����������Ϊ1������һ��Ϊ2����
        if(temp_realCorpus.add(temp_realInterest).doubleValue()>0 && 
            temp_realOverdueCorpus.add(temp_realOverdueInterest).add(temp_realPunishInterest).doubleValue()>0){
          loanFlowTail.setContractId(contractId);
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(loanKouAcc);
          loanFlowTail.setYearMonth(dto.getYearMonth());
          loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus()).add(new BigDecimal(dto.getNobackCorpus())));
          loanFlowTail.setShouldInterest(new BigDecimal(dto.getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
       //   loanFlowTail.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
          loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
          //loanFlowTail.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));        
          loanFlowTail.setLoanType("1");
          loanFlowTailDAO.insert(loanFlowTail);
          
          LoanFlowTail loanFlowTail1 = new LoanFlowTail();
          loanFlowTail1.setContractId(contractId);
          loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail1.setLoanKouAcc(loanKouAcc);
          loanFlowTail1.setYearMonth(dto.getYearMonth());
          loanFlowTail1.setShouldCorpus(new BigDecimal(dto.getRealOverdueCorpus()).add(new BigDecimal(dto.getNobackOverdueCorpus())));
          loanFlowTail1.setShouldInterest(new BigDecimal(dto.getRealOverdueInterest()).add(new BigDecimal(dto.getNobackOverdueInterest())));
          loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail1.setRealCorpus(new BigDecimal(dto.getRealOverdueCorpus()));
          loanFlowTail1.setRealInterest(new BigDecimal(dto.getRealOverdueInterest()));
          loanFlowTail1.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail1.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail1.setOccurMoney(loanFlowTail1.getRealCorpus().add(loanFlowTail1.getRealInterest()).add(loanFlowTail1.getRealPunishInterest()));        
          loanFlowTail1.setLoanType("2");
          loanFlowTailDAO.insert(loanFlowTail1);
          //�����������ݴ���0��������С��0������һ����������Ϊ1����
        }else if(temp_realCorpus.add(temp_realInterest).doubleValue()>0){
          loanFlowTail.setContractId(contractId);
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(loanKouAcc);
          loanFlowTail.setYearMonth(dto.getYearMonth());
          loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus()).add(new BigDecimal(dto.getNobackCorpus())));
          loanFlowTail.setShouldInterest(new BigDecimal(dto.getRealInterest()).add(new BigDecimal(dto.getNobackInterest())));
          //loanFlowTail.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
          loanFlowTail.setRealInterest(new BigDecimal(dto.getRealInterest()));
          //loanFlowTail.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));        
          loanFlowTail.setLoanType("1");
          loanFlowTailDAO.insert(loanFlowTail);
//        �����������ݴ���0�������ݵ���0������һ����������Ϊ2����
        }else if(temp_realOverdueCorpus.add(temp_realOverdueInterest).add(temp_realPunishInterest).doubleValue()>0){
          loanFlowTail.setContractId(contractId);
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead1.getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(loanKouAcc);
          loanFlowTail.setYearMonth(dto.getYearMonth());
          loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealOverdueCorpus()).add(new BigDecimal(dto.getNobackOverdueCorpus())));
          loanFlowTail.setShouldInterest(new BigDecimal(dto.getRealOverdueInterest()).add(new BigDecimal(dto.getNobackOverdueInterest())));
          loanFlowTail.setShouldPunishInterest(new BigDecimal(dto.getRealPunishInterest()).add(new BigDecimal(dto.getNobackPunishInterest())));
          loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealOverdueCorpus()));
          loanFlowTail.setRealInterest(new BigDecimal(dto.getRealOverdueInterest()));
          loanFlowTail.setRealPunishInterest(new BigDecimal(dto.getRealPunishInterest()));
          loanFlowTail.setOtherInterest(new BigDecimal(0.00)); 
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));        
          loanFlowTail.setLoanType("2");
          loanFlowTailDAO.insert(loanFlowTail);
        }
        // ��������
//        String loanRepayDay1 = this.getEndDay(dto.getYearMonth(), loanRepayDay);
//        int days = this.getDays(bizDate, dto.getYearMonth(), loanRepayDay1);
//        if(days<=0){
//          loanFlowTail.setLoanType("1");
//        }else{
//          loanFlowTail.setLoanType("2");
//        }
      }
    }
    //����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiLogConst.BIZBLOG_ACTION_IMP+"");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    //���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP+"");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId();
  }

  /**
   * ������˺������е�����ѯ
   * @param pagination
   * @return
   */
  public BadDebtDestroyTaAFDTO findCallbacklistByLoanBank(Pagination pagination) throws Exception{
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    BorrowerInfoDTO borrowerInfoDTO = null;
    List borrowerList = null;
    String headId = (String)pagination.getQueryCriterions().get("headId");
    List list = new ArrayList();
    int count=0;
    if(headId != null){
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      String bizDate = loanFlowHead.getBizDate();
      String orgType = loanFlowHead.getBizType();
      String orgId = loanFlowHead.getHedaiOrg();
      String orgName = "";
      if(orgId!=null&&!orgId.equals("")){
        orgName = assistantOrgDAO.queryAssistantOrgNameByAssistantOrgId(orgId);
      }
      List tailList = loanFlowTailDAO.queryRealLoanFlowTailByHeadId_LJ(headId);
      String contractId = "";//��ͬ���
      String loanRepayDay = "";//������
      if(!tailList.isEmpty()){
        ShouldBackListDTO shouldBackListDTO = (ShouldBackListDTO)tailList.get(0);
        contractId = shouldBackListDTO.getContractId();
        borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
        if (!borrowerList.isEmpty()) {
          borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
        }
        //֤������
        borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        //���ʽ
        borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
        for(int i=0;i<tailList.size();i++){
          ShouldBackListDTO dto = (ShouldBackListDTO)tailList.get(i);
          ShouldBackListDTO dto2 = new ShouldBackListDTO();
          dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
          String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
          // ��������
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(), loanRepayDay1);
          if(days<0){
            days=0;
          }
          String type = dto.getLoanKouType();
          if(type.equals("1")){
            dto2.setLoanKouType("����");
          }else if(type.equals("2")){
            dto2.setLoanKouType("����");
          }
          dto2.setShouldCorpus(dto.getShouldCorpus());
          dto2.setShouldInterest(dto.getShouldInterest());
          dto2.setPunishInterest(dto.getPunishInterest());
          dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest()).add(dto.getPunishInterest()));
          dto2.setLoanRate(dto.getLoanRate());
          dto2.setDays(days+"");
          list.add(dto2);
        }
      }
      if(!list.isEmpty()){
        ShouldBackListDTO dto = (ShouldBackListDTO) list.get(list.size()-1);
        af.setMonthYear(dto.getLoanKouYearmonth());
        count = list.size();
      }
      af.setMonthYearList(list);
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setShouldBackList(list);
      af.setSumCorpus(loanFlowHead.getRealCorpus().add(loanFlowHead.getRealOverdueCorpus()));
      af.setSumInterest(loanFlowHead.getRealInterest().add(loanFlowHead.getRealOverdueInterest()).add(loanFlowHead.getRealPunishInterest()));
      af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
      af.setRealMoney(af.getSumMoney());
      af.setOrgName(orgName);
      if(orgType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE))){
        af.setOrgType("1");        
      }else if(orgType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER))){
        if(orgName!=null && !orgName.equals("")){
          af.setOrgType("2");
        }else{
          af.setOrgType("3");
        }
      }
    }
    
    pagination.setNrOfElements(count);
    return af;
  }

  /**
   * ���˺���ȷ��(������Ϊ��)
   * @param af
   * @param securityInfo
   * @throws Exception
   * @return
   */
  public String addCallbackInfo(BadDebtDestroyTaAFDTO af,SecurityInfo securityInfo) throws Exception{
    //������Ϊ��
    String contractId = af.getBorrowerInfoDTO().getContractId();//��ͬ���
    List bizStList = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String operator = securityInfo.getUserName();
    Integer headId=null;
    String orgType=af.getOrgType();//������λ����
    //�жϴ����˺��ڱ�PL202���Ƿ����:BIZ_ST��=6������PL203��
    //�ô����˺��ڴ�����ˮ��ͷ��PL202�����Ƿ����BIZ_ST!=6(δ����)������������ˮ��β��PL203��
    bizStList = loanFlowHeadDAO.queryBizStListByLoanKouAcc_LJ(contractId,null);
    if (bizStList.size()>0) {
      throw new BusinessException("����δ���˵�ҵ��");
    }
    headId=this.addLoanFlowHeadFull(bizDate, operator, af);
    //���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpBizId(new BigDecimal(headId.toString()));
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD+"");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    //����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN));
    plBizActiveLog.setBizid(new BigDecimal(headId.toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    if(orgType.equals("1")){
      //����
      plBizActiveLog.setType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE));
    }else{
      //������˾������
      plBizActiveLog.setType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER));
    }
    plBizActiveLogDAO.insert(plBizActiveLog);
    return headId.toString();
  }
  //���ۿ�
  public Integer addLoanFlowHeadFull(String bizDate,String operator,BadDebtDestroyTaAFDTO af)throws Exception{
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldInterest = new BigDecimal(0.00);
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);
    BigDecimal punishInterest = new BigDecimal(0.00);
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    try{
      String orgType=af.getOrgType();//������λ����
      String bizYearmonth = bizDate.substring(0,6);
      List list = af.getShouldBackList();
      //������ˮͷ��
      String officeId="";
      String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      if(loanDocNumDocument.equals("1")){
        officeId=af.getBorrowerInfoDTO().getOfficeCode();
      }else{
        officeId=af.getBorrowerInfoDTO().getLoanBankId().toString();
      }
      loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(officeId, bizYearmonth));
      loanFlowHead.setBizDate(bizDate);
      //ҵ������BIZ_TYPE��A.���������λѡ������,BIZ_TYPE=6.���˺��������ģ�B.���������λѡ�񵣱���˾��������BIZ_TYPE=7.���˺�������������
      if(orgType.equals("1")){
        //����
        loanFlowHead.setBizType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE));
      }else{
        //������˾������
        loanFlowHead.setBizType(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER));
      }
      //�˴���λHEDAI_ORG��������λ�����Ϊ������˾����ʱ�����뵣����˾����Э����λ��Ϣ��PL007��Э����λ���ASSISTANT_ORG_ID����������º˵�λΪ��
      if(orgType.equals("2")){
        loanFlowHead.setHedaiOrg(af.getLoanassistantorgId());
      }
      loanFlowHead.setShouldCount(new BigDecimal(1));
      loanFlowHead.setRealCount(new BigDecimal(1));
      loanFlowHead.setOtherInterest(new BigDecimal(0.00));
      loanFlowHead.setLoanBankId(new BigDecimal(af.getBorrowerInfoDTO().getLoanBankId().toString()));
      loanFlowHead.setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN));
      loanFlowHead.setMakePerson(operator);
      loanFlowHead.setIsFinance(new Integer(1));
      loanFlowHeadDAO.insert(loanFlowHead);   
      //������ˮβ��
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
          ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
          LoanFlowTail loanFlowTail = new LoanFlowTail();
          loanFlowTail.setContractId(af.getBorrowerInfoDTO().getContractId());
          loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
          loanFlowTail.setLoanKouAcc(af.getBorrowerInfoDTO().getLoanKouAcc());
          loanFlowTail.setYearMonth(dto.getLoanKouYearmonth());
          loanFlowTail.setShouldCorpus(dto.getShouldCorpus());
          loanFlowTail.setShouldInterest(dto.getShouldInterest());
          loanFlowTail.setShouldPunishInterest(dto.getPunishInterest());
          loanFlowTail.setRealCorpus(dto.getShouldCorpus());
          loanFlowTail.setRealInterest(dto.getShouldInterest());
          loanFlowTail.setRealPunishInterest(dto.getPunishInterest());
          loanFlowTail.setTempPunishInterest(dto.getTempInterest());
          loanFlowTail.setOtherInterest(new BigDecimal(0.00));
          loanFlowTail.setLoanRate(dto.getLoanRate());
          punishInterest = punishInterest.add(dto.getPunishInterest());
          if(Integer.parseInt(dto.getDays())<=0){
            loanFlowTail.setLoanType("1");
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          }else{
            loanFlowTail.setLoanType("2");
            shouldOverdueCorpus = shouldOverdueCorpus.add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto.getShouldInterest());
          }
          loanFlowTail.setTempPunishInterest(dto.getTempInterest().subtract(dto.getPunishInterest()));
          //ע�����뷢����
          loanFlowTail.setOccurMoney(loanFlowTail.getRealCorpus().add(loanFlowTail.getRealInterest()).add(loanFlowTail.getRealPunishInterest()));
          loanFlowTailDAO.insert(loanFlowTail);
        }
      }
      //����ͷ��
      LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead.getFlowHeadId());
      loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());
      loanFlowHead1.setShouldCorpus(shouldCorpus);
      loanFlowHead1.setShouldInterest(shouldInterest);
      loanFlowHead1.setShouldOverdueCorpus(shouldOverdueCorpus);
      loanFlowHead1.setShouldOverdueInterest(shouldOverdueInterest);
      loanFlowHead1.setShouldPunishInterest(punishInterest);
      loanFlowHead1.setRealCorpus(loanFlowHead1.getShouldCorpus());
      loanFlowHead1.setRealInterest(loanFlowHead1.getShouldInterest());
      loanFlowHead1.setRealOverdueCorpus(loanFlowHead1.getShouldOverdueCorpus());
      loanFlowHead1.setRealOverdueInterest(loanFlowHead1.getShouldOverdueInterest());
      loanFlowHead1.setRealPunishInterest(loanFlowHead1.getShouldPunishInterest());
//      loanFlowHead1.setOccurMoney(loanFlowHead1.getRealCorpus().add(loanFlowHead1.getRealInterest()).add(loanFlowHead1.getRealOverdueCorpus()).
//          add(loanFlowHead1.getRealOverdueInterest()).add(loanFlowHead1.getRealPunishInterest()));
      loanFlowHead1.setOccurMoney(loanFlowHead1.getOccurMoney().add(loanFlowHead1.getRealCorpus()).add(loanFlowHead1.getRealInterest()).add(loanFlowHead1.getRealOverdueCorpus()).
          add(loanFlowHead1.getRealOverdueInterest()).add(loanFlowHead1.getRealPunishInterest()));
    }catch(Exception e){
      e.printStackTrace();
    }
    return loanFlowHead.getFlowHeadId();
  }
  
  
  /**
   * ������˺���������Ϊ�������ȷ���ı���ˮ��ҵ��״̬������--�Ǽǣ�
   * @param headId
   * @param securityInfo
   */
  public String addCallbackInfoByLoanBankId(String headId,String contractId,SecurityInfo securityInfo) throws Exception{
    LoanFlowHead loanFlowHead=loanFlowHeadDAO.queryById(new Integer(headId));
    if(!loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))){
      throw new BusinessException("���ܽ��еǼǲ�����");
    }
    loanFlowHead.setBizSt(BusiConst.PLBUSINESSSTATE_SIGN+"");
    //����ҵ����־
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_SIGN+"");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    
    //���������־
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_ADD+"");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
    return loanFlowHead.getFlowHeadId().toString();
  }
  /**
   * ������˺���ɾ��
   * @param headId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfoByBank(String headId,SecurityInfo securityInfo)throws Exception{
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      String office = "";//���´�����
      //�жϸñ�ҵ����PL202�е�ҵ��״̬�Ƿ�=2
      if(!loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))){
        throw new BusinessException("�ñ�ҵ��Ϊ"+loanFlowHead.getBizSt()+"״̬������ɾ����");
      }else{
        loanFlowTailDAO.deleteLoanFlowTailAll(headId.toString());
        //ɾ��ҵ������־
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId.toString(),loanFlowHead.getBizSt());
        //����ƾ֤��
        office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
        plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), office, loanFlowHead.getBizDate().substring(0, 6));
        //ɾ��ͷ��
        loanFlowHeadDAO.delete(loanFlowHead);
      }
      //���������־
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE+"");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_BADDEBTSOFF_DO+"");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);

  }
  
  /**
   * ���˺���ά���б���ʾ
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findCallbackList(Pagination pagination,SecurityInfo securityInfo)throws Exception{
    List list = new ArrayList();;
    try{
    List tem_list = null;
    String contractId = (String)pagination.getQueryCriterions().get("contractId");
    String loanKouAcc = (String)pagination.getQueryCriterions().get("loanKouAcc");
    String name = (String)pagination.getQueryCriterions().get("name");
    String cardNum = (String)pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String)pagination.getQueryCriterions().get("loanBankId");
    String docNum = (String)pagination.getQueryCriterions().get("docNum");
    String status = (String)pagination.getQueryCriterions().get("status");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page=pagination.getPage();
    tem_list = loanFlowHeadDAO.queryBadDestroyList_LJ(loanKouAcc, contractId, name, cardNum, docNum, loanBankId, status, orderBy, order, start, pageSize, securityInfo, page);
    if(!tem_list.isEmpty()){
      for(int i=0;i<tem_list.size();i++){
        BadDebtDestroyTbDTO dto = (BadDebtDestroyTbDTO)tem_list.get(i);
        BadDebtDestroyTbDTO dto2 = new BadDebtDestroyTbDTO();
            //ת��ҵ��״̬
            dto2.setBizSt(BusiTools.getBusiValue(Integer.parseInt(dto.getBizSt()), BusiConst.PLBUSINESSSTATE));
            dto2.setBorrowerName(dto.getBorrowerName());
            dto2.setCardNum(dto.getCardNum());
            dto2.setContractId(dto.getContractId());
            dto2.setDocNum(dto.getDocNum());
            dto2.setId(dto.getId());
            dto2.setLoanKouAcc(dto.getLoanKouAcc());
            dto2.setRealCorpus(dto.getRealCorpus());
            dto2.setRealInterest(dto.getRealInterest());
            dto2.setRealMoney(dto2.getRealCorpus().add(dto2.getRealInterest()));
            list.add(dto2);
      }
    }
    int count = loanFlowHeadDAO.queryBadDestroyCount_LJ(loanKouAcc, contractId, name, cardNum, docNum,loanBankId, status, securityInfo);
    pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ά���б�ϼ�
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public BadDebtDestroyTbDTO findCallbackListTotal(Pagination pagination,SecurityInfo securityInfo)throws Exception{
    List list = new ArrayList();
    BadDebtDestroyTbDTO dto = new BadDebtDestroyTbDTO();
    BigDecimal realCorpus = new BigDecimal(0.00);
    BigDecimal realInterest = new BigDecimal(0.00);
    BigDecimal realMoney = new BigDecimal(0.00);
    try{
      String contractId = (String)pagination.getQueryCriterions().get("contractId");
      String loanKouAcc = (String)pagination.getQueryCriterions().get("loanKouAcc");
      String name = (String)pagination.getQueryCriterions().get("name");
      String cardNum = (String)pagination.getQueryCriterions().get("cardNum");
      String loanBankId = (String)pagination.getQueryCriterions().get("loanBankId");
      String docNum = (String)pagination.getQueryCriterions().get("docNum");
      String status = (String)pagination.getQueryCriterions().get("status");
      list = loanFlowHeadDAO.queryBadDestroyTotal_LJ(loanKouAcc, contractId, name, cardNum, docNum,loanBankId, status, securityInfo);
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
          BadDebtDestroyTbDTO dto1 = (BadDebtDestroyTbDTO)list.get(i);
          realCorpus = realCorpus.add(dto1.getRealCorpus());
          realInterest = realInterest.add(dto1.getRealInterest());
          realMoney = realMoney.add(dto1.getRealCorpus().add(dto1.getRealInterest()));
        }
      }
      dto.setRealCorpus(realCorpus);
      dto.setRealInterest(realInterest);
      dto.setRealMoney(realMoney);
    }catch(Exception e){
      e.printStackTrace();
    }
    return dto;
  }
  
  /**
   * ����ά��ɾ���б���Ϣ
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void deleteCallbackInfos(String headId,SecurityInfo securityInfo) throws Exception{
        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
        String office = "";//���´�����
        // �жϸñ�ҵ����PL202�е�ҵ��״̬BIZ_ST�Ƿ�=2��3��4
        if(!(loanFlowHead.getBizSt().equals("2") || loanFlowHead.getBizSt().equals("3") || loanFlowHead.getBizSt().equals("4"))){
          String bizSt=BusiTools.getBusiValue(Integer.parseInt(loanFlowHead.getBizSt()), BusiConst.PLBUSINESSSTATE);
          throw new BusinessException(bizSt+"״̬������ɾ����");
        }else{
          loanFlowTailDAO.deleteLoanFlowTailAll(headId);
          // ɾ��ҵ������־
         plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead.getBizSt());
         // ����ƾ֤��
         office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
         plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), office, loanFlowHead.getBizDate().substring(0, 6));
         //ɾ��ͷ��
         loanFlowHeadDAO.delete(loanFlowHead);
       }
      
      // ���������־
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE+"");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN+"");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);

  }
  
  /**
   * ����ά������
   * @param rowArray
   * @param securityInfo
   * @throws Exception
   */
  public void callbackCallbackInfo(String headId,SecurityInfo securityInfo) throws Exception{

        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
        //�жϸñ�ҵ���ڴ�����ˮ��ͷ��PL202ͷ���е�״̬BIZ_ST�Ƿ�=2.����3.�Ǽ�
        if(!loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_SIGN))&&
            !loanFlowHead.getBizSt().equals(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))){
          String bizSt = BusiTools.getBusiValue(Integer.parseInt(loanFlowHead.getBizSt()), BusiConst.PLBUSINESSSTATE);
          throw new BusinessException(bizSt+"״̬�����ܻ��գ�");
        }else{
          loanFlowHead.setBizSt(BusiConst.BUSINESSSTATE_SURE+"");
          //����ҵ����־
          PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
          plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE+"");
          plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId().toString()));
          plBizActiveLog.setOperator(securityInfo.getUserName());
          plBizActiveLog.setOpTime(new Date());
          plBizActiveLog.setType(loanFlowHead.getBizType());
          plBizActiveLogDAO.insert(plBizActiveLog);
        }
      //���������־
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpBizId(new BigDecimal(headId));
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM+"");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_MAINTAIN+"");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);

  }
  
  /**
   * ����ά���������ڲ�ѯ(����)
   * @param pagination
   * @return
   * @throws Exception
   */
  public BadDebtDestroyTaAFDTO findCallbackInfoMX(Pagination pagination) throws Exception{
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    BorrowerInfoDTO borrowerInfoDTO = null;
    List borrowerList = null;
    List callbacklist = null;
    List taillist = new ArrayList();
    LoanFlowHead loanFlowHead = null;
    String contractId = (String)pagination.getQueryCriterions().get("contractId");
    String headId = (String)pagination.getQueryCriterions().get("headId");
    String bizDate = "";
    String dateStart = "";
    String dateEnd = "";
//  ��PL110��PL111ȡ��Ϣ
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    borrowerInfoDTO.setCardKind(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
    borrowerInfoDTO.setLoanMode(BusiTools.getBusiValue(Integer.parseInt(borrowerInfoDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();// ������
    callbacklist = loanFlowTailDAO.queryLoanFlowTailByHeadId_LJ(headId);
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    bizDate = loanFlowHead.getBizDate();
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = year + "-"
    + month + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    if(!callbacklist.isEmpty()){
      for(int i=0;i<callbacklist.size();i++){
        ShouldBackListDTO dto = (ShouldBackListDTO)callbacklist.get(i);
        ShouldBackListDTO dto2 = new ShouldBackListDTO();
        dto2.setLoanKouYearmonth(dto.getLoanKouYearmonth());
        String loanRepayDay1=this.getEndDay(dto.getLoanKouYearmonth(), loanRepayDay);
        String temp_date = dto.getLoanKouYearmonth().substring(0, 4) + "-"
            + dto.getLoanKouYearmonth().substring(4, 6) + "-" + loanRepayDay1;
        // ��������
        int days = BusiTools.minusDate(temp_bizDate, temp_date);
        String type = dto.getLoanKouType();
        if(type.equals("1")){
          dto2.setLoanKouType("����");
        }else if(type.equals("2")){
          dto2.setLoanKouType("����");
        }
        if(i==0){
          dateStart = dto.getLoanKouYearmonth();
        }
        if(i==(callbacklist.size()-1)){
          dateEnd = dto.getLoanKouYearmonth();
        }
        if(days<=0){
          days=0;
        }
        dto2.setShouldCorpus(dto.getShouldCorpus());
        dto2.setShouldInterest(dto.getShouldInterest());
        dto2.setPunishInterest(dto.getPunishInterest());
        dto2.setCiMoney(dto.getShouldCorpus().add(dto.getShouldInterest()).add(dto.getPunishInterest()));
        dto2.setLoanRate(dto.getLoanRate());
        dto2.setShow_loanRate(dto.getLoanRate().multiply(new BigDecimal(100))+"%");
        dto2.setRealCorpus(dto.getRealCorpus());
        dto2.setRealInterest(dto.getRealInterest());
        dto2.setRealPunishInterest(dto.getRealPunishInterest());
        dto2.setRealCiMoney(dto.getRealCorpus().add(dto.getRealInterest()).add(dto.getRealPunishInterest()));
        if(dto.getLoanRate().doubleValue()==0){
          dto2.setShow_loanRate("");
        }
        dto2.setDays(days+"");
        taillist.add(dto2);
      }
    }
    String orgId = loanFlowHead.getHedaiOrg();
    String orgName = "";
    if(orgId!=null&&!orgId.equals("")){
      orgName = assistantOrgDAO.queryAssistantOrgNameByAssistantOrgId(orgId);
    }
    af.setOrgName(orgName);
    af.setBorrowerInfoDTO(borrowerInfoDTO);
    af.setShouldBackList(taillist);
    af.setBizType(BusiTools.getBusiValue(Integer.parseInt(loanFlowHead.getBizType()), BusiConst.PLBUSINESSTYPE));
    if(loanFlowHead.getBizType().equals("6")){
      af.setOrgType("����");
    }
    if(loanFlowHead.getHedaiOrg()!=null){
      af.setOrgType("������˾");
      af.setOrgName(orgName);
    }
    else{
      af.setOrgType("����");
    }
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(loanFlowHead.getRealOverdueInterest()).add(loanFlowHead.getRealPunishInterest()));
    af.setSumMoney(af.getSumCorpus().add(af.getSumInterest()));
    af.setRealMoney(af.getSumMoney());
    af.setMonthYear(dateEnd);
    af.setHeadId(loanFlowHead.getFlowHeadId().toString());
    return af;
  }

  /**
   * ���˺���ά����ӡ��Ϣ
   * @param headId
   * @return
   */
  public BadDebtDestroyTaAFDTO findPrintCallbackInfo(String headId){
    BadDebtDestroyTaAFDTO af = new BadDebtDestroyTaAFDTO();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    List taillist = loanFlowTailDAO.queryPrintLoanFlowTailByHeadId_LJ(headId);
    String contractId="";
    BorrowerInfoDTO borrowerInfoDTO = null;
    String yearMonth="";
    List borrowerList = null;
    if(!taillist.isEmpty()){
      ShouldBackListDTO dto = (ShouldBackListDTO)taillist.get(0);
      ShouldBackListDTO dto1 = (ShouldBackListDTO)taillist.get(taillist.size()-1);
      contractId =dto.getContractId();
      yearMonth = dto1.getLoanKouYearmonth();
      //��PL110��PL111ȡ��Ϣ
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
    }
    String loanBankId = loanFlowHead.getLoanBankId().toString();
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
    List acclist = loanBankDAO.queryLoanBankAccByBankId_LJ(loanBankId);
    Iterator it = acclist.iterator();
    Object obj [] = null;
    while(it.hasNext()){
      obj=(Object [])it.next();
      if(obj[0] != null){
        af.setLoanAcc(obj[0].toString());
      }
      if(obj[1] != null){
        af.setInterestAcc(obj[1].toString());        
      }
    }
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(loanFlowHead.getRealOverdueInterest())
        .add(loanFlowHead.getRealPunishInterest()));
    af.setRealInterest(loanFlowHead.getRealInterest());
    af.setOverdueInterest(loanFlowHead.getRealOverdueInterest());
    af.setPunishInterest(loanFlowHead.getRealPunishInterest());
    af.setMakeOP(securityDAO.queryByUserid(loanFlowHead.getMakePerson()));
    af.setClearOP(securityDAO.queryByUserid(loanFlowHead.getCheckPerson()));
    af.setBankName(collBank.getCollBankName());
    af.setShouldBackList(taillist);
    String bizType = loanFlowHead.getBizType();
    if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE))){
      af.setBizType("���˺��������ģ���");
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER))){
      if(loanFlowHead.getHedaiOrg() != null){
        af.setBizType("���˺�����������λ��"+loanFlowHead.getHedaiOrg()+"��");
      }else{
        af.setBizType("���˺�������������");
      }
    }
    af.setMonthYear(yearMonth.substring(0,4)+"��"+yearMonth.substring(4,6)+"��");
    af.setBorrowerInfoDTO(borrowerInfoDTO);
    String bizDate = loanFlowHead.getBizDate();
    af.setBizDate(bizDate);
    af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()));
    af.setDocNum(loanFlowHead.getDocNum());
    af.setNoteNum(loanFlowHead.getNoteNum());
    return af;
  }
  /**
   * ������������
   * @param bizDate ҵ������
   * @param yearMonth ��������
   * @param loanRepayDay ������
   * @return
   */
  public int getDays(String bizDate,String yearMonth,String loanRepayDay){
    int days=0;
    String temp_date = yearMonth.substring(0, 4) + "-"
    + yearMonth.substring(4, 6) + "-" + loanRepayDay;
    String temp_bizDate = bizDate.substring(0, 4) + "-"
    + bizDate.substring(4,6)+"-"+bizDate.substring(6,8);
    // ��������
    days = BusiTools.minusDate(temp_bizDate, temp_date);
    return days;
  }
  /**
   * ȡ��������
   * @param loanBankId
   * @return
   */
  public String getClearYear(String loanBankId){
    String year = "";//������
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