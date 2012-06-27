package org.xpup.hafmis.signjoint.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.xpup.hafmis.signjoint.util.SignTools;
import javax.servlet.ServletContext;

import org.xpup.hafmis.signjoint.task.ServerTask;
/**
 * �߳�������
 * @author yinchao
 *
 */
public class Service extends Thread{

  private IThreadPool pool=null;//�̳߳�
  private ServerSocket server=null;//server�׽���
  private ServletContext sc=null;
  public Service(ServletContext sc)
  {
    this.sc=sc;
    SignTools.setServletContext(sc);
    BatchSignTools.setRequestFile(sc.getRealPath("/"));
    BatchSignTools.setResponsepath(sc.getRealPath("/"));
    System.out.println("The realpath is "+sc.getRealPath("/"));
  }
  /**
   * ����Thread���еķ���������execute
   */
  public void run() {
    execute();
  }
  
  /**
   * ��������ķ���
   */
  public void execute()
  {
    server=ServerConnect.getServerConnect();
    pool= new ThreadPool();

    while(true)
    {
      Socket sk=null;
      try{
        if((server!=null)&&(!server.isClosed()))
        {
          sk=server.accept();
          pool.execute(new ServerTask(sk,sc));
        }
        else
        {
          break; 
        }
      }
      catch(IOException ie)
      {
        //ie.printStackTrace();
      } 
    }//while
    

  }
  
  /**
   * �ͷ���Դ
   */
  public void destroyService()
  {
    try { 
      pool.join();
      if(!server.isClosed())
       server.close();
    } catch (Exception e) {}
  
  }



}