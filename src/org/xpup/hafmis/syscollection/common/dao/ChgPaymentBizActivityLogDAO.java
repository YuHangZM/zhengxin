package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChangeSalaryBaseBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentBizActivityLog;

/**
 * ҵ����־-�ɶ����
 * 
 *@author ���
 *2007-6-20
 */
public class ChgPaymentBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public ChgPaymentBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ChgPaymentBizActivityLog) getHibernateTemplate().get(ChgPaymentBizActivityLog.class,id);    
  }
  /**
   * �����¼
   * @param chgPaymentBizActivityLog
   * @return
   */
  public Serializable insert(ChgPaymentBizActivityLog chgPaymentBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(chgPaymentBizActivityLog);
    id = getHibernateTemplate().save(chgPaymentBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
  /**
   * ��ɾ��������¼
   * @return
   * ����� 
   */
  public void deleteWuht(ChgPaymentBizActivityLog chgPaymentBizActivityLog){
    Validate.notNull(chgPaymentBizActivityLog);
    getHibernateTemplate().delete(chgPaymentBizActivityLog);
  }
  /**
   * ��ѯ��¼(ChgPaymentSalaryBase)
   * id
   * �����
   * 2007.6.27
   * @return
   */
  public ChgPaymentBizActivityLog queryChgPaymentBizActivityLogByIdWuht(final String id,final String action) {

    ChgPaymentBizActivityLog chgPaymentBizActivityLog = null;
    chgPaymentBizActivityLog = (ChgPaymentBizActivityLog) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {    
            String hql = "select chgPaymentBizActivityLog from ChgPaymentBizActivityLog chgPaymentBizActivityLog  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null) {
              criterion += "chgPaymentBizActivityLog.bizid = ? and ";
              parameters.add(new Integer(id));
            }       
            if (action != null) {
              criterion += "chgPaymentBizActivityLog.action= ? and ";
              parameters.add(new Integer(action));
            }   
            if (criterion.length() != 0) 
              criterion = "where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            session.clear();
            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }     
            return  query0.uniqueResult();
          }
        });

    return chgPaymentBizActivityLog;
  }
  
}