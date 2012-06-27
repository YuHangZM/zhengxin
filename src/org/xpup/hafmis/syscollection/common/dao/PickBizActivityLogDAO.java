package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.PickBizActivityLog;

/**
 * ҵ����־-��ȡ
 * 
 *@author ���
 *2007-6-20
 */
public class PickBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public PickBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (PickBizActivityLog) getHibernateTemplate().get(PickBizActivityLog.class,id);    
  }
  /**
   * ���ĺ�
   * ɾ��PickBizActivityLog����
   */
  public void delete(PickBizActivityLog s){
    try{
      getHibernateTemplate().delete(s);
    }catch(Exception a){
      a.printStackTrace();
    }
  }
  /**
   * ���ĺ�..����Action=1��bizId�����־��¼
   */
  public PickBizActivityLog queryByBizId(final int bizId){
    PickBizActivityLog bizActivityLog = new PickBizActivityLog();
    try{
      PickBizActivityLog l = null;
      bizActivityLog = (PickBizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              //�˼�¼һ������..����ֻ����һ��
              String hql = "from PickBizActivityLog bizActivityLog where bizActivityLog.bizid = ? and bizActivityLog.action=1";
              BizActivityLog b = (BizActivityLog)session.createQuery(hql).setInteger(0, bizId).uniqueResult();
              return b;
            }
          });
      return bizActivityLog;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  /**
   * ���ƽ..����Action=3��bizId�����־��¼
   */
  public PickBizActivityLog queryByBizId1(final int bizId){
    PickBizActivityLog bizActivityLog = new PickBizActivityLog();
    try{
      PickBizActivityLog l = null;
      bizActivityLog = (PickBizActivityLog) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
              //�˼�¼һ������..����ֻ����һ��
              String hql = "from PickBizActivityLog bizActivityLog where bizActivityLog.bizid = ? and bizActivityLog.action=3";
              BizActivityLog b = (BizActivityLog)session.createQuery(hql).setInteger(0, bizId).uniqueResult();
              return b;
            }
          });
      return bizActivityLog;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
  /**
   * �����¼
   * @param pickBizActivityLog
   * @return
   */
  public Serializable insert(PickBizActivityLog pickBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(pickBizActivityLog);
    id = getHibernateTemplate().save(pickBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}