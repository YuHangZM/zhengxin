package org.xpup.hafmis.signjoint.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.io.*;
import java.math.BigDecimal;

import javax.servlet.ServletContext;

import org.xpup.hafmis.signjoint.dto.ConfigDTO;
/**
 * ������
 * ��Ҫʲô�͵��������Ұ�
 * @author yinchao
 */
public class SignTools {
  private static ServletContext sc=null;
  public static XmlIO xo=null;//XML�����

  /**
   * ��ȡ��ǰServlet������
   * @return ��ǰServlet������
   */
  public static ServletContext getServletContext() {
    return sc;
  }
  /**
   * ���õ�ǰServlet������
   * @param sc Servlet������
   */
  public static void setServletContext(ServletContext sc) {
    SignTools.sc = sc;
    xo=new XmlIO(sc);
  }  
//����Ϊ���ù��߷���
//------------------------------------------------------------------  
  /**
   * ת�����ݸ�ʽ
   * @param ���зָ������ַ���
   * @return ���ַ�����ȡ��List
   */
  public static List Compart(String temp)
  {
    List list=new ArrayList();
    String str=null;
    int pointer=0;
    int length=temp.length();
    String mark=xo.getConfig().getMark();
    for(int i=0;i<length;i++)
    {
      str=temp.substring(i,i+1);
      if(str.equals(mark))
      {
       list.add(temp.substring(pointer,i));
       pointer=i+1;
      }
    }
    if(list.size()!=0){
      return list;
    }
    else{
      list.add(temp);
      return list;
    }
    //list.add(temp.substring(pointer,temp.length()));//ĩβ���ӷָ���
    
  }
  
  /**
   * ����������List�е������Զ��ŷָ����ϲ���һ��String(ĩβ��'|')
   * @param �����������List
   * @return ����ϲ���һ���ض���ʽ���ַ���
   */
  public static String combinationWithComma(List temp)
  {
    Iterator iter=temp.iterator();
    StringBuffer sb= new StringBuffer();
    String mark=",";
    String temp_str=null;
    while(iter.hasNext())
    {
      sb.append(((String)iter.next()+mark));
    }
    temp_str=sb.toString();
    temp_str=temp_str.substring(0,temp_str.length()-1)+xo.getConfig().getMark();
    return temp_str;
    //return sb.toString().substring(1);//���ĩβ���ӷָ���
    
  }
  
  /**
   * ����������List�е������Զ��ŷָ����ϲ���һ��String(ĩβ��',')
   * @param �����������List
   * @return ����ϲ���һ���ض���ʽ���ַ���
   */
  public static String combinationWithAllComma(List temp)
  {
    Iterator iter=temp.iterator();
    StringBuffer sb= new StringBuffer();
    String mark=",";
    String temp_str=null;
    while(iter.hasNext())
    {
      sb.append(((String)iter.next()+mark));
    }
    return sb.toString();
    //return sb.toString().substring(1);//���ĩβ���ӷָ���
    
  }
  /**
   * ����������List�е����������÷ָ����ϲ���һ��String
   * @param �����������List
   * @return ����ϲ���һ���ض���ʽ���ַ���
   */
  public static String Combination(List temp)
  {
    Iterator iter=temp.iterator();
    StringBuffer sb= new StringBuffer();
    String mark=xo.getConfig().getMark();
    while(iter.hasNext())
    {
      sb.append(((String)iter.next()+mark));
      
    }
    return sb.toString();
    //return sb.toString().substring(1);//���ĩβ���ӷָ���
    
  }
  
  /**
   * ��������λС���Ľ��ת�������и�ʽ
   * @param money������ΪС���Ľ��
   * @return ���н��յĸ�ʽ��ȥ��С���㣩
   */
  public static String BigDecimaltoLang(BigDecimal money)
  {
    long tolang=(long)(money.doubleValue()*100);
    if(tolang==0){
      return "0";
    }
    String str=Long.toString(tolang);
    return str.substring(0,str.length()-2)+"."+str.substring(str.length()-2);
  }
  
//------------------------------------------------------------------
//����Ϊ���ز�������ķ���
//------------------------------------------------------------------
  /**
   * ���ز���ʧ�ܵ�����
   * @param disposalnum ������
   * @return ʧ������
   */
  public static String getFailedInfo(String disposalnum)
  {
    List list=new ArrayList();
    list.add("01");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }

