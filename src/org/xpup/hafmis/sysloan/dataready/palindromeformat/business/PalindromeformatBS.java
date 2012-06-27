package org.xpup.hafmis.sysloan.dataready.palindromeformat.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BankpalindromeDAO;
import org.xpup.hafmis.sysloan.common.dao.PalindromFormatHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.PalindromeFormatTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Bankpalindrome;
import org.xpup.hafmis.sysloan.common.domain.entity.PalindromFormatHead;
import org.xpup.hafmis.sysloan.common.domain.entity.PalindromeFormatTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.palindromeformat.bsinterface.IPalindromeformatBS;
import org.xpup.hafmis.sysloan.dataready.palindromeformat.form.PalindromeformatAF;

/**
 * @author yuqf 2007-12-20
 */
public class PalindromeformatBS implements IPalindromeformatBS {

  private PalindromFormatHeadDAO palindromFormatHeadDAO = null;

  private PalindromeFormatTailDAO palindromeFormatTailDAO = null;

  private BankpalindromeDAO bankpalindromeDAO = null;// PL010 ���л��ĸ�ʽ����

  private PlOperateLogDAO plOperateLogDAO = null;// PL021 ������־

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setBankpalindromeDAO(BankpalindromeDAO bankpalindromeDAO) {
    this.bankpalindromeDAO = bankpalindromeDAO;
  }

  public void setPalindromeFormatTailDAO(
      PalindromeFormatTailDAO palindromeFormatTailDAO) {
    this.palindromeFormatTailDAO = palindromeFormatTailDAO;
  }

  public void setPalindromFormatHeadDAO(
      PalindromFormatHeadDAO palindromFormatHeadDAO) {
    this.palindromFormatHeadDAO = palindromFormatHeadDAO;
  }

