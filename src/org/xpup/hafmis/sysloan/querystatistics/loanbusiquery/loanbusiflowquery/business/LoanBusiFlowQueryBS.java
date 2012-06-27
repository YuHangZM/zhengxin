package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.bsinterface.ILoanBusiFlowQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.dto.BailClearInterestTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.form.BailClearInterestTbAF;

/**
 * @author ��Ұ 2007-10-15
 */
public class LoanBusiFlowQueryBS implements ILoanBusiFlowQueryBS {

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// ��ˮͷ�� PL202

  private CollBankDAO collBankDAO = null;// ת����������

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  /**
   * Description ����ҵ����ˮͳ�Ʋ�ѯ�б�
   * 
   * @author wangy 2007-10-16
   * @param ����������ѯ�б�
   * @param pagination
   * @param securityInfo
   * @param ��LoanBusiFlowQueryShowAC����
   * @return LoanBusiFlowQueryAF
   * @throws Exception, BusinessException
   */
  public LoanBusiFlowQueryAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanBusiFlowQueryAF loanBusiFlowQueryAF = new LoanBusiFlowQueryAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
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
    String isGjjLoanback = (String) pagination.getQueryCriterions().get(
        "isGjjLoanback");
    List loanBusiFlowQueryList = new ArrayList();
    List printList = new ArrayList();// ��ӡ�б�
    List templist = new ArrayList();
    List temptotlelist = new ArrayList();
    // �޸ļ�¼�����Ʒ����ϵͳ��Ҫ���Ӵ���ҵ����ˮ����Ҫͨ��Ʊ�ݺţ�����ţ����в�ѯ���ڲ����б����������һ��notNum 2007-11-10
    templist = loanFlowHeadDAO.queryLoanBusiFlowQueryListByCriterions(start,
        orderBy, order, pageSize, page, securityInfo, docNum, contractId,
        loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName,
        beginBizDate, endBizDate, null, isGjjLoanback);
    temptotlelist = loanFlowHeadDAO.queryLoanBusiFlowQueryAllByCriterions(
        securityInfo, docNum, contractId, loanKouAcc, borrowerName, makePerson,
        bizType, bizSt, loanBankName, beginBizDate, endBizDate, null, isGjjLoanback);
    count = temptotlelist.size();
    // ����ҵ����ˮ��ѯ�б�
    Iterator iterate = templist.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();
      obj = (Object[]) iterate.next();
      // ƾ֤���PL202.DOC_NUM
      if (obj[0] != null && !obj[0].equals(""))
        loanBusiFlowQueryDTO.setDocNum(obj[0].toString());
      // �����˺�PL203.LOAN_KOU_ACC
      if (obj[1] != null && !obj[1].equals(""))
        loanBusiFlowQueryDTO.setLoanKouAcc(obj[1].toString());
      // ��ͬ���PL203.CONTRACT_ID
      if (obj[2] != null && !obj[2].equals(""))
        loanBusiFlowQueryDTO.setContractId(obj[2].toString());
      if (obj[3] != null && !obj[3].equals(""))
        loanBusiFlowQueryDTO.setBorrowerName(obj[3].toString());
      // ҵ������L202.BIZ_TYPE
      if (obj[4] != null && !obj[4].equals("")) {
        loanBusiFlowQueryDTO.setBizType(obj[4].toString());
        loanBusiFlowQueryDTO.setOriginalitybizType(obj[4].toString());
      }
      // ���Ž��L202.OCCUR_MONEY(��ҵ������=1��11��ҵ������=12��WRONG_BIZ_TYPE=1ʱ)
      if (obj[5] != null && !obj[5].equals("")) {
        if (obj[4].equals("1")) {
          loanBusiFlowQueryDTO.setOccurMoney(new BigDecimal(obj[5].toString()));
        } else if (obj[4].equals("12")) {
          if ((obj[15] != null && !obj[15].equals(""))) {
            if (obj[15].equals("1")) {
              loanBusiFlowQueryDTO.setOccurMoney(new BigDecimal(obj[5]
                  .toString()));
            }
          }
        }
      }
      // ���ձ���PL202.REAL_CORPUS+PL202.REAL_OVERDUE_CORPUS
      BigDecimal reclaimCorpus = new BigDecimal(0.00);// ���ձ���
      if (obj[6] != null && !obj[6].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("6") || obj[4].equals("7")
            || obj[4].equals("11") || obj[4].equals("12")) {
          reclaimCorpus = new BigDecimal(obj[6].toString());
          loanBusiFlowQueryDTO.setReclaimCorpus(new BigDecimal(obj[6]
              .toString()));
        }
      }
      // ������ϢPL202.REAL_INTEREST+PL202.REAL_OVERDUE_INTEREST
      BigDecimal reclaimAccrual = new BigDecimal(0.00);// ������Ϣ
      if (obj[7] != null && !obj[7].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("6") || obj[4].equals("7")
            || obj[4].equals("11") || obj[4].equals("12")) {
          reclaimAccrual = new BigDecimal(obj[7].toString());
          loanBusiFlowQueryDTO.setReclaimAccrual(new BigDecimal(obj[7]
              .toString()));
        }
      }
      // ���շ�ϢPL202.REAL_PUNISH_INTEREST
      BigDecimal realPunishInterest = new BigDecimal(0.00);// ���շ�Ϣ
      if (obj[8] != null && !obj[8].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("6") || obj[4].equals("7")
            || obj[4].equals("11") || obj[4].equals("12")) {
          realPunishInterest = new BigDecimal(obj[8].toString());
          loanBusiFlowQueryDTO.setRealPunishInterest(new BigDecimal(obj[8]
              .toString()));
        }
      }
      // ���˺������PL202.OCCUR_MONEY
      if (obj[9] != null && !obj[9].equals("")) {
        if (obj[4].equals("6") || obj[4].equals("7") || obj[4].equals("8")
            || obj[4].equals("9")) {
          loanBusiFlowQueryDTO.setBadDebt(new BigDecimal(obj[9].toString()));
        } else if (obj[4].equals("12")) {
          if ((obj[15] != null && !obj[15].equals(""))) {
            if (obj[15].equals("6") || obj[15].equals("7")) {
              loanBusiFlowQueryDTO
                  .setBadDebt(new BigDecimal(obj[9].toString()));
            }
          }
        }
      }
      // ���˽��PL202.OCCUR_MONEY
      if (obj[10] != null && !obj[10].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("13")) {
          loanBusiFlowQueryDTO
              .setPutUpMoney(new BigDecimal(obj[10].toString()));
        } else if (obj[4].equals("12")) {
          if ((obj[15] != null && !obj[15].equals(""))) {
            if (obj[15].equals("2") || obj[15].equals("5")) {
              loanBusiFlowQueryDTO.setPutUpMoney(new BigDecimal(obj[10]
                  .toString()));
            }
          }
        }
      }
      // ��֤��PL202.OCCUR_MONEY
      if (obj[11] != null && !obj[11].equals("")) {
        if (obj[4].equals("14")) {
          loanBusiFlowQueryDTO.setBail(new BigDecimal(obj[11].toString()));
        }
      }
      // ��֤����Ϣ
      if (obj[12] != null && !obj[12].equals("")) {
        if (obj[4].equals("14")) {
          loanBusiFlowQueryDTO
              .setBailAccrual(new BigDecimal(obj[12].toString()));// bizType=14ʱ����֤����Ϣ=PL202.OTHER_INTEREST
        } else {
          if (obj[11] != null && !obj[11].equals("")) {
            if (obj[4].equals("15")) {
              loanBusiFlowQueryDTO.setBailAccrual(new BigDecimal(obj[11]
                  .toString()));// bizType=15ʱ����֤����Ϣ=PL202.OCCUR_MONEY
            }
          }
        }
      }
      // ҵ��״̬PL202.BIZ_ST
      if (obj[13] != null && !obj[13].equals(""))
        loanBusiFlowQueryDTO.setBizSt(obj[13].toString());
      // ��������PL202.BIZ_DATE
      if (obj[14] != null && !obj[14].equals(""))
        loanBusiFlowQueryDTO.setBizDate(obj[14].toString());
      // �Ƶ���PL202.MAKE_PERSON
      if (obj[17] != null && !obj[17].equals(""))
        loanBusiFlowQueryDTO.setMakePerson(obj[17].toString());
      // pl202.flow_head_id
      if (obj[18] != null && !obj[18].equals(""))
        loanBusiFlowQueryDTO.setFlowHeadId(obj[18].toString());
      // �����ܽ��=���ձ��𣫻�����Ϣ�����շ�Ϣ
      BigDecimal reclaim = new BigDecimal(0.00);// �����ܽ��
      reclaim = reclaimCorpus.add(reclaimAccrual.add(realPunishInterest));
      loanBusiFlowQueryDTO.setReclaim(reclaim);
      //�Ƿ�Ϊ�����𻹴�
      loanBusiFlowQueryDTO.setIsGjjLoanBack(obj[20]!=null?"��":"��");
      // ö��ת��ҵ��״̬
      try {
        loanBusiFlowQueryDTO.setBizSt(BusiTools.getBusiValue(Integer
            .parseInt("" + loanBusiFlowQueryDTO.getBizSt()),
            BusiConst.PLBUSINESSSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      // ö��ת��ҵ������
      try {
        loanBusiFlowQueryDTO.setBizType(BusiTools.getBusiValue(Integer
            .parseInt("" + loanBusiFlowQueryDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      loanBusiFlowQueryList.add(loanBusiFlowQueryDTO);
    }
    loanBusiFlowQueryAF.setList(loanBusiFlowQueryList);
    // �ϼ���Ϣ
    BigDecimal occurMoneyTotle = new BigDecimal(0.00);// ���Ž��-�ܶ�
    BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// ���ձ���-�ܶ�
    BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// // ������Ϣ-�ܶ�
    BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// // ���շ�Ϣ-�ܶ�
    BigDecimal reclaimTotal = new BigDecimal(0.00);// �����ܽ��-�ܶ�
    BigDecimal badDebtTotle = new BigDecimal(0.00);// ���˺������-�ܶ�
    BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// ���˽��-�ܶ�
    BigDecimal bailTotle = new BigDecimal(0.00);// ��֤��-�ܶ�
    BigDecimal bailAccrualTotle = new BigDecimal(0.00);// ��֤����Ϣ-�ܶ�
    Iterator iteratetotle = temptotlelist.iterator();
    Object[] objtotle = null;
    while (iteratetotle.hasNext()) {
      objtotle = (Object[]) iteratetotle.next();
      LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();
      // ƾ֤���PL202.DOC_NUM
      if (objtotle[0] != null && !objtotle[0].equals(""))
        loanBusiFlowQueryDTO.setDocNum(objtotle[0].toString());
      // �����˺�PL203.LOAN_KOU_ACC
      if (objtotle[1] != null && !objtotle[1].equals(""))
        loanBusiFlowQueryDTO.setLoanKouAcc(objtotle[1].toString());
      // ��ͬ���PL203.CONTRACT_ID
      if (objtotle[2] != null && !objtotle[2].equals(""))
        loanBusiFlowQueryDTO.setContractId(objtotle[2].toString());
      if (objtotle[3] != null && !objtotle[3].equals(""))
        loanBusiFlowQueryDTO.setBorrowerName(objtotle[3].toString());
      // ҵ������L202.BIZ_TYPE
      if (objtotle[4] != null && !objtotle[4].equals("")) {
        loanBusiFlowQueryDTO.setBizType(objtotle[4].toString());
        loanBusiFlowQueryDTO.setOriginalitybizType(objtotle[4].toString());
      }
      // ���Ž��-�ܶ�
      if (objtotle[5] != null && !objtotle[5].equals("")) {
        if (objtotle[4].equals("1")) {
          loanBusiFlowQueryDTO.setOccurMoney(new BigDecimal(objtotle[5]
              .toString()));
          occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(objtotle[5]
              .toString()));
        } else if (objtotle[4].equals("12")) {
          if ((objtotle[15] != null && !objtotle[15].equals(""))) {
            if (objtotle[15].equals("1")) {
              loanBusiFlowQueryDTO.setOccurMoney(new BigDecimal(objtotle[5]
                  .toString()));
              occurMoneyTotle = occurMoneyTotle.add(new BigDecimal(objtotle[5]
                  .toString()));
            }
          }
        }
      }
      // ���ձ���-�ܶ�
      BigDecimal reclaimCorpus = new BigDecimal(0.00);// ���ձ���
      if (objtotle[6] != null && !objtotle[6].equals("")) {
        if (objtotle[4].equals("2") || objtotle[4].equals("3")
            || objtotle[4].equals("4") || objtotle[4].equals("5")
            || objtotle[4].equals("6") || objtotle[4].equals("7")
            || objtotle[4].equals("11") || objtotle[4].equals("12")) {
          reclaimCorpus = new BigDecimal(objtotle[6].toString());
          loanBusiFlowQueryDTO.setReclaimCorpus(new BigDecimal(objtotle[6]
              .toString()));
          reclaimCorpusTotle = reclaimCorpusTotle.add(new BigDecimal(
              objtotle[6].toString()));
        }
      }
      // ������Ϣ-�ܶ�
      BigDecimal reclaimAccrual = new BigDecimal(0.00);// ������Ϣ
      if (objtotle[7] != null && !objtotle[7].equals("")) {
        if (objtotle[4].equals("2") || objtotle[4].equals("3")
            || objtotle[4].equals("4") || objtotle[4].equals("5")
            || objtotle[4].equals("6") || objtotle[4].equals("7")
            || objtotle[4].equals("11") || objtotle[4].equals("12")) {
          reclaimAccrual = new BigDecimal(objtotle[7].toString());
          loanBusiFlowQueryDTO.setReclaimAccrual(new BigDecimal(objtotle[7]
              .toString()));
          reclaimAccrualTotle = reclaimAccrualTotle.add(new BigDecimal(
              objtotle[7].toString()));
        }
      }
      // ���շ�Ϣ-�ܶ�
      BigDecimal realPunishInterest = new BigDecimal(0.00);// ���շ�Ϣ
      if (objtotle[8] != null && !objtotle[8].equals("")) {
        if (objtotle[4].equals("2") || objtotle[4].equals("3")
            || objtotle[4].equals("4") || objtotle[4].equals("5")
            || objtotle[4].equals("6") || objtotle[4].equals("7")
            || objtotle[4].equals("11") || objtotle[4].equals("12")) {
          realPunishInterest = new BigDecimal(objtotle[8].toString());
          loanBusiFlowQueryDTO.setRealPunishInterest(new BigDecimal(objtotle[8]
              .toString()));
          realPunishInterestTotle = realPunishInterestTotle.add(new BigDecimal(
              objtotle[8].toString()));
        }
      }
      // �����ܽ��=���ձ��𣫻�����Ϣ�����շ�Ϣ
      BigDecimal reclaim = new BigDecimal(0.00);// �����ܽ��
      reclaim = reclaimCorpus.add(reclaimAccrual.add(realPunishInterest));
      loanBusiFlowQueryDTO.setReclaim(reclaim);
      // ���˺������-�ܶ�
      if (objtotle[9] != null && !objtotle[9].equals("")) {
        if (objtotle[4].equals("6") || objtotle[4].equals("7")
            || objtotle[4].equals("8") || objtotle[4].equals("9")) {
          loanBusiFlowQueryDTO
              .setBadDebt(new BigDecimal(objtotle[9].toString()));
          badDebtTotle = badDebtTotle
              .add(new BigDecimal(objtotle[9].toString()));
        } else if (objtotle[4].equals("12")) {
          if ((objtotle[15] != null && !objtotle[15].equals(""))) {
            if (objtotle[15].equals("6") || objtotle[15].equals("7")) {
              loanBusiFlowQueryDTO.setBadDebt(new BigDecimal(objtotle[9]
                  .toString()));
              badDebtTotle = badDebtTotle.add(new BigDecimal(objtotle[9]
                  .toString()));
            }
          }
        }
      }
      // ���˽��-�ܶ�
      if (objtotle[10] != null && !objtotle[10].equals("")) {
        if (objtotle[4].equals("2") || objtotle[4].equals("3")
            || objtotle[4].equals("4") || objtotle[4].equals("5")
            || objtotle[4].equals("13")) {
          loanBusiFlowQueryDTO.setPutUpMoney(new BigDecimal(objtotle[10]
              .toString()));
          putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(objtotle[10]
              .toString()));
        } else if (objtotle[4].equals("12")) {
          if ((objtotle[15] != null && !objtotle[15].equals(""))) {
            if (objtotle[15].equals("2") || objtotle[15].equals("5")) {
              loanBusiFlowQueryDTO.setPutUpMoney(new BigDecimal(objtotle[10]
                  .toString()));
              putUpMoneyTotle = putUpMoneyTotle.add(new BigDecimal(objtotle[10]
                  .toString()));
            }
          }
        }
      }
      // ��֤��-�ܶ�
      if (objtotle[11] != null && !objtotle[11].equals("")) {
        if (objtotle[4].equals("14")) {
          loanBusiFlowQueryDTO.setBail(new BigDecimal(objtotle[11].toString()));
          bailTotle = bailTotle.add(new BigDecimal(objtotle[11].toString()));
        }
      }
      // ��֤����Ϣ-�ܶ�
      if (objtotle[12] != null && !objtotle[12].equals("")) {
        if (objtotle[4].equals("14")) {
          loanBusiFlowQueryDTO.setBailAccrual(new BigDecimal(objtotle[12]
              .toString()));
          bailAccrualTotle = bailAccrualTotle.add(new BigDecimal(objtotle[12]
              .toString()));
        } else {
          if (objtotle[11] != null && !objtotle[11].equals("")) {
            if (objtotle[4].equals("15")) {
              loanBusiFlowQueryDTO.setBail(new BigDecimal(objtotle[11]
                  .toString()));
              bailAccrualTotle = bailAccrualTotle.add(new BigDecimal(
                  objtotle[11].toString()));// bizType=15ʱ����֤����Ϣ=PL202.OCCUR_MONEY
            }
          }
        }// else
      }
      // ҵ��״̬PL202.BIZ_ST
      if (objtotle[13] != null && !objtotle[13].equals(""))
        loanBusiFlowQueryDTO.setBizSt(objtotle[13].toString());
      // ��������PL202.BIZ_DATE
      if (objtotle[14] != null && !objtotle[14].equals(""))
        loanBusiFlowQueryDTO.setBizDate(objtotle[14].toString());
      // �Ƶ���PL202.MAKE_PERSON
      if (objtotle[17] != null && !objtotle[17].equals(""))
        loanBusiFlowQueryDTO.setMakePerson(objtotle[17].toString());
      // pl202.flow_head_id
      if (objtotle[18] != null && !objtotle[18].equals(""))
        loanBusiFlowQueryDTO.setFlowHeadId(objtotle[18].toString());
      // ö��ת��ҵ��״̬
      try {
        loanBusiFlowQueryDTO.setBizSt(BusiTools.getBusiValue(Integer
            .parseInt("" + loanBusiFlowQueryDTO.getBizSt()),
            BusiConst.PLBUSINESSSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      // ö��ת��ҵ������
      try {
        loanBusiFlowQueryDTO.setBizType(BusiTools.getBusiValue(Integer
            .parseInt("" + loanBusiFlowQueryDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      printList.add(loanBusiFlowQueryDTO);
    }// while
    // �ϼƣ������ܽ��=���ձ��𣫻�����Ϣ�����շ�Ϣ
    reclaimTotal = reclaimTotal.add(reclaimCorpusTotle.add(reclaimAccrualTotle
        .add(realPunishInterestTotle)));
    loanBusiFlowQueryAF.setOccurMoneyTotle(occurMoneyTotle);
    loanBusiFlowQueryAF.setReclaimCorpusTotle(reclaimCorpusTotle);
    loanBusiFlowQueryAF.setReclaimAccrualTotle(reclaimAccrualTotle);
    loanBusiFlowQueryAF.setRealPunishInterestTotle(realPunishInterestTotle);
    loanBusiFlowQueryAF.setReclaimTotle(reclaimTotal);
    loanBusiFlowQueryAF.setBadDebtTotle(badDebtTotle);
    loanBusiFlowQueryAF.setPutUpMoneyTotle(putUpMoneyTotle);
    loanBusiFlowQueryAF.setBailTotle(bailTotle);
    loanBusiFlowQueryAF.setBailAccrualTotle(bailAccrualTotle);
    loanBusiFlowQueryAF.setPrintList(printList);
    pagination.setNrOfElements(count);
    return loanBusiFlowQueryAF;
  }

  /**
   * Description ����ҵ����ˮͳ�Ʋ�ѯ�б�
   * 
   * @author wangy 2007-10-18
   * @param ����PL202.FLOW_HEAD_ID��ѯ��ת�����Ϣ
   * @param flowHeadId
   * @param securityInfo
   * @param ��LoanBusiFlowQueryClearShowWindowAC����
   * @return LoanBusiFlowQueryClearDTO
   * @throws Exception, BusinessException
   */
  public LoanBusiFlowQueryClearDTO queryLoanBusiFlowQueryClearByFlowHeadId(
      String flowHeadId, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO = null;
    try {
      List list = loanFlowHeadDAO.queryLoanBusiFlowQueryClearByFlowHeadId(
          flowHeadId, securityInfo);
      if (!list.isEmpty()) {
        loanBusiFlowQueryClearDTO = (LoanBusiFlowQueryClearDTO) list.get(0);
        loanBusiFlowQueryClearDTO.setLoanMonthRate(new BigDecimal(
            loanBusiFlowQueryClearDTO.getLoanMonthRate())
            .multiply(new BigDecimal(100))
            + "%");// ������
        // ֤�����Ͷ�Ӧ������
        loanBusiFlowQueryClearDTO.setCardKindName(BusiTools.getBusiValue(
            Integer.parseInt(loanBusiFlowQueryClearDTO.getCardKind()),
            BusiConst.DOCUMENTSSTATE));
        // ͨ��bankId�������е�����
        String bankId = loanBusiFlowQueryClearDTO.getLoanBankId();
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
        loanBusiFlowQueryClearDTO.setLoanBankName(collBank.getCollBankName());
        // ʣ�౾��=������-ʵ������
        BigDecimal surplusCorpus = new BigDecimal(0.00); // ʣ�౾��
        surplusCorpus = loanBusiFlowQueryClearDTO.getLoanMoney().add(
            loanBusiFlowQueryClearDTO.getRealCorpus().negate());
        loanBusiFlowQueryClearDTO.setSurplusCorpus(surplusCorpus);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanBusiFlowQueryClearDTO;
  }

  /**
   * Description ����ҵ����ˮͳ�Ʋ�ѯ�б�
   * 
   * @author wangy 2007-10-19
   * @param ����PL202.FLOW_HEAD_ID��ѯ��Ϣ��Ϣ
   * @param flowHeadId
   * @param securityInfo
   * @param ��LoanBusiFlowQueryClearShowWindowAC����
   * @return LoanBusiFlowQueryClearDTO
   * @throws Exception, BusinessException
   */
  public BailClearInterestTbAF queryLoanBusiFlowQueryBailClearListByFlowHeadId(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String flowHeadId = (String) pagination.getQueryCriterions().get(
        "flowHeadId");
    BigDecimal firstBalanceTotle = new BigDecimal(0.00);// ��Ϣǰ��֤��-�ܶ�
    BigDecimal occurMoneyTotle = new BigDecimal(0.00);// ��Ϣ��Ϣ-�ܶ�
    BigDecimal lastBalanceTotle = new BigDecimal(0.00);// ��Ϣ��֤��-�ܶ�
    BailClearInterestTbAF bailClearInterestTbAF = new BailClearInterestTbAF();
    List bailclearinterestTbList = new ArrayList();
    List templist = new ArrayList();
    List printList = new ArrayList();
    bailclearinterestTbList = loanFlowHeadDAO
        .queryBailClearInterestTbListByCriterions(flowHeadId, start, orderBy,
            order, pageSize, page);
    if (bailclearinterestTbList.size() > 0) {
      List countList = new ArrayList();
      countList = loanFlowHeadDAO
          .queryBailClearInterestTbCountByCriterions(flowHeadId);
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
          bailClearInterestTbAF.setLoanBankName(dto.getCollBankName());
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
