package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRateTail;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public class ChgOrgRateTailDAO extends HibernateDaoSupport {

  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public ChgOrgRateTail queryById(Integer id) {
    Validate.notNull(id);
    return (ChgOrgRateTail) getHibernateTemplate()
        .get(ChgOrgRateTail.class, id);
  }

  /**
   * �����¼
   * 
   * @param chgPaymentTail
   * @return
   */
  public Serializable insert(ChgOrgRateTail chgOrgRateTail) {
    Serializable id = null;
    try {
      Validate.notNull(chgOrgRateTail);
      id = getHibernateTemplate().save(chgOrgRateTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * ��� ���ݵ�λ��źͽɴ�ID���ɴ�״̬��ѯ ְ�����ӻ���ٽ��(����ʱΪ���ӣ�����ʱΪ����)
   * 
   * @param orgid
   * @param payheadId
   * @param payStatus
   * @return
   */
  public BigDecimal queryEmpPayMoney(final Serializable orgid,
      final Serializable payheadId, final String[] payStatus) {
    BigDecimal paymoney = null;
    try {
      paymoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(chgPaymentTail.empPay-chgPaymentTail.oldEmpPay) "
                  + " from ChgPaymentTail chgPaymentTail ";
              Vector parameters = new Vector();
              String criterion = "";


             
        if (orgid != null&&!orgid.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }
        
        if (payheadId != null&&!payheadId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id = ?  and ";
          parameters.add(new Integer(payheadId.toString()));
        }else{
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id is null and ";
        }
        if(payStatus != null && payStatus.length>0){
          criterion +="( ";
          for(int i=0;i<payStatus.length;i++){
            criterion += " chgPaymentTail.payStatus = ? or ";
            parameters.add(new Integer(payStatus[i]));
          }
          criterion =  criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus = 2 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }
    if(paymoney == null){
      paymoney = new BigDecimal(0.00); 
          }


    return paymoney;
  }

  /**
   * ��� ���ݵ�λ��źͽɴ�ID���ɴ�״̬��ѯ ��λ���ӻ���ٽ��(����ʱΪ���ӣ�����ʱΪ����)
   * 
   * @param orgid
   * @param payheadId
   * @param payStatus
   * @return
   */
  public BigDecimal queryOrgPayMoney(final Serializable orgid,
      final Serializable payheadId, final String[] payStatus) {
    BigDecimal paymoney = null;
    try {
      paymoney = (BigDecimal) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select sum(chgPaymentTail.orgPay-chgPaymentTail.oldOrgPay) "
                  + " from ChgPaymentTail chgPaymentTail ";
              Vector parameters = new Vector();
              String criterion = "";
        if (orgid != null&&!orgid.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ?  and ";
          parameters.add(new Integer(orgid.toString()));
        }
        
        if (payheadId != null&&!payheadId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id = ?  and ";
          parameters.add(new Integer(payheadId.toString()));
        }else{
          criterion += " chgPaymentTail.chgPaymentHead.paymentHead.id is null and ";
        }
        if(payStatus != null && payStatus.length>0){
          criterion +="( ";
          for(int i=0;i<payStatus.length;i++){
            criterion += " chgPaymentTail.payStatus = ? or ";
            parameters.add(new Integer(payStatus[i]));
          }
          criterion =  criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus = 2 and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        return query.uniqueResult();
      }
    });
    }catch(Exception e){
        e.printStackTrace();
      }

    if (paymoney == null) {
      paymoney = new BigDecimal(0.00);
    }

    return paymoney;
  }

  /**
   * ��������ɾ��������¼
   * 
   * @return ����� 6.30
   */
  public void delete(ChgOrgRateTail chgOrgRateTail) {
    Validate.notNull(chgOrgRateTail);
    getHibernateTemplate().delete(chgOrgRateTail);
  }

  
  /**
   * ɾ��list
   */
  public void deleteList(List list){
    Validate.notNull(list);
    getHibernateTemplate().deleteAll(list);
  }
  
  public void deleteChgTail(final Integer chgheadId){
    try{
      getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
               String sql="delete ChgOrgRateTail chgOrgRateTail where chgOrgRateTail.chgOrgRate.id=?";          
               session.createQuery(sql).setInteger(0, chgheadId.intValue()).executeUpdate();
                return null;
            }
          }  
      );
        }catch(Exception e){
          e.printStackTrace();
        }
  }
  
  public List queryByCriterionsWuht(final String id, final String chgMonth,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
  
     
        return query.list();
      }
    }

    );

    return orglist;
  }

  /**
   * ���������������ѯְ����Ϣ(List)
   * type=A(ChgPaymentSalaryBase)
   * @param String id,
   * @param String chgMonth
   * @return �����
   */
  public List queryByCriterions(final String id, final String chgMonth,
      final String orderBy, final String order, final int start,
      final int pageSize,final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize); 
         List queryList=query.list();
        if(queryList==null||queryList.size()==0){           
          query.setFirstResult(pageSize*(page-2));
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          queryList=query.list();
        }
        return query.list();
      }
    }
    );

    return orglist;
  }

  public int queryCountByCriterions(final String id,
      final String changementMonth) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String criterion = "";

          Vector parameters = new Vector();

          String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
          if (changementMonth != null) {
            criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
            parameters.add(changementMonth);
          }
          if (id != null) {
            criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
            parameters.add(new Integer(id));
          }

          if (criterion.length() != 0)
            criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * ���������������ѯְ����Ϣ
   * type=A(ChgPaymentSalaryBase)
   * @param String empid,
   * @param String orgid
   * @return �����
   */
  public ChgPaymentTail queryChgPaymentSalaryBaseWUHT(
      final String empid, final String orgid) {
    ChgPaymentTail chgPaymentTail = (ChgPaymentTail) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (empid != null && !empid.equals("")) {
              criterion += "chgPaymentTail.empId = ?  and ";
              parameters.add(new Integer(empid));
            }

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
              parameters.add(new Integer(orgid));
            }

            if (criterion.length() != 0)
              criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    return chgPaymentTail;
  }
  /**
   * ���������������ѯְ����Ϣ
   * type=B(ChgPaymentPayment)
   * @param String empid,
   * @param String orgid
   * @return �����
   */
  public ChgPaymentTail queryChgPaymentPaymentWUHT(
      final String empid, final String orgid) {
    ChgPaymentTail chgPaymentTail = (ChgPaymentTail) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
            Vector parameters = new Vector();
            String criterion = "";

            if (empid != null && !empid.equals("")) {
              criterion += "chgPaymentTail.empId = ?  and ";
              parameters.add(new Integer(empid));
            }

            if (orgid != null && !orgid.equals("")) {
              criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
              parameters.add(new Integer(orgid));
            }

            if (criterion.length() != 0)
              criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.uniqueResult();
          }
        });
    return chgPaymentTail;
  }
  
  /**
   * ���������������ѯְ����Ϣ(List)
   * type=B(ChgPaymentPayment)
   * @param String id,
   * @param String chgMonth
   * @return �����
   */
  public List queryChgPaymentPaymentOtherWuht(final String id,
      final String chgMonth, final String orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.id ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
     
        return query.list();
      }
    }

    );

    return orglist;
  }
  public List queryChgPaymentPaymentWuht(final String id,
      final String chgMonth, final String orderBy, final String order,
      final int start, final int pageSize,final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id.trim() != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id.trim()));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.id ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize); 
         List   queryList=query.list();
        if(queryList==null||queryList.size()==0){           
          query.setFirstResult(pageSize*(page-2));
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          queryList=query.list();
        }
        return query.list();
      }
    }

    );

    return orglist;
  }

  public int queryChgPaymentPaymentWuht(final String id,
      final String changementMonth) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String criterion = "";

          Vector parameters = new Vector();

          String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";

          if (changementMonth != null) {
            criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
            parameters.add(changementMonth);
          }
          if (id.trim() != null) {
            criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
            parameters.add(new Integer(id.trim()));
          }

          if (criterion.length() != 0)
            criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }
  
  
  /**
   * ���������������ѯְ����Ϣ(List)
   * type=A(ChgPaymentSalaryBase)
   * @param String id,
   * @return �����
   */
  public List queryChgPaymentTailByCriterionsWuht(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
  
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
   
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=2 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
     
        return query.list();
      }
    }

    );

    return orglist;
  }

  /**
   * ���������������ѯְ����Ϣ(List)
   * type=B(ChgPaymentPayment)
   * @param String id,
   * @param String chgMonth
   * @return �����
   */
  public List queryChgPaymentPaymentbyCriterionsWuht(final String id, final String orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
     
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=2 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize); 
        return query.list();
      }
    }

    );

    return orglist;
  }

  public List queryChgPaymentPaymentbyCriterionsOtherWuht(final String id, final String orderBy, final String order,
      final int start, final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
     
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=2 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
  
        return query.list();
      }
    }

    );

    return orglist;
  }

  /**
   * ���������������ѯ��Ϣ(List)(ͳ�Ʋ�ѯ)
   * type=A(ChgPaymentSalaryBase)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return �����
   */
  public List queryChgPaymentTailByCriterionsWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail.chgPaymentHead.org.id,"
                      +"chgPaymentTail.chgPaymentHead.org.orgInfo.name,"
                      +"emp.empId,"
                      +"emp.empInfo.name,"
                      +"chgPaymentTail.oldSalaryBase,"
                      +"chgPaymentTail.salaryBase,"
                      +"chgPaymentTail.chgPaymentHead.chgMonth,"
                      +"chgPaymentTail.chgPaymentHead.bizDate,"
                      +"chgPaymentTail.orgPay,"
                      +"chgPaymentTail.empPay,"
                      +"chgPaymentTail.oldOrgPay,"
                      +"chgPaymentTail.oldEmpPay"
                      +" from Emp emp,ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // ���Ʒ��޸ģ�����һ���µĲ�ѯ�������ڵ�����Ĳ�ѯ������AA202��id����ѯ
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += " chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and emp.empId=chgPaymentTail.empId and chgPaymentTail.chgPaymentHead.org.id=emp.org.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize); 
        Iterator it = query.list().iterator();
        List chgPaymentTailList = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj=(Object[])it.next();
          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
          ChgPaymentHead chgPaymentHead = new ChgPaymentHead();
          Org org = new Org();
          Emp emp = new Emp();
          
          org.setId(new Integer(obj[0].toString()));
          org.getOrgInfo().setName(obj[1].toString());
          chgPaymentTail.setEmpId(new Integer(obj[2].toString()));
          emp.getEmpInfo().setName(obj[3].toString());
          chgPaymentTail.setOldSalaryBase(new BigDecimal(obj[4].toString()));
          chgPaymentTail.setSalaryBase(new BigDecimal(obj[5].toString()));
          chgPaymentHead.setChgMonth(obj[6].toString());
          chgPaymentHead.setBizDate(obj[7].toString());
          
          BigDecimal oldOrgEmpPay = new BigDecimal(0.0);
          BigDecimal orgEmpPay = new BigDecimal(0.0);
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[10].toString()));
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[11].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[8].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[9].toString()));
          
          chgPaymentTail.setOldOrgEmpPaySum(oldOrgEmpPay);
          chgPaymentTail.setOrgEmpPaySum(orgEmpPay);
          
          chgPaymentHead.setOrg(org);
          chgPaymentTail.setChgPaymentHead(chgPaymentHead);
          chgPaymentTail.setEmp(emp);
          chgPaymentTailList.add(chgPaymentTail);
        }
       
        return chgPaymentTailList;
      }
    }
    );

    return orglist;
  }

  /**
   * ���������������ѯ��Ϣ(List)(ͳ�Ʋ�ѯ)(ȫ��)
   * type=A(ChgPaymentSalaryBase)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return �����
   */
  
  public List queryChgPaymentTailByCriterionsOtherWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // ���Ʒ��޸ģ�����һ���µĲ�ѯ�������ڵ�����Ĳ�ѯ������AA202��id����ѯ
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += " chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;
        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