  /**
   * ��ѯ
   */
  public PalindromeformatAF queryByBank(SecurityInfo securityInfo,
      Pagination pagination, String bankId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    PalindromeformatAF palindromeformatAF = new PalindromeformatAF();
    String value = (String) pagination.getQueryCriterions().get("key");
    
    if ("value".equals(value)) {// �����ѯ��ť

      String loanId = "";// �����ʺ� 1
      String name = "";// ���� 2
      String payDate = "";// �������� 3
      String realCorpus = "";// ʵ���������� 4
      String realOverdueCorpus = "";// ʵ�����ڱ��� 5
      String realInterest = "";// ʵ��������Ϣ 6
      String realOverdueInterest = "";// ʵ��������Ϣ 7
      String realPunishInterest = "";// ʵ�۷�Ϣ 8
      String nobackCorpus = "";// δ���������� 9
      String nobackOverdueCorpus = "";// δ�����ڱ��� 10
      String nobackInterest = "";// δ��������Ϣ 11
      String nobackOverdueInterest = "";// δ��������Ϣ 12
      String nobackPunishInterest = "";// δ����Ϣ 13
      String deadLine = "";// ��ǰ�����ʣ������ 14

      loanId = palindromFormatHeadDAO.queryByBankId(bankId, "1");
      name = palindromFormatHeadDAO.queryByBankId(bankId, "2");
      payDate = palindromFormatHeadDAO.queryByBankId(bankId, "3");
      realCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "4");
      realOverdueCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "5");
      realInterest = palindromFormatHeadDAO.queryByBankId(bankId, "6");
      realOverdueInterest = palindromFormatHeadDAO.queryByBankId(bankId, "7");
      realPunishInterest = palindromFormatHeadDAO.queryByBankId(bankId, "8");
      nobackCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "9");
      nobackOverdueCorpus = palindromFormatHeadDAO.queryByBankId(bankId, "10");
      nobackInterest = palindromFormatHeadDAO.queryByBankId(bankId, "11");
      nobackOverdueInterest = palindromFormatHeadDAO
          .queryByBankId(bankId, "12");
      nobackPunishInterest = palindromFormatHeadDAO.queryByBankId(bankId, "13");
      deadLine = palindromFormatHeadDAO.queryByBankId(bankId, "14");
      palindromeformatAF.setLoanId(loanId); // 1 //���� ʱ�� ---- ��Ӧ����
      palindromeformatAF.setName(name); // 2 //���� ���д��� ---- ��Ӧ����
      palindromeformatAF.setPayDate(payDate); // 3 //���� �����˺ţ��ۿ��˺ţ� ---- ��Ӧ����
      palindromeformatAF.setRealCorpus(realCorpus); // 4 //���� ���� ---- ��Ӧ����
      palindromeformatAF.setRealOverdueCorpus(realOverdueCorpus); // 5//���� ��������
      palindromeformatAF.setRealInterest(realInterest); // 6 //����
      palindromeformatAF.setRealOverdueInterest(realOverdueInterest); // 7 //����
      palindromeformatAF.setRealPunishInterest(realPunishInterest); // 8 //����
      palindromeformatAF.setNobackCorpus(nobackCorpus); // 9
      palindromeformatAF.setNobackOverdueCorpus(nobackOverdueCorpus); // 10
      palindromeformatAF.setNobackInterest(nobackInterest); // 11
      palindromeformatAF.setNobackOverdueInterest(nobackOverdueInterest); // 12
      palindromeformatAF.setNobackPunishInterest(nobackPunishInterest); // 13
      palindromeformatAF.setDeadLine(deadLine); // 14
      palindromeformatAF.setBankId(bankId);
    }
    return palindromeformatAF;
  }

  /**
   * ���� OR ����
   */
  public void insertSet(SecurityInfo securityInfo,
      PalindromeformatAF palindromeformatAF, String bankId) throws Exception,
      BusinessException {
    // TODO Auto-generated method stub
    String loanId = "";// �����ʺ�
    String name = "";// ����
    String payDate = "";// ��������
    String realCorpus = "";// ʵ����������
    String realOverdueCorpus = "";// ʵ�����ڱ���
    String realInterest = "";// ʵ��������Ϣ
    String realOverdueInterest = "";// ʵ��������Ϣ
    String realPunishInterest = "";// ʵ�۷�Ϣ
    String nobackCorpus = "";// δ����������
    String nobackOverdueCorpus = "";// δ�����ڱ���
    String nobackInterest = "";// δ��������Ϣ
    String nobackOverdueInterest = "";// δ��������Ϣ
    String nobackPunishInterest = "";// δ����Ϣ
    String deadLine = "";// ��ǰ�����ʣ������

    loanId = palindromeformatAF.getLoanId();// �����ʺ� 1
    if (loanId == null) {
      loanId = " ";
    }
    name = palindromeformatAF.getName();// ���� 2
    if (name == null) {
      name = " ";
    }
    payDate = palindromeformatAF.getPayDate();// �������� 3
    if (payDate == null) {
      payDate = " ";
    }
    realCorpus = palindromeformatAF.getRealCorpus();// ʵ���������� 4
    if (realCorpus == null) {
      realCorpus = " ";
    }
    realOverdueCorpus = palindromeformatAF.getRealOverdueCorpus();// ʵ�����ڱ��� 5
    if (realOverdueCorpus == null) {
      realOverdueCorpus = " ";
    }
    realInterest = palindromeformatAF.getRealInterest();// ʵ��������Ϣ 6
    if (realInterest == null) {
      realInterest = " ";
    }
    realOverdueInterest = palindromeformatAF.getRealOverdueInterest();// ʵ��������Ϣ
    // 7
    if (realOverdueInterest == null) {
      realOverdueInterest = " ";
    }
    realPunishInterest = palindromeformatAF.getRealPunishInterest();// ʵ�۷�Ϣ 8
    if (realPunishInterest == null) {
      realPunishInterest = " ";
    }
    nobackCorpus = palindromeformatAF.getNobackCorpus();// δ���������� 9
    if (nobackCorpus == null) {
      nobackCorpus = " ";
    }
    nobackOverdueCorpus = palindromeformatAF.getNobackOverdueCorpus();// δ�����ڱ���
    // 10
    if (nobackOverdueCorpus == null) {
      nobackOverdueCorpus = " ";
    }
    nobackInterest = palindromeformatAF.getNobackInterest();// δ��������Ϣ 11
    if (nobackInterest == null) {
      nobackInterest = " ";
    }
    nobackOverdueInterest = palindromeformatAF.getNobackOverdueInterest();// δ��������Ϣ
    // 12
    if (nobackOverdueInterest == null) {
      nobackOverdueInterest = " ";
    }
    nobackPunishInterest = palindromeformatAF.getNobackPunishInterest();// δ����Ϣ
    // 13
    if (nobackPunishInterest == null) {
      nobackPunishInterest = " ";
    }
    deadLine = palindromeformatAF.getDeadLine();// ��ǰ�����ʣ������ 14
    if (deadLine == null) {
      deadLine = " ";
    }

    if (bankId != null && !"".equals(bankId)) {
      // ��������ID ��ѯPL011
      PalindromFormatHead palindromFormatHead = null;
      Integer pkId = null;
      palindromFormatHead = palindromFormatHeadDAO
          .queryPalindromFormatHeadByBankId(bankId);
      if (palindromFormatHead != null) {// PL011ͷ����� ����PL012
        // ����ͷ��ID��ѯPL012 PalindromeFormatTail
        pkId = palindromFormatHead.getId();
        PalindromeFormatTail palindromeFormatTail1 = new PalindromeFormatTail();
        palindromeFormatTail1 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "1");
        palindromeFormatTail1.setFormula(loanId);

        PalindromeFormatTail palindromeFormatTail2 = new PalindromeFormatTail();
        palindromeFormatTail2 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "2");
        palindromeFormatTail2.setFormula(name);

        PalindromeFormatTail palindromeFormatTail3 = new PalindromeFormatTail();
        palindromeFormatTail3 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "3");
        palindromeFormatTail3.setFormula(payDate);

        PalindromeFormatTail palindromeFormatTail4 = new PalindromeFormatTail();
        palindromeFormatTail4 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "4");
        palindromeFormatTail4.setFormula(realCorpus);

        PalindromeFormatTail palindromeFormatTail5 = new PalindromeFormatTail();
        palindromeFormatTail5 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "5");
        palindromeFormatTail5.setFormula(realOverdueCorpus);

        PalindromeFormatTail palindromeFormatTail6 = new PalindromeFormatTail();
        palindromeFormatTail6 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "6");
        palindromeFormatTail6.setFormula(realInterest);

        PalindromeFormatTail palindromeFormatTail7 = new PalindromeFormatTail();
        palindromeFormatTail7 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "7");
        palindromeFormatTail7.setFormula(realOverdueInterest);

        PalindromeFormatTail palindromeFormatTail8 = new PalindromeFormatTail();
        palindromeFormatTail8 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "8");
        palindromeFormatTail8.setFormula(realPunishInterest);

        PalindromeFormatTail palindromeFormatTail9 = new PalindromeFormatTail();
        palindromeFormatTail9 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "9");
        palindromeFormatTail9.setFormula(nobackCorpus);

        PalindromeFormatTail palindromeFormatTail10 = new PalindromeFormatTail();
        palindromeFormatTail10 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "10");
        palindromeFormatTail10.setFormula(nobackOverdueCorpus);

        PalindromeFormatTail palindromeFormatTail11 = new PalindromeFormatTail();
        palindromeFormatTail11 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "11");
        palindromeFormatTail11.setFormula(nobackInterest);

        PalindromeFormatTail palindromeFormatTail12 = new PalindromeFormatTail();
        palindromeFormatTail12 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "12");
        palindromeFormatTail12.setFormula(nobackOverdueInterest);

        PalindromeFormatTail palindromeFormatTail13 = new PalindromeFormatTail();
        palindromeFormatTail13 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "13");
        palindromeFormatTail13.setFormula(nobackPunishInterest);

        PalindromeFormatTail palindromeFormatTail14 = new PalindromeFormatTail();
        palindromeFormatTail14 = palindromFormatHeadDAO
            .queryPalindromFormatTailByBankId(palindromFormatHead.getId(), "14");
        palindromeFormatTail14.setFormula(deadLine);

        // ������־PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_PALINDROMFORMAT));
        plOperateLog.setOpButton(String
            .valueOf(BusiLogConst.BIZLOG_ACTION_MODIFY));
        plOperateLog.setOpBizId(new BigDecimal(pkId.toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());

        plOperateLogDAO.insert(plOperateLog);
      } else {// ͷ������ ����PL011 �� PL012
        palindromFormatHead = new PalindromFormatHead();
        palindromFormatHead.setBankId(bankId);
        Integer flowHeadId = (Integer) palindromFormatHeadDAO
            .insert(palindromFormatHead);

        PalindromeFormatTail palindromeFormatTail1 = new PalindromeFormatTail();
        palindromeFormatTail1.setFlowHeadId(flowHeadId);
        palindromeFormatTail1.setCenterSet("1");
        palindromeFormatTail1.setFormula(loanId);
        palindromeFormatTailDAO.insert(palindromeFormatTail1);

        PalindromeFormatTail palindromeFormatTail2 = new PalindromeFormatTail();
        palindromeFormatTail2.setFlowHeadId(flowHeadId);
        palindromeFormatTail2.setCenterSet("2");
        palindromeFormatTail2.setFormula(name);
        palindromeFormatTailDAO.insert(palindromeFormatTail2);

        PalindromeFormatTail palindromeFormatTail3 = new PalindromeFormatTail();
        palindromeFormatTail3.setFlowHeadId(flowHeadId);
        palindromeFormatTail3.setCenterSet("3");
        palindromeFormatTail3.setFormula(payDate);
        palindromeFormatTailDAO.insert(palindromeFormatTail3);

        PalindromeFormatTail palindromeFormatTail4 = new PalindromeFormatTail();
        palindromeFormatTail4.setFlowHeadId(flowHeadId);
        palindromeFormatTail4.setCenterSet("4");
        palindromeFormatTail4.setFormula(realCorpus);
        palindromeFormatTailDAO.insert(palindromeFormatTail4);

        PalindromeFormatTail palindromeFormatTail5 = new PalindromeFormatTail();
        palindromeFormatTail5.setFlowHeadId(flowHeadId);
        palindromeFormatTail5.setCenterSet("5");
        palindromeFormatTail5.setFormula(realOverdueCorpus);
        palindromeFormatTailDAO.insert(palindromeFormatTail5);

        PalindromeFormatTail palindromeFormatTail6 = new PalindromeFormatTail();
        palindromeFormatTail6.setFlowHeadId(flowHeadId);
        palindromeFormatTail6.setCenterSet("6");
        palindromeFormatTail6.setFormula(realInterest);
        palindromeFormatTailDAO.insert(palindromeFormatTail6);

        PalindromeFormatTail palindromeFormatTail7 = new PalindromeFormatTail();
        palindromeFormatTail7.setFlowHeadId(flowHeadId);
        palindromeFormatTail7.setCenterSet("7");
        palindromeFormatTail7.setFormula(realOverdueInterest);
        palindromeFormatTailDAO.insert(palindromeFormatTail7);

        PalindromeFormatTail palindromeFormatTail8 = new PalindromeFormatTail();
        palindromeFormatTail8.setFlowHeadId(flowHeadId);
        palindromeFormatTail8.setCenterSet("8");
        palindromeFormatTail8.setFormula(realPunishInterest);
        palindromeFormatTailDAO.insert(palindromeFormatTail8);

        PalindromeFormatTail palindromeFormatTail9 = new PalindromeFormatTail();
        palindromeFormatTail9.setFlowHeadId(flowHeadId);
        palindromeFormatTail9.setCenterSet("9");
        palindromeFormatTail9.setFormula(nobackCorpus);
        palindromeFormatTailDAO.insert(palindromeFormatTail9);

        PalindromeFormatTail palindromeFormatTail10 = new PalindromeFormatTail();
        palindromeFormatTail10.setFlowHeadId(flowHeadId);
        palindromeFormatTail10.setCenterSet("10");
        palindromeFormatTail10.setFormula(nobackOverdueCorpus);
        palindromeFormatTailDAO.insert(palindromeFormatTail10);

        PalindromeFormatTail palindromeFormatTail11 = new PalindromeFormatTail();
        palindromeFormatTail11.setFlowHeadId(flowHeadId);
        palindromeFormatTail11.setCenterSet("11");
        palindromeFormatTail11.setFormula(nobackInterest);
        palindromeFormatTailDAO.insert(palindromeFormatTail11);

        PalindromeFormatTail palindromeFormatTail12 = new PalindromeFormatTail();
        palindromeFormatTail12.setFlowHeadId(flowHeadId);
        palindromeFormatTail12.setCenterSet("12");
        palindromeFormatTail12.setFormula(nobackOverdueInterest);
        palindromeFormatTailDAO.insert(palindromeFormatTail12);

        PalindromeFormatTail palindromeFormatTail13 = new PalindromeFormatTail();
        palindromeFormatTail13.setFlowHeadId(flowHeadId);
        palindromeFormatTail13.setCenterSet("13");
        palindromeFormatTail13.setFormula(nobackPunishInterest);
        palindromeFormatTailDAO.insert(palindromeFormatTail13);

        PalindromeFormatTail palindromeFormatTail14 = new PalindromeFormatTail();
        palindromeFormatTail14.setFlowHeadId(flowHeadId);
        palindromeFormatTail14.setCenterSet("14");
        palindromeFormatTail14.setFormula(deadLine);
        palindromeFormatTailDAO.insert(palindromeFormatTail14);

        // ������־PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog.setOpModel(String
            .valueOf(BusiLogConst.PL_OP_DATAPREPARATION_PALINDROMFORMAT));
        plOperateLog
            .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId.toString()));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());

        plOperateLogDAO.insert(plOperateLog);
      }
    }
  }

  /**
   * �������б�Ų�ѯPL010��rowNum;�������õ�����
   */
  public List queryRowNum(String bankId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    if (bankId != null && !"".equals(bankId)) {
      Bankpalindrome bankpalindrome = new Bankpalindrome();
      String rowNum = "";
      int intRowNum = 0;
      bankpalindrome = bankpalindromeDAO.queryById(new Integer(bankId));
      if(bankpalindrome != null){
      rowNum = bankpalindrome.getRowNum();
      if (rowNum != null && !"".equals(rowNum)) {
        intRowNum = Integer.parseInt(rowNum);
        for (int i = 1; i <= intRowNum; i++) {
          list.add(new org.apache.struts.util.LabelValueBean(new Integer(i)
              .toString(), new Integer(i).toString()));
        }
      }
      }
    }
    return list;
  }

}
