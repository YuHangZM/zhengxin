package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountAdjustPick;

/**
 * ��λס������ҵ����ˮ-����ȡL
 * 
 *@author ���
 *2007-6-25
 */
public class AdjustWrongAccountAdjustPickDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public AdjustWrongAccountAdjustPick queryById(Integer id) {
    Validate.notNull(id);
    return  (AdjustWrongAccountAdjustPick) getHibernateTemplate().get(AdjustWrongAccountAdjustPick.class,id);    
  }
  /**
   * �����¼
   * @param adjustWrongAccountAdjustPick
   * @return
   */
  public Serializable insert(AdjustWrongAccountAdjustPick adjustWrongAccountAdjustPick){
    Serializable id = null;
    try{    
    Validate.notNull(adjustWrongAccountAdjustPick);
    id = getHibernateTemplate().save(adjustWrongAccountAdjustPick); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}