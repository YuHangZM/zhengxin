package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.bsinterface.IPrincipalBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.dto.PrincipalAccInfDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.dto.PrincipalTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.form.PrincipalTaAF;

public class PrincipalBS implements IPrincipalBS {

  private CollBankDAO collBankDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public PrincipalTaAF queryYearAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    PrincipalTaAF af = new PrincipalTaAF();
    List list = null;
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int count = 0;
      String startYear = (String) pagination.getQueryCriterions().get(
          "startYear");
      String endYear = (String) pagination.getQueryCriterions().get("endYear");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      list = loanFlowHeadDAO.queryYearAccInfoList(startYear, endYear,
          loanBankId);
      List returnList = new ArrayList();
      String tempYear = "";
      String tempLoanBankId = "";
      BigDecimal thisDebitMoney = new BigDecimal(0.00);// ���ڽ跽���
      BigDecimal thisCreditMoney = new BigDecimal(0.00);// ���ڴ������
      int thisDebitCount = 0;// ���ڽ跽����
      int thisCreditCount = 0;// ���ڴ�������
      BigDecimal totalDebitMoney = new BigDecimal(0.00);// �ۼƽ跽���
      BigDecimal totalCreditMoney = new BigDecimal(0.00);// �ۼƴ������
      int totalDebitCount = 0;// �ۼƽ跽����
      int totalCreditCount = 0;// �ۼƴ�������
      BigDecimal bgnDebitBalance = null;// �ڳ��跽���
      BigDecimal bgnCreditBalance = null;// �ڳ��������
      int bgnDebitCount = 0;// �ڳ��跽����
      int bgnCreditCount = 0;// �ڳ���������
      BigDecimal valDebit = null;
      BigDecimal valCredit = null;
      BigDecimal bgnBalance = new BigDecimal(0.00);
      for (int i = 0; i < list.size(); i++) {
        PrincipalAccInfDTO info = (PrincipalAccInfDTO) list.get(i);

        if ((i != 0)
            && (!info.getYear().equals(tempYear) || !info.getLoanBankId()
                .equals(tempLoanBankId))) {
          PrincipalTaDTO dto = new PrincipalTaDTO();
          dto.setYear(tempYear);
          dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(tempLoanBankId)
              .getCollBankName());
          dto.setLoanBankId(tempLoanBankId);
          dto.setBgnBalance(bgnBalance);
          dto.setThisDebitMoney(thisDebitMoney);
          dto.setThisCreditMoney(thisCreditMoney);

          dto.setThisDebitCount(new Integer(thisDebitCount));
          dto.setThisCreditCount(new Integer(thisCreditCount));
          // �ۼ���
          dto.setTotalDebitCount(new Integer(totalDebitCount));
          dto.setTotalCreditCount(new Integer(totalCreditCount));
          dto.setTotalDebitMoney(totalDebitMoney);
          dto.setTotalCreditMoney(totalCreditMoney);
          dto.setEndBalance(totalDebitMoney.subtract(totalCreditMoney));
          returnList.add(dto);
          // �ж�������µ�һ�����������¹�0
          thisDebitMoney = new BigDecimal(0.00);
          thisCreditMoney = new BigDecimal(0.00);
          thisDebitCount = 0;
          thisCreditCount = 0;
          bgnDebitBalance = new BigDecimal(0.00);
          bgnCreditBalance = new BigDecimal(0.00);
          totalDebitCount = 0;
          totalCreditCount = 0;
          totalDebitMoney = new BigDecimal(0.00);
          totalCreditMoney = new BigDecimal(0.00);
          valDebit = new BigDecimal(0.00);
          valCredit = new BigDecimal(0.00);
        }
        if ((info.getBizType().equals("1"))
            || (info.getBizType().equals("12") && info.getWrongBizType()
                .equals("1"))) {
          thisDebitMoney = thisDebitMoney.add(new BigDecimal(info
              .getOccurMoney()));
        }
        thisCreditMoney = thisCreditMoney.add(new BigDecimal(info
            .getRealCorpus()).add(new BigDecimal(info.getRealOverdueCorpus())));
        // �ۼ���

