package org.xpup.hafmis.syscollection.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.xpup.hafmis.common.util.BusiTools;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTaListDTO;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTbListDTO;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTcListDTO;
import org.xpup.hafmis.syscollection.common.biz.loankoupop.dto.LoanKouPopDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.CollLoanbackTail;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;

/**
 * @author ���ƽ 2007-12-18
 */
public class CollLoanbackTailDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * 
   * @param id
   * @return
   */
  public CollLoanbackTail queryById(Integer id) {
    Validate.notNull(id);
    return (CollLoanbackTail) getHibernateTemplate().get(
        CollLoanbackTail.class, id);
  }

  /**
   * �����¼
   * 
   * @param collLoanbackTail
   * @return
   */
  public Serializable insert(CollLoanbackTail collLoanbackTail) {
    Serializable id = null;
    try {
      Validate.notNull(collLoanbackTail);
      id = getHibernateTemplate().save(collLoanbackTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  /**
   * �����𻹴�--����ҳ��
   * 
   * @author ���ƽ 2007-12-18 ��ѯ�б��е�������� ��ѯ����:id
   */
  public List queryCollLoanbackTaListById(final String id,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws Exception{
    List list = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.id,aa411.loan_kou_acc,aa002.org_id,aa411.emp_id,ba002.name,aa002.pre_balance+aa002.cur_balance as balance ,aa411.should_corpus"
            + ",aa411.year_month,aa411.flag,aa411.contract_id "
            + "from AA002 aa002,BA002 ba002,AA411 aa411 "
            + "where aa002.id=aa411.emp_id "
            + "and aa002.emp_info_id =ba002.id and aa411.org_id=aa002.org_id and aa411.collflag=1 " + "and aa411.head_id= ? ";//

        String ob = orderBy;
        if (ob == null)
          ob = "aa411.loan_kou_acc";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + "order by " + ob + " " + od;

        Query query = session.createSQLQuery(hql);
        query.setString(0, id);

        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);

        List queryList = query.list();

        if (queryList == null || queryList.size() == 0) {
          query.setFirstResult(pageSize * (page - 2));
          queryList = query.list();
        }

        Iterator it = queryList.iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        int i=0;
        int j=0;
        BigDecimal aa=new BigDecimal(0.00);
        
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackTaListDTO collLoanbackTaListDTO = new CollLoanbackTaListDTO();
            if (obj[0] != null) {
              collLoanbackTaListDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              collLoanbackTaListDTO.setKouLoanAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTaListDTO.setOrgid(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTaListDTO.setEmpid(BusiTools.convertSixNumber(obj[3].toString()));
            }
            if (obj[4] != null) {
              collLoanbackTaListDTO.setBorrowerName(obj[4].toString());
            }
            if (obj[5] != null) {
              collLoanbackTaListDTO.setBalance(obj[5].toString());
            }
            if (obj[6] != null) {
              collLoanbackTaListDTO.setShouldCorpus(new BigDecimal(obj[6]
                                                                       .toString()));
            }
            if (obj[7] != null) {
              collLoanbackTaListDTO.setYearMonth(obj[7].toString());
            }
            if (obj[8] != null) {
              collLoanbackTaListDTO.setFlag(obj[8].toString());
            }
            if (obj[9] != null) {
              collLoanbackTaListDTO.setContractId(obj[9].toString());
            }
            if(!collLoanbackTaListDTO.getKouLoanAcc2().equals(collLoanbackTaListDTO.getKouLoanAcc())){
              
              collLoanbackTaListDTO.setKouLoanAcc2(collLoanbackTaListDTO.getKouLoanAcc());
              j++;
            }
            i++;
            collLoanbackTaListDTO.setP_count(""+i);
            collLoanbackTaListDTO.setC_count(""+j);
          
            temp_list.add(collLoanbackTaListDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * �����𻹴�--����ҳ��
   * 
   * @author ���ƽ 2007-12-18 ��ѯ�б��е��������--count ��ѯ����:id
   */
  public List queryCollLoanbackTaListCountById(final String id) throws Exception {
  List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.id,aa411.loan_kou_acc,aa002.org_id,aa411.emp_id,ba002.name,aa002.pre_balance+aa002.cur_balance as balance ,aa411.should_corpus"
          + ",aa411.year_month,aa411.flag,aa411.contract_id "
            + "from AA002 aa002,BA002 ba002,AA411 aa411 "
            + "where aa002.id=aa411.emp_id "
            + "and aa002.emp_info_id =ba002.id and aa411.org_id=aa002.org_id and aa411.collflag=1 " + "and aa411.head_id= ? ";//

        Query query = session.createSQLQuery(hql);
        query.setString(0, id);

        List queryList = query.list();

        Iterator it = queryList.iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        int i=0;
        int j=0;
        BigDecimal aa=new BigDecimal(0.00);
        
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackTaListDTO collLoanbackTaListDTO = new CollLoanbackTaListDTO();
            if (obj[0] != null) {
              collLoanbackTaListDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              collLoanbackTaListDTO.setKouLoanAcc(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTaListDTO.setOrgid(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTaListDTO.setEmpid(BusiTools.convertSixNumber(obj[3].toString()));
            }
            if (obj[4] != null) {
              collLoanbackTaListDTO.setBorrowerName(obj[4].toString());
            }
            if (obj[5] != null) {
              collLoanbackTaListDTO.setBalance(obj[5].toString());
            }
            if (obj[6] != null) {
              collLoanbackTaListDTO.setShouldCorpus(new BigDecimal(obj[6]
                                                                       .toString()));
            }
            if (obj[7] != null) {
              collLoanbackTaListDTO.setYearMonth(obj[7].toString());
            }
            if (obj[8] != null) {
              collLoanbackTaListDTO.setFlag(obj[8].toString());
            }
            if (obj[9] != null) {
              collLoanbackTaListDTO.setContractId(obj[9].toString());
            }
            if(!collLoanbackTaListDTO.getKouLoanAcc2().equals(collLoanbackTaListDTO.getKouLoanAcc())){
              
              collLoanbackTaListDTO.setKouLoanAcc2(collLoanbackTaListDTO.getKouLoanAcc());
              j++;
            }
            i++;
            collLoanbackTaListDTO.setP_count(""+i);
            collLoanbackTaListDTO.setC_count(""+j);
          
            temp_list.add(collLoanbackTaListDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-19 ͨ��empid��ѯaa002�и�ְ���ı������+������� ��ѯ����:empid
   */
  public Object[] queryBalanceByEmpId(final String EmpId,final String orgId) throws Exception{
    Object[] obj = new Object[4];
    obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = " select t.pre_Balance+t.cur_balance,t.PRE_BALANCE,t.ORG_PAY + t.EMP_PAY,t.CUR_BALANCE,t.ORG_ID,t.pay_oldyear from aa002 t where t.id=? and t.ORG_ID=? ";

        Query query = session.createSQLQuery(hql);
        query.setInteger(0, Integer.parseInt(EmpId));
        query.setInteger(1, Integer.parseInt(orgId));
        return query.uniqueResult();
      }
    });
    return obj;
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-20 ͨ�������˺Ų�ѯ��ͬ���PL111 ��ѯ����:loanKouAcc
   */
  public String queryContractIdByLoanKouAcc(final String loanKouAcc) throws Exception {
    String contractId = "";
    contractId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select pl111.contract_id from PL111 pl111 where pl111.contract_st='11' and pl111.loan_kou_acc=? ";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, loanKouAcc);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return contractId;
  }

  
  public String querybankname(final String loanKouAcc) throws Exception {
    String contractId = "";
    contractId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select distinct a.coll_bank_name from aa410 c,bb105 a where c.batch_num=? and c.loanbankid=a.coll_bank_id";

            Query query = session.createSQLQuery(hql);
            query.setParameter(0, loanKouAcc);
            Object obj = query.uniqueResult();
            if (obj != null) {
              return query.uniqueResult().toString();
            } else {
              return query.uniqueResult();
            }
          }
        });
    return contractId;
  }
  
  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-20 ͨ����ͬ��Ų�ѯ�»���ϢPL115 ��ѯ����:contractId
   */
  public Object queryCorpusInterestByContractId(final String contractId) throws Exception {
    Object obj = null;
    obj = (Object) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select pl115.corpus_interest from PL115 pl115 where pl115.contract_id=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, contractId);

        return query.uniqueResult();
      }
    });
    return obj;
  }

  /**
   * �����𻹴�����(���) ���ݵ���
   * 
   * @param headId
   * @param loanKouAcc
   * @param contractId
   * @param borrowerName
   * @param cardNum
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param page
   * @return
   */
  public List queryExportFlowTail(final String headId) throws Exception {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.loan_kou_acc,"
            + "ba002.name,"
            + "aa411.year_month,"
            + "aa411.should_corpus,"
            + "(select nvl(sum(a411.pre_corpus + a411.current_corpus), 0) from AA411 a411 where a411.head_id = aa411.head_id " +
                "and a411.id=aa411.id), " 
            + "aa411.org_id "
            + "from AA411 aa411,BA002 ba002,AA002 aa002 "
            + "where aa411.should_corpus > 0 "
            + "and aa411.org_id=aa002.org_id "
            + "and aa411.emp_id = aa002.id "
            + "and aa002.emp_info_id = ba002.id " + "and aa411.head_id = ? ";
        hql = hql + "order by aa411.loan_kou_acc DESC ";
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, headId);

        Iterator it = query.list().iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
          if(obj[1]!=null)
          dto.setBorrowerName(obj[1].toString());
          if(obj[2]!=null)
          dto.setLoanKouYearmonth(obj[2].toString());
          if(obj[0]!=null)
          dto.setLoanKouAcc(obj[0].toString());
          if(obj[3]!=null)
          dto.setShouldCorpus(new BigDecimal(obj[3].toString()));
          if(obj[4]!=null)
          dto.setRealMoney(new BigDecimal(obj[4].toString()));
          if(obj[5]!=null)
          dto.setOrgId(obj[5].toString());
          temp_list.add(dto);
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-20 ͨ�������˺Ų�ѯ��λ���pl400 ��ѯ����:loanKouAcc
   */
  public String queryOrgIdByLoanKouAcc(final String loanKouAcc) throws Exception {
    String orgId = "";
    orgId = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select pl400.org_id from PL400 pl400 where pl400.loan_kou_acc=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, loanKouAcc);

        return query.uniqueResult();
      }
    });
    return orgId;
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-21
   *         ͨ����λ��Ų�ѯsum��AA307�и�ORG_ID�µ����е�PICK_PRE_BALANC��PICK_CUR_BALANCE��
   *         ��ѯ����:ORG_ID
   */
  public Object[] queryPickBalanceByOrgId(final String orgId) throws Exception {
    Object[] obj = new Object[2];
    obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select sum(aa307.PICK_PRE_BALANCE+aa307.PICK_CUR_BALANCE) as money1,sum(aa307.PICK_PRE_INTEREST+aa307.PICK_CUR_INTEREST)  as money2 "
            + "from AA306 aa306,AA307 aa307  where aa306.ID=aa307.PICKHEAD_ID and aa306.ORG_ID=? ";

        Query query = session.createSQLQuery(hql);
        query.setString(0, orgId);

        return query.uniqueResult();
      }
    });
    return obj;
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-22 ͨ�������˺Ų�ѯaa411���еĵ�λ���,ְ����� ��ѯ����:loanKouAcc
   */
  public List queryEmpIdByLoanKouAcc(final String loanKouAcc) throws Exception {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.org_id,aa411.emp_id from AA411 aa411 where aa411.loan_kou_acc=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, loanKouAcc);

        List temp_list = new ArrayList();
        Object[] obj = null;
        Iterator iterate = query.list().iterator();

        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          CollLoanbackTail collLoanbackTail = new CollLoanbackTail();
          if (obj != null) {
            if (obj[0] != null) {
              collLoanbackTail.setOrgId(obj[0].toString());
            }
            if (obj[1] != null) {
              collLoanbackTail.setEmpId(obj[1].toString());
            }
          }
          temp_list.add(collLoanbackTail);
        }
        return temp_list;
      }
    });
    return list;
  }

  public String getreal_count(final String loanKouAcc) throws Exception {
    String orgId = "";
    orgId = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select nvl(count(a.id),0) from aa411 a where a.head_id in (select t.id from aa410 t where t.batch_num=?) and (a.pre_corpus>0 or a.current_corpus>0) and a.collflag = '1'";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, loanKouAcc);

        return ""+query.uniqueResult();
      }
    });
    return orgId;
  }
  public String getno_count(final String loanKouAcc) throws Exception {
    String orgId = "";
    orgId = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
      SQLException {
        String hql = "select nvl(count(a.id),0) from aa411 a where a.head_id in (select t.id from aa410 t where t.batch_num=?) and a.collflag = '0' ";
        
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, loanKouAcc);
        
        return ""+query.uniqueResult();
      }
    });
    return orgId;
  }
  
  public String getreal_kou_money(final String loanKouAcc) throws Exception {
    String orgId = "";
    orgId = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select nvl(sum(a.pre_corpus)+sum(a.current_corpus),0) from aa411 a where a.head_id in (select t.id from aa410 t where t.batch_num=?) and (a.pre_corpus>0 or a.current_corpus>0) and a.collflag = '1' ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, loanKouAcc);

        return ""+query.uniqueResult();
      }
    });
    return orgId;
  }
  public String getno_kou_money(final String loanKouAcc) throws Exception {
    String orgId = "";
    orgId = (String) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
      SQLException {
        String hql = "select nvl(sum(a.should_corpus),0) from aa411 a where a.head_id in (select t.id from aa410 t where t.batch_num=?) and a.collflag = '0'";
        
        Query query = session.createSQLQuery(hql);
        query.setParameter(0, loanKouAcc);
        
        return ""+query.uniqueResult();
      }
    });
    return orgId;
  }
  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-21
   *         ͨ��ְ����Ų�ѯAA307�и�emp_ID�µ����е�PICK_PRE_BALANC��PICK_CUR_BALANCE
   *         ��ѯ����:emp_ID
   */
  public Object[] queryPickBalanceByEmpId(final String empId) throws Exception {
    Object[] obj = new Object[2];
    obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select sum(pickTail.pickSalary),sum(pickTail.pickInterest) from PickTail pickTail where pickTail.empId=? ";

        Query query = session.createQuery(hql);
        query.setParameter(0, new Integer(empId));

        return query.uniqueResult();
      }
    });
    return obj;
  }

  /**
   * �����𻹴�--������
   * 
   * @author ���ƽ 2007-12-22 ��ѯ�б��е�������� ��ѯ����:bank,batchNum
   */
  public List queryBatchNumPopList(final String batchNum,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws Exception {
    List list = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.id,aa411.emp_id,aa411.org_id,aa411.pre_corpus," +
        "aa411.current_corpus,aa411.contract_id,aa411.loan_kou_acc,aa411.should_corpus " +
        "from AA411 aa411,AA410 aa410 where aa410.id=aa411.head_id and aa411.collflag=1 and aa410.batch_num=? ";

        String ob = orderBy;
        if (ob == null)
          ob = "aa411.loan_kou_acc";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + "order by " + ob + " " + od;

        Query query = session.createSQLQuery(hql);
        query.setString(0, batchNum);
        
        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);

        List queryList = query.list();

        if (queryList == null || queryList.size() == 0) {
          query.setFirstResult(pageSize * (page - 2));
          queryList = query.list();
        }

        Iterator it = queryList.iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            LoanKouPopDTO loanKouPopDTO = new LoanKouPopDTO();
            if (obj[0] != null) {
              loanKouPopDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              loanKouPopDTO.setEmpId(BusiTools.convertSixNumber(obj[1].toString()));
            }
            if (obj[2] != null) {
              loanKouPopDTO.setOrgId(obj[2].toString());
            }
            if (obj[3] != null) {
              loanKouPopDTO.setKouPreBalance(new BigDecimal(obj[3]
                  .toString()));
            }
            if (obj[4] != null) {
              loanKouPopDTO.setKouCurBalance(new BigDecimal(obj[4]
                  .toString()));
            }
            if (obj[5] != null) {
              loanKouPopDTO.setContractId(obj[5].toString());
            }
            if (obj[6] != null) {
              loanKouPopDTO.setLoanKouAcc(obj[6].toString());
            }
            if (obj[7] != null) {
              loanKouPopDTO.setShouldKouBalance(new BigDecimal(obj[7].toString()));
            }
            
            loanKouPopDTO.setKouBalance(loanKouPopDTO.getKouPreBalance().add(loanKouPopDTO.getKouCurBalance()));
            
            temp_list.add(loanKouPopDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }
  /**
   * �����𻹴�--ά��ҳ��
   * 
   * @author ���ƽ 2007-12-25 ��ѯ�б��е�������� ��ѯ����:bank,batchNum
   */
  public List queryCollLoanbackTbList(final String officeCode,final String bank, final String batchNum,
      final String orderBy, final String order, final int start,
      final int pageSize, final int page,final String startdate,final String enddate) throws Exception {
    List list = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa410.id,aa410.loanbankid, " + "aa410.batch_num, "
            + "aa410.status,aa410.biz_date "
            + "from AA410 aa410";
        Vector parameters = new Vector();
        String criterion = "";
        
        if (bank != null && !bank.equals("")) {
          criterion += "aa410.loanbankid = ? and ";
          parameters.add(bank);
        }else if(officeCode != null && !officeCode.equals("")){
              criterion += "aa410.loanbankid in (select distinct bb105.coll_bank_id " +
              " from BB105 bb105 " +
              "where bb105.office =? " +
              "and bb105.status='1') and ";
              parameters.add(officeCode);
        }
        
        if (batchNum != null && !batchNum.equals("")) {
          criterion += "aa410.batch_num = ? and ";
          parameters.add(batchNum);
        }
        
        if (startdate != null && !startdate.equals("")) {
          criterion += "aa410.biz_date >= ? and ";
          parameters.add(startdate);
        }
        
        if (enddate != null && !enddate.equals("")) {
          criterion += "aa410.biz_date <= ? and ";
          parameters.add(enddate);
        }
        
        if (criterion.length() != 0)
          criterion = " where "+criterion.substring(0, criterion.lastIndexOf("and"));
        
        String ob = orderBy;
        if (ob == null)
          ob = "aa410.id ";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + " order by aa410.id desc";

        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);

        List queryList = query.list();

        if (queryList == null || queryList.size() == 0) {
          query.setFirstResult(pageSize * (page - 2));
          queryList = query.list();
        }

        Iterator it = queryList.iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackTbListDTO collLoanbackTbListDTO = new CollLoanbackTbListDTO();
            if (obj[0] != null) {
              collLoanbackTbListDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              collLoanbackTbListDTO.setLoanBankId(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTbListDTO.setBatchNum(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTbListDTO.setStatus(obj[3].toString());
            }
            if (obj[4] != null) {
              collLoanbackTbListDTO.setBizdate(obj[4].toString());
            }
            temp_list.add(collLoanbackTbListDTO);
          }
          String headid = obj[0].toString();
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * �����𻹴�--������
   * 
   * @author ���ƽ 2007-12-22 ��ѯ�б��е�������� 
   */
  public List queryBatchNumPopListCount(final String batchNum) throws Exception {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.id " +
        "from AA411 aa411,AA410 aa410 where aa410.id=aa411.head_id and aa411.collflag=1 and aa410.batch_num=? ";

        Query query = session.createSQLQuery(hql);
        query.setString(0, batchNum);
        
        return query.list();
      }
    });
    return list;
  }
  
  /**
   * �����𻹴�--ά��ҳ��--count
   * 
   * @author ���ƽ 2007-12-22 ��ѯ�б��е�������� 
   */
  public List queryCollLoanbackTbListCount(final String officeCode,final String bank, final String batchNum,final String startdate,final String enddate) throws Exception {
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa410.id,aa410.loanbankid, " + "aa410.batch_num, "
        + "aa410.status,aa410.biz_date "
        + "from AA410 aa410";

        Vector parameters = new Vector();
        String criterion = "";
        
        if (bank != null && !bank.equals("")) {
          criterion += "aa410.loanbankid = ? and ";
          parameters.add(bank);
        }else if(officeCode != null && !officeCode.equals("")){
          criterion += "aa410.loanbankid in (select distinct bb105.coll_bank_id " +
          " from BB105 bb105 " +
          "where bb105.office =? " +
          "and bb105.status='1') and ";
          parameters.add(officeCode);
        }
        
        if (batchNum != null && !batchNum.equals("")) {
          criterion += "aa410.batch_num = ? and ";
          parameters.add(batchNum);
        }
        
        if (startdate != null && !startdate.equals("")) {
          criterion += "aa410.biz_date >= ? and ";
          parameters.add(startdate);
        }
        
        if (enddate != null && !enddate.equals("")) {
          criterion += "aa410.biz_date <= ? and ";
          parameters.add(enddate);
        }
        
        if (criterion.length() != 0)
          criterion = " where "+criterion.substring(0, criterion.lastIndexOf("and"));
        
        hql = hql + criterion ;

        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        List queryList = query.list();

        Iterator it = queryList.iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackTbListDTO collLoanbackTbListDTO = new CollLoanbackTbListDTO();
            if (obj[0] != null) {
              collLoanbackTbListDTO.setId(obj[0].toString());
            }
            if (obj[1] != null) {
              collLoanbackTbListDTO.setLoanBankId(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTbListDTO.setBatchNum(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTbListDTO.setStatus(obj[3].toString());
            }
            if (obj[4] != null) {
              collLoanbackTbListDTO.setBizdate(obj[4].toString());
            }
            temp_list.add(collLoanbackTbListDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }

  /**
   * �����𻹴�--ά��ҳ��
   * 
   * @author ���ƽ 2007-12-22 aa410��״̬=2���ۿ���ж�AA306�ж�Ӧ�ļ�¼״̬=5 ��ѯ����:batchNum
   */
  public List is_exist(final String batchNum)  throws Exception{
    List list = new ArrayList();
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa306.id "
            + "from AA410 aa410,AA306 aa306 "
            + "where aa410.batch_num=aa306.batch_num "
            + "and aa306.pick_satatus=5 " + "and aa410.status=2 "
            + "and aa410.batch_num=?";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, batchNum);

        return query.list();
      }
    });
    return list;
  }

  /**
   * ����orginfo���officecode��bb101���е�officename
   */
  public String findOfficeNameByOrgInfoOfficeCode(final String id) throws Exception {
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
   * �����𻹴� ���ƽ 2007.12.22 ����headIdɾ��aa411��aa410���¼
   * 
   * @param headId
   */
  public void deleteCollLoanback(final String headId) throws Exception {
      getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        // ɾ��aa411
        String hql = "delete CollLoanbackTail collLoanbackTail where collLoanbackTail.headId=?";
        Query query1 = session.createQuery(hql);
        query1.setParameter(0, headId);
        query1.executeUpdate();
        // ɾ��aa410
        String hql1 = "delete CollLoanbackHead collLoanbackHead where collLoanbackHead.id=?";
        Query query2 = session.createQuery(hql1);
        query2.setParameter(0, new Integer(headId));
        query2.executeUpdate();
        return null;
      }
    });
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-22 ͨ�����κŲ�ѯ306id ��ѯ����:batchNum
   */
  public List queryHeadId(final String batchNum) throws Exception {
    List list = null;
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa306.id from AA306 aa306 where aa306.batch_num=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, batchNum);

        return query.list();
      }
    });
    return list;
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-22 ͨ��101����BIZ_ID,SPECAILTYPE,BIZ_TYPE=Dɾ��aa101,aa102
   *         ��ѯ����:bizId
   */
  public void deleteOrgFlowAndEmpFlow(final String bizId) throws Exception{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
        // �õ�AA102�н�Ҫɾ����¼������
        String sql = "";
        sql = "select a102.id from aa101 a101,aa102 a102 "
            + "where a101.id=a102.org_flow_id and a101.specailtype='2' and a101.biz_type='D' and a101.biz_id=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, new Integer(bizId));
        List list = query.list();
        for (int i = 0; i < list.size(); i++) {
          Object obj = list.get(i);
          String empFlowId = obj.toString();
          // ɾ��aa102
          String hql = "delete EmpHAFAccountFlow empHAFAccountFlow where empHAFAccountFlow.orgHAFAccountFlow.id=?";
          Query query1 = session.createQuery(hql);
          query1.setParameter(0, new Integer(empFlowId));
          query1.executeUpdate();
        }

        // ɾ��aa101
        String hql1 = "delete OrgHAFAccountFlowDrawing orgHAFAccountFlowDrawing "
            + "where orgHAFAccountFlowDrawing.specailType='2' and orgHAFAccountFlowDrawing.bizId=?";
        Query query2 = session.createQuery(hql1);
        query2.setParameter(0, new BigDecimal(bizId));
        query2.executeUpdate();
        return null;
      }
    });
  }

  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-22 ͨ��aa319����BIZID,TYPE=Dɾ��aa319 ��ѯ����:bizId
   */
  public void deleteBizLog(final String bizId) throws Exception {
      getHibernateTemplate().execute(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql1 = "delete PickBizActivityLog bizActivityLog "
            + "where  bizActivityLog.bizid=?";
        Query query2 = session.createQuery(hql1);
        query2.setParameter(0, new Integer(bizId));
        query2.executeUpdate();
        return null;
      }
    });
  }
  /**
   * �����𻹴�
   * 
   * @author ���ƽ 2007-12-25 ͨ�����κŲ�ѯaa411���е���Ϣ ��ѯ����:batchNum
   */
  public List queryByBatchNum(final String batchNum) throws Exception {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.id,aa411.emp_id,aa411.org_id,aa411.pre_corpus," +
            "aa411.current_corpus,aa411.contract_id,aa411.loan_kou_acc,aa411.head_id " +
            "from AA411 aa411,AA410 aa410 where aa410.id=aa411.head_id and aa411.collflag=1 and aa410.batch_num=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, batchNum);

        List temp_list = new ArrayList();
        Object[] obj = null;
        Iterator iterate = query.list().iterator();

        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          CollLoanbackTail collLoanbackTail = new CollLoanbackTail();
          if (obj != null) {
            if (obj[0] != null) {
              collLoanbackTail.setId(new Integer(obj[0].toString()));
            }
            if (obj[1] != null) {
              collLoanbackTail.setEmpId(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTail.setOrgId(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTail.setPreCorpus(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              collLoanbackTail.setCurrentCorpus(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              collLoanbackTail.setContractId(obj[5].toString());
            }
            if (obj[6] != null) {
              collLoanbackTail.setLoanKouAcc(obj[6].toString());
            }
            if (obj[7] != null) {
              collLoanbackTail.setHeadId(obj[7].toString());
            }
          }
          temp_list.add(collLoanbackTail);
        }
        return temp_list;
      }
    });
    return list;
  }
  public List queryByBatchNum_yl(final String batchNum) throws Exception {
    List list = new ArrayList();
    list = (List) getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select aa411.id,aa411.emp_id,aa411.org_id,aa411.pre_corpus," +
            "aa411.current_corpus,aa411.contract_id,aa411.loan_kou_acc,aa411.head_id " +
            "from AA411 aa411,AA410 aa410 where aa410.id=aa411.head_id and aa411.collflag=0 and aa410.status=1 and aa410.batch_num=? ";

        Query query = session.createSQLQuery(hql);
        query.setParameter(0, batchNum);

        List temp_list = new ArrayList();
        Object[] obj = null;
        Iterator iterate = query.list().iterator();

        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          CollLoanbackTail collLoanbackTail = new CollLoanbackTail();
          if (obj != null) {
            if (obj[0] != null) {
              collLoanbackTail.setId(new Integer(obj[0].toString()));
            }
            if (obj[1] != null) {
              collLoanbackTail.setEmpId(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTail.setOrgId(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTail.setPreCorpus(new BigDecimal(obj[3].toString()));
            }
            if (obj[4] != null) {
              collLoanbackTail.setCurrentCorpus(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              collLoanbackTail.setContractId(obj[5].toString());
            }
            if (obj[6] != null) {
              collLoanbackTail.setLoanKouAcc(obj[6].toString());
            }
            if (obj[7] != null) {
              collLoanbackTail.setHeadId(obj[7].toString());
            }
          }
          temp_list.add(collLoanbackTail);
        }
        return temp_list;
      }
    });
    return list;
  }
  /**
   * ��ѯ�鼯����
   * @return
   */
  public CollBank getCollBankByCollBankid(final String collBankid){
    CollBank  collBank=null;
    collBank = (CollBank) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session) throws SQLException,
              HibernateException {
              String sql = " from CollBank collBank where collBank.status=1 and collBank.collBankId = ? ";
              Query query = session.createQuery(sql);
              query.setParameter(0, new Integer(collBankid.toString().trim()));

              
              return query.uniqueResult();
          }
        });
    return collBank;
    }
  
  public List queryCollLoanbackTcList(final String officeName,final String bankName, final String orgId,
      final String empId,final String orgName, final String empName,final String contractId,
      final String bizDate,final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws Exception {
    List list = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a001.id id1,b001.name name1,a002.id,b002.name,a411.pre_corpus + a411.current_corpus," +
            "a411.contract_id,p110.borrower_name,a410.biz_date " +
            "from aa411 a411,aa001 a001,aa002 a002,ba001 b001,ba002 b002,pl110 p110,aa410 a410 " +
            "where a001.orginfo_id = b001.id " +
            "and a001.id = a002.org_id " +
            "and a002.emp_info_id = b002.id " +
            "and a411.emp_id = b002.id " +
            "and a411.org_id = a001.id " +
            "and a411.contract_id = p110.contract_id " +
            "and a411.head_id = a410.id " +
            "and a410.status = '2' ";

        Vector parameters = new Vector();
        String criterion = "";
        
        if (bankName != null && !bankName.equals("")) {
          criterion += "b001.collection_bank_id = ? and ";
          parameters.add(bankName);
        }else if(officeName != null && !officeName.equals("")){
              criterion += "b001.collection_bank_id in (select distinct bb105.coll_bank_id " +
              " from BB105 bb105 " +
              "where bb105.office =? " +
              "and bb105.status='1') and ";
              parameters.add(officeName);
        }
        
        if (orgId != null && !orgId.equals("")) {
          criterion += "a001.id = ? and ";
          parameters.add(orgId);
        }
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "b001.name = ? and ";
          parameters.add(orgName);
        }
        
        if (empId != null && !empId.equals("")) {
          criterion += "a002.id = ? and ";
          parameters.add(empId);
        }
        
        if (empName != null && !empName.equals("")) {
          criterion += "b002.name = ? and ";
          parameters.add(empName);
        }
        
        if (contractId != null && !contractId.equals("")) {
          criterion += "a411.contract_id = ? and ";
          parameters.add(contractId);
        }
        
        if (bizDate != null && !bizDate.equals("")) {
          criterion += "a410.biz_date = ? and ";
          parameters.add(bizDate);
        }
        
        if (criterion.length() != 0)
          criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));
        
        String ob = orderBy;
        if (ob == null)
          ob = "a001.id";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + " order by " + ob + " " + od;

        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }

        query.setFirstResult(start);
        if (pageSize > 0)
          query.setMaxResults(pageSize);

        List queryList = query.list();

        if (queryList == null || queryList.size() == 0) {
          query.setFirstResult(pageSize * (page - 2));
          queryList = query.list();
        }

        Iterator it = queryList.iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackTcListDTO collLoanbackTcListDTO = new CollLoanbackTcListDTO();
            if (obj[0] != null) {
              collLoanbackTcListDTO.setOrgId(obj[0].toString());
            }
            if (obj[1] != null) {
              collLoanbackTcListDTO.setOrgName(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTcListDTO.setEmpId(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTcListDTO.setEmpName(obj[3].toString());
            }
            if (obj[4] != null) {
              collLoanbackTcListDTO.setCheckMoney(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              collLoanbackTcListDTO.setContractId(obj[5].toString());
            }
            if (obj[6] != null) {
              collLoanbackTcListDTO.setBorrowerName(obj[6].toString());
            }
            if (obj[7] != null) {
              collLoanbackTcListDTO.setBizDate(obj[7].toString());
            }
            
            temp_list.add(collLoanbackTcListDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }
  
  public List queryCollLoanbackTcCount(final String officeName,final String bankName, final String orgId,
      final String empId,final String orgName, final String empName,final String contractId,
      final String bizDate,final String orderBy, final String order, final int start,
      final int pageSize, final int page) throws Exception {
    List list = new ArrayList();
    Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
        || "DESC".equalsIgnoreCase(order));
    Validate.isTrue(start >= 0);
    list = getHibernateTemplate().executeFind(new HibernateCallback() {
      public Object doInHibernate(Session session) throws HibernateException,
          SQLException {
        String hql = "select a001.id,b001.name,a002.id,b002.name,a411.pre_corpus + a411.current_corpus," +
            "a411.contract_id,p110.borrower_name,a410.biz_date " +
            "from aa411 a411,aa001 a001,aa002 a002,ba001 b001,ba002 b002,pl110 p110,aa410 a410 " +
            "where a001.orginfo_id = b001.id " +
            "and a001.id = a002.org_id " +
            "and a002.emp_info_id = b002.id " +
            "and a411.emp_id = b002.id " +
            "and a411.org_id = a001.id " +
            "and a411.contract_id = p110.contract_id " +
            "and a411.head_id = a410.id " +
            "and a410.status = '2' ";

        Vector parameters = new Vector();
        String criterion = "";
        
        if (bankName != null && !bankName.equals("")) {
          criterion += "b001.collection_bank_id = ? and ";
          parameters.add(bankName);
        }else if(officeName != null && !officeName.equals("")){
              criterion += "b001.collection_bank_id in (select distinct bb105.coll_bank_id " +
              " from BB105 bb105 " +
              "where bb105.office =? " +
              "and bb105.status='1') and ";
              parameters.add(officeName);
        }
        
        if (orgId != null && !orgId.equals("")) {
          criterion += "a001.id = ? and ";
          parameters.add(orgId);
        }
        
        if (orgName != null && !orgName.equals("")) {
          criterion += "b001.name = ? and ";
          parameters.add(orgName);
        }
        
        if (empId != null && !empId.equals("")) {
          criterion += "a002.id = ? and ";
          parameters.add(empId);
        }
        
        if (empName != null && !empName.equals("")) {
          criterion += "b002.name = ? and ";
          parameters.add(empName);
        }
        
        if (contractId != null && !contractId.equals("")) {
          criterion += "a411.contract_id = ? and ";
          parameters.add(contractId);
        }
        
        if (bizDate != null && !bizDate.equals("")) {
          criterion += "a410.biz_date = ? and ";
          parameters.add(bizDate);
        }
        
        if (criterion.length() != 0)
          criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));
        
        String ob = orderBy;
        if (ob == null)
          ob = "a001.id";

        String od = order;
        if (od == null)
          od = "DESC";

        hql = hql + criterion + " order by " + ob + " " + od;

        Query query = session.createSQLQuery(hql);
        for (int i = 0; i < parameters.size(); i++) {
          query.setParameter(i, parameters.get(i));
        }
        
        List queryList = query.list();

        Iterator it = queryList.iterator();
        List temp_list = new ArrayList();
        Object obj[] = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj != null) {
            CollLoanbackTcListDTO collLoanbackTcListDTO = new CollLoanbackTcListDTO();
            if (obj[0] != null) {
              collLoanbackTcListDTO.setOrgId(obj[0].toString());
            }
            if (obj[1] != null) {
              collLoanbackTcListDTO.setOrgName(obj[1].toString());
            }
            if (obj[2] != null) {
              collLoanbackTcListDTO.setEmpId(obj[2].toString());
            }
            if (obj[3] != null) {
              collLoanbackTcListDTO.setEmpName(obj[3].toString());
            }
            if (obj[4] != null) {
              collLoanbackTcListDTO.setCheckMoney(new BigDecimal(obj[4].toString()));
            }
            if (obj[5] != null) {
              collLoanbackTcListDTO.setContractId(obj[5].toString());
            }
            if (obj[6] != null) {
              collLoanbackTcListDTO.setBorrowerName(obj[6].toString());
            }
            if (obj[7] != null) {
              collLoanbackTcListDTO.setBizDate(obj[7].toString());
            }
            
            temp_list.add(collLoanbackTcListDTO);
          }
        }
        return temp_list;
      }
    });
    return list;
  }
  public String[] queryCollLoanByInfo(
      final String headId) {
    String CollLoanByInfo[] = new String[3];
    try {
      CollLoanByInfo = (String[]) getHibernateTemplate().execute(
          new HibernateCallback() {

            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {

              String hql = "select count(distinct aa411.contract_id),count(aa411.id),sum(aa411.pre_corpus+aa411.current_corpus)  from aa411 where aa411.head_id="+headId+" and aa411.collflag='1'";

              Query query = session.createSQLQuery(hql);
              String str[] = new String[3];

              if (query.list() != null) {
                Object obj[] = new Object[3];
                obj = (Object[]) query.list().get(0);
                if (obj[0] != null && !"".equals(obj[0])) {
                  str[0] = obj[0].toString();
                } else {
                  str[0] = "";
                }
                if (obj[1] != null && !"".equals(obj[1])) {
                  str[1] = obj[1].toString();
                } else {
                  str[1] = "";
                }
                if (obj[2] != null && !"".equals(obj[2])) {
                  str[2] = obj[2].toString();
                } else {
                  str[2] = "";
                }
                return str;
              } else {
                String s[] = new String[2];
                s[0] = "";
                s[1] = "";
                s[2] = "";
                return s;
              }
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return CollLoanByInfo;
  }
  public int queryTranoutCount(final String orgId, final String empId) {
    Integer count = null;
    try {
      count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " select count(a309.id)" 
              + " from aa309 a309, aa310 a310"
              + " where a309.id = a310.tran_out_head_id"
              + " and a309.tran_status <> 5 and a309.out_org_id = ?"
              + " and a310.emp_id = ?";
          Query query = session.createSQLQuery(hql);
          query.setParameter(0, orgId);
          query.setParameter(1, empId);
          return Integer.valueOf(query.uniqueResult().toString());
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count.intValue();
  }
}
