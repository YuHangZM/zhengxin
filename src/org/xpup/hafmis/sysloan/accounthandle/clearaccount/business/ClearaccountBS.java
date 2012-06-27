package org.xpup.hafmis.sysloan.accounthandle.clearaccount.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.HafInterestRateDAO;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.bsinterface.IClearaccountBS;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearAccountBalanceInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearaccountDTO;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearaccountTotalDTO;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceInfoAF;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearaccountAF;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.security.common.domain.User;

public class ClearaccountBS implements IClearaccountBS {

  private HafInterestRateDAO hafInterestRateDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private CollBankDAO collBankDAO = null;

  private SecurityDAO securityDAO = null;
  
  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setHafInterestRateDAO(HafInterestRateDAO hafInterestRateDAO) {
    this.hafInterestRateDAO = hafInterestRateDAO;
  }
  public String getMydate() throws Exception {
    String mydate="";
    try {
      mydate = loanFlowHeadDAO.findMydate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mydate;
  }
  public ClearaccountAF queryClearaccountList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    ClearaccountAF clearaccountAF = new ClearaccountAF();
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

    int affirmbizSt = 0;// ȷ��״̬����

    int checkbizSt = 0;// ����״̬����
    
    int realcount = 0 ;
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
    //��ȡ�Ƶ���Ȩ��
    List operList = securityInfo.getUserList();
    String userName="";
    User user = null;
    Iterator itr2 = operList.iterator();
    while (itr2.hasNext()) {
      user = (User) itr2.next();
      String temp_userName=user.getUsername();
      userName+="'"+temp_userName +"'"+",";
    }
    userName=userName.substring(0, userName.lastIndexOf(","));
    //
    List templist = new ArrayList();
    templist = loanFlowHeadDAO.queryClearaccoutShowListByCriterions(start,
        orderBy, order, pageSize, page, securityInfo, docNum, contractId,
        loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName,
        beginBizDate, endBizDate,userName);
    ClearaccountTotalDTO clearaccountTotalDTO = (ClearaccountTotalDTO) loanFlowHeadDAO
    .queryClearaccountShowListCountByCriterions(securityInfo, docNum,
        contractId, loanKouAcc, borrowerName, makePerson, bizType, bizSt,
        loanBankName, beginBizDate, endBizDate,userName);
    
    clearaccountAF.setList(templist);
    
    count = clearaccountTotalDTO.getCount();
    
    occurMoneyTotle = clearaccountTotalDTO.getOccurMoneyTotle(); // ���Ž��-�ܶ�

    reclaimCorpusTotle = clearaccountTotalDTO.getReclaimCorpusTotle();// ���ձ���-�ܶ�

    reclaimAccrualTotle = clearaccountTotalDTO.getReclaimAccrualTotle();// ������Ϣ-�ܶ��ܶ�

    realPunishInterestTotle = clearaccountTotalDTO.getRealPunishInterestTotle();// ���շ�Ϣ-�ܶ�

    badDebtTotle = clearaccountTotalDTO.getBadDebtTotle();// ���˺������-�ܶ�

    putUpMoneyTotle = clearaccountTotalDTO.getPutUpMoneyTotle();// ���˽��-�ܶ�

    bailTotle = clearaccountTotalDTO.getBailTotle();// ��֤��-�ܶ�

    bailAccrualTotle = clearaccountTotalDTO.getBailAccrualTotle();// ��֤����Ϣ-�ܶ�

    affirmbizSt = clearaccountTotalDTO.getAffirmbizSt();// ȷ��״̬����

    checkbizSt = clearaccountTotalDTO.getCheckbizSt();// ����״̬����
    
    realcount = clearaccountTotalDTO.getRealcount();
    
    clearaccountAF.setAffirmbizSt(affirmbizSt);
    clearaccountAF.setCheckbizSt(checkbizSt);
    clearaccountAF.setOccurMoneyTotle(occurMoneyTotle);
    clearaccountAF.setReclaimCorpusTotle(reclaimCorpusTotle);
    clearaccountAF.setReclaimAccrualTotle(reclaimAccrualTotle);
    clearaccountAF.setRealPunishInterestTotle(realPunishInterestTotle);
    clearaccountAF.setBadDebtTotle(badDebtTotle);
    clearaccountAF.setPutUpMoneyTotle(putUpMoneyTotle);
    clearaccountAF.setBailTotle(bailTotle);
    clearaccountAF.setBailAccrualTotle(bailAccrualTotle);
    clearaccountAF.setSumbackmoney(clearaccountAF.getReclaimAccrualTotle().add(
    clearaccountAF.getReclaimCorpusTotle().add(
    clearaccountAF.getRealPunishInterestTotle())));
    clearaccountAF.setRealbackpeopercountTotal(realcount);
    pagination.setNrOfElements(count);
    return clearaccountAF;
  }

