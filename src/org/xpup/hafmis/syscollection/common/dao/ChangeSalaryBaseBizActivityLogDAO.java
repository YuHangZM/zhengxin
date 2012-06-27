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
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRateBizActivityLog;

/**
 * ҵ����־-���ʻ�������
 * 
 *@author ���
 *2007-6-20
 */
public class ChangeSalaryBaseBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public ChangeSalaryBaseBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ChangeSalaryBaseBizActivityLog) getHibernateTemplate().get(ChangeSalaryBaseBizActivityLog.class,id);    
  }
  /**
   * �����¼
   * @param changeSalaryBaseBizActivityLog
   * @return
   */
  public Serializable insert(ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(changeSalaryBaseBizActivityLog);
    id = getHibernateTemplate().save(changeSalaryBaseBizActivityLog);  
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
  public void deleteWuht(ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog){
    Validate.notNull(changeSalaryBaseBizActivityLog);
    getHibernateTemplate().delete(changeSalaryBaseBizActivityLog);
  }
  /**
   * ��ѯ��¼(ChgPaymentSalaryBase)
   * id
   * �����
   * 2007.6.27
   * @return
   */
  public ChangeSalaryBaseBizActivityLog queryChgPaymentBizActivityLogByIdWuht(final String id,final String action) {

    ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog = null;
    changeSalaryBaseBizActivityLog = (ChangeSalaryBaseBizActivityLog) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {    
            String hql = "select changeSalaryBaseBizActivityLog from ChangeSalaryBaseBizActivityLog changeSalaryBaseBizActivityLog  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null) {
              criterion += "changeSalaryBaseBizActivityLog.bizid = ? and ";
              parameters.add(new Integer(id));
            }        
            if (action != null) {
              criterion += "changeSalaryBaseBizActivityLog.action = ? and ";
              parameters.add(new Integer(action));
            }     
            if (criterion.length() != 0) 
              criterion = "where "
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

    return changeSalaryBaseBizActivityLog;
  }
  
}