package org.xpup.hafmis.signjoint.quartz;

import java.util.Date;
import java.util.List;

import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;

import org.xpup.hafmis.signjoint.dto.HistoryDTO;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.util.BatchSignTools;

public class SendJob {
  private boolean isRunning=false;
  private Object ibs=null;
  public Object getIbs() {
    return ibs;
  }
  public void setIbs(Object ibs) {
    this.ibs = ibs;
  }
  public void doSendJob(){
    if(!isRunning){
      isRunning=true;
      String mark="|";
      System.out.println(new Date()+" ����������ǩԼ����ʼִ�У�");
      ISignjointBS bs=(ISignjointBS)ibs;
      List list=bs.prepareSendFile();
      try{
       BatchSignTools.setRequestFile("gjjkqy_"+BatchSignTools.getTodayDateString()+".txt");
       BatchSignTools.superAdditiontoTodayFile(BatchSignTools.getRequestpath(),BatchSignTools.getRequestFile(),mark+Integer.toString(list.size())+mark);
       //���|�ֿ��˿���|�ֿ�������|�ֿ������֤����|�������ʻ���|��ע|
       int size=list.size();
       for(int i=0;i<size;i++){
         HistoryDTO dto=(HistoryDTO)list.get(i);
         BatchSignTools.superAdditiontoTodayFile(BatchSignTools.getRequestpath(),BatchSignTools.getRequestFile(),
         Integer.toString(i)+mark+dto.getBankcardid()+mark+dto.getEmpname()+mark+dto.getCardnum()+
         mark+dto.getEmpid()+mark+dto.getSign()+mark);
       }
       //��¼�ļ����ɼ�¼
       LogDTO logdto=new LogDTO();
       logdto.setFile_name(BatchSignTools.getRequestFile());
       logdto.setOperation_type("0");
       bs.logFile(logdto);
      }
      catch(Exception e){
        e.printStackTrace();
      }
      
      BatchSignTools.setResponseFile("gjjkqyhp_"+BatchSignTools.getTodayDateString()+".txt");
      System.out.println(new Date()+" ����������ǩԼ�������ִ�У�");
      isRunning=false;
    }
    else{
      System.out.println("ǰһ������ǩԼ������ִ�У�");
    }
  }
}