        if (info.getBizType().equals("1"))
          thisDebitCount += Integer.parseInt(info.getShouldCount());
        if ((Integer.parseInt(info.getBizType()) >= 2 && Integer.parseInt(info
            .getBizType()) <= 7)
            || info.getBizType().equals("11")) {
          thisCreditCount += Integer.parseInt(info.getShouldCount());
        } else if (info.getBizType().equals("12")
            && (info.getWrongBizType().equals("2")
                || info.getWrongBizType().equals("5")
                || info.getWrongBizType().equals("6") || info.getWrongBizType()
                .equals("7"))) {
          thisCreditCount -= Integer.parseInt(info.getShouldCount());
        }
        valDebit = loanFlowHeadDAO.queryPl500CorpDebit(info.getYear(), info
            .getLoanBankId());
        valCredit = loanFlowHeadDAO.queryPl500CorpCredit(info.getYear(), info
            .getLoanBankId());
        // ���ڳ�����������ͽ��
        bgnDebitBalance = loanFlowHeadDAO.queryBgnDebitCorpBalance(info
            .getLoanBankId(), info.getYear()).add(valDebit);
        bgnDebitCount = loanFlowHeadDAO.queryBgnDebitCorpCount(info
            .getLoanBankId(), info.getYear());
        bgnCreditBalance = loanFlowHeadDAO.queryBgnCreditCorpBalance(
            info.getLoanBankId(), info.getYear()).add(valCredit);
        bgnCreditCount = loanFlowHeadDAO.queryBgnCreditCorpCount(info
            .getLoanBankId(), info.getYear());
        // �ۼƱ����ͽ��
        totalDebitCount = thisDebitCount + bgnDebitCount;
        totalCreditCount = thisCreditCount + bgnCreditCount;
        totalDebitMoney = thisDebitMoney.add(bgnDebitBalance);
        totalCreditMoney = thisCreditMoney.add(bgnCreditBalance);

