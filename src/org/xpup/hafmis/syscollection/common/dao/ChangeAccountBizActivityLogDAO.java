package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeAccountBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;

/**
 * ҵ����־-����
 * 
 *@author ���
 *2007-6-20
 */
public class ChangeAccountBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public ChangeAccountBizActivityLog queryById(Integer id) {  
    Validate.notNull(id);
    return  (ChangeAccountBizActivityLog) getHibernateTemplate().get(ChangeAccountBizActivityLog.class,id);    
  }
  /**
   * ��������ɾ��
   * @param id
   * @return
   */
  public void deleteById(Integer id) {
    try {
      Validate.notNull(id);
      ChangeAccountBizActivityLog changeAccountBizActivityLog = queryById(id);
   //   System.out.println("------����ƥ�䲻--------:"+changeAccountBizActivityLog.getId());
      getHibernateTemplate().delete(changeAccountBizActivityLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * ��ѯ
   * @param id
   * @return
   */
  public ChangeAccountBizActivityLog findChangeAccountBizActivityLogByCriterions(final String id,final String type) {
    return (ChangeAccountBizActivityLog)getHibernateTemplate().execute( 
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChangeAccountBizActivityLog changeAccountBizActivityLog  where "+
                         "changeAccountBizActivityLog.bizid=? and changeAccountBizActivityLog.action=3";
            Query query = session.createQuery(hql);
            query.setParameter(0,new Integer(id));
            return query.uniqueResult();          
        }
        }); 
  }
  /**
   * ��ѯ��־id
   * @param id
   * @return
   */
  public ChangeAccountBizActivityLog findChangeAccountBizActivityLogByCriterion(final String bisid) {
    return (ChangeAccountBizActivityLog)getHibernateTemplate().execute( 
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChangeAccountBizActivityLog changeAccountBizActivityLog  where "+
                         "changeAccountBizActivityLog.bizid=?";
            Query query = session.createQuery(hql);
            query.setParameter(0,new Integer(bisid));
            return query.uniqueResult();          
        }
        }); 
  }
  /**
   * �����¼
   * @param changeAccountBizActivityLog
   * @return
   */
  public Serializable insert(ChangeAccountBizActivityLog changeAccountBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(changeAccountBizActivityLog);
    id = getHibernateTemplate().save(changeAccountBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * ��ѯ
   * @param id and type
   * @return
   */
  public ChangeAccountBizActivityLog findBizActivityLogById(final String id,final String type) {
    return (ChangeAccountBizActivityLog)getHibernateTemplate().execute( 
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from ChangeAccountBizActivityLog changeAccountBizActivityLog  where "+
                         "changeAccountBizActivityLog.bizid=? and changeAccountBizActivityLog.action=1 " +
                         "and changeAccountBizActivityLog.types=? ";
            Query query = session.createQuery(hql);
            query.setParameter(0,new Integer(id));
            query.setParameter(1, type);
            return query.uniqueResult();          
        }
        }); 
  }
  
  
}