//        Iterator it = query.list().iterator();
//        List chgPaymentTailList = new ArrayList();
//        Object obj[] = null;
//        while (it.hasNext()) {
//          obj=(Object[])it.next();
//          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
//          chgPaymentTail = (ChgPaymentTail)obj[0];
//          Emp emp = new Emp();
//          emp.getEmpInfo().setName(obj[1].toString());
//          chgPaymentTail.setEmp(emp);
//          chgPaymentTailList.add(chgPaymentTail);
//        }

       
        return query.list();
      }
    }

    );

    return orglist;
  }

  /**
   * ���������������ѯ��Ϣ(List)(ͳ�Ʋ�ѯ)(  ����ְ�������б��еĲ�ְͬ������)
   * type=A(ChgPaymentSalaryBase)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return �����
   */

  public List queryChgPaymentTailByCriterionsCountWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select distinct  chgPaymentTail.empId from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // ���Ʒ��޸ģ�����һ���µĲ�ѯ�������ڵ�����Ĳ�ѯ������AA202��id����ѯ
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += " chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
       


        hql = hql + criterion ;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    


       
        return query.list();
      }
    }

    );

    return orglist;
  }

  /**
   * ���������������ѯ��Ϣ(List)(ͳ�Ʋ�ѯ)
   * type=A(ChgPaymentPayment)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return �����
   */
  public List queryChgPaymentChgpayChgPaymentTailByCriterionsWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
 
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail.chgPaymentHead.org.id,"
                    +"chgPaymentTail.chgPaymentHead.org.orgInfo.name,"
                    +"emp.empId,"
                    +"emp.empInfo.name,"
                    +"chgPaymentTail.oldSalaryBase,"
                    +"chgPaymentTail.salaryBase,"
                    +"chgPaymentTail.chgPaymentHead.chgMonth,"
                    +"chgPaymentTail.chgPaymentHead.bizDate,"
                    +"chgPaymentTail.orgPay,"
                    +"chgPaymentTail.empPay,"
                    +"chgPaymentTail.oldOrgPay,"
                    +"chgPaymentTail.oldEmpPay"
                    +" from Emp emp,ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // ���Ʒ��޸ģ�����һ���µĲ�ѯ�������ڵ�����Ĳ�ѯ������AA202��id����ѯ
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += "  chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and emp.empId=chgPaymentTail.empId and chgPaymentTail.chgPaymentHead.org.id=emp.org.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize); 
        Iterator it = query.list().iterator();
        List chgPaymentTailList = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj=(Object[])it.next();
          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
          ChgPaymentHead chgPaymentHead = new ChgPaymentHead();
          Org org = new Org();
          Emp emp = new Emp();
          
          org.setId(new Integer(obj[0].toString()));
          org.getOrgInfo().setName(obj[1].toString());
          chgPaymentTail.setEmpId(new Integer(obj[2].toString()));
          emp.getEmpInfo().setName(obj[3].toString());
          chgPaymentTail.setOldSalaryBase(new BigDecimal(obj[4].toString()));
          chgPaymentTail.setSalaryBase(new BigDecimal(obj[5].toString()));
          chgPaymentHead.setChgMonth(obj[6].toString());
          chgPaymentHead.setBizDate(obj[7].toString());
          
          BigDecimal oldOrgEmpPay = new BigDecimal(0.0);
          BigDecimal orgEmpPay = new BigDecimal(0.0);
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[10].toString()));
          oldOrgEmpPay=oldOrgEmpPay.add(new BigDecimal(obj[11].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[8].toString()));
          orgEmpPay=orgEmpPay.add(new BigDecimal(obj[9].toString()));
          
          chgPaymentTail.setOldOrgEmpPaySum(oldOrgEmpPay);
          chgPaymentTail.setOrgEmpPaySum(orgEmpPay);
          
          chgPaymentHead.setOrg(org);
          chgPaymentTail.setChgPaymentHead(chgPaymentHead);
          chgPaymentTail.setEmp(emp);
          chgPaymentTailList.add(chgPaymentTail);
        }
       
        return chgPaymentTailList;
      }
    }
