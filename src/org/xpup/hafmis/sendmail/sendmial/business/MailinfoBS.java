package org.xpup.hafmis.sendmail.sendmial.business;
/**
 *2008/05/28 
 * shiy
 * �ʼ�ϵͳ
 */

import java.util.List;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sendmail.common.dao.MailinfoDAO;
import org.xpup.hafmis.sendmail.sendmial.bsinterface.IMailinfoBS;
import org.xpup.hafmis.sendmail.sendmial.dto.MailinfoDTO;
import org.xpup.hafmis.sendmail.sendmial.form.MailinfoAF;

public class MailinfoBS implements IMailinfoBS {

  private MailinfoDAO mailinfoDAO = null;

  public void setMailinfoDAO(MailinfoDAO mailinfoDAO) {
    this.mailinfoDAO = mailinfoDAO;
  }
  // ��������ʹ�õ��ʼ�������Ϣ
  public MailinfoAF queryMailinfo()throws BusinessException {
    MailinfoAF mailinfoAF = new MailinfoAF();
    // ��������ʹ�õ�������Ϣ��
    List list = mailinfoDAO.queryMailinfo();
    try{
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        MailinfoDTO dto = (MailinfoDTO) list.get(i);
        // ���ݿ��д���ʼ����ʻ��ǲ�ֵģ�Ŀ���Ƿ����ʼ�ʱ�����׻�ȡ��
        String addresserMail = dto.getAddresserMail();
        mailinfoAF.setAddresserMail(addresserMail);
        String addresserPassword = dto.getAddresserPassword();// �������ʻ�����
        mailinfoAF.setAddresserPassword(addresserPassword);
        String addresseeA = dto.getAddresseeA();// �ռ�������A
        mailinfoAF.setAddresseeA(addresseeA);
        String addresseeB = dto.getAddresseeB();// �ռ�������B
        mailinfoAF.setAddresseeB(addresseeB);
        String subjectName = dto.getSubjectName();// ��������
        mailinfoAF.setSubjectName(subjectName);
        String mailId=dto.getMailId();
        mailinfoAF.setMailId(mailId);
      }
    }
    }catch(Exception e){
      e.printStackTrace();
    }
    return mailinfoAF;
  }
  //����µ��ʼ�������Ϣ��ͬʱ�������õ��ʼ���Ϣ
  public String addMailinfo(String mailId, String addresserMail, String addresserPassword,
      String addresseeA, String addresseeB, String subjectName,
      SecurityInfo securityInfo)throws BusinessException{
    String info="";
    try{
    //��ȡ"@"���ڵ�λ�ã����ڽ�ȡ��
     int findint=addresserMail.indexOf("@"); 
    //��ȡ�����˵��˺�
    String addresser=addresserMail.substring(0, findint);
    //��ȡ��������
    String tempmailbox=addresserMail.substring(findint+1);
    //ƥ���ϵͳ����ʶ����������
    String mailBoxType="smtp."+tempmailbox;
    //�����˵��û��������Ƿ���ȷ��
    try{
    this.checksendtomail(mailBoxType,addresserMail,addresser,addresserPassword,addresseeA,addresseeB);
    }catch(Exception e){
      throw new BusinessException("��������������벻��ȷ���ʵ,�ռ������Ƿ���ȷ��");
    }
    //��װ���ݣ��������ݿ��С�
    MailinfoDTO dto=new MailinfoDTO();
    dto.setAddresser(addresser);
    dto.setAddresserMail(addresserMail);
    dto.setAddresserPassword(addresserPassword);
    dto.setMailBoxType(mailBoxType);
    dto.setAddresseeA(addresseeA);
    dto.setAddresseeB(addresseeB);
    dto.setSubjectName(subjectName);
    dto.setMailId(mailId);
    //�����µ��ʼ���Ϣ
    mailinfoDAO.insertMailinfo(dto, securityInfo);
     info="pass";
    }catch(BusinessException bux){
      throw bux;
    }
    catch(Exception e){
      throw new BusinessException("��Ϣ¼��ʧ�ܣ���ȷ���Ƿ���ȷ��װ�ʼ��쳣����ϵͳ��");
    }
    return info;
  }
  public void checksendtomail(String mailBoxType,String addresserMail,String addresser,String addresserPassword,String addresseeA,String addresseeB) throws Exception {
    try {
      //����spring�ʼ���
      JavaMailSenderImpl mailSender1 = new JavaMailSenderImpl();
      //��д�����ʼ������ͣ�����smtp�ʼ�Э��
      mailSender1.setHost(mailBoxType);
//      ��д�����ʼ����ʻ�
      mailSender1.setUsername(addresser);
//      ��д�����ʼ�������
      mailSender1.setPassword(addresserPassword);
//      ������Դ�ļ�����д����
      Properties mailproperties = new Properties();
      mailproperties.setProperty("mail.smtp.auth", "true");
      mailproperties.setProperty("mail.smtp.timeout", "25000");
      mailSender1.setJavaMailProperties(mailproperties);
//      ���͵����ݲ���
      SimpleMailMessage mail = new SimpleMailMessage();
      int addresseemail=1;
      if(addresseeB!=null&&!addresseeB.equals(""))
        addresseemail=addresseemail+1;
      //�ռ�����A
      String[] addressee = new String[addresseemail];
      addressee[0] = addresseeA;
      //�ռ�����B
      if(addresseeB!=null&&!addresseeB.equals(""))
      addressee[1] = addresseeB; 
      mail.setTo(addressee);
      mail.setFrom(addresserMail);
      mail.setSubject("��ӭʹ���ʼ��쳣����ϵͳ��");
      mail.setText("���ǻ��ڵ�һʱ����쳣��Ϣ����������");
      mailSender1.send(mail);
    } catch (Exception e) {
      throw e;
    }
  }
}
