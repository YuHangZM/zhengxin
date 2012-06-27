package org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.bsinterface.IBailClearInterestBS;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author ��Ұ 2007-10-05
 */
public class BailClearInterestBS implements IBailClearInterestBS {

  private BorrowerAccDAO borrowerAccDAO = null;// ������˻��� PL111

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// ��ˮͷ�� PL202

  private LoanFlowTailDAO loanFlowTailDAO = null;// ��ˮβ�� PL203

  private PlBizActiveLogDAO plBizActiveLogDAO = null;// ҵ����־ PL020

  private PlOperateLogDAO plOperateLogDAO = null;// ������־ PL021

  private CollBankDAO collBankDAO = null;// ת����������

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  /**
   * ����֤���Ϣ��ѯ��ʾ�б�
   * 
   * @author ��Ұ 2007-10-05
   */
  public BailClearInterestTaAF findBailClearInterestTaListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    BigDecimal bailBalanceTotle = new BigDecimal(0.00);// ��Ϣǰ��֤��-�ܶ�
    BailClearInterestTaAF bailClearInterestTaAF = new BailClearInterestTaAF();
    // �����
    String bizYear = securityInfo.getUserInfo().getPlbizDate().substring(0, 4);
    // �޸ļ�¼��ֻ����6�·ݲ��ܽ��б�֤���Ϣ 2007-11-10
    // �޸ļ�¼��ֻ����6��30�ղ��ܽ��б�֤���Ϣ 2007-11-20
    String bizMonthDay = securityInfo.getUserInfo().getPlbizDate().substring(4, 8);
    if (!bizMonthDay.equals("0630")) {
      throw new BusinessException("ֻ������6��30�ս��б�֤���Ϣ��");
    }
    String bizDate = bizYear + "0630";
    // �жϸ������»������Ϊ"������6��30��"���Ƿ���ڽ�Ϣ ��PL202.Type=15��
    boolean isClearinterestTa = loanFlowHeadDAO.isExistsClearinterestTa(
        bizDate, loanBankName);
    if (isClearinterestTa) {// ��û�н�Ϣ��ȡ�����������е��б�֤��Ľ���ˣ�PL111.CUR_INTEGRAL��=null��
      List bailclearinterestTaList = new ArrayList();
      List templist = new ArrayList();
      bailclearinterestTaList = loanFlowHeadDAO
          .queryBailClearInterestTaListByCriterions(loanBankName, start,
              orderBy, order, pageSize, page);
      if (bailclearinterestTaList.size() > 0) {
        List countList = new ArrayList();
        countList = loanFlowHeadDAO
            .queryBailClearInterestTaCountByCriterions(loanBankName);
        count = countList.size();
        bailBalanceTotle = loanFlowHeadDAO
            .queryBailClearInterestTaSumMoneyByCriterions(loanBankName);
        Iterator iterate = bailclearinterestTaList.iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          BailClearInterestTaDTO bailClearInterestTaDTO = new BailClearInterestTaDTO();
          obj = (Object[]) iterate.next();
          String loanBankId = null;
          if (obj[0] != null && !obj[0].equals(""))
            loanBankId = obj[0].toString();
          if (obj[1] != null && !obj[1].equals(""))
            bailClearInterestTaDTO.setParamExplainInterestD(obj[1].toString());
          if (obj[2] != null && !obj[2].equals(""))
            bailClearInterestTaDTO.setParamExplainInterestL(obj[2].toString());
          if (obj[3] != null && !obj[3].equals(""))
            bailClearInterestTaDTO.setLoanKouAcc(obj[3].toString());
          if (obj[4] != null && !obj[4].equals(""))
            bailClearInterestTaDTO.setContractId(obj[4].toString());
          if (obj[5] != null && !obj[5].equals(""))
            bailClearInterestTaDTO.setBorrowerName(obj[5].toString());
          if (obj[6] != null && !obj[6].equals(""))
            bailClearInterestTaDTO.setCardNum(obj[6].toString());
          if (obj[7] != null && !obj[7].equals(""))
            bailClearInterestTaDTO.setBailBalance(obj[7].toString());
          // ת����������
          try {
            CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
                .toString());
            bailClearInterestTaDTO.setLoanBankName(dto.getCollBankName());
          } catch (Exception e) {
            e.printStackTrace();
          }
          templist.add(bailClearInterestTaDTO);
        }
      }
      pagination.setNrOfElements(count);
      if (bailBalanceTotle != null && !bailBalanceTotle.equals("")) {
        bailClearInterestTaAF.setBailBalanceTotle(bailBalanceTotle);
      }
      bailClearInterestTaAF.setList(templist);
    }
    // else �û����biz_Type=15�ѽ�Ϣ��
    return bailClearInterestTaAF;
  }

  /**
   * ����֤���Ϣ
   * 
   * @author ��Ұ 2007-10-06 ȫ����Ϣ
   */
  public void bailClearInterestTa(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String operateName = securityInfo.getUserInfo().getUsername();// ����Ա
    String userIp = securityInfo.getUserInfo().getUserIp();// ����ԱIP

    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    BigDecimal loanBankId = new BigDecimal(loanBankName);// PL111.LOAN_BANK_ID

    // �����
    String bizYear = securityInfo.getUserInfo().getPlbizDate().substring(0, 4);// ����������ڵ�
    String bizDate = bizYear + "0630";
    // �ж��б����Ƿ����δ���˵ļ�¼���ò���ԱȨ�����Ƿ����PL202.TYPE=14 and PL202.BIZ_ST!=6�ļ�¼��
    boolean isNoBizSt6 = loanFlowHeadDAO
        .isExistsClearinterestTaNoBizSt6(loanBankName);
    if (isNoBizSt6) {
      // �жϸý�Ϣ������Ƿ���ڽ�Ϣ�ļ�¼���ò���ԱȨ�����Ƿ����PL202.TYPE=15 and
      // PL202.BIZ_ST=6�ļ�¼��BIZ_DATE�����ڻ�����6��30�գ�
      boolean isBizSt6 = loanFlowHeadDAO.isExistsClearinterestTaBisSt6(bizDate,
          loanBankName);
      if (isBizSt6) {
        BigDecimal sumOccurMoney = new BigDecimal(0.00);// PL202�������
        BigDecimal occurMoney = new BigDecimal(0.00);// PL203�������
        String paramExplainInterestD = null;// ��������(��������)
        String paramExplainInterestL = null;// ��������(��������)
        int count = 0;// ʵ������
        // ȡƾ֤��
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankName);
        String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
            .substring(0, 6);
        String docNum = plDocNumMaintainDAO.getDocNumdocNum(collBank
            .getOffice(), bizYearmonth);
        List sumMoneyList = new ArrayList();
        List moneylist = new ArrayList();
        sumMoneyList = loanFlowHeadDAO
            .queryBailClearInterestTaCountByCriterions(loanBankName);
        count = sumMoneyList.size();
        Iterator iterate = sumMoneyList.iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          BailClearInterestTaDTO bailClearInterestTaDTO = new BailClearInterestTaDTO();
          obj = (Object[]) iterate.next();
          if (obj[0] != null && !obj[0].equals(""))
            bailClearInterestTaDTO.setLoanBankName(obj[0].toString());
          if (obj[1] != null && !obj[1].equals("")) {
            bailClearInterestTaDTO.setParamExplainInterestD(obj[1].toString());
            paramExplainInterestD = obj[1].toString();
          }
          if (obj[2] != null && !obj[2].equals("")) {
            bailClearInterestTaDTO.setParamExplainInterestL(obj[2].toString());
            paramExplainInterestL = obj[2].toString();
          }
          if (obj[3] != null && !obj[3].equals(""))
            bailClearInterestTaDTO.setLoanKouAcc(obj[3].toString());
          if (obj[4] != null && !obj[4].equals(""))
            bailClearInterestTaDTO.setContractId(obj[4].toString());
          if (obj[5] != null && !obj[5].equals(""))
            bailClearInterestTaDTO.setBorrowerName(obj[5].toString());
          if (obj[6] != null && !obj[6].equals(""))
            bailClearInterestTaDTO.setCardNum(obj[6].toString());
          if (obj[7] != null && !obj[7].equals(""))
            bailClearInterestTaDTO.setBailBalance(obj[7].toString());
          BigDecimal preIntegral = new BigDecimal(0.00);// �������
          if (obj[8] != null && !obj[8].equals("")) {
            bailClearInterestTaDTO.setPreIntegral(obj[8].toString());
            preIntegral = new BigDecimal(obj[8].toString());// �������
          }
          BigDecimal curIntegral = new BigDecimal(0.00);// �������
          if (obj[9] != null && !obj[9].equals("")) {
            bailClearInterestTaDTO.setCurIntegral(obj[9].toString());
            curIntegral = new BigDecimal(obj[9].toString());// �������
          }
          sumOccurMoney = sumOccurMoney.add((preIntegral
              .multiply(new BigDecimal(paramExplainInterestD)).add(curIntegral
              .multiply(new BigDecimal(paramExplainInterestL)))).divide(
              new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN));
          moneylist.add(bailClearInterestTaDTO);
        }// while
        // ���������ˮ��ͷ��PL202
        LoanFlowHead loanFlowHead = new LoanFlowHead();
        loanFlowHead.setBizDate(bizDate);
        loanFlowHead.setBizType(new Integer(
            BusiConst.PLBUSINESSTYPE_CLEARINTEREST).toString());// ҵ������
        // 15����֤���Ϣ��
        loanFlowHead.setRealCount(new BigDecimal(count));// ʵ������
        loanFlowHead.setOccurMoney(sumOccurMoney);// �������
        loanFlowHead.setDocNum(docNum);
        loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT)
            .toString());// ҵ��״̬
        // 6(����)
        loanFlowHead.setLoanBankId(loanBankId);
        loanFlowHead.setMakePerson(operateName);
        loanFlowHead.setIsFinance(new Integer(1));// PL202�е�isFinance��1
        String flowHeadId = loanFlowHeadDAO.insert(loanFlowHead).toString();// ����PL202
        // ������flow_head_id
        // ����ˮͷ�����Ʊ�ݺ�
        loanFlowHead.setNoteNum(flowHeadId);
        if (moneylist != null) {
          for (Iterator iter = moneylist.iterator(); iter.hasNext();) {
            BailClearInterestTaDTO element = (BailClearInterestTaDTO) iter
                .next();
            String contractId = element.getContractId();// ҳ���ϵ�ÿ����ͬ���
            String loanKouAcc = element.getLoanKouAcc();// ҳ���ϵ�ÿ�������˺�
            BigDecimal bailBalance = new BigDecimal(element.getBailBalance());// ҳ���ϵ�ÿ����Ϣǰ��֤��
            BigDecimal preIntegral = new BigDecimal(element.getPreIntegral());// �ú�ͬ����µ��������
            BigDecimal curIntegral = new BigDecimal(element.getCurIntegral());// �ú�ͬ����µı������
            occurMoney = preIntegral.multiply(
                new BigDecimal(paramExplainInterestD)).add(
                curIntegral.multiply(new BigDecimal(paramExplainInterestL)))
                .divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);
            // ���β��������ˮ��β��PL203
            LoanFlowTail loanFlowTail = new LoanFlowTail();
            loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
            loanFlowTail.setLoanKouAcc(loanKouAcc);
            loanFlowTail.setContractId(contractId);
            loanFlowTail.setOccurMoney(occurMoney);// �ú�ͬ����µģ�PL111.CUR_INTEGRAL*ҳ����ʾ�ı�������+PL111.PRE_INTEGRAL*ҳ����ʾ���������ʣ�/365
            loanFlowTailDAO.insert(loanFlowTail);
            // ���θ���PL111
            preIntegral = bailBalance.add(occurMoney).multiply(
                new BigDecimal(365));// PRE_INTEGRAL=��PL111.BAIL_BALANCE+PL203.OCCUE_MONEY��*365
            curIntegral = new BigDecimal(0);
            bailBalance = bailBalance.add(occurMoney);// BAIL_BALANCE=PL111.BAIL_BALANCE+PL203.OCCUR_MONEY
            BorrowerAcc borrowerAcc = borrowerAccDAO.queryById(contractId);
            borrowerAcc.setPreIntegral(preIntegral);
            borrowerAcc.setCurIntegral(curIntegral);
            borrowerAcc.setBailBalance(bailBalance);
          }
        }
        // ����ҵ����־PL020
        PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
        plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
        plBizActiveLog.setAction(new Integer(
            BusiConst.BUSINESSSTATE_CLEARACCOUNT).toString());// action 6
        plBizActiveLog.setOpTime(new Date());
        plBizActiveLog.setOperator(operateName);
        plBizActiveLog.setType(new Integer(
            BusiConst.PLBUSINESSTYPE_CLEARINTEREST).toString());// type 15
        plBizActiveLogDAO.insert(plBizActiveLog);
        // ���������־ pl021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ���� 2
        plOperateLog.setOpModel(new Integer(
            BusiLogConst.PL_OP_SPECIALBUSS_BAILCLEARINTEREST_DO).toString());// ��֤���Ϣ75
        plOperateLog.setOpButton(new Integer(
            BusiLogConst.BIZLOG_ACTION_INTEREST).toString());// ��Ϣ 12
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId));// PL202.FLOW_HEAD_ID
        plOperateLog.setOpIp(userIp);
        // ����ĺ�ͬ���û��
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(operateName);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        throw new BusinessException("�������ظ��ύ");
      }
    } else {
      throw new BusinessException("����δ���˵ı�֤��ҵ�񣬲����Խ�Ϣ");
    }
  }

  /**
   * ��֤���Ϣά��
   * 
   * @author ��Ұ 2007-10-08 ��ѯ�б���Ϣ
   */
  public BailClearInterestTbAF queryBailClearInterestTbListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    // ��ȡȨ���е�����
    List loanBankIDList = new ArrayList();
    List bankList = securityInfo.getDkUserBankList();
    Userslogincollbank userslogincollbank = null;
    Iterator bank = bankList.iterator();
    while (bank.hasNext()) {
      userslogincollbank = (Userslogincollbank) bank.next();
      loanBankIDList.add(userslogincollbank.getCollBankId().toString());
    }
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String bizYear = (String) pagination.getQueryCriterions().get("bizYear");// ��Ϣ���
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String findType = (String) pagination.getQueryCriterions().get("findType");
    BigDecimal firstBalanceTotle = new BigDecimal(0.00);// ��Ϣǰ��֤��-�ܶ�
    BigDecimal occurMoneyTotle = new BigDecimal(0.00);// ��Ϣ��Ϣ-�ܶ�
    BigDecimal lastBalanceTotle = new BigDecimal(0.00);// ��Ϣ��֤��-�ܶ�
    BailClearInterestTbAF bailClearInterestTbAF = new BailClearInterestTbAF();
    // �����
    String bizYearDefault = securityInfo.getUserInfo().getPlbizDate()
        .substring(0, 4);
    // �б���Ĭ����ʾ����Ľ�Ϣ��¼
    String bizDate = null;
    if (findType != null && !findType.equals("")) {
      if (bizYear != null && !bizYear.equals("")) {
        bizDate = bizYear + "0630";
      }
    } else {
      if (bizYear != null && !bizYear.equals("")) {
        bizDate = bizYear + "0630";
      } else {
        bizDate = bizYearDefault + "0630";
      }
    }
    List bailclearinterestTbList = new ArrayList();
    List templist = new ArrayList();
    List printList = new ArrayList();
    bailclearinterestTbList = loanFlowHeadDAO
        .queryBailClearInterestTbListByCriterions(loanKouAcc, borrowerName,
            bizDate, loanBankName, loanBankIDList, start, orderBy, order,
            pageSize, page);
    if (bailclearinterestTbList.size() > 0) {
      List countList = new ArrayList();
      countList = loanFlowHeadDAO.queryBailClearInterestTbCountByCriterions(
          loanKouAcc, borrowerName, bizDate, loanBankName, loanBankIDList);
      count = countList.size();
      Iterator iterate = bailclearinterestTbList.iterator();
      Object[] obj = null;
      while (iterate.hasNext()) {
        BailClearInterestTbDTO bailClearInterestTbDTO = new BailClearInterestTbDTO();
        obj = (Object[]) iterate.next();
        if (obj[0] != null && !obj[0].equals("")) {
          String bizDateTable = obj[0].toString();
          bailClearInterestTbDTO.setBizYear(bizDateTable.substring(0, 4));
        }
        String loanBankId = null;
        if (obj[1] != null && !obj[1].equals(""))
          loanBankId = obj[1].toString();
        if (obj[2] != null && !obj[2].equals(""))
          bailClearInterestTbDTO.setLoanKouAcc(obj[2].toString());
        if (obj[3] != null && !obj[3].equals(""))
          bailClearInterestTbDTO.setBorrowerName(obj[3].toString());
        String bailBalance = null;
        if (obj[4] != null && !obj[4].equals("")) {
          bailBalance = obj[4].toString();
        }
        String occurMoney = null;
        if (obj[5] != null && !obj[5].equals("")) {
          occurMoney = obj[5].toString();
          bailClearInterestTbDTO.setOccurMoney(occurMoney);
        }
        BigDecimal afterYearOccurMoney = new BigDecimal(0.00);// ��Ϣ���֮��Ľ�Ϣ��Ϣ��������Ϣ��ȣ�
        if (obj[6] != null && !obj[6].equals(""))
          afterYearOccurMoney = afterYearOccurMoney.add(new BigDecimal(obj[6]
              .toString()));
        BigDecimal firstBalance = new BigDecimal(0.00);// ��Ϣǰ��֤��
        firstBalance = new BigDecimal(bailBalance).add(afterYearOccurMoney
            .negate());
        bailClearInterestTbDTO.setBailBalance(firstBalance.toString());
        BigDecimal lastBalance = new BigDecimal(0.00);// ��Ϣ��֤��
        lastBalance = firstBalance.add(new BigDecimal(occurMoney));// ��Ϣǰ��֤��+��Ϣ��Ϣ
        bailClearInterestTbDTO.setLastBalance(lastBalance.toString());
        // ת����������
        try {
          CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
              .toString());
          bailClearInterestTbDTO.setLoanBankName(dto.getCollBankName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        templist.add(bailClearInterestTbDTO);
      }
      // ��ӡȫ���б�
      Iterator iter = countList.iterator();
      Object[] object = null;
      while (iter.hasNext()) {
        BailClearInterestTbDTO bailClearInterestTbDTO = new BailClearInterestTbDTO();
        object = (Object[]) iter.next();
        if (object[0] != null && !object[0].equals("")) {
          String bizDateTable = object[0].toString();
          bailClearInterestTbDTO.setBizYear(bizDateTable.substring(0, 4));
        }
        String loanBankId = null;
        if (object[1] != null && !object[1].equals(""))
          loanBankId = object[1].toString();
        if (object[2] != null && !object[2].equals(""))
          bailClearInterestTbDTO.setLoanKouAcc(object[2].toString());
        if (object[3] != null && !object[3].equals(""))
          bailClearInterestTbDTO.setBorrowerName(object[3].toString());
        String bailBalance = null;
        if (object[4] != null && !object[4].equals("")) {
          bailBalance = object[4].toString();
        }
        String occurMoney = null;
        if (object[5] != null && !object[5].equals("")) {
          occurMoney = object[5].toString();
          bailClearInterestTbDTO.setOccurMoney(occurMoney);
        }
        BigDecimal afterYearOccurMoney = new BigDecimal(0.00);// ��Ϣ���֮��Ľ�Ϣ��Ϣ��������Ϣ��ȣ�
        if (object[6] != null && !object[6].equals(""))
          afterYearOccurMoney = afterYearOccurMoney.add(new BigDecimal(
              object[6].toString()));
        BigDecimal firstBalance = new BigDecimal(0.00);// ��Ϣǰ��֤��
        firstBalance = new BigDecimal(bailBalance).add(afterYearOccurMoney
            .negate());
        bailClearInterestTbDTO.setBailBalance(firstBalance.toString());
        BigDecimal lastBalance = new BigDecimal(0.00);// ��Ϣ��֤��
        lastBalance = firstBalance.add(new BigDecimal(occurMoney));// ��Ϣǰ��֤��+��Ϣ��Ϣ
        bailClearInterestTbDTO.setLastBalance(lastBalance.toString());
        // �ϼ�
        firstBalanceTotle = firstBalanceTotle.add(firstBalance);// ��Ϣǰ��֤��-�ܶ�
        occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(occurMoney));// ��Ϣ��Ϣ-�ܶ�
        lastBalanceTotle = lastBalanceTotle.add(lastBalance);// ��Ϣ��֤��-�ܶ�
        // ת����������
        try {
          CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
              .toString());
          bailClearInterestTbDTO.setLoanBankName(dto.getCollBankName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        printList.add(bailClearInterestTbDTO);
      }
    }
    pagination.setNrOfElements(count);
    if (firstBalanceTotle != null && !firstBalanceTotle.equals("")) {
      bailClearInterestTbAF.setBailBalanceTotle(firstBalanceTotle);
    }
    if (occurMoneyTotle != null && !occurMoneyTotle.equals("")) {
      bailClearInterestTbAF.setOccurMoneyTotle(occurMoneyTotle);
    }
    if (lastBalanceTotle != null && !lastBalanceTotle.equals("")) {
      bailClearInterestTbAF.setLastBalanceTotle(lastBalanceTotle);
    }
    bailClearInterestTbAF.setList(templist);
    bailClearInterestTbAF.setPrintList(printList);
    return bailClearInterestTbAF;
  }
}
