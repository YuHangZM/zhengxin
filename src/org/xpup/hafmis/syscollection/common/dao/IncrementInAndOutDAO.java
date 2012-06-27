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
import org.xpup.hafmis.syscollection.common.domain.entity.IncrementInAndOut;

public class IncrementInAndOutDAO extends HibernateDaoSupport {
  /**
   * �����¼
   * 
   * @param PartPickupCondition
   * @return
   */
  public Serializable insert(IncrementInAndOut incrementInAndOut) {
    Serializable id = null;
    try {
      Validate.notNull(incrementInAndOut);
      id = getHibernateTemplate().save(incrementInAndOut);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * �鼯ʹ�ò�ѯ
   * 
   * @author ���
   * @pram condition String @ 2007-12-10
   * @return List
   */
  public List findCollectionuseinfo(final SecurityInfo securityInfo,final String officeCode,
      final String collectionBankId, final String curTime1,
      final String curTime2,final String lastTime)
      throws Exception, BusinessException {
        List list = getHibernateTemplate().executeFind(new HibernateCallback() {
          public Object doInHibernate(Session session) throws HibernateException,
              SQLException {
        String myCondition=" and ba001.officecode in ("+officeCode+") and ba001.collection_bank_id in ("+collectionBankId+")";
        List returnList =new ArrayList ();
        String yeara=curTime1.substring(0, 4);
        String hql="select"+
/* 101��λ */         "(select nvl(sum(aa001.org_rate),0)  from BA001 ba001,AA001 aa001 where ba001.open_date<='"+curTime2+"' and ba001.open_date>='"+curTime1+"' and aa001.pay_mode='1' and ba001.id=aa001.orginfo_id "+myCondition+" and ba001.id "+securityInfo.getGjjSecuritySQL()+"),"+
/* 102���� */         "(select nvl(sum(aa001.Emp_Rate),0) from BA001 ba001,AA001 aa001 where ba001.open_date<='"+curTime2+"' and ba001.open_date>='"+curTime1+"' and aa001.pay_mode='1' and ba001.id=aa001.orginfo_id "+myCondition+" and ba001.id "+securityInfo.getGjjSecuritySQL()+") ,"+

/* 103����Ӧ��ְ���� */ "(select nvl(count(aa002.id),0)  from BA002 ba002,AA002 aa002 where ba002.opendate>='"+curTime1+"' and ba002.opendate<='"+curTime2+"' and aa002.pay_status in ('1','3','4') and ba002.id=aa002.emp_info_id  and aa002.org_id "+securityInfo.getGjjSecuritySQL()+") ,"+
/* 104����ʵ��ְ���� */ "(select nvl(count(distinct aa101.id),0)  from BA002 ba002,AA002 aa002,AA101 aa101,AA102 aa102 where ba002.opendate>='"+curTime1+"' and ba002.opendate<='"+curTime2+"' and ba002.id=aa002.emp_info_id and aa102.org_flow_id= aa101.id and aa101.biz_status='5' and aa101.biz_type in ('A','B','M') and aa002.id=aa102.emp_id and aa101.org_id "+securityInfo.getGjjSecuritySQL()+") ,"+
/* 105����ĩ�ɴ���� */ "(((select nvl(sum(aa101.credit),0)  from AA101 aa101 where aa101.sett_date <= '"+lastTime+"' and aa101.biz_type in ('A', 'B','K','G') and aa101.biz_status='5' and aa101.org_id "+securityInfo.getGjjSecuritySQL()+")-(select nvl(sum(aa101.debit),0) from AA101 aa101 where  aa101.sett_date <= '"+lastTime+"' and aa101.biz_type in ('K', 'G') and aa101.biz_status='5' and aa101.org_id "+securityInfo.getGjjSecuritySQL()+"))),"+        
/* 106����ʵ�ɴ�� */   
/* ʵ�ɴ�� */      "((select nvl(sum(aa101.credit),0)  from AA101 aa101 where aa101.sett_date >= '"+curTime1+"' and aa101.sett_date <= '"+curTime2+"' and aa101.biz_type in ('A', 'B','K','G') and aa101.biz_status='5' and aa101.org_id "+securityInfo.getGjjSecuritySQL()+")-(select nvl(sum(aa101.debit),0) from AA101 aa101 where aa101.sett_date >= '"+curTime1+"' and aa101.sett_date <= '"+curTime2+"' and aa101.biz_type in ('K', 'G') and aa101.biz_status='5' and aa101.org_id "+securityInfo.getGjjSecuritySQL()+")),"+
/* ��Ϣ���� */      "(select nvl(sum(aa318.end_pre_balance+aa318.end_cur_balance),0)  from AA318 aa318,AA316 aa316 where aa318.year='"+yeara+"' and aa318.sett_head_id=aa316.id and aa316.org_id "+securityInfo.getGjjSecuritySQL()+") ,"+
/* �ʽ�ת�� */      "(select nvl(sum(aa101.credit),0)  from AA101 aa101 where aa101.sett_date >= '"+curTime1+"' and aa101.sett_date <= '"+curTime2+"' and aa101.biz_type='F' and aa101.biz_status='5' and aa101.org_id "+securityInfo.getGjjSecuritySQL()+"),"+
/*107����ĩ�ۼƸ�����ȡ��*/
/* ֧ȡ��� */      "((select nvl(sum(aa101.debit),0)  from AA101 aa101 where aa101.sett_date >= '"+curTime1+"' and aa101.sett_date <= '"+curTime2+"' and aa101.biz_type in ('D','L') and aa101.biz_status='5')-(select nvl(sum(aa101.credit),0) from AA101 aa101 where aa101.sett_date >= '"+curTime1+"' and aa101.sett_date <= '"+curTime2+"' and aa101.biz_type = 'L' and aa101.biz_status='5' and aa101.org_id "+securityInfo.getGjjSecuritySQL()+")),"+
/* �ʽ�ת�� */      "(select nvl(sum(aa101.debit),0)  from AA101 aa101 where aa101.sett_date >= '"+curTime1+"' and aa101.sett_date <= '"+curTime2+"' and aa101.biz_type='E' and aa101.biz_status='5' and aa101.org_id "+securityInfo.getGjjSecuritySQL()+") ,"+
/*108���У����ڸ�����ȡ��*/
/* ֧ȡ��� */
/*109����ĩ�ɴ��ܶ�*/
/* ����ĩ�ɴ���� *//* ����ʵ�ɴ�� *//* �ʽ�ת�� */
/*110����ĩ�ɴ����*/
/* ����ĩ�ɴ���� *//* ����ʵ�ɴ�� *//*----*//*����ĩ�ۼƸ�����ȡ��*/
/*111����ĩ����ס���������˻���*/"(select nvl(count(distinct(aa002.id)),0) from AA002 aa002,Ba002 ba002 where ba002.opendate<='"+curTime2+"'  and aa002.emp_info_id=ba002.id and aa002.pay_status in ('1','2','3','4')),"+
/*112���У�����ĩ����˻���*/"(select nvl(count(distinct(aa002.id)),0) from AA002 aa002,Ba002 ba002 where ba002.opendate<='"+curTime2+"'  and aa002.emp_info_id=ba002.id and aa002.pay_status = '2') ,"+
/**/
/**/

/*301����ĩ�������--12*/"(select nvl(sum(pl111.overplus_loan_money),0) from PL111 pl111,PL202 pl202,PL203 pl203 where pl202.biz_date<'"+curTime1+"' and pl203.contract_id = pl111.contract_id and pl202.flow_head_id = pl203.flow_head_id and pl202.biz_st = '6' and ((pl202.biz_type = '1') or (pl202.biz_type = '12' and pl202.wrong_biz_type = '1'))),"+

/*302���ڷ��Ŷ�*/"(select nvl(sum(pl202.OCCUR_MONEY),0) from PL202 pl202 where pl202.biz_date>='"+curTime1+"' and pl202.biz_date<='"+curTime2+"' and pl202.biz_st='6' and pl202.biz_type ='1' or (pl202.biz_type='12' and pl202.wrong_biz_type='1') ) ,"+
/*303���ڻ��ն�*/"(select nvl(sum((pl202.REAL_CORPUS+pl202.REAL_OVERDUE_CORPUS)),0) from PL202 pl202 where pl202.biz_date>='"+curTime1+"' and pl202.biz_date<='"+curTime2+"' and pl202.biz_st='6') ,"+
/*304����ĩ�����ܶ�*/"(select nvl(sum(pl202.occur_money),0) from PL202 pl202 where pl202.biz_date<='"+curTime2+"' and pl202.biz_st='6' and pl202.biz_type ='1' or (pl202.biz_type='12' and pl202.wrong_biz_type='1')) ,"+
/*305����ĩ�������*/
/*����ĩ�������+���ڷ��Ŷ�-���ڻ��ն�*/
/*306���У����ڴ����16*/"(select nvl(sum(pl205.owe_corpus+pl205.owe_interest+pl205.punish_interest),0) from PL205 pl205 where pl205.owe_date<='"+curTime2+"') as a4,"+
/*307���ڷŴ�����17*/"(select nvl(sum(pl202.SHOULD_COUNT),0) from PL202 pl202 where pl202.biz_date>='"+curTime1+"' and pl202.biz_date<='"+curTime2+"' and pl202.biz_st='6' and pl202.biz_type='1') ,"+
/*308����ĩ�ۼƷŴ�����18*/"(select nvl(sum(pl202.SHOULD_COUNT),0) from PL202 pl202 where pl202.biz_date<='"+curTime2+"' and pl202.biz_st='6' and pl202.biz_type='1') ,"+
/*309���У����ڴ������19*/"(select nvl(count(distinct pl205.contract_id),0) from PL205 pl205 where pl205.owe_date<='"+curTime2+"') ," +
/* 1count(aa001.id)20*/"(select count(aa001.id) from BA001 ba001,AA001 aa001 where ba001.open_date<='"+curTime2+"' and ba001.open_date>='"+curTime1+"' and aa001.pay_mode='1' and ba001.id=aa001.orginfo_id "+myCondition+" and ba001.id "+securityInfo.getGjjSecuritySQL()+") "+                      
                    " from dual";
/**
 * ����ĩ������310����53.04
���ڷ��Ŷ311����0
���ڻ��ն312����0
����ĩ������313����53.04
 * 
 * */
        Query query = session.createSQLQuery(hql);
        Iterator it1=query.list().iterator();
        Object obj[] = null;
        while(it1.hasNext()){
          obj = (Object[]) it1.next();
        }
        for(int i=0;i<obj.length;i++){
          returnList.add(new BigDecimal(obj[i].toString()));
        }
        return returnList;
      }
    });
    return list;
  }
  /**
   * ��������б�
   * 
   * @author ���
   * @pram condition String @ 2007-12-10
   * @return List
   */
  public List findAccountList(){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            List queryList=new ArrayList();           
            String hql="select fn101.book_id,fn101.book_name from FN101 fn101 where fn101.book_st='1'";                                              
            Query query = session.createSQLQuery(hql);                
            Iterator iter= query.list().iterator();
            Object obj[] = null;
            while(iter.hasNext()){
              obj=(Object[]) iter.next();
              queryList.add(new org.apache.struts.util.LabelValueBean(obj[1].toString(), obj[0].toString()));
            }
            return queryList;
          }
        });
    return list;
  }
  /**
   * ���AA400������Ϣ
   * 
   * @author ���
   * @pram condition String @ 2007-12-10
   * @return List
   */
  public List findAA400List(){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {
            List queryList=new ArrayList();           
            String hql="select * from AA400 aa400";                                              
            Query query = session.createSQLQuery(hql);                
            Iterator iter= query.list().iterator();
            Object obj[] = null;
            while(iter.hasNext()){
              obj=(Object[]) iter.next();
              queryList.add(obj[2].toString());
            }
            return queryList;
          }
        });
    return list;
  }
  /**
   * ����ά��
   * ɾ�� AA004 ��
   * @author ��� 2007-10-10
   */
  public void deleteAA004Param() {
    try {
      getHibernateTemplate().execute(new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException,
            SQLException {
          String hql = "delete IncrementInAndOut ";
          session.createQuery(hql).executeUpdate();
          return null;
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * ��Ŀ�����Ƿ���ڣ����Ѿ�����-���ض�Ӧ��subjectId,������-����""
   * 
   * @param code
   * @param states
   * @param securityInfo
   * @return
   */
  public String is_CodeIn_WL(final String code, final String states,
      final SecurityInfo securityInfo) throws BusinessException {
    String subjectId = "";
    subjectId = (String) getHibernateTemplate().execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException, SQLException {
            String hql = "select subject.subjectCode from Subject subject ";
            Vector parameters = new Vector();
            String criterion = "";

            criterion += "subject.bookId = ?  and ";
            parameters.add(securityInfo.getBookId());

            if (code != null && !code.equals("")) {
              criterion += "subject.subjectCode = ?  and ";
              parameters.add(code);
            }
            if (states != null && !states.equals("")) {
              criterion += "subject.subjectSt = ?  and ";
              parameters.add(states);
            }
            if (criterion.length() != 0)
              criterion = "where "
                  + criterion.substring(0, criterion.lastIndexOf("and"));
            hql = hql + criterion;
            Query query = session.createQuery(hql);

            for (int i = 0; i < parameters.size(); i++) {
              query.setParameter(i, parameters.get(i));
            }
            if (query.uniqueResult() != null) {
              return query.uniqueResult().toString();
            }
            return null;
          }
        });
    return subjectId;
  }

  /**
   * ����ҵ������
   * 
   * @author ���
   * @pram  @ 2007-12-10
   * @return List
   */
  public List findYeWuShouRu(final String curTime1,
      final String curTime2,final String subject_code,final String code){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {  
            List queryList=new ArrayList(); 
            String yeara=curTime1.substring(0, 4);
            String yearb=yeara+"0101";
            String hql="select fn110.balance_direction,nvl(fn201.debit,0),nvl(fn201.credit,0) from FN201 fn201,FN400 fn400,AA400 aa400,FN110 fn110 where fn201.CREDENCE_DATE>='"+yearb+"' and fn201.CREDENCE_DATE<='"+curTime2+"' and fn201.BOOK_ID=fn400.BOOK_ID and fn201.SUBJECT_CODE='"+code+"'";
            
            Query query = session.createSQLQuery(hql);                
            Iterator iter= query.list().iterator();
            Object obj[] = null;
            BigDecimal shouru =new BigDecimal(0);
            while(iter.hasNext()){
              obj=(Object[]) iter.next();
              String balance_direction=obj[0].toString();
              BigDecimal debit=new BigDecimal(obj[1].toString());
              BigDecimal credit=new BigDecimal(obj[2].toString());
              
              if(balance_direction.equals("0")){//��
                /*201����ҵ������*/
                shouru=shouru.add(debit.subtract(credit));
              }else if(balance_direction.equals("1")){//��
                /*201����ҵ������*/
                shouru=shouru.add(credit.subtract(debit));
              }
            }
            queryList.add(shouru);
            return queryList;
          }
        });
    return list;
  }
  /**
   * ����ҵ��֧��
   * 
   * @author ���
   * @pram  @ 2007-12-10
   * @return List
   */
  public List findYeWuZhiChu(final String curTime1,
      final String curTime2,final String subject_code,final String code){
    List list= getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {  
            List queryList=new ArrayList(); 
            String yeara=curTime1.substring(0, 4);
            String yearb=yeara+"0101";
            String hql="select fn110.balance_direction,nvl(fn201.debit,0),nvl(fn201.credit,0) from FN201 fn201,FN400 fn400,AA400 aa400,FN110 fn110 where fn201.CREDENCE_DATE>='"+yearb+"' and fn201.CREDENCE_DATE<='"+curTime2+"' and fn201.BOOK_ID=fn400.BOOK_ID and fn201.SUBJECT_CODE='"+code+"'";
            
            Query query = session.createSQLQuery(hql);                
            Iterator iter= query.list().iterator();
            Object obj[] = null;
            BigDecimal zhichu =new BigDecimal(0);
            while(iter.hasNext()){
              obj=(Object[]) iter.next();
              String balance_direction=obj[0].toString();
              BigDecimal debit=new BigDecimal(obj[1].toString());
              BigDecimal credit=new BigDecimal(obj[2].toString());
              
              if(balance_direction.equals("0")){//��
                /*202����ҵ��֧��*/
                zhichu=zhichu.add(debit.subtract(credit));
              }else if(balance_direction.equals("1")){//��
                /*202����ҵ��֧��*/
                zhichu=zhichu.add(credit.subtract(debit));
              }
            }
            queryList.add(zhichu);
            return queryList;
          }
        });
    return list;
  }
  /**
   * ������ȡ�������
   * 
   * @author ���
   * @pram  @ 2007-12-10
   * @return List
   */
  public List findFeiYong(final String curTime1,
      final String curTime2,final String subject_code,final String code){
    List list=getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException { 
            List queryList=new ArrayList(); 
            String yeara=curTime1.substring(0, 4);
            String yearb=yeara+"0101";
            String hql="select fn110.balance_direction,nvl(fn201.debit,0),nvl(fn201.credit,0) from FN201 fn201,FN400 fn400,AA400 aa400,FN110 fn110 where fn201.CREDENCE_DATE>='"+yearb+"' and fn201.CREDENCE_DATE<='"+curTime2+"' and fn201.BOOK_ID=fn400.BOOK_ID and fn201.SUBJECT_CODE='"+code+"'";
                
            Query query = session.createSQLQuery(hql);                
            Iterator iter= query.list().iterator();
            Object obj[] = null;
            BigDecimal shouru =new BigDecimal(0);
            while(iter.hasNext()){
              obj=(Object[]) iter.next();
              String balance_direction=obj[0].toString();
              BigDecimal debit=new BigDecimal(obj[1].toString());
              BigDecimal credit=new BigDecimal(obj[2].toString());
              
              if(balance_direction.equals("0")){//��
                /*201����ҵ������*/
                shouru=shouru.add(debit);
              }else if(balance_direction.equals("1")){//��
                /*201����ҵ������*/
                shouru=shouru.add(credit);
              }
            }
            queryList.add(shouru);
            return queryList;
          }
        });
    return list;
  }
  /**
   * ����ĩ����׼�����ܶ�
   * 
   * @author ���
   * @pram  @ 2007-12-10
   * @return List
   */
  public List findFengXianAll(final String curTime1,
      final String curTime2,final String subject_code,final String code){
    List list=getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {  
            List queryList=new ArrayList(); 
            String hql="select fn110.balance_direction,nvl(fn201.debit,0),nvl(fn201.credit,0) from FN201 fn201,FN400 fn400,AA400 aa400,FN110 fn110 where fn201.CREDENCE_DATE<='"+curTime2+"' and fn201.BOOK_ID=fn400.BOOK_ID and fn201.SUBJECT_CODE='"+code+"'";
            
            Query query = session.createSQLQuery(hql);                
            Iterator iter= query.list().iterator();
            Object obj[] = null;
            BigDecimal shouru =new BigDecimal(0);
            while(iter.hasNext()){
              obj=(Object[]) iter.next();
              String balance_direction=obj[0].toString();
              BigDecimal debit=new BigDecimal(obj[1].toString());
              BigDecimal credit=new BigDecimal(obj[2].toString());
              
              if(balance_direction.equals("0")){//��
                /*205����ĩ����׼�����ܶ�*/
                shouru=shouru.add(debit);
              }else if(balance_direction.equals("1")){//��
                /*205����ĩ����׼�����ܶ�*/
                shouru=shouru.add(credit);
              }
            }
            queryList.add(shouru);
            return queryList;
          }
        });
    return list;
  }
  /**
   * ����ĩ����׼�������
   * 
   * @author ���
   * @pram  @ 2007-12-10
   * @return List
   */
  public List findFengXianYU(final String curTime1,
      final String curTime2,final String subject_code,final String code){
    List list=getHibernateTemplate().executeFind(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
          throws HibernateException, SQLException {  
            List queryList=new ArrayList(); 
            String hql="select fn110.balance_direction,nvl(fn201.debit,0),nvl(fn201.credit,0) from FN201 fn201,FN400 fn400,AA400 aa400,FN110 fn110 where fn201.CREDENCE_DATE<='"+curTime2+"' and fn201.BOOK_ID=fn400.BOOK_ID and fn201.SUBJECT_CODE='"+code+"'";
            
            Query query = session.createSQLQuery(hql);                
            Iterator iter= query.list().iterator();
            Object obj[] = null;
            BigDecimal shouru =new BigDecimal(0);
            while(iter.hasNext()){
              obj=(Object[]) iter.next();
              String balance_direction=obj[0].toString();
              BigDecimal debit=new BigDecimal(obj[1].toString());
              BigDecimal credit=new BigDecimal(obj[2].toString());
              
              if(balance_direction.equals("0")){//��
                /*205����ĩ����׼�����ܶ�*/
                shouru=shouru.add(debit.subtract(credit));
              }else if(balance_direction.equals("1")){//��
                /*205����ĩ����׼�����ܶ�*/
                shouru=shouru.add(credit.subtract(debit));
              }
            }
            queryList.add(shouru);
            return queryList;
          }
        });
    return list;
  }
  
}
