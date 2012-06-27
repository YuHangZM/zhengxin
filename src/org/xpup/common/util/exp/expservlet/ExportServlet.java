package org.xpup.common.util.exp.expservlet;



import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xpup.common.util.exp.Iexportdatasource.ExportDateSourceInterface;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;




 
/**
 * @author Administrator
 * 
 * TODO ���servelet��session�л�ȡ������������Ҫ��һ�ж����������� jasperprint����
 */
public class ExportServlet extends HttpServlet {

  private static final long serialVersionUID = -7219742278496459509L;

  public void service(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    ServletContext context = this.getServletConfig().getServletContext();
    String EXP_NAME=request.getParameter("EXP_NAME");
    Date date=new Date();
      String day=BusiTools.dateToString(date,BusiConst.PUB_LONG_DATE_PATTERN);
      String time=BusiTools.dateToString(date,BusiConst.PUB_LONG_TIME_PATTERN);
      String shi=time.substring(0,2);
      String fen=time.substring(3,5);
      String miao=time.substring(6,8);
      String wjztime=day+","+shi+","+fen+","+miao;
      String filename=EXP_NAME+day+","+shi+","+fen+","+miao;
      
    String jasperSrc = context.getRealPath("/expXml/"
        + EXP_NAME + ".xml");
    String xlsSrc = context.getRealPath("/xlsFile/"
        + filename + ".xls");
    HttpSession session=request.getSession(false);
    String para=(String)session.getAttribute("para"); //orgID
    List explist=(List)session.getAttribute("explist");
        
    File xmlfile = new File(jasperSrc);
    
    String ACTION=request.getParameter("ACTION");
    String ISCANSHU=request.getParameter("ISCANSHU");
    
    if(ISCANSHU.equals("true")){
    String cn = "org.xpup.hafmis.common.util.exportDatabase." + EXP_NAME + "ParameterBean";
    Map pmap = request.getParameterMap();
    Set keys = pmap.keySet();
    Iterator it = keys.iterator();
    HashMap mmp = new HashMap();

    while (it.hasNext()) {
      String key = (String) it.next();
//      log.info(key);
      mmp.put(key, request.getParameter(key));
    }

    Object po = null;
    

    Class c = null;
    try {
      c = Class.forName(cn);
    } catch (ClassNotFoundException e1) {
      // TODO �Զ����� catch ��
//      log.info("�Ҳ����࣡");
      e1.printStackTrace();
    }

    Class[] parasType = new Class[1];
    parasType[0] = Map.class;
    Method m = null;
    try {
      m = c.getMethod("setAllParameters", parasType);
//      log.info(" �ҵõ����������");
    } catch (NoSuchMethodException e2) {
      // TODO �Զ����� catch ��
      e2.printStackTrace();
    }
    Object[] args = new Object[1];
    args[0] = mmp;
      
    try {
      po = c.newInstance();
//      log.info("����ʵ���ɹ�");
    } catch (InstantiationException e) {
      // TODO �Զ����� catch ��
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO �Զ����� catch ��
      e.printStackTrace();
    }
    
    
    try {
      m.invoke(po, args);
//      log.info("�������в�����");
    } catch (IllegalArgumentException e3) {
      // TODO �Զ����� catch ��
      e3.printStackTrace();
    } catch (IllegalAccessException e3) {
      // TODO �Զ����� catch ��
      e3.printStackTrace();
    } catch (InvocationTargetException e3) {
      // TODO �Զ����� catch ��
      e3.printStackTrace();
    }
//     rpb=(ReportParameterBagInterface)po;
    }
    
    String datasource="org.xpup.hafmis.common.util.exportDatabase."+EXP_NAME+"DataSource";
    Class dataclass=null;
    try {
      dataclass = Class.forName(datasource);
    } catch (ClassNotFoundException e4) {
      // TODO �Զ����� catch ��
      e4.printStackTrace();
    }
    ExportDateSourceInterface makeFile=null;
    //File file=null;
    try {
      makeFile = (ExportDateSourceInterface)dataclass.newInstance();
    } catch (InstantiationException e5) {
      // TODO �Զ����� catch ��
      e5.printStackTrace();
    } catch (IllegalAccessException e5) {
      // TODO �Զ����� catch ��
      e5.printStackTrace();
    }
    try {
      makeFile.makeFile(xmlfile,xlsSrc,para,explist);
    } catch (Exception e6) {
      // TODO �Զ����� catch ��
      e6.printStackTrace();
    }
       File file = new File(xlsSrc);
       if(!file.exists()){
        System.out.println("û������xls�ļ�!!!");
        file=new File(context.getRealPath("/xlsFile/error.xls"));
       }
        FileInputStream fileinputstream = new FileInputStream(file);
        long l = file.length();
       // System.out.println("�õ��ļ��ĳ��ȣ�"+l);
        boolean flag = false;
        int k = 0;
        byte abyte0[] = new byte[65000];
        response.setContentType("application/vnd.ms-excel");
        if(filename.length()>4){
          String ok=filename.substring(0,5);
//          log.info(" �õ�������:filenameok " + ok);          
              if(ok.equals("uc06_org_drawing_exp")){
                filename="��λ��ȡְ������"+wjztime;
              }
             
        }
        response.setHeader("Content-disposition", "attachment; filename=" + filename + ".xls");

        response.setContentLength( (int) l);
       
        while ( (long) k < l) {
          int j = fileinputstream.read(abyte0, 0, 65000);
          k += j;
          response.getOutputStream().write(abyte0, 0, j);
        }
        fileinputstream.close();
      
  }
    

}

