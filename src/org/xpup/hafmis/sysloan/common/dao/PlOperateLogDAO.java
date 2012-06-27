package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;

/**
 * ������־PL021
 * 
 *@author ���
 *2007-9-13
 */
public class PlOperateLogDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public PlOperateLog queryById(Serializable id) {  
    Validate.notNull(id);
    return  (PlOperateLog) getHibernateTemplate().get(PlOperateLog.class,id);    
  }
  /**
   * �����¼
   * 
   * @param plOperateLog
   * @return
   */
  public Serializable insert(PlOperateLog plOperateLog) {
    Serializable id = null;
    try {
      Validate.notNull(plOperateLog);
      id = getHibernateTemplate().save(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
}
