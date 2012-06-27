package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.CenterRestoreLoanPlan;

public class CenterRestoreLoanPlanDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public CenterRestoreLoanPlan queryById(Serializable id) {  
    Validate.notNull(id);
    return  (CenterRestoreLoanPlan) getHibernateTemplate().get(CenterRestoreLoanPlan.class,id);    
  }

  /**
   * �����¼
   * @param CenterRestoreLoanPlan
   * @return
   */
  public Serializable insert(CenterRestoreLoanPlan centerRestoreLoanPlan){
    Serializable id = null;
    try{    
    Validate.notNull(centerRestoreLoanPlan);
    id = getHibernateTemplate().save(centerRestoreLoanPlan);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }

}
