package org.xpup.hafmis.signjoint.dto;

public class ConfigDTO {
  int port;//�˿�
  String mark;//�ָ���
  int threadnum;//�߳���
  public final static String CODE ="1001";//����Ľ�����
  public ConfigDTO()
  {
    port=0;
    mark=" ";
    threadnum=5;
  }
  public String getMark() {
    return mark;
  }
  public void setMark(String mark) {
    this.mark = mark;
  }
  public int getPort() {
    return port;
  }
  public void setPort(int port) {
    this.port = port;
  }
  public int getThreadnum() {
    return threadnum;
  }
  public void setThreadnum(int threadnum) {
    this.threadnum = threadnum;
  }
  
  
}
