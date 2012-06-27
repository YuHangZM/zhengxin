package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestBizActivityLog;

/**
 * ҵ����־-��Ϣ
 * 
 *@author ���
 *2007-6-20
 */
public class SettInterestBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public SettInterestBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (SettInterestBizActivityLog) getHibernateTemplate().get(SettInterestBizActivityLog.class,id);    
  }
  /**
   * �����¼
   * @param settInterestBizActivityLog
   * @return
   */
  public Serializable insert(SettInterestBizActivityLog settInterestBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(settInterestBizActivityLog);
    id = getHibernateTemplate().save(settInterestBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}