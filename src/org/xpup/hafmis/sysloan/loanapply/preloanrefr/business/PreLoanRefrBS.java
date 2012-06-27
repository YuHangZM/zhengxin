/**
 * Copy Right Information   : Goldsoft 
 * Project                  : PreLoanRefrBS
 * @Version                 : 1.0
 * @author                  : wangy
 * ��������                   :2008-05-19
 **/
package org.xpup.hafmis.sysloan.loanapply.preloanrefr.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.bsinterface.IPreLoanRefrBS;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.dto.PreLoanRefrDTO;
import org.xpup.hafmis.sysloan.loanapply.preloanrefr.form.PreLoanRefrShowAF;

public class PreLoanRefrBS implements IPreLoanRefrBS {

  LoanRateDAO loanRateDAO = null;

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  /**
   * Description ��ǰ��ѯ
   * 
   * @author wangy 2008-05-19
   * @param ��ǰ��ѯ��Ϣ�б�
   * @param ��PreLoanRefrShowAC����
   * @return LoanCheckShowAF
   * @throws Exception, BusinessException
   */
  public PreLoanRefrShowAF queryPreLoanRefrListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String timelimit = null;
    String loanMoney = (String) pagination.getQueryCriterions()
        .get("loanMoney");// ������:��Ԫ
    BigDecimal loanmoney = new BigDecimal(loanMoney).multiply(new BigDecimal(
        10000));

    // �»���Ϣ��ֵ=��ʣ�౾��-Ӧ�������-��ǰ�����*��1+�����ʣ���ʣ������*������/(1+������)��ʣ������-1
    BigDecimal corpusInterest = new BigDecimal(0.00);// �»����(�»���Ϣ)
    BigDecimal loanmoneyTotal = new BigDecimal(0.00);// �����ܶ�
    BigDecimal interestTotal = new BigDecimal(0.00);// ��Ϣ�ܶ�

    BigDecimal loanRateFive = new BigDecimal(0.00);// �������ʣ������ʣ�
    BigDecimal loanRateFiveUp = new BigDecimal(0.00);// �������ʣ������ʣ�
    BigDecimal rate = new BigDecimal(0.00);// ������
    String officeCode = securityInfo.getOfficeDto().getOfficeCode();// ���´�
    // 1-5������ ȡPL001��loan_rate_type=0�����µ�����
    loanRateFive = loanRateDAO.findMontRate(officeCode, String
        .valueOf(BusiConst.PLLOANTYPE_FIVE));
    // 5���������� ȡPL001��loan_rate_type=1����������
    loanRateFiveUp = loanRateDAO.findMontRate(officeCode, String
        .valueOf(BusiConst.PLLOANTYPE_FIVEUP));
    List templist = new ArrayList();
    for (int i = 1; i <= 30; i++) {
      timelimit = new Integer(i * 12).toString();// �������� = �������ޣ��꣩ * 12
      if (i <= 5) {
        corpusInterest = CorpusinterestBS.getCorpusInterest(loanmoney,
            loanRateFive, timelimit);
        if(loanRateFive!=null)//wudi
        {
          rate = loanRateFive.multiply(new BigDecimal(12));// ������µ�������
        }
        
      } else {
        corpusInterest = CorpusinterestBS.getCorpusInterest(loanmoney,
            loanRateFiveUp, timelimit);
        if(loanRateFive!=null)
        {
          rate = loanRateFiveUp.multiply(new BigDecimal(12));// ������µ�������
        }
      
      }
      loanmoneyTotal = corpusInterest.multiply(new BigDecimal(timelimit));// �����ܶ�=�»����*��������
      interestTotal = loanmoneyTotal.subtract(loanmoney);// ��Ϣ�ܶ� = �����ܶ�-������
      PreLoanRefrDTO preLoanRefrDTO = new PreLoanRefrDTO();
      preLoanRefrDTO.setYearlimit(new Integer(i));
      preLoanRefrDTO.setCorpusInterest(corpusInterest);
      preLoanRefrDTO.setLoanmoneyTotal(loanmoneyTotal);
      preLoanRefrDTO.setInterestTotal(interestTotal);
      preLoanRefrDTO.setRate(rate.multiply(new BigDecimal(100.00)).setScale(2,
          BigDecimal.ROUND_HALF_UP).toString()
          + "%");// ������
      templist.add(preLoanRefrDTO);
    }
    PreLoanRefrShowAF preLoanRefrShowAF = new PreLoanRefrShowAF();
    preLoanRefrShowAF.setLoanMoney(loanMoney);
    preLoanRefrShowAF.setPrintMoney(loanmoney.divide(new BigDecimal(1), 2,
        BigDecimal.ROUND_HALF_UP)
        + "Ԫ");
    pagination.setNrOfElements(30);
    preLoanRefrShowAF.setList(templist);
    preLoanRefrShowAF.setCount(new Integer(30));
    return preLoanRefrShowAF;
  }
}
