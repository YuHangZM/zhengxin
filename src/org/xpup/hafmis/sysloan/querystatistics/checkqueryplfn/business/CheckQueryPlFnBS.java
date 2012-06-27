package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.business;

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
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.bsinterface.ICheckQueryPlFnBS;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnDTO;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnTBDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTcDTO;

public class CheckQueryPlFnBS implements ICheckQueryPlFnBS {
  private BorrowerAccDAO borrowerAccDAO = null;

  private CollBankDAO collBankDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;
  
  private BorrowerDAO borrowerDAO = null;

  /*
   * ����������˲�ѯ ��ѯҳ���б���Ϣ
   */
  public List showCheckQueryPlFnList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    // ��ѯ����
    String contractid = (String) pagination.getQueryCriterions().get(
        "contractid");// ����ͬ���
    String borrowername = (String) pagination.getQueryCriterions().get(
        "borrowername");// ���������
    String loankouacc = (String) pagination.getQueryCriterions().get(
        "loankouacc");// �����˺�
    String cardnum = (String) pagination.getQueryCriterions().get("cardnum");// ֤������
    String loanstartdateSt = (String) pagination.getQueryCriterions().get(
        "loanstartdateSt");// ��������ڿ�ʼ
    String loanstartdateEnd = (String) pagination.getQueryCriterions().get(
        "loanstartdateEnd");// ��������ڽ���
    String orderBy = (String) pagination.getOrderBy();
    String orderother = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List returnList = new ArrayList();
    List list = new ArrayList();
    list = borrowerAccDAO.queryCheckQueryPlFnList(contractid, borrowername,
        loankouacc, cardnum, loanstartdateSt, loanstartdateEnd, orderBy,
        orderother, start, pageSize, page, securityInfo);
    for (int i = 0; i < list.size(); i++) {
      CheckQueryPlFnDTO checkQueryPlFnDTO = (CheckQueryPlFnDTO) list.get(i);
      CollBank collBank = collBankDAO
          .getCollBankByCollBankid_(checkQueryPlFnDTO.getLoanbank());
      if (collBank != null) {
        checkQueryPlFnDTO.setLoanbankname(collBank.getCollBankName());
      }
      checkQueryPlFnDTO.setTemp_loanmode(BusiTools.getBusiValue(Integer
          .parseInt(checkQueryPlFnDTO.getLoanmode()), BusiConst.PLRECOVERTYPE));
      LoandeskaccqueryTcDTO loandeskaccqueryTcDTO = new LoandeskaccqueryTcDTO();
      loandeskaccqueryTcDTO = borrowerAccDAO
          .findborrowerAccInfo(checkQueryPlFnDTO.getContractid());
      int temp_plLoanReturnTypes = securityInfo.getPlLoanReturnType();
      // ����Ȩ���еĻ��������ж�����Ϊ��
      if (temp_plLoanReturnTypes == BusiConst.PLLOANRETURNTYPE_CENTER) {
        // ����Ϊ��
        String payday = checkQueryPlFnDTO.getPayday();// ������
        String plbizdate = securityInfo.getUserInfo().getPlbizDate();
        String yearmonth = plbizdate.substring(0, 6);// ����
        String day = plbizdate.substring(6);// ����

        int dayint = Integer.parseInt(day);
        int paydayint = Integer.parseInt(payday);
        LoandeskaccqueryTcDTO loandeskaccqueryTcDTO1 = new LoandeskaccqueryTcDTO();
        if (dayint <= paydayint) {// �����С�ڵ��ڻ�����
          loandeskaccqueryTcDTO1 = restoreLoanDAO.findOweMoneya(
              checkQueryPlFnDTO.getContractid(), yearmonth);
        } else {
          loandeskaccqueryTcDTO1 = restoreLoanDAO.findOweMoneyb(
              checkQueryPlFnDTO.getContractid(), yearmonth);
        }
        checkQueryPlFnDTO.setOwercorpus(loandeskaccqueryTcDTO1.getOwecorpus());
        checkQueryPlFnDTO.setOweinterest(loandeskaccqueryTcDTO1
            .getOweinterest());
        checkQueryPlFnDTO.setOwepunishinterest(loandeskaccqueryTcDTO1
            .getPunishinterest());
      } else {
        // ������Ϊ��
        checkQueryPlFnDTO.setOwercorpus(loandeskaccqueryTcDTO.getOwecorpus());
        checkQueryPlFnDTO
            .setOweinterest(loandeskaccqueryTcDTO.getOweinterest());
        checkQueryPlFnDTO.setOwepunishinterest(loandeskaccqueryTcDTO
            .getPunishinterest());
      }
      returnList.add(checkQueryPlFnDTO);
    }
    List countList = new ArrayList();
    countList = borrowerAccDAO.queryCheckQueryPlFnListCount(contractid,
        borrowername, loankouacc, cardnum, loanstartdateSt, loanstartdateEnd,
        securityInfo);
    pagination.setNrOfElements(countList.size());
    return returnList;
  }

  /*
   * ����������˲�ѯ����������˵�ҳ���б��ѯ
   */
  public Object[] showCheckQueryPlFnTBList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    Object[] obj=new Object[2];
    // ��ѯ����
    String contractid = (String) pagination.getQueryCriterions().get(
    "contractid");// ���´�
    String officecode = (String) pagination.getQueryCriterions().get(
        "officecode");// ���´�
    String loanbank = (String) pagination.getQueryCriterions().get("loanbank");// �鼯����
    String empid = (String) pagination.getQueryCriterions().get("empid");// ְ�����
    String empname = (String) pagination.getQueryCriterions().get("empname");// ְ������
    String plbizst = (String) pagination.getQueryCriterions().get("plbizst");// ����ҵ��״̬
    String bizdateSt = (String) pagination.getQueryCriterions()
        .get("bizdateSt");// ��������
    String bizdateEnd = (String) pagination.getQueryCriterions().get(
        "bizdateEnd");// ��������
    String fnbizst = (String) pagination.getQueryCriterions().get("fnbizst");// ����ҵ��״̬
    String orderBy = (String) pagination.getOrderBy();
    String orderother = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List returnList = new ArrayList();
    List list = new ArrayList();
    list = borrowerAccDAO.queryCheckQueryPlFnTBList(contractid,officecode, loanbank,
        empid, empname, plbizst, bizdateSt, bizdateEnd, fnbizst, orderBy,
        orderother, start, pageSize, page, securityInfo);
    for (int i = 0; i < list.size(); i++) {
      CheckQueryPlFnTBDTO checkQueryPlFnTBDTO=(CheckQueryPlFnTBDTO)list.get(i);
      if(!checkQueryPlFnTBDTO.getPlbizst().equals("")){
        checkQueryPlFnTBDTO.setTemp_plbizst(BusiTools.getBusiValue(Integer
            .parseInt("" + checkQueryPlFnTBDTO.getPlbizst()),
            BusiConst.PLBUSINESSSTATE));
      }
      if(!checkQueryPlFnTBDTO.getBiztype().equals("")){
        checkQueryPlFnTBDTO.setTemp_biztype(BusiTools.getBusiValue(Integer
            .parseInt("" + checkQueryPlFnTBDTO.getBiztype()),
            BusiConst.PLBUSINESSTYPE));
      }
      if(!checkQueryPlFnTBDTO.getCredenceSt().equals("")){
        checkQueryPlFnTBDTO.setTemp_credenceSt(BusiTools.getBusiValue(Integer
            .parseInt("" + checkQueryPlFnTBDTO.getCredenceSt()),
            BusiConst.CREDSTATE));
      }
      if(!checkQueryPlFnTBDTO.getTemp_plbizst().equals(checkQueryPlFnTBDTO.getTemp_credenceSt())){
        checkQueryPlFnTBDTO.setType("0");
      }
      returnList.add(checkQueryPlFnTBDTO);
    }
    List countList = new ArrayList();
    List reultcountList = new ArrayList();
    countList = borrowerAccDAO.queryCheckQueryPlFnTBListCount(contractid, officecode, loanbank, empid, empname, plbizst, bizdateSt, bizdateEnd, fnbizst, securityInfo);
    pagination.setNrOfElements(countList.size());
    for (int i = 0; i < countList.size(); i++) {
      CheckQueryPlFnTBDTO checkQueryPlFnTBDTO=(CheckQueryPlFnTBDTO)countList.get(i);
      if(!checkQueryPlFnTBDTO.getPlbizst().equals("")){
        checkQueryPlFnTBDTO.setTemp_plbizst(BusiTools.getBusiValue(Integer
            .parseInt("" + checkQueryPlFnTBDTO.getPlbizst()),
            BusiConst.PLBUSINESSSTATE));
      }
      if(!checkQueryPlFnTBDTO.getBiztype().equals("")){
        checkQueryPlFnTBDTO.setTemp_biztype(BusiTools.getBusiValue(Integer
            .parseInt("" + checkQueryPlFnTBDTO.getBiztype()),
            BusiConst.PLBUSINESSTYPE));
      }
      if(!checkQueryPlFnTBDTO.getCredenceSt().equals("")){
        checkQueryPlFnTBDTO.setTemp_credenceSt(BusiTools.getBusiValue(Integer
            .parseInt("" + checkQueryPlFnTBDTO.getCredenceSt()),
            BusiConst.CREDSTATE));
      }
      reultcountList.add(checkQueryPlFnTBDTO);
    }
    obj[0]=returnList;
    obj[1]=reultcountList;
    return obj;
  }
  /*
   * ��ѯְ����ˮҳ�����ʾ�б�
   */
  public LoanBusiFlowQueryAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanBusiFlowQueryAF loanBusiFlowQueryAF = new LoanBusiFlowQueryAF();
    String notenum = (String) pagination.getQueryCriterions().get("notenum");
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
    List loanBusiFlowQueryList = new ArrayList();
    List printList = new ArrayList();// ��ӡ�б�
    List templist = new ArrayList();
    List temptotlelist = new ArrayList();
    templist = borrowerAccDAO.queryCheckQueryPlFnTCList(start,
        orderBy, order, pageSize, page, securityInfo, docNum, contractId,
        loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName,
        beginBizDate, endBizDate,notenum);
    temptotlelist = borrowerAccDAO.queryCheckQueryPlFnTCListCount(
        securityInfo, docNum, contractId, loanKouAcc, borrowerName, makePerson,
        bizType, bizSt, loanBankName, beginBizDate, endBizDate, notenum);
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
    loanBusiFlowQueryAF.setPrintList(printList);
    pagination.setNrOfElements(count);
    return loanBusiFlowQueryAF;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public List showContrctList(String empId, String empName, String startTime, String endTime, String contracId, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List list=new ArrayList();
    try {
      list=borrowerDAO.queryListByCondition_wsh(contracId, empName,null , empId, securityInfo);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public List showContrctList_print(String empId, String empName, String startTime, String endTime, String contracId, String contractIdEnd, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List list=new ArrayList();
    try {
      list=borrowerDAO.queryListByCondition_wsh_print(contracId,contractIdEnd, empName,null , empId, securityInfo);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }



 
}