        bgnBalance = bgnDebitBalance.subtract(bgnCreditBalance);
        tempYear = info.getYear();
        tempLoanBankId = info.getLoanBankId();
      }
      // �������һ��
      if (!list.isEmpty()) {
        PrincipalTaDTO dto = new PrincipalTaDTO();
        dto.setYear(tempYear);
        dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(tempLoanBankId)
            .getCollBankName());
        dto.setLoanBankId(tempLoanBankId);
        dto.setBgnBalance(bgnBalance);
        dto.setThisDebitMoney(thisDebitMoney);
        dto.setThisCreditMoney(thisCreditMoney);
        dto.setThisDebitCount(new Integer(thisDebitCount));
        dto.setThisCreditCount(new Integer(thisCreditCount));
        // �ۼ���
        dto.setTotalDebitCount(new Integer(totalDebitCount));
        dto.setTotalCreditCount(new Integer(totalCreditCount));
        dto.setTotalDebitMoney(totalDebitMoney);
        dto.setTotalCreditMoney(totalCreditMoney);
        dto.setEndBalance(totalDebitMoney.subtract(totalCreditMoney));
        returnList.add(dto);
      }
      count = returnList.size();
      if (start + pageSize > count) {
        list = returnList.subList(start, count);
      } else {
        list = returnList.subList(start, start + pageSize);
      }
      af.setList(list);
      af.setPrintList(returnList);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  public PrincipalTaAF queryMonthAccList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception {
    PrincipalTaAF af = new PrincipalTaAF();
    List list = null;
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int count = 0;
      String year = (String) pagination.getQueryCriterions().get("year");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      list = loanFlowHeadDAO.queryMonthAccInfoList(year, loanBankId);
      List returnList = new ArrayList();
      String tempMonth = "";
      BigDecimal thisDebitMoney = new BigDecimal(0.00);// ���ڽ跽���
      BigDecimal thisCreditMoney = new BigDecimal(0.00);// ���ڴ������
      int thisDebitCount = 0;// ���ڽ跽����
      int thisCreditCount = 0;// ���ڴ�������
      BigDecimal totalDebitMoney = new BigDecimal(0.00);// �ۼƽ跽���
      BigDecimal totalCreditMoney = new BigDecimal(0.00);// �ۼƴ������
      int totalDebitCount = 0;// �ۼƽ跽����
      int totalCreditCount = 0;// �ۼƴ�������
      BigDecimal bgnDebitBalance = null;// �ڳ��跽���
      BigDecimal bgnCreditBalance = null;// �ڳ��������
      int bgnDebitCount = 0;// �ڳ��跽����
      int bgnCreditCount = 0;// �ڳ���������
      BigDecimal valDebit = null;
      BigDecimal valCredit = null;
      BigDecimal bgnBalance = new BigDecimal(0.00);
      for (int i = 0; i < list.size(); i++) {
        PrincipalAccInfDTO info = (PrincipalAccInfDTO) list.get(i);

        if ((i != 0)
            && !info.getMonth().equals(tempMonth)) {
          PrincipalTaDTO dto = new PrincipalTaDTO();
          dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(loanBankId)
              .getCollBankName());
          dto.setLoanBankId(loanBankId);
          dto.setMonth(tempMonth);
          dto.setBgnBalance(bgnBalance);
          dto.setThisDebitMoney(thisDebitMoney);
          dto.setThisCreditMoney(thisCreditMoney);

          dto.setThisDebitCount(new Integer(thisDebitCount));
          dto.setThisCreditCount(new Integer(thisCreditCount));
          // �ۼ���
          dto.setTotalDebitCount(new Integer(totalDebitCount));
          dto.setTotalCreditCount(new Integer(totalCreditCount));
          dto.setTotalDebitMoney(totalDebitMoney);
          dto.setTotalCreditMoney(totalCreditMoney);
          dto.setEndBalance(totalDebitMoney.subtract(totalCreditMoney));
          returnList.add(dto);
          // �ж�������µ�һ�����������¹�0
          thisDebitMoney = new BigDecimal(0.00);
          thisCreditMoney = new BigDecimal(0.00);
          thisDebitCount = 0;
          thisCreditCount = 0;
          bgnDebitBalance = new BigDecimal(0.00);
          bgnCreditBalance = new BigDecimal(0.00);
          totalDebitCount = 0;
          totalCreditCount = 0;
          totalDebitMoney = new BigDecimal(0.00);
          totalCreditMoney = new BigDecimal(0.00);
          valDebit = new BigDecimal(0.00);
          valCredit = new BigDecimal(0.00);
        }
        if ((info.getBizType().equals("1"))
            || (info.getBizType().equals("12") && info.getWrongBizType()
                .equals("1"))) {
          thisDebitMoney = thisDebitMoney.add(new BigDecimal(info
              .getOccurMoney()));
        }
        thisCreditMoney = thisCreditMoney.add(new BigDecimal(info
            .getRealCorpus()).add(new BigDecimal(info.getRealOverdueCorpus())));
        // �ۼ���

        if (info.getBizType().equals("1"))
          thisDebitCount += Integer.parseInt(info.getShouldCount());
        if ((Integer.parseInt(info.getBizType()) >= 2 && Integer.parseInt(info
            .getBizType()) <= 7)
            || info.getBizType().equals("11")) {
          thisCreditCount += Integer.parseInt(info.getShouldCount());
        } else if (info.getBizType().equals("12")
            && (info.getWrongBizType().equals("2")
                || info.getWrongBizType().equals("5")
                || info.getWrongBizType().equals("6") || info.getWrongBizType()
                .equals("7"))) {
          thisCreditCount -= Integer.parseInt(info.getShouldCount());
        }
        valDebit = loanFlowHeadDAO.queryPl500CorpDebit(info.getMonth(), info
            .getLoanBankId());
        valCredit = loanFlowHeadDAO.queryPl500CorpCredit(info.getMonth(), info
            .getLoanBankId());
        // ���ڳ�����������ͽ��
        bgnDebitBalance = loanFlowHeadDAO.queryBgnDebitCorpBalance(info
            .getLoanBankId(), info.getMonth()).add(valDebit);
        bgnDebitCount = loanFlowHeadDAO.queryBgnDebitCorpCount(info
            .getLoanBankId(), info.getMonth());
        bgnCreditBalance = loanFlowHeadDAO.queryBgnCreditCorpBalance(
            info.getLoanBankId(), info.getMonth()).add(valCredit);
        bgnCreditCount = loanFlowHeadDAO.queryBgnCreditCorpCount(info
            .getLoanBankId(), info.getMonth());
        // �ۼƱ����ͽ��
        totalDebitCount = thisDebitCount + bgnDebitCount;
        totalCreditCount = thisCreditCount + bgnCreditCount;
        totalDebitMoney = thisDebitMoney.add(bgnDebitBalance);
        totalCreditMoney = thisCreditMoney.add(bgnCreditBalance);

        bgnBalance = bgnDebitBalance.subtract(bgnCreditBalance);
        tempMonth = info.getMonth();
      }
      // �������һ��
      if (!list.isEmpty()) {
        PrincipalTaDTO dto = new PrincipalTaDTO();
        dto.setMonth(tempMonth);
        dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(loanBankId)
            .getCollBankName());
        dto.setLoanBankId(loanBankId);
        dto.setBgnBalance(bgnBalance);
        dto.setThisDebitMoney(thisDebitMoney);
        dto.setThisCreditMoney(thisCreditMoney);
        dto.setThisDebitCount(new Integer(thisDebitCount));
        dto.setThisCreditCount(new Integer(thisCreditCount));
        // �ۼ���
        dto.setTotalDebitCount(new Integer(totalDebitCount));
        dto.setTotalCreditCount(new Integer(totalCreditCount));
        dto.setTotalDebitMoney(totalDebitMoney);
        dto.setTotalCreditMoney(totalCreditMoney);
        dto.setEndBalance(totalDebitMoney.subtract(totalCreditMoney));
        returnList.add(dto);
      }
      count = returnList.size();
      if (start + pageSize > count) {
        list = returnList.subList(start, count);
      } else {
        list = returnList.subList(start, start + pageSize);
      }
      af.setList(list);
      af.setPrintList(returnList);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }

  public PrincipalTaAF queryDayAccList(Pagination pagination, SecurityInfo securityInfo) throws BusinessException, Exception {

    PrincipalTaAF af = new PrincipalTaAF();
    List list = null;
    try {
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int count = 0;
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String year = (String) pagination.getQueryCriterions().get("year");
      String month = (String) pagination.getQueryCriterions().get("month");
      String yearMonth = year + (month.length() < 2 ? "0" + month : month);
      list = loanFlowHeadDAO.queryDayAccInfoList(yearMonth, loanBankId);
      List returnList = new ArrayList();
      String tempDate = "";
      BigDecimal thisDebitMoney = new BigDecimal(0.00);// ���ڽ跽���
      BigDecimal thisCreditMoney = new BigDecimal(0.00);// ���ڴ������
      int thisDebitCount = 0;// ���ڽ跽����
      int thisCreditCount = 0;// ���ڴ�������
      BigDecimal totalDebitMoney = new BigDecimal(0.00);// �ۼƽ跽���
      BigDecimal totalCreditMoney = new BigDecimal(0.00);// �ۼƴ������
      int totalDebitCount = 0;// �ۼƽ跽����
      int totalCreditCount = 0;// �ۼƴ�������
      BigDecimal bgnDebitBalance = null;// �ڳ��跽���
      BigDecimal bgnCreditBalance = null;// �ڳ��������
      int bgnDebitCount = 0;// �ڳ��跽����
      int bgnCreditCount = 0;// �ڳ���������
      BigDecimal valDebit = null;
      BigDecimal valCredit = null;
      BigDecimal bgnBalance = new BigDecimal(0.00);
      
      BigDecimal debitMoneySum = new BigDecimal(0.00);// �ϼƽ跽���
      BigDecimal creditMoneySum = new BigDecimal(0.00);// �ϼƴ������
      int debitCountSum = 0;// �ϼƽ跽����
      int creditCountSum = 0;// �ϼƴ�������
      for (int i = 0; i < list.size(); i++) {
        PrincipalAccInfDTO info = (PrincipalAccInfDTO) list.get(i);
        if ((i != 0)
            && !info.getDate().equals(tempDate)) {
          PrincipalTaDTO dto = new PrincipalTaDTO();
          dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(loanBankId)
              .getCollBankName());
          dto.setLoanBankId(loanBankId);
          dto.setDate(tempDate);
          dto.setBgnBalance(bgnBalance);
          dto.setThisDebitMoney(thisDebitMoney);
          dto.setThisCreditMoney(thisCreditMoney);

          dto.setThisDebitCount(new Integer(thisDebitCount));
          dto.setThisCreditCount(new Integer(thisCreditCount));
          // �ۼ���
          dto.setTotalDebitCount(new Integer(totalDebitCount));
          dto.setTotalCreditCount(new Integer(totalCreditCount));
          dto.setTotalDebitMoney(totalDebitMoney);
          dto.setTotalCreditMoney(totalCreditMoney);
          dto.setEndBalance(totalDebitMoney.subtract(totalCreditMoney));
          returnList.add(dto);
          // �ж�������µ�һ�����������¹�0
          thisDebitMoney = new BigDecimal(0.00);
          thisCreditMoney = new BigDecimal(0.00);
          thisDebitCount = 0;
          thisCreditCount = 0;
          bgnDebitBalance = new BigDecimal(0.00);
          bgnCreditBalance = new BigDecimal(0.00);
          totalDebitCount = 0;
          totalCreditCount = 0;
          totalDebitMoney = new BigDecimal(0.00);
          totalCreditMoney = new BigDecimal(0.00);
          valDebit = new BigDecimal(0.00);
          valCredit = new BigDecimal(0.00);
        }
        if ((info.getBizType().equals("1"))
            || (info.getBizType().equals("12") && info.getWrongBizType()
                .equals("1"))) {
          thisDebitMoney = thisDebitMoney.add(new BigDecimal(info
              .getOccurMoney()));
        }
        thisCreditMoney = thisCreditMoney.add(new BigDecimal(info
            .getRealCorpus()).add(new BigDecimal(info.getRealOverdueCorpus())));
        // �ۼ���

        if (info.getBizType().equals("1"))
          thisDebitCount += Integer.parseInt(info.getShouldCount());
        if ((Integer.parseInt(info.getBizType()) >= 2 && Integer.parseInt(info
            .getBizType()) <= 7)
            || info.getBizType().equals("11")) {
          thisCreditCount += Integer.parseInt(info.getShouldCount());
        } else if (info.getBizType().equals("12")
            && (info.getWrongBizType().equals("2")
                || info.getWrongBizType().equals("5")
                || info.getWrongBizType().equals("6") || info.getWrongBizType()
                .equals("7"))) {
          thisCreditCount -= Integer.parseInt(info.getShouldCount());
        }
        valDebit = loanFlowHeadDAO.queryPl500CorpDebit(info.getDate(), info
            .getLoanBankId());
        valCredit = loanFlowHeadDAO.queryPl500CorpCredit(info.getDate(), info
            .getLoanBankId());
        // ���ڳ�����������ͽ��
        bgnDebitBalance = loanFlowHeadDAO.queryBgnDebitCorpBalance(info
            .getLoanBankId(), info.getDate()).add(valDebit);
        bgnDebitCount = loanFlowHeadDAO.queryBgnDebitCorpCount(info
            .getLoanBankId(), info.getDate());
        bgnCreditBalance = loanFlowHeadDAO.queryBgnCreditCorpBalance(
            info.getLoanBankId(), info.getDate()).add(valCredit);
        bgnCreditCount = loanFlowHeadDAO.queryBgnCreditCorpCount(info
            .getLoanBankId(), info.getDate());
        // �ۼƱ����ͽ��
        totalDebitCount = thisDebitCount + bgnDebitCount;
        totalCreditCount = thisCreditCount + bgnCreditCount;
        totalDebitMoney = thisDebitMoney.add(bgnDebitBalance);
        totalCreditMoney = thisCreditMoney.add(bgnCreditBalance);

        bgnBalance = bgnDebitBalance.subtract(bgnCreditBalance);
        tempDate = info.getDate();
      }
      // �������һ��
      if (!list.isEmpty()) {
        PrincipalTaDTO dto = new PrincipalTaDTO();
        dto.setDate(tempDate);
        dto.setLoanBank(collBankDAO.getCollBankByCollBankid_(loanBankId)
            .getCollBankName());
        dto.setLoanBankId(loanBankId);
        dto.setBgnBalance(bgnBalance);
        dto.setThisDebitMoney(thisDebitMoney);
        dto.setThisCreditMoney(thisCreditMoney);
        dto.setThisDebitCount(new Integer(thisDebitCount));
        dto.setThisCreditCount(new Integer(thisCreditCount));
        // �ۼ���
        dto.setTotalDebitCount(new Integer(totalDebitCount));
        dto.setTotalCreditCount(new Integer(totalCreditCount));
        dto.setTotalDebitMoney(totalDebitMoney);
        dto.setTotalCreditMoney(totalCreditMoney);
        dto.setEndBalance(totalDebitMoney.subtract(totalCreditMoney));
        returnList.add(dto);
      }
      for (int i = 0; i < returnList.size(); i++) {
        PrincipalTaDTO dto = (PrincipalTaDTO) returnList.get(i);
        debitMoneySum = debitMoneySum.add(dto.getThisDebitMoney());
        creditMoneySum = creditMoneySum.add(dto.getThisCreditMoney());
        debitCountSum += dto.getThisDebitCount().intValue();
        creditCountSum += dto.getThisCreditCount().intValue();
      }
      if (!list.isEmpty()) {
        PrincipalTaDTO dto = new PrincipalTaDTO();
        dto.setDate("�ܺϼ�");
        dto.setBgnBalance(null);
        dto.setThisDebitMoney(debitMoneySum);
        dto.setThisCreditMoney(creditMoneySum);
        dto.setThisDebitCount(new Integer(debitCountSum));
        dto.setThisCreditCount(new Integer(creditCountSum));
        dto.setTotalDebitCount(null);
        dto.setTotalCreditCount(null);
        dto.setTotalDebitMoney(null);
        dto.setTotalCreditMoney(null);
        dto.setEndBalance(null);
        returnList.add(dto);
      }
      count = returnList.size();
      if (start + pageSize > count) {
        list = returnList.subList(start, count);
      } else {
        list = returnList.subList(start, start + pageSize);
      }
      af.setList(list);
      af.setPrintList(returnList);
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  
  }
}
