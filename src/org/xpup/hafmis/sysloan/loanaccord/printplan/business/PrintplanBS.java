package org.xpup.hafmis.sysloan.loanaccord.printplan.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.bsinterface.IPrintplanBS;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;


public class PrintplanBS implements IPrintplanBS {
  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private CollBankDAO collBankDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  // ���Һ�ͬ�����pl111�Ķ�Ӧ��201�е���Ϣ��������������Ϊ�˷�ҳ������
  public PrintplanShowAF findPrintplanList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List printList = new ArrayList();
    PrintplanShowAF printplanShowAF = new PrintplanShowAF();
    BigDecimal interestMoney = new BigDecimal(0.00);
    try {
      List list = new ArrayList();
      List returnlist = new ArrayList();
      List restoreLoanlist = new ArrayList();
      List printplanAllList = new ArrayList();
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      boolean isFindContractid = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractId);
      if (!isFindContractid) {
        throw new BusinessException("�˺�ͬ��Ų����ڣ�");
      }
      interestMoney = borrowerLoanInfoDAO.queryInterest_jj(contractId);
      // ��ͬ��״̬
      String contractSt = "10,11";
      list = borrowerLoanInfoDAO.queryBorrowerLoanTbInfo_sy(contractSt,
          contractId, securityInfo);
      if (!list.isEmpty()) {
        PrintplanDTO printplanDTO = (PrintplanDTO) list.get(0);
        // ��ͬ���
        printplanDTO.setContractId(contractId);
        // ֤�����Ͷ�Ӧ������
        printplanDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(printplanDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // ͨ��bankId�������е�����
        String bankId = printplanDTO.getLoanBankId();
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
        printplanDTO.setLoanBankName(collBank.getCollBankName());
        // ���ʽ
        printplanDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(printplanDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        printplanShowAF.setPrintplanDTO(printplanDTO);
        printplanShowAF.setInterest(interestMoney);
        if (printplanDTO.getContractSt().equals("11")) {
          restoreLoanlist = restoreLoanDAO.queryRestoreLoanListByContractId_sy(contractId, orderBy, order, start, pageSize);
          if (!restoreLoanlist.isEmpty()) {
            for (int j = 0; j < restoreLoanlist.size(); j++) {
              PrintplanListDTO printplanListDTO = new PrintplanListDTO();
              printplanListDTO = (PrintplanListDTO) restoreLoanlist.get(j);
              printplanListDTO.setCiMoney(printplanListDTO.getShouldCorpus()
                  .add(printplanListDTO.getShouldInterest()));
              printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                  .multiply(new BigDecimal(100.00)).toString()
                  + "%");
              returnlist.add(printplanListDTO);
            }
          }
          // ��ӡ׼������
          printplanAllList = restoreLoanDAO
              .countRestoreLoanListByContractId_sy(contractId);
          if (!printplanAllList.isEmpty()) {
            for (int j = 0; j < printplanAllList.size(); j++) {
              PrintplanListDTO printplanListDTO = new PrintplanListDTO();
              printplanListDTO = (PrintplanListDTO) printplanAllList.get(j);
              printplanListDTO.setCiMoney(printplanListDTO.getShouldCorpus()
                  .add(printplanListDTO.getShouldInterest()));
              printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                  .multiply(new BigDecimal(100.00)).toString()
                  + "%");
              printList.add(printplanListDTO);
            }
          }
        }
        if (printplanDTO.getContractSt().equals("10")) {
          restoreLoanDAO.deleteFnTempTable();
          String loanKouYearMonth = "";// ��������
          BigDecimal loanMonthRate = new BigDecimal(0.00);// ��������
          // ��ѯpl115
          Object[] obj = new Object[3];
          obj = borrowerLoanInfoDAO.querybyContractId(printplanDTO
              .getContractId());
          // ��ѯpl001
          Object[] object = new Object[2];
          object = borrowerLoanInfoDAO.querybyLoanBankId(obj[1].toString(),
              bankId);
          if (object[0].toString().equals("0")) {
            loanMonthRate = new BigDecimal(obj[2].toString());
          } else {
            loanMonthRate = new BigDecimal(object[1].toString());
          }
          // �������»���Ϣ
          BigDecimal loanMoney = printplanDTO.getLoanMoney();// ������&&ʣ�౾��
          BigDecimal newMonthRepay = CorpusinterestBS.getCorpusInterest(
              loanMoney, loanMonthRate, printplanDTO.getLoanTimeLimit());
          String loanRepayDay = printplanDTO.getLoanRepayDay();// ������
          String loanStartDate = printplanDTO.getLoanStartDate();// ���������
          String startDate = BusiTools.addMonth(loanStartDate.substring(0, 6),
              1);// ��ʼ����
          int type=13;
          int temp_i=0;
          if(Integer.parseInt(startDate.substring(4, 6))!=12){
            temp_i=Integer.parseInt(startDate.substring(4, 6));
          }
          if (Integer.parseInt(loanRepayDay) > 28) {
            int temp_loanRepayDay = BusiTools.daysOfMonth(Integer
                .parseInt(startDate.substring(0, 4)), Integer
                .parseInt(startDate.substring(4, 6)));
            if (Integer.parseInt(loanRepayDay) > temp_loanRepayDay) {
              loanRepayDay = String.valueOf(temp_loanRepayDay);
            }
          }
          if (Integer.parseInt(printplanDTO.getLoanMode()) > 3) {
            // �����ڴ���
            loanKouYearMonth = BusiTools.addMonth(startDate, Integer
                .parseInt(printplanDTO.getLoanTimeLimit()));
            String corpusInterest = obj[0].toString();
            BigDecimal shouldInterest = new BigDecimal(corpusInterest)
                .subtract(loanMoney);
            PrintplanListDTO printplanListDTO = new PrintplanListDTO();
            printplanListDTO.setLoanKouYearmonth(loanKouYearMonth);
            printplanListDTO.setShouldCorpus(loanMoney);
            printplanListDTO.setShouldInterest(shouldInterest);
            printplanListDTO.setCiMoney(printplanListDTO.getShouldCorpus().add(
                printplanListDTO.getShouldInterest()));
            printplanListDTO.setLoanRate(loanMonthRate);
            printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                .multiply(new BigDecimal(100.00)).toString()
                + "%");
            restoreLoanDAO.insertFnTempTable(printplanListDTO);
//            returnlist.add(printplanListDTO);
          } else if (Integer.parseInt(printplanDTO.getLoanMode()) == 2) {
            // �ȶϢ
            String tempstartDate = startDate;
            for (int i = temp_i; i < type; i++) {
              // ������13��
              int tempmonth = BusiTools.getDisMonths(tempstartDate.substring(0, 4)+"-"+tempstartDate.substring(4, 6)+"-01",
                  loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-01");
              if (tempmonth > Integer.parseInt(printplanDTO.getLoanTimeLimit())) {
                // ��ʼ����-�������ڣ����£��������Ƿ���ڴ�������
                break;
              }
              if (i == 0) {
                // ��һ����
                List param = loanBankParaDAO.queryLoanBankPara_sy(bankId, "2",
                    "A");
                if (param.size() > 0) {
                  Object[] value=new Object[2];
                  value = (Object[]) param.get(0);
                  if (value[0].toString().equals("1")) {
                    // ��������
                    BigDecimal shouldInterest = loanMoney
                        .multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(newMonthRepay
                        .subtract(shouldInterest));
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(newMonthRepay);
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    //ʣ�౾��=ʣ�౾��-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(newMonthRepay.subtract(shouldInterest));
                  } else {
                    // ͳһ����
                    int tempDay = BusiTools.minusDate(startDate.substring(0, 4)+"-"+startDate.substring(4, 6) +"-"+ loanRepayDay,
                        loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-"+loanStartDate.substring(6, 8));
                    // Ӧ����Ϣ=ʣ�౾��*������*�����µ�LOAN_REPAY_DAY-�������ڣ�/30
                    BigDecimal shouldInterest = loanMoney.multiply(
                        loanMonthRate).multiply(new BigDecimal(tempDay))
                        .divide(new BigDecimal("30"), 2,
                            BigDecimal.ROUND_HALF_UP);
                    // Ӧ������=�»���Ϣ-ʣ�౾��*������
                    BigDecimal temp = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal shouldCorpus = newMonthRepay.subtract(temp);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(shouldCorpus);
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(shouldCorpus
                        .add(shouldInterest));
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    // ʣ�౾��=ʣ�౾��-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(shouldCorpus);
                  }
                }
              }
              if ((i>0)&&(tempmonth < Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                // �м���
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(newMonthRepay
                    .subtract(shouldInterest));
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(newMonthRepay);
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
                //ʣ�౾��=ʣ�౾��-SHOULD_CORPUS
                loanMoney = loanMoney.subtract(newMonthRepay.subtract(shouldInterest));
              }
              if ((i>0)&&(tempmonth == Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                //���һ����
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(loanMoney);
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(loanMoney.add(shouldInterest));
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
              }
              tempstartDate = BusiTools.addMonth(tempstartDate.substring(0, 6),
                  1);// ��ʼ����
            }
          } else if (Integer.parseInt(printplanDTO.getLoanMode()) < 4) {
            // �ȶ�������
            //����Ӧ������
            BigDecimal shouldCorpus=loanMoney.divide(new BigDecimal(printplanDTO.getLoanTimeLimit()), 2,
                BigDecimal.ROUND_HALF_UP);
            String tempstartDate = startDate;
            for (int i = temp_i; i < type; i++) {
              // ������13��
              int tempmonth = BusiTools.getDisMonths(tempstartDate.substring(0, 4)+"-"+tempstartDate.substring(4, 6)+"-01",
                  loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-01");
              if (tempmonth > Integer.parseInt(printplanDTO.getLoanTimeLimit())) {
                // ��ʼ����-�������ڣ����£��������Ƿ���ڴ�������
                break;
              }
              if (i == 0) {
                // ��һ����
                List param = loanBankParaDAO.queryLoanBankPara_sy(bankId, "2",
                    "A");
                if (param.size() > 0) {
                  Object[] value=new Object[2];
                  value = (Object[]) param.get(0);
                  if (value[0].toString().equals("1")) {
                    // ��������
                    BigDecimal shouldInterest = loanMoney
                        .multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(shouldCorpus);
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(shouldCorpus.add(shouldInterest));
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    //ʣ�౾��=ʣ�౾��-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(shouldCorpus);
                  } else {
                    // ͳһ����
                    int tempDay = BusiTools.minusDate(startDate.substring(0, 4)+"-"+startDate.substring(4, 6) +"-"+ loanRepayDay,
                        loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-"+loanStartDate.substring(6, 8));
                    // Ӧ����Ϣ=ʣ�౾��*������*�����µ�LOAN_REPAY_DAY-�������ڣ�/30
                    BigDecimal shouldInterest = loanMoney.multiply(
                        loanMonthRate).multiply(new BigDecimal(tempDay))
                        .divide(new BigDecimal("30"), 2,
                            BigDecimal.ROUND_HALF_UP);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(shouldCorpus);
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(shouldCorpus
                        .add(shouldInterest));
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    // ʣ�౾��=ʣ�౾��-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(shouldCorpus);
                  }
                }
              }
              if ((i>0)&&(tempmonth < Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                // �м���
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(shouldCorpus);
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(shouldCorpus.add(shouldInterest));
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
                //ʣ�౾��=ʣ�౾��-SHOULD_CORPUS
                loanMoney = loanMoney.subtract(shouldCorpus);
              }
              if ((i>0)&&(tempmonth == Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                //���һ����
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(loanMoney);
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(loanMoney.add(shouldInterest));
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
              }
              tempstartDate = BusiTools.addMonth(tempstartDate.substring(0, 6),
                  1);// ��ʼ����
            }
          }

          returnlist = restoreLoanDAO.queryFnTempTable(orderBy, order, start, pageSize);
          // ��ӡ׼������
          printplanAllList = restoreLoanDAO.queryFnTempTableAll();
          if (!printplanAllList.isEmpty()) {
            for (int j = 0; j < printplanAllList.size(); j++) {
              PrintplanListDTO printplanListDTO = new PrintplanListDTO();
              printplanListDTO = (PrintplanListDTO) printplanAllList.get(j);
              printList.add(printplanListDTO);
            }
          }
        
        }
        pagination.setNrOfElements(printplanAllList.size());
        printplanShowAF.setPrintList(printList);
        printplanShowAF.setList(returnlist);
        printplanShowAF.setBizDate(securityInfo.getUserInfo().getPlbizDate());
        printplanShowAF.setOperson(securityInfo.getRealName());
      } else {
        throw new BusinessException("�˺�ͬ��Ż�Ϊ���ˣ�");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return printplanShowAF;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

}
