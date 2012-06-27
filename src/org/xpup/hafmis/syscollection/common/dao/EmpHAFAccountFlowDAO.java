package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.List;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.MonthpayTbWindowDto;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.dto.EmpaccountinfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowTotalDTO;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;


public class EmpHAFAccountFlowDAO extends HibernateDaoSupport{
 /***
  *ȫ��ɾ�� 
  * ��param list
  */
  public void deleteEmpHAFAccountFlowAll_sy(List list){
    getHibernateTemplate().deleteAll(list);
  }
  public void deleteEmpHAFAccountFlowAll(final Integer orgHAFAccountFlowid){
    try{
  getHibernateTemplate().execute(
      new HibernateCallback() {
        public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
           String sql="delete EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.id=?";          
           session.createQuery(sql).setInteger(0, orgHAFAccountFlowid.intValue()).executeUpdate();
            return null;
        }
      }  
  );
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public EmpHAFAccountFlow queryById(Integer id) {
    Validate.notNull(id);
    return  (EmpHAFAccountFlow) getHibernateTemplate().get(EmpHAFAccountFlow.class,id);    
  }
  /** ����
   * ��������ɾ��
   * @param id
   * @return
   */
  public void deleteById(String id) {
    try {
      Validate.notNull(id);
      EmpHAFAccountFlow empHAFAccountFlow = queryById(new Integer(id));
      getHibernateTemplate().delete(empHAFAccountFlow);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /** ����
   * ��Ա����Ϣ����
   * @param EmpHAFAccountFlowList
   * @return
   */
  public EmpHAFAccountFlow queryEmpHAFAccountFlowAddEmp(EmpHAFAccountFlow empHAFAccountFlow,Emp emp) {
    empHAFAccountFlow.setEmp(emp);
    return null;
  }
  /** ����
   * ������ˮ�Ų�ѯ����ӦԱ��
   * @param OrgHAFAccountFlow id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryEmpHAFAccountFlowListByCriterions(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from EmpHAFAccountFlow empHAFAccountFlow  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += "empHAFAccountFlow.orgHAFAccountFlow.id = ?  and ";
            //    parameters.add(new Integer(id));
                parameters.add(new Integer(id));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              String ob = orderBy;
              if (ob == null)
                ob = " empHAFAccountFlow.empId ";
              String od = order;
              if (od == null)
                od = "DESC";
              
              hql = hql + criterion + "order by " + ob + " " + order;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              query.setFirstResult(start);
              if (pageSize > 0)
                query.setMaxResults(pageSize);        
              return query.list();
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /** ����
   * ������ˮ�Ų�ѯ����ӦԱ�������У�
   * @param OrgHAFAccountFlow id
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @return List
   */
  public List queryEmpHAFAccountFlowListAllByCriterions(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize) throws NumberFormatException, Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = " from EmpHAFAccountFlow empHAFAccountFlow  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (id != null&&!id.equals("")) {
                criterion += "empHAFAccountFlow.orgHAFAccountFlow.id = ?  and ";
            //    parameters.add(new Integer(id));
                parameters.add(new Integer(id));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
              String ob = orderBy;
              if (ob == null)
                ob = " empHAFAccountFlow.empId ";
              String od = order;
              if (od == null)
                od = "DESC";
              
              hql = hql + criterion ;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }    
              return query.list();
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  
  
  /** ����
   * ����������ѯ��¼����¼��
   * @param id
   * @param name
   * @return
   */
  public int queryDemoCountByCriterions(final String id) {
    int count=0;
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "from EmpHAFAccountFlow empHAFAccountFlow  ";
              Vector parameters = new Vector();
              String criterion = "";


              if (id != null&&!id.equals("")) {
                criterion += "empHAFAccountFlow.orgHAFAccountFlow.id = ?  and ";
                parameters.add(new Integer(id));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
           
              hql = hql + criterion ;
              session.clear();
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.list();
            }
          }

      );
      count=list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
 }
  
  
  /** ����
   * �����¼
   * @param empHAFAccountFlow
   * @return
   */
  public Serializable insert(EmpHAFAccountFlow empHAFAccountFlow){
    Serializable id = null;
    try{    
    Validate.notNull(empHAFAccountFlow);
    id = getHibernateTemplate().save(empHAFAccountFlow);
    }catch(Exception e){
      e.printStackTrace();
    }
    return id;
  }

  public void deleteAllEmpFlow(String headId){
      List list = FindEmpHAFAccountWZQ(headId);
      getHibernateTemplate().deleteAll(list);
  }

  /*
   * ��־ǿ
   * 2007-07-21
   */
  public List FindEmpHAFAccountWZQ(final String org_Flow_id) {

    List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session)
      throws HibernateException, SQLException{
        session.clear();
        String hql = " from EmpHAFAccountFlow ehf where ehf.orgHAFAccountFlow.id = ?" ;
        Query query = session.createQuery(hql);
         query.setParameter(0, new Integer(org_Flow_id));
        return query.list();
      }
    });
    return list;
  }
  
  
  
  
  

  
  /**
   * �����
   * ����org_flow_id��ѯ
   * @param id
   * @return
   */
  public List queryEmpHAFAccountFlowListByOrgFlowId(final String id){
    List list=new ArrayList();
    try {
      list = (List)getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select empHAFAccountFlow from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.id = ?";
              Vector parameters = new Vector();
              parameters.add(new Integer(id));
              Query query = session.createQuery(hql);
              query.setParameter(0, parameters.get(0));

              return query.list();
            }
          });
      }catch(Exception e){
        e.printStackTrace();
      }
      return list;
  }
  
 
  