//11111111111111111111111
    );

    return orglist;
  }

  /**
   * ���������������ѯ��Ϣ(List)(ͳ�Ʋ�ѯ)(ȫ��)
   * type=A(chgPaymentPayment)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return �����
   */
  
  public List queryChgPaymentChgpayChgPaymentTailByCriterionsOtherWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // ���Ʒ��޸ģ�����һ���µĲ�ѯ�������ڵ�����Ĳ�ѯ������AA202��id����ѯ
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += " chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += "  chgPaymentTail.empId = ? and ";
          parameters.add(new Integer(empId));
        }
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
        String ob = orderBy;
        if (ob == null)
          ob = " chgPaymentTail.chgPaymentHead.org.id DESC, chgPaymentTail.empId DESC, chgPaymentTail.id  ";

        String od = order;
        if (od == null)
          od = "ASC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
//        Iterator it = query.list().iterator();
//        List chgPaymentTailList = new ArrayList();
//        Object obj[] = null;
//        while (it.hasNext()) {
//          obj=(Object[])it.next();
//          ChgPaymentTail chgPaymentTail = new ChgPaymentTail();
//          chgPaymentTail = (ChgPaymentTail)obj[0];
//          Emp emp = new Emp();
//          emp.getEmpInfo().setName(obj[1].toString());
//          chgPaymentTail.setEmp(emp);
//          chgPaymentTailList.add(chgPaymentTail);
//        }

       
        return query.list();
      }
    }

    );

    return orglist;
  }
  
  
  /**
   * ���������������ѯ��Ϣ(List)(ͳ�Ʋ�ѯ)(  ����ְ�������б��еĲ�ְͬ������)
   * type=A(chgPaymentPayment)
   * @param orgId, 
   * orgName, 
   * empId,
   * empName, 
   * startChgMonth,
   * endChgMonth, 
   * startBizDate,
   * endBizDate,
   * @return �����
   */

  public List queryChgPaymentChgpayChgPaymentTailByCriterionsCountWuht(final String orgId, final String orgName,
      final String empId, final String empName, 
      final String startChgMonth, final String endChgMonth,
      final String startBizDate, final String endBizDate,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo securityInfo,final String chgPaymentHeadId) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);

    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select distinct  chgPaymentTail.empId from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment  ";
        String criterion = "";
        Vector parameters = new Vector();
        
        // ���Ʒ��޸ģ�����һ���µĲ�ѯ�������ڵ�����Ĳ�ѯ������AA202��id����ѯ
        if (chgPaymentHeadId!=null &&!chgPaymentHeadId.equals("") ) {
          criterion += "  chgPaymentTail.chgPaymentHead.id = ? and ";
          parameters.add(new Integer(chgPaymentHeadId));
        }
        
        if (startChgMonth != null && !startChgMonth.equals("")&& (endChgMonth == null || endChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(startChgMonth);
        }
        if (endChgMonth != null && !endChgMonth.equals("")&&( startChgMonth == null || startChgMonth.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ?  and ";
          parameters.add(endChgMonth);
        }       
        if (startChgMonth != null&&!startChgMonth.equals("") && endChgMonth != null&&!endChgMonth.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.chgMonth  between ?  and  ? ) and ";
         
          parameters.add(startChgMonth);
          parameters.add(endChgMonth);
        }
        
        if (startBizDate != null && !startBizDate.equals("")&& (endBizDate == null || endBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(startBizDate);
        }
        if (endBizDate != null && !endBizDate.equals("")&& (startBizDate == null || startBizDate.equals(""))) {
          criterion += "chgPaymentTail.chgPaymentHead.bizDate = ?  and ";
          parameters.add(endBizDate);
        }
        
        if (startBizDate != null&&!startBizDate.equals("") && endBizDate != null&&!endBizDate.equals("")) {// ��ʼ����
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ? ) and ";
         
          parameters.add(startBizDate);
          parameters.add(endBizDate);
        }
        
        
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "chgPaymentTail.chgPaymentHead.org.orgInfo.name like ?  and ";
          parameters.add("%" + orgName + "%");
        }

        if (orgId != null && !orgId.equals("")) {
          criterion += "  chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(orgId));
        }

 
        
        
        if (empName != null && !empName.equals("")) {
          criterion += "chgPaymentTail.empName like ?  and ";
          parameters.add("%" + empName + "%");
        }

        if (empId != null && !empId.equals("")) {
          criterion += "  To_Char(chgPaymentTail.empId) like ? and ";
          parameters.add("%" + empId + "%");
        }
        
        
        
        
        if (criterion.length() != 0)
          criterion = "where  chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and  chgPaymentTail.chgPaymentHead.org.id "+securityInfo.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        
       


        hql = hql + criterion ;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    


       
        return query.list();
      }
    }

    );

    return orglist;
  }
  
  /**
   * ����AA202id ����β��
   * @param String id AA202id
   * @return AA203����
   * ���Ʒ�
   */
  public List queryChgPaymentTailByChgPaymentPayment(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = null;
  try {
    orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail ";
        String criterion = "";
        Vector parameters = new Vector();
   
        if (id != null) {
          criterion += "where chgPaymentTail.chgPaymentHead.id = ? ";
          parameters.add(new Integer(id));
        }
        

        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
          query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize); 
        return query.list();
      }
    }

    );
  } catch (Exception e) {
    e.printStackTrace();
  }
    return orglist;
  }
  
  public int countChgPaymentTailByChgPaymentPayment(final String id) {

    List orglist = null;
  try {
    orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail ";
        String criterion = "";
        Vector parameters = new Vector();
   
        if (id != null) {
          criterion += "where chgPaymentTail.chgPaymentHead.id = ? ";
          parameters.add(new Integer(id));
        }

        hql = hql + criterion ;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    }

    );
  } catch (Exception e) {
    e.printStackTrace();
  }
    return orglist.size();
  }
  
  /**
   * ����� sum �ɶ����--�ɶ�仯
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCount(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select sum((chgPaymentTail.orgPay+chgPaymentTail.empPay)-(chgPaymentTail.oldOrgPay+chgPaymentTail.oldEmpPay)) from ChgPaymentTail chgPaymentTail,ChgPaymentPayment chgPaymentPayment ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where chgPaymentTail.chgPaymentHead.chgStatus = 2 and chgPaymentTail.chgPaymentHead.id=chgPaymentPayment.id and (chgPaymentTail.payStatus=1 or chgPaymentTail.payStatus=3 or chgPaymentTail.payStatus=4)  and "
            + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          BigDecimal countTemp=new BigDecimal(0.00);
          Iterator it=query.iterate();          
          if(it.hasNext()){
            countTemp=(BigDecimal)it.next();
          }
          if(countTemp==null){
            return new Integer(0);
          }else{
          return new Integer(countTemp.intValue());
          }
      }
    });
      count = countInteger.intValue();
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  
  /**
   * ����� sum �ɶ����--�ɶ�仯
   * @param office
   * @param bank
   * @param orgCharacter
   * @param dept
   * @param ragion
   * @param startDate
   * @param endDate
   * @return
   */
  public int queryCount_(final String office,final String bank,final String orgCharacter,
      final String dept,final String ragion,final String startDate,final String endDate){
    int count = 0;
    try{
      Integer countInteger = (Integer)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
     
        String hql = "select sum((chgPaymentTail.orgPay+chgPaymentTail.empPay)-(chgPaymentTail.oldOrgPay+chgPaymentTail.oldEmpPay)) from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase ";
        Vector parameters = new Vector();
        String criterion = "";
        if(office != null && !"".equals(office)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.officecode = ? and ";
          parameters.add(office);
        }
        if(bank != null && !"".equals(bank)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.collectionBankId = ? and ";
          parameters.add(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.character = ? and ";
          parameters.add(orgCharacter);
        }
        if(dept != null && !"".equals(dept)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.deptInCharge = ? and ";
          parameters.add(dept);
        }
        if(ragion != null && !"".equals(ragion)){
          criterion += " chgPaymentTail.chgPaymentHead.org.orgInfo.region = ? and ";
          parameters.add(ragion);
        }
        if(startDate != null && !"".equals(startDate) && endDate != null && !"".equals(endDate)){
          criterion += " (chgPaymentTail.chgPaymentHead.bizDate  between ?  and  ?)  and ";
          parameters.add(startDate);
          parameters.add(endDate);
        }
        if (criterion.length() != 0) 
//          criterion = "where (orgHAFAccountFlow.bizStatus = 3 or orgHAFAccountFlow.bizStatus = 4 or orgHAFAccountFlow.bizStatus = 5) and orgHAFAccountFlow.org.id  "
//              + securityInfo.getGjjSecurityHqlSQL()
//              + " and "
//              + criterion.substring(0, criterion.lastIndexOf("and"));
          criterion = " where chgPaymentTail.chgPaymentHead.chgStatus = 2 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and (chgPaymentTail.payStatus=1 or chgPaymentTail.payStatus=3 or chgPaymentTail.payStatus=4)  and "
            + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          BigDecimal countTemp=new BigDecimal(0.00);
          Iterator it=query.iterate();          
          if(it.hasNext()){
            countTemp=(BigDecimal)it.next();
          }
          if(countTemp==null){
            return new Integer(0);
          }else{
          return new Integer(countTemp.intValue());
          }
      }
    });
      count = countInteger.intValue();
    }catch(Exception e){
      e.printStackTrace();
    }
    return count;
  }
  /**
   * hanl
   * �����������������
   * @param empid
   * @return
   * @throws Exception
   */
  public String countMonthPaymentTail(final String empid)throws Exception{
    String count="0";
    count = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select count(monthPaymentTail.id) from MonthPaymentTail monthPaymentTail ";
          String criterion = "";
          Vector parameters = new Vector();
     
          if (empid != null&&empid.equals("")) {
            criterion += "where monthPaymentTail. = ? ";
            parameters.add(new Integer(empid));
          }

          hql = hql + criterion ;

          Query query = session.createQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          return query.uniqueResult().toString();
        }
      }
      );
    return count;
  }
  /**
   * hanl
   * ������
   * @param empid
   * @return
   * @throws Exception
   */
  public String countOverdueInfoOweMonth(final String contractId)throws Exception{
    String oweMonth="0";
    oweMonth = (String)getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select count(distinct(t.year_month )) from pl203 t where t.loan_type = 2 ";
          if(contractId != null && !"".equals(contractId)){
            hql = hql+" and t.contract_id = '"+contractId+"'";
          }
          Query query = session.createSQLQuery(hql);

          return query.uniqueResult().toString();
        }
      }
      );
    return oweMonth;
  }
  
  //�����2008616
  /**
   * ���������������ѯְ����Ϣ(List)
   * type=A(ChgPaymentSalaryBase)
   * @param String id,
   * @param String chgMonth
   * @return �����
   */
  public List queryByCriterionsAll(final String id, final String chgMonth,
      final String orderBy, final String order, final int start,
      final int pageSize,final int page) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail,ChgPaymentSalaryBase chgPaymentSalaryBase  ";
        String criterion = "";
        Vector parameters = new Vector();
        if (chgMonth != null) {
          criterion += "chgPaymentTail.chgPaymentHead.chgMonth = ? and ";
          parameters.add(chgMonth);
        }
        if (id != null) {
          criterion += "chgPaymentTail.chgPaymentHead.org.id = ? and ";
          parameters.add(new Integer(id));
        }
        if (criterion.length() != 0)
          criterion = "where chgPaymentTail.chgPaymentHead.chgStatus=1 and chgPaymentTail.chgPaymentHead.id=chgPaymentSalaryBase.id and  "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }    
      
        return query.list();
      }
    }
    );

    return orglist;
  }
  //�����2008616
  /**
   * ����AA202id ����β��
   * @param String id AA202id
   * @return AA203����
   * ���Ʒ�
   */
  public List queryChgPaymentTailByChgPaymentPaymentAll(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize) {
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    List orglist = null;
  try {
    orglist = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = "select chgPaymentTail from ChgPaymentTail chgPaymentTail ";
        String criterion = "";
        Vector parameters = new Vector();
   
        if (id != null) {
          criterion += "where chgPaymentTail.chgPaymentHead.id = ? ";
          parameters.add(new Integer(id));
        }
        

        String ob = orderBy;
        if (ob == null)
          ob = "chgPaymentTail.empId ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;

        Query query = session.createQuery(hql);

        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
    
        return query.list();
      }
    }

    );
  } catch (Exception e) {
    e.printStackTrace();
  }
    return orglist;
  }
  public void updateChgOrgRateTail_wsh(final String id,final  String orgRate, final String empRate, final String subjectDirection,final  String summary,final  SecurityInfo securityInfo){
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update ChgOrgRateTail chgOrgRateTail set chgOrgRateTail.orgRate=? ,chgOrgRateTail.empRate=?, chgOrgRateTail.empPay=chgOrgRateTail.salaryBase*?, chgOrgRateTail.orgPay=chgOrgRateTail.salaryBase*? "
              + "where chgOrgRateTail.chgOrgRate.id=? "
             ;
          Query query = session.createQuery(hql);
          query.setString(0, orgRate);
          query.setString(1, empRate);
          query.setInteger(4,Integer.parseInt(id));
          query.setString(2, empRate);
          query.setString(3, orgRate);
//          query.setString(3, bizType);
//          query.setString(4, moneyType);
//          query.setString(5, subjectDirection);
//          query.setString(6, securityInfo.getBookId());
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
}