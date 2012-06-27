package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
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
import org.xpup.hafmis.syscollection.common.domain.entity.CollLoanbackHead;
import org.xpup.security.common.domain.Userslogincollbank;
/**
 * @author ���ƽ 2007-12-18
 */
public class CollLoanbackHeadDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public CollLoanbackHead queryById(Integer id) {
    Validate.notNull(id);
    return (CollLoanbackHead) getHibernateTemplate().get(CollLoanbackHead.class, id);
  }

  /**
   * �����¼
   * 
   * @param collLoanbackHead
   * @return
   */
  public Serializable insert(CollLoanbackHead collLoanbackHead) {
    Serializable id = null;
    try {
      Validate.notNull(collLoanbackHead);
      id = getHibernateTemplate().save(collLoanbackHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * �����𻹴�--����ҳ��
   * @author ���ƽ 2007-12-18 
   * �жϸ�������aa410�е�״̬�Ƿ�Ϊ1��2
   * ��ѯ����:office
   */
  public List queryStatusByOffice(final String bank,final String status) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select collLoanbackHead.id,collLoanbackHead.batchNum,collLoanbackHead.bizDate from CollLoanbackHead collLoanbackHead "
            + "where (collLoanbackHead.status=? or collLoanbackHead.status=? or collLoanbackHead.status=?) and " +
                "collLoanbackHead.loanBankId=? order by collLoanbackHead.id DESC ";
        Query query = session.createQuery(hql);
        query.setString(3, bank);
        if(status.equals("")){
          System.out.println("staus="+status);
          query.setString(0, "1");
          query.setString(1, "2");
          query.setString(2, "3");
        }else if(status.equals("1")){
          System.out.println("staus="+status);
          query.setString(0, "1");
          query.setString(1, "1");
          query.setString(2, "1");
        }else if(status.equals("2")){
          System.out.println("staus="+status);
          query.setString(0, "1");
          query.setString(1, "2");
          query.setString(2, "2");
        }else{
          System.out.println("staus="+status);
          query.setString(0, "1");
          query.setString(1, "1");
          query.setString(2, "1");
        }
        Iterator it = query.list().iterator();
        System.out.println("list.size()="+query.list().size());
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackHead collLoanbackHead = new CollLoanbackHead();
            if (obj[0] != null) {
              collLoanbackHead.setId(new Integer(obj[0].toString()));
            }
            if (obj[1] != null) {
              collLoanbackHead.setBatchNum(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackHead.setBizDate(obj[2].toString());
            }
            temp_list.add(collLoanbackHead);
          }
        }
        return temp_list;
      }
    });
    return list;
  }
  /**
   * �����𻹴�
   * @author ���ƽ 2007-12-19
   * ��ѯ�������£���aa410�е�״̬Ϊ3������
   * ��ѯ����:office
   */
  public List queryByBank(final String bank) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select collLoanbackHead.id from CollLoanbackHead collLoanbackHead "
            + "where collLoanbackHead.status='3' and " +
                "collLoanbackHead.loanBankId=? ";
        Query query = session.createQuery(hql);
        query.setString(0, bank);
        Iterator it = query.list().iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackHead collLoanbackHead = new CollLoanbackHead();
            if (obj[0] != null) {
              collLoanbackHead.setId(new Integer(obj[0].toString()));
            }
            temp_list.add(collLoanbackHead);
          }
        }
        return temp_list;
      }
    });
    return list;
  }
  /**
   * ���´����鼯���У�����
   * 
   * @author ���ƽ
   * @pram officeCode String
   * @ 2007-12-19
   * @return List
   */
  public List findCollectionBankId(final String officeCode,final List collBankList) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List queryList = new ArrayList();
        List collBankList1=new ArrayList();
        String temp_collBank="";
        Userslogincollbank userslogincollbank = null;
        Iterator itr2 = collBankList.iterator();
        while (itr2.hasNext()) {
          userslogincollbank = (Userslogincollbank) itr2.next();
          collBankList1.add(userslogincollbank.getCollBankId().toString());
        }
        for(int i=0;i<collBankList1.size();i++){
          String collBank=(String)collBankList1.get(i);
          temp_collBank=temp_collBank+collBank+",";
        }
        temp_collBank=temp_collBank.substring(0, temp_collBank.lastIndexOf(","));
        String hql = "select distinct bb105.coll_bank_id ,bb105.coll_bank_name" +
            " from BB105 bb105 " +
            "where bb105.office =? " +
            "and bb105.status='1'" +
            "and bb105.coll_bank_id in ("+temp_collBank+")";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, officeCode);
        Iterator iter= query.list().iterator();
        Object obj[] = null;
        while(iter.hasNext()){
          obj=(Object[]) iter.next();
          queryList.add(new org.apache.struts.util.LabelValueBean(obj[0].toString(), obj[1].toString()));
        }
        return queryList;
      }
    });
    return list;
  }
  /**
   * �����𻹴�
   * @author ���ƽ 2007-12-20
   * ��ѯAA306�У��Ƿ���ڶ�Ӧ���κ�=AA410�����κ�
   * ��ѯ����:batchNum
   */
  public List queryStatusByBatchNum(final String batchNum) {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa306.id,aa306.pick_satatus from AA306 aa306 " +
            "where aa306.batch_num=? ";
        Query query = session.createSQLQuery(hql);
        query.setString(0, batchNum);
        return query.list();
      }
    });
    return list;
  }
  /**
   * ��ѯ�����κ���aa410�����Ƿ����
   * ���ƽ
   * 2007.12.24
   */
  public List queryBatchNum_GJP(final String batchNum) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select collLoanbackHead.id from CollLoanbackHead collLoanbackHead where collLoanbackHead.batchNum = ? ";
          Query query = session.createQuery(hql);
          query.setString(0, batchNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
