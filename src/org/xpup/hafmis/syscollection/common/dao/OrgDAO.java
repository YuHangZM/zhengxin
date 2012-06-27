package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearinterest.dto.ClearaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.dto.OrgVerAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accountmng.accountopen.dto.OrgAgentExportDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayMonthDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.dto.OrgBaseInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.form.OrgInfoSearchAF;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollInfoFindResultDTO;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollinfoSumDTO;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.dto.Fundbankmonthofyeardto;
import org.xpup.hafmis.syscommon.domain.entity.OrgInfo;
import org.xpup.hafmis.syscommon.domain.entity.PayBank;
import org.xpup.hafmis.syscommon.domain.entity.Transactor;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationTaPop3DTO;

public class OrgDAO extends HibernateDaoSupport {
  public void update_yg(final String id, final String old_id) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "update Org org set org.id=? where org.orgInfo.id=?";
          Query query = session.createQuery(sql);
          query.setString(0, id);
          query.setString(1, old_id);
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
  }

  public String queryEmpBalance(final Integer oldid) {
    String balance = "";
    try {
      balance = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select sum(t.pre_balance+t.cur_balance) from aa002 t where t.org_id = ?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, oldid);
              return query.uniqueResult().toString();
            }
          });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return balance;
  }

  public String queryEmpNum(final Integer oldid) {
    String empNum = "";
    try {
      empNum = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select count(t.id) from aa002 t where t.org_id = ? and t.pay_status in ('1','2')";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, oldid);
          return query.uniqueResult().toString();
        }
      });
    } catch (Exception s) {
      s.printStackTrace();
    }
    return empNum;
  }

  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public Org queryById(Integer id) {
    Validate.notNull(id);
    return (Org) getHibernateTemplate().get(Org.class, id);
  }

  /**
   * ����Ȩ������ѯ�����λid
   */
  public Org findById(final Integer id, final SecurityInfo info) {
    try {
      Org org = (Org) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object obj = session.createQuery(
              "select o from Org o where o.id " + info.getGjjSecurityHqlSQL()
                  + " and o.id =?").setInteger(0, id.intValue()).uniqueResult();
          return obj;
        }
      });
      return org;
    } catch (Exception s) {
      s.printStackTrace();
    }
    return null;
  }

  /**
   * ����Ȩ������ѯ�����λid
   */
  public boolean isHaveOrg(final Integer id, final SecurityInfo info) {
    boolean flag = true;
    try {
      Object orgId = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          Object obj = session.createSQLQuery(
              "select aa001.id from AA001 aa001 where aa001.id=? and (aa001.id "
                  + info.getGjjSecuritySQL() + ") ").setInteger(0,
              id.intValue()).uniqueResult();
          return obj;
        }
      });
      if (orgId != null) {
        flag = false;
      }
    } catch (Exception s) {
      s.printStackTrace();
    }
    return flag;
  }

  /**
   * �����¼
   * 
   * @param org
   * @return
   */
  public Serializable insert(Org org) {
    Serializable id = null;
    try {
      Validate.notNull(org);
      id = getHibernateTemplate().save(org);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * ���������������ѯ��λ��Ϣ�б�
   * 
   * @param id
   * @param name
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param status
   * @return
   * @throws NumberFormatException
   * @throws Exception ��� ��λ�������ѯ
   */
  public List queryOrgpopListByCriterions(final String id, final String name,
      final String oldId, final String orderBy, final String order,
      final int start, final int pageSize, final String[] status,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from Org orgs where orgs.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " to_char(orgs.id) like ? escape '/'  and ";
            parameters.add("%" + id + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += "orgs.orgInfo.name  like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }

          if (oldId != null && !oldId.equals("")) {
            criterion += " to_char(orgs.oldOrgID) like ? escape '/'  and ";
            parameters.add("%" + oldId + "%");
          }

          if (status != null && status.length > 0) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "orgs.orgInfo.openstatus = ? or ";
              parameters.add(status[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " orgs.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
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

  /**
   * ���������������ѯ��λ��Ϣ��¼��
   * 
   * @param id
   * @param name
   * @param status
   * @return ��� ��λ�������ѯ
   */

  public int queryOrgpopCountByCriterions(final String id, final String name,
      final String oldId, final String[] status, final SecurityInfo securityInfo) {
    Integer count = new Integer(0);
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " select count(orgs.id) from Org orgs where orgs.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " to_char(orgs.id)  like ? escape '/'  and ";
            parameters.add("%" + id + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += " orgs.orgInfo.name like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }

          if (oldId != null && !oldId.equals("")) {
            criterion += " to_char(orgs.oldOrgID) like ? escape '/'  and ";
            parameters.add("%" + oldId + "%");
          }

          if (status != null && status.length > 0 && !status.equals("")) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "orgs.orgInfo.openstatus = ? or ";
              parameters.add(status[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return Integer.valueOf(query.uniqueResult().toString());
        }
      }

      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }

  /*
   * insert ��λ��Ϣ
   */
  public Serializable insert(OrgInfo orgInfo) {
    Serializable id = null;
    try {
      Validate.notNull(orgInfo);
      id = getHibernateTemplate().save(orgInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /*
   * ����ά����ѯ�б�
   */
  public List queryOrgOpenByCriterions(final Serializable id,
      final String name, final Serializable payMode, final Serializable status,
      final String orderBy, final String order, final int start,
      final int pageSize, final String temp_Type,
      final SecurityInfo securityInfo, final String startdate,
      final String enddate, final String pagetype) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from Org orgs where orgs.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (id != null && !id.equals("")) {
            criterion += "to_char(orgs.id) like ? and ";
            parameters.add("%" + id.toString() + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += "orgs.orgInfo.name like ? escape '/' and ";
            parameters.add("%" + name + "%");
          }

          if (status != null && !status.equals("")) {
            criterion += "orgs.orgInfo.openstatus = ?  and ";
            parameters.add(status.toString());
          } else {
            if (temp_Type == null && "1".equals(pagetype)) {
              criterion += "orgs.orgInfo.openstatus = '1' and orgs.orgInfo.tijiao is null and ";
            } else {
              criterion += "orgs.orgInfo.openstatus = '1' and orgs.orgInfo.tijiao=1 and ";
            }
          }

          if (payMode != null && !payMode.equals("")) {
            criterion += "orgs.payMode = ? and ";
            parameters.add(new BigDecimal(payMode.toString()));
          }

          if (startdate != null && !startdate.equals("")) {
            criterion += "orgs.orgInfo.openDate >= ? and ";
            parameters.add(startdate.toString());
          }

          if (enddate != null && !enddate.equals("")) {
            criterion += "orgs.orgInfo.openDate <= ? and ";
            parameters.add(enddate.toString());
          }

          if (criterion.length() != 0)
            criterion = "and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = "orgs.id";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + " order by " + ob + " " + order;
          // System.out.println("*************");
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          List list = query.list();
          // System.out.println("------dao::::------------"+query.list());
          session.clear();
          return list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;

  }

  /*
   * ����ά���б���Ͳ�ѯ ���Ʒ��޸ģ�����temp_Type���������ж��Ƿ�Ϊ������ѯ��
   */
  public int queryOrgOpenCountByCriterions(final Serializable id,
      final String name, final Serializable payMode, final Serializable status,
      final String temp_Type, final SecurityInfo securityInfo,
      final String startdate, final String enddate, final String pagetype) {
    int count = 0;
    try {
      // List list=new ArrayList();
      Integer count_ = (Integer) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              // System.out.println("��ȥ��");
              String hql = "select count(orgs.id) from Org orgs where orgs.id "
                  + securityInfo.getGjjSecurityHqlSQL();

              Vector parameters = new Vector();
              String criterion = "";
              if (id != null && !id.equals("")) {
                criterion += "to_char(orgs.id) like ? and ";
                parameters.add("%" + id.toString() + "%");
              }

              if (name != null && !name.equals("")) {
                criterion += "orgs.orgInfo.name like ? escape '/' and ";
                parameters.add("%" + name + "%");
              }

              if (status != null && !status.equals("")) {
                criterion += "orgs.orgInfo.openstatus = ?  and ";
                parameters.add(status.toString());
              } else {
                if (temp_Type == null && "1".equals(pagetype)) {
                  criterion += "orgs.orgInfo.openstatus = '1' and orgs.orgInfo.tijiao is null and ";
                } else {
                  criterion += "orgs.orgInfo.openstatus = '1' and orgs.orgInfo.tijiao=1 and ";
                }
              }

              if (payMode != null && !payMode.equals("")) {
                criterion += "orgs.payMode = ? and ";
                parameters.add(new BigDecimal(payMode.toString()));
              }

              if (startdate != null && !startdate.equals("")) {
                criterion += "orgs.orgInfo.openDate >= ? and ";
                parameters.add(startdate.toString());
              }

              if (enddate != null && !enddate.equals("")) {
                criterion += "orgs.orgInfo.openDate <= ? and ";
                parameters.add(enddate.toString());
              }

              if (criterion.length() != 0)
                criterion = "and "
                    + criterion.substring(0, criterion.lastIndexOf("and"));

              hql = hql + criterion;
              // System.out.println(hql);
              // session.clear();
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }

              // System.out.println("fsdrqwerfsdfwerfrdfwer:::::"+query.uniqueResult());

              return query.uniqueResult();
            }
          }

      );
      // count=list.size();
      count = count_.intValue();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /*
   * ��λ�����޸�
   */
  public Serializable update(Org org) {
    Validate.notNull(org);
    Org orgg = queryById(new Integer(org.getId().toString()));

    try {
      orgg.setOrgAgentNum(org.getOrgAgentNum());
      orgg.getOrgInfo().setName(org.getOrgInfo().getName());
      orgg.getOrgInfo().setOfficecode(org.getOrgInfo().getOfficecode());
      orgg.getOrgInfo().setCode(org.getOrgInfo().getCode());
      orgg.getOrgInfo().setArtificialPerson(
          org.getOrgInfo().getArtificialPerson());
      orgg.getOrgInfo().setCharacter(org.getOrgInfo().getCharacter());
      orgg.getOrgInfo().setIndustry(org.getOrgInfo().getIndustry());
      orgg.getOrgInfo().setDeptInCharge(org.getOrgInfo().getDeptInCharge());
      orgg.getOrgInfo().setTaxRegNum(org.getOrgInfo().getTaxRegNum()); // ˰��ǼǺ�
      orgg.getOrgInfo().setInspector(org.getOrgInfo().getInspector()); // ����Ա
      orgg.getOrgInfo().setAddress(org.getOrgInfo().getAddress());
      orgg.getOrgInfo().setPostalcode(org.getOrgInfo().getPostalcode());
      if (org.getOrgInfo().getPayBank() == null) {
        org.getOrgInfo().setPayBank(new PayBank());
      }
      orgg.getOrgInfo().getPayBank().setName(
          org.getOrgInfo().getPayBank().getName()); // ��λ��н����
      orgg.getOrgInfo().getPayBank().setAccountNum(
          org.getOrgInfo().getPayBank().getAccountNum()); // ��н���ʺ�
      orgg.getOrgInfo().setTel(org.getOrgInfo().getTel());
      orgg.getOrgInfo().setRegion(org.getOrgInfo().getRegion());
      // orgg.getOrgInfo().setPayBank(new PayBank());
      // orgg.getOrgInfo().getPayBank().setName(org.getOrgInfo().getPayBank().getName());
      // orgg.getOrgInfo().getPayBank().setAccountNum(org.getOrgInfo().getPayBank().getAccountNum());
      orgg.getOrgInfo().setPaydate(org.getOrgInfo().getPaydate());// ��н��
      orgg.getOrgInfo().setCollectionBankId(
          org.getOrgInfo().getCollectionBankId());// �鼯����
      orgg.getOrgInfo().setTransactor(new Transactor());
      orgg.getOrgInfo().getTransactor().setName(
          org.getOrgInfo().getTransactor().getName());
      orgg.getOrgInfo().getTransactor().setEmail(
          org.getOrgInfo().getTransactor().getEmail());
      orgg.getOrgInfo().getTransactor().setTelephone(
          org.getOrgInfo().getTransactor().getTelephone());
      orgg.getOrgInfo().getTransactor().setMobileTelephone(
          org.getOrgInfo().getTransactor().getMobileTelephone());
      orgg.setFirstPayMonth(org.getFirstPayMonth());
      orgg.setPayMode(org.getPayMode());
      orgg.setEmpRate(org.getEmpRate());
      orgg.setOrgRate(org.getOrgRate());
      orgg.setPayPrecision(org.getPayPrecision());
      if (orgg.getOrgInfo().getOpenstatus().equals("1")) {
        orgg.setFirstPayMonth(org.getFirstPayMonth());
      }
      if(org.getOrgPayMonth()!=null){
        orgg.setOrgPayMonth(org.getOrgPayMonth());
      }
      if(org.getEmpPayMonth()!=null){
        orgg.setEmpPayMonth(org.getEmpPayMonth());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return orgg.getId();
  }

  /**
   * ���������������ѯ��λ��Ϣ
   * 
   * @param id
   * @param status
   * @return paymode �����
   */
  public Org queryByCriterions(final String id, final String status,
      final String paymode, final SecurityInfo securityInfo) {
    Org org = (Org) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "from Org orgs  ";
        Vector parameters = new Vector();
        String criterion = "";

        if (id != null && !id.equals("")) {
          criterion += "orgs.id = ?  and ";
          parameters.add(new Integer(id));
        }

        if (status != null && !status.equals("")) {
          criterion += "orgs.orgInfo.openstatus = ?  and ";
          parameters.add(status);
        }

        if (paymode != null && !paymode.equals("")) {
          criterion += "orgs.payMode = ?  and ";
          parameters.add(new BigDecimal(paymode));
        }

        if (criterion.length() != 0)
          criterion = "where orgs.id " + securityInfo.getGjjSecurityHqlSQL()
              + " and " + criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion;
        // ���Ʒ��޸ģ��������Ϊ����̬
        session.clear();
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        Object obj = query.uniqueResult();

        return obj;
      }
    });
    return org;
  }

  /**
   * ���� �÷������ڵ������뵥λID �ĵ�λ״̬,
   */
  public String getOrgStatus(final String id) {
    String orgStatus = "";
    try {
      orgStatus = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select org.orgInfo.openstatus from Org org where org.id=?  ";
              Vector parameters = new Vector();
              parameters.add(new Integer(id));
              Query query = session.createQuery(hql);
              for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
              }
              return query.uniqueResult();
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgStatus;

  }

  /*
   * ɾ��AA001ְ����Ϣ
   */
  public void deleteOrgByIdSL(Integer id) {
    try {

      Validate.notNull(id);
      Org org = queryById(id);
      getHibernateTemplate().delete(org);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ���� ���ݵ�λID ��ѯ��λ��Ϣ
   */
  public Org getOrg_WL(final String orgID) {
    Org org = null;
    try {
      org = (Org) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from Org org where org.id=?  ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(orgID));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }

  public Org getOrg_YG(final String orgID, final SecurityInfo securityInfo) {
    Org org = null;
    try {
      org = (Org) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from Org orgs where orgs.id = ? and orgs.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Query query = session.createQuery(hql);
          query.setParameter(0,new Integer(orgID));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }

  /**
   * ���� AA001���Ƿ�����������������ļ�¼
   */
  public Org getOrgByIDStatus_WL(final String orgID) {
    Org org = null;
    try {
      org = (Org) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from Org org where org.id=?  and org.payMode=1 and org.orgInfo.openstatus=2 ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(orgID));
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }

  public Org findOrgInfo(final String orgID) {
    Org org = null;
    try {
      org = (Org) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "from Org org where org.id=?  and org.orgInfo.openstatus=2 ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(orgID));

          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return org;
  }

  /**
   * ���� ��ѯ���Ͻ�Ϣ�����ĵ�λ��Ϣ
   */
  public List findClearaccountListByCriterions_WL(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String oper, final String accountantyear, final String orderBy,
      final String order, final int start, final int pageSize, final int page,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String criterion = "";
          int j = 0;

          String hql = " select distinct b.id, a.name,(select count(d.id) from aa002 d where b.id = d.org_id) "
              + " from ba001 a, aa001 b, aa002 c "
              + "  where b.id "
              + securityInfo.getGjjSecuritySQL()
              + " and a.id = b.orginfo_id and b.id = c.org_id and a.openstatus!='1' "
              + " and b.id not in (select distinct t.org_id from aa101 t where t.biz_status in (3, 4)) "
              + " and b.id not in (select distinct f.id from ba001 e, aa001 f, aa002 g, aa316 i  "
              + " where e.id = f.orginfo_id and f.id = g.org_id  and i.org_id = f.id and i.type = 'A' and i.year ="
              + accountantyear + " ) and ";

          if (officecode != null && !officecode.equals("")) {
            hql += "  a.officecode = ?  and ";
          }

          if (bankcode != null && !bankcode.equals("")) {
            hql += "  a.collection_bank_id = ? and  ";
          }

          if (orgcode != null && !orgcode.equals("")) {
            hql += "  To_Char(b.id) like ? and ";
          }

          if (orgname != null && !orgname.equals("")) {
            hql += "  a.name like ? and  ";
          }

          // if (criterion.length() != 0) {
          // criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          // } else {
          hql = hql.substring(0, hql.lastIndexOf("and"));
          // }

          hql = hql + criterion + " order by b.id";

          Query query = session.createSQLQuery(hql);
          // for (int i = 0; i < parameters.size(); i++) {
          // query.setParameter(i, parameters.get(i));
          // }

          if (officecode != null && !officecode.equals("")) {
            query.setString(j, officecode);
            j++;
          }

          if (bankcode != null && !bankcode.equals("")) {
            query.setString(j, bankcode);
            j++;
          }

          if (orgcode != null && !orgcode.equals("")) {
            query.setString(j, "%" + orgcode + "%");
            j++;
          }

          if (orgname != null && !orgname.equals("")) {
            query.setString(j, "%" + orgname + "%");
            j++;
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          ClearaccountDTO clearaccountDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearaccountDTO = new ClearaccountDTO();
            clearaccountDTO.setOrgcode("0" + obj[0].toString());
            clearaccountDTO.setOrgname(obj[1].toString());
            clearaccountDTO.setEmpcount(obj[2].toString());
            tableList.add(clearaccountDTO);
          }

          List queryList = tableList;

          if (queryList == null || queryList.size() == 0) {
            query.setFirstResult(pageSize * (page - 2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList = tableList;
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
   * ���� ��ѯ���Ͻ�Ϣ������ȫ����λ��Ϣ
   */
  public List findClearaccountAllListByCriterions_WL(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String oper, final String accountantyear,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String criterion = "";
          int j = 0;
          String hql = " select distinct b.id, a.name,(select count(d.id) from aa002 d where b.id = d.org_id) "
              + " from ba001 a, aa001 b, aa002 c "
              + "  where b.id "
              + securityInfo.getGjjSecuritySQL()
              + " and a.id = b.orginfo_id and b.id = c.org_id and a.openstatus!='1' "
              + " and b.id not in (select distinct t.org_id from aa101 t where t.biz_status in (3, 4)) "
              + " and b.id not in (select distinct f.id from ba001 e, aa001 f, aa002 g, aa316 i  "
              + " where e.id = f.orginfo_id and f.id = g.org_id  and i.org_id = f.id and i.type = 'A' and i.year ="
              + accountantyear + " ) and ";

          if (officecode != null && !officecode.equals("")) {
            hql += "  a.officecode = '"+officecode+"'  and ";
          }

          if (bankcode != null && !bankcode.equals("")) {
            hql += "  a.collection_bank_id = "+bankcode+" and  ";
          }

          if (orgcode != null && !orgcode.equals("")) {
            hql += "  To_Char(b.id) like "+"%" + orgcode + "%"+" and ";
          }

          if (orgname != null && !orgname.equals("")) {
            hql += "  a.name like "+"%" + orgname + "%"+" and  ";
          }
          String hhql = " select distinct b.id "
            + " from ba001 a, aa001 b, aa002 c "
            + "  where b.id "
            + securityInfo.getGjjSecuritySQL()
            + " and a.id = b.orginfo_id and b.id = c.org_id and a.openstatus!='1' "
            + " and b.id not in (select distinct t.org_id from aa101 t where t.biz_status in (3, 4)) "
            + " and b.id not in (select distinct f.id from ba001 e, aa001 f, aa002 g, aa316 i  "
            + " where e.id = f.orginfo_id and f.id = g.org_id  and i.org_id = f.id and i.type = 'A' and i.year ="
            + accountantyear + " ) and ";
          
          if (officecode != null && !officecode.equals("")) {
            hhql += "  a.officecode = '"+officecode+"'  and ";
          }
          
          if (bankcode != null && !bankcode.equals("")) {
            hhql += "  a.collection_bank_id = "+bankcode+" and  ";
          }
          
          if (orgcode != null && !orgcode.equals("")) {
            hhql += "  To_Char(b.id) like "+"%" + orgcode + "%"+" and ";
          }
          
          if (orgname != null && !orgname.equals("")) {
            hhql += "  a.name like "+"%" + orgname + "%"+" and  ";
          }

          // if (criterion.length() != 0) {
          // criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          // } else {
          hql = hql.substring(0, hql.lastIndexOf("and"));
          hhql = hhql.substring(0, hhql.lastIndexOf("and"));
          // }

          hhql = hhql + criterion;
          hql = hql + criterion + " order by b.id";

          
          try {
            Connection conn = getHibernateTemplate().getSessionFactory()
            .openSession().connection();
            Statement st = conn.createStatement();
            Statement sta = conn.createStatement();
            String sql = " delete jiexiorg ";
            st.executeUpdate(sql);
            st.close();
            String sqls = " insert into jiexiorg ("+hhql+") ";
            sta.executeUpdate(sqls);
            sta.close();
            conn.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
          
          
          Query query = session.createSQLQuery(hql);
          // for (int i = 0; i < parameters.size(); i++) {
          // query.setParameter(i, parameters.get(i));
          // }


          ClearaccountDTO clearaccountDTO = null;
          List tableList = new ArrayList();
          Object obj[] = null;
          Iterator iterate = query.list().iterator();

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            clearaccountDTO = new ClearaccountDTO();
            clearaccountDTO.setOrgcode("0" + obj[0].toString());
            clearaccountDTO.setOrgname(obj[1].toString());
            clearaccountDTO.setEmpcount(obj[2].toString());
            tableList.add(clearaccountDTO);
          }

          return tableList;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ���� ��ѯ���Ͻ�Ϣ�����ĵ�λ��Ϣ������
   */
  public int findClearaccountCountByCriterions_WL(final String officecode,
      final String bankcode, final String orgcode, final String orgname,
      final String oper, final String accountantyear,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String criterion = "";
          int j = 0;
          String hql = " select distinct b.id, a.name,(select count(d.id) from aa002 d where b.id = d.org_id) "
              + " from ba001 a, aa001 b, aa002 c "
              + "  where b.id "
              + securityInfo.getGjjSecuritySQL()
              + " and a.id = b.orginfo_id and b.id = c.org_id  and a.openstatus!='1' "
              + " and b.id not in (select distinct t.org_id from aa101 t where t.biz_status in (3, 4)) "
              + " and b.id not in (select distinct f.id from ba001 e, aa001 f, aa002 g, aa316 i  "
              + " where e.id = f.orginfo_id and f.id = g.org_id  and i.org_id = f.id and i.type = 'A' and i.year ="
              + accountantyear + " ) and ";

          if (officecode != null && !officecode.equals("")) {
            hql += "  a.officecode = ?  and ";
          }

          if (bankcode != null && !bankcode.equals("")) {
            hql += "  a.collection_bank_id = ? and  ";
          }

          if (orgcode != null && !orgcode.equals("")) {
            hql += "  To_Char(b.id) like ? and ";
          }

          if (orgname != null && !orgname.equals("")) {
            hql += "  a.name like ? and  ";
          }

          // if (criterion.length() != 0) {
          // criterion = criterion.substring(0, criterion.lastIndexOf("and"));
          // } else {
          hql = hql.substring(0, hql.lastIndexOf("and"));
          // }

          hql = hql + criterion + " order by b.id";

          Query query = session.createSQLQuery(hql);
          // for (int i = 0; i < parameters.size(); i++) {
          // query.setParameter(i, parameters.get(i));
          // }

          if (officecode != null && !officecode.equals("")) {
            query.setString(j, officecode);
            j++;
          }

          if (bankcode != null && !bankcode.equals("")) {
            query.setString(j, bankcode);
            j++;
          }

          if (orgcode != null && !orgcode.equals("")) {
            query.setString(j, "%" + orgcode + "%");
            j++;
          }

          if (orgname != null && !orgname.equals("")) {
            query.setString(j, "%" + orgname + "%");
            j++;
          }

          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    count = list.size();
    return count;
  }

  // ���ݵ�λ��ź͵�λ���Ʋ�ѯ��λ�ɴ���Ϣ

  public List queryOrgpaymentInfoWZQ(final String id, final String name,
      final String month, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Org orgs where orgs.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += "orgs.id = ?  and ";
            parameters.add(new Integer(id));
          }

          if (name != null && !name.equals("")) {
            criterion += "orgs.orgInfo.name like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          System.out.println(hql);
          session.clear();
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

  /**
   * ��� ��ѯ��λ���������� ��λ���ɵ�����
   * 
   * @param orgid
   * @return
   */

  public List queryOrgaddPaymonth(final Serializable orgid) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select org.orgPayMonth,org.empPayMonth from Org org where org.id=?  ";
          Query query = session.createQuery(hql);
          query.setInteger(0, Integer.parseInt(orgid.toString()));

          Iterator it = query.iterate();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            OrgaddpayMonthDTO orgaddpayMonthDTO = new OrgaddpayMonthDTO();
            orgaddpayMonthDTO.setOrgPaymonth(obj[0].toString());
            orgaddpayMonthDTO.setEmpPaymonth(obj[1].toString());
            temp_list.add(orgaddpayMonthDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ���������������ѯ��λ��Ϣ�б�
   * 
   * @param id
   * @param name
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param status
   * @return
   * @throws NumberFormatException
   * @throws Exception ��� ��λ�������ѯ--����Ȩ��
   */
  public List queryOrgpopListByCriterionsNo(final String id, final String name,
      final String oldId, final String orderBy, final String order,
      final int start, final int pageSize, final String[] status,
      final SecurityInfo securityInfo) throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from Org orgs ";
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " to_char(orgs.id)  like ? escape '/'  and ";
            parameters.add("%" + id + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += " orgs.orgInfo.name  like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }

          if (oldId != null && !oldId.equals("")) {
            criterion += " to_char(orgs.oldOrgID) like ? escape '/'  and ";
            parameters.add("%" + oldId + "%");
          }

          if (status != null && status.length > 0) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += " orgs.orgInfo.openstatus = ? or ";
              parameters.add(status[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " where "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " orgs.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
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

  /**
   * ���������������ѯ��λ��Ϣ��¼��
   * 
   * @param id
   * @param name
   * @param status
   * @return ��� ��λ�������ѯ--����Ȩ��
   */

  public int queryOrgpopCountByCriterionsNo(final String id, final String name,
      final String oldId, final String[] status, final SecurityInfo securityInfo) {
    Integer count = new Integer(0);
    count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {

        String hql = " select count(orgs.id) from Org orgs ";
        Vector parameters = new Vector();
        String criterion = "";

        if (id != null && !id.equals("")) {
          criterion += " to_char(orgs.id)  like ? escape '/'  and ";
          parameters.add("%" + id + "%");
        }

        if (name != null && !name.equals("")) {
          criterion += " orgs.orgInfo.name like ? escape '/'  and ";
          parameters.add("%" + name + "%");
        }

        if (oldId != null && !oldId.equals("")) {
          criterion += " to_char(orgs.oldOrgID) like ? escape '/'  and ";
          parameters.add("%" + oldId + "%");
        }

        if (status != null && status.length > 0 && !status.equals("")) {
          criterion += "( ";
          for (int i = 0; i < status.length; i++) {
            criterion += " orgs.orgInfo.openstatus = ? or ";
            parameters.add(status[i]);
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (criterion.length() != 0)
          criterion = " where "
              + criterion.substring(0, criterion.lastIndexOf("and"));

        hql = hql + criterion;
        session.clear();
        Query query = session.createQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        return Integer.valueOf(query.uniqueResult().toString());
      }
    }

    );
    return count.intValue();
  }

  // �����洢����
  public void insertUserOrgPre(final String username, final int orgId,
      final int collBankId, final String office) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call OrgCompliteDo(?,?,?,?)}");
      cs.setString(1, username);
      cs.setInt(2, orgId);
      cs.setInt(3, collBankId);
      cs.setString(4, office);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  // ����ɾ���洢����
  public void deleteOrgPre(final int orgId) {
    Connection conn = getHibernateTemplate().getSessionFactory().openSession()
        .connection();
    CallableStatement cs;
    try {
      cs = conn.prepareCall("{call OrgDeletePre(?)}");
      cs.setInt(1, orgId);
      cs.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  // ��λ������Ϣͳ�Ʋ�ѯ ���ƽ
  public List queryOrgBaseInfoByCriterions(
      final OrgInfoSearchAF orgInfoSearchAF, final String orderBy,
      final String order, final int start, final int pageSize,
      final SecurityInfo info) {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a001.id ," +
              " b001.name," +
              "(select c.name from bb101 c where c.id =b001.officecode)," +
              "(select d.coll_bank_name from bb105 d where d.coll_bank_id=b001.collection_bank_id)," +
              "b001.character," +
              "b001.dept_in_charge," +
              "b001.paybank_name," +
              " b001.transactor_name," +
              "b001.transactor_tel," +
              "b001.transactor_mobiletel," +
              " b001.open_date," +
              " b001.openstatus " +
              "from aa001 a001, ba001 b001 " +
              "where a001.orginfo_id = b001.id and a001.id  "
              + info.getGjjSecuritySQL();
          Vector parameters = new Vector();
          String criterion = "";
          if (orgInfoSearchAF.getId().trim() != null
              && !orgInfoSearchAF.getId().trim().equals("")) {
            criterion += "a001.id = ? and ";
            parameters.add(new Integer(orgInfoSearchAF.getId().trim()));
          }
          String officeCode = null;
          if (orgInfoSearchAF.getOfficeCode() != null
              && !orgInfoSearchAF.getOfficeCode().equals("")) {
            officeCode = orgInfoSearchAF.getOfficeCode();
          }
          if (officeCode != null && !officeCode.trim().equals("")) {
            criterion += "b001.officecode = ?  and ";
            parameters.add(officeCode);
          }

          if (orgInfoSearchAF.getCollectionBankId() != null
              && !orgInfoSearchAF.getCollectionBankId().trim().equals("")) {
            criterion += "b001.collection_bank_id = ?  and ";
            parameters.add(orgInfoSearchAF.getCollectionBankId());
          }

          if (orgInfoSearchAF.getCharacter().trim() != null
              && !orgInfoSearchAF.getCharacter().trim().equals("")) {
            criterion += "b001.character = ?  and ";
            parameters.add(orgInfoSearchAF.getCharacter());
          }

          if (orgInfoSearchAF.getDeptInCharge().trim() != null
              && !orgInfoSearchAF.getDeptInCharge().trim().equals("")) {
            criterion += "b001.dept_in_charge like ?  and ";
            parameters.add("%" + orgInfoSearchAF.getDeptInCharge() + "%");
          }

          if (orgInfoSearchAF.getName().trim() != null
              && !orgInfoSearchAF.getName().trim().equals("")) {
            criterion += "b001.name like ?  and ";
            parameters.add("%" + orgInfoSearchAF.getName().trim() + "%");
          }

          if (orgInfoSearchAF.getOpenStatus().trim() != null
              && !orgInfoSearchAF.getOpenStatus().trim().equals("")) {
            criterion += "b001.openstatus like ?  and ";
            parameters.add("%" + orgInfoSearchAF.getOpenStatus() + "%");
          }
//          if (orgInfoSearchAF.getRegion().trim() != null
//              && !orgInfoSearchAF.getRegion().trim().equals("")) {
//            criterion += "orgs.orgInfo.region like ?  and ";
//            parameters.add("%" + orgInfoSearchAF.getRegion() + "%");
//          }
//          if (orgInfoSearchAF.getOldOrgId().trim() != null
//              && !orgInfoSearchAF.getOldOrgId().trim().equals("")) {
//            criterion += "orgs.oldOrgID like ?  and ";
//            parameters.add("%" + orgInfoSearchAF.getOldOrgId().trim() + "%");
//          }

//          if (orgInfoSearchAF.getInspector().trim() != null
//              && !orgInfoSearchAF.getInspector().trim().equals("")) {
//            criterion += "orgs.orgInfo.inspector like ?  and ";
//            parameters.add("%" + orgInfoSearchAF.getInspector().trim() + "%");
//          }

//          if (orgInfoSearchAF.getPayDate().trim() != null
//              && !orgInfoSearchAF.getPayDate().trim().equals("")) {
//            criterion += "orgs.orgInfo.paydate like ?  and ";
//            parameters.add("%" + orgInfoSearchAF.getPayDate().trim() + "%");
//          }

//          if (orgInfoSearchAF.getCode().trim() != null
//              && !orgInfoSearchAF.getCode().trim().equals("")) {
//            criterion += "orgs.orgInfo.code like ?  and ";
//            parameters.add("%" + orgInfoSearchAF.getCode().trim() + "%");
//          }
          // ���ݿ������ڲ�ѯ
          if (orgInfoSearchAF.getOpenDateSta().trim() != null
              && !orgInfoSearchAF.getOpenDateSta().trim().equals("")
              && orgInfoSearchAF.getOpenDateEnd().trim() != null
              && !orgInfoSearchAF.getOpenDateEnd().trim().equals("")) {// �п�ʼ���ڽ�������
            criterion += " (b001.open_date between ? and ?) and ";
            parameters.add(orgInfoSearchAF.getOpenDateSta());
            parameters.add(orgInfoSearchAF.getOpenDateEnd());
          }
          if (orgInfoSearchAF.getOpenDateSta().trim() != null
              && !orgInfoSearchAF.getOpenDateSta().trim().equals("")
              && (orgInfoSearchAF.getOpenDateEnd().trim() == null || orgInfoSearchAF
                  .getOpenDateEnd().trim().equals(""))) {// �п�ʼ�����޽�������
            criterion += " b001.open_date >= ? and ";
            parameters.add(orgInfoSearchAF.getOpenDateSta());
          }
          if (orgInfoSearchAF.getOpenDateEnd().trim() != null
              && !orgInfoSearchAF.getOpenDateEnd().trim().equals("")
              && (orgInfoSearchAF.getOpenDateSta().trim() == null || orgInfoSearchAF
                  .getOpenDateSta().trim().equals(""))) {// �н��������޿�ʼ����
            criterion += " b001.open_date <= ? and ";
            parameters.add(orgInfoSearchAF.getOpenDateEnd());
          }
          String ob = orderBy;
          if (ob == null) {
            ob = " a001.id ";
          }
          String or = order;
          if (or == null) {
            or = "desc";
          }
          if (criterion.length() != 0)
            criterion = "and "
                + criterion.substring(0, criterion.lastIndexOf("and"));
          hql = hql + criterion + " order by " + ob + " " + or;

          session.clear();
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator iterate = query.list().iterator();

          OrgBaseInfoDTO orgBaseInfoDTO = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            orgBaseInfoDTO = new OrgBaseInfoDTO();
            orgBaseInfoDTO.setId(BusiTools.convertTenNumber(obj[0].toString()));
            orgBaseInfoDTO.setOrgName(obj[1].toString());
            orgBaseInfoDTO.setOfficecode(obj[2].toString());
            orgBaseInfoDTO.setCollectionBankId(obj[3].toString());
            if (obj[4] != null) {
              orgBaseInfoDTO.setCharacter(obj[4].toString());
            }
            
            if (obj[5] != null) {
              orgBaseInfoDTO.setDeptInCharge(obj[5]
                  .toString());
            }
            if (obj[6] != null) {
              orgBaseInfoDTO.setPaybankName(obj[6]
                  .toString());
            }
            // �����ܶ�
            if (obj[7] != null) {
              orgBaseInfoDTO.setTransactorName(obj[7]
                  .toString());
            }
            // ���������
            if (obj[8] != null) {
              orgBaseInfoDTO.setTransactorTel(obj[8]
                  .toString());
            }
            // ְ������
            if (obj[9] != null) {
              orgBaseInfoDTO
                  .setTransactorMobile(obj[9].toString());
            }
            // ��λ�ɶ�
           
            if (obj[10] != null) {
              orgBaseInfoDTO.setOpenDate(obj[10].toString());
            }
            if (obj[11] != null) {
              orgBaseInfoDTO.setOpenStatus(obj[11]
                  .toString());
            }
            temp_list.add(orgBaseInfoDTO);
          }
          session.clear();
          return temp_list;
       
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // ��λ������Ϣͳ�Ʋ�ѯ���� ���ƽ
  public List queryOrgBaseInfoCountByCriterions(
      final OrgInfoSearchAF orgInfoSearchAF, final String orderBy,
      final String order, final SecurityInfo info) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a001.id ," +
          " b001.name," +
          "(select c.name from bb101 c where c.id =b001.officecode)," +
          "(select d.coll_bank_name from bb105 d where d.coll_bank_id=b001.collection_bank_id)," +
          "b001.character," +
          "b001.dept_in_charge," +
          "b001.paybank_name," +
          " b001.transactor_name," +
          "b001.transactor_tel," +
          "b001.transactor_mobiletel," +
          " b001.open_date," +
          " b001.openstatus,(select count(e.id) from aa002 e where e.org_id=a001.id) " +
          "from aa001 a001, ba001 b001 " +
          "where a001.orginfo_id = b001.id and a001.id  "
          + info.getGjjSecuritySQL();
      Vector parameters = new Vector();
      String criterion = "";
      if (orgInfoSearchAF.getId().trim() != null
          && !orgInfoSearchAF.getId().trim().equals("")) {
        criterion += "a001.id = ? and ";
        parameters.add(new Integer(orgInfoSearchAF.getId().trim()));
      }
      String officeCode = null;
      if (orgInfoSearchAF.getOfficeCode() != null
          && !orgInfoSearchAF.getOfficeCode().equals("")) {
        officeCode = orgInfoSearchAF.getOfficeCode();
      }
      if (officeCode != null && !officeCode.trim().equals("")) {
        criterion += "b001.officecode = ?  and ";
        parameters.add(officeCode);
      }

      if (orgInfoSearchAF.getCollectionBankId() != null
          && !orgInfoSearchAF.getCollectionBankId().trim().equals("")) {
        criterion += "b001.collection_bank_id = ?  and ";
        parameters.add(orgInfoSearchAF.getCollectionBankId());
      }

      if (orgInfoSearchAF.getCharacter().trim() != null
          && !orgInfoSearchAF.getCharacter().trim().equals("")) {
        criterion += "b001.character = ?  and ";
        parameters.add(orgInfoSearchAF.getCharacter());
      }

      if (orgInfoSearchAF.getDeptInCharge().trim() != null
          && !orgInfoSearchAF.getDeptInCharge().trim().equals("")) {
        criterion += "b001.dept_in_charge like ?  and ";
        parameters.add("%" + orgInfoSearchAF.getDeptInCharge() + "%");
      }

      if (orgInfoSearchAF.getName().trim() != null
          && !orgInfoSearchAF.getName().trim().equals("")) {
        criterion += "b001.name like ?  and ";
        parameters.add("%" + orgInfoSearchAF.getName().trim() + "%");
      }

      if (orgInfoSearchAF.getOpenStatus().trim() != null
          && !orgInfoSearchAF.getOpenStatus().trim().equals("")) {
        criterion += "b001.openstatus like ?  and ";
        parameters.add("%" + orgInfoSearchAF.getOpenStatus() + "%");
      }
//      if (orgInfoSearchAF.getRegion().trim() != null
//          && !orgInfoSearchAF.getRegion().trim().equals("")) {
//        criterion += "orgs.orgInfo.region like ?  and ";
//        parameters.add("%" + orgInfoSearchAF.getRegion() + "%");
//      }
//      if (orgInfoSearchAF.getOldOrgId().trim() != null
//          && !orgInfoSearchAF.getOldOrgId().trim().equals("")) {
//        criterion += "orgs.oldOrgID like ?  and ";
//        parameters.add("%" + orgInfoSearchAF.getOldOrgId().trim() + "%");
//      }

//      if (orgInfoSearchAF.getInspector().trim() != null
//          && !orgInfoSearchAF.getInspector().trim().equals("")) {
//        criterion += "orgs.orgInfo.inspector like ?  and ";
//        parameters.add("%" + orgInfoSearchAF.getInspector().trim() + "%");
//      }

//      if (orgInfoSearchAF.getPayDate().trim() != null
//          && !orgInfoSearchAF.getPayDate().trim().equals("")) {
//        criterion += "orgs.orgInfo.paydate like ?  and ";
//        parameters.add("%" + orgInfoSearchAF.getPayDate().trim() + "%");
//      }

//      if (orgInfoSearchAF.getCode().trim() != null
//          && !orgInfoSearchAF.getCode().trim().equals("")) {
//        criterion += "orgs.orgInfo.code like ?  and ";
//        parameters.add("%" + orgInfoSearchAF.getCode().trim() + "%");
//      }
      // ���ݿ������ڲ�ѯ
      if (orgInfoSearchAF.getOpenDateSta().trim() != null
          && !orgInfoSearchAF.getOpenDateSta().trim().equals("")
          && orgInfoSearchAF.getOpenDateEnd().trim() != null
          && !orgInfoSearchAF.getOpenDateEnd().trim().equals("")) {// �п�ʼ���ڽ�������
        criterion += " (b001.open_date between ? and ?) and ";
        parameters.add(orgInfoSearchAF.getOpenDateSta());
        parameters.add(orgInfoSearchAF.getOpenDateEnd());
      }
      if (orgInfoSearchAF.getOpenDateSta().trim() != null
          && !orgInfoSearchAF.getOpenDateSta().trim().equals("")
          && (orgInfoSearchAF.getOpenDateEnd().trim() == null || orgInfoSearchAF
              .getOpenDateEnd().trim().equals(""))) {// �п�ʼ�����޽�������
        criterion += " b001.open_date >= ? and ";
        parameters.add(orgInfoSearchAF.getOpenDateSta());
      }
      if (orgInfoSearchAF.getOpenDateEnd().trim() != null
          && !orgInfoSearchAF.getOpenDateEnd().trim().equals("")
          && (orgInfoSearchAF.getOpenDateSta().trim() == null || orgInfoSearchAF
              .getOpenDateSta().trim().equals(""))) {// �н��������޿�ʼ����
        criterion += " b001.open_date <= ? and ";
        parameters.add(orgInfoSearchAF.getOpenDateEnd());
      }
      String ob = orderBy;
      if (ob == null) {
        ob = " a001.id ";
      }
      String or = order;
      if (or == null) {
        or = "desc";
      }
      if (criterion.length() != 0)
        criterion = "and "
            + criterion.substring(0, criterion.lastIndexOf("and"));
      hql = hql + criterion + " order by " + ob + " " + or;

      session.clear();
      Query query = session.createSQLQuery(hql);

      for (int i = 0; i < parameters.size(); i++) {
        query.setParameter(i, parameters.get(i));
      }
      Iterator iterate = query.list().iterator();

      OrgBaseInfoDTO orgBaseInfoDTO = null;
      List temp_list = new ArrayList();
      Object obj[] = null;

      while (iterate.hasNext()) {
        obj = (Object[]) iterate.next();
        orgBaseInfoDTO = new OrgBaseInfoDTO();
        orgBaseInfoDTO.setId(obj[0].toString());
        orgBaseInfoDTO.setOrgName(obj[1].toString());
        orgBaseInfoDTO.setOfficecode(obj[2].toString());
        orgBaseInfoDTO.setCollectionBankId(obj[3].toString());
        if (obj[4] != null) {
          orgBaseInfoDTO.setCharacter(obj[4].toString());
        }
        
        if (obj[5] != null) {
          orgBaseInfoDTO.setDeptInCharge(obj[5]
              .toString());
        }
        if (obj[6] != null) {
          orgBaseInfoDTO.setPaybankName(obj[6]
              .toString());
        }
        // �����ܶ�
        if (obj[7] != null) {
          orgBaseInfoDTO.setTransactorName(obj[7]
              .toString());
        }
        // ���������
        if (obj[8] != null) {
          orgBaseInfoDTO.setTransactorTel(obj[8]
              .toString());
        }
        // ְ������
        if (obj[9] != null) {
          orgBaseInfoDTO
              .setTransactorMobile(obj[9].toString());
        }
        // ��λ�ɶ�
       
        if (obj[10] != null) {
          orgBaseInfoDTO.setOpenDate(obj[10].toString());
        }
        if (obj[11] != null) {
          orgBaseInfoDTO.setOpenStatus(obj[11]
              .toString());
        }
        if (obj[12] != null) {
          orgBaseInfoDTO.setOrgCount(obj[12]
              .toString());
        }
        temp_list.add(orgBaseInfoDTO);
      }
      session.clear();
      return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ����� ����Ȩ�޲�ѯ
   * 
   * @param securityInfo
   * @return
   */
  public List queryByPagination(final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from Org o where o.id "
              + securityInfo.getGjjSecurityHqlSQL();

          Query query = session.createQuery(hql);
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
   * ����orginfo���officecode��bb101���е�officename ���ƽ
   */
  public String findOfficeNameByOrgInfoOfficeCode(final String id) {
    OrganizationUnit organizationUnit = null;
    organizationUnit = (OrganizationUnit) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from OrganizationUnit organizationUnit where organizationUnit.id= ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, id);
            return query.uniqueResult();
          }
        });
    return organizationUnit.getName();
  }

  /**
   * ����orginfo���collectionBankId��bb105���е�collBankname ���ƽ
   */
  public String findCollBanknameByOrgInfoCollectionBankId(final String id) {
    CollBank collBank = null;
    collBank = (CollBank) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "from CollBank collBank where collBank.collBankId= ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, new Integer(id));
            return query.uniqueResult();
          }
        });
    return collBank.getCollBankName();
  }

  /**
   * ͳ�Ʋ�ѯ-��λ�鼯��Ϣ��ѯ
   * 
   * @param dto ��ѯ������DTO
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param securityInfo
   * @return ��ѯ��Ϣ��List
   */
  public List queryStatisticOrgCollInfo(final OrgCollInfoFindDTO dto,
      final String orderBy, final String order, final int start,
      final int pageSize, final SecurityInfo securityInfo) {
    List tableList = new ArrayList();
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector parameters = new Vector();
          String hql = "select distinct "
              + "a1.id as id,"
              + "nvl((select max(aa302.pay_month) from AA302 aa302,AA301 aa301 "
              + "where aa301.id=aa302.pay_head_id and aa301.pay_type in ('A','B') "
              + "and aa301.pay_status in ('3','4','5') and aa301.pay_model in ('1','2') "
              + "and aa301.org_id = a1.id),a1.org_pay_month) as opm,"
              + "nvl((select max(aa302.pay_month) from AA302 aa302,AA301 aa301 "
              + "where aa301.id=aa302.pay_head_id and aa301.pay_type in ('A','B') "
              + "and aa301.pay_status in ('3','4','5') and aa301.pay_model in ('1','3') "
              + "and aa301.org_id = a1.id),a1.emp_pay_month) as epm,"
              + "b1.open_date as od,"
              + "b1.name as name,"
              + "b1.officecode as offname,"
              + "b1.collection_bank_id as bankname,"
              + "a1.emp_rate as emprate,"
              + "a1.org_rate as orgrate,"
              + "(select nvl(sum(aa2.salary_base),0) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as salsum,"
              + "(select nvl(sum(aa2.pre_balance+aa2.cur_balance),0) from aa002 aa2 where aa2.org_id=a1.id ) as pc_balance,"
              + "(select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as empcount,"
              + "(select nvl(sum(aa2.org_pay),0) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as opay,"
              + "(select nvl(sum(aa2.emp_pay),0) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as epay,"
              + "(select nvl(sum(aa01.credit-aa01.debit),0) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type in ('C','J') and aa01.biz_status='5') as cd, "
              + "(select nvl(count( aa2.id), 0) from aa002 aa2 where aa2.org_id = a1.id and aa2.pay_status in ('1', '2','3', '4')) as personCount, "
              + "b1.character "
              + "from aa002 a2,aa001 a1,ba001 b1 where a1.orginfo_id=b1.id and a1.id=a2.org_id(+) and a1.id "
              + securityInfo.getGjjSecuritySQL();

          if (dto != null) {
            // ���ݵ�λ��Ų�ѯ
            if (dto.getOrgId() != null && !dto.getOrgId().equals("")) {
              criterion += "a1.id=? and ";
              parameters.add(dto.getOrgId());
            }
            // ���ݰ��´���ѯ
            if (dto.getOfficecode() != null && !dto.getOfficecode().equals("")) {
              criterion += "b1.officecode=? and ";
              parameters.add(dto.getOfficecode());
            }
            // ���ݹ鼯���в�ѯ
            if (dto.getCollectionBankId() != null
                && !dto.getCollectionBankId().equals("")) {
              criterion += "b1.collection_bank_id=? and ";
              parameters.add(dto.getCollectionBankId());
            }
            // �������ܲ��Ų�ѯ
            if (dto.getDeptInCharge() != null
                && !dto.getDeptInCharge().equals("")) {
              criterion += "b1.dept_in_charge=? and ";
              parameters.add(dto.getDeptInCharge());
            }
            // ���ݵ�λ���ʲ�ѯ
            if (dto.getCharacter() != null && !dto.getCharacter().equals("")) {
              criterion += "b1.character=? and ";
              parameters.add(dto.getCharacter());
            }
            // ���ݵ�λ����
            if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
              criterion += "b1.name like ? and ";
              parameters.add("%" + dto.getOrgName() + "%");
            }
            // ��λ����
            if (dto.getOrgrateStart() != null
                && !dto.getOrgrateStart().equals("")) {
              criterion += "to_number(a1.org_rate) >= ? and ";
              parameters.add(dto.getOrgrateStart());
            }
            if (dto.getOrgrateEnd() != null && !dto.getOrgrateEnd().equals("")) {
              criterion += "to_number(a1.org_rate) <= ? and ";
              parameters.add(dto.getOrgrateEnd());
            }
            // ְ������
            if (dto.getEmprateStart() != null
                && !dto.getEmprateStart().equals("")) {
              criterion += "to_number(a1.emp_rate) >= ? and ";
              parameters.add(dto.getEmprateStart());
            }
            if (dto.getEmprateEnd() != null && !dto.getEmprateEnd().equals("")) {
              criterion += "to_number(a1.emp_rate) <= ? and ";
              parameters.add(dto.getEmprateEnd());
            }
            // ���ݵ�λ״̬��ѯ
            if (dto.getOpenStatus() != null && !dto.getOpenStatus().equals("")) {
              criterion += "b1.openstatus=? and ";
              parameters.add(dto.getOpenStatus());
            }
            // ���ݽɴ淽ʽ��ѯ
            if (dto.getPayMode() != null && !dto.getPayMode().equals("")) {
              criterion += "a1.pay_mode=? and ";
              parameters.add(dto.getPayMode());
            }
            // �������ڵ�����ѯ
            if (dto.getRegion() != null && !dto.getRegion().equals("")) {
              criterion += "b1.region=? and ";
              parameters.add(dto.getRegion());
            }
            // ����ԭ��λ��Ų�ѯ
            if (dto.getOldOrgId() != null && !dto.getOldOrgId().equals("")) {
              criterion += "a1.org_id_old=? and ";
              parameters.add(dto.getOldOrgId());
            }
            // ���ݻ���Ա��ѯ
            if (dto.getInspector() != null && !dto.getInspector().equals("")) {
              criterion += "b1.inspector like ? and ";
              parameters.add("%" + dto.getInspector() + "%");
            }
            // ���ݷ�н�ղ�ѯ
            if (dto.getPayDate() != null && !dto.getPayDate().equals("")) {
              criterion += "b1.paydate=? and ";
              parameters.add(dto.getPayDate());
            }
            // ������֯���������ѯ
            if (dto.getCode() != null && !dto.getCode().equals("")) {
              criterion += "b1.code=? and ";
              parameters.add(dto.getCode());
            }
            // ���ݿ������ڲ�ѯ
            if (dto.getOpenDateStart() != null
                && !dto.getOpenDateStart().equals("")
                && dto.getOpenDateTimeEnd() != null
                && !dto.getOpenDateTimeEnd().equals("")) {// �п�ʼ���ڽ�������
              criterion += " (b1.open_date between ? and ?) and ";
              parameters.add(dto.getOpenDateStart());
              parameters.add(dto.getOpenDateTimeEnd());
            }
            if (dto.getOpenDateStart() != null
                && !dto.getOpenDateStart().equals("")
                && (dto.getOpenDateTimeEnd() == null || dto
                    .getOpenDateTimeEnd().equals(""))) {// �п�ʼ�����޽�������
              criterion += " b1.open_date >= ? and ";
              parameters.add(dto.getOpenDateStart());
            }
            if (dto.getOpenDateTimeEnd() != null
                && !dto.getOpenDateTimeEnd().equals("")
                && (dto.getOpenDateStart() == null || dto.getOpenDateStart()
                    .equals(""))) {// �н��������޿�ʼ����
              criterion += " b1.open_date <= ? and ";
              parameters.add(dto.getOpenDateTimeEnd());
            }
            // ���ݻ���ܶ��ѯ
            if (dto.getPaySumStart() != null
                && !dto.getPaySumStart().equals("")
                && dto.getPaySumEnd() != null && !dto.getPaySumEnd().equals("")) {
              criterion += " (select sum(aa2.org_pay)+sum(aa2.emp_pay) from aa002 aa2 where aa2.org_id(+)=a1.id and aa2.pay_status in ('1','3','4')) between ? and ? and ";
              parameters.add(dto.getPaySumStart());
              parameters.add(dto.getPaySumEnd());
            }
            if (dto.getPaySumStart() != null
                && !dto.getPaySumStart().equals("")
                && (dto.getPaySumEnd() == null || dto.getPaySumEnd().equals(""))) {
              criterion += " (select sum(aa2.org_pay)+sum(aa2.emp_pay) from aa002 aa2 where aa2.org_id(+)=a1.id and aa2.pay_status in ('1','3','4')) >=? and ";
              parameters.add(dto.getPaySumStart());
            }
            if (dto.getPaySumEnd() != null
                && !dto.getPaySumEnd().equals("")
                && (dto.getPaySumStart() == null || dto.getPaySumStart()
                    .equals(""))) {
              criterion += " (select sum(aa2.org_pay)+sum(aa2.emp_pay) from aa002 aa2 where aa2.org_id(+)=a1.id and aa2.pay_status in ('1','3','4')) <=? and ";
              parameters.add(dto.getPaySumEnd());
            }
            // ���ݹ��������
            if (dto.getBalanceStart() != null
                && !dto.getBalanceStart().equals("")
                && dto.getBalanceEnd() != null
                && !dto.getBalanceEnd().equals("")) {
              criterion += " (select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2 where aa2.org_id(+)=a1.id) between ? and ? and ";
              parameters.add(dto.getBalanceStart());
              parameters.add(dto.getBalanceEnd());
            }
            if (dto.getBalanceStart() != null
                && !dto.getBalanceStart().equals("")
                && (dto.getBalanceEnd() == null || dto.getBalanceEnd().equals(
                    ""))) {
              criterion += " (select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2 where aa2.org_id(+)=a1.id) >=? and ";
              parameters.add(dto.getBalanceStart());
            }
            if (dto.getBalanceEnd() != null
                && !dto.getBalanceEnd().equals("")
                && (dto.getBalanceStart() == null || dto.getBalanceStart()
                    .equals(""))) {
              criterion += " (select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2 where aa2.org_id(+)=a1.id) <=? and ";
              parameters.add(dto.getBalanceEnd());
            }
            // ���ݹ�������ѯ
            if (dto.getOverPayStart() != null
                && !dto.getOverPayStart().equals("")
                && dto.getOverPayEnd() != null
                && !dto.getOverPayEnd().equals("")) {
              criterion += " (select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type='C' and aa01.biz_status='5') between ? and ? and ";
              parameters.add(dto.getOverPayStart());
              parameters.add(dto.getOverPayEnd());
            }
            if (dto.getOverPayStart() != null
                && !dto.getOverPayStart().equals("")
                && (dto.getOverPayEnd() == null || dto.getOverPayEnd().equals(
                    ""))) {
              criterion += " (select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type='C' and aa01.biz_status='5') >=? and ";
              parameters.add(dto.getOverPayStart());
            }
            if (dto.getOverPayEnd() != null
                && !dto.getOverPayEnd().equals("")
                && (dto.getOverPayStart() == null || dto.getOverPayStart()
                    .equals(""))) {
              criterion += " (select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type='C' and aa01.biz_status='5') <=? and ";
              parameters.add(dto.getOverPayEnd());
            }
            // ���ݵ�λ�������²�ѯ
            if (dto.getOrgPayMonthStart() != null
                && !dto.getOrgPayMonthStart().equals("")
                && dto.getOrgPayMonthEnd() != null
                && !dto.getOrgPayMonthEnd().equals("")) {
              criterion += " (a2.org_pay_month between ? and ?) and ";
              parameters.add(dto.getOrgPayMonthStart());
              parameters.add(dto.getOrgPayMonthEnd());
            }
            if (dto.getOrgPayMonthStart() != null
                && !dto.getOrgPayMonthStart().equals("")
                && (dto.getOrgPayMonthEnd() == null || dto.getOrgPayMonthEnd()
                    .equals(""))) {
              criterion += " (a2.org_pay_month>=?) and ";
              parameters.add(dto.getOrgPayMonthStart());
            }
            if (dto.getOrgPayMonthEnd() != null
                && !dto.getOrgPayMonthEnd().equals("")
                && (dto.getOrgPayMonthStart() == null || dto
                    .getOrgPayMonthStart().equals(""))) {
              criterion += " (a2.org_pay_month<=?) and ";
              parameters.add(dto.getOrgPayMonthEnd());
            }
            // ���ݸ��˽������²�ѯ
            if (dto.getEmpPayMonthStart() != null
                && !dto.getEmpPayMonthStart().equals("")
                && dto.getEmpPayMonthEnd() != null
                && !dto.getEmpPayMonthEnd().equals("")) {
              criterion += " (a2.emp_pay_month between ? and ?) and ";
              parameters.add(dto.getEmpPayMonthStart());
              parameters.add(dto.getEmpPayMonthEnd());
            }
            if (dto.getEmpPayMonthStart() != null
                && !dto.getEmpPayMonthStart().equals("")
                && (dto.getEmpPayMonthEnd() == null || dto.getEmpPayMonthEnd()
                    .equals(""))) {
              criterion += " (a2.emp_pay_month>=?) and ";
              parameters.add(dto.getEmpPayMonthStart());
            }
            if (dto.getEmpPayMonthEnd() != null
                && !dto.getEmpPayMonthEnd().equals("")
                && (dto.getEmpPayMonthStart() == null || dto
                    .getEmpPayMonthStart().equals(""))) {
              criterion += " (a2.emp_pay_month<=?) and ";
              parameters.add(dto.getEmpPayMonthEnd());
            }
            // ����ְ��������ѯ
            if (dto.getEmpCountStart() != null
                && !dto.getEmpCountStart().equals("")
                && dto.getEmpCountEnd() != null
                && !dto.getEmpCountEnd().equals("")) {
              criterion += " (select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) between ? and ? and ";
              parameters.add(dto.getEmpCountStart());
              parameters.add(dto.getEmpCountEnd());
            }
            if (dto.getEmpCountStart() != null
                && !dto.getEmpCountStart().equals("")
                && (dto.getEmpCountEnd() == null || dto.getEmpCountEnd()
                    .equals(""))) {
              criterion += " (select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))>=? and ";
              parameters.add(dto.getEmpCountStart());
            }
            if (dto.getEmpCountEnd() != null
                && !dto.getEmpCountEnd().equals("")
                && (dto.getEmpCountStart() == null || dto.getEmpCountStart()
                    .equals(""))) {
              criterion += " (select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))<=? and ";
              parameters.add(dto.getEmpCountEnd());
            }
            // ���ݹ����ܶ��ѯ
            if (dto.getSalarySumStart() != null
                && !dto.getSalarySumStart().equals("")
                && dto.getSalarySumEnd() != null
                && !dto.getSalarySumEnd().equals("")) {
              criterion += " (select sum(aa2.salary_base) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) between ? and ? and ";
              parameters.add(dto.getSalarySumStart());
              parameters.add(dto.getSalarySumEnd());
            }
            if (dto.getSalarySumStart() != null
                && !dto.getSalarySumStart().equals("")
                && (dto.getSalarySumEnd() == null || dto.getSalarySumEnd()
                    .equals(""))) {
              criterion += " (select sum(aa2.salary_base) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))>=? and ";
              parameters.add(dto.getSalarySumStart());
            }
            if (dto.getSalarySumEnd() != null
                && !dto.getSalarySumEnd().equals("")
                && (dto.getSalarySumStart() == null || dto.getSalarySumStart()
                    .equals(""))) {
              criterion += " (select sum(aa2.salary_base) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))<=? and ";
              parameters.add(dto.getSalarySumEnd());
            }
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a1.id ";

          String od = order;
          if (od == null)
            od = "DESC";

          hql = hql + criterion + "order by " + ob + " " + od;
          System.err.println(hql);
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          Iterator iterate = query.list().iterator();

          OrgCollInfoFindResultDTO orgCollInfoFindResultDTO = null;
          List temp_list = new ArrayList();
          Object obj[] = null;

          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            orgCollInfoFindResultDTO = new OrgCollInfoFindResultDTO();
            orgCollInfoFindResultDTO.setOrgId(new Integer(obj[0].toString()));
            orgCollInfoFindResultDTO.setOrgPayMonth(obj[1].toString());
            orgCollInfoFindResultDTO.setEmpPayMonth(obj[2].toString());
            if (obj[3] != null) {
              orgCollInfoFindResultDTO.setOpenDate(obj[3].toString());
            }
            orgCollInfoFindResultDTO.setOrgName(obj[4].toString());
            orgCollInfoFindResultDTO.setOfficecode(obj[5].toString());
            orgCollInfoFindResultDTO.setCollectionBankId(obj[6].toString());
            if (obj[7] != null) {
              orgCollInfoFindResultDTO.setEmpRate(new BigDecimal(obj[7]
                  .toString()));
            }
            if (obj[8] != null) {
              orgCollInfoFindResultDTO.setOrgRate(new BigDecimal(obj[8]
                  .toString()));
            }
            // �����ܶ�
            if (obj[9] != null) {
              orgCollInfoFindResultDTO.setSalarySum(new BigDecimal(obj[9]
                  .toString()));
            }
            // ���������
            if (obj[10] != null) {
              orgCollInfoFindResultDTO.setBalance(new BigDecimal(obj[10]
                  .toString()));
            }
            // ְ������
            if (obj[11] != null) {
              orgCollInfoFindResultDTO
                  .setEmpSum(new Integer(obj[11].toString()));
            }
            // ��λ�ɶ�
            if (obj[12] != null) {
              orgCollInfoFindResultDTO.setOrgPay(new BigDecimal(obj[12]
                  .toString()));
            }
            // ְ���ɶ�
            if (obj[13] != null) {
              orgCollInfoFindResultDTO.setEmpPay(new BigDecimal(obj[13]
                  .toString()));
            }
            // �������
            if (obj[14] != null) {
              orgCollInfoFindResultDTO.setOverPay(new BigDecimal(obj[14]
                  .toString()));
            }
            // �����ܶ�
            if (obj[12] != null & obj[13] != null) {
              orgCollInfoFindResultDTO.setPaySum(new BigDecimal(new BigDecimal(
                  obj[12].toString()).doubleValue()
                  + new BigDecimal(obj[13].toString()).doubleValue()));
            }
            // ���������
            if (obj[10] != null & obj[14] != null) {
              orgCollInfoFindResultDTO.setPaybalance(new BigDecimal(
                  new BigDecimal(obj[10].toString()).doubleValue()
                      + new BigDecimal(obj[14].toString()).doubleValue()));
            }
            if (obj[15] != null) {
              orgCollInfoFindResultDTO.setPersonCount(new BigDecimal(obj[15]
                  .toString()));
            }
            if (obj[16] != null) {
              orgCollInfoFindResultDTO.setCharacter(obj[16].toString());
            }
            temp_list.add(orgCollInfoFindResultDTO);
          }
          session.clear();
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return tableList;
  }

  /**
   * ͳ�Ʋ�ѯ-�����λ�鼯��Ϣ�ļ�¼����ϼƵ�����
   * 
   * @param dto ��ѯ������DTO
   * @param securityInfo
   * @return ��ѯ���ܼ�¼��
   */
  public Object[] queryStatisticCountOrgCollInfo(final OrgCollInfoFindDTO dto,
      final SecurityInfo securityInfo) {
    List tableList = new ArrayList();
    Object[] resultList = new Object[2];
    try {
      tableList = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String criterion = "";
          Vector parameters = new Vector();
          String hql = "select distinct "
              + "a1.id as id,"
              + "nvl((select max(aa302.pay_month) from AA302 aa302,AA301 aa301 "
              + "where aa301.id=aa302.pay_head_id and aa301.pay_type in ('A','B') "
              + "and aa301.pay_status in ('3','4','5') and aa301.pay_model in ('1','2') "
              + "and aa301.org_id = a1.id),a1.org_pay_month) as opm,"
              + "nvl((select max(aa302.pay_month) from AA302 aa302,AA301 aa301 "
              + "where aa301.id=aa302.pay_head_id and aa301.pay_type in ('A','B') "
              + "and aa301.pay_status in ('3','4','5') and aa301.pay_model in ('1','3') "
              + "and aa301.org_id = a1.id),a1.emp_pay_month) as epm,"
              + "b1.open_date as od,"
              + "b1.name as name,"
              + "b1.officecode as offname,"
              + "b1.collection_bank_id as bankname,"
              + "a1.emp_rate as emprate,"
              + "a1.org_rate as orgrate,"
              + "(select nvl(sum(aa2.salary_base),0) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as salsum,"
              + "(select nvl(sum(aa01.credit-aa01.debit),0) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type in ('C','J') and aa01.biz_status='5') as cd,"
              + "(select nvl(sum(aa2.pre_balance+aa2.cur_balance),0) from aa002 aa2 where aa2.org_id=a1.id ) as pc_balance,"
              + "(select nvl(sum(aa2.org_pay),0) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as opay,"
              + "(select nvl(sum(aa2.emp_pay),0) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as epay,"
              + "(select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) as empcount, "
              + "(select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','2','3','4','5')) as personcount, "
              + "b1.character "
              + "from aa002 a2,aa001 a1,ba001 b1 where a1.orginfo_id=b1.id and a1.id=a2.org_id(+) and a1.id "
              + securityInfo.getGjjSecuritySQL();

          if (dto != null) {
            // ���ݵ�λ��Ų�ѯ
            if (dto.getOrgId() != null && !dto.getOrgId().equals("")) {
              criterion += "a1.id=? and ";
              parameters.add(dto.getOrgId());
            }
            // ���ݰ��´���ѯ
            if (dto.getOfficecode() != null && !dto.getOfficecode().equals("")) {
              criterion += "b1.officecode=? and ";
              parameters.add(dto.getOfficecode());
            }
            // ���ݹ鼯���в�ѯ
            if (dto.getCollectionBankId() != null
                && !dto.getCollectionBankId().equals("")) {
              criterion += "b1.collection_bank_id=? and ";
              parameters.add(dto.getCollectionBankId());
            }
            // �������ܲ��Ų�ѯ
            if (dto.getDeptInCharge() != null
                && !dto.getDeptInCharge().equals("")) {
              criterion += "b1.dept_in_charge=? and ";
              parameters.add(dto.getDeptInCharge());
            }
            // ���ݵ�λ���ʲ�ѯ
            if (dto.getCharacter() != null && !dto.getCharacter().equals("")) {
              criterion += "b1.character=? and ";
              parameters.add(dto.getCharacter());
            }
            // ���ݵ�λ����
            if (dto.getOrgName() != null && !dto.getOrgName().equals("")) {
              criterion += "b1.name like ? and ";
              parameters.add("%" + dto.getOrgName() + "%");
            }
            // ��λ����
            if (dto.getOrgrateStart() != null
                && !dto.getOrgrateStart().equals("")) {
              criterion += "to_number(a1.org_rate) >= ? and ";
              parameters.add(dto.getOrgrateStart());
            }
            if (dto.getOrgrateEnd() != null && !dto.getOrgrateEnd().equals("")) {
              criterion += "to_number(a1.org_rate) <= ? and ";
              parameters.add(dto.getOrgrateEnd());
            }
            // ְ������
            if (dto.getEmprateStart() != null
                && !dto.getEmprateStart().equals("")) {
              criterion += "to_number(a1.emp_rate) >= ? and ";
              parameters.add(dto.getEmprateStart());
            }
            if (dto.getEmprateEnd() != null && !dto.getEmprateEnd().equals("")) {
              criterion += "to_number(a1.emp_rate) <= ? and ";
              parameters.add(dto.getEmprateEnd());
            }
            // ���ݵ�λ״̬��ѯ
            if (dto.getOpenStatus() != null && !dto.getOpenStatus().equals("")) {
              criterion += "b1.openstatus=? and ";
              parameters.add(dto.getOpenStatus());
            }
            // ���ݽɴ淽ʽ��ѯ
            if (dto.getPayMode() != null && !dto.getPayMode().equals("")) {
              criterion += "a1.pay_mode=? and ";
              parameters.add(dto.getPayMode());
            }
            // �������ڵ�����ѯ
            if (dto.getRegion() != null && !dto.getRegion().equals("")) {
              criterion += "b1.region=? and ";
              parameters.add(dto.getRegion());
            }
            // ����ԭ��λ��Ų�ѯ
            if (dto.getOldOrgId() != null && !dto.getOldOrgId().equals("")) {
              criterion += "a1.org_id_old=? and ";
              parameters.add(dto.getOldOrgId());
            }
            // ���ݻ���Ա��ѯ
            if (dto.getInspector() != null && !dto.getInspector().equals("")) {
              criterion += "b1.inspector like ? and ";
              parameters.add("%" + dto.getInspector() + "%");
            }
            // ���ݷ�н�ղ�ѯ
            if (dto.getPayDate() != null && !dto.getPayDate().equals("")) {
              criterion += "b1.paydate=? and ";
              parameters.add(dto.getPayDate());
            }
            // ������֯���������ѯ
            if (dto.getCode() != null && !dto.getCode().equals("")) {
              criterion += "b1.code=? and ";
              parameters.add(dto.getCode());
            }
            // ���ݿ������ڲ�ѯ
            if (dto.getOpenDateStart() != null
                && !dto.getOpenDateStart().equals("")
                && dto.getOpenDateTimeEnd() != null
                && !dto.getOpenDateTimeEnd().equals("")) {// �п�ʼ���ڽ�������
              criterion += " (b1.open_date between ? and ?) and ";
              parameters.add(dto.getOpenDateStart());
              parameters.add(dto.getOpenDateTimeEnd());
            }
            if (dto.getOpenDateStart() != null
                && !dto.getOpenDateStart().equals("")
                && (dto.getOpenDateTimeEnd() == null || dto
                    .getOpenDateTimeEnd().equals(""))) {// �п�ʼ�����޽�������
              criterion += " b1.open_date >= ? and ";
              parameters.add(dto.getOpenDateStart());
            }
            if (dto.getOpenDateTimeEnd() != null
                && !dto.getOpenDateTimeEnd().equals("")
                && (dto.getOpenDateStart() == null || dto.getOpenDateStart()
                    .equals(""))) {// �н��������޿�ʼ����
              criterion += " b1.open_date <= ? and ";
              parameters.add(dto.getOpenDateTimeEnd());
            }
            // ���ݻ���ܶ��ѯ
            if (dto.getPaySumStart() != null
                && !dto.getPaySumStart().equals("")
                && dto.getPaySumEnd() != null && !dto.getPaySumEnd().equals("")) {
              criterion += " (select sum(aa2.org_pay)+sum(aa2.emp_pay) from aa002 aa2 where aa2.org_id(+)=a1.id and aa2.pay_status in ('1','3','4')) between ? and ? and ";
              parameters.add(dto.getPaySumStart());
              parameters.add(dto.getPaySumEnd());
            }
            if (dto.getPaySumStart() != null
                && !dto.getPaySumStart().equals("")
                && (dto.getPaySumEnd() == null || dto.getPaySumEnd().equals(""))) {
              criterion += " (select sum(aa2.org_pay)+sum(aa2.emp_pay) from aa002 aa2 where aa2.org_id(+)=a1.id and aa2.pay_status in ('1','3','4')) >=? and ";
              parameters.add(dto.getPaySumStart());
            }
            if (dto.getPaySumEnd() != null
                && !dto.getPaySumEnd().equals("")
                && (dto.getPaySumStart() == null || dto.getPaySumStart()
                    .equals(""))) {
              criterion += " (select sum(aa2.org_pay)+sum(aa2.emp_pay) from aa002 aa2 where aa2.org_id(+)=a1.id and aa2.pay_status in ('1','3','4')) <=? and ";
              parameters.add(dto.getPaySumEnd());
            }
            // ���ݹ��������
            if (dto.getBalanceStart() != null
                && !dto.getBalanceStart().equals("")
                && dto.getBalanceEnd() != null
                && !dto.getBalanceEnd().equals("")) {
              criterion += " (select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2 where aa2.org_id(+)=a1.id) between ? and ? and ";
              parameters.add(dto.getBalanceStart());
              parameters.add(dto.getBalanceEnd());
            }
            if (dto.getBalanceStart() != null
                && !dto.getBalanceStart().equals("")
                && (dto.getBalanceEnd() == null || dto.getBalanceEnd().equals(
                    ""))) {
              criterion += " (select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2 where aa2.org_id(+)=a1.id) >=? and ";
              parameters.add(dto.getBalanceStart());
            }
            if (dto.getBalanceEnd() != null
                && !dto.getBalanceEnd().equals("")
                && (dto.getBalanceStart() == null || dto.getBalanceStart()
                    .equals(""))) {
              criterion += " (select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2 where aa2.org_id(+)=a1.id) <=? and ";
              parameters.add(dto.getBalanceEnd());
            }
            // ���ݹ�������ѯ
            if (dto.getOverPayStart() != null
                && !dto.getOverPayStart().equals("")
                && dto.getOverPayEnd() != null
                && !dto.getOverPayEnd().equals("")) {
              criterion += " (select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type='C' and aa01.biz_status='5') between ? and ? and ";
              parameters.add(dto.getOverPayStart());
              parameters.add(dto.getOverPayEnd());
            }
            if (dto.getOverPayStart() != null
                && !dto.getOverPayStart().equals("")
                && (dto.getOverPayEnd() == null || dto.getOverPayEnd().equals(
                    ""))) {
              criterion += " (select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type='C' and aa01.biz_status='5') >=? and ";
              parameters.add(dto.getOverPayStart());
            }
            if (dto.getOverPayEnd() != null
                && !dto.getOverPayEnd().equals("")
                && (dto.getOverPayStart() == null || dto.getOverPayStart()
                    .equals(""))) {
              criterion += " (select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.org_id=a1.id and aa01.biz_type='C' and aa01.biz_status='5') <=? and ";
              parameters.add(dto.getOverPayEnd());
            }
            // ���ݵ�λ�������²�ѯ
            if (dto.getOrgPayMonthStart() != null
                && !dto.getOrgPayMonthStart().equals("")
                && dto.getOrgPayMonthEnd() != null
                && !dto.getOrgPayMonthEnd().equals("")) {
              criterion += " (a2.org_pay_month between ? and ?) and ";
              parameters.add(dto.getOrgPayMonthStart());
              parameters.add(dto.getOrgPayMonthEnd());
            }
            if (dto.getOrgPayMonthStart() != null
                && !dto.getOrgPayMonthStart().equals("")
                && (dto.getOrgPayMonthEnd() == null || dto.getOrgPayMonthEnd()
                    .equals(""))) {
              criterion += " (a2.org_pay_month>=?) and ";
              parameters.add(dto.getOrgPayMonthStart());
            }
            if (dto.getOrgPayMonthEnd() != null
                && !dto.getOrgPayMonthEnd().equals("")
                && (dto.getOrgPayMonthStart() == null || dto
                    .getOrgPayMonthStart().equals(""))) {
              criterion += " (a2.org_pay_month<=?) and ";
              parameters.add(dto.getOrgPayMonthEnd());
            }
            // ���ݸ��˽������²�ѯ
            if (dto.getEmpPayMonthStart() != null
                && !dto.getEmpPayMonthStart().equals("")
                && dto.getEmpPayMonthEnd() != null
                && !dto.getEmpPayMonthEnd().equals("")) {
              criterion += " (a2.emp_pay_month between ? and ?) and ";
              parameters.add(dto.getEmpPayMonthStart());
              parameters.add(dto.getEmpPayMonthEnd());
            }
            if (dto.getEmpPayMonthStart() != null
                && !dto.getEmpPayMonthStart().equals("")
                && (dto.getEmpPayMonthEnd() == null || dto.getEmpPayMonthEnd()
                    .equals(""))) {
              criterion += " (a2.emp_pay_month>=?) and ";
              parameters.add(dto.getEmpPayMonthStart());
            }
            if (dto.getEmpPayMonthEnd() != null
                && !dto.getEmpPayMonthEnd().equals("")
                && (dto.getEmpPayMonthStart() == null || dto
                    .getEmpPayMonthStart().equals(""))) {
              criterion += " (a2.emp_pay_month<=?) and ";
              parameters.add(dto.getEmpPayMonthEnd());
            }
            // ����ְ��������ѯ
            if (dto.getEmpCountStart() != null
                && !dto.getEmpCountStart().equals("")
                && dto.getEmpCountEnd() != null
                && !dto.getEmpCountEnd().equals("")) {
              criterion += " (select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) between ? and ? and ";
              parameters.add(dto.getEmpCountStart());
              parameters.add(dto.getEmpCountEnd());
            }
            if (dto.getEmpCountStart() != null
                && !dto.getEmpCountStart().equals("")
                && (dto.getEmpCountEnd() == null || dto.getEmpCountEnd()
                    .equals(""))) {
              criterion += " (select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))>=? and ";
              parameters.add(dto.getEmpCountStart());
            }
            if (dto.getEmpCountEnd() != null
                && !dto.getEmpCountEnd().equals("")
                && (dto.getEmpCountStart() == null || dto.getEmpCountStart()
                    .equals(""))) {
              criterion += " (select count(aa2.pk_id) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))<=? and ";
              parameters.add(dto.getEmpCountEnd());
            }
            // ���ݹ����ܶ��ѯ
            if (dto.getSalarySumStart() != null
                && !dto.getSalarySumStart().equals("")
                && dto.getSalarySumEnd() != null
                && !dto.getSalarySumEnd().equals("")) {
              criterion += " (select sum(aa2.salary_base) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4')) between ? and ? and ";
              parameters.add(dto.getSalarySumStart());
              parameters.add(dto.getSalarySumEnd());
            }
            if (dto.getSalarySumStart() != null
                && !dto.getSalarySumStart().equals("")
                && (dto.getSalarySumEnd() == null || dto.getSalarySumEnd()
                    .equals(""))) {
              criterion += " (select sum(aa2.salary_base) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))>=? and ";
              parameters.add(dto.getSalarySumStart());
            }
            if (dto.getSalarySumEnd() != null
                && !dto.getSalarySumEnd().equals("")
                && (dto.getSalarySumStart() == null || dto.getSalarySumStart()
                    .equals(""))) {
              criterion += " (select sum(aa2.salary_base) from aa002 aa2 where aa2.org_id=a1.id and aa2.pay_status in ('1','3','4'))<=? and ";
              parameters.add(dto.getSalarySumEnd());
            }
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          System.err.println(hql);
          Query query = session.createSQLQuery(hql);

          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          session.clear();
          Object obj[] = null;
          OrgCollinfoSumDTO orgCollinfoSumDTO_temp = null;
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // ����ѯ�Ľ����װ��DTO��
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            orgCollinfoSumDTO_temp = new OrgCollinfoSumDTO();
            orgCollinfoSumDTO_temp.setOrgId(new Integer(obj[0].toString()));
            orgCollinfoSumDTO_temp.setOrgPayMonth(obj[1].toString());
            orgCollinfoSumDTO_temp.setEmpPayMonth(obj[2].toString());
            if (obj[3] != null) {
              orgCollinfoSumDTO_temp.setOpenDate(obj[3].toString());
            }
            orgCollinfoSumDTO_temp.setOrgName(obj[4].toString());
            orgCollinfoSumDTO_temp.setOfficecode(obj[5].toString());
            orgCollinfoSumDTO_temp.setCollectionBankId(obj[6].toString());
            orgCollinfoSumDTO_temp
                .setEmpRate(new BigDecimal(obj[7].toString()));
            orgCollinfoSumDTO_temp
                .setOrgRate(new BigDecimal(obj[8].toString()));

            orgCollinfoSumDTO_temp.setSalarySum(new BigDecimal(obj[9]
                .toString()));
            orgCollinfoSumDTO_temp
                .setOverPay(new BigDecimal(obj[10].toString()));
            orgCollinfoSumDTO_temp
                .setBalance(new BigDecimal(obj[11].toString()));
            orgCollinfoSumDTO_temp
                .setOrgPay(new BigDecimal(obj[12].toString()));
            orgCollinfoSumDTO_temp
                .setEmpPay(new BigDecimal(obj[13].toString()));
            orgCollinfoSumDTO_temp.setEmpSum(new Integer(obj[14].toString()));
            orgCollinfoSumDTO_temp.setPersonCount(new Integer(obj[15]
                .toString()));
            orgCollinfoSumDTO_temp.setPaySum(new BigDecimal(new BigDecimal(
                obj[12].toString()).doubleValue()
                + new BigDecimal(obj[13].toString()).doubleValue()));
            orgCollinfoSumDTO_temp.setPaybalance(new BigDecimal(new BigDecimal(
                obj[10].toString()).doubleValue()
                + new BigDecimal(obj[11].toString()).doubleValue()));
            if (obj[16] != null) {
              orgCollinfoSumDTO_temp.setCharacter(obj[16].toString());
            }
            temp_list.add(orgCollinfoSumDTO_temp);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    resultList[0] = tableList;
    resultList[1] = tableList.size() + "";
    return resultList;
  }

  /**
   * ���ͳ�Ʋ�ѯ-��λ�鼯��Ϣ��ѯ�ĺϼƲ���
   * 
   * @param securityInfo
   * @return
   */
  public OrgCollinfoSumDTO queryStatisticSumOrgCollInfo(
      final SecurityInfo securityInfo) {
    OrgCollinfoSumDTO orgCollinfoSumDTO = null;
    try {
      orgCollinfoSumDTO = (OrgCollinfoSumDTO) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String hql = "select "
                  + "distinct "
                  + "(select sum(aa2.org_pay) from aa002 aa2)+(select sum(aa2.emp_pay) from aa002 aa2) as sumpay,"
                  + "(select sum(aa2.salary_base) from aa002 aa2) as sumsalary,"
                  + "(select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2) as pc_balance,"
                  + "(select count(aa2.pk_id) from aa002 aa2) as empcount,"
                  + "(select sum(aa2.org_pay) from aa002 aa2) as opay,"
                  + "(select sum(aa2.emp_pay) from aa002 aa2) as epay,"
                  + "(select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.biz_type='C' and aa01.biz_status='5') as cd,"
                  + "(select sum(aa2.pre_balance+aa2.cur_balance) from aa002 aa2)+(select sum(aa01.credit-aa01.debit) from aa101 aa01 where aa01.biz_type='C' and aa01.biz_status='5') "
                  + "from aa002 a2,aa001 a1,ba001 b1 where a1.orginfo_id=b1.id and a1.id=a2.org_id(+) and a1.id "
                  + securityInfo.getGjjSecuritySQL();
              System.out.println(hql);
              Object[] obj = null;
              Query query = session.createSQLQuery(hql);
              obj = (Object[]) query.uniqueResult();
              OrgCollinfoSumDTO temp_dto = new OrgCollinfoSumDTO();
              temp_dto.setPaySum(new BigDecimal(obj[0].toString()));
              temp_dto.setSalarySum(new BigDecimal(obj[1].toString()));
              temp_dto.setBalance(new BigDecimal(obj[2].toString()));
              temp_dto.setEmpSum(new Integer(obj[3].toString()));
              temp_dto.setOrgPay(new BigDecimal(obj[4].toString()));
              temp_dto.setEmpPay(new BigDecimal(obj[5].toString()));
              temp_dto.setOverPay(new BigDecimal(obj[6].toString()));
              temp_dto.setPaybalance(new BigDecimal(obj[7].toString()));

              return temp_dto;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orgCollinfoSumDTO;
  }

  /**
   * author wsh ����ϵͳ����Ŀ��ϵ���� ��ѯ��λ����
   * 
   * @param calculRelaValue �����ϵֵ
   * @2007-10-15
   * @return String
   */
  public String findOrgName_wsh(final String calculRelaValue) {
    String orgName = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select b.name from aa001 a,ba001 b where a.orginfo_id=b.id and a.id=? ";
            Vector parameters = new Vector();
            if (calculRelaValue != null) {
              parameters.add(calculRelaValue);
            }
            Query query = session.createSQLQuery(hql);
            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            Object obj = null;
            obj = (Object) query.uniqueResult();
            String temp_name = "";
            if (obj != null) {
              temp_name = obj.toString();
            }
            return temp_name;
          }
        });
    return orgName;
  }

  /**
   * author wsh ��Ŀ��ϵ���� ��ѯ������ĵ�λ��Ϣ
   * 
   * @2007-10-16
   * @return List
   */
  public List queryOrgList_wsh(final String office, final String loanBankId,
      final int start, final int pageSize, final SecurityInfo securityInfo,
      final List officeList, final List loanbankList) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List queryList = new ArrayList();
        List temp_list = new ArrayList();
        String hql = "select a.id             orgid,"
            + "b.name           orgname," + "c.name           officename,"
            + "d.coll_bank_name collbankname "
            + "from aa001 a, ba001 b, bb101 c, bb105 d "
            + "where a.orginfo_id = b.id "
            + "and b.collection_bank_id = d.coll_bank_id "
            + "and c.id = d.office "
            + "and d.status = 1 and b.openstatus in(2,3,4) and a.id "
            + securityInfo.getGjjSecuritySQL() + " and ";
        Vector parameters = new Vector();
        String criterion = "";

        if (office != null && !office.equals("")) {
          criterion += " b.officecode=? and ";
          parameters.add(office);
        } else if (officeList != null && officeList.size() > 0) {
          criterion += "( ";
          for (int i = 0; i < officeList.size(); i++) {
            criterion += " b.officecode = ? or ";
            parameters.add(officeList.get(i).toString());
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (loanBankId != null && !loanBankId.equals("")) {
          criterion += " b.collection_bank_id=? and ";
          parameters.add(loanBankId);
        } else if (loanbankList != null && loanbankList.size() > 0) {
          criterion += "( ";
          for (int i = 0; i < loanbankList.size(); i++) {
            criterion += "  b.collection_bank_id = ? or ";
            parameters.add(loanbankList.get(i).toString());
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (criterion.length() != 0)
          criterion = criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion + "order by orgid desc ";
        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);
        queryList = query.list();
        Iterator it = queryList.iterator();
        Object obj[] = null;
        while (it.hasNext()) {
          SubjectrelationTaPop3DTO subjectrelationTaPop3DTO = new SubjectrelationTaPop3DTO();
          obj = (Object[]) it.next();
          if (obj != null) {
            if (obj != null) {
              if (obj[0].toString() != null) {
                subjectrelationTaPop3DTO.setId(obj[0].toString());
              }
              if (obj[1].toString() != null) {
                subjectrelationTaPop3DTO.setOrgName(obj[1].toString());
              }
              if (obj[2].toString() != null) {
                subjectrelationTaPop3DTO.setName(obj[2].toString());
              }
              if (obj[3].toString() != null) {
                subjectrelationTaPop3DTO.setBankName(obj[3].toString());
              }
            }
            temp_list.add(subjectrelationTaPop3DTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * author wsh ��Ŀ��ϵ���� ��ѯ������ĵ�λ������
   * 
   * @2007-10-16
   * @return List
   */
  public List queryOrgCountList_wsh(final String office,
      final String loanBankId, final SecurityInfo securityInfo,
      final List officeList, final List loanbankList) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a.id orgid "
            + "from aa001 a, ba001 b, bb101 c, bb105 d "
            + "where a.orginfo_id = b.id "
            + "and b.collection_bank_id = d.coll_bank_id "
            + "and c.id = d.office "
            + "and d.status = 1 and b.openstatus in(2,3,4) and a.id "
            + securityInfo.getGjjSecuritySQL() + " and ";
        Vector parameters = new Vector();
        String criterion = "";

        if (office != null && !office.equals("")) {
          criterion += " b.officecode=? and ";
          parameters.add(office);
        } else if (officeList != null && officeList.size() > 0) {
          criterion += "( ";
          for (int i = 0; i < officeList.size(); i++) {
            criterion += " b.officecode = ? or ";
            parameters.add(officeList.get(i).toString());
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (loanBankId != null && !loanBankId.equals("")) {
          criterion += " b.collection_bank_id=? and ";
          parameters.add(loanBankId);
        } else if (loanbankList != null && loanbankList.size() > 0) {
          criterion += "( ";
          for (int i = 0; i < loanbankList.size(); i++) {
            criterion += "  b.collection_bank_id = ? or ";
            parameters.add(loanbankList.get(i).toString());
          }
          criterion = criterion.substring(0, criterion.lastIndexOf("or"));
          criterion += ") and";
        }
        if (criterion.length() != 0)
          criterion = criterion.substring(0, criterion.lastIndexOf("and"));
        hql = hql + criterion + "order by orgid desc ";
        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        return query.list();
      }
    });
    return list;
  }

  /**
   * author wsh ��Ŀ��ϵ���� ��ѯ�����µ�λ��idList
   * 
   * @param collBankid ����id
   * @2007-10-16
   * @return List
   */
  public List queryOrgIdList_wsh(final String collBankid) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List queryList = new ArrayList();
        List temp_list = new ArrayList();
        String hql = "select a.id orgid " + "from aa001 a, ba001 b "
            + "where a.orginfo_id = b.id " + "and b.collection_bank_id = ? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, collBankid);
        queryList = query.list();
        Iterator it = queryList.iterator();
        Object obj = null;
        while (it.hasNext()) {
          String orgId = "";
          obj = (Object) it.next();
          if (obj != null) {
            if (obj != null) {
              if (obj.toString() != null) {
                orgId = obj.toString();
              }
            }
            temp_list.add(orgId);
          }
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * author wsh ��Ŀ��ϵ���� ��ѯ���´��µ�λ��idList
   * 
   * @param office ���´�id
   * @2007-10-16
   * @return List
   */
  public List queryOOrgIdList_wsh(final String office) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List queryList = new ArrayList();
        List temp_list = new ArrayList();
        String hql = "select a.id from aa001 a, ba001 b, bb101 c, bb105 d "
            + "where a.orginfo_id = b.id and "
            + "b.collection_bank_id = d.coll_bank_id and "
            + "d.office = c.id and c.id = ? ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, office);
        queryList = query.list();
        Iterator it = queryList.iterator();
        Object obj = null;
        while (it.hasNext()) {
          String orgId = "";
          obj = (Object) it.next();
          if (obj != null) {
            if (obj != null) {
              if (obj.toString() != null) {
                orgId = obj.toString();
              }
            }
            temp_list.add(orgId);
          }
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * author wsh ��Ŀ��ϵ���� ��ѯ��λ��������id
   * 
   * @param orgid ��λid
   * @2007-10-16
   * @return List
   */
  public String queryCollBankId_wsh(final String orgid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String ogid = orgid;
            String id = "";
            String hql = "select c.coll_bank_id from aa001 a,ba001 b, bb105 c where a.orginfo_id=b.id and b.collection_bank_id=c.coll_bank_id and a.id=? ";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, orgid);
            id = query.uniqueResult().toString();
            return id;
          }
        });
    return id;
  }

  /**
   * author wsh ��Ŀ��ϵ���� ��ѯ��λ���ڰ��´�id
   * 
   * @param orgid ��λid
   * @2007-10-16
   * @return String
   */
  public String queryOfficeId_wsh(final String orgid) {
    String id = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String id = "";
            String hql = "select c.id from aa001 a,ba001 b ,bb101 c, bb105 d where a.orginfo_id=b.id and b.collection_bank_id=d.coll_bank_id and d.office=c.id and a.id=?";
            Query query = session.createSQLQuery(hql);
            query.setParameter(0, orgid);
            id = query.uniqueResult().toString();
            return id;
          }
        });
    return id;
  }

  /**
   * author wsh ��Ŀ��ϵ���� ��ѯ���е�λ��idList
   * 
   * @2007-10-16
   * @return List
   */
  public List queryOrgIdList_wsh() {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List queryList = new ArrayList();
        String hql = "select a.id from aa001 a";
        Query query = session.createSQLQuery(hql);
        queryList = query.list();
        return queryList;
      }
    });
    return list;
  }

  /**
   * ��ѯ��λ���۵����б�
   * 
   * @param securityInfo
   * @return �����б�����ݰ�����λ��ţ���λ���ƣ���λ���ۺ�
   * @author ���Ʒ�
   */
  public List queryOrgAgentlist(final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select a001.id,b001.name,a001.org_agent_num "
              + "from aa001 a001,ba001 b001 "
              + "where a001.orginfo_id=b001.id and a001.id "
              + securityInfo.getGjjSecuritySQL();
          Query query = session.createSQLQuery(hql);
          Iterator it = query.list().iterator();
          List temp_List = new ArrayList();
          OrgAgentExportDTO orgAgentExportDTO = null;
          Object[] obj = null;
          while (it.hasNext()) {
            orgAgentExportDTO = new OrgAgentExportDTO();
            obj = (Object[]) it.next();
            if (obj[0] != null) {
              orgAgentExportDTO.setOrgId(obj[0].toString());
            }
            if (obj[1] != null) {
              orgAgentExportDTO.setOrgName(obj[1].toString());
            }
            if (obj[2] != null) {
              orgAgentExportDTO.setOrgAgentNum(obj[2].toString());
            }
            temp_List.add(orgAgentExportDTO);
          }
          return temp_List;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * �޸ĵ�λ���ۺŵķ���
   * 
   * @param orgId
   * @param orgAgentNum
   */
  public void updateOrgAgentNum(final String orgId, final String orgAgentNum) {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " update Org org set org.orgAgentNum=? "
              + "where org.id=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, orgAgentNum);
          query.setParameter(1, new Integer(orgId));
          query.executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ��ѯ����Ӵ��ۺ�ʱ���Ƿ����Ҫ��ӵĵ�λ
   * 
   * @param orgId
   * @param securityInfo
   * @return
   */
  public int queryOrgId(final String orgId, final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a001.id from aa001 a001 "
              + "where a001.id=? and a001.id "
              + securityInfo.getGjjSecuritySQL();
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, new Integer(orgId));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * �ڵ�λ����ʱ�жϵ�λ���ۺ��Ƿ����
   * 
   * @param orgAgentNum
   * @param securityInfo
   * @return
   */
  public int queryOrgAgentNumBySave(final String orgAgentNum,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a001.id from aa001 a001 "
              + "where a001.org_agent_num=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgAgentNum);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * ��ѯ���ݿ����Ƿ�����ͬ�Ĵ��ۺ�
   * 
   * @param orgAgentNum
   * @param securityInfo
   * @return
   */
  public int queryOrgAgentNum(final String orgAgentNum, final String orgId,
      final SecurityInfo securityInfo) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select a001.id from aa001 a001 "
              + "where a001.org_agent_num=? and (a001.id>? or a001.id<?)";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgAgentNum);
          query.setParameter(1, new Integer(orgId));
          query.setParameter(2, new Integer(orgId));
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list.size();
  }

  /**
   * �ۼƹ鼯��ѯ
   * 
   * @author ���
   * @pram condition String @ 2007-12-04
   * @return List
   */
  public List findCumulativeinfo(final SecurityInfo securityInfo,
      final String officeCode, final String collectionBankId,
      final String preYear, final String preMonth1, final String preMonth2,
      final String curYear1, final String curYear2, final String year)
      throws Exception, BusinessException {
    try {

    } catch (Exception e) {
      // TODO: handle exception
    }
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List returnList = new ArrayList();
        try {
          String myCondition = " and ba001.officecode in (" + officeCode
              + ") and ba001.collection_bank_id in (" + collectionBankId
              + ") and ba001.openstatus!=1 ";
          String myConditionFlow = " and aa101.officecode in (" + officeCode
              + ") and aa101.moneybank in (" + collectionBankId + ")";
          String montha = curYear2.substring(4, 6);
          int month = Integer.parseInt(montha);
          String pyear = String.valueOf(Integer.parseInt(year) - 1);
          String cyear = String.valueOf(Integer.parseInt(year));
          /**
           * ע���������˫Ѽɽ�������Ŀ��ѯ����ĸ������ݣ����� ʵ�ɵ�λ����ʵ��ְ������ʵ�ɴ�� ��ô����ע�����´��룬����
           * hql1�У�ÿ��select����У�ȥ����Ӧ�� String
           */
          // ��ʼ
          // String sjdws="+(select nvl(sum(t.sjdws),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*ʵ�ɵ�λ��*/
          // String sjzgs="+(select nvl(sum(t.sjzgs),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*ʵ��ְ����*/
          // String sjce="+(select nvl(sum(t.sjce),0) from tb_historydata t
          // where t.office_code in ("+officeCode+"))";/*ʵ�ɴ��*/
          // String lxsr="+(select nvl(sum(t.lxsr),0) from tb_historydata t
          // where t.office_code in ("+officeCode+"))";/*��Ϣ����*/
          // String zqrs="+(select nvl(sum(t.zqrs),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*֧ȡ����*/
          // String zqje="+(select nvl(sum(t.zqje),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*֧ȡ���*/
          // String qzgf="+(select nvl(sum(t.qzgf),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*ԭ��-����*/
          // String qzzzldgx="+(select nvl(sum(t.qzzzldgx),0) from
          // tb_historydata t where t.office_code in ("+officeCode+")
          // )";/*��ֹ�Ͷ���ϵ*/
          // String qzhkqc="+(select nvl(sum(t.qzhkqc),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*����ǩ��*/
          // String qzchdk="+(select nvl(sum(t.qzchdk),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*��������*/
          // String qzbtfz="+(select nvl(sum(t.qzbtfz),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*��������*/
          // String qzsw="+(select nvl(sum(t.qzsw),0) from tb_historydata t
          // where t.office_code in ("+officeCode+") )";/*����*/
          // ����
          // �����
          String hql1 = "select"
              +
              /* Ӧ�ɵ�λ�� */"(select nvl(count(distinct aa001.orginfo_id),0)  from BA001 ba001,AA001 aa001 where ba001.open_date<='"
              + preYear
              + "' and ba001.id=aa001.orginfo_id "
              + myCondition
              + " and ba001.id "
              + securityInfo.getGjjSecuritySQL()
              + ") as a,"
              +

              /* ʵ�ɵ�λ�� */// "((select nvl(count(distinct aa001.orginfo_id),0)
              // from BA001 ba001,AA001 aa001,AA101 aa101 where 1=1
              // and ba001.open_date<='"+preYear+"' and
              // ba001.id=aa001.orginfo_id and aa001.id= aa101.org_id
              // and aa101.biz_status='5'"+myCondition+" and
              // aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as b,"+
              "((select nvl(count(distinct aa101.org_id), 0) from BA001 ba001, AA001 aa001, AA101 aa101 where ba001.open_date <= '"
              + preYear
              + "' and ba001.id = aa001.orginfo_id and aa001.id = aa101.org_id and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as b, "
              +
              /* Ӧ��ְ���� */// "(select nvl(count(distinct aa002.emp_info_id),0)
              // from BA002 ba002,AA002 aa002 where 1=1 and
              // ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.pay_status in
              // ('1','3','4') "+myCondition+" and aa002.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as c,"+
              "(select nvl(count(distinct aa002.emp_info_id), 0) from BA002 ba002, AA002 aa002,Aa001 c,Ba001 ba001 where aa002.org_id=c.id and c.orginfo_id=ba001.id and ba002.opendate <= '"
              + preYear
              + "' and ba002.id = aa002.emp_info_id and aa002.pay_status in ('1', '3', '4') "
              + myCondition
              + " and aa002.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as c, "
              +
              /* ʵ��ְ���� */// "((select nvl(count(distinct aa002.emp_info_id),0)
              // from BA002 ba002,AA002 aa002,AA101 aa101,AA102 aa102
              // where 1=1 and ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.org_id=
              // aa101.org_id and aa101.biz_status='5' and
              // aa101.biz_type in ('A','B','M') and
              // aa002.id=aa102.emp_id and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as d,"+
              "((select nvl(count(distinct aa002.emp_info_id), 0) from BA002 ba002, AA002 aa002, AA101 aa101, AA102 aa102 where  ba002.opendate <= '"
              + preYear
              + "' and ba002.id = aa002.emp_info_id and aa002.org_id = aa101.org_id and aa101.biz_status = '5' and aa101.biz_type in ('A', 'B', 'M') and aa002.id = aa102.emp_id "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as d, "
              +
              /* Ӧ�ɴ�� */// "(select nvl(sum(aa002.emp_pay),0) from BA002
              // ba002,AA002 aa002 where 1=1 and
              // ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as e,"+
              "(select nvl(sum(aa002.emp_pay), 0) from BA002 ba002, AA002 aa002,aa001 a,ba001 ba001 where aa002.org_id=a.id and a.orginfo_id=ba001.id and ba002.opendate <= '"
              + preYear
              + "' and ba002.id = aa002.emp_info_id  "
              + myCondition
              + " and aa002.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as e, "
              +
              /* ʵ�ɴ�� */// "(((select nvl(sum(aa101.credit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type in ('A', 'B','K','G') and
              // aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")-(select
              // nvl(sum(aa101.debit),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type in
              // ('K', 'G') and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+"))"+") as f,"+
              "(((select nvl(sum(aa101.credit), 0) from AA101 aa101 where aa101.sett_date <= '"
              + preYear
              + "' and aa101.biz_type in ('A', 'B', 'K', 'G') and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") - (select nvl(sum(aa101.debit),0) from AA101 aa101 where aa101.sett_date <= '"
              + preYear
              + "' and aa101.biz_type in('K', 'G') and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")) "
              + ") as f, "
              +
              /* ��Ϣ���� */// "((select
              // nvl(sum(aa318.end_pre_balance+aa318.end_cur_balance),0)
              // from AA318 aa318,AA316 aa316 where aa318.year='2007'
              // and aa318.sett_head_id=aa316.id and aa316.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as g,"+
              "((select nvl(sum(aa318.end_pre_balance + aa318.end_cur_balance), 0) from AA318 aa318, AA316 aa316,AA001 a,BA001 b where aa318.year = "
              + pyear
              + " and aa316.org_id=a.id and a.orginfo_id=b.id and aa318.sett_head_id = aa316.id and aa316.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as g, "
              +
              /* �ʽ�ת�� */// "(select nvl(sum(aa101.credit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type='F' and aa101.biz_status='5' and
              // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as
              // h,"+
              "(select nvl(sum(aa101.credit),0)  from AA101 aa101 where aa101.sett_date <= '"
              + preYear
              + "' and aa101.biz_type='F' and aa101.biz_status='5' "
              + myConditionFlow
              + "  and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as h,"
              +
              /* ֧ȡ���� */// "((select nvl(count(*),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type='D'
              // and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as i,"+
              "((select nvl(count(*),0) from AA101 aa101 where aa101.sett_date <= '"
              + preYear
              + "' and aa101.biz_type='D' and aa101.biz_status='5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")"
              + ") as i,"
              +
              /* ֧ȡ��� */// "(((select nvl(sum(aa101.debit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type in ('D','L') and aa101.biz_status='5'
              // and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")-(select
              // nvl(sum(aa101.credit),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type =
              // 'L' and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+"))"+") as j,"+
              "(((select nvl(sum(aa101.debit),0)  from AA101 aa101 where  aa101.sett_date <= '"
              + preYear
              + "' and aa101.biz_type in ('D','L') and aa101.biz_status='5'  "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")-(select nvl(sum(aa101.credit),0)  from AA101 aa101 where  aa101.sett_date <= '"
              + preYear
              + "' and aa101.biz_type = 'L' and aa101.biz_status='5' "
              + myConditionFlow
              + "  and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + "))"
              + ") as j,"
              +
              /* ԭ��-���� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date
              // <= '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='1' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k1,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date <= '"
              + preYear
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '1' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ��ֹ�Ͷ���ϵ */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date
              // <= '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='4' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k2,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date <= '"
              + preYear
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '4' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ����ǩ�� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='6' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k3,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date <= '"
              + preYear
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '2' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �������� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='2' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k4,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date <= '"
              + preYear
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='2' and aa307.pick_reason = '1' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �������� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='3' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k5,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date <= '"
              + preYear
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '3' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ���� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='5' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k6,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date <= '"
              + preYear
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '5' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �ʽ�ת�� */// "(select nvl(sum(aa101.debit),0) from AA101 aa101
              // where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type='E'
              // and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as l,"+
              "(select nvl(sum(aa101.debit),0)  from AA101 aa101 where aa101.sett_date <= '"
              + preYear
              + "' and aa101.biz_type='E' and aa101.biz_status='5'  "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as l,"
              +
              /* ���� */// "(select nvl(sum(aa101.credit-aa101.debit),0) from
              // AA101
              // aa101 where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type='C' and aa101.biz_status='5' and
              // aa101.org_id "+securityInfo.getGjjSecuritySQL()+")as m"+
              // " from dual";
              "(select nvl(sum(aa101.credit-aa101.debit),0)  from AA101 aa101 where aa101.sett_date <= '"
              + preYear + "' and aa101.biz_type='C' and aa101.biz_status='5'  "
              + myConditionFlow + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL() + ")as m" + " from dual";
          Query query = session.createSQLQuery(hql1);
          Iterator it1 = query.list().iterator();
          Object obj[] = null;
          while (it1.hasNext()) {
            obj = (Object[]) it1.next();
          }
          for (int i = 0; i < obj.length; i++) {
            returnList.add(new BigDecimal(obj[i].toString()));
          }
          // �ڳ���
          // String hql2="select"+
          // /*Ӧ�ɵ�λ��*/ "(select nvl(count(distinct aa001.orginfo_id),0) from
          // BA001 ba001,AA001 aa001 where ba001.open_date>='"+preMonth1+"' and
          // ba001.open_date<='"+preMonth2+"' and ba001.id=aa001.orginfo_id
          // "+myCondition+" and ba001.id "+securityInfo.getGjjSecuritySQL()+")
          // as a1,"+
          // /*ʵ�ɵ�λ��*/ "(select nvl(count(distinct aa001.orginfo_id),0) from
          // BA001 ba001,AA001 aa001,AA101 aa101 where
          // ba001.open_date>='"+preMonth1+"' and
          // ba001.open_date<='"+preMonth2+"' and ba001.id=aa001.orginfo_id and
          // aa001.id= aa101.org_id and aa101.biz_status='5'"+myCondition+" and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as b1,"+
          // /*Ӧ��ְ����*/ "(select nvl(count(distinct aa002.emp_info_id),0) from
          // BA002 ba002,AA002 aa002 where ba002.opendate>='"+preMonth1+"' and
          // ba002.opendate<='"+preMonth2+"' and ba002.id=aa002.emp_info_id and
          // aa002.pay_status in ('1','3','4') and aa002.org_id
          // "+securityInfo.getGjjSecuritySQL()+") as c1,"+
          // /*ʵ��ְ����*/ "(select nvl(count(distinct aa002.emp_info_id),0) from
          // BA002 ba002,AA002 aa002,AA101 aa101,AA102 aa102 where
          // ba002.opendate>='"+preMonth1+"' and ba002.opendate<='"+preMonth2+"'
          // and ba002.id=aa002.emp_info_id and aa002.org_id= aa101.org_id and
          // aa101.biz_status='5' and aa101.biz_type in ('A','B','M') and
          // aa002.id=aa102.emp_id and aa101.org_id
          // "+securityInfo.getGjjSecuritySQL()+") as d1,"+
          // /*Ӧ�ɴ��*/ "(select nvl(sum(aa002.emp_pay),0) from BA002 ba002,AA002
          // aa002 where ba002.opendate>='"+preMonth1+"' and
          // ba002.opendate<='"+preMonth2+"' and ba002.id=aa002.emp_info_id and
          // aa002.org_id "+securityInfo.getGjjSecuritySQL()+") as e1,"+
          // /*ʵ�ɴ��*/ "((select nvl(sum(aa101.credit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+preMonth1+"' and aa101.sett_date <=
          // '"+preMonth2+"' and aa101.biz_type in ('A', 'B','K','G') and
          // aa101.biz_status='5' and aa101.org_id
          // "+securityInfo.getGjjSecuritySQL()+")-(select
          // nvl(sum(aa101.debit),0) from AA101 aa101 where aa101.sett_date >=
          // '"+preMonth1+"' and aa101.sett_date <= '"+preMonth2+"' and
          // aa101.biz_type in ('K', 'G') and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+")) as f1,"+
          // /*��Ϣ����*/ "(select
          // nvl(sum(aa318.end_pre_balance+aa318.end_cur_balance),0) from AA318
          // aa318,AA316 aa316 where aa318.year= "+cyear+" and
          // aa318.sett_head_id=aa316.id and aa316.org_id
          // "+securityInfo.getGjjSecuritySQL()+") as g1,"+
          // /*�ʽ�ת��*/ "(select nvl(sum(aa101.credit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+preMonth1+"' and aa101.sett_date <=
          // '"+preMonth2+"' and aa101.biz_type='F' and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as h1,"+
          // /*֧ȡ����*/ "(select nvl(count(*),0) from AA101 aa101 where
          // aa101.sett_date >= '"+preMonth1+"' and aa101.sett_date <=
          // '"+preMonth2+"' and aa101.biz_type='D' and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as i1,"+
          // /*֧ȡ���*/ "((select nvl(sum(aa101.debit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+preMonth1+"' and aa101.sett_date <=
          // '"+preMonth2+"' and aa101.biz_type in ('D','L') and
          // aa101.biz_status='5' and aa101.org_id
          // "+securityInfo.getGjjSecuritySQL()+")-(select
          // nvl(sum(aa101.credit),0) from AA101 aa101 where aa101.sett_date >=
          // '"+preMonth1+"' and aa101.sett_date <= '"+preMonth2+"' and
          // aa101.biz_type = 'L' and aa101.biz_status='5' and aa101.org_id
          // "+securityInfo.getGjjSecuritySQL()+")) as j1,"+
          // /*ԭ��-����*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+preMonth1+"'
          // and aa306.sett_date <= '"+preMonth2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='1' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k11,"+
          // /*��ֹ�Ͷ���ϵ*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+preMonth1+"'
          // and aa306.sett_date <= '"+preMonth2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='4' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k12,"+
          // /*����ǩ��*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+preMonth1+"'
          // and aa306.sett_date <= '"+preMonth2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='6' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k13,"+
          // /*��������*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+preMonth1+"'
          // and aa306.sett_date <= '"+preMonth2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='2' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k14,"+
          // /*��������*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+preMonth1+"'
          // and aa306.sett_date <= '"+preMonth2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='3' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k15,"+
          // /*����*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+preMonth1+"'
          // and aa306.sett_date <= '"+preMonth2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='5' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k16,"+
          // /*�ʽ�ת��*/ "(select nvl(sum(aa101.debit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+preMonth1+"' and aa101.sett_date <=
          // '"+preMonth2+"' and aa101.biz_type='E' and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as l1,"+
          // /*����*/ "(select nvl(sum(aa101.credit-aa101.debit),0) from AA101
          // aa101 where aa101.sett_date >= '"+preMonth1+"' and aa101.sett_date
          // <= '"+preMonth2+"' and aa101.biz_type='C' and aa101.biz_status='5'
          // and aa101.org_id "+securityInfo.getGjjSecuritySQL()+")as m1"+
          // " from dual";
          String hql2 = "select"
              +
              /* Ӧ�ɵ�λ�� */"(select nvl(count(distinct aa001.orginfo_id),0)  from BA001 ba001,AA001 aa001 where ba001.open_date>='"
              + preMonth1
              + "' and ba001.open_date<='"
              + preMonth2
              + "' and ba001.id=aa001.orginfo_id "
              + myCondition
              + " and ba001.id "
              + securityInfo.getGjjSecuritySQL()
              + ") as a,"
              +

              /* ʵ�ɵ�λ�� */// "((select nvl(count(distinct aa001.orginfo_id),0)
              // from BA001 ba001,AA001 aa001,AA101 aa101 where 1=1
              // and ba001.open_date<='"+preYear+"' and
              // ba001.id=aa001.orginfo_id and aa001.id= aa101.org_id
              // and aa101.biz_status='5'"+myCondition+" and
              // aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as b,"+
              "((select nvl(count(distinct aa101.org_id), 0) from BA001 ba001, AA001 aa001, AA101 aa101 where ba001.open_date>='"
              + preMonth1
              + "' and ba001.open_date<='"
              + preMonth2
              + "' and ba001.id = aa001.orginfo_id and aa001.id = aa101.org_id and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as b, "
              +
              /* Ӧ��ְ���� */// "(select nvl(count(distinct aa002.emp_info_id),0)
              // from BA002 ba002,AA002 aa002 where 1=1 and
              // ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.pay_status in
              // ('1','3','4') "+myCondition+" and aa002.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as c,"+
              "(select nvl(count(distinct aa002.emp_info_id), 0) from BA002 ba002, AA002 aa002,Aa001 c,Ba001 ba001 where aa002.org_id=c.id and c.orginfo_id=ba001.id and ba002.opendate>='"
              + preMonth1
              + "' and ba002.opendate<='"
              + preMonth2
              + "' and ba002.id = aa002.emp_info_id and aa002.pay_status in ('1', '3', '4') "
              + myCondition
              + " and aa002.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as c, "
              +
              /* ʵ��ְ���� */// "((select nvl(count(distinct aa002.emp_info_id),0)
              // from BA002 ba002,AA002 aa002,AA101 aa101,AA102 aa102
              // where 1=1 and ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.org_id=
              // aa101.org_id and aa101.biz_status='5' and
              // aa101.biz_type in ('A','B','M') and
              // aa002.id=aa102.emp_id and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as d,"+
              "((select nvl(count(distinct aa002.emp_info_id), 0) from BA002 ba002, AA002 aa002, AA101 aa101, AA102 aa102 where  ba002.opendate>='"
              + preMonth1
              + "' and ba002.opendate<='"
              + preMonth2
              + "' and ba002.id = aa002.emp_info_id and aa002.org_id = aa101.org_id and aa101.biz_status = '5' and aa101.biz_type in ('A', 'B', 'M') and aa002.id = aa102.emp_id "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as d, "
              +
              /* Ӧ�ɴ�� */// "(select nvl(sum(aa002.emp_pay),0) from BA002
              // ba002,AA002 aa002 where 1=1 and
              // ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as e,"+
              "(select nvl(sum(aa002.emp_pay), 0) from BA002 ba002, AA002 aa002,aa001 a,ba001 ba001 where aa002.org_id=a.id and a.orginfo_id=ba001.id and ba002.opendate>='"
              + preMonth1
              + "' and ba002.opendate<='"
              + preMonth2
              + "' and ba002.id = aa002.emp_info_id  "
              + myCondition
              + " and aa002.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as e, "
              +
              /* ʵ�ɴ�� */// "(((select nvl(sum(aa101.credit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type in ('A', 'B','K','G') and
              // aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")-(select
              // nvl(sum(aa101.debit),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type in
              // ('K', 'G') and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+"))"+") as f,"+
              "(((select nvl(sum(aa101.credit), 0) from AA101 aa101 where aa101.sett_date >= '"
              + preMonth1
              + "' and aa101.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_type in ('A', 'B', 'K', 'G') and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") - (select nvl(sum(aa101.debit),0) from AA101 aa101 where aa101.sett_date >= '"
              + preMonth1
              + "' and aa101.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_type in('K', 'G') and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")) "
              + ") as f, "
              +
              /* ��Ϣ���� */// "((select
              // nvl(sum(aa318.end_pre_balance+aa318.end_cur_balance),0)
              // from AA318 aa318,AA316 aa316 where aa318.year='2007'
              // and aa318.sett_head_id=aa316.id and aa316.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as g,"+
              "((select nvl(sum(aa318.end_pre_balance + aa318.end_cur_balance), 0) from AA318 aa318, AA316 aa316,AA001 a,BA001 b where aa318.year = "
              + cyear
              + " and aa316.org_id=a.id and a.orginfo_id=b.id and aa318.sett_head_id = aa316.id and aa316.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as g, "
              +
              /* �ʽ�ת�� */// "(select nvl(sum(aa101.credit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type='F' and aa101.biz_status='5' and
              // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as
              // h,"+
              "(select nvl(sum(aa101.credit),0)  from AA101 aa101 where aa101.sett_date >= '"
              + preMonth1
              + "' and aa101.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_type='F' and aa101.biz_status='5' "
              + myConditionFlow
              + "  and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as h,"
              +
              /* ֧ȡ���� */// "((select nvl(count(*),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type='D'
              // and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as i,"+
              "((select nvl(count(*),0) from AA101 aa101 where aa101.sett_date >= '"
              + preMonth1
              + "' and aa101.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_type='D' and aa101.biz_status='5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")"
              + ") as i,"
              +
              /* ֧ȡ��� */// "(((select nvl(sum(aa101.debit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type in ('D','L') and aa101.biz_status='5'
              // and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")-(select
              // nvl(sum(aa101.credit),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type =
              // 'L' and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+"))"+") as j,"+
              "(((select nvl(sum(aa101.debit),0)  from AA101 aa101 where  aa101.sett_date >= '"
              + preMonth1
              + "' and aa101.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_type in ('D','L') and aa101.biz_status='5'  "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")-(select nvl(sum(aa101.credit),0)  from AA101 aa101 where  aa101.sett_date >= '"
              + preMonth1
              + "' and aa101.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_type = 'L' and aa101.biz_status='5' "
              + myConditionFlow
              + "  and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + "))"
              + ") as j,"
              +
              /* ԭ��-���� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date
              // <= '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='1' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k1,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + preMonth1
              + "' and aa306.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '1' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ��ֹ�Ͷ���ϵ */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date
              // <= '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='4' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k2,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + preMonth1
              + "' and aa306.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '4' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ����ǩ�� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='6' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k3,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + preMonth1
              + "' and aa306.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '2' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �������� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='2' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k4,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + preMonth1
              + "' and aa306.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='2' and aa307.pick_reason = '1' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �������� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='3' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k5,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + preMonth1
              + "' and aa306.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '3' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ���� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='5' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k6,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + preMonth1
              + "' and aa306.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '5' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �ʽ�ת�� */// "(select nvl(sum(aa101.debit),0) from AA101 aa101
              // where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type='E'
              // and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as l,"+
              "(select nvl(sum(aa101.debit),0)  from AA101 aa101 where aa101.sett_date >= '"
              + preMonth1
              + "' and aa101.sett_date <= '"
              + preMonth2
              + "' and aa101.biz_type='E' and aa101.biz_status='5'  "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as l,"
              +
              /* ���� */// "(select nvl(sum(aa101.credit-aa101.debit),0) from
              // AA101
              // aa101 where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type='C' and aa101.biz_status='5' and
              // aa101.org_id "+securityInfo.getGjjSecuritySQL()+")as m"+
              // " from dual";
              "(select nvl(sum(aa101.credit-aa101.debit),0)  from AA101 aa101 where aa101.sett_date >= '"
              + preMonth1 + "' and aa101.sett_date <= '" + preMonth2
              + "' and aa101.biz_type='C' and aa101.biz_status='5'  "
              + myConditionFlow + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL() + ")as m" + " from dual";
          Query query1 = session.createSQLQuery(hql2);
          Iterator it2 = query1.list().iterator();
          Object obj1[] = null;
          while (it2.hasNext()) {
            obj1 = (Object[]) it2.next();
          }
          for (int i = 0; i < obj1.length; i++) {
            if (month < 6) {// �ж���Ϣ����
              if (i == 6) {
                returnList.add(new BigDecimal(0));
              } else {
                returnList.add(new BigDecimal(obj1[i].toString()));
              }
            } else {
              returnList.add(new BigDecimal(obj1[i].toString()));
            }
          }
          // ������
          // String hql3="select"+
          // /*Ӧ�ɵ�λ��*/ "(select nvl(count(distinct aa001.orginfo_id),0) from
          // BA001 ba001,AA001 aa001 where ba001.open_date>='"+curYear1+"' and
          // ba001.open_date<='"+curYear2+"' and ba001.id=aa001.orginfo_id
          // "+myCondition+" and ba001.id "+securityInfo.getGjjSecuritySQL()+")
          // as a2,"+
          // /*ʵ�ɵ�λ��*/ "(select nvl(count(distinct aa001.orginfo_id),0) from
          // BA001 ba001,AA001 aa001,AA101 aa101 where
          // ba001.open_date>='"+curYear1+"' and ba001.open_date<='"+curYear2+"'
          // and ba001.id=aa001.orginfo_id and aa001.id= aa101.org_id and
          // aa101.biz_status='5'"+myCondition+" and aa101.org_id
          // "+securityInfo.getGjjSecuritySQL()+") as b2,"+
          // /*Ӧ��ְ����*/ "(select nvl(count(distinct aa002.emp_info_id),0) from
          // BA002 ba002,AA002 aa002 where ba002.opendate>='"+curYear1+"' and
          // ba002.opendate<='"+curYear2+"' and ba002.id=aa002.emp_info_id and
          // aa002.pay_status in ('1','3','4') and aa002.org_id
          // "+securityInfo.getGjjSecuritySQL()+") as c2,"+
          // /*ʵ��ְ����*/ "(select nvl(count(distinct aa002.emp_info_id),0) from
          // BA002 ba002,AA002 aa002,AA101 aa101,AA102 aa102 where
          // ba002.opendate>='"+curYear1+"' and ba002.opendate<='"+curYear2+"'
          // and ba002.id=aa002.emp_info_id and aa002.org_id= aa101.org_id and
          // aa101.biz_status='5' and aa101.biz_type in ('A','B','M') and
          // aa002.id=aa102.emp_id and aa101.org_id
          // "+securityInfo.getGjjSecuritySQL()+") as d2,"+
          // /*Ӧ�ɴ��*/ "(select nvl(sum(aa002.emp_pay),0) from BA002 ba002,AA002
          // aa002 where ba002.opendate>='"+curYear1+"' and
          // ba002.opendate<='"+curYear2+"' and ba002.id=aa002.emp_info_id and
          // aa002.org_id "+securityInfo.getGjjSecuritySQL()+") as e2,"+
          // /*ʵ�ɴ��*/ "((select nvl(sum(aa101.credit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+curYear1+"' and aa101.sett_date <=
          // '"+curYear2+"' and aa101.biz_type in ('A', 'B','K','G') and
          // aa101.biz_status='5' and aa101.org_id
          // "+securityInfo.getGjjSecuritySQL()+")-(select
          // nvl(sum(aa101.debit),0) from AA101 aa101 where aa101.sett_date >=
          // '"+curYear1+"' and aa101.sett_date <= '"+curYear2+"' and
          // aa101.biz_type in ('K', 'G') and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+")) as f2,"+
          // /*��Ϣ����*/ "(select
          // nvl(sum(aa318.end_pre_balance+aa318.end_cur_balance),0) from AA318
          // aa318,AA316 aa316 where aa318.year= "+cyear+" and
          // aa318.sett_head_id=aa316.id and aa316.org_id
          // "+securityInfo.getGjjSecuritySQL()+") as g,"+
          // /*�ʽ�ת��*/ "(select nvl(sum(aa101.credit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+curYear1+"' and aa101.sett_date <=
          // '"+curYear2+"' and aa101.biz_type='F' and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as h2,"+
          // /*֧ȡ����*/ "(select nvl(count(*),0) from AA101 aa101 where
          // aa101.sett_date >= '"+curYear1+"' and aa101.sett_date <=
          // '"+curYear2+"' and aa101.biz_type='D' and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as i2,"+
          // /*֧ȡ���*/ "((select nvl(sum(aa101.debit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+curYear1+"' and aa101.sett_date <=
          // '"+curYear2+"' and aa101.biz_type in ('D','L') and
          // aa101.biz_status='5')-(select nvl(sum(aa101.credit),0) from AA101
          // aa101 where aa101.sett_date >= '"+curYear1+"' and aa101.sett_date
          // <= '"+curYear2+"' and aa101.biz_type = 'L' and aa101.biz_status='5'
          // and aa101.org_id "+securityInfo.getGjjSecuritySQL()+")) as j2,"+
          // /*ԭ��-����*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+curYear1+"'
          // and aa306.sett_date <= '"+curYear2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='1' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k21,"+
          // /*��ֹ�Ͷ���ϵ*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+curYear1+"'
          // and aa306.sett_date <= '"+curYear2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='4' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k22,"+
          // /*����ǩ��*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+curYear1+"'
          // and aa306.sett_date <= '"+curYear2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='6' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k23,"+
          // /*��������*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+curYear1+"'
          // and aa306.sett_date <= '"+curYear2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='2' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k24,"+
          // /*��������*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+curYear1+"'
          // and aa306.sett_date <= '"+curYear2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='3' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k25,"+
          // /*����*/ "(select
          // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0) from
          // AA306 aa306, AA307 aa307 where aa306.sett_date >= '"+curYear1+"'
          // and aa306.sett_date <= '"+curYear2+"' and
          // aa306.id=aa307.pickhead_id and aa307.pick_reason='5' and
          // aa306.org_id "+securityInfo.getGjjSecuritySQL()+") as k26,"+
          // /*�ʽ�ת��*/ "(select nvl(sum(aa101.debit),0) from AA101 aa101 where
          // aa101.sett_date >= '"+curYear1+"' and aa101.sett_date <=
          // '"+curYear2+"' and aa101.biz_type='E' and aa101.biz_status='5' and
          // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as l2,"+
          // /*����*/ "(select nvl(sum(aa101.credit-aa101.debit),0) from AA101
          // aa101 where aa101.sett_date >= '"+curYear1+"' and aa101.sett_date
          // <= '"+curYear2+"' and aa101.biz_type='C' and aa101.biz_status='5'
          // and aa101.org_id "+securityInfo.getGjjSecuritySQL()+")as m2"+
          // " from dual";
          String hql3 = "select"
              +
              /* Ӧ�ɵ�λ�� */"(select nvl(count(distinct aa001.orginfo_id),0)  from BA001 ba001,AA001 aa001 where ba001.open_date>='"
              + curYear1
              + "' and ba001.open_date<='"
              + curYear2
              + "' and ba001.id=aa001.orginfo_id "
              + myCondition
              + " and ba001.id "
              + securityInfo.getGjjSecuritySQL()
              + ") as a,"
              +

              /* ʵ�ɵ�λ�� */// "((select nvl(count(distinct aa001.orginfo_id),0)
              // from BA001 ba001,AA001 aa001,AA101 aa101 where 1=1
              // and ba001.open_date<='"+preYear+"' and
              // ba001.id=aa001.orginfo_id and aa001.id= aa101.org_id
              // and aa101.biz_status='5'"+myCondition+" and
              // aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as b,"+
              "((select nvl(count(distinct aa101.org_id), 0) from BA001 ba001, AA001 aa001, AA101 aa101 where ba001.open_date>='"
              + curYear1
              + "' and ba001.open_date<='"
              + curYear2
              + "' and ba001.id = aa001.orginfo_id and aa001.id = aa101.org_id and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as b, "
              +
              /* Ӧ��ְ���� */// "(select nvl(count(distinct aa002.emp_info_id),0)
              // from BA002 ba002,AA002 aa002 where 1=1 and
              // ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.pay_status in
              // ('1','3','4') "+myCondition+" and aa002.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as c,"+
              "(select nvl(count(distinct aa002.emp_info_id), 0) from BA002 ba002, AA002 aa002,Aa001 c,Ba001 ba001 where aa002.org_id=c.id and c.orginfo_id=ba001.id and ba002.opendate>='"
              + curYear1
              + "' and ba002.opendate<='"
              + curYear2
              + "' and ba002.id = aa002.emp_info_id and aa002.pay_status in ('1', '3', '4') "
              + myCondition
              + " and aa002.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as c, "
              +
              /* ʵ��ְ���� */// "((select nvl(count(distinct aa002.emp_info_id),0)
              // from BA002 ba002,AA002 aa002,AA101 aa101,AA102 aa102
              // where 1=1 and ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.org_id=
              // aa101.org_id and aa101.biz_status='5' and
              // aa101.biz_type in ('A','B','M') and
              // aa002.id=aa102.emp_id and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as d,"+
              "((select nvl(count(distinct aa002.emp_info_id), 0) from BA002 ba002, AA002 aa002, AA101 aa101, AA102 aa102 where  ba002.opendate>='"
              + curYear1
              + "' and ba002.opendate<='"
              + curYear2
              + "' and ba002.id = aa002.emp_info_id and aa002.org_id = aa101.org_id and aa101.biz_status = '5' and aa101.biz_type in ('A', 'B', 'M') and aa002.id = aa102.emp_id "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as d, "
              +
              /* Ӧ�ɴ�� */// "(select nvl(sum(aa002.emp_pay),0) from BA002
              // ba002,AA002 aa002 where 1=1 and
              // ba002.opendate<='"+preYear+"' and
              // ba002.id=aa002.emp_info_id and aa002.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as e,"+
              "(select nvl(sum(aa002.emp_pay), 0) from BA002 ba002, AA002 aa002,aa001 a,ba001 ba001 where aa002.org_id=a.id and a.orginfo_id=ba001.id and ba002.opendate>='"
              + curYear1
              + "' and ba002.opendate<='"
              + curYear2
              + "' and ba002.id = aa002.emp_info_id  "
              + myCondition
              + " and aa002.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as e, "
              +
              /* ʵ�ɴ�� */// "(((select nvl(sum(aa101.credit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type in ('A', 'B','K','G') and
              // aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")-(select
              // nvl(sum(aa101.debit),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type in
              // ('K', 'G') and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+"))"+") as f,"+
              "(((select nvl(sum(aa101.credit), 0) from AA101 aa101 where aa101.sett_date >= '"
              + curYear1
              + "' and aa101.sett_date <= '"
              + curYear2
              + "' and aa101.biz_type in ('A', 'B', 'K', 'G') and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") - (select nvl(sum(aa101.debit),0) from AA101 aa101 where aa101.sett_date >= '"
              + curYear1
              + "' and aa101.sett_date <= '"
              + curYear2
              + "' and aa101.biz_type in('K', 'G') and aa101.biz_status = '5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")) "
              + ") as f, "
              +
              /* ��Ϣ���� */// "((select
              // nvl(sum(aa318.end_pre_balance+aa318.end_cur_balance),0)
              // from AA318 aa318,AA316 aa316 where aa318.year='2007'
              // and aa318.sett_head_id=aa316.id and aa316.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as g,"+
              "((select nvl(sum(aa318.end_pre_balance + aa318.end_cur_balance), 0) from AA318 aa318, AA316 aa316,AA001 a,BA001 b where aa318.year = "
              + cyear
              + " and aa316.org_id=a.id and a.orginfo_id=b.id and aa318.sett_head_id = aa316.id and aa316.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as g, "
              +
              /* �ʽ�ת�� */// "(select nvl(sum(aa101.credit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type='F' and aa101.biz_status='5' and
              // aa101.org_id "+securityInfo.getGjjSecuritySQL()+") as
              // h,"+
              "(select nvl(sum(aa101.credit),0)  from AA101 aa101 where aa101.sett_date >= '"
              + curYear1
              + "' and aa101.sett_date <= '"
              + curYear2
              + "' and aa101.biz_type='F' and aa101.biz_status='5' "
              + myConditionFlow
              + "  and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as h,"
              +
              /* ֧ȡ���� */// "((select nvl(count(*),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type='D'
              // and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as i,"+
              "((select nvl(count(*),0) from AA101 aa101 where aa101.sett_date >= '"
              + curYear1
              + "' and aa101.sett_date <= '"
              + curYear2
              + "' and aa101.biz_type='D' and aa101.biz_status='5' "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")"
              + ") as i,"
              +
              /* ֧ȡ��� */// "(((select nvl(sum(aa101.debit),0) from AA101 aa101
              // where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type in ('D','L') and aa101.biz_status='5'
              // and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+")-(select
              // nvl(sum(aa101.credit),0) from AA101 aa101 where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type =
              // 'L' and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+"))"+") as j,"+
              "(((select nvl(sum(aa101.debit),0)  from AA101 aa101 where  aa101.sett_date >= '"
              + curYear1
              + "' and aa101.sett_date <= '"
              + curYear2
              + "' and aa101.biz_type in ('D','L') and aa101.biz_status='5'  "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ")-(select nvl(sum(aa101.credit),0)  from AA101 aa101 where  aa101.sett_date >= '"
              + curYear1
              + "' and aa101.sett_date <= '"
              + curYear2
              + "' and aa101.biz_type = 'L' and aa101.biz_status='5' "
              + myConditionFlow
              + "  and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + "))"
              + ") as j,"
              +
              /* ԭ��-���� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date
              // <= '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='1' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k1,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + curYear1
              + "' and aa306.sett_date <= '"
              + curYear2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '1' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ��ֹ�Ͷ���ϵ */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date
              // <= '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='4' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k2,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + curYear1
              + "' and aa306.sett_date <= '"
              + curYear2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '4' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ����ǩ�� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='6' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k3,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + curYear1
              + "' and aa306.sett_date <= '"
              + curYear2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '2' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �������� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='2' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k4,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + curYear1
              + "' and aa306.sett_date <= '"
              + curYear2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='2' and aa307.pick_reason = '1' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �������� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='3' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k5,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + curYear1
              + "' and aa306.sett_date <= '"
              + curYear2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '3' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* ���� */// "((select
              // nvl(sum(aa307.pick_pre_balance+aa307.pick_cur_balance),0)
              // from AA306 aa306, AA307 aa307 where aa306.sett_date <=
              // '"+preYear+"' and aa306.id=aa307.pickhead_id and
              // aa307.pick_reason='5' and aa306.org_id
              // "+securityInfo.getGjjSecuritySQL()+")"+") as k6,"+
              "((select nvl(sum(aa307.pick_pre_balance + aa307.pick_cur_balance), 0) from AA306 aa306, AA307 aa307, AA101 aa101 where aa101.biz_id = aa306.id and aa306.id = aa307.pickhead_id and aa306.sett_date >= '"
              + curYear1
              + "' and aa306.sett_date <= '"
              + curYear2
              + "' and aa101.biz_status = '5'  "
              + myConditionFlow
              + "  and aa306.pick_satatus='5' and aa307.pick_reason = '5' and aa306.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") "
              + ") as k1, "
              +
              /* �ʽ�ת�� */// "(select nvl(sum(aa101.debit),0) from AA101 aa101
              // where
              // aa101.sett_date <= '"+preYear+"' and aa101.biz_type='E'
              // and aa101.biz_status='5' and aa101.org_id
              // "+securityInfo.getGjjSecuritySQL()+") as l,"+
              "(select nvl(sum(aa101.debit),0)  from AA101 aa101 where aa101.sett_date >= '"
              + curYear1
              + "' and aa101.sett_date <= '"
              + curYear2
              + "' and aa101.biz_type='E' and aa101.biz_status='5'  "
              + myConditionFlow
              + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL()
              + ") as l,"
              +
              /* ���� */// "(select nvl(sum(aa101.credit-aa101.debit),0) from
              // AA101
              // aa101 where aa101.sett_date <= '"+preYear+"' and
              // aa101.biz_type='C' and aa101.biz_status='5' and
              // aa101.org_id "+securityInfo.getGjjSecuritySQL()+")as m"+
              // " from dual";
              "(select nvl(sum(aa101.credit-aa101.debit),0)  from AA101 aa101 where  aa101.sett_date >= '"
              + curYear1 + "' and aa101.sett_date <= '" + curYear2
              + "' and aa101.biz_type='C' and aa101.biz_status='5'  "
              + myConditionFlow + " and aa101.org_id "
              + securityInfo.getGjjSecuritySQL() + ")as m" + " from dual";
          Query query2 = session.createSQLQuery(hql3);
          Iterator it3 = query2.list().iterator();
          Object obj2[] = null;
          while (it3.hasNext()) {
            obj2 = (Object[]) it3.next();
          }
          for (int i = 0; i < obj2.length; i++) {
            if (month < 6) {// �ж���Ϣ����
              if (i == 6) {
                returnList.add(new BigDecimal(0));
              } else {
                returnList.add(new BigDecimal(obj2[i].toString()));
              }
            } else if (month == 6) {
              returnList.add(new BigDecimal(obj2[i].toString()));
            } else {
              if (i == 6) {
                returnList.add(new BigDecimal(0));
              } else {
                returnList.add(new BigDecimal(obj2[i].toString()));
              }
            }
          }

        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }
        return returnList;
      }
    });
    return list;
  }

  /**
   * ���´����鼯���У�����
   * 
   * @author ���
   * @pram officeCode String @ 2007-12-04
   * @return List
   */
  public List findCollectionBankId(final String officeCode) {
    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List queryList = new ArrayList();
        try {
          String hql = "select distinct bb105.coll_bank_id ,bb105.coll_bank_name"
              + " from BB105 bb105 "
              + "where bb105.office =? "
              + "and bb105.status='1'";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, officeCode);
          Iterator iter = query.list().iterator();
          Object obj[] = null;
          while (iter.hasNext()) {
            obj = (Object[]) iter.next();
            queryList.add(new org.apache.struts.util.LabelValueBean(obj[0]
                .toString(), obj[1].toString()));
          }
        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }

        return queryList;
      }
    });
    return list;
  }

  /**
   * ��˶ ��ѯ���������е�λ���ڰ��´��ĸ���
   */
  public int findOfficeCount_wsh(final Integer orgIds)
      throws NumberFormatException, Exception {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select count(distinct b.officecode) from aa001 a,ba001 b where a.orginfo_id=b.id and a.id in("
              + orgIds + ")";

          Query query = session.createSQLQuery(hql);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    count = Integer.parseInt((((BigDecimal) list.get(0)).toString()));
    return count;
  }

  /*
   * ��˶ ����������ѯ��λ�鼯����
   */
  public String queryOrgCollectionBankId(final Integer orgId) {

    String collectionBankId = "";
    try {
      collectionBankId = (String) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = " select b.collection_bank_id from aa001 a,ba001 b where a.orginfo_id=b.id and a.id=?  ";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, orgId);
              Object obj = null;
              Iterator it = query.list().iterator();
              if (it.hasNext())
                obj = (Object) it.next();
              return obj;
            }

          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return collectionBankId.toString();
  }

  /**
   * ��λ��_����ת ͨ��OrgId��ѯSUM(AA002.ORG_PAY),SUM(AA002.EMP_PAY)
   * 
   * @author wangy 2008-02-27
   * @param orgId ��λ���
   * @return
   */
  public List queryOrgPaySumAndEmpPaySumByOrgId(final String orgId) {
    List list = null;
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select sum(a.org_pay),sum(a.emp_pay) from aa002 a where a.pay_status in ('1','3','4') and a.org_id=? ";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, new Integer(orgId));

          Iterator it = query.list().iterator();
          List temp_list = new ArrayList();
          Object obj[] = null;
          while (it.hasNext()) {
            obj = (Object[]) it.next();
            OrgVerAccountBalanceDTO orgVerAccountBalanceDTO = new OrgVerAccountBalanceDTO();
            orgVerAccountBalanceDTO.setOrgPaySum(new BigDecimal(obj[0]
                .toString()));
            orgVerAccountBalanceDTO.setEmpPaySum(new BigDecimal(obj[1]
                .toString()));
            temp_list.add(orgVerAccountBalanceDTO);
          }
          return temp_list;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // ������޸� 2008-3-18 ��ѯ�� RelaUserAndOrg bb106
  public List queryRelaUserAndOrglist_wuht(final String orgId) {
    List empList = null;
    try {
      empList = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from RelaUserAndOrg relaUserAndOrg  ";
          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !orgId.equals("")) {
            criterion += "relaUserAndOrg.orgId = ?  and ";
            parameters.add(new BigDecimal(orgId.toString()));
          }

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
      }

      );

    } catch (Exception e) {
      e.printStackTrace();
    }
    return empList;
  }

  /*
   * ��˶ ����������ѯ��λ�鼯����
   */
  public String findOrgOver(final String orgId) {

    String type = "";
    try {
      type = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = " select * from aa301 a where a.org_id=? and a.pay_status='1' and a.pay_type='C' ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgId);
          List list = query.list();
          if (list.size() > 0) {
            return "1";
          } else {
            return "2";
          }
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return type;
  }

  /**
   * ���������������ѯ��λ��Ϣ�б�
   * 
   * @param id
   * @param name
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param status
   * @return
   * @throws NumberFormatException
   * @throws Exception ��� ��λ�������ѯ
   */
  public List queryOrgpopListByCriterions(final String id, final String name,
      final String orderBy, final String order, final int start,
      final int pageSize, final String[] status, final SecurityInfo securityInfo)
      throws NumberFormatException, Exception {
    List list = null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);

      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = " from Org orgs where orgs.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " to_char(orgs.id) like ? escape '/'  and ";
            parameters.add("%" + id + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += "orgs.orgInfo.name  like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }

          if (status != null && status.length > 0) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "orgs.orgInfo.openstatus = ? or ";
              parameters.add(status[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " orgs.id ";

          String od = order;
          if (od == null)
            od = "DESC";
          hql = hql + criterion + "order by " + ob + " " + od;
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

  /**
   * ���������������ѯ��λ��Ϣ��¼��
   * 
   * @param id
   * @param name
   * @param status
   * @return ��� ��λ�������ѯ
   */

  public int queryOrgpopCountByCriterions(final String id, final String name,
      final String[] status, final SecurityInfo securityInfo) {
    int count = 0;
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "from Org orgs where orgs.id "
              + securityInfo.getGjjSecurityHqlSQL();
          Vector parameters = new Vector();
          String criterion = "";

          if (id != null && !id.equals("")) {
            criterion += " to_char(orgs.id)  like ? escape '/'  and ";
            parameters.add("%" + id + "%");
          }

          if (name != null && !name.equals("")) {
            criterion += " orgs.orgInfo.name like ? escape '/'  and ";
            parameters.add("%" + name + "%");
          }
          if (status != null && status.length > 0 && !status.equals("")) {
            criterion += "( ";
            for (int i = 0; i < status.length; i++) {
              criterion += "orgs.orgInfo.openstatus = ? or ";
              parameters.add(status[i]);
            }
            criterion = criterion.substring(0, criterion.lastIndexOf("or"));
            criterion += ") and";
          }
          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;
          session.clear();
          Query query = session.createQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          return query.list();
        }
      }

      );
      count = list.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  /**
   * bit add ���ݵ�λID��ѯ��λ�ù鼯����
   */
  public String querycollbank(final Integer orgid, final String orgname) {
    Object o = "";
    try {
      o = getHibernateTemplate().execute(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select c.coll_bank_name from aa001 a,ba001 b,bb105 c where a.orginfo_id=b.id and b.collection_bank_id = c.coll_bank_id and a.id=? and b.name=?";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, orgid);
          query.setParameter(1, orgname);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return o.toString();
  }

  /**
   * @author yangg
   * @return
   */
  public String queryNoteNum() {
    Object o = "";
    try {
      o = getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select seq_notnum.nextval from dual";
          Query query = session.createSQLQuery(sql);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/" + o.toString();
  }

  public String findAA103_DayTime(final String collbankid) {
    String str = "";
    str = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select t.bank_date from aa103 t where t.bank_id = ?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, collbankid);

        return query.uniqueResult();
      }
    });
    return str;
  }

  public String find_empid_card_num(final String orgid, final String empid) {
    String str = "";
    str = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select a.card_num from aa002 t,ba002 a where t.emp_info_id=a.id and t.org_id=? and t.id=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, orgid);
        query.setParameter(1, empid);

        return query.uniqueResult();
      }
    });
    return str;
  }

  /**
   * ���� �����������ʱ��
   * 
   * @param bookId
   * @return String
   */
  public String[] findBankInfor(final String bankId) {
    Validate.notNull(bankId);
    String useYearmonth[] = new String[3];
    try {
      useYearmonth = (String[]) getHibernateTemplate().execute(
          new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
              String sql = "select t.center_name from bb105 t where t.coll_bank_id=?";
              String sql1 = "select t.coll_bank_name from bb105 t where t.coll_bank_id=?";
              String sql2 = "select t.collection_bank_acc from bb105 t where t.coll_bank_id=?";
              Query query = session.createSQLQuery(sql);
              query.setParameter(0, bankId);
              Query query1 = session.createSQLQuery(sql1);
              query1.setParameter(0, bankId);
              Query query2 = session.createSQLQuery(sql2);
              query2.setParameter(0, bankId);
              String a = query.uniqueResult().toString();
              String b = query1.uniqueResult().toString();
              String c = query2.uniqueResult().toString();
              String d[] = new String[3];
              d[0] = a;
              d[1] = b;
              d[2] = c;
              return d;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return useYearmonth;
  }

  public String find_user_realname(final String user) {
    String str = "";
    str = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select a.real_name from ca101 t,bb201 a where a.id=t.id and t.username=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, user);

        return query.uniqueResult();
      }
    });
    return str;
  }

  public List findFundbankmonthofyear(final SecurityInfo securityInfo,
      final String officeCode, final String collectionBankId, final String Year)
      throws Exception, BusinessException {

    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        List returnList = null;
        returnList = new ArrayList();
        String year = Year;
        String BankId = collectionBankId;
        Fundbankmonthofyeardto dto = null;
        try {

          for (int i = 1; i <= 12; i++) {
            if (i < 10) {
              year = Year + "0" + i;
            } else {
              year = Year + i;
            }

            String hql1 = "select "

                + "(select  nvl(sum(aa301.pay_money),0) from  AA301, aa001, ba001 where aa301.pay_status=5 and aa301.org_id=aa001.id and ba001.id=aa001.orginfo_id "
                + "and substr(aa301.sett_date,0,6)='"
                + year
                + "' and ba001.collection_bank_id ="
                + BankId
                + ") pay_fund, "

                + "(select nvl(count(t.id),0) from ba001 t where t.openstatus = 2 and substr(t.open_date,0,6)='"
                + year
                + "' and t.collection_bank_id="
                + BankId
                + ")  org_neworg, "

                + "(select nvl(count(t.id), 0) from aa002 t, aa001 s, ba001 b " +
                    "where t.org_id = s.id and s.orginfo_id = b.id and b.openstatus = 2 and "
                + "substr(b.open_date,0,6)='"
                + year
                + "' and b.collection_bank_id="
                + BankId
                + ")  person_neworg,"

                + "(select nvl(sum(t.org_pay)+sum(t.emp_pay),0) from aa002 t, aa001 s, ba001 b " +
                    "where t.org_id = s.id and s.orginfo_id = b.id and b.openstatus = 2 " +
                    "and substr(b.open_date,0,6)='"
                + year
                + "' and b.collection_bank_id="
                + BankId
                + ")  pay_neworg,"

                + "(select nvl(count(distinct aa201.org_id ),0) from AA201, aa001, ba001 where aa201.chg_status='2' and aa201.org_id=aa001.id and "
                + "ba001.id=aa001.orginfo_id and substr(aa201.biz_date,0,6)='"
                + year
                + "' and ba001.collection_bank_id ="
                + BankId
                + ")  org_rate,"

                + "(select  nvl(sum(aa201.emp_pay)+sum(aa201.org_pay)-sum(aa201.old_org_pay)-sum(aa201.old_emp_pay),0) from  AA201, aa001, ba001 where "
                + "aa201.chg_status='2' and aa201.org_id=aa001.id and ba001.id=aa001.orginfo_id and substr(aa201.biz_date,0,6)='"
                + year
                + "' and ba001.collection_bank_id ="
                + BankId
                + ")  pay_rate,"

                + "(select nvl(count(distinct aa202.org_id),0) from aa202, aa001, ba001  where aa202.chg_status='2' and aa202.org_id=aa001.id and ba001.id=aa001.orginfo_id"
                + " and substr(aa202.biz_date,0,6)='"
                + year
                + "' and ba001.collection_bank_id ="
                + BankId
                + " )  org_salary,"

                + "(select nvl(sum(aa203.org_pay)+sum(aa203.emp_pay)-sum(aa203.old_org_pay)-sum(aa203.old_emp_pay),0) from aa202,aa203, aa001, ba001  "
                + "where aa202.id=aa203.chng_head_id and aa202.org_id=aa001.id and ba001.id=aa001.orginfo_id and aa202.chg_status='2' and substr(aa202.biz_date,0,6)='"
                + year
                + "' and ba001.collection_bank_id="
                + BankId
                + " )  pay_salary,"

                + "(select nvl(sum(aa301.pay_money),0) from aa301,aa001,ba001 where aa301.pay_status=5 and aa301.org_id=aa001.id and aa001.orginfo_id=ba001.id "
                + "and substr(aa301.sett_date,0,6)='"
                + year
                + "' and ba001.collection_bank_id="
                + BankId
                + "and aa301.pay_type='C')  after_pay,"

                + "(select nvl(count(t.id),0) from aa305_1 t where  t.collection_bank_id="
                + BankId
                + " and t.year_month='"
                + year
                + "' and t.open_status='2' "
                + " and t.org_money>'0' and t.emp_money>'0' and (t.org_month>=1 or t.emp_month>=1) )  org_over,"

                + "(select nvl(sum(t.org_money)+sum(t.emp_money),0) from aa305_1 t where  t.collection_bank_id="
                + BankId
                + " and t.year_month='"
                + year
                + "' "
                + "and t.open_status='2' and (t.org_month>=1 or t.emp_month>=1) )  pay_over,"

                + "(select nvl(count(t.id),0) from aa305_1 t where  t.collection_bank_id="
                + BankId
                + " and t.year_month='"
                + year
                + "' and t.open_status='2' "
                + " and t.org_money>'0' and t.emp_money>'0' and (t.org_month>1 or t.emp_month>1) )  org_overs,"

                + "(select nvl(sum(t.org_money)+sum(t.emp_money),0) from aa305_1 t where  t.collection_bank_id="
                + BankId
                + " and t.year_month='"
                + year
                + "' "
                + "and t.open_status='2' and (t.org_month>1 or t.emp_month>1) ) pay_overs "

                + " from dual";
            Query query = session.createSQLQuery(hql1);

            Iterator it1 = query.list().iterator();
            Object obj[] = null;
            while (it1.hasNext()) {
              obj = (Object[]) it1.next();
              dto = new Fundbankmonthofyeardto();
              dto.setPay_fund(new BigDecimal(obj[0].toString()));
              dto.setOrg_neworg(new BigDecimal(obj[1].toString()));
              dto.setPerson_neworg(new BigDecimal(obj[2].toString()));
              dto.setPay_neworg(new BigDecimal(obj[3].toString()));
              dto.setOrg_rate(new BigDecimal(obj[4].toString()));
              dto.setPay_rate(new BigDecimal(obj[5].toString()));
              dto.setOrg_salary(new BigDecimal(obj[6].toString()));
              dto.setPay_salary(new BigDecimal(obj[7].toString()));
              dto.setAfter_pay(new BigDecimal(obj[8].toString()));
              dto.setOrg_over(new BigDecimal(obj[9].toString()));
              dto.setPay_over(new BigDecimal(obj[10].toString()));
              dto.setOrg_overs(new BigDecimal(obj[11].toString()));
              dto.setPay_overs(new BigDecimal(obj[12].toString()));

              // System.out.println("orgdao...dto.getPay_fund="+dto.getPay_fund());
              returnList.add(dto);
            }
          }

        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }
        return returnList;
      }
    });
    return list;
  }

  public String find_bank_realname(final String user) {
    String str = "";
    str = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String sql = "select t.coll_bank_name from bb105 t where t.coll_bank_id="
            + user;
        Query query = session.createSQLQuery(sql);
        return query.uniqueResult();
      }
    });
    return str;
  }

  public String getAa101ClearPerson_wsh(final String bizId) {
    String stutas = "";
    try {
      stutas = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String sql = "select aa101.clearperson from aa101 where aa101.biz_type='H' and aa101.biz_id=? ";
          Query query = session.createSQLQuery(sql);
          query.setParameter(0, bizId);
          if (query.uniqueResult() == null) {
            return "";
          } else {
            return query.uniqueResult().toString();
          }
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stutas;
  }

  public void update_ba001(final String id) throws Exception {
    try {
      Connection conn = getHibernateTemplate().getSessionFactory()
          .openSession().connection();
      Statement st = conn.createStatement();
      String sql = " update ba001  set ba001.tijiao='1' where ba001.id in(select t.orginfo_id from aa001 t where t.id="
          + id + ") ";
      st.executeUpdate(sql);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
