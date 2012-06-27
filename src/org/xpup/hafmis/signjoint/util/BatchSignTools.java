package org.xpup.hafmis.signjoint.util;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/**
 * ����ǩԼ������
 * @author yinchao
 */
public class BatchSignTools {

  public static String requestFile="gjjkqy_"+getTodayDateString()+".txt";//�����ļ��� 
  public static String responseFile="gjjkqyhp_"+getTodayDateString()+".txt";//���з����ļ���
  public static String requestpath="";
  public static String responsepath="";
  
  
  /**
   * �Ƿ��л����ļ�
   * @return
   */
  public static boolean isHaveResponseFile(){
    File f=new File(responsepath,responseFile); 
    return f.exists();
  }
  
  
  
  /**
   * ��ý������ڵ��ַ��� 
   * @return ��ʽΪ��YYYYMMDD
   */
  public static String getTodayDateString(){
    Date d = new Date();
    String year = Integer.toString(d.getYear()+1900);
    String month = Integer.toString(d.getMonth()+1);
    String day = Integer.toString(d.getDate());
    if(month.length()==1) month="0"+month;
    if(day.length()==1) day="0"+day;
    return year+month+day;
  }
  /**
   * �ж��ļ��Ƿ��Ѵ���
   * @param path ·��
   * @return
   */
  public static boolean isHaveTodayFile(String path){
    File f=new File(path,requestFile); 
    return f.exists();
  }
  /**
   * ���ļ�β׷���ı�
   * @param path �ļ�·��
   * @param file �ļ���
   * @param content Ҫ׷�ӵ��ı�
   * @throws IOException 
   */
  public static void superAdditiontoTodayFile(String path,String file,String content) throws IOException{
    FileWriter fw=new FileWriter(path+file,true);   
    PrintWriter pw=new PrintWriter(fw);   
    pw.print(content);   
    pw.close();   
    fw.close();
  }
  /**
   * ���������ļ�
   * @param path ·��
   * @throws IOException
   */
  public static void mkRequestTodayFile() throws IOException{
    File f=new File(requestpath,requestFile); 
    f.createNewFile();
  }
  
  /**
   * ��ȡ����
   * @param path �ļ�·��
   * @return ����
   * @throws IOException 
   */
  public static String readResponseFile(String name) throws IOException{
    StringBuffer sb=new StringBuffer();
    File response=new File(responsepath,name);
    FileReader in=new FileReader(response);
    BufferedReader bin=new BufferedReader(in);
    int c;
    while((c=bin.read())!=-1)
      sb.append((char)c);
    bin.close();
    return sb.toString();
  }
  
  /**
   * ��������
   * @param content Ҫ�������ı�
   * @param num ÿ���Ķ���
   * @return Listÿ������ÿ����¼
   */
  public static List Analyse(String content,int num){
    List list=SignTools.Compart(content);
    List returnlist=new ArrayList();
    List temp=new ArrayList();
    int length=list.size();
    for(int i=0;i<length;i++){
      temp.add(list.get(i));
      if(((i+1)%num)==0){
         returnlist.add(temp);
         temp.clear();
      }
    }
    return returnlist;
  }

  /**
   * �����Ƿ�����ȷ��ʽ
   * @param content ����
   * @param num ÿ������Ŀ
   * @return true����ȷ��ʽ false�Ǵ����ʽ
   */
  public static boolean isRightFormat(String content,int num){
    List list=SignTools.Compart(content);
    return list.size()%num==0?true:false;
  }
  /**
   * ��List���ݰ�װ��д���ļ�����
   * @param path �ļ�·��
   * @param file �ļ���
   * @param list ������Ϣ����
   * @throws IOException
   */
  public static void writeRequestInfo(String path,String file,List list) throws IOException{
    superAdditiontoTodayFile(path,file,SignTools.Combination(list));
  }
  /**
   * ��ð�����Ա
   * @return
   */
  public static String getOperater(){
    
    
    return null;
  }
  /**
   * �����'0'ȥ��
   * @param id 
   * @return
   */
  public static String trimID(String id){
    String str;
    int i;
    id=id.trim();
    String temp=id;
    int length=id.length();
    for(i=0;i<length;i++)
    {
      str=temp.substring(i,i+1);
      if(!str.equalsIgnoreCase("0"))
         break;        
    }    
    return id.substring(i);
  }
  
  /**
   * ��'~'�ָ��Ĵ����Ϊ�����ַ���
   * @param str
   * @return
   */
  public static String [] Compart(String str){
    
    String mark="~";
    str=str.trim();
    int length=str.length();
    String [] temp=new String[2];
    int i=0;
    for(i=0;i<length;i++){
      if(str.substring(i,i+1).equalsIgnoreCase(mark))
      {
         temp[0]=str.substring(0,i);
         break;
      }
    }
    temp[1]=str.substring(i+1);
    return temp;
  }
  
  /**
   * ת����ȷ�����ı�ʶ
   * @param str
   * @return
   */
  public static String transformSucc_Fail(String str){
    if(str.equalsIgnoreCase("0")){
      return "�ɹ�";
    }else if(str.equalsIgnoreCase("1")){
      return "ʧ��";
    }else{
      return "δ֪";
    }
  }
  
  /**
   * ��õ�ǰʱ���Timestamp����
   * @return
   */
  public  static Timestamp   getTimestamp()   
  { Calendar   calendar   =   new   GregorianCalendar();   
    String   str="";   
    int   year;   
    int   month;   
    int   date;   
    int   hour;   
    int   minute;   
    int   second;   
    int   million;   
    year   =   calendar.get(Calendar.YEAR);   
    month   =   calendar.get(Calendar.MONTH);   
    date   =   calendar.get(Calendar.DAY_OF_MONTH);   
    hour   =   calendar.get(Calendar.HOUR);   
    minute   =   calendar.get(Calendar.MINUTE);   
    second   =   calendar.get(Calendar.SECOND);     
    str   =Integer.toString(year)+"-"+Integer.toString(month+1)   +   "-"   
    +   Integer.toString(date)   +   " "   +   Integer.toString(hour)   +   ":"   +   Integer.toString(minute)   +   ":"     
    +   Integer.toString(second);    
    return   Timestamp.valueOf(str);   
  }
  
  
  /**
   * ת���ļ�����
   * @param str
   * @return
   */
  public static String transformFile_Type(String str){
    if(str.equalsIgnoreCase("0")){
      return "�����ļ�";
    }else if(str.equalsIgnoreCase("1")){
      return "�����ļ�";
    }else{
      return "δ֪";
    }
  }
  
  
  
  

  
  
  public static String getRequestFile() {
    return requestFile;
  }
  public static void setRequestFile(String requestFile) {
    BatchSignTools.requestFile = requestFile;
  }
  public static String getResponseFile() {
    return responseFile;
  }
  public static void setResponseFile(String responseFile) {
    BatchSignTools.responseFile = responseFile;
  }



  public static String getRequestpath() {
    return requestpath;
  }



  public static void setRequestpath(String requestpath) {
    BatchSignTools.requestpath = requestpath;
  }



  public static String getResponsepath() {
    return responsepath;
  }



  public static void setResponsepath(String responsepath) {
    BatchSignTools.responsepath = responsepath;
  }
  
  


}
