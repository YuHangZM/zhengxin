package org.xpup.hafmis.sendmail.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import oracle.sql.DATE;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sendmail.sendmial.dto.MailinfoDTO;

public class MailinfoDAO extends HibernateDaoSupport {
  /**
   * �ʼ�ϵͳ
   * 
   * @author shiy ��������ʹ�õ���Ϣ
   * @return
   */
  public List queryMailinfo() {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select  t.addressermail,  t.addersserpassword,  t.mailboxtype,  t.addresseea,  t.addresseeb, t.subjectname,t.id from ca200 t where t.is_start=1 ";
          Query query = session.createSQLQuery(hql);

          Iterator iterate = query.list().iterator();

          List tempList = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            MailinfoDTO dto = new MailinfoDTO();
            dto.setAddresserMail(obj[0].toString());
            dto.setAddresserPassword(obj[1].toString());
            dto.setMailBoxType(obj[2].toString());
            dto.setAddresseeA(obj[3].toString());
            if (obj[4] != null)
              dto.setAddresseeB(obj[4].toString());
            dto.setSubjectName(obj[5].toString());
            dto.setMailId(obj[6].toString());
            tempList.add(dto);
          }
          return tempList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * �ʼ�ϵͳ
   * 
   * @author shiy ������ʹ�õĸ��³�δʹ�ã������µ���Ϣ����ֻ֤��һ����¼���á�
   * @return
   * @throws BusinessException
   */
  public void updateMailinfo(int mailId) throws BusinessException {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      String sql = "update ca200 set is_start=0 where id=? ";
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setInt(1, mailId);
      pstm.executeUpdate();
    } catch (Exception e) {
      throw new BusinessException("��ӹ����г��ֳ�ͻ��");
    }
  }

  /**
   * �ʼ�ϵͳ
   * 
   * @author shiy �������µ���Ϣ
   * @return
   * @throws SQLException 
   * @throws BusinessException 
   */
  public void insertMailinfo(MailinfoDTO dto, SecurityInfo securityInfo) throws SQLException, BusinessException {
    Connection conn = getHibernateTemplate().getSessionFactory()
    .openSession().connection();
    try {
      
      conn.setAutoCommit(false);
      //��������ʹ�õ��ʼ���Ϣ
      String mailId=dto.getMailId();
      if(mailId!=null&!mailId.equals("")){
      int changemailId=new Integer(mailId).intValue();
      String sql = "update ca200 set is_start=0 where id=? ";
      PreparedStatement pstms = conn.prepareStatement(sql);
      pstms.setInt(1, changemailId);
      pstms.executeUpdate();
      }
      //�����µļ�¼
      String sql = " insert into ca200 tca200(tca200.id,tca200.addresser,tca200.addersserpassword," +
          "tca200.mailboxtype,tca200.addresseea,tca200.addresseeb,tca200.subjectname," +
          "tca200.operator,tca200.insert_ip,tca200.insert_date,tca200.addressermail,tca200.is_start) " +
                   " values(seq_ca200.nextval,?,?,?,?,?,?,?,?,?,?,?) ";
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, dto.getAddresser());
      pstm.setString(2, dto.getAddresserPassword());
      pstm.setString(3, dto.getMailBoxType());
      pstm.setString(4, dto.getAddresseeA());
      pstm.setString(5, dto.getAddresseeB());
      pstm.setString(6, dto.getSubjectName());
      pstm.setString(7, securityInfo.getUserName());
      pstm.setString(8, securityInfo.getUserIp());
      pstm.setTimestamp(9,Timestamp.valueOf(BusiTools.dateToString(new Date(),
      "yyyy-MM-dd HH:MM:ss")));
      pstm.setString(10, dto.getAddresserMail());
      pstm.setString(11, "1");
      pstm.executeUpdate();
      conn.commit();
    } catch (Exception e) {
      conn.rollback();
      throw new BusinessException("������Ϣʧ�ܣ�û�б���ɹ���������������û�������ż��ѷ�����");
    }
  }
}
