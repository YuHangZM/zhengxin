package org.xpup.signjoint.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.*;

import org.xpup.signjoint.dto.ConfigDTO;
/**
 * ������
 * @author yinchao
 *
 */
public class SignTools {

  public static XmlOutput xo=new XmlOutput();//XML�����
  
  /**
   * ת�����ݸ�ʽ
   * @param ���зָ������ַ���
   * @return ���ַ�����ȡ��List
   */
  public static List Compart(String temp)
  {

    List list=new ArrayList();
    String mark=null;
    int pointer=0;
    for(int i=0;i<temp.length();i++)
    {
      mark=temp.substring(i,i+1);
      if(mark.equals(xo.getConfig().getMark()))
      {
       list.add(temp.substring(pointer,i));
       pointer=i+1;
      }
    }
    //list.add(temp.substring(pointer,temp.length()));//ĩβ���ӷָ���
    return list;
  }
  
  /**
   * ����������List�е����ݺϲ���һ��String
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
  public static long DoubletoLong(double money)
  {
    money=money*100;
    return (long)money;
  }
  
  
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
}
