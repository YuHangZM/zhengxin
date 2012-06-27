package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;


/**
 * ������ȡ
 * 
 *@author ���
 *2007-6-20
 */
/**
 * ���ĺ�
 * ��������ĵ�λ������ж�aa308
 */
public class SpecialPickDAO extends HibernateDaoSupport{
  public SpecialPick findSpecialPickByOrgId(final int id){
    List list = getHibernateTemplate().executeFind(
        new HibernateCallback(){
          public Object doInHibernate(Session session) throws HibernateException, SQLException {//���ж�����¼...
           List s = session.createQuery("from SpecialPick as s where s.org.id=? and s.isPick=1").setInteger(0, id).list();
            return s;
          }
        }
       );
    if(list.isEmpty() || list == null)
      return null;
    else
      return (SpecialPick)list.get(0);
  }
  public void updateIsPick(SpecialPick sp){
    try{
      getHibernateTemplate().update(sp);
    }catch(Exception s){
      s.printStackTrace();
    }
  }
  /**
   * ��ɾ���û���ʱ���޸�������ȡ��� 1δʹ��
   */
  public void updateByIsPick(SpecialPick sp){
    try{
      getHibernateTemplate().update(sp);
    }catch(Exception s){
      s.printStackTrace();
    }
  }
  /**
   * ���ĺơ��жϴ��û��Ƿ���������ȡ
   */
  public SpecialPick isSpecialPick(final int orgId,final int empId){
    try{
      Object obj = getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Object o = session.createQuery("from SpecialPick s where s.org.id=? and s.empId=? and s.isPick=1")
              .setInteger(0, orgId).setInteger(1, empId).setMaxResults(1).uniqueResult();
              return o;
            }
          }
      );
      return (SpecialPick)obj;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  /**
   * ���ĺơ����ݵ�λ��ź�ְ����Ų���AA308��
   */
  public SpecialPick  querySpecialPick(final int orgId,final int empId){
    try{
      Object obj = getHibernateTemplate().execute(
          new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
              Object o = session.createQuery("from SpecialPick s where s.org.id=? and s.empId=?")
              .setInteger(0, orgId).setInteger(1, empId).setMaxResults(1).uniqueResult();
              return o;
            }
          }
      );
      return (SpecialPick)obj;
    }catch(Exception s){
      s.printStackTrace();
    }
    return null;
  }
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public SpecialPick queryById(Serializable id) {  
    Validate.notNull(id);
    return  (SpecialPick) getHibernateTemplate().get(SpecialPick.class,id);    
  }
  /**
   * �����¼
   * @param specialPick
   * @return
   */
  public Serializable insert(SpecialPick specialPick){
    Serializable id = null;
    try{    
    Validate.notNull(specialPick);
    id = getHibernateTemplate().save(specialPick);  
    }catch(Exception e){
      e.printStackTrace();
    }
    return id.toString();
  }
