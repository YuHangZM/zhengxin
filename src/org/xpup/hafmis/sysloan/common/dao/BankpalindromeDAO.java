package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.Bankpalindrome;

/** 
 * PL010 ���л��ĸ�ʽ���ñ�
 * @author Administrator
 *
 */
    
public class BankpalindromeDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public Bankpalindrome queryById(Serializable id) {
    Validate.notNull(id);
    return (Bankpalindrome) getHibernateTemplate().get(Bankpalindrome.class, id);
  }
  /**
   * �����¼
   * 
   * @param assistantOrg
   * @return
   */
  public Serializable insert(Bankpalindrome bankpalindrome) {
    Serializable id = null;
    try {
      Validate.notNull(bankpalindrome);
      id = getHibernateTemplate().save(bankpalindrome);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * ɾ��
   */
  public void delete(Bankpalindrome bankpalindrome){
    try{
      getHibernateTemplate().delete(bankpalindrome);
    }catch(Exception a){
      a.printStackTrace();
    }
  }
}
