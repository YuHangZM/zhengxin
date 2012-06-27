package org.xpup.hafmis.sendmail.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Properties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.MailMessageDTO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class Sendexceptionmail {
	public static void sendtomail(Object emassage,SecurityInfo securityInfo) {
		try {
      //�鿴�Ƿ�ʹ���ʼ�ϵͳ1��ʹ��0�ǲ�ʹ��
      if(securityInfo.getMailFunction()==1){
        //��÷����˵�������Ϣ���ռ��˵�������Ϣ
       MailMessageDTO dto= securityInfo.getMaildto();
			Sendexceptionmail sendtomail = new Sendexceptionmail();
			//����spring�ʼ���
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
			//��д�����ʼ������ͣ�����smtp�ʼ�Э��
			senderImpl.setHost(dto.getMailboxtype());
//			��д�����ʼ����ʻ�
			senderImpl.setUsername(dto.getAddresser());
//			��д�����ʼ�������
			senderImpl.setPassword(dto.getAddersserpassword());
//			������Դ�ļ�����д����
			Properties mailproperties = new Properties();
      mailproperties.setProperty("mail.smtp.auth", "true");
      mailproperties.setProperty("mail.smtp.timeout", "25000");
			senderImpl.setJavaMailProperties(mailproperties);
//			���͵����ݲ���
			sendtomail.sendMimeMessage(senderImpl,emassage,dto);
      }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//���淢�͵�����
	private void sendMimeMessage(JavaMailSenderImpl sender,final Object emassage,MailMessageDTO dto) throws Exception {
      SimpleMailMessage mail = new SimpleMailMessage();
      int addresseemail=1;
      if(dto.getAddresseeb()!=null&&!dto.getAddresseeb().equals(""))
        addresseemail=addresseemail+1;
      //�ռ�����A
      String[] addressee = new String[addresseemail];
      addressee[0] = dto.getAddresseea();
      //�ռ�����B
      if(dto.getAddresseeb()!=null&&!dto.getAddresseeb().equals(""))
      addressee[1] = dto.getAddresseeb(); 
      mail.setTo(addressee);
      //����������
      mail.setFrom(dto.getAddressermail());
      //���͵���Ŀ
      mail.setSubject(dto.getSubjectname());
      ExceptionDTO dtoe=(ExceptionDTO)emassage;
      if(dtoe!=null){
        //exception�쳣
        if(dtoe.getExcep()!=null){
    	  Exception e=(Exception)dtoe.getExcep();
    	  StringWriter   swe   =   new   StringWriter();   
    	  PrintWriter   w   =   new   PrintWriter(swe);   
    	  e.printStackTrace(w); 
    	  mail.setText("�쳣��ϢΪException��"+swe.getBuffer());
        }
        //BusinessException
        if(dtoe.getBx()!=null){
          BusinessException bx=(BusinessException)dtoe.getBx();
          mail.setText("�쳣��ϢΪBusinessException��"+bx.getMessage());
        }
//      SQLException
        if(dtoe.getSqe()!=null){
          SQLException sqle=(SQLException)dtoe.getSqe();
          StringWriter   sws   =   new   StringWriter();   
          PrintWriter   w   =   new   PrintWriter(sws);   
          sqle.printStackTrace(w); 
          mail.setText("�쳣��ϢΪSQLException��"+sws.getBuffer());
        }
//      IOException
        if(dtoe.getIoe()!=null){
          IOException ioe=(IOException)dtoe.getIoe();
          StringWriter   swi   =   new   StringWriter();   
          PrintWriter   w   =   new   PrintWriter(swi);   
          ioe.printStackTrace(w); 
          mail.setText("�쳣��ϢΪIOException��"+swi.getBuffer());
        }
      }
      sender.send(mail);
	}
}