  /**
   * ���ز����ɹ�������
   * @param disposalnum ������,data ��ѯ�������,remark ��ע����
   * @return �ɹ�����
   */
  public static String getSuccessInfo(String disposalnum,List data,List remark){
    List list=new ArrayList();
    list.add("00");
    list.add(disposalnum);
    Iterator it=data.iterator();
    while(it.hasNext())
    {
      list.add(it.next());
    }
    return SignTools.Combination(list)+SignTools.combinationWithComma(remark);
  }
  /**
   * ���ز����ɹ�������
   * @param disposalnum ������,data ��ѯ�������,remark ��ע����
   * @return �ɹ�����
   */
  public static String getSuccessInfo(String disposalnum,List data){
    List list=new ArrayList();
    list.add("00");
    list.add(disposalnum);
    Iterator it=data.iterator();
    while(it.hasNext())
    {
      list.add(it.next());
    }
    return SignTools.Combination(list);
  }
  /**
   * ���02���������������
   * �ڴ�������ѯ��������ϸ��ѯʱ
   * ������û�û����,�򷵻أ�����+������
   * @param disposalnum������
   * @return 02���������������
   */
  public static String getInfo_02(String disposalnum){
    List list=new ArrayList();
    list.add("02");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * ���03���������������
   * �������ϸ��ѯ��������ϸ��ѯʱ����ѯ��ʱ�䷶Χ�������6���£�
   * ���ܾ���ѯ�����أ�����+������
   * @param disposalnum������
   * @return 03���������������
   */
  public static String getInfo_03(String disposalnum){
    List list=new ArrayList();
    list.add("03");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * ���04���������������
   * ���������ѯ�������ϸ��ѯ�������ѯ��������ϸ��ѯ
   * ʱ������δǩԼ���û�,�򷵻�:����+������ 
   * @param disposalnum������
   * @return 04���������������
   */
  public static String getInfo_04(String disposalnum){
    List list=new ArrayList();
    list.add("04");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * ���05���������������
   * ������Ϣϵͳ�緢��IO���������ҵ���޹ش��󣬽����أ�����
   * @return 05���������������
   */
  public static String getInfo_05(){
    List list=new ArrayList();
    list.add("05");
    return SignTools.Combination(list);
  }
  /**
   * ���06���������������
   * ���д���������Ϊ�Ƿ����ݸ�ʽ��Ƿ����ݣ����أ�����+������
   * @param disposalnum������
   * @return 06���������������
   */
  public static String getInfo_06(String disposalnum){
    List list=new ArrayList();
    list.add("06");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }

  /**
   * ���07���������������
   * �ڴ����ѯ��������ϸ��ѯʱ������û��Ѿ�ǩԼ����ְ��������
   * �����֤����Ϣ�ڹ�������Ϣϵͳ�в����ڣ����أ�����+������
   * @param disposalnum������
   * @return 07���������������
   */
  public static String getInfo_07(String disposalnum){
    List list=new ArrayList();
    list.add("07");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  /**
   * ���08���������������
   * ������ѯ�������ϸ��ѯʱ������û��Ѿ�ǩԼ��
   * ��ְ�������Ϣ�ڹ�������Ϣϵͳ�в����ڣ����أ�����+������
   * @param disposalnum������
   * @return 08���������������
   */
  public static String getInfo_08(String disposalnum){
    List list=new ArrayList();
    list.add("08");
    list.add(disposalnum);
    return SignTools.Combination(list);
  }
  
  
//------------------------------------------------------------------
  /**
   * �ж�List��Ԫ�صĸ����Ƿ����num
   * @param listҪ�жϸ�����List
   * @param num Ҫ�жϵĸ���
   * @return �Ƿ����
   */
  public static boolean isRightNum(List list,int num){
    if(list.size()==num){
      return true;
    }
    else{
      return false;
    }
  }

//����Ϊ��ֵת������
//------------------------------------------------------------------
  /**
   * �ж��Ƿ��ǿ�ֵ����ת��
   * @param money ����������
   * @return ת����Ľ��
   */
  public static BigDecimal parseNull(BigDecimal money){
    if(money!=null){
      return money;
    }
    return new BigDecimal(0);  
  }
  /**
   * �ж��Ƿ��ǿ�ֵ����ת��
   * @param num ��������
   * @return ת����Ľ��
   */
  public static Integer parseNull(Integer num){
    if(num!=null){
      return num;
    }
    return new Integer(0);
    
  }  
  /**
   * �ж��Ƿ��ǿ�ֵ����ת��
   * @param str �ַ���
   * @return ת����Ľ��
   */
  public static String parseNull(String str){
    if(str!=null){
      return str;
    }
    return "";
  }
  /**
   * �ж��Ƿ��ǿ�ֵ����ת��
   * @param str �ַ���
   * @return ת����Ľ��
   */
  public static Long parseNull(Long lon){
    if(lon!=null){
      return lon;
    }
    return new Long(0);
  }
//------------------------------------------------------------------
//����Ϊ���ù��߷���
//------------------------------------------------------------------  
  /**
   * ����������ȥ���ָ���
   * @param   ������
   * @return �洢�Ŵ�����������ȡ���ݵ�List
   */
  public static List Compart(InputStream in)
  {
    InputStreamReader isr=new InputStreamReader(in);
    BufferedReader bin=new BufferedReader(isr);
    int ch;
    StringBuffer sb= new StringBuffer();
    try {
      while((ch=bin.read())!=-1)
      {
        sb.append(ch);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Compart(sb.toString());
  }

  /**
   * �ӻ������ж�ȡ���ݣ�ת����String
   * @param in �ַ���
   * @return ת����Ľ��
   * @throws IOException
   */
  public static String ReaderToString(BufferedReader in) throws IOException{
    
    char [] ch=new char[300];
    int number=0;
    char mark=xo.getConfig().getMark().charAt(0);
    number=in.read(ch);
    String temp=new String(ch).trim();
    if(number!=-1){
      return temp;
    }
    else{
      return "";
    } 
  }
  /**
   * �����Ϣ���
   * @return ��ʼ���ºͽ�������
   */
  public static String [] getCheckOutAccrual(){
    String curdate=null;
    String startdate=null;
    String enddate=null;
    Date d = new Date();
    String year = Integer.toString(d.getYear()+1900);
    String month = Integer.toString(d.getMonth()+1);
    String day = Integer.toString(d.getDate());
    if(month.length()==1) month="0"+month;
    if(day.length()==1) day="0"+day;  
    StringBuffer sb=new StringBuffer();
    if((d.getMonth()+1)>6){
      startdate=Integer.toString(d.getYear()+1900)+"07";
      enddate=Integer.toString(d.getYear()+1900+1)+"06";
      String [] str={startdate,enddate};
      return str;
    }else{
      startdate=Integer.toString(d.getYear()+1900-1)+"07";
      enddate=Integer.toString(d.getYear()+1900)+"06";
      String [] str={startdate,enddate};
      return str;
    }
  }
  /**
   * �������µ��ַ�����������
   * @param yyyymmָ������
   * @param xm�Ӽ���
   * @return �����������ַ���
   */
  public static String getNextXMonth(String yyyymm,int xm)
  { 
    if("".equalsIgnoreCase(yyyymm)){
      return yyyymm;
    }
    int year=0;
    int month=0;
    year=Integer.parseInt(yyyymm.trim().substring(0,4));
    month=Integer.parseInt(yyyymm.trim().substring(4));
    month=month+xm;
    if(month<=12){
      return Integer.toString(year*100+month);
    }else{
      year=year+month/12;
      month=month%12;
      return Integer.toString(year*100+month);
    } 
  }
  
  /**
   * �������µ��ַ�����������
   * @param yyyymmָ������
   * @param xm������
   * @return �����������ַ���
   */
  public static String getPreviousXMonth(String yyyymm,int xm)
  { 
    if("".equalsIgnoreCase(yyyymm)){
      return yyyymm;
    }
    int year=0;
    int month=0;
    year=Integer.parseInt(yyyymm.trim().substring(0,4));
    month=Integer.parseInt(yyyymm.trim().substring(4));
    month=month-xm;
    if(month>0){
      return Integer.toString(year*100+month);
    }else{
      year=year-((-(month-1)/12)+1);
      month=12+(month)%12;
      return Integer.toString(year*100+month);
    } 
  }
  
 /**
   * ��List�еļ�¼��i�����ִ�
   * @param list Ҫ�ÿմ���list
   * @param i �ÿմ�������
   * @return ��i�����ִ����list
   */
  public static List rowIsNull(List list,int i){
    for(int j=0;j<i;j++){
      list.add("");
    }
    return list;
  }

  
  /**
   * ���ݿۿ��ջ�õ�ǰ���������·�
   * @param repayday �ۿ���
   * @return ���������·�
   */
  public static String getLoanRepayYearMonth(String repayday){
    String curdate=null;
    String startdate=null;
    String enddate=null;
    Date d = new Date();
    String year = Integer.toString(d.getYear()+1900);
    String month = Integer.toString(d.getMonth()+1);
    String day = Integer.toString(d.getDate());
    int m=0;
    int y=0;
    if(month.length()==1) month="0"+month;
    if(day.length()==1) day="0"+day;
    if(Integer.parseInt(day)>Integer.parseInt(repayday.trim())){
      return year+month;
    }else{
      m=Integer.parseInt(month)-1;
      y=Integer.parseInt(year)-1;
      if(m!=0){
        return year+Integer.toString(m);  
      }else{
        return Integer.toString(y)+"12";
      }
    }
  }
  /**
   * ��һ�������ձȵڶ��������մ���ٸ���
   * @param yyyymmdd1 ���������
   * @param yyyymmdd2 С��������
   * @return ��������,�������ʧ�ܷ���-1
   */
  public static int dateBiggerThanDateXMonth(String yyyymmdd1,String yyyymmdd2){
    if("".equalsIgnoreCase(yyyymmdd1)||"".equalsIgnoreCase(yyyymmdd2)){
      return -1;
    }
    if((Integer.parseInt(yyyymmdd1)-Integer.parseInt(yyyymmdd2))<0){
      return -1;
    }

    int dd1=Integer.parseInt(yyyymmdd1.substring(6));
    int dd2=Integer.parseInt(yyyymmdd2.substring(6));   
    yyyymmdd1=yyyymmdd1.substring(0,6);
    yyyymmdd2=yyyymmdd2.substring(0,6);
    int year1=0;
    int month1=0;
    int year2=0;
    int month2=0;
    year1=Integer.parseInt(yyyymmdd1.trim().substring(0,4));
    month1=Integer.parseInt(yyyymmdd1.trim().substring(4));
    year2=Integer.parseInt(yyyymmdd2.trim().substring(0,4));
    month2=Integer.parseInt(yyyymmdd2.trim().substring(4));
    int sumy=year1-year2;
    int summ=month1-month2;
    //System.out.println((dd1-dd2)>=0?(sumy*12+summ):(sumy*12+summ-1));
    return (dd1-dd2)<=0?(sumy*12+summ):(sumy*12+summ+1);    
  }
  /**
   * У���Ƿ�Ϊ��ȷ������
   * @param date ����
   * @return �Ƿ�Ϊ�Ϸ����� �Ϸ�����true,�Ƿ�����false
   * @throws ParseException 
   */
  
  public static boolean isRightDate(String date) {
    if(date.length()!=8){
      return false;
    }
    String year=date.substring(0,4);
    String month=date.substring(4,6);
    String day=date.substring(6);
    if((!year.matches("[0-9]{4}"))||(!month.matches("[0-9]{2}"))||(!day.matches("[0-9]{2}"))){
      return false; 
    }
    int y=Integer.parseInt(year);
    int m=Integer.parseInt(month);
    int d=Integer.parseInt(day);
    boolean run=(y%4)==0;//�Ƿ�������
    if(!(m>0&&m<=12)){
      return false;
    }
    if(m==2){//�����ѯ�·�Ϊ2��
      if(!run){//�Ƿ�Ϊ����
        if(d>0&&d<=28)
          return true;
        else
          return false;
      }else{
        if(d>0&&d<=29)
          return true;
        else
          return false;
      }
    }
    int [] small={4,6,9,11};//С��
    for(int i=0;i<4;i++){//�ж��Ƿ�ΪС��
      if(m==small[i]){
         if(d>0&&d<=30){
           return true;
         }else{
           return false;
         }
      }
    }
    return true; 
  }
  
  /**
   * �Ƿ�Ϊ��ȷ��ǩԼ��
   * @param empid ǩԼ��
   * @return trueΪ��ȷ��ְ���� falseΪ�Ƿ���ְ����
   */
  public static boolean isRigntSignid(String empid){
    if(empid.matches("\\d+")){
      return true;
    }else{
      return false;
    }
  }
  

  /**
   * ��18λ���֤��תΪ15λ
   * @param cardnum ���֤��
   * @return ת��������֤��
   */
  public static String conversionTo15DigitCard_num(String cardnum){
    return cardnum.substring(0,6)+cardnum.substring(8, cardnum.length()-1); 
  }
  
  
  
  public void readParam(){
    ResourceBundle bundle; 
  }
  

  
  
  
  
  
  
  
  
  
}
