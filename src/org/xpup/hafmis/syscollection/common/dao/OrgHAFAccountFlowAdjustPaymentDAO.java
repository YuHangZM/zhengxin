package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowAdjustPayment;
/**
 * ��λס������ҵ����ˮ-���ɴ�K
 * 
 *@author ���
 *2007-6-25
 */
public class OrgHAFAccountFlowAdjustPaymentDAO extends HibernateDaoSupport{
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowAdjustPayment queryById(Serializable id){
    Validate.notNull(id);
    return (OrgHAFAccountFlowAdjustPayment) getHibernateTemplate().get(OrgHAFAccountFlowAdjustPayment.class,id);
    
  }
  /**
   * �����¼
   * @param orgAccountFlowAdjustPayment
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowAdjustPayment orgAccountFlowAdjustPayment){
    Serializable id = null;
    try{
      Validate.notNull(orgAccountFlowAdjustPayment);
      id=getHibernateTemplate().save(orgAccountFlowAdjustPayment);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}