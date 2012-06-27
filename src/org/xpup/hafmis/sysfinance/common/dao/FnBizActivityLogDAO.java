package org.xpup.hafmis.sysfinance.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnBizActivityLog;

public class FnBizActivityLogDAO extends HibernateDaoSupport {

  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public FnBizActivityLog queryById(Serializable id) {
    Validate.notNull(id);
    return (FnBizActivityLog) getHibernateTemplate().get(
        FnBizActivityLog.class, id);
  }

  /**
   * �����¼
   * 
   * @param BookParameter
   * @return
   */
  public Serializable insert(FnBizActivityLog fnBizActivityLog) {
    Serializable id = null;
    try {
      Validate.notNull(fnBizActivityLog);
      id = getHibernateTemplate().save(fnBizActivityLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * �ֽ��ռ���
   * @author ���ƽ
   * 2007-10-18
   * ɾ��fn310���¼��CREDENCE_NUM=��ѡ��¼��ƾ֤�� and CREDENCE_DATE=ƾ֤���� and  OFFICE=�������´� and CREDENCE_TYPE=1��
   */
  public void deleteFnBizActivityLog(final String credenceNum,final String credenceDate,final String office,final SecurityInfo securityInfo) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from FnBizActivityLog fnBizActivityLog where fnBizActivityLog.credenceNum = ? " +
              "and fnBizActivityLog.credenceDate = ? and fnBizActivityLog.office = ? and fnBizActivityLog.credenceType = '1' " +
              "and fnBizActivityLog.bookId=? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, credenceNum);
          query.setParameter(1, credenceDate);
          query.setParameter(2, office);
          query.setParameter(3, securityInfo.getBookId());
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * ƾ֤���_ƾ֤���_��������
   * @author wsh
   * 2007-10-29
   * ɾ��fn310���¼��CREDENCE_NUM=��ѡ��¼��ƾ֤�� and CREDENCE_DATE=ƾ֤���� and  OFFICE=�������´� and CREDENCE_TYPE=1 ����ACTION=0��ƾ֤����CREDENCE_TYPE=0.��
   */
  public void deleteFnBizActivityLog_wsh(final String credenceNum,final String credenceDate,final String office,final SecurityInfo securityInfo) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from FnBizActivityLog fnBizActivityLog " +
              "where fnBizActivityLog.credenceNum = ? " +
              "and fnBizActivityLog.credenceDate = ? " +
              "and fnBizActivityLog.office = ?" +
              " and fnBizActivityLog.credenceType = '0' " +
              "and fnBizActivityLog.action='0' " +
              "and fnBizActivityLog.bookId = ? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, credenceNum);
          query.setParameter(1, credenceDate);
          query.setParameter(2, office);
          query.setParameter(3, securityInfo.getBookId());
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * ƾ֤¼��
   * @author ����
   * 2007-11-7
   * ɾ��fn310���¼��CREDENCE_NUM=��ѡ��¼��ƾ֤�� and CREDENCE_DATE=ƾ֤���� and  OFFICE=�������´� and CREDENCE_TYPE=0��
   */
  public void deleteFnBizActivityLog_lb(final String credenceNum,final String credenceDate,final String office,final String credenceType,final SecurityInfo securityInfo) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from FnBizActivityLog fnBizActivityLog where fnBizActivityLog.credenceNum = ? " +
              "and fnBizActivityLog.credenceDate = ? and fnBizActivityLog.office = ? and fnBizActivityLog.credenceType = ? " +
              "and fnBizActivityLog.bookId=? ";
          Query query = session.createQuery(sql);
          query.setParameter(0, credenceNum);
          query.setParameter(1, credenceDate);
          query.setParameter(2, office);
          query.setParameter(3, credenceType);
          query.setParameter(4, securityInfo.getBookId());
          
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
