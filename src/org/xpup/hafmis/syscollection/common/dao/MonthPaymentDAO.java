package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;

/**
 * �������
 * 
 *@author ���
 *2007-6-20
 */
public class MonthPaymentDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public MonthPayment queryById(Serializable id) {  
    Validate.notNull(id);
    return  (MonthPayment) getHibernateTemplate().get(MonthPayment.class,id);    
  }
  /**
   * �����¼
   * @param monthPayment
   * @return
   */
  public Serializable insert(MonthPayment monthPayment){
    Serializable id = null;
    try{    
    Validate.notNull(monthPayment);
    id = getHibernateTemplate().save(monthPayment);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * ���
   * ���ݵ�λID��ѯ�˵�λ�½ɴ�״̬Ϊ2�����Ļ��ID
   * @param orgId
   * @return
   */
  public Integer getPaymentIdLJ(final Serializable orgId) {
    Validate.notNull(orgId);
    Integer paymentId = null;
    paymentId = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select max(monthPayment.id) from MonthPayment monthPayment" +
            " where monthPayment.payStatus <>5 and monthPayment.org.id = ? ";
        Vector parameters = new Vector();
        parameters.add(orgId);
       
        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));
        
        return query.uniqueResult();
      }
    });
    if(paymentId==null){
      paymentId = new Integer(0);
    }
    return paymentId;
  }
  
  /**
   * ���ݽ���ź�ҵ�����Ͳ�ѯ�ɴ���
   * @param noteNum
   * @param type
   * @return
   */
  public BigDecimal getPayMoney_jj(final String noteNum,final String type) {
    BigDecimal payMoney = null;
    payMoney = (BigDecimal)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select aa301.pay_money from AA301 aa301" +
            " where aa301.note_num = ? and aa301.pay_type = ? ";       
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,noteNum);
        query.setParameter(1,type);        
        return query.uniqueResult();
      }
    });
    if(payMoney==null){
      payMoney = new BigDecimal(0.00);
    }
    return payMoney;
  }
  /**
   * ���
   * ���ݻ��ID��ѯ�ɴ�״̬
   * @param paymentId
   * @return
   */
  public Integer getPayStatusLJ(final Serializable paymentId) {
    Validate.notNull(paymentId);
    Integer payStatus = null;
    payStatus = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select monthPayment.payStatus from MonthPayment monthPayment" +
            " where monthPayment.id = ? ";
        Vector parameters = new Vector();
        parameters.add(new Integer(paymentId.toString()));
       
        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));
        
        return query.uniqueResult();
      }
    });
    if(payStatus==null){
      payStatus = new Integer(0);
    }
    return payStatus;
  }
  /**
   * ���
   * ���ݻ��ID��ѯ��λID
   * @param paymentId
   * @return
   */
  public Integer getOrgIdLJ(final Serializable paymentId) {
    Validate.notNull(paymentId);
    Integer orgId = null;
    orgId = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select monthPayment.org.id from MonthPayment monthPayment" +
            " where monthPayment.id = ? ";
        Vector parameters = new Vector();
        parameters.add(paymentId);
       
        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));
        
        return query.uniqueResult();
      }
    });
    if(orgId==null){
      orgId = new Integer(0);
    }
    return orgId;
  }
  /**
   * 
   * ���ݻ��ID��ѯ��ѡC(�ж��Ƿ�����Զ�����)
   * @param paymentId
   * @return
   * @author ���Ʒ�
   */
  public Object getReservea_a(final Serializable paymentId) {
    Validate.notNull(paymentId);
    Object obj = null;
    obj = getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select monthPayment.reserveaA from MonthPayment monthPayment" +
            " where monthPayment.id = ? ";
        Vector parameters = new Vector();
        parameters.add(paymentId);
       
        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));
        
        return query.uniqueResult();
      }
    });
    return obj;
  }
  public List getMonthPaymentByOrgid(final Serializable orgid) {
    Validate.notNull(orgid);
    List list = null;
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " from MonthPayment monthPayment" +
            " where monthPayment.org.id = ? ";
        Vector parameters = new Vector();
        parameters.add(new Integer(orgid.toString()));
       
        Query query = session.createQuery(hql);
        query.setParameter(0, parameters.get(0));
        
        return query.list();
      }
    });
    return list;
  }
  /**
   * ɾ��������¼
   * @param monthPayment
   */
  public void delete(MonthPayment monthPayment){
    Validate.notNull(monthPayment);
    getHibernateTemplate().delete(monthPayment);
  }
  /**
   * ɾ��list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }
  /**
   * ��λ��������
   */
  public String getOrgPayMonth(final Serializable orgId,final String status) {
    Validate.notNull(orgId);
    String orgPayMonth = "";
    orgPayMonth = (String)getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql="";
        if(status.equals("org")){
        hql = "select org_pay_month from aa001 where id=? " ;
        }else{
          hql = "select emp_pay_month from aa001 where id=? " ;
        }
         
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, orgId);
        Object object=null;
        List list=query.list();
        if(list.size()>0){
          object=(Object)list.get(0);
        }
        return object.toString();
      }
    });

    return orgPayMonth;
  }
  
  
  /**
   * �û������ɷ�ʽ�����Ļ������С�//�鼯
   * @author ���
   * @return
   * @throws Exception
   */
  public String getNamePara() throws Exception {
    String param_value = "";
    param_value = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select collPara.param_type from CollPara collPara where collPara.param_value='name' ";

            Query query = session.createQuery(hql);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return param_value;
  }
}