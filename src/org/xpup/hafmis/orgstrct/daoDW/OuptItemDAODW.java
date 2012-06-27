package org.xpup.hafmis.orgstrct.daoDW;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.enums.OrderEnum;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.exception.EntityNotFoundException;
import org.xpup.common.exception.IllegalDuplicationException;
import org.xpup.hafmis.orgstrct.domain.OuptItem;
import org.xpup.hafmis.orgstrct.domain.enums.PropertyTypeEnum;
import org.xpup.hafmis.orgstrct.domain.enums.ValueTypeEnum;

public class OuptItemDAODW extends HibernateDaoSupport {

  public OuptItem queryById(Serializable id) {
    Validate.notNull(id, "����id����Ϊ��");

    return (OuptItem) getHibernateTemplate().get(OuptItem.class, id);
  }

  public List queryUnusedOuptItems(final Serializable orgUnitId,
      final PropertyTypeEnum type) {

    List unuseds = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        Query query = session.getNamedQuery("unusedOuptItems");
        query.setParameter("orgUnitId1", orgUnitId);
        query.setParameter("orgUnitId2", orgUnitId);
        query.setParameter("type", new Integer(type.getValue()));
        return query.list();
      }
    });
    return unuseds;
  }

  public List queryByCriterions(final Serializable ouptId, final String name,
      final ValueTypeEnum valueType, final PropertyTypeEnum type,
      final Boolean nullable, final String orderBy, final OrderEnum order,
      final int start, final int pageSize) {
    Validate.notNull(orderBy, "����orderBy����Ϊ�գ�");
    Validate.notNull(order, "����order����Ϊ�գ�");
    Validate
        .isTrue(
            orderBy
                .matches("ouptItem\\.(id|name|valueType|type|nullable|innerName)$"),
            "����orderBy("
                + orderBy
                + ")������Ҫ����������ouptItem.id, ouptItem.name, ouptItem.valueType, ouptItem.type, ouptItem.innerName,ouptItem.nullable�е�һ����");

    List oupts = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from OuptItem ouptItem ";
        Vector parameters = new Vector();
        String criterion = "";

        if (ouptId != null) {
          criterion += "ouptItem.orgUnitPropTemplate.id = ? and ";
          parameters.add(ouptId);
        }

        if (name != null) {
          criterion += "ouptItem.name like ? escape '/' and ";
          parameters.add("%" + name + "%");
        }

        if (valueType != null) {
          criterion += "ouptItem.valueType = ? and ";
          parameters.add(new Integer(valueType.getValue()));
        }

        if (type != null) {
          criterion += "ouptItem.type = ? and ";
          parameters.add(new Integer(type.getValue()));
        }

        if (nullable != null) {
          criterion += "ouptItem.nullable = ? and ";
          parameters.add(nullable);
        }

        if (criterion.length() != 0)
          criterion = "where "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        hql = hql + criterion + "order by " + orderBy + " " + order.getName();

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
    return oupts;
  }

  public int queryCountByCriterions(final Serializable ouptId,
      final String name, final ValueTypeEnum valueType,
      final PropertyTypeEnum type, final Boolean nullable) {

    Integer count = (Integer) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select count(ouptItem.id) from OuptItem ouptItem ";
            Vector parameters = new Vector();
            String criterion = "";

            if (ouptId != null) {
              criterion += "ouptItem.orgUnitPropTemplate.id = ? and ";
              parameters.add(ouptId);
            }

            if (name != null) {
              criterion += "ouptItem.name like ? escape '/' and ";
              parameters.add("%" + name + "%");
            }

            if (valueType != null) {
              criterion += "ouptItem.valueType = ? and ";
              parameters.add(new Integer(valueType.getValue()));
            }

            if (type != null) {
              criterion += "ouptItem.type = ? and ";
              parameters.add(new Integer(type.getValue()));
            }

            if (nullable != null) {
              criterion += "ouptItem.nullable = ? and ";
              parameters.add(nullable);
            }

            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));

            hql = hql + criterion;

            Query query = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }

            return query.uniqueResult();
          }
        });
    return count.intValue();
  }

  public Serializable insert(OuptItem ouptItem) throws BusinessException {
    Validate.notNull(ouptItem, "����ouptItem����Ϊ�գ�");
    Validate.notNull(ouptItem.getName(), "ouptItem.getName()����Ϊ�գ�");
    Validate.notNull(ouptItem.getInnerName(), "ouptItem.getInnerName()����Ϊ�գ�");
    Validate.notNull(ouptItem.getOrgUnitPropTemplate(),
        "ouptItem.getOrgUnitPropTemplate()����Ϊ�գ�");

    int count = getHibernateTemplate()
        .find(
            "from OuptItem ouptItem where (lower(ouptItem.name) = ? or lower(ouptItem.innerName) = ?) and ouptItem.orgUnitPropTemplate.id = ?",
            new Object[] { ouptItem.getName().toLowerCase().trim(),
                ouptItem.getInnerName(),
                ouptItem.getOrgUnitPropTemplate().getId() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("ģ�������ƻ��ڲ����Ƴ����ظ�����ʹ���������ƣ�");
    }
    return getHibernateTemplate().save(ouptItem);
  }

  public Serializable insertDW(Serializable id,OuptItem ouptItem) throws BusinessException {
    Validate.notNull(ouptItem, "����ouptItem����Ϊ�գ�");
    Validate.notNull(ouptItem.getName(),"ouptItem.getName()����Ϊ�գ�");
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    PreparedStatement pre=null;
    String hql="insert into BB104 t (t.id,t.name,t.value,t.value_type,t.type,t.template_id,t.nullable,t.inner_name,t.enum_value) "
      +" values (?,?,?,?,?,?,?,?,?) ";  
    int length=0;
    try {
      pre=conn.prepareStatement(hql.toString());
      pre.setString(1, id.toString()); 
      pre.setString(2, ouptItem.getName());    
      pre.setString(3, ouptItem.getValue());
      length=ouptItem.getValueType().toString().length();
      pre.setInt(4, Integer.parseInt(ouptItem.getValueType().toString().substring(length-2,length).substring(0,1))); 
      length=ouptItem.getType().toString().length();
      pre.setInt(5, Integer.parseInt(ouptItem.getType().toString().substring(length-2,length).substring(0,1)));  
      pre.setString(6, ouptItem.getOrgUnitPropTemplate().getId().toString()); 
      if(ouptItem.isNullable()==false){
        pre.setInt(7, 0);     
      }else{
        pre.setInt(7, 1);     
      }
      pre.setString(8, ouptItem.getInnerName());     
      pre.setString(9, ouptItem.getEnumValue());   
      pre.executeUpdate(); 
      } catch (SQLException e) {
        throw new BusinessException("���ݲ�������");
      }
      return id;
  }
  
  public void deleteById(Serializable id) throws BusinessException {
    Validate.notNull(id, "����id����Ϊ��");

    OuptItem ouptItem = (OuptItem) getHibernateTemplate().get(OuptItem.class,
        id);
    if (ouptItem == null) {
      throw new EntityNotFoundException("ģ������ڣ����Ѿ���ɾ��!");
    }
    getHibernateTemplate().delete(ouptItem);
  }

  public void checkBeforeCreate(OuptItem ouptItem) throws BusinessException {
    int count = getHibernateTemplate()
    .find(
        "from OuptItem ouptItem where (lower(ouptItem.name) = ? or lower(ouptItem.innerName) = ?) and ouptItem.orgUnitPropTemplate.id = ?",
        new Object[] { ouptItem.getName().toLowerCase().trim(),
            ouptItem.getInnerName(),
            ouptItem.getOrgUnitPropTemplate().getId() }).size();
    if (count != 0) {
      throw new IllegalDuplicationException("ģ�������ƻ��ڲ����Ƴ����ظ�����ʹ���������ƣ�");
    }
  }
  
  public void checkBeforeUpdate(OuptItem ouptItem) throws BusinessException {
    int count = getHibernateTemplate()
        .find(
            "from OuptItem ouptItem where (lower(ouptItem.name) = ? or lower(ouptItem.innerName) = ?) and ouptItem.orgUnitPropTemplate.id = ? and ouptItem.id <> ?",
            new Object[] { ouptItem.getName().toLowerCase().trim(),
                ouptItem.getInnerName(),
                ouptItem.getOrgUnitPropTemplate().getId(), ouptItem.getId() })
        .size();
    if (count != 0) {
      throw new IllegalDuplicationException("ģ�������ƻ��ڲ����Ƴ����ظ�����ʹ���������ƣ�");
    }
  }
  
  public void updateDW(OuptItem ouptItem) throws BusinessException {
    Validate.notNull(ouptItem, "����ouptItem����Ϊ�գ�");
    Connection conn=getHibernateTemplate().getSessionFactory().openSession().connection();
    PreparedStatement pre=null;
    String hql="update bb104 t set t.name=?,t.value=?,t.value_type=?,t.type=?,t.template_id=?,t.nullable=?,t.inner_name=?,t.enum_value=? where t.id='"+ouptItem.getId().toString()+"'";     
    int length=0;
    try {
      pre=conn.prepareStatement(hql.toString());
      pre.setString(1, ouptItem.getName());    
      pre.setString(2, ouptItem.getValue());
      length=ouptItem.getValueType().toString().length();
      pre.setInt(3, Integer.parseInt(ouptItem.getValueType().toString().substring(length-2,length).substring(0,1))); 
      length=ouptItem.getType().toString().length();
      pre.setInt(4, Integer.parseInt(ouptItem.getType().toString().substring(length-2,length).substring(0,1)));  
      pre.setString(5, ouptItem.getOrgUnitPropTemplate().getId().toString()); 
      if(ouptItem.isNullable()==false){
        pre.setInt(6, 0);     
      }else{
        pre.setInt(6, 1);     
      }
      pre.setString(7, ouptItem.getInnerName());     
      pre.setString(8, ouptItem.getEnumValue());   
      pre.executeUpdate(); 
      } catch (SQLException e) {
        throw new BusinessException("���ݲ�������");
      }
  }

}
