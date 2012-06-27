package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustOther;

/**
 * ��λס������ҵ����ˮ-������G
 * 
 *@author ���
 *2007-6-25
 */
public class OrgHAFAccountFlowAdjustOtherDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowAdjustOther queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowAdjustOther) getHibernateTemplate().get(OrgHAFAccountFlowAdjustOther.class,id);    
  }
  /**
   * �����¼
   * @param orgHAFAccountFlowAdjustOther
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowAdjustOther orgHAFAccountFlowAdjustOther){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowAdjustOther);
    id = getHibernateTemplate().save(orgHAFAccountFlowAdjustOther); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}