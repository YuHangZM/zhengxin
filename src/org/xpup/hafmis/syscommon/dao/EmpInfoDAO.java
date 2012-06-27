package org.xpup.hafmis.syscommon.dao;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.sysfinance.bookmng.datainitialize.dto.DatainitializeDTO;

/**
 * ְ����Ϣ
 * 
 *@author ���
 *2007-6-19
 */

public class EmpInfoDAO extends HibernateDaoSupport{
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public EmpInfo queryById(Serializable id) {  
    Validate.notNull(id);
    return  (EmpInfo) getHibernateTemplate().get(EmpInfo.class,id);    
  }
  
  
  /**
   * ���20090310
   */
  public EmpInfo getEmpInfo_yg(final Integer empid) throws BusinessException{
    EmpInfo info = (EmpInfo)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session) throws HibernateException, SQLException {
            String hql = "select e from EmpInfo e,Emp emp where emp.empInfo.id=e.id and emp.empId = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, empid);
            return query.uniqueResult();
          }
        }
    );
    return info;
  }
  /**
   * ���ĺ�...�������֤�ĺ�����������ж�һ�����Ƿ��������ݿ����м�¼
   */
  public EmpInfo getEmpInfo(final String cardnum,final String name) throws BusinessException{
      EmpInfo info = (EmpInfo)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              EmpInfo e = (EmpInfo)session.createQuery("from EmpInfo e where e.cardNum=? and e.name=?")
                          .setString(0, cardnum).setString(1, name).setMaxResults(1).uniqueResult();
              return e;
            }
          }
      );
      return info;
  }
  /**
   * �����¼
   * @param empInfo
   * @return
   */
  public Serializable insert(EmpInfo empInfo){
    Serializable id = null;
    try{    
    Validate.notNull(empInfo);
    id = getHibernateTemplate().save(empInfo);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
/*
 * ɾ��BA002��Ϣ
 */
  public void deleteEmpInfoByIdSL(Serializable id) {
    try {

      Validate.notNull(id);
      EmpInfo empInfo = queryById(id);
      getHibernateTemplate().delete(empInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void deleteEmpInfoByIdSL_org(final String id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "delete from EmpInfo t where t.id in (select emp.empInfo.id from Emp emp where emp.empInfo.id in" +
              "(select emp.empInfo.id from Emp emp where emp.org.id = ?) group by emp.empInfo.id having count(emp.empInfo.id) = '1') ";
          Query query = session.createQuery(sql);
          query.setString(0, id);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  /**
   * �����ְ��ʱ���뱸ѡ�ֶε����֤��
   * @param cardNum15
   * @throws BusinessException
   * @throws HibernateException
   * @author ���Ʒ�
   */
  public String queryCardNumTo18(final String cardNum15) throws BusinessException,
      HibernateException {
    Validate.notNull(cardNum15);
    Object obj = null;
    try {
      obj = getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {           
          String hql = " select to_pid18(?) from dual";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, cardNum15);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj.toString();
  }
        
}