  public String clearAccountInfo(SecurityInfo securityInfo, String[] rowArray,
      String plLoanReturnType) throws BusinessException {
    String info = "";
    try {
      String flowid = "";
      if (rowArray.length > 0) {
        int[] a = new int[rowArray.length];
        for (int i = 0; i < rowArray.length; i++) {
          a[i] = Integer.parseInt(rowArray[i]);
        }
        Arrays.sort(a);

        for (int i = a.length - 1; i >= 0; i--) {
          flowid += a[i] + ",";
        }
        flowid.substring(0, flowid.lastIndexOf(","));
        // ��ȡ���ʱ��ͽ�Ϣ�յ�ʱ����
        String numberdate = hafInterestRateDAO.getDay(securityInfo
            .getUserInfo().getPlbizDate())
            + "";
        loanFlowHeadDAO.clearAccount_sy(plLoanReturnType, flowid, securityInfo
            .getUserInfo().getUserIp(), numberdate, securityInfo.getUserInfo()
            .getUsername());
        info = "pass";
      } else {
        throw new BusinessException("��ѡ����Ҫ���˵�ҵ��");
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return info;
  }

  public String clearAccountList(SecurityInfo securityInfo,
      String plLoanReturnType,Pagination pagination) throws BusinessException {
    String info = "";
    try {
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
      //��״̬�Ǹ��˵Ľ�������
      // String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
      String bizSt = "5";
      String loanBankName = (String) pagination.getQueryCriterions().get(
          "loanBankName");
      String beginBizDate = (String) pagination.getQueryCriterions().get(
          "beginBizDate");
      String endBizDate = (String) pagination.getQueryCriterions().get(
          "endBizDate");
      //��ȡ�Ƶ���Ȩ��
      List operList = securityInfo.getUserList();
      String userName="";
      User user = null;
      Iterator itr2 = operList.iterator();
      while (itr2.hasNext()) {
        user = (User) itr2.next();
        String temp_userName=user.getUsername();
        userName+="'"+temp_userName +"'"+",";
      }
      userName=userName.substring(0, userName.lastIndexOf(","));
      //

      List list = loanFlowHeadDAO.queryClearaccoutListByCriterions(securityInfo, docNum, contractId,
          loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName, beginBizDate, endBizDate, userName);
      if (!list.isEmpty()) {
        // ��ȡ���ʱ��ͽ�Ϣ�յ�ʱ����
        String numberdate = hafInterestRateDAO.getDay(securityInfo
            .getUserInfo().getPlbizDate())
            + "";
        loanFlowHeadDAO.clearAccount_all(plLoanReturnType, userName, securityInfo
            .getUserInfo().getUserIp(), numberdate, securityInfo.getUserInfo()
            .getUsername(),list);
        info = "pass";
      } else {
        throw new BusinessException("û�з�������������ҵ��");
      }
    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return info;
  }
  
  /**
   * ���㵥��ѯ
   * jj
   * 2007-11-01
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public ClearAccountBalanceInfoAF findClearAccountBalanceInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception{
    ClearAccountBalanceInfoAF af = new ClearAccountBalanceInfoAF();
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
    String makePerson = (String) pagination.getQueryCriterions().get("makePerson");
    String startDate = (String) pagination.getQueryCriterions().get("startDate");
    String endDate = (String) pagination.getQueryCriterions().get("endDate");   
    List temp_list = null;
    String bizSt = String.valueOf(BusiConst.BUSINESSSTATE_CLEARACCOUNT);
    Integer credit_count = new Integer(0);//����
    BigDecimal credit_corpus = new BigDecimal(0.00);//��������
    BigDecimal credit_overdueCorpus = new BigDecimal(0.00);//���ڱ���
    BigDecimal credit_interest = new BigDecimal(0.00);//��Ϣ
    BigDecimal credit_punishInterest = new BigDecimal(0.00);//��Ϣ
    BigDecimal credit_destoryOccurMoney = new BigDecimal(0.00);//���˺������
    BigDecimal credit_overpayOccurMoney = new BigDecimal(0.00);//���˽��
    Integer debit_count = new Integer(0);//����
    BigDecimal debit_occurMoney = new BigDecimal(0.00);//������
    BigDecimal credit_occurMoney = new BigDecimal(0.00);//������
    Integer credit_bail_count = new Integer(0);//��֤���������
    BigDecimal credti_bail_occurMoney = new BigDecimal(0.00);//���
    //Ĭ����ʾȨ�������µ�ҵ��startDate,endDate���ڻ������
    if(bizType==null){
      /**
       * ���ڴ���
       */
      //���ʻ���2
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setSingle_count(dto.getCount());
        af.setSingle_corpus(dto.getCorpus().add(dto.getOverdueCorpus()));
        af.setSingle_overdueCorpus(dto.getOverdueCorpus());
        af.setSingle_interest(dto.getInterest());
        af.setSingle_punishInterest(dto.getPunishInterest());
        af.setSingle_occurMoney(dto.getOverMoney());
        af.setCheckPerson(securityDAO.queryByUserid(dto.getCheckPerson()));
        af.setClearAccountPerson(securityDAO.queryByUserid(dto.getClearAccPerson()));
      }
      //������ǰ����3
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setPart_count(dto.getCount());
        af.setPart_corpus(dto.getCorpus().add(dto.getOverdueCorpus()));
        af.setPart_overdueCorpus(dto.getOverdueCorpus());
        af.setPart_interest(dto.getInterest());
        af.setPart_punishInterest(dto.getPunishInterest());
        af.setPart_occurMoney(dto.getOverMoney());
      }
      //һ���Ի���4
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAll_count(dto.getCount());
        af.setAll_corpus(dto.getCorpus().add(dto.getOverdueCorpus()));
        af.setAll_overdueCorpus(dto.getOverdueCorpus());
        af.setAll_interest(dto.getInterest());
        af.setAll_punishInterest(dto.getPunishInterest());
        af.setAll_occurMoney(dto.getOverMoney());
      }
      //��������5
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setBatch_count(dto.getCount());
        af.setBatch_corpus(dto.getCorpus().add(dto.getOverdueCorpus()));
        af.setBatch_overdueCorpus(dto.getOverdueCorpus());
        af.setBatch_interest(dto.getInterest());
        af.setBatch_punishInterest(dto.getPunishInterest());
        af.setBatch_occurMoney(dto.getOverMoney());
      }
      //���˺��������ģ�6,���˺�����������7
      /*temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroy_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE), String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setDestroy_count(dto.getCount());
        af.setDestroy_corpus(dto.getCorpus());
        af.setDestroy_overdueCorpus(dto.getOverdueCorpus());
        af.setDestroy_interest(dto.getInterest());
        af.setDestroy_punishInterest(dto.getPunishInterest());
        af.setDestroy_occurMoney(dto.getOccurMoney());
      }
      //�����ջأ����ģ�8,�����ջأ�������9
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroy_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER), String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setDestroyback_count(dto.getCount());
        af.setDestroyback_corpus(dto.getCorpus());
        af.setDestroyback_overdueCorpus(dto.getOverdueCorpus());
        af.setDestroyback_interest(dto.getInterest());
        af.setDestroyback_punishInterest(dto.getPunishInterest());
        af.setDestroyback_occurMoney(dto.getOccurMoney());        
      }*/
      //����13
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpay_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setOverpay_count(dto.getCount());
        af.setOverpay_occurMoney(dto.getOverMoney());    
      }
      //���˵���12 �������Ͳ����ڷ���
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountCount_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_count(dto.getCount());
      }
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_corpus(dto.getCorpus());
        af.setAdjustaccount_interest(dto.getInterest());
        af.setAdjustaccount_punishInterest(dto.getPunishInterest());
      }
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountB_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_overdueCorpus(dto.getOverdueCorpus());
        af.setAdjustaccount_corpus(af.getAdjustaccount_corpus().add(dto.getCorpus()));
      }
      /*temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountC_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_destroyOccurMoney(dto.getDestroyOccurMoney());
      }*/
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountD_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setAdjustaccount_occurMoney(dto.getOverMoney());
      }
      //��֤�� ��ֵ14
      /*temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoneyCredit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        credit_bail_count = dto.getCount();     
        credti_bail_occurMoney = dto.getBailOccurMoney();
      }
      //��֤�� ��ֵ15
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoneyCredit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setCredit_bail_count(new Integer(dto.getCount().intValue()+credit_bail_count.intValue()));     
        af.setCredti_bail_occurMoney(dto.getBailOccurMoney().add(credti_bail_occurMoney));
      }*/
      /**
       * ���ڽ跽
       */
      //����1
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceLoanaccord_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_ISSUED), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setLoanaccord_count(dto.getCount());
        af.setLoanaccord_occurMoney(dto.getOccurMoney());
      }
      //���˵���12����1
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceAdjustaccountLoanaccord_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), "1", loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setAdjustaccount_loanaccordCount(dto.getCount());
        af.setAdjustaccount_loanaccordOccurMoney(dto.getOccurMoney());
      }
      //��֤�� ��ֵ
      /*temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoneyDebit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setDebit_bail_count(dto.getCount());     
        af.setDebit_bail_occurMoney(dto.getBailOccurMoney());
      }
      //��֤����ȡ��Ϣ
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOtherInterestDebit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setDebit_interest_count(dto.getCount());     
        af.setDebit_interest_occurMoney(dto.getBailInterestOccurMoney());
      }*/      
      //�ڳ�δ�ջؽ��
      BigDecimal initialStages_destroyOccurMoney=new BigDecimal(0.00);
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesDestroyOccurMoneyBad_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        initialStages_destroyOccurMoney=dto.getInitialStages_destroyOccurMoney();
      }
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesDestroyOccurMoneyBadBack_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_destroyOccurMoney(initialStages_destroyOccurMoney.subtract(dto.getInitialStages_destroyOccurMoney()));
      }
      //���˺���������
      /*temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroyOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE),String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setDestroyOccurMoney(dto.getDestroyOccurMoney());
      }
      //�����ջط�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroybackOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER),String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setDestroybackOccurMoney(dto.getDestroybackOccurMoney());
      }*/
      //��ĩδ�ջؽ��
      BigDecimal final_destroyOccurMoney = new BigDecimal(0.00);
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalDestroyOccurMoneyBad_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        final_destroyOccurMoney=dto.getFinal_destroyOccurMoney();
      }
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalDestroyOccurMoneyBadBack_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_destroyOccurMoney(final_destroyOccurMoney.subtract(dto.getFinal_destroyOccurMoney()));
      }
      //�ڳ����˽��
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesOverpayOccurMoney_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_overpayOccurMoney(dto.getInitialStages_overpayOccurMoney());
      }
      //���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpayOccurMoney_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setOverpayOccurMoney(dto.getOverpayOccurMoney());
      }
      //��ĩ���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalOverpayOccurMoney_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_overpayOccurMoney(dto.getFinal_overpayOccurMoney());
      }
      //�ڳ���֤�����
      /*temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN),String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_bailOccurMoney(dto.getInitialStages_bailOccurMoney());
      }
      //��֤������14
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);        
        af.setBailOccurMoney(dto.getBailOccurMoney());
      }
      //��֤������15
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);        
        af.setBailOccurMoney(af.getBailOccurMoney().add(dto.getBailOccurMoney()));
      }
      //��֤����Ϣ
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOtherInterest_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setBailInterestOccurMoney(dto.getBailInterestOccurMoney());
      }
      //��ĩ��֤�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST),null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_bailOccurMoney(dto.getFinal_bailOccurMoney());
      }*/
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ISSUED))){//����1

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceLoanaccord_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_ISSUED), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setLoanaccord_count(dto.getCount());
        af.setLoanaccord_occurMoney(dto.getOccurMoney());
      }
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER))){//���ʻ���2

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setSingle_count(dto.getCount());
        af.setSingle_corpus(dto.getCorpus());
        af.setSingle_overdueCorpus(dto.getOverdueCorpus());
        af.setSingle_interest(dto.getInterest());
        af.setSingle_punishInterest(dto.getPunishInterest());
        af.setSingle_occurMoney(dto.getOverMoney());
      }
      //�ڳ����˽��
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_overpayOccurMoney(dto.getInitialStages_overpayOccurMoney());
      }
      //���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setOverpayOccurMoney(dto.getOverpayOccurMoney());
      }
      //��ĩ���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_overpayOccurMoney(dto.getFinal_overpayOccurMoney());
      }
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))){//������ǰ����3

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setPart_count(dto.getCount());
        af.setPart_corpus(dto.getCorpus());
        af.setPart_overdueCorpus(dto.getOverdueCorpus());
        af.setPart_interest(dto.getInterest());
        af.setPart_punishInterest(dto.getPunishInterest());
        af.setPart_occurMoney(dto.getOverMoney());
      }
      //�ڳ����˽��
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_overpayOccurMoney(dto.getInitialStages_overpayOccurMoney());
      }
      //���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setOverpayOccurMoney(dto.getOverpayOccurMoney());
      }
      //��ĩ���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_overpayOccurMoney(dto.getFinal_overpayOccurMoney());
      }
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))){//һ���Ի���4

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAll_count(dto.getCount());
        af.setAll_corpus(dto.getCorpus());
        af.setAll_overdueCorpus(dto.getOverdueCorpus());
        af.setAll_interest(dto.getInterest());
        af.setAll_punishInterest(dto.getPunishInterest());
        af.setAll_occurMoney(dto.getOverMoney());
      }
      //�ڳ����˽��
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_overpayOccurMoney(dto.getInitialStages_overpayOccurMoney());
      }
      //���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setOverpayOccurMoney(dto.getOverpayOccurMoney());
      }
      //��ĩ���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_overpayOccurMoney(dto.getFinal_overpayOccurMoney());
      }
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER))){//��������5

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceCallBack_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setBatch_count(dto.getCount());
        af.setBatch_corpus(dto.getCorpus());
        af.setBatch_overdueCorpus(dto.getOverdueCorpus());
        af.setBatch_interest(dto.getInterest());
        af.setBatch_punishInterest(dto.getPunishInterest());
        af.setBatch_occurMoney(dto.getOverMoney());
      }
      //�ڳ����˽��
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_overpayOccurMoney(dto.getInitialStages_overpayOccurMoney());
      }
      //���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setOverpayOccurMoney(dto.getOverpayOccurMoney());
      }
      //��ĩ���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_overpayOccurMoney(dto.getFinal_overpayOccurMoney());
      }
    }/*else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE))){//���˺��������ģ�6

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroy_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setDestroy_count(dto.getCount());
        af.setDestroy_corpus(dto.getCorpus());
        af.setDestroy_overdueCorpus(dto.getOverdueCorpus());
        af.setDestroy_interest(dto.getInterest());
        af.setDestroy_punishInterest(dto.getPunishInterest());
        af.setDestroy_occurMoney(dto.getOccurMoney());
      }
//      //�ڳ�δ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setInitialStages_destroyOccurMoney(dto.getInitialStages_destroyOccurMoney());
//      }
      //���˺���������
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroyOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE),null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setDestroyOccurMoney(dto.getDestroyOccurMoney());
      }
//      //��ĩδ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setFinal_destroyOccurMoney(dto.getFinal_destroyOccurMoney());
//      }
    }/*else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER))){//���˺�����������7
      
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroy_LJ(null, String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setDestroy_count(dto.getCount());
        af.setDestroy_corpus(dto.getCorpus());
        af.setDestroy_overdueCorpus(dto.getOverdueCorpus());
        af.setDestroy_interest(dto.getInterest());
        af.setDestroy_punishInterest(dto.getPunishInterest());
        af.setDestroy_occurMoney(dto.getOccurMoney());
      }
//      //�ڳ�δ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setInitialStages_destroyOccurMoney(dto.getInitialStages_destroyOccurMoney());
//      }
      //���˺���������
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroyOccurMoney_LJ(null,String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setDestroyOccurMoney(dto.getDestroyOccurMoney());
      }
//      //��ĩδ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setFinal_destroyOccurMoney(dto.getFinal_destroyOccurMoney());
//      }
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER))){//�����ջأ����ģ�8

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroy_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER),null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setDestroy_count(dto.getCount());
        af.setDestroy_corpus(dto.getCorpus());
        af.setDestroy_overdueCorpus(dto.getOverdueCorpus());
        af.setDestroy_interest(dto.getInterest());
        af.setDestroy_punishInterest(dto.getPunishInterest());
        af.setDestroy_occurMoney(dto.getOccurMoney());
      }
//      //�ڳ�δ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setInitialStages_destroyOccurMoney(dto.getInitialStages_destroyOccurMoney());
//      }
      //�����ջط�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroybackOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER),null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setDestroybackOccurMoney(dto.getDestroybackOccurMoney());
      }
//      //��ĩδ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setFinal_destroyOccurMoney(dto.getFinal_destroyOccurMoney());
//      }
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER))){//�����ջأ�������9
      
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroy_LJ(null,String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setDestroy_count(dto.getCount());
        af.setDestroy_corpus(dto.getCorpus());
        af.setDestroy_overdueCorpus(dto.getOverdueCorpus());
        af.setDestroy_interest(dto.getInterest());
        af.setDestroy_punishInterest(dto.getPunishInterest());
        af.setDestroy_occurMoney(dto.getOccurMoney());
      }
//      //�ڳ�δ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setInitialStages_destroyOccurMoney(dto.getInitialStages_destroyOccurMoney());
//      }
      //�����ջط�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceDestroybackOccurMoney_LJ(null,String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setDestroybackOccurMoney(dto.getDestroybackOccurMoney());
      }
//      //��ĩδ�ջؽ��
//      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalDestroyOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
//      if(!temp_list.isEmpty()){
//        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
//        af.setFinal_destroyOccurMoney(dto.getFinal_destroyOccurMoney());
//      }
    }*/else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG))){//���˵���12

      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountCount_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_count(dto.getCount());
      }
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_corpus(dto.getCorpus());
        af.setAdjustaccount_interest(dto.getInterest());
        af.setAdjustaccount_punishInterest(dto.getPunishInterest());
      }
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountB_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_overdueCorpus(dto.getOverdueCorpus());
      }
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountC_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setAdjustaccount_destroyOccurMoney(dto.getDestroyOccurMoney());
      }
      temp_list = loanFlowHeadDAO.queryClearAccountAdjustaccountD_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setAdjustaccount_occurMoney(dto.getOverMoney());
      }

      //���˵���12����1
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceAdjustaccountLoanaccord_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), "1", loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setAdjustaccount_loanaccordCount(dto.getCount());
        af.setAdjustaccount_loanaccordOccurMoney(dto.getOccurMoney());
      }

      //�ڳ����˽��
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_overpayOccurMoney(dto.getInitialStages_overpayOccurMoney());
      }
      //���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setOverpayOccurMoney(dto.getOverpayOccurMoney());
      }
      //��ĩ���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_overpayOccurMoney(dto.getFinal_overpayOccurMoney());
      }
    }else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY))){//����13

      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpay_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY), loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
        af.setOverpay_count(dto.getCount());
        af.setOverpay_occurMoney(dto.getOverMoney());    
      }
      //�ڳ����˽��
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_overpayOccurMoney(dto.getInitialStages_overpayOccurMoney());
      }
      //���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setOverpayOccurMoney(dto.getOverpayOccurMoney());
      }
      //��ĩ���˷�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalOverpayOccurMoneyA_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_OVERPAY), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_overpayOccurMoney(dto.getFinal_overpayOccurMoney());
      }
    }/*else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN))){//��֤��14

      //��֤�� ��ֵ14
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoneyCredit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setCredit_bail_count(dto.getCount());     
        af.setCredti_bail_occurMoney(dto.getBailOccurMoney());
      }
      //��֤�� ��ֵ
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoneyDebit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setDebit_bail_count(dto.getCount());     
        af.setDebit_bail_occurMoney(dto.getBailOccurMoney());
      }
      //��֤����ȡ��Ϣ
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOtherInterestDebit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setDebit_interest_count(dto.getCount());     
        af.setDebit_interest_occurMoney(dto.getBailOccurMoney());
      } 
      //�ڳ���֤�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN),null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_bailOccurMoney(dto.getInitialStages_bailOccurMoney());
      }
      //��֤������
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setBailOccurMoney(dto.getBailOccurMoney());
      }
      //��֤����Ϣ
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOtherInterest_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setBailInterestOccurMoney(dto.getBailInterestOccurMoney());
      }
      //��ĩ��֤�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_MARGIN), null,null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_bailOccurMoney(dto.getFinal_bailOccurMoney());
      }
    }*/else if(bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST))){//��Ϣ15
      //��֤�� ��ֵ15
      /*temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoneyCredit_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);     
        af.setCredit_bail_count(dto.getCount());     
        af.setCredti_bail_occurMoney(dto.getBailOccurMoney());
      }
      //�ڳ���֤�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST),null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setInitialStages_bailOccurMoney(dto.getInitialStages_bailOccurMoney());
      }
      //��֤������
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceBailOccurMoney_LJ(String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST), null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setBailOccurMoney(dto.getBailOccurMoney());
      }
      //��ĩ��֤�����
      temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalBailOccurMoney_LJ(null, String.valueOf(BusiConst.PLBUSINESSTYPE_CLEARINTEREST),null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
      if(!temp_list.isEmpty()){
        ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
        af.setFinal_bailOccurMoney(dto.getFinal_bailOccurMoney());
      }*/
    }
    credit_count = new Integer(af.getSingle_count().intValue()+af.getPart_count().intValue()+af.getAll_count().intValue()+
        af.getBatch_count().intValue()+af.getDestroy_count().intValue()+af.getDestroyback_count().intValue()+
        af.getOverpay_count().intValue()+af.getAdjustaccount_count().intValue()+af.getCredit_bail_count().intValue());
    
    credit_corpus = af.getSingle_corpus().add(af.getPart_corpus()).add(af.getAll_corpus()).add(af.getBatch_corpus()).add
    (af.getDestroy_corpus()).add(af.getDestroyback_corpus()).add(af.getAdjustaccount_corpus()); 
    
