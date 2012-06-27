package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowDrawing;

public class OrgHAFAccountFlowDrawingDAO extends HibernateDaoSupport{

  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public OrgHAFAccountFlowDrawing queryById(Integer id) {
    Validate.notNull(id);
    return  (OrgHAFAccountFlowDrawing) getHibernateTemplate().get(OrgHAFAccountFlowDrawing.class,id);    
  }
  /**
   * ���ĺ�... ����ͷ���Id�ҳ�����λ����ˮ��Id...
   * ���Ǵ˼�¼������Ψһ��һ����¼..�������������¼ ��ô�����ݿ�Ĵ���
   */
  public OrgHAFAccountFlow getOrgHAFAccountFlow(final Integer id){
    try{
      OrgHAFAccountFlow s = (OrgHAFAccountFlow)getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              OrgHAFAccountFlow d = (OrgHAFAccountFlow)session.createQuery("from OrgHAFAccountFlowDrawing s where s.bizId=?").setBigDecimal(0, new BigDecimal(id.intValue())).uniqueResult();
              return d;
            }
          }
      );
      return s;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  //�ڳ�����ȡ��ʱ��,ɾ��aa101����ļ�¼..
  public int deleteOrgFlowToRecallPickup(final int headId){
    //������¼һ������Ϊnull��..��Ϊ����һ��������ȡ�Ĺ���..���������ȡ��ʱ����Ѿ�������aa101�ı�����
    OrgHAFAccountFlowDrawing aa101 = findByHeadIdAndStateIsOne(headId);
    getHibernateTemplate().delete(aa101);//ɾ��aa101��������¼
    int id = Integer.parseInt(aa101.getId().toString());
    return id;
  }
  /**
   * ���ĺ�...
   * ����ͷ���Id�ܹ��ҳ���aa301ͷ״̬Ϊ1��������¼ 
   */
  public OrgHAFAccountFlowDrawing findByHeadIdAndStateIsOne(final int headId){
    try{
      OrgHAFAccountFlowDrawing s = new OrgHAFAccountFlowDrawing();
      OrgHAFAccountFlowDrawing d = (OrgHAFAccountFlowDrawing)
      getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Object obj = session.createQuery("from OrgHAFAccountFlowDrawing a where a.bizId=?")
              .setBigDecimal(0, new BigDecimal(headId)).uniqueResult();
              return obj;
            }
          }
      );
      return d;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  /**
   * �����¼
   * @param OrgHAFAccountFlowDrawing
   * @return
   */
  public Serializable insert(OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing){
    Serializable id = null;
    try{    
    Validate.notNull(orgHAFAccountFlowDrawing);
    id = getHibernateTemplate().save(orgHAFAccountFlowDrawing); 
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }
}
