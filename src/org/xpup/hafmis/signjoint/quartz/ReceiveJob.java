package org.xpup.hafmis.signjoint.quartz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.dto.LogDTO;
import org.xpup.hafmis.signjoint.dto.RecieveFileDTO;
import org.xpup.hafmis.signjoint.util.BatchSignTools;
import org.xpup.hafmis.signjoint.util.SignTools;

public class ReceiveJob {
  private boolean isRunning=false;
  private Object ibs=null;
  public Object getIbs() {
    return ibs;
  }
  public void setIbs(Object ibs) {
    this.ibs = ibs;
  }
  public void doReceiveJob() throws IOException{
    if(!isRunning){
      isRunning=true;
      System.out.println(new Date()+" ����������ǩԼȷ������ʼִ�У�");
      
      if(!BatchSignTools.isHaveResponseFile()){//������ֹ���
        System.out.println(new Date()+" ����������ǩԼ�����й��ϣ�");
        System.out.println("�����Ա��飺");
        System.out.println("1,�����������Ƿ���ͨ");
        System.out.println("2,�������Ƿ�崻�");
        System.out.println("3,FTP�����Ƿ���");
        //String str=BatchSignTools.readResponseFile(BatchSignTools.getResponseFile());
        //BatchSignTools.setRequestFile("gjjkqy_"+BatchSignTools.getTodayDateString()+".txt");
        //BatchSignTools.superAdditiontoTodayFile(BatchSignTools.getRequestpath(),BatchSignTools.getRequestFile(), str);
        System.out.println(new Date()+" ����������ǩԼȷ���������ִ�У�");
        isRunning=false;
        return ;
      }
      
      String content=BatchSignTools.readResponseFile(BatchSignTools.getResponseFile());
      ISignjointBS bs=(ISignjointBS)ibs;
      List data=SignTools.Compart(content);
      System.out.println(content);
      int size=data.size();
      List list=new ArrayList();
      try{
       for(int i=0;i<size;i=i+7){
        RecieveFileDTO dto=new RecieveFileDTO();
        //���|�ֿ��˿���|�ֿ�������|�ֿ������֤����|�������ʻ���|��ע|�ɹ���־|
        dto.setId((String)data.get(i));
        dto.setBankcardid((String)data.get(i+1));
        dto.setName((String)data.get(i+2));
        dto.setCardnum((String)data.get(i+3));
        dto.setEmpid((String)data.get(i+4));
        dto.setSign((String)data.get(i+5));
        dto.setS_f((String)data.get(i+6));
        list.add(dto);
       }
       bs.prepareReceiveFile(list);
      }catch(Exception e){//�����ʽ���Ծ��˳�
        e.printStackTrace();
        System.out.println(new Date()+" ���ĸ�ʽ����");
        System.out.println(new Date()+" ����������ǩԼȷ���������ִ�У�");
        isRunning=false;
        return;
      }
      
      //�����ļ���¼
      try{
        LogDTO logdto=new LogDTO();
        logdto.setFile_name(BatchSignTools.getResponseFile());
        logdto.setOperation_type("1");
        bs.logFile(logdto);
        bs.clearTemp();//��ջ����
      }catch(Exception e){
        e.printStackTrace();
      }
      System.out.println(new Date()+" ����������ǩԼȷ���������ִ�У�");
      isRunning=false;
    }
    else{
      System.out.println("ǰһ������������ִ�У�");
    }
  }


}
