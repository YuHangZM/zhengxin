package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustPick;

/**
 * ��λס������ҵ����ˮ-����ȡL
 * 
 *@author ���
 *2007-6-25
 */
public class OrgHAFAccountFlowAdjustPickDAO extends HibernateDaoSupport{
  
  /**
   * ����id��ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowAdjustPick queryById(Serializable id){
    Validate.notNull(id);
    return (OrgHAFAccountFlowAdjustPick) getHibernateTemplate().
    get(OrgHAFAccountFlowAdjustPick.class, id);
    
  }
  /**
   * �����¼
   * @param orgHAFAccountFlowAdjustPick
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowAdjustPick orgHAFAccountFlowAdjustPick){
    Serializable id = null;
    try{
      id = getHibernateTemplate().save(orgHAFAccountFlowAdjustPick);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}