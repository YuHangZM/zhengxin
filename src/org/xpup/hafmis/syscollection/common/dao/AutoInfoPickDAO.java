package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;


public class AutoInfoPickDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public AutoInfoPick queryById(Serializable id) {
    Validate.notNull(id);
    return (AutoInfoPick) getHibernateTemplate().get(
        AutoInfoPick.class, id);
  }

  /**
   * �����¼
   * 
   * @param AutoInfoPick
   * @return
   */
  public Serializable insert(AutoInfoPick autoInfoPick) {
    Serializable id = null;
    try {
      Validate.notNull(autoInfoPick);
      id = getHibernateTemplate().save(autoInfoPick);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * ɾ��DA001
   * @author ��� 20071214
   * @param org_id ��λ���
   * @param org_head_id��AA202ID
   * @param  type
   * @return
   */
  public void deleteAutoInfoPick(final String orgid,final String org_head_id,
      final String type) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql = "delete from AutoInfoPick autoInfoPick where autoInfoPick.orgId=? and autoInfoPick.orgHeadId=? and autoInfoPick.type=?";
          Query query = session.createQuery(sql);
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, new Integer(org_head_id));
          query.setParameter(2, type);
          return new Integer(query.executeUpdate());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * ����DA001
   * 
   * @param AutoInfoPick
   * @return
   */
  public void update(AutoInfoPick autoInfoPick) {
    try {
      Validate.notNull(autoInfoPick);
      getHibernateTemplate().update(autoInfoPick);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * ɾ������DA001��ɾ����
   * @author ��� 20071213
   * @param status
   * @param center_head_id
   * @param org_id
   * @param org_head_id
   * @param  type
   * @return
   */
  public void deleteupdateAutoInfoPick(final String status,final String center_head_id,final String centerBizDate,final String org_id,final String centerhead_id,
      final String type) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql ="update AutoInfoPick autoInfoPick set autoInfoPick.status=?,autoInfoPick.centerHeadId=?,autoInfoPick.centerBizDate=? where autoInfoPick.orgId=? and autoInfoPick.centerHeadId=? and autoInfoPick.type=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, status);
          query.setParameter(1, center_head_id);
          if(centerBizDate.equals("")){
            query.setParameter(2, centerBizDate);
          }else{
            query.setParameter(2, new Date());
          }
          query.setParameter(3, new Integer(org_id));
          query.setParameter(4, new Integer(centerhead_id));
          query.setParameter(5, type);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * �ж��ύ״̬�Ƿ�Ϊδ��ȡ
   *  @author ��� 20071212
   * @param org_id����λ���
   * @param org_head_id����λ��ͷ��id aa202��id
   * @param��type��ҵ������
   * @return flag������true�Ǹü�¼���ύ״̬Ϊδ��ȡ��falseΪ������δ��ȡ��
   */
  public boolean isNOPickUp(final String org_id,final String org_head_id,final String type) {
    boolean flag = true;
    String count  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select count(*) from da001 da  where da.org_id=? and da.org_head_id=? and da.type=? and da.status=0";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(org_id));
            query.setParameter(1, new Integer(org_head_id));
            query.setParameter(2, type);
            return query.uniqueResult().toString();
          }
        });
     if (new Integer(count).intValue()==0) {
      flag = false;
    }  
    return flag;
  }
  /**
   * ��ѯDA001�е�״̬
   *  @author ��� 20071212
   * @param org_id����λ���
   * @param org_head_id����λ��ͷ��id aa202��id
   * @param��type��ҵ������
   * @return status
   */
  public String findStatus(final String org_id, final String org_head_id,
      final String type) {
    String status = "";
    status = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select da.status  from da001 da  where da.org_id=? and da.org_head_id=? and da.type=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, new Integer(org_id));
        query.setParameter(1, new Integer(org_head_id));
        query.setParameter(2, type);
        return query.uniqueResult();
      }
    });
    if (status == null || status.equals("")) {
      status = "2";
    }
    return status;
  }
  /**
   * �ж�DA001���Ƿ�����ύ��¼
   * @author ��� ��20071212
   * @param org_id����λ���
   * @param org_head_id����λ��ͷ��id aa202��id
   * @param��type��ҵ������
   * @return flag ����trueΪ�����ύ�ļ�¼��false�������ύ�ļ�¼��
   */
  public boolean isNOPickIn(final String org_id,final String org_head_id,final String type) {
    boolean flag = true;
    String count  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select count(*) from da001 da  where da.org_id=? and da.org_head_id=? and da.type=?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(org_id));
            query.setParameter(1, new Integer(org_head_id));
            query.setParameter(2, type);
            return query.uniqueResult().toString();
          }
        });
    if (new Integer(count).intValue()==0) {
      flag = false;
    }    
    return flag;
  }
  /**
   * ��ѯ��С��org_head_id
   * @author ��� ��20071212
   * @param org_id����λ���
   * @param status����ȡ״̬��0��δ��ȡ��1������ȡ
   * @param��type����ҵ������
   * @return orgheadid
   */
  public String findOrgHeadid(final String org_id,final String type,final String status) {
    String orgheadid = "";
     orgheadid  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select min(da.org_head_id) from da001 da  where da.org_id=? and da.status=? and da.type=?" ;
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(org_id));
            query.setParameter(1, status);
            query.setParameter(2, type);
            if(query.uniqueResult()==null){
              return null;
            }else{
              return query.uniqueResult().toString();
            }
          
          }
        });
    return orgheadid;
  }
  /**
   * ���ݵ�λ��Ų�ѯ��DA001��STATUS=0,TYPE=M,N,O�ļ�¼
   * @author ��� ��20071212
   * @param orgid  ��λ���
   * @return list
   */
  public int queryNoPickUpListbyOrgid(final String org_id) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.status=0 and autoInfoPick.type in ('M','N','O') and autoInfoPick.orgId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(org_id));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }
  /**
   * ȡ��org_head_id��type 
   * @author ��� ��20071212
   * @return Object[]
   */
  public Object[] queryOrgHeadidAndType(final String orgid) {
    Object[] obj = new Object[2];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select da.org_head_id, da.type"
            +" from da001 da "
            +" where da.type in ('M', 'N', 'O') "
            +" and da.status = 0"
            +" and da.org_id = ? "
            +" and da.org_head_id ="
            +" (select min(da.org_head_id)"
            +" from da001 da "
            +" where  da.org_id = ?"
            +" and  da.type in ('M', 'N', 'O')"
            +" and da.status = 0"
            +" and da.pay_head_id ="
            +" (select min(da.pay_head_id)"
            +" from da001 da "
            +" where da.org_id = ?"
            +" and da.pay_head_id is not null"
            +" and da.status = 0"
            +" and da.type in ('M', 'N', 'O')))";
          Query query = session.createSQLQuery(sql);  
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, new Integer(orgid));
          query.setParameter(2, new Integer(orgid));
          return query.uniqueResult();       
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }
  /**ҵ��������ȡ�����õ�
   * ��PAY_HEAD_ID��Ϊ�գ�ȡ��org_head_id��type
   * @author ��� ��20071212
   * @return Object[]
   */
  public Object[] findOrgHeadidAndType(final String orgid) {
    Object[] obj = new Object[2];
    try {
      obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select da.org_head_id, da.type "
                      +" from da001 da "
                      +" where da.org_id = ? "
                      +" and da.type in ('M', 'N', 'O') "
                      +" and da.status = 0 "
                      +" and da.org_head_id = (select min(da.org_head_id) "
                      +" from da001 da "
                      +" where da.org_id = ? "
                      +" and da.type in ('M', 'N', 'O') "
                      +" and da.status = 0) ";
          Query query = session.createSQLQuery(sql);  
          query.setParameter(0, new Integer(orgid));
          query.setParameter(1, new Integer(orgid));
          return query.uniqueResult();       
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * ���ݵ�λ��Ų�ѯ��DA001��STATUS=0,TYPE=K�ļ�¼
   * @author wangy ��2007-12-14
   * @param orgid  ��λ���
   * @return int
   */
  public int queryPickUpCountbyOrgidAndStatusAndType(final Integer org_id) {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.status=0 and autoInfoPick.type='K' and autoInfoPick.orgId=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, org_id);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }
  /**
   * ����DA001 ��ȡ��
   * @author ��� 20071213
   * @param status
   * @param center_head_id
   * @param org_id
   * @param centerheadid
   * @param  type
   * @return
   */


  public void updateAutoInfoPick(final String status,final String center_head_id,final String centerBizDate,final String org_id,final String centerhead_id,

      final String type) throws Exception {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {


          String hql ="update AutoInfoPick autoInfoPick set autoInfoPick.status=?,autoInfoPick.centerHeadId=?,autoInfoPick.centerBizDate=? where autoInfoPick.orgId=? and autoInfoPick.orgHeadId=? and autoInfoPick.type=?";

          Query query = session.createQuery(hql);
          query.setParameter(0, status);
          query.setParameter(1, center_head_id);

          if(centerBizDate.equals("")){
            query.setParameter(2, centerBizDate);
          }else{
            query.setParameter(2, new Date());
          }
          query.setParameter(3, new Integer(org_id));
          query.setParameter(4, new Integer(centerhead_id));
          query.setParameter(5, type);

          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }  
  /**
   * ���ݵ�λ��Ų�ѯ��DA001��center_head_id,TYPE�ļ�¼
   * @author sy ��2007-12-19
   * @param orgid  ��λ���
   * @return List
   */
  public List queryPickUpCountbyOrgidAndStatusAndType(final Integer center_head_id,final String Type)throws BusinessException {
    List list = null;
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from AutoInfoPick autoInfoPick where autoInfoPick.centerHeadId=? and autoInfoPick.type=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, center_head_id);
          query.setParameter(1, Type);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * ����aa204��id��ѯ����Ϊo��orgheadid
   * @author ��� ��20071221��
   * @param chgPersonHeadID
   * @return orgheadid
   */
  public String queryOrgHeadid(final String chgPersonHeadID) {
    String orgheadid = "";
     orgheadid  = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String sql = "select da001.org_head_id from DA001 da001 where da001.center_head_id=? and da001.type='O'" ;
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, new Integer(chgPersonHeadID));
            if(query.uniqueResult()==null){
              return null;
            }else{
              return query.uniqueResult().toString();
            }          
          }
        });
    return orgheadid;
  }
  /**
   * ��ѯorgheadid
   * @author ������20071221��
   * @param chgPersonHeadID
   * @return orgheadid
   */
  public String queryOrgHeadid1(final String chgPersonHeadID) {
    String orgheadid = "";
     try {
      orgheadid  = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String ssql = "select d001.org_head_id from da001 d001 where d001.center_head_id=? and d001.type='E' ";
              Query query = session.createSQLQuery(ssql);
              query.setParameter(0,new Integer(chgPersonHeadID));
              if(query.uniqueResult()==null){
                return null;
                
              }else{
                return query.uniqueResult().toString();
              }          
            }
          });
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return orgheadid;
  }
}
