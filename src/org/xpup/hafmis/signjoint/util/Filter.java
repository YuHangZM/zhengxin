package org.xpup.hafmis.signjoint.util;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Filter {

  /**
   * У��list���Ƿ��п�ֵ
   * @param list
   * @param num
   * @return �Ƿ��п�ֵ���Ƿ���true �񷵻�false��
   */
  public static boolean doFilter(List list,int num){
    if(list.size()==num){
      Iterator inter=list.iterator();
      String temp=null;
      while(inter.hasNext()){
        temp=(String)inter.next();
        if("".equalsIgnoreCase(temp)){
          return false;
        }
      }//while
      return true;
    }//list.size()==num
    return false;
  }




}
