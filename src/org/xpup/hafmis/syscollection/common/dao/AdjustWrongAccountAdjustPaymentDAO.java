package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustPayment;

/**
 * ���˵���ͷ��-���ɴ� 
 * 
 *@author ���
 *2007-6-19
 */
public class AdjustWrongAccountAdjustPaymentDAO extends HibernateDaoSupport{
  
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public AdjustWrongAccountAdjustPayment queryById(Serializable id) {  
    Validate.notNull(id);
    return  (AdjustWrongAccountAdjustPayment) getHibernateTemplate().get(AdjustWrongAccountAdjustPayment.class,id);    
  }
  /**
   * �����¼
   * @param adjustWrongAccountAdjustPayment
   * @return
   */
  public Serializable insert(AdjustWrongAccountAdjustPayment adjustWrongAccountAdjustPayment){
    Serializable id = null;
    try{    
    Validate.notNull(adjustWrongAccountAdjustPayment);
    id = getHibernateTemplate().save(adjustWrongAccountAdjustPayment);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
}
