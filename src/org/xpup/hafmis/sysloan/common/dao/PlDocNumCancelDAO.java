package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.sysloan.common.domain.entity.PlDocNumCancel;

public class PlDocNumCancelDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public PlDocNumCancel queryById(Serializable id) {  
    Validate.notNull(id);
    return  (PlDocNumCancel) getHibernateTemplate().get(PlDocNumCancel.class,id);    
  }

  /**
   * �����¼
   * @param PlDocNumCancel
   * @return
   */
  public Serializable insert(PlDocNumCancel plDocNumCancel){
    Serializable id = null;
    try{    
    Validate.notNull(plDocNumCancel);
    id = getHibernateTemplate().save(plDocNumCancel);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
  /**
   * ��������ȷ��-�����ϵ�ƾ֤�Ų������ϱ�
   */
  public void insertPlDocNumCancel(final String cancelcredenceid,final String officeCode,final String yearMonth)throws BusinessException{
    try{
      PlDocNumCancel plDocNumCancel=new PlDocNumCancel();
      plDocNumCancel.setCancelcredenceid(cancelcredenceid);
      plDocNumCancel.setYearMonth(yearMonth);
      plDocNumCancel.setOfficeCode(officeCode);
    this.insert(plDocNumCancel);
    }catch(Exception e){
      throw new BusinessException("����ҵ��ƾ֤��ʱ���ִ���!");
    }
  }  
}
