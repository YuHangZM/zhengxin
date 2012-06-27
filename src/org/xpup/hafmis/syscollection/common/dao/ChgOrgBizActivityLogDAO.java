package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgBizActivityLog;

/**
 * ҵ����־-��λ���
 * 
 *@author ���
 *2007-6-20
 */
public class ChgOrgBizActivityLogDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public ChgOrgBizActivityLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (ChgOrgBizActivityLog) getHibernateTemplate().get(ChgOrgBizActivityLog.class,id);    
  }
  /**
   * �����¼
   * @param chgOrgBizActivityLog
   * @return
   */
  public Serializable insert(ChgOrgBizActivityLog  chgOrgBizActivityLog){
    Serializable id = null;
    try{    
    Validate.notNull(chgOrgBizActivityLog);
    id = getHibernateTemplate().save(chgOrgBizActivityLog);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}