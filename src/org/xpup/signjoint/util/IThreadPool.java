package org.xpup.signjoint.util;

/**
 * ���ӳؽӿ�
 */
public interface IThreadPool {
  /**��������*/
  public void execute(Runnable task) ;
  /**��õ�ǰ����*/
  public Runnable getTask()throws InterruptedException;
  /**ǿ�ƹر�*/
  public void close();
  /**�ȴ�ȫ��������ɺ�ر�*/
  public void join();
  
}
