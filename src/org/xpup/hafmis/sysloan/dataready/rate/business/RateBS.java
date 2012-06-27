package org.xpup.hafmis.sysloan.dataready.rate.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateTypeDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRate;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.rate.bsinterface.IRateBS;
import org.xpup.hafmis.sysloan.dataready.rate.dto.RateDTO;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateNewAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateUseAF;

/**
 * shiy 2007.9.20 ����ά��
 */
public class RateBS implements IRateBS {
  private LoanRateDAO loanRateDAO = null;
  
  private LoanRateTypeDAO loanRateTypeDAO = null;

  private LoanBankDAO loanBankDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public void setLoanRateTypeDAO(LoanRateTypeDAO loanRateTypeDAO) {
    this.loanRateTypeDAO = loanRateTypeDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  // ��������pl001����Ϣ��������������Ϊ�˷�ҳ������������������
  public RateShowAF findRateList(Pagination pagination)
      throws BusinessException {
    RateShowAF rateShowAF = new RateShowAF();
    try {
      String officecode = (String) pagination.getQueryCriterions().get("officecode");
      String usetime = (String) pagination.getQueryCriterions().get("usetime");
      String ratetype = (String) pagination.getQueryCriterions().get("ratetype");
      String basis = (String) pagination.getQueryCriterions().get("adjustBasis");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List list = new ArrayList();
      List returnlist = new ArrayList();
      if("all".equals(officecode)){
        officecode=null;
      }
      String date = loanRateDAO.findLatestDate(officecode, usetime, ratetype, basis);
      String date1 = "";
      if(date!=null&&!"".equals(date)){
        date1 = (Integer.parseInt(date.substring(0,4))+1)+"��01��01��";
        date = date.substring(0,4)+"��"+date.substring(4,6)+"��"+date.substring(6,8)+"��";
      }
      rateShowAF.setLatestDate(date);
      rateShowAF.setLatestDateNex(date1);
      list = loanRateDAO.queryRate_sy(officecode,usetime,ratetype,basis,orderBy, order, start, pageSize, page);
      if (list == null || list.isEmpty()) {
        rateShowAF.setList(returnlist);
        return rateShowAF;
      }
      for (int i = 0; i < list.size(); i++) {
        RateDTO rateDTO = new RateDTO();
        rateDTO = (RateDTO) list.get(i);
        rateDTO.setLoanRateType(loanRateTypeDAO.queryExplainByType(rateDTO.getLoanRateType()));
        rateDTO.setLoanMonthRate(rateDTO.getLoanMonthRate().multiply(
            new BigDecimal(12.00)).multiply(new BigDecimal(100.00)).divide(
            new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP));
        rateDTO.setStatus(BusiTools.getBusiValue(Integer.parseInt(rateDTO
            .getStatus()), BusiConst.APPSTATUS));
        // ��ȡ���´���Ӧ������
        List temp_officeList = (List) loanRateDAO.queryOfficecodeName(rateDTO
            .getOffice());
        String officeName = (String) temp_officeList.get(0);
        rateDTO.setOffice(officeName);
        returnlist.add(rateDTO);
      }
      int count = 0;
      count = loanRateDAO.countRate_sy(officecode,usetime,ratetype,basis);
      rateShowAF.setList(returnlist);
      pagination.setNrOfElements(count);
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rateShowAF;
  }

  // ����µ����ʡ�
  public void addRateInfo(RateNewAF rateNewAF, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      String rateId = "";
      // ��ӵ�һ�İ��´�
      if (!rateNewAF.getOffice().equals("all")) {
        LoanRate loanRate = new LoanRate();
        // ǰ̨ҳ�������������ʣ����ݿ��в����������
        loanRate.setLoanMonthRate(rateNewAF.getLoanYearRate().divide(
            new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP).divide(
            new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
        // �������
        loanRate.setAjustDate(rateNewAF.getAdjustDate());
        // ״̬δ����
        loanRate.setStatus(BusiConst.APPSTATUS_1 + "");
        // �������Ա
        loanRate.setOperator(securityInfo.getUserInfo().getUsername());
        // ��ȡ��������
        loanRate.setLoanRateType(rateNewAF.getLoanRateType());
        loanRate.setOffice(rateNewAF.getOffice());
        loanRate.setAdjustBasis(rateNewAF.getAdjustBasis());
        rateId = loanRateDAO.insert(loanRate).toString();
        try{
          this.modifyBorrowerLoanInfo(rateNewAF);
        }catch(BusinessException bx){
          throw bx;
        }
        } else {
        // ������еİ��´�
        List temp_list = securityInfo.getAllOfficeList();
        OfficeDto officeDto = null;
        Iterator chaitr = temp_list.iterator();
        while (chaitr.hasNext()) {
          officeDto = (OfficeDto) chaitr.next();
          officeDto.getOfficeCode();
          // ��ȡ��������0����1-5��
          LoanRate loanRate = new LoanRate();
          // ǰ̨ҳ�������������ʣ����ݿ��в����������
          loanRate.setLoanMonthRate(rateNewAF.getLoanYearRate().divide(
              new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP).divide(
              new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
          // �������
          loanRate.setAjustDate(securityInfo.getUserInfo().getPlbizDate());
          // ״̬δ����
          loanRate.setStatus(BusiConst.APPSTATUS_1 + "");
          // �������Ա
          loanRate.setOperator(securityInfo.getUserInfo().getUsername());

          loanRate.setLoanRateType(rateNewAF.getLoanRateType());
          loanRate.setOffice(officeDto.getOfficeCode());
          loanRate.setAdjustBasis(rateNewAF.getAdjustBasis());
          loanRateDAO.insert(loanRate);
          // ����pl115
          rateNewAF.setLoanRateType(rateNewAF.getLoanRateType());
          rateNewAF.setOffice(officeDto.getOfficeCode());
          try{
            this.modifyBorrowerLoanInfo(rateNewAF);
            }catch(BusinessException bx){
              throw bx;
            }
        }
      }
      // ���������־
      String opModel = BusiLogConst.PL_OP_DATAPREPARATION_RATE + "";
      String opButton = BusiLogConst.BIZLOG_ACTION_ADD + "";
      this.addPlOperateLog_sy(opModel, opButton, rateId, securityInfo);
    }catch(BusinessException bxe){
     throw bxe; 
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // ����pl115����˶����Ϣ���ı���ʹ���µ�����
  public void modifyBorrowerLoanInfo(RateNewAF rateNewAF) throws BusinessException{
    // ͨ�����´����Ҷ��õ�����
    String officecode = rateNewAF.getOffice();
    // ������
    String loanMonthRate = rateNewAF.getLoanYearRate().divide(
        new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP).divide(
        new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP).toString();
    try {
      List bankIdList = loanBankDAO.queryBank_sy(officecode);
      if (!bankIdList.isEmpty()) {
        for (int i = 0; i < bankIdList.size(); i++) {
          BigDecimal temp_bankId = (BigDecimal) bankIdList.get(i);
          Integer bankId = new Integer(temp_bankId.toString());
          List temp_list = loanBankParaDAO.queryLoanBankPara_sy(bankId);
          // ���½���˶����Ϣ
          if (!temp_list.isEmpty()) {
            List listContractId = borrowerLoanInfoDAO.queryContractId_sy(
                bankId, rateNewAF.getLoanRateType());
            if (!listContractId.isEmpty()) {
              for (int j = 0; j < listContractId.size(); j++) {
                BigDecimal corpusInterest = new BigDecimal(0.00);
                Object office[] = (Object[]) listContractId.get(j);
                String contractId = office[0] + "";
                String loanMode = office[1] + "";
                if (loanMode.equals("2")) {
                  if (office[2] != null && office[3] != null) {
                    BigDecimal loanMoney = new BigDecimal(office[2] + "");
                    String loanTimeLimit = office[3] + "";
                    corpusInterest = CorpusinterestBS
                        .getCorpusInterest(loanMoney, new BigDecimal(
                            loanMonthRate), loanTimeLimit);
                  }
                } else {
                  corpusInterest = null;
                }
                borrowerLoanInfoDAO.updateBorrowerLoanInfo_sy(new BigDecimal(loanMonthRate),
                    corpusInterest, contractId);
              }
            }
          }
        }
      }
    } catch (Exception e) {
      throw new BusinessException("������������Ϣʧ�ܣ�");
    }
  }

  // ͨ������ά��pl001���id������pl001��Ӧ�ļ�¼
  public RateNewAF findRateInfo(String rateId) throws BusinessException {
    RateNewAF rateNewAF = new RateNewAF();
    try {

      LoanRate loanRate = loanRateDAO.queryById(new Integer(rateId));
      // �鿴�Ƿ���ͬһ�����´���ͬ�����������һ����
      List temp_list = loanRateDAO.queryRate_sy(loanRate.getOffice(), loanRate
          .getLoanRateType());
      if ((temp_list.get(0).toString()).equals(rateId.toString())) {
        rateNewAF.setRateId(loanRate.getLoanRateId() + "");
        rateNewAF.setLoanRateType(loanRate.getLoanRateType());
        rateNewAF.setLoanRateTypeUpdate(loanRate.getLoanRateType());
        rateNewAF.setOffice(loanRate.getOffice());
        rateNewAF.setLoanRateOfficeUpdate(loanRate.getOffice());
        rateNewAF.setAdjustDate(loanRate.getAjustDate());
        rateNewAF.setLoanYearRate(loanRate.getLoanMonthRate().multiply(
            new BigDecimal(12.00)).multiply(new BigDecimal(100.00)).divide(
            new BigDecimal(1.00), 2, BigDecimal.ROUND_HALF_UP));
        rateNewAF.setAdjustBasis(loanRate.getAdjustBasis());
        // ��ȡ���´���Ӧ������
        List temp_officeList = (List) loanRateDAO.queryOfficecodeName(loanRate
            .getOffice());
        String officeName = (String) temp_officeList.get(0);
        rateNewAF.setOfficeName(officeName);
        rateNewAF.setType("2");
      } else {
        throw new BusinessException("��ѡ�����µ������޸�");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rateNewAF;
  }

  // ����pl021������־
  public void addPlOperateLog_sy(String opModel, String opButton,
      String opBizId, SecurityInfo securityInfo) {
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(opModel);
    plOperateLog.setOpButton(opButton);
    if (opBizId != null && !opBizId.equals("")) {
      plOperateLog.setOpBizId(new BigDecimal(opBizId));
    }
    plOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
    plOperateLogDAO.insert(plOperateLog);
  }

  public void updateRateInfo(RateNewAF rateNewAF, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      LoanRate loanRate = loanRateDAO.queryById(new Integer(rateNewAF
          .getRateId()));
      if (loanRate.getStatus().equals("1")) {
        throw new BusinessException("���������ã���ȷ�ϣ�");
      }
      // �鿴�Ƿ���ͬһ�����´���ͬ�����������һ����
      List temp_list = loanRateDAO.queryRate_sy(loanRate.getOffice(), loanRate
          .getLoanRateType());
      if ((temp_list.get(0).toString()).equals(rateNewAF.getRateId())) {
        // ǰ̨ҳ�������������ʣ����ݿ��в����������
        loanRate.setLoanMonthRate(rateNewAF.getLoanYearRate().divide(
            new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP).divide(
            new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP));
        loanRate.setAdjustBasis(rateNewAF.getAdjustBasis());
        // ���¸����Ͷ��壬��Ϊҳ��ʹ��disable����ȡ����ֵ�����������ֶ��ڴ˸��丳ֵ
        rateNewAF.setLoanRateType(rateNewAF.getLoanRateTypeUpdate());
        // ���¶���office����Ϊҳ��ʹ��disable����ȡ����ֵ�����������ֶ��ڴ˸��丳ֵ
        rateNewAF.setOffice(rateNewAF.getLoanRateOfficeUpdate());
        rateNewAF.setLoanRateType(rateNewAF.getLoanRateTypeUpdate());
        this.modifyBorrowerLoanInfo(rateNewAF);
        // ���������־
        String opModel = BusiLogConst.PL_OP_DATAPREPARATION_RATE + "";
        String opButton = BusiLogConst.BIZLOG_ACTION_MODIFY + "";
        this.addPlOperateLog_sy(opModel, opButton, rateNewAF.getRateId(),
            securityInfo);
      } else {
        throw new BusinessException("��ѡ�����µ������޸�");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // ��������
  public void useRateInfo_sy(RateUseAF rateUseAF, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      String rateId = rateUseAF.getRateId();
      String appMode = rateUseAF.getAppMode(); // �������û����������ô�ŵ���0��1
      LoanRate loanRate = loanRateDAO.queryById(new Integer(rateId));
      if (loanRate.getStatus().equals("1")) {
        throw new BusinessException("���������ã���ȷ�ϣ�");
      }
      // �鿴�Ƿ���ͬһ�����´���ͬ�����������һ����
      List temp_list = loanRateDAO.queryRate_sy(loanRate.getOffice(), loanRate
          .getLoanRateType());
      if ((temp_list.get(0).toString()).equals(rateId)) {
        // ��ȡ�������
        String bizDate = "";
        // ��������
        String appdate = "";
        // ���õ��ϸ���
        String lastappdate = "";
        // ���÷�ʽ
        String appmode = "";
        bizDate = securityInfo.getUserInfo().getPlbizDate();
        String temp_appdate = bizDate.substring(4, 6);
        if (appMode.equals("0")) {
          appmode = "0";
          if (temp_appdate.equals("01")) {
            // �鿴�ð��´���һ����Ƿ����
            List temp_bankList = loanBankDAO.queryBank_sy((Integer
                .parseInt(bizDate.substring(0, 4)) - 1)
                + "", loanRate.getOffice());
            if (temp_bankList.isEmpty()) {
              throw new BusinessException("�������");
            } else {
              appdate = bizDate.substring(0, 6) + "01";
              // ��һ�����õ��ϸ�������������¶�����
              int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                  .substring(0, 4)) - 1, 12);
              // lastappdate = "12" + "" + month_day;
              lastappdate = month_day + "";
            }
          } else {
            appdate = bizDate.substring(0, 6) + "01";
            // ��һ�����õ��ϸ�������������¶�����
            int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                .substring(0, 4)),
                Integer.parseInt(bizDate.substring(4, 6)) - 1);
            // lastappdate = Integer.parseInt(bizDate.substring(4, 6)) - 1 + ""
            // + month_day;
            lastappdate = month_day + "";
          }
        } else {
          appmode = "1";
          if (temp_appdate.equals("12")) {

            // �ж��Ƿ����
            List temp_bankList = loanBankDAO.queryBank_sy(bizDate.substring(0,
                4), loanRate.getOffice());
            if (temp_bankList.isEmpty()) {
              throw new BusinessException("�������");
            } else {
              appdate = (Integer.parseInt(bizDate.substring(0, 4)) + 1) + "01"
                  + "01";
              // ��һ�����õ��ϸ�������������¶�����
              int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                  .substring(0, 4)), Integer.parseInt(bizDate.substring(4, 6)));
              // lastappdate = bizDate.substring(4, 6) + "" + month_day;
              lastappdate = month_day + "";
            }
          } else {
            appdate = (Integer.parseInt(bizDate.substring(0, 6)) + 1) + "01";
            // ��һ�����õ��ϸ�������������¶�����
            int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                .substring(0, 4)), Integer.parseInt(bizDate.substring(4, 6)));
            // lastappdate = bizDate.substring(4, 6) + "" + month_day;
            lastappdate = month_day + "";
          }
        }
        // ���ô洢����

        String lastloankou = lastappdate; // ���õ��ϸ��£�ֻ���������µ�����С�ĸ��»�������
        loanRateDAO.useRate_sy(rateId, appdate, lastloankou, securityInfo
            .getUserInfo().getUserIp(), securityInfo.getUserInfo()
            .getUsername(), appmode);
      } else {
        throw new BusinessException("��ѡ�����µ���������");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      throw new BusinessException("����ʧ��");
    }
  }

  public void useRateAll_sy(String appMode, SecurityInfo securityInfo)
      throws BusinessException {
    try {
      List officeAllList = loanRateDAO.queryRate_sy();
      String pl001id = ""; // ��Ҫ���õ����ʵ�idƴ������
      // ��������
      String appdate = "";
      // ���õ��ϸ���
      String lastappdate = "";
      // ���÷�ʽ
      String appmode = "";
      for (int i = 0; i < officeAllList.size(); i++) {
        Object office[] = (Object[]) officeAllList.get(i);
        String temp_pl001id = office[0] + "";
        String officecode = office[1] + "";
        String rateType = office[2] + "";
        List temp_list = loanRateDAO.queryRate_sy(officecode, rateType);
        if ((temp_list.get(0).toString()).equals(temp_pl001id)) {
          // ��ȡ�������
          String bizDate = "";

          bizDate = securityInfo.getUserInfo().getPlbizDate();
          String temp_appdate = bizDate.substring(4, 6);
          if (appMode.equals("0")) {
            appmode = "0";
            if (temp_appdate.equals("01")) {
              // �鿴�ð��´���һ����Ƿ����
              List temp_bankList = loanBankDAO.queryBank_sy((Integer
                  .parseInt(bizDate.substring(0, 4)) - 1)
                  + "", officecode);
              if (temp_bankList.isEmpty()) {
                throw new BusinessException("�������");
              } else {
                appdate = bizDate.substring(0, 6) + "01";
                // ��һ�����õ��ϸ�������������¶�����
                int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                    .substring(0, 4)) - 1, 12);
                // lastappdate="12"+""+month_day;
                lastappdate = month_day + "";
              }
            } else {
              appdate = bizDate.substring(0, 6) + "01";
              // ��һ�����õ��ϸ�������������¶�����
              int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                  .substring(0, 4)),
                  Integer.parseInt(bizDate.substring(4, 6)) - 1);
              // lastappdate=Integer.parseInt(bizDate.substring(4,6))-1+""+month_day;
              lastappdate = month_day + "";
            }
          } else {
            appmode = "1";
            if (temp_appdate.equals("12")) {
              // �ж��Ƿ����
              List temp_bankList = loanBankDAO.queryBank_sy(bizDate.substring(
                  0, 4), officecode);
              if (temp_bankList.isEmpty()) {
                throw new BusinessException("�������");
              } else {
                appdate = (Integer.parseInt(bizDate.substring(0, 4)) + 1)
                    + "01" + "01";
                // ��һ�����õ��ϸ�������������¶�����
                int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                    .substring(0, 4)), Integer
                    .parseInt(bizDate.substring(4, 6)));
                // lastappdate=bizDate.substring(4,6)+""+month_day;
                lastappdate = month_day + "";
              }
            } else {
              appdate = (Integer.parseInt(bizDate.substring(0, 6)) + 1) + "01";
              // ��һ�����õ��ϸ�������������¶�����
              int month_day = BusiTools.daysOfMonth(Integer.parseInt(bizDate
                  .substring(0, 4)), Integer.parseInt(bizDate.substring(4, 6)));
              // lastappdate=bizDate.substring(4,6)+""+month_day;
              lastappdate = month_day + "";
            }
          }
          pl001id = pl001id + temp_pl001id + ",";
        }
      }
      // ���ô洢����
      String lastloankou = lastappdate; // ���õ��ϸ��£�ֻ���������µ�����С�ĸ��»�������
      loanRateDAO.useRate_sy(pl001id, appdate, lastloankou, securityInfo
          .getUserInfo().getUserIp(), securityInfo.getUserInfo().getUsername(),
          appmode);
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String checkUseInfo_sy(String rateId) throws BusinessException {
    String info = "";
    try {
      LoanRate loanRate = loanRateDAO.queryById(new Integer(rateId));
      // �鿴�Ƿ���ͬһ�����´���ͬ�����������һ����
      List temp_list = loanRateDAO.queryRate_sy(loanRate.getOffice(), loanRate
          .getLoanRateType());
      if ((temp_list.get(0).toString()).equals(rateId)) {
        // ͨ����֤
        info = "pass";
      } else {
        throw new BusinessException("��ѡ�����µ���������");
      }
      if (loanRate.getStatus().equals("1")) {
        throw new BusinessException("���������ã���ȷ�ϣ�");
      }
    } catch (BusinessException bex) {
      throw bex;
    }
    return info;
  }
}
