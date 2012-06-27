package org.xpup.hafmis.sysloan.loanaccord.loanaccord.action;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiTools;

public class LoanaccordInforAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      //��ȡ��������
      String tempLoanStartDate=request.getParameter("changeOverTime");
      //��ȡ����
      String tempLoanTimeLimit=request.getParameter("tempLoanTimeLimit");
//    �������ջ���ͳһ����
      String loanRepayDayInfo=request.getParameter("loanRepayDayInfo");
      //�����
      String temploanMoney=request.getParameter("loanMoney");
      BigDecimal loanMoney=new BigDecimal(0.00);
      if(!temploanMoney.equals(""))loanMoney=new BigDecimal(temploanMoney);
      //��������
      String temploanMonthRate=request.getParameter("loanMonthRate"); 
      BigDecimal loanMonthRate=new BigDecimal(0.00);
      if(!temploanMonthRate.equals(""))loanMonthRate=new BigDecimal(temploanMonthRate);
      //���»���Ϣ
      String tempcorpusInterest=request.getParameter("corpusInterest");
      BigDecimal corpusInterest=new BigDecimal(0.00);
      if(!tempcorpusInterest.equals(""))corpusInterest=new BigDecimal(tempcorpusInterest);
      //û��ʵ������ֻ���ڼ������»���
      String temploanRepayDay=request.getParameter("temploanRepayDay");
      //���ʽ
      String loanMode=request.getParameter("loanMode");
      int temp_addYear=(Integer.parseInt(tempLoanStartDate.substring(4, 6))+Integer.parseInt(tempLoanTimeLimit))/12;
      int temp_moth=(Integer.parseInt(tempLoanStartDate.substring(4, 6))+Integer.parseInt(tempLoanTimeLimit))%12;
      //�ж��ǲ������һ���µ�����������ǿ�������һ����
      int month_day = BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear),temp_moth);
      //������������
      String overDay="";
      if(month_day<Integer.parseInt(tempLoanStartDate.substring(6,8))){
        overDay=month_day+"";
      }else{
        overDay=tempLoanStartDate.substring(6,8);
      }
      //��������
      String overTime="";
      if(temp_moth<10){
        if(temp_moth==0){
          overTime =(Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear-1)+""+"12"+""+overDay;
        }else{
          overTime =(Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear)+""+"0"+temp_moth+""+overDay;
        } 
      }
      else{
        overTime =(Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear)+""+temp_moth+""+overDay; 
      }
      //������
      String loanRepayDay="";
      //��������
      if(loanRepayDayInfo.equals("1")){
        loanRepayDay=tempLoanStartDate.substring(6, 8);
        temploanRepayDay=loanRepayDay;
      }
      //�������»�����
      String firstLoanMoney="";
      //��������������ܹ�������:����µ�����-��������+�¸�������
      int tempmonth_day = BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0, 4))),(Integer.parseInt(tempLoanStartDate.substring(4, 6))));
      int tloanRepayDay=new Integer(temploanRepayDay).intValue();
      if(tloanRepayDay>28){
        //�жϵ�һ���µû������ǲ�������һ�졣
        int tempnextday=0;
        if(Integer.parseInt(tempLoanStartDate.substring(4, 6))==12){
          tempnextday=BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0, 4))),Integer.parseInt("01"));   
        }else{
        tempnextday=BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0, 4))),(Integer.parseInt(tempLoanStartDate.substring(4, 6)))+1);
        }
        if(tloanRepayDay>tempnextday){
          tloanRepayDay=tempnextday;
        }
      }
      int fristday=tempmonth_day-Integer.parseInt(tempLoanStartDate.substring(6,8))+(tloanRepayDay);
      if(loanMode.equals("2")){
        //�ȶϢ
        //��������
        if(loanRepayDayInfo.equals("1")){
          //Ӧ����Ϣ+Ӧ������
          firstLoanMoney= (loanMoney.multiply(loanMonthRate)).add(corpusInterest.subtract(loanMoney.multiply(loanMonthRate))).toString();
        }
        //ͳһ����
        else{
//        Ӧ����Ϣ+Ӧ������
          firstLoanMoney=((loanMoney.multiply(new BigDecimal(fristday+""))).multiply(loanMonthRate).divide(new BigDecimal("30"),2, BigDecimal.ROUND_HALF_UP)).add(corpusInterest.subtract(loanMoney.multiply(loanMonthRate))).toString();
        }
      }else{
        //�ȶ��
        
        //��������
        if(loanRepayDayInfo.equals("1")){
          //����ʣ�౾��*������+�»�����
          firstLoanMoney=(loanMoney.multiply(loanMonthRate)).add(loanMoney.divide(new BigDecimal(tempLoanTimeLimit),2, BigDecimal.ROUND_HALF_UP)).toString();
        }
        //ͳһ����
        else{
         
         //
          firstLoanMoney=((loanMoney.multiply(loanMonthRate)).multiply(new BigDecimal(fristday+"")).divide(new BigDecimal("30"), 2, BigDecimal.ROUND_HALF_UP)).add(loanMoney.divide(new BigDecimal(tempLoanTimeLimit),2, BigDecimal.ROUND_HALF_UP)).toString();
        }
      }
      String text = null;
      text = "displays('" + overTime + "','" + loanRepayDay + "','" + firstLoanMoney + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      String text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    }
    return null;
  }
}
