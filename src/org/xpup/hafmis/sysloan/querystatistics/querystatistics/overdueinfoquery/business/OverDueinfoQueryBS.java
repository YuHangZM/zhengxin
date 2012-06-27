package org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.OverdueInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.OverdueInfo;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.bsinterface.IOverDueinfoQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryImportDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryShowListDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryTotleDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.form.OverDueinfoQueryShowListAF;

public class OverDueinfoQueryBS implements IOverDueinfoQueryBS {
  private BorrowerAccDAO borrowerAccDAO = null;// ������˻��� PL111

  private AssistantOrgDAO assistantOrgDAO = null; // Э����λ��Ϣ��PL007

  private OverdueInfoDAO overdueInfoDAO = null;

  private SecurityDAO securityDAO = null;
  
  private RestoreLoanDAO restoreLoanDAO = null;
  
  private LoanBankParaDAO loanBankParaDAO = null;

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setOverdueInfoDAO(OverdueInfoDAO overdueInfoDAO) {
    this.overdueInfoDAO = overdueInfoDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  /**
   * ͳ�Ʋ�ѯ�����ڻ�����Ϣ�б�
   * 
   * @author ��� 2007-10-23
   * @return
   */
  public OverDueinfoQueryShowListAF queryShowListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    OverDueinfoQueryShowListAF overDueinfoQueryShowListAF = new OverDueinfoQueryShowListAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String offic = (String) pagination.getQueryCriterions().get("offic");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String buyhouseorgid = (String) pagination.getQueryCriterions().get(
        "buyhouseorgid");
    String floorId = (String) pagination.getQueryCriterions().get("floorId");
    String assistantorg = (String) pagination.getQueryCriterions().get(
        "assistantorg");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String beginoweMonth = (String) pagination.getQueryCriterions().get(
        "beginoweMonth");
    String endoweMonth = (String) pagination.getQueryCriterions().get(
        "endoweMonth");
    String check = (String) pagination.getQueryCriterions().get("temp_check");
    String isNoAcquittance = (String) pagination.getQueryCriterions().get(
        "isNoAcquittance");
    // -------------------------------------------------------//
    // ��ѯ����
    String housetype = (String) pagination.getQueryCriterions()
        .get("housetype");
    String buildAreaMin = (String) pagination.getQueryCriterions().get(
        "buildAreaMin");
    String buildAreaMax = (String) pagination.getQueryCriterions().get(
        "buildAreaMax");
    String floorid = (String) pagination.getQueryCriterions().get("floorid");
    String floorName = (String) pagination.getQueryCriterions()
        .get("floorName");
    String floorNum = (String) pagination.getQueryCriterions().get("floorNum");
    String roomNum = (String) pagination.getQueryCriterions().get("roomNum");
    String agreement = (String) pagination.getQueryCriterions()
        .get("agreement");
    String applyDate = (String) pagination.getQueryCriterions()
        .get("applyDate");
    String otherMoney = (String) pagination.getQueryCriterions().get(
        "otherMoney");
    String startDateMin = (String) pagination.getQueryCriterions().get(
        "startDateMin");
    String startDateMax = (String) pagination.getQueryCriterions().get(
        "startDateMax");
    if (check != null && !"".equals(check)) {
      List overDueinfoQueryShowList = new ArrayList();
      overDueinfoQueryShowList = borrowerAccDAO
          .queryoverDueinfoQueryShowListByCriterions_yg(start, orderBy, order,
              pageSize, page, securityInfo, offic, loanBankName, buyhouseorgid,
              floorId, assistantorg, contractId, loanKouAcc, borrowerName,
              cardNum, beginoweMonth, endoweMonth, isNoAcquittance, housetype,
              buildAreaMin, buildAreaMax, floorid, floorName, floorNum,
              roomNum, agreement, applyDate, otherMoney, startDateMin,
              startDateMax);
      // ���ڻ�����Ϣ�б�
      overDueinfoQueryShowListAF.setList(overDueinfoQueryShowList);
      OverDueinfoQueryTotleDTO overDueinfoQueryTotleDTO = new OverDueinfoQueryTotleDTO();
      overDueinfoQueryTotleDTO = borrowerAccDAO
          .queryoverDueinfoQueryTotleShowListByCriterions_yg(securityInfo,
              offic, loanBankName, buyhouseorgid, floorId, assistantorg,
              contractId, loanKouAcc, borrowerName, cardNum, beginoweMonth,
              endoweMonth, isNoAcquittance, housetype, buildAreaMin,
              buildAreaMax, floorid, floorName, floorNum, roomNum, agreement,
              applyDate, otherMoney, startDateMin, startDateMax);
      // �ϼ���Ϣ
      BigDecimal oweCorpusTotle = overDueinfoQueryTotleDTO.getOweCorpusTotle();// Ƿ������-�ܶ�
      BigDecimal oweInterestTotle = overDueinfoQueryTotleDTO
          .getOweInterestTotle();// Ƿ����Ϣ-�ܶ�
      BigDecimal punishInterest = overDueinfoQueryTotleDTO.getPunishInterest();// Ƿ����Ϣ��Ϣ-�ܶ�
      overDueinfoQueryShowListAF.setOweCorpusTotle(oweCorpusTotle);
      overDueinfoQueryShowListAF.setOweInterestTotle(oweInterestTotle);
      overDueinfoQueryShowListAF.setPunishInterest(punishInterest);
      // �б�����
      count = overDueinfoQueryTotleDTO.getCount();
    }
    // �󵣱���˾
    List assistantOrglist = assistantOrgDAO.queryAssistantOrgList();
    overDueinfoQueryShowListAF.setAssistantorgList(assistantOrglist);
    pagination.setNrOfElements(count);
    return overDueinfoQueryShowListAF;
  }

