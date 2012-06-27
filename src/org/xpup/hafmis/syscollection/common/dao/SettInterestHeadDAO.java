package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.dto.OrgVerAccountBalanceDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHead;

/**
 * ��Ϣ����ͷ��
 * 
 * @author ��� 2007-6-19
 */
public class SettInterestHeadDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public SettInterestHead queryById(Serializable id) {
    Validate.notNull(id);
    return (SettInterestHead) getHibernateTemplate().get(
        SettInterestHead.class, id);
  }

  /**
   * �����¼
   * 
   * @param settInterestHead
   * @return
   */
  public Serializable insert(SettInterestHead settInterestHead) {
    Serializable id = null;
    try {
      Validate.notNull(settInterestHead);
      id = getHibernateTemplate().save(settInterestHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id.toString();
  }

  /**
   * ���ݵ�λID,AA316.YEAR,��ѯAA316����ΪA(SettInterestHeadNZJX)��,����DA002
   * 
   * @author wangy 2008-02-27
   * @param orgId AA316.ORG_ID
   * @param year AA316.YEAR
   * @param ��ClearaccountBS����
   */
  public List querySettInterestHeadByOrgId(final String orgId, final String year)
      throws BusinessException {
    List list = null;
    try {
      list = (List) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select settInterestHead from SettInterestHeadNZJX settInterestHead, OrgEdition orgEdition where settInterestHead.org.id=orgEdition.orgId and orgEdition.isOrg=1 and settInterestHead.org.id=? and settInterestHead.year=?";
          Query query = session.createQuery(hql);
          query.setParameter(0, new Integer(orgId));
          query.setParameter(1, year);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * ��λ��_����ת
   * ���ݵ�λID,AA316.YEAR,����AA002,��ѯ��λ���ת����б�
   * 
   * @author wangy 2008-02-27
   * @param orgId AA316.ORG_ID
   * @param accYear ��ת���
   * @param empId ְ�����
   * @param empName ְ������
   * @param start
   * @param orderBy
   * @param order
   * @param pageSize
   * @param page
   * @param ��OrgVerAccountBalanceBS����
   */
  public List queryOVAccountBalanceListByCriterions(final String orgId,
      final String accYear, final String empId, final String empName,
      final int start, final String orderBy, final String order,
      final int pageSize, final int page) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select a002.id as empid, b002.name as empname, a318.bef_pre_balance as befprebalance, a002.pre_balance as prebalance, "
              + " a318.bef_cur_balance as befcurbalance, a002.cur_balance as curbalance, a316.year as year, a316.id as id "
              + " from ba002 b002, aa002 a002, aa318 a318,aa316 a316 "
              + " where a002.emp_info_id=b002.id  and a318.emp_id=a002.id and a316.org_id=a002.org_id and a316.id=a318.sett_head_id and a316.type = 'A' ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !"".equals(orgId)) {
            criterion += " a316.org_id = ? and ";
            parameters.add(new Integer(orgId));
          }

          if (accYear != null && !"".equals(accYear)) {
            criterion += " a316.year = ? and ";
            parameters.add(accYear);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " a002.id = ? and ";
            parameters.add(new Integer(empId));
          }

          if (empName != null && !"".equals(empName)) {
            criterion += " b002.name = ? and ";
            parameters.add(empName);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          String ob = orderBy;
          if (ob == null)
            ob = " a316.id";

          String od = order;
          if (od == null)
            od = " DESC";

          hql = hql + criterion + " order by " + ob + " " + od;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);

          Object obj[] = null;
          OrgVerAccountBalanceDTO orgVerAccountBalanceDTO = null;
          // ��ʱ����б��DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // ����ѯ�Ľ����װ��DTO��
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            orgVerAccountBalanceDTO = new OrgVerAccountBalanceDTO();
            if (obj[0] != null) {
              orgVerAccountBalanceDTO.setEmpId(obj[0].toString());
            }
            if (obj[1] != null) {
              orgVerAccountBalanceDTO.setEmpName(obj[1].toString());
            }
            if (obj[2] != null) {
              orgVerAccountBalanceDTO.setPreBalanceCen(new BigDecimal(obj[2]
                  .toString()));
            }
            if (obj[3] != null) {
              orgVerAccountBalanceDTO.setPreBalanceOrg(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              orgVerAccountBalanceDTO.setCurBalanceCen(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              orgVerAccountBalanceDTO.setCurBalanceOrg(new BigDecimal(obj[5]
                  .toString()));
            }
            if (obj[6] != null) {
              orgVerAccountBalanceDTO.setAccYear(obj[6].toString());
            }
            if (obj[7] != null) {
              orgVerAccountBalanceDTO.setId(obj[7].toString());
            }
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

  /**
   * ��λ��_����ת
   * ���ݵ�λID,AA316.YEAR,����AA002,��ѯ��λ���ת����б��¼��(count) ��ѯ ��ת���ʱ������AA102������Ϣ
   * 
   * @author wangy 2008-02-27
   * @param orgId AA316.ORG_ID
   * @param accYear ��ת���
   * @param empId ְ�����
   * @param empName ְ������
   * @param ��OrgVerAccountBalanceBS����
   */
  public List queryOVAccountBalanceAllByCriterions(final String orgId,
      final String accYear, final String empId, final String empName) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select a002.id as empid, b002.name as empname, a318.bef_pre_balance as befprebalance, a002.pre_balance as prebalance, "
              + " a318.bef_cur_balance as befcurbalance, a002.cur_balance as curbalance, a316.year as year, a316.id as id, a318.pre_interest as preinterest, a318.cur_interest as curinterest "
              + " from ba002 b002, aa002 a002, aa318 a318,aa316 a316 "
              + " where a002.emp_info_id=b002.id  and a318.emp_id=a002.id and a316.org_id=a002.org_id and a316.id=a318.sett_head_id and a316.type = 'A' ";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !"".equals(orgId)) {
            criterion += " a316.org_id = ? and ";
            parameters.add(new Integer(orgId));
          }

          if (accYear != null && !"".equals(accYear)) {
            criterion += " a316.year = ? and ";
            parameters.add(accYear);
          }

          if (empId != null && !"".equals(empId)) {
            criterion += " a002.id = ? and ";
            parameters.add(new Integer(empId));
          }

          if (empName != null && !"".equals(empName)) {
            criterion += " b002.name = ? and ";
            parameters.add(empName);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
   * ��λ��_����ת
   * ���ݵ�λID,AA316.TYPE='A',��ѯ��λ���ת���ҳ��Ľ�ת��������б�(AA316.YEAR)
   * 
   * @author wangy 2008-02-27
   * @param orgId AA316.ORG_ID
   * @param ��OrgVerAccountBalanceBS����
   */
  public List queryOVAccountBalanceAccYearList(final String orgId) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select t.year, t.org_id from aa316 t where t.type='A'";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !"".equals(orgId)) {
            criterion += " t.org_id = ? and ";
            parameters.add(new Integer(orgId));
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
          for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i, parameters.get(i));
          }

          Object obj[] = null;
          OrgVerAccountBalanceDTO orgVerAccountBalanceDTO = null;
          // ��ʱ����б��DTO
          List temp_list = new ArrayList();
          Iterator iterate = query.list().iterator();
          // ����ѯ�Ľ����װ��DTO��
          while (iterate.hasNext()) {
            obj = (Object[]) iterate.next();
            orgVerAccountBalanceDTO = new OrgVerAccountBalanceDTO();
            if (obj[0] != null) {
              orgVerAccountBalanceDTO.setAccYear(obj[0].toString());
            }
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

  /**
   * ��λ��_����ת
   * ���ݵ�λID,AA316.YEAR,����AA002,��ѯ��λ���ת����б��¼��(count) ��ѯ ��ת���ʱ������AA102������Ϣ
   * 
   * @author wangy 2008-02-27
   * @param orgId AA316.ORG_ID
   * @param accYear ��ת���
   * @param empId ְ�����
   * @param empName ְ������
   * @param ��OrgVerAccountBalanceBS����
   */
  public List queryExistsEqualsOVAccBalanceList(final String orgId,
      final String accYear, final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {

          String hql = "select a002.org_id,a002.id as empid,a316.year as year,a318.id from aa002 a002, aa318 a318,aa316 a316 "
              + " where a318.bef_pre_balance=a002.pre_balance and a318.bef_cur_balance=a002.cur_balance "
              + " and a318.emp_id=a002.id and a316.org_id=a002.org_id and a316.id=a318.sett_head_id and a316.type = 'A'";

          Vector parameters = new Vector();
          String criterion = "";

          if (orgId != null && !"".equals(orgId)) {
            criterion += " a316.org_id = ? and ";
            parameters.add(new Integer(orgId));
          }

          if (accYear != null && !"".equals(accYear)) {
            criterion += " a316.year = ? and ";
            parameters.add(accYear);
          }

          if (criterion.length() != 0)
            criterion = " and "
                + criterion.substring(0, criterion.lastIndexOf("and"));

          hql = hql + criterion;

          Query query = session.createSQLQuery(hql);
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
}
