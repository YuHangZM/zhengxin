package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopFindDTO;

import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopListDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaSaveDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbListDTO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
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
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class AdjustAccountBS implements IAdjustAccountBS {
  // PL202��DAO
  protected LoanFlowHeadDAO loanFlowHeadDAO = null;

  // PL203��DAO
  protected LoanFlowTailDAO loanFlowTailDAO = null;

  // ƾ֤��
  protected PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  // PL111��DAO
  protected BorrowerAccDAO borrowerAccDAO = null;

  // PL020��DAO
  protected PlBizActiveLogDAO plBizActiveLogDAO = null;

  // PL021��DAO
  protected PlOperateLogDAO plOperateLogDAO = null;

  // ����ƾ֤��
  protected PlDocNumCancelDAO plDocNumCancelDAO = null;

  // PL201��DAO
  protected RestoreLoanDAO restoreLoanDAO = null;

  protected CollParaDAO collParaDAO = null;

  protected CollBankDAO collBankDAO = null;

  protected LoanBankDAO loanBankDAO = null;

  protected SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public List findAdjustAccountPopList(Pagination pagination, List loanbankList)
      throws Exception {
    // TODO Auto-generated method stub
    AdjustAccountPopFindDTO adjustAccountPopFindDTO = null;
    if (pagination.getQueryCriterions().get("adjustAccountPopFindDTO") == null) {
      adjustAccountPopFindDTO = new AdjustAccountPopFindDTO();
    } else {
      adjustAccountPopFindDTO = (AdjustAccountPopFindDTO) pagination
          .getQueryCriterions().get("adjustAccountPopFindDTO");
    }

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List adjustAccountPopListDTOList = loanFlowHeadDAO
        .queryAdjustAccountPopList(adjustAccountPopFindDTO, orderBy, order,
            start, pageSize, loanbankList);

    for (int i = 0; i < adjustAccountPopListDTOList.size(); i++) {
      AdjustAccountPopListDTO adjustAccountPopListDTO = (AdjustAccountPopListDTO) adjustAccountPopListDTOList
          .get(i);
      // ��ʱ��ŷ��������ݲ�ͬ״̬���
      BigDecimal temp_Money = adjustAccountPopListDTO.getOccurMoney();

      // �ж��Ƿ��з��Ž��
      if (!adjustAccountPopListDTO.getBizType().equals("1")) {
        adjustAccountPopListDTO.setOccurMoney(new BigDecimal(0.00));
      }
      // �ж��Ƿ��й��˷������
      if (adjustAccountPopListDTO.getBizType().equals("2")
          || adjustAccountPopListDTO.getBizType().equals("5")) {
        adjustAccountPopListDTO.setOverPay(temp_Money);
      } else {
        adjustAccountPopListDTO.setOverPay(new BigDecimal(0.00));
      }

      // ת��ҵ������
      adjustAccountPopListDTO.setBizType(BusiTools.getBusiValue(Integer
          .parseInt(adjustAccountPopListDTO.getBizType()),
          BusiConst.PLBUSINESSTYPE));
      // ����ʵ�ս��
      adjustAccountPopListDTO.setFactPay(adjustAccountPopListDTO.getOverPay()
          .add(adjustAccountPopListDTO.getLoanBackPay()));

    }

    int count = loanFlowHeadDAO.queryAdjustAccountPopCount(
        adjustAccountPopFindDTO, orderBy, order, start, pageSize, loanbankList);

    pagination.setNrOfElements(count);

    return adjustAccountPopListDTOList;
  }

  public AdjustAccountTaInfoDTO judgeLoanFlowHead(String flowHeadId,
      List loanbankList, SecurityInfo securityInfo) throws Exception,
      BusinessException {

    AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = null;

    // ����PL202����������ҵ��״̬
    Object[] obj = loanFlowHeadDAO.queryBizTypeByFlowHeadId(flowHeadId);
    String bizType = obj[0].toString();

    String bankDate = "";// ��������
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    bankDate = loanBankDAO.queryPL002BizDate_jj(obj[2].toString());
    if (!bizDate.equals(bankDate)) {
      throw new BusinessException("��¼����������ҵ�����ڲ�һ�£�������ҵ��");
    }
    if (obj[1].toString().equals("1")) {
      throw new BusinessException("�ñ�ҵ�����������˵������������ٵ���!");
    }

    // ���ʻ���ҵ���ɴ��ж�
    if (bizType.equals("2") || bizType.equals("6") || bizType.equals("7")) {
      String maxflowHeadId = loanFlowTailDAO.queryMaxFlowHeadId(flowHeadId);
      LoanFlowHead head = loanFlowHeadDAO.queryById(Integer
          .valueOf(maxflowHeadId));
      // �������Ҫ������ҵ�������һ�����׳��쳣
      if (flowHeadId.equals(maxflowHeadId)) {
        // ״̬��֤�ɹ����Խ��е���ҵ��
        adjustAccountTaInfoDTO = loanFlowHeadDAO
            .queryAdjustAccountSingle(flowHeadId);
        adjustAccountTaInfoDTO.setStrBizType(BusiTools.getBusiValue(Integer
            .parseInt(adjustAccountTaInfoDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      } else {
        throw new BusinessException("�ñ�ҵ�������һ�ʻ���ҵ�񣬲����Ե���!\\r���һ��ҵ���ƾ֤����"
            + head.getDocNum() + " ҵ��������" + head.getBizDate());
      }
    }

    // ���������ɴ��ж�
    if (bizType.equals("5")) {
      // ״̬������������ͷ��id���ֵ
      String maxflowHeadId = loanFlowHeadDAO.queryMaxFlowHeadId(new Integer(
          obj[2].toString()));
      LoanFlowHead head = loanFlowHeadDAO.queryById(Integer
          .valueOf(maxflowHeadId));
      if (flowHeadId.equals(maxflowHeadId)) {
        List list = loanFlowTailDAO.queryIsNotMaxFlowHeadId(flowHeadId,
            loanbankList);
        // �жϸñ���������Ƿ������һ��
        if (list.size() > 0) {
          throw new BusinessException("�ñ�ҵ����ĳЩ�˲������һ�ʻ��գ������Ե���!");
        } else {
          // ״̬��֤�ɹ����Խ��е���ҵ��
          adjustAccountTaInfoDTO = loanFlowHeadDAO
              .queryAdjustAccountBatch(flowHeadId);
          adjustAccountTaInfoDTO.setStrBizType(BusiTools.getBusiValue(Integer
              .parseInt(adjustAccountTaInfoDTO.getBizType()),
              BusiConst.PLBUSINESSTYPE));
        }

      } else {
        throw new BusinessException("�ñ�ҵ���Ǹ÷ſ������µ����һ����������ҵ�񣬲����Ե���!\\r"
            + "���һ��ҵ���ƾ֤����" + head.getDocNum() + " ҵ��������" + head.getBizDate());
      }
    }

    // ����ҵ���ɴ��ж�
    if (bizType.equals("1")) {
      // �жϸ��ʺ��Ƿ������������뱣֤�������ҵ��,��i=0ʱ��û����������ҵ��
      int i = loanFlowTailDAO.queryIsExistFlowHeadId(flowHeadId);
      if (i == 0) {
        // ״̬��֤�ɹ����Խ��е���ҵ��
        adjustAccountTaInfoDTO = loanFlowHeadDAO
            .queryAdjustAccountPutout(flowHeadId);
        adjustAccountTaInfoDTO.setStrBizType(BusiTools.getBusiValue(Integer
            .parseInt(adjustAccountTaInfoDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      } else {
        throw new BusinessException("�ñʷſ�ҵ���������գ������Ե���!");
      }
    }

    return adjustAccountTaInfoDTO;
  }

  public AdjustAccountTaInfoDTO judgeLoanKouAcc(String loanKouAcc,
      List loanbankList, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = new AdjustAccountTaInfoDTO();
    String borrowerName = loanFlowTailDAO.queryBorrowerNameByLoanKouAcc(
        loanKouAcc, loanbankList);
    String bankDate = "";// ��������
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// �������
    String bankId = loanFlowTailDAO.queryBorrowerBankIdByLoanKouAcc(loanKouAcc,
        loanbankList);
    if (borrowerName == null) {
      throw new BusinessException("������Ĵ����ʺſ��ܲ�����!");
    } else {
      bankDate = loanBankDAO.queryPL002BizDate_jj(bankId);
      if (!bizDate.equals(bankDate)) {
        throw new BusinessException("��¼����������ҵ�����ڲ�һ�£�������ҵ��");
      }
      adjustAccountTaInfoDTO.setBorrowerName(borrowerName);
    }
    return adjustAccountTaInfoDTO;
  }

  public String saveAdjustAccountInfo(String flowHeadId, String loanKouAcc,
      String bizType, String autoOverPay, SecurityInfo securityInfo,
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO) throws Exception {

    Serializable new_flowHeadId = null;
    String contractId = "";
    // ��ѯ����Ҫ������������Ϣ,���Ҫ���������������ѯ����Ӧβ�����Ϣ
    if (bizType != null && !bizType.equals("")) {
      /** -------------��������------------ */
      if (bizType.equals("5")) {
        /** �������� */
        AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = loanFlowHeadDAO
            .queryAdjustAccountInfoBatch(flowHeadId);
        // ����PL202
        String[] str = saveLoanFlowHeadInfo(autoOverPay, securityInfo,
            adjustAccountTaSaveDTO);
        // ��ѯ��ӦPL203�ļ�¼
        List list = loanFlowTailDAO.queryLoanFlowTailByFlowHeadId(flowHeadId);
        // ѭ������PL203
        for (int i = 0; i < list.size(); i++) {
          AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = (AdjustAccountTaSaveDTO) list
              .get(i);
          // ��ѯ�Ƿ�Ϊ���һ�λ���
          Object obj = loanFlowTailDAO
              .queryLoanMoney(temp_adjustAccountTaSaveDTO.getContractId());
          if (new BigDecimal(obj.toString()).intValue() == 0) {
            loanFlowTailDAO.updateBorrowerAcc(temp_adjustAccountTaSaveDTO
                .getContractId());
            loanFlowTailDAO.updateCongealInfo(temp_adjustAccountTaSaveDTO
                .getContractId());
          }
          saveLoanFlowTailInfo(autoOverPay, temp_adjustAccountTaSaveDTO, str[0]);
        }
        new_flowHeadId = str[0];
        contractId = str[0];
      } else if (bizType.equals("2") || bizType.equals("6")
          || bizType.equals("7")) {
        /** ���ʻ��� */
        AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = loanFlowHeadDAO
            .queryAdjustAccountInfoSingleHead(flowHeadId);
        // ����PL202
        String[] str = saveLoanFlowHeadInfo(autoOverPay, securityInfo,
            adjustAccountTaSaveDTO);
        // ��ѯ��ӦPL203�ļ�¼
        List list = loanFlowTailDAO.queryLoanFlowTailByFlowHeadId(flowHeadId);
        String temp_contractId = ((AdjustAccountTaSaveDTO) list.get(0))
            .getContractId();
        // ��ѯ�Ƿ�Ϊ���һ�λ���
        Object obj = loanFlowTailDAO.queryLoanMoney(temp_contractId);
        if (new BigDecimal(obj.toString()).intValue() == 0) {
          loanFlowTailDAO.updateBorrowerAcc(temp_contractId);
          loanFlowTailDAO.updateCongealInfo(temp_contractId);
        }
        // ѭ������PL203
        for (int i = 0; i < list.size(); i++) {
          AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = (AdjustAccountTaSaveDTO) list
              .get(i);
          saveLoanFlowTailInfo(autoOverPay, temp_adjustAccountTaSaveDTO, str[0]);
        }
        // ����PL203
        // saveLoanFlowTailInfo(autoOverPay, adjustAccountTaSaveDTO, str[0]);
        // �õ�����PL201�ĺ�ͬ���
        contractId = adjustAccountTaSaveDTO.getContractId();
        new_flowHeadId = str[0];
      } else if (bizType.equals("1")) {
        /** ����ҵ�� */
        AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = loanFlowHeadDAO
            .queryAdjustAccountInfoSingle(flowHeadId);
        // OCCUR_MONEY = ҳ���ϵķ��Ž��
        adjustAccountTaSaveDTO.setOccurMoney(adjustAccountTaInfoDTO
            .getPutOutMoney());
        // ����PL202
        String[] str = saveLoanFlowHeadInfo(autoOverPay, securityInfo,
            adjustAccountTaSaveDTO);
        // ����PL203
        saveLoanFlowTailInfo(autoOverPay, adjustAccountTaSaveDTO, str[0]);
        // �õ�����PL201�ĺ�ͬ���
        contractId = adjustAccountTaSaveDTO.getContractId();
        new_flowHeadId = str[0];
      }
      // ��������ҵ���޸ĳ����������ʵ���ҵ��״̬
      loanFlowHeadDAO.updateAdjustAccountIs_adjust(flowHeadId, "1");
    } else {
      /** -------------���ʵ���------------ */
      // ����PL202��
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());
      loanFlowHead.setBizType(String
          .valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG));
      loanFlowHead.setRealCount(new BigDecimal(1));
      loanFlowHead.setRealCorpus(adjustAccountTaInfoDTO.getCallbackMoney());
      loanFlowHead
          .setRealInterest(adjustAccountTaInfoDTO.getCallbackInterest());
      loanFlowHead.setRealPunishInterest(adjustAccountTaInfoDTO
          .getPunishInterest());
      loanFlowHead.setOccurMoney(new BigDecimal(0.00));
      loanFlowHead.setIsFinance(new Integer(1));

      Object[] obj = borrowerAccDAO
          .queryLoanBankIdByLoanKouAcc(adjustAccountTaInfoDTO.getLoanKouAcc());

      // �õ�����PL201�ĺ�ͬ���
      contractId = obj[1].toString();
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = loanFlowHeadDAO.queryOfficeByBank(obj[0].toString());
      } else {
        officeId = obj[0].toString();
      }
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(officeId,
          securityInfo.getUserInfo().getPlbizDate().substring(0, 6));
      Map office = securityInfo.getOfficeInnerCodeMap();
      String officecode = office.get(officeId).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      String loanbankid = obj[0].toString();
      docNum = securityInfo.getUserInfo().getPlbizDate().substring(0, 4)
          + officecode
          + (loanbankid.length() < 2 ? "0" + loanbankid : loanbankid) + docNum;
      loanFlowHead.setLoanBankId(new BigDecimal(obj[0].toString()));
      loanFlowHead.setDocNum(docNum);
      loanFlowHead.setMakePerson(securityInfo.getUserName());
      loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      new_flowHeadId = loanFlowHeadDAO.insert(loanFlowHead);

      // ����PL203
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(new_flowHeadId.toString()));
      loanFlowTail.setLoanKouAcc(adjustAccountTaInfoDTO.getLoanKouAcc());
      loanFlowTail.setContractId(obj[1].toString());
      loanFlowTail.setRealCorpus(adjustAccountTaInfoDTO.getCallbackMoney());
      loanFlowTail
          .setRealInterest(adjustAccountTaInfoDTO.getCallbackInterest());
      loanFlowTail.setRealPunishInterest(adjustAccountTaInfoDTO
          .getPunishInterest());
      loanFlowTail.setOccurMoney(new BigDecimal(0.00));
      loanFlowTail.setYearMonth(adjustAccountTaInfoDTO.getYearMonth());

      loanFlowTailDAO.insert(loanFlowTail);

    }
    // ����PL020
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setBizid(new BigDecimal(new_flowHeadId.toString()));
    plBizActiveLog.setAction(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setType(String
        .valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG));

    plBizActiveLogDAO.insert(plBizActiveLog);

    // ����PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO));
    plOperateLog
        .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_CONFIRM));
    plOperateLog.setOpBizId(new BigDecimal(new_flowHeadId.toString()));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);
    return new_flowHeadId.toString();

  }

  /**
   * ����PL202�ķ���
   * 
   * @param autoOverPay �Ƿ��Զ�����
   * @param securityInfo Ȩ��
   * @param adjustAccountTaSaveDTO ������ҵ�����Ϣ
   * @return obj[0] PL202������,obj[1]�����ɵ�ƾ֤��
   * @throws Exception
   * @throws BusinessException
   */
  private String[] saveLoanFlowHeadInfo(String autoOverPay,
      SecurityInfo securityInfo, AdjustAccountTaSaveDTO adjustAccountTaSaveDTO)
      throws Exception, BusinessException {
    String[] str = new String[2];
    // String office = loanFlowHeadDAO.queryOfficeByBank(adjustAccountTaSaveDTO
    // .getLoanBankId());
    String officeId = "";
    String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
    if (loanDocNumDocument.equals("1")) {
      officeId = loanFlowHeadDAO.queryOfficeByBank(adjustAccountTaSaveDTO
          .getLoanBankId());
    } else {
      officeId = adjustAccountTaSaveDTO.getLoanBankId().toString();
    }
    String bizYearmonth = securityInfo.getUserInfo().getPlbizDate().substring(
        0, 6);
    String loanbankid = adjustAccountTaSaveDTO.getLoanBankId();
    String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanbankid,
        bizYearmonth.substring(0, 4));
    Map office = securityInfo.getOfficeInnerCodeMap();
    String officecode = office.get(officeId).toString();
    if (officecode.length() == 1) {
      officecode = "0" + officecode;
    }
    docNum = bizYearmonth.substring(0, 4) + officecode + "0" + loanbankid
        + docNum;
    // ����PL202��
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());
    loanFlowHead.setBizType(String
        .valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG));

    loanFlowHead.setShouldCount(adjustAccountTaSaveDTO.getShouldCount());
    loanFlowHead.setRealCount(adjustAccountTaSaveDTO.getRealCount());

    loanFlowHead.setShouldCorpus(adjustAccountTaSaveDTO.getShouldCorpus());
    loanFlowHead.setShouldInterest(adjustAccountTaSaveDTO.getShouldInterest());
    loanFlowHead.setShouldOverdueCorpus(adjustAccountTaSaveDTO
        .getShouldOverdueCorpus());
    loanFlowHead.setShouldOverdueInterest(adjustAccountTaSaveDTO
        .getShouldOverdueInterest());
    loanFlowHead.setShouldPunishInterest(adjustAccountTaSaveDTO
        .getShouldPunishInterest());

    loanFlowHead.setNoteNum(adjustAccountTaSaveDTO.getFlowHeadId());
    loanFlowHead.setRealCorpus(adjustAccountTaSaveDTO.getRealCorpus().multiply(
        new BigDecimal(-1)));
    loanFlowHead.setRealInterest(adjustAccountTaSaveDTO.getRealInterest()
        .multiply(new BigDecimal(-1)));
    loanFlowHead.setRealOverdueCorpus(adjustAccountTaSaveDTO
        .getRealOverdueCorpus().multiply(new BigDecimal(-1)));
    loanFlowHead.setRealOverdueInterest(adjustAccountTaSaveDTO
        .getRealOverdueInterest().multiply(new BigDecimal(-1)));
    loanFlowHead.setRealPunishInterest(adjustAccountTaSaveDTO
        .getRealPunishInterest().multiply(new BigDecimal(-1)));
    loanFlowHead.setIsFinance(new Integer(1));
    // ���Ϊ����״̬�����Ž��Ϊҳ������Ľ��
    if (adjustAccountTaSaveDTO.getBizType().equals("1")) {
      loanFlowHead.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoney());
    } else {
      // �ж��Ƿ��ƶ�����
      if (autoOverPay == null || autoOverPay.equals("")
          || autoOverPay.equals("1")) {
        loanFlowHead.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoney()
            .multiply(new BigDecimal(-1)));
      } else {
        loanFlowHead.setOccurMoney(adjustAccountTaSaveDTO.getRealCorpus().add(
            adjustAccountTaSaveDTO.getRealInterest()).add(
            adjustAccountTaSaveDTO.getRealOverdueCorpus().add(
                adjustAccountTaSaveDTO.getRealOverdueInterest().add(
                    adjustAccountTaSaveDTO.getRealPunishInterest()))));
      }
    }
    loanFlowHead.setDocNum(docNum);
    loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
    loanFlowHead.setLoanBankId(new BigDecimal(adjustAccountTaSaveDTO
        .getLoanBankId()));
    loanFlowHead.setWrongFlowNum(new BigDecimal(adjustAccountTaSaveDTO
        .getFlowHeadId()));
    loanFlowHead.setWrongBizType(adjustAccountTaSaveDTO.getBizType());
    loanFlowHead.setMakePerson(securityInfo.getUserName());

    Serializable temp_flowHeadId = loanFlowHeadDAO.insert(loanFlowHead);
    // ����Ʊ�ݱ��
    // ϵͳ�Զ����ɽ���ţ�ҵ������+��ˮ��
    String noteNum = "";
    String bizDate = securityInfo.getUserInfo().getBizDate();
    noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
    loanFlowHead.setNoteNum(noteNum);// ����ˮ�Ų嵽Ʊ�ݱ����
    str[0] = temp_flowHeadId.toString();
    str[1] = docNum;
    return str;
  }

  /**
   * ����PL203�ķ���
   * 
   * @param autoOverPay �Ƿ��Զ�����
   * @param adjustAccountTaSaveDTO ������ҵ�����Ϣ
   * @param temp_flowHeadId ͷ�������
   */
  private void saveLoanFlowTailInfo(String autoOverPay,
      AdjustAccountTaSaveDTO adjustAccountTaSaveDTO,
      Serializable temp_flowHeadId) {
    LoanFlowTail loanFlowTail = new LoanFlowTail();

    loanFlowTail.setFlowHeadId(new BigDecimal(temp_flowHeadId.toString()));
    loanFlowTail.setContractId(adjustAccountTaSaveDTO.getContractId());
    loanFlowTail.setLoanKouAcc(adjustAccountTaSaveDTO.getLoanKouAcc());
    loanFlowTail.setYearMonth(adjustAccountTaSaveDTO.getYearMonth());

    loanFlowTail.setShouldCorpus(adjustAccountTaSaveDTO.getShouldCorpusTail());
    loanFlowTail.setShouldInterest(adjustAccountTaSaveDTO
        .getShouldInterestTail());
    loanFlowTail.setShouldPunishInterest(adjustAccountTaSaveDTO
        .getShouldPunishInterestTail());

    loanFlowTail.setRealCorpus(adjustAccountTaSaveDTO.getRealCorpusTail()
        .multiply(new BigDecimal(-1)));
    loanFlowTail.setRealInterest(adjustAccountTaSaveDTO.getRealInterestTail()
        .multiply(new BigDecimal(-1)));
    loanFlowTail.setRealPunishInterest(adjustAccountTaSaveDTO
        .getRealPunishInterestTail().multiply(new BigDecimal(-1)));
    loanFlowTail.setLoanType(adjustAccountTaSaveDTO.getLoanType());
    loanFlowTail.setTempPunishInterest(adjustAccountTaSaveDTO
        .getTempPunishInterest().add(
            adjustAccountTaSaveDTO.getRealPunishInterestTail()));
    // ���Ϊ����״̬�����Ž��Ϊҳ������Ľ��
    if (adjustAccountTaSaveDTO.getBizType().equals("1")) {
      loanFlowTail.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoney());
    } else {
      // �ж��Ƿ��ƶ�����
      if (autoOverPay == null || autoOverPay.equals("")
          || autoOverPay.equals("1")) {
        loanFlowTail.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoneyTail()
            .multiply(new BigDecimal(-1)));
      } else {
        loanFlowTail.setOccurMoney(adjustAccountTaSaveDTO.getRealCorpusTail()
            .add(
                adjustAccountTaSaveDTO.getRealInterestTail().add(
                    adjustAccountTaSaveDTO.getRealPunishInterestTail())));
      }
    }
    loanFlowTailDAO.insert(loanFlowTail);
  }

  public Object[] findAdjustAccountList(Pagination pagination, List loanbankList)
      throws Exception {
    Object[] temp_array = new Object[5];
    List list = null;
    AdjustAccountTbFindDTO adjustAccountTbFindDTO = (AdjustAccountTbFindDTO) pagination
        .getQueryCriterions().get("adjustAccountTbFindDTO");
    String type = "";
    if (pagination.getQueryCriterions().get("type") != null) {
      type = (String) pagination.getQueryCriterions().get("type");
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    if (adjustAccountTbFindDTO == null) {
      adjustAccountTbFindDTO = new AdjustAccountTbFindDTO();
    }

    list = loanFlowHeadDAO.queryAdjustAccountList(adjustAccountTbFindDTO, type,
        orderBy, order, start, pageSize, loanbankList, page);

    for (int i = 0; i < list.size(); i++) {
      AdjustAccountTbListDTO adjustAccountTbListDTO = (AdjustAccountTbListDTO) list
          .get(i);
      String bizType = adjustAccountTbListDTO.getBizType();
      if (!bizType.equals("1")) {
        adjustAccountTbListDTO.setOccurMoney(new BigDecimal(0.00));
      }
      // ת��ҵ������
      if (adjustAccountTbListDTO.getBizType() == null
          || adjustAccountTbListDTO.getBizType().equals("")) {
        adjustAccountTbListDTO.setBizType("");
      } else {
        adjustAccountTbListDTO.setBizTypeStr(BusiTools.getBusiValue(Integer
            .parseInt(adjustAccountTbListDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      }

    }

    BigDecimal sumOccurMoney = new BigDecimal(0.00);
    BigDecimal sumCallbackCorpus = new BigDecimal(0.00);
    BigDecimal sumCallbackInterest = new BigDecimal(0.00);
    BigDecimal sumPunishInterest = new BigDecimal(0.00);

    // ����ϼ���count
    List countList = loanFlowHeadDAO.queryAdjustAccountCount(
        adjustAccountTbFindDTO, type, loanbankList);
    for (int i = 0; i < countList.size(); i++) {
      AdjustAccountTbListDTO adjustAccountTbListDTO = (AdjustAccountTbListDTO) countList
          .get(i);
      if (adjustAccountTbListDTO.getBizType().equals("1")) {
        sumOccurMoney = sumOccurMoney.add(adjustAccountTbListDTO
            .getOccurMoney());
      }
      sumCallbackCorpus = sumCallbackCorpus.add(adjustAccountTbListDTO
          .getCallbackCorpus());
      sumCallbackInterest = sumCallbackInterest.add(adjustAccountTbListDTO
          .getCallbackInterest());
      sumPunishInterest = sumPunishInterest.add(adjustAccountTbListDTO
          .getPunishInterest());

    }
    pagination.setNrOfElements(countList.size());

    // ����ѯ�Ľ����װ������
    temp_array[0] = list;
    temp_array[1] = sumOccurMoney;
    temp_array[2] = sumCallbackCorpus;
    temp_array[3] = sumCallbackInterest;
    temp_array[4] = sumPunishInterest;

    return temp_array;
  }

  public void deleteAdjustAccountInfo(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    Object[] obj = loanFlowHeadDAO.queryBizStAndWrongBizType(flowHeadId);
    // �жϱ�ɾ����ҵ��״̬
    if (obj == null) {
      throw new BusinessException("����ɾ���Ĵ��˵���ҵ�񲻴��ڣ�");
    } else if (!obj[0].toString().equals("4")) {
      throw new BusinessException("�ñ�ҵ���״̬����ȷ�ϣ�������ɾ����");
    }
    // �ж��������������ǵ��ʵ���
    if (obj[1] != null && !obj[1].toString().equals("")) {
      if (obj[2].toString() != null) {
        // ���������ҵ������±�����ҵ��Is_adjust״̬
        loanFlowHeadDAO.updateAdjustAccountIs_adjust(obj[2].toString(), "");
        if (obj[1].toString().equals("2") || obj[1].toString().equals("5")) {
          try {
            loanFlowTailDAO.updateCongealInfoInDelete(flowHeadId);
          } catch (BusinessException e) {
            throw new BusinessException("ɾ��ʧ�ܣ�");
          }

        }
      }
    }

    // ����Ҫɾ����ˮͷβ��
    List temp_FlowHeadInfo = new ArrayList();
    temp_FlowHeadInfo = loanFlowTailDAO.queryLoanFlowHeadInfo(flowHeadId);
    Object[] info = (Object[]) temp_FlowHeadInfo.get(0);
    // ������ƾ֤��
    String cancelcredenceid = info[1] + "";
    // ƾ֤����������
    String yearMonth = info[2] + "";
    // ���´�
    String officeCode = info[3] + "";
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
    System.out.println(cancelcredenceid);
    plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid.substring(8, 12),
        cancelcredenceid.substring(7, 8), cancelcredenceid.substring(0, 4));
    // ɾ��β������
    loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);
    // ɾ��ͷ������
    loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
    // ɾ��PL020
    plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_FYF(flowHeadId, "12");

    // ����PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog
        .setOpModel(String
            .valueOf(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN));
    plOperateLog
        .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_CONFIRM));
    plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
    plOperateLog.setContractId(flowHeadId);
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  public AdjustAccountTbListDTO findPrintInfo(String flowHeadId)
      throws Exception {

    AdjustAccountTbListDTO adjustAccountTbListDTO = loanFlowHeadDAO
        .queryAdjustAccountPrint(flowHeadId);

    // ת��ҵ������
    adjustAccountTbListDTO.setBizType(BusiTools.getBusiValue(Integer
        .parseInt(adjustAccountTbListDTO.getBizType()),
        BusiConst.PLBUSINESSTYPE));

    return adjustAccountTbListDTO;
  }

  public Object[] findCorpusAndInterest(String yearMonth, String loanKouAcc,
      int plLoanReturnType) throws Exception, BusinessException {
    Object[] obj = null;
    if (plLoanReturnType == 1) {
      // ���������Ϊ����ȥ��ѯpl201
      obj = restoreLoanDAO.queryCorpusAndInterest(yearMonth, loanKouAcc);
    } else {
      // �����ѯpl203
      obj = restoreLoanDAO.queryCorpusAndInterest_bank(yearMonth, loanKouAcc);
    }

    if (obj == null) {
      throw new BusinessException("����������д����!");
    }
    return obj;
  }

  public LoancallbackTaAF findPrintCallbackInfo(String headId) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    List taillist = loanFlowTailDAO.queryPrintLoanFlowTailByHeadId_FYF(headId);
    String contractId = "";
    BorrowerInfoDTO borrowerInfoDTO = null;
    String yearMonth = "";
    List borrowerList = null;
    if (!taillist.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) taillist.get(0);
      ShouldBackListDTO dto1 = (ShouldBackListDTO) taillist
          .get(taillist.size() - 1);
      contractId = dto.getContractId();
      yearMonth = dto1.getLoanKouYearmonth();
      // ��PL110��PL111ȡ��Ϣ
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
    Object obj[] = null;
    while (it.hasNext()) {
      obj = (Object[]) it.next();
      if (obj[0] != null) {
        af.setLoanAcc(obj[0].toString());
      }
      if (obj[1] != null) {
        af.setInterestAcc(obj[1].toString());
      }
    }
    af.setRealCorpus(loanFlowHead.getRealCorpus());
    af.setRealOverduCorpus(loanFlowHead.getRealOverdueCorpus());
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(
        loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(
        loanFlowHead.getRealOverdueInterest()).add(
        loanFlowHead.getRealPunishInterest()));
    af.setOverOccurMoney(loanFlowHead.getOccurMoney());
    af.setInterest(loanFlowHead.getRealInterest());
    af.setOverdueInterest(loanFlowHead.getRealOverdueInterest());
    af.setPunishInterest(loanFlowHead.getRealPunishInterest());
    af.setMakeOP(securityDAO.queryByUserid(loanFlowHead.getMakePerson()));
    af.setClearOP(loanFlowHead.getCheckPerson());
    af.setBankName(collBank.getCollBankName());
    af.setShouldBackList(taillist);
    String bizType = loanFlowHead.getBizType();
    if (bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER))) {
      af.setBizType("�������");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "��"
          + yearMonth.substring(4, 6) + "��");
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))) {
      af.setBizType("������ǰ���");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "��"
          + yearMonth.substring(4, 6) + "��");
    } else if (bizType
        .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
      af.setBizType("һ�����廹��");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "��"
          + yearMonth.substring(4, 6) + "��");
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER))) {
      af.setBizType("�������գ�");
      af.setMonthYear(loanFlowHead.getBizDate().substring(0, 4) + "��"
          + loanFlowHead.getBizDate().substring(4, 6) + "��");
    } else {
      af.setBorrowerInfoDTO(borrowerInfoDTO);
    }
    // �õ����ʵ�������
    String wrongBizType = loanFlowHead.getWrongBizType();
    if (wrongBizType.equals("6") || wrongBizType.equals("7")) {
      af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()));
    } else {
      af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()).add(
          af.getOverOccurMoney()));
    }
    af.setMonths(taillist.size() + "");
    String bizDate = loanFlowHead.getBizDate();
    af.setBizDate(bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8));
    af.setDocNum(loanFlowHead.getDocNum());
    af.setNoteNum(loanFlowHead.getNoteNum());
    return af;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
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

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

}