/*
 * ��ǿ
 */
  public List queryEmpHafByCriterionsWZQ(final String pkid) {
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "from EmpHAFAccountFlow empHAFAccountFlow  ";
              Vector parameters = new Vector();
              String criterion = "";

              if (pkid != null&&!pkid.equals("")) {
                criterion += "empHAFAccountFlow.orgHAFAccountFlow.id = ?  and ";
                parameters.add(new Integer(pkid));
              }
              if (criterion.length() != 0)
                criterion = "where "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
           
              hql = hql + criterion ;
              session.clear();
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.list();
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
 }

  /**
   * ���
   * ��ѯְ��ҵ����ˮ
   * @param orgid
   * @param orgname
   * @param empid
   * @param empname
   * @param noteNum
   * @param docNum
   * @param opStatus
   * @param opType
   * @param opDate
   * @param opMoney
   * @param opDirection
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryEmpHAFAccountFlowListLJ(final Serializable orgid,
      final String orgname, final Serializable empid, final String empname,
      final String noteNum, final String docNum, final String opStatus,
      final String opType, final String inOpDate, final String opDate,
      final String inOpMoney, final String opMoney, final String opDirection,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page, final SecurityInfo securityInfo,
      final String orgbusinessflowHeadID) throws NumberFormatException,
      Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.org.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (orgid != null && !orgid.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.id = ? and ";
            parameters.add(new Integer(orgid.toString()));
          }
          if (orgname != null && !orgname.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
            parameters.add("%" + orgname + "%");
          }
          if (empid != null && !empid.equals("")) {
            criterion += " empHAFAccountFlow.empId = ? and ";
            parameters.add(new Integer(empid.toString()));
          }
          if (empname != null && !empname.equals("")) {
            criterion += " empHAFAccountFlow. empName like  ? escape '/'  and ";
            parameters.add("%" + empname + "%");
          }
          if (noteNum != null && !noteNum.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.noteNum like ?  and ";
            parameters.add("%" + noteNum + "%");
          }
          if (docNum != null && !docNum.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.docNum like ?  escape '/'  and ";
            parameters.add("%" + docNum + "%");
          }
          if (opStatus != null && !opStatus.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.bizStatus = ?  and ";
            parameters.add(new BigDecimal(opStatus));
          }
          if (opType != null && !opType.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.biz_Type = ?  and ";
            parameters.add(opType);
          }
          if (inOpDate != null && !inOpDate.equals("") && opDate != null
              && !opDate.equals("")) {
            criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ?  and  ? ) and";
            parameters.add(inOpDate);
            parameters.add(opDate);
          } else if (inOpDate != null && !inOpDate.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
            parameters.add(inOpDate);
          } else if (opDate != null && !opDate.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
            parameters.add(opDate);
          }
          if (inOpMoney != null && !inOpMoney.equals("") && opMoney != null
              && !opMoney.equals("")) {
            criterion += " ((empHAFAccountFlow.debit+empHAFAccountFlow.credit) between ?  and  ? ) and";
            parameters.add(new BigDecimal(inOpMoney));
            parameters.add(new BigDecimal(opMoney));
          } else if (inOpMoney != null && !inOpMoney.equals("")) {
            criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
            parameters.add(new BigDecimal(inOpMoney));

          } else if (opMoney != null && !opMoney.equals("")) {
            criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
            parameters.add(new BigDecimal(opMoney));

          }
          if (opDirection != null && !opDirection.equals("")) {
            if (opDirection.equals("0")) {
              criterion += " empHAFAccountFlow.debit > 0 and ";
            } else if (opDirection.equals("1")) {
              criterion += "  empHAFAccountFlow.credit > 0  and ";
            }
          }
          if (orgbusinessflowHeadID != null
              && !orgbusinessflowHeadID.equals("")) {
            criterion += " empHAFAccountFlow.orgHAFAccountFlow.id = ?  and";
            parameters.add(new Integer(orgbusinessflowHeadID));

          }
          String ob = orderBy;
          if (ob == null)
            ob = " empHAFAccountFlow.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + "order by " + ob + " " + od;

          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          List queryList = query.list();

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList = query.list();
          }
          return queryList;
        }
      });

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  
  /**
   * ��� ��ѯְ����ˮ��¼��
   * 
   * @param orgid
   * @param orgname
   * @param empid
   * @param empname
   * @param noteNum
   * @param docNum
   * @param opStatus
   * @param opType
   * @param opDate
   * @param opMoney
   * @param opDirection
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public int queryEmpHAFAccountFlowCountLJ(final Serializable orgid,final String orgname,final Serializable empid,final String empname,
      final String noteNum,final String docNum,final String opStatus,final String opType,final String inOpDate,final String opDate,final String inOpMoney,final String opMoney,final String opDirection,
      final SecurityInfo securityInfo,final String orgbusinessflowHeadID) throws NumberFormatException, Exception {
    int count=0;
    Integer iCount = null;
    try {

      iCount = (Integer)getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql ="select count(empHAFAccountFlow.id) from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.org.id "+securityInfo.getGjjSecurityHqlSQL(); 
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null&&!orgid.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.id = ? and ";
                parameters.add(new Integer(orgid.toString()));
              }
              if (orgname != null&&!orgname.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
                parameters.add("%" + orgname + "%");
              }
              if (empid != null&&!empid.equals("")) {
                criterion += " empHAFAccountFlow.empId = ? and ";
                parameters.add(new Integer(empid.toString()));
              }
              if (empname != null&&!empname.equals("")) {
                criterion += " empHAFAccountFlow. empName like  ? escape '/'  and ";
                parameters.add("%" + empname + "%");
              }
              if (noteNum != null&&!noteNum.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.noteNum like ?  and ";
                parameters.add("%" + noteNum + "%");
              }
              if (docNum != null&&!docNum.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.docNum like ?  escape '/'  and ";
                parameters.add("%" + docNum + "%");
              }
              if (opStatus != null&&!opStatus.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.bizStatus = ?  and ";
                parameters.add(new BigDecimal(opStatus));
              }
              if (opType != null&&!opType.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.biz_Type = ?  and ";
                parameters.add(opType);
              }
              if (inOpDate != null && !inOpDate.equals("") && opDate != null && !opDate.equals("")) {
                criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ?  and  ? ) and";
                parameters.add(inOpDate);
                parameters.add(opDate);
              }else if(inOpDate != null && !inOpDate.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
                parameters.add(inOpDate);
              }else if(opDate != null && !opDate.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
                parameters.add(opDate);                
              }
              if (inOpMoney != null && !inOpMoney.equals("") && opMoney != null && !opMoney.equals("")) {
                criterion += " ((empHAFAccountFlow.debit+empHAFAccountFlow.credit) between ?  and  ? ) and";
                parameters.add(new BigDecimal(inOpMoney));
                parameters.add(new BigDecimal(opMoney));
              }else if(inOpMoney != null && !inOpMoney.equals("")){
                criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
                parameters.add(new BigDecimal(inOpMoney));
                
              }else if(opMoney != null && !opMoney.equals("")){
                criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
                parameters.add(new BigDecimal(opMoney));
                
              }
              if (opDirection != null&&!opDirection.equals("")) {
                if(opDirection.equals("0")){
                  criterion += " empHAFAccountFlow.debit > 0 and ";        
                }else if(opDirection.equals("1")){
                  criterion += "  empHAFAccountFlow.credit > 0  and ";        
                }
              } 
              if(orgbusinessflowHeadID != null && !orgbusinessflowHeadID.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.id = ?  and";
                parameters.add(new Integer(orgbusinessflowHeadID));
                
              }
    
              if (criterion.length() != 0)
                criterion=" and  "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion  ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              return query.uniqueResult();
            }
          }
      );
      count=iCount.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return count;
  }
  /**
   * ���
   * ��ѯ�ϼ�
   * @param orgid
   * @param orgname
   * @param empid
   * @param empname
   * @param noteNum
   * @param docNum
   * @param opStatus
   * @param opType
   * @param inOpDate
   * @param opDate
   * @param inOpMoney
   * @param opMoney
   * @param opDirection
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */ 
  public List queryEmpHAFAccountFlowTotalLJ(final Serializable orgid,final String orgname,final Serializable empid,final String empname,
      final String noteNum,final String docNum,final String opStatus,final String opType,final String inOpDate,final String opDate,final String inOpMoney,final String opMoney,final String opDirection,
      final SecurityInfo securityInfo,final String orgbusinessflowHeadID) throws NumberFormatException, Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              List temp_list=new ArrayList();
              String hql ="select count(empHAFAccountFlow.id),sum(empHAFAccountFlow.debit+empHAFAccountFlow.credit),sum(empHAFAccountFlow.interest)" +
                  " from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.org.id "+securityInfo.getGjjSecurityHqlSQL(); 
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null&&!orgid.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              if (orgname != null&&!orgname.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
                parameters.add("%" + orgname + "%");
              }
              if (empid != null&&!empid.equals("")) {
                criterion += " empHAFAccountFlow.empId = ? and ";
                parameters.add(new Integer(empid.toString()));
              }
              if (empname != null&&!empname.equals("")) {
                criterion += " empHAFAccountFlow. empName like  ? escape '/'  and ";
                parameters.add("%" + empname + "%");
              }
              if (noteNum != null&&!noteNum.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.noteNum like ?  and ";
                parameters.add("%" + noteNum + "%");
              }
              if (docNum != null&&!docNum.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.docNum like ?  escape '/'  and ";
                parameters.add("%" + docNum + "%");
              }
              if (opStatus != null&&!opStatus.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.bizStatus = ?  and ";
                parameters.add(new BigDecimal(opStatus));
              }
              if (opType != null&&!opType.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.biz_Type = ?  and ";
                parameters.add(opType);
              }
              if (inOpDate != null && !inOpDate.equals("") && opDate != null && !opDate.equals("")) {
                criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ?  and  ? ) and";
                parameters.add(inOpDate);
                parameters.add(opDate);
              }else if(inOpDate != null && !inOpDate.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
                parameters.add(inOpDate);
              }else if(opDate != null && !opDate.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
                parameters.add(opDate);                
              }
              if (inOpMoney != null && !inOpMoney.equals("") && opMoney != null && !opMoney.equals("")) {
                criterion += " ((empHAFAccountFlow.debit+empHAFAccountFlow.credit) between ?  and  ? ) and";
                parameters.add(new BigDecimal(inOpMoney));
                parameters.add(new BigDecimal(opMoney));
              }else if(inOpMoney != null && !inOpMoney.equals("")){
                criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
                parameters.add(new BigDecimal(inOpMoney));
                
              }else if(opMoney != null && !opMoney.equals("")){
                criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
                parameters.add(new BigDecimal(opMoney));
                
              }
              if (opDirection != null&&!opDirection.equals("")) {
                if(opDirection.equals("0")){
                  criterion += " empHAFAccountFlow.debit > 0 and ";        
                }else if(opDirection.equals("1")){
                  criterion += "  empHAFAccountFlow.credit > 0  and ";        
                }
              } 
              if(orgbusinessflowHeadID != null && !orgbusinessflowHeadID.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.id = ?  and";
                parameters.add(new Integer(orgbusinessflowHeadID));
                
              }
    
              if (criterion.length() != 0)
                criterion=" and  "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion  ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              Iterator it=query.iterate();
              Object obj[]=null;
              while(it.hasNext()){
                obj=(Object[])it.next();
                EmpOperationFlowTotalDTO empOperationFlowTotalDTO=new EmpOperationFlowTotalDTO();
                empOperationFlowTotalDTO.setCounts(obj[0].toString());
                if(empOperationFlowTotalDTO.getCounts().equals("0")){
                  empOperationFlowTotalDTO.setSumMoney("0.00");
                  empOperationFlowTotalDTO.setSumInterest("0.00");                  
                }else{
                  empOperationFlowTotalDTO.setSumMoney(obj[1].toString());
                  empOperationFlowTotalDTO.setSumInterest(obj[2].toString());
                }
                temp_list.add(empOperationFlowTotalDTO);
              }
              return temp_list;
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  /**
   * ���
   * ��ӡ
   * @param orgid
   * @param orgname
   * @param empid
   * @param empname
   * @param noteNum
   * @param docNum
   * @param opStatus
   * @param opType
   * @param inOpDate
   * @param opDate
   * @param inOpMoney
   * @param opMoney
   * @param opDirection
   * @param orderBy
   * @param order
   * @param securityInfo
   * @return
   * @throws NumberFormatException
   * @throws Exception
   */
  public List queryEmpHAFAccountFlowListPrint(final Serializable orgid,final String orgname,final Serializable empid,final String empname,
      final String noteNum,final String docNum,final String opStatus,final String opType,final String inOpDate,final String opDate,final String inOpMoney,final String opMoney,final String opDirection,
      final String orderBy, final String order,final SecurityInfo securityInfo,final String orgbusinessflowHeadID) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));

      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql =" select empHAFAccountFlow from EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.org.id "+securityInfo.getGjjSecurityHqlSQL(); 
              Vector parameters = new Vector();
              String criterion = "";
              if (orgid != null&&!orgid.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
                parameters.add(new Integer(orgid.toString()));
              }
              if (orgname != null&&!orgname.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name like  ? escape '/'  and ";
                parameters.add("%"+orgname+"%");
              }
              if (empid != null&&!empid.equals("")) {
                criterion += " empHAFAccountFlow.empId = ?  and ";
                parameters.add(new Integer(empid.toString()));
              }
              if (empname != null&&!empname.equals("")) {
                criterion += " empHAFAccountFlow. empName like  ? escape '/'  and ";
                parameters.add("%"+empname+"%");
              }
              if (noteNum != null&&!noteNum.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.noteNum = ?  and ";
                parameters.add(noteNum);
              }
              if (docNum != null&&!docNum.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.docNum = ?  and ";
                parameters.add(docNum);
              }
              if (opStatus != null&&!opStatus.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.bizStatus = ?  and ";
                parameters.add(new BigDecimal(opStatus));
              }
              if (opType != null&&!opType.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.biz_Type = ?  and ";
                parameters.add(opType);
              }
              if (inOpDate != null && !inOpDate.equals("") && opDate != null && !opDate.equals("")) {
                criterion += " (empHAFAccountFlow.orgHAFAccountFlow.settDate between ?  and  ? ) and";
                parameters.add(inOpDate);
                parameters.add(opDate);
              }else if(inOpDate != null && !inOpDate.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
                parameters.add(inOpDate);
              }else if(opDate != null && !opDate.equals("")){
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.settDate = ?  and";
                parameters.add(opDate);                
              }
              if (inOpMoney != null && !inOpMoney.equals("") && opMoney != null && !opMoney.equals("")) {
                criterion += " ((empHAFAccountFlow.debit+empHAFAccountFlow.credit) between ?  and  ? ) and";
                parameters.add(new BigDecimal(inOpMoney));
                parameters.add(new BigDecimal(opMoney));
              }else if(inOpMoney != null && !inOpMoney.equals("")){
                criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
                parameters.add(new BigDecimal(inOpMoney));
                
              }else if(opMoney != null && !opMoney.equals("")){
                criterion += " (empHAFAccountFlow.debit+empHAFAccountFlow.credit) = ?  and";
                parameters.add(new BigDecimal(opMoney));
                
              }
              if (opDirection != null&&!opDirection.equals("")) {
                if(opDirection.equals("0")){
                  criterion += " empHAFAccountFlow.debit > 0 and ";        
                }else if(opDirection.equals("1")){
                  criterion += "  empHAFAccountFlow.credit > 0  and ";        
                }
              }
              if (orgbusinessflowHeadID != null&&!orgbusinessflowHeadID.equals("")) {
                criterion += " empHAFAccountFlow.orgHAFAccountFlow.id = ?  and ";
                parameters.add(new Integer(orgbusinessflowHeadID));
              }
              String ob = orderBy;
              if (ob == null)
                ob = " empHAFAccountFlow.id ";

              String od = order;
              if (od == null)
                od = "DESC";
              if (criterion.length() != 0)
                criterion=" and "+
                criterion.substring(0, criterion.lastIndexOf("and"));
              hql = hql + criterion + "order by " + ob + " " + od ;

              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              
              List queryList=query.list();
              
              return queryList;
            }
          }
      );

    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
  /**
   * ͳ�Ʋ�ѯ���������з������ڣ���λ���ƣ���λ��ţ�Ա�����ƣ�Ա����� ���ظ�list.
   */
  public List queryEmpAccountList_sy(final String orderBy, final String order,
      final int start, final int pageSize, final int page,
      final String startDate, final String endDate, final String orgIdaa101,
      final String nameba001, final String empIdaa102, final String nameba002,
      final SecurityInfo securityInfo) {
    List list = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select tt.emp_id,bb.org_id from aa102 tt,aa101 bb,ba001 cc,ba002 dd,aa002 ee,aa001 ff";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgIdaa101 != null && !orgIdaa101.equals("")) {// ��λ���
            criterion += " bb.org_id =  ?  and ";
            parameters.add(orgIdaa101);
          }
          if (nameba001 != null && !nameba001.equals("")) {// ��λ����
            criterion += " cc.name like ? and ";
            parameters.add("%" + nameba001 + "%");
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {// ��ʼ����
            criterion += " bb.sett_Date between ?  and  ?  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (endDate != null && !endDate.equals("")
              && (startDate == null || startDate.equals(""))) {// ��������
            criterion += " bb.sett_Date= ? and  ";
            parameters.add(endDate);
          }
          if (startDate != null && !startDate.equals("")
              && (endDate == null || endDate.equals(""))) {// ��������
            criterion += "  bb.settDate= ? and  ";
            parameters.add(startDate);
          }
          if (empIdaa102 != null && !empIdaa102.equals("")) {// ְ�����
            criterion += "  tt.emp_id= ? and  ";
            parameters.add(empIdaa102);
          }
          if (nameba002 != null && !nameba002.equals("")) {// ����
            criterion += "  dd.name like ? and  ";
            parameters.add("%" + nameba002 + "%");
          }
            criterion += "  tt.org_flow_id= bb.id  and cc.id = ff.orginfo_id and  ff.id=bb.org_id and tt.emp_id=ee.id and ee.emp_info_id=dd.id and bb.biz_status= '5' and bb.org_id "+securityInfo.getGjjSecuritySQL();
          hql = hql +" where "+ criterion+" group by tt.emp_id, bb.org_id ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
           List queryList=query.list();
           return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   *�˷����Ǽ����ڳ���� 
   *prama ����ʱ�䣬Ա����ţ���λ���
   * return �ڳ���� 
   * 
   **/
  public List empAccountPrebalance(final String starttime,final String empid,final String orgid){
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  sum(empHAFAccountFlow.credit) - sum(empHAFAccountFlow.debit) from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "empHAFAccountFlow.empId = ?  and ";
          parameters.add(new Integer(empid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
          parameters.add(new Integer(orgid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate < ?  and ";
          parameters.add(starttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.bizStatus = '5'  and ";
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }  
          session.clear();
          return query.list();
          
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * ͳ�Ʋ�ѯ���������з������ڣ���λ���ƣ���λ��ţ�Ա�����ƣ�Ա����� ���ظ�list.
   */
  public List queryEmpAccountCountList_sy(final String startDate, final String endDate, final String orgIdaa101,
    final String nameba001, final String empIdaa102, final String nameba002,
    final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select tt.emp_id,bb.org_id from aa102 tt,aa101 bb,ba001 cc,ba002 dd,aa002 ee,aa001 ff ";
          Vector parameters = new Vector();
          String criterion = "";
          if (orgIdaa101 != null && !orgIdaa101.equals("")) {// ��λ���
            criterion += " bb.org_id =  ?  and ";
            parameters.add(orgIdaa101);
          }
          if (nameba001 != null && !nameba001.equals("")) {// ��λ����
            criterion += " cc.name like ? and ";
            parameters.add("%" + nameba001 + "%");
          }
          if (startDate != null && !startDate.equals("") && endDate != null
              && !endDate.equals("")) {// ��ʼ����
            criterion += " bb.sett_Date between ?  and  ?  and ";
            parameters.add(startDate);
            parameters.add(endDate);
          }
          if (endDate != null && !endDate.equals("")
              && (startDate == null || startDate.equals(""))) {// ��������
            criterion += " bb.sett_Date= ? and  ";
            parameters.add(endDate);
          }
          if (startDate != null && !startDate.equals("")
              && (endDate == null || endDate.equals(""))) {// ��������
            criterion += "  bb.settDate= ? and  ";
            parameters.add(startDate);
          }
          if (empIdaa102 != null && !empIdaa102.equals("")) {// ְ�����
            criterion += "  tt.emp_id= ? and  ";
            parameters.add(empIdaa102);
          }
          if (nameba002 != null && !nameba002.equals("")) {// ����
            criterion += "  dd.name like ? and  ";
            parameters.add("%" + nameba002 + "%");
          }
            criterion += "  tt.org_flow_id= bb.id  and cc.id = ff.orginfo_id and  ff.id=bb.org_id and tt.emp_id=ee.id and ee.emp_info_id=dd.id and bb.BIZ_STATUS = '5' and bb.org_id "+securityInfo.getGjjSecuritySQL();
          hql = hql +" where "+ criterion+" group by tt.emp_id, bb.org_id ";
          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
           List queryList=query.list();
           return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   *�˷����Ǽ��㱾�ڽ跽������ ,���ڴ��������� ,������Ϣ
   *prama ����ʱ�䣬Ա����ţ���λ���
   * return �ڳ���� 
   * 
   **/
  public List empAccountcurbalance(final String starttime,final String empid,final String orgid,final String lasttime){
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  sum(empHAFAccountFlow.credit), sum(empHAFAccountFlow.debit),sum(empHAFAccountFlow.interest) from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "empHAFAccountFlow.empId = ?  and ";
          parameters.add(new Integer(empid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
          parameters.add(new Integer(orgid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate >= ?  and ";
          parameters.add(starttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate <= ?  and ";
          parameters.add(lasttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.bizStatus = '5'  and ";
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }  
          session.clear();
          Iterator it= query.iterate();
          List t=new ArrayList();
          Object obj[]=null;
          
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            EmpaccountinfoDTO empaccountinfoDTO=new EmpaccountinfoDTO();
            empaccountinfoDTO.setTemp_credit(empaccountinfoDTO.getTemp_credit().add(new BigDecimal(obj[0].toString())));
            empaccountinfoDTO.setTemp_debit(empaccountinfoDTO.getTemp_debit().add(new BigDecimal(obj[1].toString())));
            empaccountinfoDTO.setTemp_interest(empaccountinfoDTO.getTemp_interest().add(new BigDecimal(obj[2].toString())));
            t.add(empaccountinfoDTO);
          }               
          return t;     
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**���뵽���²�ѯ
   *�˷����Ǽ��㱾�ڽ跽������ ,���ڴ��������� ,���ڽ跽���� ,���ڴ������� .
   *prama ����ʱ�䣬Ա����ţ���λ���
   * return �ڳ���� 
   * 
   **/
  public List empAccountcurbalance1(final String orderBy, final String order,
    final int start, final int pageSize, final int page,final String starttime,final String empid,final String orgid,final String lasttime){
  List list=null;
  try{
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select  col_5_0_,sum(col_0_0_) as credita,sum(col_1_0_) as debita from ";
        Vector parameters = new Vector();
        String criterion = "";
        criterion+= "(select sum(emphafacco0_.CREDIT) as col_0_0_,"+
             " sum(emphafacco0_.DEBIT) as col_1_0_,"+
              " count(emphafacco0_.CREDIT) as col_2_0_,"+
              " count(emphafacco0_.DEBIT) as col_3_0_,"+
             "  substr(orghafacco1_.SETT_DATE, 0, 6) as col_5_0_ from AA102 emphafacco0_, AA101 orghafacco1_"+
             " where emphafacco0_.ORG_FLOW_ID = orghafacco1_.ID  and ";
        criterion += "emphafacco0_.EMP_ID = ?  and ";
        parameters.add(new Integer(empid));
        criterion += "orghafacco1_.ORG_ID = ?  and ";
        parameters.add(new Integer(orgid));
        criterion += "orghafacco1_.SETT_DATE >= ?  and ";
        parameters.add(starttime);
        criterion += "orghafacco1_.SETT_DATE <= ?  and ";
        parameters.add(lasttime);
        criterion += "orghafacco1_.BIZ_STATUS = '5'  group by orghafacco1_.SETT_DATE) ";
        criterion += " group by col_5_0_ ";
        hql = hql + criterion ;
        Query query=session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
         List queryList=query.list();
         return queryList;
      }
    });
  } catch (Exception e) {
    e.printStackTrace();
  }
  return list;
}
  /**���뵽���²�ѯ
   *�˷����Ǽ��㱾�ڽ跽������ ,���ڴ��������� ,���ڽ跽���� ,���ڴ������� .
   *prama ����ʱ�䣬Ա����ţ���λ���
   * return �ڳ���� 
   * ˵�������ѯ���������÷���ʱ�����Ҫ�ҵ�����ȡ����,�ڴ��н�ȡ����,�ٴη��������·���.
   * ���η����ԭ��,ͷ��id��һ��.����һ����ȡ����Ҫ������.
   **/
  public List empAccountcurCountBalance1(final String starttime,final String empid,final String orgid,final String lasttime){
  List list=null;
  try{
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select  col_5_0_,sum(col_0_0_) as credita,sum(col_1_0_) as debita , sum(col_7_0_) as suminterest from ";
        Vector parameters = new Vector();
        String criterion = "";
        criterion+= "(select sum(emphafacco0_.CREDIT) as col_0_0_,"+
             " sum(emphafacco0_.DEBIT) as col_1_0_,"+
              " count(emphafacco0_.CREDIT) as col_2_0_,"+
              " count(emphafacco0_.DEBIT) as col_3_0_,"+
             "  substr(orghafacco1_.SETT_DATE, 0, 6) as col_5_0_ ,sum(emphafacco0_.interest) as col_7_0_ from AA102 emphafacco0_, AA101 orghafacco1_"+
             " where emphafacco0_.ORG_FLOW_ID = orghafacco1_.ID  and ";
        criterion += "emphafacco0_.EMP_ID = ?  and ";
        parameters.add(new Integer(empid));
        criterion += "orghafacco1_.ORG_ID = ?  and ";
        parameters.add(new Integer(orgid));
        criterion += "orghafacco1_.SETT_DATE >= ?  and ";
        parameters.add(starttime);
        criterion += "orghafacco1_.SETT_DATE <= ?  and ";
        parameters.add(lasttime);
        criterion += "orghafacco1_.BIZ_STATUS = '5'  group by orghafacco1_.SETT_DATE) ";
        criterion += " group by col_5_0_ ";
        hql = hql + criterion ;
        Query query=session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
         List queryList=query.list();
         return queryList;
      }
    });
  } catch (Exception e) {
    e.printStackTrace();
  }
  return list;
}
  /**���뵽�շ�ҳ
   *�˷����Ǽ��㱾�ڽ跽������ ,���ڴ��������� ,���ڽ跽���� ,���ڴ������� .
   *prama ����ʱ�䣬Ա����ţ���λ���
   * return �ڳ���� 
   * 
   **/
  public List empAccountcurCountBalance2(final String starttime,final String empid,final String orgid,final String lasttime){
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  sum(empHAFAccountFlow.credit), sum(empHAFAccountFlow.debit),sum(empHAFAccountFlow.interest), empHAFAccountFlow.orgHAFAccountFlow.settDate ,substr(empHAFAccountFlow.orgHAFAccountFlow.settDate,0,8) from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "empHAFAccountFlow.empId = ?  and ";
          parameters.add(new Integer(empid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
          parameters.add(new Integer(orgid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate >= ?  and ";
          parameters.add(starttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate <= ?  and ";
          parameters.add(lasttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.bizStatus = '5'  and ";
          if (criterion.length() != 0)
            criterion = "where  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion +"  group by empHAFAccountFlow.orgHAFAccountFlow.settDate " ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
           List queryList=query.list();
           return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**���뵽��
   *�˷����Ǽ��㱾�ڽ跽������ ,���ڴ��������� ,���ڽ跽���� ,���ڴ������� .
   *prama ����ʱ�䣬Ա����ţ���λ���
   * return �ڳ���� 
   * 
   **/
  public List empAccountcurbalance2(final String orderBy, final String order,
      final int start, final int pageSize, final int page,final String starttime,final String empid,final String orgid,final String lasttime){
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select distinct sum(empHAFAccountFlow.credit), sum(empHAFAccountFlow.debit),empHAFAccountFlow.orgHAFAccountFlow.settDate ,substr(empHAFAccountFlow.orgHAFAccountFlow.settDate,0,8)from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "empHAFAccountFlow.empId = ?  and ";
          parameters.add(new Integer(empid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
          parameters.add(new Integer(orgid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate >= ?  and ";
          parameters.add(starttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate <= ?  and ";
          parameters.add(lasttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.bizStatus = '5'  and ";
          if (criterion.length() != 0)
            criterion = "where  "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion +"  group by empHAFAccountFlow.orgHAFAccountFlow.settDate" ;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
           List queryList=query.list();
           return queryList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  /**
   *�˷����Ǽ�����ĩ��� 
   *prama ����ʱ�䣬Ա����ţ���λ���
   * return ��ĩ��� 
   * 
   **/
  public List empAccountCurbalance(final String starttime,final String empid,final String orgid){
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  sum(empHAFAccountFlow.credit) - sum(empHAFAccountFlow.debit) from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "empHAFAccountFlow.empId = ?  and ";
          parameters.add(new Integer(empid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
          parameters.add(new Integer(orgid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate <= ?  and ";
          parameters.add(starttime);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.bizStatus = '5'  and ";
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }  
//          session.clear();
          return query.list();
          
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  
  public void deleteAllEmpFlowByList(List list){
    getHibernateTemplate().deleteAll(list);
  }
  //��ʾ���˻�����Ϣ
  public List queryEmpHAFAccountFlowList(final String empId,
      final String orgId) throws NumberFormatException, Exception {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select empHAFAccountFlow.empId,empHAFAccountFlow.orgHAFAccountFlow.org.id,empHAFAccountFlow.empName,empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name from EmpHAFAccountFlow empHAFAccountFlow  ";
              Vector parameters = new Vector();
              String criterion = "";
              if (orgId != null&&!orgId.equals("")) {
                criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
                parameters.add(new Integer(orgId));
              }
              if (empId != null&&!empId.equals("")) {
                criterion += "empHAFAccountFlow.empId = ?  and ";
                parameters.add(new Integer(empId));
              }
//              criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate >= ?  and ";
//              parameters.add(startDate);
//              criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate <= ?   group by empHAFAccountFlow.empId,empHAFAccountFlow.orgHAFAccountFlow.org.id,empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name  and ";
//              parameters.add(lastDate);
              if (criterion.length() != 0)
                criterion = "where   "
                    + criterion.substring(0, criterion.lastIndexOf("and"));
 
              hql = hql + criterion;
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              } 
              session.clear();
              Iterator it= query.iterate();
              List t=new ArrayList();
              Object obj[]=null;
              EmpHAFAccountFlow empHAFAccountFlow=null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                
                empHAFAccountFlow=new EmpHAFAccountFlow(); 
                Org org=new Org();
                OrgInfo orgInfo=new OrgInfo();
                org.setId(new Integer(obj[1].toString()));
                orgInfo.setName(obj[3].toString());
                org.setOrgInfo(orgInfo);
                OrgHAFAccountFlowPayment orgHAFAccountFlowPayment = new OrgHAFAccountFlowPayment(); 
                orgHAFAccountFlowPayment.setOrg(org);
                empHAFAccountFlow.setOrg(org);
                empHAFAccountFlow.setEmpId(new Integer(obj[0].toString()));
                empHAFAccountFlow.setEmpName(obj[2].toString());
                t.add(empHAFAccountFlow);
              }               
              return t;
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  //ͳ�ƴ�����
  public List countEmpHAFAccountCredit(final String startDate,final String empid,final String orgid,final String lastDate){
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  count(empHAFAccountFlow.credit) from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "empHAFAccountFlow.empId = ?  and ";
          parameters.add(new Integer(empid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
          parameters.add(new Integer(orgid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate >= ?  and ";
          parameters.add(startDate);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate <= ?  and ";
          parameters.add(lastDate);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.bizStatus = '5'  and ";
          criterion += " empHAFAccountFlow.credit != 0  and ";
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
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
  //ͳ�ƽ����
  public List countEmpHAFAccountDebit(final String startDate,final String empid,final String orgid,final String lastDate){
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select  count(empHAFAccountFlow.debit) from EmpHAFAccountFlow empHAFAccountFlow ";
          Vector parameters = new Vector();
          String criterion = "";
          criterion += "empHAFAccountFlow.empId = ?  and ";
          parameters.add(new Integer(empid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.org.id = ?  and ";
          parameters.add(new Integer(orgid));
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate >= ?  and ";
          parameters.add(startDate);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.settDate <= ?  and ";
          parameters.add(lastDate);
          criterion += "empHAFAccountFlow.orgHAFAccountFlow.bizStatus = '5'  and ";
          criterion += " empHAFAccountFlow.debit != 0   and ";
          if (criterion.length() != 0)
            criterion = "where "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion;
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
  
  public Object queryByCriterionsTranInTail(final String empid,
      final String headid) {

    Object tranInTail = null;
    try{
    tranInTail = (Object) getHibernateTemplate()
        .execute(new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select tranInTail.name from TranInTail tranInTail  ";
            Vector parameters = new Vector();
            String criterion = "";
            if (empid != null) {
              criterion += "tranInTail.empId = ? and ";
              parameters.add(new Integer(empid));
            }
            if (headid != null) {
              criterion += "tranInTail.tranInHead.id = ? and ";
              parameters.add(new Integer(headid));
            }
            if (criterion.length() != 0) 
              criterion = " where  "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;

            Query query0 = session.createQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query0.setParameter(i, parameters.get(i));
            }
            return  query0.uniqueResult();
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
    return tranInTail;
  }
  public List queryEmpHAFAccount_sy(final String startDate, final String empId,
      final String orgId,final String lastDate){
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select  aa002.id as empid,aa001.id as orgid,ba002.name as empname,ba001.name as orgname," +
                  "(select nvl((sum(a102.credit) - sum(a102.debit)),0)"+  //����ȡ�����
         " from AA101 a101, AA102 a102 "  +
        " where a102.emp_id = aa002.id " +
       "    and a102.org_flow_id=a101.id "+
        "   and a101.org_id = aa001.id "+
         "  and a101.sett_date < ? "+
        "   and a101.biz_status = 5 "+
       "  ), (select sum(a102.credit) "+   //���ڴ������
        "  from AA101 a101, AA102 a102 "+
       "  where a102.emp_id = aa002.id "+
       "   and a102.org_flow_id=a101.id "+
        "   and a101.org_id = aa001.id "+
        "    and a101.sett_date between ? and ?  "+
         "     and a101.biz_status = 5),(select sum(a102.debit) "+   //���ڽ跽���
        "  from AA101 a101, AA102 a102 "+
       "  where a102.emp_id = aa002.id "+
        "  and a102.org_flow_id=a101.id "+
       "    and a101.org_id = aa001.id "+
         "  and a101.sett_date between ? and ? "+ 
      "     and a101.biz_status = 5),(select count(a102.id) "+  //ͳ�Ʊ��ڷ�������
       "   from AA101 a101, AA102 a102 "+
      "   where a102.emp_id = aa002.id "+
      "    and a102.org_flow_id=a101.id "+
       "    and a101.org_id = aa001.id "+
        "   and a101.sett_date between ? and ? "+
      "     and a101.biz_status = 5 "+
       "    and a102.credit != 0),(select count(a102.id) "+
      "    from AA101 a101, AA102 a102 "+
     "    where a102.emp_id = aa002.id "+
       "    and  a102.org_flow_id=a101.id "+
     "      and a101.org_id = aa001.id "+
     "      and a101.sett_date between ? and ?  "+
      "     and a101.biz_status = 5 "+
      "     and a102.debit != 0),(select sum(a102.credit) - sum(a102.debit) "+
      "    from AA101 a101, AA102 a102 "+
       "  where a102.emp_id = aa002.id "+
       "   and a102.org_flow_id=a101.id "+
         "  and a101.org_id = aa001.id "+
       "    and a101.sett_date <= ?  "+
        "   and a101.biz_status = 5),(select sum(a102.interest) "+
       "   from AA101 a101, AA102 a102 "+
        " where a102.emp_id = aa002.id "+
       "    and a102.org_flow_id = a101.id "+
       "    and a101.org_id = aa001.id " +
      "     and a101.sett_date between ? and ? "+
       "     and a101.biz_status = 5) from AA101 aa101,"+
      " AA102 aa102, "+
      " AA001 aa001, "+
      " BA001 ba001, "+
      " AA002 aa002, "+
      " BA002 ba002  where aa001.orginfo_id = ba001.id "+
  " and aa002.emp_info_id = ba002.id "+
 "  and aa102.org_flow_id = aa101.id "+
  " and aa002.id = aa102.emp_id  "+
  " and aa101.org_id = aa001.id "+
 "  and aa001.id=? and aa102.emp_id=? "; 
              Vector parameters = new Vector();
              String criterion = "";
              //����ڳ�ʱ�䣬���ڼ����ڳ����
              parameters.add(startDate);
              //��ӱ��ڷ���ʱ��δ����
              parameters.add(startDate);
              parameters.add(lastDate);
              //��ӱ��ڷ���ʱ��ν���
              parameters.add(startDate);
              parameters.add(lastDate);
              //��ӱ��ڷ���ʱ��δ�������
              parameters.add(startDate);
              parameters.add(lastDate);
              //��ӱ��ڷ���ʱ��ν������
              parameters.add(startDate);
              parameters.add(lastDate);
              //���ڼ�����ĩ���
              parameters.add(lastDate);
              //��ӱ��ڷ���ʱ��λ�ȡ��Ϣ
              parameters.add(startDate);
              parameters.add(lastDate);
              //��λ���
              parameters.add(new Integer(orgId));
              //empid
              parameters.add(new Integer(empId));
              Query query = session.createSQLQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              } 
              session.clear();
              List list=query.list();
              Iterator it=list.iterator() ;
              List t=new ArrayList();
              Object obj[]=null;
              EmpHAFAccountFlow empHAFAccountFlow=null;
              while (it.hasNext()) {
                obj = (Object[]) it.next();
                
                empHAFAccountFlow=new EmpHAFAccountFlow(); 
                Org org=new Org();
                OrgInfo orgInfo=new OrgInfo();
                org.setId(new Integer(obj[1].toString()));
                orgInfo.setName(obj[3].toString());
                org.setOrgInfo(orgInfo);
                empHAFAccountFlow.setOrg(org);
                empHAFAccountFlow.setEmpId(new Integer(obj[0].toString()));
                empHAFAccountFlow.setPrebalance(new BigDecimal(obj[4].toString()));
                empHAFAccountFlow.setTemp_credit(new BigDecimal(obj[5].toString()));
                empHAFAccountFlow.setTemp_debit(new BigDecimal(obj[6].toString()));
                empHAFAccountFlow.setCountCredit(obj[7].toString());
                empHAFAccountFlow.setCountDebit(obj[8].toString());
                empHAFAccountFlow.setCurbalance(new BigDecimal(obj[9].toString()));
                empHAFAccountFlow.setDisplayTme(startDate+"-"+lastDate);
                empHAFAccountFlow.setEmpName(obj[2].toString());
                empHAFAccountFlow.setTemp_interest(new BigDecimal(obj[10].toString()));
                t.add(empHAFAccountFlow);
              }  

              return t;
            }
          }
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /*
   * ��ѯְ�����
   */  
  public BigDecimal queryEmpBalance(final Integer empid,final Integer orgid) {

      BigDecimal balance=new BigDecimal(0.00);
      try {
       balance=(BigDecimal)getHibernateTemplate().execute(
            new HibernateCallback() {
              public Object doInHibernate(Session session)
                  throws HibernateException, SQLException {
                String sql = " select sum(a2.pre_balance+a2.cur_balance) from aa002 a2  where a2.id=? and  a2.org_id=? ";              
                Query query = session.createSQLQuery(sql);
                query.setParameter(0, empid);
                query.setParameter(1, orgid);
                Object obj=null;
                Iterator it=query.list().iterator();
                if(it.hasNext())
                  obj=(Object)it.next();
                return obj;
       }
                
            }
        );
      } catch (Exception e) {
        e.printStackTrace();
      }
      return balance;     
    }
  public String findReason(final String bizid, final String empidd) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = " select a307.pick_reason from aa307 a307  where a307.pickhead_id=? and  a307.emp_id=? ";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0,  new Integer(bizid));
            query.setParameter(1, new Integer(empidd));
            return query.uniqueResult().toString();
          }
        });

    return id;
  }
   
}



