/**
 * ����ְ����Ų�ѯ
 */
  public SpecialPick queryByEmpIdSL(final String id){
    Object obj = null;

    try{
    obj = getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session) throws HibernateException, SQLException {
            String hql ="from SpecialPick s ";
            Vector parameters = new Vector(); 
            String criterion = "";
            if (id != null) {
              criterion += "s.empId = ? and ";
              parameters.add(new Integer(id));
            }
            if (criterion.length() != 0) 
              criterion = "where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
           Query query0 = session.createQuery(hql);
           for (int i = 0; i < parameters.size(); i++) {
             query0.setParameter(i, parameters.get(i));
           }
           return  query0.uniqueResult();
           
           
          }
        }
       );
    }catch(Exception e){
      e.printStackTrace();
    }
    return (SpecialPick)obj;
    
  }
  /**
   * ����orginfo���officecode��bb101���е�officename
   */
  public String findOfficeNameByOrgInfoOfficeCode(final String id){
    OrganizationUnit organizationUnit=null;
    organizationUnit = (OrganizationUnit)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session) throws HibernateException, SQLException {
           String hql = "from OrganizationUnit organizationUnit where organizationUnit.id= ?";
           Query query = session.createQuery(hql);
           query.setParameter(0, id);
           return query.uniqueResult();
        }
       }
       );
    return organizationUnit.getName();
  }
  /**
   * ����orginfo���collectionBankId��bb105���е�collBankname
   */
  public String findCollBanknameByOrgInfoCollectionBankId(final String id){
    CollBank collBank=null;
    collBank = (CollBank)getHibernateTemplate().execute(
        new HibernateCallback(){
          public Object doInHibernate(Session session) throws HibernateException, SQLException {
           String hql = "from CollBank collBank where collBank.collBankId= ?";
           Query query = session.createQuery(hql);
           query.setParameter(0, new Integer(id));
           return query.uniqueResult();
        }
       }
       );
    return collBank.getCollBankName();
  }
  /**
   * ��ѯ������ȡά���б�
   */
  public List queryByCriterionsSL(final String id, final String name,
      final String officecode,final String collectionBank,final String operateTime1,final String operateTime2,
      final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo info ) {
    List list = null ;
    try{
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from SpecialPick specialPick ";
        Vector parameters = new Vector();   
        String criterion = "";
        if (id != null&&!id.equals("")) {
          criterion += "To_Char(specialPick.org.id) like ? and ";
          parameters.add("%"+id+"%");
        }
        if (name != null&&!name.equals("")) {
          criterion += "specialPick.org.orgInfo.name like ? escape '/' and ";
          parameters.add("%" + name + "%");
        }
        
        if(officecode != null&&!officecode.equals("")) {
          criterion+="specialPick.org.orgInfo.officecode = ?  and ";
          parameters.add(officecode);
        }
        if(collectionBank != null&&!collectionBank.equals("")) {
          criterion+="specialPick.org.orgInfo.collectionBankId = ? and ";
          parameters.add(collectionBank);
        }
        
        if (operateTime1 != null&&!operateTime1.equals("") && operateTime2 != null&&!operateTime2.equals("")) {//�п�ʼ���ڽ�������
          criterion += " (to_char(specialPick.operateTime,'YYYYMMDD')  between ? and ? ) and ";
          parameters.add(operateTime1);
          parameters.add(operateTime2);
        }
        
        if (operateTime1 != null&&!operateTime1.equals("") && (operateTime2 == null || operateTime2.equals(""))) {//�п�ʼ�����޽�������
          criterion += " to_char(specialPick.operateTime,'YYYYMMDD') >= ? and ";
          parameters.add(operateTime1);
        }
        
        if (operateTime2 != null&&!operateTime2.equals("") && (operateTime1 == null || operateTime1.equals(""))) {//�޿�ʼ�����н�������
          criterion += " to_char(specialPick.operateTime,'YYYYMMDD') <= ? and ";
          parameters.add(operateTime2);
        }
        
        if (criterion.length() != 0){
          criterion = " where specialPick.org.id "+info.getGjjSecurityHqlSQL()+" and "
              + criterion.substring(0, criterion.lastIndexOf("and"));
        }else{
          criterion = " where specialPick.org.id "+info.getGjjSecurityHqlSQL();
        }
        String ob = orderBy;
        if (ob == null)
          ob = "specialPick.id";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + "order by " + ob + " " + od;
        session.clear();
        Query query = session.createQuery(hql);
        
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);        
        return query.list();

        
      }
    });
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  /**
   * ������ȡ�б����
   */
  public List queryCountByCriterionsSL(final String id, final String name,
      final String officecode,final String collectionBank,final String operateTime1,final String operateTime2,final String orderBy, final String order, final int start,
      final int pageSize,final SecurityInfo info) {
    List list = new ArrayList();
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
           public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from SpecialPick specialPick ";
            Vector parameters = new Vector();
            String criterion = "";
            if (id != null&&!id.equals("")) {
              criterion += "To_Char(specialPick.org.id) like ? and ";
              parameters.add("%"+id+"%");
            }

            if (name != null&&!name.equals("")) {
              criterion += "specialPick.org.orgInfo.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }
            
            if(officecode != null&&!officecode.equals("")){
              criterion+="specialPick.org.orgInfo.officecode =? and ";
              parameters.add (officecode);
            }
            if(collectionBank != null&&!collectionBank.equals("")){
              criterion+="specialPick.org.orgInfo.collectionBankId = ? and ";
              parameters.add(collectionBank);
            }
           
            if (operateTime1 != null&&!operateTime1.equals("") && operateTime2 != null&&!operateTime2.equals("")) {//�п�ʼ���ڽ�������
              criterion += " (to_char(specialPick.operateTime,'YYYYMMDD')  between ? and ? ) and ";
              parameters.add(operateTime1);
              parameters.add(operateTime2);
            }
            
            if (operateTime1 != null&&!operateTime1.equals("") && (operateTime2 == null || operateTime2.equals(""))) {//�п�ʼ�����޽�������
              criterion += " to_char(specialPick.operateTime,'YYYYMMDD') >= ? and ";
              parameters.add(operateTime1);
            }
            
            if (operateTime2 != null&&!operateTime2.equals("") && (operateTime1 == null || operateTime1.equals(""))) {//�޿�ʼ�����н�������
              criterion += " to_char(specialPick.operateTime,'YYYYMMDD') <= ? and ";
              parameters.add(operateTime2);
            }
            if (criterion.length() != 0){
              criterion = " where specialPick.org.id "+info.getGjjSecurityHqlSQL()+" and "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            }else{
              criterion = " where specialPick.org.id "+info.getGjjSecurityHqlSQL();
            }

            String ob = orderBy;
            if (ob == null)
              ob = "specialPick.id";

            String od = order;
            if (od == null)
              od = "DESC";

            hql = hql + criterion + "order by " + ob + " " + od;
            session.clear();
            Query query = session.createQuery(hql);
            
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            return query.list();
          }
        });
    }catch(Exception e){
      e.printStackTrace();
}
    return list;
  }
  public void delete(SpecialPick specialPick){
    Validate.notNull(specialPick);
    getHibernateTemplate().delete(specialPick);
  }
  //�ж�������ȡʱ�Ƿ���δ���˵�ת��
  public List queryTranOutHeadByOrgid(final String orgid) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select tranOutHead from TranOutHead tranOutHead where tranOutHead.tranStatus not in (5) and tranOutHead.tranOutOrg.id = ?  ";
              Vector parameters = new Vector();
              parameters.add(new Integer(orgid));
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.list();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
//�ж�������ȡʱ�Ƿ���δ���˵���ȡ
  public List isClearAccount(final String orgid,final String empid) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select  aa306.id from AA306 aa306,AA307 aa307 " +
                  "where aa306.org_id=? " +
                  "and aa307.emp_id=? " +
                  "and aa306.pick_satatus in (3,4) " +
                  "and aa306.id=aa307.pickhead_id";
              Query query = session.createSQLQuery(hql);
              query.setParameter(0, new Integer(orgid));
              query.setParameter(1, new Integer(empid));
              return query.list();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
