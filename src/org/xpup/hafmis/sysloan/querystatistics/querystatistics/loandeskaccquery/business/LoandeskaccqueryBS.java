package org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.OverdueInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.bsinterface.ILoandeskaccqueryBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTaDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTbDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTcDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.form.LoanDeskaccQueryTaAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.form.LoanDeskaccQueryTbAF;

public class LoandeskaccqueryBS implements ILoandeskaccqueryBS {
  private BorrowerAccDAO borrowerAccDAO = null;

  private CollBankDAO collBankDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private AssistantOrgDAO assistantOrgDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private OverdueInfoDAO overdueInfoDAO = null;

  public void setOverdueInfoDAO(OverdueInfoDAO overdueInfoDAO) {
    this.overdueInfoDAO = overdueInfoDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * hanl ��ʾ̨�ʲ�ѯ�����Ϣ
   */
  public LoanDeskaccQueryTaAF showLoandeskaccList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    LoanDeskaccQueryTaAF loanDeskaccQueryTaAF = new LoanDeskaccQueryTaAF();
    try {
      // ��ѯ����
      String office = (String) pagination.getQueryCriterions().get("offic");
      String payBank = (String) pagination.getQueryCriterions().get("payBank");
      if (payBank != null) {
        String bank = collBankDAO.getCollBankByCollBankid_(payBank)
            .getCollBankName();
        pagination.getQueryCriterions().put("paybank_", bank);
      }
      String developer = (String) pagination.getQueryCriterions().get(
          "developer");
      String contactid = (String) pagination.getQueryCriterions().get(
          "contactid");
      String loankouacc = (String) pagination.getQueryCriterions().get(
          "loankouacc");
      String assistantorg = (String) pagination.getQueryCriterions().get(
          "assistantorg");
      String contact_st = (String) pagination.getQueryCriterions().get(
          "contact_st");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanMoneyB = (String) pagination.getQueryCriterions().get(
          "loanMoneyB");
      String loanMoneyE = (String) pagination.getQueryCriterions().get(
          "loanMoneyE");
      String loanLimitB = (String) pagination.getQueryCriterions().get(
          "loanLimitB");
      String loanLimitE = (String) pagination.getQueryCriterions().get(
          "loanLimitE");
      String loanStartDateB = (String) pagination.getQueryCriterions().get(
          "loanStartDateB");
      String loanStartDateE = (String) pagination.getQueryCriterions().get(
          "loanStartDateE");

      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String eare = (String) pagination.getQueryCriterions().get("area");

      String ageB = (String) pagination.getQueryCriterions().get("ageB");
      String ageE = (String) pagination.getQueryCriterions().get("ageE");
      String temp_check = (String) pagination.getQueryCriterions().get(
          "temp_check");
      String areaB = (String) pagination.getQueryCriterions().get("areaB");
      String areaE = (String) pagination.getQueryCriterions().get("areaE");
      String houseType = (String) pagination.getQueryCriterions().get(
          "houseType");
      String loanType = (String) pagination.getQueryCriterions()
          .get("loanType");
      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      String plBizDate = securityInfo.getUserInfo().getPlbizDate();
      String bizMonth = plBizDate.substring(0, 6);
      String bizDay = plBizDate.substring(6);
      if (temp_check != null && !temp_check.equals("")) {
        List list = borrowerAccDAO.showLoandeskaccqueryList(office, payBank,
            developer, contactid, loankouacc, assistantorg, contact_st,
            borrowerName, cardNum, loanMoneyB, loanMoneyE, loanLimitB,
            loanLimitE, loanStartDateB, loanStartDateE, ageB, ageE, orderBy,
            orderother, start, pageSize, page, securityInfo, orgId, eare,
            areaB, areaE, houseType, loanType);// ��ʾ�б���Ϣ��list
        // ����ҳ����Ҫ��Ȩ�޵ģ���Ȩ����ȡ����������Ϊ������������Ϊ��
        // ���������1.����Ϊ��2.����Ϊ��
        int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
        // ����Ȩ���еĻ��������ж�����Ϊ��
        if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
          // ����Ϊ��
          for (int i = 0; i < list.size(); i++) {
            LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = (LoandeskaccqueryTaDTO) list
                .get(i);
            CollBank collBank = collBankDAO
                .getCollBankByCollBankid_(loandeskaccqueryTaDTO.getPaybank());
            if (collBank != null) {
              loandeskaccqueryTaDTO.setPaybank(collBank.getCollBankName());
            }
            loandeskaccqueryTaDTO.setContact_st(BusiTools.getBusiValue(Integer
                .parseInt(loandeskaccqueryTaDTO.getContact_st()),
                BusiConst.PLCONTRACTSTATUS));
            // ������
            int payDay = Integer.parseInt(loandeskaccqueryTaDTO.getPayday());

            String loantimelimit = loandeskaccqueryTaDTO.getLoanlimit();// ��������
            String loanstartdate = loandeskaccqueryTaDTO.getLoanstartdate();// ������8λ
            String lefttime = loandeskaccqueryTaDTO.getOverlimited();// ʣ������
            String loanstartyearmonth = loanstartdate.substring(0, 6);// //������6λ
            int monthnum = BusiTools
                .monthInterval(loanstartyearmonth, bizMonth)
                - Integer.parseInt(loantimelimit) + Integer.parseInt(lefttime);
            String overmonthnum = "";
            if (Integer.parseInt(bizDay) > payDay) {
              overmonthnum = String.valueOf(monthnum);
            } else {
              overmonthnum = String.valueOf(monthnum - 1);
            }
            if (Integer.parseInt(overmonthnum) < 0) {
              overmonthnum = "0";
            }
            loandeskaccqueryTaDTO.setOwemonth(overmonthnum);// �ı���������
          }

          Object[] info = borrowerAccDAO.queryDeskAccTotalInfo(office, payBank,
              developer, contactid, loankouacc, assistantorg, contact_st,
              borrowerName, cardNum, loanMoneyB, loanMoneyE, loanLimitB,
              loanLimitE, loanStartDateB, loanStartDateE, ageB, ageE,
              securityInfo, orgId, eare, areaB, areaE, houseType, loanType);
          loanDeskaccQueryTaAF.setTolloanMoney(new BigDecimal(info[1]
              .toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
          loanDeskaccQueryTaAF.setTolovareloanrepay(info[2].toString());
          loanDeskaccQueryTaAF.setTolnobackmoney(info[3].toString());
          loanDeskaccQueryTaAF.setTolballbalance(info[4].toString());
          loanDeskaccQueryTaAF.setTolmonthpay(info[5].toString());
          loanDeskaccQueryTaAF.setTolrealcorpus(info[6].toString());
          loanDeskaccQueryTaAF.setTolrealinterest(info[7].toString());
          loanDeskaccQueryTaAF.setTolrealpunishinterest(info[8].toString());
          loanDeskaccQueryTaAF.setCount(info[0].toString());
          pagination.setNrOfElements(Integer.parseInt(info[0].toString()));
          loanDeskaccQueryTaAF.setHushu(Integer.parseInt(info[0].toString()));
          loanDeskaccQueryTaAF.setMianji(new BigDecimal(info[9].toString())
              .setScale(3, BigDecimal.ROUND_HALF_UP));
          loanDeskaccQueryTaAF.setFangwuzongjia(new BigDecimal(info[10]
              .toString()).setScale(2, BigDecimal.ROUND_HALF_UP));
          BigDecimal overplusMoney = new BigDecimal(info[11].toString());
          BigDecimal val = new BigDecimal(0.00);
          if (contactid == null && developer == null && loankouacc == null
              && assistantorg == null && contact_st == null
              && borrowerName == null && cardNum == null && loanMoneyB == null
              && loanMoneyE == null && loanLimitB == null && loanLimitE == null
              && loanStartDateB == null && loanStartDateE == null
              && ageB == null && ageE == null && orgId == null && eare == null
              && areaE == null && houseType == null && areaB == null) {
            if (payBank != null && !payBank.equals("")) {
              val = assistantOrgDAO.queryPl500CorpusConteract_(payBank);
            } else if (office != null && !office.equals("")) {
              val = assistantOrgDAO.queryPl500CorpusConteract_office(office);
            }
          }
          loanDeskaccQueryTaAF.setDkyue(overplusMoney.add(val).setScale(2,
              BigDecimal.ROUND_HALF_UP));
        }
        loanDeskaccQueryTaAF.setList(list);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanDeskaccQueryTaAF;
  }

  public List showLoandeskaccAllList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List allList = null;
    try {
      String office = (String) pagination.getQueryCriterions().get("offic");
      String payBank = (String) pagination.getQueryCriterions().get("payBank");
      if (payBank != null) {
        String bank = collBankDAO.getCollBankByCollBankid_(payBank)
            .getCollBankName();
        pagination.getQueryCriterions().put("paybank_", bank);
      }
      String developer = (String) pagination.getQueryCriterions().get(
          "developer");
      String contactid = (String) pagination.getQueryCriterions().get(
          "contactid");
      String loankouacc = (String) pagination.getQueryCriterions().get(
          "loankouacc");
      String assistantorg = (String) pagination.getQueryCriterions().get(
          "assistantorg");
      String contact_st = (String) pagination.getQueryCriterions().get(
          "contact_st");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanMoneyB = (String) pagination.getQueryCriterions().get(
          "loanMoneyB");
      String loanMoneyE = (String) pagination.getQueryCriterions().get(
          "loanMoneyE");
      String loanLimitB = (String) pagination.getQueryCriterions().get(
          "loanLimitB");
      String loanLimitE = (String) pagination.getQueryCriterions().get(
          "loanLimitE");
      String loanStartDateB = (String) pagination.getQueryCriterions().get(
          "loanStartDateB");
      String loanStartDateE = (String) pagination.getQueryCriterions().get(
          "loanStartDateE");

      String orgId = (String) pagination.getQueryCriterions().get("orgId");
      String eare = (String) pagination.getQueryCriterions().get("area");

      String ageB = (String) pagination.getQueryCriterions().get("ageB");
      String ageE = (String) pagination.getQueryCriterions().get("ageE");
      String temp_check = (String) pagination.getQueryCriterions().get(
          "temp_check");
      String areaB = (String) pagination.getQueryCriterions().get("areaB");
      String areaE = (String) pagination.getQueryCriterions().get("areaE");
      String houseType = (String) pagination.getQueryCriterions().get(
          "houseType");
      String loanType = (String) pagination.getQueryCriterions()
          .get("loanType");
      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      String plBizDate = securityInfo.getUserInfo().getPlbizDate();
      String bizMonth = plBizDate.substring(0, 6);
      String bizDay = plBizDate.substring(6);
      allList = borrowerAccDAO.showLoandeskaccqueryList(office, payBank,
          developer, contactid, loankouacc, assistantorg, contact_st,
          borrowerName, cardNum, loanMoneyB, loanMoneyE, loanLimitB,
          loanLimitE, loanStartDateB, loanStartDateE, ageB, ageE, orderBy,
          orderother, 0, 0, 0, securityInfo, orgId, eare, areaB, areaE,
          houseType, loanType);

      for (int i = 0; i < allList.size(); i++) {
        LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = (LoandeskaccqueryTaDTO) allList
            .get(i);
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid_(loandeskaccqueryTaDTO.getPaybank());
        if (collBank != null) {
          loandeskaccqueryTaDTO.setPaybank(collBank.getCollBankName());
        }
        loandeskaccqueryTaDTO.setContact_st(BusiTools.getBusiValue(Integer
            .parseInt(loandeskaccqueryTaDTO.getContact_st()),
            BusiConst.PLCONTRACTSTATUS));
        // ������
        int payDay = Integer.parseInt(loandeskaccqueryTaDTO.getPayday());

        String loantimelimit = loandeskaccqueryTaDTO.getLoanlimit();// ��������
        String loanstartdate = loandeskaccqueryTaDTO.getLoanstartdate();// ������8λ
        String lefttime = loandeskaccqueryTaDTO.getOverlimited();// ʣ������
        String loanstartyearmonth = loanstartdate.substring(0, 6);// //������6λ
        int monthnum = BusiTools.monthInterval(loanstartyearmonth, bizMonth)
            - Integer.parseInt(loantimelimit) + Integer.parseInt(lefttime);
        String overmonthnum = "";
        if (Integer.parseInt(bizDay) > payDay) {
          overmonthnum = String.valueOf(monthnum);
        } else {
          overmonthnum = String.valueOf(monthnum - 1);
        }
        if (Integer.parseInt(overmonthnum) < 0) {
          overmonthnum = "0";
        }
        loandeskaccqueryTaDTO.setOwemonth(overmonthnum);// �ı���������
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return allList;
  }

  /**
   * hanl ��ʾ������ˮ
   */
  public LoanDeskaccQueryTbAF showIndividualFlow(String contractIdHl,
      Pagination pagination, SecurityInfo securityInfo) throws Exception {
    LoanDeskaccQueryTbAF loanDeskaccQueryTbAF = new LoanDeskaccQueryTbAF();
    try {
      String plbizdate = securityInfo.getUserInfo().getPlbizDate();
      List list = borrowerAccDAO.showLoandeskaccqueryallList(null, null, null,
          null, contractIdHl, null, null, null, null, null, null, null, null,
          null, null, null, null, null, securityInfo, null, null, null, null,
          null, null, null, null, null);
      LoandeskaccqueryTaDTO loandeskaccqueryTaDTO = (LoandeskaccqueryTaDTO) list
          .get(0);
      loanDeskaccQueryTbAF.setContractid(loandeskaccqueryTaDTO.getContactid());
      loanDeskaccQueryTbAF.setLoankouacc(loandeskaccqueryTaDTO.getLoankouacc());
      loanDeskaccQueryTbAF.setBorrowername(loandeskaccqueryTaDTO
          .getBorrowername());
      loanDeskaccQueryTbAF.setCardnum(loandeskaccqueryTaDTO.getCardnum());
      CollBank collBank = collBankDAO
          .getCollBankByCollBankid_(loandeskaccqueryTaDTO.getPaybank());
      if (collBank != null) {
        loanDeskaccQueryTbAF.setLoanbank(collBank.getCollBankName());
      }
      loanDeskaccQueryTbAF.setLoanmoney(loandeskaccqueryTaDTO.getLoanmoney());
      loanDeskaccQueryTbAF.setLoanlimit(loandeskaccqueryTaDTO.getLoanlimit());
      loanDeskaccQueryTbAF.setNobackmoney(loandeskaccqueryTaDTO
          .getNobackmoney());
      loanDeskaccQueryTbAF.setOveaerloanrepay(loandeskaccqueryTaDTO
          .getOvareloanrepay());
      loanDeskaccQueryTbAF.setBallbalance(loandeskaccqueryTaDTO
          .getBallbalance());
      loanDeskaccQueryTbAF.setOverlimited(loandeskaccqueryTaDTO
          .getOverlimited());
      loanDeskaccQueryTbAF.setOtherArrearage(loandeskaccqueryTaDTO
          .getOtherArrearage().equals("0") ? "��" : "��");
      LoandeskaccqueryTaDTO dtor = loanFlowHeadDAO.queryRMoney(contractIdHl);// ���ݺ�ͬ������203���������
      String loanRepayDay = loandeskaccqueryTaDTO.getLoanRepayDay();
      String yearMonth = plbizdate.substring(0, 6);
      String days = plbizdate.substring(6, 8);
      if (new Integer(loanRepayDay).intValue() < new Integer(days).intValue()) {
        yearMonth = BusiTools.addMonths(yearMonth, 1);
      }
      String corputInterest = restoreLoanDAO.findthisMonthPay(
          loandeskaccqueryTaDTO.getContactid(), yearMonth);
      if (corputInterest == null || "".equals(corputInterest)) {
        yearMonth = BusiTools.addMonths(yearMonth, 1);
        corputInterest = restoreLoanDAO.findthisMonthPay(loandeskaccqueryTaDTO
            .getContactid(), yearMonth);
      }
      loanDeskaccQueryTbAF.setCorputInterest(new BigDecimal(corputInterest));
      loanDeskaccQueryTbAF.setSrealcorpus(dtor.getRealcorpus());
      loanDeskaccQueryTbAF.setSrealinterest(dtor.getRealinterest());
      loanDeskaccQueryTbAF.setSrealpunishinterest(dtor.getRealpunishinterest());
      LoandeskaccqueryTcDTO dtot = new LoandeskaccqueryTcDTO();
      dtot = borrowerAccDAO.queryloanMoneyLoanmood(contractIdHl); // �󻹿ʽ�ʹ������
      LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = new LoandeskaccqueryTcDTO();
      loandeskaccqueryTcDTO = borrowerAccDAO.findborrowerAccInfo(contractIdHl);// ���205�������Ǯ
      loanDeskaccQueryTbAF.setLoanmode(BusiTools.getBusiValue(Integer
          .parseInt(dtot.getPayloanmood()), BusiConst.PLRECOVERTYPE));
      loanDeskaccQueryTbAF.setOverplusloanmoney(dtot.getLoanleftmoney());
      // ����
      int temp_plLoanReturnTypes = securityInfo.getPlLoanReturnType();
      // ����Ȩ���еĻ��������ж�����Ϊ��
      if (temp_plLoanReturnTypes == BusiConst.PLLOANRETURNTYPE_CENTER) {
        // ����Ϊ��

        String payday = loandeskaccqueryTaDTO.getPayday();// ������
        String yearmonth = plbizdate.substring(0, 6);// ����
        String day = plbizdate.substring(6);// ����

        int dayint = Integer.parseInt(day);
        int paydayint = Integer.parseInt(payday);
        LoandeskaccqueryTcDTO loandeskaccqueryTcDTO1 = new LoandeskaccqueryTcDTO();
        if (dayint <= paydayint) {// �����С�ڵ��ڻ�����
          loandeskaccqueryTcDTO1 = restoreLoanDAO.findOweMoneya(contractIdHl,
              yearmonth);
        } else {
          loandeskaccqueryTcDTO1 = restoreLoanDAO.findOweMoneyb(contractIdHl,
              yearmonth);
        }
        loanDeskaccQueryTbAF.setOwercorpus(loandeskaccqueryTcDTO1
            .getOwecorpus());
        loanDeskaccQueryTbAF.setOweinterest(loandeskaccqueryTcDTO1
            .getOweinterest());
        loanDeskaccQueryTbAF.setOwepunishinterest(loandeskaccqueryTcDTO1
            .getPunishinterest());
      } else {
        // ������Ϊ��
        loanDeskaccQueryTbAF
            .setOwercorpus(loandeskaccqueryTcDTO.getOwecorpus());
        loanDeskaccQueryTbAF.setOweinterest(loandeskaccqueryTcDTO
            .getOweinterest());
        loanDeskaccQueryTbAF.setOwepunishinterest(loandeskaccqueryTcDTO
            .getPunishinterest());
      }
      loanDeskaccQueryTbAF.setShouldCorputInterest(new BigDecimal(
          loanDeskaccQueryTbAF.getOwercorpus()).add(
          new BigDecimal(loanDeskaccQueryTbAF.getOweinterest())).add(
          new BigDecimal(loanDeskaccQueryTbAF.getOwepunishinterest())).add(
          loanDeskaccQueryTbAF.getCorputInterest()));
      // ��ҳ���list������ҳ��
      String docnum = (String) pagination.getQueryCriterions().get("docnum");
      String biztype = (String) pagination.getQueryCriterions().get("biztype");
      String bizdateB = (String) pagination.getQueryCriterions()
          .get("bizdateB");
      String bizdateE = (String) pagination.getQueryCriterions()
          .get("bizdateE");

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List list1 = new ArrayList();

      list1 = loanFlowHeadDAO.findIndividualFlowList(contractIdHl, docnum,
          biztype, bizdateB, bizdateE, orderBy, orderother, start, pageSize,
          page, securityInfo);
      List newlist1 = new ArrayList();
      for (int i = 0; i < list1.size(); i++) {
        LoandeskaccqueryTbDTO loandeskaccqueryTbDTO = (LoandeskaccqueryTbDTO) list1
            .get(i);
        if (loandeskaccqueryTbDTO.getBatchNum() != null
            && !loandeskaccqueryTbDTO.getBatchNum().equals("")
            && !loandeskaccqueryTbDTO.getBatchNum().equals("��")) {
          String s = loanFlowHeadDAO.queryPL400SEQYG(contractIdHl,
              loandeskaccqueryTbDTO.getBatchNum(),loandeskaccqueryTbDTO.getYearmonth());
          if (s==null || s.equals("0")) {
            loandeskaccqueryTbDTO.setBatchNum("����˻���");
          } else {
            loandeskaccqueryTbDTO.setBatchNum("��ͬ����˻���");
          }
        }
        String biztypeone = loandeskaccqueryTbDTO.getBiztype();// ҵ������
        String wrongbiztype = loandeskaccqueryTbDTO.getWrongbiztype();// ��������

        if (biztypeone.equals("1") || biztypeone.equals("11")
            || (biztypeone.equals("12") && wrongbiztype.equals("1"))) {
          loandeskaccqueryTbDTO.setAccordmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ���Ž��
        }
        if (biztypeone.equals("2")
            || biztypeone.equals("3")
            || biztypeone.equals("4")
            || biztypeone.equals("5")
            || biztypeone.equals("13")
            || (biztypeone.equals("12") && (wrongbiztype.equals("2") || wrongbiztype
                .equals("5")))) {
          loandeskaccqueryTbDTO.setOverloanmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ���˽��
        }
        if (biztypeone.equals("6")
            || biztypeone.equals("7")
            || biztypeone.equals("8")
            || biztypeone.equals("9")
            || (biztypeone.equals("12") && (wrongbiztype.endsWith("6") || wrongbiztype
                .equals("7")))) {
          loandeskaccqueryTbDTO.setBaddebtmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ���˺������
        }
        if (biztypeone.equals("14") || biztypeone.equals("15")) {
          loandeskaccqueryTbDTO.setBailmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ��֤��
        }

        loandeskaccqueryTbDTO.setBiztype(BusiTools.getBusiValue(Integer
            .parseInt(loandeskaccqueryTbDTO.getBiztype()),
            BusiConst.PLBUSINESSTYPE));
        if (loandeskaccqueryTbDTO.getLoantype() != null
            && !loandeskaccqueryTbDTO.getLoantype().equals("")) {
          loandeskaccqueryTbDTO.setLoantype(BusiTools.getBusiValue(Integer
              .parseInt(loandeskaccqueryTbDTO.getLoantype()),
              BusiConst.GIVEBACK));
        } else {
          loandeskaccqueryTbDTO.setLoantype("");
        }
        newlist1.add(loandeskaccqueryTbDTO);

      }

      List allList = new ArrayList();
      allList = loanFlowHeadDAO.findIndividualFlowAllList(contractIdHl, docnum,
          biztype, bizdateB, bizdateE, securityInfo, orderBy, orderother);
      List newalllist = new ArrayList();
      for (int i = 0; i < allList.size(); i++) {
        LoandeskaccqueryTbDTO loandeskaccqueryTbDTO = (LoandeskaccqueryTbDTO) allList
            .get(i);
        if (loandeskaccqueryTbDTO.getBatchNum() != null
            && !loandeskaccqueryTbDTO.getBatchNum().equals("")
            && !loandeskaccqueryTbDTO.getBatchNum().equals("��")) {
          String s = loanFlowHeadDAO.queryPL400SEQYG(contractIdHl,
              loandeskaccqueryTbDTO.getBatchNum(),loandeskaccqueryTbDTO.getYearmonth());
          if (s==null || s.equals("0")) {
            loandeskaccqueryTbDTO.setBatchNum("����˻���");
          } else {
            loandeskaccqueryTbDTO.setBatchNum("��ͬ����˻���");
          }
        }
        String biztypeone = loandeskaccqueryTbDTO.getBiztype();// ҵ������
        String wrongbiztype = loandeskaccqueryTbDTO.getWrongbiztype();// ��������

        if (biztypeone.equals("1") || biztypeone.equals("11")
            || (biztypeone.equals("12") && wrongbiztype.equals("1"))) {
          loandeskaccqueryTbDTO.setAccordmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ���Ž��
        }
        if (biztypeone.equals("2")
            || biztypeone.equals("3")
            || biztypeone.equals("4")
            || biztypeone.equals("5")
            || biztypeone.equals("13")
            || (biztypeone.equals("12") && (wrongbiztype.equals("2") || wrongbiztype
                .equals("5")))) {
          loandeskaccqueryTbDTO.setOverloanmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ���˽��
        }
        if (biztypeone.equals("6")
            || biztypeone.equals("7")
            || biztypeone.equals("8")
            || biztypeone.equals("9")
            || (biztypeone.equals("12") && (wrongbiztype.endsWith("6") || wrongbiztype
                .equals("7")))) {
          loandeskaccqueryTbDTO.setBaddebtmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ���˺������
        }
        if (biztypeone.equals("14") || biztypeone.equals("15")) {
          loandeskaccqueryTbDTO.setBailmoney(loandeskaccqueryTbDTO
              .getOccurmoney());// ��֤��
        }

        loandeskaccqueryTbDTO.setBiztype(BusiTools.getBusiValue(Integer
            .parseInt(loandeskaccqueryTbDTO.getBiztype()),
            BusiConst.PLBUSINESSTYPE));
        if (loandeskaccqueryTbDTO.getLoantype() != null
            && !loandeskaccqueryTbDTO.getLoantype().equals("")) {
          loandeskaccqueryTbDTO.setLoantype(BusiTools.getBusiValue(Integer
              .parseInt(loandeskaccqueryTbDTO.getLoantype()),
              BusiConst.GIVEBACK));
        } else {
          loandeskaccqueryTbDTO.setLoantype("");
        }
        newalllist.add(loandeskaccqueryTbDTO);

      }

      // ���������ܼƵ�
      // �ܷ��Ž��
      BigDecimal tolaccordmoney = new BigDecimal(0.00);
      // �ܹ��˽��
      BigDecimal toloverloanmoney = new BigDecimal(0.00);
      // �ܴ��˺������
      BigDecimal tolbaddebtmoney = new BigDecimal(0.00);
      // �ܱ�֤��
      BigDecimal tolbailmoney = new BigDecimal(0.00);
      // ��ʵ������
      BigDecimal tolrealcorpus = new BigDecimal(0.00);
      // ��ʵ����Ϣ
      BigDecimal tolrealinterest = new BigDecimal(0.00);
      // ��ʵ����Ϣ
      BigDecimal tolrealpunishinterest = new BigDecimal(0.00);
      // �ܻ��ս��
      BigDecimal sumr = new BigDecimal(0.00);

      for (int k = 0; k < newalllist.size(); k++) {
        LoandeskaccqueryTbDTO loandeskaccqueryTbDTO = (LoandeskaccqueryTbDTO) newalllist
            .get(k);
        if (loandeskaccqueryTbDTO.getAccordmoney() != null
            && !loandeskaccqueryTbDTO.getAccordmoney().equals("")) {
          tolaccordmoney = tolaccordmoney.add(new BigDecimal(
              loandeskaccqueryTbDTO.getAccordmoney()));
        }
        if (loandeskaccqueryTbDTO.getOverloanmoney() != null
            && !loandeskaccqueryTbDTO.getOverloanmoney().equals("")) {
          toloverloanmoney = toloverloanmoney.add(new BigDecimal(
              loandeskaccqueryTbDTO.getOverloanmoney()));
        }
        if (loandeskaccqueryTbDTO.getBaddebtmoney() != null
            && !loandeskaccqueryTbDTO.getBaddebtmoney().equals("")) {
          tolbaddebtmoney = tolbaddebtmoney.add(new BigDecimal(
              loandeskaccqueryTbDTO.getBaddebtmoney()));
        }
        if (loandeskaccqueryTbDTO.getBailmoney() != null
            && !loandeskaccqueryTbDTO.getBailmoney().equals("")) {
          tolbailmoney = tolbailmoney.add(new BigDecimal(loandeskaccqueryTbDTO
              .getBailmoney()));
        }
        if (loandeskaccqueryTbDTO.getRealcorpus() != null
            && !loandeskaccqueryTbDTO.getRealcorpus().equals("")) {
          tolrealcorpus = tolrealcorpus.add(new BigDecimal(
              loandeskaccqueryTbDTO.getRealcorpus()));
        }
        if (loandeskaccqueryTbDTO.getRealinterest() != null
            && !loandeskaccqueryTbDTO.getRealinterest().equals("")) {
          tolrealinterest = tolrealinterest.add(new BigDecimal(
              loandeskaccqueryTbDTO.getRealinterest()));
        }
        if (loandeskaccqueryTbDTO.getRealpunishinterest() != null
            && !loandeskaccqueryTbDTO.getRealpunishinterest().equals("")) {
          tolrealpunishinterest = tolrealpunishinterest.add(new BigDecimal(
              loandeskaccqueryTbDTO.getRealpunishinterest()));
        }
      }
      sumr = sumr.add(tolrealcorpus).add(tolrealinterest).add(
          tolrealpunishinterest);
      loanDeskaccQueryTbAF.setTolaccordmoney(tolaccordmoney.toString());
      loanDeskaccQueryTbAF.setToloverloanmoney(toloverloanmoney.toString());
      loanDeskaccQueryTbAF.setTolbaddebtmoney(tolbaddebtmoney.toString());
      loanDeskaccQueryTbAF.setTolbailmoney(tolbailmoney.toString());
      loanDeskaccQueryTbAF.setTolrealcorpus(tolrealcorpus.toString());
      loanDeskaccQueryTbAF.setTolrealinterest(tolrealinterest.toString());
      loanDeskaccQueryTbAF.setTolrealpunishinterest(tolrealpunishinterest
          .toString());
      loanDeskaccQueryTbAF.setSumr(sumr.toString());
      pagination.setNrOfElements(newalllist.size());
      // loanDeskaccQueryTbAF.setCount(String.valueOf(newalllist.size()));
      loanDeskaccQueryTbAF.setCount(new Integer(newalllist.size()).toString());
      loanDeskaccQueryTbAF.setList(newlist1);
      loanDeskaccQueryTbAF.setAlllist(newalllist);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return loanDeskaccQueryTbAF;
  }

}