//    credit_overdueCorpus = af.getSingle_overdueCorpus().add(af.getPart_overdueCorpus()).add(af.getAll_overdueCorpus()).add(af.getBatch_overdueCorpus()).add
//    (af.getDestroy_overdueCorpus()).add(af.getDestroyback_overdueCorpus()).add(af.getAdjustaccount_overdueCorpus());
    
    credit_interest = af.getSingle_interest().add(af.getPart_interest()).add(af.getAll_interest()).add(af.getBatch_interest()).add
    (af.getDestroy_interest()).add(af.getDestroyback_interest()).add(af.getAdjustaccount_interest());
    
    credit_punishInterest = af.getSingle_punishInterest().add(af.getPart_punishInterest()).add(af.getAll_punishInterest()).add
    (af.getBatch_punishInterest()).add(af.getDestroy_punishInterest()).add(af.getDestroyback_punishInterest()).add(af.getAdjustaccount_punishInterest());
    
    credit_destoryOccurMoney = af.getDestroy_occurMoney().add(af.getDestroyback_occurMoney()).add(af.getAdjustaccount_destroyOccurMoney());
    
    credit_overpayOccurMoney = af.getSingle_occurMoney().add(af.getPart_occurMoney()).add(af.getAll_occurMoney()).add(af.getBatch_occurMoney()).add
    (af.getOverpay_occurMoney()).add(af.getAdjustaccount_occurMoney());
    
    credit_occurMoney = af.getCredti_bail_occurMoney();
    
    debit_count = new Integer(af.getLoanaccord_count().intValue()+af.getAdjustaccount_loanaccordCount().intValue()+af.getDebit_bail_count().intValue()
        +af.getDebit_interest_count().intValue());
    
    debit_occurMoney = af.getLoanaccord_occurMoney().add(af.getAdjustaccount_loanaccordOccurMoney().add(af.getDebit_bail_occurMoney()).add(af.getDebit_interest_occurMoney()));
    
    af.setCredit_count(credit_count);
    af.setCredit_corpus(credit_corpus.add(credit_overdueCorpus));
    af.setCredit_overdueCorpus(credit_overdueCorpus);
    af.setCredit_interest(credit_interest);
    af.setCredit_punishInterest(credit_punishInterest);
    af.setCredit_destoryOccurMoney(credit_destoryOccurMoney);
    af.setCredit_overpayOccurMoney(credit_overpayOccurMoney);
    af.setCredit_occurMoney(credit_occurMoney);
    
    af.setDebit_count(debit_count);
    af.setDebit_occurMoney(debit_occurMoney);
    
    //�ڳ��������
    BigDecimal temp_initialStages_corpus = new BigDecimal(0.00);
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesCorpusA_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
       temp_initialStages_corpus=dto.getInitialStages_corpus();        
    }
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesCorpusB_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      BigDecimal tempInitialStages_corpus = temp_initialStages_corpus.subtract(dto.getInitialStages_corpus()).divide(new BigDecimal(1), 2,BigDecimal.ROUND_HALF_UP);
      BigDecimal tempInitialStages_corpus2 = loanFlowHeadDAO.queryPl500Corpus(startDate, endDate, loanBankId);
      af.setInitialStages_corpus(tempInitialStages_corpus.add(tempInitialStages_corpus2));
    }
    //�����������
    BigDecimal temp_corpus=new BigDecimal(0.00);
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceCorpusOccurMoney_LJ(bizType, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      temp_corpus = dto.getCorpus_occurMoney();  
    }
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceOccurMoney_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      af.setCorpus_occurMoney(temp_corpus.subtract(af.getCredit_corpus()));
    }
    //���ڱ��������Ϣ�������Ϣ������
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceOccurMoney_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      //af.setCorpus_occurMoney(dto.getCorpus_occurMoney());
      af.setOverdue_occurMoney(dto.getOverdue_occurMoney().multiply(new BigDecimal(-1)));
      af.setInterest_occurMoney(dto.getInterest_occurMoney());
      af.setPunishInterest_occurMoney(dto.getPunishInterest_occurMoney());
    }
    //��ĩ�������
    BigDecimal temp_finalStages_corpus = new BigDecimal(0.00);
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalCorpusA_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      temp_finalStages_corpus = dto.getFinal_corpus();
    }
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalCorpusB_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      BigDecimal tempFinal_corpus = temp_finalStages_corpus.subtract(dto.getFinal_corpus()).divide(new BigDecimal(1), 2,BigDecimal.ROUND_HALF_UP);
      BigDecimal tempFinal_corpus1 = loanFlowHeadDAO.queryPl500finalCorpus(startDate, endDate, loanBankId);
      af.setFinal_corpus(tempFinal_corpus.add(tempFinal_corpus1));
    }
    //�ڳ���Ϣ���
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesInterest_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      BigDecimal tempInitialStages_interest = dto.getInitialStages_interest();
      BigDecimal tempInitialStages_interest2 = loanFlowHeadDAO.queryPl500Interest(startDate, endDate, loanBankId);
      af.setInitialStages_interest(tempInitialStages_interest.add(tempInitialStages_interest2));
    }
    //��ĩ��Ϣ���
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialFinalInterest_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      BigDecimal tempFinal_interest = dto.getFinal_interest();
      BigDecimal tempFinal_interest1 = loanFlowHeadDAO.queryPl500FinalInterest(startDate, endDate, loanBankId);
      af.setFinal_interest(tempFinal_interest.add(tempFinal_interest1));
    }
    //�ڳ���Ϣ���
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceInitialStagesPunishInterest_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0); 
      BigDecimal tempInitialStages_punishInterest = dto.getInitialStages_punishInterest();
      BigDecimal tempInitialStages_punishInterest2 = loanFlowHeadDAO.queryPl500Punish_interest(startDate, endDate, loanBankId);
      af.setInitialStages_punishInterest(tempInitialStages_punishInterest.add(tempInitialStages_punishInterest2));
    }
    //��ĩ��Ϣ���
    temp_list = loanFlowHeadDAO.queryClearAccountBalanceFinalPunishInterest_LJ(null, null, loanBankId, makePerson, startDate, endDate, bizSt, securityInfo);
    if(!temp_list.isEmpty()){
      ClearAccountBalanceInfoDTO dto = (ClearAccountBalanceInfoDTO)temp_list.get(0);
      BigDecimal tempFinal_punishInterest = dto.getFinal_punishInterest();
      BigDecimal tempFinal_punishInterest1 = loanFlowHeadDAO.queryPl500FinalPunish_interest(startDate, endDate, loanBankId);
      af.setFinal_punishInterest(tempFinal_punishInterest.add(tempFinal_punishInterest1));
    }
    return af;    
  }
  
  /**
   * �鿴��ϸ
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public ClearaccountAF findClearaccountMXList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    ClearaccountAF clearaccountAF = new ClearaccountAF();
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

    //BigDecimal badDebtTotle = new BigDecimal(0.00);// ���˺������-�ܶ�

    BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// ���˽��-�ܶ�

    //BigDecimal bailTotle = new BigDecimal(0.00);// ��֤��-�ܶ�

    //BigDecimal bailAccrualTotle = new BigDecimal(0.00);// ��֤����Ϣ-�ܶ�

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
    //��ȡ�Ƶ���Ȩ��
//    List operList = securityInfo.getUserList();
//    String userName="";
//    User user = null;
//    Iterator itr2 = operList.iterator();
//    while (itr2.hasNext()) {
//      user = (User) itr2.next();
//      String temp_userName=user.getUsername();
//      userName+="'"+temp_userName +"'"+",";
//    }
//    userName=userName.substring(0, userName.lastIndexOf(","));
    //
    List templist = new ArrayList();
    templist = loanFlowHeadDAO.queryClearaccoutShowListByCriterions_LJ(start,
        orderBy, order, pageSize, page, securityInfo, docNum, contractId,
        loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName,
        beginBizDate, endBizDate,makePerson);
    for (int i = 0; i < templist.size(); i++) {
      //ת������
      ClearaccountDTO dto = (ClearaccountDTO)templist.get(i);
      String bankName = collBankDAO.getCollBankByCollBankid(dto.getLoanBank()).getCollBankName();
      dto.setLoanBank(bankName);
    }
    ClearaccountTotalDTO clearaccountTotalDTO = (ClearaccountTotalDTO) loanFlowHeadDAO
    .queryClearaccountShowListCountByCriterions_LJ(securityInfo, docNum,
        contractId, loanKouAcc, borrowerName, makePerson, bizType, bizSt,
        loanBankName, beginBizDate, endBizDate,makePerson);
    
    clearaccountAF.setList(templist);
    
    count = clearaccountTotalDTO.getCount();
    
    occurMoneyTotle = clearaccountTotalDTO.getOccurMoneyTotle(); // ���Ž��-�ܶ�

    reclaimCorpusTotle = clearaccountTotalDTO.getReclaimCorpusTotle();// ���ձ���-�ܶ�

    reclaimAccrualTotle = clearaccountTotalDTO.getReclaimAccrualTotle();// ������Ϣ-�ܶ��ܶ�

    realPunishInterestTotle = clearaccountTotalDTO.getRealPunishInterestTotle();// ���շ�Ϣ-�ܶ�

    //badDebtTotle = clearaccountTotalDTO.getBadDebtTotle();// ���˺������-�ܶ�

    putUpMoneyTotle = clearaccountTotalDTO.getPutUpMoneyTotle();// ���˽��-�ܶ�

    //bailTotle = clearaccountTotalDTO.getBailTotle();// ��֤��-�ܶ�

    //bailAccrualTotle = clearaccountTotalDTO.getBailAccrualTotle();// ��֤����Ϣ-�ܶ�

    affirmbizSt = clearaccountTotalDTO.getAffirmbizSt();// ȷ��״̬����

    checkbizSt = clearaccountTotalDTO.getCheckbizSt();// ����״̬����
    clearaccountAF.setAffirmbizSt(affirmbizSt);
    clearaccountAF.setCheckbizSt(checkbizSt);
    clearaccountAF.setOccurMoneyTotle(occurMoneyTotle);
    clearaccountAF.setReclaimCorpusTotle(reclaimCorpusTotle);
    clearaccountAF.setReclaimAccrualTotle(reclaimAccrualTotle);
    clearaccountAF.setRealPunishInterestTotle(realPunishInterestTotle);
    //clearaccountAF.setBadDebtTotle(badDebtTotle);
    clearaccountAF.setPutUpMoneyTotle(putUpMoneyTotle);
    //clearaccountAF.setBailTotle(bailTotle);
    //clearaccountAF.setBailAccrualTotle(bailAccrualTotle);
    clearaccountAF.setSumbackmoney(clearaccountAF.getReclaimAccrualTotle().add(
    clearaccountAF.getReclaimCorpusTotle().add(
    clearaccountAF.getRealPunishInterestTotle())));
    clearaccountAF.setRealCorpusTotal(clearaccountTotalDTO.getRealCorpusTotal());
    clearaccountAF.setRealInterestTotal(clearaccountTotalDTO.getRealInterestTotal());
    clearaccountAF.setRealPunishInterestTotle(clearaccountTotalDTO.getRealPunish_interestTotal());
    pagination.setNrOfElements(count);
    return clearaccountAF;
  }
  
  /**
   * �õ���������
   */
  public String getLoanBankName(String loanBankId) throws Exception{
    String loanBankName = "";
    try{
      if(loanBankId != null){
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
        loanBankName = collBank.getCollBankName();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return loanBankName;
  }
  /**
   * ��ϸ�б��ӡ
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findClearaccountMXPrint(Pagination pagination,
      SecurityInfo securityInfo)throws Exception{
    List list = null;
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
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
    //��ȡ�Ƶ���Ȩ��
//    List operList = securityInfo.getUserList();
//    String userName="";
//    User user = null;
//    Iterator itr2 = operList.iterator();
//    while (itr2.hasNext()) {
//      user = (User) itr2.next();
//      String temp_userName=user.getUsername();
//      userName+="'"+temp_userName +"'"+",";
//    }
//    userName=userName.substring(0, userName.lastIndexOf(","));
    //
    try{
    list = loanFlowHeadDAO.queryClearaccoutShowListByCriterionsPrint_LJ(start,
        orderBy, order, pageSize, page, securityInfo, docNum, contractId,
        loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName,
        beginBizDate, endBizDate,makePerson);
    for (int i = 0; i < list.size(); i++) {
      //ת������
      ClearaccountDTO dto = (ClearaccountDTO)list.get(i);
      String bankName = collBankDAO.getCollBankByCollBankid(dto.getLoanBank()).getCollBankName();
      dto.setMakeBillPerson(securityDAO.queryByUserid(dto.getMakeBillPerson()));
      dto.setCheckPerson(securityDAO.queryByUserid(dto.getCheckPerson()));
      dto.setClearAccPerson(securityDAO.queryByUserid(dto.getClearAccPerson()));
      dto.setLoanBank(bankName);
    }
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
}
