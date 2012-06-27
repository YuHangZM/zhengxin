package org.xpup.hafmis.sysloan.common.dao;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBank;
import org.xpup.hafmis.sysloan.dataready.bank.dto.BankDTO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.dto.BankQueryDTO;

/**
 * ������Ϣ��PL002
 * 
 *@author ���
 *2007-9-13
 */
public class LoanBankDAO extends HibernateDaoSupport {
  /**
   * ����������ѯ
   * @param id
   * @return
   */
  public LoanBank queryById(Serializable id) {  
    Validate.notNull(id);
    return  (LoanBank) getHibernateTemplate().get(LoanBank.class,id);    
  }
  /**
   * �����¼
   * 
   * @param loanBank
   * @return
   */
  public Serializable insert(LoanBank loanBank) {
    Serializable id = null;
    try {
      Validate.notNull(loanBank);
      id = getHibernateTemplate().save(loanBank);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * name ����
   * ����������Ϣ��
   */
  public void update(LoanBank loanBank) {
    try {
      Validate.notNull(loanBank);
      getHibernateTemplate().update(loanBank);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * name ����
   * ͨ��id ɾ��������Ϣ���¼
   * return boolean
   * �ɹ� true
   * ʧ�� false
   */
  public void delete_YM(final Integer id)
  {
    try{
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) {
          String sql="delete from LoanBank loanBank where loanBank.id= ? ";
          Query query=session.createQuery(sql);
          query.setParameter(0,id);
          return new Integer(query.executeUpdate());
          }
        });
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  
  /**
   * name ����
   * ���������Ϣ��(pl002)ȫ��������ʾ�б�
   * ���б�� ���´� ����ί�д����˺� ������Ϣ�˺� �����г� �г��绰 ��ϵ�� ��ϵ�˵绰 ��ϵ��ְ�� ����״̬
   * @param orderBy ������ 
   * @param order ��������
   * @param start ��ʼ
   * @param pageSize ÿҳ��ʾ����
   * return List;
   */
  public List findLoanBank_YM(final String orderBy,
      final String order,final int start,final int pageSize,final int page)
  {
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.id," +              //id
              "loanBank.loanBankId," +                     //���б��
              "loanBank.office," +                        //���´�
              "loanBank.loanAcc," +                       //����ί�д����˺�
              "loanBank.interestAcc," +                   //������Ϣ�˺�
              "loanBank.bankPrisident," +                 //�����г�
              "loanBank.bankPrisidentTel," +              //�г��绰
              "loanBank.contactPrsn," +                   //��ϵ��
              "loanBank.contactTel," +                    //��ϵ�˵绰
              "loanBank.business," +                      //��ϵ��ְ��
              "loanBank.loanBnakSt ," +                    //����״̬
              "loanBank.outAccount," +                      //��ϵ��ְ��
              "loanBank.inAccount " +                    //����״̬
              "from  LoanBank loanBank ";
          String ob = orderBy;
          if(ob == null){
            ob = " loanBank.office,loanBank.loanBankId,loanBank.id ";
          }
          String or=order;
          if(or == null){
            or = "desc";
          }
          hql = hql + " order by "+ob+" "+or;
          Query query = session.createQuery(hql); 
          query.setFirstResult(start);
          if (pageSize > 0)
            query.setMaxResults(pageSize);
          
          //���и� ͷ
          List queryList=query.list();
          if(queryList==null||queryList.size()==0){           
            query.setFirstResult(pageSize*(page-2));
            if (pageSize > 0)
              query.setMaxResults(pageSize);
            queryList=query.list();
          }
          //���и� β 
          
          Iterator it=queryList.iterator();
          Object obj[]=null;
          List l =new ArrayList();

          while(it.hasNext())
          {
            obj=(Object[])it.next();
           
            LoanBank loanBank=new LoanBank();
            loanBank.setId(new Integer(obj[0].toString()));//id
            loanBank.setLoanBankId(new BigDecimal(obj[1].toString()));//���б��
            loanBank.setOffice(obj[2].toString());//���´�
            if(obj[3]!=null)
              loanBank.setLoanAcc(obj[3].toString());//����ί�д����˺�      null
            else
              loanBank.setLoanAcc("");
            if(obj[4]!=null)
              loanBank.setInterestAcc(obj[4].toString());//������Ϣ�˺�     null
            else
              loanBank.setInterestAcc("");
            if(obj[5]!=null)
              loanBank.setBankPrisident(obj[5].toString());//�����г�       null
            else
              loanBank.setBankPrisident("");
            if(obj[6]!=null)
              loanBank.setBankPrisidentTel(obj[6].toString());//�г��绰   null
            else
              loanBank.setBankPrisidentTel("");
            if(obj[7]!=null)
              loanBank.setContactPrsn(obj[7].toString());//��ϵ��           null
            else
              loanBank.setContactPrsn("");
            if(obj[8]!=null)
              loanBank.setContactTel(obj[8].toString());//��ϵ�˵绰        null 
            else
              loanBank.setContactTel("");
            if(obj[9]!=null)
              loanBank.setBusiness(obj[9].toString());//��ϵ��ְ��          null
            else
              loanBank.setBusiness("");
            loanBank.setLoanBnakSt(obj[10].toString());//����״̬
            if(obj[11]!=null)
              loanBank.setOutAccount(obj[11].toString());//��ϵ��ְ��          null
            else
              loanBank.setOutAccount("");
            if(obj[12]!=null)
              loanBank.setInAccount(obj[12].toString());//��ϵ��ְ��          null
            else
              loanBank.setInAccount("");
            l.add(loanBank);
          }          
          return l;
        }
      }
      ); 
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return list;
  }
  /**
   * name ����
   * ����������������Ϣ���¼����
   * @return int
   */
  public int findBankCount_YM()
  {
    List list=null;
    try{
      list = getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.id " +    //���б��
              "from LoanBank loanBank ";          
          Query query = session.createQuery(hql);   
          return query.list();
        }
      }
      ); 
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return list.size();
  }
  /**
   * name ����
   * �жϰ��´�&&�����Ƿ���� ������Ϣ�� PL002
   * reuturn boolean 
   * yes true
   * no false
   */
  public boolean isCheckBank_YM(final BankDTO bankDTO)
  {
    boolean is_bank=false;
    List  list=null;
    try{
      list=(List)getHibernateTemplate().executeFind(new HibernateCallback() {

        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.id " +    //���б��
              "from LoanBank loanBank where loanBank.loanBankId=? and loanBank.office=? ";                   
          Query query = session.createQuery(hql);   
          query.setParameter(0,bankDTO.getBankName());
          query.setParameter(1, bankDTO.getOffice());
          return query.list();
        }
      }
      ); 
      if(list.size()>0)
        is_bank=true;
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return is_bank;
  }
  /**
   * shiy ���ڲ��Ұ��´���Ӧ������ 2007.09.22 param officecode return list
   */
  public List queryBank_sy(final String officecode) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.loanBankId  from LoanBank loanBank where loanBank.office=? ";
          Query query = session.createQuery(hql);
          query.setString(0, officecode);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * yqf ���ڲ��Ұ��´���Ӧ������ 2007.09.22 param officecode return list
   */
  public List queryBank_yqf(final String officecode,final SecurityInfo securityInfo) {
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.loanBankId,collBank.collBankName from LoanBank loanBank,CollBank collBank" +
              " where loanBank.loanBankId=collBank.collBankId and loanBank.loanBnakSt = 1 and loanBank.office=? "+" and loanBank.loanBankId "+securityInfo.getDkSecurityHqlSQL();
          Query query = session.createQuery(hql);
          query.setString(0, officecode);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * �õ����´��¹鼯����
   * @param office
   * @return   ����
   */
 public List getCollBankList(){
   
   List officeCollBankList = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = 
             " from CollBank collBank where collBank.status = 1";   
             Query query=session.createQuery(hql);     
             return query.list();
         }
       });
   return officeCollBankList;
 }
 
  /**
   * �õ����´��¹鼯����
   * @param office
   * @return
   */
 public List getOfficeCollBankList(final String office){
   
   List officeCollBankList = (List) getHibernateTemplate().executeFind(
       new HibernateCallback() {
         public Object doInHibernate(Session session) throws SQLException,
             HibernateException {
             String hql = 
             " from CollBank collBank where collBank.status = 1 and collBank.office='"+office+"' ";   
             Query query=session.createQuery(hql);     
             return query.list();
         }
       });
   return officeCollBankList;
 }

  public List queryBank_sy(final String yearclear,final String officecode){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.loanBankId  from LoanBank loanBank where loanBank.office=? and loanBank.yearClear=?";
          Query query = session.createQuery(hql);
          query.setString(0, officecode);
          query.setString(1, yearclear);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * �������б�Ų�ѯ����ί�д����˺š�������Ϣ�˺�
   * ����ά����ӡ
   * jj
   * @param loanBankId
   * @return
   */
  public List queryLoanBankAccByBankId_LJ(final String loanBankId){
    List list = new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.loanAcc,loanBank.interestAcc from LoanBank loanBank where loanBank.loanBankId = ? ";
          Query query = session.createQuery(hql);
          query.setString(0, loanBankId);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * ���д��۵���ģ��
   * �������д����ѯ��Ӧ�İ��´�IDΪ������ƾ֤��
   * jj 2007-10-15
   * @param loanBankId
   * @return
   */
  public String queryOfficeCodeByBankId_LJ(final String loanBankId){
    String officeCode = "";
    try {
      officeCode = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.office from LoanBank loanBank where loanBank.loanBankId = ? ";
          Query query = session.createQuery(hql);
          query.setString(0, loanBankId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return officeCode;
  }
  /**
   * author wsh
   * 2007-10-09
   * �ſ����в�ѯ
   * @param office
   * @param bankQueryDTOId
   * @param bankPrisident
   * @param contactPrsn
   * @param orderBy
   * @param order
   * @param start
   * @param pageSize
   * @param officeList
   * @param bankList
   * @return
   */
  public List queryBankQueryList_wsh(final String office,
      final String loanBankId,final String  bankPrisident,final String contactPrsn, final String orderBy, final String order, final int start, final int pageSize,final List officeList,final List bankList){
    List list=null;
    try {
      Validate.isTrue(order == null || "ASC".equalsIgnoreCase(order)
          || "DESC".equalsIgnoreCase(order));
      Validate.isTrue(start >= 0);
      list =getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql ="select a.id id," +
                "a.loan_bank_id   loanbankid,"+//���б��
                "a.loan_acc       loanacc,"+                            //����ί�д����˺�
                "a.interest_acc   interestacc,"+                        //������Ϣ�˺�
                "a.bank_prisident bankprisident,"+                      //�����г�
                "a.bank_prisident_tel bankprisidenttel,"+               //�г��绰
                "a.contact_prsn contractprsn,"+                         //��ϵ��
                "a.contact_tel cotracttel,"+                            //��ϵ�˵绰
                "a.business business,"+                                 //��ϵ��ְ��
                "a.office office,"+                                     //���´�
                "a.loan_bnak_st bankQueryDTOst "+                           //����״̬
                "from pl002 a where ";                        
            Vector parameters = new Vector();
            String criterion = "";          
            if (bankPrisident != null&&!bankPrisident.equals("")) {
              criterion += " a.bank_prisident=? and ";
              parameters.add(bankPrisident);
            }
            if (contactPrsn != null&&!contactPrsn.equals("")) {
              criterion += " a.contact_prsn=? and ";
              parameters.add(contactPrsn);
            }
            if (office != null&&!office.equals("")) {
              criterion += " a.office=? and ";
              parameters.add(office);
            }
            if (officeList != null && officeList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < officeList.size(); i++) {
                criterion += " a.office = ? or ";
                parameters.add(officeList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (loanBankId != null&&!loanBankId.equals("")) {
              criterion += " a.loan_bank_id=? and ";
              parameters.add(loanBankId);
            }
            if (bankList != null && bankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < bankList.size(); i++) {
                criterion += " a.loan_bank_id = ? or ";
                parameters.add(bankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            
            if (criterion.length() != 0)   
              criterion=
              criterion.substring(0, criterion.lastIndexOf("and"));

           String ob = orderBy;
           if (ob == null)
             ob = " office,bankQueryDTOid,id ";

           String od = order;
           if (od == null)
             od = "DESC";
           
           hql = hql + criterion + "order by " + ob + " " + od ;
           Query query = session.createSQLQuery(hql);
           
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }

           query.setFirstResult(start);
           if (pageSize > 0)
             query.setMaxResults(pageSize);       
           List temp_list1=new ArrayList();
           temp_list1=query.list();           
           Iterator it=query.list().iterator();
           List temp_list=new ArrayList();
           Object obj[]=null;
           while(it.hasNext()){
             obj=(Object[])it.next();
             if(obj!=null){
               BankQueryDTO bankQueryDTO=new BankQueryDTO();
               bankQueryDTO.setId(obj[0].toString());//id
               bankQueryDTO.setLoanBankId(obj[1].toString());//���б��
              
               if(obj[2]!=null)
                 bankQueryDTO.setLoanAcc(obj[2].toString());//����ί�д����˺�      null
               else
                 bankQueryDTO.setLoanAcc("");
               if(obj[3]!=null)
                 bankQueryDTO.setInterestAcc(obj[3].toString());//������Ϣ�˺�     null
               else
                 bankQueryDTO.setInterestAcc("");
               if(obj[4]!=null)
                 bankQueryDTO.setBankPrisident(obj[4].toString());//�����г�       null
               else
                 bankQueryDTO.setBankPrisident("");
               if(obj[5]!=null)
                 bankQueryDTO.setBankPrisidentTel(obj[5].toString());//�г��绰   null
               else
                 bankQueryDTO.setBankPrisidentTel("");
               if(obj[6]!=null)
                 bankQueryDTO.setContactPrsn(obj[6].toString());//��ϵ��           null
               else
                 bankQueryDTO.setContactPrsn("");
               if(obj[7]!=null)
                 bankQueryDTO.setContactTel(obj[7].toString());//��ϵ�˵绰        null 
               else
                 bankQueryDTO.setContactTel("");
               if(obj[8]!=null)
                 bankQueryDTO.setBusiness(obj[8].toString());//��ϵ��ְ��          null
               else
                 bankQueryDTO.setBusiness("");
               if(obj[9]!=null)
                 bankQueryDTO.setOffice(obj[9].toString());//���´�          null
               else
               bankQueryDTO.setOffice(obj[2].toString());
               if(obj[10]!=null)
                 bankQueryDTO.setLoanBnakSt(obj[10].toString());//����״̬          null
               else
               bankQueryDTO.setLoanBnakSt(obj[9].toString());
              
               temp_list.add(bankQueryDTO);
               
             }
             
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
  public List queryAssistantOrgListCount_wsh(final String office,
      final String loanBankId,final String  bankPrisident,final String contactPrsn,final List officeList,final List bankList){
    List list=null;
    try {
      list =getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql ="select a.id id," +
                "a.loan_bank_id   loanbankid,"+//���б��
                "a.loan_acc       loanacc,"+                            //����ί�д����˺�
                "a.interest_acc   interestacc,"+                        //������Ϣ�˺�
                "a.bank_prisident bankprisident,"+                      //�����г�
                "a.bank_prisident_tel bankprisidenttel,"+               //�г��绰
                "a.contact_prsn contractprsn,"+                         //��ϵ��
                "a.contact_tel cotracttel,"+                            //��ϵ�˵绰
                "a.business business,"+                                 //��ϵ��ְ��
                "a.office office,"+                                     //���´�
                "a.loan_bnak_st bankQueryDTOst "+                           //����״̬
                "from pl002 a where ";                        
            Vector parameters = new Vector();
            String criterion = "";          
            if (bankPrisident != null&&!bankPrisident.equals("")) {
              criterion += " a.bank_prisident=? and ";
              parameters.add(bankPrisident);
            }
            if (contactPrsn != null&&!contactPrsn.equals("")) {
              criterion += " a.contact_prsn=? and ";
              parameters.add(contactPrsn);
            }
            if (office != null&&!office.equals("")) {
              criterion += " a.office=? and ";
              parameters.add(office);
            }
            if (officeList != null && officeList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < officeList.size(); i++) {
                criterion += " a.office = ? or ";
                parameters.add(officeList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            if (loanBankId != null&&!loanBankId.equals("")) {
              criterion += " a.loan_bank_id=? and ";
              parameters.add(loanBankId);
            }
            if (bankList != null && bankList.size() > 0) {
              criterion += "( ";
              for (int i = 0; i < bankList.size(); i++) {
                criterion += " a.loan_bank_id = ? or ";
                parameters.add(bankList.get(i).toString());
              }
              criterion = criterion.substring(0, criterion.lastIndexOf("or"));
              criterion += ") and";
            }
            
            if (criterion.length() != 0)   
              criterion=
              criterion.substring(0, criterion.lastIndexOf("and"));

          
           
           hql = hql + criterion ;
           Query query = session.createSQLQuery(hql);
           
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }   
           List temp_list1=new ArrayList();
           temp_list1=query.list();           
           Iterator it=query.list().iterator();
           List temp_list=new ArrayList();
           Object obj[]=null;
           while(it.hasNext()){
             obj=(Object[])it.next();
             if(obj!=null){
               BankQueryDTO bankQueryDTO=new BankQueryDTO();
               bankQueryDTO.setId(obj[0].toString());//id
               bankQueryDTO.setLoanBankId(obj[1].toString());//���б��
              
               if(obj[2]!=null)
                 bankQueryDTO.setLoanAcc(obj[2].toString());//����ί�д����˺�      null
               else
                 bankQueryDTO.setLoanAcc("");
               if(obj[3]!=null)
                 bankQueryDTO.setInterestAcc(obj[3].toString());//������Ϣ�˺�     null
               else
                 bankQueryDTO.setInterestAcc("");
               if(obj[4]!=null)
                 bankQueryDTO.setBankPrisident(obj[4].toString());//�����г�       null
               else
                 bankQueryDTO.setBankPrisident("");
               if(obj[5]!=null)
                 bankQueryDTO.setBankPrisidentTel(obj[5].toString());//�г��绰   null
               else
                 bankQueryDTO.setBankPrisidentTel("");
               if(obj[6]!=null)
                 bankQueryDTO.setContactPrsn(obj[6].toString());//��ϵ��           null
               else
                 bankQueryDTO.setContactPrsn("");
               if(obj[7]!=null)
                 bankQueryDTO.setContactTel(obj[7].toString());//��ϵ�˵绰        null 
               else
                 bankQueryDTO.setContactTel("");
               if(obj[8]!=null)
                 bankQueryDTO.setBusiness(obj[8].toString());//��ϵ��ְ��          null
               else
                 bankQueryDTO.setBusiness("");
               if(obj[9]!=null)
                 bankQueryDTO.setOffice(obj[9].toString());//���´�          null
               else
               bankQueryDTO.setOffice(obj[2].toString());
               if(obj[10]!=null)
                 bankQueryDTO.setLoanBnakSt(obj[10].toString());//����״̬          null
               else
               bankQueryDTO.setLoanBnakSt(obj[9].toString());
              
               temp_list.add(bankQueryDTO);
               
             }
             
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
  public List findBankQueryInfo_wsh(final String id){
    List list=null;
    try {
      list =getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql ="select a.id id," +
                "a.loan_bank_id   loanbankid,"+//���б��
                "a.loan_acc       loanacc,"+                            //����ί�д����˺�
                "a.interest_acc   interestacc,"+                        //������Ϣ�˺�
                "a.bank_prisident bankprisident,"+                      //�����г�
                "a.bank_prisident_tel bankprisidenttel,"+               //�г��绰
                "a.contact_prsn contractprsn,"+                         //��ϵ��
                "a.contact_tel cotracttel,"+                            //��ϵ�˵绰
                "a.business business,"+                                 //��ϵ��ְ��
                "a.office office,"+                                     //���´�
                "a.loan_bnak_st bankQueryDTOst "+                           //����״̬
                "from pl002 a where a.id=? ";                        
            Vector parameters = new Vector();
                               
            if (id != null&&!id.equals("")) {     
              parameters.add(id);
            }
           Query query = session.createSQLQuery(hql);
           
           for (int i = 0; i < parameters.size(); i++) {
             query.setParameter(i, parameters.get(i));
           }   
           List temp_list1=new ArrayList();
           temp_list1=query.list();           
           Iterator it=query.list().iterator();
           List temp_list=new ArrayList();
           Object obj[]=null;
           while(it.hasNext()){
             obj=(Object[])it.next();
             if(obj!=null){
               BankQueryDTO bankQueryDTO=new BankQueryDTO();
               bankQueryDTO.setId(obj[0].toString());//id
               bankQueryDTO.setLoanBankId(obj[1].toString());//���б��
              
               if(obj[2]!=null)
                 bankQueryDTO.setLoanAcc(obj[2].toString());//����ί�д����˺�      null
               else
                 bankQueryDTO.setLoanAcc("");
               if(obj[3]!=null)
                 bankQueryDTO.setInterestAcc(obj[3].toString());//������Ϣ�˺�     null
               else
                 bankQueryDTO.setInterestAcc("");
               if(obj[4]!=null)
                 bankQueryDTO.setBankPrisident(obj[4].toString());//�����г�       null
               else
                 bankQueryDTO.setBankPrisident("");
               if(obj[5]!=null)
                 bankQueryDTO.setBankPrisidentTel(obj[5].toString());//�г��绰   null
               else
                 bankQueryDTO.setBankPrisidentTel("");
               if(obj[6]!=null)
                 bankQueryDTO.setContactPrsn(obj[6].toString());//��ϵ��           null
               else
                 bankQueryDTO.setContactPrsn("");
               if(obj[7]!=null)
                 bankQueryDTO.setContactTel(obj[7].toString());//��ϵ�˵绰        null 
               else
                 bankQueryDTO.setContactTel("");
               if(obj[8]!=null)
                 bankQueryDTO.setBusiness(obj[8].toString());//��ϵ��ְ��          null
               else
                 bankQueryDTO.setBusiness("");
               if(obj[9]!=null)
                 bankQueryDTO.setOffice(obj[9].toString());//���´�          null
               else
               bankQueryDTO.setOffice(obj[2].toString());
               if(obj[10]!=null)
                 bankQueryDTO.setLoanBnakSt(obj[10].toString());//����״̬          null
               else
               bankQueryDTO.setLoanBnakSt(obj[9].toString());
              
               temp_list.add(bankQueryDTO);
               
             }
             
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
   * ���ս�ת�ж��Ƿ����
   * �������д����ѯ��Ӧ�İ��´�IDΪ������ƾ֤��
   * shiy 2007-11-10
   * @param loanBankId
   * @return
   */
  public String queryYearClearByBankId_sy(final String loanBankId){
    String yearClear = "";
    try {
      yearClear = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.yearClear from LoanBank loanBank where loanBank.loanBankId = ? ";
          Query query = session.createQuery(hql);
          query.setString(0, loanBankId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return yearClear;
  }
  
  /**
   * Description ��֤��Ǽǰ���
   * 
   * @author wangy 2007-11-12
   * @param ͨ������ID�����ſ������˺�loanAcc
   * @param ��BailenRolBS����
   * @param loanBankId
   * @return String
   */
  public String queryBailenRolTaBankAccByBankId(final String loanBankId){
    String loanAcc = "";
    try {
      loanAcc = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanBank.loanAcc from LoanBank loanBank where loanBank.loanBankId = ? ";
          Query query = session.createQuery(hql);
          query.setString(0, loanBankId);
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanAcc;
  }
  
  
//������޸�2008-3-11��ѯ    PL111
  public List queryBorrowerAccByCcontractId_wuht(final String id){
    List list = new ArrayList();
    try{
     list = (List) getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "  from BorrowerAcc borrowerAcc where borrowerAcc.contractId= ? ";

            Query query = session.createQuery(hql);
            query.setParameter(0, id);

            return query.list();
          }
        });
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
//������޸�2008-3-11LoanFlowTail��ѯ����·�
  public String queryLoanFlowTaiMaxlYearMonth_wuht(final String contractId ){
    String yearMonth=null;
    try {
      yearMonth = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select max(loanFlowTail.yearMonth) from LoanFlowTail loanFlowTail where loanFlowTail.contractId=? and loanFlowTail.loanType is not null  ";
          Query query = session.createQuery(hql);
          query.setString(0, contractId);
 
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return yearMonth;
  }
  
//������޸�2008-3-11RestoreLoan��ѯ��С�·�    
  public String queryRestoreLoanMinlYearMonth_wuht(final String contractId ){
    String yearMonth=null;
    try {
      yearMonth = (String) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select min(restoreLoan.loanKouYearmonth) from RestoreLoan restoreLoan where restoreLoan.contractId=? and restoreLoan.shouldCorpus > 0  ";
          Query query = session.createQuery(hql);
          query.setString(0, contractId);
 
          return query.uniqueResult();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return yearMonth;
  }
//������޸�2008-3-11RestoreLoan
  public List queryRestoreLoanbyCriterions_wuht(final String contractId ,final String monthYear){
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from RestoreLoan restoreLoan where restoreLoan.contractId=?  and restoreLoan.loanKouYearmonth= ? ";
          Query query = session.createQuery(hql);
          query.setString(0, contractId);
          query.setString(1, monthYear);
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
//������޸�2008-3-11
  public BigDecimal queryLoanLoanRateMaxRate_wuht(final String office,final String loanRateType ){
    BigDecimal loanMonthRate=new BigDecimal(0.00);
    try {

      loanMonthRate = (BigDecimal) getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "select loanRate.loanMonthRate from LoanRate loanRate where loanRate.loanRateId in " +
          " ( select max(pl001.loanRateId) from LoanRate pl001 where pl001.office=? and pl001.loanRateType =?)  ";
       Query query = session.createQuery(hql);
        query.setString(0, office);
       query.setString(1, loanRateType);

          return query.uniqueResult();
       
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
 
    return loanMonthRate;
  }
  
//������޸�2008-3-11Borrower    PL110
  public List queryBorrower_wuht(final String contractId ){
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "  from Borrower borrower where borrower.contractId=?   ";
          Query query = session.createQuery(hql);
          query.setString(0, contractId);
        
 
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
//������޸�2008-3-11LoanFlowTail   PL203
  public List queryLoanFlowTail_wuht(final String contractId ){
    List list=new ArrayList();
    try {
      list = getHibernateTemplate().executeFind(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = " from LoanFlowTail loanFlowTail where loanFlowTail.contractId=?  ";
          Query query = session.createQuery(hql);
          query.setString(0, contractId);
 
          return query.list();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public String queryPL002BizDate_jj(final String loanBankId) {
    String str = "";
    str =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            String sql = "select t.biz_date from PL002 t where t.loan_bank_id = ?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0,loanBankId);
            
            return query.uniqueResult();
          }
        });
    return str;
  }
  public String queryIsBoKuan(final String contactId) {
    String str = "";
    str =(String)getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            String sql = "select pl006.fund_status from pl114,pl006 where pl006.floor_id=pl114.floor_id and pl114.contract_id=?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0,contactId);
            
            return query.uniqueResult();
          }
        });
    return str;
  }
}