  /**
   * ͳ�Ʋ�ѯ-���ڴ߻���ѯ��Ϣ�б��ӡ
   * 
   * @author ��� 2007-10-23
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List findoverDueinfoQueryPrint(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    
    OverDueinfoQueryShowListAF overDueinfoQueryShowListAF = new OverDueinfoQueryShowListAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String offic = (String) pagination.getQueryCriterions().get("offic");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String buyhouseorgid = (String) pagination.getQueryCriterions().get(
        "buyhouseorgid");
    String floorId = (String) pagination.getQueryCriterions().get("floorId");
    String assistantorg = (String) pagination.getQueryCriterions().get(
        "assistantorg");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String beginoweMonth = (String) pagination.getQueryCriterions().get(
        "beginoweMonth");
    String endoweMonth = (String) pagination.getQueryCriterions().get(
        "endoweMonth");
    String check=(String) pagination.getQueryCriterions().get(
    "temp_check");
    String isNoAcquittance = (String) pagination.getQueryCriterions().get(
    "isNoAcquittance");
    //-------------------------------------------------------//
//  ��ѯ����
    String housetype = (String) pagination.getQueryCriterions().get("housetype");
    String buildAreaMin = (String) pagination.getQueryCriterions().get("buildAreaMin");
    String buildAreaMax = (String) pagination.getQueryCriterions().get("buildAreaMax");
    String floorid = (String) pagination.getQueryCriterions().get("floorid");
    String floorName = (String) pagination.getQueryCriterions().get("floorName");
    String floorNum = (String) pagination.getQueryCriterions().get("floorNum");
    String roomNum = (String) pagination.getQueryCriterions().get("roomNum");
    String agreement = (String) pagination.getQueryCriterions().get("agreement");
    String applyDate = (String) pagination.getQueryCriterions().get("applyDate");
    String otherMoney = (String) pagination.getQueryCriterions().get("otherMoney");
    String startDateMin = (String) pagination.getQueryCriterions().get("startDateMin");
    String startDateMax = (String) pagination.getQueryCriterions().get("startDateMax");
    
    List overDueinfoQueryShowList = new ArrayList();
    overDueinfoQueryShowList = borrowerAccDAO
        .queryoverDueinfoQueryShowListByCriterions_yga(start, orderBy, order,
            pageSize, page, securityInfo, offic, loanBankName, buyhouseorgid,
            floorId, assistantorg, contractId, loanKouAcc, borrowerName,
            cardNum, beginoweMonth, endoweMonth, isNoAcquittance, housetype,
            buildAreaMin, buildAreaMax, floorid, floorName, floorNum, roomNum,
            agreement, applyDate, otherMoney, startDateMin, startDateMax);
    
    return overDueinfoQueryShowList;
  }

  /**
   * ͳ�Ʋ�ѯ-���ڴ߻���ѯ��Ϣ������ӡ
   * 
   * @author ��� 2007-10-23
   * @param contractId
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public OverDueinfoQueryShowListDTO findoverDueinfoQueryPrintone(String id,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // �����ջ�ά��������ӡ
    OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO= borrowerAccDAO.queryoverDueinfoQueryPrintoneByCriterions_wsh(
        securityInfo, id);
    return  overDueinfoQueryShowListDTO;
  }
  
  /**
   * ������Ϊ��������������
   * jj
   * @param securityInfo
   * @throws Exception
   */
  public void createOverdueData(SecurityInfo securityInfo,String loanBankId) throws Exception{
    String username = securityInfo.getUserName();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String bizYearMonth = bizDate.substring(0,6);
    String bizDay = bizDate.substring(6,8);
    overdueInfoDAO.executeCreateOverdueData(username, bizDate,new Integer(bizDay),new Integer(bizYearMonth),loanBankId);
    String paramType = "A";// ��������
    String isRate = "";// �Ƿ��Ϣ
    String accountDate = "";// ��������
    String interestMode = "";// ���㷣Ϣ��ʽ
    String paramExplain = "";//����˵��
    String allowdays = "";// ��������
    // ��PL003�в�ѯ�����������Ƿ��Ϣ
    isRate = loanBankParaDAO.queryParamValue_LJ(new Integer(loanBankId), paramType, "5");
    // ��PL003��ȡ���㷣Ϣ��ʽ��������Ϣ�����ʡ�����ͬ�����ʡ�����ÿ��XXԪ���㣩
    interestMode = loanBankParaDAO.queryParamValue_LJ(new Integer(loanBankId), paramType,
        "4");
    // ��PL003��ȡ��Ӧ�Ĳ���˵��
    paramExplain = loanBankParaDAO.queryParamExplain_LJ(new Integer(loanBankId),
        paramType, "4");
    // PL003�в�ѯ��������
    allowdays = loanBankParaDAO.queryParamExplain_LJ(new Integer(loanBankId), paramType,
        "5");
    List callbackList = overdueInfoDAO.queryRestoreLoanListByBank_LJ(loanBankId, bizYearMonth, bizDay); 
    this.updatePunishInterest(securityInfo,isRate,accountDate,interestMode,paramExplain,allowdays,callbackList);
  }
  
