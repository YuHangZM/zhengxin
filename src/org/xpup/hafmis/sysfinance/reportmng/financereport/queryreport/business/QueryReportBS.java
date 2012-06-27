package org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.business;

import java.math.BigDecimal;
import java.util.StringTokenizer;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.ReportMngDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.ReportMng;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.bsinterface.IQueryReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF;

public class QueryReportBS implements IQueryReportBS {

  private ReportMngDAO reportMngDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;
  
  private SubjectDAO subjectDAO = null;

  public void setReportMngDAO(ReportMngDAO reportMngDAO) {
    this.reportMngDAO = reportMngDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  /**
   * ��ѯ������Ϣ
   */
  public QueryReportAF queryReportMessage(QueryReportAF queryReportAF,
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    try {
      String tablename = (String) pagination.getQueryCriterions().get(
          "tablename");
      String year = (String) pagination.getQueryCriterions().get("bizyear");
      String starperiod = (String) pagination.getQueryCriterions().get(
          "starperiod");
      String endperiod = (String) pagination.getQueryCriterions().get(
          "endperiod");
      String office = (String) pagination.getQueryCriterions().get("office");
      if (office.equals("ȫ��")) {
        office = null;
      }
      // �жϻ���ڼ�
      String yearmonth = this.getMinYearmonth_WL(securityInfo);
      String yearBydate = yearmonth.substring(0, 4);
      String monthBydate = yearmonth.substring(4, 6);
      if (Integer.parseInt(year) < Integer.parseInt(yearBydate)) {
        return queryReportAF;
      } else if (Integer.parseInt(year) == Integer.parseInt(yearBydate)) {
        if ((Integer.parseInt(starperiod) <= Integer.parseInt(monthBydate))
            && (Integer.parseInt(endperiod) <= Integer.parseInt(monthBydate))) {
          return queryReportAF;
        } else if ((Integer.parseInt(starperiod) <= Integer
            .parseInt(monthBydate))
            && (Integer.parseInt(endperiod) > Integer.parseInt(monthBydate))) {
          starperiod = "" + (Integer.parseInt(monthBydate) + 1);
          if (starperiod.length() == 1) {
            starperiod = "0" + starperiod;
          }
        }
      }
      ReportMng reportMng = reportMngDAO.queryHeadReportMessageByTablename_WL(
          tablename, securityInfo);
      pagination.getQueryCriterions().put("col", reportMng.getColspan());
      pagination.getQueryCriterions().put("row", reportMng.getRowspan());
      queryReportAF.setYearmonth(year + "��" + starperiod + "��");
      queryReportAF.setCol(reportMng.getColspan());
      queryReportAF.setRow(reportMng.getRowspan());
      queryReportAF.setTablename(reportMng.getTailtableNameChinese());

      queryReportAF = reportMngDAO.queryReportMessage_WL(queryReportAF,
          reportMng, securityInfo);
      String flag = "0";// �жϹ�ʽ���Ƿ����й�ʽ 0:û�У�1����
      String[] colformula = new String[1000];// ����1000���п��ܲ���Ŷ����
      int flag_col = 0;// ��ʶ�ж��ٸ��ǰ����еĹ�ʽ����
      for (int i = 1; i < (Integer.parseInt(reportMng.getColspan()) + 1); i++) {
        for (int j = 1; j < (Integer.parseInt(reportMng.getRowspan()) + 1); j++) {
          if ((queryReportAF.getValue("" + i + "_" + j + "") != null)
              && (!queryReportAF.getValue("" + i + "_" + j + "").toString()
                  .equals(""))) {
            queryReportAF.setValue("v" + i + "_" + j + "", queryReportAF
                .getValue("" + i + "_" + j + ""));
            if (queryReportAF.getValue("" + i + "_" + j + "").toString()
                .substring(0, 1).equals(BusiConst.REPORTLOGO_FORMULA)) {
              flag = this.getExpressValue_(queryReportAF.getValue(
                  "" + i + "_" + j + "").toString());
              if (flag.equals("1")) {
                colformula[flag_col] = "" + i + "_" + j + "";
                flag_col++;
                continue;
              }
              queryReportAF.setValue("" + i + "_" + j + "", this
                  .getExpressValue(queryReportAF
                      .getValue("" + i + "_" + j + "").toString(), year,
                      starperiod, endperiod, office, securityInfo,
                      queryReportAF, j, flag));
            }
          }
        }
      }
      // ���¼����еĹ�ʽ
      for (int i = 0; i < flag_col; i++) {
        String formula_temp = colformula[i];
        queryReportAF.setValue(formula_temp, queryReportAF
            .getValue(formula_temp));
        if (queryReportAF.getValue(formula_temp).toString().substring(0, 1)
            .equals(BusiConst.REPORTLOGO_FORMULA)) {
          String[] temp_arr = formula_temp.split("_");
          queryReportAF.setValue(formula_temp, this.getExpressValue(
              queryReportAF.getValue(formula_temp).toString(), year,
              starperiod, endperiod, office, securityInfo, queryReportAF,
              Integer.parseInt(temp_arr[1]), flag));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return queryReportAF;
  }

  /**
   * ���񱨱����ݹ�ʽ��ñ��ʽ��ֵ
   * 
   * @param s
   * @param yearMonth
   * @param starperiod
   * @param endperiod
   * @param office
   * @return
   */
  public BigDecimal getExpressValue(String s, String year, String starperiod,
      String endperiod, String office, SecurityInfo securityInfo,QueryReportAF queryReportAF,int row,String flag) {
    BigDecimal value = new BigDecimal(0.00);
    try {
      BigDecimal val = new BigDecimal(0.00);
      s = s.substring(1);
      StringTokenizer substr = new StringTokenizer(s, "+");
      while (substr.hasMoreTokens()) {
        String expressBig = substr.nextToken();
        StringTokenizer substr2 = new StringTokenizer(expressBig, "-");
        int num = substr2.countTokens();
        if (num > 1) {
          int j = 0;
          while (substr2.hasMoreTokens()) {
            String express = substr2.nextToken();
            j++;
            if (j == 1) {
              val = getField(express, year, starperiod, endperiod, office,
                  securityInfo,queryReportAF,row,flag);
              value = value.add(val);
            } else {
              val = getField(express, year, starperiod, endperiod, office,
                  securityInfo,queryReportAF,row,flag);
              value = value.subtract(val);
            }
          }
        } else {
          val = getField(expressBig, year, starperiod, endperiod, office,
              securityInfo,queryReportAF,row,flag);
          value = value.add(val);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return value;
  }

  /**
   * ���񱨱����ݹ�ʽ��ñ��ʽ��ֵ
   * 
   * @param express String
   * @return String
   */
  public BigDecimal getField(String express, String year, String starperiod,
      String endperiod, String office, SecurityInfo securityInfo,QueryReportAF queryReportAF,int row,String flag)
      throws Exception, BusinessException {
    BigDecimal value=null;
    String subjectcode=express.substring(2);
    try {
      if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_BEGBALANCE_DEBIT)) {// ������
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryBgnblance_WL(subjectcode,direction, year, null, null, office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_BEGBALANCE_CREDIT)) {// �ڳ����
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryBgnblance_WL(subjectcode, direction,year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ENDBALANCE_DEBIT)) {// ��ĩ���(����)
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryEndblance_WL(subjectcode,direction, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ENDBALANCE_CREDIT)) {// ��ĩ���
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryEndblance_WL(subjectcode,direction, year, null, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURFIGURES_DEBIT)) {// ���ڷ�����跽��
        value=accountantCredenceDAO.queryTermlendmnt_WL(subjectcode, year, starperiod, endperiod, office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURFIGURES_CREDIT)) {// ���ڷ����������
        value=accountantCredenceDAO.queryTermloanmnt_WL(subjectcode, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT)) {// ��ԭ�����귢����跽����Ϊ�������ۼƷ�����跽��
        value=accountantCredenceDAO.queryYearlendmnt_WL(subjectcode, year, endperiod, office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT)) {// ��ԭ�����귢�����������Ϊ�������ۼƷ����������
        value=accountantCredenceDAO.queryYearloanmnt_WL(subjectcode, year, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_DEBIT)) {// ��ԭ�����귢����跽����Ϊ�������ۼƷ�����跽��
        value=accountantCredenceDAO.queryLastyearlentmnt_WL(subjectcode, year, "0", office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_CREDIT)) {// ��ԭ�����귢�����������Ϊ�������ۼƷ����������
        value=accountantCredenceDAO.queryLastyearloanmnt_WL(subjectcode, year, "0", office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT)) {// ��ԭ�������ۼƷ�����跽����Ϊ����ĩ�ۼƷ�����跽��
        value=accountantCredenceDAO.queryTermlendmnt_WL(subjectcode, year, null, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT)) {// ��ԭ�������ۼƷ������������Ϊ����ĩ�ۼƷ����������
        value=accountantCredenceDAO.queryTermloanmnt_WL(subjectcode, year, null, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT)) {// �����ۼƷ�����跽�������ã�
        value=accountantCredenceDAO.queryLastyearlentmnt_WL(subjectcode, year, "1", office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT)) {// �����ۼƷ���������������ã�
        value=accountantCredenceDAO.queryLastyearloanmnt_WL(subjectcode, year, "1", office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_COL)){// ��
        String temp_row=""+row;
        value=new BigDecimal(queryReportAF.getValue("" + subjectcode+ "_" + temp_row + "").toString());

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REPAIR_CURTERMAMOUNT)) {// ��ȡ�����򡢽��졢����������ס����ȡ�����ڷ�����                                                                                                                                       �����ڷ�����
        String[] pickreason=new String[2];
        pickreason[0]=""+BusiConst.BUYHOUSE;
        pickreason[1]=""+BusiConst.PARTREST;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REPAIR_CURYEARAMOUNT)) {// ��ȡ�����򡢽��졢����������ס����ȡ�������ۼƷ�����                                                                                                                                   �����ڷ�����
        String[] pickreason=new String[2];
        pickreason[0]=""+BusiConst.BUYHOUSE;
        pickreason[1]=""+BusiConst.PARTREST;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REPAIR_CURYEARSUMAMOUNT)) {// ��ȡ�����򡢽��졢����������ס����ȡ����ĩ�ۼƷ�����                                                                                                                  �����ڷ�����
        String[] pickreason=new String[2];
        pickreason[0]=""+BusiConst.BUYHOUSE;
        pickreason[1]=""+BusiConst.PARTREST;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_RETIREMENT_CURTERMAMOUNT)) {// ��ȡ����������ȡ�����ڷ�����                                                                                                                              �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.BOWOUT;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_RETIREMENT_CURYEARAMOUNT)) {// ��ȡ����������ȡ�������ۼƷ�����                                                                                                                               �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.BOWOUT;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT)) {// ��ȡ����������ȡ����ĩ�ۼƷ�����                                                                                                                        �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.BOWOUT;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LOSEABILITY_CURTERMAMOUNT)) {// ��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ�����ڷ�����                                                                                                                     �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.DISTORY;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LOSEABILITY_CURYEARAMOUNT)) {// ��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ�������ۼƷ�����                                                                                                              �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.DISTORY;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT)) {// ��ȡ����ȫɥʧ�Ͷ��������뵥λ��ֹ�Ͷ���ϵ��ȡ����ĩ�ۼƷ�����                                                                                                                   �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.DISTORY;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ABROAD_CURTERMAMOUNT)) {// ��ȡ������������ȡ�����ڷ�����                                                                                                    �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ABROAD_CURYEARAMOUNT)) {// ��ȡ������������ȡ�������ۼƷ�����                                                                                                  �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ABROAD_CURYEARSUMAMOUNT)) {// ��ȡ������������ȡ����ĩ�ۼƷ�����                                                                                                            �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT)) {// ��ȡ�������������Ϣ��ȡ�����ڷ�����                                                                                              �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT)) {// ��ȡ�������������Ϣ��ȡ�������ۼƷ�����                                                                                      �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT)) {// ��ȡ�������������Ϣ��ȡ����ĩ�ۼƷ�����                                                                                     �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT)) {// ��ȡ��֧��������ȡ�����ڷ�����                                                                                       �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT)) {// ��ȡ��֧��������ȡ�������ۼƷ�����                                                                                    �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT)) {// ��ȡ��֧��������ȡ����ĩ�ۼƷ�����                                                                            �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_OTHERS_CURTERMAMOUNT)) {// ��ȡ��������ȡ�����ڷ�����                                                                             �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_OTHERS_CURYEARAMOUNT)) {// ��ȡ��������ȡ�������ۼƷ�����                                                                           �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_OTHERS_CURYEARSUMAMOUNT)) {// ��ȡ��������ȡ����ĩ�ۼƷ�����                                                                       �����ڷ�����
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    if(value==null){
      value = new BigDecimal(0.00);
    }
    return value;
  }
  /**
   * �õ����ݿ�������ʼ������
   */
  public String getMinYearmonth_WL(SecurityInfo securityInfo) throws Exception, BusinessException {
    String yearmonth="";
    try{
      yearmonth=accountantCredenceDAO.getMinYearmonth_WL(securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return yearmonth;
  }
  /**
   * ���񱨱��жϹ�ʽ���Ƿ����й�ʽ����
   * 
   * @param s
   * @param yearMonth
   * @param starperiod
   * @param endperiod
   * @param office
   * @return
   */
  public String getExpressValue_(String s) {
    String value = "0";
    try {
      s = s.substring(1);
      StringTokenizer substr = new StringTokenizer(s, "+");
      while (substr.hasMoreTokens()) {
        String expressBig = substr.nextToken();
        StringTokenizer substr2 = new StringTokenizer(expressBig, "-");
        int num = substr2.countTokens();
        if (num > 1) {
          int j = 0;
          while (substr2.hasMoreTokens()) {
            String express = substr2.nextToken();
            j++;
            if (j == 1) {
              value = getField_(express);
            } else {
              value = getField_(express);
            }
          }
        } else {
          value = getField_(expressBig);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return value;
  }

  /**
   * ���񱨱��жϹ�ʽ���Ƿ�����
   * 
   * @param express String
   * @return String
   */
  public String getField_(String express)
      throws Exception, BusinessException {
    String value="0";
    try {
      if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_COL)){// ��
        value="1";
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }
}