  public void updatePunishInterest(SecurityInfo securityInfo,String isRate,String accountDate,
      String interestMode,String paramExplain,String allowdays,List callbackList)throws Exception{
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      List list = this.findCallbackList(callbackList, bizDate, isRate, accountDate, interestMode, paramExplain, allowdays);
      if(!list.isEmpty()){
        for(int i=0;i<list.size();i++){
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) list.get(i);
          overdueInfoDAO.insertOver_data_jj(dto.getContractId(),dto.getPunishInterest());
        }
      }
      overdueInfoDAO.executeUpdateOverdueData(bizDate.substring(0,6));
  }
  /**
   * ������Ϊ����������������
   * @param importList
   * @param securityInfo
   * @throws Exception
   */
  public void importOverdueData(List importList, SecurityInfo securityInfo)throws Exception{
    String loanBankId="";
    List bankList = new ArrayList();
    List borrowerAccList=null;
    String loanKouAcc = "";
    String contractId = "";
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING+"";// ��ͬ״̬ 11.������
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String bizDateim="";
    if(importList.isEmpty()){
      throw new BusinessException("�����ļ�Ϊ�գ�");
    }else{
          OverDueinfoQueryImportDTO dto=(OverDueinfoQueryImportDTO)importList.get(0);
          loanBankId = dto.getLoanBankId();
          bizDateim = dto.getBizDate();
    }
    // �����Ƿ�����������ͬ
    String temp_bizDate = bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8);
    if(!temp_bizDate.equals(bizDateim)){
      throw new BusinessException("�����ļ��е������������ڲ�һ�£�");
    }
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(), loanBankId);
      //�ж����޲��������е�Ȩ��
      if(bankList.isEmpty()){
        throw new BusinessException("���߱������е�Ȩ�ޣ�");
      }
      //ɾ������������������ϢPL205�е����ݣ����ݻ������ɾ������ͬ������ڿ���ɾ�����ٵ���     
      overdueInfoDAO.deleteOverdueInfoByLoanBankId(loanBankId,bizDate);
      //��������ϢPL205�в������ݣ���������Ϊ�������½�����˻���PL111�к�ͬ״̬=11.�����У�
      borrowerAccList = borrowerAccDAO.queryBorrowerAccInfoByLoanBankIdAcc_LJ(loanBankId, contractSt);
      if(!borrowerAccList.isEmpty()){
        Object obj=null;
        Iterator it = borrowerAccList.iterator();
        while(it.hasNext()){
          obj=(Object)it.next();
          OverdueInfo overdueInfo = new OverdueInfo();
          overdueInfo.setContractId(obj.toString());
          overdueInfo.setLoanBankId(new Integer(loanBankId));
          overdueInfo.setOweCorpus(new BigDecimal(0.00));
          overdueInfo.setOweInterest(new BigDecimal(0.00));
          overdueInfo.setPunishInterest(new BigDecimal(0.00));
          overdueInfo.setOweMonth("0");
          overdueInfo.setOweDate(bizDate);
          overdueInfoDAO.insert(overdueInfo);
        }
      }
      //�õ��������е�δ������δ����Ϣ��δ����Ϣ�������������»���Ϣ����������ϢPL205�ж�Ӧ�����ݣ�������ͬ��ţ�
      for(int i=1;i<importList.size();i++){
        OverDueinfoQueryImportDTO dto = (OverDueinfoQueryImportDTO)importList.get(i);
        loanKouAcc = dto.getLoanKouAcc();
        contractId = borrowerAccDAO.queryBorrowerAccByLoanKouAcc_LJ(loanKouAcc,
            contractSt, securityInfo);
        Integer pkId = overdueInfoDAO.queryOverdueInfoContactIds(contractId,securityInfo);
        if(pkId==null){
          throw new BusinessException("�����ļ��еĴ����˺������ݿ������ݲ�һ�£�");
        }else{
          OverdueInfo overdueInfo=overdueInfoDAO.queryById(pkId);
          overdueInfo.setOweCorpus(new BigDecimal(dto.getNobackCorpus()));
          overdueInfo.setOweInterest(new BigDecimal(dto.getNobackInterest()));
          overdueInfo.setOweMonth(dto.getMonthsCount());
          overdueInfo.setPunishInterest(new BigDecimal(dto.getNobackPunishInterest()));
          overdueInfo.setReserveaA(dto.getCorpusInterest());
        }
      }
    }

  public LoancallbackTaAF getLoancallbackTaAF(String contractId,
      SecurityInfo securityInfo,String isRate,String accountDate,
      String interestMode,String paramExplain,String allowdays)throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    //String contractId = (String)pagination.getQueryCriterions().get("contractId");
    BorrowerInfoDTO borrowerInfoDTO = new BorrowerInfoDTO();
    List borrowerList = new ArrayList();// �˻���Ϣ
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    List shouldBackList = new ArrayList();
    String yearMonth=bizDate.substring(0,6);
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    // ��PL110��PL111ȡ��Ϣ
    borrowerList = borrowerAccDAO
        .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
    if (!borrowerList.isEmpty()) {
      borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
    }
    String loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
    String loanRepayDay1 = this.getEndDay(yearMonth, loanRepayDay);
    //���û��ѡ�����������жϻ����պͻ���յĴ�С
    if (Integer.parseInt(day) < Integer.parseInt(loanRepayDay1)) {
     // �����С�ڻ�����
     shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJA(
         contractId, yearMonth);
   }else {
     // ����մ��ڵ��ڻ�����
     shouldBackList = restoreLoanDAO.queryRestoreLoanListByContractId_LJB(
         contractId, yearMonth);
   }
    af = this.findCallbackList(shouldBackList, borrowerInfoDTO, bizDate,isRate,accountDate,interestMode,paramExplain,allowdays);
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
  public LoancallbackTaAF findCallbackList(List shouldBackList,BorrowerInfoDTO borrowerInfoDTO,String bizDate,
      String isRate,String accountDate,String interestMode,String paramExplain,String allowdays)throws Exception{
    LoancallbackTaAF af = new LoancallbackTaAF();
    List temp_list = new ArrayList();
    BigDecimal sumCorpus = new BigDecimal(0.00);// �ܻ����
    BigDecimal sumInterest = new BigDecimal(0.00);// �����ܻ�����Ϣ
    //Integer loanBankId = borrowerInfoDTO.getLoanBankId();// �ſ�����
    String loanRepayDay = "";// ������ ��ȡӦ������Ϣʱ�õ�
    BigDecimal temp_interest = new BigDecimal(0.00);// ��ʱ��Ϣ
    String yearMonth = bizDate.substring(0, 6);// ȡ����������е�����
    String day = bizDate.substring(6, bizDate.length());// ȡ����������е���
    String temp_bizDate = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + day;// �����ж��б��еĻ���������ת���Ļ������
    loanRepayDay = borrowerInfoDTO.getLoanRepayDay();
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
        } else if(days<=0){
          dto2.setPunishInterest(dto1.getPunishInterest());
        }else {
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
            } else {// �Ѿ�����
              // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����+��������
   //           String temp_day = dto1.getBizDate().substring(6, 8);// �������ڵ���
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
    af.setSumCorpus(sumCorpus);
    af.setSumInterest(sumInterest);
    af.setShouldBackList(temp_list);
    return af;
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
   * �õ����㷣Ϣ���б�
   * @param callbackList
   * @param loanBankId
   * @param bizDate
   * @return
   * @throws Exception
   */
  public List findCallbackList(List callbackList,String bizDate,
      String isRate,String accountDate,String interestMode,String paramExplain,String allowdays)throws Exception{
    List temp_list = new ArrayList();
    BigDecimal temp_interest = new BigDecimal(0.00);// ��ʱ��Ϣ
    String loanRepayDay = "";//������
    if (!callbackList.isEmpty()) {
      for (int i = 0; i < callbackList.size(); i++) {
        BatchShouldBackListDTO dto1 = (BatchShouldBackListDTO)callbackList.get(i);
        BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO)callbackList.get(i);
        loanRepayDay = this.getEndDay(dto1.getLoanKouYearmonth(), dto1.getLoanRepayDay());
        int days = this.getDays(bizDate, dto1.getLoanKouYearmonth(),loanRepayDay);//��������
        // ��������ÿ�²����ķ�Ϣ
        // �жϻ����(Ӧ������-����+Ӧ����Ϣ-��Ϣ)�Ƿ�=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        }else if(days<=0){
          dto2.setPunishInterest(dto1.getPunishInterest());
        }else {
    //      loanRepayDay = dto1.getLoanRepayDay();
          // ������0�ж��Ƿ��ڿ��������ڼ�Ϣ
          // �������д������PL003������Ϊ��A:������������Ҳ������PARAM_NUM=5�Ĳ���ֵPARAM_VALUE�Ƿ�=0(���������ڼ�Ϣ)
          if (isRate.equals(BusiConst.YES + "")) {// ��Ϣ
            // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����
            accountDate = dto1.getBizDate();
       //     String temp_day = accountDate.substring(6, 8);// �������ڵ���
      //      if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay)) {// С�ڵ��ڻ�����
            if (accountDate==null || accountDate.equals("")) {//�ж��Ƿ��м������ڣ�û�У����������£��У�����������
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
                      .getShouldInterest(), dto1.getRealInterest(),
                  paramExplain ,dto1.getLoanRate());
            }else if(Integer.parseInt(accountDate) <= Integer.parseInt(dto1.getLoanKouYearmonth()+loanRepayDay)){// С�ڵ��ڻ�����
              temp_interest = PunishInterestBS.getTempInterestByYearMonth(interestMode,bizDate,
                  dto1.getLoanKouYearmonth(), loanRepayDay, dto1
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
            } else {// �Ѿ�����
              // �����ж�PL201�еļ��������Ƿ�С�ڵ��ڻ�����+��������
     //         String temp_day = dto1.getBizDate().substring(6, 8);// �������ڵ���
              // ����������+�������������ɵ�������
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay, Integer.parseInt(allowdays));
              //temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
//              if (Integer.parseInt(temp_day) <= Integer.parseInt(temp_loanRepayDay)) {// С�ڵ��ڻ�����+��������
              if (dto1.getBizDate()==null || dto1.getBizDate().equals("")) {//�ж��Ƿ��м�������
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
                        .getShouldInterest(), dto1.getRealInterest(),
                    paramExplain, allowdays,dto1.getLoanRate());
              } else if(Integer.parseInt(dto1.getBizDate())<=Integer.parseInt(temp_loanRepayDay)){//С�ڵ��ڻ�����+��������
                temp_interest = PunishInterestBS.getTempInterestByAllowdays(interestMode,bizDate,
                    dto1.getLoanKouYearmonth(), loanRepayDay, dto1
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
        dto2.setLoanRate(dto1.getLoanRate());// ��ʾÿ������
        dto2.setDays(String.valueOf(days));
        temp_list.add(dto2);
      }
    }
    return temp_list;
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
  }

